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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/red.css">
    <style type="text/css">
        .butn-class{
            margin: 20px;
            margin-left: 142px;
            width: 73px;
            height: 29px;
            background: #fc340e;
            color: #fff;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="content-box">
    <form:form method="post" id="boxInformation" name="boxInformation" action="${ctx}/memberNew/uploadMemberSave" commandName="memberModel" enctype="multipart/form-data">
        <div style="height: 68px;margin-left: 49px;">
            <input type="file" style="width:200px;margin-top: 30px;" class="input-text" id="file" name="file" placeholder="excel文件"/>
        </div>
        <div style="height: 30px;">
            <button type="submit" id="upload" class="butn-class">上传</button>
        </div>
    </form:form>
    <input type="hidden" id="ctx" value="${ctx}">
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
</html>