package org.fan.learn.demo.quick.start.mapper;

import org.fan.learn.demo.quick.start.bean.User;

public interface UserMapper {
    public User getUserById(long id);
    public int insertNewUser(User user);
}
