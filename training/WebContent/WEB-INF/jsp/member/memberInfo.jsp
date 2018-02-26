<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<div class="photo">
	<c:choose>
		<c:when test="${not empty memberBox.member.avatar}">
			<img src="${memberBox.member.avatar}">
		</c:when>
		<c:otherwise>
			<img src="${ctx}/resources/images/members.png">
		</c:otherwise>
	</c:choose>
</div>
<div class="nature">
	<c:if test="${memberBox.memberType eq 1}">月卡用户</c:if>
	<c:if test="${memberBox.memberType eq 2}">次卡用户</c:if>
</div>
<div class="name">
	<c:choose>
		<c:when test="${memberBox.member.sex eq 0}">
			<img src="${ctx}/resources/images/man.png" alt="">
		</c:when>
		<c:otherwise>
			<img src="${ctx}/resources/images/woman.png" alt="">
		</c:otherwise>
	</c:choose>
	
	<span>${memberBox.member.name}</span>
</div>
<ul class="datas clearfix">
	<li><img src="${ctx}/resources/images/icon2.png" alt=""><span class="phone">${memberBox.member.phone}</span></li>
	<li><img src="${ctx}/resources/images/icon6.png" alt=""><span class="email">${memberBox.member.email}</span></li>
	<li><img src="${ctx}/resources/images/icon5.png" alt=""><span class="pinCode">${memberBox.member.pinCode}</span></li>
	<li><img src="${ctx}/resources/images/icon4.png" alt="">
		<span class="timeLimit">
			<c:if test="${memberBox.memberType eq 1}">
				<fmt:formatDate value="${memberBox.memberStartTime}" pattern="yyyy/MM/dd"/>-<fmt:formatDate value="${memberBox.memberEndTime}" pattern="yyyy/MM/dd"/>
			</c:if>
			<c:if test="${memberBox.memberType eq 2}">
				${memberBox.memberRemainNum}
			</c:if>
		</span>
	</li>
</ul>
<ul class="userCode clearfix" >
	<li ><img src="${ctx}/resources/images/icon1.png" alt="">CFer用户名：<span class="userName">${user.userName}</span></li>
	<li ><img src="${ctx}/resources/images/icon7.png" alt="">CFer密码：<span class="password">${user.password}</span></li>
</ul>
<a href="${ctx}/member/edit/${memberBox.memberId}" class="edit"><img src="${ctx}/resources/images/edit.png" alt=""></a>