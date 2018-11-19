package com.imudges.store.domain;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.List;

/**
 * orders 实体类
 */ 


public class Orders {
	private String oid;
	private Date ordertime;
	private double total;
	private int state;          // 订单状态 1-下单未付款 2-付款未发货 3-已发货/未收货 4-签收/订单结束
	private String address;     // 收货地址
	private String name;        // 收货人姓名
	private String telephone;   // 地址
//	private String uid;         // 用户id - 外键

    // 对象与对象发生关系，不是属性
    // user对象携带更多的数据
    private User user;

    // 订单对象与订单项之间关系
    private List<Orderitem> orderList = new ArrayList<>();

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOid() {
        return oid;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }


}

