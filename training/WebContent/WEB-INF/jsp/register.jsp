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
	<title>注册</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/css/register.css">
</head>
<body>
	<div class="banner">
		<div class="registerBox">
			<p class="title">注册</p>
			<div class="input-box">
				<!-- <div class="photo">
					<img id="pic" src="" >
					<input id="upload" name="file" accept="image/*" type="file" style="display: none"/>
				</div> -->
				<div class="row" style="margin-top:30px;">
					<div class="col-sm-6">
						<div class="col-sm-12 fl">
							<div class="col-sm-3 text-center register-title">训练馆名称</div>
							<div class="col-sm-9">
								<div class="col-sm-10">
									<input type="text" id="name" class="srk register-item">
								</div>
							</div>
						</div>
						<div class="col-sm-12 register-tip tip-blue" id="name-tip">训练馆名称不能为空</div>
						<div class="col-sm-12 fl">
							<div class="col-sm-3 text-center register-title">联系电话</div>
							<div class="col-sm-9">
								<div class="col-sm-10">
									<input type="text" id="phone" class="srk register-item">
								</div>
							</div>
						</div>
						<div class="col-sm-12 register-tip tip-blue" id="phone-tip">请输入有效的联系电话</div>
						<div class="col-sm-12 fl">
							<div class="col-sm-3 text-center register-title">负责人</div>
							<div class="col-sm-9">
								<div class="col-sm-10">
									<input type="text" id="contact" class="srk register-item">
								</div>
							</div>
						</div>
						<div class="col-sm-12 register-tip tip-blue" id="contact-tip">负责人姓名不能为空</div>
						<div class="col-sm-12 fl">
							<div class="col-sm-3 text-center register-title">用户名</div>
							<div class="col-sm-9">
								<div class="col-sm-10">
									<input type="text" id="userName" class="srk register-item">
								</div>
							</div>
						</div>
						<div class="col-sm-12 register-tip tip-blue" id="userName-tip">用户名为8~16位字母数字下划线组合</div>
						<div class="col-sm-12 register-tip tip-red" id="userNameExist-tip"></div>
					</div>
					<div class="col-sm-6">
						<div class="col-sm-12 fl">
							<div class="col-sm-3 text-center register-title">训练馆地址</div>
							<div class="col-sm-9">
								<div class="col-sm-10">
									<input type="text" id="address" class="srk register-item">
								</div>
							</div>
						</div>
						<div class="col-sm-12 register-tip tip-blue" id="address-tip">训练馆地址不能为空</div>
						<div class="col-sm-12 fl">
							<div class="col-sm-3 text-center register-title">邮箱地址</div>
							<div class="col-sm-9">
								<div class="col-sm-10">
									<input type="text" id="email" class="srk register-item">
								</div>
							</div>
						</div>
						<div class="col-sm-12 register-tip tip-blue" id="email-tip">请输入有效的邮箱地址</div>
						<div class="col-sm-12 fl">
							<div class="col-sm-3 text-center register-title">负责人手机号</div>
							<div class="col-sm-9">
								<div class="col-sm-10">
									<input type="text" id="contactTel" class="srk register-item">
								</div>
							</div>
						</div>
						<div class="col-sm-12 register-tip tip-blue" id="contactTel-tip">负责人手机号不能为空</div>
						<div class="col-sm-12 fl">
							<div class="col-sm-3 text-center register-title">密码</div>
							<div class="col-sm-9">
								<div class="col-sm-10">
									<input type="text" id="password" class="srk register-item">
								</div>
							</div>
						</div>
						<div class="col-sm-12 register-tip tip-blue" id="password-tip">密码为8~16位字母数字组合</div>
					</div>
				</div>
			</div>
			<div class="registerBtn" id="registerBtn"><a class="btn btn-register" id="btn-register">注册</a></div>
			<div class="login-a pull-right"><a href="${ctx}/">登录</a></div>
		</div>
	</div>
	
	<input type="hidden" id="ctx" value="${ctx}"/>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/namespace.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/crossfit.user.js"></script>
	<script type="text/javascript">
		$(function(){
			(new crossfit.cfer.login.registerJS()).init();
		});
	</script>
</body>
</html>