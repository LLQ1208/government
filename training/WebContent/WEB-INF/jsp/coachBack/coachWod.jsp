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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/wod.css">
    <script src="${ctx}/resources/js/new-js/jquery.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${ctx}/resources/js/new-js/page/wod.js"></script>
    <script src="${ctx}/resources/js/new-js/page/coachBackMenu.js"></script>
    <%
        request.setAttribute("vEnter", "\n");
        //奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
        //都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
    %>
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
                <li class="listCommon active" data-value="wod" href="${ctx}/coachWod/coachWodPage">训练计划</li>
                <li class="listCommon" data-value="order" href="${ctx}/coachBackReser/reservationGroup">预约列表</li>
                <li class="listCommon" data-value="board" href="${ctx}/coachBackBoard/coachBoard">白板</li>
                <li class="listCommon" data-value="ranking" href="${ctx}/coachBackRand/coachRank">排行榜</li>
                <li class="listCommon" data-value="analysis" href="${ctx}/coachBackAnalysis/coachAnalysis">数据分析</li>
            </ul>
        </div>
    </header>
</div>
<div class="box" style=" margin: 156px auto 0;">
    <div class="wodLeft fl clearfix">
        <select class="common wodOne" id="boxId">
            <c:forEach items="${boxList}" var="box">
                <option value="${box.id}">${box.name}</option>
            </c:forEach>
        </select>
        <p>训练馆</p>
        <select class="common wodOne" id="courseType">
            <c:forEach items="${courseTypeList}" var="courseType">
                <option value="${courseType.id}">${courseType.name}</option>
            </c:forEach>
        </select>
        <p>课程类型</p>
        <div class="date-box">
            <input size="16" type="text" value="" readonly class="common form_datetime">
        </div>
        <p>日期</p>
    </div>
    <div class="wodRight fr clearfix">
        <c:if test="${wodModel != null}">
            <div class="time">
                <span class="timeLeft">${date}</span>
                <span class="timeRight">${wodModel.wod.name}</span>
            </div>
            <div class="content">
                <c:forEach items="${wodModel.sectionModelList}" var="wodSection">
                    <c:if test="${wodSection.wodSection.type == 1}">
                        <h2 class="title">${wodSection.wodSection.section.title}</h2>
                        <p class="notes">${fn:replace(wodSection.wodSection.remark,vEnter,'<br/>')}</p>
                    </c:if>
                    <c:forEach items="${wodSection.wodContentList}" var="wodContent">
                        <c:choose>
                            <c:when test="${wodContent.contentType == 1}">
                                <h3 class="subtitle">
                                        ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                                        ${wodContent.contentEntity.name}
                                </h3>
                                <p class="record">${fn:replace(wodContent.contentEntity.description,vEnter,'<br/>')}</p>
                                <p class="notes">${fn:replace(wodContent.remark,vEnter,'<br/>')}</p>
                            </c:when>
                            <c:when test="${wodContent.contentType == '2'}">
                                <h3 class="subtitle">
                                        ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                                        ${wodContent.contentEntity.name}
                                        ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khl:''}
                                        ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? wodContent.repsScheme:''}
                                        ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khr:''}
                                </h3>
                                <p class="record">${fn:replace(wodContent.contentEntity.description,vEnter,'<br/>')}</p>
                                <p class="notes">${fn:replace(wodContent.remark,vEnter,'<br/>')}</p>
                            </c:when>
                            <c:when test="${wodContent.contentType == '3'}">
                                <h3 class="subtitle">
                                        ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                                        ${wodContent.contentEntity.name}
                                        ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khl:''}
                                        ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? wodContent.repsScheme:''}
                                        ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khr:''}
                                </h3>
                                <p class="record">${fn:replace(wodContent.contentEntity.description,vEnter,'<br/>')}</p>
                                <p class="notes">${fn:replace(wodContent.remark,vEnter,'<br/>')}</p>
                            </c:when>
                            <c:when test="${wodContent.contentType == '4' || wodContent.contentType == '5'}">
                                <h3 class="subtitle">
                                        ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                                        ${wodContent.contentEntity.name}
                                        ${khl}${wodContent.contentRecordTypeName}${khr}
                                </h3>
                                <p class="record">${fn:replace(wodContent.contentEntity.description,vEnter,'<br/>')}</p>
                                <p class="notes">${fn:replace(wodContent.remark,vEnter,'<br/>')}</p>
                            </c:when>
                            <c:when test="${wodContent.contentType == '6'}">
                                <h3 class="subtitle">
                                        ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                                    WarmUp(no measure)
                                </h3>
                                <p class="record">${fn:replace(wodContent.descript,vEnter,'<br/>')}</p>
                                <p class="notes">${fn:replace(wodContent.remark,vEnter,'<br/>')}</p>
                            </c:when>
                            <c:when test="${wodContent.contentType == '7'}">
                                <h3 class="subtitle">
                                        ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                                        ${wodContent.contentTitle == null || wodContent.contentTitle == "" ? 'Metcon':wodContent.contentTitle}
                                        ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khl:''}
                                        ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? wodContent.repsScheme:''}
                                        ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khr:''}
                                </h3>
                                <p class="record">${fn:replace(wodContent.descript,vEnter,'<br/>')}</p>
                                <p class="notes">${fn:replace(wodContent.remark,vEnter,'<br/>')}</p>
                            </c:when>
                            <c:when test="${wodContent.contentType == '8'}">
                                <h3 class="subtitle">
                                        ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                                        ${wodContent.contentTitle == null || wodContent.contentTitle == "" ? 'Metcon':wodContent.contentTitle}
                                        ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khl:''}
                                        ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? wodContent.repsScheme:''}
                                        ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khr:''}
                                </h3>
                                <p class="record">${fn:replace(wodContent.descript,vEnter,'<br/>')}</p>
                                <p class="notes">${fn:replace(wodContent.remark,vEnter,'<br/>')}</p>
                            </c:when>
                            <c:when test="${wodContent.contentType == '9'}">
                                <h3 class="subtitle">
                                        ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                                        ${wodContent.contentTitle == null || wodContent.contentTitle == "" ? 'Metcon':wodContent.contentTitle}
                                        ${khl}${wodContent.contentRecordTypeName}${khr}
                                </h3>
                                <p class="record">${fn:replace(wodContent.descript,vEnter,'<br/>')}</p>
                                <p class="notes">${fn:replace(wodContent.remark,vEnter,'<br/>')}</p>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </c:forEach>
            </div>
        </c:if>
    </div>
</div>
<input type="hidden" id="ctx" value="${ctx}"/>
</body>
</html>