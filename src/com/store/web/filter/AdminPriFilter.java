package com.store.web.filter;

import com.store.domain.Admin;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ Date: 2018/11/24 15:23
 * @ Description:
 */
@WebFilter(filterName = "AdminPriFilter", urlPatterns = {"/admin/*"})
public class AdminPriFilter implements Filter {
    private String[] excludedPageArray = {"/admin/index.jsp", "/admin/info.jsp"};

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        boolean isExcludedPage = false;
        for (String page : excludedPageArray) {
            //判断是否在过滤url之内
            if (request.getServletPath().equals(page)) {
                isExcludedPage = true;
                break;
            }
        }
        if (isExcludedPage) {
            //在过滤url之内
            chain.doFilter(req, resp);
            return;
        }
        // 判断session是否有用户登录
        Admin admin = (Admin) request.getSession().getAttribute("loginAdmin");
        if (null != admin) {
            // 登录则放行
            chain.doFilter(req, resp);
        } else {
            // 未登录则提示信息
            request.setAttribute("msg", "请登录后再进行操作");
            // 跳转页面
            request.getRequestDispatcher("/admin/info.jsp").forward(request, resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
