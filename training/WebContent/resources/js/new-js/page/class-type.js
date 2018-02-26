var typeColor = "";
$(function () {
    changeWin();
    $('.class-name').on('click',function () {
        $('#sortType').val(1);
        courseTypeSearch($('#sortType').val(),1);

    });
    $('.class-time').on('click',function () {
        $('#sortType').val(0);
        courseTypeSearch($('#sortType').val(),0);

    });
    //右上角红色按钮，添加课程
    $('.add-class').on('click',function(){
        $('.tip-bg').show();    //没有删除按钮的弹窗 显示
        $('.tip-name').val('');  //清空title内容  必填选项
        $('.write').val('');     //清空文本内容
        $('.tip-name').removeClass('active');  //清空边框颜色
    });
    //取消
    $('.cancel').on('click',function(){    //弹窗上面的取消按钮
        $('.tip-bg').hide();                //没有删除按钮的弹窗 隐藏
    });
    // 保存
    $('.confirm').on('click',function(){    //弹窗上面的确定按钮
        // 标题空，边框变色
        if($('.tip-name').val()==''){
            $('.tip-name').addClass('active');
            return false
        }else{   //有标题，边框不变色，弹窗隐藏
            $('.tip-name').removeClass('active');
            $('.tip-bg').hide();
            courseTypeAdd();
        }
    });
    //点击列表，弹出有删除按钮的弹窗
    $('.list-common').on('click',function(){
        $('.check-bg').show();
        $('.del').hide();
        $('.check-name').removeClass('active');
        console.log("1     "+$(this).find('.list-name').html());
        console.log("2    "+$(this).find('.describe').val());
        $(".check-name").val($(this).find('.list-name').html());
        $(".write").val($(this).find('.describe').val());
        // $('.check-name').val('');
        $("#transId").val($(this).find('.courseTypeId').val());
        $("#courseClolor").val($(this).find('.courseTypeColor').val());
        $("#courseClolor").focus();
        $("#courseClolor").blur();
    });
    // 保存与取消逻辑和上面相同
    //取消
    $('.cancel1').on('click',function(){
        $('.check-bg').hide();
    });
    $('.confirm1').on('click',function(){
        // 标题空
        if($('.check-name').val()==''){
            $('.check-name').addClass('active');
            return false
        }else{   //有标题
            $('.check-name').removeClass('active');
            $('.check-bg').hide();
            courseTypeUpdate();
        }
    });

    // 点击删除 二级弹窗显示
    $('.delete').on('click',function(){
        $('.del').show();
    });
    //二级弹窗取消按钮
    $('.cancel2').on('click',function(){
        $('.del').hide();
    });
    //二级弹窗确认删除
    $('.confirm2').on('click',function(){
        $('.check-bg').hide();
        //此处需要添加逻辑删除列表项
        courseTypeDeleted();
    });
    loadpage();
});

function courseTypeSearch(sortType,page){
    var ctx = $("#ctx").val();
    // var page ="";
    // if(typeof(pagess) == "undefined"){
    //     page = pageIndex;
    // }else{
    //     page = pagess;
    // }
    $.ajax({
        url: ctx + "/setting/courseType/newCourseTypeListSearch",
        type: 'POST', //GET
        async: false,    //或false,是否异步
        data:"sortType="+sortType+"&pageIndex="+page,
        dataType: 'json',
        success: function (pager) {
            var list = pager.list;
            var members = '';
            $.each(list, function (i, item) {
                var insertDate = newDatetimeFormat(item.insertTime);
                // var  inserTime = insertDate.format("yyyy-MM-dd HH:mm:ss");
                // var  inserTime = insertDate.formatDate(insertDate,"yyyy-MM-dd HH:mm:ss");
                members +='<div class="list-common">';
                members +='<div class="list-name fl" >'+item.name+'</div>';
                members +='<div class="list-remarks fl">'+(item.describe != undefined ? item.describe:'')+'</div>';
                members +='<div class="list-time fr">'+insertDate+'</div>';
                members +='<input type="hidden" class="describe" value="'+item.describe+'"/>';
                members +='<input type="hidden" class="courseTypeColor" value="'+item.color+'"/>';
                members +='<input type="hidden" class="courseTypeId" value="'+item.id+'"/>';
                members +='</div>'
            });
            $(".class-list").html(members);

            //点击列表，弹出有删除按钮的弹窗
            $('.list-common').on('click',function(){
                $('.check-bg').show();
                $('.del').hide();
                $('.check-name').removeClass('active');
                console.log("1     "+$(this).find('.list-name').html());
                console.log("2    "+$(this).find('.describe').val());
                $(".check-name").val($(this).find('.list-name').html());
                $(".write").val($(this).find('.describe').val());
                // $('.check-name').val('');
                $("#transId").val($(this).find('.courseTypeId').val());
                $("#courseClolor").val($(this).find('.courseTypeColor').val());
                $("#courseClolor").focus();
                $("#courseClolor").blur();
            });
            $("#PageCount").val(pager.totalRow == 0 ? 1 : pager.totalRow);
            loadpage();
            console.log("成功查询");
        },
        error: function () {
            alert("错误");
        }
    });
}


