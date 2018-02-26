/**
 * Created by anran on 2018/1/21.
 */
$(function () {
    // var logo = $("#boxLogo").val();
    // if("" != logo && typeof(logo) != "undefined"){
    //     $("#img").val(logo);
    //     $(".traningLogo").css("background-image","url("+logo+")");
    // }

    // $('.save-btn').on('click', function () {
    //     var boxId = $("#boxId").val();
    //     if("" != boxId && typeof(boxId) != "undefined"){
    //         if(-99 == boxId){
    //             addBoxInfo();
    //         }else{
    //             updateBox();
    //         }
    //     }
    // });
});

// function addBoxInfo(){
//     var ctx = $("#ctx").val();
//     var name = $("#name").val().trim();
//     var phone = $("#phone").val().trim();
//     var address = $("#address").val().trim();
//     var contact = $("#contact").val().trim();
//     var email = $("#email").val().trim();
//
//     if("" == name || typeof(name) == "undefined"){
//         alert("请输入正确的训练馆名称！");
//         return false;
//     }
//     if("" == address || typeof(address) == "undefined"){
//         alert("请输入正确的训练馆地址！");
//         return false;
//     }
//     if("" == contact || typeof(contact) == "undefined"){
//         alert("请输入正确的店长名称！");
//         return false;
//     }
//     if("" == phone || typeof(phone) == "undefined" || !isPhoneNo(phone)){
//         alert("请输入正确的电话号码！");
//         return false;
//     }
//     if("" == email || typeof(email) == "undefined" || !isEmail(email)){
//         alert("请输入正确的邮箱");
//         return false;
//     }
//     var data = {name:name,address:address,contact:contact,phone:phone,email:email}
//     $.ajax({
//         url: ctx + "/personalCenter/addBox",
//         type: 'POST', //POST
//         async: true,    //或false,是否异步
//         data: data,
//         dataType: 'json',
//         success: function (data) {
//             if("0" == data.code){
//                 window.location.href = ctx+"/personalCenter/boss";
//             }else{
//                 alert(data.mesg);
//             }
//         },
//         error: function () {
//             alert("错误");
//         }
//     });
// }

// function updateBox(){
//     var ctx = $("#ctx").val();
//     var name = $("#name").val().trim();
//     var phone = $("#phone").val().trim();
//     var address = $("#address").val().trim();
//     var contact = $("#contact").val().trim();
//     var email = $("#email").val().trim();
//     var boxId = $("#boxId").val();
//
//     if("" == name || typeof(name) == "undefined"){
//         alert("请输入正确的训练馆名称！");
//         return false;
//     }
//     if("" == address || typeof(address) == "undefined"){
//         alert("请输入正确的训练馆地址！");
//         return false;
//     }
//     if("" == contact || typeof(contact) == "undefined"){
//         alert("请输入正确的店长名称！");
//         return false;
//     }
//     if("" == phone || typeof(phone) == "undefined" || !isPhoneNo(phone)){
//         alert("请输入正确的电话号码！");
//         return false;
//     }
//     if("" == email || typeof(email) == "undefined" || !isEmail(email)){
//         alert("请输入正确的邮箱");
//         return false;
//     }
//     var data = {boxId:boxId,name:name,address:address,contact:contact,phone:phone,email:email}
//     $.ajax({
//         url: ctx + "/personalCenter/updateBox",
//         type: 'POST', //POST
//         async: true,    //或false,是否异步
//         data: data,
//         dataType: 'text',
//         success: function (data) {
//             if("success" == data){
//                 window.location.href = ctx+"/personalCenter/boss";
//             }else{
//                 if(data == 'error1')
//                     alert("存在有效会员，不能删除该馆！");
//             }
//         },
//         error: function () {
//             alert("错误");
//         }
//     });
// }

//校验邮箱
function isEmail(email){
    return email.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
}
// 验证手机号
function isPhoneNo(phone) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
}

function saveBefore(){
    var name = $("#name").val().trim();
    var phone = $("#phone").val().trim();
    var address = $("#address").val().trim();
    var contact = $("#contact").val().trim();
    var email = $("#email").val().trim();
    var img = $("#img").val().trim();

    if("" == name || typeof(name) == "undefined"){
        alert("请输入正确的训练馆名称！");
        return false;
    }
    if("" == address || typeof(address) == "undefined"){
        alert("请输入正确的训练馆地址！");
        return false;
    }
    if("" == contact || typeof(contact) == "undefined"){
        alert("请输入正确的店长名称！");
        return false;
    }
    if("" == phone || typeof(phone) == "undefined" || !isPhoneNo(phone)){
        alert("请输入正确的电话号码！");
        return false;
    }else{
        if(queryPhone(phone)){
            alert("该手机号已被注册!");
            return false;
        }
    }
    if("" == email || typeof(email) == "undefined" || !isEmail(email)){
        alert("请输入正确的邮箱");
        return false;
    }
    var boxId = $("#boxId").val();

    if(-99 == boxId){
        if(img == null || "" == img){
            alert("请上传图片");
            return false;
        }
    }
    return true;
}

function queryPhone(phone){
    var ctx = $("#ctx").val();
    var code = 0;
    var data = {phone:phone}
    $.ajax({
        url: ctx + "/personalCenter/queryPhone",
        type: 'POST', //POST
        async: false,    //或false,是否异步
        data: data,
        dataType: 'text',
        success: function (data) {
            if(-1 == data.code){
               code = -1;
            }
        },
        error: function () {
            alert("错误");
        }
    });
    if(code == 0){
        return false;
    }else{
        return true;
    }
}

function previewImage(file) {
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
}