<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/private-schedule.css">
</head>
<body>
<div class="content-box">
<jsp:include page="../menu.jsp" flush="false" >
    <jsp:param value="private-schedule" name="menuName"/>
    <jsp:param value="4" name="group"/>
</jsp:include>
    <div class="main-content">
<div class="private-box">
    <div id="bread" class="clearfix">
        <span class="breadFirst">课程表</span>
        <span class="breadSecond"> > </span>
        <span>私教课表</span>
    </div>
    <%--<div class="date-box">--%>
        <%--<div class="input-append date form_datetime" >--%>
            <%--<input size="26" type="text" readonly>--%>
            <%--<span class="add-on" style="display: none;"><i class="icon-th"></i></span>--%>
        <%--</div>--%>
        <%--<p class="now-date"></p>--%>
    <%--</div>--%>
    <div class="coach-list">
        <ul class="clearfix">
            <c:forEach items="${coachList}" var="coach">
                <li class="coachLi clearfix">
                    <c:choose>
                        <c:when test="${coach.avatar != null && coach.avatar != ''}">
                            <img class="fl" src="${coach.avatar}" alt="">
                        </c:when>
                        <c:otherwise>
                            <img class="fl" src="${ctx}/resources/images/new-image/no_pic.png" alt="">
                        </c:otherwise>
                    </c:choose>

                    <p class="fl">${coach.name}</p>
                    <input type="hidden" value="${coach.id}" class="coachId">
                    <input type="hidden" value="${coach.level}" class="coachType">
                </li>
            </c:forEach>
        </ul>
    </div>
    <input type="hidden" id="ctx" value="${ctx}"/>
</div>
    </div>
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/resources/js/new-js/page/private-schedule.js"></script>
</html>