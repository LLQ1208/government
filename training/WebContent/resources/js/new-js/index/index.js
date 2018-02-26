
$(function () {
    changeWin();

    // $('.sure-btn').on('click',function () {
    //     $('.mask-box', window.parent.document).hide();
    // });

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
    $.each($('.changeSrc'),function(){
        $(this).on('click',function(){
            $('.changeSrc').removeClass('active');
            $(this).addClass('active');
            $('iframe').attr('src',$(this).attr('data-value'));
            $('.leftBar').css({'height':$(document).height()})
        })
    })
});

function changeWin() {
    $(window).on('resize load', function () {
        $('.box').css({'height':$(window).height(), 'min-height': '768px'});
    })
}
