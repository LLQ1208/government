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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/red.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/personal.css">
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="" name="menuName"/>
        <jsp:param value="0" name="group"/>
    </jsp:include>
    <div class="main-content">
<div class="personal">
    <div id="bread" class="clearfix">
        <span class="breadThird">个人中心</span>
    </div>
    <div class="member-detail clearfix">
        <div class="member-base fl">
            <div class="head-input">
                <input type="file">
                <img src="../images/temp/temp.jpg" alt="">
            </div>
            <div class="sex">
                <span>性别</span>
                <input type="radio" class="male" name="sex" checked>
                <label>男</label>
                <input type="radio" class="female" name="sex">
                <label>女</label>
            </div>
            <div class="birth-box clearfix">
                <input size="16" type="text" value="" readonly class="form-datetime">
                <p>出生年月</p>
            </div>
        </div>
        <div class="member-info fl">
            <div class="member-info-box clearfix">
                <p class="fl">姓名:</p>
                <span class="fl">左浩</span>
            </div>
            <div class="member-info-box clearfix">
                <p class="fl">邮箱:</p>
                <span class="fl">liweisi101198job@hotmail.com</span>
            </div>
            <div class="member-info-box clearfix">
                <p class="fl">手机号:</p>
                <span class="fl">13709871234</span>
            </div>
        </div>
    </div>
    <div class="remark">
        <textarea cols="30" rows="10" placeholder="备注："></textarea>
    </div>
</div>
    </div>
    <input type="hidden" id="ctx" value="${ctx}" />
</div>
</body>

<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/icheck.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/resources/js/new-js/page/personal.js"></script>
</html>