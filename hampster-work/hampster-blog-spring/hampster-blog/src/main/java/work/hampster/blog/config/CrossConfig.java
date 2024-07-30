//package work.hampster.blog.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CrossConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:*") // 允许所有端口的 localhost
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的方法
//                .allowedHeaders("*") // 允许所有请求头
//                .allowCredentials(true) // 允许携带凭证
//                .maxAge(3600); // 预检请求的缓存时间
//    }
//}
