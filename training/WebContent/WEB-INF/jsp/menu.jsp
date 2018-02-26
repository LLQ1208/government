<%@ page import="com.acsm.training.model.User" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="menuName" value="${pageContext.request.getParameter('menuName')}"/>
<c:set var="group" value="${pageContext.request.getParameter('group')}"/>
<%
    User user = (User)request.getSession().getAttribute("currentUser");
    String loginShowName = (String)request.getSession().getAttribute("loginShowName");
    request.setAttribute("userName",loginShowName);
    request.setAttribute("userType",user.getUserType());
    String loginImg = (String)request.getSession().getAttribute("loginImg");
    request.setAttribute("loginImg",loginImg);
    String backCan = (String)request.getSession().getAttribute("backCan");
    request.setAttribute("backCan",backCan);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/menu.css">
    <style type="text/css">
    </style>
</head>
<body>
<div class="leftBar fl clearfix">
    <div class="logo">
    </div>
    <div class="user clearfix">
        <%--<span class="userHead"></span>--%>
        <input type="hidden" id="userType" value="${userType}">
        <c:choose>
            <c:when test="${ null != loginImg && '' != loginImg}">
               <img src="${loginImg}" class="photo-img" />
            </c:when>
            <c:otherwise>
                <span class="userHead" ></span>
            </c:otherwise>
        </c:choose>
        <p class="userName">${userName}</p>
        <span class="downIcon title-change"></span>
        <ul class="choose  title-list-li">
            <li class="current back-or-cfer">CFer管理平台</li>
            <c:if test="${backCan == '1'}">
                <li class="back-or-cfer">教练后台</li>
            </c:if>
        </ul>
    </div>
    <div class="list clearfix">
        <!--wod-->
        <c:if test="${boss || menuNew.WOD_MENU}">
        <div class="listCommon clearfix">
            <div class="listCommonTitle clearfix ${group == 1 ? 'active  ':''}">
                <span class="listCommonIcon fl"></span>
                <p class="titleName fl" style="margin-top:18px;">WOD</p>
                <span class="commonDownIcon fl"></span>
            </div>
            <div class="listCommonDetail  ${group == 1 ? 'one':''}">
                <c:if test="${boss || menuNew.CALENDAR_MENU}">
                <p class="changeSrc ${menuName == 'calendar' ? 'active':''}" data-value="calendar" href="${ctx}/wod/queryWodList">日历</p>
                </c:if>
                <c:if test="${boss || menuNew.ACTION_MENU}">
                <p class="changeSrc ${menuName == 'actions' ? 'active':''}" data-value="actions" href="${ctx}/wod/actions?searchType=3">动作元素</p>
                </c:if>
                <c:if test="${boss || menuNew.WOD_SETTING_MENU}">
                <p class="changeSrc ${menuName == 'wodSetting' ? 'active':''}" data-value="wodSetting" href="${ctx}/wod/wodSetting">WOD设置</p>
                </c:if>
            </div>
        </div>
        </c:if>
        <!--member-->
        <c:if test="${boss || menuNew.MEMBER_MANAGE_TOP_MENU}">
        <div class="listCommon clearfix">
            <div class="listCommonTitle clearfix ${group == 2 ? 'active  ':''}">
                <span class="listCommonIcon fl memberIcon"></span>
                <p class="titleName fl">会员管理</p>
                <span class="commonDownIcon fl"></span>
            </div>
            <div class="listCommonDetail ${group == 2 ? 'one':''}">
                <c:if test="${boss || menuNew.MEMBER_MANAGE_MENU }">
                    <p class="changeSrc ${menuName == 'memberList' ? 'active':''}"  href="${ctx}/memberNew/memberList" data-value="calender">会员列表</p>
                </c:if>
                <c:if test="${boss || menuNew.TEMPLATE_MENU }">
                    <p class="changeSrc ${menuName == 'memberSetting' ? 'active':''}"  href="${ctx}/memberNew/memberSetting" data-value="calender">设置模板</p>
                </c:if>
            </div>
        </div>
        </c:if>
        <!--order-->
        <c:if test="${boss || menuNew.APPOINTMENT_TOP_MENU }">
        <div class="listCommon clearfix">
            <div class="listCommonTitle clearfix ${group == 3 ? 'active  ':''}">
                <span class="listCommonIcon fl orderIcon"></span>
                <p class="titleName fl">预约管理</p>
                <span class="commonDownIcon fl"></span>
            </div>
            <div class="listCommonDetail ${group == 3 ? 'one':''}" >
                <c:if test="${boss || menuNew.APPOINTMENT_MENU }">
                <p class="changeSrc ${menuName == 'order-list' ? 'active':''}" data-value="order-list" href="${ctx}/reservation/reservationList">预约列表</p>
                </c:if>
            </div>
        </div>
        </c:if>
        <!--class-->
        <c:if test="${boss || menuNew.COURSE_MENU}">
        <div class="listCommon clearfix">
            <div class="listCommonTitle clearfix  ${group == 4 ? 'active  ':''}">
                <span class="listCommonIcon fl classIcon"></span>
                <p class="titleName fl">课程表</p>
                <span class="commonDownIcon fl"></span>
            </div>
            <div class="listCommonDetail ${group == 4 ? 'one':''}">
                <c:if test="${boss || menuNew.COURSE_CALENDAR_MENU}">
                <p class="changeSrc ${menuName == 'cycle-schedule' ? 'active':''}" data-value="cycle-schedule" href="${ctx}/classSchedule/classSchedule">日历</p>
                </c:if>
                <c:if test="${boss || menuNew.COURSE_COACH_MENU}">
                <p class="changeSrc ${menuName == 'private-schedule' ? 'active':''}" data-value="private-schedule" href="${ctx}/classSchedule/privateEducationList">私教课表</p>
                </c:if>
                <c:if test="${boss || menuNew.MANAGE_MENU}">
                <p class="changeSrc ${menuName == 'set-schedule' ? 'active':''}" data-value="set-schedule" href="${ctx}/classSchedule/classSettingPage">设置</p>
                </c:if>
                <c:if test="${boss || menuNew.COURSE_TYPE_MENU}">
                <p class="changeSrc ${menuName == 'class-type' ? 'active':''}" data-value="class-type" href="${ctx}/setting/courseType/newCourseTypeList">课程类型</p>
                </c:if>
            </div>
        </div>
        </c:if>
        <!--opration-->
        <c:if test="${boss || menuNew.OPERATION_MENU}">
        <div class="listCommon clearfix ">
            <div class="listCommonTitle clearfix ${group == 5 ? 'active  ':''}">
                <span class="listCommonIcon fl oprationIcon"></span>
                <p class="titleName fl" >统计分析</p>
                <span class="commonDownIcon fl"></span>
            </div>
            <div class="listCommonDetail ${group == 5 ? 'one':''}">
                <c:if test="${boss || menuNew.MEMBER_ANALYSIS_MENU}">
                <p class="changeSrc ${menuName == 'memberAnalysis' ? 'active':''}" data-value="memberAnalysis" href="${ctx}/analysis/member">会员统计</p>
                </c:if>
                <c:if test="${boss || menuNew.COACH_ANALYSIS_MENU}">
                <p class="changeSrc ${menuName == 'coachAnalysis' ? 'active':''} " data-value="coachAnalysis" href="${ctx}/analysis/coach">教练统计</p>
                </c:if>
            </div>
        </div>
        </c:if>
        <!--email-->
        <c:if test="${boss || menuNew.MAIL_MANAGE}">
        <div class="listCommon clearfix">
            <div class="listCommonTitle clearfix ${group == 7 ? 'active  ':''}">
                <span class="listCommonIcon fl emailIcon"></span>
                <p class="titleName fl" style="margin-top: 15px;">邮件管理</p>
                <span class="commonDownIcon fl"></span>
            </div>
            <div class="listCommonDetail ${group == 7 ? 'one':''}">
                <c:if test="${boss || menuNew.MAIL_LIST}">
                <p class="changeSrc  ${menuName == 'mailList' ? 'active':''}" data-value="calender" href="${ctx}/mailManage/mailList">邮件列表</p>
                </c:if>
            </div>
        </div>
        </c:if>
        <!--price-->
        <%--<div class="listCommon clearfix">--%>
            <%--<div class="listCommonTitle clearfix ">--%>
                <%--<span class="listCommonIcon fl priceIcon"></span>--%>
                <%--<p class="titleName fl">价目表</p>--%>
                <%--<span class="commonDownIcon fl"></span>--%>
            <%--</div>--%>
            <%--<div class="listCommonDetail">--%>
                <%--<p class="changeSrc" data-value="calender">日历</p>--%>
                <%--<p class="changeSrc" data-value="calender">动作元素</p>--%>
                <%--<p class="changeSrc" data-value="calender">WOD设置</p>--%>
            <%--</div>--%>
        <%--</div>--%>
        <!--set-->
        <c:if test="${boss || menuNew.MANAGE_TOP_MENU}">
        <div class="listCommon clearfix">
            <div class="listCommonTitle clearfix  ${group == 6 ? 'active  ':''}">
                <span class="listCommonIcon fl setIcon"></span>
                <p class="titleName fl">设置</p>
                <span class="commonDownIcon fl"></span>
            </div>
            <div class="listCommonDetail ${group == 6 ? 'one':''}">
                <c:if test="${boss || menuNew.MANAGE_ROLE_MENU}">
                <p class="changeSrc ${menuName == 'roleNew' ? 'active':''}" data-value="roleNew"  href="${ctx}/setting/roleNew">权限管理</p>
                </c:if>
                <c:if test="${boss || menuNew.MANAGE_EMPLOYEE_MENU}">
                <p class="changeSrc ${menuName == 'employee' ? 'active':''}" data-value="employ" href="${ctx}/setting/employee">员工管理</p>
                </c:if>
            </div>
        </div>
        </c:if>
        <div class="listCommon clearfix">
            <div class="listCommonTitle clearfix ">
                <span class="listCommonIcon fl exitIcon"></span>
                <p class="titleName fl  log-out">退出登录</p>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/menu.js"></script>
</html>