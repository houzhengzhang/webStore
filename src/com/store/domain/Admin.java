package com.store.domain;

/**
 * admin 实体类
 */


public class Admin {
    private String email;
    private String password;
    private String adminname;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminname() {
        return adminname;
    }

}

