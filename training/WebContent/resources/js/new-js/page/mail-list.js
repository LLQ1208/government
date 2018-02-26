/**
 * Created by anran on 2018/1/21.
 */
$(function () {
    loadpage();
    changeWin();
    $('.mail-list-block .clearfix .name').on('click', function () {
        //console.log("--------"+$(this).siblings('.employeeId').val());
        var mailId = $(this).siblings('.mailId').val();
        console.log("id  = "+mailId);

        var ctx = $("#ctx").val();
        window.location = ctx+"/mailManage/mailAdd?mailId="+mailId;
    });
});

function nextMailPage(page) {
    var ctx = $("#ctx").val();
    $.ajax({
        url: ctx + "/mailManage/nextMailList",
        type: 'GET', //GET
        async: false,    //或false,是否异步
        data:"pageIndex="+page,
        dataType: 'json',
        success: function (pager) {
            var list = pager.list;
            var mails = '';
            $.each(list, function (i, item) {
                var insertDate = newDatetimeFormat(item.insertTime);
                mails+= '<li class="clearfix">'
                mails +='<p class="name fl">'+item.title+'</p>';
                mails +='<p class="time fl">'+insertDate+'</p>';
                mails +='<p class="person fl">'+item.userName+'</p>';
                mails +='<input type="hidden" class="mailId" value="'+item.id+'"/>';
                mails +='</li>';
            });
            $(".mail-list-block").html(mails);
            $("#PageCount").val(pager.totalRow == 0 ? 1 : pager.totalRow);
            loadpage();
            console.log("成功查询");
        },
        error: function () {
            alert("错误");
        }
    });
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
                nextMailPage(num);
            }
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