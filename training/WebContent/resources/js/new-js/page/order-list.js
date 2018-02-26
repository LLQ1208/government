/**
 * Created by anran on 2017/12/11.
 */

var count;
$(function () {
    changeWin();
    $('.form_datetime').datetimepicker({
        format: 'yyyy/mm/dd',
        language: 'zh-CN',
        minView: 2,
        initialDate: new Date(),
        autoclose: true
    });
    var dayArr = ['日','一','二','三','四','五','六'];
    var oDate = new Date();
    $('.form_datetime').val(oDate.getFullYear() + '/' + toDou(oDate.getMonth() + 1) + '/' + oDate.getDate());
    $('.date-box span').html('星期'+dayArr[oDate.getDay()]);
    $('.form_datetime').on('changeDate', function (e) {
        var n = e.date.getDay();
        $('.date-box span').html('星期'+dayArr[n]);
        getDateOrderList();
    });
    $("#course").on("change",function(){
        getOrderList();
    })
    $("#boxId").on("change",function(){
        getBoxOrderList();
    })
    $('.filters,#unit,#weightUnit,#metWeightUnit').selectpicker();

    $('.gym-pop .close').on('click', function () {
        $('.gym-pop').fadeOut();
    });
    $('.weight-pop .close').on('click', function () {
        $('.weight-pop').fadeOut();
    });


    //天厨添加成绩弹出框
    actionClick();
    //初始化添加成绩的rx选中动作
    rxSelect();
    //初始化返回按钮
    addCancleInit();
    //添加成绩按钮初始化
    addButInit();
    //签到
    signInit();
    //弹出框
    popShowInit();
});
function toDou(n) {
    return n > 9 ? n : '0' + n;
}

