<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>会员管理</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/plugins/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/pagination.css" media="screen">
	<link rel="stylesheet" href="${ctx}/resources/css/member.css">
</head>
<body>
	<div class="memberBanner">
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
									<li><a href="${ctx}/setting/role">员工权限管理</a></li>
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
									<li class="act"><a href="${ctx}/setting/config">配置</a></li>
								</c:if>
							</ul>
						</div>
					</li>
				</c:if>
				<li><a href="${ctx}/logout">退出</a></li>
			</ul>
		</div>
		<div class="content" style="padding-top:100px;color:#cbcbcc;">
			<div class="row item">
				<div class="col-sm-12 fl">
					<div class="col-sm-3 input-item" align="right">预约与取消时间</div>
					<div class="col-sm-6">
						<div class="col-sm-10">
							<input type="text" id="orderCancelTime" value="${configMap.ORDER_CANCEL_TIME.configValue}" class="srk fl type-name">
						</div>
						<div class="col-sm-2" style="margin-top:10px;">分钟</div>
					</div>
				</div>
			</div>
			<div class="row item">
				<div class="col-sm-12 fl">
					<div class="col-sm-3 input-item" align="right">课堂人数上限</div>
					<div class="col-sm-6">
						<div class="col-sm-10">
							<input type="text" id="peopleLimit" value="${configMap.PEOPLE_LIMIT.configValue}" class="srk fl type-name">
						</div>
						<div class="col-sm-2" style="margin-top:10px;">人</div>
					</div>
				</div>
			</div>
			<div class="row item">
				<div class="col-sm-12 fl">
					<div class="col-sm-3 input-item" align="right">可预约天数</div>
					<div class="col-sm-6">
						<div class="col-sm-10">
							<input type="text" id="orderDay" value="${configMap.ORDER_DAY.configValue}" class="srk fl type-name">
						</div>
						<div class="col-sm-2" style="margin-top:10px;">天</div>
					</div>
				</div>
			</div>
			<div class="btns">
				<span class="hold" id="hold">保存</span>
			</div>
		</div>
	</div>
	<input type="hidden" id="ctx" value="${ctx}"/>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/namespace.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/crossfit.cofig.js"></script>
	<script type="text/javascript">
		$(function(){
			(new crossfit.cfer.config.configList()).init();
		});
	</script>
</body>
</html>