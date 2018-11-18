package cn.jeefast.service.impl;

import cn.jeefast.entity.HjArticle;
import cn.jeefast.dao.HjArticleDao;
import cn.jeefast.service.HjArticleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
@Service
public class HjArticleServiceImpl extends ServiceImpl<HjArticleDao, HjArticle> implements HjArticleService {

    @Resource
    private HjArticleDao hjArticleDao;

    @Override
    public List<Map<String, Object>> findNewArticle(Integer page,Integer pageSize,String categoryCode) {
        return hjArticleDao.findNewArticle(categoryCode,page,pageSize);
    }

    /**
     *  return articleType=1:表示文章推荐，articleType=2：表示提问的推荐
     * @param categoryCode
     * @param currentArticleId
     * @param readOk
     * @param pageSize
     * @return
     */
    @Override
    public List<Map<String, Object>> findYourLike(String categoryCode,Long currentArticleId,Integer readOk,Integer pageSize) {
        List<Map<String, Object>> map =  hjArticleDao.findYourLike(categoryCode,currentArticleId,readOk,pageSize);
        if(map == null){
            map =  hjArticleDao.findYourLike(categoryCode,currentArticleId,readOk,1);
        }
        return map;
    }
}
