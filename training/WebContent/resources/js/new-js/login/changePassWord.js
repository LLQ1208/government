function savePassWord() {
    var ctx = $("#ctx").val();
    var passWord = $("#newPassWord").val().trim();
    var rePassWord = $("#reNewPassWord").val().trim();
    var passArr = passWord.trim().split(" ");
    var userName = sessionStorage.getItem("userName");
    if(passArr.length > 1 || passWord.trim().length < 6 || passWord.trim().length > 20){
        alert("密码规则为6-20个字符,只能包含字母、数字以及标点符号（空格除外）");
        return false;
    }
    if(passWord != rePassWord){
        alert("两次密码输入不一致");
        return false;
    }
    if(userName == passWord){
        alert("密码不能和用户名相同")
        return false;
    }
    $.ajax({
        url:ctx + "/updatePassWord",
        type:'post', //GET
        async:true,    //或false,是否异步
        data:"passWord=" + passWord + "&userName=" + userName,
        dataType:'text',
        success:function(data){
            if("success" == data){
                window.location = ctx+"/wod/queryWodList";
            }
        },
        error:function(){
            alert("内部错误");
        }
    });

}