package work.hampster.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.hampster.user.mapper.UserMapper;
import work.hampster.model.User;
import work.hampster.user.service.UserService;
import work.hampster.user.util.UserRedis;
import work.hampster.transfer.UserDTO;
import work.hampster.util.AjaxResult;
import work.hampster.util.Json;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import static work.hampster.util.Json.toJson;

@RestController
//@CrossOrigin(origins = "http://localhost")
public class UserController {

    private final UserService userService;
    private final ServiceImpl<UserMapper, User> userServicePlus;

    @Autowired
    public UserController(UserService userService, ServiceImpl<UserMapper, User> userServicePlus) {
        this.userService = userService;
        this.userServicePlus = userServicePlus;
    }

    @GetMapping(value = "/user/isexist/{userBind}", produces = "application/json; charset=utf-8")
    public String isExist(@PathVariable String userBind) {
       return userBind.contains("@") ?
                userServicePlus.getBaseMapper().selectCount(new QueryWrapper<User>().eq("u_mail", userBind)) > 0 ? "true" : "false" :
                userServicePlus.getBaseMapper().selectCount(new QueryWrapper<User>().eq("u_phone", userBind)) > 0 ? "true" : "false" ;
    }

    @GetMapping(value = "/user/getmailcode/{username}", produces = "application/json; charset=utf-8")
    public String getMailCode(@PathVariable String username) {
        return UserRedis.handleMailCode(userService, username).get("status").toString();
    }

    @GetMapping(value = "/user/checkmailcode/{username}/{mailcode}", produces = "application/json; charset=utf-8")
    public String checkMailCode(@PathVariable String username, @PathVariable String mailcode) {
        return UserRedis.checkMailCode(username, mailcode);
    }

    @PostMapping(value = "/user/register", produces = "application/json; charset=utf-8")
    public String register(@RequestBody User user) { // DTO
//        User userObj = (User) UserJson.fromJsonToObject(User.class, user);
        String token = userService.getToken();
        user.setUToken(token);

        HashMap<String, String> map = new HashMap<>();
        map.put("status", userServicePlus.save(user) ? "true" : "false");
        // if status == true`K
        map.put("user_token", token);

        return Json.getGson().toJson(map);
    }

    @PutMapping(value = "/user/setname/{utoken}", produces = "application/json; charset=utf-8")
    public String setUserInfo(@PathVariable String utoken, @RequestBody User info) {
//        int uid = userService.getIdByToken(utoken);
//        User userObj = (User)Json.fromJsonToObject(User.class, info);
//        userObj.setUId(uid);
        return toJson(userServicePlus.update(
                new UpdateWrapper<User>().set("u_name", info.getUName())
                        .eq("u_token", utoken)) ?
                AjaxResult.success() : AjaxResult.error());
//        return null;
//        return userService.cUpdateById(Entity.fromEntityToRq(User.class, userObj), uid);
    }

    @PostMapping(value = "/user/login", produces = "application/json; charset=utf-8")
    public String login(@RequestBody UserDTO info) throws Exception {
        return toJson(userService.login(info));
    }

    @GetMapping(value = "/user/info", produces = "application/json; charset=utf-8")
    public String getUserInfo(@RequestParam String token) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return toJson(userService.getUserInfo(token));
    }

}
