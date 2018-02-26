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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/analysis/member-statistic.css">
</head>
<body>
<div class="content-box">
<jsp:include page="../menu.jsp" flush="false" >
    <jsp:param value="memberAnalysis" name="menuName"/>
    <jsp:param value="5" name="group"/>
</jsp:include>
    <div class="main-content">
    <div id="bread" class="clearfix">
        <span class="breadFirst">统计分析</span>
        <span class="breadSecond"> > </span>
        <span>会员统计</span>
    </div>
    <div class="statistic-box">
        <ul class="statistic-classify clearfix">
            <li class="fl active">上课统计<span></span></li>
            <li class="fl">增减会员<span></span></li>
        </ul>
        <div class="position-block clearfix">
            <div class="position-box fl">
                <select id="boxId" class="position-select" onchange="getSource()">
                    <option value="-1"  selected = "selected">全部</option>
                    <c:forEach items="${boxList}" var="box">
                        <option value="${box.id}">${box.name}</option>
                    </c:forEach>
                </select>
                <p class="one-title">位置</p>
            </div>
            <div class="position-box fl" id="courseType">
            <select id="courseTypeId" class="position-select" onchange="getSource()">
                <option value="-1"  selected = "selected">全部</option>
                <c:forEach items="${courseTypeList}" var="courseType">
                    <option value="${courseType.id}">${courseType.name}</option>
                </c:forEach>
            </select>
            <p class="one-title">类型</p>
        </div>
        </div>
        <div class="chart-box">
            <div class="filter-box">
                <ul class="clearfix">
                    <li class="active">周</li>
                    <li>月</li>
                    <li>季</li>
                    <li>自定义时间</li>
                </ul>
                <div class="date-box  clearfix">
                    <span class="glyphicon glyphicon-chevron-left ht-rili-leftarr fl" id="leftBtn"></span>
                    <p class="fl" id="titleTime"></p>
                    <span class="glyphicon glyphicon-chevron-right ht-rili-rightarr fl" id="rightBtn"></span>
                </div>
                <div class="custom-date">
                    <input size="16" type="text" value="" readonly class="form_datetime_start">
                    <span>-</span>
                    <input size="16" type="text" value="" readonly class="form_datetime_end">
                </div>
            </div>
            <div class="chart">
                <div id="container"></div>
            </div>
        </div>
    </div>
</div>
    <input type="hidden" id="ctx" value="${ctx}" />
    <input name="beginTime" id="beginTime" style="display: none" value=""/>
    <input name="endTime" id="endTime"  style="display: none" />
    <input name="searchType" id="searchType"  style="display: none" value="0"/>
    <input name="lastOrNextWeek" id="lastOrNextWeek"  value="0" style="display: none"/>
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/resources/js/new-js/highcharts.js"></script>
<script src="${ctx}/resources/js/new-js/page/member-statistic.js"></script>
</html>