package com.store.service.serviceImp;

import com.store.dao.CategoryDao;
import com.store.dao.ProductDao;
import com.store.dao.daoImp.CategoryDaoImp;
import com.store.dao.daoImp.ProductDaoImp;
import com.store.domain.Category;
import com.store.domain.Product;
import com.store.service.CategoryService;
import com.store.utils.JDBCUtils;
import com.store.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
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

    @Override
    public void updateCategory(Category category) throws SQLException {
        categoryDao.updateCategory(category);
    }

    @Override
    public Category findCategoryByCid(String cid) throws SQLException {
        return categoryDao.findCategoryByCid(cid);
    }

    @Override
    public void delCategory(String cid) throws SQLException {
        // 首先查询该类别下的商品
        ProductDao productDao = new ProductDaoImp();
        List<Product> productList = productDao.findProductByCid(cid);
        Connection connection = null;
        try {
            // 获取连接
            connection = JDBCUtils.getConnection();
            // 开启事务
            connection.setAutoCommit(false);
            for (Product p : productList) {
                // 修改商品状态
                p.setPflag(1);
                // 更新数据库
                productDao.updateProduct(connection, p);
            }
            // 删除该类别
            categoryDao.delCategory(connection, cid);
            // 提交事务
            connection.commit();
        } catch (Exception e) {
            // 回滚
            connection.rollback();
            e.printStackTrace();
        }
    }
}
