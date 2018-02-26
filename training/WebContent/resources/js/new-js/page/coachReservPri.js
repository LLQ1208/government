/**
 * Created with webstorm.
 * Author: dameng
 * Date: 2017/12/11
 * Time: 21:29
 *
 */
var dayArr = ['日','一','二','三','四','五','六'];
$(function () {
    var ctx = $("#ctx").val();
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
    $('#boxId').on('change', function () {
        $('#calendar').fullCalendar( 'refetchEvents' );
    });
    $("#courseType").on("change",function(){
        var value = $(this).val();
        if(value == "2"){
            window.location.href=ctx + "/coachBackReser/reservationGroup";
        }
    })
    $(".form_datetime").on("changeDate",function(e){
        var date = $(this).val();
        var reg = new RegExp("/","g");//g,表示全部替换。
        var nowDate =  date.replace(reg,"-");
        var n = e.date.getDay();
        $('.timeLeft').html(nowDate + '星期'+dayArr[n]);
        var noTime = $.fullCalendar.moment(nowDate);
        $('#calendar').fullCalendar('gotoDate', noTime);
    })
    var dayArr = ['sun','mon','tue','wed','thu','fru','sat'];
    $('#courseStatus').on('change', function () {
        var status = $(this).val();
        if(status == "1"){
            if(!confirm("确定可预约？")){
                $('#courseStatus').selectpicker("val",2);
                return false;
            }
        }else if(status == "2"){
            if(!confirm("确定休息？")){
                $('#courseStatus').selectpicker("val",1);
                return false;
            }
        }
       var date = $(".form_datetime").val();
        var year = date.split("/")[0];
        var month = date.split("/")[1];
        var day = date.split("/")[2];
        var myDate=new Date()
        myDate.setFullYear(parseInt(year),parseInt(month)-1,parseInt(day));
        var n = myDate.getDay();
        var boxId = $("#boxId").val();
        var coachId = $("#coachId").val();
        var coachType = $("#coachType").val();
        $.ajax({
            type: 'get',
            dataType: 'json',
            jsonp: 'callback',
            url: ctx+'/coachBackReser/restDayCourse',
            data: {boxId:boxId,coachId:coachId,coachType:coachType,
                date:date,status:status},
            success: function (result) {

            },
            error: function () {

            }
        });
        if(status == 1){
            $(".fc-"+dayArr[n]).css('backgroundColor','#000');
        }else{
            $(".fc-"+dayArr[n]).css('backgroundColor','#808080');
        }
    });

    $('#start-hours,#start-minute,#end-hours,#end-minute').selectpicker();
    // 初始化日历
    $('#calendar').fullCalendar({
        defaultView: 'agendaWeek',	// 初始化视图
        header: {
            left: 'prev,next today',
            center: 'agendaWeek',
            right: 'title'
        },
        contentHeight: 'auto',
        minTime: '05:00:00',	// 开始时间
        selectable: true,
        selectHelper: true,
        allDaySlot: false,		// 是否显示allDay
        weekNumbers: false,
        weekNumbersWithinDays: true,
        weekNumberCalculation: 'ISO',
        slotDuration: '00:15:00',	// 每行间隔15分钟
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        events: function (start, end, timezone, callback) {
            var coachId = $("#coachId").val();
            var coachType = $("#coachType").val();
            var startTime = start.format("YYYY-MM-D");
            var endTime = end.format("YYYY-MM-D");
            var boxId = $("#boxId").val();
            $.ajax({
                type: 'get',
                dataType: 'json',
                jsonp: 'callback',
                url: ctx + '/coachBackReser/queryCoachCourseList',
                data: {coachId: coachId,coachType:coachType, startTime: startTime, endTime: endTime,
                    boxId:boxId},
                success: function (data) {
                    var res = data.courseList;
                    if (null != res && undefined != res) {
                        var events = [];
                        for (var i = 0; i < res.length; i++) {
                            events.push({
                                id: res[i].id,
                                title: res[i].capacity,
                                start: res[i].date + 'T' + res[i].startTime,
                                end: res[i].date + 'T' + res[i].endTime,
                                backgroundColor: '#39A535',
                                num:res[i].peopleLimit,
                                reservNum:res[i].reservNum
                            })
                        }
                        callback(events);
                    }
                    var restList = data.restList;
                    if(null != restList && undefined != restList){
                        for(var j = 0; j < restList.length; j++){
                            var weekTitle = restList[j].weekTitle;
                            $(".fc-"+weekTitle).css('backgroundColor','#808080');
                        }
                    }
                },
                error: function () {

                }
            })
        },
        select: function (start, end, jsEvent) {				// 添加
            var startTime = start.format('YYYY-MM-DD hh:mm:ss');
            var date = startTime.split(' ')[0];
            var time = startTime.split(' ')[1];
            var bh = parseInt(time.split(':')[0]);
            var bm = parseInt(time.split(':')[1]) / 15;
            var eh = Number(toDou(bh + 1));
            $('.add-tip').removeClass('edit-add-tip').fadeIn();
            $('#start-hours').selectpicker('val', bh);
            $('#start-minute').selectpicker('val', bm);
            $('#end-hours').selectpicker('val', eh);
            $('#end-minute').selectpicker('val', bm);
            $("#editDate").val(date);
            $('#studentNum').val(1);
            $("#editSign").hide();
            $(".delete").hide();

        },
        eventClick: function (calEvent, jsEvent, view) {		// 选中某个块
            //编辑时间显示
            var startTime= calEvent.start.format('YYYY-MM-DD HH:mm');
            var startDate = startTime.split(' ')[0];
            var time = startTime.split(' ')[1];
            var bh = parseInt(time.split(':')[0]);
            var bm = parseInt(time.split(':')[1]) / 15;
            var endTime= calEvent.end.format('YYYY-MM-DD HH:mm');
            var endDate = endTime.split(' ')[0];
            var etime = endTime.split(' ')[1];
            var eh = parseInt(etime.split(':')[0]);
            var em = parseInt(etime.split(':')[1]) / 15;
            $('#start-hours').selectpicker('val', bh);
            $('#start-minute').selectpicker('val', bm);
            $('#end-hours').selectpicker('val', eh);
            $('#end-minute').selectpicker('val', em);
            $("#editDate").val(startDate);
            $('#studentNum').val(calEvent.num);
            $('#editCourseId').val(calEvent.id);
            $('#editReservNum').val(calEvent.reservNum);
            if(calEvent.reservNum == 0){
                $(".cancel").show();
                $(".delete").show();
                // change the border color just for fun
                //$(this).css('background', 'red');
                var coachId = $("#coachId").val();
                var coachType = $("#coachType").val();
                var boxId = $("#boxId").val();
                $.ajax({
                    type: 'post',
                    dataType: 'json',
                    jsonp: 'callback',
                    url: ctx+'/coachBackReser/queryCoachCourseSignStatus',
                    data:{boxId:boxId,coachId:coachId,coachType:coachType,coachCourseId:calEvent.id,date:startDate},
                    success: function (result) {
                        if($("#editSign").hasClass("color-orange")){
                            $("#editSign").removeClass("color-orange").addClass("color-toSign").html("签到");
                        }
                        if(result.status == "y"){
                            $("#editSign").removeClass("color-toSign").addClass("color-orange").html("已签到");
                        }
                        $('.add-tip').addClass('edit-add-tip').fadeIn();
                    },
                    error: function () {

                    }
                });

            }else{
                var coachId = $("#coachId").val();
                var coachType = $("#coachType").val();
                var boxId = $("#boxId").val();
                $.ajax({
                    type: 'get',
                    dataType: 'json',
                    jsonp: 'callback',
                    url: ctx+'/coachBackReser/queryMemberList',
                    data:{boxId:boxId,coachId:coachId,coachType:coachType,coachCourseId:calEvent.id,date:startDate},
                    success: function (result) {
                        var list = result.list;
                        var memberHtml = "";
                        if(null != list && undefined != list && list.length>0){
                            for(var i=0;i<list.length;i++){
                                memberHtml += '<p>'+ list[i].name + '&nbsp;&nbsp;'+ list[i].phone+'</p>';
                            }
                        }
                        var signStatus = result.signStatus;
                        if($("#showSign").hasClass("color-orange")){
                            $("#showSign").removeClass("color-orange").addClass("color-toSign").html("签到");
                        }
                        if(signStatus== "y"){
                            $("#showSign").removeClass("color-toSign").addClass("color-orange").html("已签到");
                        }
                        $("#memberList").html(memberHtml);
                        $("#showStartTime").html(time);
                        $("#showEndTime").html(etime);
                        $("#showDate").val(startDate);
                        $("#showCourseId").val(calEvent.id);
                        $('.show-tip').fadeIn();
                    },
                    error: function () {

                    }
                });

            }
        },
        eventDragStop: function (event, jsEvent, ui, view) {		// 移动块停止

        },
        eventDrop: function(event, delta, minuteDelta, allDay, revertFunc, jsEvent, ui, view) {//停止拖动事件后 view.name=[month,agendaWeek,agendaDay]
            var startTime= event.start.format('YYYY-MM-DD HH:mm');
            var startDate = startTime.split(' ')[0];
            var time = startTime.split(' ')[1];
            var endTime= event.end.format('YYYY-MM-DD HH:mm');
            var etime = endTime.split(' ')[1];

            $.ajax({
                type: 'get',
                dataType: 'json',
                jsonp: 'callback',
                url: ctx+'/classSchedule/changeCoachCourseTime',
                data: {coachCourseId:event.id,startTime:time,endTime:etime,date:startDate},
                success: function (result) {

                },
                error: function () {

                }
            });
        },
        eventResize: function (event, jsEvent, ui, view) {
            var startTime= event.start.format('YYYY-MM-DD HH:mm');
            var startDate = startTime.split(' ')[0];
            var time = startTime.split(' ')[1];
            var endTime= event.end.format('YYYY-MM-DD HH:mm');
            var etime = endTime.split(' ')[1];

            $.ajax({
                type: 'get',
                dataType: 'json',
                jsonp: 'callback',
                url: ctx+'/classSchedule/changeCoachCourseTime',
                data: {coachCourseId:event.id,startTime:time,endTime:etime,date:startDate},
                success: function (result) {

                },
                error: function () {

                }
            });
        }
    });

    // 消失弹框
    $('.add-mask,.cancel').on('click', function (e) {
        $('.add-tip').fadeOut();
    });

    $('.add-mask,.showCan').on('click', function (e) {
        $('.show-tip').fadeOut();
    });
    $('.sure').on('click', function () {
        if (!$('.add-tip').hasClass('edit-add-tip')) {
            var studentNum = $("#studentNum").val();
            var date = $('#editDate').val();
            var sh = $('#start-hours').val();
            var sm = $('#start-minute').val();
            var fh = $('#end-hours').val();
            var fm = $('#end-minute').val();
            var eventData;
            if (studentNum != '') {
                //添加
                var beginHour = $("#start-hours").find("option:selected").text();
                var beginMinute = $("#start-minute").find("option:selected").text();
                var endHour = $("#end-hours").find("option:selected").text();
                var endMinute = $("#end-minute").find("option:selected").text();
                var coachId = $("#coachId").val();
                var editCourseId = $("#editCourseId").val();
                var coachType = $("#coachType").val();
                var boxId = $("#boxId").val();
                if(studentNum == "" || isNaN(studentNum) || parseFloat(studentNum)%1 != 0){
                    alert("请输入合理的人数");
                    return false;
                }
                var beginTime = beginHour.length < 2 ? "0"+beginHour+":"+beginMinute : beginHour+":"+beginMinute;
                var entTime = endHour.length < 2 ? "0"+endHour+":" + endMinute : endHour+":" + endMinute;
                if(beginTime >= entTime){
                    alert("时间不正确");
                    return false;
                }
                $.ajax({
                    type: 'get',
                    dataType: 'json',
                    jsonp: 'callback',
                    url: ctx+'/classSchedule/addCoachCourse',
                    data: {beginTime:beginTime,entTime:entTime,studentNum:studentNum,
                        date:date,coachId:coachId,editCourseId:'',coachType:coachType,boxId:boxId},
                    success: function (result) {
                        eventData = {
                            id: result,
                            title:  '\n\r0/' + $('.number').val(),
                            start: date + 'T' + toDou(sh) + ':' + toDou(sm * 15) + ':00',
                            end: date + 'T' + toDou(fh) + ':' + toDou(fm * 15) + ':00',
                            backgroundColor: '#39A535',
                            num:studentNum
                        };
                        $('#calendar').fullCalendar('renderEvent', eventData, false); // stick? = true
                        $('#calendar').fullCalendar('unselect');
                    },
                    error: function () {

                    }
                });
                $('.add-tip').fadeOut();
            }
        }else{
            var studentNum = $("#studentNum").val();
            var date = $('#editDate').val();
            var editCourseId = $('#editCourseId').val();
            var sh = $('#start-hours').val();
            var sm = $('#start-minute').val();
            var fh = $('#end-hours').val();
            var fm = $('#end-minute').val();
            var coachId = $("#coachId").val();
            var coachType = $("#coachType").val();
            var boxId = $("#boxId").val();
            var eventData;
            //编辑
            var beginHour = $("#start-hours").find("option:selected").text();
            var beginMinute = $("#start-minute").find("option:selected").text();
            var endHour = $("#end-hours").find("option:selected").text();
            var endMinute = $("#end-minute").find("option:selected").text();

            if(studentNum == "" || isNaN(studentNum) || parseFloat(studentNum)%1 != 0){
                alert("请输入合理的人数");
                return false;
            }
            $('.add-tip').fadeOut();
            var beginTime = beginHour.length < 2 ? "0"+beginHour+":"+beginMinute : beginHour+":"+beginMinute;
            var entTime = endHour.length < 2 ? "0"+endHour+":" + endMinute : endHour+":" + endMinute;
            if(beginTime >= entTime){
                alert("时间不正确");
                return false;
            }
            if (studentNum != '') {
                eventData = {
                    id: editCourseId,
                    title: '\n\r0/' + $('.number').val(),
                    start: date + 'T' + toDou(sh) + ':' + toDou(sm * 15) + ':00',
                    end: date + 'T' + toDou(fh) + ':' + toDou(fm * 15) + ':00',
                    backgroundColor: '#39A535',
                    num:studentNum
                };
                $('#calendar').fullCalendar('removeEvents', editCourseId)
                $('#calendar').fullCalendar('renderEvent', eventData, false)
            }


            $.ajax({
                type: 'get',
                dataType: 'json',
                jsonp: 'callback',
                url: ctx+'/classSchedule/addCoachCourse',
                data: {beginTime:beginTime,entTime:entTime,studentNum:studentNum,
                    date:date,coachId:coachId,editCourseId:editCourseId,coachType:coachType,boxId:boxId},
                success: function (result) {

                },
                error: function () {

                }
            });
        }
    });
    signInit();
})


