/**
 * Created by anran on 2017/12/29.
 */

var phoneReg = /^1[34578]\d{9}$/;
function previewImage1(file)
{
    var fileName=$("#img").val();
    $("#imgName").attr("value",fileName);

    var MAXWIDTH  = 218;     //用来显示上传图片的宽度
    var MAXHEIGHT = 158;     //用来显示上传图片的高度
    var div = document.getElementById('traningLogo');
    if (file.files && file.files[0])
    {
        if(file.files[0].size > 1048576){
            alert("图片不能大于1M");
            return false;
        }
        div.innerHTML = '<img id="imghead">';
        var img = document.getElementById('imghead');
        img.onload = function(){
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            //img.width = rect.width;
            //img.height = rect.height;
            //img.style.marginLeft = rect.left+'px';
            //img.style.marginTop = rect.top+'px';
        };
        var reader = new FileReader();
        reader.onload = function(evt){img.src = evt.target.result;};
        reader.readAsDataURL(file.files[0]);
    }
    else
    {
        var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id="imghead">';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
        div.innerHTML = "<div id='divhead'  style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";
    }
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
    var param = {top:0, left:0, width:width, height:height};
    if( width>maxWidth || height>maxHeight )
    {
        var rateWidth = width / maxWidth;
        var rateHeight = height / maxHeight;
        if( rateWidth > rateHeight )
        {
            param.width =  maxWidth;
            param.height = Math.round(height / rateWidth);
        }else
        {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}

// 验证手机号
function isPhoneNo(phone) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
}

function isEmail(email){
    return email.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
}

function saveBefore1(){
    var result = 1;
    var ctx = $("#ctx").val();
    var birthday = $("#birthday").val().trim();
    var employeeName = $("#employeeName").val().trim();
    var phone = $("#phone").val().trim();
    var email = $("#email").val().trim();
    var img = $("#img").val().trim();
    //var pwd = $("#pwd").val().trim();
    if(birthday == null || "" == birthday){
        alert("请输入生日");
        result = -1;
    }
    if(employeeName == null || "" == employeeName){
        alert("请输入姓名");
        result = -1;
    }
    if(email == null || "" == email || !isEmail(email)){
        alert("请输入正确的邮箱");
        result = -1;
    }
    var reg = new RegExp(phoneReg);
    if(phone == null || "" == phone || !reg.test(phone.trim())){
        alert("请输入正确的电话号码");
        result = -1;
    }else{
        $.ajax({
            url:ctx + "/setting/employee/queryPhone",
            type:'POST', //GET
            async:false,    //或false,是否异步
            data:{phone:phone},
            dataType:'json',
            success:function(data){
                if(data.code == "-1"){
                    alert(data.mesg);
                    result = -1;
                }
            },
            error:function(){
                result = -1;
            }
        });
    }
    //if(pwd == null || "" == pwd){
    //    alert("请输入密码");
    //    return false;
    //}

    if(img == null || "" == img){
        alert("请上传图片");
        result = -1;
    }
    return result;
}

$(function () {
   $('.selectpicker').selectpicker();

    $('.form_datetime').datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        minView: 2,
        initialDate: new Date(),
        autoclose: true
    });

    var oDate = new Date();
    $('.form_datetime').val(oDate.getFullYear() + '-' + toDou(oDate.getMonth() + 1) + '-' + toDou(oDate.getDate()));

    $('.sex input').iCheck({
        radioClass: 'iradio_flat-red'
    });

    function toDou(n) {
        return n > 9 ? n : '0' + n;
    }

    $('.save-btn').on('click', function(){
        var result= saveBefore1();
        if(result != -1){
            $("#employeeInformation").submit();
        }else{
            return false;
        }
    })
    $('.cancel-btn').on('click', function(){
        var ctx = $("#ctx").val();
        window.location = ctx + "/setting/employee";
    })
});