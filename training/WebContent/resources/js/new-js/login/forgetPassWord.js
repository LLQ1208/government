$(function () {
    //点击登录按钮
    $("#affirm").click(function(){
        var ctx = $("#ctx").val();
        var req = $("#req").val();
        var code = $("#verifyCode").val();
        var userName = $("#userName").val();
        $.ajax({
            url: ctx+"/checkCode",
            type: "GET",
            dataType:'text',
            data:"code=" + code+"&userName="+userName,
            async:false,
            // beforeSend: function(){
            //     $(".login-status").html("正在登录中.....");
            // },
            success: function(rs){
                var rsJson = eval('(' + rs+ ')');
                if("success" == rsJson.result){
                    console.log(rsJson.userName);
                    console.log(rsJson.phone);
                    sessionStorage.setItem("userName",rsJson.userName);
                    sessionStorage.setItem("phone",rsJson.phone);
                    window.location = ctx+"/toSendMessage";
                }else if("noPhone" == rsJson.result){
                    alert("此用户没有手机号")
                }else{
                    alert("验证码错误");
                    console.log("login fail");
                }
            },
            error:function(XMLHttpRequest){
                console.log('服务器异常');
            }
        });
    });
});