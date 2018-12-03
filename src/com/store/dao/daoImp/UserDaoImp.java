package com.store.dao.daoImp;

import com.store.dao.UserDao;
import com.store.domain.User;
import com.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @Date: 2018/11/11 17:19
 * @Description:
 */
public class UserDaoImp implements UserDao {
    @Override
    public void userRegist(User user) throws SQLException {
        // 实现注册功能
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params = {user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
                user.getTelephone(), user.getSex(), user.getState(), user.getCode()};
        queryRunner.update(sql, params);

    }

    @Override
    public User userActive(String code) throws SQLException {
        String sql = "select * from user where code=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        User user = queryRunner.query(sql, new BeanHandler<>(User.class), code);
        return user;
    }

    @Override
    public void userUpdate(User user) throws SQLException {
        String sql = "update user set username=?, password=?, name=?, email=?, telephone=?,"
                + "sex=?, state=?, code=? where uid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params = {user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getTelephone(),
                user.getSex(), user.getState(), user.getCode(), user.getUid()};
        queryRunner.update(sql, params);

    }

    @Override
    public User userLodin(User user) throws SQLException {
        String sql = "select * from user where username=? and password=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params = {user.getUsername(), user.getPassword()};
        return queryRunner.query(sql, new BeanHandler<>(User.class), params);
    }
}
