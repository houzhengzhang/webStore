package com.store.dao;

import com.store.domain.Orderitem;
import com.store.domain.Orders;
import com.store.domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2018/11/19 19:17
 * @ Description:
 */
public interface OrderDao {
    void saveOrders(Connection connection, Orders orders) throws SQLException;

    void saveOrderItem(Connection connection, Orderitem orderitem) throws SQLException;

    int getTotalRecords(User user) throws SQLException;

    List findOrdersWithPage(User user, int startIndex, int pageSize) throws SQLException;

    Orders findOrdersByOid(String oid) throws SQLException;

    int findTotalRecords() throws SQLException;

    List findOrdersWithPage(int startIndex, int pageSize, String state) throws SQLException;

    List findOrdersWithPage(int startIndex, int pageSize) throws SQLException;

    void updateOrder(Orders orders) throws SQLException;

}
