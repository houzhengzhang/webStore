package com.store.service;

import com.store.domain.Orders;
import com.store.domain.PageModel;
import com.store.domain.User;

import java.sql.SQLException;

/**
 * @Date: 2018/11/19 19:16
 * @Description:
 */
public interface OrderService {
    void saveOrders(Orders orders) throws SQLException;

    PageModel findOrdersWithPage(User user, int curNum) throws SQLException;

    Orders findOrdersByOid(String oid) throws SQLException;

    void affirmOrdersByOid(String oid) throws SQLException;

    PageModel findAllProductsWithPage(int curNum, String state) throws SQLException;

    PageModel findAllProductsWithPage(int curNum) throws SQLException;

    void updateOrder(Orders orders) throws SQLException;
}
