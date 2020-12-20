package cn.valjean.inventory.serverImpl;

import cn.valjean.common.entity.Inventory;
import cn.valjean.common.server.InventoryService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0", tag = "red", weight = 100)
public class InventoryServiceImpl implements InventoryService {
    @Override
    public Inventory queryInventoryById(Integer id) {
        Inventory inventory = new Inventory();
        inventory.setId(id);
        inventory.setName("humily dubbo test inventory ");
        return inventory;
    }
}
