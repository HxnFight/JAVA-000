package cn.valjean.week08.controller;


import cn.valjean.week08.entity.TOrder;
import cn.valjean.week08.service.TOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author valjean
 * @since 2020-12-02
 */
@RestController
@RequestMapping("/t-order")
public class TOrderController {
    @Resource
    private TOrderService tOrderService;

    public static void main(String[] args) {
        int i = "9".hashCode();
        System.out.println("i = " + i % 8);

    }

    @GetMapping("/api")
    public String test() {
        TOrder tOrder = new TOrder();

        tOrder.setId(100L);
        tOrder.setTransId("42472893472347");
        tOrder.setOrderType(1);
        tOrder.setAmount(1.0);
        tOrder.setSaleType(1);
        tOrder.setGoodsId("sfsfs");
        tOrder.setPurchasePhone("sfs");
        tOrder.setPurchaseAddr("sfs");
        tOrder.setStatus(1);
        tOrder.setCreateTime(System.currentTimeMillis());
        tOrder.setUpdateTime(new Date());

        boolean save = tOrderService.save(tOrder);
        System.out.println("save = " + save);

        TOrder torderById = tOrderService.getById(100);
        System.out.println("torderById = " + torderById);
        return "OK";
    }

}

