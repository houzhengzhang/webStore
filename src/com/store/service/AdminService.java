package com.store.service;

import com.store.domain.Admin;

import java.sql.SQLException;

/**
 * @ Date: 2018/11/28 19:34
 * @ Description:
 */
public interface AdminService {
    Admin adminLogin(Admin admin) throws SQLException;
}
