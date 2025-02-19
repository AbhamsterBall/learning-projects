package work.hampster.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.hampster.transfer.AESDTO;
import work.hampster.transfer.RSADTO;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

@Component
public class AES {

    public static RSADTO rsaDTO = null;

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int KEY_SIZE = 128;
    private static final int ITERATIONS = 100;

    @Autowired
    public AES(RSADTO rsa) {
        rsaDTO = rsa;
    }

    public static String decrypt(AESDTO aesDTO) throws Exception {

        String encryptedInfo = aesDTO.getInfo();
        String keyStr = RSA.decrypt(aesDTO.getKey(), rsaDTO.getPrivateKey());

        // 1. 拆分 IV 和 AES 密文
        String ivBase64 = encryptedInfo.substring(0, 24); // IV 的 Base64 长度固定为 24
        String ciphertextBase64 = encryptedInfo.substring(24);

        // Hex转字节数组
        assert keyStr != null;
        byte[] aesKeyBytes = keyStr.getBytes();

        // PBKDF2生成实际AES密钥
        SecretKeySpec aesKey = generatePBKDF2Key(aesKeyBytes);

        // AES解密
        return decryptAES(ciphertextBase64, aesKey, ivBase64);
    }

    public static String encrypt(AESDTO aesDTO) throws Exception {
        String encryptInfo = aesDTO.getInfo();
        String keyStr = RSA.decrypt(aesDTO.getKey(), rsaDTO.getPrivateKey());

        // Hex转字节数组
        assert keyStr != null;
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

}
