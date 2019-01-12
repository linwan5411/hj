package cn.jeefast.service.impl;

import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.common.utils.KeyGeneratorUtils;
import cn.jeefast.config.redis.Cacheable;
import cn.jeefast.dao.HjHaciendaInfoDao;
import cn.jeefast.dao.HjServerInfoDao;
import cn.jeefast.dao.HjUserDao;
import cn.jeefast.entity.*;
import cn.jeefast.dao.HjOrderDao;
import cn.jeefast.service.HjMessageService;
import cn.jeefast.service.HjOrderService;
import cn.jeefast.vo.RecordRps;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.catalina.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 联系表 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-24
 */
@Service
public class HjOrderServiceImpl extends ServiceImpl<HjOrderDao, HjOrder> implements HjOrderService {

    @Resource
    private HjOrderDao hjOrderDao;

    @Resource
    private HjServerInfoDao serverInfoDao;

    @Resource
    private HjHaciendaInfoDao hjHaciendaInfoDao;

    @Resource
    private HjMessageService hjMessageService;

    @Transactional(rollbackFor = {Exception.class,RuntimeException.class})
    @Override
    public void createOrder(HjUser user, Integer userType, Long objectId, String userMobile, String userName) {
        if(StringUtils.isBlank(user.getUserName())){
            user.setUserName(userName);
        }
        HjOrder t = new HjOrder();
        t.setFromUserId(user.getUserId());
        t.setFromUserMobile(userMobile);
        t.setFromUserName(userName);
        t.setFromUserType(user.getUserType());
        t.setCreateTime(new Date());
        Long megId = null;
        if(userType == 1){
            HjHaciendaInfo info = new HjHaciendaInfo();
            info.setHaciendaId(objectId);
            info = hjHaciendaInfoDao.selectOne(info);
            if(info == null){
                throw new BusinessException("联系农场/服务商不存在", ResultEnum.ORDER_USER_EXP.getCode());
            }
            megId = info.getUserId();
            t.setToUserId(info.getHaciendaId());
            t.setToUserType(1);
            t.setToUserName(info.getHaciendaName());
        }else if(userType == 2){
            HjServerInfo info = new HjServerInfo();
            info.setServerId(objectId);
            info = serverInfoDao.selectOne(info);
            if(info == null){
                throw new BusinessException("联系农场/服务商不存在", ResultEnum.ORDER_USER_EXP.getCode());
            }
            megId = info.getUserId();
            t.setToUserId(info.getServerId());
            t.setToUserType(1);
            t.setToUserName(info.getCompanyName());
        }

        HjMessage hj = new HjMessage();
        hj.setUserId(megId);
        hj.setReadCount(0);
        hj.setCreateTime(new Date());
        hj.setId(KeyGeneratorUtils.getLongValue());
        hj.setMessageTpye(2);
        hj.setMesageTitle(userName+":对您提供的服务非常感兴趣!");
        StringBuilder builder = new StringBuilder(StringUtils.isBlank(userName) ? "新农人" : userName).append(",联系电话:").append(user.getUserMobile()).append("对您提供的服务非常感兴趣");
        hj.setMessageInfo(builder.toString());
        hjMessageService.sendMessage(hj);

        hjOrderDao.insert(t);
    }

    @Cacheable(key = "homeNotify",expireTime = 3600L)
    @Override
    public List<RecordRps> orderListByTen(Integer size) {
        String beforeTime = null;
        Integer type = null;
        Long objectId = null;
        List<RecordRps> l =  hjOrderDao.orderListByTen(size,beforeTime,type,objectId);
        if(l != null && l.size() > 0){
            return l;
        }
        return null;
    }

    @Override
    public List<RecordRps> orderListByTen(Integer size, String beforeTime, Integer type, Long objectId) {
        return hjOrderDao.orderListByTen(size,beforeTime,type,objectId);
    }
}
