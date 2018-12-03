package com.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2018/11/19 14:51
 * @Description:
 */
public class Cart {
    // 个数不确定的购物项
    // pid <=> CartItem
    private Map<String, CartItem> map = new HashMap<String, CartItem>();
    // 总计
    private double total;

    // 添加
    public void addCartItemToCart(CartItem cartItem) {
        // 获取pid
        String pid = cartItem.getProduct().getPid();
        if (map.containsKey(pid)) {
            // 买过该商品
            CartItem oldItem = map.get(pid);
            oldItem.setNum(oldItem.getNum() + cartItem.getNum());
        } else {
            // 第一次购买
            map.put(pid, cartItem);
        }

    }

    // 移除
    public void removeCartItem(String pid) {
        // 遍历查找
        map.remove(pid);
    }

    // 清空
    public void clearCart() {
        map.clear();
    }

    public double getTotal() {
        // 总计清零
        total = 0;
        // 获取map中所有的购物项
        Collection<CartItem> values = map.values();
        // 遍历求和
        for (CartItem cartItem : values) {
            total += cartItem.getSubTotal();
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Collection<CartItem> getCartItems() {
        return map.values();
    }
}
