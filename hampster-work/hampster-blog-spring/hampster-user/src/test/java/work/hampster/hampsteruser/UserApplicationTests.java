package work.hampster.hampsteruser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.spec.PBKDF2KeySpec;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import work.hampster.transfer.AESDTO;
import work.hampster.util.RSA;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.HexFormat;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

//@SpringBootTest
class UserApplicationTests {

    @Test
    void contextLoads() {
    }

    AESDTO AESDTO = new AESDTO();
 {

//        AESDTO.setInfo("X26n9fq6FBlvPAeVQslXrg==0pVQ7jcmft03MExNdns7Bw==");
    AESDTO.setInfo("2FbDG1eUGkoIXQMPGDDCjw==lyKGSrRkdMffy7gUQrZzfA==");
    AESDTO.setKey("JNaQRJgb/hNXHKKNLTvqNNiKAki4QGMTFqcl+nyWWhGjiIegnh+fnhpQcA2qX/wQuEzOlaoRSxCRtdvjVllMiPGHUG1WEvk/HO9OTfJOC7Fz061Z12c+PCF2OXkBCUXIyLBNYjVHmtQe/X4WL2TvfxA+ooWFTUm9Hfu3Er8zdxc=");

    }
    String keyStr = RSA.decrypt(AESDTO.getKey(),
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMOunJ6/oUy1HfCdL" +
                    "rGtV1NVHAaPeqJu9LkteMqfzLdSxf+LzD81ofhW0XxZ1cghum9g2mtgP6VCRJh6X" +
                    "PdZuCXg7h8pYfzLvbIHLRfu8Rw4ViQYT8UH5nUSat/GOCAam9GBIy9HDZgGymZON" +
                    "LUqKok5SJkiuMejv8KiYiafwVxlAgMBAAECgYAcdsDHgr52xv2061hIdht2vrGV7" +
                    "rb6+8LTUN76MmZ34C1276HlK+sEkbRlLzgepXFWFDORXO3FHy+VMm9ld4KGlnzlW" +
                    "uuQhGZ0IBgScgW233Gqwyd6jbFYG9SwbZQAPf18DKgbRuFi+m/2cQ8I9ITYZnox" +
                    "0/BN7STNAE9zFAX6HQJBAPpMKmLNXIA8Cccv55lzPo3PuOj67LSwc1PADxKpRjDg" +
                    "KJHMdZ8KZWwbtN5p8f5cUPT6g8NrhIgbFo+yuQtsDt8CQQDII+hEQoV+2i5YNMMk" +
                    "iEm/4uzr+ZNpsMn9sv+wWdG5Bu5LlA/N92K7QgtzdU/zxDSUQrBsl01vTRH7M+OJ" +
                    "ZvE7AkApo/g9PbsQ3mwmBjM6lTCn+RHjOLOej+hv23jmeuDhirlVIDXFQr6C0M0z" +
                    "X17SGVuilfbL0RxYZGFJgMtGOjDzAkEAwTLoY/xTIcE8O6mzNvDZQIKucpzZ0Iz4" +
                    "cA77BCGwcgxevFaRayIEDQAUTcBRDd+5YeV+qVTlSyv6z6OnYlwdQQJAAd9oqSbQ" +
                    "X6ScuyOR861Iz8awfbEsvQLDs5zRjfjfEBxCZh8lEdJt1cBm4o1AXvDuXrBtC3NN" +
                    "VY/Px/IOMNh2EA==");

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS7Padding";
    private static final String PBKDF2_ALGORITHM = "PBKDF2WITHHMACSHA256";
    private static final int KEY_SIZE = 128;
    private static final int ITERATIONS = 100;

    public static SecretKeySpec generateKey(String password, byte[] salt) throws Exception {
        KeySpec spec = new PBKDF2KeySpec(password.toCharArray(), salt, ITERATIONS, KEY_SIZE, null);
        SecretKeyFactory f = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        byte[] key = f.generateSecret(spec).getEncoded();
        return new SecretKeySpec(key, ALGORITHM);
    }

