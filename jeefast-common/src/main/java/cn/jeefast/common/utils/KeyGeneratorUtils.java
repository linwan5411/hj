package cn.jeefast.common.utils;

import cn.jeefast.common.key.DefaultKeyGenerator;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

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
     *
     * @return
     */
    public static Long getLongValue() {
        //return gk.generateKey().longValue();
        return Long.valueOf(getUnqueId());
    }

    /**
     * 默认采用的是18位
     *
     * @return
     */
    public static Long getLongValue(String mobile) {
        if (StringUtils.isNumeric(mobile) && StringUtils.isNotBlank(mobile)) {
            try {
                String text = 8 + String.format("%011d",mobile);
                return Long.valueOf(text);
            } catch (Exception e) {

            }
        }
        return Long.valueOf(getUnqueId());
    }


    public static String getUnqueId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId+String.format("%011d", hashCodeV);
    }

}
