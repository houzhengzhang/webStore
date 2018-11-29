package com.imudges.store.dao;

import com.imudges.store.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/13 13:07
 * @Description:
 */
public interface CategoryDao {
    List<Category> getAllCats() throws SQLException;

    void addCategory(Category category) throws SQLException;
}
