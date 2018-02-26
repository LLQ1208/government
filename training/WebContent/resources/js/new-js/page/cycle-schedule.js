/**
 * Created by anran on 2017/12/3.
 */
var ctx = $("#ctx").val();
$(function () {
    changeWin();
    var weekOrDay = $("#weekOrDay").val();
    if(weekOrDay == "2"){
        $('.day-box').hide();
        $('.week-box').show();
    }
    var x = 0; // 区分拖动块
    var dragLeft = 0;
    var dragTop = 0;
    $('.filters,.filters-s,#start-hours,#start-minute,#end-hours,#end-minute').selectpicker();

    $('.schedule-classify li').on('click', function () {
        $(this).addClass('active').siblings('li').removeClass('active');
    });
    $('.top-middle-block li').on('click', function () {
        if ($(this).index() == 0) {
            $('.day-box').show();
            $('.week-box').hide();
            $("#weekOrDay").val(1);
        } else {
            $('.day-box').hide();
            $('.week-box').show();
            $("#weekOrDay").val(2);
        }
        // $(this).addClass('current').siblings('li').removeClass('current');
    });

    changeDay();

    // 初始化日历
    $('.calendar-box').calendar({
        ele: '.demo-box', //依附
        title: ''
    });

    var sheetHtml = '';
    var daySheet = '';
    for (var i = 0; i < 19 * 4; i++) {
        sheetHtml += '<li></li>';
        daySheet += '<li></li>'
    }
    $('.sheet ul').html(sheetHtml);
    $('.day-sheet ul').html(daySheet);

    // week出弹框
    $('.sheet ul li').on('dblclick', function () {
        //将选择的值默认给弹出框
        var courseTypeId = $("#courseType").val();
        var coachId= $("#coach").val();
        $("#editCourseType").selectpicker('val',courseTypeId);
        $("#editCoach").selectpicker('val',coachId);
        // 点击的是哪一天
        var n = $(this).parents('ul').index();
        $("#hideWeek").val(Number(n) + 1);
        var clickDate = $('.ht-rili-td:eq(' + n + ')').attr('data-date');
        console.log(clickDate);
        dragLeft = n;
        // 点击的是几点
        var m = $(this).index();
        console.log(Math.floor(m / 4));
        $('.time-line').html(Math.floor(m / 4) + 5 + ':' + toDou((m % 4) * 15) + '-' + (Math.floor(m / 4) + 6) + ':' + toDou((m % 4) * 15));
        $('#start-hours').selectpicker('val',(Math.floor(m / 4) + 5));
        $('#start-minute').selectpicker('val',m % 4);
        $('#end-hours').selectpicker('val',(Math.floor(m / 4) + 6));
        $('#end-minute').selectpicker('val',m%4);
        $("#hideCourseId").val("");
        $("a.deleted").hide();
        $('.add-tip').fadeIn();

    });

    // day出弹框
    $('.day-sheet ul li').on('dblclick', function () {
        // 点击的是哪一天
        var n = $(this).parents('ul').index();
        var clickDate = $('.ht-rili-td:eq(' + n + ')').attr('data-date');
        console.log(clickDate);
        dragLeft = n;
        // 点击的是几点
        var m = $(this).index();
        console.log(Math.floor(m / 4));
        $('.time-line').html(Math.floor(m / 4) + 5 + ':' + toDou((m % 4) * 15) + '-' + (Math.floor(m / 4) + 6) + ':' + toDou((m % 4) * 15));

        $('.add-tip').fadeIn();

    });

    // 删除
    $('.add-mask,.deleted').on('click', function (e) {
        var courseId = $("#hideCourseId").val();
        $.ajax({
            type: 'get',
            dataType: 'json',
            jsonp: 'callback',
            url: ctx+'/classSchedule/deleteCourseById',
            data: {courseId:courseId},
            success: function (result) {

            },
            error: function () {

            }
        });
        $("#courseId"+courseId).parents("div.drag-block").remove();
        $('.add-tip').fadeOut();
    });
    // 消失弹框
    $('.add-mask,.cancel').on('click', function (e) {
        $('.add-tip').fadeOut();
    });
    // 确定弹框
    $('.sure').on('click', function () {
        var beginHour = $("#start-hours").find("option:selected").text();
        var beginMinute = $("#start-minute").find("option:selected").text();
        var endHour = $("#end-hours").find("option:selected").text();
        var endMinute = $("#end-minute").find("option:selected").text();
        var boxId = $("#boxId").val();
        var editCourseType = $("#editCourseType").val();
        var editCoach = $("#editCoach").val();
        var studentNum = $("#studentNum").val();
        var weekDay = $("#hideWeek").val();
        var courseId = $("#hideCourseId").val();
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
        var beginTime = beginHour.length < 2 ? "0"+beginHour+":"+beginMinute : beginHour+":"+beginMinute;
        var entTime = endHour.length < 2 ? "0"+endHour+":" + endMinute : endHour+":" + endMinute;
        $.ajax({
            type: 'get',
            dataType: 'json',
            jsonp: 'callback',
            url: ctx+'/classSchedule/addClassSchedule',
            data: {beginTime:beginTime,entTime:entTime,boxId:boxId,editCourseType:editCourseType,
                editCoach:editCoach,studentNum:studentNum,weekDay:weekDay,courseId:courseId},
            success: function (result) {
                var boxName = $("#boxId").find("option:selected").text();
                var coachName = $("#editCoach").find("option:selected").text();
                if(courseId != ""){
                    $("#courseId"+courseId).parents("div.drag-block").remove();
                }
                x = $('.drag-block').length;
                console.log($('#start-hours').val());
                console.log($('#start-minute').val());
                console.log($('#end-hours').val());
                console.log($('#end-minute').val());
                console.log($('.training-gym-s').val());
                console.log($('.schedule-type-s').val());
                console.log($('.coach-s').val());
                dragTop = Number($('#start-hours').val() - 5) * 4 + Number($('#start-minute').val());
                var dragHeight = (Number($('#end-hours').val() - 5) * 4 + Number($('#end-minute').val()) - dragTop) * 25;

                console.log(dragLeft + '--' + dragTop + '--' + dragHeight);
                if ($('.training-gym-s').val() == '' || $('.schedule-type-s').val() == '' || $('.coach-s').val() == '' || $.trim($('.number').val()) == '') {
                    return;
                }
                var dragHtml = '<div class="drag-block drag' + x + '" style="height:' + dragHeight + 'px;top:' + dragTop * 25 + 'px;left:' + dragLeft * 136 + 'px">' +
                    '<p>'+beginTime+' '+boxName+'</p>' +
                    '<p>'+coachName+'</p>' +
                    '<span>0/'+studentNum+'</span>' +
                    '<div class="delete-box">删除</div>' +
                    '<input type="hidden" class="courseId" id="courseId'+result+'" value="'+result+'">'+
                    '</div>';
                if ($('.day-box').css('display') == 'block') {
                    $('.day-sheet').append(dragHtml);
                } else {
                    $('.sheet').append(dragHtml);
                }

                dragable($('.drag' + x + ''));
                $('.add-tip').fadeOut();
            },
            error: function () {

            }
        });

    });


    dragable($('.drag-block'));

    /*
     * 渲染数据
     */
    getWeek();

    //下拉框改变触发查询
    $(".filters").on("change",function(){
        var boxId = $("#boxId").val();
        var courseType = $("#courseType").val();
        var coach = $("#coach").val();
        var weekOrDay = $(".day-box").is(":hidden");
        var weekShow = weekOrDay ? 2:1;
        window.location.href = ctx+"/classSchedule/classSchedule?boxId="+boxId
        +"&courseType="+courseType+"&coach="+coach+"&weekOrDay="+weekOrDay+"&weekShow="+weekShow;

    });

});

