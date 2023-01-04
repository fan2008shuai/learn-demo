package org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.algorithm;

import com.google.gson.Gson;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Properties;

/**
 * @author author
 * @date 2022/12/2
 */
public class StandardModuloShardingTableAlgorithm implements StandardShardingAlgorithm<Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StandardModuloShardingTableAlgorithm.class);

    private Properties properties;

    @Override
    public void init(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String doSharding(Collection<String> availableTableNames,
                             PreciseShardingValue<Integer> shardingValue) {
        for (String availableTargetName : availableTableNames) {
            if (availableTargetName.endsWith(shardingValue.getValue() / 2 % 2 + "")) {
                return availableTargetName;
            }
        }
        throw new UnsupportedOperationException("modulo sharding error, " +
                "no available table name found");
    }

    @Override
    public String getType() {
        return "MODULO";
    }


    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Integer> shardingValue) {
        return null;
    }

    @Override
    public Properties getProps() {
        return properties;
    }



}