// 获取列表
function getOrderList() {
    var boxId = $("#boxId").val();
    var courseId = $("#course").val();
    var date = $(".form_datetime").val();
    $.ajax({
        type: 'get',
        dataType: 'json',
        // jsonp: 'callback',
        url: ctx+'/reservation/getOrderList',
        data: {boxId:boxId,courseId:courseId,date:date},
        success: function (result) {
            console.log(result);
            $('.count').html('COUNT(M/F):' + result.maleNum + "/" + result.fmaleNum);
            var femaleArr = result.fmaleList;
            var femaleHtml = '';
            if(null != femaleArr && undefined != femaleArr){
                for (var i = 0; i < femaleArr.length; i++) {
                    femaleHtml += '<li class="reserOneLi clearfix">' +
                    '<div class="left-block fl">' +
                    '<img src="' + femaleArr[i].ico + '" class="fl" alt="">' +
                    '<div class="name-block fl">' +
                    '<p>' + femaleArr[i].memberName + '</p>';
                    if (femaleArr[i].signStatus == 1) {
                        femaleHtml += '<span class="color-orange sign-but">已签到</span>';
                    } else if (femaleArr[i].courseEnd) {
                        femaleHtml += '<span class="color-gray">未签到</span>';
                    } else {
                        femaleHtml += '<span class="color-toSign sign-but">签到</span>';
                    }
                    femaleHtml += '</div>';
                    if (!femaleArr[i].boxMember) {
                        femaleHtml += '<span class="other">非</span>';
                    }
                    femaleHtml += '<input type="hidden" class="wodId" value="' + femaleArr[i].wodId + '">' +
                    '<input type="hidden" class="courseId" value="' + femaleArr[i].courseId + '">' +
                    '<input type="hidden" class="courseOrderId" value="' + femaleArr[i].courseOrderId + '">' +
                    '<input type="hidden" class="memberId" value="' + femaleArr[i].memberId + '">' +
                    '</div>' +
                    ' <div class="center-block fl wodContentDiv">';
                    for (var j = 0; j < femaleArr[i].actionModelList.length; j++) {
                        var actionMod = femaleArr[i].actionModelList[j];
                        femaleHtml += '<div class="rx-line clearfix" type="' + actionMod.recordType + '" rxplus="' + (actionMod.hasRxPlus == true ? '1' : '0')
                        + '" sportResultId="' + (actionMod.sportResultId == undefined || actionMod.sportResultId == null ? '' : actionMod.sportResultId) + '">' +
                        '<input type="hidden" class="wodContentId" value="' + actionMod.wodContentId + '">' +
                        '<span style="display: none"  class="wodContentCon">' + (actionMod.content != null && actionMod.content != undefined ? actionMod.content : '') + '</span>' +
                        '<span class="fl show-rx ' + (actionMod.rx == true ? 'color-blue' : 'color-gray') + '">Rx</span>' +
                        '<span class="fl show-rxp ' + (actionMod.rxPlus == true ? 'color-blue' : 'color-gray') + '">Rx+</span>' +
                        '<div class="fl" style="height: 22px;width: 22px">'+
                        '<img class="fl showPop" style= "cursor: pointer;width: 22px;' + (actionMod.hasPop == true ? '' : 'display: none') + '" contentid="' + actionMod.contentId + '" src="' + ctx + '/resources/images/new-image/order/icon_chart.png" alt="">' +
                        '</div>'+
                        '<div class="score fl">' + actionMod.actionTitle + '</div>' +
                        '</div>';
                    }
                    femaleHtml += '</div>' +
                    '</li>'
                }
            }

            $('.famale').html(femaleHtml);

            var maleArr = result.maleList;
            var maleHtml = '';
            if(null != maleArr && undefined != maleArr){
                for (var i = 0; i < maleArr.length; i++) {
                    maleHtml += '<li class="reserOneLi clearfix">' +
                    '<div class="left-block fl">' +
                    '<img src="' + maleArr[i].ico + '" class="fl" alt="">' +
                    '<div class="name-block fl">' +
                    '<p>' + maleArr[i].memberName + '</p>';
                    if (maleArr[i].signStatus == 1) {
                        maleHtml += '<span class="color-orange sign-but">已签到</span>';
                    } else if(maleArr[i].courseEnd){
                        maleHtml += '<span class="color-gray">未签到</span>';
                    }else{
                        maleHtml += '<span class="color-toSign sign-but">签到</span>';
                    }
                    maleHtml += '</div>';
                    if(!maleArr[i].boxMember){
                        maleHtml += '<span class="other">非</span>';
                    }
                    maleHtml +='<input type="hidden" class="wodId" value="'+maleArr[i].wodId+'">' +
                    '<input type="hidden" class="courseId" value="'+maleArr[i].courseId+'">'+
                    '<input type="hidden" class="courseOrderId" value="'+maleArr[i].courseOrderId+'">'+
                    '<input type="hidden" class="memberId" value="'+maleArr[i].memberId+'">';
                    maleHtml +=  '</div>' +
                    '<div class="center-block fl wodContentDiv">';
                    for (var j = 0; j < maleArr[i].actionModelList.length; j++) {
                        var actionMod = maleArr[i].actionModelList[j];
                        maleHtml += '<div class="rx-line clearfix" type="' + actionMod.recordType + '" rxplus="'+(actionMod.hasRxPlus == true ? '1':'0')
                        +'" sportResultId="'+(actionMod.sportResultId == undefined || actionMod.sportResultId == null ? '' : actionMod.sportResultId)+'">'+
                        '<input type="hidden" class="wodContentId" value="'+actionMod.wodContentId+'">'+
                        '<span style="display: none"  class="wodContentCon">'+(actionMod.content != null && actionMod.content != undefined ? actionMod.content : '')+'</span>'+
                        '<span class="fl show-rx '+ (actionMod.rx == true ? 'color-blue':'color-gray') +'">Rx</span>'+
                        '<span class="fl show-rxp '+ (actionMod.rxPlus == true ? 'color-blue': 'color-gray') +'">Rx+</span>'+
                        '<div class="fl" style="height: 22px;width: 22px">'+
                        '<img class="fl showPop" style= "cursor: pointer;width: 22px;'+(actionMod.hasPop == true ? '':'display: none')+'" contentid="'+ actionMod.contentId +'" src="'+ctx+'/resources/images/new-image/order/icon_chart.png" alt="">'+
                        '</div>' +
                        '<div class="score fl">'+actionMod.actionTitle+'</div>'+
                        '</div>';
                    }
                    maleHtml += '</div>' +
                    '</li>'
                }
            }
            $('.male').html(maleHtml);

            ////天厨添加成绩弹出框
            actionClick();
            ////初始化添加成绩的rx选中动作
            //rxSelect();
            ////签到
            signInit();
            ////弹出框
            popShowInit();
        },
        error: function () {

        }
    })
}
// 获取列表
function getDateOrderList() {
    var boxId = $("#boxId").val();
    var date = $(".form_datetime").val();
    $.ajax({
        type: 'get',
        dataType: 'json',
        // jsonp: 'callback',
        url: ctx+'/reservation/getDateOrderList',
        data: {boxId:boxId,date:date},
        success: function (result) {
            console.log(result);
            var courseList = result.courseModelList;
            var options = '';
            if(courseList != null && courseList != undefined){
                for(var c=0;c<courseList.length;c++){
                    options += ' <option value="' + courseList[c].id + '" >'+ courseList[c].beginTime + '&nbsp;' +
                    courseList[c].crouseTypeName + '</option>';
                }
            }
            if(options == ""){
                options = '<option value>无</option>';
            }
            $("#course").selectpicker();
            $("#course").html(options);
            $("#course").selectpicker('refresh');
            $("#course").on("change",function() {
                getOrderList();
            });
            $('.count').html('COUNT(M/F):' + result.maleNum + "/" + result.fmaleNum);
            var femaleArr = result.fmaleList;
            var femaleHtml = '';
            if(null != femaleArr && undefined != femaleArr){
                for (var i = 0; i < femaleArr.length; i++) {
                    femaleHtml += '<li class="reserOneLi clearfix">' +
                    '<div class="left-block fl">' +
                    '<img src="' + femaleArr[i].ico + '" class="fl" alt="">' +
                    '<div class="name-block fl">' +
                    '<p>' + femaleArr[i].memberName + '</p>';
                    if (femaleArr[i].signStatus == 1) {
                        femaleHtml += '<span class="color-orange sign-but">已签到</span>';
                    } else if (femaleArr[i].courseEnd) {
                        femaleHtml += '<span class="color-gray">未签到</span>';
                    } else {
                        femaleHtml += '<span class="color-toSign sign-but">签到</span>';
                    }
                    femaleHtml += '</div>';
                    if (!femaleArr[i].boxMember) {
                        femaleHtml += '<span class="other">非</span>';
                    }
                    femaleHtml += '<input type="hidden" class="wodId" value="' + femaleArr[i].wodId + '">' +
                    '<input type="hidden" class="courseId" value="' + femaleArr[i].courseId + '">' +
                    '<input type="hidden" class="courseOrderId" value="' + femaleArr[i].courseOrderId + '">' +
                    '<input type="hidden" class="memberId" value="' + femaleArr[i].memberId + '">' +
                    '</div>' +
                    ' <div class="center-block fl wodContentDiv">';
                    for (var j = 0; j < femaleArr[i].actionModelList.length; j++) {
                        var actionMod = femaleArr[i].actionModelList[j];
                        femaleHtml += '<div class="rx-line clearfix" type="' + actionMod.recordType + '" rxplus="' + (actionMod.hasRxPlus == true ? '1' : '0')
                        + '" sportResultId="' + (actionMod.sportResultId == undefined || actionMod.sportResultId == null ? '' : actionMod.sportResultId) + '">' +
                        '<input type="hidden" class="wodContentId" value="' + actionMod.wodContentId + '">' +
                        '<span style="display: none"  class="wodContentCon">' + (actionMod.content != null && actionMod.content != undefined ? actionMod.content : '') + '</span>' +
                        '<span class="fl show-rx ' + (actionMod.rx == true ? 'color-blue' : 'color-gray') + '">Rx</span>' +
                        '<span class="fl show-rxp ' + (actionMod.rxPlus == true ? 'color-blue' : 'color-gray') + '">Rx+</span>' +
                        '<div class="fl" style="height: 22px;width: 22px">'+
                        '<img class="fl showPop" style= "cursor: pointer;width: 22px;' + (actionMod.hasPop == true ? '' : 'display: none') + '" contentid="' + actionMod.contentId + '" src="' + ctx + '/resources/images/new-image/order/icon_chart.png" alt="">' +
                        '</div>'+
                        '<div class="score fl">' + actionMod.actionTitle + '</div>' +
                        '</div>';
                    }
                    femaleHtml += '</div>' +
                    '</li>'
                }
            }

            $('.famale').html(femaleHtml);

            var maleArr = result.maleList;
            var maleHtml = '';
            if(null != maleArr && undefined != maleArr){
                for (var i = 0; i < maleArr.length; i++) {
                    maleHtml += '<li class="reserOneLi clearfix">' +
                    '<div class="left-block fl">' +
                    '<img src="' + maleArr[i].ico + '" class="fl" alt="">' +
                    '<div class="name-block fl">' +
                    '<p>' + maleArr[i].memberName + '</p>';
                    if (maleArr[i].signStatus == 1) {
                        maleHtml += '<span class="color-orange sign-but">已签到</span>';
                    } else if(maleArr[i].courseEnd){
                        maleHtml += '<span class="color-gray">未签到</span>';
                    }else{
                        maleHtml += '<span class="color-toSign sign-but">签到</span>';
                    }
                    maleHtml += '</div>';
                    if(!maleArr[i].boxMember){
                        maleHtml += '<span class="other">非</span>';
                    }
                    maleHtml +='<input type="hidden" class="wodId" value="'+maleArr[i].wodId+'">' +
                    '<input type="hidden" class="courseId" value="'+maleArr[i].courseId+'">'+
                    '<input type="hidden" class="courseOrderId" value="'+maleArr[i].courseOrderId+'">'+
                    '<input type="hidden" class="memberId" value="'+maleArr[i].memberId+'">';
                    maleHtml +=  '</div>' +
                    '<div class="center-block fl wodContentDiv">';
                    for (var j = 0; j < maleArr[i].actionModelList.length; j++) {
                        var actionMod = maleArr[i].actionModelList[j];
                        maleHtml += '<div class="rx-line clearfix" type="' + actionMod.recordType + '" rxplus="'+(actionMod.hasRxPlus == true ? '1':'0')
                        +'" sportResultId="'+(actionMod.sportResultId == undefined || actionMod.sportResultId == null ? '' : actionMod.sportResultId)+'">'+
                        '<input type="hidden" class="wodContentId" value="'+actionMod.wodContentId+'">'+
                        '<span style="display: none"  class="wodContentCon">'+(actionMod.content != null && actionMod.content != undefined ? actionMod.content : '')+'</span>'+
                        '<span class="fl show-rx '+ (actionMod.rx == true ? 'color-blue':'color-gray') +'">Rx</span>'+
                        '<span class="fl show-rxp '+ (actionMod.rxPlus == true ? 'color-blue': 'color-gray') +'">Rx+</span>'+
                        '<div class="fl" style="height: 22px;width: 22px">'+
                        '<img class="fl showPop" style= "cursor: pointer;width: 22px;'+(actionMod.hasPop == true ? '':'display: none')+'" contentid="'+ actionMod.contentId +'" src="'+ctx+'/resources/images/new-image/order/icon_chart.png" alt="">'+
                        '</div>' +
                        '<div class="score fl">'+actionMod.actionTitle+'</div>'+
                        '</div>';
                    }
                    maleHtml += '</div>' +
                    '</li>'
                }
            }
            $('.male').html(maleHtml);

            ////天厨添加成绩弹出框
            actionClick();
            ////初始化添加成绩的rx选中动作
            //rxSelect();
            ////签到
            signInit();
            ////弹出框
            popShowInit();
        },
        error: function () {

        }
    })
}
// 获取列表
function getBoxOrderList() {
    var boxId = $("#boxId").val();
    var date = $(".form_datetime").val();
    $.ajax({
        type: 'get',
        dataType: 'json',
        // jsonp: 'callback',
        url: ctx+'/reservation/getDateOrderList',
        data: {boxId:boxId,date:date},
        success: function (result) {
            console.log(result);
            var courseList = result.courseModelList;
            var options = '';
            if(courseList != null && courseList != undefined && courseList.length > 0){
                for(var c=0;c<courseList.length;c++){
                    options += ' <option value="' + courseList[c].id + '" >'+ courseList[c].beginTime + '&nbsp;' +
                    courseList[c].crouseTypeName + '</option>';
                }
            }
            if(options == ""){
                options = '<option value>无</option>';
            }
            $("#course").selectpicker();
            $("#course").html(options);
            $("#course").selectpicker('refresh');
            $("#course").on("change",function() {
                getOrderList();
            });
            $('.count').html('COUNT(M/F):' + result.maleNum + "/" + result.fmaleNum);
            var femaleArr = result.fmaleList;
            var femaleHtml = '';
            if(null != femaleArr && undefined != femaleArr){
                for (var i = 0; i < femaleArr.length; i++) {
                    femaleHtml += '<li class="reserOneLi clearfix">' +
                    '<div class="left-block fl">' +
                    '<img src="' + femaleArr[i].ico + '" class="fl" alt="">' +
                    '<div class="name-block fl">' +
                    '<p>' + femaleArr[i].memberName + '</p>';
                    if (femaleArr[i].signStatus == 1) {
                        femaleHtml += '<span class="color-orange sign-but">已签到</span>';
                    } else if (femaleArr[i].courseEnd) {
                        femaleHtml += '<span class="color-gray">未签到</span>';
                    } else {
                        femaleHtml += '<span class="color-toSign sign-but">签到</span>';
                    }
                    femaleHtml += '</div>';
                    if (!femaleArr[i].boxMember) {
                        femaleHtml += '<span class="other">非</span>';
                    }
                    femaleHtml += '<input type="hidden" class="wodId" value="' + femaleArr[i].wodId + '">' +
                    '<input type="hidden" class="courseId" value="' + femaleArr[i].courseId + '">' +
                    '<input type="hidden" class="courseOrderId" value="' + femaleArr[i].courseOrderId + '">' +
                    '<input type="hidden" class="memberId" value="' + femaleArr[i].memberId + '">' +
                    '</div>' +
                    ' <div class="center-block fl wodContentDiv">';
                    for (var j = 0; j < femaleArr[i].actionModelList.length; j++) {
                        var actionMod = femaleArr[i].actionModelList[j];
                        femaleHtml += '<div class="rx-line clearfix" type="' + actionMod.recordType + '" rxplus="' + (actionMod.hasRxPlus == true ? '1' : '0')
                        + '" sportResultId="' + (actionMod.sportResultId == undefined || actionMod.sportResultId == null ? '' : actionMod.sportResultId) + '">' +
                        '<input type="hidden" class="wodContentId" value="' + actionMod.wodContentId + '">' +
                        '<span style="display: none"  class="wodContentCon">' + (actionMod.content != null && actionMod.content != undefined ? actionMod.content : '') + '</span>' +
                        '<span class="fl show-rx ' + (actionMod.rx == true ? 'color-blue' : 'color-gray') + '">Rx</span>' +
                        '<span class="fl show-rxp ' + (actionMod.rxPlus == true ? 'color-blue' : 'color-gray') + '">Rx+</span>' +
                        '<div class="fl" style="height: 22px;width: 22px">'+
                        '<img class="fl showPop" style= "cursor: pointer;width: 22px;' + (actionMod.hasPop == true ? '' : 'display: none') + '" contentid="' + actionMod.contentId + '" src="' + ctx + '/resources/images/new-image/order/icon_chart.png" alt="">' +
                        '</div>'+
                        '<div class="score fl">' + actionMod.actionTitle + '</div>' +
                        '</div>';
                    }
                    femaleHtml += '</div>' +
                    '</li>'
                }
            }

            $('.famale').html(femaleHtml);

            var maleArr = result.maleList;
            var maleHtml = '';
            if(null != maleArr && undefined != maleArr){
                for (var i = 0; i < maleArr.length; i++) {
                    maleHtml += '<li class="reserOneLi clearfix">' +
                    '<div class="left-block fl">' +
                    '<img src="' + maleArr[i].ico + '" class="fl" alt="">' +
                    '<div class="name-block fl">' +
                    '<p>' + maleArr[i].memberName + '</p>';
                    if (maleArr[i].signStatus == 1) {
                        maleHtml += '<span class="color-orange sign-but">已签到</span>';
                    } else if(maleArr[i].courseEnd){
                        maleHtml += '<span class="color-gray">未签到</span>';
                    }else{
                        maleHtml += '<span class="color-toSign sign-but">签到</span>';
                    }
                    maleHtml += '</div>';
                    if(!maleArr[i].boxMember){
                        maleHtml += '<span class="other">非</span>';
                    }
                    maleHtml +='<input type="hidden" class="wodId" value="'+maleArr[i].wodId+'">' +
                    '<input type="hidden" class="courseId" value="'+maleArr[i].courseId+'">'+
                    '<input type="hidden" class="courseOrderId" value="'+maleArr[i].courseOrderId+'">'+
                    '<input type="hidden" class="memberId" value="'+maleArr[i].memberId+'">';
                    maleHtml +=  '</div>' +
                    '<div class="center-block fl wodContentDiv">';
                    for (var j = 0; j < maleArr[i].actionModelList.length; j++) {
                        var actionMod = maleArr[i].actionModelList[j];
                        maleHtml += '<div class="rx-line clearfix" type="' + actionMod.recordType + '" rxplus="'+(actionMod.hasRxPlus == true ? '1':'0')
                        +'" sportResultId="'+(actionMod.sportResultId == undefined || actionMod.sportResultId == null ? '' : actionMod.sportResultId)+'">'+
                        '<input type="hidden" class="wodContentId" value="'+actionMod.wodContentId+'">'+
                        '<span style="display: none"  class="wodContentCon">'+(actionMod.content != null && actionMod.content != undefined ? actionMod.content : '')+'</span>'+
                        '<span class="fl show-rx '+ (actionMod.rx == true ? 'color-blue':'color-gray') +'">Rx</span>'+
                        '<span class="fl show-rxp '+ (actionMod.rxPlus == true ? 'color-blue': 'color-gray') +'">Rx+</span>'+
                        '<div class="fl" style="height: 22px;width: 22px">'+
                        '<img class="fl showPop" style= "cursor: pointer;width: 22px;'+(actionMod.hasPop == true ? '':'display: none')+'" contentid="'+ actionMod.contentId +'" src="'+ctx+'/resources/images/new-image/order/icon_chart.png" alt="">'+
                        '</div>' +
                        '<div class="score fl">'+actionMod.actionTitle+'</div>'+
                        '</div>';
                    }
                    maleHtml += '</div>' +
                    '</li>'
                }
            }
            $('.male').html(maleHtml);

            ////天厨添加成绩弹出框
            actionClick();
            ////初始化添加成绩的rx选中动作
            //rxSelect();
            ////签到
            signInit();
            ////弹出框
            popShowInit();
        },
        error: function () {

        }
    })
}
// 点击折线图
function chartClick() {
    $('.chart-box img').on('click', function () {
        var contentId = $(this).parents('.rx-line').attr('id');
        var status = $(this).parents('.rx-line').attr('status');
        if(contentId == 1){
            weightPop();
        }else{
            gymPop(userId,contentId);
        }
    });
}



