<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>网络商城</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    </head>

    <body>
        <%@ include file="/jsp/header.jsp" %>
        <div class="container-fluid">
            <!--

                描述：商品显示
            -->
            <div class="container-fluid">
                <div class="col-md-2">

                </div>
                <div class="col-md-10">
                    <h2>热门商品&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/title2.jpg"/></h2>
                </div>
                <div class="col-md-1"  >
                    <%--<img src="products/hao/big01.jpg" width="205" height="404" style="display: inline-block;"/>--%>
                </div>
                <div class="col-md-10">
                    <%--<div class="col-md-6" style="text-align:center;height:200px;padding:0px;">--%>
                        <%--&lt;%&ndash;<a href="product_info.htm">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<img src="products/hao/middle01.jpg" width="220px" height="200px"&ndash;%&gt;--%>
                                 <%--&lt;%&ndash;style="display: inline-block;">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                    <%--</div>--%>
                    <c:forEach items="${hots}" var="p">
                        <div class="col-md-2" style="text-align:center;height:240px; display: block;
                            float: left; margin: 1% 0 1% 1.6%;box-shadow: 0px 0px 3px rgb(150, 150, 150);
                            -webkit-box-shadow: 0px 0px 3px rgb(150, 150, 150);">
                            <a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${p.pid}">
                                <img src="${pageContext.request.contextPath}/${p.pimage}" width="130" height="130"
                                     style="display: inline-block;margin:10% 0% 0% 0%;">
                            </a>
                            <p style="color:green;display: block; overflow: hidden; text-overflow: ellipsis;
                               white-space: nowrap; align-content: center">
                                <a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${p.pid}"
                                    >${p.pname}</a></p>
                            <p><font color="#E4393C" style="font-size:16px">&yen;${p.shop_price}</font></p>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div style="width: 100%; height: 20px">
            </div>
            <!--

                描述：广告部分
            -->
            <div class="container-fluid">
                <img src="products/hao/ad.png" width="90%" style="margin: 0% 0% 0% 5%;" />
            </div>
            <!--

                描述：商品显示
            -->
            <div class="container-fluid">
                <div class="col-md-2">

                </div>
                <div class="col-md-10">
                    <h2>最新商品&nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/title2.jpg"/></h2>
                </div>
                <div class="col-md-1">
                    <%--<img src="products/hao/big01.jpg" width="205" height="404" style="display: inline-block;"/>--%>
                </div>
                <div class="col-md-10">
                    <%--<div class="col-md-6" style="text-align:center;height:200px;padding:0px;">--%>
                        <%--<a href="product_info.htm">--%>
                            <%--<img src="products/hao/middle01.jpg" width="516px" height="200px"--%>
                                 <%--style="display: inline-block;">--%>
                        <%--</a>--%>
                    <%--</div>--%>
                    <c:forEach items="${news}" var="p">
                        <div class="col-md-2" style="text-align:center;height:240px; display: block;
                            float: left; margin: 1% 0 1% 1.6%;box-shadow: 0px 0px 3px rgb(150, 150, 150);
                            -webkit-box-shadow: 0px 0px 3px rgb(150, 150, 150);">
                            <a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${p.pid}">
                                <img src="${pageContext.request.contextPath}/${p.pimage}" width="130" height="130"
                                     style="display: inline-block;margin:10% 0% 0% 0%;">
                            </a>
                            <p style="color:green;display: block; overflow: hidden; text-overflow: ellipsis;
                               white-space: nowrap; align-content: center">
                                <a href="${pageContext.request.contextPath}/ProductServlet?method=findProductByPid&pid=${p.pid}"
                                   >${p.pname}</a></p>
                            <p><font color="#E4393C" style="font-size:16px">&yen;${p.shop_price}</font></p>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div style="width: 100%; height: 50px">
            </div>
        </div>

        <!--
            描述：页脚部分
        -->
        <%@ include file="/jsp/footer.jsp" %>
    </body>

</html>