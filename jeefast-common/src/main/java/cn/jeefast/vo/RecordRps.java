package cn.jeefast.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 返回的BU赠送通知消息
 */
public class RecordRps implements Serializable {

    private static final long serialVersionUID = -1279332744224345045L;
    /**
     * 联系人手机号
     */
    private String mobile;

    /**
     * 被联系人对象
     */
    private String userName;

    /**
     * 联系的时间
     */
    private String dateTime;

    /**
     * 联系的类型
     */
    private String linkType;

    public String getLinkType() {
        if("1".equals(linkType)){
            return "农场主";
        }else{
            return "服务商";
        }
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Long days;

    @JsonIgnore
    private Long mins;

    @JsonIgnore
    private Long hours;


    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public Long getMins() {
        return mins;
    }

    public void setMins(Long mins) {
        this.mins = mins;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateTime() {
        if(StringUtils.isNotBlank(dateTime)){
            return dateTime;
        }
        return vorgesternDay();
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 标签
     * @return
     */
    private  String vorgesternDay(){
       if(days > 1){
           return days+"天前";
       }else if(days == 1){
           return "昨天";
       }else if(hours > 0){
           return hours+"小时前";
       }else if(mins > 0){
           return mins+"分钟前";
       }else{
           return "刚刚";
       }
    }

}
