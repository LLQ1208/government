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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/list-schedule.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/cycle-schedule.css">

</head>
<body>
<div class="content-box">
<jsp:include page="../menu.jsp" flush="false" >
    <jsp:param value="cycle-schedule" name="menuName"/>
    <jsp:param value="4" name="group"/>
</jsp:include>
    <div class="main-content">
<div class="schedule">
    <div id="bread" class="clearfix">
        <span class="breadFirst">课程表</span>
        <span class="breadSecond"> > </span>
        <span>循环课表</span>
    </div>
    <div class="filter-box clearfix" >
        <div class="one-filter fl">
            <select class="training-gym filters" id="boxId">
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
            <p class="one-title">训练馆</p>
        </div>
        <div class="one-filter fl">
            <select class="schedule-type filters" id="courseType">
                <option value>全部</option>
                <c:forEach items="${courseTypeList}" var="courseType">
                    <option value="${courseType.id}">${courseType.name}</option>
                </c:forEach>
            </select>
            <p class="one-title">课程类型</p>
        </div>
        <div class="one-filter fl">
            <select class="coach filters" id="coach">
                <option value>全部</option>
                <c:forEach items="${coachList}" var="coach">
                    <option value="${coach.id}">${coach.name}</option>
                </c:forEach>
            </select>
            <p class="one-title">教练</p>
        </div>
        <span class="add-btn" style="display: none;">添加</span>
    </div>
    <div class="date-box" style="display:none">
        <input size="16" type="text" value="" readonly class="form_datetime" id="listDate"     style="cursor: pointer;">
    </div>
    <ul class="schedule-classify clearfix">
        <li class="fl " name="listClass">课程列表<span></span></li>
        <li class="fl active" name="weekClass">循环列表<span></span></li>
    </ul>
    <div class="week-box" >
        <div id='calendar'></div>
        <div class="legend-box">
            <ul class="clearfix courseColor">

            </ul>
        </div>
    </div>
    <div class="list-box" style="display: none;">
        <div class="list-head clearfix">
            <p class="class-name fl">课程名称</p>
            <p class="class-time fl">时间</p>
            <p class="class-num fl">上课人数</p>
            <p class="class-coach fl">教练</p>
            <p class="class-address fl">训练馆</p>
            <p class="class-handle fl">操作</p>
        </div>
        <ul class="class-list" id="classListUl">
            <input type="hidden" class="liCourseId">
        </ul>
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
                    <select id="end-minute">
                        <option value="0">00</option>
                        <option value="1">15</option>
                        <option value="2">30</option>
                        <option value="3">45</option>
                    </select>
                </div>
            </div>
            <div class="select-block">
                <select class="schedule-type-s filters-s" id="editCourseType">
                    <c:choose>
                        <c:when test="${null == courseTypeList || courseTypeList.size() < 1}">
                            <option value>无</option>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${courseTypeList}" var="courseType">
                                <option value="${courseType.id}">${courseType.name}</option>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select>
                <p class="one-title">课程类型</p>
                <select class="coach-s filters-s" id="editCoach">
                    <c:choose>
                        <c:when test="${null == coachList || coachList.size() < 1}">
                            <option value>无</option>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${coachList}" var="coach">
                                <option value="${coach.id}">${coach.name}</option>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select>
                <p class="one-title">教练</p>
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
    <input type="hidden" id="coursetTypeIds" value=""/>
    </div>
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/moment.min.js"></script>
<script src="${ctx}/resources/js/new-js/fullcalendar.min.js"></script>
<script src="${ctx}/resources/js/new-js/zh-cn.js"></script>
<script src="${ctx}/resources/js/new-js/page/Ncycle-schedule.js"></script>
</html>