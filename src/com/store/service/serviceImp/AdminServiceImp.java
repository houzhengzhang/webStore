package com.store.service.serviceImp;

import com.store.dao.AdminDao;
import com.store.dao.daoImp.AdminDaoImp;
import com.store.domain.Admin;
import com.store.service.AdminService;

import java.sql.SQLException;

/**
 * @ Date: 2018/11/28 19:34
 * @ Description:
 */
public class AdminServiceImp implements AdminService {
    @Override
    public Admin adminLogin(Admin admin) throws SQLException {
        // 利用异常在模块间传递消息
        AdminDao adminDao = new AdminDaoImp();
        Admin a = adminDao.adminLogin(admin);
        if (null == a) {
            // 密码错误
            throw new RuntimeException("密码错误！");
        } else {
            return a;
        }
    }
}
