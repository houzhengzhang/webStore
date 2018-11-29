package com.imudges.store.web.servlet;

import com.imudges.store.domain.Admin;
import com.imudges.store.service.AdminService;
import com.imudges.store.service.serviceImp.AdminServiceImp;
import com.imudges.store.utils.MyBeanUtils;
import com.imudges.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ Date: 2018/11/28 16:12
 * @ Description:
 */
@WebServlet(name = "AdminServlet", urlPatterns = "/AdminServlet")
public class AdminServlet extends BaseServlet {
    private static final long serialVersionUID = -2268808681712267743L;

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取管理员数据
        Admin admin = new Admin();
        MyBeanUtils.populate(admin, request.getParameterMap());
        // 调用业务层功能
        AdminService adminService = new AdminServiceImp();
        try {
            admin = adminService.adminLogin(admin);
            // 登录信息放入session
            request.getSession().setAttribute("loginAdmin", admin);
            // 重定向
            response.sendRedirect("/store/admin/home.jsp");
            return null;
        } catch (Exception e) {
            // 登录失败
            String msg = "邮箱或密码错误，请重试";
            request.setAttribute("msg", msg);
            return "/admin/index.jsp";
        }
    }
}
