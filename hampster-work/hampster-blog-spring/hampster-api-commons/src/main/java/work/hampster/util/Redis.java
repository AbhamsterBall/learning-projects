package work.hampster.util;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
public class Redis {

    public static RedisTemplate<String, Object> redis = null;

    @Autowired
    public Redis(RedisTemplate<String, Object> template) {
        redis = template;
    }

    public static Object readAndWrite(String key, Supplier<Object> dataProvider, int timeout) {
        Object re = redis.opsForValue().get(key);
        if (re == null) {
            re = dataProvider.get();
            if (!ObjectUtils.isEmpty(re))
                redis.opsForValue().set(key, re, timeout, TimeUnit.MINUTES);
            else // prevent from useless mysql query
                redis.opsForValue().set(key, "", timeout, TimeUnit.MINUTES);
        }
        return re;
    }

    public static Object readAndWrite(String key, Supplier<Object> dataProvider) {
        Object re = redis.opsForValue().get(key);
        if (re == null) {
            re = dataProvider.get();
            if (!ObjectUtils.isEmpty(re))
                redis.opsForValue().set(key, re);
            else // prevent from useless mysql query
                redis.opsForValue().set(key, "");
        }

        return re;
    }

//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        this.beanFactory = beanFactory;
//    }
}