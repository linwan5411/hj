package cn.jeefast.common.enums;


/**
 * 通用枚举
 */
public enum CommonEnum {

    ONE("1",1,"适合1的状态"),
    TWO("2",2,"适合2的状态"),
    ZERO("0",0,"适合0的状态"),
    MINUS("-1",-1,"适合-1的状态")
    ;

    private String str_state;

    private Integer int_state;

    private String remark;

    CommonEnum(String str_state, Integer int_state, String remark) {
        this.str_state = str_state;
        this.int_state = int_state;
        this.remark = remark;
    }

    public String getStr_state() {
        return str_state;
    }

    public void setStr_state(String str_state) {
        this.str_state = str_state;
    }

    public Integer getInt_state() {
        return int_state;
    }

    public void setInt_state(Integer int_state) {
        this.int_state = int_state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
