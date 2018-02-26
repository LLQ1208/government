<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/login/index.css">
    <script src="${ctx}/resources/js/new-js/jquery.js"></script>
    <style type="text/css">
        .redColor{
            color: red;
        }
    </style>
    <title>CFer3.0训练馆</title>
</head>
<body>
<form:form method="post" id="boxInformation" name="boxInformation" action="${ctx}/toSaveInformation" commandName="box" enctype="multipart/form-data">
<div id="login" class="clearfix">

    <!--third fill in information start-->
    <div class="fillIn clearfix">
        <div class="fillTop">
            <div class="changeLogo fl"></div>
            <p class="fl">填写训练馆信息</p>
        </div>
        <div class="traningLogo1">
            <div class="traningLogo" id="traningLogo"></div>
            <div class="file">
                <%--<input class="serverImg" id="imgName" name="imgName" />--%>
                <input type="file" id="img" name="img" onchange="previewImage(this);" accept="image/gif,image/jpg,image/png"/>
            </div>
        </div>

        <div class="fillBottom clearfix">
            <input type="text" placeholder="训练馆名称" id="name" name="name" maxlength="20">
            <!--<input type="text" placeholder="训练馆地址">-->
            <!--<textarea id="textarea" onpropertychange="MaxMe(this)" oninput="MaxMe(this)" ontextinput="MaxMe(this)" style="overflow: hidden" placeholder="训练馆地址"></textarea>-->

            <div id="textarea" class="clearfix">
                <p contenteditable="true" class="pCover1" id="pAdress"></p >
                <span class="pCover">训练馆地址</span>
            </div>
            <input type="text" placeholder="联系电话" id="contactTel" name="contactTel" maxlength="50">
            <input type="text" placeholder="负责人姓名（老板）" id="contact" name="contact" maxlength="10">
            <input type="text" placeholder="电子邮箱" id="email" name="email" maxlength="30">
            <button class="fillInConfirm" type="submit" onclick="return saveBefore()" id="submitBut">注册开馆</button>
        </div>
    </div>
    <!--third fill in information end-->
    <input type="text" style="display:none" placeholder="训练馆地址" id="address" name="address">
</div>
</form:form>
<script src="${ctx}/resources/js/new-js/login/information.js"></script>
</body>
</html>