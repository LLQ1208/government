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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/gym-add.css">
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="" name="menuName"/>
        <jsp:param value="0" name="group"/>
    </jsp:include>
    <div class="main-content">
<div class="gym-add">
    <div id="bread" class="clearfix">
        <span class="breadFirst">老板主页</span>
        <span class="breadSecond"> > </span>
        <span class="breadThird">新增训练馆</span>
    </div>
    <form:form method="post" id="boxInformation" name="boxInformation" action="${ctx}/personalCenter/toSaveInformation" commandName="box" enctype="multipart/form-data">
    <div class="gym-box">
        <div class="logo-box">
            <c:if test="${boxId != -99}">
                <div class="traningLogo" id="traningLogo" style="width: 84px;height: 84px;background-image: url(${box.logo});
                        background-size: cover;border-radius: 50%;"></div>
            </c:if>
            <c:if test="${boxId == -99}">
                <div class="traningLogo" id="traningLogo"></div>
            </c:if>
            <%--<div class="traningLogo" id="traningLogo" style="width: 84px;height: 84px;background-image: url(${box.logo});--%>
                    <%--background-size: cover;border-radius: 50%;"></div>--%>
            <div class="file">
                <input type="file" id="img" name="img" onchange="previewImage(this);" accept="image/gif,image/jpg,image/png"/>
            </div>
            <%--<span></span>--%>
            <%--<input type="file">--%>
        </div>
        <div class="info-box">
            <div class="info-block clearfix">
                <p class="fl">训练馆名称：</p>
                <input type="text" id="name" name="name" class="fl" value="${box.name}">
            </div>
            <div class="info-block clearfix">
                <p class="fl">地址：</p>
                <input type="text" id="address" class="fl" name="address" value="${box.address}">
            </div>
            <div class="info-block clearfix">
                <p class="fl">联系电话：</p>
                <input type="text" id="phone" class="fl" name="contactTel" value="${box.phone}" >
            </div>
            <div class="info-block clearfix">
                <p class="fl">店长名：</p>
                <input type="text" class="fl" id="contact" name="contact" value="${box.contact}">
            </div>
            <div class="info-block clearfix">
                <p class="fl">邮箱：</p>
                <input type="text" class="fl" id="email" name="email" value="${box.email}">
            </div>
            <%--<div class="info-block clearfix">--%>
                <%--<p class="fl">密码：</p>--%>
                <%--<input type="text" class="fl">--%>
            <%--</div>--%>
        </div>
        <div class="btn-block clearfix">
            <a href="${ctx}/personalCenter/boss" class="cancel-btn">取消</a>
            <button class="save-btn" type="submit" onclick="return saveBefore()" id="submitBut">保存</button>
        </div>
        <input type="hidden" id="boxId" name="id" value="${boxId}">
        <input type="hidden" id="boxLogo" value="${box.logo}">
    </div>
    </form:form>
</div>
    </div>
    <input type="hidden" id="ctx" value="${ctx}" />
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/resources/js/new-js/page/gym-add.js"></script>
</html>