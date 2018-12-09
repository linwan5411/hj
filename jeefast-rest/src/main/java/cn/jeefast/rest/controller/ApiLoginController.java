package cn.jeefast.rest.controller;

import cn.jeefast.common.enums.MessageTypeEnum;
import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.common.utils.BaseResponse;
import cn.jeefast.common.utils.ResultUtils;
import cn.jeefast.common.utils.TokenUtil;
import cn.jeefast.entity.HjUser;
import cn.jeefast.rest.entity.vo.*;
import cn.jeefast.service.HjMsgRecordService;
import cn.jeefast.service.HjUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * API登录授权
 */
@RestController
@RequestMapping("/api/v1")
public class ApiLoginController {

    @Resource
    private HjUserService hjUserService;

    @Resource
    private HjMsgRecordService hjMsgRecordService;

    @ApiOperation(value = "登陆")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body",
            dataType = "LoginParamVo", name = "paramVo", value = "登陆参数", required = true) })
    @PostMapping("/login")
    public BaseResponse login(@Valid @RequestBody LoginParamVo paramVo){
        Map<String,Object> map = hjUserService.login(paramVo.getMobile(),paramVo.getPass());
        return ResultUtils.successV2(map);
    }

    @ApiOperation(value = "注册")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body",
            dataType = "EnrollVo", name = "paramVo", value = "注册参数", required = true) })
    @PostMapping("/enroll")
    public BaseResponse enroll(@Valid @RequestBody EnrollVo paramVo){
        hjMsgRecordService.validateMsgCode(paramVo.getCode(),paramVo.getMobile(), MessageTypeEnum.ZHU_CE.getType(),MessageTypeEnum.ZHU_CE.getMaxTime());
        Map<String,Object> map = hjUserService.enroll(paramVo.getMobile(),paramVo.getMobile());
        return ResultUtils.successV2(map);
    }

    @ApiOperation(value = "找回密码")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body",
            dataType = "EnrollVo", name = "paramVo", value = "找回密码", required = true) })
    @PostMapping("/backPass")
    public BaseResponse backPass(@Valid @RequestBody EnrollVo paramVo){
        hjMsgRecordService.validateMsgCode(paramVo.getCode(),paramVo.getMobile(), MessageTypeEnum.BACK_PWD.getType(),MessageTypeEnum.BACK_PWD.getMaxTime());
        hjUserService.updatePassWord(paramVo.getMobile(),paramVo.getPass());
        return ResultUtils.successV2();
    }


    @ApiOperation(value = "修改密码")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body",
            dataType = "BackPwdVo", name = "backPwdVo", value = "修改密码", required = true) })
    @PostMapping("/updatePass")
    public BaseResponse updatePass(@Valid @RequestBody BackPwdVo backPwdVo){
        if(backPwdVo.equals(backPwdVo.getConfirmPass())){
            throw new BusinessException("两次密码不一致", ResultEnum.TWO_PWD_EXP.getCode());
        }
        hjMsgRecordService.validateMsgCode(backPwdVo.getCode(),backPwdVo.getMobile(), MessageTypeEnum.XIU_GAI_PWD.getType(),MessageTypeEnum.XIU_GAI_PWD.getMaxTime());
        hjUserService.updatePassWord(backPwdVo.getMobile(),backPwdVo.getNewPass());
        return ResultUtils.successV2();
    }


    @ApiOperation(value = "修改个人资料")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body",
            dataType = "UserDataVo", name = "userDataVo", value = "修改个人资料", required = true) })
    @PostMapping("/updateData")
    public BaseResponse updateData(@Valid @RequestBody UserDataVo userDataVo){
        Long userId = TokenUtil.parseUserId(userDataVo.getToken());
        HjUser user = new HjUser();
        user.setUserMobile(userDataVo.getMobile());
        user.setUserId(userId);
        user.setUserPortrait(userDataVo.getUserPortrait());
        user.setUserName(userDataVo.getUserName());
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.where("user_id={0}",userId);
        hjUserService.update(user,entityWrapper);
        return ResultUtils.successV2();
    }
}
