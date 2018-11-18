package cn.jeefast.service;

import cn.jeefast.entity.HjAd;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 广告 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
public interface HjAdService extends IService<HjAd> {


    List<Map<String,Object>> findAdBySite(Long siteId);
}
