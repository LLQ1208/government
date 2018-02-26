
/**
 * Created with webstorm.
 * Author: dameng
 * Date: 2017/11/22
 * Time: 21:42
 *
 */

//var phoneReg = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
var phoneReg = /^1[34578]\d{9}$/;
$(function () {
    $('.agreePopusClose').on('click',function(){
        $('.agreePopusBg').fadeOut()
        countdown = 59;
    })
    //$('.agreePopus1').on('change',function(){
    //    if ($('.agreePopus1').prop('checked')){
    //        $('.agreePopusBg').fadeIn()
    //    }
    //})
    $("#userName").focus(function(){
        $("#userNameNotice").show();
    });
    $("#passWord").focus(function(){
        $("#passWordNotice").show();
    });
    $("#userName").blur(function(){
        var userNameVal = $("#userName").val();
        if(null != userNameVal && "" != userNameVal.trim()){
            if(userNameVal.trim().length < 5 || userNameVal.length > 20){
                //判断是否已经提示
                var color = $("#userNameNotice").hasClass("redColor");
                if(!color){
                    $("#userNameNotice").addClass("redColor");
                    $("#userName").focus();
                }
            }else{
                var color = $("#userNameNotice").hasClass("redColor");
                if(color){
                    $("#userNameNotice").removeClass("redColor");
                }
            }
        }
    });
    $("#passWord").blur(function(){
        var passWordVal = $("#passWord").val();
        if(null != passWordVal && "" != passWordVal.trim()){
            var passArr = passWordVal.trim().split(" ");
            if(passArr.length > 1 || passWordVal.trim().length < 6 || passWordVal.trim().length > 20){
                //判断是否已经提示
                var color = $("#passWordNotice").hasClass("redColor");
                if(!color){
                    $("#passWordNotice").addClass("redColor");
                    $("#passWord").focus();
                }
            }else{
                var color = $("#passWordNotice").hasClass("redColor");
                if(color){
                    $("#passWordNotice").removeClass("redColor");
                }
            }
        }
    });
    $("#rePassWord").blur(function(){
        var rePassWordVal = $("#rePassWord").val().trim();
        var passWordVal = $("#passWord").val().trim();
        if(null != rePassWordVal && "" != rePassWordVal){
            if(rePassWordVal != passWordVal){
                if($("#rePassWordNotice").is(':hidden')){
                    $("#rePassWordNotice").show();
                }
                //判断是否已经提示
                var color = $("#rePassWordNotice").hasClass("redColor");
                if(!color){
                    $("#rePassWordNotice").addClass("redColor");
                    $("#rePassWord").focus();
                }
            }else{
                $("#rePassWordNotice").removeClass("redColor");
                $("#rePassWordNotice").hide();
            }
        }
    });
    $("#phone").blur(function(){
        var phoneVal = $("#phone").val();
        if(null != phoneVal && "" != phoneVal){
            var reg = new RegExp(phoneReg);
            if(!reg.test(phoneVal.trim())){
                if($("#phoneNotice").is(':hidden')){
                    $("#phoneNotice").show();
                }
                //判断是否已经提示
                var color = $("#phoneNotice").hasClass("redColor");
                if(!color){
                    $("#phoneNotice").addClass("redColor");
                    $("#phone").focus();
                }
            }else{
                $("#phoneNotice").removeClass("redColor");
                $("#phoneNotice").hide();
            }
        }
    });
});

