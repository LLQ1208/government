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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/myPage.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/coachGroup.css">
    <script src="${ctx}/resources/js/new-js/jquery.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${ctx}/resources/js/new-js/highcharts.js"></script>
    <script src="${ctx}/resources/js/new-js/jqPaginator.min.js"></script>
    <script src="${ctx}/resources/js/new-js/myPage.js"></script>
    <script src="${ctx}/resources/js/new-js/page/coachGroup.js"></script>
    <script src="${ctx}/resources/js/new-js/page/coachResevGroup.js"></script>
    <script src="${ctx}/resources/js/new-js/page/coachBackMenu.js"></script>
    <%
        request.setAttribute("vEnter", "\n");
        //奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
        //都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
    %>
    <style type="text/css">
        .color-gray{
            background: #ccc;
        }
        .color-blue{
            background: #0000ff;
        }
        .color-orange{
            background: #f19149;
        }
        .color-toSign{
            background: #fc340e;
            cursor: pointer;
        }
        .color-geneCentent{
            background: #4F4F4F;
            cursor: pointer;
        }
        .color-clickCentent{
            background: #E0E0E0;
            cursor: pointer;
        }
        .sport-detail{
            margin-left: 416px;
            height: 30px;
            width: 100px;
            background: #0000ff;
            cursor: pointer;
        }
        .showPop{

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
                <li class="listCommon " data-value="wod"  href="${ctx}/coachWod/coachWodPage">训练计划</li>
                <li class="listCommon active" data-value="order" href="${ctx}/coachBackReser/reservationGroup">预约列表</li>
                <li class="listCommon" data-value="board" href="${ctx}/coachBackBoard/coachBoard">白板</li>
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
            <option value="2" selected>团课</option>
            <option value="1">私教课</option>
        </select>
        <p>类型</p>
        <div class="date-box">
            <input size="16" type="text" value="" readonly class="common form_datetime" style="text-align: center;">
        </div>
        <p>日期</p>
        <%--<c:if test="${coachType != 'boss' && courseModelList != null && courseModelList.size() > 0}">--%>
        <div class="date-box" id="signDiv">
            <%--<c:if test="${coachType != 'boss'}">--%>
                <c:choose>
                    <c:when test="${reservationListModel.coachSignStatus == 1}">
                        <p class="coach-sign color-orange sign-coach-sapn" style=" margin-left: 20px;width: 167px;text-indent: 0px;margin-top: 8px !important;">已签到</p>
                    </c:when>
                    <c:when test="${reservationListModel.coachSignStatus == -1}">
                        <p class="coach-nosign color-gray sign-coach-sapn" style="margin-left: 20px;width: 167px;text-indent: 0px;margin-top: 8px !important;">未签到</p>
                    </c:when>
                    <c:otherwise>
                        <p class="coach-sign color-toSign sign-coach-sapn" name="ssssss" style="margin-left: 20px;width: 167px;text-indent: 0px;margin-top: 8px !important;">签到</p>
                    </c:otherwise>
                </c:choose>
           <%-- </c:if>--%>
        </div>
    </div>
    <div class="wodRight fr clearfix schedule">
        <div class="filter-box">
            <div class="time">
                <span class="timeLeft">${dateTime}</span>
                <%--<span class="sport-detail">运动详情</span>--%>
            </div>
            <input type="hidden" id="coachId" value="${coachId}">
            <input type="hidden" id="coachType" value="${coachType}">
            <div class="one-filter fl">
                <select class="schedule-type filters" id="course">
                    <c:choose>
                        <c:when test="${null == courseModelList || courseModelList.size() < 1}">
                            <option value="0">无</option>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${courseModelList}" var="course">
                                <option value="${course.id}">${course.beginTime}&nbsp;${course.crouseTypeName}</option>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select>
                <p class="one-title">Class</p>
            </div>
            <div class="one-filter count fl">
                <span class="count">COUNT(M/F):${reservationListModel.maleNum}/${reservationListModel.fmaleNum}</span>
            </div>
            <div class="one-filter fl" >
                <ul id="contentList" style="display: none;">
                    <li class="content-list color-clickCentent">pull up</li>
                    <li class="content-list color-geneCentent" contentId = "">clean up</li>
                </ul>
            </div>
        </div>

        <%--列表开始--%>
        <div class="member-box clearfix  member-list-s">
            <ul class="male mr fl">
                <c:forEach items="${reservationListModel.maleList}" var="male">
                    <li class="reserOneLi clearfix">
                        <div class="left-block fl">
                            <img src="${male.ico}" class="fl" alt="">
                            <div class="name-block fl">
                                <p>${male.memberName}</p>
                                <c:choose>
                                    <c:when test="${male.signStatus == 1}">
                                        <span class="color-orange sign-but">已签到</span>
                                    </c:when>
                                    <c:when test="${male.courseEnd }">
                                        <span class="color-gray">未签到</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="color-toSign sign-but">签到</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <c:if test="${!male.boxMember}">
                                <span class="other">非</span>
                            </c:if>
                            <input type="hidden" class="wodId" value="${male.wodId}">
                            <input type="hidden" class="courseId" value="${male.courseId}">
                            <input type="hidden" class="courseOrderId" value="${male.courseOrderId}">
                            <input type="hidden" class="memberId" value="${male.memberId}">
                        </div>
                        <div class="center-block fl wodContentDiv">
                            <c:forEach items="${male.actionModelList}" var="actoin">
                                <div class="rx-line clearfix" type="${actoin.recordType}" rxplus="${actoin.hasRxPlus ? 1:0}"  sportResultId = "${actoin.sportResultId}">
                                    <input type="hidden" class="wodContentId" value="${actoin.wodContentId}">
                                    <span style="display: none"  class="wodContentCon">${actoin.content}</span>
                                    <span class="fl show-rx ${actoin.rx ? 'color-blue':'color-gray'}">Rx</span>
                                    <span class="fl show-rxp ${actoin.rxPlus ? 'color-blue': 'color-gray'}">Rx+</span>
                                    <div class="fl" style="height: 22px;width: 22px; margin-right: 4px;">
                                        <c:if test="${actoin.hasPop}">
                                            <img class="fl showPop" style= "width: 22px;cursor: pointer;" contentid="${actoin.contentId}" src="${ctx}/resources/images/new-image/order/icon_char_w.png" alt="">
                                        </c:if>
                                        <c:if test="${!actoin.hasPop}">
                                            <img class="fl showPop" style= "display: none;width: 22px;cursor: pointer;" contentid="${actoin.contentId}" src="${ctx}/resources/images/new-image/order/icon_char_w.png" alt="">
                                        </c:if>
                                    </div>
                                    <div class="score fl">${actoin.actionTitle}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <ul class="famale fl" style="margin-left: 480px;">
                <c:forEach items="${reservationListModel.fmaleList}" var="fmale">
                    <li class="fl reserOneLi clearfix">
                        <div class="left-block fl">
                            <img src="${fmale.ico}" class="fl" alt="">
                            <div class="name-block fl">
                                <p>${fmale.memberName}</p>
                                <c:choose>
                                    <c:when test="${fmale.signStatus == 1}">
                                        <span class="color-orange sign-but">已签到</span>
                                    </c:when>
                                    <c:when test="${fmale.courseEnd }">
                                        <span class="color-gray">未签到</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="color-toSign sign-but">签到</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <c:if test="${!fmale.boxMember}">
                                <span class="other">非</span>
                            </c:if>
                            <input type="hidden" class="wodId" value="${fmale.wodId}">
                            <input type="hidden" class="courseId" value="${fmale.courseId}">
                            <input type="hidden" class="courseOrderId" value="${fmale.courseOrderId}">
                            <input type="hidden" class="memberId" value="${fmale.memberId}">
                        </div>
                        <div class="center-block fl wodContentDiv">
                            <c:forEach items="${fmale.actionModelList}" var="actoin">
                                <div class="rx-line clearfix" type="${actoin.recordType}" rxplus="${actoin.hasRxPlus ? 1:0}" sportResultId = "${actoin.sportResultId}">
                                    <input type="hidden" class="wodContentId" value="${actoin.wodContentId}">
                                    <span style="display: none"  class="wodContentCon">${actoin.content}</span>
                                    <span class="fl show-rx ${actoin.rx ? 'color-blue':'color-gray'}">Rx</span>
                                    <span class="fl show-rxp ${actoin.rxPlus ? 'color-blue': 'color-gray'}">Rx+</span>
                                    <div class="fl" style="height: 22px;width: 22px;margin-right: 4px;">
                                        <c:if test="${actoin.hasPop}">
                                            <img class="fl showPop" style= "width: 22px;cursor: pointer;" contentid="${actoin.contentId}" src="${ctx}/resources/images/new-image/order/icon_char_w.png" alt="">
                                        </c:if>
                                        <c:if test="${!actoin.hasPop}">
                                            <img class="fl showPop" style= "display: none;width: 22px;" contentid="${actoin.contentId}" src="${ctx}/resources/images/new-image/order/icon_char_w.png" alt="">
                                        </c:if>
                                    </div>
                                    <div class="score fl">${actoin.actionTitle}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>


        <div class="member-box clearfix member-list-t" style="display: none">
            <div id="maleScore">

            </div>
            <div id="fMaleScore">

            </div>
        </div>
    </div>



    <!--Calories-->
    <div class="add calories">
        <div class="record">
            <div class="input-box clearfix">
                <div class="one-block fl">
                    <p>CALORIES</p>
                    <input type="text" id="calories" maxlength="10">
                </div>
                <div class="rx-box fl">
                    <span class="rx fl rx-span">RX</span>
                    <span class="rx fl rxp-span" canSel="0">RX+</span>
                </div>
            </div>
        </div>
        <div class="remark">
            <div class="title-line">备注</div>
            <textarea cols="30" rows="10" class="recordRemark"></textarea>
        </div>
        <div class="btn-box clearfix">
            <span class="cancel-btn fl">取消</span>
            <span class="save-btn fr">保存</span>
        </div>
        <input type="hidden" class="addWodId" value="">
        <input type="hidden" class="addCourseId" value="">
        <input type="hidden" class="addRecordType" value="">
        <input type="hidden" class="addCourseOrderId" value="">
        <input type="hidden" class="addMemberId" value="">
        <input type="hidden" class="addWodContentId" value="">
        <input type="hidden" class="addResultId" value="">
    </div>

    <!--Weight-->
    <div class="add weight">
        <div class="record">
            <div class="title-line">Deadlift</div>
            <div class="input-box clearfix">
                <div class="one-block fl">
                    <p>SETS</p>
                    <input type="text" id="weightSets" maxlength="9">
                </div>
                <span class="and fl">×</span>
                <div class="one-block fl">
                    <p>REPS</p>
                    <input type="text" id="weightReps" maxlength="9">
                </div>
                <span class="and fl">@</span>
                <div class="one-block fl">
                    <p>WEIGHT</p>
                    <input type="text" id="weightNum" maxlength="9">
                </div>
                <div class="unit-box fl">
                    <select id="weightUnit" disabled>
                        <option value="1">kg</option>
                        <option value="2">lb</option>
                    </select>
                </div>
                <div class="rx-box fl">
                    <span class="rx fl rx-span">RX</span>
                    <span class="rx fl rxp-span" canSel="0">RX+</span>
                </div>
            </div>
        </div>
        <div class="remark">
            <div class="title-line">备注</div>
            <textarea cols="30" rows="10" class="recordRemark"></textarea>
        </div>
        <div class="btn-box clearfix">
            <span class="cancel-btn fl">取消</span>
            <span class="save-btn fr">保存</span>
        </div>
        <input type="hidden" class="addWodId" value="">
        <input type="hidden" class="addCourseId" value="">
        <input type="hidden" class="addRecordType" value="">
        <input type="hidden" class="addCourseOrderId" value="">
        <input type="hidden" class="addMemberId" value="">
        <input type="hidden" class="addWodContentId" value="">
        <input type="hidden" class="addResultId" value="">
    </div>


    <!--met Weight-->
    <div class="add metWeight">
        <div class="record">
            <div class="title-line">Deadlift</div>
            <div class="input-box clearfix">
                <div class="one-block fl">
                    <p>WEIGHT</p>
                    <input type="text" id="metWeightNum" maxlength="9">
                </div>
                <div class="unit-box fl">
                    <select id="metWeightUnit" disabled>
                        <option value="1">kg</option>
                        <option value="2">lb</option>
                    </select>
                </div>
                <div class="rx-box fl">
                    <span class="rx fl rx-span">RX</span>
                    <span class="rx fl rxp-span" canSel="0">RX+</span>
                </div>
            </div>
        </div>
        <div class="remark">
            <div class="title-line">备注</div>
            <textarea cols="30" rows="10" class="recordRemark"></textarea>
        </div>
        <div class="btn-box clearfix">
            <span class="cancel-btn fl">取消</span>
            <span class="save-btn fr">保存</span>
        </div>
        <input type="hidden" class="addWodId" value="">
        <input type="hidden" class="addCourseId" value="">
        <input type="hidden" class="addRecordType" value="">
        <input type="hidden" class="addCourseOrderId" value="">
        <input type="hidden" class="addMemberId" value="">
        <input type="hidden" class="addWodContentId" value="">
        <input type="hidden" class="addResultId" value="">
    </div>

    <!--Gymnastics/Amrap-Rounds/Amrap-Rounds and Reps-->
    <div class="add gymnastics">
        <div class="record">
            <div class="input-box clearfix">
                <div class="one-block fl">
                    <p>SETS</p>
                    <input type="text" id="gymSets" maxlength="6">
                </div>
                <span class="and fl">×</span>
                <div class="one-block fl">
                    <p>REPS</p>
                    <input type="text" id="gymReps" maxlength="6">
                </div>
                <div class="rx-box fl">
                    <span class="rx fl rx-span">RX</span>
                    <span class="rx fl rxp-span" canSel="0">RX+</span>
                </div>
            </div>
        </div>
        <div class="remark">
            <div class="title-line">备注</div>
            <textarea cols="30" rows="10" class="recordRemark"></textarea>
        </div>
        <div class="btn-box clearfix">
            <span class="cancel-btn fl">取消</span>
            <span class="save-btn fr">保存</span>
        </div>
        <input type="hidden" class="addWodId" value="">
        <input type="hidden" class="addCourseId" value="">
        <input type="hidden" class="addRecordType" value="">
        <input type="hidden" class="addCourseOrderId" value="">
        <input type="hidden" class="addMemberId" value="">
        <input type="hidden" class="addWodContentId" value="">
        <input type="hidden" class="addResultId" value="">
    </div>
    <!--Amrap-Rounds and Reps-->
    <div class="add amrapRounds">
        <div class="record">
            <div class="input-box clearfix">
                <div class="one-block fl">
                    <p>SETS</p>
                    <input type="text" id="arSets" maxlength="6">
                </div>
                <span class="and fl">+</span>
                <div class="one-block fl">
                    <p>REPS</p>
                    <input type="text" id="arReps" maxlength="6">
                </div>
                <div class="rx-box fl">
                    <span class="rx fl rx-span">RX</span>
                    <span class="rx fl rxp-span" canSel="0">RX+</span>
                </div>
            </div>
        </div>
        <div class="remark">
            <div class="title-line">备注</div>
            <textarea cols="30" rows="10" class="recordRemark"></textarea>
        </div>
        <div class="btn-box clearfix">
            <span class="cancel-btn fl">取消</span>
            <span class="save-btn fr">保存</span>
        </div>
        <input type="hidden" class="addWodId" value="">
        <input type="hidden" class="addCourseId" value="">
        <input type="hidden" class="addRecordType" value="">
        <input type="hidden" class="addCourseOrderId" value="">
        <input type="hidden" class="addMemberId" value="">
        <input type="hidden" class="addWodContentId" value="">
        <input type="hidden" class="addResultId" value="">
    </div>
    <!--Time-->
    <div class="add time">
        <div class="record">
            <div class="input-box clearfix">
                <div class="one-block fl">
                    <p>MINUTES</p>
                    <input type="text" id="timeMinus" maxlength="4">
                </div>
                <span class="and fl">:</span>
                <div class="one-block fl">
                    <p>SECONDS</p>
                    <input type="text" id="timeSecons" maxlength="2">
                </div>
                <div class="rx-box fl">
                    <span class="rx fl rx-span">RX</span>
                    <span class="rx fl rxp-span" canSel="0">RX+</span>
                </div>
            </div>
        </div>
        <div class="remark">
            <div class="title-line">备注</div>
            <textarea cols="30" rows="10" class="recordRemark" id="timeComment"></textarea>
        </div>
        <div class="btn-box clearfix">
            <span class="cancel-btn fl">取消</span>
            <span class="save-btn fr">保存</span>
        </div>
        <input type="hidden" class="addWodId" value="">
        <input type="hidden" class="addCourseId" value="">
        <input type="hidden" class="addRecordType" value="">
        <input type="hidden" class="addCourseOrderId" value="">
        <input type="hidden" class="addMemberId" value="">
        <input type="hidden" class="addWodContentId" value="">
        <input type="hidden" class="addResultId" value="">
    </div>
    <!--Amrap-Reps-->
    <div class="add amrap">
        <div class="record">
            <div class="input-box clearfix">
                <div class="one-block fl">
                    <p>REPS</p>
                    <input type="text" id="amReps">
                </div>
                <div class="rx-box fl">
                    <span class="rx fl rx-span">RX</span>
                    <span class="rx fl rxp-span" canSel="0">RX+</span>
                </div>
            </div>
        </div>
        <div class="remark">
            <div class="title-line">备注</div>
            <textarea cols="30" rows="10" class="recordRemark"></textarea>
        </div>
        <div class="btn-box clearfix">
            <span class="cancel-btn fl">取消</span>
            <span class="save-btn fr">保存</span>
        </div>
        <input type="hidden" class="addWodId" value="">
        <input type="hidden" class="addCourseId" value="">
        <input type="hidden" class="addRecordType" value="">
        <input type="hidden" class="addCourseOrderId" value="">
        <input type="hidden" class="addMemberId" value="">
        <input type="hidden" class="addWodContentId" value="">
        <input type="hidden" class="addResultId" value="">
    </div>
    <!--Distance-->
    <div class="add distance">
        <div class="record">
            <div class="input-box clearfix">
                <div class="one-block fl">
                    <p id="distanceUnitName">METERS</p>
                    <input type="text" id="meters" maxlength="9">
                </div>
                <div class="rx-box fl">
                    <span class="rx fl rx-span">RX</span>
                    <span class="rx fl rxp-span" canSel="0">RX+</span>
                </div>
            </div>
        </div>
        <div class="remark">
            <div class="title-line">备注</div>
            <textarea cols="30" rows="10" class="recordRemark"></textarea>
        </div>
        <div class="btn-box clearfix">
            <span class="cancel-btn fl">取消</span>
            <span class="save-btn fr">保存</span>
        </div>
        <input type="hidden" class="addWodId" value="">
        <input type="hidden" class="addCourseId" value="">
        <input type="hidden" class="addRecordType" value="">
        <input type="hidden" class="addCourseOrderId" value="">
        <input type="hidden" class="addMemberId" value="">
        <input type="hidden" class="addWodContentId" value="">
        <input type="hidden" class="addResultId" value="">
    </div>
    <!--Each Round-->
    <div class="add each-round">
        <div class="record">
            <div class="input-box clearfix">
                <div class="each">
                    <ul id="roundRoundUl">
                        <li class="clearfix">
                            <p class="fl title-left">ROUND</p>
                            <p class="fl title-center">TIME</p>
                            <p class="fl title-right">NOTE</p>
                        </li>
                        <li class="clearfix">
                            <p class="fl title-left inLi">1</p>
                            <input type="text" class="title-center fl numIn">
                            <input type="text" class="title-right fl remarkIn">
                        </li>
                    </ul>
                </div>
                <div class="unit-box" style="display: none">
                    <select id="roundMeasure" disabled >
                        <option value="1">reps</option>
                        <option value="2">time</option>
                        <option value="3">weight</option>
                        <option value="4">distance</option>
                        <option value="5">calories</option>
                    </select>
                </div>
                <div class="rx-box clearfix">
                    <span class="rx fl rx-span">RX</span>
                    <span class="rx fl rxp-span" canSel="0">RX+</span>
                </div>
            </div>
        </div>
        <div class="remark">
            <div class="title-line">备注</div>
            <textarea cols="30" rows="10" class="recordRemark"></textarea>
        </div>
        <div class="btn-box clearfix">
            <span class="cancel-btn fl">取消</span>
            <span class="save-btn fr">保存</span>
        </div>
        <input type="hidden" class="addWodId" value="">
        <input type="hidden" class="addCourseId" value="">
        <input type="hidden" class="addRecordType" value="">
        <input type="hidden" class="addCourseOrderId" value="">
        <input type="hidden" class="addMemberId" value="">
        <input type="hidden" class="addWodContentId" value="">
        <input type="hidden" class="addResultId" value="">
    </div>
</div>
<!--弹框-->
<!--举重类弹框-->
<div class="pop weight-pop" style="overflow: auto;z-index:  99;">
    <strong class="close"></strong>
    <div class="pop-title">
        <p>1 次最大：108kg （估算）</p>
        <p>PUSH JERK</p>
    </div>
    <div class="two person">
        <p class="two-title">个人数据记录（PRS）</p>
        <div class="person-table clearfix">
        </div>
    </div>
    <div class="two percent">
        <p class="two-title">百分比</p>
        <div class="percent-table">
            <ul class="clearfix">
            </ul>
        </div>
    </div>
    <div class="history-block">
        <div class="history-title clearfix">
            <p class="fl">历史记录</p>
            <span class="fr">5条记录</span>
        </div>
        <div class="table-box">
            <div class="table-head clearfix">
                <p class="date fl">日期</p>
                <p class="result fl">记录结果</p>
                <p class="remark fl">备注</p>
            </div>
            <ul>
                <li class="clearfix">
                    <p class="date fl">2017/12/09</p>
                    <p class="result fl">5*3</p>
                    <p class="remark fl">腹肌力量不足</p>
                </li>
            </ul>

        </div>
        <div class="page-box">
            <div>
                <ul class="pagination" id="pagination">
                </ul>
                <input type="hidden" id="PageCount" runat="server" />
                <input type="hidden" id="PageSize" runat="server" value="5" />
                <input type="hidden" id="countindex" runat="server" value="1"/>
                <!--设置最多显示的页码数 可以手动设置 默认为7-->
                <input type="hidden" id="visiblePages" runat="server" value="5" />
            </div>
        </div>
    </div>
    <input type="hidden" class="weightPopMemberId" value="">
    <input type="hidden" class="weightPopContentId" value="">
    <div id="weight-chart" style="width: 478px; height: 240px;"></div>
</div>
<!--体操类弹框-->
<div class="pop gym-pop" style="z-index:  99;">
    <strong class="close"></strong>
    <div class="pop-title">
        <p>1 次最大：8</p>
        <p>Bar Muscle-ups</p>
    </div>
    <div class="history-block">
        <div class="history-title clearfix">
            <p class="fl">历史记录</p>
            <span class="fr">5条记录</span>
        </div>
        <div class="table-box" style="width: 466px;">
            <div class="table-head clearfix">
                <p class="date fl">日期</p>
                <p class="result fl">记录结果</p>
                <p class="remark fl">备注</p>
            </div>
            <ul>
                <%--<li class="clearfix">--%>
                <%--<p class="date fl">2017/12/09</p>--%>
                <%--<p class="result fl">5*3</p>--%>
                <%--<p class="remark fl">腹肌力量不足</p>--%>
                <%--</li>--%>
            </ul>

        </div>
        <div class="page-box">
            <ol class="page clearfix">

            </ol>
        </div>
    </div>
    <div id="gym-chart" style="width: 478px; height: 240px;"></div>
</div>
<input type="hidden" id="ctx" value="${ctx}"/>
</body>
</html>