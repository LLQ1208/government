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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/red.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/member-add.css">
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="memberList" name="menuName"/>
        <jsp:param value="2" name="group"/>
    </jsp:include>
    <div class="main-content" >
        <form:form method="post" id="boxInformation" name="boxInformation" action="${ctx}/memberNew/memberSave" commandName="memberModel" enctype="multipart/form-data">
        <div class="member-add">
            <div id="bread" class="clearfix">
                <span class="breadFirst">会员管理</span>
                <span class="breadSecond"> > </span>
                <span class="breadThird">添加会员</span>
            </div>
            <div class="member-detail clearfix">
                <div class="member-base fl">
                    <div class="head-input">
                        <div class="traningLogo" id="traningLogo" style="background-size: 100%;width: 120px;height: 120px;background-image: url(${ctx}/resources/images/new-image/no_pic.png);background-repeat: no-repeat;" ></div>
                        <input type="file" id="img" name="img" onchange="previewImage(this);" accept="image/gif,image/jpg,image/png"/>
                    </div>
                    <div class="sex">
                        <span>性别</span>
                        <input type="radio" class="male" name="sex" checked value="0">
                        <label>男</label>
                        <input type="radio" class="female" name="sex" value="1">
                        <label>女</label>
                    </div>
                    <div class="birth-box clearfix">
                        <input size="16" type="text" value="" readonly class="form-datetime" style="text-align: center;cursor: pointer;" id="birthday" name="birthday">
                        <p>出生年月</p>
                    </div>
                </div>
                <div class="member-info fl">
                    <div class="member-info-box">
                        <input type="text" placeholder="请输入会员姓名" maxlength="10" id="memberName" name="memberName">
                        <p>姓名</p>
                    </div>
                    <div class="member-info-box">
                        <input type="text" placeholder="请输入会员手机号" id="phone" maxlength="20" name="phone">
                        <p>手机号</p>
                    </div>
                    <div class="member-info-box">
                        <input type="text" placeholder="请输入会员电子邮箱" id="email" name="email" maxlength="50">
                        <p>电子邮箱</p>
                    </div>
                    <div class="member-info-box">
                        <select  id="box" name="box">
                            <c:forEach items="${boxList}" var="box">
                                <option value="${box.id}">${box.name}</option>
                            </c:forEach>
                        </select>
                        <p>办理会员卡的场馆</p>
                    </div>
                </div>
            </div>
            <div class="card-type">
                <h4>选择会员卡</h4>
                <div class="card-type-list">
                    <div class="head-top clearfix">
                        <p class="card-type-block fl">卡类型</p>
                        <p class="class-type-block fl">课程类型</p>
                    </div>
                    <ul>
                        <li class="clearfix">
                            <p class="card-type-block fl"><input class="custom" type="radio" name="type" checked  value="0">自定义</p>
                            <p class="class-type-block fl"></p>
                        </li>
                        <c:forEach items="${templateList}" var="template">
                            <li class="clearfix">
                                <p class="card-type-block fl"><input class="type" type="radio" name="type" value="${template.id}" templateType="${template.templateType}"  money="${template.money}" templateName="${template.templateName}">${template.templateName}</p>
                                <p class="class-type-block fl ">${template.courseTypeNames}</p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="quick-add">
                <h4>快速添加</h4>
                <div class="quick-add-box">
                    <div class="quick-add-custom">
                        <div class="custom-type">
                            <input type="radio" name="custom-type" checked class="count-type" value="2">
                            <label>次卡</label>
                            <input type="radio" name="custom-type" value="1">
                            <label>时限卡</label>
                        </div>
                        <div class="custom-info custom-info-count clearfix">
                            <div class="custom-info-block fl">
                                <input size="16" type="text" value="" readonly class="start-datetime" id="countStartTime" name="countStartTime" style="cursor: pointer;">
                                <p>开卡时间</p>
                            </div>
                            <div class="custom-info-block fl">
                                <input size="16" type="text" value="" readonly class="end-datetime" id="countEndTime" name="countEndTime" style="cursor: pointer;">
                                <p>到期时间</p>
                            </div>
                            <div class="custom-info-block expire-block fl">
                                <input type="text" value=""  placeholder="请输入天数" class="expire number_input" id="countExpireDay" name="countExpireDay">
                                <p>到期提醒（天）</p>
                            </div>
                            <div class="custom-info-block count-num-block fl">
                                <input type="text" value="" placeholder="请输入次数" class="count-num number_input" id="countNum" name="countNum">
                                <p>次数</p>
                            </div>
                            <div class="custom-info-block count-num-block fl">
                                <input type="text" value="" placeholder="请输入金额" class="count-num number_input" id="countMoney" name="countMoney">
                                <p>金额</p>
                            </div>
                        </div>
                        <div class="custom-info custom-info-time clearfix">
                            <div class="custom-info-block fl">
                                <input size="16" type="text" value="" readonly class="start-datetime" id="timeStartTime" name="timeStartTime" style="cursor: pointer;">
                                <p>开卡时间</p>
                            </div>
                            <div class="custom-info-block fl">
                                <input size="16" type="text" value="" readonly class="end-datetime" id="timeEndTime" name="timeEndTime" style="cursor: pointer;">
                                <p>到期时间</p>
                            </div>
                            <div class="custom-info-block expire-block fl">
                                <input type="text" value="" placeholder="请输入天数" class="expire number_input" id="timeExpire" name="timeExpire">
                                <p>到期提醒（天）</p>
                            </div>
                            <div class="custom-info-block count-num-block fl">
                                <input type="text" value="" placeholder="请输入金额" class="count-num number_input" id="timeMoney" name="timeMoney">
                                <p>金额</p>
                            </div>
                        </div>
                        <div class="order-class-block" >
                            <p class="select-title" style="font-size: 16px;color: #333;">选择课程</p>
                            <div class="select-type">
                                <select id="select-type">
                                    <option value="1">团体课</option>
                                    <option value="2">私教课</option>
                                </select>
                            </div>
                            <div class="class-block clearfix course-type-div">
                                <div class="class-one fl">
                                    <input type="checkbox" class="class-all">
                                    <label>全部</label>
                                </div>
                                <c:forEach items="${courseTypeList}" var="courseType">
                                    <div class="class-one fl">
                                        <input type="checkbox" class="class-o" name="time_courseType" value="${courseType.id}">
                                        <label>${courseType.name}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <p class="course-type-div">可预约课程类型</p>
                        </div>
                    </div>
                    <div class="quick-add-card">
                        <div class="card-info clearfix">
                            <div class="card-info-block fl">
                                <h5 class="card-name">半年卡</h5>
                                <p>卡类型</p>
                            </div>
                            <div class="card-info-block card-price fl">
                                <input type="text" value="" placeholder="请输入金额" class="price number_input" id="generalMoney" name="generalMoney">
                                <p>金额</p>
                            </div>
                            <div class="card-info-block expire-block fl">
                                <input size="16" type="text" value="" readonly class="card-datetime" id="generalBeginTime" name="generalBeginTime"  style="cursor: pointer;">
                                <p>开卡时间</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="btns-div clearfix">
            <a href="javascript:;" class="cancel-btn fl">取消</a>
            <button type="submit" class="save-btn" onclick="return save();" >保存</button>
        </div>

            <input type="hidden" id="templateId" name="templateId" value="">
            <input type="hidden" id="customCardType" name="customCardType" value="">
            <input type="hidden" id="customCountType" name="customCountType" value="">
            <input type="hidden" id="customCourseTypeIds" name="customCourseTypeIds" value="">
        </form:form>
    </div>
    <input type="hidden" id="ctx" value="${ctx}">
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/icheck.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/resources/js/new-js/page/member-add.js"></script>
</html>