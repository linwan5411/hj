package cn.jeefast.rest.controller;

import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.common.utils.BaseResponse;
import cn.jeefast.common.utils.LocationUtils;
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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource
    private RedisUtils redisUtils;


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
                BeanUtils.copyProperties(e,vo);
                list.add(vo);
            });
        }
        Long serverId = null;
        try {
            serverId = hjServerInfoService.userApprove(info,list,userId);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException){
                throw new BusinessException("您已是服务商", ResultEnum.SER_AUTH_EXP.getCode());
            }else{
                throw e;
            }
        }
        serverAuthVo.setServerId(serverId);
        serverAuthVo.setRemarkList(remarkList);
        redisUtils.delete("wh_findServerDetail",serverId.toString());
        return ResultUtils.successV2(serverAuthVo);
    }

    @ApiOperation(value = "添加案例")
    @PostMapping("/userAddCase")
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
                BeanUtils.copyProperties(e,vo);
                list.add(vo);
            });
        }

        Long caseId = hjServerCaseService.userCase(hjServerCase,list,userId);
        caseVo.setCaseId(caseId);
        return ResultUtils.successV2(caseVo);
    }

    @ApiOperation(value = "经纬度查询最近3条的服务商")
    @PostMapping("/findServer")
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
    @PostMapping("/server/{serverId}")
    public BaseResponse findServerDetail(@PathVariable("serverId")Long serverId,@RequestBody CordOnlyVo cordOnlyVo){
        HjServerInfo h = hjServerInfoService.findServerDetail(serverId);
        if(h != null){
            int m = LocationUtils.getDistanceToM(cordOnlyVo.getLat(),cordOnlyVo.getLng(),h.getLatitude(),h.getLongitude());
            h.setDistnce(m+"");
        }
        return ResultUtils.successV2(h);
    }

    @ApiOperation(value = "查询案例详情")
    @PostMapping("/case/{caseId}")
    public BaseResponse findCaseDetail(@PathVariable("caseId")Long serverId){
        return ResultUtils.successV2(hjServerCaseService.findCase(serverId));
    }

    @ApiOperation(value = "我的案例列表")
    @PostMapping("/myCaseList/{serverId}")
    public BaseResponse myCaseList(@PathVariable("serverId")Long serverId){
        return ResultUtils.successV2(hjServerCaseService.myCaseList(serverId));
    }

}
