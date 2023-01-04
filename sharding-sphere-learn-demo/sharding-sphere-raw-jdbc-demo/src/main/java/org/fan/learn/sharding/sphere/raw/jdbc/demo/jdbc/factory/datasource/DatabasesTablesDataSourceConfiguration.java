package org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.factory.datasource;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.factory.datasource.util.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author author
 * @date 2022/12/1
 */
public class DatabasesTablesDataSourceConfiguration implements DataSourceConfiguration {
    private static final String ALGORITHM_EXPRESSION_KEY = "algorithm-expression";
    private static final String KEY_GENERATOR_SNOWFLAKE = "snowflake";

    @Override
    public DataSource getDataSource() throws SQLException {
        return ShardingSphereDataSourceFactory.createDataSource(
                "demo_ds",
                createDataSourceMap(),
                Collections.singleton(createShardingRuleConfiguration()),
                new Properties());
    }

    @Override
    public DataSource getDataSource(ModeConfiguration modeConfiguration) throws SQLException {
        return ShardingSphereDataSourceFactory.createDataSource(
                "demo_ds",
                modeConfiguration,
                createDataSourceMap(),
                Collections.singleton(createShardingRuleConfiguration()),
                new Properties());
    }

//    shardingRuleConfiguration.getTables().add(getOrderItemTableRuleConfiguration());
//    shardingRuleConfiguration.getBindingTableGroups().add("t_order, t_order_item");
//    shardingRuleConfiguration.getBroadcastTables().add("t_address");


    private ShardingRuleConfiguration createShardingRuleConfiguration() {
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();

        shardingRuleConfiguration.getTables().add(getOrderTableRuleConfiguration());

        shardingRuleConfiguration.setDefaultDatabaseShardingStrategy(
                new StandardShardingStrategyConfiguration("user_id",
                                                    "inline"));
        shardingRuleConfiguration.setDefaultTableShardingStrategy(
                new StandardShardingStrategyConfiguration("user_id",
                                                    "modulo"));

        Properties properties = new Properties();
        properties.setProperty(ALGORITHM_EXPRESSION_KEY, "demo_ds_${user_id % 2}");
        shardingRuleConfiguration.getShardingAlgorithms().put("inline",
                new AlgorithmConfiguration("INLINE", properties));
        shardingRuleConfiguration.getShardingAlgorithms().put("modulo",
                new AlgorithmConfiguration("MODULO", new Properties()));
        shardingRuleConfiguration.getKeyGenerators().put(KEY_GENERATOR_SNOWFLAKE,
                new AlgorithmConfiguration("SNOWFLAKE", new Properties()));

        return shardingRuleConfiguration;
    }

    private ShardingTableRuleConfiguration getOrderTableRuleConfiguration() {
        ShardingTableRuleConfiguration shardingTableRuleConfiguration =
                new ShardingTableRuleConfiguration("t_order",
                            "demo_ds_${0..1}.t_order_${[0, 1]}");
        shardingTableRuleConfiguration.setKeyGenerateStrategy(
                new KeyGenerateStrategyConfiguration("order_id",
                                          KEY_GENERATOR_SNOWFLAKE));
        return shardingTableRuleConfiguration;
    }


    private ShardingTableRuleConfiguration getOrderItemTableRuleConfiguration() {
        ShardingTableRuleConfiguration result = new ShardingTableRuleConfiguration("t_order_item", "demo_ds_${0..1}.t_order_item_${[0, 1]}");
        result.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("order_item_id", KEY_GENERATOR_SNOWFLAKE));
        return result;
    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("demo_ds_0", DataSourceUtil.createDataSource("demo_ds_0"));
        result.put("demo_ds_1", DataSourceUtil.createDataSource("demo_ds_1"));
        return result;
    }
}

