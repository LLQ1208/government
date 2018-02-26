var ctx = $("#ctx").val();
$(function(){

    $("#userName").blur(function(){
        autoPwd();
    });

   $("#userName").keyup(function(){
      $("#showMesg").html("");
   });
    $("#password").keyup(function(){
        $("#showMesg").html("");
    });

    //点击登录按钮
    $("#loginBtn").click(function(){
        var userName = $("#userName").val();
        var password = $("#password").val();
        var ck_rmbUser = $("#ck_rmbUser").is(':checked');
        if(userName==""||userName==null||userName=="请输入用户名"||password==""||password==null||password=="请输入密码"){
            $(".login-status").html("请输入用户名密码");
            return false;
        }
        var param = {"userName":userName,"password":password};
        $.ajax({
            url: ctx+"/login",
            type: "POST",
            dataType:'json',
            data:param,
            async:false,
            beforeSend: function(){
                $("#showMesg").html("正在登录中.....");
            },
            success: function(rs){
                if(rs.result){
                    saveUserNameAndPassword(userName, password, ck_rmbUser);
                    if(rs.ret == '3'){
                        window.location = ctx+"/toInformation";
                    }else{
                        if(rs.hasWod){
                            window.location = ctx+"/wod/queryWodList";
                        }else{
                            window.location = ctx+"/index";
                        }

                    }
                }else{
                    $("#showMesg").html(rs.msg);
                    console.log("login fail");
                }
            },
            error:function(XMLHttpRequest){
                console.log('服务器异常');
            }
        });
    });
})


function saveUserNameAndPassword(userName, password, ck_rmbUser) {
    if(ck_rmbUser) {
        var str_username = userName;
        var str_password = password;
        $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
        $.cookie("username", str_username, { expires: 7 });
        $.cookie("password", str_password, { expires: 7 });
    }else {
        $.cookie("rmbUser", "false", { expire: -1 });
        $.cookie("username", "", { expires: -1 });
        $.cookie("password", "", { expires: -1 });
    }
};

function autoPwd(){
    if ($.cookie("rmbUser") == "true") {
        var username=$("#userName").val();
        if(username==$.cookie("username")){
            $("#ck_rmbUser").attr("checked", true);
            $("#password").val($.cookie("password"));
            $("#password").attr("type","password");
        }
    }
}