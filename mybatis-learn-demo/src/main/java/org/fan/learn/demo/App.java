package org.fan.learn.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.fan.learn.demo.quick.start.bean.Role;
import org.fan.learn.demo.quick.start.bean.User;
import org.fan.learn.demo.quick.start.mapper.RoleMapper;
import org.fan.learn.demo.quick.start.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 *  javaType="sex" jdbcType="VARCHAR"
 */
public class App {
    public static void main( String[] args ) {
        String mybatisConfigFile = "mybatis-config.xml";
        SqlSession sqlSession = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFile);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            // 默认不自动提交
            sqlSession = sqlSessionFactory.openSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = roleMapper.getRoleById(1);
            System.out.println("getRoleById: " + role);

            User user = new User();
            user.setUserName("tester");
            user.setSex(User.Sex.MAN);
            user.setMobile("18888888888");
            user.setEmail("tester@test.com");
            user.setNote("just for test");
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            int insertResult = userMapper.insertNewUser(user);
            System.out.println("insertNewUser: " + insertResult);

            User userFromDb = userMapper.getUserById(2);
            System.out.println("getUserById: " + userFromDb);

            List<User> users = userMapper.getUserListBySex(User.Sex.MAN);
            for (User usr : users) {
                System.out.println("user: " + usr);
            }

            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
