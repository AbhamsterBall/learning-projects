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
public class ReturnController {
    @RequestMapping("/return")
    public void getreturn(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException {
        //获取支付宝GET过来反馈信息
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

        //RSA2验证
        boolean signVerified = AlipaySignature.rsaCheckV2(params, AppUtils.alipay_public_key, AppUtils.charset, AppUtils.sign_type); //调用SDK验证签名
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if(signVerified) {
            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");
            //支付宝交易号
            String trade_no = request.getParameter("trade_no");
            //付款金额
            String total_amount = request.getParameter("total_amount");
            out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
        }else {
            out.println("验签失败");
        }
    }
}
