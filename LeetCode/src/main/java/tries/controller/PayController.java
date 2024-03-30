package tries.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tries.AppUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PayController {
    @Resource
    private AlipayClient alipayClient;
    @Resource
    private AlipayTradePagePayRequest alipayTradePagePayRequest;

    //处理支付请求
    //1.接收页面传过来的数据:订单号，金额，名称，商品描述  表单中的name值=参数名
    @RequestMapping("/pay")
    public void pay(String WIDout_trade_no, String WIDsubject, String WIDtotal_amount, String WIDbody, HttpServletResponse response)
            throws AlipayApiException, IOException {
        //2.获得支付的客户端AlipayClient,和配置支付信息的对象AlipayTradePagePayRequest
        //3.设置响应的地址(支付宝返回给商户的响应地址)
        alipayTradePagePayRequest.setNotifyUrl(AppUtils.notify_url);
        alipayTradePagePayRequest.setReturnUrl(AppUtils.return_url);
        //4.设置请求的参数(传递给支付宝的数据)
        alipayTradePagePayRequest.setBizContent(
                "{\"out_trade_no\":\""+ WIDout_trade_no +"\","
                        + "\"total_amount\":\""+ WIDtotal_amount +"\","
                        + "\"subject\":\""+ WIDsubject +"\","
                        + "\"body\":\""+ WIDbody +"\","
<<<<<<< HEAD
                        + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //5.发送请求
        String result = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
        //6.将响应结果返回给前端
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(result);
=======
                        + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\","
                        + "\"qr_pay_mode\":\"2\"}");
        //5.发送请求
        String result = alipayClient.pageExecute(alipayTradePagePayRequest, "POST").getBody();
        //6.将响应结果返回给前端
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(result);
//        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
//        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
////异步接收地址，仅支持http/https，公网可访问
//        request.setNotifyUrl("");
////同步跳转地址，仅支持http/https
//        request.setReturnUrl("");
///******必传参数******/
//        JSONObject bizContent = new JSONObject();
////商户订单号，商家自定义，保持唯一性
//        bizContent.put("out_trade_no", "20210817010101004");
////支付金额，最小值0.01元
//        bizContent.put("total_amount", 0.01);
////订单标题，不可使用特殊符号
//        bizContent.put("subject", "测试商品");
////电脑网站支付场景固定传值FAST_INSTANT_TRADE_PAY
//        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
//
///******可选参数******/
////bizContent.put("time_expire", "2022-08-01 22:00:00");
//
////// 商品明细信息，按需传入
////JSONArray goodsDetail = new JSONArray();
////JSONObject goods1 = new JSONObject();
////goods1.put("goods_id", "goodsNo1");
////goods1.put("goods_name", "子商品1");
////goods1.put("quantity", 1);
////goods1.put("price", 0.01);
////goodsDetail.add(goods1);
////bizContent.put("goods_detail", goodsDetail);
//
////// 扩展信息，按需传入
////JSONObject extendParams = new JSONObject();
////extendParams.put("sys_service_provider_id", "2088511833207846");
////bizContent.put("extend_params", extendParams);
//
//        request.setBizContent(bizContent.toString());
//        AlipayTradePagePayResponse response = alipayClient.pageExecute(request,"POST");
//// 如果需要返回GET请求，请使用
//// AlipayTradePagePayResponse response = alipayClient.pageExecute(request,"GET");
//        String pageRedirectionData = response.getBody();
//        System.out.println(pageRedirectionData);
//
//        if(response.isSuccess()){
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//        }
>>>>>>> temp-branch
    }
}