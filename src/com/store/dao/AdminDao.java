package com.store.dao;

import com.store.domain.Admin;

import java.sql.SQLException;

/**
 * @ Date: 2018/11/28 19:35
 * @ Description:
 */
public interface AdminDao {
    Admin adminLogin(Admin admin) throws SQLException;
}
