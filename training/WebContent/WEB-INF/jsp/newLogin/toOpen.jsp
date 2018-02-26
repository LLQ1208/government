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
    <script src="${ctx}/resources/js/new-js/login/toOpen.js"></script>
    <style type="text/css">
        .redColor{
            color: red;
        }
        .gray-color{
            background: #a7a3a3
        }
    </style>
    <title>CFer3.0训练馆</title>
</head>
<body>

<div id="login" class="clearfix">

    <!--open start-->
    <div class="open">
        <div class="forgetWordTop">
            <div class="changeLogo fl"></div>
            <p class="fl">我要开馆</p>
        </div>
        <div class="openInformation">
            <div class="openUser openCommon">
                <span class="openUserIcon"></span>
                <input type="text" placeholder="设置用户名" id="userName">
                <span  class="toopen-mesg" id="userNameNotice" style="display: none;">5-25个字符，一旦设置无法修改</span>
            </div>
            <div class="openWord openCommon">
                <span class="openWordIcon"></span>
                <input type="password" placeholder="设置登录密码" id="passWord">
                <span  class="toopen-mesg" id="passWordNotice" style="display: none;top:0">6-20个字符，密码和用户名不能相同，只能包含字母、数字以及标点符号（空格除外）</span>
            </div>
            <div class="openAgain openCommon">
                <span class="openAgainIcon"></span>
                <input type="password" placeholder="再次输入登录密码" id="rePassWord">
                <span class="toopen-mesg" style="display: none;" id="rePassWordNotice">密码不一致</span>
            </div>
            <div class="openPhone openCommon">
                <span class="openPhoneIcon"></span>
                <input type="text" placeholder="请输入手机号" id="phone">
                <span class="toopen-mesg" style="display: none;" id="phoneNotice" >请输入正确的手机号</span>
            </div>
            <div class="rules clearfix">
                <input type="checkbox" class="fl agreePopus1" id="agreeService" checked="true" onchange="toAgree(this)">
                <span class="fl">同意<a href="${ctx}/toAgree" target="_blank">《CFer系统服务条款》</a></span>
            </div>
            <button class="openBtn" onclick="agreeToNext()">注册开馆</button>
        </div>
        <div class="agreePopusBg">
            <div class="agreePopus">
                <div class="agreePopusClose"></div>
                <div class="agreePopusContent">
                    <p class="agreePopusTitle">验证手机</p>
                    <p class="agreePopusPhoneNumber">手机号：<span class="agreePopusPhone">13000001111</span></p>
                    <div class="agreePopusCode clearfix">
                        <input type="text" placeholder="输入验证码" class="fl" id="smscode">
                        <%--<div class="agreePopusGetCode fr">重新发送 <span>59s</span></div>--%>
                        <input type="button" class="agreePopusGetCode fr" id="sendSms" onclick="toSendSMS()" value="重新发送59s"/>
                    </div>
                    <p class="agreePopusTip">请输入您收到的短信验证码</p>
                    <button class="agreePopusConfirm" onclick="toRegister()">确认</button>
                </div>
            </div>
        </div>

    </div>
    <!--open end-->
    <input type="hidden" id="ctx" value="${ctx}"/>
</div>
<script type="text/javascript">
</script>
</body>
</html>