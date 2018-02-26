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
    <title>邮件列表</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-dialog.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/font-awesome.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/mail-list.css">
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="mailList" name="menuName"/>
        <jsp:param value="7" name="group"/>
    </jsp:include>
    <div class="main-content">
<div class="mail-list">

    <div id="bread" class="clearfix">
        <span class="breadFirst">邮件管理</span>
        <span class="breadSecond"> > </span>
        <span class="breadThird">邮件列表</span>
    </div>
    <div class="add-block clearfix">
        <a href="${ctx}/mailManage/mailAdd" class="add-btn fr">新建</a>
    </div>
    <div class="mail-list-box">
        <div class="head-block clearfix">
            <p class="name fl">名称</p>
            <p class="time fl">创建时间</p>
            <p class="person fl">操作人</p>
        </div>
        <ul class="mail-list-block">
            <c:forEach items="${pager.list}" var="mail">
            <li class="clearfix">
                <p class="name fl">${mail.title}</p>
                <p class="time fl"><fmt:formatDate value="${mail.insertTime}" pattern="yyyy-MM-dd"></fmt:formatDate></p>
                <p class="person fl">${mail.userName}</p>
                <input type="hidden" class="mailId" value="${mail.id}">
            </li>
            </c:forEach>
        </ul>
    </div>
    <div style="text-align: center;">
        <ul class="pagination" id="pagination">
        </ul>
        <input type="hidden" id="PageCount" runat="server" value="${pageCount}"/>
        <input type="hidden" id="PageSize" runat="server" value="10" />
        <input type="hidden" id="countindex" runat="server" value="10"/>
        <!--设置最多显示的页码数 可以手动设置 默认为7-->
        <input type="hidden" id="visiblePages" runat="server" value="5" />
    </div>
</div>
    </div>
    <input type="hidden" id="ctx" value="${ctx}"/>
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/jqPaginator.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/mail-list.js"></script>
</html>