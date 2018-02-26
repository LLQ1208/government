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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/red.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/member/template-add.css">
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="memberSetting" name="menuName"/>
        <jsp:param value="2" name="group"/>
    </jsp:include>
    <div class="main-content" >
        <div class="template-add">
            <div id="bread" class="clearfix">
                <span class="breadFirst">会员管理</span>
                <span class="breadSecond"> > </span>
                <span class="breadFirst">设置模版</span>
                <span class="breadSecond"> > </span>
                <span class="breadThird">添加时限卡模版</span>
            </div>
            <div class="temp-box">
                <div class="name-block">
                    <input type="text" placeholder="请输入模版名称" id="name" maxlength="50" onkeyup="listenInput(this)">
                    <p>名称</p>
                </div>
                <div class="card-kind">
                    <input type="radio" name="kind" class="time-limit" checked value="1">
                    <label>时限卡</label>
                    <input type="radio" name="kind" class="count-limit" value="2">
                    <label>次卡</label>
                </div>
                <div class="detail-block time-block">
                    <div class="card-detail clearfix">
                        <div class="detail-info fl">
                            <input type="text"  class="number_input" placeholder="整数（单位：天）" id="time_validity">
                            <p>有效期</p>
                        </div>
                        <div class="detail-info fl">
                            <input type="text" class="number_input" placeholder="整数（单位：RMB）" id="time_money">
                            <p>费用</p>
                        </div>
                        <div class="detail-info fl">
                            <input type="text" class="number_input" placeholder="整数（单位：天）" id="time_expireDay">
                            <p>到期提醒</p>
                        </div>
                    </div>
                    <div class="select-detail">
                        <p class="select-title">选择训练馆<span>此处选项决定会员可以训练的训练馆</span></p>
                        <div class="select-box clearfix">
                            <div class="one fl">
                                <input type="checkbox" class="gym-all time-gym-all" id="time_allBox">
                                <label>全部</label>
                            </div>
                            <c:forEach items="${boxList}" var="box">
                                <div class="one fl">
                                    <input type="checkbox" name="time_box" class="gym-one time-gym-one" value="${box.id}">
                                    <label>${box.name}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="select-detail">
                        <p class="select-title">选择课程<span>此处选项决定会员可以预约的课程</span></p>
                        <div class="select-box clearfix">
                            <div class="one fl">
                                <input type="checkbox" class="class-all time-class-all" id="time_allCourseType">
                                <label>全部</label>
                            </div>
                            <c:forEach items="${courseTypeList}" var="courseType">
                                <div class="one fl">
                                    <input type="checkbox" class="class-one time-class-one" name="time_courseType" value="${courseType.id}">
                                    <label>${courseType.name}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="select-detail">
                            <p class="select-title">选择邮件<span>选择发送给会员的邮件类型</span></p>
                            <div class="select-box clearfix">
                                <c:if test="${mailList != null && mailList.size() > 0}">
                                    <c:forEach items="${mailList}" var="mail">
                                        <div class="one fl">
                                            <input type="checkbox" class="email-one" value="${mail.id}" name="time_email">
                                            <label>${mail.title}</label>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                    </div>
                    <div class="remark">
                        <textarea cols="30" rows="10" placeholder="填写备注内容..." id="time_remark"></textarea>
                    </div>
                </div>
                <div class="detail-block count-block">
                    <div class="card-detail clearfix">
                        <div class="detail-info fl">
                            <input type="text" placeholder="整数（单位：次）" class="number_input" id="bout_count">
                            <p>次数</p>
                        </div>
                        <div class="detail-info fl">
                            <input type="text" placeholder="整数（单位：天）" class="number_input" id="bout_validity">
                            <p>有效期</p>
                        </div>
                        <div class="detail-info fl">
                            <input type="text" placeholder="整数（单位：RMB）" class="number_input" id="bout_money">
                            <p>费用</p>
                        </div>
                        <div class="detail-info fl">
                            <input type="text" placeholder="整数（单位：天）" class="number_input" id="bout_expireDay">
                            <p>到期提醒</p>
                        </div>
                    </div>
                    <div class="select-detail">
                        <p class="select-title">选择训练馆<span>此处选项决定会员可以训练的训练馆</span></p>
                        <div class="select-box clearfix">
                            <div class="one fl">
                                <input type="checkbox" class="gym-all bout-gym-all" id="bout_allBox">
                                <label>全部</label>
                            </div>
                            <c:forEach items="${boxList}" var="box">
                                <div class="one fl">
                                    <input type="checkbox" name="bout_box" class="gym-one bout-gym-one" value="${box.id}">
                                    <label>${box.name}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="select-detail class-detail">
                        <p class="select-title">选择课程<span>此处选项决定会员可以预约的课程</span></p>
                        <div class="select-type">
                            <select id="select-type">
                                <option value="1">团体课</option>
                                <option value="2">私教课</option>
                            </select>
                        </div>
                        <div class="select-box clearfix">
                            <div class="one fl">
                                <input type="checkbox" class="class-all bout-class-all" id="bout_allCourseType">
                                <label>全部</label>
                            </div>
                            <c:forEach items="${courseTypeList}" var="courseType">
                                <div class="one fl">
                                    <input type="checkbox" name="bout_courseType" class="class-one bout-class-one" value="${courseType.id}">
                                    <label>${courseType.name}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="select-detail">
                        <p class="select-title">选择邮件<span>选择发送给会员的邮件类型</span></p>
                        <div class="select-box clearfix">
                            <c:if test="${mailList != null && mailList.size() > 0}">
                                <c:forEach items="${mailList}" var="mail">
                                    <div class="one fl">
                                        <input type="checkbox" class="email-one" value="${mail.id}" name="bout_email">
                                        <label>${mail.title}</label>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                    <div class="remark">
                        <textarea cols="30" rows="10" placeholder="填写备注内容..." id="bout_remark"></textarea>
                    </div>
                </div>
            </div>
            <div class="btns clearfix">
                <a href="javascript:;" class="cancel-btn fl">取消</a>
                <a href="javascript:;" class="save-btn fl">保存</a>
            </div>
        </div>
    </div>
    <input id="ctx" value="${ctx}" type="hidden">
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/icheck.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/template-add.js"></script>
</html>