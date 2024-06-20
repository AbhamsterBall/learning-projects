package work.hampster.blog.service;

import work.hampster.blog.mapper.UserMapper;
import work.hampster.blog.model.User;

import java.util.Map;

public interface UserService extends EntityService<UserMapper, User> {
    Map<String, Object> getMailCode(String username);
}
