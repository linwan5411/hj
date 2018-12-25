package cn.jeefast.service.impl;

import cn.jeefast.entity.HjMessage;
import cn.jeefast.dao.HjMessageDao;
import cn.jeefast.service.HjMessageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户的消息通知 服务实现类
 * </p>
 *
 * @author zhihang
 * @since 2018-12-25
 */
@Service
public class HjMessageServiceImpl extends ServiceImpl<HjMessageDao, HjMessage> implements HjMessageService {

    @Resource
    private HjMessageDao hjMessageDao;

    @Override
    public List<Map<String, Object>> findMyMessage(Long userId, Integer pageIndex, Integer pageSize) {
        return hjMessageDao.findMyMessage(userId,pageIndex,pageSize);
    }

    @Override
    public void sendMessage(HjMessage hjMessage) {
        try {
            hjMessageDao.insert(hjMessage);
        }catch (Exception e){

        }
    }

    @Override
    public void viewMessage(Long userId,Long messageId) {
        hjMessageDao.viewMessage(userId,messageId);
    }
}
