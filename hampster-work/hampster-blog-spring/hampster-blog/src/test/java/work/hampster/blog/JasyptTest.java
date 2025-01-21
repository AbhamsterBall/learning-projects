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
        String plainText = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMOunJ6/oUy1HfCdLrGtV1NVHAaPeqJu9LkteMqfzLdSxf+LzD81ofhW0XxZ1cghum9g2mtgP6VCRJh6XPdZuCXg7h8pYfzLvbIHLRfu8Rw4ViQYT8UH5nUSat/GOCAam9GBIy9HDZgGymZONLUqKok5SJkiuMejv8KiYiafwVxlAgMBAAECgYAcdsDHgr52xv2061hIdht2vrGV7rb6+8LTUN76MmZ34C1276HlK+sEkbRlLzgepXFWFDORXO3FHy+VMm9ld4KGlnzlWuuQhGZ0IBgScgW233Gqwyd6jbFYG9SwbZQAPf18DKgbRuFi+m/2cQ8I9ITYZnox0/BN7STNAE9zFAX6HQJBAPpMKmLNXIA8Cccv55lzPo3PuOj67LSwc1PADxKpRjDgKJHMdZ8KZWwbtN5p8f5cUPT6g8NrhIgbFo+yuQtsDt8CQQDII+hEQoV+2i5YNMMkiEm/4uzr+ZNpsMn9sv+wWdG5Bu5LlA/N92K7QgtzdU/zxDSUQrBsl01vTRH7M+OJZvE7AkApo/g9PbsQ3mwmBjM6lTCn+RHjOLOej+hv23jmeuDhirlVIDXFQr6C0M0zX17SGVuilfbL0RxYZGFJgMtGOjDzAkEAwTLoY/xTIcE8O6mzNvDZQIKucpzZ0Iz4cA77BCGwcgxevFaRayIEDQAUTcBRDd+5YeV+qVTlSyv6z6OnYlwdQQJAAd9oqSbQX6ScuyOR861Iz8awfbEsvQLDs5zRjfjfEBxCZh8lEdJt1cBm4o1AXvDuXrBtC3NNVY/Px/IOMNh2EA==";
        String factor = "vmfJ.M&VNAiL6Yz=Cc";

        String encryptWithSHA512Str = encryptWithSHA512(plainText, factor);
        String decryptWithSHA512Str = decryptWithSHA512(encryptWithSHA512Str, factor);
        System.out.println("采用AES256加密后原文密文: " + encryptWithSHA512Str);
        System.out.println("采用AES256解密后密文原文: " + decryptWithSHA512Str);
    }
}
