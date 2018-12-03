package com.store.web.servlet;

import com.store.domain.Orders;
import com.store.domain.PageModel;
import com.store.service.OrderService;
import com.store.service.serviceImp.OrderServiceImp;
import com.store.web.base.BaseServlet;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @ Date: 2018/11/26 15:56
 * @ Description:
 */
@WebServlet(name = "AdminOrderServlet", urlPatterns = "/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {
    private static final long serialVersionUID = 8816213299441076747L;

    public String findAllOrdersWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用业务层查询全部商品信息返回pagemodel
        OrderService orderService = new OrderServiceImp();
        PageModel pageModel = null;
        // 获取当前页
        int curNum = Integer.parseInt(request.getParameter("num"));
        // 获取订单状态
        String state = request.getParameter("state");
        try {
            if (null == state || "".equals(state)) {
                // 查询全部订单
                pageModel = orderService.findAllProductsWithPage(curNum);
            } else {
                // 按state 查找
                pageModel = orderService.findAllProductsWithPage(curNum, state);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 将 pagemodel 放入 request
        request.setAttribute("page", pageModel);
        // 转发
        return "/admin/order/list.jsp";
    }
    public String findOrderByOidWithAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取订单id
        String oid = request.getParameter("id");
        OrderService orderService = new OrderServiceImp();
        try {
            Orders orders = orderService.findOrdersByOid(oid);
            // 转换为json格式
            JSONArray jsonArray = JSONArray.fromObject(orders.getOrderList());
            // 返回给客户端
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(jsonArray.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String updateOrderByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取订单id
        String oid = request.getParameter("oid");
        // 查询订单
        OrderService orderService = new OrderServiceImp();
        try {
            Orders order = orderService.findOrdersByOid(oid);
            // 修改状态
            order.setState(3);
            orderService.updateOrder(order);
            // 重定向到已发货订单
            response.sendRedirect("/store/AdminOrderServlet?method=findAllOrdersWithPage&num=1&state=3");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
}
