package cn.jeefast.common.enums;

/**
 * 状态码
 */
public enum ResultEnum {

    /******************************基本验证*********************************/

    /**
     * 处理成功
     */
    REQ_SUCCESS("20000","处理成功","success"),

    /**********************************************************/

    REQ_PARAM_EXP("11001","参数不合法","param bind exception"),
    REQ_MAX_IMAGE_EXP("11002","图片最大为9张","param bind exception"),


    LOGIN_EXP("13002","登陆信息不正确","param bind exception"),
    COLLECT_EXP("13003","收藏失败","param bind exception"),
    COLLECT_EXIST_EXP("13004","已收藏","param bind exception"),
    ORDER_EXP("13005","联系对象不明确","param bind exception"),
    ORDER_USER_EXP("13006","联系对象不存在","param bind exception"),


    MOBILE_EXIST("12001","手机已注册","param bind exception"),
    MOBILE_NOT_EXIST("12002","手机未注册","param bind exception"),
    MOBILE_REG_EXP("12003","注册失败","param bind exception"),
    MOBILE_PASS_EXP("12004","密码错误","param bind exception"),
    MOBILE_BLOCK_EXP("12005","账户被冻结","param bind exception"),
    TWO_PWD_EXP("12006","两次密码不一致","param bind exception"),

    SER_AUTH_EXP("14001","认证失败","param bind exception"),
    SER_NOT_AUTH_EXP("14002","还未认证","param bind exception"),




    /*******************************系统级别*********************************/

    /**
     * 系统异常
     */
    SYSTEM_ERROR_EXP("50000","系统异常，请联系您的最美客户","error"),

    /**
     * 参数处理异常
     */
    TOOL_EXP("50001","参数处理异常","param bind exception"),

    /**
     * 请求超时
     */
    REQ_TIMEOUT_EXP("50002","请求超时"," request time out"),

    /**********************************短信************************************/

    /**
     * 超过该业务短信日最大次数
     */
    MESSAGE_MAX_EXP("30001","超过该业务短信日最大次数","message max times of 5"),

    /**
     * 短信发送失败
     */
    MESSAGE_SEND_EXP("30002","短信发送失败","message send error"),

    /**
     * 短信验证码错误
     */
    MESSAGE_VALIDATE_EXP("30003",",短信验证码错误","message error"),

    /**
     * 短信验证码错误
     */
    MESSAGE_MOBILE_EXP("30004","手机号码不正确","mobile error"),


    /**
     * 短信验证码错误
     */
    MESSAGE_MOBILE_TIMEOUT_EXP("30005","短信验证码已过期","mobile error"),


    ;


    /**
     * 获取异常信息
     * @param message
     * @return
     */
    public static String getSortCodeName(String message){
        try {
            if(message.indexOf(">") >= 0 && message.indexOf("<") >= 0){
                return message.substring(message.indexOf("<")+1, message.indexOf(">")).split(",")[1];
            }else{
                return message.split(",")[1];
            }
        }catch (Exception e){

        }
        return message;
    }

    /**
     * 获取对应的code
     * @param message
     * @return
     */
    public static String getSortCode(String message){
        try {
            if(message.indexOf(">") >= 0 && message.indexOf("<") >= 0){
                return message.substring(message.indexOf("<")+1, message.indexOf(">")).split(",")[0];
            }else{
                return message.split(",")[0];
            }
        }catch (Exception e){

        }
        return ResultEnum.REQ_TIMEOUT_EXP.getCode();
    }
    /**
     * 获取异常信息
     * @param message
     * @return
     */
    public static String getSortCodeNameNone(String message){
        try {
            return message.split(",")[1];
        }catch (Exception e){

        }
        return message;
    }

    /**
     * 获取对应的code
     * @param message
     * @return
     */
    public static String getSortCodeNone(String message){
        try {
            return message.split(",")[0];
        }catch (Exception e){

        }
        return ResultEnum.REQ_TIMEOUT_EXP.getCode();
    }

    private String code;

    private String message;

    private String reminder;

    ResultEnum(String code, String message,String reminder) {
        this.code = code;
        this.message = message;
        this.reminder = reminder;
    }

    /**
     * 根据编码来检索对应的枚举值
     * @param code
     * @return
     */
    public static ResultEnum getResultEnumByCode(String code) {
        for (ResultEnum _enum : values()) {
            if (_enum.getCode().equalsIgnoreCase(code)) {
                return _enum;
            }
        }
        return SYSTEM_ERROR_EXP;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
}
