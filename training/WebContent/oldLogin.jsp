<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>登录页</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/css/login.css">
</head>
<body>
	<div class="banner">
		<%--<div class="logo">
			<img src="${ctx}/resources/images/logo.png" alt="">
		</div>--%>
		<div class="loginBox">
			<p class="title">登录页</p>
			<div class="login-status" align="center"></div>
			<ul class="inputBox">
				<li class="firstLine"><input type="text" id="userName" value="请输入用户名"></li>
				<li class="secondLine"><input type="text" id="password" value="请输入密码"></li>
			</ul>
			<div class="code">
				  <input type="checkbox" id="ck_rmbUser"/>记住密码
			</div>
			<div class="loginBtn" id="loginBtn"><img src="${ctx}/resources/images/loginBtn.png" alt=""></div>
			<div style="margin-left:440px;"><a href="${ctx}/register">注册</a></div>
		</div>
	</div>
	
	<input type="hidden" id="ctx" value="${ctx}"/>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery.cookie.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/namespace.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/crossfit.user.js"></script>
	<script type="text/javascript">
		$(function(){
			(new crossfit.cfer.login.loginJS()).init();
		});
	</script>
</body>
</html>