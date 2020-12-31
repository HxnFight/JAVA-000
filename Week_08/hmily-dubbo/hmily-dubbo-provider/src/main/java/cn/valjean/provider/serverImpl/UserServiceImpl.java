package cn.valjean.provider.serverImpl;

import cn.valjean.common.entity.Inventory;
import cn.valjean.common.server.InventoryService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0", tag = "red", weight = 100)
public class UserServiceImpl implements InventoryService {
    @Override
    public Inventory queryInventoryById(Integer id) {
        Inventory user = new Inventory();
        user.setId(id);
        user.setName("humily dubbo test user ");
        return user;
    }
}
