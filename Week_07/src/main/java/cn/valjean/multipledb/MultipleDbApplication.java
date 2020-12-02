package cn.valjean.multipledb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@MapperScan("cn.valjean.multipledb.mapper")
public class MultipleDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDbApplication.class, args);
    }

}
