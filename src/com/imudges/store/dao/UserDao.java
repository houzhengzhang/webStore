package com.imudges.store.dao;

import com.imudges.store.domain.User;

import java.sql.SQLException;

/**
 * @Date: 2018/11/11 17:19
 * @Description:
 */
public interface UserDao {
    void userRegist(User user) throws SQLException;

    User userActive(String code) throws SQLException;

    void userUpdate(User user) throws SQLException;

    User userLodin(User user) throws SQLException;
}
