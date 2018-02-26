/**
 * Created by anran on 2018/1/13.
 */
$(function () {
    $('.male,.female,.custom,.type,.custom-type input,.class-all,.class-o').iCheck({
        checkboxClass: 'icheckbox_flat-red',
        radioClass: 'iradio_flat-red'
    });
    $('.select-box,#box,#select-type').selectpicker();
    $('.form-datetime,.start-datetime,.end-datetime,.card-datetime').datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        minView: 2,
        initialDate: new Date(),
        autoclose: true
    });
    var oDate = new Date();
    $('.start-datetime,.end-datetime,.card-datetime').val(oDate.getFullYear() + '-' + toDou(oDate.getMonth() + 1) + '-' + toDou(oDate.getDate()));
    $(".form-datetime").val("1990-01-01");
    //自定义课程
    // 全选
    $('.class-all').on('ifClicked', function () {
        console.log($(this).prop('checked'));
        if (!$(this).prop('checked') == true) {
            $('.class-o').iCheck('check');
        } else {
            $('.class-o').iCheck('uncheck');
        }
    });
    // 分个选
    $('.class-o').on('ifChanged', function () {
        // console.log($(this).parents('li').index());
        var n = 0;
        for (var i = 0; i < $('.class-o').length; i++) {
            if ($('.class-o').eq(i).prop('checked') == true) {
                n++;
            }
        }
        if (n == $('.class-o').length) {
            $('.class-all').iCheck('check');
        } else {
            $('.class-all').iCheck('uncheck');
        }
    });

    // 自定义卡种
    $('.count-type').on('ifChanged', function () {
        if($(this).prop('checked')){
            $('.custom-info-count').show();
            $('.custom-info-time').hide();
            $(".course-type-div").show();
            $(".select-type").show();
            $('#countStartTime,#countEndTime').val(oDate.getFullYear() + '-' + toDou(oDate.getMonth() + 1) + '-' + toDou(oDate.getDate()));
            $("#countExpireDay").val("");
            $("#countNum").val("");
            $("#countMoney").val("");
            $("#select-type").val("1");
            $('.class-o').iCheck('uncheck');
            $('.class-all').iCheck('uncheck');
        }else{
            $('.custom-info-count').hide();
            $('.custom-info-time').show();
            $(".course-type-div").show();
            $(".select-type").hide();
            $('#timeStartTime,#timeEndTime').val(oDate.getFullYear() + '-' + toDou(oDate.getMonth() + 1) + '-' + toDou(oDate.getDate()));
            $("#timeExpire").val("");
            $("#timeMoney").val("");
            $("#countMoney").val("");
            $("#select-type").val("1");
            $('.class-o').iCheck('uncheck');
            $('.class-all').iCheck('uncheck');
        }
    });

    // 选择会员卡
    $('.custom').on('ifChanged', function () {
        if($(this).prop('checked')){
            $('.quick-add-custom').show();
            $('.quick-add-card').hide();
        }else{
            $('.quick-add-custom').hide();
            $('.quick-add-card').show();
        }
    });
    $('.type').on('ifChanged', function () {
        if($(this).prop('checked')){
            var templateId = $(this).attr("templateId");
            var money = $(this).attr("money");
            var templateType = $(this).attr("templateType");
            var templateName = $(this).attr("templateName");
            $('.card-name').html(templateName);
            $("#generalMoney").val(money);
        }
    });

    //只能输入数字
    $(".number_input").keyup(function(){
        $(this).val($(this).val().replace(/[^0-9]/g,''));
    }).bind("paste",function(){
        $(this).val($(this).val().replace(/[^0-9]/g,''));
    })

    //私教选择下拉框
    $('#select-type').on('change', function () {
        var id=$(this).val();
        if(id == '1'){
            $(".course-type-div").show();
        }else if(id == '2'){
            $(".course-type-div").hide();
        }
    });
});
function toDou(n) {
    return n > 9 ? n : '0' + n;
}
function isEmail(email){
    return email.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
}

// 验证手机号
function isPhoneNo(phone) {
    var phoneReg = /^1[34578]\d{9}$/;
    var reg = new RegExp(phoneReg);
    return reg.test(phone);
}

