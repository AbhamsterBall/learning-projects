package com.hyh;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.hyh.service.EmpService;
import com.sun.javaws.jnl.RContentDesc;
import org.apache.catalina.filters.CorsFilter;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan("com.hyh")
//@PropertySource("db.properties")
public class Config extends WebMvcConfigurerAdapter {

    @CrossOrigin(origins = "http://localhost:8081")
    public class CorsConfig {
        // You can customize the configuration further if needed
    }

    private static final Properties props = new Properties();
//    @Autowired
//    EmpService empService;

    static {
        try {
            props.load(Config.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean("multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
//        empService.cUpdateById(null, 0);

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        // 设置最大文件大小，单位为字节  所有文件
        commonsMultipartResolver.setMaxUploadSize(209715200000L);
        // 设置单个文件上传最大字节
        commonsMultipartResolver.setMaxUploadSizePerFile(10245760000L);

        return commonsMultipartResolver;
    }

    @Bean("currentPath")
    public String getCurrentPath() {
        String classpath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        classpath = classpath.substring(0, classpath.indexOf("/out/"));
        return classpath;
    }

    public static int getString() {
        return 1;
    }

    @Value("${driver}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;
    @Value("${pass}")
    private String password;

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setConnectProperties(props); // 猜，它是这样反射的
        druidDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        druidDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        druidDataSource.setUsername("scott");
        druidDataSource.setPassword("123456");

//        druidDataSource.setUrl(url);
//        druidDataSource.setDriverClassName(driverClassName);
//        druidDataSource.setUsername(username);
//        druidDataSource.setPassword(password);
//        System.out.println(props.getProperty("url"));
//        props.set
        System.out.println(url);
        System.out.println(druidDataSource.getUrl());
        System.out.println(druidDataSource.getDataSourceStat());
        return druidDataSource;
    }

    @Bean(name = "sessionFactory")
    public MybatisSqlSessionFactoryBean sessionFactory(DataSource dataSource) {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sessionFactory.setTypeAliasesPackage("com.hyh.model");
        return sessionFactory;
    }

    @Bean(name = "mapperScanner")
    public MapperScannerConfigurer mapperScanner(MybatisSqlSessionFactoryBean sessionFactory) {
        MapperScannerConfigurer mapperScanner = new MapperScannerConfigurer();
        mapperScanner.setSqlSessionFactoryBeanName("sessionFactory");
        mapperScanner.setBasePackage("com.hyh.mapper");
        return mapperScanner;
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        // 在这里可以进行额外的配置
        // factory.setPort(8080);
        return factory;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true)
                .maxAge(3600);
    }


}
