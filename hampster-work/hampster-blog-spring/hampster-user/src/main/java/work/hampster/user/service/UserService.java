package work.hampster.user.service;

import work.hampster.user.mapper.UserMapper;
import work.hampster.user.model.User;

import java.util.Map;

public interface UserService extends EntityService<UserMapper, User> {
    Map<String, Object> getMailCode(String username);
    String getToken();
    int getIdByToken(String utoken);
}
