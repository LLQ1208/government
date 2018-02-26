<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<div class="row member">
	<c:forEach items="${pager.list}" var="item">
		<div class="col-sm-6">
			<div class="list borderTop">
				<div class="pic fl">
					<c:choose>
						<c:when test="${not empty item.avatar}">
							<img src="${item.avatar}">
						</c:when>
						<c:otherwise>
							<img src="${ctx}/resources/images/members.png">
						</c:otherwise>
					</c:choose>
				</div>
				<ul class="txt fl">
					<li class="name">${item.name}</li>
					<li class="phone">${item.phone}</li>
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