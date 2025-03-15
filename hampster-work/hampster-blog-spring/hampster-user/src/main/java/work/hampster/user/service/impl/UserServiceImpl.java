package work.hampster.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.minio.errors.*;
import org.jetbrains.annotations.TestOnly;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Service;
import work.hampster.transfer.RSADTO;
import work.hampster.user.mapper.UserMapper;
import work.hampster.model.User;
import work.hampster.user.service.UserService;
import work.hampster.transfer.UserDTO;
import work.hampster.util.AES;
import work.hampster.util.AjaxResult;
import work.hampster.util.Jwt;
import work.hampster.util.Minio;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class UserServiceImpl extends EntityServiceImpl<UserMapper, User> implements UserService {

    private final UserServicePlus userServicePlus;
    private final RSADTO rsaDTO;
    private final Jwt jwtUtil;

    @Autowired
    public UserServiceImpl(ServiceImpl<UserMapper, User> userServicePlus,
                           RSADTO rsaDTO, Jwt jwtUtil) {
        super(userServicePlus);
        this.userServicePlus = (UserServicePlus) userServicePlus;
        this.rsaDTO = rsaDTO;
        this.jwtUtil = jwtUtil;
//        this.properties = properties;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Value("${spring.data.sendgrid.api-key}")
    private String apiKey;

    @Override
    public Map<String, Object> getMailCode(String username) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.sendgrid.net");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        final String smtpUsername = "apikey";
        String password = apiKey;

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUsername, password);
            }
        });

        String code = getCode("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ", 6);
        String status = "fail";

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info@hampster.work"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));

            message.setSubject("HAMPSTER.WORK 验证码");

            message.setHeader("Content-Type", "text/html; charset=utf-8");
            message.setContent("<font face=\"microsoft yahei\">您的验证码为：<h2 style=\"font-family:Times New Roman;font-weight: 1000;\">" + code + "</h2></font>", "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("邮件发送成功！");
            status = "ok";
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败：" + e.getMessage());
        }

        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", BCrypt.hashpw(code.toString(), BCrypt.gensalt()));
        codeMap.put("status", status);

        return codeMap;
    }

    @Override
    public String getToken() {
        String token = getCode("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", 16);
        while (userServicePlus.exists(new QueryWrapper<User>().eq("u_token", "admin"))) {
            token = getCode("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", 16);
        }
        return token;
    }

    @Override
    public int getIdByToken(String utoken) {
        MockHttpServletRequest rq = new MockHttpServletRequest();
        rq.addParameter("u_token", utoken);
        rq.getParameter("u_token");
        User user = (User) ((List) this.cQuery(rq, 1, 1).get("data")).get(0);

        return user.getUId();
    }

    @Override
    public AjaxResult login(UserDTO info) throws Exception {
        User re = null;
        if (ObjectUtils.isNotEmpty(info.getUMail()))
            re = userServicePlus.getOne(
                new QueryWrapper<User>()
                    .eq("u_mail", info.getUMail()));
        else if (ObjectUtils.isNotEmpty(info.getUPhone()))
            re = userServicePlus.getOne(
                new QueryWrapper<User>()
                    .eq("u_phone", info.getUPhone()));
        if (ObjectUtils.isNotEmpty(re)) {
            info.setUserInfo(re);
            if (BCrypt.checkpw(AES.decrypt(info.getUPassDTO()), re.getUPass()))
//                return AjaxResult.success(re.getUToken());
                return AjaxResult.success(jwtUtil.generateUserToken(info));
            else
                return AjaxResult.error("password error");
        } else
            return AjaxResult.error("account not exist");
    }

    @Override
    public AjaxResult getUserInfo(String token, String fingerprint) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        HashMap<String, Object> map = new HashMap<>();
        Claims claims;
        try {
            claims = jwtUtil.extractAllClaims(token);
        } catch (Exception e) {
            return AjaxResult.error("token error");
        }
        if (!claims.get("fingerprint").equals(fingerprint))
            return AjaxResult.error("fingerprint error");
        else {
            map.put("userId", claims.get("user_id"));
            map.put("userName", claims.get("user_name"));

            // get profile
            User user = userServicePlus.getById((Integer) claims.get("user_id"));
            String profileURL = Minio.getTempUrl("profile/" + user.getUProfile(), 60 * 24);
            map.put("userProfile", profileURL.replace("http://mail.hampster.work:9000/",
                    "https://www.hampster.work/minio/"));

            return AjaxResult.success(map);
        }
    }

    public String getCode(String characters, int length) {
        StringBuilder code = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }


}
