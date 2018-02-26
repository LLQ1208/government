/**
 * Created by anran on 2017/12/13.
 */
$(function () {

    changeWin();

    $('.title-change').on('click', function () {
        if(!$(this).parents('.user').hasClass('select-user')){
            $(this).parents('.user').addClass('select-user');
            $('.title-list-li').slideDown();
        }else{
            $(this).parents('.user').removeClass('select-user');
            $('.title-list-li').slideUp();
        }
    });

    $('.one').show();
    $.each($('.listCommonTitle'),function(i,v){
        console.log(i,v);
        $(this).on('click',function(){
            if($(this).hasClass('active')){
                $(this).removeClass('active');
                $(this).siblings('.listCommonDetail').slideUp();
            }else{
                $('.listCommonTitle').removeClass('active');
                $('.listCommonDetail').slideUp();
                $(this).addClass('active');
                $(this).siblings('.listCommonDetail').slideDown();
            }
        })

    });
    //$.each($('.changeSrc'),function(){
    //    $(this).on('click',function(){
    //        $('.changeSrc').removeClass('active');
    //        $(this).addClass('active');
    //        $('iframe').attr('src','./pages/'+$(this).attr('data-value')+'.html');
    //        $('.leftBar').css({'height':$(document).height()})
    //    })
    //})

    $("p.changeSrc").on("click",function(){
       window.location.href = $(this).attr("href");
    });
    $(".choose li").on("click",function(){
        var ctx = $("#ctx").val();
        if($(this).index() == "1"){
            window.location.href= ctx+"/coachWod/coachWodPage";
        }
    });

    $("p.log-out").on("click",function(){
        localStorage.clear();
        sessionStorage.clear();
        var ctx = $("#ctx").val();
        window.location.href = ctx + "/logout"
    });

    $(".userHead").on("click",function(){
        var userType = $("#userType").val();
        var ctx = $("#ctx").val();
        if(userType == 1){
            window.location.href = ctx + "/personalCenter/boss";
        }else{
            window.location.href = ctx + "/personalCenter/personal"
        }
    });

    $(".photo-img").on("click",function(){
        var userType = $("#userType").val();
        var ctx = $("#ctx").val();
        if(userType == 1){
            window.location.href = ctx + "/personalCenter/boss";
        }else{
            window.location.href = ctx + "/personalCenter/personal"
        }
    });


});

function changeWin() {
    $(window).on('resize load', function () {
        $('.box').css({'height':$(window).height(), 'min-height': '768px'});
    })
}

function titleInit(){
    $.ajax({
        url:ctx + "/wod/toEditContent",
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:"wodContentId=" + wodContentId,
        dataType:'text',
        success:function(data){
            var wodContent = eval("("+data+")");
            if(wodContent.canEdit == '1'){
                return false;
            }
            assignContent(wodContent);
        },
        error:function(){
            return false;
        },
        complete:function(){
            $("#content"+wodContentId).children('.addContentA').slideDown();
            $("#content"+wodContentId).children('.metconA').slideUp();
        }
    });
}
