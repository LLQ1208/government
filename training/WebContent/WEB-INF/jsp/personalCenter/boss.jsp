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
    <title>老板主页</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/boss.css">
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="" name="menuName"/>
        <jsp:param value="0" name="group"/>
    </jsp:include>
    <div class="main-content">
<div class="boss">
    <div id="bread" class="clearfix">
        <span class="breadThird">老板主页</span>
    </div>
    <form:form method="post" id="boxInformation" name="boxInformation" action="${ctx}/personalCenter/updateBossInfo" commandName="user" enctype="multipart/form-data">
    <div class="boss-box clearfix">
        <div class="logo-box fl">
            <div class="img-box fl" id="traningLogo" style="width: 84px;height: 84px;background-image: url(${user.ico});
                    background-size: cover;border-radius: 50%;"></div>
            <div class="file">
                <input type="file" id="img" name="img" onchange="previewImage(this);" accept="image/gif,image/jpg,image/png"/>
            </div>
        </div>

        <div class="info-box fl">
            <input class="textBoss"  id="bossName" type="text" name="userName" readonly value="${user.userName}">
            <p><span>手机号：</span><input id="bossPhone" type="text" name="phone" value="${user.phone}"></p>
            <p><span>邮箱：</span><input id="bossEmail" name="email" type="text" value="${user.email}"></p>
            <input type="hidden" id="bossId" name="id" value="${user.id}">
        </div>
        <div class="fr" style="width: 96px;">
            <button class="save-btn" id="saveBoss" type="submit" onclick="return bossInfoUpdate()" id="submitBut">信息保存</button>
            <%--<a href="javascript:;" id="saveBoss" class="build-btn fr" style="margin-top: 20px">信息保存</a>--%>
            </form:form>
            <a href="javascript:;" id="addBox" class="build-btn fr" style="margin-top: 18px">新增训练馆</a>
        </div>
    </div>

    <div class="boss-list">
        <div class="boss-head clearfix">
            <p class="logo-box fl">训练馆logo</p>
            <p class="name-box fl">训练馆名称</p>
            <p class="owner-box fl">店长</p>
            <p class="address-box fl">训练馆地址</p>
            <p class="operation fl">操作</p>
        </div>
        <ul class="boss-list-block">
            <c:forEach items="${pager.list}" var="box">
            <li class="clearfix">
                <p class="logo-box fl"><span><img style="width: 60px; height: 60px; object-fit: contain;" src="${box.logo}"></span></p>
                <p class="name-box fl">${box.name} </p>
                <p class="owner-box fl">${box.contact}</p>
                <p class="address-box fl">${box.address}</p>
                <div class="operation fl">
                    <a href="javascript:;" class="edit-btn">编辑</a>
                    <c:if test="${box.status eq 1}">
                        <a href="javascript:;" class="disable-btn">禁用</a>
                    </c:if>
                    <c:if test="${box.status eq 0}">
                        <a href="javascript:;" style="background-color:#cccccc" class="start-btn">启用</a>
                    </c:if>

                    <a href="javascript:;" class="delete-btn">删除</a>
                    <input type="hidden" class="boxId" value="${box.id}">
                </div>

            </li>
            </c:forEach>
        </ul>
    </div>
</div>
    </div>
    <input type="hidden" id="ctx" value="${ctx}" />
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/boss.js"></script>
</html>