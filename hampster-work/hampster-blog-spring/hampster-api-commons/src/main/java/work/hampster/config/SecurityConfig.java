package work.hampster.config;

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
        RSA.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQeAUQiHVNd4wNsuBa78hvFPhtuWMF1Wc1ys+meNt5nMZrIzQUb3XHxpDKr2FmWnTpP814jA2I3RngZ8R36UKZs2g2M8Zv454Vkc8u1vuZRXyUwRuTog71REVdkjnKsgPWM9TgdybysFycptGEDNP3mJ38sppFMsEQ97X9L3nqgQIDAQAB");
        RSA.setPrivateKey("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJB4BRCIdU13jA2y4FrvyG8U+G25YwXVZzXKz6Z423mcxmsjNBRvdcfGkMqvYWZadOk/zXiMDYjdGeBnxHfpQpmzaDYzxm/jnhWRzy7W+5lFfJTBG5OiDvVERV2SOcqyA9Yz1OB3JvKwXJym0YQM0/eYnfyymkUywRD3tf0veeqBAgMBAAECgYA+f62xOVeF6/Hq+zeUH6fWZnwS++Ca5XeBDfxHSIGqWuh78P7W2GFby8tX5Ad/ACMTxUZeoDg6vWw0aK/WMFNfBJcnvrKbD5aqT6MztVCgFwRkeSyR41MwmnX74Kscru7Pj2inQnwtJWhADwmylwa0DKeqEh6TOZ639aEUhPM3AQJBAL52wNdiTGj5X/d++yIoTK0fakD/TLh95PSB6PcfXHt/NAraQgIUC/mklrSJuCuar38OKVVW0Hhasps3UkVZ/tMCQQDCLbw+GoP3BB1GZiGSHRMG/LN6wXND9W8/z/eUZQAuUvMUPuSRpdR/DKINWkYYLsei2LjWijKhrZPrlRz9s+TbAkAqRJBnPBlY8n6pnjR9UBQ0F1StN/r+tnGpEWtx7DPJ9GDIR8d+TpSZwjzvs+B7TMjFmY7ysWFyVC5u48gOO/qhAkEAlD66gmdX7JQ1jXOXmaecyS7QD531VCLi5kLt71sUwkkNz50cf52tF7obVjpfXUMJouf2ZcRoV0rfrXHQClj3FQJATZzZIr4goikLHJXV90ZA0QrJmjXM6iX82MsopyQ+kyy+wliXb4yRNVez/V7B1u8nobdyJbDHO8OUDaD+Yk6LnA==");
        return RSA;
    }

}