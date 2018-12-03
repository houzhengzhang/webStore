package com.store.domain;

/**
 * @Date: 2018/11/19 14:45
 * @Description:
 */
public class CartItem {

    private Product product;    //携带购物项参数（图片路径，商品名称。商品价格）
    private int num;            // 当前类别商品数量
    private double subTotal;    // 小计

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getSubTotal() {
        return product.getShop_price() * num;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", num=" + num +
                ", subTotal=" + subTotal +
                '}';
    }
}
