package org.fan.learn.sharding.sphere.raw.jdbc.demo;

import com.google.gson.Gson;
import org.fan.learn.sharding.sphere.raw.jdbc.demo.entity.Order;
import org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.OrderDao;
import org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.config.ShardingType;
import org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.factory.DataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author author
 * @date 2022/12/2
 */
public class StandaloneApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandaloneApp.class);

    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        try {
            DataSource dataSource = DataSourceFactory.getDataSource(ShardingType.SHARDING_DATABASES_TABLES);
            OrderDao orderDao = new OrderDao(dataSource);

//            for (int i = 0; i < 10; i++) {
//                Order order = Order.builder().userId(i).addressId(i).status("insert test").build();
//                orderDao.insertOrder(order);
//            }
//
//            List<Order> orders = orderDao.selectAll();
//            orders.forEach(order -> LOGGER.info(GSON.toJson(order)));

            LOGGER.info("=================================");

            String sql = "select * from t_order where user_id in (0, 1, 2, 3, 4, 5, 6, 7, 8)";
            List<Order> orders2 = orderDao.getOrders(sql);
            orders2.forEach(order -> LOGGER.info(GSON.toJson(order)));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
