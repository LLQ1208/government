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
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/red.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/personal/personalview.css">
    <style>
        .sex{
            width: 200px;
            height: 34px;
        }

    </style>
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="" name="menuName"/>
        <jsp:param value="0" name="group"/>
    </jsp:include>
    <div class="main-content">
<div class="member-view">
    <input type="hidden" id="userId" value="${userId}">
    <div id="bread" class="clearfix">
        <span class="breadFirst">会员管理</span>
        <span class="breadSecond"> > </span>
        <span class="breadFirst">会员列表</span>
        <span class="breadSecond"> > </span>
        <span class="breadThird">泰勒斯威夫特</span>
    </div>
    <%--<div class="renewal clearfix">--%>
        <%--<a href="javascript:;" class="renewal-btn fr">续卡</a>--%>
    <%--</div>--%>
    <div class="head clearfix">
        <div class="headcont clearfix">
            <div class="left fl" style="height: 100%;">
                <%--<input type="file">--%>
                <%--<div class="pic"></div>--%>
                <%--<p class="changpic">编辑头像</p>--%>
                    <iframe id="iframe" src="${ctx}/personalCenter/imgIframe" width="100%" height="100%" scrolling="no" frameborder="0"  allowtransparency="true" ></iframe>
            </div>
            <div class="right fl clearfix">
                <div class="one">
                    <p class="name">
                        <input class="edit-info" id="userName" type="text" value="${name}" >
                        <span class="showValue">${name}</span>
                        <span class="rewrite"></span>
                    </p>
                    <div class="sex fl" >
                        <div class="edit-info" style="display: none" id="sexInput">
                            <input type="radio" class="male" name="sex" value="0">
                            <label>男</label>
                            <input type="radio" class="female" name="sex" value="1">
                            <label>女</label>
                        </div>
                        <span class="showValue">${sex == 0 ? '男' : '女'}</span>
                        <span class="rewrite"></span>
                    </div>
                    <%--<p class="sex">--%>
                        <%--<input class="edit-info" id="sex" type="text" value="${sex}" >--%>
                        <%--<span>${sex}</span>--%>
                        <%--<span class="rewrite"></span>--%>
                    <%--</p>--%>
                </div>
                <p class="commonp">
                    <span>出生日期：</span>
                    <input size="16" type="text" value="${birth}" id="birthday" readonly class="edit-info form_datetime"  style="cursor: pointer;">
                    <span class="showValue">${birth}</span>
                    <span class="rewrite"></span>
                </p>
                <p class="commonp">
                    <span>手机号：</span>
                    <input class="edit-info" id="phone" type="text" value="${phone}" >
                    <span class="showValue" >${phone}</span>
                    <span class="rewrite"></span>
                </p>
                <p class="commonp">
                    <span>邮箱：</span>
                    <input class="edit-info" id="email" type="text" value="${email}" >
                    <span class="showValue">${email}</span>
                    <span class="rewrite"></span>
                </p>
            </div>
        </div>
        <div class="edit">
            <textarea name="" id="remark" cols="30" rows="10" placeholder="备注：">${remark}</textarea>
        </div>
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
<script src="${ctx}/resources/js/new-js/page/personalview.js"></script>
</html>