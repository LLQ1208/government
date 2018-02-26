
    $('#login').css({'height':$(window).height()});
    $('.pCover1').on('input',function () {
        if($('.pCover1').html()==''){
            $('.pCover').show();
            $('.pCover1').css({'position':'absolute'});
        }else {
            $('.pCover').hide();
            $('.pCover1').css({'position':'static'});
        }
    });

    var phoneReg = /^1[34578]\d{9}$/;
    function previewImage(file)
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

    function saveBefore(){

        var name = $("#name").val().trim();
        var pAdress = $("#pAdress").html().trim();
        var contactTel = $("#contactTel").val().trim();
        var contact = $("#contact").val().trim();
        var email = $("#email").val().trim();
        var img = $("#img").val().trim();
        if(name == null || "" == name){
            alert("请输入名字");
            //$("#submitBut").attr('disabled',false);
            return false;
        }else{
            if(name.length > 20){
                alert("名字最多20字");
                //$("#submitBut").attr('disabled',false);
                return false;
            }
        }
        if(pAdress == null || "" == pAdress){
            alert("请输入地址");
            //$("#submitBut").attr('disabled',false);
            return false;
        }else{
            if(pAdress.length > 100){
                alert("地址最多100字");
                //$("#submitBut").attr('disabled',false);
                return false;
            }
        }
        var reg = new RegExp(phoneReg);
        if(contactTel == null || "" == contactTel || !reg.test(contactTel.trim())){
            alert("请输入正确的电话号码");
            //$("#submitBut").attr('disabled',false);
            return false;
        }
        if(email == null || "" == email || !isEmail(email)){
            alert("请输入正确的邮箱");
            //$("#submitBut").attr('disabled',false);
            return false;
        }
        if(img == null || "" == img){
            alert("请上传图片");
           // $("#submitBut").attr('disabled',false);
            return false;
        }
        //$("#submitBut").attr('disabled',true);
        return true;
    }
    // 验证手机号
    function isPhoneNo(phone) {
        var pattern = /^1[34578]\d{9}$/;
        return pattern.test(phone);
    }

    function isEmail(email){
        return email.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
    }