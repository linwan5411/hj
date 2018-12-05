package cn.jeefast.service.impl;

import cn.jeefast.config.redis.Cacheable;
import cn.jeefast.entity.HjArea;
import cn.jeefast.dao.HjAreaDao;
import cn.jeefast.service.HjAreaService;
import cn.jeefast.vo.AreaLntGntVo;
import cn.jeefast.vo.AreaVo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 地区码表 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-17
 */
@Service
public class HjAreaServiceImpl extends ServiceImpl<HjAreaDao, HjArea> implements HjAreaService {

    @Resource
    private  HjAreaDao hjAreaDao;


    @Cacheable(key = "'AreaPid'+#areaId")
    @Override
    public List<AreaVo> findAreaByParentId(Long areaId) {
        if(areaId == null || areaId <= 0){
            areaId = -1L;
        }
        return hjAreaDao.findAreaByParentId(areaId);
    }

    @Cacheable(key = "'AreaId'+#areaId")
    @Override
    public HjArea findByAreaId(Long areaId) {
        HjArea a = new HjArea();a.setAreaId(areaId);
        return hjAreaDao.selectOne(a);
    }

    @Cacheable(key = "'AreaCode'+#areaCode")
    @Override
    public HjArea findByCode(String areaCode) {
        HjArea a = new HjArea();a.setAreaCode(areaCode);
        return hjAreaDao.selectOne(a);
    }

    @Override
    public AreaLntGntVo findByCodeVo(String areaCode) {
        double lng = 106.551643D;
        double lat = 29.562849D;
        Long areaId = 2236L;
        HjArea area = findByCode(areaCode);
        if(area != null){
            areaId = area.getAreaId();
            lng = Double.valueOf(area.getCenter().split(",")[0]);
            lat = Double.valueOf(area.getCenter().split(",")[1]);
        }
        AreaLntGntVo vo = new AreaLntGntVo();
        vo.setAreaId(areaId);
        vo.setLat(lat);
        vo.setLng(lng);
        return vo;
    }
}
