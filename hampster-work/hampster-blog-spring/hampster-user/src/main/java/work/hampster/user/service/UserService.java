package work.hampster.user.service;

import work.hampster.user.mapper.UserMapper;
import work.hampster.user.model.User;
import work.hampster.user.transfer.UserDTO;
import work.hampster.util.AjaxResult;

import java.util.Map;

public interface UserService extends EntityService<UserMapper, User> {
    Map<String, Object> getMailCode(String username);
    String getToken();
    int getIdByToken(String utoken);

    AjaxResult login(UserDTO info) throws Exception;
}
