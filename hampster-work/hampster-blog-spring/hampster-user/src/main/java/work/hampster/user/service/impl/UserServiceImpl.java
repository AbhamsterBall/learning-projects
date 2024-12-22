package work.hampster.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Service;
import work.hampster.user.Config;
import work.hampster.user.mapper.UserMapper;
import work.hampster.user.model.User;
import work.hampster.user.service.UserService;
import work.hampster.util.AjaxResult;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Service
public class UserServiceImpl extends EntityServiceImpl<UserMapper, User> implements UserService {

    final UserServicePlus userServicePlus;
    final Properties properties;

    public UserServiceImpl(ServiceImpl<UserMapper, User> service, UserServicePlus userServicePlus, Properties properties) {
        super(service);
        this.userServicePlus = userServicePlus;
        this.properties = properties;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Map<String, Object> getMailCode(String username) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.sendgrid.net");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        final String smtpUsername = "apikey";
        String password = properties.get("spring.data.sendgrid.api-key").toString();

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
    public AjaxResult login(User info) {
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
            if (BCrypt.checkpw(info.getUPass(), re.getUPass()))
                return AjaxResult.success(re.getUToken());
            else
                return AjaxResult.error("password error");
        } else
            return AjaxResult.error("account not exist");
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
