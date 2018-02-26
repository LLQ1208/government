/**
 * Created by anran on 2017/12/3.
 */
var ctx = $("#ctx").val();
$(function () {
    //下拉框
    $('.filters,.filters-s,#start-hours,#start-minute,#end-hours,#end-minute').selectpicker();
    //时间插件
    $('.form_datetime').datetimepicker({
        format: 'yyyy/mm/dd',
        language: 'zh-CN',
        minView: 2,
        initialDate: new Date(),
        autoclose: true
    });
    var oDate = new Date();
    $('.form_datetime').val(oDate.getFullYear() + '/' + toDou(oDate.getMonth() + 1) + '/' + oDate.getDate());
    $('.form_datetime').on('changeDate', function (e) {
        listClass();
    });

    // 点击添加按钮
    $('.add-btn').on('click', function () {
        var optionFirst = $("#editCoach").children('option').first().attr("value");
        $("#editCoach").selectpicker('val',optionFirst);
        var optionFirst = $("#editCourseType").children('option').first().attr("value");
        $("#editCourseType").selectpicker('val',optionFirst);
        $("#studentNum").val(10);
        $('.add-tip').fadeIn();
    });
    var startH = toDou(oDate.getHours());
    var endH = (oDate.getHours()+1)>24?1:(oDate.getHours()+1)
    $('#start-hours').selectpicker('val',startH);
    $('#end-hours').selectpicker('val',toDou(endH));


    //切换选项卡
    $('.schedule-classify li').on('click', function () {
        var liName = $(this).attr("name");
        if(liName == "listClass"){
            listClass();
            $("div.list-box").show();
            $("div.week-box").hide();
            $(".date-box").show();
            $(".add-btn").show();

        }else if(liName == "weekClass"){
            $("div.list-box").hide();
            $("div.week-box").show();
            $(".date-box").hide();
            $(".add-btn").hide();
        }
        $(this).addClass('active').siblings('li').removeClass('active');
    });
    // 初始化日历
    $('#calendar').fullCalendar({
        defaultView: 'agendaWeek',	// 初始化视图
        header: {
            left: 'prev,next today',
            center: 'agendaDay,agendaWeek',
            right: 'title'
        },
        contentHeight: 'auto',
        minTime: '05:00:00',	// 开始时间
        selectable: true,
        selectHelper: true,
        allDaySlot: false,		// 是否显示allDay
        weekNumbers: true,
        weekNumbersWithinDays: true,
        weekNumberCalculation: 'ISO',
        slotDuration: '00:15:00',	// 每行间隔15分钟
        editable: true,
        eventLimit: true, // allow "more" link when too many events
        events: function (start, end, timezone, callback) {
            var boxId = $("#boxId").val();
            var courseType = $("#courseType").val();
            var coach = $("#coach").val();
            var weekOrDay = $(".day-box").is(":hidden");
            var weekShow = weekOrDay ? 2 : 1;
            var startTime = start.format("YYYY-MM-D");
            var endTime = end.format("YYYY-MM-D");
            $.ajax({
                type: 'get',
                dataType: 'json',
                jsonp: 'callback',
                url: ctx + '/classSchedule/queryClassScheduleList',
                data: {boxId: boxId, courseType: courseType, coach: coach, weekShow: weekShow, start: startTime, end: endTime},
                success: function (data) {
                    $('#calendar').fullCalendar('removeEvents');
                    if (null != data && undefined != data) {
                        var res = data;
                        var events = [];
                        for (var i = 0; i < res.length; i++) {
                            events.push({
                                id: res[i].id,
                                title: res[i].crouseTypeName + '\n\r' + res[i].masterName + '\n\r' + res[i].capacity,
                                start: res[i].date + 'T' + res[i].beginTime,
                                end: res[i].date + 'T' + res[i].endTime,
                                backgroundColor: res[i].color,
                                courseTypeId:res[i].courseTypeId,
                                coachId:res[i].coachId,
                                num:res[i].num
                            })
                        }
                    }
                    callback(events);
                },
                error: function () {

                }
            })
            getCourseColor();
        },

        select: function (start, end, jsEvent) {				// 添加
            // var title = prompt('Event Title:');
            //x++;
            var startTime = start.format('YYYY-MM-DD HH:mm:ss');
            var date = startTime.split(' ')[0];
            var time = startTime.split(' ')[1];
            var bh = parseInt(time.split(':')[0]);
            var bm = parseInt(time.split(':')[1]) / 15;
            var eh = Number(toDou(bh + 1));
            //将选择的值默认给弹出框
            var courseTypeId = $("#courseType").val();
            var coachId= $("#coach").val();
            if(courseTypeId != null && courseTypeId != undefined && courseTypeId != ""){
                $("#editCourseType").selectpicker('val',courseTypeId);
            }else{
                var optionFirst = $("#editCourseType").children('option').first().attr("value");
                $("#editCourseType").selectpicker('val',optionFirst);
            }

            if(coachId != null && coachId != undefined && coachId != ""){
                $("#editCoach").selectpicker('val',coachId);
            }else{
                var optionFirst = $("#editCoach").children('option').first().attr("value");
                $("#editCoach").selectpicker('val',optionFirst);
            }

            $('.add-tip').removeClass('edit-add-tip').fadeIn();
            $('#start-hours').selectpicker('val', bh);
            $('#start-minute').selectpicker('val', bm);
            $('#end-hours').selectpicker('val', eh);
            $('#end-minute').selectpicker('val', bm);
            $("#editDate").val(date);
            $('#studentNum').val(10);
            $(".delete").hide();

        },
        eventClick: function (calEvent, jsEvent, view) {		// 选中某个块
            //编辑时间显示
            var startTime= calEvent.start.format('YYYY-MM-DD HH:mm:ss');
            var startDate = startTime.split(' ')[0];
            var time = startTime.split(' ')[1];
            var bh = parseInt(time.split(':')[0]);
            var bm = parseInt(time.split(':')[1]) / 15;
            var endTime= calEvent.end.format('YYYY-MM-DD HH:mm:ss');
            var endDate = endTime.split(' ')[0];
            var etime = endTime.split(' ')[1];
            var eh = parseInt(etime.split(':')[0]);
            var em = parseInt(etime.split(':')[1]) / 15;
            $('#start-hours').selectpicker('val', bh);
            $('#start-minute').selectpicker('val', bm);
            $('#end-hours').selectpicker('val', eh);
            $('#end-minute').selectpicker('val', em);
            $("#editDate").val(startDate);
            $('.add-tip').addClass('edit-add-tip').fadeIn();
            //课程类型和教练
            var courseTypeId = calEvent.courseTypeId;
            var coachId = calEvent.coachId;
            var num = calEvent.num;;
            $('#editCourseType').selectpicker('val', courseTypeId);
            $('#editCoach').selectpicker('val', coachId);
            $('#studentNum').val(num);
            //id
            $('#editCourseId').val(calEvent.id);
            $(".cancel").show();
            $(".delete").show();
            // change the border color just for fun
            //$(this).css('background', 'red');
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
                url: ctx+'/classSchedule/changeCourseTime',
                data: {courseId:event.id,startTime:time,endTime:etime,date:startDate},
                success: function (result) {

                },
                error: function () {

                }
            });
        },
        eventResize: function (event,dayDelta,minuteDelta,revertFunc) {
            var startTime= event.start.format('YYYY-MM-DD HH:mm');
            var startDate = startTime.split(' ')[0];
            var time = startTime.split(' ')[1];
            var endTime= event.end.format('YYYY-MM-DD HH:mm');
            var etime = endTime.split(' ')[1];

            $.ajax({
                type: 'get',
                dataType: 'json',
                jsonp: 'callback',
                url: ctx+'/classSchedule/changeCourseTime',
                data: {courseId:event.id,startTime:time,endTime:etime,date:startDate},
                success: function (result) {

                },
                error: function () {

                }
            });
        }
    });

    //下拉框改变触发查询
    $(".filters").on("change",function(){
        getWeek();
        listClass();
    });

    $('.sure').on('click', function () {
        var weekOrList = $("li[name='listClass']").hasClass("active");
        if(weekOrList){
            //添加
            var date = $("#listDate").val();
            date = date.replace("/","-");
            date = date.replace("/","-");
            var gym = $('.training-gym-s').find('option:selected').text();
            var ty = $('.schedule-type-s').find('option:selected').text();
            var coach = $('.coach-s').find('option:selected').text();
            var beginHour = $("#start-hours").find("option:selected").text();
            var beginMinute = $("#start-minute").find("option:selected").text();
            var endHour = $("#end-hours").find("option:selected").text();
            var endMinute = $("#end-minute").find("option:selected").text();
            var boxId = $("#boxId").val();
            var editCourseType = $("#editCourseType").val();
            var editCoach = $("#editCoach").val();
            var studentNum = $("#studentNum").val();

            if(editCoach == null || editCoach == "" || editCourseType == undefined ){
                alert("请选择教练");
                return false;
            }
            if(editCourseType == null || editCourseType == "" || editCourseType == undefined){
                alert("请选择课程类型");
                return false;
            }
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
            $.ajax({
                type: 'get',
                dataType: 'json',
                jsonp: 'callback',
                url: ctx+'/classSchedule/addClassSchedule',
                data: {beginTime:beginTime,entTime:entTime,boxId:boxId,editCourseType:editCourseType,
                    editCoach:editCoach,studentNum:studentNum,date:date,courseId:"",week:0},
                success: function (result) {
                    listClass();
                    getCourseColor();
                },
                error: function () {

                }
            });
        }else{
            if (!$('.add-tip').hasClass('edit-add-tip')) {
                var studentNum = $("#studentNum").val();
                var date = $('#editDate').val();
                var sh = $('#start-hours').val();
                var sm = $('#start-minute').val();
                var fh = $('#end-hours').val();
                var fm = $('#end-minute').val();
                var gym = $('.training-gym-s').find('option:selected').text();
                var ty = $('.schedule-type-s').find('option:selected').text();
                var coach = $('.coach-s').find('option:selected').text();
                var eventData;
                if ($('.training-gym-s').val() != '' && $('.schedule-type-s').val() != '' && $('.coach-s').val() != '') {
                    //添加
                    var beginHour = $("#start-hours").find("option:selected").text();
                    var beginMinute = $("#start-minute").find("option:selected").text();
                    var endHour = $("#end-hours").find("option:selected").text();
                    var endMinute = $("#end-minute").find("option:selected").text();
                    var boxId = $("#boxId").val();
                    var editCourseType = $("#editCourseType").val();
                    var editCoach = $("#editCoach").val();

                    if(editCoach == null || editCoach == "" || editCourseType == undefined ){
                        alert("请选择教练");
                        return false;
                    }
                    if(editCourseType == null || editCourseType == "" || editCourseType == undefined){
                        alert("请选择课程类型");
                        return false;
                    }
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
                    $.ajax({
                        type: 'get',
                        dataType: 'json',
                        jsonp: 'callback',
                        url: ctx+'/classSchedule/addClassSchedule',
                        data: {beginTime:beginTime,entTime:entTime,boxId:boxId,editCourseType:editCourseType,
                            editCoach:editCoach,studentNum:studentNum,date:date,courseId:"",week:1},
                        success: function (result) {
                            var color = "#" + result.color;
                            var id = result.id;
                            eventData = {
                                id: id,
                                title: gym + '\n\r' + ty + '\n\r' + coach + '\n\r0/' + $('.number').val(),
                                start: date + 'T' + toDou(sh) + ':' + toDou(sm * 15) + ':00',
                                end: date + 'T' + toDou(fh) + ':' + toDou(fm * 15) + ':00',
                                courseTypeId:editCourseType,
                                backgroundColor: color,
                                coachId:editCoach,
                                num:studentNum
                            };
                            console.log(eventData)
                            $('#calendar').fullCalendar('renderEvent', eventData, false)
                            //getCourseColor();
                        },
                        error: function () {

                        }
                    });
                }
            }else{
                var studentNum = $("#studentNum").val();
                var date = $('#editDate').val();
                var editCourseId = $('#editCourseId').val();
                var sh = $('#start-hours').val();
                var sm = $('#start-minute').val();
                var fh = $('#end-hours').val();
                var fm = $('#end-minute').val();
                var gym = $('.training-gym-s').find('option:selected').text();
                var ty = $('.schedule-type-s').find('option:selected').text();
                var coach = $('.coach-s').find('option:selected').text();
                var eventData;
                //编辑
                var beginHour = $("#start-hours").find("option:selected").text();
                var beginMinute = $("#start-minute").find("option:selected").text();
                var endHour = $("#end-hours").find("option:selected").text();
                var endMinute = $("#end-minute").find("option:selected").text();
                var boxId = $("#boxId").val();
                var editCourseType = $("#editCourseType").val();
                var editCoach = $("#editCoach").val();

                var courseId = $("#editCourseId").val();
                if(editCoach == null || editCoach == "" || editCourseType == undefined ){
                    alert("请选择教练");
                    return false;
                }
                if(editCourseType == null || editCourseType == "" || editCourseType == undefined){
                    alert("请选择课程类型");
                    return false;
                }
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
                $.ajax({
                    type: 'get',
                    dataType: 'json',
                    jsonp: 'callback',
                    url: ctx+'/classSchedule/addClassSchedule',
                    data: {beginTime:beginTime,entTime:entTime,boxId:boxId,editCourseType:editCourseType,
                        editCoach:editCoach,studentNum:studentNum,date:date,courseId:courseId,week:1},
                    success: function (result) {
                        var color = "#" + result.color;
                        eventData = {
                            id: editCourseId,
                            title: gym + '\n\r' + ty + '\n\r' + coach + '\n\r0/' + $('.number').val(),
                            start: date + 'T' + toDou(sh) + ':' + toDou(sm * 15) + ':00',
                            end: date + 'T' + toDou(fh) + ':' + toDou(fm * 15) + ':00',
                            backgroundColor: color,
                            num:studentNum
                        };
                        console.log(eventData)
                        $('#calendar').fullCalendar('removeEvents', editCourseId)
                        $('#calendar').fullCalendar('renderEvent', eventData, false)
                        getCourseColor();
                    },
                    error: function () {

                    }
                });
            }
        }
        getCourseColor();
    });


    $('.delete ').on('click',function(){
        if(confirm("确定删除这节课?")){
            var editCourseId = $('#editCourseId').val();
            $('#calendar').fullCalendar('removeEvents', editCourseId)
            $('.add-tip').fadeOut();
            $.ajax({
                type: 'get',
                dataType: 'json',
                jsonp: 'callback',
                url: ctx+'/classSchedule/deleteCourseById',
                data: {courseId:editCourseId},
                success: function (result) {
                    getCourseColor();
                },
                error: function () {

                }
            });
        }
    });

    $('.cancel ').on('click',function(){
        $('.add-tip').fadeOut();
    });
    listClass();
});

