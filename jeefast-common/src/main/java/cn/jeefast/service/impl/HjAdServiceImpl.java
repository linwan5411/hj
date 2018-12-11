package cn.jeefast.service.impl;

import cn.jeefast.config.redis.Cacheable;
import cn.jeefast.entity.HjAd;
import cn.jeefast.dao.HjAdDao;
import cn.jeefast.service.HjAdService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 广告 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
@Service
public class HjAdServiceImpl extends ServiceImpl<HjAdDao, HjAd> implements HjAdService {

    @Resource
    private HjAdDao hjAdDao;

    @Cacheable(key = "adList",fieldKey = "#siteId")
    @Override
    public List<Map<String, Object>> findAdBySite(Long siteId) {
        return hjAdDao.findAdBySite(siteId);
    }
}
