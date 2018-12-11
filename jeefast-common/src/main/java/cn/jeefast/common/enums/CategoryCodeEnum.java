package cn.jeefast.common.enums;


/**
 * 类别的枚举
 */
public enum CategoryCodeEnum {

    server(1L),
    land(9L),
    study(15L),
    ;

    private Long pid;

    CategoryCodeEnum(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
