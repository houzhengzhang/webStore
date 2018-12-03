package com.store.web.servlet;

import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.service.serviceImp.CategoryServiceImp;
import com.store.utils.JedisUtils;
import com.store.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/13 13:06
 * @Description:
 */
@WebServlet(name = "CategoryServlet", urlPatterns = "/CategoryServlet")
public class CategoryServlet extends BaseServlet {
    private static final long serialVersionUID = -727978090184771633L;

    public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String jsonStr = null;
        Jedis jedis = null;
        // 调用业务层 获取全部分类信息
        CategoryService categoryService = new CategoryServiceImp();
        List<Category> categoryList = null;
        // 在redis中获取全部分类信息
        try {

            jedis = JedisUtils.getJedis();
            jsonStr = jedis.get("allCats");
            if (null == jsonStr || "".equals(jsonStr)) {
                // redis 中数据为空
                categoryList = categoryService.getAllCats();
                // 将全部分类转换为JSON格式的数据
                jsonStr = JSONArray.fromObject(categoryList).toString();
                // 存入redis
                jedis.set("allCats", jsonStr);
            }

        } catch (JedisConnectionException e) {
            e.printStackTrace();
            // redis 连接异常
            categoryList = categoryService.getAllCats();
            // 将全部分类转换为JSON格式的数据
            jsonStr = JSONArray.fromObject(categoryList).toString();
        } finally {
            if (null != jedis) {
                // 释放redis资源
                JedisUtils.closeJedis(jedis);
            }
        }


        // 设置响应数据类型为JSON
        response.setContentType("application/json;charset=utf-8");
        // 将全部分类信息响应到客户端
        response.getWriter().print(jsonStr);
        // 不需要转发
        return null;
    }
}
