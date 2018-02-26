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
    <title>set-schedule</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/set-schedule.css" />
</head>
<body>
<div class="content-box">
<jsp:include page="../menu.jsp" flush="false" >
    <jsp:param value="set-schedule" name="menuName"/>
    <jsp:param value="4" name="group"/>
</jsp:include>
    <div class="main-content">
        <div class="setting">
            <div id="bread" class="clearfix">
                <span class="breadFirst">课程表</span>
                <span class="breadSecond"> > </span>
                <span class="breadThird">设置</span>
            </div>
            <div class="settingDeail">
                <div class="title">
                    <p class="titleTip">课程设置</p>
                </div>
                <div class="editor clearfix">
                    <p>预约／取消时间</p>
                    <p>此处决定了你的会员提前多久可以预约或者取消该节课程  </p>
                    <p class="showChoose showReservationMinus">${classReservationMinus}分钟</p>
                    <div class="editorBtn" type="1">编辑</div>
                    <div class="backEditor clearfix">
                        <div class="cancel fl">取消</div>
                        <div class="confirm fl reservationMinusConfirm">保存</div>
                    </div>
                    <div class="choose">
                        <div class="time fl">
                            <input type="hidden" id="hideReserMinux" value="${classReservationMinus}">
                            <input id="classReservationMinus"   class="number" value="${classReservationMinus}" ><span>分钟</span>
                        </div>
                    </div>
                </div>
                <%--<div class="editor clearfix">--%>
                    <%--<p>Drop in 预约／取消时间</p>--%>
                    <%--<p>此处决定了非会员提前多久可以预约或者取消该节课程   </p>--%>
                    <%--<p class="showChoose ">展示：<span class="showDropInMinus">${dropInMinus}</span>分钟</p>--%>
                    <%--<div class="editorBtn" type="2">编辑</div>--%>
                    <%--<div class="backEditor clearfix">--%>
                        <%--<div class="cancel fl">取消</div>--%>
                        <%--<div class="confirm fl dropInMinusConfirm">保存</div>--%>
                    <%--</div>--%>
                    <%--<div class="choose">--%>
                        <%--<input type="hidden" id="hideDropIn" value="${dropInMinus}">--%>
                        <%--<input id="dropInMinus"  class="number" value="${dropInMinus}" ><span>分钟</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="editor clearfix">
                    <p>最多可预约天数</p>
                    <p>此处决定了你的会员最多可预约多少天的课程</p>
                    <p class="showChoose ">展示：<span class="showReservationDay">${reservationDay}</span>天</p>
                    <div class="editorBtn" type="3">编辑</div>
                    <div class="backEditor clearfix">
                        <div class="cancel fl">取消</div>
                        <div class="confirm fl reservationDayConfirm">保存</div>
                    </div>
                    <div class="choose">
                        <input type="hidden" id="hidereservationDay" value="${reservationDay}">
                        <input id="reservationDay" class="number" value="${reservationDay}" ><span>天</span>
                    </div>
                </div>
            </div>
            <input type="hidden" id="ctx" value="${ctx}"/>
        </div>
    </div>
</div>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/page/set-schedule.js"></script>
</body>
</html>