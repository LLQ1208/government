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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/week.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/cycle-schedule.css">
</head>
<body>
<div class="content-box">
<jsp:include page="../menu.jsp" flush="false" >
    <jsp:param value="cycle-schedule" name="menuName"/>
    <jsp:param value="4" name="group"/>
</jsp:include>
    <div class="main-content">
<form:form method="post"  action="${ctx}/classSchedule/classSchedule"  id="wodPage" commandName="wodPage">
<div class="schedule">
    <div id="bread" class="clearfix">
        <span class="breadFirst">WOD</span>
        <span class="breadSecond"> > </span>
        <span>循环课表</span>
    </div>
    <div class="filter-box clearfix">
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
            <p class="one-title">课程类型</p>
        </div>
        <div class="one-filter fl">
            <select class="coach filters" id="coach">
                <option value>全部</option>
                <c:forEach items="${coachList}" var="coach">
                    <c:choose>
                        <c:when test="${coach.id == coachId}">
                            <option value="${coach.id}" selected>${coach.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${coach.id}">${coach.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <p class="one-title">教练</p>
        </div>
    </div>
    <ul class="schedule-classify clearfix">
        <li class="fl">课程列表<span></span></li>
        <li class="fl active">循环列表<span></span></li>
    </ul>
    <div class="week-box">
        <div class="top-block clearfix">
            <div class="top-left-block fl">
                <span class="glyphicon glyphicon-chevron-left ht-rili-leftarr fl"></span>
                <span class="glyphicon glyphicon-chevron-right ht-rili-rightarr fl"></span>
                <span class="back-today fl">回到今天</span>
            </div>
            <ul class="top-middle-block fl clearfix">
                <li class="fl">day<span></span></li>
                <li class="current fl">week<span></span></li>
            </ul>
            <div class="top-right-block ht-rili-date fr">
                <strong>11月</strong>
                <span>23</span>
                <span>-</span>
                <strong>11月</strong>
                <span>29</span>
            </div>
        </div>
        <div class="week-calendar">
            <div class="calendar-box demo-box"></div>
            <div class="sheet clearfix">
                <ul id="monday"></ul>
                <ul id="tuesday"></ul>
                <ul id="wednesday"></ul>
                <ul id="thursday"></ul>
                <ul id="friday"></ul>
                <ul id="saturday"></ul>
                <ul id="sunday"></ul>

                <%--<div class="drag-block">--%>
                    <%--<p>06:00 CF_Fitness</p>--%>
                    <%--<p>左浩</p>--%>
                    <%--<span>09/12</span>--%>
                    <%--<div class="delete-box">删除</div>--%>
                <%--</div>--%>

            </div>
            <div class="legend-box">
                <ul class="clearfix">
                    <li><span class="fit"></span>CF_Fitness</li>
                    <li><span class="yoga"></span>Yoga</li>
                </ul>
            </div>
            <div class="times">
                <ul>
                    <li>05</li>
                    <li>06</li>
                    <li>07</li>
                    <li>08</li>
                    <li>09</li>
                    <li>10</li>
                    <li>11</li>
                    <li>12</li>
                    <li>13</li>
                    <li>14</li>
                    <li>15</li>
                    <li>16</li>
                    <li>17</li>
                    <li>18</li>
                    <li>19</li>
                    <li>20</li>
                    <li>21</li>
                    <li>22</li>
                    <li>23</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="day-box">
        <div class="top-block clearfix">
            <div class="top-left-block fl">
                <span class="glyphicon glyphicon-chevron-left ht-day-leftarr fl"></span>
                <span class="glyphicon glyphicon-chevron-right ht-day-rightarr fl"></span>
                <span class="back-today-day fl">回到今天</span>
            </div>
            <ul class="top-middle-block fl clearfix">
                <li class="current fl">day<span></span></li>
                <li class="fl">week<span></span></li>
            </ul>
            <div class="top-right-block day-date fr">
                <strong>11月</strong>
                <span>23</span>
            </div>
        </div>
        <div class="day-calendar">
            <div class="day-sheet">
                <ul id="day"></ul>
                <div class="drag-block">
                    <p>06:00 CF_Fitness</p>
                    <p>左浩</p>
                    <span>09/12</span>
                </div>
            </div>
            <div class="legend-box">
                <ul class="clearfix">
                    <li><span class="fit"></span>CF_Fitness</li>
                    <li><span class="yoga"></span>Yoga</li>
                </ul>
            </div>
            <div class="times">
                <ul>
                    <li>05</li>
                    <li>06</li>
                    <li>07</li>
                    <li>08</li>
                    <li>09</li>
                    <li>10</li>
                    <li>11</li>
                    <li>12</li>
                    <li>13</li>
                    <li>14</li>
                    <li>15</li>
                    <li>16</li>
                    <li>17</li>
                    <li>18</li>
                    <li>19</li>
                    <li>20</li>
                    <li>21</li>
                    <li>22</li>
                    <li>23</li>
                </ul>
            </div>
        </div>
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
                <select class="schedule-type-s filters-s" id="editCourseType">
                    <option value>全部</option>
                    <c:forEach items="${courseTypeList}" var="courseType">
                        <option value="${courseType.id}">${courseType.name}</option>
                    </c:forEach>
                </select>
                <p class="one-title">课程类型</p>
                <select class="coach-s filters-s" id="editCoach">
                    <option value>全部</option>
                    <c:forEach items="${coachList}" var="coach">
                        <option value="${coach.id}">${coach.name}</option>
                    </c:forEach>
                </select>
                <p class="one-title">教练</p>
                <input type="number" class="number" value="10" id="studentNum">
                <p class="one-title">人数上限</p>
            </div>
            <div class="btn-box clearfix">
                <a href="javascript:;" class="deleted fl" style="margin-left: 11px;display: none">删除</a>
                <a href="javascript:;" class="cancel fl" style="margin-left: 11px;">取消</a>
                <a href="javascript:;" class="sure fr">确定</a>
            </div>
        </div>
        <input type="hidden" value="" id="hideWeek"/>
        <input type="hidden" value="" id="hideCourseId"/>
    </div>
    <input type="hidden" id="ctx" value="${ctx}"/>
    <input type="text" id="weekOrDay" value="${weekOrDay}" style="display: none"/>
    <button type="submit" id="queryButn" />
</div>
</form:form>
</div>
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/week.js"></script>
<script src="${ctx}/resources/js/new-js/page/cycle-schedule.js"></script>
</html>