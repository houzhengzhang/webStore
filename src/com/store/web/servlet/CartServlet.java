package com.store.web.servlet;

import com.store.domain.Cart;
import com.store.domain.CartItem;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.serviceImp.ProductServiceImp;
import com.store.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Date: 2018/11/19 15:57
 * @Description:
 */
@WebServlet(name = "CartServlet", urlPatterns = "/CartServlet")
public class CartServlet extends BaseServlet {
    private static final long serialVersionUID = -7122769949534195945L;

    /**
     * 添加购物项
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String addCartItemToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从session获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (null == cart) {
            // 获取不到则创建 并放入session
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        // 获取商品id，数量
        String pid = request.getParameter("pid");
        int num = Integer.parseInt(request.getParameter("quantity"));
        // 通过pid查询商品
        ProductService productService = new ProductServiceImp();
        CartItem cartItem = new CartItem();
        try {
            Product product = productService.findProductByPid(pid);
            cartItem.setNum(num);
            cartItem.setProduct(product);
            // 调用购物车的方法
            cart.addCartItemToCart(cartItem);
            // 重定向
            response.sendRedirect("/store/jsp/cart.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除购物车项
     *
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String removeCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取删除商品的pid
        String pid = request.getParameter("pid");
        // 获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 删除购物车项
        cart.removeCartItem(pid);
        // 重定向
        response.sendRedirect("/store/jsp/cart.jsp");
        return null;
    }

    public String clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        // 清空购物车
        cart.clearCart();
        // 重定向
        response.sendRedirect("/store/jsp/cart.jsp");
        return null;
    }

}
