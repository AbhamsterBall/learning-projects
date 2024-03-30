package work.hampster.blog.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

public class Json {
    private static Gson getGson() {
        GsonBuilder gb = new GsonBuilder();
        gb.serializeNulls();
        Gson g = gb.create();
        return g;
    }

}
