package cn.jeefast.service.impl;

import cn.jeefast.common.annotation.Log;
import cn.jeefast.common.utils.JsonUtils;
import cn.jeefast.entity.HjArea;
import cn.jeefast.entity.HjInvitation;
import cn.jeefast.service.*;
import cn.jeefast.vo.AreaVo;
import cn.jeefast.vo.CategoryCode;
import cn.jeefast.vo.RecordRps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl implements HomeService {

    @Resource
    private HjAdService hjAdService;

    @Resource
    private HjOrderService hjOrderService;

    @Resource
    private HjHaciendaInfoService hjHaciendaInfoService;

    @Resource
    private HjAreaService hjAreaService;

    @Resource
    private HjServerInfoService hjServerInfoService;

    @Resource
    private HjServerCodeService hjServerCodeService;

    @Resource
    private HjArticleService hjArticleService;

    @Resource
    private HjInvitationService hjInvitationService;

    /**
     * 查询对应的文章主题相关消息
     * @return
     */
    @Override
    public Map<String, Object> articleHomeIndex() {
        Map<String,Object> map = new HashMap<>();

        //ad
        List<Map<String,Object>> adList = hjAdService.findAdBySite(2L);
        map.put("adList",adList);

        //类别
        List<CategoryCode> l = hjServerCodeService.findByParenId(15L);
        map.put("cList",l);


        //文章
        List<Map<String,Object>> ts = hjArticleService.findAdArticle(0,3);
        map.put("aList",ts);
        //帖子
        List<HjInvitation> noteList = hjInvitationService.findAdNote(0,3);

        map.put("noteList",noteList);
        return map;
    }

    /**
     * 查询对应的主页相关信息
     * @param areaCode
     * @return
     */
    public Map<String,Object> homeIndex(String areaCode){
        Map<String,Object> map = new HashMap<>();
        //area
        List<AreaVo> areas =  hjAreaService.findAreaByParentId(0L);
        map.put("areas",areas);
        map.put("areaCode",areaCode);

        //ad
        List<Map<String,Object>> adList = hjAdService.findAdBySite(1L);
        map.put("adList",adList);

        //通知
        List<RecordRps> recordRps =  hjOrderService.orderListByTen(10);
        map.put("records",recordRps);

        //订单量统计
        Map<String,Object> order = new HashMap<>();
        order.put("orderNum",2000);
        order.put("landNum",5000);
        order.put("serverNum",90000);
        map.put("counts",order);

        double lng = 106.551643D;
        double lat = 29.562849D;
        Long areaId = 2236L;
        HjArea area = hjAreaService.findByCode(areaCode);
        if(area != null){
            areaId = area.getAreaId();
            lng = Double.valueOf(area.getCenter().split(",")[0]);
            lat = Double.valueOf(area.getCenter().split(",")[1]);
        }

        List<Map<String,Object>> landList = hjHaciendaInfoService.findLand(areaId,lng,lat,3);
        map.put("landList",landList);

        List<Map<String,Object>> serverList = hjServerInfoService.findServerAndLand(areaId,lat,lng,3);
        map.put("serverList",serverList);
        return map;
    }


    /**
     * 查询对应的主页相关信息
     * @param area
     * @return
     */
    public Map<String,Object> homeIndexOther(HjArea area){
        Map<String,Object> map = new HashMap<>();
        double lng = 106.551643D;
        double lat = 29.562849D;
        Long areaId = 2236L;
        if(area != null){
            areaId = area.getAreaId();
            lng = Double.valueOf(area.getCenter().split(",")[0]);
            lat = Double.valueOf(area.getCenter().split(",")[1]);
        }

        List<Map<String,Object>> landList = hjHaciendaInfoService.findLand(areaId,lng,lat,3);
        map.put("landList",landList);

        List<Map<String,Object>> serverList = hjServerInfoService.findServerAndLand(areaId,lat,lng,3);
        map.put("serverList",serverList);
        return map;
    }


}
