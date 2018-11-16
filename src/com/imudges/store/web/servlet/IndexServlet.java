package com.imudges.store.web.servlet;

import com.imudges.store.domain.Category;
import com.imudges.store.domain.Product;
import com.imudges.store.service.CategoryService;
import com.imudges.store.service.ProductService;
import com.imudges.store.service.serviceImp.CategoryServiceImp;
import com.imudges.store.service.serviceImp.ProductServiceImp;
import com.imudges.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Date: 2018/11/13 13:01
 * @Description:
 */
@WebServlet(name = "IndexServlet", urlPatterns = "/IndexServlet")
public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用业务层 product service 查询最新商品 查询最热商品 返回两个集合
        ProductService productService = new ProductServiceImp();
        try {
            // 最新商品
            List<Product> newProductList = productService.findNews();
            // 最热商品
            List<Product> hotProductList = productService.findHots();
            // 将集合放入request
            request.setAttribute("news", newProductList);
            request.setAttribute("hots", hotProductList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 转发到真实的首页
       return "/jsp/index.jsp";
    }
}
