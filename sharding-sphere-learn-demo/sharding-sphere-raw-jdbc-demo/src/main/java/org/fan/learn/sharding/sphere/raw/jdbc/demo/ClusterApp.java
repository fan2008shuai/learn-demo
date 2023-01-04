package org.fan.learn.sharding.sphere.raw.jdbc.demo;

import com.google.gson.Gson;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.apache.shardingsphere.mode.repository.cluster.ClusterPersistRepositoryConfiguration;
import org.fan.learn.sharding.sphere.raw.jdbc.demo.entity.Order;
import org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.OrderDao;
import org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.config.ShardingType;
import org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.factory.DataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author author
 * @date 2022/12/2
 */
public class ClusterApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterApp.class);

    private static final String REPOSITORY_TYPE_ZOOKEEPER = "ZooKeeper";

    private static final String REPOSITORY_NAMESPACE = "sharding-sphere-governance";

    private static final String ZOOKEEPER_CONNECTION_STRING = "localhost:2181";

    private static final String MODE_TYPE = "Cluster";


    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        try {
            ClusterPersistRepositoryConfiguration clusterRepositoryConfig =
                    new ClusterPersistRepositoryConfiguration(REPOSITORY_TYPE_ZOOKEEPER,
                            REPOSITORY_NAMESPACE,
                            ZOOKEEPER_CONNECTION_STRING,
                            new Properties());
            ModeConfiguration modeConfiguration = new ModeConfiguration(MODE_TYPE, clusterRepositoryConfig);

            DataSource dataSource = DataSourceFactory.getDataSource(ShardingType.SHARDING_DATABASES_TABLES, modeConfiguration);
            OrderDao orderDao = new OrderDao(dataSource);

            for (int i = 0; i < 10; i++) {
                Order order = Order.builder().userId(i).addressId(i).status("insert test").build();
                orderDao.insertOrder(order);
            }

            List<Order> orders = orderDao.selectAll();
            orders.forEach(order -> LOGGER.info(GSON.toJson(order)));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            TimeUnit.MINUTES.sleep(90);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
