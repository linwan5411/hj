package cn.jeefast.service;

import cn.jeefast.entity.HjServerCode;
import cn.jeefast.vo.CategoryCode;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类别编码 服务类
 * </p>
 *
 * @author zhihang
 * @since 2018-11-18
 */
public interface HjServerCodeService extends IService<HjServerCode> {


    List<CategoryCode> findByParenId(Long parentId);

    HjServerCode findByCode(String categoryCode);
}
