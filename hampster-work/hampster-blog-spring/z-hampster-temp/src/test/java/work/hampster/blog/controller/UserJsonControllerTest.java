package work.hampster.blog.controller;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import work.hampster.blog.mapper.UserMapper;
import work.hampster.blog.model.User;
import work.hampster.blog.service.UserService;
import work.hampster.blog.util.Redis;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserJsonControllerTest {

    private final UserService userService;
    private final ServiceImpl<UserMapper, User> userServicePlus;

    @Autowired
    UserJsonControllerTest(UserService userService, ServiceImpl<UserMapper, User> userServicePlus) {
        this.userService = userService;
        this.userServicePlus = userServicePlus;
    }

    @Test
    public void testMail() {
        String token = "awefuy89776231";
        String username = "ajh415@hotmail.com";
        Map<String, Object> re = (Map<String, Object>) Redis.readAndWrite(token + "_getmailcode", () -> userService.getMailCode(username), 15);
        System.out.println(re.get("status").toString());
    }

}