<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<c:forEach items="${weekCourseMemberList}" var="item">
	<li class="cff">${item.name}
		<span>${fn:length(item.courseOrderList)}/
			<c:choose>
				<c:when test="${not empty item.course.peopleLimit && item.course.peopleLimit != 0}">
					${item.course.peopleLimit}
				</c:when>
				<c:otherwise>
					${defaultPeopleLimit}
				</c:otherwise>
			</c:choose>
		</span>
		<a data-courseid="${item.course.id}" class="btn-c reserve">预约</a>
		<a class="btn-c edit">编辑</a>
	</li>
	<li class="clearfix">
		<c:forEach items="${item.courseOrderList}" var="courseOrder" varStatus="status">
			<label><span class="name ">${status.index+1}、${courseOrder.member.name}</span><span class="ion ion-close-round delete" data-courseid="${item.course.id}" data-memberid="${courseOrder.member.id}"></span></label>
		</c:forEach>
	</li>
</c:forEach>