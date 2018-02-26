/**
 * Created by anran on 2017/11/27.
 */
$(document).ready(function () {
    changeWin();
    // $('.mask-box', window.parent.document).hide();
    $("#remarkDiv").hide();
    $('.selectpicker').selectpicker();
    $(".hours").val($("#time").val());
    $(".minus").val($("#munitus").val());
    $('.hours').selectpicker();
    $('.minus').selectpicker();

    // 初始化checkbox
    $('.select-all').iCheck({
        checkboxClass: 'icheckbox_flat-red'
    });
    $('.select-one').iCheck({
        checkboxClass: 'icheckbox_flat-red'
    });



    // 全选
    $('.select-all').on('ifClicked', function () {
        // console.log($(this).prop('checked'));
        if (!$(this).prop('checked') == true) {
            $('.select-one').iCheck('check');
        } else {
            $('.select-one').iCheck('uncheck');
        }
    });
    $('.select-all').iCheck('check');
    $('.select-one').iCheck('check');
    // 分个选
    $('.select-one').on('ifChanged', function () {
        // console.log($(this).parents('li').index());
        var n = 0;
        for (var i = 0; i < $('.list-box li').length; i++) {
            if($('.list-box li').eq(i).find('.select-one').prop('checked') == true){
                n++;
            }
        }
        if(n == $('.list-box li').length){
            $('.select-all').iCheck('check');
        }else{
            $('.select-all').iCheck('uncheck');
        }
    });

    // 添加备注
    $('.add-remark').on('click',function () {
        $("#remarkDiv").show();
    });
    $('#closeSpan').on('click',function () {
        $("#remarkDiv").hide();
    });
    $('.mask-box .close-btn', window.parent.document).on('click',function () {
        $('.mask-box', window.parent.document).fadeOut();
    });

    // 点击取消
    $('.cancel-btn').on('click',function () {
        var ctx = $("#ctx").val();
        //$('#iframe',window.parent.document).attr('src','pages/calendar.html');
        window.location.href = ctx+"/wod/queryWodList";
    });
    // 点击保存
    $('.save-btn').on('click',function () {
        var ctx = $("#ctx").val();
        var wodName = $("#wodName").val().trim();
        var boxsIds = "";
        var courseTypeId  = $("#courseTypeId").val();
        var remark = $("#remarkText").val();
        var day = $(".time-line").text();
        var hours = $(".hours").val();
        var minus = $(".minus").val();


        if(wodName ==""){
            alert("请填写训练计划名称");
            return false;
        }
        $.each($('input:checkbox'),function(){
            if(this.checked){
                boxsIds+= $(this).val()+",";
            }
        });
        if(boxsIds == ""){
            alert("没有选择训练馆");
            return false;
        }
        console.log(boxsIds);
        $.ajax({
            url:ctx + "/wod/saveTraining",
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:"boxsIds=" + boxsIds + "&wodName=" + wodName+"&courseTypeId="+courseTypeId+"&hours="+hours+"&remark="+remark
            +'&minus='+minus+"&day="+day,
            dataType:'text',
            success:function(data){
               console.log("成功");
              window.location.href = ctx+'/wod/toEditWodPage?wodId='+data+"&date="+day;
            },
            error:function(){
                alert("保存失败");
            }
        });

    });
});
function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}