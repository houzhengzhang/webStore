package com.imudges.store.web.servlet;

import com.imudges.store.domain.Category;
import com.imudges.store.service.CategoryService;
import com.imudges.store.service.serviceImp.CategoryServiceImp;
import com.imudges.store.utils.JedisUtils;
import com.imudges.store.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

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
        // 在redis中获取全部分类信息
        Jedis jedis = JedisUtils.getJedis();
        String jsonStr = jedis.get("allCats");
        if (null == jsonStr || "".equals(jsonStr)) {
            // redis 中数据为空
            // 调用业务层 获取全部分类信息
            CategoryService categoryService = new CategoryServiceImp();
            List<Category> categoryList = categoryService.getAllCats();
            // 将全部分类转换为JSON格式的数据
            jsonStr = JSONArray.fromObject(categoryList).toString();
            // 存入redis
            jedis.set("allCats", jsonStr);
        }
        // 释放redis资源
        JedisUtils.closeJedis(jedis);

        // 设置响应数据类型为JSON
        response.setContentType("application/json;charset=utf-8");
        // 将全部分类信息响应到客户端
        response.getWriter().print(jsonStr);
        // 不需要转发
        return null;
    }
}
