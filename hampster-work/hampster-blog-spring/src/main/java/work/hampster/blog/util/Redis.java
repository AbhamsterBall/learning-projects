package work.hampster.blog.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import work.hampster.blog.service.UserService;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Service
public class Redis {

    public static RedisTemplate redis;

    @Autowired
    public Redis(RedisTemplate redisTemplate) {
        this.redis = redisTemplate;
    }
    public static Object readAndWrite(String key, Supplier dataProvider, int timeout) {
        Object re = redis.opsForValue().get(key);
        if (re == null) {
            re = dataProvider.get();
            redis.opsForValue().set(key, re, timeout, TimeUnit.MINUTES);
        }
        return re;
    }

    public static Map<String, Object> handleMailCode(UserService userService, String username) {
        String key = username + "_getmailcode";
        Map<String, Object> re = (Map<String, Object>) Redis.redis.opsForValue().get(key);
        if (re == null || re.get("status").equals("expired")) {
            re = userService.getMailCode(username);
            Redis.redis.opsForValue().set(key, re, 15, TimeUnit.MINUTES);
            Map<String, Object> finalRe = re;
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(300000);
                    finalRe.put("status", "expired");
                    Redis.redis.opsForValue().set(username + "_getmailcode", finalRe, 10, java.util.concurrent.TimeUnit.MINUTES);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("异步任务执行完成");
            });
        }
        return re;
    }

    public static String checkMailCode(String username, String mailcode) {
        String key = username + "_getmailcode";
        Map<String, Object> re = (Map<String, Object>) Redis.redis.opsForValue().get(key);
        if (re != null) {
            if (re.get("status").equals("expired")) {
                return "expired";
            }
            if (BCrypt.checkpw(mailcode, re.get("code").toString())) {
                re.put("status", "expired"); // 使用后过期
                Redis.redis.opsForValue().set(key, re, 10, java.util.concurrent.TimeUnit.MINUTES);
                return "true";
            }
        }
        return "false";
    }


}
