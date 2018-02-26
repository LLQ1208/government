/**
 * Created by anran on 2017/11/23.
 */
(function () {
    var calendarDate = {};
    var riliHtml = '';
    calendarDate.today = new Date();
    calendarDate.curyear = calendarDate.today.getFullYear();//当前年
    calendarDate.year = calendarDate.today.getFullYear();//当前年
    calendarDate.curmonth = calendarDate.today.getMonth() + 1;//当前月
    calendarDate.month = calendarDate.today.getMonth() + 1;//当前月
    calendarDate.date = calendarDate.today.getDate();//当前日
    calendarDate.day = calendarDate.today.getDay() == 0 ? 7 : calendarDate.today.getDay();//当前周几
    // console.log(time(calendarDate.day-1));
    // calendarDate.startDate = calendarDate.year + '-' + calendarDate.month + '-' + (calendarDate.date - calendarDate.day + 1);
    calendarDate.startDate = time(calendarDate.year,calendarDate.month,calendarDate.date,1-calendarDate.day);

    // 时间戳
    function time(year,month,date,i) {
        // console.log(year,month,date);
        var iDate = new Date();
        var oDate = new Date().setFullYear(year, month-1,date);
        // console.log(oDate)
        iDate.setTime((oDate+i*3600*24*1000));
        return iDate.getFullYear()+'-'+(iDate.getMonth()+1)+'-'+iDate.getDate();
    }
    //绘制
    function getIndexDay() {
        isLeapYear();
        getDays();
        riliHtml = '';
        //本月一号周几
        calendarDate.monthStart = new Date(calendarDate.year + '/' + calendarDate.month + '/1').getDay();
        //上个月所占空格数
        console.log(calendarDate);
        if (calendarDate.monthStart == 0) {//独占一行
            calendarDate.monthStart = 7;
        }

        // 前月数据
        if (calendarDate.startDate.split('-')[1] < calendarDate.month) {
            for (var k = calendarDate.monthStart - 1; k > 0; k--) {
                var dataDateStr = calendarDate.lastYear + '-' + calendarDate.lastMonth + '-' + (calendarDate.lastDays - k + 1);
                riliHtml += '<div class="ht-rili-td" data-date="' + dataDateStr + '"><span class="ht-rili-day">' + calendarDate.startDate.split('-')[1]+'/'+toDou(calendarDate.lastDays - k + 1) + '</span>';
                riliHtml += '</div>';
            }
        }else{

        }

        // 当前月数据
        var dataStr = '';
        if(calendarDate.startDate.split('-')[1] < calendarDate.month){
            dataStr = 1;
        }else{
            dataStr = calendarDate.startDate.split('-')[2];
        }
        for (var i = dataStr; i <= calendarDate.days; i++) {
            var dataDateStr = calendarDate.year + '-' + calendarDate.month + '-' + i;
            // var n = (i-parseInt(calendarDate.startDate.split('-')[2]));
            // console.log(n)
            if (dataDateStr == calendarDate.curyear + '-' + calendarDate.curmonth + '-' + calendarDate.date) {
                riliHtml += '<div class="ht-rili-td today" data-date="' + dataDateStr + '"><span class="ht-rili-day">' +calendarDate.curmonth+'/'+ toDou(i) + '</span>';
            } else {
                riliHtml += '<div class="ht-rili-td" data-date="' + dataDateStr + '"><span class="ht-rili-day">' +calendarDate.month+'/'+ toDou(i) + '</span>';
            }
            riliHtml += '</div>';

        }
        // 下月数据
        var surplusNum = 28 - (calendarDate.days - calendarDate.startDate.split('-')[2] + 1);
        calendarDate.surplusNum = surplusNum;
        for (var j = 1; j <= surplusNum; j++) {
            var dataDateStr = calendarDate.nextYear + '-' + calendarDate.nextMonth + '-' + j;
            if (dataDateStr == calendarDate.curyear + '-' + calendarDate.curmonth + '-' + calendarDate.date) {
                riliHtml += '<div class="ht-rili-td today" data-date="' + dataDateStr + '"><span class="ht-rili-day">' + toDou(j) + '</span>';
            }else{
                riliHtml += '<div class="ht-rili-td" data-date="' + dataDateStr + '"><span class="ht-rili-day">' + toDou(j) + '</span>';
            }
            riliHtml += '</div>';
        }

        $('.ht-rili-body').append(riliHtml);
        var arr = ['一','二','三','四','五','六','日'];
        for(var i = 0; i<$('.ht-rili-td').length;i++){
            $('.ht-rili-td').eq(i).append('<p>周'+arr[i%7]+'</p>')
        }
    }

    //是否是闰年
    function isLeapYear() {
        if ((calendarDate.year % 4 == 0) && (calendarDate.year % 100 != 0 || calendarDate.year % 400 == 0)) {
            calendarDate.isLeapYear = true;
        } else {
            calendarDate.isLeapYear = false;
        }
    }

    //日期点击事件
    function dateClick(obj) {
        $(obj).siblings().each(function () {
            $(this).removeClass('ht-rili-td-active');
        });
        $(obj).addClass('ht-rili-td-active');
    }

    //获取上个月份，本月，下个月份信息
    function getDays() {
        //上月天数
        if (parseInt(calendarDate.month) == 1) {
            calendarDate.lastDays = new Date(calendarDate.year - 1, 12, 0).getDate();
            calendarDate.lastMonth = new Date(calendarDate.year - 1, 12, 0).getMonth() + 1;
            calendarDate.lastYear = new Date(calendarDate.year - 1, 12, 0).getFullYear();
        } else {
            calendarDate.lastDays = new Date(calendarDate.year, calendarDate.month - 1, 0).getDate();
            calendarDate.lastMonth = new Date(calendarDate.year, calendarDate.month - 1, 0).getMonth() + 1;
            calendarDate.lastYear = new Date(calendarDate.year, calendarDate.month - 1, 0).getFullYear();
        }
        //下个月天数
        if (parseInt(calendarDate.month) == 12) {
            calendarDate.nextDays = new Date(calendarDate.year + 1, 1, 0).getDate();
            calendarDate.nextMonth = new Date(calendarDate.year + 1, 1, 0).getMonth() + 1;
            calendarDate.nextYear = new Date(calendarDate.year + 1, 1, 0).getFullYear();
        } else {
            calendarDate.nextDays = new Date(calendarDate.year, calendarDate.month + 1, 0).getDate();
            calendarDate.nextMonth = new Date(calendarDate.year, calendarDate.month + 1, 0).getMonth() + 1;
            calendarDate.nextYear = new Date(calendarDate.year, calendarDate.month + 1, 0).getFullYear();
        }
        //本月天数
        calendarDate.days = new Date(calendarDate.year, calendarDate.month, 0).getDate();
    }

    //检测时间是否一致
    function checkDate(dateStr1, dateStr2) {
        var date1 = dateStr1.split('-'); //[0]year,[1]month,[2]date;
        var date2 = dateStr2.split('-'); //[0]year,[1]month,[2]date;
        if (date1[1] < 10 && date1[1].length < 2) {
            date1[1] = '0' + date1[1];
        }
        if (date1[2] < 10 && date1[2].length < 2) {
            date1[2] = '0' + date1[2];
        }
        if (date2[1] < 10 && date2[1].length < 2) {
            date2[1] = '0' + date2[1];
        }
        if (date2[2] < 10 && date2[2].length < 2) {
            date2[2] = '0' + date2[2];
        }
        date1 = date1.join('-');
        date2 = date2.join('-');
        return date1 == date2;
    }


    function toDou(n) {
        return n > 9 ? n : '0' + n;
    }

    function setTitle() {
        var lastDate = time(calendarDate.startDate.split('-')[0],calendarDate.startDate.split('-')[1],calendarDate.startDate.split('-')[2],6);
        console.log(calendarDate.startDate)
        console.log(lastDate)
       var html = '<strong>'+toDou(calendarDate.startDate.split('-')[1])+'月</strong>'+
            '<span>'+toDou(calendarDate.startDate.split('-')[2])+'</span>'+
            '<span>-</span>'+
            '<strong>'+toDou(lastDate.split('-')[1])+'月</strong>'+
            '<span>'+toDou(lastDate.split('-')[2])+'</span>';
        $('.ht-rili-date').html(html);
        // $('.ht-rili-date').html(toDou(calendarDate.startDate.split('-')[1]) + ' ' + toDou(calendarDate.startDate.split('-')[2])+'-'+ toDou(calendarDate.month) + '.' + toDou(calendarDate.days));
    }

    $.fn.extend({
        calendar: function (opt) {

            calendarDate.opt = opt;

            //加载容器
            calendarDate.container = '<div class="ht-rili-querybox"></div><div class="ht-rili-body"><!--<div class="ht-rili-td"><span class="ht-rili-day">1</span><span class="ht-rili-money">&yen;100</span></div>--></div></div>'
            $(opt.ele).append(calendarDate.container);
            getIndexDay();

            setTitle();

            for(var i = 0;i<7;i++){
                if(calendarDate.day == (i+1)){
                    $('.ht-rili-th').eq(i).addClass('today').siblings('.ht-rili-th').removeClass('today');
                }
            }
            $('.ht-rili-leftarr').on('click', function () {
                $('.ht-rili-body').html('');

                if ((calendarDate.startDate.split('-')[2] - 7 <= 0)||(calendarDate.startDate.split('-')[1]<calendarDate.month)) {

                    calendarDate.month -= 1;

                    if (calendarDate.month == 0) {
                        calendarDate.year -= 1;
                        calendarDate.month = 12;
                    }
                    // calendarDate.startDate = calendarDate.year + '-' + calendarDate.month + '-' + (calendarDate.lastDays - (calendarDate.monthStart - 1) + 1);
                    calendarDate.startDate = time(calendarDate.startDate.split('-')[0],calendarDate.startDate.split('-')[1],calendarDate.startDate.split('-')[2],-7);
                } else {
                    // calendarDate.startDate = calendarDate.year + '-' + calendarDate.month + '-' + (calendarDate.startDate.split('-')[2] - 7);
                    calendarDate.startDate = time(calendarDate.startDate.split('-')[0],calendarDate.startDate.split('-')[1],calendarDate.startDate.split('-')[2],-7);
                }
                getIndexDay();

                setTitle();
            });
            $('.ht-rili-rightarr').on('click', function () {

                $('.ht-rili-body').html('');

                if (parseInt(calendarDate.startDate.split('-')[2]) + 7 > calendarDate.days) {
                    console.log(calendarDate.startDate)
                    if(calendarDate.startDate.split('-')[1]<calendarDate.month){

                    }else{
                        calendarDate.month += 1;
                    }
                    if (calendarDate.month == 13) {
                        calendarDate.year += 1;
                        calendarDate.month = 1;
                    }
                    console.log(calendarDate.month)
                    // calendarDate.startDate = calendarDate.year + '-' + calendarDate.month + '-' + (parseInt(calendarDate.startDate.split('-')[2]) + 7 - calendarDate.days);
                    calendarDate.startDate = time(calendarDate.startDate.split('-')[0],calendarDate.startDate.split('-')[1],calendarDate.startDate.split('-')[2],7);
                } else {
                    // calendarDate.startDate = calendarDate.year + '-' + calendarDate.month + '-' + (parseInt(calendarDate.startDate.split('-')[2]) + 7);
                    calendarDate.startDate = time(calendarDate.startDate.split('-')[0],calendarDate.startDate.split('-')[1],calendarDate.startDate.split('-')[2],7);

                }
                getIndexDay();

                setTitle();
            });
            $('.back-today').on('click', function () {
                $('.ht-rili-body').html('');
                calendarDate.month = calendarDate.curmonth;
                calendarDate.year = calendarDate.curyear;
                // calendarDate.startDate = calendarDate.curyear + '-' + calendarDate.curmonth + '-' + (calendarDate.date - calendarDate.day + 1);
                calendarDate.startDate = time(calendarDate.curyear,calendarDate.curmonth,calendarDate.date,1-calendarDate.day);
                getIndexDay();

                setTitle();
            })
        }

    });
})(jQuery)