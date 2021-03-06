package cn.jeefast.service.impl;

import cn.jeefast.common.utils.KeyGeneratorUtils;
import cn.jeefast.config.redis.Cacheable;
import cn.jeefast.entity.HjFarmersInfo;
import cn.jeefast.dao.HjFarmersInfoDao;
import cn.jeefast.service.HjFarmersInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 农场主认证 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-12-13
 */
@Service
public class HjFarmersInfoServiceImpl extends ServiceImpl<HjFarmersInfoDao, HjFarmersInfo> implements HjFarmersInfoService {

    @Resource
    private HjFarmersInfoDao hjFarmersInfoDao;

    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    @Override
    public Long farmersAuth(HjFarmersInfo framerAuthVo) {
        if(framerAuthVo.getFarmersId() != null){
            framerAuthVo.setUpdateTime(new Date());
            hjFarmersInfoDao.updateByHjFarmersInfo(framerAuthVo);
            return framerAuthVo.getFarmersId();
        }
        framerAuthVo.setCreateTime(new Date());
        framerAuthVo.setFarmersId(KeyGeneratorUtils.getLongValue());
        hjFarmersInfoDao.insert(framerAuthVo);
        return framerAuthVo.getFarmersId();
    }

    @Cacheable(key = "wh_farmersInfo",fieldKey = "#userId")
    @Override
    public HjFarmersInfo farmersInfo(Long userId) {
        HjFarmersInfo hjFarmersInfo = new HjFarmersInfo();hjFarmersInfo.setUserId(userId);
        return hjFarmersInfoDao.selectOne(hjFarmersInfo);
    }

    @Cacheable(key = "wh_farmersInfo_id",fieldKey = "#framerId")
    @Override
    public HjFarmersInfo farmersInfoById(Long framerId) {
        HjFarmersInfo hjFarmersInfo = new HjFarmersInfo();hjFarmersInfo.setFarmersId(framerId);
        return hjFarmersInfoDao.selectOne(hjFarmersInfo);
    }
}
