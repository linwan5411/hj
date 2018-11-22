package cn.jeefast.rest.controller;

import cn.jeefast.common.utils.BaseResponse;
import cn.jeefast.common.utils.ResultUtils;
import cn.jeefast.rest.entity.vo.MessageVo;
import cn.jeefast.service.HjMsgRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * API短信接口
 */
@RestController
@RequestMapping("/api/v1")
public class ApiMessageController {

    @Resource
    private HjMsgRecordService hjMsgRecordService;

    @ApiOperation(value = "发送注册短信")
    @PostMapping("/message")
    public BaseResponse sendMessage(@Valid @RequestBody MessageVo messageVo){
        hjMsgRecordService.sendMessage(messageVo.getMobile(),messageVo.getType());
        return ResultUtils.successV2();
    }


}
