package cn.jeefast.rest.controller;

import cn.jeefast.common.utils.BaseResponse;
import cn.jeefast.common.utils.ResultUtils;
import cn.jeefast.rest.entity.vo.AdSiteVo;
import cn.jeefast.rest.entity.vo.AddrReqVo;
import cn.jeefast.rest.entity.vo.CategoryVo;
import cn.jeefast.service.HjAdService;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.service.HjServerCodeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 广告，类别相关的接口
 */
@RestController
@RequestMapping("/api/v1")
public class ApiAdController {

    @Resource
    private HjServerCodeService hjServerCodeService;

    @Resource
    private HjAdService hjAdService;

    @Resource
    private HjAreaService hjAreaService;

    @ApiOperation(value = "查询广告")
    @PostMapping("/findAdByType")
    public BaseResponse findAdBySite(@Valid @RequestBody AdSiteVo adSiteVo){
        return ResultUtils.successV2(hjAdService.findAdBySite(Long.valueOf(adSiteVo.getType())));
    }

    @ApiOperation(value = "查询对应的类别")
    @PostMapping("/findCategoryByType")
    public BaseResponse serverType(@Valid @RequestBody CategoryVo categoryVo){
        return ResultUtils.successV2(hjServerCodeService.findByParenId(Long.valueOf(categoryVo.getType())));
    }

    @ApiOperation(value = "查询地址三级联动")
    @PostMapping("/findArea")
    public BaseResponse findArea(@RequestBody  AddrReqVo addrReqVo){
        return ResultUtils.successV2(hjAreaService.findAreaByParentId(addrReqVo.getAreaId()));
    }

    @ApiOperation(value = "查询所有的地址列表")
    @PostMapping("/findAllArea")
    public BaseResponse findAllArea(){
        return ResultUtils.successV2(hjAreaService.findAllArea());
    }
}
