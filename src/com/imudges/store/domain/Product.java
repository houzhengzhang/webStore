package com.imudges.store.domain;

import java.util.Date;

/**
 * product 实体类
 */


public class Product {
    private String pid;
    private String pname;
    private double market_price;
    private double shop_price;
    private String pimage;
    private Date pdate;
    private int isHot;
    private String pdesc;
    private int pflag;
    private String cid;

    public Product() {
    }

    public Product(String pid, String pname, double market_price, double shop_price, String pimage, Date pdate, int isHot,
                   String pdesc, int pflag, String cid) {
        this.pid = pid;
        this.pname = pname;
        this.market_price = market_price;
        this.shop_price = shop_price;
        this.pimage = pimage;
        this.pdate = pdate;
        this.isHot = isHot;
        this.pdesc = pdesc;
        this.pflag = pflag;
        this.cid = cid;

    }

    public String getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public double getMarket_price() {
        return market_price;
    }

    public double getShop_price() {
        return shop_price;
    }

    public String getPimage() {
        return pimage;
    }

    public Date getPdate() {
        return pdate;
    }

    public int getIsHot() {
        return isHot;
    }

    public String getPdesc() {
        return pdesc;
    }

    public int getPflag() {
        return pflag;
    }

    public String getCid() {
        return cid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
    }

    public void setShop_price(double shop_price) {
        this.shop_price = shop_price;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public void setPflag(int pflag) {
        this.pflag = pflag;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid='" + pid + '\'' +
                ", pname='" + pname + '\'' +
                ", market_price=" + market_price +
                ", shop_price=" + shop_price +
                ", pimage='" + pimage + '\'' +
                ", pdate=" + pdate +
                ", isHot=" + isHot +
                ", pdesc='" + pdesc + '\'' +
                ", pflag=" + pflag +
                ", cid='" + cid + '\'' +
                '}';
    }
}

