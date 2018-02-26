/**
 * Created by anran on 2017/11/26.
 */
$(function () {
    changeWin();

// 下拉框
    $('.name-selectpicker').selectpicker();
    $('.type-selectpicker').selectpicker();

    //sessionStorage 存储下拉框信息
    var sessionBoxId = $("#sessionBoxId").val();
    var sessionCourseType = $("#sessionCourseType").val();
    var thisBoxId =  $('.name-selectpicker').val();
    var thisCourseType = $('.type-selectpicker').val();
    if(sessionBoxId != null && sessionBoxId != "" && sessionBoxId != undefined && thisBoxId != sessionBoxId){
        $('.name-selectpicker').selectpicker("val",sessionBoxId);
    }
    if(sessionCourseType != null && sessionCourseType != ""&& sessionCourseType != undefined && thisCourseType != sessionCourseType){
        $('.type-selectpicker').selectpicker("val",sessionCourseType);
    }

    selectToQuery();

    // 点击几周
    $('.top-right-block ul li').on('click', function () {
        $(this).addClass('active').siblings('li').removeClass('active');
        $("#weekType").val($(this).attr("myAttr"));
        selectToQuery();
   });



    $('.ht-rili-leftarr').on('click', function () {
        $("#lastOrNextWeek").val(-1);
        selectToQuery();
    });
    $('.ht-rili-rightarr').on('click', function () {
        $("#lastOrNextWeek").val(1);
        selectToQuery();
    });
    $('.back-today').on('click', function () {
        $("#thisWeek").val(0);
        selectToQuery();
    });


});
//ajax异步查询
function selectToQuery(){
    var ctx = $("#ctx").val();
    var boxId = $("#boxId").val();
    var courseType = $("#courseTypeId").val();
    //存储sessionStorage
    var lastOrNextWeek = $("#lastOrNextWeek").val();
    var weekType = $("#weekType").val();
    var beginDate = $("#beginDate").val();
    var endDate = $("#endDate").val();
    $.ajax({
        url:ctx + "/wod/searchWodList",
        type:'GET', //GET
        async:true,    //或false,是否异步
        data:{boxId:boxId,courseType:courseType,lastOrNextWeek:lastOrNextWeek,
            weekType:weekType,beginDate:beginDate,endDate:endDate},
        dataType:'json',
        success:function(data){
            var wodPage = data;
            console.log(data);
            var wodList = wodPage.wodPageModelList;
            $("#beginDate").val(wodPage.beginDate);
            $("#endDate").val(wodPage.endDate);
            $("#endDate").val(wodPage.endDate);
            $("#weekType").val(wodPage.weekType);
            $("#lastOrNextWeek").val(wodPage.lastOrNextWeek);
            $("#titleT").html(wodPage.title);
            var today = wodPage.today;
            var bodyHtml = "";
            for(var i=0;i<wodList.length;i++){
                var wod = wodList[i];
                if(null == wod.wodId || undefined == wod.wodId){
                    bodyHtml += '<div class="ht-rili-td '+(wod.date == today ? 'today':'')+'" data-date="">'+
                        '<span class="ht-rili-day">'+wod.day+'</span>'+
                        '<div class="ht-rili-con">'+
                        '<div class="mini-title">'+
                        '<img class="plus  addWodPlus" dateAttr="'+wod.date+'"  src="'+ctx+'/resources/images/new-image/calendar/icon_plus.png"/>'+
                        '</div>'+
                        '</div>'+
                        '</div>';
                }else{
                    bodyHtml +=
                        '<div class="ht-rili-td '+ (wod.date == today ? 'today':'')+'" data-date="'+wod.date+'">'+
                            '<span class="ht-rili-day">'+wod.day+'</span>'+
                            '<div class="ht-rili-con">'+
                                '<div class="mini-title wod-title-div">'+
                                '<img class="view" src="'+ctx+'/resources/images/new-image/calendar/icon_view.png" /><span>'+wod.wodName+'</span>'+
                                '</div>';
                    if(null != wod.sectionModelList && undefined != wod.sectionModelList ){
                        bodyHtml +=  '<a class="wod-link" href="'+ctx+'/wod/toEditWodPage?wodId='+wod.wodId+'" >'+
                            '<div style="height: 265px;">';
                        for(var j=0;j<wod.sectionModelList.length;j++){
                            var wodSection = wod.sectionModelList[j];
                           if(wodSection.type != '0'){
                               bodyHtml += '<div class="one-block action-div section-cla" style="display: none">'+
                               ' <i class="black-circle" ></i><p>'+wodSection.wodSectionName+'</p>'+
                               '</div>';
                           }
                            if(null != wodSection.wodContentPageModelList && undefined != wodSection.wodContentPageModelList){
                                for(var k =0;k<wodSection.wodContentPageModelList.length;k++){
                                    var wodContent = wodSection.wodContentPageModelList[k];
                                    var contentType = wodContent.wodContentType;

                                    bodyHtml += '<div class="one-block action-div action-cla">'+
                                        '<i class="';
                                    if(contentType == '1' || contentType == '6'){
                                        bodyHtml += 'warm-up-circle';
                                    }else if(contentType == '2' || contentType == '7'){
                                        bodyHtml += 'gym-circle';
                                    }else if(contentType == '3' || contentType == '8'){
                                        bodyHtml += 'weight-circle';
                                    }else if(contentType == '4' || contentType == '9'){
                                        bodyHtml += 'met-circle';
                                    }else if(contentType == '5'){
                                        bodyHtml += 'popu-circle ';
                                    }
                                    bodyHtml += '"></i><p>'+(wodContent.seriaNum != null && wodContent.seriaNum != undefined && wodContent.seriaNum != ''? wodContent.seriaNum+'、':'');

                                    if(contentType == 6){
                                        bodyHtml += 'WarmUp';
                                    }else{
                                       // bodyHtml += "" + (wodContent.wodContentName != null && wodContent.wodContentName !=undefined ?  wodContent.wodContentName : 'Metcon');
                                        if(wodContent.wodContentName != null && wodContent.wodContentName !=undefined){
                                            bodyHtml += "" +  wodContent.wodContentName;
                                        }else{
                                            if(contentType == 7){
                                                bodyHtml += "Gymnastics";
                                            }else if(contentType == 8){
                                                bodyHtml += "Weightliting";
                                            }else if(contentType == 9){
                                                bodyHtml += "Mecton";
                                            }
                                        }
                                    }
                                    if(contentType == "2" || contentType == "3" ){
                                        bodyHtml += (wodContent.repsScheme != null && wodContent.repsScheme != undefined && wodContent.repsScheme != ''? '('+wodContent.repsScheme+')' : '');
                                    }else if(contentType == "6" ){
                                        bodyHtml += '(no measure)';
                                    }else if(contentType == "4" || contentType == "5"  || contentType == "9"){
                                        bodyHtml += "(" + wodContent.contentRecordTypeName + ")";
                                    }else if(contentType == "2" || contentType == "3" ){
                                        bodyHtml += (wodContent.repsScheme != null && wodContent.repsScheme != undefined && wodContent.repsScheme != ''? '('+wodContent.repsScheme+')' : '');
                                    }
                                    bodyHtml += '</p>'+
                                        '</div>'+
                                        '<div class="small-block action-div">'+
                                        '<p>'+(wodContent.comment != null && wodContent.comment != undefined ? wodContent.comment.replace(/(\r)*\n/g,"<br />").replace(/\s/g," "):"")+'</p>'+
                                        '</div>';
                                }
                            }
                        }
                        bodyHtml += '</div>' +
                        '</a>';
                    }
                    bodyHtml += '</div>'+
                        '</div>';

                }
            }

            $("div.ht-rili-body").html(bodyHtml);

            $('img.addWodPlus').on('click', function () {
                var date = $(this).attr("dateAttr");
                var courseTypeId = $("#courseTypeId").val();
                if(courseTypeId == null || courseTypeId == undefined){
                    alert("请选择课程信息");
                    return false;
                }
                window.location.href = ctx + "/wod/initTraining?wodTime="  + date + "&courseTypeId="+courseTypeId;
            });
            magnifier();
        },
        error:function(){
            return false;
        }
    });
}

