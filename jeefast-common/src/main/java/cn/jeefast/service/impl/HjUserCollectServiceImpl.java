package cn.jeefast.service.impl;

import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.entity.HjUserCollect;
import cn.jeefast.dao.HjUserCollectDao;
import cn.jeefast.service.HjUserCollectService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户的收藏表 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-24
 */
@Service
public class HjUserCollectServiceImpl extends ServiceImpl<HjUserCollectDao, HjUserCollect> implements HjUserCollectService {

    @Resource
    private HjUserCollectDao hjUserCollectDao;

    @Override
    public Object myCollect(Integer type, Long userId, Integer pageIndex, Integer pageSize,Double lat, Double lng) {
        if(type == 1){
            List<Map<String,Object>> list = hjUserCollectDao.findServer(userId,lat,lng,pageIndex,pageSize);
            return list;
        }else if(type == 2){
            List<Map<String,Object>> list = hjUserCollectDao.findLand(userId,lat,lng,pageIndex,pageSize);
            return list;
        }
        return null;
    }

    @Override
    public void addCollect(Long userId, Integer collectType, Long objectId) {
        try {
            HjUserCollect h = new HjUserCollect();
            h.setUserId(userId);
            h.setCollectType(collectType);
            h.setObjectId(objectId);
            h.setCreateTime(new Date());
            hjUserCollectDao.insert(h);
        }catch (Exception e){
            throw new BusinessException("您已收藏", ResultEnum.COLLECT_EXIST_EXP.getCode());
        }
    }

    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    @Override
    public void deleteCollect(Long userId, Long collectId) {
        Map<String,Object> obj = new HashMap<>();
        obj.put("user_id",userId);
        obj.put("collect_id",collectId);
        hjUserCollectDao.deleteByMap(obj);
    }

    @Override
    public void cleanCollect(Long userId) {
        Map<String,Object> obj = new HashMap<>();
        obj.put("user_id",userId);
        hjUserCollectDao.deleteByMap(obj);
    }
}
