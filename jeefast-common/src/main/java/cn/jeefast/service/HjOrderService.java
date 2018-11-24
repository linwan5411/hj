package cn.jeefast.service;

import cn.jeefast.entity.HjOrder;
import cn.jeefast.entity.HjUser;
import cn.jeefast.vo.RecordRps;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 联系表 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-24
 */
public interface HjOrderService extends IService<HjOrder> {

    void createOrder(HjUser user, Integer userType, Long objectId, String userMobile, String userName);

    List<RecordRps> orderListByTen(Integer size);

    List<RecordRps> orderListByTen(Integer size, String beforeTime, Integer type, Long objectId);
}
