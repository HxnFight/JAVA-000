package cn.valjean.week08;

import cn.valjean.week08.entity.TOrder;
import cn.valjean.week08.service.TOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class Week08ApplicationTests {


    @Resource
    private TOrderService tOrderService;

    @Test
    void contextLoads() {
    }

    private TOrder createBean() {
        TOrder tOrder = new TOrder();
        long createTime = System.currentTimeMillis();
        Random random = new Random(createTime);
//        tOrder.setId(100L);
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
        tOrder.setUpdateTime(new Date());
        return tOrder;
    }


    @Test
    void saveOrder() {
        TOrder bean = createBean();
        boolean save = tOrderService.save(bean);
    }

    @Test
    void queryOrder() {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("trans_id", "10000");
        TOrder torderById = tOrderService.getOne(wrapper);
        System.out.println("torderById = " + torderById);
    }

    @Test
    void saveOrderByStock() {
        TOrder bean = createBean();
        bean.setTransId("10000");
        boolean save = tOrderService.save(bean);
    }

    @Test
    void delOrder() {
        tOrderService.delOrderByTransId("10000");
    }

    @Test
    void updateOrder() {
        UpdateWrapper<TOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("trans_id", "10000")
                .set("goods_id", "update_" + System.currentTimeMillis());
        tOrderService.update(wrapper);
    }


}
