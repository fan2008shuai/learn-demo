package org.fan.learn.demo.spring.boot.mybatis.bean;

/**
 * @author
 */
public class ClusterInfo extends BaseInfo {
    private String appKey;
    private String clusterName;
    private ClusterType clusterType;
    private String redisName;
    private String mysqlName;
    private int clusterInstanceCount;
    private long maxQpmDaily;
    private long totalRequestDaily;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public ClusterType getClusterType() {
        return clusterType;
    }

    public void setClusterType(ClusterType clusterType) {
        this.clusterType = clusterType;
    }

    public String getRedisName() {
        return redisName;
    }

    public void setRedisName(String redisName) {
        this.redisName = redisName;
    }

    public String getMysqlName() {
        return mysqlName;
    }

    public void setMysqlName(String mysqlName) {
        this.mysqlName = mysqlName;
    }

    public int getClusterInstanceCount() {
        return clusterInstanceCount;
    }

    public void setClusterInstanceCount(int clusterInstanceCount) {
        this.clusterInstanceCount = clusterInstanceCount;
    }

    public long getMaxQpmDaily() {
        return maxQpmDaily;
    }

    public void setMaxQpmDaily(long maxQpmDaily) {
        this.maxQpmDaily = maxQpmDaily;
    }

    public long getTotalRequestDaily() {
        return totalRequestDaily;
    }

    public void setTotalRequestDaily(long totalRequestDaily) {
        this.totalRequestDaily = totalRequestDaily;
    }

    @Override
    public String toString() {
        return "ClusterInfo{" +
                "appKey='" + appKey + '\'' +
                ", clusterName='" + clusterName + '\'' +
                ", clusterType=" + clusterType +
                ", redisName='" + redisName + '\'' +
                ", mysqlName='" + mysqlName + '\'' +
                ", clusterInstanceCount=" + clusterInstanceCount +
                ", maxQpmDaily=" + maxQpmDaily +
                ", totalRequestDaily=" + totalRequestDaily +
                '}';
    }
}
