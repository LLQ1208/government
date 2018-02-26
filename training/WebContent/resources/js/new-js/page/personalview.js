/**
 * Created by anran on 2018/1/13.
 */
$(function () {
    $('.male,.female').iCheck({
        checkboxClass: 'icheckbox_flat-red',
        radioClass: 'iradio_flat-red'
    });

    $('.form_datetime').datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        minView: 2,
        initialDate: new Date(),
        autoclose: true
    });

    $('.datetimepicker-dropdown-bottom-right').on('click', function () {
        var cardStartTime = $("#cardStartTime").val();
        $("#cardStartTime").siblings('span.showValue').html(cardStartTime);
        var birthday = $("#birthday").val();
        $("#birthday").siblings('span.showValue').html(birthday);
        $("#cardStartTime").siblings('span').show();
        $("#birthday").siblings('span').show();
        $("#cardStartTime").hide();
        $("#birthday").hide();
        updateBirthday(birthday);
    });

    $('.cardsopt').on('click', function () {
        $('.cardsopt').removeClass('active');
        $(this).addClass('active');
        $('.recodeopt').eq(0).addClass('active').siblings('.recodeopt').removeClass('active');
        $('.detail').eq(0).show();
        $('.detail').eq(1).hide();
    });
    $("#sex").selectpicker();

    $('.recodeopt').on('click', function () {
        $('.recodeopt').removeClass('active');
        $(this).addClass('active');
        if($(this).index() == 0){
            $('.detail').eq(0).show();
            $('.detail').eq(1).hide();
        }else{
            $('.detail').eq(1).show();
            $('.detail').eq(0).hide();
        }
    });
    $('.show1').hover(function () {
        $(this).children('.commondet').show()
    }, function () {
        $(this).children('.commondet').hide()
    });

    // 点击编辑头像
    $('.changpic').on('click', function () {
        $('.left input').show()
    });

    $('.rewrite').on('click', function () {
        $(this).siblings('.edit-info').show();
        $(this).hide();
        $(this).prev('span').hide();
        var id = $(this).siblings('.edit-info').attr("id");
        // var val = $(this).siblings('.edit-info').val();
        // $(this).siblings('.edit-info').val('').focus().val(val);
        if(id != 'sexInput'){
            var val = $(this).siblings('.edit-info').val();
            $(this).siblings('.edit-info').val('').focus().val(val);
        }else{
            var sexVal = $(".sex .showValue").html();
            if(sexVal == "男"){
                $(".male").iCheck("check");
            }else if(sexVal == "女"){
                $(".female").iCheck("check");
            }

            $('input:radio[name="sex"]:checked').focus();
            $("#sexInput").show();
        }
    });

    $('input:radio[name="sex"]').on("blur",function(){
        var sex = $('input:radio[name="sex"]:checked').val();
        var showValue = sex == "0" ? "男":"女";
        $(this).parents(".sex").find('span.showValue').html(showValue);
        $(this).parents(".sex").find('span').show();
        $("#sexInput").hide();
    });


    $('input.male[name="sex"]').on('ifChanged', function () {
        var sex = 0;
        if($(this).prop('checked')){
            sex =0;
        }else{
            sex = 1;
        }
        var userId = $("#userId").val();
        var showValue = sex == "0" ? "男":"女";
        $(this).parents(".sex").find('span.showValue').html(showValue);
        $(this).parents(".sex").find('span').show();
        $("#sexInput").hide();
        var ctx = $("#ctx").val();
        var json = {type:2,sex:sex,userId:userId};

        $.ajax({
            url: ctx + "/personalCenter/editInfo",
            type: 'POST', //GET
            async: false,    //或false,是否异步
            data: json,
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {

            },
            error: function () {
                console.log("错误")
            }
        });
    });


    $('.edit-info').on('blur', function () {
        var id = $(this).attr("id");
        var userId = $("#userId").val();
        var ctx = $("#ctx").val();
        if(id != 'cardStartTime' && id != 'birthday'){
            var value = $(this).val().trim();
            //校验组装参数
            if(value == "" ){
                $(this).focus();
                return false;
            }
            var json = {};
            if(id == "userName"){
                json = {type:1,userName:value,userId:userId};
            }else if(id == "sex"){
                // if(value != "男" && value!="女" ){
                //     $(this).focus();
                //     return false;
                // }
                // json = {type:2,sex:value,userId:userId};
            }else if(id == "birthday"){
                value = $("#birthday").val();
                json = {type:3,birthday:value,userId:userId};
            }else if(id == "email"){
                if(!isEmail(value)){
                    $(this).focus();
                    return false;
                }
                json = {type:4,email:value,userId:userId};
            }else if(id == "phone"){
                if(!isPhoneNo(value)){
                    $(this).focus();
                    return false;
                }
                json = {type:5,phone:value,userId:userId};
            }
            $(this).siblings('span.showValue').html(value);
            $(this).siblings('span').show();
            $(this).hide();

            $.ajax({
                url: ctx + "/personalCenter/editInfo",
                type: 'POST', //GET
                async: false,    //或false,是否异步
                data: json,
                dataType: 'json',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {

                },
                error: function () {
                    console.log("错误")
                }
            });
        }
    });

    // 续卡
    $('.renewal-btn').on('click', function () {
        $('#iframe',window.parent.document).attr('src','pages/member-add.html');
    });
});

function isPhoneNo(phone) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
}
//校验邮箱
function isEmail(email){
    return email.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
}

function updateBirthday(value){
    var ctx = $("#ctx").val();
    var userId = $("#userId").val();
    var json = {type:3,birthday:value,userId:userId};
    $.ajax({
        url: ctx + "/personalCenter/editInfo",
        type: 'POST', //GET
        async: false,    //或false,是否异步
        data: json,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (data) {

        },
        error: function () {
            console.log("错误")
        }
    });
}


