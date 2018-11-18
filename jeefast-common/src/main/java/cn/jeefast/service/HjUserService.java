package cn.jeefast.service;

import cn.jeefast.entity.HjUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户管理表 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
public interface HjUserService extends IService<HjUser> {

    void createNewUser(String mobile,String pwd,Integer userType);

    void updatePassWord(String mobile,String pwd);

    void updateLoginTime(String mobile);
}
