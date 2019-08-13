package com.wuyou.cart.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import util.AlipayConfig;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/alipay")
public class AlipayController {

    /**
     * 生成二维码
     *
     * @return
     * @throws
     */
    @RequestMapping("/createNative")
    public String createNative(Long oderid, Long total) {
        System.out.println(oderid);
        System.out.println(total);
        System.out.println("支付宝支付接口");
        //获取当前用户
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("userID:" + userId);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = oderid + "";
        //付款金额，必填
        String total_amount = total + "";
        System.out.println(out_trade_no);
        System.out.println(total_amount);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //订单名称，必填
        String subject = "无忧商品";
        //商品描述，可空
        String body = "用户订购商品个数：1";
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1c";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout_express + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = "";
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("阿里请求返回：" + result);

        return result;
    }

    @RequestMapping("/pay")
    public String pay(HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        // resp.setContentType("text/html;charset=utf-8");
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = "12846977655654";
        //付款金额，必填
        String total_amount = "25425";
        //订单名称，必填
        String subject = "无忧商城";
        //商品描述，可空
        String body = "手机";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");


        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println(result);
        //输出
        return result;

    }
//@RequestMapping("/pay")
//public String pay(Long oderid,Long total) throws Exception {
//    System.out.println("***********************************"+oderid+total);
//    //获得初始化的AlipayClient
//    AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
//
//    //设置请求参数
//    AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//    alipayRequest.setReturnUrl(AlipayConfig.return_url);
//    alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
//
//    //商户订单号，商户网站订单系统中唯一订单号，必填
//    //String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//    String out_trade_no = oderid+"";
//    //付款金额，必填
//    //String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
//    String total_amount = total+"";
//    //订单名称，必填
//    //String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
//    String subject = new String("订单");
//    //商品描述，可空
//    //String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
//    String body = new String("手表");
//
//    alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
//            + "\"total_amount\":\""+ total_amount +"\","
//            + "\"subject\":\""+ subject +"\","
//            + "\"body\":\""+ body +"\","
//            + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
//
//    //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
//    //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
//    //		+ "\"total_amount\":\""+ total_amount +"\","
//    //		+ "\"subject\":\""+ subject +"\","
//    //		+ "\"body\":\""+ body +"\","
//    //		+ "\"timeout_express\":\"10m\","
//    //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
//    //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
//
//    //请求
//    String result = alipayClient.pageExecute(alipayRequest).getBody();
//
//    //输出
////        out.println(result);
//    System.out.println(result);
//    return result;
//}


}
