package com.imudges.store.dao;

import com.imudges.store.domain.PageModel;
import com.imudges.store.domain.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/16 12:58
 * @Description:
 */
public interface ProductDao {
    public List<Product> findNews() throws SQLException;
    public List<Product> findHots() throws SQLException;
    public Product findProductByPid(int pid) throws SQLException;
    public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException;
    public int findTotalRecords(String cid) throws SQLException;
}
