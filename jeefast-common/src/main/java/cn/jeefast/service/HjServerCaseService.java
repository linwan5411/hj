package cn.jeefast.service;

import cn.jeefast.entity.HjServerCase;
import cn.jeefast.entity.HjServerCaseRemark;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 服务商案例 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-20
 */
public interface HjServerCaseService extends IService<HjServerCase> {

    Long userCase(HjServerCase hjServerCase, List<HjServerCaseRemark> list, Long userId);

    List<HjServerCaseRemark> findCaseDetail(Long serverId);

    HjServerCase findCase(Long caseId);

    List<HjServerCase> myCaseList(Long serverId);
}
