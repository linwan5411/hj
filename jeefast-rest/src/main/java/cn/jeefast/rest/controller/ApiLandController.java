package cn.jeefast.rest.controller;

import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.common.utils.BaseResponse;
import cn.jeefast.common.utils.LocationUtils;
import cn.jeefast.common.utils.ResultUtils;
import cn.jeefast.common.utils.TokenUtil;
import cn.jeefast.config.RedisUtils;
import cn.jeefast.entity.*;
import cn.jeefast.rest.entity.vo.*;
import cn.jeefast.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 农场主的处理
 */
@RestController
@RequestMapping("/api/v1")
public class ApiLandController {

    /**
     * 首页推荐查询最近的距离取3条数据
     */
    private static final int homeSize = 3;

    /**
     * 显示的通知条数
     */
    private static final int notifySize = 10;

    @Resource
    private HjHaciendaInfoService hjHaciendaInfoService;

    @Resource
    private HjFarmersInfoService hjFarmersInfoService;

    @Resource
    private RedisUtils redisUtils;


    @ApiOperation(value = "农场主认证")
    @PostMapping("/farmersAuth")
    public BaseResponse farmersAuth(@Valid @RequestBody FramerAuthVo framerAuthVo){
        //token
        Long userId = TokenUtil.parseUserId(framerAuthVo.getToken());
        HjFarmersInfo info = new HjFarmersInfo();
        BeanUtils.copyProperties(framerAuthVo,info);
        info.setUserId(userId);
        Long farmersId = null;
        try {
            farmersId = hjFarmersInfoService.farmersAuth(info);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                throw new BusinessException("您已是农场主", ResultEnum.SER_AUTH_EXP.getCode());
            }else{
                throw e;
            }
        }
        framerAuthVo.setFarmersId(farmersId);
        redisUtils.delete("wh_farmersInfo",userId.toString());
        redisUtils.delete("wh_farmersInfo_id",farmersId.toString());
        return ResultUtils.successV2(framerAuthVo);
    }

    @ApiOperation(value = "农场主详情")
    @PostMapping("/farmersInfo")
    public BaseResponse farmersInfo(@Valid @RequestBody TokenVo tokenVo){
        //token
        Long userId = TokenUtil.parseUserId(tokenVo.getToken());
        return ResultUtils.successV2(hjFarmersInfoService.farmersInfo(userId));
    }

    @ApiOperation(value = "根据农场主ID查询农场主详情")
    @PostMapping("/farmersInfo/{farmersId}")
    public BaseResponse farmersInfo(@PathVariable("farmersId")Long farmersId){
        return ResultUtils.successV2(hjFarmersInfoService.farmersInfoById(farmersId));
    }

    @ApiOperation(value = "添加土地")
    @PostMapping("/landApprove")
    public BaseResponse landApprove(@Valid @RequestBody LandAuthVo serverAuthVo){

        //token
        Long userId = TokenUtil.parseUserId(serverAuthVo.getToken());

        //农场主
        HjHaciendaInfo info = new HjHaciendaInfo();
        BeanUtils.copyProperties(serverAuthVo,info);

        //案例
        List<HjHaciendaRemark> list = new ArrayList<>();
        List<LandRemakVo> remarkList = serverAuthVo.getRemarkList();
        if(remarkList != null && remarkList.size() > 0){
            remarkList.forEach(e ->{
                HjHaciendaRemark vo = new HjHaciendaRemark();
                BeanUtils.copyProperties(e,vo);
                list.add(vo);
            });
        }

        Long serverId = hjHaciendaInfoService.landApprove(info,list,userId);
        serverAuthVo.setHaciendaId(serverId);
        redisUtils.delete("wanhe_HjHaciendaInfo",serverId.toString());
        return ResultUtils.successV2(serverAuthVo);
    }

    @ApiOperation(value = "查询我的所有土地")
    @PostMapping("/myLands")
    public BaseResponse myLands(@Valid @RequestBody TokenVo tokenVo){
        //token
        Long userId = TokenUtil.parseUserId(tokenVo.getToken());
        if(userId == null){
            return ResultUtils.successV2();
        }
        return ResultUtils.successV2(hjHaciendaInfoService.findLandByUserId(userId));
    }

    @ApiOperation(value = "经纬度查询最近3条的农场主")
    @PostMapping("/findLand")
    public BaseResponse findLand(@RequestBody CordVo cordVo){
        List<Map<String,Object>> list = hjHaciendaInfoService.findLand(cordVo.getAreaId(),cordVo.getLat(),cordVo.getLng(),homeSize);
        return ResultUtils.successV2(list);
    }

    @ApiOperation(value = "经纬度分页查询土地")
    @PostMapping("/findLandMore")
    public BaseResponse findLandMore(@RequestBody CordPageVo basePage){
        List<Map<String,Object>> list = hjHaciendaInfoService.findLandMore(basePage.getLng(),basePage.getLat(),
                basePage.getAreaId(),basePage.getAuthType(),basePage.getUserType(),basePage.getPageIndex(),
                basePage.getPageSize(),basePage.getCategoryCode());
        return ResultUtils.successV2(list);
    }

    @ApiOperation(value = "查询土地的详情")
    @PostMapping("/lndInfo/{haciendaId}")
    public BaseResponse findLandDetail(@PathVariable("haciendaId")Long haciendaId,@RequestBody CordOnlyVo cordOnlyVo){
        HjHaciendaInfo h = hjHaciendaInfoService.findLandDetail(haciendaId);
        if(h != null){
            int m = LocationUtils.getDistanceToM(cordOnlyVo.getLat(),cordOnlyVo.getLng(),h.getLatitude(),h.getLongitude());
            h.setDistnce(m+"");
        }
        return ResultUtils.successV2(h);


    }
}