function toDou(n) {
    return n > 9 ? n : '0' + n;
}



function getWeek() {
        var boxId = $("#boxId").val();
        var courseType = $("#courseType").val();
        var coach = $("#coach").val();
        var weekOrDay = $(".day-box").is(":hidden");
        var weekShow = weekOrDay ? 2 : 1;
        var start = $('#calendar').fullCalendar('getView').start;
        var end = $('#calendar').fullCalendar('getView').end;
        var startTime = start.format("YYYY-MM-D");
        var endTime = end.format("YYYY-MM-D");
        $.ajax({
            type: 'get',
            dataType: 'json',
            jsonp: 'callback',
            url: ctx + '/classSchedule/queryClassScheduleList',
            data: {boxId: boxId, courseType: courseType, coach: coach, weekShow: weekShow, start: startTime, end: endTime},
            success: function (data) {
                if (null != data && undefined != data) {
                    var res = data;
                    var events = [];
                    for (var i = 0; i < res.length; i++) {
                        events.push({
                            id: res[i].id,
                            title: res[i].crouseTypeName + '\n\r' + res[i].masterName + '\n\r' + res[i].capacity,
                            start: res[i].date + 'T' + res[i].beginTime,
                            end: res[i].date + 'T' + res[i].endTime,
                            backgroundColor: res[i].color,
                            courseTypeId:res[i].courseTypeId,
                            coachId:res[i].coachId,
                            num:res[i].num
                        })
                    }
                    $('#calendar').fullCalendar( 'removeEvents');
                    $('#calendar').fullCalendar( 'addEventSource', events);
                    $('#calendar').fullCalendar({
                        events:events
                    });
                }
            },
            error: function () {

            }
        })
    getCourseColor();
}