function magnifier() {
    $('img.view').on('click',function () {
        var n = $(this).parents('.ht-rili-td').index();
        var wodName = $(this).next().html();
        var actionList = $(this).parents("div.ht-rili-con").find(".action-div");
        var actionHtml = '<strong class="close"></strong>';
        actionHtml += '<p class="pop-title">' + wodName + '</p>';
        if(actionList.length > 0){
            $(this).parents("div.ht-rili-con").find(".action-div").each(function(i){
                var oneAction = $(this);
                var isSectoin = oneAction.hasClass("section-cla");
                var isAction = oneAction.hasClass("action-cla");
                var title = oneAction.find("p").html();
                var lastType = 0;  //1 section 2 action 3 remark
                if(i == 0){
                    if(isSectoin){
                        actionHtml += '<div class="pop-block">'+
                        '<p  class="little-title">' +
                        title +
                        '</p>';
                        lastType = 1;
                    }else{
                        actionHtml += '<div class="pop-block">'+
                        '<div class="del-content">'+
                        '<p>' + title +
                        '</p>';
                        lastType = 2;
                    }
                }else{
                    if(!isSectoin && !isAction){
                        if(title != ""){
                            actionHtml +=  '<span style="font-style: italic;">'+ ''+ title + '</span>';
                        }
                        actionHtml += '</div>' +
                        '</div>';
                        lastType = 3;
                    }else if(isAction){
                        if(lastType != 3){
                            actionHtml += '</div>';
                        }
                        actionHtml += '<div class="del-content">'+
                        '<p class="little-title">' +
                        title+ '</p>';
                        lastType = 2;
                    }else if(isSectoin){
                        if(lastType != 3){
                            actionHtml += '</div>';
                        }
                        actionHtml += '<div class="pop-block">'+
                        '<p class="little-title">' +
                        title +
                        '</p>';
                        lastType = 1;
                    }
                }
            })
        }
        $(".pop-box").html(actionHtml);
        if(n == 0 || n == 7 || n == 14 || n== 21){
            $('.pop-box').css({
                top: 75 + Math.floor(n / 7) + 'px',
                left: 14 + 2 * 146 + 'px',
                right: 'inherit'
            }).fadeIn();
        }else if(n == 1 || n == 8 || n == 15|| n== 22){
            $('.pop-box').css({
                top: 75 + Math.floor(n / 7) + 'px',
                left: 14 + 3 * 146 + 'px',
                right: 'inherit'
            }).fadeIn();
        }else if(n == 2 || n == 9 || n == 16|| n== 23){
            $('.pop-box').css({
                top: 75 + Math.floor(n / 7) + 'px',
                left: 14 + 4 * 146 + 'px',
                right: 'inherit'
            }).fadeIn();
        }else if(n == 3 || n == 10 || n == 17|| n== 24){
            $('.pop-box').css({
                top: 75 + Math.floor(n / 7) + 'px',
                left: 14 + 5 * 146 + 'px',
                right: 'inherit'
            }).fadeIn();
        }else{
            $('.pop-box').css({
                top: 75 + Math.floor(n / 7) * 330 + 'px',
                right: 14 + (7 - n%7) * 146 + 'px',
                left: 'inherit'
            }).fadeIn();
        }

        $('strong.close').on('click', function (){
            $(".pop-box").fadeOut();
        });
    });



}

function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}