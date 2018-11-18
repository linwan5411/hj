package cn.jeefast.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * <pre>
 * <b>.</b>
 * <b>Description:</b>
 *
 * <b>Author: 641597345@qq.com </b>
 * <b>Date: 2018/8/30 0030 17:08   </b>
 * <b>Copyright:</b> Copyright 2008-2026 http://www.jinvovo.com Technology Co., Ltd. All rights reserved.
 * <b>Changelog:</b>
 *   ----------------------------------------------------------------------------
 *   Ver    Date                     Author                        Detail
 *   ----------------------------------------------------------------------------
 *   1.0   2018/8/30 0030 17:08          zhihang            new file.
 * <pre>
 */
@RestControllerAdvice(basePackages = "cn.jeefast.controller")
public class ControllerAdviceConfig {

    private static Logger logger = LoggerFactory.getLogger(ControllerAdviceConfig.class);

    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object processApplicationCheckedException(NativeWebRequest request, Exception e) throws Exception {
        String msg = "bu batch system error";
        /*e.printStackTrace();
        logger.error(msg+",exp:{}",e.getMessage());
        String code = ResultEnum.SYSTEM_ERROR_EXP.getCode();
        if (e instanceof MethodArgumentTypeMismatchException) {
            msg = "request param type not match";
            code = ResultEnum.REQ_PARAM_EXP.getCode();
        }else if(e instanceof BusinessException){
            msg = e.getMessage();
            code = ((BusinessException) e).getCode();
        }else if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            if(me.getBindingResult() != null && me.getBindingResult().getFieldErrors() != null && me.getBindingResult().getFieldErrors().size() > 0){
                msg = me.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
                code = ResultEnum.REQ_PARAM_EXP.getCode();
            }
        }else if (e instanceof BindException){
            BindException bindException = (BindException) e;
            if (bindException.getBindingResult()!=null && bindException.getBindingResult().getFieldError()!=null){
                msg = bindException.getBindingResult().getFieldError().getDefaultMessage();
                code = ResultEnum.REQ_PARAM_EXP.getCode();
            }
        }else if (e instanceof HystrixRuntimeException){
            msg =  e.getCause().getMessage();
            code = ResultEnum.getSortCode(msg);
            //msg = ResultEnum.getSortCodeNameNone(msg);
        }
        Map<String,Object> map = new HashMap<>();
        HttpServletRequest req = request.getNativeRequest(HttpServletRequest.class);

        //老接口
        String uri = req.getRequestURI();
        if(UriUtils.isUri(uri,wallet_path) || UriUtils.isUri(uri,ilife_path)){
            map.put("code",code);
            map.put("message",ResultEnum.getSortCodeName(msg));
        }else{
        //新接口
            map.put("code",code);
            map.put("status",code);
            map.put("message",ResultEnum.getSortCodeName(msg));
            map.put("timestamp",System.currentTimeMillis());
        }*/
        //return map;
        return null;
    }

}
