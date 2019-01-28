package cn.jeefast.wx;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by Ro on 2017/4/21.
 *
 *  授权作用域（scope）	接口	接口说明
     snsapi_base	/sns/oauth2/access_token	通过code换取access_token、refresh_token和已授权scope
     /sns/oauth2/refresh_token	刷新或续期access_token使用
     /sns/auth	检查access_token有效性
     snsapi_userinfo	/sns/userinfo	获取用户个人信息
 *
 * 微信登录
 */
public class WeixinLogin implements Serializable{

    private String appid = WeixinUtils.WEIXIN_APPID;     //应用唯一标识
    private String redirect_uri;                         //重定向地址，需要进行UrlEncode
    private String response_type = "code";               //填code
    private String scope = "snsapi_login";               //应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
    private String state;                                //用于保持请求和回调的状态，授权请求后原样带回给第三方。
                                                         // 该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
    public WeixinLogin(String redirect_uri, String state) {
        this.redirect_uri = redirect_uri;
        this.state = state;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getResponse_type() {
        if(StringUtils.isBlank(response_type)) {
            response_type = "code";
        }
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getScope() {
        if(StringUtils.isBlank(scope)) {
            scope = "snsapi_login";
        }
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "WeixinLogin{" +
                "appid='" + appid + '\'' +
                ", redirect_uri='" + redirect_uri + '\'' +
                ", response_type='" + response_type + '\'' +
                ", scope='" + scope + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getParam() {
        StringBuffer buffer = new StringBuffer("appid=");
        buffer.append(appid);
        buffer.append("&redirect_uri=" + redirect_uri);
        buffer.append("&response_type=" + response_type);
        buffer.append("&scope=" + scope);
        if(!StringUtils.isBlank(state)) {
            buffer.append("&state=" + state);
        }
        return  buffer.toString();
    }

}