function toDou(n) {
    return n > 9 ? n : '0' + n;
}


function signInit(){
    var ctx = $("#ctx").val();
    $("#editSign").on("click",function(){
        var isHasSign = $(this).hasClass("color-orange");
        var boxId = $("#boxId").val();
        var coachId = $("#coachId").val();
        var coachType = $("#coachType").val();
        var date = $('#editDate').val();
        var coachCourseId = $('#editCourseId').val();
        if(isHasSign){
            if(confirm("是否取消签到?")){
                $(this).removeClass("color-orange").addClass("color-toSign").html("签到");
                $.ajax({
                    url:ctx + "/coachBackReser/coachCourseSign",
                    type:'POST', //GET
                    async:true,    //或false,是否异步
                    data:{coachCourseId:coachCourseId,signStatus:2,boxId:boxId,coachId:coachId,coachType:coachType,date:date},
                    dataType:'json',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success:function(data){
                    },
                    error:function(){
                        return false;
                    }
                });
            }
        }else{
            if(confirm("是否签到?")){
                $(this).removeClass("color-toSign").addClass("color-orange").html("已签到");
                $.ajax({
                    url:ctx + "/coachBackReser/coachCourseSign",
                    type:'POST', //GET
                    async:true,    //或false,是否异步
                    data:{coachCourseId:coachCourseId,signStatus:1,boxId:boxId,coachId:coachId,coachType:coachType,date:date},
                    dataType:'json',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success:function(data){
                    },
                    error:function(){
                        return false;
                    }
                });
            }
        }
    });
    $("#showSign").on("click",function(){
        var isHasSign = $(this).hasClass("color-orange");
        var boxId = $("#boxId").val();
        var coachId = $("#coachId").val();
        var coachType = $("#coachType").val();
        var date = $('#showDate').val();
        var coachCourseId = $('#showCourseId').val();
        if(isHasSign){
            if(confirm("是否取消签到?")){
                $(this).removeClass("color-orange").addClass("color-toSign").html("签到");
                $.ajax({
                    url:ctx + "/coachBackReser/coachCourseSign",
                    type:'POST', //GET
                    async:true,    //或false,是否异步
                    data:{coachCourseId:coachCourseId,signStatus:2,boxId:boxId,coachId:coachId,coachType:coachType,date:date},
                    dataType:'json',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success:function(data){
                    },
                    error:function(){
                        return false;
                    }
                });
            }
        }else{
            if(confirm("是否签到?")){
                $(this).removeClass("color-toSign").addClass("color-orange").html("已签到");
                $.ajax({
                    url:ctx + "/coachBackReser/coachCourseSign",
                    type:'POST', //GET
                    async:true,    //或false,是否异步
                    data:{coachCourseId:coachCourseId,signStatus:1,boxId:boxId,coachId:coachId,coachType:coachType,date:date},
                    dataType:'json',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success:function(data){
                    },
                    error:function(){
                        return false;
                    }
                });
            }
        }
    });
}