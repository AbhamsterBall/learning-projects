package work.individual;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sendgrid.Method;
import com.sendgrid.SendGrid;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import org.springframework.beans.factory.support.*;

import static java.net.Authenticator.requestPasswordAuthentication;

public class SMSMail {
//    public static final String ACCOUNT_SID = "";
//    public static final String AUTH_TOKEN = "";

    private Properties getProps() {
        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        ClassPathResource resource = new ClassPathResource("application.yml");
        yamlFactory.setResources(resource);
        Properties props = yamlFactory.getObject();
        return props;
    }


    @Test
    void testTwilio() { // 免费测试接口
        Twilio.init(getProps().getProperty("spring.data.twilio.sid"), getProps().getProperty("spring.data.twilio.token"));

        Message message = Message.creator(
                        getProps().getProperty("spring.data.twilio.sid"),
                        new com.twilio.type.PhoneNumber("+8617882372530"), // 只能发这一个号码
                        new com.twilio.type.PhoneNumber("+15169904088"),
                        "您的验证码为：DB76SD，请在5分钟内输入。")
                .create();

        System.out.println(message.getSid());
    }

    @Test
    void testAliyunBalance() { // 余额为0以下则不发短信
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                .setAccessKeyId(getProps().getProperty("spring.data.alibaba.id"))
                .setAccessKeySecret(getProps().getProperty("spring.data.alibaba.secret"));
        config.endpoint = "business.aliyuncs.com";

        com.aliyun.bssopenapi20171214.Client client = null;
        try {
            client = new com.aliyun.bssopenapi20171214.Client(config);
            com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
            Gson gson = new Gson();
            com.aliyun.bssopenapi20171214.models.QueryAccountBalanceResponseBody.QueryAccountBalanceResponseBodyData data = client.queryAccountBalanceWithOptions(runtime).getBody().getData();
            String jsonData = gson.toJson(data);

            System.out.println(jsonData);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(jsonData);
                System.out.println(jsonNode.get("availableCashAmount"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    @Test
    public void testAliyunSMS() { // 付费项目

        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                .setAccessKeyId(getProps().getProperty("spring.data.alibaba.id"))
                .setAccessKeySecret(getProps().getProperty("spring.data.alibaba.secret"));
        config.endpoint = "dysmsapi.aliyuncs.com";

        com.aliyun.dysmsapi20170525.Client client = null;
        try {
            client = new com.aliyun.dysmsapi20170525.Client(config);
            com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                    .setPhoneNumbers("18280031397")
                    .setSignName("HAMPSTER")
                    .setTemplateCode("SMS_295390409")
                    .setTemplateParam("{\"code\":\"576349\"}");
            com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
            System.out.println(client.sendSmsWithOptions(sendSmsRequest, runtime).getBody().getMessage());
        } catch (Exception error) {
            error.printStackTrace();
        }

    }

    @Test
    public void testAddSender() {
        try {
            SendGrid sg = new SendGrid(getProps().getProperty("spring.data.sendgrid.api-key"));
            com.sendgrid.Request request = new com.sendgrid.Request();
            request.setMethod(Method.POST);
            request.setEndpoint("/verified_senders");
            request.setBody("{\"nickname\": \"info@hampster.work\", \"from_email\": \"info@hampster.work\", \"from_name\": \"info@hampster.work\", \"reply_to\": \"info@hampster.work\", \"reply_to_name\": \"info@hampster.work\", \"address\": \"1234 Fake St\", \"address2\": \"PO Box 1234\", \"state\": \"CA\", \"city\": \"San Francisco\", \"country\": \"USA\", \"zip\": \"94105\"}");
            com.sendgrid.Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) { throw new RuntimeException(ex); }
    }

    @Test
    public void tempTest() {
        String text = "<em>注</em><em>意</em>：\\r\\n        - 它会自动和旧文件合并，并且不覆盖原来的配置，所以你可以多次生成，只会覆盖之前自动生成的元素。";
        String regex = "\\\\r|\\\\n|```|###";
        String filteredText = text.replaceAll(regex, "");
        System.out.println(filteredText);
    }

    @Test
    public void sendMail() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.sendgrid.net");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        final String username = "apikey";
        final String password = getProps().getProperty("spring.data.sendgrid.api-key");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        StringBuilder code = new StringBuilder();

        try {
            javax.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("info@hampster.work"));
            message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse("ajh415@hotmail.com"));

            String characters = getProps().getProperty("spring.data.sendgrid.api-key");
            int length = 6;

            Random random = new Random();

            for (int i = 0; i < length; i++) {
                int index = random.nextInt(characters.length());
                code.append(characters.charAt(index));
            }

            message.setSubject("HAMPSTER.WORK 验证码");
            message.setText("您的验证码为：" + code);
            Transport.send(message);
            System.out.println("邮件发送成功！");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败：" + e.getMessage());
        }

    }
}
