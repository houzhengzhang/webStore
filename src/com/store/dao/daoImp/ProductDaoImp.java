package com.store.dao.daoImp;

import com.store.dao.ProductDao;
import com.store.domain.Product;
import com.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/16 12:59
 * @Description:
 */
public class ProductDaoImp implements ProductDao {
    @Override
    public List<Product> findNews() throws SQLException {
        String sql = "select * from product where pflag=0 order by pdate desc limit 0,10";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(Product.class));
    }

    @Override
    public List<Product> findHots() throws SQLException {
        String sql = "select * from product where pflag=0 and is_hot=1 order by pdate desc limit 0,10";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(Product.class));
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        String sql = "select * from product where pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanHandler<>(Product.class), pid);
    }

    @Override
    public List<Product> findProductByCid(String cid) throws SQLException {
        String sql = "select * from product where cid =?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(Product.class), cid);
    }

    @Override
    public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException {
        String sql = "select * from product where pflag=0 and cid=? limit ?,?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(Product.class), cid, startIndex, pageSize);
    }

    @Override
    public int findTotalRecords(String cid) throws SQLException {
        String sql = "select count(*) from product where cid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Long num = (Long) queryRunner.query(sql, new ScalarHandler(), cid);
        return num.intValue();
    }

    @Override
    public int findTotalRecords() throws SQLException {
        String sql = "select count(*) from product";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Long num = (Long) queryRunner.query(sql, new ScalarHandler());
        return num.intValue();
    }

    @Override
    public List findProductsWithPage(String pflag, int startIndex, int pageSize) throws SQLException {
        String sql = "select * from product where pflag=? limit ?,?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(Product.class), pflag, startIndex, pageSize);
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        String sql = "insert into product values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params = {product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(),
                product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(),
                product.getCid()};
        queryRunner.update(sql, params);
    }

    @Override
    public void updateProduct(Connection connection, Product product) throws SQLException {
        String sql = "update product set pname=?, market_price=?, shop_price=?, pimage=?, pdate=?, is_hot=?, "
                + "pdesc=?, pflag=? where pid=?";
        QueryRunner queryRunner = new QueryRunner();
        Object[] params = {product.getPname(), product.getMarket_price(), product.getShop_price(), product.getPimage(),
                product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(), product.getPid()};
        queryRunner.update(connection, sql, params);
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        String sql = "update product set pname=?, market_price=?, shop_price=?, pimage=?, pdate=?, is_hot=?, "
                + "pdesc=?, pflag=?, cid=? where pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] params = {product.getPname(), product.getMarket_price(), product.getShop_price(), product.getPimage(),
                product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(), product.getCid(),
                product.getPid()};
        queryRunner.update(sql, params);
    }
}
