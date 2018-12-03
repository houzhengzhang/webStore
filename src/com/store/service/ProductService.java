package com.store.service;

import com.store.domain.PageModel;
import com.store.domain.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/16 12:58
 * @Description:
 */
public interface ProductService {
    List<Product> findNews() throws SQLException;

    List<Product> findHots() throws SQLException;

    Product findProductByPid(String pid) throws SQLException;

    PageModel findProductsByCidWithPage(String cid, int curNum) throws SQLException;

    PageModel findAllProductsWithPage(String pflag, int curNum) throws SQLException;

    void addProduct(Product product) throws SQLException;

    void updateProduct(Product product) throws SQLException;
}
