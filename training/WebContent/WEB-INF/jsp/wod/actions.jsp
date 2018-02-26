<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Title</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css"/>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-dialog.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/font-awesome.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/minimal/blue.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/red.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/myPage.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/actions.css">
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false">
        <jsp:param value="actions" name="menuName"/>
        <jsp:param value="1" name="group"/>
    </jsp:include>
    <div class="main-content">
        <div class="actions">
            <div id="bread" class="clearfix">
                <span class="breadFirst">WOD</span>
                <span class="breadSecond"> > </span>
                <span class="breadThird"> 动作元素</span>
            </div>
            <div class="section-list">
                <div class="add-btn">Sections</div>
                <ul id="contentSearchType" class="section-type clearfix">
                    <li class="fl active">Sections<span></span></li>
                    <li class="fl">Warm-Ups<span></span></li>
                    <li class="fl">Gymnastics<span></span></li>
                    <li class="fl">Weightlifting<span></span></li>
                    <li class="fl">Metcons<span></span></li>
                    <li class="fl">Popular Wod<span></span></li>
                </ul>
                <ul id="sectionSearchType" class="section-kind clearfix">
                    <li class="fl active">自定义<span></span></li>
                    <li class="fl">系统<span></span></li>
                    <li class="fl">全部<span></span></li>
                </ul>
                <div class="search-box">
                    <input type="text" placeholder="搜索输入Sections" id="keyword">
                    <span onclick="searchAll();" id="searchBut"></span>
                </div>
            </div>
            <!--sectionList-->
            <div id="sectionList" class="lists">
                <div class="head-list clearfix">
                    <div class="check-box fl">
                        <input type="checkbox" class="select-all">
                    </div>
                    <p class="top-title-box fl">Title</p>
                    <p class="type-box fl">类型</p>
                </div>
                <div id="sectionListSource">
                    <ul id="sectionUl" class="list-box">
                        <c:forEach items="${pager.list}" var="sectionOne">
                            <li class="clearfix">
                                <div class="check-box fl">v
                                    <input type="checkbox" class="select-one">
                                </div>
                                <p class="title-box fl">${sectionOne.title}</p>
                                <c:if test="${sectionOne.userId eq 174}">
                                    <p class="type-box fl">系统</p>
                                </c:if>
                                <c:if test="${sectionOne.userId ne 174}">
                                    <p class="type-box fl">自定义</p>
                                </c:if>
                                <input type="hidden" class="actionSourceId" value="${sectionOne.sectionId}"/>
                                <input type="hidden" class="sectionUserId" value="${sectionOne.userId}"/>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <!--contentList-->
            <div id="contentList" class="lists" style="display: none;">
                <div class="head-list clearfix">
                    <div class="check-box fl">
                        <input type="checkbox" class="select-all">
                    </div>
                    <p class="top-title-box fl">名称</p>
                    <p class="type-box fl">类型</p>
                    <p class="record-box fl">记录方式</p>
                    <p class="hide-box fl">未使用天数</p>
                    <p class="last-box fl">上次使用日期</p>
                </div>
                <div id="contentListSource">
                    <ul class="list-box">
                        <li class="clearfix">
                            <div class="check-box fl">
                                <input type="checkbox" class="select-one">
                            </div>
                            <p class="title-box fl">Bar Muscle-Up</p>
                            <p class="type-box fl">系统</p>
                            <p class="record-box fl">Max Reps</p>
                            <p class="hide-box fl">12</p>
                            <p class="last-box fl">2017/11/23</p>
                        </li>
                    </ul>
                </div>
            </div>
            <div style="text-align: center;padding-top: 280px">
                <ul class="pagination" id="pagination">
                </ul>
                <input type="hidden" id="PageCount" runat="server" value="${pageCount}"/>
                <input type="hidden" id="PageSize" runat="server" value="10"/>
                <input type="hidden" id="countindex" runat="server" value="10"/>
                <!--设置最多显示的页码数 可以手动设置 默认为7-->
                <input type="hidden" id="visiblePages" runat="server" value="5"/>
            </div>
            <%--<div id="pagesTitle">--%>
            <%--<div class="row pagniation-div">--%>
            <%--<div class="col-md-12">${pagerBottom}</div>--%>
            <%--</div>--%>
            <%--</div>--%>

            <%--<div class="page-box clearfix">--%>
            <%----%>
            <%--<span class="glyphicon glyphicon-chevron-left ht-rili-leftarr fl"></span>--%>
            <%--<span class="fl pages">21-40</span>--%>
            <%--<span class="glyphicon glyphicon-chevron-right ht-rili-rightarr fr" onclick="nextSource();"></span>--%>
            <%--</div>--%>
            <%--<div class="M-box3" id="pagination-bottom"></div>--%>
        </div>
        <!--section-->
        <div class="pop-box-bg sections">
            <div class="add-section">
            <div class="section-title clearfix">
                <p class="fl">Section</p>
                <span class="fr close-btn"></span>
            </div>
            <div class="section-content">
                <span class="delete-btn" onclick="sectionDel();">删除</span>
                <input type="text" id="sectionTitle"/>
                <i>TITLE*</i>
                <div class="btn-box clearfix">
                    <span id="saveSe" class="btn-block section-save-btn fr" onclick="sectionAdd()">保存</span>
                    <span id="updateSe" class="btn-block section-save-btn fr" onclick="sectionEdit()">保存</span>
                    <span class="btn-block section-cancel-btn fr">取消</span>
                </div>
            </div>
        </div>
        </div>
        <!--warm-up-->
        <div class="pop-box-bg warm-up">
            <div class="add-section">
            <div class="section-title clearfix">
                <p class="fl">Warm-Ups</p>
                <span class="fr close-btn"></span>
            </div>
            <div class="section-content">
                <input type="text" id="contentName"/>
                <i>名称*</i>
                <textarea cols="30" rows="10" id="contentDescription"></textarea>
                <strong>描述</strong>
                <div class="btn-box clearfix">
                    <span class="btn-block delete-btn fl" onclick="contentDel();">删除</span>
                    <span id="saveContentFrist" class="btn-block section-save-btn fr" onclick="contentAdd();">保存</span>
                    <span id="updateContentFrist" class="btn-block section-save-btn fr"
                          onclick="contentEdit();">保存</span>
                    <span class="btn-block section-cancel-btn fr">取消</span>
                </div>
            </div>
        </div>
        </div>
        <!--metcon-->
        <div class="pop-box-bg metcon">
            <div class="add-section">
            <div class="section-title clearfix">
                <p class="fl">Metcons</p>
                <span class="fr close-btn"></span>
            </div>
            <div class="section-content">
                <input type="text" id="contentNameSecond"/>
                <i class="mark-title">名称*</i>
                <textarea cols="30" rows="10" id="contentDescriptionSecond"></textarea>
                <strong class="little-title">描述</strong>
                <%--<div class="record-type" id="recordTypeFamousWodDiv" style="display: none">--%>
                <%--<select id="famousWodType">--%>
                <%--<option value="1">Girls</option>--%>
                <%--<option value="2">Hero</option>--%>
                <%--<option value="3">Games</option>--%>
                <%--</select>--%>
                <%--</div>--%>
                <div class="record-type">
                    <select id="record" onchange="changeOption();">
                        <option value="1">Time</option>
                        <%--<option value="2">AMRAP-Rounds</option>--%>
                        <option value="3">AMRAP-Reps</option>
                        <option value="4">AMRAP-Rounds and Reps</option>
                        <option value="5">each round</option>
                        <option value="6">distance</option>
                        <option value="7">Calories</option>
                        <option value="8">No Measure</option>
                        <option value="10">weight</option>
                    </select>
                </div>
                <div class="clearfix record-block">
                    <input type="number" id="eachRoundRecord" style="display: none" min="1">
                    <div class="eachRoundRecordType" style="display: none">
                        <select id="eachRoundRecordType">
                            <option value="1">For Reps</option>
                            <option value="2">For Time</option>
                            <option value="3">For Weight</option>
                            <option value="4">For Distance</option>
                            <option value="5">For Calories</option>
                        </select>
                    </div>
                </div>
                <strong class="little-title">记录方式</strong>
                <div class="rx clearfix">
                    <span class="fl">是否Rx+？</span>
                    <div class="fl">
                        <input type="radio" id="yes" name="rx"  class="radio" value="1">
                        <label>是</label>
                        <input type="radio" id="no" name="rx" checked class="radio" value="0">
                        <label>否</label>
                    </div>
                </div>
                <div class="btn-box clearfix">
                    <span class="btn-block delete-btn fl" onclick="contentDel();">删除</span>
                    <%--<span class="btn-block section-save-btn fr">保存</span>--%>
                    <span id="saveContentSecond" class="btn-block section-save-btn fr" onclick="contentAdd();">保存</span>
                    <span id="updateContentSecond" class="btn-block section-save-btn fr"
                          onclick="contentEdit();">保存</span>
                    <span class="btn-block section-cancel-btn fr">取消</span>
                </div>
            </div>
        </div>
        </div>

        <!--系统查看弹框-->
        <!--section-->
        <div class="pop-box-bg">
            <div class="view-section system-section">
            <div class="section-title clearfix">
                <p class="fl">Section</p>
                <span class="fr close-btn"></span>
            </div>
            <div class="view">
                <p class="view-title">section</p>
                <i class="mark-title">名称*</i>
                <div class="btn-box clearfix">
                    <span class="btn-block section-cancel-btn fr">取消</span>
                </div>
            </div>
        </div>
        </div>
        <!--warm-up-->
        <div class="view-section system-warm-up">
            <div class="section-title clearfix">
                <p class="fl" id="warmUpTitle">warm-up</p>
                <span class="fr close-btn"></span>
            </div>
            <div class="view">
                <p class="view-title">section</p>
                <i class="mark-title">名称*</i>
                <p class="view-desc">fkljgrkeldjasfkrekglkdsjflkfk</p>
                <strong class="little-title">描述</strong>
                <div class="btn-box clearfix">
                    <span class="btn-block section-cancel-btn fr">取消</span>
                </div>
            </div>
        </div>
        <!--metcon-->
        <div class="view-section system-warm-up">
            <div class="section-title clearfix">
                <p class="fl" id="metconTitle">metcon</p>
                <span class="fr close-btn"></span>
            </div>
            <div class="view">
                <p class="view-title">section</p>
                <i class="mark-title">名称*</i>
                <p class="view-desc">fkljgrkeldjasfkrekglkdsjflkfk</p>
                <strong class="little-title">描述</strong>
                <p class="view-record">fkljgrkeldjasfkrekglkdsjflkfk</p>
                <strong class="little-title">记录方式</strong>
                <p class="view-rx">是否Rx+？ 否</p>
                <div class="btn-box clearfix">
                    <span class="btn-block section-cancel-btn fr">取消</span>
                </div>
            </div>
            <input type="hidden" id="ctx" value="${ctx}"/>
            <input type="hidden" id="transId">
            <input type="hidden" id="deletedId">
            <input type="hidden" id="contentType">
        </div>
    </div>
</div>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/icheck.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/actions.js"></script>
<script src="${ctx}/resources/js/new-js/page/jqPaginator.min.js"></script>
<%--<script src="${ctx}/resources/js/new-js/page/myPage.js"></script>--%>
</html>