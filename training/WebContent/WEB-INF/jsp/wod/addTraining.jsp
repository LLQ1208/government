<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/index/index.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/red.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/add-training.css" />
</head>
<body>
<div class="content-box">
<jsp:include page="../menu.jsp" flush="false" >
    <jsp:param value="calendar" name="menuName"/>
    <jsp:param value="1" name="group"/>
</jsp:include>
    <div class="main-content">
    <div class="add-training">
        <div id="bread" class="clearfix">
            <span class="breadFirst">WOD</span>
            <span class="breadSecond"> > </span>
            <span class="breadFirst">日历</span>
            <span class="breadSecond"> > </span>
            <span class="breadThird">训练计划</span>
        </div>
        <div class="training-top clearfix">
            <p class="fl">基本信息</p>
            <div class="btns fr">
                <span class="cancel-btn">取消</span>
                <span class="save-btn">下一步</span>
            </div>
        </div>
        <div class="set-info">
            <p class="info-title">设置训练计划基本信息</p>
            <div class="select-info clearfix">
                <div class="type-box fl">
                    <select class="selectpicker" id="courseTypeId">
                        <c:forEach items="${courseTypes}" var="courseType">
                            <c:choose>
                                <c:when test="${courseTypeId == courseType.id}">
                                    <option value="${courseType.id}" selected>${courseType.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${courseType.id}">${courseType.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    <p class="select-name">类型</p>
                </div>
                <div class="name-box fl">
                    <input id="wodName" class="add-name" type="text" placeholder="请输入训练计划名称"/>
                    <p class="select-name">训练计划名称</p>
                </div>
                <div class="fr add-remark">
                    <img class="fl" src="${ctx}/resources/images/new-image/training/icon_add.png" alt="">
                    <span class="fr">添加备注</span>
                </div>
            </div>
            <p class="time-line">${wodTime}</p>
        </div>

        <div class="select-block clearfix">
            <div class="fl left-block">
                <p class="block-title">选择此WOD将要展示的场馆</p>
                <div class="all-box">
                    <input type="checkbox" class="select-all">
                    <span>选择全部</span>
                </div>
                <ul class="list-box">
                    <c:forEach items="${boxs}" var="box">
                        <li>
                            <input type="checkbox" class="select-one" value="${box.id}">
                            <span>${box.name}</span>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="fr right-block">
                <p class="block-title">设置训练计划公布时间</p>
                <div class="date-block clearfix">
                    <p class="fl years">${showTime}</p>
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

    <!--添加备注弹框-->
    <div class="mask-box" id="remarkDiv">
        <div class="remark-box">
            <div class="box-title clearfix">
                <p class="fl">添加备注</p>
                <span class="fr close-btn"></span>
            </div>
            <div class="box-content">
                <textarea cols="30" rows="10" id="remarkText"></textarea>
                <span class="sure-btn" id="closeSpan">确定</span>
            </div>
        </div>
    </div>
    <input type="hidden" id="ctx" value="${ctx}"/>
    <input type="hidden" id="time" value="${time}"/>
    <input type="hidden" id="munitus" value="${munitus}"/>
</div>
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/icheck.js"></script>
<script src="${ctx}/resources/js/new-js/page/add-training.js"></script>
</html>