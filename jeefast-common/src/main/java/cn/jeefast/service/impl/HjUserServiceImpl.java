package cn.jeefast.service.impl;

import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.common.utils.*;
import cn.jeefast.entity.HjUser;
import cn.jeefast.dao.HjUserDao;
import cn.jeefast.service.HjUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 用户管理表 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
@Service
public class HjUserServiceImpl extends ServiceImpl<HjUserDao, HjUser> implements HjUserService {

    private final static Logger logger = LoggerFactory.getLogger(HjUserServiceImpl.class);

    @Resource
    private HjUserDao hjUserDao;

    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    @Override
    public void createNewUser(String mobile, String pwd, Integer userType) {
        HjUser user = new HjUser();user.setUserMobile(mobile);
        user = hjUserDao.selectOne(user);
        if(user != null){
            throw new BusinessException("手机号已注册", ResultEnum.MOBILE_EXIST.getCode());
        }
        user = new HjUser();
        user.setUserId(KeyGeneratorUtils.getLongValue(mobile));
        user.setUserMobile(mobile);
        user.setLoginSalt(RandomUtils.randomString(6));
        user.setLoginPwd(PwdUtils.createPwd(pwd,user.getLoginSalt()));
        user.setUserName("新农人"+ MobileUtils.subMobile(mobile));
        user.setCreateTime(new Date());
        Integer row = hjUserDao.insert(user);
        if(row <= 0){
            throw new BusinessException("注册失败", ResultEnum.MOBILE_REG_EXP.getCode());
        }
    }

    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    @Override
    public void updatePassWord(String mobile, String pwd) {
        HjUser user = new HjUser();user.setUserMobile(mobile);
        user = hjUserDao.selectOne(user);
        if(user == null){
            throw new BusinessException("未注册", ResultEnum.MOBILE_NOT_EXIST.getCode());
        }
        user.setLoginPwd(PwdUtils.createPwd(pwd,user.getLoginSalt()));
        Integer row = hjUserDao.updateById(user);
        if(row <= 0){
            throw new BusinessException("修改失败", ResultEnum.MOBILE_REG_EXP.getCode());
        }
    }

    @Override
    public void updateLoginTime(String mobile) {
        try {
            HjUser user = new HjUser();user.setUserMobile(mobile);
            user = hjUserDao.selectOne(user);
            if(user != null){
                user.setLastLoginTime(new Date());
                hjUserDao.updateById(user);
            }
        }catch (Exception e){
            logger.error("修改登录时间异常:{}",e);
        }
    }
}