function toDou(n) {
    return n > 9 ? n : '0' + n;
}

// 拖拽
function dragable($box) {
    $box.on('mousedown', function (e) {
        var courseId = $(this).find("input.courseId").val();
        var _this = $(this);
        console.log(_this.outerHeight(true));
        var disX = e.pageX - _this.position().left;
        var disY = e.pageY - _this.position().top;
        var moveX;
        var moveY;

        function fnMove(e) {
            moveX = e.pageX - disX;
            moveY = e.pageY - disY;
            if (moveX < 0) {
                moveX = 0;
            } else if (moveX > (952 - 136)) {
                moveX = 952 - 136;
            }
            if (moveY < 0) {
                moveY = 0;
            } else if (moveY > 19 * 100 - _this.outerHeight(true)) {
                moveY = 19 * 100 - _this.outerHeight(true)
}
            _this.css({'left': moveX + 'px', 'top': moveY + 'px'})
        }

        function fnEnd() {
            console.log(Math.floor(moveX / 136));
            // 移动后的开始时间
            console.log(Math.floor(moveY / 100) + 5);
            if (!moveX && !moveY) {
                //if (_this.hasClass('cur')) {
                //    _this.removeClass('cur');
                //    _this.children('.delete-box').fadeOut();
                //} else {
                //    _this.addClass('cur').siblings('.drag-block').removeClass('cur');
                //    _this.children('.delete-box').fadeIn();
                //    _this.siblings('.drag-block').children('.delete-box').fadeOut();
                //}
            }

            // 移动后的时间
            if(moveX||moveY){
                var h = _this.outerHeight(true)/25;
                console.log(getMovedTime(moveX, moveY,h))
                var changeTime = getMovedTime(moveX, moveY,h).startTime.substring(0,4);
                if(changeTime != null && changeTime != "" & changeTime != undefined){
                    $.ajax({
                        type: 'get',
                        dataType: 'json',
                        jsonp: 'callback',
                        url: ctx+'/classSchedule/changeCourseTime',
                        data: {courseId:courseId,changeTime:changeTime},
                        success: function (result) {
                            $("#courseId"+courseId).parents("div.drag-block").find("p").eq(2).html();
                            var a = "1";
                        },
                        error: function () {

                        }
                    });
                }
            }
            _this.css({'left': Math.floor(moveX / 136) * 136 + 'px', 'top': Math.floor(moveY / 25) * 25 + 'px'})
            $(document).off('mousemove', fnMove);
            $(document).off('mouseup', fnEnd);
        }

        $(document).on('mousemove', fnMove);
        $(document).on('mouseup', fnEnd);
        return false;
    })

    $box.on('dblclick', function (e) {
        var courseId = $(this).find("input.courseId").val();
        $.ajax({
            type: 'get',
            dataType: 'json',
            jsonp: 'callback',
            url: ctx+'/classSchedule/queryCourseById',
            data: {courseId:courseId},
            success: function (result) {
                console.log(result);
                var course = result;
                $("#start-hours").selectpicker('val',course.beginHour);
                $("#start-minute").selectpicker('val',course.beginMinus);
                $("#end-hours").selectpicker('val',course.endHour);
                $("#end-minute").selectpicker('val',course.endMinus);
                $("#editCourseType").selectpicker('val',course.courseTypeId);
                $("#editCoach").selectpicker('val',course.coachId);
                $("#studentNum").val(course.peopleNum);
                $("#hideWeek").val(course.week);
                $("a.deleted").show();
                $("#hideCourseId").val(course.courseId);
                $('.add-tip').fadeIn();
            },
            error: function () {

            }
        })
    });
}

