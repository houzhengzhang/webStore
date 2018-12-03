package com.store.service;

import com.store.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2018/11/13 13:08
 * @ Description:
 */
public interface CategoryService {
    List<Category> getAllCats() throws SQLException;

    void addCategory(Category category) throws SQLException;

    void updateCategory(Category category) throws SQLException;

    Category findCategoryByCid(String cid) throws SQLException;

    void delCategory(String cid) throws SQLException;
}
