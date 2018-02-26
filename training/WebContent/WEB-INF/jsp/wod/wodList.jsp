<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="khl" value="("/>
<c:set var="khr" value=")"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>WOD</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/calendar.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/calendar-page.css">
    <style type="text/css">
        .view{
            cursor: pointer;
            position: absolute;
            top: 0;
            left: 0;
        }
    </style>
</head>
<body style="background: #eee">
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="calendar" name="menuName"/>
        <jsp:param value="1" name="group"/>
    </jsp:include>
    <div class="main-content">
        <input type="hidden" value="${sessionBoxId}" id="sessionBoxId">
        <input type="hidden" value="${sessionCourseType}" id="sessionCourseType">
        <div class="calendar">
        <input name="beginDate" id="beginDate" value="${beginDate}" style="display: none"/>
        <input name="endDate" id="endDate" value="${endDate}" style="display: none"/>
        <input name="weekType" id="weekType" value="${weekType}" style="display: none"/>
        <input name="lastOrNextWeek" id="lastOrNextWeek" value="0" style="display: none"/>
        <div class="top-block clearfix">
            <div class="top-left-block fl">
                <span class="glyphicon glyphicon-chevron-left ht-rili-leftarr fl"></span>
                <span class="glyphicon glyphicon-chevron-right ht-rili-rightarr fl"></span>
                <span class="back-today fl">回到今天</span>
            </div>
            <div class="top-title ht-rili-date fl" id="titleT">${title}</div>
            <div class="top-right-block fr">
                <ul class="clearfix">
                    <c:forEach items="${weekTypeMap}"  var="weekIndex">
                        <c:choose>
                            <c:when test="${weekType eq weekIndex.key}">
                                <li class="active" myAttr="${weekIndex.key}">${weekIndex.value}<span></span></li>
                            </c:when>
                            <c:otherwise>
                                <li myAttr="${weekIndex.key}">${weekIndex.value}<span></span></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="select-block clearfix">
            <div class="left-select fl">
                    <select id="boxId" class="name-selectpicker" onchange="selectToQuery()">
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
                <p>场馆名称</p>
            </div>
            <div class="left-select fl">
                <select id="courseTypeId" class="type-selectpicker" onchange="selectToQuery()">
                    <c:forEach items="${courseTypeList}" var="courseType">
                        <c:choose>
                            <c:when test="${courseType.id == courseTypeId}">
                                <option value="${courseType.id}" selected>${courseType.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${courseType.id}">${courseType.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <p>课程类型</p>
            </div>
        </div>
        <div class="calendar-box demo-box">
            <div class="ht-rili-querybox"></div>
            <div class="ht-rili-head">
                <div class="ht-rili-th">周一</div>
                <div class="ht-rili-th">周二</div>
                <div class="ht-rili-th">周三</div>
                <div class="ht-rili-th">周四</div>
                <div class="ht-rili-th">周五</div>
                <div class="ht-rili-th">周六</div>
                <div class="ht-rili-th">周日</div>
            </div>
        <div class="ht-rili-body">
        </div>

        <div class="pop-box">
            <strong class="close"></strong>
            <p class="pop-title">HappyHump Day!!</p>
            <div class="pop-block">
                <p class="little-title">Warm-up</p>
                <p class="little-content">Dynamic stretch</p>
            </div>
            <div class="pop-block">
                <p class="little-title">Gymnastics</p>
                <p class="little-content">Handstand Pushups</p>
            </div>
            <div class="pop-block">
                <p class="little-title">WOD</p>
                <div class="del-content">
                    <p>1:Hetcon (AMRAPRound an Reps)</p>
                    <span>6 mins AMRAP</span>
                    <span>-8 SDHPs</span>
                    <span>-8 TTBs</span>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="ctx" value="${ctx}"/>
        </div>
    </div>
</div>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<%--<script src="${ctx}/resources/js/new-js/calendar.js"></script>--%>
<script src="${ctx}/resources/js/new-js/page/calendar-page.js"></script>
<script type="text/javascript">
</script>
</body>
</html>