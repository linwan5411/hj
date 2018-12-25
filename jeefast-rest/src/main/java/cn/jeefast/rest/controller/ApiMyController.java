package cn.jeefast.rest.controller;

import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.common.utils.BaseResponse;
import cn.jeefast.common.utils.ResultUtils;
import cn.jeefast.common.utils.TokenUtil;
import cn.jeefast.rest.entity.TokenPageVo;
import cn.jeefast.rest.entity.vo.CollectVo;
import cn.jeefast.rest.entity.vo.TokenVo;
import cn.jeefast.service.HjMessageService;
import cn.jeefast.service.HjUserCollectService;
import cn.jeefast.service.HjUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 我的个人中心
 */
@RestController
@RequestMapping("/api/v1")
public class ApiMyController {

    @Resource
    private HjUserCollectService hjUserCollectService;

    @Resource
    private HjUserService hjUserService;

    @Resource
    private HjMessageService hjMessageService;

    @ApiOperation(value = "我的收藏")
    @PostMapping("/myCollect/{type}")
    public BaseResponse myCollect(@PathVariable("type")Integer type, @RequestBody TokenPageVo basePage){
        if(type == null || type > 2 || type <= 0){
            return null;
        }
        Long userId = TokenUtil.parseUserId(basePage.getToken());
        return ResultUtils.successV2(hjUserCollectService.myCollect(type,userId,basePage.getPageIndex(),basePage.getPageSize(),
                basePage.getLatitude(),basePage.getLongitude()));
    }

    @ApiOperation(value = "添加到我的收藏")
    @PostMapping("/addCollect")
    public BaseResponse addCollect(@Valid @RequestBody CollectVo collectVo){
        if(collectVo.getCollectType() == null || collectVo.getCollectType() > 2 || collectVo.getCollectType() <= 0){
            throw new BusinessException("收藏失败", ResultEnum.COLLECT_EXP.getCode());
        }
        Long userId = TokenUtil.parseUserId(collectVo.getToken());
        hjUserCollectService.addCollect(userId,collectVo.getCollectType(),collectVo.getObjectId());
        return ResultUtils.successV2();
    }

    @ApiOperation(value = "删除我的收藏")
    @PostMapping("/deleteCollect/{collectId}")
    public BaseResponse deleteCollect(@PathVariable("collectId")Long collectId,@RequestBody TokenVo tokenVo){
        Long userId = TokenUtil.parseUserId(tokenVo.getToken());
        hjUserCollectService.deleteCollect(userId,collectId);
        return ResultUtils.successV2();
    }

    @ApiOperation(value = "清空我的收藏")
    @PostMapping("/cleanCollect")
    public BaseResponse cleanCollect(@RequestBody TokenVo tokenVo){
        Long userId = TokenUtil.parseUserId(tokenVo.getToken());
        hjUserCollectService.cleanCollect(userId);
        return ResultUtils.successV2();
    }

    @ApiOperation(value = "我的个人中心信息")
    @PostMapping("/myZoneData")
    public BaseResponse myZoneData(@RequestBody TokenVo tokenVo){
        Long userId = TokenUtil.parseUserId(tokenVo.getToken());
        return ResultUtils.successV2(hjUserService.myZoneData(userId));
    }


    @ApiOperation(value = "查看用户的个人信息")
    @PostMapping("/findUserInfo/{userId}")
    public BaseResponse findUserInfo(@PathVariable("userId")Long userId){
        return ResultUtils.successV2(hjUserService.findUserInfo(userId));
    }

    @ApiOperation(value = "查看我的消息列表")
    @PostMapping("/myMessageList")
    public BaseResponse myMessageList(@RequestBody TokenPageVo tokenPageVo){
        Long userId = TokenUtil.parseUserId(tokenPageVo.getToken());
        return ResultUtils.successV2(hjMessageService.findMyMessage(userId,tokenPageVo.getPageIndex(),tokenPageVo.getPageSize()));
    }

    @ApiOperation(value = "查看我的消息")
    @PostMapping("/viewMessage/{messageId}")
    public BaseResponse viewMessage(@PathVariable("messageId")Long messageId,@RequestBody TokenVo tokenVo){
        Long userId = TokenUtil.parseUserId(tokenVo.getToken());
        hjMessageService.viewMessage(userId,messageId);
        return ResultUtils.successV2();
    }
}
