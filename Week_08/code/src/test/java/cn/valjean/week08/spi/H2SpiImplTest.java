package cn.valjean.week08.spi;

import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.*;

class H2SpiImplTest {

    @Test
    void sayHello() {
        ServiceLoader<SpiService> spiServices = ServiceLoader.load(SpiService.class);
        for (SpiService spiService : spiServices) {
            spiService.sayHello();
        }
    }
}
