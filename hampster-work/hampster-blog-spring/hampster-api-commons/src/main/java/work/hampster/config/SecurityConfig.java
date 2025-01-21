package work.hampster.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import work.hampster.filter.JwtRequestFilter;
import work.hampster.transfer.RSADTO;
import work.hampster.util.Jwt;

import java.util.Map;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Value("${spring.security.rsa.public-key}")
    private String publicKey;
    @Value("${spring.security.rsa.private-key}")
    private String privateKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> {
                    request.requestMatchers(
                            "/**").permitAll();
                    request.anyRequest().authenticated();
                }).csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagementCustomizer ->
                        sessionManagementCustomizer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtRequestFilter(jwt()), UsernamePasswordAuthenticationFilter.class);
//                .httpBasic(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public Jwt jwt() {
        return new Jwt(initRSA());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("let_go")
                .password("let_go")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public RSADTO initRSA() {
        RSADTO RSA = new RSADTO();
        RSA.setPublicKey(publicKey);
        RSA.setPrivateKey(privateKey);
        return RSA;
    }

}