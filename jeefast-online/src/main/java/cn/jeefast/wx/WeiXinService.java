package cn.jeefast.wx;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by lyj on 2017/5/19.
 * 微信相关的业务
 */
public class WeiXinService {

    //模拟进行测试的数据相关
    private static final String TOKEN = "whnc2018";

    /**
     * 判断是否来至于微信的请求
     * @param timestamp 事件搓
     * @param nonce 标志
     * @param signature 签名[含token]
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static boolean isFromWeixin( String timestamp,String nonce,String signature) throws NoSuchAlgorithmException {
        String[] params = new String[] { TOKEN, timestamp, nonce };
        Arrays.sort(params);
        // 将三个参数字符串拼接成一个字符串进行sha1加密
        String clearText = params[0] + params[1] + params[2];
        String algorithm = "SHA-1";
        String sign = new String(
                Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));
        // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (signature.equals(sign)) {
           return true;
        }
        return false;
    }

    /**
     * 获取微信返回的xml数据
     * @param request
     * @return
     * @throws IOException
     */
    public static String getXmlByRequest(HttpServletRequest request) throws IOException {
        // 接收XML数据
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
            String line = null;
            if (br != null) {
                line = br.readLine();
            }
            int linecount = 0;
            while (br != null && line != null) {
                sb.append(line);
                line = br.readLine();
                if(linecount > 1000){
                    //最多取1000行的内容防止死循环
                    break;
                }else{
                    linecount++;
                }
            }

        } catch (Exception e) {

        }
        return sb.toString();
    }

    /**
     * 返回发送的信息
     * @param fromuser 发送信息方
     * @param touser 回答信息方
     * @return
     */
    public static String returnWeiXinMessage(String fromuser,String touser,String msg){
        StringBuffer xmlContent = new StringBuffer("");
        xmlContent.append("<xml>");
        xmlContent.append("<ToUserName><![CDATA[" + fromuser + "]]></ToUserName>");
        xmlContent.append("<FromUserName><![CDATA[" + touser + "]]></FromUserName>");
        xmlContent.append("<CreateTime>" + new Date().getTime() + "</CreateTime>");
        xmlContent.append("<MsgType><![CDATA[text]]></MsgType>");
        xmlContent.append("<Content><![CDATA["+msg+"]]></Content>");
        xmlContent.append("</xml>");
        return xmlContent.toString();
    }

}
