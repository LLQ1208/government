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
									<li class="act"><a href="${ctx}/setting/courseType">课程类型管理</a></li>
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
			<div class="searchLine clearfix">
				<form class="searchInput fl"><span class="glass"></span>
			    	<input type="text" id="keyword" value="">
			    </form>
			    <span class="fl searchBtn">搜索</span>
			    <a href="${ctx}/setting/courseType/add" class="btn-c fr">添加</a>
			</div>
			<div style="height:450px;">
				<table class="table table-responsive">
					<thead>
						<tr>
							<td width="35%">名称</td>
							<td width="40%">课程等级</td>
							<td width="25%" align="center">操作</td>
						</tr>
					</thead>
					<tbody id="courseType-list">
						<c:forEach items="${pager.list}" var="item">
							<tr>
								<td>${item.name}</td>
								<td>
									<c:forEach items="${item.levelList}" var="level">
										${level},
									</c:forEach>
								</td>
								<td>
									<a data-id="${item.id}" class="btn-b fr delete">删除</a>
									<a href="${ctx}/setting/courseType/edit/${item.id}" class="btn-b fr edit">编辑</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<input id="totalRow" type="hidden" value="${pager.totalRow}"/>
			<div class="M-box3" id="pagination-bottom"></div>
		</div>
		<!-- 弹窗开始 -->
		<div class="popBox" id="member-info-box"></div>
	</div>
	<input type="hidden" id="ctx" value="${ctx}"/>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/namespace.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/crossfit.courseType.js"></script>
	<script type="text/javascript">
		$(function(){
			(new crossfit.cfer.courseType.courseTypeList()).init();
		});
	</script>
</body>
</html>