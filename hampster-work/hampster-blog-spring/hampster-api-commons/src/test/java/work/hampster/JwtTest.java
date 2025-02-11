package work.hampster;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtTest {
    @Test
    void generate() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[64]; // 256 bits
        secureRandom.nextBytes(keyBytes);
        String secretKey = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Generated Secret Key: " + secretKey);
    }
}
