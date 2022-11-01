package org.fan.learn.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.fan.learn.demo.quick.start.bean.Role;
import org.fan.learn.demo.quick.start.mapper.RoleMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        String mybatisConfigFile = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFile);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = roleMapper.getRoleById(1);
            System.out.println(role);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
