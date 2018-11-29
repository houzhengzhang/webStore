package com.imudges.store.web.servlet;

import com.imudges.store.domain.Cart;
import com.imudges.store.domain.CartItem;
import com.imudges.store.domain.Orderitem;
import com.imudges.store.domain.Orders;
import com.imudges.store.domain.PageModel;
import com.imudges.store.domain.User;
import com.imudges.store.service.OrderService;
import com.imudges.store.service.serviceImp.OrderServiceImp;
import com.imudges.store.utils.UUIDUtils;
import com.imudges.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * @ Date: 2018/11/19 19:15
 * @ Description:
 */
@WebServlet(name = "OrderServlet", urlPatterns = "/OrderServlet")
public class OrderServlet extends BaseServlet {

    private static final long serialVersionUID = 6956436636926123554L;

    public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 将购物车信息以订单信息保存
        // 确认用户登录状态
        User user = (User) request.getSession().getAttribute("loginUser");
        if (null == user) {
            request.setAttribute("msg", "请登录之后再下单");
            return "/jsp/info.jsp";
        }
        // 获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // TODO 判断购物项是否为空
//        if(null 0 == cart.getCartItems().size())
        // 创建订单对象 并赋值
        Orders orders = new Orders();
        orders.setOid(UUIDUtils.getCode());
        orders.setOrdertime(new Date());
        orders.setTotal(cart.getTotal());
        orders.setState(1);
        orders.setUser(user);
        // 遍历购物项  创建订单项 并赋值
        for (CartItem item : cart.getCartItems()) {
            Orderitem orderitem = new Orderitem();
            orderitem.setItemid(UUIDUtils.getCode());
            orderitem.setQuantity(item.getNum());
            orderitem.setTotal(item.getSubTotal());
            orderitem.setProduct(item.getProduct());
            // 添加订单项加入到订单对象存储
            orderitem.setOrders(orders);
            orders.getOrderList().add(orderitem);
        }
        // 调用业务层功能，保存订单
        OrderService orderService = new OrderServiceImp();
        try {
            // 订单数据 用户数据 订单项数据
            orderService.saveOrders(orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 清空购物车 删除session cart
        cart.clearCart();
        // TODO 清空购物车
        request.getSession().removeAttribute("cart");
        // 订单放入request
        request.setAttribute("orders", orders);
        // 转发
        return "/jsp/order_info.jsp";
    }

    public String findOrdersWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户信息
        User user = (User) request.getSession().getAttribute("loginUser");
        // 获取当前页
        int curNum = Integer.parseInt(request.getParameter("num"));
        // 调用业务层功能：查询当前用户订单信息，返回PageModel
        OrderService orderService = new OrderServiceImp();
        PageModel pageModel = null;
        try {
            pageModel = orderService.findOrdersWithPage(user, curNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 将PageModel放入request
        request.setAttribute("page", pageModel);
        // 转发
        return "/jsp/order_list.jsp";
    }

    public String findOrdersByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取订单oid
        String oid = request.getParameter("oid");
        // 调用业务层 查询订单信息
        OrderService orderService = new OrderServiceImp();
        try {
            Orders orders = orderService.findOrdersByOid(oid);
            // 将查询结果放入request
            request.setAttribute("orders", orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/jsp/order_info.jsp";
    }
}

