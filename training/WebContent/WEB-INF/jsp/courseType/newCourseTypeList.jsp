<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"  http-equiv="X-UA-Compatible" content="IE=edge">
    <title>课程类型</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-dialog.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/font-awesome.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/class-type.css" />
</head>
<body>
<div class="content-box">
<jsp:include page="../menu.jsp" flush="false" >
    <jsp:param value="class-type" name="menuName"/>
    <jsp:param value="4" name="group"/>
</jsp:include>
    <div class="main-content">
    <div class="class-type clearfix" style="width: 100%;">
        <div id="bread" class="clearfix">
            <span class="breadFirst">课程表</span>
            <span class="breadSecond"> > </span>
            <span class="breadThird">课程类型</span>
        </div>
        <div class="add-class">
            +课程类型
        </div>
        <div class="class-content">
            <div class="class-title clearfix">
                <div class="class-name fl">名称</div>
                <div class="class-remark fl">备注</div>
                <div class="class-time fr">创建时间</div>
            </div>
            <div class="class-list">
                <c:forEach items="${pager.list}" var="courseType">
                    <div class="list-common">
                        <div class="list-name fl" >${courseType.name}</div>
                        <div class="list-remarks fl" >${courseType.describe}</div>
                        <div class="list-time fr"><fmt:formatDate value="${courseType.insertTime}" pattern="yyyy-MM-dd"></fmt:formatDate></div>
                        <input type="hidden" class="describe" value="${courseType.describe}">
                        <input type="hidden" class="courseTypeColor" value="${courseType.color}">
                        <input type="hidden" class="courseTypeId" value="${courseType.id}">
                    </div>
                </c:forEach>
            </div>
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
        <!--tip-->
        <div class="tip-bg">
            <div class="class-tip">
                <input type="text" class="tip-name" style="text-align:left" maxlength="30">
                <p>名称</p>
                <textarea class="write" id="tipWrite">
                </textarea>
                <p>描述</p>
                Color: <input class="jscolor{onFineChange:'changeColor(this)'}" value="">
                <div class="class-btn">
                    <div class="confirm fr">保存</div>
                    <div class="cancel fr">取消</div>
                </div>
            </div>
        </div>
        <!--check-->
        <div class="check-bg">
            <div class="class-tip">
                <div class="del">
                    <p>您确定要删除这类课程吗？</p>
                    <div class="check-btn">
                        <div class="confirm2 fr">确定</div>
                        <div class="cancel2 fr">取消</div>
                    </div>
                </div>
                <input type="text" class="check-name">
                <p>名称</p>
                <textarea class="write" id="write">
                </textarea>
                <p>描述</p>
                Color: <input class="jscolor {onFineChange:'changeColor(this)'}" value="ab2567" id="courseClolor">
                <div class="class-btn">
                    <div class="delete fl">删除</div>
                    <div class="confirm1 fr">保存</div>
                    <div class="cancel1 fr">取消</div>
                </div>
            </div>
        </div>
        <input type="hidden" id="ctx" value="${ctx}"/>
        <input type="hidden" id="transId">
        <input type="hidden" id="sortType" value="0">
        <input type="hidden" id="deletedId">
        <input type="hidden" id="typeColor">
    </div>
    </div>
</div>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/jscolor.js"></script>
<script src="${ctx}/resources/js/new-js/page/jqPaginator.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/class-type.js"></script>

</body>
</html>