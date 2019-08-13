package util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016093000628268";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC/WtaXSe7wrHDmm4crg4S9X6p+qpT5EgaPAbhPK1IS7dbvLce7nOMn/8ACsagElc1f2pDxHzS3A7NTLujtuv0HgL6GhFHviQgALmDCmdHkHxz5ecWqH5lfpHictcEi96nT390eAF+7FafjmakZjymzOdUuJaCxLHwxgU3S0oXCqH3HZJbX9V5QpeYPSCYMao2rHzXcYQlpepKOtCBgctXRYCw+t8xHFYn2w3O9BpOFo0S9qk6KpxXLY9yAI56nBqBdJEbhvWiz7YIFSps1AGmzOXGOo05zVFPO6vxmZxtoOLbLoA7THqI0y5qiKwXUHybALF4cFssFqxUL461WNUa1AgMBAAECggEAV4DhmoM7maExa7L5Z+OXgsXJgGze/NrKVwDxWHq16GQMookrydZfZf4kvtagv6AFL20SVMDxaTNvAH32FCgq5xBvj7HYihH+r56FrteKTKgRpt+RjU8v4rWiVf8jV6srZPD8+2guXq3zfTfaRQ7bgdLTcmvQhNST4kt/3AVbF30MqJG8n3f1YuZRVRk1zgMya5nm5b7ml/VawfJPj+219IEKcgV27JUY+GRkPBTdVK2xAHwCavVy+CnhDskaDMTFywOhGpdiOTiRoY22DkHzt+hdSf7NaPtwyeMWoRlG3YA2mNmiGDapHXkCf1HOzXjN7XF64sp92ACl2sRd6J7jgQKBgQDkfyakpGHEmrRHE0cBdo7psBBZbnAoD2cAcGkNlTGajK7kSUWz9QWDOdtWeYj4XirUaHqc7gSKZ6Zi/ZxEmBH24aaLTCsc8oT4h+Egk3se3PwIA7koWQjeafLlEvUeeK1xFZgUD2v4MUMtj76NqKNrwU4uyqysUudfIn7FjeMTYQKBgQDWYzTJif4dJnAlQUs4qoV9OgUB93rgQEnBkUUoa7D7JieBSOCzCSezUc1P7FNaX9TZwjOFgkrED8WhacWtQppmmV42D1PHBbI25zq4+v2+25HKQP+hACgy0GNIQyxaRx4aSDygFzmpXztgQ7NpN/veNEl/R2QvrQdMIW3Ikc6H1QKBgFktrv1PEsFSx819npbZmMln59mfmrJn4IFxXe7pLaTuB0wdtNCZbn9a01oasPSOfBTOehxJYQhLj8rOxHCZEVnTk9aF8cWoeXEuRxUZulG/WbYfDeLLbQSxQRVaosQzwS2Digk6Q59oSpm7ollTWgSyOztT6rSO3rJomiWsTNXBAoGAXYtkGRxxGN8Qx/C5lbwyCvYqATWvE/J5PFDa0ExNKQj469yUzwoLnJ7w6xK/PAvcoDewq2yymuIW5dt1Oah6HpKFHSmK8Kh3nqU+ZkB+P60m7A14fRPIdoOfDoTg8XNemzEx4ti/fDvleJeRzTN0XvyJpVPNT1A8gueDM6ULbRECgYEAkm1tYm4SqHUDi1huGqEa2784jjPMb6+3vwMG8niipX/MFZr2BfujLHA0i8BiRbYUikeJ2/TCRf44vg0Fjehs9Vc3HnWRN7AxmdbebQM8uMs7yF0jLRe/+FnadsJ/puXlMThhDrW9nf8MLz2K8Qb5nWc7UyN4LDr4qs+l/fm5PZM=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgORVFY3sOc8NK8OJijV+c+Tpo8V7RTDgIDEMK0uOw4lYot0Vjj1D+J9qYbm5SZAQG3R9l85jjw+UAdObLi9jThlYLQcTqcA1aHK8RYMa4w0najR5TReCiHh2PTxAPCS7izgtozRa19gn0Murf2XjHuetty11QlNeyimNArTyIcIJ8DNHzHyZClHtexxZlrvnA++ycZPKtSh991dkPPu3NGEGRW3iZLxjjga+XR7Ssu1O0feFYuVb5EYseVUJs9dx42/YcCFROEao1tVcjiBSaJD4nypHaAU0X62AtWvXfG6FVhZumrmQsyG2jDhZDil6tlA1yShkAL21bdni12nYPwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "261k49040o.qicp.vip:52979/aliapy/notify_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "261k49040o.qicp.vip:52979/alipay/return_url";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
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