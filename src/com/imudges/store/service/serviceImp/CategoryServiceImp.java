package com.imudges.store.service.serviceImp;

import com.imudges.store.dao.CategoryDao;
import com.imudges.store.dao.daoImp.CategoryDaoImp;
import com.imudges.store.domain.Category;
import com.imudges.store.service.CategoryService;
import com.imudges.store.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/13 13:08
 * @Description:
 */
public class CategoryServiceImp implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImp();
    @Override
    public List<Category> getAllCats() throws SQLException {
        return categoryDao.getAllCats();
    }

    @Override
    public void addCategory(Category category) throws SQLException {
        categoryDao.addCategory(category);
        // 更新redis缓存
        Jedis jedis = JedisUtils.getJedis();
        jedis.del("allCats");
        JedisUtils.closeJedis(jedis);
    }
}
