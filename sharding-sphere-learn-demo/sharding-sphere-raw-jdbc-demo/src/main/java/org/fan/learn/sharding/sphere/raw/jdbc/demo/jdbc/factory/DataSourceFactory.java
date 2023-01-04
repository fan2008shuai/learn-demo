package org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.factory;

import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;
import org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.config.ShardingType;
import org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.factory.datasource.DatabasesDataSourceConfiguration;
import org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.factory.datasource.DatabasesTablesDataSourceConfiguration;
import org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.factory.datasource.TablesDataSourceConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author author
 * @date 2022/12/2
 */
public class DataSourceFactory {

    public static DataSource getDataSource(ShardingType shardingType) throws SQLException {
        DataSource dataSource = null;
        switch (shardingType) {
            case SHARDING_DATABASES:
                dataSource = new DatabasesDataSourceConfiguration().getDataSource();
                break;
            case SHARDING_TABLES:
                dataSource = new TablesDataSourceConfiguration().getDataSource();
                break;
            case SHARDING_DATABASES_TABLES:
                dataSource = new DatabasesTablesDataSourceConfiguration().getDataSource();
                break;
            default:
                break;
        }
        return dataSource;
    }

    public static DataSource getDataSource(ShardingType shardingType, ModeConfiguration modeConfiguration) throws SQLException {
        DataSource dataSource = null;
        switch (shardingType) {
            case SHARDING_DATABASES:
                dataSource = new DatabasesDataSourceConfiguration().getDataSource(modeConfiguration);
                break;
            case SHARDING_TABLES:
                dataSource = new TablesDataSourceConfiguration().getDataSource(modeConfiguration);
                break;
            case SHARDING_DATABASES_TABLES:
                dataSource = new DatabasesTablesDataSourceConfiguration().getDataSource(modeConfiguration);
                break;
            default:
                break;
        }
        return dataSource;
    }

}
