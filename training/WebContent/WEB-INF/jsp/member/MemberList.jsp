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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/minimal/blue.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/member-list.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/myPage.css">
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="memberList" name="menuName"/>
        <jsp:param value="2" name="group"/>
    </jsp:include>
    <div class="main-content">
        <div class="member-list">
            <div id="bread" class="clearfix">
                <span class="breadFirst">会员管理</span>
                <span class="breadSecond"> > </span>
                <span class="breadThird">会员列表</span>
            </div>
            <div class="filter-line clearfix">
                <div class="search-box fl">
                    <span class="icon-search"></span>
                    <input type="text" placeholder="搜索用户名称或手机号..." id="searchKey">
                    <span class="btn-search fr" id="searchBut">搜索</span>
                </div>
                <div class="trains fl">
                    <select id="box">
                        <c:forEach items="${boxList}" var="box">
                            <option value="${box.id}">${box.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <a class="add-member fl" href="${ctx}/memberNew/memberAdd">添加</a>
            </div>
            <div class="list-box">
                <div class="list-filter clearfix">
                    <ul class="cards fl clearfix">
                        <li class="current fl" index="1">团课卡</li>
                        <li class="fl" index="2">私教卡</li>
                    </ul>
                    <ul class="card-status fl">
                        <li class="active fl" timeType="-1">全部用户<span></span></li>
                        <li class="fl" timeType="1">有效<span></span></li>
                        <li class="fl" timeType="2">即将过期<span></span></li>
                        <li class="fl" timeType="3">已经过期<span></span></li>
                    </ul>
                </div>
                <div class="list-block">
                    <div class="head-table clearfix">
                        <p class="name fl"><input type="checkbox" class="select-all"><label>姓名</label></p>
                        <div class="sex-box fl">
                            <select id="sex">
                                <option value="-1">全部</option>
                                <option value="0">男</option>
                                <option value="1">女</option>
                            </select>
                        </div>
                        <p class="sort-list birth fl" orderType="1">出生日期<span></span></p>
                        <p class="phone fl">手机号</p>
                        <p class="mail fl">邮箱</p>
                        <p class="run-card fl sort-list " orderType="2">初次办卡时间<span></span></p>
                        <p class="renewal-card fl sort-list" orderType="3">续卡时间<span></span></p>
                        <p class="expire fl sort-list" orderType="4">到期时间<span></span></p>
                        <div class="card-kind fl">
                            <select id="template">
                                <c:forEach items="${templateList}" var="template">
                                    <option value="${template.id}">${template.templateName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <p class="card-type fl">卡类型</p>
                        <p class="count fl">总次数</p>
                        <p class="status fl">状态</p>
                        <p class="latest-class fl">最近上课</p>
                        <p class="add-up-num fl sort-list" orderType="5">累计约课次数<span></span></p>
                        <p class="rate fl sort-list" orderType="6">累计约课频率（次/n天）<span></span></p>
                        <p class="order-class fl sort-list" orderType="7">当前约课次数<span></span></p>
                        <p class="order-rate fl sort-list" orderType="8">当前约课频率（次/n天）<span></span></p>
                        <p class="attendance fl sort-list" orderType="9">会员出勤率变化<span></span></p>
                    </div>
                    <ul class="data-list">
                        <c:forEach items="${pageHelper.list}" var="template">
                            <li class="clearfix">
                                <p class="name fl"><input type="checkbox" class="select-one" value="${template.memberCardId}"><label onclick="toDetail(this)" class="over-hide" memberId="${template.memberId}" boxid="${template.box}">${template.memberName}</label></p>
                                <p class="sex-box fl">${template.sexStr}</p>
                                <p class="birth fl">${template.birthday}</p>
                                <p class="phone fl">${template.phone}</p>
                                <p class="mail fl">${template.email}</p>
                                <p class="run-card fl">${template.firstOpenCardTime}</p>
                                <p class="renewal-card fl">${template.continueCardTime}</p>
                                <p class="expire fl">${template.cardEndTime}</p>
                                <p class="card-kind fl">${template.cardTemplateName}</p>
                                <p class="card-type fl">${template.memberCardType}</p>
                                <p class="count fl">${template.totalCount}</p>
                                <p class="status fl">${template.cardStatus}</p>
                                <p class="latest-class fl">${template.lastCourseTime}</p>
                                <p class="add-up-num fl">${template.orderCourseCount}</p>
                                <p class="rate fl">${template.orderCourseRate}</p>
                                <p class="order-class fl">${template.thisCardOrderCourseCount}</p>
                                <p class="order-rate fl">${template.thisCardOrderCourseRate}</p>
                                <p class="attendance fl">${template.attendanceRate}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="operation clearfix">
                    <p class="extend fl">延长<span></span></p>
                    <p class="export fl" id="exportExcel">导出<span></span></p>
                   <%-- <p class="import fl" id="importExcel">导入<span></span></p>--%>
                    <div style="text-align: center;padding-top: 60px; margin: 0 auto;">
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

            <!-- 延长弹框 -->
            <div class="extend-box">
                <div class="extend-block">
                    <div class="extend-block-title">您确定给xx位会员延长会员卡时间吗？</div>
                    <div class="extend-block-content">
                        <p>设置会员卡增加天数</p>
                        <div class="input">
                            <input type="text" id="lengthDays"><span>天</span>
                        </div>
                    </div>
                    <div class="extend-block-bottom clearfix">
                        <a href="javascript:;" class="sure-btn fr" id="lengthenBut">保存</a>
                        <a href="javascript:;" class="cancel-btn fr">取消</a>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>
<form:form method="post"  action="${ctx}/memberNew/memberSave" id="exportform">
</form:form>
<input type="hidden" id="ctx" value="${ctx}">
<input type="hidden" id="nowPageIndex"  value="1">
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/icheck.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/member-list.js"></script>
<script src="${ctx}/resources/js/new-js/page/jqPaginator.min.js"></script>
<script src="${ctx}/resources/js/new-js/popwin.js"></script>
</html>