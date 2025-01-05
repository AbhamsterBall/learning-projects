package work.hampster.blog.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

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

}
