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
    <style>
        .blank{
            width: 100%;
            min-height: 768px;
            /*background: url("../images/login/login.jpg") no-repeat;*/
            background-size: cover;
        }
        .content-box{
            width: 1324px;
            min-height: 768px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div class="content-box">
    <jsp:include page="menu.jsp" flush="false" >
        <jsp:param value="index" name="menuName"/>
        <jsp:param value="0" name="group"/>
    </jsp:include>
    <div class="main-content" style="width: 1033px">
        <div class="blank"></div>
    </div>
    <input type="hidden" id="ctx" value="${ctx}"/>
</div>
</body>
<script>
    $(function () {
        $('.content-box').css({"height": $(window).height()});
    });
</script>
</html>