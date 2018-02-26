/**
 * Created with webstorm.
 * Author: dameng
 * Date: 2017/12/16
 * Time: 20:41
 *
 */
$(function () {
    $('.choose').selectpicker();
    $('.box-choose').selectpicker();
    $('.midLine').css({'height':$('.left').height()});
    $('.topchoose').selectpicker();
    $(".topchoose").on("change",function(){
        var ctx = $("#ctx").val();
        if($(this).val() == "2"){
            window.location.href= ctx+"/wod/queryWodList";
        }
    })
    $("#wordType").on("change",function(){
        getContentList();
    })
    $("#boxId").on("change",function(){
        getBoxContentList();
    })
})

function getContentList(){
    var boxId = $("#boxId").val();
    var populorType = $("#wordType").val();
    var ctx = $("#ctx").val();
    $.ajax({
        type: 'get',
        dataType: 'json',
        url: ctx +'/coachBackRand/queryByPopulorType',
        data: {
            boxId: boxId,
            populorType:populorType
        },
        success: function (data) {
            // 历史数据
            var contentList = data.result;
            var contentHtml = "";
            if(null != contentList && undefined != contentList){
                for(var i=0;i<contentList.length;i++){
                    var contentName = contentList[i].contentName;
                    var maleList = contentList[i].maleList;
                    var fmaleList = contentList[i].fmaleList;
                    contentHtml += '<div class="commonList clearfix">'+
                        '<div class="title">' + contentName + '</div>'+
                        '<div class="left fl">' +
                        '<div class="leftTitle">MALE</div>'+
                        '<ul class="contentList clearfix">';
                    if(null != maleList && undefined != maleList){
                        for(var m=0;m < maleList.length; m++){
                            var male = maleList[m];
                            contentHtml += '<li class="clearfix">' +
                                '<p class="name fl">'+ (m+1) + '.' + male.memberName + '</p>' +
                                '<p class="date fl">' + male.day + '</p>' +
                                '<p class="time fl">' + male.time + '</p>' +
                                '</li>';
                        }
                    }
                    contentHtml += '</ul>'+
                        '</div>'+
                        '<div class="midLine fl"></div>'+
                        '<div class="left fl">'+
                        '<div class="leftTitle">FEMALE</div>'+
                        '<ul class="contentList clearfix">';
                        if(null != fmaleList && undefined != fmaleList){
                            for(var f=0;f<fmaleList.length;f++){
                                var fmale = fmaleList[f];
                                contentHtml += '<li class="clearfix">'+
                                    '<p class="name fl">' + (f+1) + '.' + fmale.memberName + '</p>'+
                                    '<p class="date fl">' + fmale.day + '</p>' +
                                    '<p class="time fl">' + fmale.time + '</p>'+
                                    '</li>';
                            }
                        }
                    contentHtml += '</ul>'+
                        '</div>'+
                    '</div>';
                }
            }
            $("#contentList").html(contentHtml);
        },
        error: function () {}
    })
}

function getBoxContentList(){
    var ctx = $("#ctx").val();
    var boxId = $("#boxId").val();
    var typeHtml = '<option value="1">GIRLS</option>'+
            '<option value="2">HERO</option>'+
            '<option value="3">GAMES</option>';
    $("#wordType").html(typeHtml);
    $("#wordType").selectpicker("refresh");
    $.ajax({
        type: 'get',
        dataType: 'json',
        url: ctx +'/coachBackRand/queryByPopulorType',
        data: {
            boxId: boxId,
            populorType:1
        },
        success: function (data) {
            // 历史数据
            var contentList = data.result;
            var contentHtml = "";
            if(null != contentList && undefined != contentList){
                for(var i=0;i<contentList.length;i++){
                    var contentName = contentList[i].contentName;
                    var maleList = contentList[i].maleList;
                    var fmaleList = contentList[i].fmaleList;
                    contentHtml += '<div class="commonList clearfix">'+
                    '<div class="title">' + contentName + '</div>'+
                    '<div class="left fl">' +
                    '<div class="leftTitle">MALE</div>'+
                    '<ul class="contentList clearfix">';
                    if(null != maleList && undefined != maleList){
                        for(var m=0;m < maleList.length; m++){
                            var male = maleList[m];
                            contentHtml += '<li class="clearfix">' +
                            '<p class="name fl">'+ (m+1) + '.' + male.memberName + '</p>' +
                            '<p class="date fl">' + male.day + '</p>' +
                            '<p class="time fl">' + male.time + '</p>' +
                            '</li>';
                        }
                    }
                    contentHtml += '</ul>'+
                    '</div>'+
                    '<div class="midLine fl"></div>'+
                    '<div class="left fl">'+
                    '<div class="leftTitle">FEMALE</div>'+
                    '<ul class="contentList clearfix">';
                    if(null != fmaleList && undefined != fmaleList){
                        for(var f=0;f<fmaleList.length;f++){
                            var fmale = fmaleList[f];
                            contentHtml += '<li class="clearfix">'+
                            '<p class="name fl">' + (f+1) + '.' + fmale.memberName + '</p>'+
                            '<p class="date fl">' + fmale.day + '</p>' +
                            '<p class="time fl">' + fmale.time + '</p>'+
                            '</li>';
                        }
                    }
                    contentHtml += '</ul>'+
                    '</div>'+
                    '</div>';
                }
            }
            $("#contentList").html(contentHtml);
        },
        error: function () {}
    })
}