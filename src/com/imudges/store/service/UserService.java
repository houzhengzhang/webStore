package com.imudges.store.service;

import com.imudges.store.domain.User;

import java.sql.SQLException;

/**
 * @Date: 2018/11/11 17:22
 * @Description:
 */
public interface UserService {
    public void userRegist(User user) throws SQLException;
    public boolean userActive(String code) throws SQLException;
    public User userLodin(User user) throws SQLException;
}
