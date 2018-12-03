<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网上商城管理中心</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style_admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/body_admin.css"/>
</head>
<body>
<div class="container">
	<section id="content">
		<form action="${pageContext.request.contextPath }/AdminServlet?method=login" method="post">
			<h1>管理员登录</h1>
			<div>
				<input type="text" placeholder="邮箱" name="email" id="username" />
			</div>
			<div>
				<input type="password" placeholder="密码" name="password" id="password" />
			</div>
			 <div class="" >
                 <c:if test="${empty loginAdmin}">
                     <font color="red"><p align="left" >${msg}</p><font>
                 </c:if>
             </div>
			<div>
				<!-- <input type="submit" value="Log in" /> -->
				<input type="submit" value="登录" class="btn btn-primary" id="js-btn-login"/>
				<%--<a href="#">忘记密码?</a>--%>
				<!-- <a href="#">Register</a> -->
			</div>
		</form><!-- form -->
	</section><!-- content -->
</div>

</body>
</html>