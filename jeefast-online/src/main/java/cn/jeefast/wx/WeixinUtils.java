package cn.jeefast.wx;

import cn.jeefast.common.utils.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;


/**
 * 微信工具类
 * @author WJ
 *
 */
public class WeixinUtils {

	private static final Logger logger = LoggerFactory.getLogger(WeixinUtils.class);

	/**
	 * 微信应用号ID
	 */
	public final static String WEIXIN_APPID = "wxbdffc7702a29bb55";

	/**
	 * 微信AppSecret秘钥
	 */
	public final static String WEIXIN_APP_SECRET = "5f19b10cfd97ef4239d40eae4578d8ef";

	/**
	 * 商户号ID
	 */
	public final static String WEIXIN_MCHID = "1307350901";


	/**
	 * 微信请求CODE
	 * 若提示“该链接无法访问”，请检查参数是否填写错误，如redirect_uri的域名与审核时填写的授权域名不一致或scope不为snsapi_login。
	 * appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
	 */
	private static final String WIEXIN_REQUEST_CODE = "https://open.weixin.qq.com/connect/qrconnect";

	/**
	 * 通过code获取access_token
	 * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
	 */
	private static final String WIEXIN_REQUEST_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

	/**
	 * 刷新access_token有效期 access_token过期后通过refresh_token重新刷新access_token获取最新token无需用户授权
	 * access_token过期时间为2小时
	 * refresh_token过期时间为30天
	 * https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
	 */
	private static final String WIEXIN_REQUEST_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

	/**
	 * 获取用户个人信息（UnionID机制）
	 * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
	 */
	private static final String WIEXIN_REQUEST_USER_INFO = "https://api.weixin.qq.com/sns/userinfo";

	/**
	 * 检验授权凭证（access_token）是否有效
	 * https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID
	 */
	private static final String WIEXIN_AUTH_TOKEN = "https://api.weixin.qq.com/sns/auth";

	/**
	 * 微信APP支付下单请求地址
	 */
	public final static String WEIXIN_ORDER_REQUEST = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	
	/**
	 * 微信支付API秘钥 与微信商户后台API秘钥一致
	 */
	public final static String WEIXIN_API_KEY = "RpCrWUwatC2PSGcajJDRMb7Ubc9M3f59";



	/**
	 * XML生成策略
	 * @param o
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String xmlGenerationStrategy(Object o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, UnsupportedEncodingException {
		StringBuilder builder = new StringBuilder("<xml>");
		
		Field[] fields = o.getClass().getDeclaredFields();
		Method[] methods = o.getClass().getMethods();
		
		List<String> list = new ArrayList<>();
		for (Field f : fields) {
			list.add(f.getName());
		}
		
		// 排序
		Collections.sort(list);
		for (String s : list) {
			for (Method m : methods) {
				String mname = m.getName();
				if("get".equals(mname.substring(0, 3)) && s.equals(mname.substring(3).toLowerCase())) {
					Object obj = m.invoke(o, null);
					if(obj != null) {
						builder.append("<");
						builder.append(s);
						builder.append(">");
						if(obj != null) {
							builder.append("<![CDATA[");
							builder.append(obj.toString());
							builder.append("]]>");
						}
						builder.append("</");
						builder.append(s);
						builder.append(">");
					}
				}
			}
		}
		builder.append("<sign>"); 
		builder.append(getSign(o));
		builder.append("</sign>");
		builder.append("</xml>");
		return builder.toString();
	}
	
	/**
	 * 生成签名的字符串参数拼接
	 * @param o
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static String getSignParam(Object o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuilder builder = new StringBuilder();
		Field[] fields = o.getClass().getDeclaredFields();
		Method[] methods = o.getClass().getMethods();
		
		List<String> list = new ArrayList<>();
		for (Field f : fields) {
			if(f.getName().equals("sign")) continue;
			list.add(f.getName());
		}
		
		// 排序
		Collections.sort(list);
		
		for (String s : list) {
			for (Method m : methods) {
				String mname = m.getName();
				if("get".equals(mname.substring(0, 3)) && s.equals(mname.substring(3).toLowerCase())) {
					Object obj = m.invoke(o, null);
					if(obj != null) {
						builder.append(s);
						builder.append("=");
						if(obj != null)
							builder.append(obj.toString());
						builder.append("&");
					}
				}
			}
		}
		builder.append("key=" + WEIXIN_API_KEY);
		return builder.toString();
	}
	
	/**
	 * 生成签名
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String getSign(Object o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, UnsupportedEncodingException {
		String param = getSignParam(o);
		return Md5Utils.encodeMD5(param).toUpperCase();
	}

	/**
	 * 生成签名
	 * @param map
	 * @return
	 */
	public static String getSign(Map<String,Object> map) {
		if(map != null && map.keySet().size() > 0) {
			Set<String> keys = map.keySet();
			List<String> list = new ArrayList<>(keys);
			Collections.sort(list);
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < list.size(); i++) {
				if(i > 0) {
					builder.append("&");
				}
				builder.append(list.get(i));
				builder.append("=");
				builder.append(map.get(list.get(i)));
			}
			builder.append("&key=" + WEIXIN_API_KEY);
			System.out.println(builder.toString());
			return Md5Utils.encodeMD5(builder.toString()).toUpperCase();
		}
		return null;
	}
	
	/**  
     * 将分为单位的转换为元 （除100）  
     *   
     * @param amount  
     * @return  
     * @throws Exception   
     */    
    public static String changeF2Y(BigDecimal amount) throws Exception{    
        return BigDecimal.valueOf(amount.longValue()).divide(new BigDecimal(100)).toString();    
    }    
        
    /**   
     * 将元为单位的转换为分 （乘100）  
     *   
     * @param amount  
     * @return  
     */    
    public static String changeY2F(BigDecimal amount){    
        return amount.multiply(new BigDecimal(100)).intValue()+"";    
    }
	
    public static String getDateTimestamp () {
    	TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		Calendar c = Calendar.getInstance();
		Long time = c.getTimeInMillis() / 1000;
		return time.toString();
    }
    
	
    /**
     * <xml>
     * <return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg>
     * <appid><![CDATA[wx78093a39491e4b32]]></appid>
     * <mch_id><![CDATA[1412646402]]></mch_id>
     * <nonce_str><![CDATA[V44H49eEOtKztAlp]]></nonce_str>
     * <sign><![CDATA[3440F861125EBB8355823838AE759BB0]]></sign>
     * <result_code><![CDATA[SUCCESS]]></result_code>
     * <prepay_id><![CDATA[wx20161122135751dfa6bbee650963791323]]></prepay_id>
     * <trade_type><![CDATA[APP]]></trade_type>
     * </xml>
     * @param args
     */

	/**
	 * 异步调用的创建
	 * @param return_code 返回的SUCCESS/FAIL  SUCCESS表示商户接收通知成功并校验成功
	 * @param return_msg 返回信息，如非空，为错误原因 签名失败 参数格式校验错误
	 * @return
	 */
	public static String createReturnWeixinXML(String return_code, String return_msg) {
		String xml = "<xml> \n" +
				"\n" +
				"  <return_code><![CDATA["+ return_code +"]]></return_code>\n" +
				"  <return_msg><![CDATA["+return_msg+"]]></return_msg>\n" +
				"</xml> \n";
		return xml;
	}


}
