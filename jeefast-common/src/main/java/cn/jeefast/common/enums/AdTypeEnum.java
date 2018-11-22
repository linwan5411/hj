package cn.jeefast.common.enums;


/**
 * 短息发送枚举
 */
public enum AdTypeEnum {
    /**
     *  主页广告
     */
    HOME(1L),
    /**
     * 学习农
     */
    XUE_NONG(2L),

    ;
    private Long site;

    AdTypeEnum(Long site) {
        this.site = site;
    }

    public Long getSite() {
        return site;
    }

    public void setSite(Long site) {
        this.site = site;
    }
}
