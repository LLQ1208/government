/**
 * Created by anran on 2018/1/18.
 */
$(function () {
    var E = window.wangEditor;
    var editor = new E('#editor');
    // 或者 var editor = new E( document.getElementById('editor') )
    editor.create();
    if(0 != $("#mailId").val()){
        $("#save").hide();
        $("#update").show();
        $("#deleted").show();
    }else{
        $("#save").show();
        $("#update").hide();
        $("#deleted").hide();
    }
    addInit(editor,$("#mailId").val());

    document.getElementById('save').addEventListener('click', function () {
        // 读取 html
        var json = editor.txt.getJSON();
        var txtLength = json[0].children[0].length;
        var titleLength =  $("#title").val().length;
        if(titleLength > 200){
            alert("标题能超过200字");
              return false;
        }else if(titleLength <= 0){
            alert("标题不能为空");
            return false;
        }
        if( txtLength > 0){
            if(txtLength > 10000){
                alert('输入文本不能超过10000字。');
                return false;
            }
            addMail(editor.txt.html())
        }else{
            alert('输入文本不能为空。');
            return false;
        }

    }, false);

    document.getElementById('update').addEventListener('click', function () {
        // 读取 html
        var json = editor.txt.getJSON();
        var txtLength = json[0].children[0].length;
        var titleLength =  $("#title").val().length;
        if(titleLength > 200){
            alert("标题能超过200字");
            return false;
        }else if(titleLength <= 0){
            alert("标题不能为空");
            return false;
        }
        if(txtLength > 0){
            if(txtLength >10000){
                alert('输入文本不能超过10000字。');
                return false;
            }
            updateMail($("#mailId").val(),editor.txt.html());
        }else{
            alert('输入文本不能为空。');
            return false;
        }

    }, false);

    document.getElementById('deleted').addEventListener('click', function () {
        // 读取 html

        var msg = "是否删除 "+$("#title").val()+"?";
        if (confirm(msg)==true){
            deletedMail($("#mailId").val());
        }else{
            return false;
        }
    }, false);

    $('.rule-btns a').on('click', function () {
        $(this).toggleClass('current').siblings('a').removeClass('current');
        if ($(this).hasClass('current')) {
            if ($(this).index() == 0) {
                $('.rule span').html('该邮件会根据会员的生日来发送');
            } else if ($(this).index() == 1) {
                $('.rule span').html('该邮件会根据会员的即将到期时间来发送');
            } else if ($(this).index() == 2) {
                $('.rule span').html('该邮件会根据会员的到期时间来发送');
            }
        }else{
            $('.rule span').html('');
        }
    });
    changeWin();
});

function addInit(editor,id) {
    var ctx = $("#ctx").val();
    var data = {mailId:id};
    if(null != id && 0 != id && typeof(id) != "undefined"){
        $.ajax({
            url: ctx + "/mailManage/mailAddInit",
            type: 'POST', //POST
            async: true,    //或false,是否异步
            data:data,
            dataType: 'json',
            success: function (data) {
                editor.txt.html(data.content);
                $("#title").val(data.title);
                if(data.mailSendRule == 0){
                    $('.rule-btns a:eq(0)').toggleClass('current');
                }else if(data.mailSendRule == 1){
                    $('.rule-btns a:eq(1)').toggleClass('current');
                }else if(data.mailSendRule == 2){
                    $('.rule-btns a:eq(2)').toggleClass('current');
                }
            },
            error: function () {
                alert("错误");
            }
        });
    }
}

function addMail(content){
    var ctx = $("#ctx").val();
    var title = $("#title").val();
    // var content = editor.txt.html();
    var rule = $('.rule-btns a[class=current]').index();
    var data = {title:title,content:content,rule:rule};
    $.ajax({
        url: ctx + "/mailManage/addMailSource",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        data:data,
        dataType: 'text',
        success: function () {
            window.location = ctx+"/mailManage/mailList";
        },
        error: function () {
            alert("错误");
        }
    });
}

function updateMail(id,content){
    var ctx = $("#ctx").val();
    var title = $("#title").val();
    // var content = editor.txt.html();
    var rule = $('.rule-btns a[class=current]').index();
    var data = {mailId:id,title:title,content:content,rule:rule};
    if(id == 0 ){
        alert('参数错误');
        return false;
    }
    $.ajax({
        url: ctx + "/mailManage/updateMailSource",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        data:data,
        dataType: 'text',
        success: function () {
            window.location = ctx+"/mailManage/mailList";
        },
        error: function () {
            alert("错误");
        }
    });
}

function deletedMail(id){
    var ctx = $("#ctx").val();
    var data = {mailId:id,isDeleted:true};
    if(id == 0 ){
        alert('参数错误');
        return false;
    }
    $.ajax({
        url: ctx + "/mailManage/updateMailSource",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        data:data,
        dataType: 'text',
        success: function () {
            window.location = ctx+"/mailManage/mailList";
        },
        error: function () {
            alert("错误");
        }
    });
}

function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}

function cancleToList(){
    var ctx = $("#ctx").val();
    if(confirm("确认是否取消?")){
        window.location.href= ctx + "/mailManage/mailList";
    }
}
