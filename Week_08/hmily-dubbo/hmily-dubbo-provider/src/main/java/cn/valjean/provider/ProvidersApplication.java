package cn.valjean.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ProvidersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvidersApplication.class, args);
    }

}
