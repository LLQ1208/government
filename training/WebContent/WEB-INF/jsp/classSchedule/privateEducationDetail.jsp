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
    <title>课程表</title>
    <link rel="stylesheet" href="${ctx}/resources/css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/fullcalendar.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/fullcalendar.print.min.css" media='print'>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/cycle-schedule.css">
</head>
<body>
<div class="content-box">
<jsp:include page="../menu.jsp" flush="false" >
    <jsp:param value="private-schedule" name="menuName"/>
    <jsp:param value="4" name="group"/>
</jsp:include>
    <div class="main-content">
<div class="schedule">
    <div id="bread" class="clearfix">
        <span class="breadFirst">课程表</span>
        <span class="breadSecond"> > </span>
        <span>私教课表</span>
    </div>
    <div class="week-box">
        <input type="hidden" id="coachId" value="${coachId}">
        <input type="hidden" id="coachType" value="${coachType}">
        <select id="boxId" >
            <c:forEach items="${boxList}" var="box">
                <option value="${box.id}">${box.name}</option>
            </c:forEach>
        </select>
        <div id='calendar'></div>
    </div>
    <!--弹框-->
    <div class="add-tip">
        <div class="add-mask"></div>
        <div class="add-box">
            <!--<p class="time-line">9:00-10:00</p>-->
            <div class="time-box clearfix">
                <div class="start-time fl">
                    <select id="start-hours">
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                    </select>
                    <select id="start-minute">
                        <option value="0">00</option>
                        <option value="1">15</option>
                        <option value="2">30</option>
                        <option value="3">45</option>
                    </select>
                </div>
                <div class="fl">--</div>
                <div class="end-time fl">
                    <select id="end-hours">
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                    </select>
                    <select id="end-minute">
                        <option value="0">00</option>
                        <option value="1">15</option>
                        <option value="2">30</option>
                        <option value="3">45</option>
                    </select>
                </div>
            </div>
            <div class="select-block">
                <input type="number" class="number" value="10" id="studentNum">
                <p class="one-title">人数上限</p>
            </div>
            <div class="btn-box clearfix">
                <a href="javascript:;" class="cancel fl" >取消</a>
                <a href="javascript:;" style="margin-left: 12px;" class="delete fl">删除</a>
                <a href="javascript:;" class="sure fr">确定</a>
            </div>
        </div>
        <input type="hidden" id="editDate" value=""/>
        <input type="hidden" id="editCourseId" value=""/>
    </div>

</div>
<input type="hidden" id="ctx" value="${ctx}"/>
    </div>
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/moment.min.js"></script>
<script src="${ctx}/resources/js/new-js/fullcalendar.min.js"></script>
<script src="${ctx}/resources/js/new-js/zh-cn.js"></script>
<script src="${ctx}/resources/js/new-js/page/privateEducationDetail.js"></script>
</html>