function pageData(historyArr,Count,PageSize,currentPage) {
    var historyHtml = '';

        for (var i = (currentPage - 1) * PageSize; i < PageSize * currentPage; i++) {
            if(i<Count){
                historyHtml += '<li class="clearfix">' +
                    '<p class="date fl">' + historyArr[i].time + '</p>' +
                    '<p class="result fl">' + historyArr[i].recordSource + '</p>' +
                    '<p class="remark fl">' + historyArr[i].remark + '</p>' +
                    '</li>';
            }
        }

    $('.gym-pop .history-block .table-box ul').html(historyHtml);
}

function popShowInit(){
    $(".showPop").on("click",function(){
        var type = $(this).parents('.rx-line').attr('type');
        var boxId = $("#boxId").val();
        var memberId = $(this).parents(".reserOneLi").find("input.memberId").val();
        var contentId = $(this).attr("contentid");
        var wodContentId = $(this).parents(".rx-line").find("input.wodContentId").val();
        $(".weightPopMemberId").val(memberId);
        $(".weightPopContentId").val(contentId);

        if(type == 10){
            $.ajax({
                url:ctx + "/reservation/getWeightPop",
                type:'POST', //GET
                async:true,    //或false,是否异步
                data:{boxId:boxId,memberId:memberId,contentId:contentId,wodContentId:wodContentId},
                dataType:'json',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success:function(result){
                    var weight = result;
                    $('.weight-pop .pop-title').html('<p>' + weight.maxSource + '</p><p>' + weight.name + '</p>');
                    var personalHtml = '';
                    for (var i = 0; i < weight.personalData.length; i++) {
                        personalHtml += '<div class="person-data fl">' +
                        '<p class="person-key">' + weight.personalData[i].rpsTitle + '</p>' +
                        '<p class="person-value">' + weight.personalData[i].rpsSource + '</p>' +
                        '</div>';
                    }
                    $('.person .person-table').html(personalHtml);
                    $('.percent .percent-table ul').html('<li>' + weight.percentage[0] + '(95%)</li>' +
                    '<li>' + weight.percentage[1] + '(80%)</li>' +
                    '<li>' + weight.percentage[2] + '(65%)</li>' +
                    '<li>' + weight.percentage[3] + '(90%)</li>' +
                    '<li>' + weight.percentage[4] + '(75%)</li>' +
                    '<li>' + weight.percentage[5] + '(60%)</li>' +
                    '<li>' + weight.percentage[6] + '(85%)</li>' +
                    '<li>' + weight.percentage[7] + '(70%)</li>' +
                    '<li>' + weight.percentage[8] + '(55%)</li>');
                    weightChart(weight.serieses);
                    weightHistory(1);
                    count = result.historySourceCount; // 记录条数
                    $('.weight-pop .history-block .history-title span').html(count + '条记录');
                    //分页
                    $("#PageCount").val(count);
                    $("#countindex").val(1);
                    weightLoadpage();
                    $(".weight-pop").fadeIn();
                },
                error:function(){
                    return false;
                }
            });

        }else if(type == 11){
            $.ajax({
                url:ctx + "/reservation/getGymPop",
                type:'POST', //GET
                async:true,    //或false,是否异步
                data:{boxId:boxId,memberId:memberId,contentId:contentId,wodContentId:wodContentId},
                dataType:'json',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success:function(result){
                    console.log(result);
                    var gym = result;
                    $('.gym-pop .pop-title').html('<p>' + gym.maxSource + '</p><p>' + gym.name + '</p>');
                    // 历史数据
                    var historyArr = gym.historySource;
                    var Count = historyArr.length; // 记录条数
                    var PageSize = 3; // 设置每页示数目
                    var PageCount = Math.ceil(Count / PageSize); // 计算总页数
                    var currentPage = 1; // 当前页，默认为1。
                    $('.gym-pop .page').html("");
                    for (var i = 1; i <= PageCount; i++) {
                        $('.gym-pop .page').append('<li class="fl" selectPage="'+i+'">' + i + '</li>')
                    }
                    $('.gym-pop .page li:first').addClass('active');
                    $('.gym-pop .history-block .history-title span').html(Count + '条记录');

                    pageData(historyArr,Count,PageSize,currentPage);

                    $('.gym-pop .page li').on('click', function(){
                        $(this).addClass('active').siblings('li').removeClass('active');

                        var selectPage=$(this).attr('selectPage');
                        pageData(historyArr,Count,PageSize,selectPage);
                    });
                    gymChart(gym.serieses);
                    $(".gym-pop").fadeIn();
                },
                error:function(){
                    return false;
                }
            });
        }
    })

    $(".weight-pop .close").on("click",function(){
        $(".weight-pop").fadeOut();
    })
    $(".gym-pop .close").on("click",function(){
        $(".weight-pop").fadeOut();
    })
}


