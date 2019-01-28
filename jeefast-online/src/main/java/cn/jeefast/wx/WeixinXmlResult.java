package cn.jeefast.wx;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 微信订单API接口返回的XML解析对象
 * @author WJ
 *
 */
@XmlRootElement(name = "xml")
public class WeixinXmlResult implements Serializable{

	private static final long serialVersionUID = -5471667170865721589L;
	
	private String return_code;	// 微信API返回状态码
	private String return_msg;	// 返回信息
	private String appid;		// 应用APPID
	private String mch_id;		// 商户号
	private String device_info;	// 设备号
	private String nonce_str;	// 随机字符串
	private String sign;		// 签名
	private String result_code;	// 业务结果
	private String prepay_id;	// 预支付交易会话标识
	private String trade_type;	// 交易类型
	private String err_code;	// 错误代码
	private String err_code_des;// 错误代码描述
	private String timestamp;	// 时间戳
	private Long order_id;	// 订单编号
	
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getDevice_info() {
		return device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public String getErr_code() {
		return err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getReturn_code() {
		return return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public String getAppid() {
		return appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public String getSign() {
		return sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	
}
