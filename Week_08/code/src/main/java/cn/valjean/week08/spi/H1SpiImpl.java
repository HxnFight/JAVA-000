package cn.valjean.week08.spi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class H1SpiImpl implements SpiService {
    @Override
    public void sayHello() {
        log.info("#this is spi 01 implement SpiService");
    }
}
