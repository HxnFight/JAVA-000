package cn.valjean.fxlearn;

import cn.valjean.starter.domain.Klass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@SpringBootApplication
public class FxLearnApplication {

    @Resource
    private Klass klass;

    public static void main(String[] args) {
        SpringApplication.run(FxLearnApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return klass.toString();
    }
}
