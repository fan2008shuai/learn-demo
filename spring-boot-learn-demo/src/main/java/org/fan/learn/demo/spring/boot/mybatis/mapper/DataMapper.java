package org.fan.learn.demo.spring.boot.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.fan.learn.demo.spring.boot.mybatis.bean.ClusterInfo;

import java.util.List;

/**
 * @author author
 * @date 2022/11/14
 */
@Mapper
public interface DataMapper {

    /**
     * 获得集群信息
     * @return
     */
    @Select("select * from cluster_info")
    public List<ClusterInfo> getClusterInfos();

    /**
     * 插入集群信息
     * @param clusterInfo
     * @return
     */
    @Insert("insert into cluster_info(app_key, cluster_name, " +
            "cluster_type, redis_name, mysql_name, " +
            "cluster_instance_count, max_qpm_daily, total_request_daily, create_time, update_time) " +
            "values(#{appKey}," +
            " #{clusterName}, #{clusterType}, #{redisName}, " +
            "#{mysqlName}, #{clusterInstanceCount}, #{maxQpmDaily}, " +
            "#{totalRequestDaily}, #{createTime}, #{updateTime})")
    public int insertClusterInfo(ClusterInfo clusterInfo);

}
