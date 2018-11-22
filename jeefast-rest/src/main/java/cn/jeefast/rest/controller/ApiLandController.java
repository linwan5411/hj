package cn.jeefast.rest.controller;

import cn.jeefast.common.utils.BaseResponse;
import cn.jeefast.common.utils.ResultUtils;
import cn.jeefast.common.utils.TokenUtil;
import cn.jeefast.entity.*;
import cn.jeefast.rest.entity.vo.*;
import cn.jeefast.service.HjHaciendaInfoService;
import cn.jeefast.service.HjHaciendaRemarkService;
import cn.jeefast.service.HjServerCaseService;
import cn.jeefast.service.HjServerInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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

    @ApiOperation(value = "农场主认证")
    @PostMapping("/landApprove")
    public BaseResponse landApprove(@Valid @RequestBody LandAuthVo serverAuthVo){

        //token
        Long userId = TokenUtil.parseUserId(serverAuthVo.getToken());

        //服务
        HjHaciendaInfo info = new HjHaciendaInfo();
        BeanUtils.copyProperties(serverAuthVo,info);

        //案例
        List<HjHaciendaRemark> list = new ArrayList<>();
        List<LandRemakVo> remarkList = serverAuthVo.getRemarkList();
        if(remarkList != null && remarkList.size() > 0){
            remarkList.forEach(e ->{
                HjHaciendaRemark vo = new HjHaciendaRemark();
                BeanUtils.copyProperties(vo,e);
                list.add(vo);
            });
        }

        Long serverId = hjHaciendaInfoService.landApprove(info,list,userId);
        serverAuthVo.setHaciendaId(serverId);
        return ResultUtils.successV2(serverAuthVo);
    }


    @ApiOperation(value = "经纬度查询最近3条的农场主")
    @PostMapping("/findLand")
    public BaseResponse findLand(@RequestBody CordVo cordVo){
        List<Map<String,Object>> list = hjHaciendaInfoService.findLand(cordVo.getAreaId(),cordVo.getLat(),cordVo.getLng(),homeSize);
        return ResultUtils.successV2(list);
    }

    @ApiOperation(value = "经纬度分页查询服务商")
    @PostMapping("/findLandMore")
    public BaseResponse findLandMore(@RequestBody CordPageVo basePage){
        List<Map<String,Object>> list = hjHaciendaInfoService.findLandMore(basePage.getLng(),basePage.getLat(),
                basePage.getAreaId(),basePage.getAuthType(),basePage.getUserType(),basePage.getPageIndex(),
                basePage.getPageSize(),basePage.getCategoryCode());
        return ResultUtils.successV2(list);
    }

    @ApiOperation(value = "查询农场的详情")
    @PostMapping("/userCase/{haciendaId}")
    public BaseResponse findLandDetail(@PathVariable("haciendaId")Long haciendaId){
        return ResultUtils.successV2(hjHaciendaInfoService.findLandDetail(haciendaId));
    }


    @ApiOperation(value = "查询目前订单量与订阅的服务通知")
    @PostMapping("/findOrderAndNotify")
    public BaseResponse findOrderAndNotify(){

        // 此通知，为发布服务，联系订单等一个总体的消息通知
        /**
         * 处理方式，采用在添加的时候，自动往mysql写入今天的最新消息，然后存入redis，并进行3小时刷新一次的操作
         */
        //TODO 根据经纬度查询对应的农场，服务商的列表,  >>>> notifySize


        return ResultUtils.successV2();
    }
}
