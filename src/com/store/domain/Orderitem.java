package com.store.domain;

/**
 * orderitem 实体类
 */


public class Orderitem {
    private String itemid;  // 订单编号
    private int quantity;   // 数量
    private double total;   // 小计
    //	private String pid;     // 商品表pid - 外键
//	private String oid;     // 订单id - 外键
    // 对象对应对象
    private Product product;
    private Orders orders;

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Orderitem{"
                + "itemid='" + itemid + '\''
                + ", quantity=" + quantity
                + ", total=" + total
                + ", product=" + product
                + ", orders=" + orders
                + '}';
    }
}

