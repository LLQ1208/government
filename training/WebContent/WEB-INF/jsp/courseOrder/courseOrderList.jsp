<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>约课管理</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugins/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugins/bootstrap-dialog/css/bootstrap-dialog.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugins/ionicons/css/ionicons.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/css/class.css">
	<style>
		#courseType{
			border:1px solid #5d5d63;
			color: #eaebeb; 
			font-size:16px; 
			background-color:#02040D;
			margin-left:30px;
			width:180px;
			height:40px;
		}
	</style>
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
					<li class="cur"><a href="${ctx}/courseOrder">约课管理</a></li>
				</c:if>
				<c:if test="${menuVO.courseContent}">
					<li><a href="${ctx}/courseContent">课程管理</a></li>
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
		<div class="content">
			<div class="firstLine clearfix">
				<div class="timeSelect fl clearfix">
					<span class="fl minus">-</span>
					<p class="stime fl"><o id="date-str">2016/12/12</o>  星期三</p>
					<span class="fl plus">+</span>
				</div>
				<div class="fl" style="margin-left:30px;width:200px;height:40px;">
					<select id="courseType" class="form-control">
						<option value="0" selected="selected">全部课程</option>
						<c:forEach items="${courseTypes}" var="ct">
							<option value="${ct.id}">${ct.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="export fr" id="export"><img src="${ctx}/resources/images/export.png" alt="">导出</div>
			</div>
			<ul class="course" id="course-ul">
				<c:forEach items="${weekCourseMemberList}" var="item">
					<li class="cff">${item.name}
						<span>${fn:length(item.courseOrderList)}/
							<c:choose>
								<c:when test="${not empty item.course.peopleLimit && item.course.peopleLimit != 0}">
									${item.course.peopleLimit}
								</c:when>
								<c:otherwise>
									${defaultPeopleLimit}
								</c:otherwise>
							</c:choose>
						</span>
						<a data-courseid="${item.course.id}" class="btn-c reserve">预约</a>
						<a class="btn-c edit">编辑</a>
					</li>
					<li class="clearfix">
						<c:forEach items="${item.courseOrderList}" var="courseOrder" varStatus="status">
							<label><span class="name">${status.index+1}、${courseOrder.member.name}</span><span class="ion ion-close-round delete" data-courseid="${item.course.id}" data-memberid="${courseOrder.member.id}"></span></label>
						</c:forEach>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	
	<div class="modal" id="addOrderModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">预约</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="name">姓名</label>
							<div class="col-sm-8">
								<input id="name" class="form-control" placeholder="请输入姓名"/>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="save">添加</button>
				</div>
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
	<script type="text/javascript" src="${ctx}/resources/js/crossfit.courseOrder.js"></script>
	<script type="text/javascript">
		$(function(){
			(new crossfit.cfer.courseOrder.courseOrderList()).init();
		});
	</script>
</html>