var setCc;
var countdown=59;
$(function () {
    var user = sessionStorage.getItem("userName").substr(0, 2) + '****' + sessionStorage.getItem("userName").substr(5);
    var phone = sessionStorage.getItem("phone").substr(0, 3) + '****' + sessionStorage.getItem("phone").substr(7);
    $("#userName").html(user);
    $("#phoneNumber").html(phone);
    //点击登录按钮
    $("#btn").click(function(){
        settime();
    });
});

function toSendSMS() {
    var phone = sessionStorage.getItem("phone");
    var ctx = $("#ctx").val();
    $.ajax({
        url:ctx + "/toPhoneCode",
        type:'GET', //GET
        async:true,    //或false,是否异步
        data:"phone=" + phone+"&mode=update",
        dataType:'text',
        beforeSend: function(){
            settime();
        },

        success:function(data){
            console.log("成功");
        },
        error:function(){
            alert("错误");
        }
    });
}
/**
*@Author : RedCometJ
*@Description : 验证码倒计时
*@params
*@return
*@Date : 2017/11/28
*/
function settime() {
    if (countdown == 0) {
        $('#sendSms').attr("disabled",false);
        $('#sendSms').css("background","#fffbfb")
        $('#sendSms').css("color","black")
        $("#sendSms").val("获取验证码");
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

function affirmBtn() {
    var ctx = $("#ctx").val();
    var phone = sessionStorage.getItem("phone");
    var code =$("#phoneCode").val();
    $.ajax({
        url:ctx + "/checkOutCode",
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:"code=" + code + "&phone=" + phone,
        dataType:'text',
        success:function(data){
            if("success" == data){
                window.location = ctx+"/toChangePassWord"
            }else{
                alert("验证码错误");
            }
        },
        error:function(){
            alert("验证码错误");
        }
    });
}