package com.imudges.store.service.serviceImp;

import com.imudges.store.dao.UserDao;
import com.imudges.store.dao.daoImp.UserDaoImp;
import com.imudges.store.domain.User;
import com.imudges.store.service.UserService;
import com.imudges.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * @Date: 2018/11/11 17:22
 * @Description:
 */
public class UserServiceImp implements UserService {
    @Override
    public void userRegist(User user) throws SQLException {
        // 实现注册功能
        UserDao userDao = new UserDaoImp();
        userDao.userRegist(user);
    }

    @Override
    public boolean userActive(String code) throws SQLException {
        // 实现用户邮箱验证
        UserDao userDao = new UserDaoImp();
        User user = userDao.userActive(code);
        if(null != user){
            // 可以根据激活码查询到用户
            // 修改用户状态，清除激活码
            user.setState(1);
            user.setCode(null);
            // 对数据库进行更新操作
            userDao.userUpdate(user);
            return  true;
        }else {
            // 不可以根据激活码查询到用户
            return false;

        }
    }

    @Override
    public User userLodin(User user) throws SQLException {
        // 利用异常在模块间传递消息
        UserDao userDao = new UserDaoImp();
        User u = userDao.userLodin(user);
        if(null == u){
            // 密码错误
            throw new RuntimeException("密码有误！");
        }else if(u.getState()==0){
            throw new RuntimeException("用户未激活！");
        }else {
            return u;
        }
    }
}
