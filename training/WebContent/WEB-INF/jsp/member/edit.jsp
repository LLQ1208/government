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
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap.css" />
	 <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/css/edit.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugins/diyUpload/css/webuploader.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugins/diyUpload/css/diyUpload.css">
</head>
<body>
	<div class="editTop">
		<div class="header">
			<%--<span class="littleLogo fl"><img src="${ctx}/resources/images/littleLogo.png" alt="" /></span>--%>
			<ul class="nav fr clearfix">
				<%--<li><a href="${ctx}/wod/queryWodList">首页</a></li>--%>
				<%--<c:if test="${menuVO.member}">--%>
					<%--<li class="cur"><a href="${ctx}/member">会员管理</a></li>--%>
				<%--</c:if>--%>
				<%--<c:if test="${menuVO.courseOrder}">--%>
					<%--<li><a href="${ctx}/courseOrder">约课管理</a></li>--%>
				<%--</c:if>--%>
				<%--<c:if test="${menuVO.courseContent}">--%>
					<%--<li><a href="${ctx}/courseContent">课程管理</a></li>--%>
				<%--</c:if>--%>
				<%--<c:if test="${menuVO.notification}">--%>
					<%--<li><a href="${ctx}/notification">推送管理</a></li>--%>
				<%--</c:if>--%>
				<%--<c:if test="${menuVO.role || menuVO.coach || menuVO.supporter--%>
				<%--|| menuVO.courseType || menuVO.coursePlan || menuVO.config}">--%>
					<%--<li>--%>
						<%--<div class="dropdown nav-dropdown">--%>
						<%--<span class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">设置--%>
							<%--<span class="caret"></span>--%>
						<%--</span>--%>
							<%--<ul class="dropdown-menu">--%>
								<%--<c:if test="${menuVO.role}">--%>
									<%--<li><a href="${ctx}/setting/role">员工权限管理</a></li>--%>
								<%--</c:if>--%>
								<%--<c:if test="${menuVO.coach}">--%>
									<%--<li><a href="${ctx}/setting/coach">教练管理</a></li>--%>
								<%--</c:if>--%>
								<%--<c:if test="${menuVO.supporter}">--%>
									<%--<li><a href="${ctx}/setting/supporter">员工管理</a></li>--%>
								<%--</c:if>--%>
								<%--<c:if test="${menuVO.courseType}">--%>
									<%--<li><a href="${ctx}/setting/courseType">课程类型管理</a></li>--%>
								<%--</c:if>--%>
								<%--<c:if test="${menuVO.coursePlan}">--%>
									<%--<li><a href="${ctx}/setting/coursePlan">课程安排</a></li>--%>
								<%--</c:if>--%>
								<%--<c:if test="${menuVO.config}">--%>
									<%--<li><a href="${ctx}/setting/config">配置</a></li>--%>
								<%--</c:if>--%>
							<%--</ul>--%>
						<%--</div>--%>
					<%--</li>--%>
				<%--</c:if>--%>
				<%--<li><a href="${ctx}/logout">退出</a></li>--%>
			</ul>
		</div>
	</div>
	<div class="content">
		<div class="row item">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">手机号</div>
				<div class="col-sm-9">
					<div class="col-sm-10">
						<input type="text" id="phone" value="${member.phone}" readonly="readonly" class="srk fl member-item">
					</div>
				</div>
			</div>
			<div class="col-sm-12 text-center tip" id="phone-tip"></div>
		</div>
		<div class="row item">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">会员类型</div>
				<div class="col-sm-9">
					<div class="col-sm-10">
						<ul class="cards clearfix fl" id="memberType">
							<c:choose>
								<c:when test="${memberBox.memberType eq 1}">
									<li class="cur monthCard" data-value="1"></li>
									<li class="timeCard" data-value="2"></li>
								</c:when>
								<c:otherwise>
									<li class="monthCard" data-value="1"></li>
									<li class="cur timeCard" data-value="2"></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="row item memberTime <c:if test='${memberBox.memberType eq 2}'>hidden</c:if>">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">会员时间</div>
				<div class="col-sm-9">
					<div class="col-sm-12">
						<div class="input-append date form_datetime fl" >
			                <input size="26" id="memberStartTime" type="text" value="${memberBox.memberStartTimeFormat}" readonly>
			                <span class="add-on"><i class="icon-th"></i></span>
			            </div>
			            <span class="fl dashs" >-</span>
			            <div class="input-append date form_datetime fl" >
			                <input size="26" id="memberEndTime" type="text" value="${memberBox.memberEndTimeFormat}" readonly>
			                <span class="add-on"><i class="icon-th"></i></span>
			            </div>
		            </div>
				</div>
			</div>
		</div>
		<div class="row item cardTime cardTime-days <c:if test='${memberBox.memberType eq 1}'>hidden</c:if>">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">有效天数</div>
				<div class="col-sm-9">
					<div class="col-sm-6">
						<div class="fl num">
							<span class="fl minus">-</span>
							<c:if test="${memberBox.memberType eq 1}">
								<input type="text" id="dayLimit" value="50" class="fl">
							</c:if>
							<c:if test="${memberBox.memberType eq 2}">
								<input type="text" id="dayLimit" value="${memberBox.memberDays}" class="fl">
							</c:if>
							<span class="fl plus">+</span>
						</div>
					</div>
					<span class="member-item-util fl">天</span>
				</div>
			</div>
		</div>
		<div class="row item cardTime cardTime-totals <c:if test='${memberBox.memberType eq 1}'>hidden</c:if>">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">次数</div>
				<div class="col-sm-9">
					<div class="col-sm-6">
						<div class="fl num">
							<span class="fl minus">-</span>
							<c:if test="${memberBox.memberType eq 1}">
								<input type="text" id="memberRemainNum" value="15"  class="fl">
							</c:if>
							<c:if test="${memberBox.memberType eq 2}">
								<input type="text" id="memberRemainNum" value="${memberBox.memberRemainNum}"  class="fl">
							</c:if>
							<span class="fl plus">+</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row item">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">可预约课程</div>
				<div class="col-sm-9" style="padding-top:12px;padding-left:30px;">
					<c:forEach items="${courseTypes}" var="ct">
						<input type="checkbox" name="courseType" value="${ct.id}" <c:if test="${ct.selected eq 1}">checked="checked"</c:if>> ${ct.name}
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="btns">
			<span class="hold" id="update">保存</span>
			<span class="cancel" id="cancel">取消</span>
		</div>
	</div>
	<input type="hidden" id="id" value="${member.id}"/>
	<input type="hidden" id="ctx" value="${ctx}"/>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/namespace.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/crossfit.member.js"></script>
	<script type="text/javascript">
		$(function(){
			(new crossfit.cfer.member.edit()).init();
		});
	</script>
</body>
</html>