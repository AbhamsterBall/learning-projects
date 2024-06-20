package work.hampster.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.elasticsearch.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.hampster.user.mapper.UserMapper;
import work.hampster.user.model.User;
import work.hampster.user.service.UserService;
import work.hampster.user.util.Entity;
import work.hampster.user.util.Json;
import work.hampster.user.util.Redis;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;

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

    @GetMapping(value = "/json/user/isexist/{userBind}", produces = "application/json; charset=utf-8")
    public String isExist(@PathVariable String userBind) {
       return userBind.contains("@") ?
                userServicePlus.getBaseMapper().selectCount(new QueryWrapper<User>().eq("u_mail", userBind)) > 0 ? "true" : "false" :
                userServicePlus.getBaseMapper().selectCount(new QueryWrapper<User>().eq("u_phone", userBind)) > 0 ? "true" : "false" ;
    }

    @GetMapping(value = "json/user/getmailcode/{username}", produces = "application/json; charset=utf-8")
    public String getMailCode(@PathVariable String username) {
        return Redis.handleMailCode(userService, username).get("status").toString();
    }

    @GetMapping(value = "json/user/checkmailcode/{username}/{mailcode}", produces = "application/json; charset=utf-8")
    public String checkMailCode(@PathVariable String username, @PathVariable String mailcode) {
        return Redis.checkMailCode(username, mailcode);
    }

    @PostMapping(value = "json/user/register", produces = "application/json; charset=utf-8")
    public String register(@RequestBody LinkedHashMap user) { // DTO
        User userObj = (User)Json.fromJsonToObject(User.class, user);
        String token = userService.getToken();
        userObj.setUToken(token);

        HashMap<String, String> map = new HashMap<>();
        map.put("status", userServicePlus.save(userObj) ? "true" : "false");
        // if status == true`K
        map.put("user_token", token);

        return Json.getGson().toJson(map);
    }

    @PutMapping(value = "json/user/setname/{utoken}", produces = "application/json; charset=utf-8")
    public String setUserInfo(@PathVariable String utoken, @RequestBody LinkedHashMap info) {
        int uid = userService.getIdByToken(utoken);
        User userObj = (User)Json.fromJsonToObject(User.class, info);
        userObj.setUId(uid);
        return userService.cUpdateById(Entity.fromEntityToRq(User.class, userObj), uid);
    }

}
