package com.store.web.filter;

import com.store.domain.User;

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
@WebFilter(filterName = "UserPriFilter", urlPatterns = {"/jsp/cart.jsp", "/jsp/order_info.jsp", "/jsp/order_list.jsp"})
public class UserPriFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        // 判断session是否有用户登录
        User user = (User) request.getSession().getAttribute("loginUser");
        if (null != user) {
            // 登录则放行
            chain.doFilter(req, resp);
        } else {
            // 未登录则提示信息
            request.setAttribute("msg", "请登录后再进行操作");
            // 跳转页面
            request.getRequestDispatcher("/jsp/info.jsp").forward(request, resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
