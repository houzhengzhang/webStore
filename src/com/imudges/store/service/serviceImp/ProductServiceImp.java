package com.imudges.store.service.serviceImp;

import com.imudges.store.dao.ProductDao;
import com.imudges.store.dao.daoImp.ProductDaoImp;
import com.imudges.store.domain.PageModel;
import com.imudges.store.domain.Product;
import com.imudges.store.service.ProductService;

import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/16 12:58
 * @Description:
 */
public class ProductServiceImp implements ProductService {
    private ProductDao productDao = new ProductDaoImp();
    @Override
    public List<Product> findNews() throws SQLException {
        return productDao.findNews();
    }

    @Override
    public List<Product> findHots() throws SQLException {
        return productDao.findHots();
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        return productDao.findProductByPid(pid);
    }

    @Override
    public PageModel findProductsByCidWithPage(String cid, int curNum) throws SQLException {
        // 创建pagemodel 计算分页参数
        // 当前分类下商品个数
        int totalRecords = productDao.findTotalRecords(cid);
        PageModel pageModel = new PageModel(curNum, totalRecords, 12);
        // 关联集合
        List list = productDao.findProductsByCidWithPage(cid, pageModel.getStartIndex(), pageModel.getPageSize());
        pageModel.setList(list);
        // 关联url
        pageModel.setUrl("/ProductServlet?method=findProductsByCidWithPage&cid=" + cid);
        return pageModel;
    }

    @Override
    public PageModel findAllProductsWithPage(int curNum) throws SQLException {
        int totalRecords = productDao.findTotalRecords();
        // 创建对象
        PageModel pageModel = new PageModel(curNum, totalRecords, 5);
        // 关联集合
        List<Product> productList = productDao.findProductsWithPage(pageModel.getStartIndex(), pageModel.getPageSize());
        pageModel.setList(productList);
        // 设置url
        pageModel.setUrl("AdminProductServlet?method=findAllProductsWithPage");
        return pageModel;
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        productDao.addProduct(product);
    }


}
