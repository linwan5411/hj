package cn.jeefast.service;

import cn.jeefast.entity.HjServerInfo;
import cn.jeefast.entity.HjServerRemak;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务商详请 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-20
 */
public interface HjServerInfoService extends IService<HjServerInfo> {

    /**
     * 服务商的注册
     * @param info
     * @param list
     * @param userId
     */
    Long userApprove(HjServerInfo info,List<HjServerRemak> list,Long userId);

    /**
     * 根据条件查询对应的列表
     * @param lng
     * @param lat
     * @param areaId
     * @param authType
     * @param userType
     * @param pageIndex
     * @param pageSize
     * @param categoryCode
     * @return
     */
    List<Map<String,Object>> findServerMore(Double lng, Double lat, Long areaId,
                                            Integer authType, Integer userType,
                                            Integer pageIndex, Integer pageSize, String categoryCode);

    List<Map<String,Object>> findServerAndLand(Long areaId, Double lat, Double lng,int size);


    HjServerInfo findServerDetail(Long serverId);
}
