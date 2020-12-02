package cn.valjean.multipledb;

import cn.valjean.multipledb.entity.TOrder;
import cn.valjean.multipledb.service.TOrderService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class MultipleDbApplicationTests {

    @Resource
    private TOrderService tOrderService;

    @Test
    void tOrderService() {
        TOrder byId = tOrderService.findOrderByIdPrimary(1);
        log.info(" torder --> {}", JSON.toJSONString(byId));

        byId = tOrderService.findOrderByIdSecondary(1);
        log.info(" torder --> {}", JSON.toJSONString(byId));

    }

}
