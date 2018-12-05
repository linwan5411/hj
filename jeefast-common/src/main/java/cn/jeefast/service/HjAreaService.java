package cn.jeefast.service;

import cn.jeefast.entity.HjArea;
import cn.jeefast.vo.AreaLntGntVo;
import cn.jeefast.vo.AreaVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 地区码表 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-17
 */
public interface HjAreaService extends IService<HjArea> {

    /**
     * 查询地址
     * @param areaId
     * @return
     */
    List<AreaVo> findAreaByParentId(Long areaId);

    /**
     * 查询地址
     * @param areaId
     * @return
     */
    HjArea findByAreaId(Long areaId);

    HjArea findByCode(String areaCode);

    AreaLntGntVo findByCodeVo(String areaCode);

    List<Map<String,Object>> findAllArea();
}
