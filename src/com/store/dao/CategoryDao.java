package com.store.dao;

import com.store.domain.Category;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/13 13:07
 * @Description:
 */
public interface CategoryDao {
    List<Category> getAllCats() throws SQLException;

    void addCategory(Category category) throws SQLException;

    void updateCategory(Category category) throws SQLException;

    Category findCategoryByCid(String cid) throws SQLException;

    void delCategory(Connection connection, String cid) throws SQLException;
}
