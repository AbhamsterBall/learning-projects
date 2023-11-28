package tries;

import java.io.FileWriter;
import java.io.IOException;

public class AppUtils {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "9021000132634090";
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCMQxnUNbAOhZGYcSiFl71SwRapmzC1GmYtg/YDqXTJo2yhW2OKHC6EPnsuJnnUmTN6pO07sSohuRpDGxRZdTG3CkGDv8uiCZ+h7jIgel0JhimMM1xq/hoRy/9aRecFtt/V0PDVAN7BSBW9LzJLUaHfk8/5Gy0b0Ut6VB8fwfpCqFjzCN+4MxFCv3Vtin8V0Me7P6+9KRM+X5NGOoKa6ykFPO7e12U7pswhV2S7crMDR22AWeaTNPjGPHeVtuCM5wRZwiGAbLJvlsFlumNLo25fi94hklbmzWmb6aGegt5I0a5arwo5vT3P44gHixh0ty3Kg8Ok189poa6pbyfdUcsLAgMBAAECggEABAV8fRgY9yGfPgaoicQC+pdspeo8KEUcftuUtyApqApWd/3dybzrHIGKGJWj0pOd5TtLTfXTZmofNaNWHwZ8KWpS3IsoNHogKI1ixImdl0NQd6OVV7/3UWFO7fCpIgHPQxsdfI3s1Q/TB7yNrZkmkflz8gl0bqK+jkBRMCDoovaTdHGoz0Q7+1qZZkVQZaWTfAAqgi32f+68StQVFIIxTaTqdy0Q3wMNAfLfmmyxRMOZzZrGC7XBMvihqtGEEROFlYDI0bOelvs1jUICP0QDtm9bthNYzYrN86G69EqpIj39jWFX2MFBz6lu8+go6rtrd7R8Rb9M9giHjX0Z1y2PUQKBgQD09IzbCDD9VNJeoW+MGsjn45tX8GkoeBbUGZkE9Y3mE5b78BX4s+XemCCkJ9zSqUg49clMNv03VLetyQ1QemoXPj9nJ9mW20AlzOVSU/8W8o2WciHDbOvhIH/3NIz3fgh6q9eKmIg+x/5FECBYGeGDYREfRJiX6IJ4HEk9cSKgQwKBgQCSlhtjQAK5qBc4vUs5bGNpXm8AsZK6bDIZkAl02I3s/z4d+QmgSro/055blH+16voWRHUhLLJADNvCp06RfX6bfqUnRh9DAgqDAnrw6bWltD0RZtCB6niPGTmg37oIdRKHSRUvgXLF9MnLBswNe5+M1Ip81XlLwlC5wCZqc+VBmQKBgGswUCrQjM9uPJgYQH2ogt2mJaX3l6yT0DmD8gfPhAZ38AvS5WptHlunajErGGA+n1H9i9aEAY4izjWH0wGyb5Y4MPps37Q5wPpJjNfX1bou7XI2AzxG/3kGBbkhD8aFKiSz6Z/GW2hNj6CdC+pBeQNKiHmMRNHEdsMkbZArX6GNAoGATk1g0+OdkQ75WJXFWSdM8zw+j2BLwMmK+Zy3cHM9XRv7Uod1XOqOEwo3zTPGBKdAcCShbNczYIN94Lz+ezlOKEG+dvw+Ns0gUFtC0O4G2j0tYd5KAxv8ynPvFMBt35aqDxo69uVvKc9Y+9rlpU4kLFZco9JkuNNTy+WYn8TI8gECgYAIC5BxoYzrN1j7hIsxzEPNTgaJ8fQ4nL/vv8SyuDO994RhQYDlWP5q9b2ZgJN9LdYY+KcPqik6zZied3ATAwEGJrSiReI6YWwEvtw+TCM52MUABW62mbSYo/RIPtet9JLT3EBTgZ2aq83Cmecn9/sMjA5wb7FxN/GNN1VISKlAcA==";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzI5GVqwzNC0+JzGPT/GoQm5yzeku6G3Wqg52MVuU+niJlrzovHy+djthuej2idH7Fw/HlRLo8lIzQvaYhfMTQ1SwbZbxBSMv3QhCaXcEN78iTiiRlHmx2HOdvtrPdgAyl0ok8a1xignWUUFu4bMS0bk+Jnox8M/lavnww2d3rvkhMALdWJ1lzmn+mRFtUJK5l+8yo4JJzF7y/bHtc6BOr0xhxBfr5viUm3hfq4RfFzkQs36tDC+IyqWwl42tI0/oDdObtDQqyiiHD03YtsmP1sLMwzYkomRfm0eaZPH7dY7EbzTHIVOyGgDLmAj8L1njUTAaFu8SZOqaT3dsiNOAoQIDAQAB";
    // 服务器异步通知页面路径
    //需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8081/notify";
    // 页面跳转同步通知页面路径
    //需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8081/index";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关,注意这些使用的是沙箱的支付宝网关，与正常网关的区别是多了dev
    public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    // 支付宝网关
    public static String log_path = "F:\\JAVA\\20231010\\LeetCode\\log";
    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}