function previewImage(file)
{
    var fileName=$("#img").val();
    $("#imgName").attr("value",fileName);

    var MAXWIDTH  = 218;     //用来显示上传图片的宽度
    var MAXHEIGHT = 158;     //用来显示上传图片的高度
    var div = document.getElementById('traningLogo');
    if (file.files && file.files[0])
    {
        if(file.files[0].size > 5242880){
            alert("图片不能大于5M");
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
        $("#traningLogo").css("background-image","");
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
//保存
function save(){
    var memberId = $("#memberId").val();
    var img = $("#img").val();
    var imgHeadSrc =  $("#imghead").attr("src");
    var sex = $("input[name='sex']:checked").val();
    var birthday = $("#birthday").val();
    var memberName = $("#memberName").val().trim();
    var phone = $("#phone").val().trim();
    var email = $("#email").val().trim();
    var box = $("#box").val();
    if((null == img || img == "") && imgHeadSrc.indexOf("no_pic.png") > 0){
        alert("请上传图片");
        return false;
    }
    if(null == memberName || memberName == ""){
        alert("请输入会员姓名");
        return false;
    }
    $("#memberName").val(memberName);
    if(null == phone || phone == "" || !isPhoneNo(phone)){
        alert("请输入正确会员手机号");
        return false;
    }
    $("#phone").val(phone);
    if(null == email || email == ""  || !isEmail(email)){
        alert("请输入正确会员电子邮箱");
        return false;
    }
    $("#email").val(email);
    var templateId = $("input[name='type']:checked").val();
    $("#templateId").val(templateId);
    //校验手机号，姓名，有效的团课卡，如果有一张有效的团课卡的话，时间校验，次卡校验
    var json = {};
    if(templateId == '0'){
        var customType = $("input[name='custom-type']:checked").val();
        $("#customCardType").val(customType);
        if(customType == "2"){
            var beginTime = $("#countStartTime").val();
            var endTime = $("#countEndTime").val();
            var expireDay = $("#countExpireDay").val().trim();
            var countNum = $("#countNum").val().trim();
            var money = $("#countMoney").val().trim();
            if(null == expireDay || expireDay == ""){
                alert("请输入到期提醒天数");
                return false;
            }
            $("#countExpireDay").val(expireDay);
            if(null == countNum || countNum == ""){
                alert("请输入次数");
                return false;
            }
            $("#countNum").val(countNum);

            if(null == money || money == ""){
                alert("请输入金额");
                return false;
            }
            $("#countMoney").val(money);

            var countType = $("#select-type").val();
            $("#customCountType").val(countType);

            if(countType == '1'){//团课卡
                //课程类型
                var courseTypeIds = '';
                $.each($('input[name="time_courseType"]'),function(){
                    if(this.checked){
                        courseTypeIds+= $(this).val()+",";
                    }
                });
                if(courseTypeIds == ""){
                    alert("请选择课程");
                    return false;
                }else{
                    $("#customCourseTypeIds").val(courseTypeIds.substring(0,courseTypeIds.length-1));
                }
            }else if(countType == '2'){//私教卡

            }
            json = {memberId:memberId,memberName:memberName,phone:phone,box:box,templateId:templateId,
                customType:customType,beginTime:beginTime,endTime:endTime,money:money,
                bountType:countType};
        }else if(customType == "1"){
            var beginTime = $("#timeStartTime").val();
            var endTime = $("#timeEndTime").val();
            var expireDay = $("#timeExpire").val().trim();
            if(null == expireDay || expireDay == ""){
                alert("请输入到期提醒天数");
                return false;
            }
            $("#timeExpire").val(expireDay);
            var courseTypeIds = '';
            $.each($('input[name="time_courseType"]'),function(){
                if(this.checked){
                    courseTypeIds+= $(this).val()+",";
                }
            });
            if(courseTypeIds == ""){
                alert("请选择课程");
                return false;
            }else{
                $("#customCourseTypeIds").val(courseTypeIds.substring(0,courseTypeIds.length-1));
            }
            json = {memberId:memberId,memberName:memberName,phone:phone,box:box,templateId:templateId,
                customType:customType,beginTime:beginTime,endTime:endTime};
        }
    }else{
        var money = $("#generalMoney").val().trim();
        if(null == money || money == ""){
            alert("请输入金额");
            return false;
        }
        $("#generalMoney").val(money);
        var beginTime = $("#generalBeginTime").val();
        json = {memberId:memberId,memberName:memberName,phone:phone,box:box,templateId:templateId,
            beginTime:beginTime};
    }
    var ctx = $("#ctx").val();
    var isExist = false;
    var mesg =  "";
    $.ajax({
        url: ctx + "/memberNew/memberExtendCheck",
        type: 'POST', //GET
        async: false,    //或false,是否异步
        data: json,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (data) {
            if(data.status == "fail"){
                isExist = true;
                mesg = data.mesg;
                return false;
            }
        },
        error: function () {
            console.log("校验会员卡错误")
        }
    });
    if(isExist){
        alert(mesg);
        return false;
    }
    return true;
}

