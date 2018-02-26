<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>员工权限管理</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugins/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugins/bootstrap-dialog/css/bootstrap-dialog.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/plugins/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" href="${ctx}/resources/css/role.css">
</head>
<body>
	<div class="class">
		<div class="header">
			<%--<span class="littleLogo fl"><img src="${ctx}/resources/images/littleLogo.png" alt="" /></span>--%>
			<ul class="nav fr clearfix">
				<li><a href="${ctx}/index">首页</a></li>
				<c:if test="${menuVO.member}">
					<li><a href="${ctx}/member">会员管理</a></li>
				</c:if>
				<c:if test="${menuVO.courseOrder}">
					<li><a href="${ctx}/courseOrder">约课管理</a></li>
				</c:if>
				<c:if test="${menuVO.courseContent}">
					<li><a href="${ctx}/courseContent">课程管理</a></li>
				</c:if>
				<c:if test="${menuVO.notification}">
					<li><a href="${ctx}/notification">推送管理</a></li>
				</c:if>
				<c:if test="${menuVO.role || menuVO.coach || menuVO.supporter
				|| menuVO.courseType || menuVO.coursePlan || menuVO.config}">
					<li class="cur">
						<div class="dropdown nav-dropdown">
						<span class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">设置
							<span class="caret"></span>
						</span>
							<ul class="dropdown-menu">
								<c:if test="${menuVO.role}">
									<li class="act"><a href="${ctx}/setting/role">员工权限管理</a></li>
								</c:if>
								<c:if test="${menuVO.coach}">
									<li><a href="${ctx}/setting/coach">教练管理</a></li>
								</c:if>
								<c:if test="${menuVO.supporter}">
									<li><a href="${ctx}/setting/supporter">员工管理</a></li>
								</c:if>
								<c:if test="${menuVO.courseType}">
									<li><a href="${ctx}/setting/courseType">课程类型管理</a></li>
								</c:if>
								<c:if test="${menuVO.coursePlan}">
									<li><a href="${ctx}/setting/coursePlan">课程安排</a></li>
								</c:if>
								<c:if test="${menuVO.config}">
									<li><a href="${ctx}/setting/config">配置</a></li>
								</c:if>
							</ul>
						</div>
					</li>
				</c:if>
				<li><a href="${ctx}/logout">退出</a></li>
			</ul>
		</div>
		<div class="content">
			<div><a href="${ctx}/setting/role/add" class="btn-c fr">添加</a></div>
			<div style="margin-top:75px;" id="role-list">
				<ul class="course">
					<li class="cff">教练 <a class="btn-d saveCoachMenu">保存</a></li>
					<li class="clearfix">
						<div class="row">
							<c:forEach items="${coachMenus}" var="menu">
								<label style="margin-right:20px;"><input name="menu" type="checkbox" value="${menu.id}" <c:if test="${menu.selected eq 1}">checked="checked"</c:if> />${menu.name}</label>
							</c:forEach>
						</div>
					</li>
					<c:forEach items="${roles}" var="role">
						<li class="cff" data-id="${role.id}">${role.name} <a class="btn-d save">保存</a> <a class="btn-d delete">删除</a></li>
						<li class="clearfix">
							<div class="row">
								<c:forEach items="${role.menus}" var="menu">
									<label style="margin-right:20px;"><input name="menu" type="checkbox" value="${menu.id}" <c:if test="${menu.selected eq 1}">checked="checked"</c:if> />${menu.name}</label>
								</c:forEach>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</body>
<input type="hidden" id="ctx" value="${ctx}"/>
<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/plugins/bootstrap-dialog/js/bootstrap-dialog.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/namespace.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/crossfit.role.js"></script>
<script type="text/javascript">
	$(function(){
		(new crossfit.cfer.role.roleList()).init();
	});
</script>
</html>