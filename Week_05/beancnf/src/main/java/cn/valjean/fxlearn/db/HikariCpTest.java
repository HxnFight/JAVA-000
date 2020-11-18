package cn.valjean.fxlearn.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public class HikariCpTest {

    private static HikariDataSource ds;

    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(DbOperation.url);
            config.setUsername(DbOperation.username);
            config.setPassword(DbOperation.password);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            config.setMaximumPoolSize(5);
            config.setPoolName("HC-----------> ");
            ds = new HikariDataSource(config);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return ds.getConnection();
    }

}
