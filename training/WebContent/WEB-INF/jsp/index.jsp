<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CFer3.0</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/index/index.css">
    <script src="${ctx}/resources/js/new-js/jquery.js"></script>
    <script src="${ctx}/resources/js/new-js/index/index.js"></script>
</head>
<body>
    <div class="box clearfix">
        <section class="leftBar fl clearfix">
            <div class="logo">
            </div>
            <div class="user clearfix">
                <span class="userHead"></span>
                <p class="userName">Hendrix</p>
                <span class="downIcon"></span>
            </div>
            <div class="list clearfix">
                <!--wod-->
                <div class="listCommon clearfix">
                    <div class="listCommonTitle clearfix active">
                        <span class="listCommonIcon fl"></span>
                        <p class="titleName fl" style="margin-top:18px;">WOD</p>
                        <span class="commonDownIcon fl"></span>
                    </div>
                    <div class="listCommonDetail one">
                        <p class="changeSrc active" data-value="${ctx}/wod/queryWodList">日历</p>
                        <p class="changeSrc" data-value="${ctx}/wod/actions?searchType=3">动作元素</p>
                        <p class="changeSrc" data-value="${ctx}/wod/wodSetting">WOD设置</p>
                    </div>
                </div>
                <!--member-->
                <div class="listCommon clearfix">
                    <div class="listCommonTitle clearfix ">
                        <span class="listCommonIcon fl memberIcon"></span>
                        <p class="titleName fl">会员管理</p>
                        <span class="commonDownIcon fl"></span>
                    </div>
                    <div class="listCommonDetail">
                        <p class="changeSrc" data-value="calender">会员列表</p>
                        <p class="changeSrc" data-value="calender">设置模版</p>
                    </div>
                </div>
                <!--order-->
                <div class="listCommon clearfix">
                    <div class="listCommonTitle clearfix ">
                        <span class="listCommonIcon fl orderIcon"></span>
                        <p class="titleName fl">预约管理</p>
                        <span class="commonDownIcon fl"></span>
                    </div>
                    <div class="listCommonDetail">
                        <p class="changeSrc" data-value="${ctx}/reservation/reservationList">预约列表</p>
                    </div>
                </div>
                <!--class-->
                <div class="listCommon clearfix">
                    <div class="listCommonTitle clearfix ">
                        <span class="listCommonIcon fl classIcon"></span>
                        <p class="titleName fl">课程表</p>
                        <span class="commonDownIcon fl"></span>
                    </div>
                    <div class="listCommonDetail">
                        <p class="changeSrc" data-value="${ctx}/classSchedule/classSchedule">日历</p>
                        <p class="changeSrc" data-value="${ctx}/classSchedule/privateEducationList">私教课表</p>
                        <p class="changeSrc" data-value="${ctx}/classSchedule/classSettingPage">设置</p>
                        <p class="changeSrc" data-value="${ctx}/setting/courseType/newCourseTypeList">课程类型</p>                    </div>
                </div>
                <!--opration-->
                <div class="listCommon clearfix">
                    <div class="listCommonTitle clearfix ">
                        <span class="listCommonIcon fl oprationIcon"></span>
                        <p class="titleName fl">统计分析</p>
                        <span class="commonDownIcon fl"></span>
                    </div>
                    <div class="listCommonDetail">
                        <p class="changeSrc" data-value="calender">会员统计</p>
                        <p class="changeSrc" data-value="calender">教练统计</p>
                    </div>
                </div>
                <!--email-->
                <div class="listCommon clearfix">
                    <div class="listCommonTitle clearfix ">
                        <span class="listCommonIcon fl emailIcon"></span>
                        <p class="titleName fl" style="margin-top: 15px;">邮件管理</p>
                        <span class="commonDownIcon fl"></span>
                    </div>
                    <div class="listCommonDetail">
                        <p class="changeSrc" data-value="calender">邮件列表</p>
                        <p class="changeSrc" data-value="calender">动作元素</p>
                        <p class="changeSrc" data-value="calender">WOD设置</p>
                    </div>
                </div>
                <!--price-->
                <div class="listCommon clearfix">
                    <div class="listCommonTitle clearfix ">
                        <span class="listCommonIcon fl priceIcon"></span>
                        <p class="titleName fl">价目表</p>
                        <span class="commonDownIcon fl"></span>
                    </div>
                    <div class="listCommonDetail">
                        <p class="changeSrc" data-value="calender">日历</p>
                        <p class="changeSrc" data-value="calender">动作元素</p>
                        <p class="changeSrc" data-value="calender">WOD设置</p>
                    </div>
                </div>
                <!--set-->
                <div class="listCommon clearfix">
                    <div class="listCommonTitle clearfix ">
                        <span class="listCommonIcon fl setIcon"></span>
                        <p class="titleName fl">设置</p>
                        <span class="commonDownIcon fl"></span>
                    </div>
                    <div class="listCommonDetail">
                        <p class="changeSrc" data-value="calender">权限管理</p>
                        <p class="changeSrc" data-value="calender">员工管理</p>
                    </div>
                </div>
            </div>
        </section>
        <div class="rightContent fr">
            <iframe id="iframe" src="${ctx}/wod/queryWodList" width="100%" height="100%" frameborder="0"  allowtransparency="true" ></iframe>
        </div>
    </div>

    <!--添加备注弹框-->
    <div class="mask-box">
        <div class="remark-box">
            <div class="box-title clearfix">
                <p class="fl">添加备注</p>
                <span class="fr close-btn"></span>
            </div>
            <div class="box-content">
                <textarea cols="30" rows="10"></textarea>
                <span class="sure-btn">确定</span>
            </div>
        </div>
    </div>
</body>
</html>