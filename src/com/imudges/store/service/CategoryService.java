package com.imudges.store.service;

import com.imudges.store.domain.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/13 13:08
 * @Description:
 */
public interface CategoryService {
    public List<Category> getAllCats() throws SQLException;
}
