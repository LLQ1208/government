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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/member/set-template.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/myPage.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css">
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="memberSetting" name="menuName"/>
        <jsp:param value="2" name="group"/>
    </jsp:include>
    <div class="main-content" >
        <div class="set-template">
            <div id="bread" class="clearfix">
                <span class="breadFirst">会员管理</span>
                <span class="breadSecond"> > </span>
                <span class="breadThird">设置模版</span>
            </div>
            <div class="add-box clearfix">
                <a href="javascript:;" class="add-btn fr">添加</a>
            </div>
            <div class="template-box">
                <div class="temp-head clearfix">
                    <p class="name fl">名称</p>
                    <p class="name fl">卡类型</p>
                    <p class="price fl">费用</p>
                    <p class="classes fl">课程</p>
                </div>
                <ul class="temp-list">
                    <c:forEach items="${pageHelper.list}" var="template">
                        <li class="clearfix">
                            <p class="name template_name fl"  templateId = "${template.id}">${template.templateName}</p>
                            <p class="name fl">
                                <c:if test="${template.boutCardType != 2}">
                                    团课卡
                                </c:if>
                                <c:if test="${template.boutCardType == 2}">
                                    私教卡
                                </c:if>
                            </p>
                            <p class="price fl">${template.money}</p>
                            <p class="classes fl">${template.courseTypeIds}</p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div style="text-align: center;padding-top: 260px">
                <ul class="pagination" id="pagination">
                </ul>
                <input type="hidden" id="PageCount" runat="server" value="${pageHelper.totalRow}"/>
                <input type="hidden" id="PageSize" runat="server" value="20"/>
                <input type="hidden" id="countindex" runat="server" value="10"/>
                <!--设置最多显示的页码数 可以手动设置 默认为7-->
                <input type="hidden" id="visiblePages" runat="server" value="5"/>
            </div>
        </div>
    </div>
</div>
<input type="hidden" value="${ctx}" id="ctx"/>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/member_template.js"></script>
<script src="${ctx}/resources/js/new-js/page/jqPaginator.min.js"></script>
</html>