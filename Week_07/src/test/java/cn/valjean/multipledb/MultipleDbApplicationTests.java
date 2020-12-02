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
import java.util.Random;

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

    @Test
    void testSS() {
        TOrder tOrder = new TOrder();

        long createTime = System.currentTimeMillis();
        Random random = new Random(createTime);
        tOrder.setId(100L);
        String transId = random.nextInt(10) + "";
        tOrder.setTransId(transId);
        tOrder.setOrderType(1);
        tOrder.setAmount(1.0);
        tOrder.setSaleType(1);
        tOrder.setGoodsId("sfsfs");
        tOrder.setPurchasePhone("sfs");
        tOrder.setPurchaseAddr("sfs");
        tOrder.setStatus(1);
        tOrder.setCreateTime(createTime);
        tOrder.setUpdateTime(createTime);

        boolean save = tOrderService.save(tOrder);
        System.out.println("save = " + save);

//        TOrder torderById = tOrderService.getById(100);
//        System.out.println("torderById = " + torderById);


    }


    @Test
    void querySS() {

        TOrder torderById = tOrderService.getById(1);
        System.out.println("torderById first = " + torderById);

        torderById = tOrderService.getById(1);
        System.out.println("torderById second = " + torderById);

    }


}
