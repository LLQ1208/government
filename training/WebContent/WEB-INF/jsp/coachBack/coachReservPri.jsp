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
    <title>wod</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/fullcalendar.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/fullcalendar.print.min.css" media='print'>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/coachReservPri.css">
    <script src="${ctx}/resources/js/new-js/jquery.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${ctx}/resources/js/new-js/moment.min.js"></script>
    <script src="${ctx}/resources/js/new-js/fullcalendar.min.js"></script>
    <script src="${ctx}/resources/js/new-js/zh-cn.js"></script>
    <script src="${ctx}/resources/js/new-js/page/coachReservPri.js"></script>
    <script src="${ctx}/resources/js/new-js/page/coachBackMenu.js"></script>
    <%
        request.setAttribute("vEnter", "\n");
        //奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
        //都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
    %>
    <style type="text/css">
        .sign-span{
            display: block;
            width: 60px;
            height: 20px;
            text-align: center;
            line-height: 20px;
            font-size: 14px;
            color: #fff;
            float: right;
            margin-top: 5px;
        }
        .color-orange{
            background: #f19149;
        }
        .color-toSign{
            background: #2196f3;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="box">

    <header class="nav">
        <div class="top clearfix">
            <div class="left fl clearfix">
                <div class="user fl">
                    <c:choose>
                        <c:when test="${img == '' || img==null}">
                            <img src="${ctx}/resources/images/new-image/default_member.png" class="photo fl" />
                        </c:when>
                        <c:otherwise>
                            <img src="${img}" class="photo fl" />
                        </c:otherwise>
                    </c:choose>
                    <span class="name fl">${name}</span>
                </div>
                <select class="choose fr">
                    <option value="1">CFer</option>
                    <option value="2">CFer后台</option>
                </select>
            </div>
            <a href="${ctx}/logout">
            <div class="right fr">
                退出
            </div>
            </a>
        </div>
        <div class="bottom">
            <ul class="list clearfix">
                <li class="listCommon " data-value="wod" href="${ctx}/coachWod/coachWodPage">训练计划</li>
                <li class="listCommon active" data-value="order" href="${ctx}/coachBackReser/reservationGroup">预约列表</li>
                <li class="listCommon" data-value="board"  href="${ctx}/coachBackBoard/coachBoard">白板</li>
                <li class="listCommon" data-value="ranking" href="${ctx}/coachBackRand/coachRank">排行榜</li>
                <li class="listCommon" data-value="analysis" href="${ctx}/coachBackAnalysis/coachAnalysis">数据分析</li>
            </ul>
        </div>
    </header>
</div>
<div class="box " style=" margin: 156px auto 0;">
    <div class="wodLeft fl clearfix">
        <select class="common wodOne" id="boxId">
            <c:forEach items="${boxList}" var="box">
                <option value="${box.id}">${box.name}</option>
            </c:forEach>
        </select>
        <p>训练馆</p>
        <select class="common wodOne" id="courseType">
            <option value="1" selected>私教课</option>
            <option value="2">团课</option>
        </select>
        <p>类型</p>
        <div class="date-box">
            <input size="16" type="text" value="" readonly class="common form_datetime" style="text-align: center;">
        </div>
        <p>日期</p>
        <select class="common wodOne" id="courseStatus">
            <option value="1">可预约</option>
            <option value="2">休息</option>
        </select>
        <p>状态</p>
    </div>
    <div class="wodRight fr clearfix schedule">
        <div class="time">
            <span class="timeLeft">${date}</span>
            <span class="timeRight"></span>
        </div>
        <input type="hidden" id="coachId" value="${coachId}">
        <input type="hidden" id="coachType" value="${coachType}">
        <div class="content" id="calendar">

        </div>
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
                    <p class="one-title">人数上限<span class="sign-span color-toSign" id="editSign">签到</span></p>
                </div>
                <div class="btn-box clearfix">
                    <a href="javascript:;" class="cancel fl" >取消</a>
                    <a href="javascript:;" style="margin-left: 12px;" class="delete fl">删除</a>
                    <a href="javascript:;" class="sure fr">确定</a>
                </div>
            </div>
            <input type="hidden" id="editDate" value=""/>
            <input type="hidden" id="editCourseId" value=""/>
            <input type="hidden" id="editReservNum" value=""/>
        </div>


        <div class="show-tip">
            <div class="add-mask"></div>
            <div class="add-box">
                <!--<p class="time-line">9:00-10:00</p>-->
                <div class="time-box clearfix" style="color: #666;">
                    <div class="start-time fl" id="showStartTime">
                    </div>
                    <div class="fl">--</div>
                    <div class="end-time fl" id="showEndTime">
                    </div>
                </div>
                <div class="select-block">
                    <input type="number" class="number" value="10" id="showStudentNum" disabled>
                    <p class="one-title">人数上限</p>
                </div>
                <div class="select-block">
                    <p class="yy-title">已预约人<span class="sign-span color-toSign" id="showSign">签到</span></p>
                    <hr style="margin-top: 1px;width: 60px;">
                </div>
                <div class="select-block" id="memberList">

                </div>
                <div class="btn-box clearfix">
                    <a href="javascript:;" class="cancel fl showCan" >取消</a>
                </div>
            </div>
        </div>
        <input type="hidden" id="showDate" value=""/>
        <input type="hidden" id="showCourseId" value=""/>
    </div>
    <!--弹框-->

</div>
<input type="hidden" id="ctx" value="${ctx}"/>
</body>
</html>