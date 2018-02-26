/**
 * Created with webstorm.
 * Author: dameng
 * Date: 2017/11/26
 * Time: 17:03
 *
 */
$(function () {
    changeWin();
    $("#appHour").val(2);
    $.each($('.editorBtn'),function(){
        var obj = $(this);
        $(this).on('click',function () {
            var ctx = $("#ctx").val();
            $.ajax({
                type: 'get',
                dataType: 'json',
                jsonp: 'callback',
                url: ctx+'/wod/queryWodSetting',
                data: {},
                success: function (result) {
                    console.log(result);
                    if(null != result){
                        if(null != result.appShowDay && undefined != result.appShowDay){
                            var day = result.appShowDay;
                            var hour = result.appShowHour;
                            var minus = result.appShowMinus;
                            $("#appDay").val(day);
                            $("#appHour").val(hour);
                            $("#appMinus").val(minus);
                        }
                        if(null !=  result.wodShowWeek && undefined != result.wodShowWeek){
                            var week = result.wodShowWeek;
                            $("#wodWeek").val(week);
                        }
                    }
                    obj.hide();
                    obj.siblings('.backEditor').show();
                    obj.siblings('.choose').show();
                    obj.siblings('.showChoose').hide();
                },
                error: function () {

                }
            });

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
    $.each($('.appTimeConfirm'),function(){
        $(this).on('click',function () {
            var day = $("#appDay").val();
            var hour = $("#appHour").val();
            var minus = $("#appMinus").val();
            var minusShow = $("#appMinus").find("option:selected").text();
            var ctx = $("#ctx").val();
            $.ajax({
                type: 'get',
                dataType: 'json',
                jsonp: 'callback',
                url: ctx+'/wod/saveWodSetting',
                data: {day:day,hour:hour,minus:minus},
                success: function (result) {
                    console.log(result);
                },
                error: function () {

                }
            });
            var appTime = day+"天前"+
            (hour<10 ? "0"+hour: hour) + "点"+
                minusShow;
            $("p.showAppTime").html(appTime);
            $(this).parents('.editor').children('.backEditor').hide();
            $(this).parents('.editor').children('.choose').hide();
            $(this).parents('.editor').children('.showChoose').show();
            $(this).parents('.editor').children('.editorBtn').show();
        })
    });

    $.each($('.weekConfirm'),function(){
        $(this).on('click',function () {
            var week = $("#wodWeek").val();
            var weekTxt = $("#wodWeek").find("option:selected").text();
            var ctx = $("#ctx").val();
            $.ajax({
                type: 'get',
                dataType: 'json',
                jsonp: 'callback',
                url: ctx+'/wod/saveWodSetting',
                data: {week:week},
                success: function (result) {
                    console.log(result);
                },
                error: function () {

                }
            });
            $("span.showWodWeek").html(weekTxt);
            $(this).parents('.editor').children('.backEditor').hide();
            $(this).parents('.editor').children('.choose').hide();
            $(this).parents('.editor').children('.showChoose').show();
            $(this).parents('.editor').children('.editorBtn').show();
        })
    });
})
function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}