function agreeToNext(){
    var ctx = $("#ctx").val().trim();
    var userName = $("#userName").val().trim();
    var passWord = $("#passWord").val().trim();
    var rePassWord = $("#rePassWord").val().trim();
    var phone = $("#phone").val().trim();
    var agreeService = $('#agreeService').is(':checked');
    if(userName.trim().length < 5 || userName.length > 20){
        return false;
    }
    var passArr = passWord.trim().split(" ");
    if(passArr.length > 1 || passWord.trim().length < 6 || passWord.trim().length > 20){
        return false;
    }
    if(passWord != rePassWord){
        return false;
    }
    var reg = new RegExp(phoneReg);
    if(!reg.test(phone.trim())){
        return false;
    }
    if(!agreeService){
        return false;
    }
    $.ajax({
        url:ctx + "/queryPhone",
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:"phone=" + phone + "&userName=" + userName,
        dataType:'text',
        success:function(data){
             if(data == "success"){
                 $.ajax({
                     url:ctx + "/toPhoneCode",
                     type:'GET', //GET
                     async:true,    //或false,是否异步
                     data:"phone=" + phone+"&mode=register",
                     dataType:'text',
                     beforeSend:function () {
                         $("span.agreePopusPhone").html(phone);
                         $("#smscode").val("");
                         $('.agreePopusBg').fadeIn(500);
                         settime();
                     },
                     success:function(data){
                         $("span.agreePopusPhone").html(phone);
                         $('.agreePopusBg').fadeIn(500);
                         settime();
                     },
                     error:function(){
                         alert("错误");
                         return false;
                     }
                 });
             }else if(data == "exist phone"){
                 alert("该手机号已经被注册!");
                 return false;
             }else if(data == "exist user"){
                 alert("用户名已被注册!");
                 return false;
             }
        },
        error:function(){
            alert("错误");
            return false;
        }
    });

}
var setCc;
var countdown=59;
function settime() {
    if (countdown == 0) {
        $('#sendSms').attr("disabled",false);
        $('#sendSms').css("background","#fffbfb")
        $('#sendSms').css("color","black")
        $("#sendSms").val("免费获取验证码");
        countdown = 59;
        return;
    } else {
        $('#sendSms').attr("disabled",true);
        $('#sendSms').css("background","rgb(174,174,174)")
        $('#sendSms').css("color","black")
        $("#sendSms").val("重新发送(" + countdown + ")");
        countdown--;
    }
    setCc = setTimeout(function() {
            settime() }
        ,1000)
}

function toSendSMS(){
    var ctx = $("#ctx").val().trim();
    var phone = $("#phone").val().trim();
    $.ajax({
            url:ctx + "/toPhoneCode",
            type:'GET', //GET
            async:true,    //或false,是否异步
            data:"phone=" + phone+"&mode=register",
            dataType:'text',
            beforeSend: function(){
                settime();
            },

            error:function(){
                alert("错误");
            }
        });
}

function toRegister(){
    var ctx = $("#ctx").val().trim();
    var userName = $("#userName").val().trim();
    var passWord = $("#passWord").val().trim();
    var rePassWord = $("#rePassWord").val().trim();
    var phone = $("#phone").val().trim();
    var code = $("#smscode").val().trim();
    $.ajax({
        url:ctx + "/newRegister",
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:"userName=" + userName + "&passWord=" + passWord
                + "&rePassWord=" + rePassWord + "&phone=" + phone + "&code=" + code,
        dataType:'text',
        success:function(data){
            //if(data == "existPhone"){
            //    alert("手机号已经被注册");
            //}else
            if(data == "existingUser"){
                alert("用户名已存在");
                clearTimeout(setCc);
                countdown = 59;
                $('#sendSms').attr("disabled",false);
                $('#sendSms').css("background","#fffbfb")
                $('#sendSms').css("color","black")
                $("#sendSms").val("免费获取验证码");
            }else if(data == "errorCode"){
                alert("验证码不正确");
                clearTimeout(setCc);
                countdown = 59;
                $('#sendSms').attr("disabled",false);
                $('#sendSms').css("background","#fffbfb")
                $('#sendSms').css("color","black")
                $("#sendSms").val("免费获取验证码");
            }else{
                window.location = ctx+"/toInformation";
            }
        },
        error:function(){
            alert("错误");
        }
    });
}

function toAgree(obj){
    if(!$(obj).is(':checked')){
        $(".openBtn").css({'background-color':'#a7a3a3'});
        $(".openBtn").attr("disabled",true);
    }else{
        $(".openBtn").css({'background-color':'#f54337'});
        $(".openBtn").attr("disabled",false);
    }
}