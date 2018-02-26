/**
 * Created by anran on 2017/12/7.
 */
var ctx = $("#ctx").val();
$(function () {
    changeWin();
    $(".form_datetime").datetimepicker({
        format: "yyyy年mm月dd日",
        minView: 2,
        autoclose: true,
        weekStart: 0,
        language:  'zh-CN',
        pickerPosition: "bottom-left",
        defaultDate : new Date()
    });

    // 点击跳页
    $('.coach-list').on('click','li',function () {
        var id = $(this).find("input.coachId").val();
        var type = $(this).find("input.coachType").val();
        window.location.href = ctx+'/classSchedule/privateEducationDetail?coachId='+id + "&type="+type;
    })
});
function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}