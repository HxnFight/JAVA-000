package cn.valjean.multipledb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;


//https://blog.csdn.net/qq_37502106/article/details/91044952

@Slf4j
public class DynamicDbSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public DynamicDbSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }

    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }
}
