package cn.jeefast.service.impl;

import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.dao.HjHaciendaInfoDao;
import cn.jeefast.dao.HjServerInfoDao;
import cn.jeefast.dao.HjUserDao;
import cn.jeefast.entity.HjHaciendaInfo;
import cn.jeefast.entity.HjOrder;
import cn.jeefast.dao.HjOrderDao;
import cn.jeefast.entity.HjServerInfo;
import cn.jeefast.entity.HjUser;
import cn.jeefast.service.HjOrderService;
import cn.jeefast.vo.RecordRps;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

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

    @Override
    public void createOrder(HjUser user, Integer userType, Long objectId, String userMobile, String userName) {
        HjOrder t = new HjOrder();
        t.setFromUserId(user.getUserId());
        t.setFromUserMobile(userMobile);
        t.setFromUserName(userName);
        t.setFromUserType(user.getUserType());
        t.setCreateTime(new Date());
        if(userType == 1){
            HjHaciendaInfo info = new HjHaciendaInfo();
            info.setHaciendaId(objectId);
            info = hjHaciendaInfoDao.selectOne(info);
            if(info == null){
                throw new BusinessException("联系农场/服务商不存在", ResultEnum.ORDER_USER_EXP.getCode());
            }
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
            t.setToUserId(info.getServerId());
            t.setToUserType(1);
            t.setToUserName(info.getCompanyName());
        }

        hjOrderDao.insert(t);
    }

    @Override
    public List<RecordRps> orderListByTen(Integer size) {
        String beforeTime = null;
        Integer type = null;
        Long objectId = null;
        return hjOrderDao.orderListByTen(size,beforeTime,type,objectId);
    }

    @Override
    public List<RecordRps> orderListByTen(Integer size, String beforeTime, Integer type, Long objectId) {
        return hjOrderDao.orderListByTen(size,beforeTime,type,objectId);
    }
}
