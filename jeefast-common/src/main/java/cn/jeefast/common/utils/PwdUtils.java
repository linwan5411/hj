package cn.jeefast.common.utils;

import cn.jeefast.common.enums.MobileCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * <pre>
 * <b>密码.</b>
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
public class PwdUtils {

    private static String createString (String pwd,String salt){
        return new StringBuffer(pwd).append("@").append(salt).toString();
    }

    /**
     * 密码规则
     * @param pwd
     * @param salt
     * @return
     */
    public static String createPwd(String pwd,String salt){
        return Md5Utils.encodeMD5(createString(pwd,salt)).toLowerCase();
    }

    /**
     * 校验密码
     * @param inputPwd
     * @param salt
     * @param dbPwd
     * @return
     */
    public static boolean verfyPwd(String inputPwd,String salt,String dbPwd){
        if(createPwd(inputPwd,salt).equals(dbPwd)){
            return true;
        }
        return false;
    }
}
