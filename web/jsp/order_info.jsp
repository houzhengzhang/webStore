﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>下订单</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- 引入自定义css文件 style.css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
        <style>
            body {
                margin-top: 20px;
                margin: 0 auto;
            }

            .carousel-inner .item img {
                width: 100%;
                height: 300px;
            }
        </style>
    </head>

    <body>


        <%-- 页面头部--%>
        <%@include file="/jsp/header.jsp" %>

        <div class="container">
            <div class="row">

                <div style="margin:0 auto;margin-top:10px;width:950px;">
                    <strong>订单详情：</strong>
                    <div style="width: 100%; height: 20px">
                    </div>
                    <table class="table table-bordered">
                        <tbody>
                            <tr class="warning">
                                <th colspan="5">订单编号:${orders.oid} </th>
                            </tr>
                            <tr class="warning">
                                <th>图片</th>
                                <th>商品</th>
                                <th>价格</th>
                                <th>数量</th>
                                <th>小计</th>
                            </tr>
                            <c:forEach items="${orders.orderList}" var="item">
                                <tr class="active">
                                    <td width="60" width="40%">
                                        <input type="hidden" name="id" value="22">
                                        <img src="${pageContext.request.contextPath}/${item.product.pimage}" width="70"
                                             height="60">
                                    </td>
                                    <td width="30%">
                                        <a target="_blank"> ${item.product.pname} </a>
                                    </td>
                                    <td width="20%">
                                        ￥${item.product.shop_price}
                                    </td>
                                    <td width="10%">
                                            ${item.quantity}
                                    </td>
                                    <td width="15%">
                                        <span class="subtotal">￥${item.total}</span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div style="text-align:right;margin-right:120px;">
                    商品金额: <strong style="color:#ff6600;">￥${orders.total}元</strong>
                </div>

            </div>

            <div>
                <hr/>
                <form class="form-horizontal" style="margin-top:5px;margin-left:150px;" id="orderForm"
                      action="${pageContext.request.contextPath}/OrderServlet?method=payOrder&oid=${orders.oid}"
                      method="post">
                    <div class="form-group">
                        <label for="username" class="col-sm-1 control-label">地址</label>
                        <div class="col-sm-5">
                            <input type="text" name="address" class="form-control" id="username" placeholder="请输入收货地址">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-1 control-label">收货人</label>
                        <div class="col-sm-5">
                            <input type="text" name="name" class="form-control" id="inputPassword3" placeholder="请输收货人">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="confirmpwd" class="col-sm-1 control-label">电话</label>
                        <div class="col-sm-5">
                            <input type="text" name="phone" class="form-control" id="confirmpwd" placeholder="请输入联系方式">
                        </div>
                    </div>
                </form>

                <hr/>
                <p style="text-align:right;margin-right:100px;">
                    <a href="javascript:document.getElementById('orderForm').submit();">
                        <img src="${pageContext.request.contextPath}/img/finalbutton.gif" width="204" height="51"
                             border="0"/>
                    </a>
                </p>

            </div>

        </div>
        <div style="width: 100%; height: 20px">
        </div>
        <%--页脚--%>
        <%@include file="/jsp/footer.jsp" %>

    </body>

</html>