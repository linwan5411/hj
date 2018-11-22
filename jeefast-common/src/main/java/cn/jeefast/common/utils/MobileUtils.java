package cn.jeefast.common.utils;

import cn.jeefast.common.enums.MobileCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * <pre>
 * <b>.</b>
 * <b>Description:</b>
 *
 * <b>Author:zhihang</b>
 * <b>Date: 2018/11/16 0016 11:10   </b>
 * <b>Copyright:</b> Copyright 2008-2026 http://www.jinvovo.com Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver    Date                     Author                        Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2018/11/16 0016 11:10          zhihang            new file.
 * <pre>
 */
public class MobileUtils {


    /**
     * 截取手机号码
     * @param mobile
     * @return
     */
    public static String subMobile(String mobile){
        StringBuffer bf = new StringBuffer(mobile.substring(0,3));
        bf.append("***");
        bf.append(mobile.substring(8,11));
        return bf.toString();
    }

    /**
     * 验证手机号
     * @param mobile
     * @return
     */
    public static boolean valMobile(String mobile){
        if(StringUtils.isBlank(mobile)){
            return false;
        }
        String p = "^(\\d{6,13})";
        if(Pattern.matches(p, mobile)){
            return true;
        }
        return false;
    }

    /**
     * 获取对应的手机号
     * @param mobile
     * @return
     */
    public static String code(String mobile){
        if(StringUtils.isNotBlank(mobile)){
            if(mobile.indexOf(MobileCodeEnum.default_link.getCode()) > 0){
                return mobile.split(MobileCodeEnum.default_link.getCode())[0];
            }
        }
        return MobileCodeEnum.default_code.getCode();
    }


    /**
     * 得到组合的完整国际手机号
     * @param mobile
     * @return
     */
    public static String createAll(String mobile,String countryCode){
        if(StringUtils.isBlank(mobile)){
           return mobile;
        }
        return new StringBuilder(StringUtils.isNotBlank(countryCode) ? countryCode : MobileCodeEnum.default_code.getCode())
                .append(MobileCodeEnum.default_link.getCode())
                .append(mobile).toString();
    }

    /**
     * 得到国际的手机号码
     * @param mobile
     * @return
     */
    public static String codeAll(String mobile){
        if(StringUtils.isNotBlank(mobile)){
            if(mobile.indexOf(MobileCodeEnum.default_link.getCode()) > 0){
                return mobile;
            }else{
                return new StringBuilder(MobileCodeEnum.default_code.getCode())
                        .append(MobileCodeEnum.default_link.getCode())
                        .append(mobile).toString();
            }
        }
        return mobile;
    }

    /**
     * 获取手机号
     * @param mobile
     * @return
     */
    public static String mobile(String mobile){
        if(StringUtils.isNotBlank(mobile)){
            if(mobile.indexOf(MobileCodeEnum.default_link.getCode()) > 0){
                return mobile.split(MobileCodeEnum.default_link.getCode())[1];
            }
        }
        return mobile;
    }


}
