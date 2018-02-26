<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>课程管理</title>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/css/course.css">
	<style>
		.course-type-select{
			width:200px;
			height:40px;
			position:absolute;
			top:18%;
			left:20%;
			z-index:5;
		}
	</style>
</head>
<body>
	<div class="course">
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
					<li class="cur"><a href="${ctx}/courseContent">课程管理</a></li>
				</c:if>
				<c:if test="${menuVO.notification}">
					<li><a href="${ctx}/notification">推送管理</a></li>
				</c:if>
				<c:if test="${menuVO.role || menuVO.coach || menuVO.supporter
				|| menuVO.courseType || menuVO.coursePlan || menuVO.config}">
					<li>
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
									<li><a href="${ctx}/setting/config">配置</a></li>
								</c:if>
							</ul>
						</div>
					</li>
				</c:if>
				<li><a href="${ctx}/logout">退出</a></li>
			</ul>
		</div>
		<div class="course-type-select">
			<select id="courseType" class="form-control">
				<c:forEach items="${courseTypes}" var="courseType">
					<option value="${courseType.id}" <c:if test="${defaultCourseTypeId eq courseType.id}">selected</c:if>>${courseType.name}</option>
				</c:forEach>
			</select>
		</div>
		<input type="hidden" id="currentMonth-addDate" value="${addDate}"/>
		<div class="zx-calender-square"></div>
	</div>
	<input type="hidden" id="defaultCourseTypeId" value="${defaultCourseTypeId}"/>
	<input type="hidden" id="defaultDate" value="${defaultDate}"/>
	<input type="hidden" id="ctx" value="${ctx}"/>
	<script src="${ctx}/resources/js/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script src="${ctx}/resources/js/zx-calender-square.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="${ctx}/resources/js/namespace.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/crossfit.courseContent.js"></script>
	<script type="text/javascript">
		$(function(){
			(new crossfit.cfer.course.courseContent()).init();
		});
	</script>
</body>
</html>