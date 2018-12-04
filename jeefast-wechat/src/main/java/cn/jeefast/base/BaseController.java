package cn.jeefast.base;

import cn.jeefast.common.utils.HttpClientUtils;
import cn.jeefast.common.utils.IPUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

   public static final String address_url = "http://pv.sohu.com/cityjson?ie=utf-8";
   public static final String default_address = "500000";

    /**
     * 用户的编码
     */
   public static final String USER_CODE = "USER_CODE";


   public static void setAreaCode(String code,HttpServletRequest request){
       request.getSession().setAttribute(USER_CODE,code);
   }

    public static String  getAreaCode(HttpServletRequest request){
        Object o = request.getSession().getAttribute(USER_CODE);
        if(o != null){
            return o.toString();
        }
        return null;
   }


   private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 根据IP获取对应的省编码
     * @param request
     * @return
     */
   public static String getPriviceCode(HttpServletRequest request){
       String ip = IPUtils.getIpAddr(request);
       if(StringUtils.isNotBlank(ip) || "127.0.0.1".equals(ip)){
           return default_address;
       }
       try {
           String res = HttpClientUtils.get(address_url+ip,null);
           if(StringUtils.isNotBlank(res)){
              if(res.indexOf(",") > 0 && res.lastIndexOf(",") > 0){
                  String code = res.substring(res.indexOf(",")+1, res.lastIndexOf(",")).split(":")[1];
                  if(Long.valueOf(code) > 0){
                      return code;
                  }
              }
           }
       }catch (Exception e){
          logger.error("根据IP获取地址错误");
       }
       return default_address;
   }

    public static void main(String[] args) throws JSONException {
         String res = "{\"code\":0,\"data\":{\"ip\":\"218.192.3.42\",\"country\":\"中国\",\"area\":\"\",\"region\":\"广东\",\"city\":\"广州\",\"county\":\"XX\",\"isp\":\"教育网\",\"country_id\":\"CN\",\"area_id\":\"\",\"region_id\":\"440000\",\"city_id\":\"440100\",\"county_id\":\"xx\",\"isp_id\":\"100027\"}}";
        JSONObject  json = new JSONObject(res);
        JSONObject data = (JSONObject) json.get("data");
        System.out.println(data);

        String x = HttpClientUtils.get("http://pv.sohu.com/cityjson?ie=utf-8",null);
        System.out.println(x.substring(x.indexOf(",")+1, x.lastIndexOf(",")).split(":")[1]);
    }
}
