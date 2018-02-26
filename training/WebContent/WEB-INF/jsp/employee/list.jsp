<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Title</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css">
	<link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css" />
	<link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-dialog.css">
	<link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
	<link rel="stylesheet" href="${ctx}/resources/css/new-css/font-awesome.css">
	<link rel="stylesheet" href="${ctx}/resources/css/new-css/minimal/blue.css">
	<link rel="stylesheet" href="${ctx}/resources/css/new-css/red.css">
	<link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/myPage.css">
	<link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/employee.css">
</head>
<body>
<div class="content-box">
	<jsp:include page="../menu.jsp" flush="false" >
		<jsp:param value="employee" name="menuName"/>
		<jsp:param value="6" name="group"/>
	</jsp:include>
	<div class="main-content">
		<div class="box" style="width: 100%;">
			<div id="bread" class="clearfix">
				<span class="breadFirst">设置</span>
				<span class="breadSecond"> > </span>
				<span class="breadThird"> 员工管理</span>
			</div>
			<!--教练和客服-->

			<c:forEach items="${boxes}" var="boxList">
				<div class="addBox clearfix">
					<div class="addTitle clearfix">
						<p class="titleName fl">${boxList.name}</p>
						<div class="addRole fr">添加角色</div>
						<input type="hidden" class="hBoxId" value="${boxList.id}" />
					</div>

					<c:forEach items="${boxList.roleNewList}" var="roleList">
						<div class="addCoach customer clearfix">
							<div class="coachLine fl clearfix">
								<div class="name fl">
									<%--<input type="checkbox" class="fl">--%>
									<span class="txt fl" id="${roleList.id}-${roleList.allowDelete}">${roleList.name}</span>
									<span class="downIcon fl"></span>
									<input class="allowDelete" value="${roleList.allowDelete}" type="hidden" />
									<input class="roleId" value="${roleList.id}" type="hidden" />
									<input class="roleName" value="${roleList.name}" type="hidden" />
									<input class="rBoxId" value="${boxList.id}" type="hidden" />
								</div>
								<div class="phone fl">
									手机号
								</div>
								<div class="email fl">
									邮箱
								</div>
								<div class="addBtn fr">添加</div>
								<input class="roleIdAdd" value="${roleList.id}" type="hidden" />
								<input type="hidden" class="eBoxId" value="${boxList.id}" />
								<input class="eallowDelete" value="${roleList.allowDelete}" type="hidden" />
								<input class="itemSize"  type="hidden" value="${roleList.employees.size()}">
							</div>
							<div class="coachContent">
								<c:forEach items="${roleList.employees}" var="employeeList" varStatus="idxStatus">
									<div class="coachCommon clearfix">
										<div class="name fl">
											<%--<input type="checkbox" class="fl">--%>
											<span class="txt fl">${employeeList.name}</span>
											<input class="employeeId" value="${employeeList.id}" type="hidden" />
											<input class="employeeRoleId" value="${roleList.id}" type="hidden" />
											<input class="callowDelete" value="${roleList.allowDelete}" type="hidden" />
										</div>
										<div class="phone fl">
												${employeeList.phone}
										</div>
										<div class="email fl">
												${employeeList.email}
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:forEach>

			<!--添加角色-->
			<div class="add-role-box">
				<div class="add-role-block">
					<div class="add-role-title">添加角色</div>
					<div class="add-role-content">
						<input type="text" id="roleName">
						<input type="hidden" id="roleId" value="0"/>
						<input type="hidden" id="boxId" value="0"/>
						<p>角色名称</p>
					</div>
					<div class="add-role-button clearfix">
						<a href="javascript:;" class="delete-btn fl">删除</a>
						<a href="javascript:;" class="save-btn fr">保存</a>
						<a href="javascript:;" class="cancel-btn fr">取消</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="ctx" value="${ctx}"/>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/icheck.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/employee.js"></script>
<script src="${ctx}/resources/js/new-js/page/jqPaginator.min.js"></script>
<%--<script src="${ctx}/resources/js/new-js/page/myPage.js"></script>--%>
</html>