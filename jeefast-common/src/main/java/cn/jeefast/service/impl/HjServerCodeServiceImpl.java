package cn.jeefast.service.impl;

import cn.jeefast.config.redis.Cacheable;
import cn.jeefast.entity.HjServerCode;
import cn.jeefast.dao.HjServerCodeDao;
import cn.jeefast.service.HjServerCodeService;
import cn.jeefast.vo.AreaVo;
import cn.jeefast.vo.CategoryCode;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类别编码 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
@Service
public class HjServerCodeServiceImpl extends ServiceImpl<HjServerCodeDao, HjServerCode> implements HjServerCodeService {

    @Resource
    private HjServerCodeDao hjServerCodeDao;

    @Cacheable(key = "CodeType",fieldKey = "#parentId")
    @Override
    public List<CategoryCode> findByParenId(Long parentId) {
        return hjServerCodeDao.findByParenId(parentId);
    }

    @Cacheable(key = "CodeOne",fieldKey = "#categoryCode")
    @Override
    public HjServerCode findByCode(String categoryCode) {
        HjServerCode c = new HjServerCode();c.setCategoryCode(categoryCode);
        return hjServerCodeDao.selectOne(c);
    }

    @Cacheable(key = "serverCodeList",fieldKey = "#serverCodes")
    @Override
    public List<CategoryCode> findCodeList(String serverCodes) {
        String[] codes = serverCodes.split(",");
        if(codes !=null && codes.length > 0){
            return hjServerCodeDao.findCodeList(codes);
        }
        return null;
    }

}
