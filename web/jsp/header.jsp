<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>关于我们</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- 引入自定义css文件 style.css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    </head>
    <body>
        <div class="page-header-fixed">
            <div class="collapse navbar-collapse">
                <nav class="navbar navbar-default" role="navigation">
                    <div class="container">
                        <div class="col-md-8">
                        </div>
                        <%--padding-top:20px;--%>
                        <div class="col-md-3" style="padding-top:10px;">
                            <ol class="list-inline">
                                <%-- 用户未登录--%>
                                <c:if test="${empty loginUser}">
                                    <li><a href="${pageContext.request.contextPath}/UserServlet?method=loginUI">登录</a>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/UserServlet?method=registUI">注册</a>
                                    </li>
                                </c:if>
                                <%-- 用户登录后--%>
                                <c:if test="${not empty loginUser}">
                                    <li>您好，&nbsp; ${loginUser.username} </li>
                                    <li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/OrderServlet?method=findOrdersWithPage&num=1">我的订单</a>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/UserServlet?method=logOut">退出</a>
                                    </li>
                                </c:if>
                            </ol>
                        </div>
                    </div>

                </nav>
            </div>
        </div>
        <!--
            描述：导航条
        -->
        <div class="container">
            <nav class="navbar navbar-inverse">
                <div class="container">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href=" ${pageContext.request.contextPath}/IndexServlet">首页</a>
                    </div>

                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav" id="categoryUL">
                            <%--<c:forEach items="${categoryList}" var="cat">--%>
                            <%--<li><a href="">${cat.cname}</a></li>--%>
                            <%--</c:forEach>--%>
                        </ul>
                        <%--<form class="navbar-form navbar-right" role="search">--%>
                        <%--<div class="form-group">--%>
                        <%--<input type="text" class="form-control" placeholder="Search">--%>
                        <%--</div>--%>
                        <%--<button type="submit" class="btn btn-default">Submit</button>--%>
                        <%--</form>--%>

                    </div>
                    <!-- /.navbar-collapse -->
                </div>
                <!-- /.container-fluid -->
            </nav>
        </div>
    </body>
    <script type="text/javascript">
        // 向服务端CategoryServlet-getAllcats发起ajax请求，服务端将JSON格式的数据返回 绑定到显示分类区域
        $(function () {
            var url = "/store/CategoryServlet";
            var obj = {"method": "findAllCats"};
            $.post(url, obj, function (data) {
                // 获取服务端返回的json数组 动态显示分类区域代码
                $.each(data, function (i, obj) {
                    var li = " <li><a href='${pageContext.request.contextPath}/ProductServlet?method=findProductsByCidWithPage&num=1&cid=" +
                        obj.cid + "'>" + obj.cname + "</a></li>";
                    $("#categoryUL").append(li);
                });
            }, "json");
        });
    </script>

</html>