package cn.jeefast.dao;

import cn.jeefast.entity.HjMsgRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  * 短信记录表 Mapper 接口
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
public interface HjMsgRecordDao extends BaseMapper<HjMsgRecord> {
    /**
     * 获取当天短信次数
     * @param mobile
     * @param msgType
     * @return
     */
    int isMsgTimesOver(@Param("mobile")String mobile, @Param("msgType")String msgType);

    /**
     * 最新的验证码
     * @param mobile
     * @param msgType
     * @param maxMinute 限制时间
     * @return
     */
    String selectNewCode(@Param("mobile")String mobile,@Param("msgType")String msgType,@Param("maxMinute") Integer maxMinute);

}