function courseTypeAdd(){
    var ctx = $("#ctx").val();
    var name = $(".tip-name").val().trim();
    if(name.length > 20){
        alert("名称不能超过20个字");
        return false;
    }
    var describes = $("#tipWrite").val();
    var color = $(".jscolor").val();
    $.ajax({
        url: ctx+"/setting/courseType/addRourseType",
        type: 'POST',
        dataType:'text',
        data:"name="+name+"&describes="+describes+"&color="+color,
        async:false,
        success: function(result){
            courseTypeSearch($('#sortType').val(),1);
        },
        error:function(XMLHttpRequest){
            console.log('服务器异常');
        }
    });
}

function courseTypeDeleted(){
    var id = $("#transId").val();
    var ctx = $("#ctx").val();
    $.ajax({
        url: ctx+"/setting/courseType/deleteRourseType",
        type: 'POST',
        dataType:'text',
        data: "id=" + id,
        async:false,
        success: function(result){
            courseTypeSearch($('#sortType').val(),1);
        },
        error:function(XMLHttpRequest){
            console.log('服务器异常');
        }
    });
}

function courseTypeUpdate(){
    var ctx = $("#ctx").val();
    var id = $("#transId").val();
    var name = $(".check-name").val().trim();
    var describes = $("#write").val();
    // var color = $(".jscolor").val();
    var color = $("#typeColor").val();
    if(name.length > 20){
        alert("名称不能超过20个字");
        return false;
    }
    $.ajax({
        url: ctx + "/setting/courseType/updateRourseType",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        data: "id=" + id+"&name="+name+"&describes="+describes+"&color="+color,
        dataType: 'text',
        success: function (data) {
            $('.add-section').fadeOut(function () {
                $('.section-content').removeClass('red edit-content');
                $('.section-content input').val('');
            });
            console.log("成功保存");
            courseTypeSearch($('#sortType').val(),1);
        },
        error: function () {
            alert("错误");
        }
    });
}

function newDatetimeFormat(longTypeDate){
    var datetimeType = "";
    var date = new Date();
    date.setTime(longTypeDate);
    datetimeType+= date.getFullYear();   //年
    datetimeType+= "-" + getMonth(date); //月
    datetimeType += "-" + getDay(date);   //日
    // datetimeType+= "&nbsp;&nbsp;" + getHours(date);   //时
    // datetimeType+= ":" + getMinutes(date);      //分
    // datetimeType+= ":" + getSeconds(date);      //分
    return datetimeType;
}

function getMonth(date){
    var month = "";
    month = date.getMonth() + 1; //getMonth()得到的月份是0-11
    if(month<10){
        month = "0" + month;
    }
    return month;
}
//返回01-30的日期
function getDay(date){
    var day = "";
    day = date.getDate();
    if(day<10){
        day = "0" + day;
    }
    return day;
}
//返回小时
function getHours(date){
    var hours = "";
    hours = date.getHours();
    if(hours<10){
        hours = "0" + hours;
    }
    return hours;
}
//返回分
function getMinutes(date){
    var minute = "";
    minute = date.getMinutes();
    if(minute<10){
        minute = "0" + minute;
    }
    return minute;
}
//返回秒
function getSeconds(date){
    var second = "";
    second = date.getSeconds();
    if(second<10){
        second = "0" + second;
    }
    return second;
}
function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}

function changeColor(picker){
    $('#typeColor').val(picker.toString());
}

function loadpage() {
    var myPageCount = parseInt($("#PageCount").val());
    var myPageSize = parseInt($("#PageSize").val());
    var countindex = myPageCount % myPageSize > 0 ? (myPageCount / myPageSize) + 1 : (myPageCount / myPageSize);
    $("#countindex").val(countindex);

    $.jqPaginator('#pagination', {
        totalPages: parseInt($("#countindex").val()),
        visiblePages: parseInt($("#visiblePages").val()),
        currentPage: 1,
        first: '<li class="first"><a href="javascript:;">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:;"><i class="arrow arrow2"></i>上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页<i class="arrow arrow3"></i></a></li>',
        last: '<li class="last"><a href="javascript:;">末页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            if (type == "change") {
                courseTypeSearch($('#sortType').val(),num);
            }
        }
    });
}