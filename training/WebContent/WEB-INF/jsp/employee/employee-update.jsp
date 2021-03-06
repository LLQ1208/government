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
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-select.min.css" />
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/bootstrap-dialog.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/breadCommon.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/font-awesome.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/minimal/blue.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/red.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/myPage.css">
    <link rel="stylesheet" href="${ctx}/resources/css/new-css/pages/employee-add.css">
</head>
<body>
<div class="content-box">
    <jsp:include page="../menu.jsp" flush="false" >
        <jsp:param value="employee" name="menuName"/>
        <jsp:param value="6" name="group"/>
    </jsp:include>
    <div class="main-content">
        <div class="add-employee">
            <div id="bread" class="clearfix">
                <span class="breadFirst">设置</span>
                <span class="breadSecond"> > </span>
                <span class="breadFirst">员工管理</span>
                <span class="breadSecond"> > </span>
                <span class="breadThird">添加员工</span>
            </div>
            <form:form method="post" id="employeeInformation" name="employeeInformation" action="${ctx}/setting/employee/updateEmployeeInfo" commandName="employeeNew" enctype="multipart/form-data">
                <input type="hidden" name="id" id="id" value="${employeeNew.id}" />
                <input type="hidden" id="roleId" name="roleId" value="${employeeNew.roleId}" />
                <input type="hidden" id="allowDelete" name="allowDelete" value="${employeeNew.allowDelete}" />
                <div class="info-box">
                    <div class="info-top clearfix">
                        <div class="top-left fl">
                            <div class="img-block">
                                <div class="traningLogo" id="traningLogo" style="width: 84px;height: 84px;background-image: url(${employeeNew.logo});
                                        background-size: cover;border-radius: 50%;"></div>
                                <div class="file">
                                    <input type="file" id="img" style="cursor: pointer;" name="img" onchange="previewImage1(this);" accept="image/gif,image/jpg,image/png"/>
                                </div>
                            </div>
                            <div class="sexual">
                                <input type="radio" name="sex" value="男" <c:if test="${employeeNew.sex eq '男'}">checked</c:if> />
                                <label>男</label>
                                <input type="radio" name="sex" value="女" <c:if test="${employeeNew.sex eq '女'}">checked</c:if>>
                                <label>女</label>
                            </div>
                            <div class="birth">
                                <input size="16" id="birthday" name="birthday" type="text" value="${employeeNew.birthday}" readonly class="form_datetime">
                                <p class="birth-text">出生日期：YYYY-MM-DD</p>
                            </div>
                        </div>
                        <div class="top-right fl">
                            <div class="detail clearfix">
                                <p class="fl">姓名:</p>
                                <input class="fl" type="text" name="name" id="employeeName" value="${employeeNew.name}">
                            </div>
                            <div class="detail clearfix">
                                <p class="fl">邮箱:</p>
                                <input class="fl" type="text" name="email" id="email" value="${employeeNew.email}">
                            </div>
                            <div class="detail clearfix">
                                <p class="fl">手机号:</p>
                                <input class="fl" type="text" name="phone" id="phone" value="${employeeNew.phone}">
                            </div>
                            <%--<div class="detail clearfix">--%>
                                <%--<p class="fl">密码:</p>--%>
                                <%--<input type="text" name="pwd" id="pwd" readonly="readonly">--%>
                            <%--</div>--%>
                        </div>
                    </div>
                    <div class="info-remark clearfix">
                        <p class="fl">备注:</p>
                        <textarea class="fl" id="remarks" name="remarks" cols="30" rows="10">${employeeNew.remarks}</textarea>
                    </div>
                    <div class="info-btn clearfix">
                        <a href="javascript:;" class="delete-btn fl" style="display: block">删除</a>
                        <a href="javascript:;" class="save-btn fr">保存</a>
                        <a href="javascript:;" class="cancel-btn fr">取消</a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<input type="hidden" id="ctx" value="${ctx}"/>
</body>
<script src="${ctx}/resources/js/new-js/jquery.js"></script>
<script src="${ctx}/resources/js/new-js/icheck.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/resources/js/new-js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/new-js/page/employee-update.js"></script>
</html>