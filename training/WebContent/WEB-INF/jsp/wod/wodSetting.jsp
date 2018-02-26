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
    <title>WOD设置</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/calendar.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/calendar-page.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/wodSetting.css">
    <script src="${ctx}/resources/js/new-js/jquery.js"></script>
    <script src="${ctx}/resources/js/new-js/page/wodSetting.js"></script>
</head>
<body>
<div class="content-box">
<jsp:include page="../menu.jsp" flush="false" >
    <jsp:param value="wodSetting" name="menuName"/>
    <jsp:param value="1" name="group"/>
</jsp:include>
    <div class="main-content">
<div class="setting">
    <div id="bread" class="clearfix">
        <span class="breadFirst">WOD</span>&nbsp;
        <span class="breadSecond"> > </span>&nbsp;
        <span class="breadThird">设置</span>
    </div>
    <div class="settingDeail">
        <div class="title">
            <p class="titleTip">WOD公布设置  </p>
            <p class="tip">此处决定你的会员所看到WOD的时间</p>
        </div>
        <div class="editor clearfix">
            <p>在CFer APP 公布</p>
            <p>设置在CFer APP 中公布的时间</p>
            <p class="showChoose showAppTime">${appTime}</p>
            <div class="editorBtn">编辑</div>
            <div class="backEditor clearfix">
                <div class="cancel fl">取消</div>
                <div class="confirm fl appTimeConfirm">保存</div>
            </div>
            <div class="choose">
                <select name="select" id="appDay" class="chooseCommon">
                    <c:forEach items="${dayMap}" var="dayEntry">
                        <option value="${dayEntry.key}">${dayEntry.value}</option>
                    </c:forEach>
                </select>
                <select name="select"  id="appHour" class="chooseCommon">
                    <c:forEach items="${hourMap}" var="hourEntry">
                        <option value="${hourEntry.key}">${hourEntry.value}</option>
                    </c:forEach>
                </select>
                <select name="select" id="appMinus" class="chooseCommon">
                    <option value="0">00分</option>
                    <option value="1">15分</option>
                    <option value="2">30分</option>
                    <option value="3">45分</option>
                </select>
            </div>
        </div>
        <div class="editor clearfix">
            <p>WOD日历展示周数</p>
            <p>选择WOD日历展示的周数</p>
            <p class="showChoose ">展示：<span class="showWodWeek">${wodWeek}</span></p>
            <div class="editorBtn">编辑</div>
            <div class="backEditor clearfix">
                <div class="cancel fl">取消</div>
                <div class="confirm fl weekConfirm">保存</div>
            </div>
            <div class="choose">
                <select name="select" id="wodWeek" class="chooseCommon">
                    <option value="1">一周</option>
                    <option value="2">二周</option>
                    <option value="3">三周</option>
                    <option value="4">四周</option>
                </select>
            </div>
        </div>
    </div>
    <input type="hidden" id="ctx" value="${ctx}"/>
</div>
    </div>
</div>
</body>
</html>