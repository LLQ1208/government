/**
 * Created with webstorm.
 * Author: dameng
 * Date: 2017/12/11
 * Time: 21:29
 *
 */

$(function () {
    $('.form_datetime').datetimepicker({
        format: 'yyyy/mm/dd',
        language: 'zh-CN',
        minView: 2,
        initialDate: new Date(),
        autoclose: true
    });
    var oDate = new Date();
    $('.form_datetime').val(oDate.getFullYear() + '/' + toDou(oDate.getMonth() + 1) + '/' + oDate.getDate());
    $('.wodOne').selectpicker();
    $('.choose').selectpicker();
    $(".choose").on("change",function(){
        var ctx = $("#ctx").val();
        if($(this).val() == "2"){
            window.location.href= ctx+"/wod/queryWodList";
        }
    })
    $(".wodOne").on("change",function(){
        getWodContent();
    })
    var dayArr = ['日','一','二','三','四','五','六'];
    $(".form_datetime").on("changeDate",function(e){
        // var n = e.date.getDay();
        // var nowTime = $(".form_datetime").val();
        // $('span.timeLeft').html(nowTime + '星期'+dayArr[n]);
        getWodContent();
    })
})


function toDou(n) {
    return n > 9 ? n : '0' + n;
}


function getWodContent(){
    var boxId = $("#boxId").val();
    var courseType = $("#courseType").val();
    var date = $(".form_datetime").val();
    var ctx = $("#ctx").val();
    $.ajax({
        url:ctx + "/coachWod/queryWod",
        type:'GET', //GET
        async:true,    //或false,是否异步
        data:{boxId:boxId,courseType:courseType,date:date},
        dataType:'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function(data){
            var wodHtml = "";
            if(data.result == "no"){
                var wodModel = data;
                wodHtml += '<div class="time">'+
                '<span class="timeLeft">' + wodModel.date +'</span>'+
                '</div>';
            }else{
                var wodModel = data;
                wodHtml += '<div class="time">'+
                '<span class="timeLeft">' + wodModel.date +'</span>'+
                '<span class="timeRight">' + wodModel.wod.name + '</span>'+
                '</div>'+
                '<div class="content">';
                if(null != wodModel.sectionModelList && undefined != wodModel.sectionModelList){
                    for(var i=0; i<wodModel.sectionModelList.length; i++){
                        var wodSection = wodModel.sectionModelList[i];
                        if(wodSection.wodSection.type=='1'){
                            wodHtml += '<h2 class="title">'+wodSection.wodSection.section.title+'</h2>'+
                            '<p class="notes">'+(wodSection.wodSection.remark != null && wodSection.wodSection.remark != undefined ? wodSection.wodSection.remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '')+'</p>';

                        }
                        if(null != wodSection.wodContentList && undefined != wodSection.wodContentList){
                            for(var j=0; j<wodSection.wodContentList.length; j++){
                                var wodContent = wodSection.wodContentList[j];
                                var contentType = wodContent.contentType;
                                if(contentType == "1"){
                                    wodHtml += '<h3 class="subtitle">'+
                                    (wodContent.seriaNum!="" && wodContent.seriaNum !=null && wodContent.seriaNum !=undefined ? wodContent.seriaNum + '、' : '')+
                                    wodContent.contentEntity.name +
                                    '</h3>'+
                                    '<p class="record">'+ (wodContent.contentEntity.description != null && wodContent.contentEntity.description != ''  && wodContent.contentEntity.description != undefined ? wodContent.contentEntity.description.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '') +
                                    '</p>'+
                                    '<p class="notes">'+(wodContent.remark != null && wodContent.remark != undefined ? wodContent.remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '')+'</p>';
                                }else if(contentType == "2" || contentType == "3"){
                                    wodHtml += '<h3 class="subtitle">'+
                                    (wodContent.seriaNum!="" && wodContent.seriaNum !=null && wodContent.seriaNum !=undefined ? wodContent.seriaNum + '、' : '')+
                                    wodContent.contentEntity.name +
                                    (wodContent.repsScheme!="" && wodContent.repsScheme !=null && wodContent.repsScheme !=undefined ? '('+wodContent.repsScheme+')' : '')  +
                                    '</h3>'+
                                    '<p class="record">'+ (wodContent.contentEntity.description != null && wodContent.contentEntity.description != undefined ? wodContent.contentEntity.description.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '') +
                                    '</p>'+
                                    '<p class="notes">'+(wodContent.remark != null && wodContent.remark != undefined ? wodContent.remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '')+'</p>';
                                }else if(contentType == "4" || contentType == "5"){
                                    wodHtml += '<h3 class="subtitle">'+
                                    (wodContent.seriaNum!="" && wodContent.seriaNum !=null && wodContent.seriaNum !=undefined ? wodContent.seriaNum + '、' : '')+
                                    wodContent.contentEntity.name +
                                    '('+wodContent.contentRecordTypeName+')' +
                                    '</h3>'+
                                    '<p class="record">'+ (wodContent.contentEntity.description != null && wodContent.contentEntity.description != undefined ? wodContent.contentEntity.description.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '') +
                                    '</p>'+
                                    '<p class="notes">'+(wodContent.remark != null && wodContent.remark != undefined ? wodContent.remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '')+'</p>';
                                }else if(contentType == "6"){
                                    wodHtml += '<h3 class="subtitle">'+
                                    (wodContent.seriaNum!="" && wodContent.seriaNum !=null && wodContent.seriaNum !=undefined ? wodContent.seriaNum + '、' : '')+
                                    'WarmUp(no measure)' +
                                    '</h3>'+
                                    '<p class="record">'+ (wodContent.contentEntity.description != null && wodContent.contentEntity.description != undefined ? wodContent.contentEntity.description.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '') +
                                    '</p>'+
                                    '<p class="notes">'+(wodContent.remark != null && wodContent.remark != undefined ? wodContent.remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '')+'</p>';
                                }else if(contentType == "7" || contentType == "8"){
                                    wodHtml += '<h3 class="subtitle">'+
                                    (wodContent.seriaNum!="" && wodContent.seriaNum !=null && wodContent.seriaNum !=undefined ? wodContent.seriaNum + '、' : '')+
                                    (wodContent.contentTitle!="" && wodContent.contentTitle !=null && wodContent.contentTitle !=undefined ? wodContent.contentTitle + 'Metcon' : '') +
                                    (wodContent.repsScheme!="" && wodContent.repsScheme !=null && wodContent.repsScheme !=undefined ? '('+wodContent.repsScheme+')' : '')  +
                                    '</h3>'+
                                    '<p class="record">'+ (wodContent.descript != null && wodContent.descript != undefined ? wodContent.descript.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '') +
                                    '</p>'+
                                    '<p class="notes">'+(wodContent.remark != null && wodContent.remark != undefined ? wodContent.remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '')+'</p>';
                                }else if(contentType == "9"){
                                    wodHtml += '<h3 class="subtitle">'+
                                    (wodContent.seriaNum!="" && wodContent.seriaNum !=null && wodContent.seriaNum !=undefined ? wodContent.seriaNum + '、' : '')+
                                    (wodContent.contentTitle!="" && wodContent.contentTitle !=null && wodContent.contentTitle !=undefined ? wodContent.contentTitle + 'Metcon' : '') +
                                    '('+wodContent.contentRecordTypeName+')' +
                                    '</h3>'+
                                    '<p class="record">'+ (wodContent.descript != null && wodContent.descript != undefined ? wodContent.descript.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '') +
                                    '</p>'+
                                    '<p class="notes">'+(wodContent.remark != null && wodContent.remark != undefined ? wodContent.remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '')+'</p>';
                                }
                            }
                        }
                    }
                }
                wodHtml += '</div>';
            }
            $(".wodRight").html(wodHtml);
        },
        error:function(){
            return false;
        }
    });
}