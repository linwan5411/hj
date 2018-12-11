package cn.jeefast.service;

import cn.jeefast.entity.HjUserCollect;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户的收藏表 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-24
 */
public interface HjUserCollectService extends IService<HjUserCollect> {

    Object myCollect(Integer type, Long userId, Integer pageIndex, Integer pageSize,Double lat, Double lng);

    void addCollect(Long userId, Integer collectType, Long objectId);

    void deleteCollect(Long userId, Long collectId);

    void cleanCollect(Long userId);
}
