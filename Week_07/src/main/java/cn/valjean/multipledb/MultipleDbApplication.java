package cn.valjean.multipledb;

import cn.valjean.multipledb.config.DynamicDbSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@SpringBootApplication
@MapperScan("cn.valjean.multipledb.mapper")
//@Import({DynamicDbSource.class})
public class MultipleDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDbApplication.class, args);
    }

}
