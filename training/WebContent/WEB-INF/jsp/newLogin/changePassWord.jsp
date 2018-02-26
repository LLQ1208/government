<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/login/index.css">
    <script src="${ctx}/resources/js/new-js/jquery.js"></script>
    <script src="${ctx}/resources/js/new-js/login/changePassWord.js"></script>
    <title>CFer3.0训练馆</title>
</head>
<body>

<div id="login" class="clearfix">
    <!--second change password start-->
    <div class="changeWord">
        <div class="changeTop">
            <div class="changeLogo fl"></div>
            <p class="fl">更改新密码</p>
        </div>
        <div class="changeBottom clearfix">
            <input type="password" placeholder="输入新密码" id="newPassWord">
            <input type="password" placeholder="再次输入新密码" id="reNewPassWord" style="margin-top: 20px;">
            <button class="confirm" style="cursor: pointer;" onclick="savePassWord();">确定</button>
        </div>
    </div>
    <!--second change password end-->
    <input type="hidden" id="ctx" value="${ctx}"/>
</div>
<script>
    $('#login').css({'height': $(window).height()})
</script>
</body>
</html>