// 分页
function weightHistory(pageNum) {
    var boxId = $("#boxId").val();
    var memberId = $(".weightPopMemberId").val();
    var contentId = $(".weightPopContentId").val();
    $.ajax({
        type: 'get',
        dataType: 'json',
        url: ctx +'/reservation/getWeightHistory',
        data: {
            pageNum: pageNum,
            boxId:boxId,
            memberId:memberId,
            contentId:contentId
        },
        success: function (result) {
            // 历史数据
            var historyArr = result;
            var hisHtml = '';
            for (var i = 0; i < historyArr.length; i++) {
                hisHtml += '<li class="clearfix">'+
                '<p class="date fl">'+historyArr[i].time+'</p>'+
                '<p class="result fl">'+historyArr[i].recordSource+'</p>'+
                '<p class="remark fl">'+historyArr[i].remark+'</p>'+
                '</li>';
            }
            $('.weight-pop .history-block .table-box ul').html(hisHtml);

        },
        error: function () {}
    })
}
// 翻页
function weightChangePageInit(num) {
    weightHistory(num)
}

// 举重
function weightChart(data) {
    Highcharts.chart('weight-chart', {
        chart: {
            type: 'spline'
        },
        title: {
            text: ''
        },
        xAxis: {
            type: 'category',
            title: {
                text: null
            }
        },
        yAxis: {
            title: {
                text: ''
            },
            min: 0
        },
        tooltip: {
            headerFormat: '<b>{series.name}</b><br>',
            pointFormat: '{point.y:.2f}'
        },
        plotOptions: {
            spline: {
                marker: {
                    enabled: true
                }
            }
        },
        series:data
    });

}