    @Test
    void decryptAES() throws Exception {
//        AESDTO AESDTO = new AESDTO();
////        AESDTO.setInfo("X26n9fq6FBlvPAeVQslXrg==0pVQ7jcmft03MExNdns7Bw==");
//        AESDTO.setInfo("2FbDG1eUGkoIXQMPGDDCjw==lyKGSrRkdMffy7gUQrZzfA==");
//        AESDTO.setKey("JNaQRJgb/hNXHKKNLTvqNNiKAki4QGMTFqcl+nyWWhGjiIegnh+fnhpQcA2qX/wQuEzOlaoRSxCRtdvjVllMiPGHUG1WEvk/HO9OTfJOC7Fz061Z12c+PCF2OXkBCUXIyLBNYjVHmtQe/X4WL2TvfxA+ooWFTUm9Hfu3Er8zdxc=");
//        String keyStr = RSA.decrypt(AESDTO.getKey(),
//                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMOunJ6/oUy1HfCdL" +
//                        "rGtV1NVHAaPeqJu9LkteMqfzLdSxf+LzD81ofhW0XxZ1cghum9g2mtgP6VCRJh6X" +
//                        "PdZuCXg7h8pYfzLvbIHLRfu8Rw4ViQYT8UH5nUSat/GOCAam9GBIy9HDZgGymZON" +
//                        "LUqKok5SJkiuMejv8KiYiafwVxlAgMBAAECgYAcdsDHgr52xv2061hIdht2vrGV7" +
//                        "rb6+8LTUN76MmZ34C1276HlK+sEkbRlLzgepXFWFDORXO3FHy+VMm9ld4KGlnzlW" +
//                        "uuQhGZ0IBgScgW233Gqwyd6jbFYG9SwbZQAPf18DKgbRuFi+m/2cQ8I9ITYZnox" +
//                        "0/BN7STNAE9zFAX6HQJBAPpMKmLNXIA8Cccv55lzPo3PuOj67LSwc1PADxKpRjDg" +
//                        "KJHMdZ8KZWwbtN5p8f5cUPT6g8NrhIgbFo+yuQtsDt8CQQDII+hEQoV+2i5YNMMk" +
//                        "iEm/4uzr+ZNpsMn9sv+wWdG5Bu5LlA/N92K7QgtzdU/zxDSUQrBsl01vTRH7M+OJ" +
//                        "ZvE7AkApo/g9PbsQ3mwmBjM6lTCn+RHjOLOej+hv23jmeuDhirlVIDXFQr6C0M0z" +
//                        "X17SGVuilfbL0RxYZGFJgMtGOjDzAkEAwTLoY/xTIcE8O6mzNvDZQIKucpzZ0Iz4" +
//                        "cA77BCGwcgxevFaRayIEDQAUTcBRDd+5YeV+qVTlSyv6z6OnYlwdQQJAAd9oqSbQ" +
//                        "X6ScuyOR861Iz8awfbEsvQLDs5zRjfjfEBxCZh8lEdJt1cBm4o1AXvDuXrBtC3NN" +
//                        "VY/Px/IOMNh2EA==");

        System.out.println(decrypt(keyStr, AESDTO.getInfo()));
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }


