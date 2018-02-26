/**
 * Created by anran on 2018/1/21.
 */
$(function () {

    var msg = GetRequest();
    if(msg.news == 1){
        alert("信息保存成功");
    }
    var ctx = $("#ctx").val();
    $('#addBox').on('click', function () {
        // $('#iframe',window.parent.document).attr('src','pages/gym-add.html');
        window.location.href = ctx+"/personalCenter/addBoxInit";
    });

    $('.edit-btn').on('click', function () {
        var boxId = $(this).siblings('.boxId').val();
        window.location.href = ctx+"/personalCenter/addBoxInit?boxId="+boxId;
    });

    // $('#saveBoss').on('click', function () {
    //     var bossId = $("#bossId").val();
    //     if(!bossInfoUpdate(bossId)){
    //         return false;
    //     }
    // });

    $('.disable-btn').on('click', function () {
        var boxId = $(this).siblings('.boxId').val();
        var msg = "是否暂定使用该馆，如若暂停会员将不能预约课程！";
        if (confirm(msg) == true){
            updateBox(boxId,0);
        }
    });

    $('.start-btn').on('click', function () {
        var boxId = $(this).siblings('.boxId').val();
        updateBox(boxId,1);
    });

    $('.delete-btn').on('click', function () {
        var boxId = $(this).siblings('.boxId').val();
        var msg = "是否删除该馆，如若删除所有会员信息将消失！";
        if (confirm(msg) == true){
            updateBox(boxId,2);
        }
    });
});

function updateBox(id,status){
    var ctx = $("#ctx").val();
    var data = {boxId:id,status:status}
    $.ajax({
        url: ctx + "/personalCenter/updateBox",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        data: data,
        dataType: 'text',
        success: function (data) {
            if("success" == data){
                window.location.href = ctx+"/personalCenter/boss";
            }else{
                if(data == 'error1')
                alert("存在有效会员，不能删除该馆！");
            }
        },
        error: function () {
            alert("错误");
        }
    });
}

function bossInfoUpdate(id) {
    var bossId = $("#bossId").val();

    if("" == bossId || typeof(bossId) == "undefined"){
        console.log("id null");
        return false;
    }
    var bossName = $("#bossName").val().trim();
    var bossPhone = $("#bossPhone").val().trim();
    var bossEmail = $("#bossEmail").val().trim();
    var ctx = $("#ctx").val();

    if("" == bossName || typeof(bossName) == "undefined"){
        return false;
    }
    if("" == bossPhone || typeof(bossPhone) == "undefined" || !isPhoneNo(bossPhone)){
        alert("请输入正确的电话号码！");
        return false;
    }
    if("" == bossEmail || typeof(bossEmail) == "undefined" || !isEmail(bossEmail)){
        alert("请输入正确的邮箱");
        return false;
    }
    var data = {userId:id,bossName:bossName,bossPhone:bossPhone,bossEmail:bossEmail}

    // $.ajax({
    //     url: ctx + "/personalCenter/updateBossInfo",
    //     type: 'POST', //POST
    //     async: true,    //或false,是否异步
    //     data: data,
    //     contentType:false,//必须false才会自动加上正确的Content-Type
    //     processData:false,//必须false才会避开jQuery对 formdata 的默认处理.XMLHttpR
    //     dataType: 'text',
    //     success: function (data) {
    //         if("success" == data){
    //             alert("信息保存成功");
    //             window.location.href = ctx+"/personalCenter/boss";
    //         }
    //     },
    //     error: function () {
    //         alert("错误");
    //     }
    // });
}

//校验邮箱
function isEmail(email){
    return email.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
}
// 验证手机号
function isPhoneNo(phone) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
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

function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

