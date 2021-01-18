package cn.valjean.week08;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("cn.valjean.week08.mapper")
@SpringBootApplication
public class Week08Application {

    public static void main(String[] args) {
        SpringApplication.run(Week08Application.class, args);
    }

}
