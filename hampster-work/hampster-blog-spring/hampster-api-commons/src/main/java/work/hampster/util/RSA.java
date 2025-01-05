package work.hampster.util;

import org.apache.commons.codec.binary.Base64;
import work.hampster.transfer.RSADTO;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA {

    public static RSADTO generateRasKey() {
        RSADTO rs = new RSADTO();
        try {
            // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = null;
            keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(1024, new SecureRandom());
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 得到私钥 公钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
            // 得到私钥字符串
            String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
            // 将公钥和私钥保存到Map
            rs.setPrivateKey(privateKeyString);
            rs.setPublicKey(publicKeyString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


    public static String encrypt(String str, String publicKey) {
        try {
            //base64编码的公钥
            byte[] decoded = Base64.decodeBase64(publicKey);
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
            //RSA加密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    public static String decrypt(String str, String privateKey) {

        try {
            //64位解码加密后的字符串
            byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
            //base64编码的私钥
            byte[] decoded = Base64.decodeBase64(privateKey);
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
            //RSA解密
            Cipher cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.DECRYPT_MODE, priKey);

            return new String(cipher.doFinal(inputByte));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String decryptURL(String str, String privateKey) {

        try {
            //64位解码加密后的字符串
            byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
            //base64编码的私钥
            byte[] decoded = Base64.decodeBase64(privateKey);
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
            //RSA解密
            Cipher cipher = Cipher.getInstance("RSA");

            cipher.init(Cipher.DECRYPT_MODE, priKey);

            return new String(cipher.doFinal(inputByte));
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            return e.getMessage().contains("error") ?
                    "continue" : null;
        }

    }

//    public static boolean isBase64(String str) {
//        try {
//            // 尝试解码字符串
//            java.util.Base64.getDecoder().decode(str);
//            // 解码成功
//            return true;
//        } catch (IllegalArgumentException e) {
//            // 解码失败
//            return false;
//        }
//    }

//    public static void main(String[] args) {
//        Map<String, String> key = generateRasKey();
//        String encrypt = encrypt("sqlTS: " + System.currentTimeMillis(), key.get(PUBLIC_KEY));
//        System.out.println(encrypt);
//
//        String decrypt = decrypt(encrypt, key.get(PRIVATE_KEY));
//
//        System.out.println(Long.valueOf(decrypt.split(": ")[1]) - System.currentTimeMillis() < 60 * 1000);
//    }

}