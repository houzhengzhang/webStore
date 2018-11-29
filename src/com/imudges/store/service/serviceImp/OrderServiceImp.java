package com.imudges.store.service.serviceImp;

import com.imudges.store.dao.OrderDao;
import com.imudges.store.dao.daoImp.OrderDaoImp;
import com.imudges.store.domain.Orderitem;
import com.imudges.store.domain.Orders;
import com.imudges.store.domain.PageModel;
import com.imudges.store.domain.User;
import com.imudges.store.service.OrderService;
import com.imudges.store.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2018/11/19 19:17
 * @ Description:
 */
public class OrderServiceImp implements OrderService {
    private
    OrderDao orderDao = new OrderDaoImp();

    @Override
    public void saveOrders(Orders orders) throws SQLException {
        // 保存订单及订单项 - 事务
//        try {
//            // 开启事务
//            JDBCUtils.startTransaction();
//            OrderDao orderDao = new OrderDaoImp();
//            orderDao.saveOrders(orders);
//            for (Orderitem item : orders.getOrderList()) {
//                orderDao.saveOrderItem(item);
//            }
//            JDBCUtils.commitAndClose();
//        } catch (Exception e) {
//            // 回滚
//            JDBCUtils.rollbackAndClose();
//        }
        Connection connection = null;
        try {
            // 获取连接
            connection = JDBCUtils.getConnection();
            // 开启事务
            connection.setAutoCommit(false);
            // 保存订单
            orderDao.saveOrders(connection, orders);
            // 保存订单项
            for (Orderitem item : orders.getOrderList()) {
                orderDao.saveOrderItem(connection, item);
            }
            // 提交
            connection.commit();
        } catch (Exception e) {
            // 回滚
            connection.rollback();
        }
    }

    @Override
    public PageModel findOrdersWithPage(User user, int curNum) throws SQLException {
        // 创建pagemodel 计算分页参数
        int totalRecords = orderDao.getTotalRecords(user);
        PageModel pageModel = new PageModel(curNum, totalRecords, 3);
        // 关联集合
        List list = orderDao.findOrdersWithPage(user, pageModel.getStartIndex(), pageModel.getPageSize());
        pageModel.setList(list);
        // 关联url
        pageModel.setUrl("/AdminOrderServlet?method=findAllOrdersWithPage");
        return pageModel;
    }

    @Override
    public Orders findOrdersByOid(String oid) throws SQLException {
        return orderDao.findOrdersByOid(oid);
    }

    @Override
    public PageModel findAllProductsWithPage(int curNum, String state) throws SQLException {
        int totalRecords = orderDao.findTotalRecords();
        // 创建对象
        PageModel pageModel = new PageModel(curNum, totalRecords, 5);
        // 关联集合
        List<Orders> ordersList = orderDao.findOrdersWithPage(pageModel.getStartIndex(), pageModel.getPageSize(), state);
        pageModel.setList(ordersList);
        // 设置url
        pageModel.setUrl("AdminProductServlet?method=findAllProductsWithPage");
        return pageModel;
    }

    @Override
    public PageModel findAllProductsWithPage(int curNum) throws SQLException {
        int totalRecords = orderDao.findTotalRecords();
        // 创建对象
        PageModel pageModel = new PageModel(curNum, totalRecords, 5);
        // 关联集合
        List<Orders> ordersList = orderDao.findOrdersWithPage(pageModel.getStartIndex(), pageModel.getPageSize());
        pageModel.setList(ordersList);
        // 设置url
        pageModel.setUrl("AdminProductServlet?method=findAllProductsWithPage");
        return pageModel;
    }

    @Override
    public void updateOrder(Orders orders) throws SQLException {
        orderDao.updateOrder(orders);
    }
}