// 按天切换
function changeDay() {
    var calendarDay = {};
    calendarDay.today = new Date();
    calendarDay.curyear = calendarDay.today.getFullYear();//当前年
    calendarDay.year = calendarDay.today.getFullYear();//当前年
    calendarDay.curmonth = calendarDay.today.getMonth() + 1;//当前月
    calendarDay.month = calendarDay.today.getMonth() + 1;//当前月
    calendarDay.date = calendarDay.today.getDate();//当前日
    calendarDay.curDay = days(calendarDay.year, calendarDay.month, calendarDay.date, 0);

    // 时间戳
    function days(year, month, date, i) {
        // console.log(year,month,date);
        var iDate = new Date();
        var oDate = new Date().setFullYear(year, month - 1, date);
        // console.log(oDate)
        iDate.setTime((oDate + i * 3600 * 24 * 1000));
        return iDate.getFullYear() + '-' + (iDate.getMonth() + 1) + '-' + iDate.getDate();
    }

    getToday();
    function getToday() {
        $('.day-date').html('<strong>' + toDou(calendarDay.curDay.split('-')[1]) + '月</strong><span>' + toDou(calendarDay.curDay.split('-')[2]) + '</span>');
    }

    $('.ht-day-leftarr').on('click', function () {
        calendarDay.curDay = days(calendarDay.curDay.split('-')[0], calendarDay.curDay.split('-')[1], calendarDay.curDay.split('-')[2], -1);
        getToday();
    });
    $('.ht-day-rightarr').on('click', function () {
        calendarDay.curDay = days(calendarDay.curDay.split('-')[0], calendarDay.curDay.split('-')[1], calendarDay.curDay.split('-')[2], 1);
        getToday();
    })

    $('.back-today-day').on('click', function () {
        $('.day-date').html('<strong>' + toDou(calendarDay.curmonth) + '月</strong><span>' + toDou(calendarDay.date) + '</span>');

        calendarDay.curDay = days(calendarDay.curyear, calendarDay.curmonth, calendarDay.date, 0);
    })

}

