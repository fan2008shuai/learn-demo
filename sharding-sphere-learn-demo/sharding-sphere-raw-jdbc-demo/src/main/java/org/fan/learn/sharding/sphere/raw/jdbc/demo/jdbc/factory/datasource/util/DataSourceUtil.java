package org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.factory.datasource.util;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * @author author
 * @date 2022/12/1
 */
public class DataSourceUtil {
    private static final String HOST = "localhost";

    private static final int PORT = 3306;

    private static final String USER_NAME = "root";

    private static final String PASSWORD = "";

    public static DataSource createDataSource(String db) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        dataSource.setUrl(String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC" +
                "&useSSL=false&useUnicode=true&characterEncoding=UTF-8",
                HOST, PORT, db));
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

}
