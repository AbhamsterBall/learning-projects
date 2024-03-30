package work.hampster.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import work.hampster.blog.mapper.UserMapper;
import work.hampster.blog.model.User;
import work.hampster.blog.service.UserService;
import work.hampster.blog.util.Redis;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(origins = "http://localhost")
public class UserJsonController {

    private final UserService userService;
    private final ServiceImpl<UserMapper, User> userServicePlus;

    @Autowired
    public UserJsonController(UserService userService, ServiceImpl<UserMapper, User> userServicePlus) {
        this.userService = userService;
        this.userServicePlus = userServicePlus;
    }

    @GetMapping(value = "/json/user/isexist/{username}", produces = "application/json; charset=utf-8")
    public String isExist(@PathVariable String username) {
        return userServicePlus.getBaseMapper().selectCount(new QueryWrapper<User>().eq("u_name", username)) > 0 ? "true" : "false";
    }

    @GetMapping(value = "json/user/getmailcode/{username}", produces = "application/json; charset=utf-8")
    public String getMailCode(@PathVariable String username) {
        return Redis.handleMailCode(userService, username).get("status").toString();
    }

    @GetMapping(value = "json/user/checkmailcode/{username}/{mailcode}", produces = "application/json; charset=utf-8")
    public String checkMailCode(@PathVariable String username, @PathVariable String mailcode) {
        return Redis.checkMailCode(username, mailcode);
    }

}
