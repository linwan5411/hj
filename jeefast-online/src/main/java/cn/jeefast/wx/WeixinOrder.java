package cn.jeefast.wx;

import java.io.Serializable;

/**
 * 微信下单对象 用于微信APP支付统一下单
 * 除被扫支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易回话标识后再按扫码、
 * JSAPI、APP等不同场景生成交易串调起支付。 URL地址：https://api.mch.weixin.qq.com/pay/unifiedorder
 * 
 * @author WJ
 *
 */
public class WeixinOrder implements Serializable{

	private static final long serialVersionUID = -8374457517373416706L;
	
	private String appid; // 应用ID
	private String attach; // 附加数据
	private String body; // 商品描述
	private String device_info; // 设备号
	private String detail; // 商品详情
	private String fee_type; // 货币类型 默认人民币：CNY
	private String goods_tag; // 商品标记
	private String limit_pay; // 指定支付方式
	private String time_start; // 交易起始时间
	private String sign; // 签名
	private String mch_id; // 商户号
	private String nonce_str; // 随机字符串
	private String sign_type; // 签名类型
	private String spbill_create_ip; // 终端IP
	private String out_trade_no; // 商户订单号
	private String notify_url; // 通知地址
	private String total_fee; // 总金额
	private String time_expire; // 交易结束时间
	private String trade_type; // 交易类型
	private String product_id;	// 商品ID
	private String input_charset;// 字符编码
	private String openid;		// 用户在商户appid下的唯一标识 trade_type=JSAPI，此参数必传
	private String scene_info;	// 该字段用于上报支付的场景信息,针对H5支付有以下三种场景,请根据对应场景上报
								//{"h5_info": {"type":"IOS","app_name": "王者荣耀","bundle_id": "com.tencent.wzryIOS"}}
								//{"h5_info": {"type":"Android","app_name": "王者荣耀","package_name": "com.tencent.tmgp.sgame"}}
								//{"h5_info": {"type":"Wap","wap_url": "https://m.jd.com","wap_name": "京东官网"}}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScene_info() {
//		if("JSAPI".equals(trade_type)) {
//			scene_info = "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"https://www.xlx101.com\",\"wap_name\": \"校连校\"}}";
//		}
		return scene_info;
	}

	public void setScene_info(String scene_info) {
		this.scene_info = scene_info;
	}

	public String getInput_charset() {
		return input_charset;
	}
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getAppid() {
		return appid;
	}
	public String getAttach() {
		return attach;
	}
	public String getBody() {
		return body;
	}
	public String getDevice_info() {
		return device_info;
	}
	public String getDetail() {
		return detail;
	}
	public String getFee_type() {
		return fee_type;
	}
	public String getGoods_tag() {
		return goods_tag;
	}
	public String getLimit_pay() {
		return limit_pay;
	}
	public String getMch_id() {
		return mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public String getSign() {
		return sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public String getTime_start() {
		return time_start;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}
	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
}
