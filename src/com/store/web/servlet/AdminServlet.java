package com.store.web.servlet;

import com.store.domain.Admin;
import com.store.service.AdminService;
import com.store.service.serviceImp.AdminServiceImp;
import com.store.utils.MD5Util;
import com.store.utils.MyBeanUtils;
import com.store.web.base.BaseServlet;

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
        // md5 加密
        admin.setPassword(MD5Util.toMd5(admin.getPassword()));
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

    public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 使 session 失效
        request.getSession().invalidate();
        // 重定向到首页
        response.sendRedirect("/store/index.jsp");
        return null;
    }
}
