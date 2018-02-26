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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/member/member-view.css">
    <style type="text/css">
        .main-content{
            min-height: inherit;
            margin-left: 0;
        }
        .left{
            width: 122px;
            position: relative;
        }

        .left input {
            width: 74px;
            height: 25px;
            position: absolute;
            top: 168px;
            left: 22px;
            opacity: 0;
            filter: alpha(opacify=0); }
        .left .pic {
            width: 100%;
            height: 122px;
            margin-top: 24px;
        //background: url("../../images/temp/temp.jpg");
            background-size: 100%; }
        .left .changpic {
            width: 74px;
            height: 25px;
            line-height: 25px;
            text-align: center;
            margin: 20px auto 0;
            font-size: 14px;
            color: #fff;
            background: #ff350e; }
    </style>
</head>
<body>
    <div class="main-content" style="width: 122px;height: 208px">
        <form:form method="post" id="boxInformation" name="boxInformation" action="${ctx}/personalCenter/savePersonalInfo" commandName="box" enctype="multipart/form-data">
            <div class="left fl">
                <input type="file" id="img" name="img" onchange="previewImage(this);" accept="image/gif,image/jpg,image/png"/>
                <div class="pic">
                    <c:choose>
                        <c:when test="${img == null || img==''}">
                            <img src="${ctx}/resources/images/new-image/no_pic.png" id="imghead" class="fl" alt="" style="width: 120px;height: 120px;border-radius: 50%;margin-right: 18px;">
                        </c:when>
                        <c:otherwise>
                            <img src="${img}" id="imghead" class="fl" alt="" style="width: 120px;height: 120px;border-radius: 50%;margin-right: 18px;">
                        </c:otherwise>
                    </c:choose>
                </div>
                <p class="changpic">编辑头像</p>
            </div>
            <%--<input value="${userId}" id="memberId" type="hidden" name = "memberId">--%>
            <button type="submit" style="display: none" id="submitBut">提交</button>
        </form:form>
        <input type="hidden" id="ctx" value="${ctx}" />
    </div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/resources/js/new-js/page/PersonalViewIframe.js"></script>
</html>