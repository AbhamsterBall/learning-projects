package work.hampster.util;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.hampster.transfer.RSADTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class Jwt {

    public static RSADTO rsa = null;

    @Autowired
    public Jwt(RSADTO rsa) {
        Jwt.rsa = rsa;
    }

    private String SECRET_KEY = "iUvIMC0594izu5mVypwEY67ALdhp6drJ1nI8vO1sB1zI1fN1rI8jJ1lO5aY9";

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).build()
//                .parseClaimsJws(token).getBody();
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String timestamp, String url) {
        String decrypt = RSA.decrypt(timestamp, Jwt.rsa.getPrivateKey());
        if (System.currentTimeMillis() - Long.valueOf(decrypt.split(": ")[1]) < 5 * 60 * 1000) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("copyright", "YQS");
            claims.put("timestamp", Long.valueOf(decrypt.split(": ")[1]));
            claims.put("request", url);
            claims.put("random", UUID.randomUUID().toString());

            String token = createToken(claims);
            while (ObjectUtils.isNotNull(Redis.redis.opsForValue().get("token_" + token)))
                token = createToken(claims);
            Redis.redis.opsForValue().set("token_" + token, 0, 5, TimeUnit.MINUTES);
            return token;
        } else {
            return "unauthorized request";
        }
    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token) {
        // TODO: VALIDATION CONDITION
        boolean valid = true;
        if (!ObjectUtils.isNull(Redis.redis.opsForValue().get("token_" + token))) {
            int value = (int) Redis.redis.opsForValue().get("token_" + token);
            if (value > 1)
                valid = false;
            else
                Redis.redis.opsForValue().set("token_" + token, value + 1, 5, TimeUnit.MINUTES);
        } else
            valid = false;

        System.out.println("valid: " + valid);
        System.out.println("isTokenExpired: " + isTokenExpired(token));
        return (valid && !isTokenExpired(token));
    }
}