package cn.jeefast.service;

import cn.jeefast.entity.HjArea;

import java.util.Map;

public interface HomeService {
    /**
     * 首页的查询
     * @param areaCode
     * @return
     */
    Map<String,Object> homeIndex(String areaCode);

    Map<String,Object> homeIndexOther(HjArea area);

    /**
     * 文章主题
     * @return
     */
    Map<String,Object> articleHomeIndex();

}
