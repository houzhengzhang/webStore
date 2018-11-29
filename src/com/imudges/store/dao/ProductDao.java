package com.imudges.store.dao;

import com.imudges.store.domain.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/16 12:58
 * @Description:
 */
public interface ProductDao {
    List<Product> findNews() throws SQLException;

    List<Product> findHots() throws SQLException;

    Product findProductByPid(String pid) throws SQLException;

    List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException;

    int findTotalRecords(String cid) throws SQLException;

    int findTotalRecords() throws SQLException;

    List findProductsWithPage(int startIndex, int pageSize) throws SQLException;

    void addProduct(Product product) throws SQLException;
}
