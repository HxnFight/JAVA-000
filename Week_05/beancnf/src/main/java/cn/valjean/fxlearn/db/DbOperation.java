package cn.valjean.fxlearn.db;

import sun.lwawt.macosx.CSystemTray;

import java.sql.*;
import java.util.Arrays;


public class DbOperation {

    public static final String url = "jdbc:mysql://192.168.1.110:3306/valjean?useUnicode=true&characterEncoding=UTF-8";
    public static final String username = "apper";//数据库的用户名
    public static final String password = "superme@2019";//数据库的密码:这个是自己安装数据库的时候设置的，每个人不同。
    //    http://tw.gitbook.net/jdbc/jdbc-transactions.html
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;  //声明数据库连接对

    //静态代码块负责加载驱动
    static {
        try {
            Class.forName(driver);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //单例模式返回数据库连接对象，供外部调用
    public static Connection getConnection() throws Exception {
        if (conn == null) {
//            conn = DriverManager.getConnection(url, username, password); //连接数据库
            conn = HikariCpTest.getConnection();
            return conn;
        }
        return conn;
    }

    public static void insert() throws Exception {
        conn = DbOperation.getConnection();
        Statement statement = conn.createStatement();

        for (int i = 0; i < 5; i++) {
            String sql = "insert test (id) values (  " + System.currentTimeMillis() + ")";
            statement.executeUpdate(sql);
        }

        String sql = "insert test (id) values (1605696189612)";
        statement.executeUpdate(sql);


        statement.close();
    }

    public static void delect() throws Exception {
        conn = DbOperation.getConnection();
        Statement statement = conn.createStatement();
//        String sql = "delete from test where id = 1605696189612";
        String sql = "delete from test where id = 1";
        int cnt = statement.executeUpdate(sql);
        System.out.println("delete cnt = " + cnt);
        statement.close();
        statement.close();
    }

    public static void change() throws Exception {
        conn = DbOperation.getConnection();
        Statement statement = conn.createStatement();
        String sql = "update  test set id = 1 where id = 1605696189612";
        int cnt = statement.executeUpdate(sql);
        System.out.println("change cnt = " + cnt);
        statement.close();
    }

    public static void query() throws Exception {
        conn = DbOperation.getConnection();
        Statement statement = conn.createStatement();
        String result;

//        ResultSet resultSet = statement.executeQuery("select * from test");
        ResultSet resultSet = statement.executeQuery("select * from test where  id = 1605696189612");

        while (resultSet.next()) {
            result = resultSet.getString("id");
            System.out.println("result = " + result);
        }
        resultSet.close();
        statement.close();
    }

    public static void prepareStatementT() throws Exception {
        conn = DbOperation.getConnection();

        String sql = "insert test (id) values (?)";
        PreparedStatement statement = conn.prepareStatement(sql);

        long timeMillis = System.currentTimeMillis();
        statement.setDouble(1, timeMillis);
        int cnt = statement.executeUpdate();
        System.out.println("prepareStatementT cnt = " + cnt);

        String result;
//        ResultSet resultSet = statement.executeQuery("select * from test");
        sql = "select * from test where  id = " + timeMillis;
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            result = resultSet.getString("id");
            System.out.println("result = " + result);
        }
        resultSet.close();

        statement.close();
    }


    public static void addBatch() throws Exception {

        long start = System.currentTimeMillis();

        conn = DbOperation.getConnection();
        conn.setAutoCommit(false);

        String sql = "insert test (id) values (?)";

        PreparedStatement statement = conn.prepareStatement(sql);

        for (int i = 0; i < 5; i++) {
            statement.setDouble(1, System.currentTimeMillis());
            Thread.sleep(100);
//            if (i == 4) conn.rollback();
            statement.addBatch();
        }

        int[] ints = statement.executeBatch();
        conn.commit();


        double cnt = Arrays.stream(ints).asDoubleStream().sum();
        System.out.println("prepareStatementT cnt = " + cnt);


        String result;
//        ResultSet resultSet = statement.executeQuery("select * from test");
        sql = "select * from test where  id > " + start;
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            result = resultSet.getString("id");
            System.out.println("result = " + result);
        }
        resultSet.close();
        statement.close();
//        conn.commit();
    }


    public static void singleSQL() throws SQLException {
        try {
            insert();
            change();
            query();
            delect();
            query();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
        }

    }

    public static void main(String[] args) throws Exception {

//        prepareStatementT();
//        addBatch();
//        singleSQL();
        mockData();

    }


    public static void mockData() throws Exception {

        long start = System.currentTimeMillis();

        conn = DbOperation.getConnection();
        conn.setAutoCommit(false);

        String sql = "insert into t_order (trans_id, order_type, amount, sale_type, goods_id, purchase_phone, purchase_addr, create_time, update_time) values (?,?,?,?,?,?,?,?,?);";

        PreparedStatement statement = conn.prepareStatement(sql);

        String str;
//        for (int i = 1; i < 1000000; i++) {
        for (int i = 1; i < 10; i++) {
            str = String.valueOf(i);
            statement.setString(1, str);
//            i = i*100;
            statement.setInt(2, i + 1);
            statement.setDouble(3, i);
            statement.setInt(4, i);
            statement.setString(5, str);
            statement.setString(6, str);
            statement.setString(7, str);
            statement.setLong(8, System.currentTimeMillis());
            statement.setLong(9, System.currentTimeMillis());
            statement.addBatch();
            if (i % 1000 == 0) {
                int[] ints = statement.executeBatch();
                conn.commit();
                double cnt = Arrays.stream(ints).asDoubleStream().sum();
                System.out.println("prepareStatementT cnt = " + cnt);
            }
        }

        int[] ints = statement.executeBatch();
        conn.commit();


        double cnt = Arrays.stream(ints).asDoubleStream().sum();
        System.out.println("prepareStatementT cnt = " + cnt);
//        String result;
////        ResultSet resultSet = statement.executeQuery("select * from test");
//        sql = "select * from test where  id > " + start;
//        ResultSet resultSet = statement.executeQuery(sql);

//        while (resultSet.next()) {
//            result = resultSet.getString("id");
//            System.out.println("result = " + result);
//        }
//        resultSet.close();
        statement.close();
//        conn.commit();
        long time = System.currentTimeMillis() - start;
        System.out.println("customers time  = " + time);
    }


}
