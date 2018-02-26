/**
 * Created by anran on 2018/1/13.
 */
$(function () {
    changeWin();
    $('.male,.female').iCheck({
        checkboxClass: 'icheckbox_flat-red',
        radioClass: 'iradio_flat-red'
    });
    $('.form_datetime').datetimepicker({
        format: 'yyyy/mm/dd',
        language: 'zh-CN',
        minView: 2,
        initialDate: new Date(),
        autoclose: true
    });
    $('.cardsopt').on('click', function () {
        $('.cardsopt').removeClass('active');
        $(this).addClass('active');
        //$('.recodeopt').eq(0).addClass('active').siblings('.recodeopt').removeClass('active');
        var detail = $(".recode").find(".active").attr("index");
        if(detail == "0"){
            $('.detail').eq(0).show();
            $('.detail').eq(1).hide();
        }else{
            $('.detail').eq(1).show();
            $('.detail').eq(0).hide();
        }
        cardCardSearch();
    });
    $("#sex").selectpicker();
    $('.recodeopt').on('click', function () {
        $('.recodeopt').removeClass('active');
        $(this).addClass('active');
        if($(this).index() == 0){
            $('.detail').eq(0).show();
            $('.detail').eq(1).hide();
        }else{
            $('.detail').eq(1).show();
            $('.detail').eq(0).hide();
        }
       /* $('.detail').eq(1).show();
        $('.detail').eq(0).hide();*/
        cardCardSearch();
    });
    $('.show1').hover(function () {
        $(this).children('.commondet').show()
    }, function () {
        $(this).children('.commondet').hide()
    });

    // 点击编辑头像
    $('.changpic').on('click', function () {
        $('.left input').show()
    });
    //停开卡
    $("span[name='statusBut']").on("click",function(){
        var cardId = $("#cardId").val();
        var changeStatus = -1;
        var status = $(this).attr("status");
        if(status == "0"){
            $("span[name='statusBut']").html("开卡");
            changeStatus = 1;
            $(this).attr("status","1");
        }else{
            $("span[name='statusBut']").html("停卡");
            $(this).attr("status","0");
            changeStatus = 0;
        }
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/memberNew/openOrStopCard",
            type: 'POST', //GET
            async: false,    //或false,是否异步
            data: {cardId:cardId,status:changeStatus},
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {
                cardCardSearch();
            },
            error: function () {
                console.log("错误")
            }
        });
    });
    $('.rewrite').on('click', function () {
        $(this).siblings('.edit-info').show();
        $(this).hide();
        $(this).prev('span').hide();
        var id = $(this).siblings('.edit-info').attr("id");
        if(id != 'sexSel'){
            var val = $(this).siblings('.edit-info').val();
            $(this).siblings('.edit-info').val('').focus().val(val);
        }else{
            var sexVal = $("#sexInput").val();
            if(sexVal == "0"){
                $("input.male").prop("checked", "checked")
            }else if(sexVal == "1"){
                $("input.female").prop("checked", "checked")
            }

            $('input:radio[name="sex"]:checked').focus();
            $("#sexSel").show();
        }
    });
    $('input:radio[name="sex"]').on("blur",function(){
        var sex = $('input:radio[name="sex"]:checked').val();
        var showValue = sex == "0" ? "男":"女";
        $(this).parents(".sex").find('span.showValue').html(showValue);
        $(this).parents(".sex").find('span').show();
        $("#sexSel").hide();
    });

    $('input.male[name="sex"]').on('ifChanged', function () {
        var sex = 0;
        if($(this).prop('checked')){
            sex =0;
        }else{
            sex = 1;
        }
        var showValue = sex == "0" ? "男":"女";
        $(this).parents(".sex").find('span.showValue').html(showValue);
        $(this).parents(".sex").find('span').show();
        $("#sexSel").hide();

        var memberId = $("#memberId").val();
        var cardId = $("#cardId").val();
        var ctx = $("#ctx").val();
        var json = {type:2,sex:sex,memberId:memberId};
        $.ajax({
            url: ctx + "/memberNew/editMember",
            type: 'POST', //GET
            async: false,    //或false,是否异步
            data: json,
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {

            },
            error: function () {
                console.log("错误")
            }
        });
    });

    $('.edit-info').on('blur', function () {
        var id = $(this).attr("id");
        var memberId = $("#memberId").val();
        var cardId = $("#cardId").val();
        var ctx = $("#ctx").val();
        if(id != 'cardStartTime' && id != 'birthday' && id != "sexSel"){
            var value = $(this).val().trim();
            //校验组装参数
            if(value == ""){
                $(this).focus();
                return false;
            }
            var json = {};
            if(id == "memberName"){
                json = {type:1,mamberName:value,memberId:memberId};
            }else if(id == "cardStartTime"){
                json = {type:3,cardStartTime:value,memberId:memberId,cardId:cardId};
            }else if(id == "birthday"){
                json = {type:4,birthday:value,memberId:memberId};
            }else if(id == "emial"){
                if(!isEmail(value)){
                    $(this).focus();
                    return false;
                }
                json = {type:5,emial:value,memberId:memberId};
            }
            $(this).siblings('span.showValue').html(value);
            $(this).siblings('span').show();
            $(this).hide();

            $.ajax({
                url: ctx + "/memberNew/editMember",
                type: 'POST', //GET
                async: false,    //或false,是否异步
                data: json,
                dataType: 'json',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {

                },
                error: function () {
                    console.log("错误")
                }
            });
        }
    });
    $('.datetimepicker-dropdown-bottom-right').on('click', function () {
        var memberId = $("#memberId").val();
        var cardId = $("#cardId").val();
        var ctx = $("#ctx").val();
        var cardStartTime = $("#cardStartTime").val();
        $("#cardStartTime").siblings('span.showValue').html(cardStartTime);
        var birthday = $("#birthday").val();
        $("#birthday").siblings('span.showValue').html(birthday);
        var canEdit = $("#cardTimeCanEdit").val();
        if(canEdit == 1){
            $("#cardStartTime").siblings('span').show();
        }
        $("#birthday").siblings('span').show();
        $("#cardStartTime").hide();
        $("#birthday").hide();
        var json = {type:7,birthday:birthday,cardId:cardId,memberId:memberId,cardStartTime:cardStartTime};
        $.ajax({
            url: ctx + "/memberNew/editMember",
            type: 'POST', //GET
            async: false,    //或false,是否异步
            data: json,
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {

            },
            error: function () {
                console.log("错误")
            }
        });
    });

    $("#comment").on("blur",function(){
        var memberId = $("#memberId").val();
        var ctx = $("#ctx").val();
        var value = $(this).val().trim();

        var json = {type:6,comment:value,memberId:memberId};
        $.ajax({
            url: ctx + "/memberNew/editMember",
            type: 'POST', //GET
            async: false,    //或false,是否异步
            data: json,
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {

            },
            error: function () {
                console.log("错误")
            }
        });
    })



    // 续卡
    $('#toExtendA').on('click', function () {
        var cardId = $("#cardId").val();
        var ctx = $("#ctx").val();
        var memberId = $("#memberId").val();
        //$('#iframe',window.parent.document).attr('src','pages/member-add.html');
        window.location.href = ctx + "/memberNew/memberExtend?cardId="+cardId + "&memberId="+memberId;
    });

});



