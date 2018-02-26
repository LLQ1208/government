<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<html lang="en">
<% Integer isNew = (Integer)request.getAttribute("isNew");%>
<head>
    <meta charset="UTF-8">
    <title>添加</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/mail.css">
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="mailList" name="menuName"/>
        <jsp:param value="7" name="group"/>
    </jsp:include>
    <div class="main-content">
        <div class="mail">
            <div id="bread" class="clearfix">
                <span class="breadFirst">邮件管理</span>
                <span class="breadSecond"> > </span>
                <span class="breadThird">添加邮件</span>
            </div>
            <div class="mail-content">
                <div class="mail-data">
                    <div class="insert clearfix">
                        <p class="fl">可插入数据:</p>
                        <a herf="javascript:;" class="fl">会员姓名</a>
                    </div>
                    <div class="rule clearfix">
                        <p class="fl">邮件发送规则:</p>
                        <div class="fl rule-btns">
                            <a href="javascript:;">生日</a>
                            <a href="javascript:;">即将到期时间</a>
                            <a href="javascript:;">到期</a>
                        </div>
                        <span></span>
                    </div>
                </div>
                <%--<div class="mail-input addressee">--%>
                    <%--<input type="text">--%>
                    <%--<p>收件人</p>--%>
                <%--</div>--%>
                <div class="mail-input theme">
                    <input type="text" id="title">
                    <p>主题</p>
                </div>
                <div class="editor-box">
                    <div id="editor"></div>
                </div>
                <div class="btn-box clearfix">
                    <a href="javascript:;" id="save" class="send-btn fl">保存</a>
                    <a href="javascript:;" id="update" class="send-btn fl">保存</a>
                    <a href= "javascript:void(0)" onclick="cancleToList()" class="cancel-btn fl">取消</a>
                    <a href="javascript:;" id="deleted" class="send-btn fl">删除</a>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="ctx" value="${ctx}"/>
    <input type="hidden" id="mailId" value="${mailId}">
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/wangEditor.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/mail.js"></script>
<script>
    <%--var content = "${mail.content}";--%>
</script>
</html>