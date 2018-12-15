package cn.jeefast.service.impl;

import cn.jeefast.common.enums.CommonEnum;
import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.common.utils.*;
import cn.jeefast.config.redis.Cacheable;
import cn.jeefast.dao.HjFarmersInfoDao;
import cn.jeefast.dao.HjServerInfoDao;
import cn.jeefast.entity.HjFarmersInfo;
import cn.jeefast.entity.HjHaciendaInfo;
import cn.jeefast.entity.HjServerInfo;
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
import java.util.HashMap;
import java.util.Map;

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

    @Resource
    private HjFarmersInfoDao hjFarmersInfoDao;

    @Resource
    private HjServerInfoDao hjServerInfoDao;

    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    @Override
    public HjUser createNewUser(String mobile, String pwd, Integer userType) {
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
        user.setUserName("新农人"+ RandomUtils.randomNumber(4));
        user.setAuthType(0);
        user.setUserType(0);
        user.setCreateTime(new Date());
        Integer row = hjUserDao.insert(user);
        if(row <= 0){
            throw new BusinessException("注册失败", ResultEnum.MOBILE_REG_EXP.getCode());
        }
        return user;
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

    @Override
    public Map<String, Object> login(String mobile, String pass) {
        HjUser user = new HjUser();user.setUserMobile(mobile);
        user = hjUserDao.selectOne(user);
        if(user == null){
            throw new BusinessException("未注册", ResultEnum.MOBILE_NOT_EXIST.getCode());
        }

        if(!PwdUtils.verfyPwd(pass,user.getLoginSalt(),user.getLoginPwd())){
            throw new BusinessException("密码不正确", ResultEnum.MOBILE_PASS_EXP.getCode());
        }

        if(user.getDataStatus() != null && user.getDataStatus().intValue() != CommonEnum.ONE.getInt_state()){
            throw new BusinessException("已被冻结", ResultEnum.MOBILE_BLOCK_EXP.getCode());
        }
        updateLoginTime(mobile);
        Map<String,Object> map = TokenUtil.loginRps(user);
        map.put("userName",user.getUserName());
        map.put("userMobile",user.getUserMobile());
        map.put("hideUserMobile",MobileUtils.subMobile(user.getUserMobile()));
        map.put("userPortrait",user.getUserPortrait());
        map.put("userType",user.getUserType());
        map.put("authType",user.getAuthType());
        return map;
    }

    @Override
    public Map<String, Object> enroll(String mobile, String pass) {
        HjUser user = createNewUser(mobile,pass,CommonEnum.ZERO.getInt_state());
        Map<String,Object> map = TokenUtil.loginRps(user);
        map.put("userName",user.getUserName());
        map.put("userMobile",user.getUserMobile());
        map.put("hideUserMobile",MobileUtils.subMobile(user.getUserMobile()));
        map.put("userPortrait",user.getUserPortrait());
        map.put("userType",user.getUserType());
        map.put("authType",user.getAuthType());
        return map;
    }

    @Cacheable(key = "wh_myZoneData",fieldKey = "#userId",expireTime = 86000)
    @Override
    public Map<String, Object> myZoneData(Long userId) {
        Map<String,Object> map = new HashMap<>();
        HjUser user = new HjUser();user.setUserId(userId);
        user = hjUserDao.selectOne(user);
        if(user != null){
            map.put("userName",user.getUserName());
            map.put("userMobile",user.getUserMobile());
            map.put("hideUserMobile",MobileUtils.subMobile(user.getUserMobile()));
            map.put("userPortrait",user.getUserPortrait());
            map.put("userType",user.getUserType());
            map.put("authType",user.getAuthType());
        }
        //农场主ID
        HjFarmersInfo  info = new HjFarmersInfo();info.setUserId(userId);
        info = hjFarmersInfoDao.selectOne(info);
        if(info != null){
            map.put("farmersId",info.getFarmersId());
        }

        //服务商ID
        HjServerInfo serverInfo = new HjServerInfo();serverInfo.setUserId(userId);
        serverInfo = hjServerInfoDao.selectOne(serverInfo);
        if(serverInfo != null){
            map.put("serverId",serverInfo.getServerId());
        }
        return map;
    }
    @Cacheable(key = "wh_findUserInfo",fieldKey = "#userId",expireTime = 86000)
    @Override
    public Map<String, Object> findUserInfo(Long userId) {
        Map<String,Object> map = new HashMap<>();
        HjUser user = new HjUser();user.setUserId(userId);
        user = hjUserDao.selectOne(user);
        if(user != null){
            map.put("userName",user.getUserName());
            map.put("userMobile",user.getUserMobile());
            map.put("hideUserMobile",MobileUtils.subMobile(user.getUserMobile()));
            map.put("userPortrait",user.getUserPortrait());
            map.put("userType",user.getUserType());
            map.put("authType",user.getAuthType());
        }

        //农场主ID
        HjFarmersInfo  info = new HjFarmersInfo();info.setUserId(userId);
        info = hjFarmersInfoDao.selectOne(info);
        if(info != null){
            map.put("farmersId",info.getFarmersId());
            map.put("farmersName",info.getFarmersName());
        }

        //服务商ID
        HjServerInfo serverInfo = new HjServerInfo();serverInfo.setUserId(userId);
        serverInfo = hjServerInfoDao.selectOne(serverInfo);
        if(serverInfo != null){
            map.put("serverId",serverInfo.getServerId());
            map.put("companyName",serverInfo.getServerId());
            map.put("companyImage",serverInfo.getCompanyImage());
        }
        return map;
    }


}
