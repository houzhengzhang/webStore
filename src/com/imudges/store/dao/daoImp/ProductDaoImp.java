package com.imudges.store.dao.daoImp;

import com.imudges.store.dao.ProductDao;
import com.imudges.store.domain.PageModel;
import com.imudges.store.domain.Product;
import com.imudges.store.utils.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/16 12:59
 * @Description:
 */
public class ProductDaoImp implements ProductDao {
    @Override
    public List<Product> findNews() throws SQLException {
        String sql = "select * from product where pflag=0 order by pdate desc limit 0,9";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(Product.class));
    }

    @Override
    public List<Product> findHots() throws SQLException {
        String sql = "select * from product where pflag=0 and is_hot=1 order by pdate desc limit 0,9";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(Product.class));
    }

    @Override
    public Product findProductByPid(int pid) throws SQLException {
        String sql = "select * from product where pid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanHandler<>(Product.class), pid);
    }

    @Override
    public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException {
        String sql = "select * from product where cid=? limit ?,?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        return queryRunner.query(sql, new BeanListHandler<>(Product.class),cid, startIndex, pageSize);
    }

    @Override
    public int findTotalRecords(String cid) throws SQLException {
        String sql = "select count(*) from product where cid=?";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Long num = (Long) queryRunner.query(sql,new ScalarHandler(), cid);
        return num.intValue();
    }
}
