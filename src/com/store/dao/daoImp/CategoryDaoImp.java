package com.store.dao.daoImp;

import com.store.dao.CategoryDao;
import com.store.domain.Category;
import com.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/13 13:07
 * @Description:
 */
public class CategoryDaoImp implements CategoryDao {
    @Override
    public List<Category> getAllCats() throws SQLException {
        String sql = "select * from category";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(Category.class));
    }

    @Override
    public void addCategory(Category category) throws SQLException {
        String sql = "insert into category values(?, ?)";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql, category.getCname(), category.getCid());
    }

    @Override
    public void updateCategory(Category category) throws SQLException {
        String sql = "update category set cname = ? where cid = ?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        queryRunner.update(sql, category.getCname(), category.getCid());
    }

    @Override
    public Category findCategoryByCid(String cid) throws SQLException {
        String sql = "select * from category where cid = ?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanHandler<>(Category.class), cid);
    }

    @Override
    public void delCategory(Connection connection, String cid) throws SQLException {
        String sql = "delete from category where cid = ?";
        QueryRunner queryRunner = new QueryRunner();
        queryRunner.update(connection, sql, cid);
    }
}
