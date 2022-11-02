package org.fan.learn.demo.quick.start.type.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.fan.learn.demo.quick.start.bean.User;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEnumTypeHandler implements TypeHandler<User.Sex> {

    @Override
    public void setParameter(PreparedStatement ps, int i, User.Sex parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getName());
    }

    /**
     * Gets the result.
     *
     * @param rs         the rs
     * @param columnName Column name, when configuration <code>useColumnLabel</code> is <code>false</code>
     * @return the result
     * @throws SQLException the SQL exception
     */
    @Override
    public User.Sex getResult(ResultSet rs, String columnName) throws SQLException {
        String name = rs.getString(columnName);
        return User.Sex.getSex(name);
    }

    @Override
    public User.Sex getResult(ResultSet rs, int columnIndex) throws SQLException {
        String name = rs.getString(columnIndex);
        return User.Sex.getSex(name);
    }

    @Override
    public User.Sex getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String name = cs.getString(columnIndex);
        return User.Sex.getSex(name);
    }
}
