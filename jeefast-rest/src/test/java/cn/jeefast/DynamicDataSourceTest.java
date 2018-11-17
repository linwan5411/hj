package cn.jeefast;

import cn.jeefast.entity.HjArea;
import cn.jeefast.service.HjAreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ComponentScan(value = "cn.jeefast")
@SpringBootTest
public class DynamicDataSourceTest {

    @Resource
    private HjAreaService hjAreaService;

    @Test
    public void test1() throws Exception {
        HjArea a = new HjArea();a.setAreaId(111111L);a.setAreaName("test");
        hjAreaService.insert(a);

        a.setAreaName("xxxxx");
        hjAreaService.updateById(a);
    }


}
