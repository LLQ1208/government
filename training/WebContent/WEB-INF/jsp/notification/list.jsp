<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>推送管理</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/plugins/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/pagination.css" media="screen">
	<link rel="stylesheet" href="${ctx}/resources/css/member.css">
	<style>
		.pic {
		    width: 110px;
		    height: 83px;
		    overflow: hidden;
		    border-radius: 5px;
		}
		.title{
			font-size:20px;
			font-weight: bold;
			color:#fff;
			margin-top:5px;
		}
		.notic-content{
			color: #ffffff;
			width: 450px;
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
			margin-top:30px;
			font-size:16px;
		}
		.pushTime{
			color: #ffffff;
		}
		.heat{
			color: #C5160F;
			margin-top: 32px;
		}
		.notic-item{
			padding-bottom: 10px;
			border-bottom: 1px solid #1F0807;
			padding-top: 10px;
		}
		.cover{
			border-radius: 5px;
			overflow: hidden;
		}
		.cover img{
			height: 85px;
			width: 110px;
		}
		.memberBanner {
			min-height: 1450px;
		}
		.content {
			top: 14%;
		}

		.M-box3{
			margin-top:10px;
		}
	</style>
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
					<li class="cur"><a href="${ctx}/notification">推送管理</a></li>
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
		<div class="content">
			<div class="searchLine clearfix">
				<form class="searchInput fl"><span class="glass"></span>
			    	<input type="text" id="keyword" value="">
			    </form>
			    <span class="fl searchBtn">搜索</span>
			    <a href="${ctx}/notification/add" class="btn-c fr">添加</a>
			</div>
			<div id="member-list">
				<div class="row member">
					<c:forEach items="${pager.list}" var="item">
						<div class="col-sm-12 notic-item">
							<div class="col-sm-2">
								<div class="cover"><img src="${item.cover}"></div>
							</div>
							<div class="col-sm-8">
								<div class="title">${item.title}</div>
								<div class="notic-content">${item.content}</div>
							</div>
							<div class="col-sm-2">
								<div class="pushTime pull-right">
									<fmt:formatDate value="${item.pushTime}" pattern="yyyy/MM/dd"/>
								</div>
								<div class="heat pull-right">
									阅读：${item.heat}
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<input id="totalRow" type="hidden" value="${pager.totalRow}"/>
			<div class="M-box3 pull-right" id="pagination-bottom"></div>
		</div>
		<!-- 弹窗开始 -->
		<div class="popBox" id="member-info-box"></div>
	</div>
	<input type="hidden" id="ctx" value="${ctx}"/>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/namespace.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/crossfit.notification.js"></script>
	<script type="text/javascript">
		$(function(){
			(new crossfit.cfer.notification.notificationList()).init();
		});
	</script>
</body>
</html>