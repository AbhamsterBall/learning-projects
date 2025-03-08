package work.hampster.blog;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

public class JasyptTest {

    public static String encryptWithSHA512(String plainText, String factor) {
        // 1. 创建加解密工具实例
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        // 2. 加解密配置
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(factor);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        // 为减少配置文件的书写，以下都是 Jasyp 3.x 版本，配置文件默认配置
        config.setKeyObtentionIterations( "755");
        config.setPoolSize("2");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        // 3. 加密
        return encryptor.encrypt(plainText);
    }

    /**
     * @Description: Jaspy解密（PBEWITHHMACSHA512ANDAES_256）
     * @Author:      Rambo
     * @CreateDate:  2020/7/25 14:40
     * @UpdateUser:  Rambo
     * @UpdateDate:  2020/7/25 14:40
     * @param        encryptedText  待解密密文
     * @param        factor         解密秘钥
     * @return       java.lang.String
     * @Version:     1.0.0
     */
    public static String decryptWithSHA512(String encryptedText, String factor) {
        // 1. 创建加解密工具实例
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        // 2. 加解密配置
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(factor);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        // 为减少配置文件的书写，以下都是 Jasyp 3.x 版本，配置文件默认配置
        config.setKeyObtentionIterations( "755");
        config.setPoolSize("2");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        // 3. 解密
        return encryptor.decrypt(encryptedText);
    }

    @Test
    public void testENC() throws Exception {
        String plainText = "F:\\JAVA\\20231010\\hampster-work\\hampster-blog-spring\\hampster-blog\\src\\main\\resources\\summary.py";
        String factor = "vmfJ.M&VNAiL6Yz=Cc";

        String encryptWithSHA512Str = encryptWithSHA512(plainText, factor);
        String decryptWithSHA512Str = decryptWithSHA512(encryptWithSHA512Str, factor);
        System.out.println("采用AES256加密后原文密文: " + encryptWithSHA512Str);
        System.out.println("采用AES256解密后密文原文: " + decryptWithSHA512Str);
    }
}