//课程列表刷新
function listClass(){
    var boxId = $("#boxId").val();
    var courseType = $("#courseType").val();
    var coach = $("#coach").val();
    var date = $("#listDate").val();
    var boxName = $('#boxId').find('option:selected').text();
    $("#classListUl").html("");
    $.ajax({
        type: 'get',
        dataType: 'json',
        jsonp: 'callback',
        url: ctx + '/classSchedule/queryClassListOfDay',
        data: {boxId: boxId, courseType: courseType, coach: coach, date: date},
        success: function (data) {
            if (null != data && undefined != data) {
                var classListHtml = "";
                for(var i=0;i<data.length;i++){
                    classListHtml += '<li class="clearfix class_list_li" >'+
                            '<input type="hidden" class="liCourseId" value="'+data[i].id+'">'+
                            '<p class="class-name fl">'+data[i].crouseTypeName + '</p>'+
                            '<p class="class-time fl">'+data[i].beginTime + '</p>'+
                            '<p class="class-num fl">' + data[i].capacity + '</p>'+
                            '<p class="class-coach fl">'+ data[i].masterName + '</p>'+
                            '<p class="class-address fl">'+boxName+'</p>'+
                            '<p class="class-handle fl">' +
                                '<select class="training-gym courseOperationSel" style="height: 31px;width: 66px;">'+
                                    '<option value="1" '+ (data[i].operation == '1' ? 'selected':'') +'>开始</option>' +
                                    '<option value="2" '+ (data[i].operation == '2' ? 'selected':'') +'>暂停</option>'+
                                '</select>'+
                            '</p>'+
                            '</li>';
                    $("#classListUl").html(classListHtml);

                    $(".courseOperationSel").on('change',function(){
                        var operation = $(this).val();
                        var id = $(this).parents("li.class_list_li").children("input.liCourseId").val();
                        $.ajax({
                            type: 'get',
                            dataType: 'json',
                            jsonp: 'callback',
                            url: ctx + '/classSchedule/operationCourse',
                            data: {operation: operation, id: id},
                            success: function (data) {

                            },
                            error: function () {

                            }
                        })
                    });
                }
            }
        },
        error: function () {

        }
    })
    getCourseColor();
}


function getCourseColor(){
    var boxId = $("#boxId").val();
    var courseType = $("#courseType").val();
    var coach = $("#coach").val();
    $.ajax({
        type: 'get',
        dataType: 'json',
        jsonp: 'callback',
        url: ctx + '/classSchedule/queryCourseColorOfWeek',
        data: {boxId: boxId, courseType: courseType, coach: coach},
        success: function (data) {
            if (null != data && undefined != data) {
                var res = data;
               var colorHtml = ""
                for (var i = 0; i < res.length; i++) {
                   colorHtml += '<li><span class="fit" style="background: '+res[i].color+'"></span>' +res[i].crouseTypeName+'</li>';
                }
                $("ul.courseColor").html(colorHtml);
            }
        },
        error: function () {

        }
    })
}