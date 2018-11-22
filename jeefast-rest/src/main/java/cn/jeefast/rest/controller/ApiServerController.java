package cn.jeefast.rest.controller;

import cn.jeefast.common.utils.BaseResponse;
import cn.jeefast.common.utils.ResultUtils;
import cn.jeefast.common.utils.TokenUtil;
import cn.jeefast.config.RedisUtils;
import cn.jeefast.entity.HjServerCase;
import cn.jeefast.entity.HjServerCaseRemark;
import cn.jeefast.entity.HjServerInfo;
import cn.jeefast.entity.HjServerRemak;
import cn.jeefast.rest.entity.vo.*;
import cn.jeefast.service.HjServerCaseService;
import cn.jeefast.service.HjServerInfoService;
import com.sun.corba.se.spi.ior.ObjectKey;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 农场+服务商的查询操作接口
 */
@RestController
@RequestMapping("/api/v1")
public class ApiServerController {

    /**
     * 首页推荐查询最近的距离取3条数据
     */
    private static final int homeSize = 3;

    /**
     * 显示的通知条数
     */
    private static final int notifySize = 10;

    @Resource
    private HjServerInfoService hjServerInfoService;

    @Resource
    private HjServerCaseService hjServerCaseService;


    @ApiOperation(value = "服务商认证")
    @PostMapping("/userApprove")
    public BaseResponse userApprove(@Valid @RequestBody ServerAuthVo serverAuthVo){

        //token
        Long userId = TokenUtil.parseUserId(serverAuthVo.getToken());

        //服务
        HjServerInfo info = new HjServerInfo();
        BeanUtils.copyProperties(serverAuthVo,info);

        //案例
        List<HjServerRemak> list = new ArrayList<>();
        List<ServerRemakVo> remarkList = serverAuthVo.getRemarkList();
        if(remarkList != null && remarkList.size() > 0){
            remarkList.forEach(e ->{
                HjServerRemak vo = new HjServerRemak();
                BeanUtils.copyProperties(vo,e);
                list.add(vo);
            });
        }

        Long serverId = hjServerInfoService.userApprove(info,list,userId);
        serverAuthVo.setServerId(serverId);
        return ResultUtils.successV2(serverAuthVo);
    }

    @ApiOperation(value = "添加案例")
    @PostMapping("/userCase")
    public BaseResponse userCase(@Valid @RequestBody CaseVo caseVo){
        //token
        Long userId = TokenUtil.parseUserId(caseVo.getToken());

        HjServerCase hjServerCase = new HjServerCase();
        BeanUtils.copyProperties(caseVo,hjServerCase);


        //案例
        List<HjServerCaseRemark> list = new ArrayList<>();
        List<CaseRemakVo> remarkList = caseVo.getList();
        if(remarkList != null && remarkList.size() > 0){
            remarkList.forEach(e ->{
                HjServerCaseRemark vo = new HjServerCaseRemark();
                BeanUtils.copyProperties(vo,e);
                list.add(vo);
            });
        }

        Long caseId = hjServerCaseService.userCase(hjServerCase,list,userId);
        caseVo.setCaseId(caseId);
        return ResultUtils.successV2(caseVo);
    }

    @ApiOperation(value = "经纬度查询最近3条的服务商")
    @PostMapping("/findServerAndLand")
    public BaseResponse findServerAndLand(@RequestBody CordVo cordVo){
        List<Map<String,Object>> list = hjServerInfoService.findServerAndLand(cordVo.getAreaId(),cordVo.getLat(),cordVo.getLng(),homeSize);
        return ResultUtils.successV2(list);
    }

    @ApiOperation(value = "经纬度分页查询服务商")
    @PostMapping("/findServerMore")
    public BaseResponse findServerMore(@RequestBody CordPageVo basePage){
        List<Map<String,Object>> list = hjServerInfoService.findServerMore(basePage.getLng(),basePage.getLat(),
                basePage.getAreaId(),basePage.getAuthType(),basePage.getUserType(),basePage.getPageIndex(),
                basePage.getPageSize(),basePage.getCategoryCode());
        return ResultUtils.successV2(list);
    }

    @ApiOperation(value = "查询服务商的详情")
    @PostMapping("/userCase/{serverId}")
    public BaseResponse findServerDetail(@PathVariable("serverId")Long serverId){
        return ResultUtils.successV2(hjServerInfoService.findServerDetail(serverId));
    }

    @ApiOperation(value = "查询案例详情")
    @PostMapping("/caseInfo/{caseId}")
    public BaseResponse findCaseDetail(@PathVariable("caseId")Long serverId){
        return ResultUtils.successV2(hjServerCaseService.findCaseDetail(serverId));
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
