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
	<link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/css/edit.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugins/diyUpload/css/webuploader.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugins/diyUpload/css/diyUpload.css">
	<style type="text/css">
		.bootstrap-select:not([class*=span]):not([class*=col-]):not([class*=form-control]):not(.input-group-btn) {
			width: 194px; }

		.bootstrap-select.btn-group:not(.input-group-btn), .bootstrap-select.btn-group[class*=span] {
			margin-bottom: 0; }
	</style>
</head>
<body>
	<div class="editTop">
		<div class="header">
			<%--<span class="littleLogo fl"><img src="${ctx}/resources/images/littleLogo.png" alt="" /></span>--%>
			<ul class="nav fr clearfix">
				<%--<li><a href="${ctx}/index">首页</a></li>--%>
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
		<%--<div class="photo">
			<img id="pic" src="" >
			<input id="upload" name="file" accept="image/*" type="file" style="display: none"/>
		</div>
		<div class="tip text-center" id="photo-tip"></div>
		<div class="nature clearfix text-center">
			<ul class="selects fr sex" id="sex">
				<li class="cur" data-value="0">男</li>
				<li data-value="1">女</li>
			</ul>
		</div>--%>
		<%--<div class="row item" style="">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">姓名</div>
				<div class="col-sm-9">
					<div class="col-sm-10">
						<input type="text" id="name" value="" class="srk fl member-item">
					</div>
				</div>
			</div>
			<div class="col-sm-12 text-center tip" id="name-tip"></div>
		</div>--%>
		<div class="row item">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">手机号</div>
				<div class="col-sm-9">
					<div class="col-sm-10">
						<input type="text" id="phone" value="" class="srk fl member-item">
					</div>
				</div>
			</div>
			<div class="col-sm-12 text-center tip" id="phone-tip"></div>
		</div>
		<%--<div class="row item">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">身高</div>
				<div class="col-sm-9">
					<div class="col-sm-10">
						<input type="text" id="height" value="" class="srk fl member-item">
					</div>
					<div class="col-sm-2 member-item-util">cm</div>
				</div>
			</div>
			<div class="col-sm-12 text-center tip" id="height-tip"></div>
		</div>
		<div class="row item">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">体重</div>
				<div class="col-sm-9">
					<div class="col-sm-10">
						<input type="text" id="weight" value="" class="srk fl member-item">
					</div>
					<div class="col-sm-2 member-item-util">kg</div>
				</div>
			</div>
			<div class="col-sm-12 text-center tip" id="weight-tip"></div>
		</div>--%>
		<%--<div class="row item">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">证件号码</div>
				<div class="col-sm-9">
					<div class="col-sm-10">
						<input type="text" id="pinCode" value="" class="srk fl member-item">
					</div>
				</div>
			</div>
			<div class="col-sm-12 text-center tip" id="pinCode-tip"></div>
		</div>--%>
		<%--<div class="row item">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">电子邮件</div>
				<div class="col-sm-9">
					<div class="col-sm-10">
						<input type="text" id="email" value="" class="srk fl member-item">
					</div>
				</div>
			</div>
			<div class="col-sm-12 text-center tip" id="email-tip"></div>
		</div>--%>
		<div class="row item">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">会员类型</div>
				<div class="col-sm-9">
					<div class="col-sm-10">
						<ul class="cards clearfix fl" id="memberType">
							<li class="cur monthCard" data-value="1"></li>
							<li class="timeCard" data-value="2"></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="row item memberTime">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">会员时间</div>
				<div class="col-sm-9">
					<div class="col-sm-12">
						<div class="input-append date form_datetime fl" >
			                <input size="26" id="memberStartTime" type="text" value="${currentDate}" readonly>
			                <span class="add-on"><i class="icon-th"></i></span>
			            </div>
			            <span class="fl dashs" >-</span>
			            <div class="input-append date form_datetime fl" >
			                <input size="26" id="memberEndTime" type="text" value="${currentDate}" readonly>
			                <span class="add-on"><i class="icon-th"></i></span>
			            </div>
		            </div>
				</div>
			</div>
		</div>
		<div class="row item memberTime">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">训练馆</div>
				<div class="col-sm-9">
					<div class="col-sm-12">
						<select id="boxId" class="name-selectpicker" style="height: 34px;width: 140px;margin-top: 6px;">
							<c:forEach items="${boxList}" var="box">
								<c:choose>
									<c:when test="${box.id == boxId}">
										<option value="${box.id}" selected>${box.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${box.id}">${box.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="row item cardTime cardTime-days hidden">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">有效天数</div>
				<div class="col-sm-9">
					<div class="col-sm-6">
						<div class="fl num">
							<span class="fl minus">-</span>
							<input type="text" id="dayLimit" value="50" class="fl">
							<span class="fl plus">+</span>
						</div>
					</div>
					<span class="member-item-util fl">天</span>
				</div>
			</div>
			<div class="col-sm-12 text-center tip" id="dayLimit-tip"></div>
		</div>
		<div class="row item cardTime cardTime-totals hidden">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">次数</div>
				<div class="col-sm-9">
					<div class="col-sm-6">
						<div class="fl num">
							<span class="fl minus">-</span>
							<input type="text" id="memberRemainNum" value="15"  class="fl">
							<span class="fl plus">+</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-12 text-center tip" id="memberRemainNum-tip"></div>
		</div>
		<%--<div class="row item">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">CFer用户名</div>
				<div class="col-sm-9">
					<div class="col-sm-10">
						<input type="text" id="userName" value="" class="srk fl member-item">
					</div>
				</div>
			</div>
			<div class="col-sm-12 text-center tip" id="userName-tip"></div>
		</div>
		<div class="row item">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">CFer密码</div>
				<div class="col-sm-9">
					<div class="col-sm-10">
						<input type="text" id="password" value="" class="srk fl member-item">
					</div>
				</div>
			</div>
			<div class="col-sm-12 text-center tip" id="password-tip"></div>
		</div>--%>
		<div class="row item">
			<div class="col-sm-12 fl">
				<div class="col-sm-3 input-item text-center">可预约课程</div>
				<div class="col-sm-9" style="padding-top:12px;padding-left:30px;">
					<c:forEach items="${courseTypes}" var="ct">
						<input type="checkbox" name="courseType" value="${ct.id}"> ${ct.name}
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="btns">
			<span class="hold" id="hold">保存</span>
			<span class="cancel" id="cancel">取消</span>
		</div>
	</div>
	<input type="hidden" id="ctx" value="${ctx}"/>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/plugins/diyUpload/js/webuploader.html5only.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/plugins/diyUpload/js/diyUpload.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
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