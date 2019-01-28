package cn.jeefast.web;

import cn.jeefast.base.BaseController;
import cn.jeefast.config.RedisUtils;
import cn.jeefast.wx.WeiXinService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 微信通讯
 */
@Controller
public class WeiXinController extends BaseController{

    private  static String MSG_KEY = "MSG_KEY";
    private  static String msg = "欢迎您关注禾农家!您的信赖就是最大的动力\n邮箱:bang_newfarmer@126.com\n联系电话:15213211515";

    @Value("${hnj.wx.token}")
    private String token;

    @Resource
    private RedisUtils redisUtils;

    /**
     * 主页数据的返回
     * @param request
     * @return
     */
    @RequestMapping(value = "/wx/token")
    public void home(HttpServletRequest request, HttpServletResponse response) {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);

                try {
                    Object o = redisUtils.getValue(MSG_KEY);
                    if(o != null){
                        msg = o.toString();
                    }
                }catch (Exception e){

                }

                // 取出发送用户
                String xmlS = WeiXinService.getXmlByRequest(request);

                //得到
                int fromuser_s = xmlS.indexOf("<FromUserName><![CDATA[");
                int fromuser_e = xmlS.indexOf("]]></FromUserName>");

                String fromuser = xmlS.substring(fromuser_s + 23, fromuser_e);

                int touser_s = xmlS.indexOf("<ToUserName><![CDATA[");
                int touser_e = xmlS.indexOf("]]></ToUserName>");

                String touser = xmlS.substring(touser_s + 21, touser_e);
                int msgIndex = xmlS.indexOf("<Event>");


                int count = 0;
                Integer userIda = null;
                if (msgIndex > 0 && xmlS != null && !xmlS.equals("")) {
                    String event = xmlS.substring(xmlS.indexOf("<Event><![CDATA[") + 16, xmlS.indexOf("]]></Event>"));
                    // 判断是否是订阅事件
                    if (event.equals("subscribe")) {
                        //发送消息
                        out.print(WeiXinService.returnWeiXinMessage(fromuser,touser,msg));

                    }else if (event.equals("unsubscribe")) {
                        // 判断是否是取消订阅事件
                        out.print("success");
                    }
                    if (event.equals("VIEW")) {

                        out.print("success");
                    }
                    if (event.equals("CLICK")) {
                        out.print("success");
                    }
                }
                if (msgIndex < 0) {
                    out.print(WeiXinService.returnWeiXinMessage(fromuser,touser,msg));
                    count++;
                }
                if (count == 0) {
                    out.print(WeiXinService.returnWeiXinMessage(fromuser,touser,msg));
                }
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out != null){
                out.close();
            }
        }
    }

    private boolean checkSignature(String signature, String timestamp,
                                         String nonce) {
        // 1.将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = new String[] { token, timestamp, nonce };
        Arrays.sort(arr);

        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        content = null;
        // 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    private  String byteToStr(byte[] byteArray) {
        StringBuilder strDigest = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            strDigest.append(byteToHexStr(byteArray[i]));
        }
        return strDigest.toString();
    }

    private  String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

}
