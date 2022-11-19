package org.fan.learn.demo.spring.boot.mybatis.bean;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author
 */

public enum ClusterType {
    // cluster-proxy-redis
    CLUSTER_PROXY_REDIS("cluster-proxy-redis"),
    // cluster-proxy-mysql
    CLUSTER_PROXY_MYSQL("cluster-proxy-mysql"),
    // unknown
    UNKNOWN("unknown");

    @EnumValue
    private String name;

    ClusterType(String name) {
        this.name = name;
    }
}
