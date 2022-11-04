package org.fan.learn.demo.quick.start.mapper;

import org.fan.learn.demo.quick.start.bean.User;

import java.util.List;

public interface UserMapper {
    public User getUserById(long id);
    public int insertNewUser(User user);

    public List<User> getUserListBySex(User.Sex sex);
}
