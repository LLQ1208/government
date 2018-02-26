
$(function () {
    changeWin();
    $('.editor').on('click',function(){
        $(this).siblings('.time').children('.number').attr('readonly',false).focus();
    });
    $(".reservationMinusConfirm").on('click',function () {
        var classReservationMinus = $("#classReservationMinus").val();
        var ctx = $("#ctx").val();
        if(classReservationMinus == ""){
            alert("请输入时间");
            return false;
        }
        $.ajax({
            type: 'get',
            dataType: 'json',
            jsonp: 'callback',
            url: ctx+'/classSchedule/saveSetting',
            data: {classReservationMinus:classReservationMinus},
            success: function (result) {

            },
            error: function () {

            }
        });
        $("#hideReserMinux").val(classReservationMinus);
        $("p.showReservationMinus").html(classReservationMinus + "分钟");
        $(this).parents('.editor').children('.backEditor').hide();
        $(this).parents('.editor').children('.choose').hide();
        $(this).parents('.editor').children('.showChoose').show();
        $(this).parents('.editor').children('.editorBtn').show();
    })



    $(".dropInMinusConfirm").on('click',function () {
        var dropInMinus = $("#dropInMinus").val();
        var ctx = $("#ctx").val();
        if(dropInMinus == ""){
            alert("请输入时间");
            return false;
        }
        $.ajax({
            type: 'get',
            dataType: 'json',
            jsonp: 'callback',
            url: ctx+'/classSchedule/saveSetting',
            data: {dropInMinus:dropInMinus},
            success: function (result) {

            },
            error: function () {

            }
        });
        $("#hideDropIn").val(dropInMinus);
        $("span.showDropInMinus").html(dropInMinus);
        $(this).parents('.editor').children('.backEditor').hide();
        $(this).parents('.editor').children('.choose').hide();
        $(this).parents('.editor').children('.showChoose').show();
        $(this).parents('.editor').children('.editorBtn').show();
    })



    $(".reservationDayConfirm").on('click',function () {
        var reservationDay = $("#reservationDay").val();
        var ctx = $("#ctx").val();
        if(reservationDay == ""){
            alert("请输入时间");
            return false;
        }
        $.ajax({
            type: 'get',
            dataType: 'json',
            jsonp: 'callback',
            url: ctx+'/classSchedule/saveSetting',
            data: {reservationDay:reservationDay},
            success: function (result) {
                console.log(result);

            },
            error: function () {

            }
        });
        $("#hidereservationDay").val(reservationDay);
        $("span.showReservationDay").html(reservationDay);
        $(this).parents('.editor').children('.backEditor').hide();
        $(this).parents('.editor').children('.choose').hide();
        $(this).parents('.editor').children('.showChoose').show();
        $(this).parents('.editor').children('.editorBtn').show();
    })
    $.each($('.editorBtn'),function(){
        var obj = $(this);
        $(this).on('click',function () {
            var ctx = $("#ctx").val();
            var type = $(this).attr("type");
                if(type == '1'){
                    var classReservationMinus = $("#hideReserMinux").val();
                    if(null != classReservationMinus && undefined != classReservationMinus){
                        $("#classReservationMinus").val(classReservationMinus);
                    }
                }else if(type == '2'){
                    var hideDropIn = $("#hideDropIn").val();
                    if(null != hideDropIn && undefined != hideDropIn){
                        $("#dropInMinus").val(hideDropIn);
                    }
                }else if(type == '3'){
                    var hidereservationDay = $("#hidereservationDay").val();
                    if(null != hidereservationDay && undefined != hidereservationDay){
                        $("#reservationDay").val(hidereservationDay);
                    }
                }

            obj.hide();
            obj.siblings('.backEditor').show();
            obj.siblings('.choose').show();
            obj.siblings('.showChoose').hide();

        })
    });
    $.each($('.cancel'),function(){
        $(this).on('click',function () {
            $(this).parents('.editor').children('.backEditor').hide();
            $(this).parents('.editor').children('.choose').hide();
            $(this).parents('.editor').children('.showChoose').show();
            $(this).parents('.editor').children('.editorBtn').show();
        })
    });



});
function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}