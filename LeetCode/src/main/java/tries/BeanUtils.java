package tries;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tries.AppUtils;

@Configuration
public class BeanUtils {
    //创建支付宝需要的客户端对象
    @Bean
    public AlipayClient alipayClient(){
        return new DefaultAlipayClient(AppUtils.gatewayUrl,AppUtils.app_id,AppUtils.merchant_private_key,"json",AppUtils.charset,
                AppUtils.alipay_public_key,AppUtils.sign_type);
    }
    //创建一个支付宝的请求对象
    @Bean
    public AlipayTradePagePayRequest alipayTradePagePayRequest(){
        return new AlipayTradePagePayRequest();
    }
}