package org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc;

import org.fan.learn.sharding.sphere.raw.jdbc.demo.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author author
 * @date 2022/12/3
 */
public class OrderDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDao.class);

    private DataSource dataSource;

    public OrderDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public long insertOrder(Order order) {
        String sql = "insert into t_order(user_id, address_id, status, create_time, update_time) values(?, ?, ?, ?, ?)";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {
            statement.setInt(1, order.getUserId());
            statement.setLong(2, order.getAddressId());
            statement.setString(3, order.getStatus());
            statement.setTimestamp(4, new Timestamp(order.getCreateTime().getTime()));
            statement.setTimestamp(5, new Timestamp(order.getUpdateTime().getTime()));

            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    order.setOrderId(resultSet.getLong(1));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("insert order error.", e);
        }
        return order.getOrderId();
    }

    /**
     * 不需要指定路由key，自动聚合分库分表的所有数据
     * @return
     */
    public List<Order> selectAll() {
        String sql = "select * from t_order";
        return getOrders(sql);
    }

    public List<Order> getOrders(String sql) {
        List<Order> orders = new LinkedList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                ) {
            while (resultSet.next()) {
                Order order = Order.builder()
                        .orderId(resultSet.getLong("order_id"))
                        .userId(resultSet.getInt("user_id"))
                        .addressId(resultSet.getLong("address_id"))
                        .status(resultSet.getString("status"))
                        .createTime(resultSet.getTimestamp("create_time"))
                        .updateTIme(resultSet.getTimestamp("update_time"))
                        .build();

                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.error("get orders error.", e);
        }
        return orders;
    }
}
