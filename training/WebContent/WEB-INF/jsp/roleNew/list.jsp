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
	<link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/jurisdiction.css">
</head>
<body>
<div class="content-box">
	<jsp:include page="../menu.jsp" flush="false" >
		<jsp:param value="roleNew" name="menuName"/>
		<jsp:param value="6" name="group"/>
	</jsp:include>
	<div class="main-content" >
		<div class="box">
			<div id="bread" class="clearfix">
				<span class="breadFirst">设置</span>
				<span class="breadSecond"> > </span>
				<span class="breadThird"> 权限设置</span>
			</div>
			<c:forEach items="${boxList}" var="rolesNew">
			<div class="boxCommon clearfix">
				<p class="title">${rolesNew.name}</p>
				<c:forEach items="${rolesNew.roleNewList}" var="roleList">
					<div class="set clearfix">
						<div class="editor clearfix">
							<div class="top clearfix">
								<p class="name fl">${roleList.name}</p>
								<div class="edit fr">
									<span class="pic fl"></span>
									<span class="editting fl" onclick="editRole(${roleList.id});">编辑</span>
								</div>
							</div>
							<div class="bottom clearfix">
								<p class="type">
									<c:forEach items="${roleList.menus}" var="menusList" varStatus="idxStatus">
										<c:choose>
											<c:when test="${menusList.parentId eq 0}">
												<c:if test="${menusList.selected eq 1}" >
													${menusList.name}-
												</c:if>
											</c:when>
											<c:otherwise>
												<c:if test="${menusList.selected eq 1}" >
													${menusList.name}
													<c:if test="${!idxStatus.last}">
														、
													</c:if>
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
				<c:forEach items="${rolesNew.roleNewList}" var="roleList">
				<div class="pop-box-bg" style="display: none;" id="${roleList.id}">
					<div class="choose-pop">
					<div class="top clearfix">
						<p class="name fl">${roleList.name}</p>
						<div class="confirm fr" onclick="sureRole(${roleList.id});">确定</div>
						<div class="cancel fr" onclick="cencelRole(${roleList.id});">取消</div>
					</div>
					<div class="bottom clearfix">
						<div class="leftChoose fl">
							<c:forEach items="${roleList.menus}" var="menusList" varStatus="idxStatus">
							<c:choose>
							<c:when test="${menusList.parentId eq 0}">
							<c:if test="${!idxStatus.last && !idxStatus.first}">
						</div>
						</c:if>
						<div class="chooseCommon">
							<p class="choosename">${menusList.name}</p><input type="checkbox" name="menu" value="${menusList.id}" style="display: none" checked="checked" />
							</c:when>
							<c:otherwise>
								<div class="option fl" style="margin-left: 24px;">
									<div class="optionType">
										<input type="checkbox" name="menu" onclick="getCheck(${menusList.parentId});" value="${menusList.id}" <c:if test="${menusList.selected eq 1}">checked="checked"</c:if> class="square">${menusList.name}
									</div>
								</div>
							</c:otherwise>
							</c:choose>
							<c:if test="${idxStatus.last}">
						</div>
						</c:if>
						</c:forEach>
					</div>
				</div>
				</div>
			</div>
			</c:forEach>
		</div>
		</c:forEach>
	</div>
</div>
<input type="hidden" id="ctx" value="${ctx}"/>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/icheck.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/roleNew.js"></script>
<script src="${ctx}/resources/js/new-js/page/jqPaginator.min.js"></script>
<%--<script src="${ctx}/resources/js/new-js/page/myPage.js"></script>--%>
</html>