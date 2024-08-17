package work.hampster.blog;

//import com.alibaba.druid.pool.DruidDataSource;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

import static org.elasticsearch.client.RestClient.builder;

//import jakarta.sql.DataSource;

@Configuration(proxyBeanMethods = false)
@ComponentScan("work.hampster.blog")
@PropertySource("classpath:db.properties")
@PropertySource("classpath:application.yml")
public class Config implements WebMvcConfigurer {

    public static Properties getProps() {
        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        ClassPathResource resource = new ClassPathResource("application.yml");
        yamlFactory.setResources(resource);
        Properties props = yamlFactory.getObject();
        return props;
    }

    @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(Environment environment) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(environment.getProperty("mysql80_url"));
        dataSource.setDriverClassName(environment.getProperty("mysql80_driver"));
        dataSource.setUsername(environment.getProperty("mysql80_username"));
        dataSource.setPassword(environment.getProperty("mysql80_password"));

        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public MybatisSqlSessionFactoryBean sessionFactory(DataSource dataSource) {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sessionFactory.setTypeAliasesPackage("work.hampster.blog.model");
        return sessionFactory;
    }

    @Bean(name = "mapperScanner")
    public MapperScannerConfigurer mapperScanner(MybatisSqlSessionFactoryBean sessionFactory) {
        MapperScannerConfigurer mapperScanner = new MapperScannerConfigurer();
        mapperScanner.setSqlSessionFactoryBeanName("sessionFactory");
        mapperScanner.setBasePackage("work.hampster.blog.mapper");
        return mapperScanner;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        //指定使用jackson工具负责具体的序列化操作
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //创建jackson的核心api的 ObjectMapper ,将bean,list,map,数组等等转成字符串
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setConnectionFactory(factory);
        template.setKeySerializer(redisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    @Bean
    public RestClient RestClient(Environment environment) {
        return builder(new HttpHost(environment.getProperty("spring.data.elasticsearch.rest.address"),
                        Integer.parseInt(environment.getProperty("spring.data.elasticsearch.rest.port")),
                        environment.getProperty("spring.data.elasticsearch.rest.scheme"))).build();
    }

    @Bean
    public CorsFilter corsFilter()
    {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("http://localhost:*");
        config.addAllowedOriginPattern("http://localhost");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(3600L);
        // 添加映射路径，拦截一切请求(都要过该配置)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        // 返回新的CorsFilter
        return new CorsFilter(source);
    }

}
