package cn.jeefast.service;

import cn.jeefast.entity.HjMessage;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户的消息通知 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-12-25
 */
public interface HjMessageService extends IService<HjMessage> {

    List<Map<String,Object>> findMyMessage(Long userId, Integer pageIndex, Integer pageSize);

    /**
     * 发送消息
     * @param hjMessage
     */
    void sendMessage(HjMessage hjMessage);

    void viewMessage(Long userId,Long messageId);
}
