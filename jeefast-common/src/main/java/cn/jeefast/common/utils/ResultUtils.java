package cn.jeefast.common.utils;

import cn.jeefast.common.enums.ResultEnum;

/**
 * <pre>
 * <b>.</b>
 * <b>Description:</b>
 *
 * <b>Author:zhihang</b>
 * <b>Date: 2018/10/14 0014 18:19   </b>
 * <b>Copyright:</b> Copyright 2008-2026 http://www.jinvovo.com Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver    Date                     Author                        Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2018/10/14 0014 18:19          zhihang            new file.
 * <pre>
 */
public class ResultUtils {


    public static BaseResponse successV2(Object t){
        return new BaseResponse(ResultEnum.REQ_SUCCESS.getCode(),ResultEnum.REQ_SUCCESS.getReminder(),t);
    }

    public static BaseResponse successV2(){
        return new BaseResponse(ResultEnum.REQ_SUCCESS.getCode(),ResultEnum.REQ_SUCCESS.getReminder());
    }
}
