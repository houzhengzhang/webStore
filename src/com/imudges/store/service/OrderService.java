package com.imudges.store.service;

import com.imudges.store.domain.Orders;
import com.imudges.store.domain.PageModel;
import com.imudges.store.domain.User;

import java.sql.SQLException;

/**
 * @Date: 2018/11/19 19:16
 * @Description:
 */
public interface OrderService {
    void saveOrders(Orders orders) throws SQLException;

    PageModel findOrdersWithPage(User user, int curNum) throws SQLException;

    Orders findOrdersByOid(String oid) throws SQLException;

    PageModel findAllProductsWithPage(int curNum, String state) throws SQLException;

    PageModel findAllProductsWithPage(int curNum) throws SQLException;

    void updateOrder(Orders orders) throws SQLException;
}
