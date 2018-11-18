package cn.jeefast.service;

import cn.jeefast.entity.HjMsgRecord;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 短信记录表 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
public interface HjMsgRecordService extends IService<HjMsgRecord> {
    /**
     * 发送短信
     * @param mobile
     */
    String sendMessage(String mobile, String msgType);

    /**
     * 验证当天短信次数是否超过上限
     * @param mobile
     * @param msgType
     * @return
     */
    boolean isMsgTimesOver(String mobile, String msgType);

    /**
     * 查询最新的验证码,不含检查分值
     * @param mobile
     * @param msgType
     * @return
     */
    String selectNewCode(String mobile, String msgType);

    /**
     * 查询最新的验证码,不含检查分值
     * @param mobile
     * @param msgType
     * @param max_minute 多少分钟之类
     * @return
     */
    String selectNewCode(String mobile, String msgType, Integer max_minute);

    /**
     * 验证短信接口
     * @param code
     * @param mobile
     * @param msgType
     * @return
     */
    boolean validateMsgCode(String code, String mobile, String msgType);

    /**
     * 验证短信接口
     * @param code
     * @param mobile
     * @param msgType
     * @param max_minute
     * @return
     */
    boolean validateMsgCode(String code, String mobile, String msgType, Integer max_minute);

}
