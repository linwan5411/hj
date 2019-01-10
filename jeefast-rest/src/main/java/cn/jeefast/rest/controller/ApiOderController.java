package cn.jeefast.rest.controller;

import cn.jeefast.common.enums.MessageTypeEnum;
import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.common.utils.BaseResponse;
import cn.jeefast.common.utils.DateTimeKit;
import cn.jeefast.common.utils.ResultUtils;
import cn.jeefast.common.utils.TokenUtil;
import cn.jeefast.entity.HjUser;
import cn.jeefast.rest.entity.TokenPageVo;
import cn.jeefast.rest.entity.vo.CollectVo;
import cn.jeefast.rest.entity.vo.OrderVo;
import cn.jeefast.rest.entity.vo.TokenVo;
import cn.jeefast.service.HjMsgRecordService;
import cn.jeefast.service.HjOrderService;
import cn.jeefast.service.HjUserCollectService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 我的个人中心
 */
@RestController
@RequestMapping("/api/v1")
public class ApiOderController {

    @Resource
    private HjOrderService hjOrderService;

    @Resource
    private HjMsgRecordService hjMsgRecordService;

    @ApiOperation(value = "添加联系单")
    @PostMapping("/order")
    public BaseResponse createOrder(@Valid @RequestBody OrderVo orderVo){
        if(orderVo.getUserType() == null || orderVo.getUserType() > 2 || orderVo.getUserType() <= 0){
            throw new BusinessException("联系对象不明确",ResultEnum.ORDER_EXP.getCode());
        }
        hjMsgRecordService.validateMsgCode(orderVo.getCode(),orderVo.getUserMobile(), MessageTypeEnum.ORDER.getType(),MessageTypeEnum.ORDER.getMaxTime());
        HjUser userId = TokenUtil.parseUser(orderVo.getToken());
        hjOrderService.createOrder(userId,orderVo.getUserType(),orderVo.getObjectId(),orderVo.getUserMobile(),orderVo.getUserName());
        return ResultUtils.successV2();
    }

    @ApiOperation(value = "查询最近10条联系记录")
    @PostMapping("/orderList")
    public BaseResponse orderList(){
        return ResultUtils.successV2(hjOrderService.orderListByTen(10));
    }

    @ApiOperation(value = "农场主被联系的最近10条")
    @PostMapping("/orderLandList/{landId}")
    public BaseResponse orderLandList(@PathVariable("landId")Long landId){
        String beforeTime = DateTimeKit.format(DateTimeKit.lastMouth(),DateTimeKit.NORM_DATE_PATTERN);
        return ResultUtils.successV2(hjOrderService.orderListByTen(10,beforeTime,1,landId));
    }
}
