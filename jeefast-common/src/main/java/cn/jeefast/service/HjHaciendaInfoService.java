package cn.jeefast.service;

import cn.jeefast.entity.HjHaciendaInfo;
import cn.jeefast.entity.HjHaciendaRemark;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 农场主 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-22
 */
public interface HjHaciendaInfoService extends IService<HjHaciendaInfo> {

    Long landApprove(HjHaciendaInfo info, List<HjHaciendaRemark> list, Long userId);

    List<Map<String,Object>> findLand(Long areaId, Double lat, Double lng, int homeSize);

    List<Map<String,Object>> findLandMore(Double lng, Double lat, Long areaId, Integer authType, Integer userType, Integer pageIndex, Integer pageSize, String categoryCode);

    HjHaciendaInfo findLandDetail(Long haciendaId);

    List<HjHaciendaInfo> findLandByUserId(Long userId);
}
