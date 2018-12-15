package cn.jeefast.service.impl;

import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.common.message.AliyunMessageApi;
import cn.jeefast.common.utils.RandomUtils;
import cn.jeefast.config.RedisUtils;
import cn.jeefast.entity.HjMsgRecord;
import cn.jeefast.dao.HjMsgRecordDao;
import cn.jeefast.service.HjMsgRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 短信记录表 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
@Service
public class HjMsgRecordServiceImpl extends ServiceImpl<HjMsgRecordDao, HjMsgRecord> implements HjMsgRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HjMsgRecordServiceImpl.class);

    /**
     * 短信开关
     */
    @Value("${bu.message.switch:false}")
    private boolean buMessageSwitch;

    /**
     * 不同类型的短信短信次数
     */
    @Value("${bu.message.msgTimes:2}")
    private int msgTimes;

    @Resource
    private HjMsgRecordDao hjMsgRecordDao;

    /**
     * 发送短信
     * @param mobile
     */
    @Override
    public String sendMessage(String mobile, String msgType) {
        String msgNum = RandomUtils.randomNumber(6);
        try {
            if (buMessageSwitch){
                AliyunMessageApi.sendSms(mobile,msgNum);
            }
            saveMessage(mobile,msgType,msgNum);
        }catch (Exception e){
            LOGGER.error("短信发送失败:{}",e);
            throw new BusinessException("短信发送失败",ResultEnum.MESSAGE_VALIDATE_EXP.getCode());
        }
        return msgNum;
    }


    /**
     * 保存短信
     * @param mobile
     * @param msgType
     * @param msgNum
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveMessage(String mobile,String msgType,String msgNum){
        HjMsgRecord systemMsgRecord = new HjMsgRecord();
        systemMsgRecord.setMobile(mobile);
        systemMsgRecord.setMsgType(Integer.parseInt(msgType));
        systemMsgRecord.setMsgNum(msgNum);
        systemMsgRecord.setCreateTime(new Date());
        int count = hjMsgRecordDao.insert(systemMsgRecord);
        if (count<1){
            LOGGER.error("保存短息失败");
        }
    }

    /**
     * 验证当天短信次数是否超过上限
     * @param mobile
     * @param msgType
     * @return
     */
    @Override
    public boolean isMsgTimesOver(String mobile, String msgType){
        int times  = hjMsgRecordDao.isMsgTimesOver(mobile, msgType);
        if(times >= msgTimes){
            throw new BusinessException(ResultEnum.MESSAGE_MAX_EXP.getMessage() , ResultEnum.MESSAGE_MAX_EXP.getCode());
        }
        return true;
    }

    /**
     * 查询最新的验证码,不含检查分值
     * @param mobile
     * @param msgType
     * @return
     */
    @Override
    public String selectNewCode(String mobile, String msgType){
        return hjMsgRecordDao.selectNewCode(mobile, msgType,null);
    }

    /**
     * 查询最新的验证码,不含检查分值
     * @param mobile
     * @param msgType
     * @param maxMinute 多少分钟之类
     * @return
     */
    @Override
    public String selectNewCode(String mobile, String msgType, Integer maxMinute){
        return hjMsgRecordDao.selectNewCode(mobile, msgType,maxMinute);
    }

    /**
     * 验证短信接口
     * @param code
     * @param mobile
     * @param msgType
     * @param maxMinute
     * @return
     */
    @Override
    public boolean validateMsgCode(String code, String mobile, String msgType, Integer maxMinute){
        String msg;
        if (maxMinute==null){
            msg = selectNewCode(mobile,msgType);
        }else {
            msg = selectNewCode(mobile,msgType,maxMinute);
        }
        if(StringUtils.isBlank(msg)){
            throw new BusinessException(ResultEnum.MESSAGE_MOBILE_TIMEOUT_EXP.getMessage(),ResultEnum.MESSAGE_MOBILE_TIMEOUT_EXP.getCode());
        }
        if(!code.equals(msg)){
            throw new BusinessException(ResultEnum.MESSAGE_VALIDATE_EXP.getMessage(),ResultEnum.MESSAGE_VALIDATE_EXP.getCode());
        }
        return true;
    }

    /**
     * 验证短信接口
     * @param code
     * @param mobile
     * @param msgType
     * @return
     */
    @Override
    public boolean validateMsgCode(String code, String mobile, String msgType){
        return validateMsgCode(code,mobile,msgType,10);
    }
	
}
