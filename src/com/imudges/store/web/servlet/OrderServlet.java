package com.imudges.store.web.servlet;

import com.imudges.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Date: 2018/11/19 19:15
 * @Description:
 */
@WebServlet(name = "OrderServlet", urlPatterns = "/OrderServlet")
public class OrderServlet extends BaseServlet {
    public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return null;
    }
}
