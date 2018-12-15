package cn.jeefast.service;

import cn.jeefast.entity.HjFarmersInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 农场主认证 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-12-13
 */
public interface HjFarmersInfoService extends IService<HjFarmersInfo> {

    Long farmersAuth(HjFarmersInfo framerAuthVo);

    HjFarmersInfo farmersInfo(Long userId);
}
