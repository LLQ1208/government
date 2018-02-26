<%@ page contentType="text/html;charset=UTF-8"%>
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
    <script src="${ctx}/resources/js/new-js/login/forgetPassWord.js"></script>
    <title>CFer3.0训练馆</title>
</head>
<body>

<div id="login" class="clearfix">
    <!--fourth forget start-->
    <div id="forgetWord">
        <div class="forgetWordTop">
            <div class="changeLogo fl"></div>
            <p class="fl">更改新密码</p>
        </div>
        <div class="forgetWordBottom clearfix">
            <input type="text" placeholder="输入用户名" id="userName">
            <div class="imgCode clearfix">
                <div class="imgCodeTop">
                    <input id="verifyCode" type="text" placeholder="输入验证码" class="short">
                    <img src="${ctx}/chkCode" id="chkCodeImg" alt="" class="imgCode1">
                </div>
                <div class="clickToRefresh" ><a href="javascript:;" onclick="document.getElementById('chkCodeImg').src='${ctx}/chkCode?date='+Math.random();">点击刷新验证码</a>  </div>
            </div>
            <button id = "affirm" class="forgetWordConfirm" >确认</button>
        </div>
    </div>
    <!--fourth forget end-->
    <input type="hidden" id="ctx" value="${ctx}"/>
</div>
<script type="text/javascript">
    $('#login').css({'height':$(window).height()})
</script>
</body>
</html>