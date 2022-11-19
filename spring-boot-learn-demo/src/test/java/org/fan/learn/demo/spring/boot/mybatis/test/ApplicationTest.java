package org.fan.learn.demo.spring.boot.mybatis.test;

import org.fan.learn.demo.spring.boot.mybatis.bean.ClusterInfo;
import org.fan.learn.demo.spring.boot.mybatis.bean.ClusterType;
import org.fan.learn.demo.spring.boot.mybatis.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @author author
 * @date 2022/11/14
 */
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private DataService dataService;

    @Test
    public void insertClusterInfo() {
        ClusterInfo clusterInfo = new ClusterInfo();
        clusterInfo.setAppKey("app.key.test");
        clusterInfo.setClusterName("cluster_name");
        clusterInfo.setClusterType(ClusterType.CLUSTER_PROXY_MYSQL);
        clusterInfo.setRedisName("cluster_redis");
        clusterInfo.setMysqlName("cluster_mysql");
        clusterInfo.setClusterInstanceCount(10);
        clusterInfo.setMaxQpmDaily(1000);
        clusterInfo.setTotalRequestDaily(2000);
        clusterInfo.setCreateTime(new Date());
        clusterInfo.setUpdateTime(new Date());
        dataService.insertClusterInfo(clusterInfo);

        List<ClusterInfo> infos = dataService.getClusterInfos();
        for (ClusterInfo info : infos) {
            System.out.println(info);
        }
    }
}
