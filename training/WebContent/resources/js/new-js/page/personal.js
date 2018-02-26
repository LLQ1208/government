/**
 * Created by anran on 2018/1/21.
 */
$(function () {
    $('.male,.female').iCheck({
        radioClass: 'iradio_flat-red'
    });

    $('.form-datetime').datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        minView: 2,
        initialDate: new Date(),
        autoclose: true
    });
    var oDate = new Date();
    $('.form-datetime').val(oDate.getFullYear() + '-' + toDou(oDate.getMonth() + 1) + '-' + toDou(oDate.getDate()));

});

function toDou(n) {
    return n > 9 ? n : '0' + n;
}