package cn.jeefast.common.utils;

import cn.jeefast.common.key.DefaultKeyGenerator;
import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 * <b>.主键生成long类型的规则</b>
 * <b>Description:</b>
 *
 * <b>Author:zhihang</b>
 * <b>Date: 2018/9/7 0007 15:06   </b>
 * <b>Copyright:</b> Copyright 2008-2026 http://www.jinvovo.com Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver    Date                     Author                        Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2018/9/7 0007 15:06          zhihang            new file.
 * <pre>
 */
public class KeyGeneratorUtils {

    private final static DefaultKeyGenerator gk = new DefaultKeyGenerator();

    /**
     * 默认采用的是18位
     * @return
     */
    public static Long getLongValue(){
        return gk.generateKey().longValue();
    }

    /**
     * 默认采用的是18位
     * @return
     */
    public static Long getLongValue(String mobile){
        if(StringUtils.isNumeric(mobile) && StringUtils.isNotBlank(mobile)){
            try {
                Long index = Long.valueOf(mobile.substring(mobile.length() - 1,mobile.length()));
                if(index % 2 == 0){
                    return  gk.generateKey().longValue();
                }else{
                    return gk.generateKey().longValue() + index;
                }
            }catch (Exception e){

            }
        }
        return gk.generateKey().longValue();
    }
}
