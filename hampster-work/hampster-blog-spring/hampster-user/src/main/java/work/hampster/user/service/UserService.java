package work.hampster.user.service;

import io.minio.errors.*;
import work.hampster.user.mapper.UserMapper;
import work.hampster.model.User;
import work.hampster.transfer.UserDTO;
import work.hampster.util.AjaxResult;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface UserService extends EntityService<UserMapper, User> {
    Map<String, Object> getMailCode(String username);
    String getToken();
    int getIdByToken(String utoken);

    AjaxResult login(UserDTO info) throws Exception;
    AjaxResult getUserInfo(String token, String fingerprint) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}
