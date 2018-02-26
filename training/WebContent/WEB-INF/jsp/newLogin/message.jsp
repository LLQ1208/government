<%@ page contentType="text/html;charset=UTF-8"%>
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
    <script src="${ctx}/resources/js/new-js/login/message.js"></script>
    <title>CFer3.0训练馆</title>
</head>
<body>

<div id="login" class="clearfix">
    <!--message start-->
    <div class="message">
        <div class="forgetWordTop">
            <div class="changeLogo fl"></div>
            <p class="fl">忘记密码短信验证</p>
        </div>
        <div class="messageContent clearfix">
            <p class="messageOne">手机验证码验证</p>
            <p class="messageTwo">账户<span id="userName"></span>为确认是你本人操作，
                请完成以下验证</p>
            <p class="messageThree">手机号：<span class="phoneNumber" id="phoneNumber"></span></p>
            <div class="messageInput clearfix">
                <input type="text" class="fl messageCode" id="phoneCode" placeholder="输入验证码">
                <%--<div class="getMessage fl" id="btn">获取验证码</div>--%>
                <%--<input class="getMessage fl"  type="button" id="btn" value="获取验证码" />--%>
                <input type="button" class="getMessage fl" id="sendSms" onclick="toSendSMS()" value="获取验证码"/>
            </div>
                <p class="tip">请输入您手机收到的短信验证码</p>
            <button class="messageConfirm" onclick="affirmBtn();">确认</button>
        </div>
    </div>
    <!--message end-->
    <input type="hidden" id="ctx" value="${ctx}"/>
</div>
<script>
    $('#login').css({'height':$(window).height()})
</script>
</body>
</html>