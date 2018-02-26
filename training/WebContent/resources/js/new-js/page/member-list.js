/**
 * Created by anran on 2018/1/11.
 */
$(function () {
    changeWin();
   $('#train,#sex,#template,#box').selectpicker();
    $("#searchBut").on("click",function(){
        search(1,1);
    })
    $("#box").on("change",function(){
        search(1,1);
    })

    $("#sex").on("change",function(){
        search(1,1);
    })
    $("#template").on("change",function(){
        search(1,1);
    })
   $('.cards li').on('click', function () {
       $(this).addClass('current').siblings('li').removeClass('current');
       //$('.card-status li:eq(0) ').addClass('active').siblings('li').removeClass('active');
       search(1,1);
   });
   $('.card-status li').on('click', function () {
       $(this).addClass('active').siblings('li').removeClass('active');
       search(1,1);
   });

    $('.select-all,.select-one').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        increaseArea: '20%' // optional
    });
    $("#lengthenBut").on("click",function(){
        var days = $("#lengthDays").val();
        var r =  /^[0-9]+.?[0-9]*$/;
        if(!r.test(days) || Number(days) < 1){
            alert("请输入正确的天数");
            return false;
        }
        var cardIds = '';
        $.each($('input.select-one'),function(){
            if(this.checked){
                cardIds+= $(this).val()+",";
            }
        });
        cardIds = cardIds.substring(0,cardIds.length-1);
        //参数
        var json = {cardIds:cardIds,days:days};
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/memberNew/memberLengthen",
            type: 'POST', //GET
            async: false,    //或false,是否异步
            data: json,
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {
                var page = $("#nowPageIndex").val();
                search(page,0);
                $('.extend-box').fadeOut();
            },
            error: function () {
                console.log("搜索会员错误")
            }
        });
    });
    // 全选
    $('.select-all').on('ifClicked', function () {
        // console.log($(this).prop('checked'));
        if (!$(this).prop('checked') == true) {
            $('.select-one').iCheck('check');
        } else {
            $('.select-one').iCheck('uncheck');
        }
    });

    // 分个选
    $('.select-one').on('ifChanged', function () {
        // console.log($(this).parents('li').index());
        var n = 0;
        for (var i = 0; i < $('.data-list li').length; i++) {
            if ($('.data-list li').eq(i).find('.select-one').prop('checked') == true) {
                n++;
            }
        }
        if (n == $('.data-list li').length) {
            $('.select-all').iCheck('check');
        } else {
            $('.select-all').iCheck('uncheck');
        }
    });

    $('.sort-list').on('click', function () {
        $(".sort-list").not(this).removeClass("sort-up").removeClass("sort-down");
        if(!$(this).hasClass("sort-down") && !$(this).hasClass("sort-up")){
            $(this).addClass("sort-down");
        }else if($(this).hasClass("sort-down") && !$(this).hasClass("sort-up")){
            $(this).removeClass("sort-down").addClass("sort-up");
        }else if(!$(this).hasClass("sort-down") && $(this).hasClass("sort-up")){
            $(this).removeClass("sort-up").addClass("sort-down");
        }
        search(1,1);
    });

    $('.cancel-btn').on('click', function () {
        $('.extend-box').fadeOut();
    });
    $('.extend').on('click', function () {
        var cardIds = '';
        $.each($('input.select-one'),function(){
            if(this.checked){
                cardIds+= $(this).val()+",";
            }
        });
        if(cardIds == ''){
            alert("请选择要延长的会员");
            return false;
        }
        cardIds = cardIds.substring(0,cardIds.length-1);
        var num = cardIds.split(",").length;
        $(".extend-block-title").html("您确定给"+num+"位会员延长会员卡时间吗？");
        $('.extend-box').fadeIn();
    });

    $('.add-member').on('click', function () {
        $('#iframe',window.parent.document).attr('src','pages/member-add.html');
    });

    $('.name').on('click', function () {
        $('#iframe',window.parent.document).attr('src','pages/member-view.html');
    });

    loadpage();
});


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
            if(type != 'init'){
                var ctx = $("#ctx").val();
                console.log(num);
               search(num,0);
            }
        }
    });
}

