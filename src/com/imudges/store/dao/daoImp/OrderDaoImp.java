package com.imudges.store.dao.daoImp;

import com.imudges.store.dao.OrderDao;
import com.imudges.store.domain.Orderitem;
import com.imudges.store.domain.Orders;
import com.imudges.store.domain.Product;
import com.imudges.store.domain.User;
import com.imudges.store.utils.JDBCUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @ Date: 2018/11/19 19:18
 * @ Description:
 */
public class OrderDaoImp implements OrderDao {
    @Override
    public void saveOrders(Connection connection, Orders orders) throws SQLException {
        String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner();
        Object[] params = {orders.getOid(), orders.getOrdertime(), orders.getTotal(), orders.getState(),
                orders.getAddress(), orders.getName(), orders.getTelephone(), orders.getUser().getUid()};
        queryRunner.update(connection, sql, params);

    }

    @Override
    public void saveOrderItem(Connection connection, Orderitem orderitem) throws SQLException {
        String sql = "insert into orderitem values(?,?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner();
        Object[] params = {orderitem.getItemid(), orderitem.getQuantity(), orderitem.getTotal(),
                orderitem.getProduct().getPid(), orderitem.getOrders().getOid()};
        queryRunner.update(connection, sql, params);
    }

    @Override
    public int getTotalRecords(User user) throws SQLException {
        String sql = "select count(*) from orders where uid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Long num = (Long) queryRunner.query(sql, new ScalarHandler(), user.getUid());
        return num.intValue();
    }

    @Override
    public List findOrdersWithPage(User user, int startIndex, int pageSize) throws SQLException {
        String sql = "select * from orders where uid=? limit ? , ?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<Orders> ordersList = queryRunner.query(sql, new BeanListHandler<>(Orders.class), user.getUid(), startIndex, pageSize);
        // 遍历订单
        for (Orders order : ordersList) {
            // 查询每个订单下的订单项及订单项对应的商品信息
            String oid = order.getOid();
            // 多表查询
            sql = "select * from orderitem o, product p where oid=? and o.pid=p.pid";
            List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), oid);
            // 遍历list
            for (Map<String, Object> map : list) {
                Orderitem orderitem = new Orderitem();
                Product product = new Product();
                // 手动向BeanUtils注册一个时间类型转换器
                // 创建时间类型的转换器
                DateConverter dt = new DateConverter();
                // 设置转换的格式
                dt.setPattern("yyyy-MM-dd");
                // 注册转换器
                ConvertUtils.register(dt, java.util.Date.class);

                // map 数据自动填充
                try {
                    BeanUtils.populate(orderitem, map);
                    BeanUtils.populate(product, map);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                // 设置关联关系
                orderitem.setProduct(product);
                // 将订单项添加至订单list
                order.getOrderList().add(orderitem);
            }
        }
        return ordersList;
    }

    @Override
    public Orders findOrdersByOid(String oid) throws SQLException {
        String sql = "select * from orders where oid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Orders orders = queryRunner.query(sql, new BeanHandler<>(Orders.class), oid);

        // 根据订单id 查询订单项及商品信息
        sql = "select * from orderitem o, product p where o.oid=? and o.pid=p.pid";
        List<Map<String, Object>> ordersList = queryRunner.query(sql, new MapListHandler(), oid);

        for (Map<String, Object> map : ordersList) {
            Orderitem orderitem = new Orderitem();
            Product product = new Product();

            DateConverter dt = new DateConverter();
            // 设置转换的格式
            dt.setPattern("yyyy-MM-dd");
            // 注册转换器
            ConvertUtils.register(dt, java.util.Date.class);

            // map 数据自动填充
            try {
                BeanUtils.populate(orderitem, map);
                BeanUtils.populate(product, map);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

            // 设置关联关系
            orderitem.setProduct(product);
            // 将订单项添加至订单list
            orders.getOrderList().add(orderitem);
        }
        return orders;
    }

    @Override
    public int findTotalRecords() throws SQLException {
        String sql = "select count(*) from orders";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Long num = (Long) queryRunner.query(sql, new ScalarHandler());
        return num.intValue();
    }

    @Override
    public List findOrdersWithPage(int startIndex, int pageSize, String state) throws SQLException {
        String sql = "select * from orders where state=? limit ?,?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(Orders.class), state, startIndex, pageSize);
    }

    @Override
    public List findOrdersWithPage(int startIndex, int pageSize) throws SQLException {
        String sql = "select * from orders limit ?,?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(Orders.class), startIndex, pageSize);
    }

    @Override
    public void updateOrder(Orders orders) throws SQLException {
        String sql = "update orders set ordertime=? ,total=? ,state= ?, address=?,NAME=?, telephone =? WHERE oid=?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params = {orders.getOrdertime(), orders.getTotal(), orders.getState(), orders.getAddress(),
                orders.getName(), orders.getTelephone(), orders.getOid()};
        qr.update(sql, params);
    }
}
