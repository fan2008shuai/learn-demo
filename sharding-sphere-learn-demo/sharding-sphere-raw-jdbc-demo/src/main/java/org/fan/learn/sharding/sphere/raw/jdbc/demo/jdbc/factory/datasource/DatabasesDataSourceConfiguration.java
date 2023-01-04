package org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.factory.datasource;

import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author author
 * @date 2022/12/1
 */
public class DatabasesDataSourceConfiguration implements DataSourceConfiguration {
    @Override
    public DataSource getDataSource() throws SQLException {
        return null;
    }

    @Override
    public DataSource getDataSource(ModeConfiguration modeConfiguration) throws SQLException {
        return null;
    }
}
