package com.imudges.store.dao.daoImp;

import com.imudges.store.dao.AdminDao;
import com.imudges.store.domain.Admin;
import com.imudges.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @ Date: 2018/11/28 19:35
 * @ Description:
 */
public class AdminDaoImp implements AdminDao {
    @Override
    public Admin adminLogin(Admin admin) throws SQLException {
        String sql = "select * from admin where email=? and password=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanHandler<>(Admin.class), admin.getEmail(), admin.getPassword());
    }
}
