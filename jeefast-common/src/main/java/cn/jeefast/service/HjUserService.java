package cn.jeefast.service;

import cn.jeefast.entity.HjUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户管理表 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
public interface HjUserService extends IService<HjUser> {

    HjUser createNewUser(String mobile,String pwd,Integer userType);

    void updatePassWord(String mobile,String pwd);

    void updateLoginTime(String mobile);

    Map<String,Object> login(String mobile, String pass);

    /**
     * 注册
     * @param mobile
     * @param pass
     * @return
     */
    Map<String,Object> enroll(String mobile, String pass);

    Map<String,Object> myZoneData(Long userId);

    Map<String,Object> findUserInfo(Long userId);
}
