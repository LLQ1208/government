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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/red.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/member/member-view.css">
    <style>
        .sex{
            width: 200px;
            height: 34px;
        }

    </style>
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="memberList" name="menuName"/>
        <jsp:param value="2" name="group"/>
    </jsp:include>
    <div class="main-content" >
        <div class="member-view">
            <div id="bread" class="clearfix">
                <span class="breadFirst">会员管理</span>
                <span class="breadSecond"> > </span>
                <span class="breadFirst">会员列表</span>
                <span class="breadSecond"> > </span>
                <span class="breadThird">${member.name}</span>
            </div>
            <div class="renewal clearfix">
                <a href="javascript:;" class="renewal-btn fr" id="toExtendA">续卡</a>
            </div>
            <div class="head clearfix">
                <div class="headcont clearfix">
                    <div class="left fl" style="height: 100%;">
                        <%--<input type="file">
                        <div class="pic"><img src="${member.avatar}" id="memberImg" class="fl" alt="" style="width: 60px;height: 60px;border-radius: 50%;margin-right: 18px;"></div>
                        <p class="changpic">编辑头像</p>--%>
                        <iframe id="iframe" src="${ctx}/memberNew/memberImg?memberId=${member.id}" width="100%" height="100%" frameborder="0" scrolling="no"  allowtransparency="true" ></iframe>
                    </div>
                    <div class="right fl clearfix">
                        <div class="one clearfix">
                            <p class="name fl">
                                <input class="edit-info" type="text" value="${member.name}" id="memberName" maxlength="20">
                                <span class="showValue">${member.name}</span>
                                <span class="rewrite"></span>
                            </p>
                            <div class="sex fl" >
                                <div class="edit-info" style="display: none" id="sexSel">
                                    <input type="radio" class="male" name="sex" ${member.sex == 0 ? 'checked' : ''}  value="0">
                                    <label>男</label>
                                    <input type="radio" class="female"  ${member.sex == 1 ? 'checked' : ''} name="sex" value="1">
                                    <label>女</label>
                                </div>
                                <input type="hidden" value="${member.sex}" id="sexInput">
                                <span class="showValue">${member.sex == 0 ? '男' : '女'}</span>
                                <span class="rewrite" ></span>
                            </div>
                        </div>
                        <p class="commonp pone">
                            <span>开卡时间：</span>
                            <input size="16" type="text" value="${cardStartTime}" id="cardStartTime" readonly class="edit-info form_datetime"  style="cursor: pointer;">
                            <span class="showValue">${cardStartTime}</span>
                            <c:choose>
                                <c:when test="${canEdit == 1}">
                                    <span class="rewrite"></span>
                                </c:when>
                                <c:otherwise>
                                    <span class="rewrite" style="display: none"></span>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" id="cardTimeCanEdit" value="${canEdit}">
                        </p>
                        <p class="commonp">
                            <span>出生日期：</span>
                            <input size="16" type="text" value="${birthday}" id="birthday" readonly class="edit-info form_datetime"  style="cursor: pointer;">
                            <span class="showValue">${birthday}</span>
                            <span class="rewrite"></span>
                        </p>
                        <p class="commonp">
                            <span>手机号：</span>
                            <span>${member.phone}</span>
                        </p>
                        <p class="commonp">
                            <span>邮箱：</span>
                            <input class="edit-info" type="text" value="${member.email}" id="emial">
                            <span class="showValue">${member.email}</span>
                            <span class="rewrite"></span>
                        </p>
                    </div>
                </div>
                <div class="edit">
                    <textarea  id="comment" cols="30" rows="10" placeholder="备注:"  maxlength="200" >${member.comment}</textarea>
                </div>
            </div>
            <div class="cont clearfix">
                <div class="option clearfix">
                    <div class="cards fl">
                        <span class="cardsopt active" index="1">团课卡</span>
                        <span class="cardsopt" index="2">私教卡</span>
                    </div>
                    <div class="recode fl">
                        <span class="recodeopt active" index="0">会员卡记录</span>
                        <span class="recodeopt" index="1">预约记录</span>
                    </div>
                    <c:choose>
                        <c:when test="${thisCardVality == 1}">
                            <div class="operator-card" style="float: right;">
                            <span class="card-span statusBut" status="${memberCard.status}"  name="statusBut">
                                <c:choose>
                                    <c:when test="${memberCard.status == 0}">
                                        停卡
                                    </c:when>
                                    <c:otherwise>
                                        开卡
                                    </c:otherwise>
                                </c:choose>
                            </span>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="operator-card" style="float: right;" style="display: none" >
                            <span class="card-span statusBut" status="${memberCard.status}" name="statusBut">
                                停卡
                            </span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="detail card-list">
                    <div class="title">
                        <span>会员卡名称</span>
                        <span>费用</span>
                        <span>开始时间</span>
                        <span>到期时间</span>
                        <span>状态</span>
                        <span>处理人</span>
                        <span>可预约课程</span>
                        <span>事件</span>
                    </div>
                    <c:forEach items="${cardList}" var="card">
                        <div class="common" style="position: relative;">
                            <c:if test="${card.status == 1}">
                                <strong style="position:  absolute;width: 20px;height: 20px;background:  #ff0000;color: #fff;border-radius:  50%;text-align:  center;line-height:  20px;left: 96px;top: 6px;">停</strong>
                            </c:if>
                            <span>${card.cardTemplateName}</span>
                            <span>¥${card.generalMoney}</span>
                            <span>${card.generalBeginTime}</span>
                            <span>${card.cardEndTime}</span>
                            <span>${card.cardStatus}</span>
                            <span>${card.dealName}</span>
                        <span class="show1">查看
                            <div class="commondet" style="box-sizing:  border-box;padding: 6px;color: #666;">
                                <p style="line-height: 26px;">${card.canOrderCourseType}</p>
                            </div>
                        </span>
                        <span class="show1">查看
                            <div class="commondet">
                                <c:choose>
                                    <c:when test="${card.eventList.size() > 0}">
                                        <c:forEach items="${card.eventList}" var="event">
                                            <span>${event}</span>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <span>无</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </span>
                        </div>
                    </c:forEach>
                </div>
                <div class="detail order-list">
                    <div class="title">
                        <span>日期</span>
                        <span>训练馆</span>
                        <span style="width: 200px;">课程类型</span>
                        <span style="width: 200px;">详情</span>
                        <span>状态</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="ctx" value="${ctx}">
<input type="hidden" id="memberId" value="${member.id}">
<input type="hidden" id="cardId" value="${cardId}">
<input type="hidden" id="boxId" value="${boxId}">
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/icheck.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/resources/js/new-js/page/member-view.js"></script>
</html>