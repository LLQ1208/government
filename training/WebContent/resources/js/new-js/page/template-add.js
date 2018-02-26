/**
 * Created by anran on 2018/1/12.
 */
$(function () {
    changeWin();
    var ctx = $("#ctx").val();
    $('.time-limit,.count-limit,.gym-all,.gym-one,.class-all, .class-one,.email-one').iCheck({
        checkboxClass: 'icheckbox_flat-red',
        radioClass: 'iradio_flat-red'
    });

    $('#select-type').selectpicker();

    // 点击切换卡的种类
    $('.time-limit').on('ifChanged', function () {
        $("#name").css("border-color","");
        if($(this).prop('checked')){
            $('.time-block').show();
            $('.count-block').hide();
            $('.breadThird').html('添加时限卡模版');
        }else{
            $('.time-block').hide();
            $('.count-block').show();
            $('.breadThird').html('添加次卡模版');
        }
    });

    // 选择私教课
    $('#select-type').on('change', function (e) {
        if(e.target.value == '2'){
            $('.count-block .class-detail .select-box').hide();
        }else{
            $('.count-block .class-detail .select-box').show();
        }
    });

    // 训练馆
    // 全选
    $('.gym-all').on('ifClicked', function () {
        // console.log($(this).prop('checked'));
        if (!$(this).prop('checked') == true) {
            $('.gym-one').iCheck('check');
        } else {
            $('.gym-one').iCheck('uncheck');
        }
    });
    // 分个选
    $('.time-gym-one').on('ifChanged', function () {
        // console.log($(this).parents('li').index());
        var n = 0;
        for (var i = 0; i < $('.time-gym-one').length; i++) {
            if ($('.time-gym-one').eq(i).prop('checked') == true) {
                n++;
            }
        }
        if (n == $('.time-gym-one').length) {
            $('.time-gym-all').iCheck('check');
        } else {
            $('.time-gym-all').iCheck('uncheck');
        }
    });
    // 分个选
    $('.bout-gym-one').on('ifChanged', function () {
        // console.log($(this).parents('li').index());
        var n = 0;
        for (var i = 0; i < $('.bout-gym-one').length; i++) {
            if ($('.bout-gym-one').eq(i).prop('checked') == true) {
                n++;
            }
        }
        if (n == $('.bout-gym-one').length) {
            $('.bout-gym-all').iCheck('check');
        } else {
            $('.bout-gym-all').iCheck('uncheck');
        }
    });
    // 课程
    // 全选
    $('.class-all').on('ifClicked', function () {
        // console.log($(this).prop('checked'));
        if (!$(this).prop('checked') == true) {
            $('.class-one').iCheck('check');
        } else {
            $('.class-one').iCheck('uncheck');
        }
    });
    // 分个选
    $('.time-class-one').on('ifChanged', function () {
        // console.log($(this).parents('li').index());
        var n = 0;
        for (var i = 0; i < $('.time-class-one').length; i++) {
            if ($('.time-class-one').eq(i).prop('checked') == true) {
                n++;
            }
        }
        if (n == $('.time-class-one').length) {
            $('.time-class-all').iCheck('check');
        } else {
            $('.time-class-all').iCheck('uncheck');
        }
    });
    $('.bout-class-one').on('ifChanged', function () {
        // console.log($(this).parents('li').index());
        var n = 0;
        for (var i = 0; i < $('.bout-class-one').length; i++) {
            if ($('.bout-class-one').eq(i).prop('checked') == true) {
                n++;
            }
        }
        if (n == $('.bout-class-one').length) {
            $('.bout-class-all').iCheck('check');
        } else {
            $('.bout-class-all').iCheck('uncheck');
        }
    });

    //只能输入数字
    $(".number_input").keyup(function(){
        $(this).val($(this).val().replace(/[^0-9]/g,''));
        if($(this).val().trim() != ""){
            $(this).css("border-color","");
        }
    }).bind("paste",function(){
        $(this).val($(this).val().replace(/[^0-9]/g,''));
        if($(this).val().trim() != ""){
            $(this).css("border-color","");
        }
    })

    //保存
    $(".save-btn").on("click",function(){
        var templateName= $("#name").val().trim();
        if(templateName == ""){
            alert("请输入名称");
            $("#name").css("border-color","#e83a1b");
            return false;
        }
        var templateType = $("input[name='kind']:checked").val();
        if(templateType == '1'){
            var validity = $("#time_validity").val().trim();
            var money = $("#time_money").val().trim();
            var expireDay = $("#time_expireDay").val().trim();
            var boxIds = '';
            $.each($('input[name="time_box"]'),function(){
                if(this.checked){
                    boxIds+= $(this).val()+",";
                }
            });
            var courseTypeIds = '';
            $.each($('input[name="time_courseType"]'),function(){
                if(this.checked){
                    courseTypeIds+= $(this).val()+",";
                }
            });
            var emails = '';
            $.each($('input[name="time_email"]'),function(){
                if(this.checked){
                    emails+= $(this).val()+",";
                }
            });
            var remarks = $("#time_remark").val().trim();
            if(validity == ""){
                alert("请输入有效期");
                $("#time_validity").css("border-color","#e83a1b");
                return false;
            }
            if(money == ""){
                alert("请输入费用");
                $("#time_money").css("border-color","#e83a1b");
                return false;
            }
            if(expireDay == ""){
                alert("请输入到期提醒");
                $("#time_expireDay").css("border-color","#e83a1b");
                return false;
            }
            if(boxIds == ""){
                alert("请选择训练馆");
                return false;
            }else{
                boxIds = boxIds.substring(0,boxIds.length-1);
            }
            if(courseTypeIds == ""){
                alert("请选择课程");
                return false;
            }else{
                courseTypeIds = courseTypeIds.substring(0,courseTypeIds.length-1);
            }
            if(emails != ""){
                emails = emails.substring(0,emails.length-1);
            }
            if(confirm("是否保存模版页面？")){
                $.ajax({
                    url: ctx + "/memberNew/memberTemplateSave",
                    type: 'POST', //GET
                    async: true,    //或false,是否异步
                    data: {templateName:templateName,templateType:templateType,validity:validity,
                        money:money,expireDay:expireDay,boxIds:boxIds,courseTypeIds:courseTypeIds,
                        emails:emails,remarks:remarks},
                    dataType: 'json',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (data) {
                        if(data.code == "-1"){
                            alert("模版名称已存在");
                            return false;
                        }else{
                            window.location.href = ctx + '/memberNew/memberSetting';
                        }
                    },
                    error: function () {
                        console.log("添加会员错误")
                    }
                });
            }
        }else if(templateType == '2'){
            var boutCount = $("#bout_count").val();
            var validity = $("#bout_validity").val().trim();
            var money = $("#bout_money").val().trim();
            var expireDay = $("#bout_expireDay").val().trim();
            var boxIds = '';
            $.each($('input[name="bout_box"]'),function(){
                if(this.checked){
                    boxIds+= $(this).val()+",";
                }
            });
            var selectType = $("#select-type").val();
            var courseTypeIds = '';
            $.each($('input[name="bout_courseType"]'),function(){
                if(this.checked){
                    courseTypeIds+= $(this).val()+",";
                }
            });
            var emails = '';
            $.each($('input[name="bout_email"]'),function(){
                if(this.checked){
                    emails+= $(this).val()+",";
                }
            });
            var remarks = $("#bout_remark").val().trim();
            if(boutCount == ""){
                alert("请输入次数");
                $("#bout_count").css("border-color","#e83a1b");
                return false;
            }
            if(validity == ""){
                alert("请输入有效期");
                $("#bout_validity").css("border-color","#e83a1b");
                return false;
            }
            if(money == ""){
                alert("请输入费用");
                $("#bout_money").css("border-color","#e83a1b");
                return false;
            }
            if(expireDay == ""){
                alert("请输入到期提醒");
                $("#bout_expireDay").css("border-color","#e83a1b");
                return false;
            }
            if(boxIds == ""){
                alert("请选择训练馆");
                return false;
            }else{
                boxIds = boxIds.substring(0,boxIds.length-1);
            }
            if(selectType == "1"){
                if(courseTypeIds == ""){
                    alert("请选择课程");
                    return false;
                }else{
                    courseTypeIds = courseTypeIds.substring(0,courseTypeIds.length-1);
                }
            }
            if(emails != ""){
                emails = emails.substring(0,emails.length-1);
            }
            if(confirm("是否保存模版页面？")){
                $.ajax({
                    url: ctx + "/memberNew/memberTemplateSave",
                    type: 'POST', //GET
                    async: true,    //或false,是否异步
                    data: {templateName:templateName,templateType:templateType,boutCount:boutCount,validity:validity,
                        money:money,expireDay:expireDay,boxIds:boxIds,selectType:selectType,courseTypeIds:courseTypeIds,
                        emails:emails,remarks:remarks},
                    dataType: 'json',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (data) {
                        window.location.href = ctx + '/memberNew/memberSetting';
                    },
                    error: function () {
                        console.log("添加会员错误")
                    }
                });
            }
        }
    });

    $(".cancel-btn").on("click",function(){
        if(confirm("是否取消模版页面？")){
            var ctx = $("#ctx").val();
            window.location.href = ctx + "/memberNew/memberSetting";
        }
    });
});

function listenInput(obj){
    if($(obj).val().trim() != ""){
        $(obj).css("border-color","");
    }
}

function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}