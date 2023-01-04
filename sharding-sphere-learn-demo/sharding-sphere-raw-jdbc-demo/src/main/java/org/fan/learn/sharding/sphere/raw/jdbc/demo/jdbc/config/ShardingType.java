package org.fan.learn.sharding.sphere.raw.jdbc.demo.jdbc.config;

/**
 * @author author
 * @date 2022/12/1
 */
public enum ShardingType {
    // 仅分库
    SHARDING_DATABASES,
    // 仅分表
    SHARDING_TABLES,
    // 分库分表
    SHARDING_DATABASES_TABLES;
}