    public String decrypt(String KEY, String encryptedPayload) {
        try {
            String iv = encryptedPayload.substring(0, 24);
            String encryptedData = encryptedPayload.substring(24);

            byte[] keyBytes = KEY.getBytes(StandardCharsets.UTF_8);
            byte[] ciphertextBytes = encryptedData.getBytes();
            byte[] ivBytes = Base64.getDecoder().decode(iv);

            Security.addProvider(new BouncyCastleProvider());

            SecretKeySpec secretKeySpec = generateKey(KEY, "salt".getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            IvParameterSpec ivParams = new IvParameterSpec(ivBytes);

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParams);

            byte[] decodedData = Base64.getDecoder().decode(ciphertextBytes);
            byte[] decryptedBytes = cipher.doFinal(decodedData);
            return new String(decryptedBytes, StandardCharsets.UTF_8).trim();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String encrypt(String KEY, String message) {
        try {
            byte[] keyBytes = KEY.getBytes(StandardCharsets.UTF_8);
            byte[] plaintextBytes = message.getBytes(StandardCharsets.UTF_8);
            byte[] ivBytes = new byte[16];

            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(ivBytes);

            Security.addProvider(new BouncyCastleProvider());

            SecretKeySpec secretKeySpec = generateKey(KEY, "salt".getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            IvParameterSpec ivParams = new IvParameterSpec(ivBytes);

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParams);

            byte[] encryptedBytes = cipher.doFinal(plaintextBytes);
            byte[] encodedData = Base64.getEncoder().encode(encryptedBytes);
            byte[] encodedIV = Base64.getEncoder().encode(ivBytes);

            String encryptedData = new String(encodedData, StandardCharsets.UTF_8);
            String encodedIVString = new String(encodedIV, StandardCharsets.UTF_8);

            return encodedIVString + encryptedData;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Test
    void testAES() throws Exception
    {
        AESDTO AESDTO = new AESDTO();
        AESDTO.setInfo("X26n9fq6FBlvPAeVQslXrg==0pVQ7jcmft03MExNdns7Bw==");
        AESDTO.setKey("MTaO0uNliojPf9bzVHCj5/GHXSF7xSnPDEMzmOJjKrdg+aKd/CAnXyTqOFTcf0okj6rh7CfHEXyespom4CEWfDLS7yzveLj3DFS+Ot4LaGueLdMVV2quP+9JE1gnnXSvPHDBgzwzlzuZX1bHt/SUhNFoDyajltFxHykKClRLAD0=");

        String key = RSA.decrypt(AESDTO.getKey(),
                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMOunJ6/oUy1HfCdL" +
                        "rGtV1NVHAaPeqJu9LkteMqfzLdSxf+LzD81ofhW0XxZ1cghum9g2mtgP6VCRJh6X" +
                        "PdZuCXg7h8pYfzLvbIHLRfu8Rw4ViQYT8UH5nUSat/GOCAam9GBIy9HDZgGymZON" +
                        "LUqKok5SJkiuMejv8KiYiafwVxlAgMBAAECgYAcdsDHgr52xv2061hIdht2vrGV7" +
                        "rb6+8LTUN76MmZ34C1276HlK+sEkbRlLzgepXFWFDORXO3FHy+VMm9ld4KGlnzlW" +
                        "uuQhGZ0IBgScgW233Gqwyd6jbFYG9SwbZQAPf18DKgbRuFi+m/2cQ8I9ITYZnox" +
                        "0/BN7STNAE9zFAX6HQJBAPpMKmLNXIA8Cccv55lzPo3PuOj67LSwc1PADxKpRjDg" +
                        "KJHMdZ8KZWwbtN5p8f5cUPT6g8NrhIgbFo+yuQtsDt8CQQDII+hEQoV+2i5YNMMk" +
                        "iEm/4uzr+ZNpsMn9sv+wWdG5Bu5LlA/N92K7QgtzdU/zxDSUQrBsl01vTRH7M+OJ" +
                        "ZvE7AkApo/g9PbsQ3mwmBjM6lTCn+RHjOLOej+hv23jmeuDhirlVIDXFQr6C0M0z" +
                        "X17SGVuilfbL0RxYZGFJgMtGOjDzAkEAwTLoY/xTIcE8O6mzNvDZQIKucpzZ0Iz4" +
                        "cA77BCGwcgxevFaRayIEDQAUTcBRDd+5YeV+qVTlSyv6z6OnYlwdQQJAAd9oqSbQ" +
                        "X6ScuyOR861Iz8awfbEsvQLDs5zRjfjfEBxCZh8lEdJt1cBm4o1AXvDuXrBtC3NN" +
                        "VY/Px/IOMNh2EA==");
        String message = "19990415hyh";

        String out = encrypt(key, message);
        System.out.println(out);
        System.out.println(decrypt(key, out));

    }


    // 解密主入口
    public String decryptB(String encryptedInfo) throws Exception {
        // 1. 拆分 IV 和 AES 密文
        String ivBase64 = encryptedInfo.substring(0, 24); // IV 的 Base64 长度固定为 24
        String ciphertextBase64 = encryptedInfo.substring(24);
//
//        // 2. RSA 解密获取原始 aesKey (Hex 字符串)
//        String aesKeyHex = decryptRSA(rsaEncryptedAesKey, privateKey);

        // Hex转字节数组
        byte[] aesKeyBytes = keyStr.getBytes();

        // PBKDF2生成实际AES密钥
        SecretKeySpec aesKey = generatePBKDF2Key(aesKeyBytes);

        // AES解密
        return decryptAES(ciphertextBase64, aesKey, ivBase64);
    }

    // 解密主入口
    public String encryptB(String encryptInfo) throws Exception {
        // 1. 拆分 IV 和 AES 密文
//        String ivBase64 = encryptedInfo.substring(0, 24); // IV 的 Base64 长度固定为 24
//        String ciphertextBase64 = encryptedInfo.substring(24);
//
//        // 2. RSA 解密获取原始 aesKey (Hex 字符串)
//        String aesKeyHex = decryptRSA(rsaEncryptedAesKey, privateKey);

        // Hex转字节数组
        byte[] aesKeyBytes = keyStr.getBytes();

        // PBKDF2生成实际AES密钥
        SecretKeySpec aesKey = generatePBKDF2Key(aesKeyBytes);

        // AES解密
        return encryptAES(encryptInfo, aesKey);
    }

    // PBKDF2 密钥派生
    private static SecretKeySpec generatePBKDF2Key(byte[] aesKeyBytes) throws Exception {
        String salt = "salt";
        int iterations = 100;
        int keyLength = 128;

        String passwordHex = new String(aesKeyBytes, StandardCharsets.UTF_8);
        PBEKeySpec spec = new PBEKeySpec(
                passwordHex.toCharArray(),
                salt.getBytes(StandardCharsets.UTF_8),
                iterations,
                keyLength
        );

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey secretKey = factory.generateSecret(spec);
        return new SecretKeySpec(secretKey.getEncoded(), "AES");
    }

    // AES 解密
    private static String decryptAES(String ciphertextBase64, SecretKeySpec aesKey, String ivBase64) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(ciphertextBase64);
        byte[] ivBytes = Base64.getDecoder().decode(ivBase64);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, aesKey, ivSpec);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private static String encryptAES(String ciphertext, SecretKeySpec aesKey) throws Exception {
        byte[] ciphertextBytes = ciphertext.getBytes(StandardCharsets.UTF_8);
//        byte[] ivBytes = Base64.getDecoder().decode(ivBase64);
        byte[] ivBytes = new byte[16];

        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(ivBytes);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivSpec);

        byte[] encryptedBytes = cipher.doFinal(ciphertextBytes);
        byte[] encodedData = Base64.getEncoder().encode(encryptedBytes);
        byte[] encodedIV = Base64.getEncoder().encode(ivBytes);

        String encryptedData = new String(encodedData, StandardCharsets.UTF_8);
        String encodedIVString = new String(encodedIV, StandardCharsets.UTF_8);

        return encodedIVString + encryptedData;
    }

    @Test
    void test() {
        try {
            String encryptedInfo = encryptB("123456");

            String plaintext = decryptB(encryptedInfo);
            System.out.println("Decrypted: " + plaintext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testExtract() {
        Claims body = Jwts.parserBuilder()
                .setSigningKey("WpBzX/t+Bh/awLy5cS1rR4hvT9jRTTw558B3SXV2E3bs/HdDYVMkcnzO7a6lq03T5ZmXLtVdQuGVSWVT9EQxRw==")
                .build()
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJyYW5kb20iOiJlMzAyZmNhYS1kYWEwLTRkYzItOTBlNC1iMzAyNzAxNGY5NjkiLCJjb3B5cmlnaHQiOiJIWUgiLCJmaW5nZXJwcmludCI6eyJrZXkiOiJoYU1TeEpoc0x1SDRlUzF0RUZld2tLVUhPckxFUEZMeWlWMjJkcEJ2KzBnZmlXSHJkQWhGWjRrVFVQMHhGTlA0ZURrVkpTaGtwRStjZlRVVE5DTHNHVm1DajFPa3hSVTZEODFqaHl6Tm5WUFkvWEpvbld4STNady8wUVI3cy9ucGtGUk5yakwvYjFXWkw3bm9iMUhBRm9sUXlCbWpCckV5bGtJWWNYck1ieDA9IiwiaW5mbyI6ImkzTW5maDhCajEySEJpNG1NTXR2VVE9PTlPdTA3bldhblQxV0NQK2VLcmorMHprbmhmQXNoeVNhNGFPaGlFSHpVcXZjZ1BkSkxHV045ckFLemk2ajdNWm0ifSwidGltZXN0YW1wIjoxNzM5NDI0OTEzMDc3LCJpYXQiOjE3Mzk0MjQ5MTMsImV4cCI6MTc0NzIwMDkxM30.Hsp7zSGIuO8hN1SsL3JXsniRpBpEusQlexJJlTo72jU")
                .getBody();
        System.out.println("finished");

        try {
            Claims body0 = Jwts.parserBuilder()
                    .setSigningKey("WpBzX/t+Bh/awLy5cS1rR4hvT9jRTTw558B3SXV2E3bs/HdDYVMkcnzO7a6lq03T5ZmXLtVdQuGVSWVT9EQxRw==")
                    .build()
                    .parseClaimsJws("bGciOiJIUzI1NiJ9.eyJyYW5kb20iOiJlMzAyZmNhYS1kYWEwLTRkYzItOTBlNC1iMzAyNzAxNGY5NjkiLCJjb3B5cmlnaHQiOiJIWUgiLCJmaW5nZXJwcmludCI6eyJrZXkiOiJoYU1TeEpoc0x1SDRlUzF0RUZld2tLVUhPckxFUEZMeWlWMjJkcEJ2KzBnZmlXSHJkQWhGWjRrVFVQMHhGTlA0ZURrVkpTaGtwRStjZlRVVE5DTHNHVm1DajFPa3hSVTZEODFqaHl6Tm5WUFkvWEpvbld4STNady8wUVI3cy9ucGtGUk5yakwvYjFXWkw3bm9iMUhBRm9sUXlCbWpCckV5bGtJWWNYck1ieDA9IiwiaW5mbyI6ImkzTW5maDhCajEySEJpNG1NTXR2VVE9PTlPdTA3bldhblQxV0NQK2VLcmorMHprbmhmQXNoeVNhNGFPaGlFSHpVcXZjZ1BkSkxHV045ckFLemk2ajdNWm0ifSwidGltZXN0YW1wIjoxNzM5NDI0OTEzMDc3LCJpYXQiOjE3Mzk0MjQ5MTMsImV4cCI6MTc0NzIwMDkxM30.Hsp7zSGIuO8hN1SsL3JXsniRpBpEusQlexJJlTo72jU")
                    .getBody();
            System.out.println("finished");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
