package work.hampster.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import work.hampster.blog.Config;
import work.hampster.blog.mapper.UserMapper;
import work.hampster.blog.model.User;
import work.hampster.blog.service.UserService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

@Service
public class UserServiceImpl extends EntityServiceImpl<UserMapper, User> implements UserService {
    public UserServiceImpl(ServiceImpl<UserMapper, User> service) {
        super(service);
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
        final String password = Config.getProps().getProperty("spring.data.sendgrid.api-key");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(smtpUsername, password);
            }
        });

        StringBuilder code = new StringBuilder();
        String status = "fail";

        try {
            javax.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info@hampster.work"));
            message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(username));

            String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int length = 6;

            Random random = new Random();

            for (int i = 0; i < length; i++) {
                int index = random.nextInt(characters.length());
                code.append(characters.charAt(index));
            }

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
  }
