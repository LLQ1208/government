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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/board.css">
    <style type="text/css">
        .table-condensed>thead>tr>th, .table-condensed>tbody>tr>th, .table-condensed>tfoot>tr>th, .table-condensed>thead>tr>td, .table-condensed>tbody>tr>td, .table-condensed>tfoot>tr>td{cursor:pointer;}
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
                <li class="listCommon" data-value="wod" href="${ctx}/coachWod/coachWodPage">训练计划</li>
                <li class="listCommon" data-value="order" href="${ctx}/coachBackReser/reservationGroup">预约列表</li>
                <li class="listCommon active" data-value="board" href="${ctx}/coachBackBoard/coachBoard">白板</li>
                <li class="listCommon" data-value="ranking" href="${ctx}/coachBackRand/coachRank">排行榜</li>
                <li class="listCommon" data-value="analysis" href="${ctx}/coachBackAnalysis/coachAnalysis">数据分析</li>
            </ul>
        </div>
    </header>
</div>
<div class="box" style=" margin: 156px auto 0;">
    <div class="boardLeft fl clearfix" >
        <select class="common commono" id="boxId">
            <c:forEach items="${boxList}" var="box">
                <option value="${box.id}">${box.name}</option>
            </c:forEach>
        </select>
        <p>训练馆</p>
        <select class="common commono" id="courseType">
            <c:forEach items="${courseTypeList}" var="courseType">
                <option value="${courseType.id}">${courseType.name}</option>
            </c:forEach>
        </select>
        <p>课程类型</p>
        <input size="16" type="text" value="" readonly class="common form_datetime" style="text-align: center;">
        <p style="margin-top: 9px;">日期</p>
        <div class="textBtn common">
            <c:forEach items="${wodContentList}" var="wodContent" varStatus="wodContentStatus">
                <p class="text-Btn ${wodContentStatus.index == 0 ? 'active':''}" wodContentId="${wodContent.wodContentId}">
                    <c:choose>
                        <c:when test="${wodContent.contentType == '1'}">
                            ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                            ${wodContent.contentEntity.name}
                        </c:when>
                        <c:when test="${wodContent.contentType == '2' || wodContent.contentType == '3'}">
                            ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                            ${wodContent.contentEntity.name}
                            ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khl:''}
                            ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? wodContent.repsScheme:''}
                            ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khr:''}
                        </c:when>
                        <c:when test="${wodContent.contentType == '4' || wodContent.contentType == '5'}">
                            ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                            ${wodContent.contentEntity.name}
                            ${khl}${wodContent.contentRecordTypeName}${khr}
                        </c:when>
                        <c:when test="${wodContent.contentType == '6'}">
                            ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                            WarmUp(no measure)
                        </c:when>
                        <c:when test="${wodContent.contentType == '7' || wodContent.contentType == '8'}">
                            ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                            ${wodContent.contentTitle == null || wodContent.contentTitle == "" ? 'Metcon':wodContent.contentTitle}
                            ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khl:''}
                            ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? wodContent.repsScheme:''}
                            ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khr:''}
                        </c:when>
                        <c:when test="${wodContent.contentType == '9'}">
                            ${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                            ${wodContent.contentTitle == null || wodContent.contentTitle == "" ? 'Metcon':wodContent.contentTitle}
                            ${khl}${wodContent.contentRecordTypeName}${khr}
                        </c:when>
                    </c:choose>
                </p>
            </c:forEach>
        </div>
        <div class="common commonWod">
            <span class="pic"></span>
            <select class="wod" disabled>
                <option value="">${wodModel != null ? wodModel.wod.name : '无'}</option>
            </select>
        </div>
        <div class="type">
            <c:forEach items="${wodModel.sectionModelList}" var="wodSection">
                <div class="typeCommon">
                <c:if test="{wodSection.wodSection.type==1}">
                    <h2 class="title">${wodSection.wodSection.section.title}</h2>
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
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="boardRight fr clearfix">
        <div class="time">
            <span class="timeLeft">${date}</span>
            <span class="timeRight">${wodModel == null  ||wodModel.wod == null  ? '' : wodModel.wod .name}</span>
        </div>
        <div class="content clearfix">
            <div class="contentLeft fl contentCommon">
                <h5 class="contentTitle">MALE</h5>
                <ul class="list male-list">
                    <c:if test="${boardPage != null }">
                        <c:forEach items="${boardPage.maleList}" var="maleBoard" varStatus="maleStatus">
                            <li class="listCommon clearfix">
                                <div class="malePic fl">
                                    <c:if test="${maleBoard.ico == null || maleBoard.ico==''}">
                                        <img style="width: 100%;height: 100%;" src="${ctx}/resources/images/new-image/default_member.png"/>
                                    </c:if>
                                    <c:if test="${maleBoard.ico != null && maleBoard.ico!=''}">
                                        <img style="width: 100%;height: 100%;" src="${member.ico}"/>
                                    </c:if>
                                </div>
                                <div class="number">${maleStatus.index + 1}</div>
                                <div class="maleRight fl">
                                    <p class="one">${maleBoard.memberName}</p>
                                    <p class="two">${maleBoard.courseTitle}</p>
                                    <p class="three">${maleBoard.record}</p>
                                    <p class="four">${fmaleBoard.remark}</p>
                                </div>
                                <c:if test="${maleBoard.isRx == 1}">
                                    <div class="rx">Rx</div>
                                </c:if>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
            <div class="contentRight fl contentCommon">
                <h5 class="contentTitle">FEMALE</h5>
                <ul class="list fmale-list">
                    <c:if test="${boardPage != null }">
                        <c:forEach items="${boardPage.fmaleList}" var="fmaleBoard" varStatus="fmaleStatus">
                            <li class="listCommon clearfix">
                                <div class="malePic fl">
                                    <c:if test="${fmaleBoard.ico == null || fmaleBoard.ico==''}">
                                        <img style="width: 100%;height: 100%;" src="${ctx}/resources/images/new-image/default_member.png"/>
                                    </c:if>
                                    <c:if test="${fmaleBoard.ico != null && fmaleBoard.ico!=''}">
                                        <img style="width: 100%;height: 100%;" src="${member.ico}"/>
                                    </c:if>
                                </div>
                                <div class="number">${fmaleStatus.index + 1}</div>
                                <div class="maleRight fl">
                                    <p class="one">${fmaleBoard.memberName}</p>
                                    <p class="two">${fmaleBoard.courseTitle}</p>
                                    <p class="three">${fmaleBoard.record}</p>
                                    <p class="four">${fmaleBoard.remark}</p>
                                </div>
                                <c:if test="${fmaleBoard.isRx == 1}">
                                    <div class="rx">Rx</div>
                                </c:if>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="ctx" value="${ctx}"/>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/resources/js/new-js/page/board.js"></script>
<script src="${ctx}/resources/js/new-js/page/coachBackMenu.js"></script>
</html>