// 体操类弹框
//function gymPop(userId,contentId) {
//    $.ajax({
//        type: 'get',
//        dataType: 'json',
//        // jsonp: 'callback',
//        url: ctx + '/reservation/getGymPop',
//        data: {
//            userId: userId,
//            contentId: contentId
//        },
//        success: function (result) {
//            console.log(result);
//            var gym = result.Gymnastics;
//            $('.gym-pop .pop-title').html('<p>' + gym.maxSource + '</p><p>' + gym.name + '</p>');
//            // 历史数据
//            var historyArr = gym.historySource;
//            var Count = historyArr.length; // 记录条数
//            var PageSize = 3; // 设置每页示数目
//            var PageCount = Math.ceil(Count / PageSize); // 计算总页数
//            var currentPage = 1; // 当前页，默认为1。
//            for (var i = 1; i <= PageCount; i++) {
//                $('.gym-pop .page').append('<li class="fl" selectPage="'+i+'">' + i + '</li>')
//            }
//            $('.gym-pop .page li:first').addClass('active');
//            $('.gym-pop .history-block .history-title span').html(Count + '条记录');
//
//            pageData(historyArr,Count,PageSize,currentPage);
//
//            $('.gym-pop .page li').on('click', function(){
//                $(this).addClass('active').siblings('li').removeClass('active');
//
//                var selectPage=$(this).attr('selectPage');
//                pageData(historyArr,Count,PageSize,selectPage);
//
//
//            });
//            gymChart(gym.linechart);
//        },
//        error: function () {
//
//        }
//    })
//}
// 体操
function gymChart(data) {
    Highcharts.chart('gym-chart', {
        chart: {
            type: 'spline'
        },
        title: {
            text: ''
        },
        xAxis: {
            type: 'category',
            title: {
                text: null
            }
        },
        yAxis: {
            title: {
                text: ''
            },
            min: 0
        },
        tooltip: {
            headerFormat: '<b>{series.name}</b><br>',
            pointFormat: '{point.y:.2f}'
        },
        plotOptions: {
            spline: {
                marker: {
                    enabled: true
                }
            }
        },
        series:data
    });
}
function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}
