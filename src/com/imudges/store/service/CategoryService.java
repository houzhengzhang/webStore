package com.imudges.store.service;

import com.imudges.store.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2018/11/13 13:08
 * @ Description:
 */
public interface CategoryService {
    List<Category> getAllCats() throws SQLException;

    void addCategory(Category category) throws SQLException;
}
