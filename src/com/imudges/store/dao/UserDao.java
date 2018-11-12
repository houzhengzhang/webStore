package com.imudges.store.dao;

import com.imudges.store.domain.User;

import java.sql.SQLException;

/**
 * @Date: 2018/11/11 17:19
 * @Description:
 */
public interface UserDao {
    public void userRegist(User user) throws SQLException;
    public User userActive(String code) throws SQLException;
    public void userUpdate(User user) throws SQLException;
    public User userLodin(User user) throws SQLException;
}
