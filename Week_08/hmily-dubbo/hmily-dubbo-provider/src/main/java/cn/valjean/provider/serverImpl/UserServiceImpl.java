package cn.valjean.provider.serverImpl;

import cn.valjean.common.entity.User;
import cn.valjean.common.server.Userservice;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0", tag = "red", weight = 100)
public class UserServiceImpl implements Userservice {
    @Override
    public User findUserById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("humily dubbo test user ");
        return user;
    }
}
