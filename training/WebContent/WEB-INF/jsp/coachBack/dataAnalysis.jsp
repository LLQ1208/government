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
    <title>wod</title>
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap3.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/analysis.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/myPage.css">

    <script src="${ctx}/resources/js/new-js/jquery.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
    <script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
    <script src="${ctx}/resources/js/new-js/highcharts.js"></script>
    <script src="${ctx}/resources/js/new-js/page/analysis.js"></script>
    <script src="${ctx}/resources/js/new-js/jqPaginator.min.js"></script>
    <script src="${ctx}/resources/js/new-js/page/coachBackMenu.js"></script>


</head>
<body>
<div class="box">
    <header class="nav">
        <div class="top clearfix">
            <div class="left fl clearfix">
                <div class="user fl">
                    <c:choose>
                        <c:when test="${img == '' || img==null}">
                            <img src="${ctx}/resources/images/new-image/default_member.png" class="photo fl" />
                        </c:when>
                        <c:otherwise>
                            <img src="${img}" class="photo fl" />
                        </c:otherwise>
                    </c:choose>
                    <span class="name">${name}</span>
                </div>
                <select class="choose fr">
                    <option value="1">CFer</option>
                    <option value="2">CFer后台</option>
                </select>
            </div>
            <a href="${ctx}/logout">
            <div class="right fr">
                退出
            </div>
            </a>
        </div>
        <div class="bottom">
            <ul class="list clearfix">
                <li class="listCommon " data-value="wod" href="${ctx}/coachWod/coachWodPage">训练计划</li>
                <li class="listCommon" data-value="order" href="${ctx}/coachBackReser/reservationGroup">预约列表</li>
                <li class="listCommon" data-value="board" href="${ctx}/coachBackBoard/coachBoard">白板</li>
                <li class="listCommon" data-value="ranking" href="${ctx}/coachBackRand/coachRank">排行榜</li>
                <li class="listCommon active" data-value="analysis" href="${ctx}/coachBackAnalysis/coachAnalysis">数据分析</li>
            </ul>
        </div>
    </header>
</div>
<div class="box clearfix" style=" margin: 156px auto 0;">
    <div class="wodLeft fl clearfix">
        <p class="textBtn active" contentType="2">GYMNASTICS</p>
        <p class="textBtn" contentType="3">WEIGHTLIFTING</p>
        <p class="textBtn" contentType="4">METCON</p>
        <p class="textBtn" contentType="5">POPULAR WOD</p>
    </div>
    <div class="wodRight fr clearfix">
        <!--右侧选项卡rightCommon-34-->
        <div class="rightCommon">
            <div class="action">
                <div class="choose clearfix">
                    <div class="one fl">
                        <select  id="boxRight" class="tableL">
                            <c:forEach items="${boxList}" var="box" >
                                <option value="${box.id}">${box.name}</option>
                            </c:forEach>
                        </select>
                        <p>位置</p>
                    </div>
                    <div class="two fl">
                        <select id="member" class="tableR" data-live-search="true">
                            <c:choose>
                                <c:when test="${memberList == null || memberList.size() == 0}">
                                    <option value="">无</option>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${memberList}" var="member">
                                        <option value="${member.id}">${member.name}</option>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select>
                        <p>姓名</p>
                    </div>
                </div>
                <div class="actionTable">
                    <div class="actionTitle">
                        <p class="fl">动作名称</p>
                        <p class="fl">成绩</p>
                        <p class="fl">记录时间</p>
                    </div>
                    <div class="cont clearfix score-list">
                        <c:if test="${recordJson.list != null && recordJson.list.size() > 0}">
                            <c:forEach items="${recordJson.list}" var="record">
                                <div class="pCont clearfix">
                                    <p style="cursor: pointer;" class="fl record-name"  contentid="${record.contentId}">${record.contentName}</p>
                                    <p class="fl">${record.score}</p>
                                    <p class="fl">${record.time}</p>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--弹框--%>
<div class="pop" style="overflow: auto;z-index: 99;">
    <strong class="close"></strong>
    <div class="pop-title">
        <p class="pop-max">1 次最大：108kg （估算）</p>
        <p class="actionName">Bar Muscle-ups</p>
    </div>
    <div class="history-block">
        <div class="history-title clearfix">
            <p class="fl">历史记录</p>
            <span class="fr allNum">5条记录</span>
        </div>
        <div class="table-box">
            <div class="table-head clearfix">
                <p class="date fl">日期</p>
                <p class="result fl">记录结果</p>
                <p class="remark fl">备注</p>
            </div>
            <ul id="score-ul">
                <li class="clearfix">
                    <p class="date fl">2017/12/09</p>
                    <p class="result fl">5*3</p>
                    <p class="remark fl">腹肌力量不足</p>
                </li>
            </ul>

        </div>
        <div class="page-box">
            <div>
                <ul class="pagination" id="pagination">
                </ul>
                <input type="hidden" id="PageCount" runat="server" />
                <input type="hidden" id="PageSize" runat="server" value="5" />
                <input type="hidden" id="countindex" runat="server" value="1"/>
                <!--设置最多显示的页码数 可以手动设置 默认为7-->
                <input type="hidden" id="visiblePages" runat="server" value="3" />
            </div>
        </div>
    </div>
    <input type="hidden" class="weightPopMemberId" value="">
    <input type="hidden" class="weightPopContentId" value="">
    <div id="weight-chart" style="width: 478px; height: 240px;"></div>
</div>
<input type="hidden" id="ctx" value="${ctx}"/>
</body>
</html>