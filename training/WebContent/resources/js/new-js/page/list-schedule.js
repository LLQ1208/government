/**
 * Created by anran on 2017/12/12.
 */
$(function () {
    $('.filters,.filters-s,#start-hours,#start-minute,#end-hours,#end-minute').selectpicker();
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
        console.log(e.target.value)
    });

    // 点击添加按钮
    $('.add-btn').on('click', function () {
        $('.add-tip').fadeIn();
    });
    var startH = toDou(oDate.getHours());
    var endH = (oDate.getHours()+1)>24?1:(oDate.getHours()+1)

    $('#start-hours').selectpicker('val',startH);
    $('#end-hours').selectpicker('val',toDou(endH));

    // 取消添加
    $('.cancel').on('click', function () {
        $('.add-tip').fadeOut();
    })
});

function toDou(n) {
    return n > 9 ? n : '0' + n;
}