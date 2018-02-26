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
    <title>wod</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/rankingList.css">
    <style type="text/css">

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
                    <span class="name">${name}</span>
                </div>
                <select class="topchoose fr">
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
                <li class="listCommon" data-value="order" href="${ctx}/coachBackReser/reservationGroup">预约列表</li>
                <li class="listCommon" data-value="board" href="${ctx}/coachBackBoard/coachBoard">白板</li>
                <li class="listCommon active" data-value="ranking" href="${ctx}/coachBackRand/coachRank">排行榜</li>
                <li class="listCommon" data-value="analysis" href="${ctx}/coachBackAnalysis/coachAnalysis">数据分析</li>
            </ul>
        </div>
    </header>
</div>
<div class="box clearfix" style=" margin: 156px auto 0;">
    <div class="fl">
        <select class="box-choose" id="boxId">
            <c:forEach items="${boxList}" var="box">
                <option value="${box.id}">${box.name}</option>
            </c:forEach>
        </select>
        <p style="padding-left: 71px;color: #fff;">场馆名称</p>
    </div>
    <select class="choose" id="wordType">
        <option value="1">GIRLS</option>
        <option value="2">HERO</option>
        <option value="3">GAMES</option>
    </select>
    <div id="contentList">
    <c:forEach items="${listJson.result}" var="content" >
        <div class="commonList clearfix">
            <div class="title">${content.contentName}</div>
            <div class="left fl">
                <div class="leftTitle">MALE</div>
                <ul class="contentList clearfix">
                    <c:forEach items="${content.maleList}" var="male" varStatus="index">
                        <li class="clearfix">
                            <p class="name fl">${index.index + 1}.${male.memberName}</p>
                            <p class="date fl">${male.day}</p>
                            <p class="time fl">${male.time}</p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="midLine fl"></div>
            <div class="left fl">
                <div class="leftTitle">FEMALE</div>
                <ul class="contentList clearfix">
                    <c:forEach items="${content.fmaleList}" var="fmscore" varStatus="findex">
                        <li class="clearfix">
                            <p class="name fl">${findex.index + 1}.${fmscore.memberName}</p>
                            <p class="date fl">${fmscore.day}</p>
                            <p class="time fl">${fmscore.time}</p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </c:forEach>
    </div>
</div>
<input type="hidden" id="ctx" value="${ctx}"/>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/rankingList.js"></script>
<script src="${ctx}/resources/js/new-js/page/coachBackMenu.js"></script>
</html>