package cn.valjean.multipledb.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class DataSourceConfig {

    /**
     * 显示数据库连接池信息
     *
     * @param dataSource
     */
    public static void logDS(DataSource dataSource) {
        HikariDataSource hds = (HikariDataSource) dataSource;
        String info = "\n\n\tHikariCP连接池配置\n\t连接池名称：" +
                hds.getPoolName() +
                "\n\t最小空闲连接数：" +
                hds.getMinimumIdle() +
                "\n\t最大连接数：" +
                hds.getMaximumPoolSize() +
                "\n\t连接超时时间：" +
                hds.getConnectionTimeout() +
                "ms\n\t空闲连接超时时间：" +
                hds.getIdleTimeout() +
                "ms\n\t连接最长生命周期：" +
                hds.getMaxLifetime() +
                "ms\n";
        log.info(info);
    }

    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    //    @Primary
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public DynamicDbSource dataSource(DataSource primaryDataSource, DataSource secondaryDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put(DbType.FIRST, primaryDataSource);
        targetDataSources.put(DbType.SECOND, secondaryDataSource);
        return new DynamicDbSource(primaryDataSource, targetDataSources);
    }


}