function search(page,type){
    var searchKey = $("#searchKey").val().trim();
    var boxId = $("#box").val();
    var cardType = $(".cards li.current").attr("index");
    var timeType = $(".card-status li.active").attr("timeType");
    var sex = $("#sex").val();
    var templateId = $("#template").val();
    var orderJson = getOrderType();
    var param = {searchKey:searchKey,boxId:boxId,cardType:cardType,timeType:timeType,
        sex:sex,templateId:templateId,orderType:orderJson.orderType,orderDesc:orderJson.orderDesc,
        pageIndex:page};
    var ctx = $("#ctx").val();
    $.ajax({
        url: ctx + "/memberNew/memberSearch",
        type: 'POST', //GET
        async: false,    //或false,是否异步
        data: param,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (data) {
            var html = '';
            if(null != data && data != undefined){
                var pageHelper = data;
                if(pageHelper.list != null && pageHelper.list.length > 0){
                    for(var i=0;i<pageHelper.list.length;i++){
                        var template = pageHelper.list[i];
                        html += '<li class="clearfix">'+
                            '<p class="name fl"><input type="checkbox" class="select-one" value="'+template.memberCardId+'"><label onclick="toDetail(this)" class="over-hide" memberId="'+template.memberId+'"  boxid="'+template.box+'">'+template.memberName+'</label></p>'+
                            '<p class="sex-box fl">'+template.sexStr+'</p>'+
                            '<p class="birth fl">'+template.birthday+'</p>'+
                            '<p class="phone fl">'+template.phone+'</p>'+
                            '<p class="mail fl">'+template.email+'</p>'+
                            '<p class="run-card fl">'+template.firstOpenCardTime+'</p>'+
                            '<p class="renewal-card fl">'+template.continueCardTime+'</p>'+
                            '<p class="expire fl">'+template.cardEndTime+'</p>'+
                            '<p class="card-kind fl">'+template.cardTemplateName+'</p>'+
                            '<p class="card-type fl">'+template.memberCardType+'</p>'+
                            '<p class="count fl">'+template.totalCount+'</p>'+
                            '<p class="status fl">'+template.cardStatus+'</p>'+
                            '<p class="latest-class fl">'+template.lastCourseTime+'</p>'+
                            '<p class="add-up-num fl">'+template.orderCourseCount+'</p>'+
                            '<p class="rate fl">'+template.orderCourseRate+'</p>'+
                            '<p class="order-class fl">'+template.thisCardOrderCourseCount+'</p>'+
                            '<p class="order-rate fl">'+template.thisCardOrderCourseRate+'</p>'+
                            '<p class="attendance fl">'+template.attendanceRate+'</p>'+
                            '</li>';
                    }
                }
                $(".data-list").html(html);
                $('.select-all,.select-one').iCheck({
                    checkboxClass: 'icheckbox_minimal-blue',
                    increaseArea: '20%' // optional
                });
                $('.select-one').on('ifChanged', function () {
                    // console.log($(this).parents('li').index());
                    var n = 0;
                    for (var i = 0; i < $('.data-list li').length; i++) {
                        if ($('.data-list li').eq(i).find('.select-one').prop('checked') == true) {
                            n++;
                        }
                    }
                    if (n == $('.data-list li').length) {
                        $('.select-all').iCheck('check');
                    } else {
                        $('.select-all').iCheck('uncheck');
                    }
                });
                // 全选
                $('.select-all').on('ifClicked', function () {
                    // console.log($(this).prop('checked'));
                    if (!$(this).prop('checked') == true) {
                        $('.select-one').iCheck('check');
                    } else {
                        $('.select-one').iCheck('uncheck');
                    }
                });
                if(pageHelper.totalRow != 0){
                    $("#pagination").show();
                    $("#PageCount").val(pageHelper.totalRow);
                }else{
                    $("#pagination").hide();
                }

            }
            $("#nowPageIndex").val(page);
        },
        error: function () {
            console.log("搜索会员错误")
        }
    });
    if(type == 1){
        loadpage();
    }
}

function getOrderType(){
    var upobj = $(".sort-up").attr("orderType");
    var downobj = $(".sort-down").attr("orderType");
    var json = {orderType:null,orderDesc:1};
    if(upobj != null && upobj != undefined){
        var orderType =  $(".sort-up").attr("orderType");
        json = {orderType:orderType,orderDesc:2};
    }
    if(downobj != null && downobj != undefined){
        var orderType =  $(".sort-down").attr("orderType");
        json = {orderType:orderType,orderDesc:1};
    }
    return json;
}

function toDetail(obj){
    var ctx = $("#ctx").val();
    var cardType = $(".cards li.current").attr("index");
    var memberId = $(obj).attr("memberId");
    var boxId = $(obj).attr("boxid");
    window.location.href = ctx + "/memberNew/memberView?memberId="+memberId + "&cardType=" + cardType + "&boxId="+boxId;
}

function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}

$("#exportExcel").click(function(){

    var memberCardIds = '';
    $.each($('input.select-one'),function(){
        if(this.checked){
            memberCardIds+= $(this).val()+",";
        }
    });
    if(memberCardIds == ''){
        alert("请选择会员");
        return false;
    }else{
        memberCardIds = memberCardIds.substring(0,memberCardIds.length-1);
    }
    if(confirm("是否导出"+memberCardIds.split(",").length+"条会员信息？")){
        var searchKey = $("#searchKey").val().trim();
        var boxId = $("#box").val();
        var cardType = $(".cards li.current").attr("index");
        var timeType = $(".card-status li.active").attr("timeType");
        var sex = $("#sex").val();
        var templateId = $("#template").val();
        var orderJson = getOrderType();

        var ctx = $("#ctx").val();
        var url = ctx+"/memberNew/exprotMemberList?searchKey="+searchKey + "&cardType="+cardType+
            "&boxId="+boxId+"&timeType="+timeType+"&sex="+sex+"&templateId="+templateId+"&orderType="+orderJson.orderType+
            "&orderDesc="+orderJson.orderDesc + "&memberCardIds="+memberCardIds;
        $('#exportform').attr('action', url);
        $('#exportform').submit();
    }
});


$("#importExcel").click(function(){
    var ctx = $("#ctx").val();
    popWin.showWin("400","200","请选择Excel",ctx+"/memberNew/uploadMember");
})