/*
 * 获取week数据
 */
function getWeek() {
    var boxId = $("#boxId").val();
    var courseType = $("#courseType").val();
    var coach = $("#coach").val();
    var weekOrDay = $(".day-box").is(":hidden");
    var weekShow = weekOrDay ? 2:1;
    $.ajax({
        type: 'get',
        dataType: 'json',
        jsonp: 'callback',
        url: ctx+'/classSchedule/queryClassScheduleList',
        data: {boxId:boxId,courseType:courseType,coach:coach,weekShow:weekShow},
        success: function (result) {
            console.log(result);
            var res = result;
            for (var name in res) {
                var curWeek = $('#'+name.toLowerCase()+'').index();
                if(res[name].length !=0){
                    for (var i = 0; i < res[name].length; i++) {
                        var dragHtml = '<div class="drag-block '+ name + i + '" style="height:' + getPosition(res[name][i].beginTime, res[name][i].endTime, curWeek).height + 'px;top:' + getPosition(res[name][i].beginTime, res[name][i].endTime, curWeek).top * 25 + 'px;left:' + getPosition(res[name][i].beginTime, res[name][i].endTime, curWeek).left + 'px">' +
                            '<p>' + res[name][i].beginTime + ' ' + res[name][i].crouseTypeName + '</p>' +
                            '<p>' + res[name][i].masterName + '</p>' +
                            '<span>' + res[name][i].capacity + '</span>' +
                            '<div class="delete-box" conlick="deleteCourse(this)">删除</div>' +
                            '<input type="hidden" class="courseId" id="courseId'+res[name][i].courseId+'" value="'+res[name][i].courseId+'">'+
                            '</div>';

                        $('.sheet').append(dragHtml);
                        dragable($('.'+name + i + ''));
                    }
                }
            }
        },
        error: function () {

        }
    })
}

// 根据位置获取时间
function getMovedTime(left, top, h) {
    console.log(left,top,h);
    var t = Math.floor(top/25);
    var l = left/136;
    var movedDate = $('.ht-rili-td:eq(' + l + ')').attr('data-date');
    var movedTime = Math.floor(t / 4) + 5 + ':' + toDou((t % 4) * 15) + '-' + (Math.floor(t / 4) + 5 +Math.floor(h / 4)) + ':' + toDou(((t+h) % 4) * 15);
    console.log(movedDate);
    console.log(movedTime);
    return {startTime: movedTime,movedDate:movedDate}
}

// 根据时间获取位置
function getPosition(startTime, endTime, week) {

    var startHours = startTime.split(':')[0];
    var startMinute = startTime.split(':')[1];
    var endHours = endTime.split(':')[0];
    var endMinute = endTime.split(':')[1];
    var top = Number(startHours - 5) * 4 + Number(startMinute/15);
    var height = (Number(endHours - 5) * 4 + Number(endMinute/15) - top) * 25;

    var left = week*136;

    return {top: top,left: left, height: height}
}


//删除
function deleteCourse(obj){
    $(obj).parents(".drag-block").remove();
}
function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}
