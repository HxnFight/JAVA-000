package cn.valjean.fxlearn;

import cn.valjean.starter.domain.Klass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class FxLearnApplicationTest {


    @Resource
    private Klass klass;

    @Test
    void hello() {
        System.out.println("klass = " + klass.toString());
    }
}
