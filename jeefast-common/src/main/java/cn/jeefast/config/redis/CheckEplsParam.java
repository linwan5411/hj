package cn.jeefast.config.redis;

import org.apache.commons.lang.StringUtils;

/**
 * 检查表达式
 */
public class CheckEplsParam {

    /**
     * 验证是否含有表达式
     * @param key
     * @return
     */
    public static boolean checkSpel(String key){
        if(StringUtils.isBlank(key)){
            return false;
        }
        if(key.indexOf("#") > -1){
            return true;
        }
        return false;
    }

}
