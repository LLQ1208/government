/**
 * Created with webstorm.
 * Author: dameng
 * Date: 2017/12/14
 * Time: 16:01
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
     $(".choose").selectpicker();
     $(".choose").on("change",function(){
          var ctx = $("#ctx").val();
          if($(this).val() == "2"){
               window.location.href= ctx+"/wod/queryWodList";
          }
     })
     //$('.wod').selectpicker();
     $('.commono').selectpicker();
     $('.text-Btn').on('click',function(){
          $('.text-Btn').removeClass('active');
          $(this).addClass('active');
          var wodContentId = $(this).attr("wodContentId");
          var ctx = $("#ctx").val();
          $.ajax({
               url:ctx + "/coachBackBoard/queryBoardByWodContent",
               type:'GET', //GET
               async:true,    //或false,是否异步
               data:{wodContentId:wodContentId},
               dataType:'json',
               contentType: "application/x-www-form-urlencoded; charset=utf-8",
               success:function(data){
                    var maleHtml = '';
                    if(data.maleList != null && data.maleList != undefined){
                         for(var i=0;i<data.maleList.length; i++){
                              var maleBoard = data.maleList[i];
                              maleHtml += '<li class="listCommon clearfix">'+
                              '<div class="malePic fl">';
                              if(maleBoard.ico == undefined || maleBoard.ico == null || maleBoard.ico ==''){
                                   maleHtml +=  '<img style="width: 100%;height: 100%;" src="'+ctx +'/resources/images/new-image/default_member.png"/>';
                              }else{
                                   maleHtml += '<img style="width: 100%;height: 100%;" src="'+maleBoard.ico+'"/>';
                              }

                              maleHtml += '</div>'+
                              '<div class="number">'+(i+1)+'</div>'+
                              '<div class="maleRight fl">'+
                              '<p class="one">'+maleBoard.memberName+'</p>'+
                              '<p class="two">'+maleBoard.courseTitle+'</p>'+
                              '<p class="three">'+maleBoard.record+'</p>'+
                              '<p class="four">'+maleBoard.remark+'</p>'+
                              '</div>';
                              if(maleBoard.isRx == '1'){
                                   maleHtml += '<div class="rx">Rx</div>';
                              }
                              maleHtml += '</li>';
                         }
                    }
                    $(".male-list").html(maleHtml);

                    var fmaleHtml = '';
                    if(data.fmaleList != null && data.fmaleList != undefined){
                         for(var i=0;i<data.fmaleList.length; i++){
                              var maleBoard = data.fmaleList[i];
                             fmaleHtml += '<li class="listCommon clearfix">'+
                              '<div class="malePic fl">';
                              if(maleBoard.ico == undefined || maleBoard.ico == null || maleBoard.ico ==''){
                                   fmaleHtml +=  '<img style="width: 100%;height: 100%;" src="'+ctx +'/resources/images/new-image/default_member.png"/>';
                              }else{
                                   fmaleHtml += '<img style="width: 100%;height: 100%;" src="'+maleBoard.ico+'"/>';
                              }
                              fmaleHtml +=
                             '</div>'+
                              '<div class="number">'+(i+1)+'</div>'+
                              '<div class="maleRight fl">'+
                              '<p class="one">'+maleBoard.memberName+'</p>'+
                              '<p class="two">'+maleBoard.courseTitle+'</p>'+
                              '<p class="three">'+maleBoard.record+'</p>'+
                              '<p class="four">'+maleBoard.remark+'</p>'+
                              '</div>';
                              if(maleBoard.isRx == '1'){
                                  fmaleHtml += '<div class="rx">Rx</div>';
                              }
                             fmaleHtml += '</li>';
                         }
                    }
                    $(".fmale-list").html(fmaleHtml);
               },
               error:function(){
                    return false;
               }
          });
     })

     $(".commono").on("change",function(){
          query();
     })
     $(".form_datetime").on("changeDate",function(e){
          query();
     })
})

function toDou(n) {
     return n > 9 ? n : '0' + n;
}

function query(){
     var boxId = $("#boxId").val();
     var courseType = $("#courseType").val();
     var date = $(".form_datetime").val();
     var ctx = $("#ctx").val();
     $.ajax({
          url:ctx + "/coachBackBoard/queryBoard",
          type:'GET', //GET
          async:true,    //或false,是否异步
          data:{boxId:boxId,courseType:courseType,date:date},
          dataType:'json',
          contentType: "application/x-www-form-urlencoded; charset=utf-8",
          success:function(data){

               var wodModel = data.wodModel;
               var wodHtml = "";
               var wodContentHtml = "";
               var wodName = "无";
               if(null != wodModel && undefined != wodModel){
                    var wodContentList = data.wodContentList;
                    if(null != wodContentList && undefined != wodContentList){
                         for(var i = 0; i < wodContentList.length; i++){
                              var wodContent = wodContentList[i];
                              var contentType = wodContent.contentType;
                              wodContentHtml += '<p class="text-Btn';
                              if(i == 0){
                                   wodContentHtml += ' active'
                              }
                              wodContentHtml += '" wodContentId="'+wodContent.wodContentId+'">';
                              if(contentType == "1"){
                                   wodContentHtml += (wodContent.seriaNum == null || wodContent.seriaNum == undefined || wodContent.seriaNum == '' ? '' : wodContent.seriaNum + '、');
                                   wodContentHtml += wodContent.contentEntity.name;
                              }else if(contentType == "2" || contentType == "3"){
                                   wodContentHtml += (wodContent.seriaNum == null || wodContent.seriaNum == undefined || wodContent.seriaNum == '' ? '' : wodContent.seriaNum + '、');
                                   wodContentHtml += wodContent.contentEntity.name;
                                   wodContentHtml += (wodContent.repsScheme!="" && wodContent.repsScheme !=null && wodContent.repsScheme != undefined ? '('+ wodContent.repsScheme +')' : '');
                              }else if(contentType == "4" || contentType == "5"){
                                   wodContentHtml += (wodContent.seriaNum == null || wodContent.seriaNum == undefined || wodContent.seriaNum == '' ? '' : wodContent.seriaNum + '、');
                                   wodContentHtml += wodContent.contentEntity.name;
                                   wodContentHtml += '('+ wodContent.contentRecordTypeName +')';
                              }else  if(contentType == "6"){
                                   wodContentHtml += (wodContent.seriaNum == null || wodContent.seriaNum == undefined || wodContent.seriaNum == '' ? '' : wodContent.seriaNum + '、');
                                   wodContentHtml += "WarmUp(no measure)";
                              }else if(contentType == "7" || contentType == "8"){
                                   wodContentHtml += (wodContent.seriaNum == null || wodContent.seriaNum == undefined || wodContent.seriaNum == '' ? '' : wodContent.seriaNum + '、');
                                   wodContentHtml += (wodContent.contentTitle == null || wodContent.contentTitle == "" || undefined == wodContent.contentTitle ? 'Metcon':wodContent.contentTitle);
                                   wodContentHtml += (wodContent.repsScheme!="" && wodContent.repsScheme !=null && wodContent.repsScheme != undefined ? '('+ wodContent.repsScheme +')' : '');
                              }else if(contentType == "9"){
                                   wodContentHtml += (wodContent.seriaNum == null || wodContent.seriaNum == undefined || wodContent.seriaNum == '' ? '' : wodContent.seriaNum + '、');
                                   wodContentHtml += (wodContent.contentTitle == null || wodContent.contentTitle == "" || undefined == wodContent.contentTitle ? 'Metcon':wodContent.contentTitle);
                                   wodContentHtml += '('+ wodContent.contentRecordTypeName +')';
                              }
                              wodContentHtml += '</p>';
                         }
                    }
                    //wod 名字
                    wodName = wodModel.wod.name;
                    //wod内容
                    if(wodModel.sectionModelList != null && undefined != wodModel.sectionModelList){
                         for(var j=0; j<wodModel.sectionModelList.length; j++){
                              var wodSection = wodModel.sectionModelList[j];
                              wodHtml += '<div class="typeCommon">';
                              if(wodSection.wodSection.type=='1'){
                                   wodHtml += '<h2 class="title">' + wodSection.wodSection.section.title + '</h2>';
                              }
                              if(null != wodSection.wodContentList && undefined != wodSection.wodContentList){
                                   for(var k=0;k<wodSection.wodContentList.length;k++){
                                        var wodContent = wodSection.wodContentList[k];
                                        if(wodContent.contentType == '1'){
                                             wodHtml += '<h3 class="subtitle">'+
                                             (wodContent.seriaNum == null || wodContent.seriaNum == undefined || wodContent.seriaNum == '' ? '' : wodContent.seriaNum + '、')+
                                             wodContent.contentEntity.name+
                                                 '</h3>'+
                                                 '<p class="record">'+
                                             (wodContent.contentEntity.description != null && wodContent.contentEntity.description != undefined ? wodContent.contentEntity.description.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "):'')+
                                                 '</p>'+
                                                 '<p class="notes">'+
                                             (wodContent.remark != null && wodContent.remark != undefined ? wodContent.remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "):'')+
                                                 '</p>';
                                        }else if(wodContent.contentType == '2' || wodContent.contentType == '3'){
                                             wodHtml += '<h3 class="subtitle">'+
                                             (wodContent.seriaNum == null || wodContent.seriaNum == undefined || wodContent.seriaNum == '' ? '' : wodContent.seriaNum + '、')+
                                             wodContent.contentEntity.name+
                                             (wodContent.repsScheme != null && wodContent.repsScheme != undefined ? '('+wodContent.repsScheme+')' : '')+
                                             '</h3>'+
                                             '<p class="record">'+
                                             (wodContent.contentEntity.description != null && wodContent.contentEntity.description != undefined ? wodContent.contentEntity.description.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "):'')+
                                             '</p>'+
                                             '<p class="notes">'+
                                             (wodContent.remark != null && wodContent.remark != undefined ? wodContent.remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "):'')+
                                             '</p>';
                                        } else if(wodContent.contentType == '4' || wodContent.contentType == '5'){
                                             wodHtml += '<h3 class="subtitle">'+
                                             (wodContent.seriaNum == null || wodContent.seriaNum == undefined || wodContent.seriaNum == '' ? '' : wodContent.seriaNum + '、')+
                                             wodContent.contentEntity.name+
                                            '('+wodContent.contentRecordTypeName + ')' +
                                             '</h3>'+
                                             '<p class="record">'+
                                             (wodContent.contentEntity.description != null && wodContent.contentEntity.description != undefined ? wodContent.contentEntity.description.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "):'')+
                                             '</p>'+
                                             '<p class="notes">'+
                                             (wodContent.remark != null && wodContent.remark != undefined ? wodContent.remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "):'')+
                                             '</p>';
                                        }else if(wodContent.contentType == '6'){
                                             wodHtml += '<h3 class="subtitle">'+
                                             (wodContent.seriaNum == null || wodContent.seriaNum == undefined || wodContent.seriaNum == '' ? '' : wodContent.seriaNum + '、')+
                                             wodContent.contentEntity.name+
                                             'WarmUp(no measure)'+
                                             '</h3>'+
                                             '<p class="record">'+
                                             (wodContent.descript != null && wodContent.descript != undefined ? wodContent.descript.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "):'')+
                                             '</p>'+
                                             '<p class="notes">'+
                                             (wodContent.remark != null && wodContent.remark != undefined ? wodContent.remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "):'')+
                                             '</p>';
                                        }else if(wodContent.contentType == '7' || wodContent.contentType == '8'){
                                             wodHtml += '<h3 class="subtitle">'+
                                             (wodContent.seriaNum == null || wodContent.seriaNum == undefined || wodContent.seriaNum == '' ? '' : wodContent.seriaNum + '、')+
                                             (wodContent.contentTitle == null || wodContent.contentTitle == "" || wodContent.contentTitle == undefined ? 'Metcon':wodContent.contentTitle) +
                                             (wodContent.repsScheme != null && wodContent.repsScheme != undefined ? '('+wodContent.repsScheme+')' : '')+
                                             '</h3>'+
                                             '<p class="record">'+
                                             (wodContent.descript != null && wodContent.descript != undefined ? wodContent.descript.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "):'')+
                                             '</p>'+
                                             '<p class="notes">'+
                                             (wodContent.remark != null && wodContent.remark != undefined ? wodContent.remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "):'')+
                                             '</p>';
                                        }else if(wodContent.contentType == '9'){
                                             wodHtml += '<h3 class="subtitle">'+
                                             (wodContent.seriaNum == null || wodContent.seriaNum == undefined || wodContent.seriaNum == '' ? '' : wodContent.seriaNum + '、')+
                                             (wodContent.contentTitle == null || wodContent.contentTitle == "" || wodContent.contentTitle == undefined ? 'Metcon':wodContent.contentTitle) +
                                             '('+wodContent.contentRecordTypeName + ')' +
                                             '</h3>'+
                                             '<p class="record">'+
                                             (wodContent.descript != null && wodContent.descript != undefined ? wodContent.descript.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "):'')+
                                             '</p>'+
                                             '<p class="notes">'+
                                             (wodContent.remark != null && wodContent.remark != undefined ? wodContent.remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "):'')+
                                             '</p>';
                                        }
                                   }
                              }
                              wodHtml += '</div>';
                         }
                    }
               }
               $(".textBtn").html(wodContentHtml);
               $('.text-Btn').on('click',function(){
                    $('.text-Btn').removeClass('active');
                    $(this).addClass('active');
                    var wodContentId = $(this).attr("wodContentId");
                    var ctx = $("#ctx").val();
                    $.ajax({
                         url:ctx + "/coachBackBoard/queryBoardByWodContent",
                         type:'GET', //GET
                         async:true,    //或false,是否异步
                         data:{wodContentId:wodContentId},
                         dataType:'json',
                         contentType: "application/x-www-form-urlencoded; charset=utf-8",
                         success:function(data){
                              var maleHtml = '';
                              if(data.maleList != null && data.maleList != undefined){
                                   for(var i=0;i<data.maleList.length; i++){
                                        var maleBoard = data.maleList[i];
                                        maleHtml += '<li class="listCommon clearfix">'+
                                        '<div class="malePic fl">';
                                         if(maleBoard.ico == undefined || maleBoard.ico == null || maleBoard.ico ==''){
                                              maleHtml +=  '<img style="width: 100%;height: 100%;" src="'+ctx +'/resources/images/new-image/default_member.png"/>';
                                         }else{
                                              maleHtml += '<img style="width: 100%;height: 100%;" src="'+maleBoard.ico+'"/>';
                                         }
                                        maleHtml +='</div>'+
                                        '<div class="number">'+(i+1)+'</div>'+
                                        '<div class="maleRight fl">'+
                                        '<p class="one">'+maleBoard.memberName+'</p>'+
                                        '<p class="two">'+maleBoard.courseTitle+'</p>'+
                                        '<p class="three">'+maleBoard.record+'</p>'+
                                        '<p class="four">'+maleBoard.remark+'</p>'+
                                        '</div>';
                                        if(maleBoard.isRx == '1'){
                                             maleHtml += '<div class="rx">Rx</div>';
                                        }
                                        maleHtml += '</li>';
                                   }
                              }
                              $(".male-list").html(maleHtml);

                              var fmaleHtml = '';
                              if(data.fmaleList != null && data.fmaleList != undefined){
                                   for(var i=0;i<data.fmaleList.length; i++){
                                        var maleBoard = data.fmaleList[i];
                                       fmaleHtml += '<li class="listCommon clearfix">'+
                                        '<div class="malePic fl">';
                                        if(maleBoard.ico == undefined || maleBoard.ico == null || maleBoard.ico ==''){
                                             fmaleHtml +=  '<img style="width: 100%;height: 100%;" src="'+ctx +'/resources/images/new-image/default_member.png"/>';
                                        }else{
                                             fmaleHtml += '<img style="width: 100%;height: 100%;" src="'+maleBoard.ico+'"/>';
                                        }
                                        fmaleHtml +='</div>'+
                                        '<div class="number">'+(i+1)+'</div>'+
                                        '<div class="maleRight fl">'+
                                        '<p class="one">'+maleBoard.memberName+'</p>'+
                                        '<p class="two">'+maleBoard.courseTitle+'</p>'+
                                        '<p class="three">'+maleBoard.record+'</p>'+
                                        '<p class="four">'+maleBoard.remark+'</p>'+
                                        '</div>';
                                        if(maleBoard.isRx == '1'){
                                            fmaleHtml += '<div class="rx">Rx</div>';
                                        }
                                       fmaleHtml += '</li>';
                                   }
                              }
                              $(".fmale-list").html(fmaleHtml);
                         },
                         error:function(){
                              return false;
                         }
                    });
               })
               $(".type").html(wodHtml);
               $(".wod").find("option:selected").text(wodName);

               var maleHtml = '';
               if(data.maleList != null && data.maleList != undefined){
                    for(var i=0;i<data.maleList.length; i++){
                         var maleBoard = data.maleList[i];
                         maleHtml += '<li class="listCommon clearfix">'+
                             '<div class="malePic fl">';
                              if(maleBoard.ico == undefined || maleBoard.ico == null || maleBoard.ico ==''){
                                   maleHtml +=  '<img style="width: 100%;height: 100%;" src="'+ctx +'/resources/images/new-image/default_member.png"/>';
                              }else{
                                   maleHtml += '<img style="width: 100%;height: 100%;" src="'+maleBoard.ico+'"/>';
                              }
                              maleHtml += '</div>'+
                             '<div class="number">'+(i+1)+'</div>'+
                             '<div class="maleRight fl">'+
                             '<p class="one">'+maleBoard.memberName+'</p>'+
                             '<p class="two">'+maleBoard.courseTitle+'</p>'+
                              '<p class="three">'+maleBoard.record+'</p>'+
                             '<p class="four">'+maleBoard.remark+'</p>'+
                             '</div>';
                             if(maleBoard.isRx == '1'){
                                  maleHtml += '<div class="rx">Rx</div>';
                             }
                         maleHtml += '</li>';
                    }
               }
               $(".male-list").html(maleHtml);

               var fmaleHtml = '';
               if(data.fmaleList != null && data.fmaleList != undefined){
                    for(var i=0;i<data.fmaleList.length; i++){
                         var maleBoard = data.fmaleList[i];
                         fmaleHtml += '<li class="listCommon clearfix">'+
                         '<div class="malePic fl">';
                         if(maleBoard.ico == undefined || maleBoard.ico == null || maleBoard.ico ==''){
                              fmaleHtml +=  '<img style="width: 100%;height: 100%;" src="'+ctx +'/resources/images/new-image/default_member.png"/>';
                         }else{
                              fmaleHtml += '<img style="width: 100%;height: 100%;" src="'+maleBoard.ico+'"/>';
                         }
                         fmaleHtml +='</div>'+
                         '<div class="number">'+(i+1)+'</div>'+
                         '<div class="maleRight fl">'+
                         '<p class="one">'+maleBoard.memberName+'</p>'+
                         '<p class="two">'+maleBoard.courseTitle+'</p>'+
                         '<p class="three">'+maleBoard.record+'</p>'+
                         '<p class="four">'+maleBoard.remark+'</p>'+
                         '</div>';
                         if(maleBoard.isRx == '1'){
                              fmaleHtml += '<div class="rx">Rx</div>';
                         }
                         fmaleHtml += '</li>';
                    }
               }
               $(".fmale-list").html(fmaleHtml);

               var dateWeek = "";
               if(data != undefined && data.dateWeek != null && data.dateWeek != undefined){
                    dateWeek = data.dateWeek;
               }
               $(".timeLeft").html(dateWeek);

               var wodName = "";
               if(wodModel != undefined && wodModel.wod != null && wodModel.wod != undefined && wodModel.wod.name != null && wodModel.wod.name != undefined){
                    wodName = wodModel.wod.name;
               }
               $(".timeRight").html(wodName);
          },
          error:function(){
               return false;
          }
     });
}
