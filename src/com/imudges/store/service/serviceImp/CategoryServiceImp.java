package com.imudges.store.service.serviceImp;

import com.imudges.store.dao.CategoryDao;
import com.imudges.store.dao.daoImp.CategoryDaoImp;
import com.imudges.store.domain.Category;
import com.imudges.store.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/13 13:08
 * @Description:
 */
public class CategoryServiceImp implements CategoryService {

    @Override
    public List<Category> getAllCats() throws SQLException {
        CategoryDao categoryDao = new CategoryDaoImp();
        return categoryDao.getAllCats();
    }
}
