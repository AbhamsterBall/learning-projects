package tries.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tries.AppUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class NotifyController {
    //接收支付宝返回的异步通知的信息
    @RequestMapping("/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException {
        //支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        Iterator<String> iter = requestParams.keySet().iterator();
        while(iter.hasNext()){
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AppUtils.alipay_public_key, AppUtils.charset, AppUtils.sign_type); //调用SDK验证签名
        /*
         * 实际验证过程建议商户务必添加以下校验：
         */
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no =request.getParameter("out_trade_no");
            //支付宝交易号
            String trade_no = request.getParameter("trade_no");
            //交易状态
            String trade_status = request.getParameter("trade_status");
            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
            }
            out.println("success");
        }else {
            out.println("fail");
        }

    }
}