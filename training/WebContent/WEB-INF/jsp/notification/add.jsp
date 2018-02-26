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
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/plugins/bootstrap/css/bootstrap.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/css/editNotice.css">
	<%-- <link rel="stylesheet" type="text/css" href="${ctx}/resources/plugins/diyUpload/css/webuploader.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/plugins/diyUpload/css/diyUpload.css"> --%>
	<link rel="stylesheet" href="${ctx}/resources/plugins/font-awesome/css/font-awesome.css"/>
	<link href="${ctx}/resources/plugins/summernote/summernote.css" type="text/css" rel="stylesheet" />
	<style>
		#pic {
			border-radius: 0%;
			cursor: pointer;
		}
	</style>
	
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
	</div>
	<div class="content" style="padding-top:50px;">
		<div class="row">
			<form class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label" for="pushObject">推送对象</label>
					<div class="col-sm-2">
						<select id="pushObject" class="form-control">
							<option value="0">全部用户</option>
							<option value="1">月卡用户</option>
							<option value="2">次卡用户</option>
						</select>
					</div>
					<label class="col-sm-1 control-label" for="pushSex">推送性别</label>
					<div class="col-sm-2">
						<select id="pushSex" class="form-control">
							<option value="0">全部</option>
							<option value="1">男</option>
							<option value="2">女</option>
						</select>
					</div>
					<label class="col-sm-1 control-label" for="pushTime">推送时间</label>
					<div class="col-sm-2">
						<input id="pushTime" class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="title">标题</label>
					<div class="col-sm-8">
						<input id="title" class="form-control" placeholder="请输入标题"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="upload">封面图片</label>
					<div class="col-sm-8 ">
						<div class="photo">
							<img id="pic" src="" >
							<input id="upload" name="file" accept="image/*" type="file" placeholder="点击上传图片" style="display: none"/>
						</div>
					</div>
					<div class="col-sm-2 tip text-center" id="photo-tip"></div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="content">内容</label>
					<div class="col-sm-8">
						<textarea id="content"></textarea>
					</div>
				</div>
			</form>
		</div>
		<div class="btns">
			<span class="hold" id="hold">保存</span>
			<span class="cancel" id="cancel">取消</span>
		</div>
	</div>
	<input type="hidden" id="ctx" value="${ctx}"/>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
	<%-- <script type="text/javascript" src="${ctx}/resources/plugins/diyUpload/js/webuploader.html5only.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/plugins/diyUpload/js/diyUpload.js"></script> --%>
	<script type="text/javascript" src="${ctx}/resources/plugins/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${ctx}/resources/plugins/summernote/summernote.js"></script>
	<script type="text/javascript" src="${ctx}/resources/plugins/summernote/summernote-zh-CN.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/namespace.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/crossfit.notification.js"></script>
	<script type="text/javascript">
		$(function(){
			(new crossfit.cfer.notification.add()).init();
		});
	</script>
</body>
</html>