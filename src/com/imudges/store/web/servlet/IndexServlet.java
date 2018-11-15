package com.imudges.store.web.servlet;

import com.imudges.store.domain.Category;
import com.imudges.store.service.CategoryService;
import com.imudges.store.service.serviceImp.CategoryServiceImp;
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
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // 调用业务层 获取全部分类信息
//        CategoryService categoryService = new CategoryServiceImp();
//        try {
//            List<Category> categoryList = categoryService.getAllCats();
//            // 将返回的集合放入request
//            request.setAttribute("categoryList", categoryList);
//            // 转发到真实的首页
//            return "/jsp/index.jsp";
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//       return null;
//    }
}
