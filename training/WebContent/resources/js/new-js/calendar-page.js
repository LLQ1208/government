/**
 * Created by anran on 2017/11/26.
 */
$(function () {

    // 点击几周
    //$('.top-right-block ul li').on('click', function () {
    //    $(this).addClass('active').siblings('li').removeClass('active');
    //
    //    $('.calendar-box').calendarGetActive([
    //        {date: '2017-11-20', title: '1'},
    //        {date: '2017-11-21', title: '2'},
    //        {date: '2017-11-22', title: '3'},
    //        {date: '2017-11-23', title: '4!'},
    //        {date: '2017-11-24', title: '5!!'},
    //        {date: '2017-11-25', title: '6!!!'},
    //        {date: '2017-11-26', title: '7y!!!'},
    //        {date: '2017-11-20', title: '1'},
    //        {date: '2017-11-21', title: '2'},
    //        {date: '2017-11-22', title: '3'},
    //        {date: '2017-11-23', title: '4!'},
    //        {date: '2017-11-24', title: '5!!'}
    //    ]);
    //});

    // 下拉框
    $('.name-selectpicker').selectpicker();
    $('.type-selectpicker').selectpicker();

    // 初始化日历
    //$('.calendar-box').calendar({
    //    ele: '.demo-box', //依附
    //    title: ''
    //});

    //$('.calendar-box').calendarGetActive([
    //    {date: '2017-11-20', title: '1'},
    //    {date: '2017-11-21', title: '2'},
    //    {date: '2017-11-22', title: '3'},
    //    {date: '2017-11-23', title: '4!'},
    //    {date: '2017-11-24', title: '5!!'},
    //    {date: '2017-11-25', title: '6!!!'},
    //    {date: '2017-11-26', title: '7y!!!'}
    //]);

    // 回到今天
    //$('.back-today').on('click', function () {
    //    $('.calendar-box').calendarGetActive([
    //        {date: '2017-11-20', title: '1'},
    //        {date: '2017-11-21', title: '2'},
    //        {date: '2017-11-22', title: '3'},
    //        {date: '2017-11-23', title: '4!'},
    //        {date: '2017-11-24', title: '5!!'},
    //        {date: '2017-11-25', title: '6!!!'},
    //        {date: '2017-11-26', title: '7y!!!'}
    //    ]);
    //});

    // 上一周
    //$('.ht-rili-leftarr').on('click', function () {
    //    $('.calendar-box').calendarGetActive([
    //        {date: '2017-11-20', title: '1'},
    //        {date: '2017-11-21', title: '2'},
    //        {date: '2017-11-22', title: '3'},
    //        {date: '2017-11-23', title: '4!'},
    //        {date: '2017-11-24', title: '5!!'},
    //        {date: '2017-11-25', title: '6!!!'},
    //        {date: '2017-11-26', title: '7y!!!'}
    //    ]);
    //    magnifier();
    //});

    // 下一周
    //$('.ht-rili-rightarr').on('click', function () {
    //    $('.calendar-box').calendarGetActive([
    //        {date: '2017-11-20', title: '11'},
    //        {date: '2017-11-21', title: '22'},
    //        {date: '2017-11-22', title: '33'},
    //        {date: '2017-11-23', title: '44!'},
    //        {date: '2017-11-24', title: '55!!'},
    //        {date: '2017-11-25', title: '66!!!'},
    //        {date: '2017-11-26', title: '76y!!!'}
    //    ]);
    //    magnifier();
    //});
    magnifier();
    function magnifier() {
        $('.ht-rili-td').on('mouseenter', '.mini-title .view', function () {
            var n = $(this).parents('.ht-rili-td').index();
            // console.log(Math.floor(n / 7));
            if (n % 7 < 4) {
                $('.pop-box').css({
                    top: 75 + Math.floor(n / 7) * 330 + 'px',
                    left: 14 + (n%7+ 1) * 146 + 'px',
                    right: 'inherit'
                }).fadeIn();
            } else {
                $('.pop-box').css({
                    top: 75 + Math.floor(n / 7) * 330 + 'px',
                    right: 14 + (7 - n%7) * 146 + 'px',
                    left: 'inherit'
                }).fadeIn();
            }
        });
        $('.ht-rili-td').on('mouseleave','.mini-title .view', function () {
            $('.pop-box').fadeOut();
        });

        $('.ht-rili-td').on('click', '.mini-title .plus', function () {
            // 跳页
            $('#iframe',window.parent.document).attr('src','pages/add-training.html');
        })
    }
});