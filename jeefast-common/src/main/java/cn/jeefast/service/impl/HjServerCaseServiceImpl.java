package cn.jeefast.service.impl;

import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.common.utils.KeyGeneratorUtils;
import cn.jeefast.dao.HjServerCaseRemarkDao;
import cn.jeefast.dao.HjServerInfoDao;
import cn.jeefast.entity.HjServerCase;
import cn.jeefast.dao.HjServerCaseDao;
import cn.jeefast.entity.HjServerCaseRemark;
import cn.jeefast.entity.HjServerInfo;
import cn.jeefast.service.HjServerCaseService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务商案例 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-20
 */
@Service
public class HjServerCaseServiceImpl extends ServiceImpl<HjServerCaseDao, HjServerCase> implements HjServerCaseService {

    @Resource
    private HjServerCaseDao hjServerCaseDao;

    @Resource
    private HjServerCaseRemarkDao hjServerCaseRemarkDao;

    @Resource
    private HjServerInfoDao hjServerInfoDao;

    @Override
    public Long userCase(HjServerCase hjServerCase, List<HjServerCaseRemark> list, Long userId) {
        HjServerInfo info = new HjServerInfo();info.setUserId(userId);
        info = hjServerInfoDao.selectOne(info);
        if(info == null){
            throw new BusinessException("您还未进行认证", ResultEnum.SER_NOT_AUTH_EXP.getCode());
        }
        if(hjServerCase.getCaseId() != null){
            Map<String,Object> map = new HashMap<>();map.put("case_id",info.getServerId());
            hjServerCaseRemarkDao.deleteByMap(map);

            if(list != null && list.size() > 0){
                for(HjServerCaseRemark re : list){
                    if(StringUtils.isBlank(hjServerCase.getCaseImage())){
                        hjServerCase.setCaseImage(re.getCaseImage());
                    }
                    re.setCaseId(info.getServerId());
                    re.setCreateTime(new Date());
                    hjServerCaseRemarkDao.insert(re);
                }
            }
            hjServerCase.setUpdateTime(new Date());
            //进行修改 。。。。。。 hjServerCaseRemarkDao.up();
            EntityWrapper entityWrapper = new EntityWrapper();
            entityWrapper.where("case_id={0}",hjServerCase.getCaseId());
            hjServerCaseDao.update(hjServerCase,entityWrapper);
        }else{
            hjServerCase.setCaseId(KeyGeneratorUtils.getLongValue());
            hjServerCase.setCreateTime(new Date());
            hjServerCase.setServerId(info.getServerId());
            if(list != null && list.size() > 0){
                for(HjServerCaseRemark re : list){
                    re.setCaseId(hjServerCase.getCaseId());
                    re.setCreateTime(new Date());
                    hjServerCaseRemarkDao.insert(re);
                }
            }
            hjServerCaseDao.insert(hjServerCase);

        }
        return hjServerCase.getCaseId();

    }

    @Override
    public List<HjServerCaseRemark> findCaseDetail(Long serverId) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.where("case_id={0}",serverId);
        return hjServerCaseRemarkDao.selectList(entityWrapper);
    }
}
