<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>编辑页</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bootstrap.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap-select.min.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/css/edit.css">
</head>
<body>
	<div class="editTop">
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
									<li class="act"><a href="${ctx}/setting/coursePlan">课程安排</a></li>
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
	</div>
	<c:choose>
		<c:when test="${fn:length(courseTypeList) eq 0}">
			<div class="content" style="margin-top:50px;">
				<div class="text-center" style="font-size:16px;">无可选课程类型，请先课程类型管理页添加课程类型，<a href="${ctx}/setting/coach" style="color:#ff0000;">点击跳转到课程类型管理</a></div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="content" style="margin-top:50px;">
				<div class="row item">
					<div class="col-sm-12 fl">
						<div class="col-sm-3 input-item text-center">课程类型</div>
						<select class="col-sm-8 selectpicker" id="courseType">
							<c:forEach items="${courseTypeList}" var="item">
								<option value="${item.id}" <c:if test="${selectedCourseType.id eq item.id}">selected="selected"</c:if>>${item.name}</option>
							</c:forEach>
					    </select>
					</div>
				</div>
				<div id="course-level">
					<c:if test="${selectedCourseType.hasLevel eq 1}">
						<div class="row item">
							<div class="col-sm-12 fl">
								<div class="col-sm-3 input-item text-center">课程级别</div>
								<select class="col-sm-8 selectpicker" id="courseLevel">
									<c:forEach items="${selectedCourseType.levelList}" var="level">
										<option value="${level}" <c:if test="${level eq coursePlan.courseLevel}">selected="selected"</c:if>>${level}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</c:if>
				</div>
				<div class="row item">
					<div class="col-sm-12 fl">
						<div class="col-sm-3 input-item text-center">课堂人数上限</div>
						<div class="col-sm-8">
							<input class="srk type-name" id="peopleLimit" type="number" min="1" value="${coursePlan.peopleLimit}">
						</div>
					</div>
				</div>
				<div class="row item">
					<div class="col-sm-12 fl">
						<div class="col-sm-3 input-item text-center">上课时间</div>
						<div class="col-sm-3">
							<input class="srk training-time" value="${coursePlan.startTime}" id="startTime"/>
						</div>
					    <div class="col-sm-1" style="font-size:16px;margin-top:12px;">-</div>
					    <div class="col-sm-3">
					    	<input class="srk training-time" value="${coursePlan.endTime}" id="endTime"/>
					    </div>
					</div>
					<div class="col-sm-12 text-center tip-red" id="time-tip"></div>
				</div>
				<div class="btns">
					<span class="hold" id="update">保存</span>
					<span class="delete" id="delete">删除</span>
					<span class="cancel" id="cancel">取消</span>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<input type="hidden" id="id" value="${coursePlan.id}"/>
	<input type="hidden" id="ctx" value="${ctx}"/>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/namespace.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/crossfit.coursePlan.js"></script>
	<script type="text/javascript">
		$(function(){
			(new crossfit.cfer.coursePlan.edit()).init();
		});
	</script>
</body>
</html>