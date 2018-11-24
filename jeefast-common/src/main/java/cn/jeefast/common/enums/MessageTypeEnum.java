package cn.jeefast.common.enums;


/**
 * 短息发送枚举
 */
public enum MessageTypeEnum {
    /**
     *  注册
     */
    ZHU_CE("1",2,20),
    /**
     * 修改密码
     */
    XIU_GAI_PWD("2",2,20),

    /**
     * 找回密码
     */
    BACK_PWD("3",2,20),
    /**
     *  联系订单验证码
     */
    ORDER("3",20,20),
    ;

    private String type;

    private int times;

    private int maxTime;

    MessageTypeEnum(String type, int times, int maxTime) {
        this.type = type;
        this.times = times;
        this.maxTime = maxTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }
}
