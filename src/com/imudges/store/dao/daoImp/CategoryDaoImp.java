package com.imudges.store.dao.daoImp;

import com.imudges.store.dao.CategoryDao;
import com.imudges.store.domain.Category;
import com.imudges.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
}
