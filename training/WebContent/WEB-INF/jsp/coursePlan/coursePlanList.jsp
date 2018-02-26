<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>约课管理</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/plugins/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" href="${ctx}/resources/css/coursePlan.css">
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
		<div class="content">
			<div style="margin-top:75px;">
				<ul class="course">
					<li class="cff">星期一</li>
					<li class="clearfix">
						<div class="row">
							<div class="col-sm-2 text-center">
								<div class="course-plan-item add" data-week="1">
									<i class="fa fa-plus"></i>
								</div>
							</div>
							<c:forEach items="${weekPlanMap.monday}" var="coursePlan">
								<div class="col-sm-2 text-center">
									<div class="course-plan-item item" data-id="${coursePlan.id}">
										<span>${coursePlan.courseType.name}</span><br>
										<c:if test="${not empty coursePlan.courseLevel}">
											<span>${coursePlan.courseLevel}</span><br>
										</c:if>
										<span>${coursePlan.peopleLimit} 人</span><br>
										<span>${coursePlan.startTime}-${coursePlan.endTime}</span>
									</div>
								</div>
							</c:forEach>
						</div>
					</li>
					<li class="cff">星期二</li>
					<li class="clearfix">
						<div class="row">
							<div class="col-sm-2 text-center">
								<div class="course-plan-item add" data-week="2">
									<i class="fa fa-plus"></i>
								</div>
							</div>
							<c:forEach items="${weekPlanMap.tuesday}" var="coursePlan">
								<div class="col-sm-2 text-center">
									<div class="course-plan-item item" data-id="${coursePlan.id}">
										<span>${coursePlan.courseType.name}</span><br>
										<c:if test="${not empty coursePlan.courseLevel}">
											<span>${coursePlan.courseLevel}</span><br>
										</c:if>
										<span>${coursePlan.peopleLimit} 人</span><br>
										<span>${coursePlan.startTime}-${coursePlan.endTime}</span>
									</div>
								</div>
							</c:forEach>
						</div>
					</li>
					<li class="cff">星期三</li>
					<li class="clearfix">
						<div class="row">
							<div class="col-sm-2 text-center">
								<div class="course-plan-item add" data-week="3">
									<i class="fa fa-plus"></i>
								</div>
							</div>
							<c:forEach items="${weekPlanMap.wednesday}" var="coursePlan">
								<div class="col-sm-2 text-center">
									<div class="course-plan-item item" data-id="${coursePlan.id}">
										<span>${coursePlan.courseType.name}</span><br>
										<c:if test="${not empty coursePlan.courseLevel}">
											<span>${coursePlan.courseLevel}</span><br>
										</c:if>
										<span>${coursePlan.peopleLimit} 人</span><br>
										<span>${coursePlan.startTime}-${coursePlan.endTime}</span>
									</div>
								</div>
							</c:forEach>
						</div>
					</li>
					<li class="cff">星期四</li>
					<li class="clearfix">
						<div class="row">
							<div class="col-sm-2 text-center">
								<div class="course-plan-item add" data-week="4">
									<i class="fa fa-plus"></i>
								</div>
							</div>
							<c:forEach items="${weekPlanMap.thursday}" var="coursePlan">
								<div class="col-sm-2 text-center">
									<div class="course-plan-item item" data-id="${coursePlan.id}">
										<span>${coursePlan.courseType.name}</span><br>
										<c:if test="${not empty coursePlan.courseLevel}">
											<span>${coursePlan.courseLevel}</span><br>
										</c:if>
										<span>${coursePlan.peopleLimit} 人</span><br>
										<span>${coursePlan.startTime}-${coursePlan.endTime}</span>
									</div>
								</div>
							</c:forEach>
						</div>
					</li>
					<li class="cff">星期五</li>
					<li class="clearfix">
						<div class="row">
							<div class="col-sm-2 text-center">
								<div class="course-plan-item add" data-week="5">
									<i class="fa fa-plus"></i>
								</div>
							</div>
							<c:forEach items="${weekPlanMap.friday}" var="coursePlan">
								<div class="col-sm-2 text-center">
									<div class="course-plan-item item" data-id="${coursePlan.id}">
										<span>${coursePlan.courseType.name}</span><br>
										<c:if test="${not empty coursePlan.courseLevel}">
											<span>${coursePlan.courseLevel}</span><br>
										</c:if>
										<span>${coursePlan.peopleLimit} 人</span><br>
										<span>${coursePlan.startTime}-${coursePlan.endTime}</span>
									</div>
								</div>
							</c:forEach>
						</div>
					</li>
					<li class="cff">星期六</li>
					<li class="clearfix">
						<div class="row">
							<div class="col-sm-2 text-center">
								<div class="course-plan-item add" data-week="6">
									<i class="fa fa-plus"></i>
								</div>
							</div>
							<c:forEach items="${weekPlanMap.saturday}" var="coursePlan">
								<div class="col-sm-2 text-center">
									<div class="course-plan-item item" data-id="${coursePlan.id}">
										<span>${coursePlan.courseType.name}</span><br>
										<c:if test="${not empty coursePlan.courseLevel}">
											<span>${coursePlan.courseLevel}</span><br>
										</c:if>
										<span>${coursePlan.peopleLimit} 人</span><br>
										<span>${coursePlan.startTime}-${coursePlan.endTime}</span>
									</div>
								</div>
							</c:forEach>
						</div>
					</li>
					<li class="cff">星期天</li>
					<li class="clearfix">
						<div class="row">
							<div class="col-sm-2 text-center">
								<div class="course-plan-item add" data-week="7">
									<i class="fa fa-plus"></i>
								</div>
							</div>
							<c:forEach items="${weekPlanMap.sunday}" var="coursePlan">
								<div class="col-sm-2 text-center">
									<div class="course-plan-item item" data-id="${coursePlan.id}">
										<span>${coursePlan.courseType.name}</span><br>
										<c:if test="${not empty coursePlan.courseLevel}">
											<span>${coursePlan.courseLevel}</span><br>
										</c:if>
										<span>${coursePlan.peopleLimit} 人</span><br>
										<span>${coursePlan.startTime}-${coursePlan.endTime}</span>
									</div>
								</div>
							</c:forEach>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
<input type="hidden" id="ctx" value="${ctx}"/>
<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.nicescroll.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/namespace.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/crossfit.coursePlan.js"></script>
<script type="text/javascript">
	$(function(){
		(new crossfit.cfer.coursePlan.coursePlanList()).init();
	});
</script>
</html>