function isEmail(email){
    return email.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);

}


function cardCardSearch(){
    var memberId = $("#memberId").val();
    var cardType = $(".cards").find(".active").attr("index");
    var detail = $(".recode").find(".active").attr("index");
    var boxId = $("#boxId").val();
    var ctx = $("#ctx").val();
    if(detail == "0"){
        $.ajax({
            url: ctx + "/memberNew/memberCardSearch",
            type: 'POST', //GET
            async: false,    //或false,是否异步
            data: {memberId:memberId,cardType:cardType,detail:detail,boxId:boxId},
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {
                var html = '<div class="title">'+
                    '<span>会员卡名称</span>'+
                    '<span>费用</span>'+
                    '<span>开始时间</span>'+
                    '<span>到期时间</span>'+
                    '<span>状态</span>'+
                    '<span>处理人</span>'+
                    '<span>可预约课程</span>'+
                    '<span>事件</span>'+
                    '</div>';
                if(data.code == "1"){
                    if(data.cardList != null && data.cardList.length > 0){
                        for(var i=0;i<data.cardList.length;i++){
                            var card = data.cardList[i];
                            html += '<div class="common" style="position: relative;">';
                            if(card.status == 1){
                                html += '<strong style="position:  absolute;width: 20px;height: 20px;background:  #ff0000;color: #fff;border-radius:  50%;text-align:  center;line-height:  20px;left: 96px;top: 6px;">停</strong>';
                            }
                           html +=  '<span>'+card.cardTemplateName+'</span>'+
                            '<span>¥'+card.generalMoney+'</span>'+
                            '<span>'+card.generalBeginTime+'</span>'+
                            '<span>'+card.cardEndTime+'</span>'+
                            '<span>'+card.cardStatus+'</span>'+
                            '<span>'+card.dealName+'</span>'+
                            '<span class="show1">查看'+
                            '<div class="commondet" style="box-sizing:  border-box;padding: 6px;color: #666;">'+
                            ' <p style="line-height: 26px;">'+card.canOrderCourseType+'</p>'+
                            '</div>'+
                            '</span>'+
                            '<span class="show1">查看'+
                            '<div class="commondet">';
                            var eventList = card.eventList;
                            if(null != eventList && eventList != undefined && eventList.length > 0){
                                for(var j=0;j<eventList.length;j++){
                                    html += '<span>'+eventList[j]+'</span>';
                                }
                            }else{
                                html += '<span>无</span>';
                            }
                            html += '</div>'+
                            '</span>'+
                            '</div>';
                        }
                    }

                    var cardId = data.cardId;
                    if(cardId != null && cardId != undefined){
                        $("#cardId").val(cardId);
                    }

                    if(data.thisCardVality == "1"){
                        if(data.memberStatus== "0"){
                            $("span[name='statusBut']").attr("0");
                            $("span[name='statusBut']").html("停卡");
                            $(".operator-card").show();
                        }else{
                            $("span[name='statusBut']").attr("1");
                            $("span[name='statusBut']").html("开卡");
                            $(".operator-card").show();
                        }
                    }
                }else{

                }

                $(".card-list").html(html);
                $('.show1').hover(function () {
                    $(this).children('.commondet').show()
                }, function () {
                    $(this).children('.commondet').hide()
                });

            },
            error: function () {
                console.log("错误")
            }
        });
    }else if(detail == "1"){
        $.ajax({
            url: ctx + "/memberNew/memberOrderSearch",
            type: 'POST', //GET
            async: false,    //或false,是否异步
            data: {memberId:memberId,cardType:cardType,detail:detail},
            dataType: 'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {
                var html = '<div class="title">' +
                        '<span>日期</span>'+
                        '<span>训练馆</span>'+
                        '<span style="width: 200px;">课程类型</span>'+
                        '<span style="width: 200px;">详情</span>'+
                        ' <span>状态</span>'+
                        '</div>';
                if(null != data && undefined != data && data.list.length > 0){
                    for(var i=0;i<data.list.length;i++){
                        var order = data.list[i];
                        html += '<div class="common">'+
                            '<span>'+order.time+'</span>'+
                            '<span>'+order.boxName+'</span>'+
                            '<span style="width: 200px;">'+order.courseTypeName+'</span>'+
                            '<span style="width: 200px;">'+order.detail+'</span>'+
                            '<span>'+order.status+'</span>'+
                            '</div>';
                    }
                }

                $(".order-list").html(html);

            },
            error: function () {
                console.log("错误")
            }
        });
    }
}

function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}

function LimitTextArea(field) {
    var maxlimit = 200;
    if (field.value.length > maxlimit) {
        field.value = field.value.substring(0, maxlimit);
        alert("字数不得多于4000！");
    }
}