package cn.valjean.common.server;

import cn.valjean.common.entity.Inventory;

public interface InventoryService {
    Inventory queryInventoryById(Integer id);
}
