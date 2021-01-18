package cn.valjean.webflux.controller;

import cn.valjean.webflux.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class WebFluxController {

    @GetMapping(value = "/hello")
    @ResponseBody
    public Publisher<String> hello() {
        log.info("test webflux");
        return Mono.just("valjean");
    }

    @GetMapping(value = "/user")
    @ResponseBody
    public Publisher<User> getUser() {
        User user = new User();
        user.setId("1");
        user.setName("user-1");
        log.info("test webflux");
        return Mono.just(user);
    }
}
