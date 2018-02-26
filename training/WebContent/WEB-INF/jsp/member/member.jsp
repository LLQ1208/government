<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>会员管理</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/plugins/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/pagination.css" media="screen">
	<link rel="stylesheet" href="${ctx}/resources/css/member.css">
</head>
<body>
<div class="content-box">
	<jsp:include page="../menu.jsp" flush="false" >
		<jsp:param value="memberList" name="menuName"/>
		<jsp:param value="2" name="group"/>
	</jsp:include>
	<div class="main-content">
		<%--<div class="header">--%>
			<%--&lt;%&ndash;<span class="littleLogo fl">--%>
				<%--<img src="${ctx}/resources/images/littleLogo.png" alt="" />--%>
			<%--</span>&ndash;%&gt;--%>
			<%--&lt;%&ndash;<ul class="nav fr clearfix">&ndash;%&gt;--%>
				<%--&lt;%&ndash;<li><a href="${ctx}/index">首页</a></li>&ndash;%&gt;--%>
				<%--&lt;%&ndash;<c:if test="${menuVO.member}">&ndash;%&gt;--%>
					<%--&lt;%&ndash;<li class="cur"><a href="${ctx}/member">会员管理</a></li>&ndash;%&gt;--%>
				<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
				<%--&lt;%&ndash;<c:if test="${menuVO.courseOrder}">&ndash;%&gt;--%>
					<%--&lt;%&ndash;<li><a href="${ctx}/courseOrder">约课管理</a></li>&ndash;%&gt;--%>
				<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
				<%--&lt;%&ndash;<c:if test="${menuVO.courseContent}">&ndash;%&gt;--%>
					<%--&lt;%&ndash;<li><a href="${ctx}/courseContent">课程管理</a></li>&ndash;%&gt;--%>
				<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
				<%--&lt;%&ndash;<c:if test="${menuVO.notification}">&ndash;%&gt;--%>
					<%--&lt;%&ndash;<li><a href="${ctx}/notification">推送管理</a></li>&ndash;%&gt;--%>
				<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
				<%--&lt;%&ndash;<c:if test="${menuVO.role || menuVO.coach || menuVO.supporter&ndash;%&gt;--%>
				<%--&lt;%&ndash;|| menuVO.courseType || menuVO.coursePlan || menuVO.config}">&ndash;%&gt;--%>
					<%--&lt;%&ndash;<li>&ndash;%&gt;--%>
						<%--&lt;%&ndash;<div class="dropdown nav-dropdown">&ndash;%&gt;--%>
						<%--&lt;%&ndash;<span class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">设置&ndash;%&gt;--%>
							<%--&lt;%&ndash;<span class="caret"></span>&ndash;%&gt;--%>
						<%--&lt;%&ndash;</span>&ndash;%&gt;--%>
							<%--&lt;%&ndash;<ul class="dropdown-menu">&ndash;%&gt;--%>
								<%--&lt;%&ndash;<c:if test="${menuVO.role}">&ndash;%&gt;--%>
									<%--&lt;%&ndash;<li><a href="${ctx}/setting/role">员工权限管理</a></li>&ndash;%&gt;--%>
								<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
								<%--&lt;%&ndash;<c:if test="${menuVO.coach}">&ndash;%&gt;--%>
									<%--&lt;%&ndash;<li><a href="${ctx}/setting/coach">教练管理</a></li>&ndash;%&gt;--%>
								<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
								<%--&lt;%&ndash;<c:if test="${menuVO.supporter}">&ndash;%&gt;--%>
									<%--&lt;%&ndash;<li><a href="${ctx}/setting/supporter">员工管理</a></li>&ndash;%&gt;--%>
								<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
								<%--&lt;%&ndash;<c:if test="${menuVO.courseType}">&ndash;%&gt;--%>
									<%--&lt;%&ndash;<li><a href="${ctx}/setting/courseType">课程类型管理</a></li>&ndash;%&gt;--%>
								<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
								<%--&lt;%&ndash;<c:if test="${menuVO.coursePlan}">&ndash;%&gt;--%>
									<%--&lt;%&ndash;<li><a href="${ctx}/setting/coursePlan">课程安排</a></li>&ndash;%&gt;--%>
								<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
								<%--&lt;%&ndash;<c:if test="${menuVO.config}">&ndash;%&gt;--%>
									<%--&lt;%&ndash;<li><a href="${ctx}/setting/config">配置</a></li>&ndash;%&gt;--%>
								<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
							<%--&lt;%&ndash;</ul>&ndash;%&gt;--%>
						<%--&lt;%&ndash;</div>&ndash;%&gt;--%>
					<%--&lt;%&ndash;</li>&ndash;%&gt;--%>
				<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
				<%--&lt;%&ndash;<li><a href="${ctx}/logout">退出</a></li>&ndash;%&gt;--%>
			<%--&lt;%&ndash;</ul>&ndash;%&gt;--%>
		<%--</div>--%>
		<div class="content">
			<div class="searchLine clearfix">
				<form class="searchInput fl"><span class="glass"></span>
			    	<input type="text" id="keyword" value="">
			    </form>
			    <span class="fl searchBtn">搜索</span>
			    <a href="${ctx}/member/add" class="btn-c fr">添加</a>
			</div>
			<div id="member-list">
				<div class="row member">
					<c:forEach items="${pager.list}" var="item">
						<div class="col-sm-6">
							<div class="list borderTop  member-list" data-id="${item.memberId}">
								<div class="pic fl">
									<c:choose>
										<c:when test="${not empty item.member.avatar}">
											<img src="${item.member.avatar}">
										</c:when>
										<c:otherwise>
											<img src="${ctx}/resources/images/members.png">
										</c:otherwise>
									</c:choose>
								</div>
								<ul class="txt fl">
									<li class="<c:choose><c:when test='${item.timeStatus eq 3}'>name-timeout</c:when><c:otherwise>name</c:otherwise></c:choose>">
										${item.member.name}
										<c:choose>
											<c:when test="${item.memberType eq 1 && item.timeStatus eq 2}"><span class="label label-danger pull-right">即将过期</span></c:when>
											<c:when test="${item.memberType eq 2 && item.timeStatus eq 2}"><span class="label label-primary pull-right">即将过期</span></c:when>
											<c:when test="${item.timeStatus eq 3}"><span class="label label-default pull-right">已过期</span></c:when>
										</c:choose>
									</li>
									<li class="phone">${item.member.phone}</li>
									<li class="time">
										<c:if test="${item.memberType eq 1}">
											<fmt:formatDate value="${item.memberStartTime}" pattern="yyyy/MM/dd"/>-<fmt:formatDate value="${item.memberEndTime}" pattern="yyyy/MM/dd"/>
										</c:if>
										<c:if test="${item.memberType eq 2}">
											${item.memberRemainNum}次
										</c:if>
									</li>
								</ul>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<input id="totalRow" type="hidden" value="${pager.totalRow}"/>
			<div class="M-box3 fr" id="pagination-bottom" style="margin-top:20px;"></div>
		</div>
		<!-- 弹窗开始 -->
		<div class="popBox" id="member-info-box"></div>
	</div>
</div>
	<input type="hidden" id="ctx" value="${ctx}"/>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/namespace.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/crossfit.member.js"></script>
	<script type="text/javascript">
		$(function(){
		    changeWin();
			(new crossfit.cfer.member.memberList()).init();
		});
	</script>
</body>
</html>