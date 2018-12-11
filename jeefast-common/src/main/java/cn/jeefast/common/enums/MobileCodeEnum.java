package cn.jeefast.common.enums;

/**
 * BU交易的类型
 */
public enum MobileCodeEnum {

    default_code("86"),
    default_link("-"),
    ;

    private String code;

    MobileCodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
