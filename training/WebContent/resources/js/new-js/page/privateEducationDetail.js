/**
 * Created by anran on 2017/12/3.
 */
var ctx = $("#ctx").val();
$(function () {
    changeWin();
    var x = 10;
    $('#start-hours,#start-minute,#end-hours,#end-minute,#boxId').selectpicker();

    $('.schedule-classify li').on('click', function () {
        $(this).addClass('active').siblings('li').removeClass('active');
    });

    $('#boxId').on('change', function () {
        $('#calendar').fullCalendar( 'refetchEvents' );
    });
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
            var startTime = start.format("YYYY-MM-DD");
            var endTime = end.format("YYYY-MM-DD");
            var boxId = $("#boxId").val();
            $.ajax({
                type: 'get',
                dataType: 'json',
                jsonp: 'callback',
                url: ctx + '/classSchedule/queryCoachCourseList',
                data: {coachId: coachId,coachType:coachType,startTime: startTime, endTime: endTime,boxId:boxId},
                success: function (data) {
                    if (null != data && undefined != data) {
                        var res = data;
                        var events = [];
                        for (var i = 0; i < res.length; i++) {
                            events.push({
                                id: res[i].id,
                                title: res[i].capacity,
                                start: res[i].date + 'T' + res[i].startTime,
                                end: res[i].date + 'T' + res[i].endTime,
                                backgroundColor: '#E066FF',
                                num:res[i].peopleLimit
                            })
                        }
                        callback(events);
                    }
                },
                error: function () {

                }
            })
        },
        select: function (start, end, jsEvent) {				// 添加
            var startTime = start.format('YYYY-MM-DD HH:mm:ss');
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
            $('#studentNum').val(calEvent.num);
            $('#editCourseId').val(calEvent.id);
            $(".cancel").show();
            $(".delete").show();
            // change the border color just for fun
            $('.add-tip').addClass('edit-add-tip').fadeIn();
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



    //初始化保存事件
    $('.cancel').off('click',function(){
        $('.add-tip').fadeOut();
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
                            backgroundColor: '#E066FF',
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
            var eventData;

            //编辑
            var beginHour = $("#start-hours").find("option:selected").text();
            var beginMinute = $("#start-minute").find("option:selected").text();
            var endHour = $("#end-hours").find("option:selected").text();
            var endMinute = $("#end-minute").find("option:selected").text();
            var boxId = $("#boxId").val();

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
                    backgroundColor: '#E066FF',
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


    $('.delete ').on('click',function(){
        if(confirm("确定删除这节课？")){
            var editCourseId = $('#editCourseId').val();
            $('#calendar').fullCalendar('removeEvents', editCourseId)
            $('.add-tip').fadeOut();
            $.ajax({
                type: 'get',
                dataType: 'json',
                jsonp: 'callback',
                url: ctx+'/classSchedule/deleteCoachCourse',
                data: {editCourseId:editCourseId},
                success: function (result) {

                },
                error: function () {

                }
            });
        }
    });

    $('.cancel ').on('click',function(){
        $('.add-tip').fadeOut();
    });
});

function toDou(n) {
    return n > 9 ? n : '0' + n;
}
function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}

