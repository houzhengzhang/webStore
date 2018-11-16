package com.imudges.store.web.servlet;

import com.imudges.store.domain.PageModel;
import com.imudges.store.domain.Product;
import com.imudges.store.service.ProductService;
import com.imudges.store.service.serviceImp.ProductServiceImp;
import com.imudges.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Date: 2018/11/16 12:57
 * @Description:
 */
@WebServlet(name = "ProductServlet", urlPatterns = "/ProductServlet")
public class ProductServlet extends BaseServlet {
    public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取pid
        int pid = Integer.parseInt(request.getParameter("pid"));
        // 调用业务层
        ProductService productService = new ProductServiceImp();
        try {
            // 根据pid查询商品信息
            Product product = productService.findProductByPid(pid);
            // 将查询结果放入request
            request.setAttribute("product", product);
            // 转发页面
            return "/jsp/product_info.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取 cid num
        String cid = request.getParameter("cid");
        int curNum = Integer.parseInt(request.getParameter("num"));
        ProductService productService = new ProductServiceImp();
        try {
            // 调用业务层 以分页形式查询当前类别下的商品信息
            PageModel pageModel = productService.findProductsByCidWithPage(cid, curNum);
            // 将pageModel对象放入request
            request.setAttribute("page", pageModel);
            // 转发
            return "/jsp/product_list.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
