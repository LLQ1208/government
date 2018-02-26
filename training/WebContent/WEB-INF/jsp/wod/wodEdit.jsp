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
    <title>WOD设置</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/index/index.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/red.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/add-training.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/wodEditor.css">
    <style type="text/css">
        .myReps{
            width: 308px;
            min-height: 30px;
            border: 1px solid #ddd;
            resize: none;
            margin-left: 102px;
            display: inline-block;
            outline: none;
        }
    </style>
    <%
        request.setAttribute("vEnter", "\n");
        //奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
        //都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
    %>
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="calendar" name="menuName"/>
        <jsp:param value="1" name="group"/>
    </jsp:include>
    <div class="wodEditor main-content" >
        <input type="hidden" value="${wodId}" id="wodId">
        <div id="bread" class="clearfix">
            <span class="breadFirst">WOD</span>
            <span class="breadSecond"> > </span>
            <span class="breadThird">日历</span>
            <span class="breadSecond"> > </span>
            <span>编辑WOD</span>
            <div class="delete_but">删除</div>
        </div>
        <!--head-->
        <div class="wodName clearfix">
            <div class="title">
                <p class="titleLeft fl" id="showWodName">${wod.name}</p>
                <p class="titleRight fl">预览</p>
            </div>
            <p class="date">日期：<fmt:formatDate value="${wod.wDate}" pattern="yyyy/MM/dd"></fmt:formatDate></p>
            <p class="type" id="showCourseTypeName">类型：${courseTypeName}</p>
            <p class="traningGym" id="showBoxName">训练馆：${boxName}</p>
            <div class="nameEditor">编辑</div>
        </div>

        <!--编辑-->
        <div class="edit-box">
            <!--添加备注弹框-->
            <div class="mask-box" id="wodRemark" style="z-index: 99;">
                <div class="remark-box">
                    <div class="box-title clearfix">
                        <p class="fl">添加备注</p>
                        <span class="fr close-btn" id="wodRemarkClose"></span>
                    </div>
                    <div class="box-content">
                        <textarea cols="30" rows="10" id="wodCommentContent"></textarea>
                        <span class="sure-btn" id="wodRemarkSure">确定</span>
                    </div>
                </div>
            </div>
            <div class="add-training">
                <div class="training-top clearfix">
                    <p class="fl">基本信息</p>
                    <div class="btns fr">
                        <span class="cancel-btn">取消</span>
                        <span class="save-btn">保存</span>
                    </div>
                </div>
                <div class="set-info">
                    <p class="info-title">设置训练计划基本信息</p>
                    <div class="select-info clearfix">
                        <div class="type-box fl">
                            <select class="selectpicker" id="wodCourseType">
                                <c:forEach items="${courseTypes}" var="courseType">
                                    <option value="${courseType.id}">${courseType.name}</option>
                                </c:forEach>
                            </select>
                            <p class="select-name">类型</p>
                        </div>
                        <div class="name-box fl">
                            <input class="add-name" id="wodEditName" type="text" placeholder="请输入训练计划名称"/>
                            <p class="select-name">训练计划名称</p>
                        </div>
                       <%-- <div class="fr add-remark" id="toAddRemark">
                            <img class="fl" src="${ctx}/resources/images/new-image/training/icon_add.png" alt="">
                            <span class="fr" >添加备注</span>
                        </div>--%>

                    </div>
                    <p class="time-line"><fmt:formatDate value="${wod.wDate}" pattern="yyyy/MM/dd"></fmt:formatDate></p>
                </div>

                <div class="select-block clearfix">
                    <div class="fl left-block">
                        <p class="block-title">选择此WOD将要展示的场馆</p>
                        <div class="all-box">
                            <input type="checkbox" class="select-all">
                            <span >选择全部</span>
                        </div>
                        <ul class="list-box">
                            <c:forEach items="${boxs}" var="box">
                                <li class="box_li">
                                    <input type="checkbox" id="boxCheckBox${box.id}" class="select-one boxCheckBox" value="${box.id}">
                                    <span class="box_name">${box.name}</span>
                                </li>
                            </c:forEach>
                        </ul>
                        <input type="hidden" id="wodCommentValue" value="">
                    </div>
                    <div class="fr right-block">
                        <p class="block-title">设置训练计划公布时间</p>
                        <div class="date-block clearfix">
                            <p class="fl years" id="wodShowDate"><fmt:formatDate value="${showDate}" pattern="yyyy/MM/dd"></fmt:formatDate></p>
                            <div class="date-box fl">
                                <select class="hours" id="hours">
                                    <option value="0">0点</option>
                                    <option value="1">1点</option>
                                    <option value="2">2点</option>
                                    <option value="3">3点</option>
                                    <option value="4">4点</option>
                                    <option value="5">5点</option>
                                    <option value="6">6点</option>
                                    <option value="7">7点</option>
                                    <option value="8">8点</option>
                                    <option value="9">9点</option>
                                    <option value="10">10点</option>
                                    <option value="11">11点</option>
                                    <option value="12">12点</option>
                                    <option value="13">13点</option>
                                    <option value="14">14点</option>
                                    <option value="15">15点</option>
                                    <option value="16">16点</option>
                                    <option value="17">17点</option>
                                    <option value="18">18点</option>
                                    <option value="19">19点</option>
                                    <option value="20">20点</option>
                                    <option value="21">21点</option>
                                    <option value="22">22点</option>
                                    <option value="23">23点</option>
                                </select>
                            </div>
                            <div class="date-box fl">
                                <select class="minus" id="minus">
                                    <option value="0">00分</option>
                                    <option value="1">15分</option>
                                    <option value="2">30分</option>
                                    <option value="3">45分</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div id="contnet" class=" clearfix">
            <!--add-->
            <div class="add clearfix">
                <p class="addEditor">+ WOD 编辑</p>
                <div class="addMiddle">

                    <select id="section" data-live-search="true">
                        <option disabled value="" selected>添加section</option>
                        <c:forEach items="${sectionList}" var="section">
                            <option value="${section.sectionId}" >${section.title}</option>
                        </c:forEach>
                    </select>
                    <select id="content" data-live-search="true">
                        <option value="" disabled selected>添加Content</option>
                        <c:forEach items="${generalContentList}" var="generalContent">
                            <option value="${generalContent.contentId}" contentType="${generalContent.contentType}">${generalContent.name}</option>
                        </c:forEach>
                    </select>
                    <select id="content1" data-live-search="true">
                        <option value="" disabled selected>添加自定义Content</option>
                        <c:forEach items="${customizeContentTypeMap}" var="contentType">
                            <option value="${contentType.key}">${contentType.value}</option>
                        </c:forEach>
                    </select>
                </div>

            </div>
            <!--拖拽start　-->
            <div class="addleft clearfix" id="sortable">
                <c:forEach items="${wodModel.sectionModelList}" var="wodSection">
                <c:choose>
                <c:when test="${wodSection.wodSection.type==0}">
                <div class="section-box ui-state-disabled section-box-top" id="section${wodSection.wodSection.wodSectionId}" style="display:none">
                    </c:when>
                    <c:otherwise>
                    <div class="section-box ui-state-default section-show" id="section${wodSection.wodSection.wodSectionId}">
                        </c:otherwise>
                        </c:choose>

                        <div class="metcon clearfix metconA sectionShow">
                            <h3 class="changeTitle" id="secionTitle${wodSection.wodSection.wodSectionId}">${wodSection.wodSection.section.title}</h3>
                            <p class="p1" id="wodSecionCommentShow${wodSection.wodSection.wodSectionId}">${fn:replace(wodSection.wodSection.remark,vEnter,'<br/>')}</p>
                        </div>
                        <div class="addContent addContentA clearfix sectionEdit">
                            <div class="addSection clearfix">
                                <div class="middle clearfix sectionShow">
                                    <div class="middleLeft clearfix single">
                                        <div class="sectionBtn sectionBtnsingle">
                                            <div class="cancle fl">取消</div>
                                            <div class="confirm fl">保存</div>
                                        </div>
                                        <div class="chodse clearfix">
                                            <select  class="clean singleSel" id="sectionSel${wodSection.wodSection.wodSectionId}">
                                                <c:forEach items="${sectionList}" var="section">
                                                    <c:choose>
                                                        <c:when test="${section.sectionId == wodSection.wodSection.section.sectionId}">
                                                            <option value="${section.sectionId}" selected>${section.title}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${section.sectionId}">${section.title}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="clearfix number singleNumber">TYPE</div>
                                        <div class="comment comment3 singlecomment sectionCommentTitle" >
                                            <span class="jia"></span> COMMENT
                                        </div>
                                        <div class="down2 down">
                                            <div class="weight">
                                            <textarea class="weightT singleweightT" id="sectionComment${wodSection.wodSection.wodSectionId}" onclick="this.focus()">
                                                    ${wodSection.wodSection.remark}
                                            </textarea>
                                                <span class="circle circle2"></span>
                                            </div>
                                            <div class="comment comment2 singlecomment">COMMENT</div>
                                        </div>
                                    </div>
                                </div>
                                <div class="addSectionCancel"> 删除</div>
                            </div>
                        </div>
                        <input type="hidden" value="${wodSection.wodSection.wodSectionId}" class="sectionId">
                    </div>
                    <c:forEach items="${wodSection.wodContentList}" var="wodContent">
                        <c:choose>
                            <c:when test="${wodContent.contentType == '1'}">
                                <div class="addOne custon-weightlifting ui-state-default contentDiv" id="content${wodContent.wodContentId}">
                                    <div class="metcon clearfix metconA" >
                                        <h3 class="changeTitle"  id="contentTitle${wodContent.wodContentId}">${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}${wodContent.contentEntity.name}
                                        </h3>
                                        <p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow${wodContent.wodContentId}">
                                                ${fn:replace(wodContent.remark,vEnter,'<br/>')}
                                        </p>
                                    </div>
                                    <div class="addContent addContentA clearfix">
                                        <div class="addSection clearfix">
                                            <div class="sectionBtn">
                                                <div class="cancle fl">取消</div>
                                                <div class="confirm fl">保存</div>
                                            </div>
                                            <div class="middle clearfix">
                                                <div class="middleLeft clearfix fl">
                                                    <div class="title">Content</div>
                                                    <div class="chodse clearfix">
                                                        <input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)" value="${wodContent.seriaNum}" id="seriaNum${wodContent.wodContentId}">
                                                        <select  class="clean" id="contentSel${wodContent.wodContentId}">
                                                            <c:forEach items="${generalContentList}" var="generalContent">
                                                                <c:if test="${generalContent.contentType == wodContent.contentType}">
                                                                    <c:choose>
                                                                        <c:when test="${wodContent.contentEntity.contentId == generalContent.contentId}">
                                                                            <option value="${generalContent.contentId}" contentType="${generalContent.contentType}" selected>${generalContent.name}</option>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <option value="${generalContent.contentId}" contentType="${generalContent.contentType}">${generalContent.name}</option>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="clearfix number">
                                                        <p class="number1 fl" >序号</p>
                                                        <p class="number2 fl" >WARM-UP</p>
                                                    </div>
                                                    <div class="comment comment1">
                                                        <span class="jia"></span> COMMENT
                                                    </div>
                                                    <div class="down1 down">
                                                        <div class="weight">
                                                            <textarea class="weightT" onclick="this.focus()" id="contentComment${wodContent.wodContentId}"></textarea>
                                                            <span class="circle circle1"></span>
                                                        </div>
                                                        <div class="comment comment2">
                                                            COMMENT
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="middleright fl clearfix">
                                                    <div class="title">记录方式</div>
                                                    <p class="recode">记录no measure</p>
                                                </div>
                                            </div>
                                            <div class="addSectionCancel"> 删除</div>
                                        </div>
                                    </div>
                                    <input type="hidden" value="${wodContent.wodContentId}" class="contentId">
                                    <input type="hidden" value="${wodContent.contentType}" class="wodContentType">
                                    <input type="hidden" value="${wodSection.wodSection.wodSectionId}" class="contentOfwodsectionId">
                                    <input type="hidden" value="${wodContent.tIndex}" id="contentNum${wodContent.wodContentId}">
                                </div>
                            </c:when>
                            <c:when test="${wodContent.contentType == '2'}">
                                <div class="addOne custom-gymnastics ui-state-default contentDiv" id="content${wodContent.wodContentId}">
                                    <div class="metcon clearfix metconA" >
                                        <h3 class="changeTitle"  id="contentTitle${wodContent.wodContentId}">${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}${wodContent.contentEntity.name}
                                                ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khl:''}
                                                ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? wodContent.repsScheme:''}
                                                ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khr:''}
                                        </h3>
                                            <%--<p class="p1" id="contentShowDes${wodContent.wodContentId}">${wodContent.descript == null ? wodContent.contentEntity.description:wodContent.descript}</p>--%>
                                        <p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow${wodContent.wodContentId}">
                                                ${fn:replace(wodContent.remark,vEnter,'<br/>')}
                                        </p>
                                    </div>
                                    <div class="addContent addContentA clearfix">
                                        <div class="addSection clearfix ">
                                            <div class="sectionBtn">
                                                <div class="cancle fl">取消</div>
                                                <div class="confirm fl">保存</div>
                                            </div>
                                            <div class="middle clearfix">
                                                <div class="middleLeft clearfix fl">
                                                    <div class="title">Content</div>
                                                    <div class="chodse clearfix">
                                                        <input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)" id="seriaNum${wodContent.wodContentId}" value="${wodContent.tIndex}">
                                                        <select  class="clean" id="contentSel${wodContent.wodContentId}">
                                                            <c:forEach items="${generalContentList}" var="generalContent">
                                                                <c:if test="${generalContent.contentType == wodContent.contentType}">
                                                                    <c:choose>
                                                                        <c:when test="${wodContent.contentEntity.contentId == generalContent.contentId}">
                                                                            <option value="${generalContent.contentId}" contentType="${generalContent.contentType}" selected>${generalContent.name}</option>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <option value="${generalContent.contentId}" contentType="${generalContent.contentType}">${generalContent.name}</option>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="clearfix number">
                                                        <p class="number1 fl">序号</p>
                                                        <p class="number2 fl">GYMNASTICS</p>
                                                    </div>
                                                    <input type="text" class="set5" value="" onclick="fouceToLast(this)" value="${wodContent.descript}" id="contentEditReps${wodContent.wodContentId}">
                                                    <p class="rep">REP SCHEME</p>
                                                    <div class="comment comment1">
                                                        <span class="jia"></span> COMMENT
                                                    </div>
                                                    <div class="down1 down">
                                                        <div class="weight">
                                                            <textarea class="weightT" onclick="this.focus()"  id="contentComment${wodContent.wodContentId}"></textarea>
                                                            <span class="circle circle1"></span>
                                                        </div>
                                                        <div class="comment comment2">
                                                            COMMENT
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="middleright fl clearfix">
                                                    <div class="title">记录方式</div>
                                                    <p class="recode">记录REPS</p>
                                                    <div class="for clearfix for3">
                                                        <span class="spanfor">for</span>
                                                        <input type="text" onkeypress="return event.keyCode>=48&&event.keyCode<=57" ng-pattern="/[^a-zA-Z]/" value="${wodContent.conentContent == null ? 1:wodContent.conentContent.sets}" min="1"  onclick="fouceToLast(this)" id="contentRecordSet${wodContent.wodContentId}">
                                                        <span class="x">x</span>
                                                        <input type="text" disabled value="MAX" onclick="fouceToLast(this)">
                                                    </div>
                                                    <div class="sets">
                                                        <span class="ref">SETS</span>
                                                        <span class="ref">REPS</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="addSectionCancel"> 删除</div>
                                        </div>
                                    </div>
                                    <input type="hidden" value="${wodContent.wodContentId}" class="contentId">
                                    <input type="hidden" value="${wodContent.contentType}" class="wodContentType">
                                    <input type="hidden" value="${wodSection.wodSection.wodSectionId}" class="contentOfwodsectionId">
                                    <input type="hidden" value="${wodContent.tIndex}" id="contentNum${wodContent.wodContentId}">
                                </div>
                            </c:when>
                            <c:when test="${wodContent.contentType == '3'}">
                                <div class="addOne custon-weightlifting ui-state-default contentDiv" id="content${wodContent.wodContentId}">
                                    <div class="metcon clearfix metconA" >
                                        <h3 class="changeTitle"  id="contentTitle${wodContent.wodContentId}">${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}${wodContent.contentEntity.name}
                                                ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khl:''}
                                                ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? wodContent.repsScheme:''}
                                                ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khr:''}
                                        </h3>
                                            <%--<p class="p1" id="contentShowDes${wodContent.wodContentId}">${wodContent.descript == null ? wodContent.contentEntity.description:wodContent.descript}</p>--%>
                                        <p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow${wodContent.wodContentId}">
                                                ${fn:replace(wodContent.remark,vEnter,'<br/>')}
                                        </p>
                                    </div>
                                    <div class="addContent addContentA clearfix">
                                        <div class="addSection clearfix">
                                            <div class="sectionBtn">
                                                <div class="cancle fl">取消</div>
                                                <div class="confirm fl">保存</div>
                                            </div>
                                            <div class="middle clearfix">
                                                <div class="middleLeft clearfix fl">
                                                    <div class="title">Content</div>
                                                    <div class="chodse clearfix">
                                                        <input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)" id="seriaNum${wodContent.wodContentId}" value="${wodContent.tIndex}">
                                                        <select  class="clean" id="contentSel${wodContent.wodContentId}">
                                                            <c:forEach items="${generalContentList}" var="generalContent">
                                                                <c:if test="${generalContent.contentType == wodContent.contentType}">
                                                                    <c:choose>
                                                                        <c:when test="${wodContent.contentEntity.contentId == generalContent.contentId}">
                                                                            <option value="${generalContent.contentId}" contentType="${generalContent.contentType}" selected>${generalContent.name}</option>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <option value="${generalContent.contentId}" contentType="${generalContent.contentType}">${generalContent.name}</option>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="clearfix number">
                                                        <p class="number1 fl">序号</p>
                                                        <p class="number2 fl">WEIGHTLIFTING</p>
                                                    </div>
                                                    <input type="text" class="set5" placeholder="" maxlength="50" onclick="fouceToLast(this)" placeholder="Title this WEIGHTLIFTING" value="" id="contentEditReps${wodContent.wodContentId}">
                                                    <p class="rep">REP SCHEME</p>
                                                    <div class="comment comment1">
                                                        <span class="jia"></span> COMMENT
                                                    </div>
                                                    <div class="down1 down">
                                                        <div class="weight">
                                                            <textarea class="weightT" onclick="this.focus()" id="contentComment${wodContent.wodContentId}"></textarea>
                                                            <span class="circle circle1"></span>
                                                        </div>
                                                        <div class="comment comment2">
                                                            COMMENT
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="middleright fl clearfix">
                                                    <div class="title">记录方式</div>
                                                    <p class="recode">记录WEIGHT</p>
                                                    <div class="in clearfix">
                                                        <span class="spanin">in</span>
                                                        <select class="clean1" id="contentRecordUnit${wodContent.wodContentId}">
                                                            <option value="1">kg</option>
                                                            <option value="2">lb</option>
                                                        </select>
                                                    </div>
                                                    <div class="for clearfix">
                                                        <span class="spanfor">for</span>
                                                        <input class="clean1" type="text" onkeypress="return event.keyCode>=48&&event.keyCode<=57" ng-pattern="/[^a-zA-Z]/" min="1" value="${wodContent.conentContent == null ? 1:wodContent.conentContent.sets}" onclick="fouceToLast(this)" id="contentRecordSet${wodContent.wodContentId}">
                                                        <span class="x">x</span>
                                                        <input type="text" onkeypress="return event.keyCode>=48&&event.keyCode<=57" ng-pattern="/[^a-zA-Z]/" value="${wodContent.conentContent == null ? 1:wodContent.conentContent.resps}" min="1" onclick="fouceToLast(this)" id="contentRecordReps${wodContent.wodContentId}">
                                                    </div>
                                                    <div class="sets">
                                                        <span class="ref">SETS</span>
                                                        <span class="ref">REPS</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="addSectionCancel"> 删除</div>
                                        </div>
                                    </div>
                                    <input type="hidden" value="${wodContent.wodContentId}" class="contentId">
                                    <input type="hidden" value="${wodContent.contentType}" class="wodContentType">
                                    <input type="hidden" value="${wodSection.wodSection.wodSectionId}" class="contentOfwodsectionId">
                                    <input type="hidden" value="${wodContent.tIndex}" id="contentNum${wodContent.wodContentId}">
                                </div>
                            </c:when>
                            <c:when test="${wodContent.contentType == '4' || wodContent.contentType == '5'}">
                                <div class="addOne custon-weightlifting ui-state-default contentDiv" id="content${wodContent.wodContentId}">
                                    <div class="metcon clearfix metconA" >
                                        <h3 class="changeTitle"  id="contentTitle${wodContent.wodContentId}">${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}${wodContent.contentEntity.name}
                                                ${khl}${wodContent.contentRecordTypeName}${khr}
                                        </h3>
                                        <p class="p1" id="contentShowDes${wodContent.wodContentId}"> ${fn:replace(wodContent.contentEntity.description,vEnter,'<br/>')}</p>
                                        <p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow${wodContent.wodContentId}">
                                                ${fn:replace(wodContent.remark,vEnter,'<br/>')}
                                        </p>
                                    </div>
                                    <div class="addContent addContentA clearfix">
                                        <div class="addSection clearfix">
                                            <div class="sectionBtn">
                                                <div class="cancle fl">取消</div>
                                                <div class="confirm fl">保存</div>
                                            </div>
                                            <div class="middle clearfix">
                                                <div class="middleLeft clearfix fl">
                                                    <div class="title">Content</div>
                                                    <div class="chodse clearfix">
                                                        <input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)" id="seriaNum${wodContent.wodContentId}" value="${wodContent.seriaNum}">
                                                        <select  class="clean" id="contentSel${wodContent.wodContentId}" onchange="popularChange(this)">
                                                            <c:forEach items="${generalContentList}" var="generalContent">
                                                                <c:if test="${generalContent.contentType == wodContent.contentType}">
                                                                    <c:choose>
                                                                        <c:when test="${wodContent.contentEntity.contentId == generalContent.contentId}">
                                                                            <option value="${generalContent.contentId}" contentType="${generalContent.contentType}" selected>${generalContent.name}</option>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <option value="${generalContent.contentId}" contentType="${generalContent.contentType}">${generalContent.name}</option>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="clearfix number">
                                                        <p class="number1 fl">序号</p>
                                                        <p class="number2 fl">METCON</p>
                                                    </div>
                                                    <div style="height: auto;margin-left: 107px;"class="clearfix number">
                                                        <p style="text-indent:0px" class="write" id="gmetcondes${wodContent.wodContentId}"></p>
                                                    </div>
                                                    <div class="comment comment1">
                                                        <span class="jia"></span> COMMENT
                                                    </div>
                                                    <div class="down1 down">
                                                        <div class="weight">
                                                            <textarea class="weightT" onclick="this.focus()"  id="contentComment${wodContent.wodContentId}"></textarea>
                                                            <span class="circle circle1"></span>
                                                        </div>
                                                        <div class="comment comment2">
                                                            COMMENT
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="middleright fl clearfix">
                                                    <div class="title">记录方式</div>
                                                    <div class="recode">
                                                        <span id="metconRecord${wodContent.wodContentId}">记录</span>
                                                    </div>
                                                    <div class="rx">
                                                        <span>是否Rx+？</span>
                                                        <input class="rxt" type="radio" value ="1" id="isRecord${wodContent.wodContentId}" name="isRecord${wodContent.wodContentId}" >是
                                                        <input class="rxf" type="radio" value ="0" id="noRecord${wodContent.wodContentId}" name="isRecord${wodContent.wodContentId}" >否
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="addSectionCancel">删除</div>
                                        </div>
                                    </div>
                                    <input type="hidden" value="${wodContent.wodContentId}" class="contentId">
                                    <input type="hidden" value="${wodContent.contentType}" class="wodContentType">
                                    <input type="hidden" value="${wodSection.wodSection.wodSectionId}" class="contentOfwodsectionId">
                                    <input type="hidden" value="${wodContent.tIndex}" id="contentNum${wodContent.wodContentId}">
                                </div>
                            </c:when>
                            <c:when test="${wodContent.contentType == '6'}">
                                <div class="addOne custom-four ui-state-default contentDiv" id="content${wodContent.wodContentId}">
                                    <div class="metcon clearfix metconA" >
                                        <h3 class="changeTitle"  id="contentTitle${wodContent.wodContentId}">${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}WarmUp(no measure)</h3>
                                        <p class="p1" id="contentShowDes${wodContent.wodContentId}">${fn:replace(wodContent.descript,vEnter,'<br/>')}</p>
                                        <p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow${wodContent.wodContentId}">
                                                ${fn:replace(wodContent.remark,vEnter,'<br/>')}
                                        </p>
                                    </div>
                                    <div class="addContent clearfix addContentA">
                                        <div class="addSection1 clearfix ">
                                            <div class="sectionBtn">
                                                <div class="cancle fl">取消</div>
                                                <div class="confirm fl">保存</div>
                                            </div>
                                            <div class="middle clearfix">
                                                <div class="middleLeft clearfix fl">
                                                    <div class="title">Content</div>
                                                    <div class="chodse1 clearfix fl">
                                                        <input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)" id="seriaNum${wodContent.wodContentId}" value="${wodContent.tIndex}">
                                                        <p class="number1">序号</p>
                                                    </div>
                                                    <div class="chodseright1 fr">
                                                        <textarea class="weightT1" onclick="this.focus()" id="contentEditDes${wodContent.wodContentId}"></textarea>
                                                        <p class="number2 ">DETAILS</p>
                                                        <div class="comment comment1">
                                                            <span class="jia"></span> COMMENT
                                                        </div>
                                                        <div class="down down1">
                                                            <div class="weight">
                                                                <textarea class="weightT" onclick="this.focus()" id="contentComment${wodContent.wodContentId}"></textarea>
                                                                <span class="circle circle1"></span>
                                                            </div>
                                                            <div class="comment commentaa">
                                                                COMMENT
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="middleright fl clearfix">
                                                    <div class="title">记录方式</div>
                                                    <p class="recode">记录no measure</p>
                                                </div>
                                            </div>
                                            <div class="addSectionCancel"> 删除</div>
                                        </div>
                                    </div>
                                    <input type="hidden" value="${wodContent.wodContentId}" class="contentId">
                                    <input type="hidden" value="${wodContent.contentType}" class="wodContentType">
                                    <input type="hidden" value="${wodSection.wodSection.wodSectionId}" class="contentOfwodsectionId">
                                    <input type="hidden" value="${wodContent.tIndex}" id="contentNum${wodContent.wodContentId}">
                                </div>
                            </c:when>
                            <c:when test="${wodContent.contentType == '7'}">
                                <div class="addOne custom-gymnastics ui-state-default contentDiv" id="content${wodContent.wodContentId}">
                                    <div class="metcon clearfix metconA" >
                                        <h3 class="changeTitle"  id="contentTitle${wodContent.wodContentId}">${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}${wodContent.contentTitle == null || wodContent.contentTitle == "" ? 'Gymnastics':wodContent.contentTitle}
                                                ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khl:''}
                                                ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? wodContent.repsScheme:''}
                                                ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khr:''}
                                        </h3>
                                        <p class="p1" id="contentShowDes${wodContent.wodContentId}">${fn:replace(wodContent.descript,vEnter,'<br/>')}</p>
                                        <p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow${wodContent.wodContentId}">
                                                ${fn:replace(wodContent.remark,vEnter,'<br/>')}
                                        </p>
                                    </div>
                                    <div class="addContent addContentA clearfix">
                                        <div class="addSection clearfix ">
                                            <div class="sectionBtn">
                                                <div class="cancle fl">取消</div>
                                                <div class="confirm fl">保存</div>
                                            </div>
                                            <div class="middle clearfix">
                                                <div class="middleLeft clearfix fl">
                                                    <div class="title">Content</div>
                                                    <div class="chodse clearfix">
                                                        <input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)" id="seriaNum${wodContent.wodContentId}" value="${wodContent.tIndex}">
                                                        <input style="width: 308px; margin: 0; text-align: left;" type="text" maxlength="100" placeholder="Title this GYMNASTICS" onclick="fouceToLast(this)" id="contentSaveTitle${wodContent.wodContentId}">
                                                    </div>
                                                    <div class="clearfix number">
                                                        <p class="number1 fl">序号</p>
                                                        <p class="number2 fl">GYMNASTICS TITLE*</p>
                                                    </div>
                                                    <div class="weight" style="width: 308px;">
                                                        <textarea class="weightT" onclick="fouceToLast(this)" placeholder="Type the details of the gymnastics" id="contentEditDes${wodContent.wodContentId}"></textarea>
                                                    </div>
                                                    <p class="rep">DETAIL</p>
                                                    <input type="text" class="set5" value="" onclick="fouceToLast(this)" value="" id="contentEditReps${wodContent.wodContentId}">
                                                    <p class="rep">REP SCHEME</p>
                                                    <div class="comment comment1">
                                                        <span class="jia"></span> COMMENT
                                                    </div>
                                                    <div class="down1 down">
                                                        <div class="weight">
                                                            <textarea class="weightT" onclick="this.focus()" id="contentComment${wodContent.wodContentId}"></textarea>
                                                            <span class="circle circle1"></span>
                                                        </div>
                                                        <div class="comment comment2">
                                                            COMMENT
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="middleright fl clearfix">
                                                    <div class="title">记录方式</div>
                                                    <p class="recode">记录REPS</p>
                                                    <div class="for clearfix for3">
                                                        <span class="spanfor">for</span>
                                                        <input type="text"  onkeypress="return event.keyCode>=48&&event.keyCode<=57" ng-pattern="/[^a-zA-Z]/"  value="${wodContent.conentContent == null ? 1:wodContent.conentContent.sets}" min="1" onclick="fouceToLast(this)" id="contentRecordSet${wodContent.wodContentId}">
                                                        <span class="x">x</span>
                                                        <input type="text" disabled value="MAX" onclick="fouceToLast(this)">
                                                    </div>
                                                    <div class="sets">
                                                        <span class="ref">SETS</span>
                                                        <span class="ref">REPS</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="addSectionCancel"> 删除</div>
                                        </div>
                                    </div>
                                    <input type="hidden" value="${wodContent.wodContentId}" class="contentId">
                                    <input type="hidden" value="${wodContent.contentType}" class="wodContentType">
                                    <input type="hidden" value="${wodSection.wodSection.wodSectionId}" class="contentOfwodsectionId">
                                    <input type="hidden" value="${wodContent.tIndex}" id="contentNum${wodContent.wodContentId}">
                                </div>
                            </c:when>
                            <c:when test="${wodContent.contentType == '8'}">
                                <div class="addOne custon-weightlifting ui-state-default contentDiv" id="content${wodContent.wodContentId}">
                                    <div class="metcon clearfix metconA" >
                                        <h3 class="changeTitle"  id="contentTitle${wodContent.wodContentId}">${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}${wodContent.contentTitle == null || wodContent.contentTitle == "" ? 'Weightliting':wodContent.contentTitle}
                                                ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khl:''}
                                                ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? wodContent.repsScheme:''}
                                                ${wodContent.repsScheme!="" && wodContent.repsScheme !=null ? khr:''}
                                        </h3>
                                        <p class="p1" id="contentShowDes${wodContent.wodContentId}">${fn:replace(wodContent.descript,vEnter,'<br/>')}</p>
                                        <p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow${wodContent.wodContentId}">
                                                ${fn:replace(wodContent.remark,vEnter,'<br/>')}
                                        </p>
                                    </div>
                                    <div class="addContent addContentA clearfix">
                                        <div class="addSection clearfix">
                                            <div class="sectionBtn">
                                                <div class="cancle fl">取消</div>
                                                <div class="confirm fl">保存</div>
                                            </div>
                                            <div class="middle clearfix">
                                                <div class="middleLeft clearfix fl">
                                                    <div class="title">Content</div>
                                                    <div class="chodse clearfix">
                                                        <input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)" id="seriaNum${wodContent.wodContentId}" value="${wodContent.tIndex}">
                                                        <input style="width: 308px; margin: 0; text-align: left;" type="text" maxlength="100" placeholder="Title this WEIGHTLIFTING" onclick="fouceToLast(this)" id="contentSaveTitle${wodContent.wodContentId}">
                                                    </div>
                                                    <div class="clearfix number">
                                                        <p class="number1 fl">序号</p>
                                                        <p class="number2 fl">WEIGHTLIFTING TITLE*</p>
                                                    </div>
                                                    <div class="weight" style="width: 308px;">
                                                        <textarea class="weightT" onclick="fouceToLast(this)" placeholder="Type the details of the weightliftine" id="contentEditDes${wodContent.wodContentId}"></textarea>
                                                    </div>
                                                    <p class="rep">DETAIL</p>
                                                    <input type="text" class="set5" placeholder="" maxlength="50" onclick="fouceToLast(this)"  id="contentEditReps${wodContent.wodContentId}">
                                                    <p class="rep">REP SCHEME</p>
                                                    <div class="comment comment1">
                                                        <span class="jia"></span> COMMENT
                                                    </div>
                                                    <div class="down1 down">
                                                        <div class="weight">
                                                            <textarea class="weightT" onclick="this.focus()"  id="contentComment${wodContent.wodContentId}"></textarea>
                                                            <span class="circle circle1"></span>
                                                        </div>
                                                        <div class="comment comment2">
                                                            COMMENT
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="middleright fl clearfix">
                                                    <div class="title">记录方式</div>
                                                    <p class="recode">记录WEIGHT</p>
                                                    <div class="in clearfix">
                                                        <span class="spanin">in</span>
                                                        <select class="clean1">
                                                            <option value="">kg</option>
                                                        </select>
                                                    </div>
                                                    <div class="for clearfix">
                                                        <span class="spanfor">for</span>
                                                        <input class="clean1" type="text" onkeypress="return event.keyCode>=48&&event.keyCode<=57" ng-pattern="/[^a-zA-Z]/" min="1" value="${wodContent.conentContent == null ? 1:wodContent.conentContent.sets}" onclick="fouceToLast(this)" id="contentRecordSet${wodContent.wodContentId}">
                                                        <span class="x">x</span>
                                                        <input type="text" onkeypress="return event.keyCode>=48&&event.keyCode<=57" ng-pattern="/[^a-zA-Z]/" value="${wodContent.conentContent == null ? 1:wodContent.conentContent.resps}" min="1" onclick="fouceToLast(this)" id="contentRecordReps${wodContent.wodContentId}">
                                                    </div>
                                                    <div class="sets">
                                                        <span class="ref">SETS</span>
                                                        <span class="ref">REPS</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="addSectionCancel"> 删除</div>
                                        </div>
                                    </div>
                                    <input type="hidden" value="${wodContent.wodContentId}" class="contentId">
                                    <input type="hidden" value="${wodContent.contentType}" class="wodContentType">
                                    <input type="hidden" value="${wodSection.wodSection.wodSectionId}" class="contentOfwodsectionId">
                                    <input type="hidden" value="${wodContent.tIndex}" id="contentNum${wodContent.wodContentId}">
                                </div>
                            </c:when>
                            <c:when test="${wodContent.contentType == '9'}">
                                <div class="addOne custom-metcon ui-state-default contentDiv" id="content${wodContent.wodContentId}">
                                    <div class="metcon clearfix metconA" >
                                        <h3 class="changeTitle"  id="contentTitle${wodContent.wodContentId}">${wodContent.seriaNum}${wodContent.seriaNum!="" && wodContent.seriaNum !=null ? '、':''}
                                                ${wodContent.contentTitle == null || wodContent.contentTitle == "" ? 'Metcon':wodContent.contentTitle}
                                                ${khl}${wodContent.contentRecordTypeName}${khr}
                                        </h3>
                                        <p class="p1" id="contentShowDes${wodContent.wodContentId}"> ${fn:replace(wodContent.descript,vEnter,'<br/>')}</p>
                                        <p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow${wodContent.wodContentId}">
                                                ${fn:replace(wodContent.remark,vEnter,'<br/>')}
                                        </p>
                                    </div>
                                    <div class="addContent clearfix addContentA">
                                        <div class="addSection1 clearfix ">
                                            <div class="sectionBtn">
                                                <div class="cancle fl">取消</div>
                                                <div class="confirm fl">保存</div>
                                            </div>
                                            <div class="middle clearfix">
                                                <div class="middleLeft clearfix fl">
                                                    <div class="title">Content</div>
                                                    <div class="chodse1 clearfix fl">
                                                        <input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)"  value="${wodContent.tIndex}" id="seriaNum${wodContent.wodContentId}">
                                                        <p class="number1">序号</p>
                                                    </div>
                                                    <div class="chodseright1 fr">
                                                        <textarea class="weightT1" onclick="this.focus()" id="contentEditDes${wodContent.wodContentId}"></textarea>
                                                        <p class="number2 ">DETAILS</p>
                                                        <div class="comment comment1">
                                                            <span class="jia"></span> COMMENT
                                                        </div>
                                                        <div class="down down1">
                                                            <div class="weight">
                                                                <textarea class="weightT" onclick="this.focus()" id="contentComment${wodContent.wodContentId}"></textarea>
                                                                <span class="circle circle1"></span>
                                                            </div>
                                                            <div class="comment commentaa">
                                                                COMMENT
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="middleright fl clearfix ">
                                                    <div class="title">记录方式</div>
                                                    <div class="recode">
                                                        <span>记录</span>
                                                        <select name="" id="metcon-type${wodContent.wodContentId}" class="time custom_metcon_type">
                                                            <option value="1" ${(wodContent.conentContent == null ? 1:wodContent.conentContent.metconType) == 1 ? 'selected':''}>Time</option>
                                                            <option value="3" ${(wodContent.conentContent == null ? 1:wodContent.conentContent.metconType) == 3 ? 'selected':''}>AMRAP-Reps</option>
                                                                <%--<option value="2" ${(wodContent.conentContent == null ? 1:wodContent.conentContent.metconType) == 2 ? 'selected':''}>AMRAP-Rounds</option>--%>
                                                            <option value="4" ${(wodContent.conentContent == null ? 1:wodContent.conentContent.metconType) == 4 ? 'selected':''}>AMRAP-Rounds and Reps</option>
                                                            <option value="7" ${(wodContent.conentContent == null ? 1:wodContent.conentContent.metconType) == 7 ? 'selected':''}>Calories</option>
                                                            <option value="8" ${(wodContent.conentContent == null ? 1:wodContent.conentContent.metconType) == 8 ? 'selected':''}>No Measure</option>
                                                            <option value="10" ${(wodContent.conentContent == null ? 1:wodContent.conentContent.metconType) == 10 ? 'selected':''}>weight</option>
                                                            <option value="5" ${(wodContent.conentContent == null ? 1:wodContent.conentContent.metconType) == 5 ? 'selected':''}>each round</option>
                                                            <option value="6" ${(wodContent.conentContent == null ? 1:wodContent.conentContent.metconType) == 6 ? 'selected':''}>distance</option>
                                                        </select>
                                                    </div>
                                                    <div class="rx custom_content_general">
                                                        <span>是否Rx+？</span>
                                                        <input class="rxt" type="radio" name="generalRecordRx${wodContent.wodContentId}" value="1"  ${((wodContent.conentContent != null && wodContent.conentContent.metconType < 7) ? wodContent.conentContent.isRx:1) == 1 ? 'checked="true"':''}>是
                                                        <input class="rxf" type="radio" value="0" name="generalRecordRx${wodContent.wodContentId}" ${((wodContent.conentContent != null && wodContent.conentContent.metconType < 7) ? wodContent.conentContent.isRx:1) == 0 ? 'checked="true"':''}>否
                                                    </div>
                                                    <div class="recode custom_content_weight">
                                                        <span> 单位</span>
                                                        <select name="" class="time" id="weightRecordUnit${wodContent.wodContentId}">
                                                            <option value="1"  >kg</option>
                                                            <option value="2"  >lb</option>
                                                        </select>
                                                    </div>
                                                    <div class="rx custom_content_weight">
                                                        <span>是否Rx+？</span>
                                                        <input class="rxt" type="radio" value="1" name="weightRecordRx${wodContent.wodContentId}"  />是
                                                        <input class="rxf" type="radio" value="0" name="weightRecordRx${wodContent.wodContentId}" checked="true"/>否
                                                    </div>
                                                    <div class="recode custom_content_eachround">
                                                        <span> 规则</span>
                                                        <input type="text" class="rounds" value="10"  onclick="fouceToLast(this)"  id="eachRoundB${wodContent.wodContentId}">
                                                        <select  id="eachRoundRecord${wodContent.wodContentId}" class="time timeshort">
                                                            <option value="1">For Reps</option>
                                                            <option value="2">For Time</option>
                                                            <option value="3">For Weight</option>
                                                            <option value="4">For Distance</option>
                                                            <option value="5">For Calories</option>
                                                        </select>
                                                    </div>
                                                    <div class="recode unit unit-weight custom_content_eachround  eachRoundWeightSel">
                                                        <span> 单位</span>
                                                        <select  id="eachRoundUnit${wodContent.wodContentId}" class="time unit-weight-select">
                                                            <option value="1">kg</option>
                                                            <option value="2">lb</option>
                                                        </select>
                                                    </div>
                                                    <div class="recode unit unit-distance custom_content_eachround eachRoundDistanceSel" id="eachRoundDistanceSel${wodContent.wodContentId}">
                                                        <span> 单位</span>
                                                        <select  id="eachRoundA${wodContent.wodContentId}" class="time unit-distance-select eachRoundA">
                                                            <option value="3">miles</option>
                                                            <option value="4">meters</option>
                                                            <option value="5">km</option>
                                                            <option value="6">yards</option>
                                                            <option value="7">feet</option>
                                                            <option value="8">inches</option>
                                                            <option value="9">cm</option>
                                                        </select>
                                                    </div>
                                                    <div class="rx custom_content_eachround">
                                                        <span>是否Rx+？</span>
                                                        <input class="rxt" type="radio" value="1" name="eachRoundRx${wodContent.wodContentId}" >是
                                                        <input class="rxf" type="radio" value="0" name="eachRoundRx${wodContent.wodContentId}" checked="true">否
                                                    </div>
                                                    <div class="recode custom_content_distance">
                                                        <span>单位</span>
                                                        <select name="" id="distanceRecord${wodContent.wodContentId}" class="time">
                                                            <option value="3">meters</option>
                                                            <option value="4">miles</option>
                                                            <option value="5">km</option>
                                                            <option value="6">yards</option>
                                                            <option value="7">feet</option>
                                                            <option value="8">inches</option>
                                                            <option value="9">cm</option>
                                                        </select>
                                                    </div>
                                                    <div class="rx custom_content_distance">
                                                        <span>是否Rx+？</span>
                                                        <input class="rxt" type="radio" value="1" name="distanceRx${wodContent.wodContentId}" >是
                                                        <input class="rxf" type="radio" value="0" name="distanceRx${wodContent.wodContentId}" checked="true">否
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="addSectionCancel"> 删除</div>
                                        </div>
                                    </div>
                                    <input type="hidden" value="${wodContent.wodContentId}" class="contentId">
                                    <input type="hidden" value="${wodContent.contentType}" class="wodContentType">
                                    <input type="hidden" value="${wodSection.wodSection.wodSectionId}" class="contentOfwodsectionId">
                                    <input type="hidden" value="${wodContent.tIndex}" id="contentNum${wodContent.wodContentId}">
                                </div>
                            </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    </c:forEach>

                </div>
                    <div class="mark ui-state-disabled  record_mark">

                    </div>
            </div>

            <input type="hidden" id="ctx" value="${ctx}"/>
        </div>
    </div>
</div>
<div class="pop-box">
    <strong class="close"></strong>
    <p class="pop-title">HappyHump Day!!</p>
    <div class="pop-block">
        <p class="little-title">Warm-up</p>
        <p class="little-content">Dynamic stretch</p>
    </div>
    <div class="pop-block">
        <p class="little-title">Gymnastics</p>
        <p class="little-content">Handstand Pushups</p>
    </div>
    <div class="pop-block">
        <p class="little-title">WOD</p>
        <div class="del-content">
            <p>1:Hetcon (AMRAPRound an Reps)</p>
            <span>6 mins AMRAP</span>
            <span>-8 SDHPs</span>
            <span>-8 TTBs</span>
        </div>
    </div>
</div>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/jquery-ui.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/icheck.js"></script>
<script src="${ctx}/resources/js/new-js/page/editWod.js"></script>
<script src="${ctx}/resources/js/new-js/page/wodEditor.js"></script>
</body>
</html>