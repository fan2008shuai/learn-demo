package org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.factory.datasource;

import org.apache.shardingsphere.infra.config.mode.ModeConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author author
 * @date 2022/12/1
 */
public interface DataSourceConfiguration {

    /**
     * getDataSource
     * @return
     * @throws SQLException
     */
    DataSource getDataSource() throws SQLException;

    /**
     * getDataSource with mode configuration
     * @param modeConfiguration
     * @return
     * @throws SQLException
     */
    DataSource getDataSource(ModeConfiguration modeConfiguration) throws SQLException;

}
