<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>添加课程页</title>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap-select.min.css" />
	<link rel="stylesheet" href="${ctx}/resources/plugins/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/css/addCourse.css">
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
	</div>
	<c:choose>
		<c:when test="${fn:length(coachList) eq 0}">
			<div class="content">
				<div class="text-center" style="font-size:16px;">无可选教练，请先到教练管理页添加教练信息，<a href="${ctx}/setting/coach" style="color:#ff0000;">点击跳转到教练管理</a></div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="content">
				<div class="row item">
					<div class="col-sm-12">
						<span class="plan-date"><i class="fa fa-calendar"></i> 课程日期：${date}</span>
						<input type="hidden" id="planDate" value="${date}"/>
					</div>
				</div>
				<div class="row item">
					<div class="col-sm-6 fl">
						<span class="publish"><i class="fa fa-unlock"></i> 公布时间</span>
						<div class="selects1">
							<select class="selectpicker" id="openTime">
								<option value="00:00" <c:if test="${courseContent.openTimeStr eq '00:00'}">selected="selected"</c:if>>00:00</option>
								<option value="00:15" <c:if test="${courseContent.openTimeStr eq '00:15'}">selected="selected"</c:if>>00:15</option>
								<option value="00:30" <c:if test="${courseContent.openTimeStr eq '00:30'}">selected="selected"</c:if>>00:30</option>
								<option value="00:45" <c:if test="${courseContent.openTimeStr eq '00:45'}">selected="selected"</c:if>>00:45</option>
								<option value="01:00" <c:if test="${courseContent.openTimeStr eq '01:00'}">selected="selected"</c:if>>01:00</option>
								<option value="01:15" <c:if test="${courseContent.openTimeStr eq '01:15'}">selected="selected"</c:if>>01:15</option>
								<option value="01:30" <c:if test="${courseContent.openTimeStr eq '01:30'}">selected="selected"</c:if>>01:30</option>
								<option value="01:45" <c:if test="${courseContent.openTimeStr eq '01:45'}">selected="selected"</c:if>>01:45</option>
								<option value="02:00" <c:if test="${courseContent.openTimeStr eq '02:00'}">selected="selected"</c:if>>02:00</option>
								<option value="02:15" <c:if test="${courseContent.openTimeStr eq '02:15'}">selected="selected"</c:if>>02:15</option>
								<option value="02:30" <c:if test="${courseContent.openTimeStr eq '02:30'}">selected="selected"</c:if>>02:30</option>
								<option value="02:45" <c:if test="${courseContent.openTimeStr eq '02:45'}">selected="selected"</c:if>>02:45</option>
								<option value="03:00" <c:if test="${courseContent.openTimeStr eq '03:00'}">selected="selected"</c:if>>03:00</option>
								<option value="03:15" <c:if test="${courseContent.openTimeStr eq '03:15'}">selected="selected"</c:if>>03:15</option>
								<option value="03:30" <c:if test="${courseContent.openTimeStr eq '03:30'}">selected="selected"</c:if>>03:30</option>
								<option value="03:45" <c:if test="${courseContent.openTimeStr eq '03:45'}">selected="selected"</c:if>>03:45</option>
								<option value="04:00" <c:if test="${courseContent.openTimeStr eq '04:00'}">selected="selected"</c:if>>04:00</option>
								<option value="04:15" <c:if test="${courseContent.openTimeStr eq '04:15'}">selected="selected"</c:if>>04:15</option>
								<option value="04:30" <c:if test="${courseContent.openTimeStr eq '04:30'}">selected="selected"</c:if>>04:30</option>
							    <option value="04:45" <c:if test="${courseContent.openTimeStr eq '04:45'}">selected="selected"</c:if>>04:45</option>
							    <option value="05:00" <c:if test="${courseContent.openTimeStr eq '05:00'}">selected="selected"</c:if>>05:00</option>
								<option value="05:15" <c:if test="${courseContent.openTimeStr eq '05:15'}">selected="selected"</c:if>>05:15</option>
								<option value="05:30" <c:if test="${courseContent.openTimeStr eq '05:30'}">selected="selected"</c:if>>05:30</option>
								<option value="05:45" <c:if test="${courseContent.openTimeStr eq '05:45'}">selected="selected"</c:if>>05:45</option>
								<option value="06:00" <c:if test="${courseContent.openTimeStr eq '06:00'}">selected="selected"</c:if>>06:00</option>
						    </select>
						</div>
					</div>
					<div class="col-sm-6 fl">
						<span class="coach"><i class="fa fa-user"></i> 教练</span>
						<div class="selects1">
							<select class="selectpicker" id="coach">
								<c:forEach items="${coachList}" var="item">
									<option value="${item.id}" <c:if test="${courseContent.coachId eq item.id}">selected="selected"</c:if>>${item.name}</option>
								</c:forEach>
						    </select>
						</div>
					</div>
				</div>
				<div class="content-main">
					<c:forEach items="${courseContent.contentList}" var="content">
						<div class="row content-item">
							<div class="col-sm-10">
								<div class="row item">
									<input type="text" class="caption fl" value="${content.title}">
									<div class="selects2">
										<select class="selectpicker time">
										<c:forEach items="${timeMap}" var="item">
											<option value="${item.key}" <c:if test="${content.time eq item.key}">selected="selected"</c:if>>${item.value}</option>
										</c:forEach>
										</select>
									</div>
								</div>
								<div class="row item">
									<textarea rows="4" class="details">${content.detail}</textarea>
								</div>
								<div class="row item">
									<textarea rows="3" class="remark">${content.remark}</textarea>
								</div>
							</div>
							<div class="col-sm-2">
								<div class="remove-item">
									<a class="btn btn-remove"><i class="fa fa-minus"></i></a>
								</div>
							</div>
							<div class="col-sm-12 tip"></div>
						</div>
					</c:forEach>
				</div>
				<div class="row">
					<div class="add-item">
						<a class="btn btn-add" id="add-item-btn"><i class="fa fa-plus"></i></a>
					</div>
				</div>
				<div class="row tip" id="main-tip" style="padding-top:30px;"></div>
				<div class="btns">
					<span class="hold" id="update">保存</span>
					<span class="cancel" id="cancel">取消</span>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	<input type="hidden" id="id" value="${courseContent.id}"/>
	<input type="hidden" id="courseTypeId" value="${courseTypeId}"/>
	<input type="hidden" id="ctx" value="${ctx}"/>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery.nicescroll.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/namespace.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/crossfit.courseContent.js"></script>
	<script type="text/javascript">
		$(function(){
			(new crossfit.cfer.course.addOrEditCourseContent()).init();
		});
	</script>
</body>
</html>