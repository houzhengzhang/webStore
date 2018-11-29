package com.imudges.store.web.servlet;

import com.imudges.store.domain.Category;
import com.imudges.store.service.CategoryService;
import com.imudges.store.service.serviceImp.CategoryServiceImp;
import com.imudges.store.utils.UUIDUtils;
import com.imudges.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @ Date: 2018/11/24 15:57
 * @ Description:
 */
@WebServlet(name = "AdminCategoryServlet", urlPatterns = "/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
    private static final long serialVersionUID = -8940145559215326377L;

    public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取全部分类信息
        CategoryService categoryService = new CategoryServiceImp();
        try {
            List<Category> categoryList = categoryService.getAllCats();
            // 将全部分类信息放入request
            System.out.println(categoryList.get(0).toString());
            request.setAttribute("allCats", categoryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 转发
        return "/admin/category/list.jsp";
    }

    public String addCategoryUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/admin/category/add.jsp";
    }

    public String addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取分类名称
        String cName = request.getParameter("cname");
        // 创建分类id
        String cid = UUIDUtils.getId();
        Category category = new Category(cName, cid);
        CategoryService categoryService = new CategoryServiceImp();
        try {
            // 调用业务层添加分类
            categoryService.addCategory(category);
            // 重定向到查询全部分类信息页面
            response.sendRedirect("/jsp/AdminCategoryServlet?method=findAllCats");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
