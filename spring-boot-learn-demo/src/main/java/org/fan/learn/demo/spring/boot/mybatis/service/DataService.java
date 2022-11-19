package org.fan.learn.demo.spring.boot.mybatis.service;

import org.fan.learn.demo.spring.boot.mybatis.bean.ClusterInfo;
import org.fan.learn.demo.spring.boot.mybatis.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author author
 * @date 2022/11/14
 */
@Component
public class DataService {
    @Autowired
    private DataMapper dataMapper;

    public List<ClusterInfo> getClusterInfos() {
        return dataMapper.getClusterInfos();
    }

    public int insertClusterInfo(ClusterInfo clusterInfo) {
        return dataMapper.insertClusterInfo(clusterInfo);
    }
}
