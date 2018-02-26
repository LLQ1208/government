/**
 * Created by anran on 2017/12/21.
 */
var type = 2;
Highcharts.setOptions({ global: { useUTC: false } });
$(function () {
    changeWin();
    $('.statistic-classify li').on('click', function () {
        $(this).addClass('active').siblings('li').removeClass('active');
        var titleStr = $(this).html();
        if(titleStr.substring(0,4) == '上课统计'){
            $("#courseType option[value='2']").remove();
            $('#courseType').selectpicker('refresh');
            type = 2;
        }
        if(titleStr.substring(0,4) == '出勤统计'){
            $('#courseType').append("<option value='2'>私教课</option>");
            $('#courseType').selectpicker('refresh');
            type = 1;
        }
        getSource();
    });

    $('#leftBtn').on('click', function () {
        $('#lastOrNextWeek').val(-1);
        getSource();
    });
    $('#rightBtn').on('click', function () {
        $('#lastOrNextWeek').val(1);
        getSource();
    });

    $('.position-select').selectpicker();

    $('.form_datetime_start,.form_datetime_end').datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        minView: 2,
        initialDate: new Date(),
        autoclose: true
    });
    var oDate = new Date();
    $('.form_datetime_start').val(oDate.getFullYear() + '-' + toDou(oDate.getMonth() + 1) + '-' + toDou(oDate.getDate()));
    var iDate = new Date();
    var ss = iDate.getTime()+6*60*60*24*1000;
    iDate.setTime(ss);
    // console.log(iDate.getFullYear(),iDate.getMonth()+1, iDate.getDate())
    $('.form_datetime_end').val(iDate.getFullYear() + '-' + toDou(iDate.getMonth() + 1) + '-' + toDou(iDate.getDate()));
    $('.form_datetime_start').on('changeDate', function (e) {
        console.log(e.target.value)
        var customBeginTime = $('.form_datetime_start').val();
        var customEndTime = $('.form_datetime_end').val();
        if(customEndTime<customBeginTime){
            alert('结束时间不能小于开始时间');
        }else{
            getSource();
        }
    });
    $('.form_datetime_end').on('changeDate', function (e) {
        console.log(e.target.value)
        var customBeginTime = $('.form_datetime_start').val();
        var customEndTime = $('.form_datetime_end').val();
        if(customEndTime<customBeginTime){
            alert('结束时间不能小于开始时间');
        }else{
            getSource();
        }
    });
    $('.filter-box ul li').on('click', function () {
        $(this).addClass('active').siblings('li').removeClass('active');
        if($(this).index()<=2){
            $('.date-box').show();
            $('.custom-date').hide();
        }else{
            $('.custom-date').show();
            $('.date-box').hide();
        }
        $('#searchType').val($(this).index());
        $('#beginTime').val("");
        $('#lastOrNextWeek').val(0);
        getSource();
    });
    getSource();
});
function toDou(n) {
    return n > 9 ? n : '0' + n;
}

function getSource() {
    if (type == 1) {
        coachAttendanceAnalysisList();
    } else {
        coachTakeClassesAnalysisList();
    }
}

//教练上课统计
    function coachTakeClassesAnalysisList() {
        var ctx = $("#ctx").val();
        var beginTime = $('#beginTime').val();
        var customBeginTime = $('.form_datetime_start').val();
        var customEndTime = $('.form_datetime_end').val();
        var lastOrNextWeek = $('#lastOrNextWeek').val();
        var searchType = $('#searchType').val();
        var boxId = $("#boxId").val();
        var courseType = $("#courseType").val();
        $.ajax({
            url: ctx + "/analysis/getCoachTakeClassesAnalysisList",
            type: 'POST', //POST
            async: true,    //或false,是否异步
            data: "boxId=" + boxId + "&courseType=" + courseType + "&beginTime=" + beginTime + "&lastOrNextWeek=" + lastOrNextWeek + "&searchType=" + searchType + "&customBeginTime=" + customBeginTime + "&customEndTime=" + customEndTime,
            dataType: 'json',
            success: function (data) {
                console.log("成功");
                chart(data.serieses,1);
                $("#titleTime").html(data.timeTitle);
                $("#beginTime").val(data.beginDate);
            },
            error: function () {
                alert("错误");
            }
        });
    }

//出勤
    function coachAttendanceAnalysisList() {
        var ctx = $("#ctx").val();
        var beginTime = $('#beginTime').val();
        var customBeginTime = $('.form_datetime_start').val();
        var customEndTime = $('.form_datetime_end').val();
        var lastOrNextWeek = $('#lastOrNextWeek').val();
        var searchType = $('#searchType').val();
        var boxId = $("#boxId").val();
        var courseType = $("#courseType").val();
        $.ajax({
            url: ctx + "/analysis/getCoachAttendanceAnalysisList",
            type: 'POST', //POST
            async: true,    //或false,是否异步
            data: "boxId=" + boxId + "&courseType=" + courseType + "&beginTime=" + beginTime + "&lastOrNextWeek=" + lastOrNextWeek + "&searchType=" + searchType + "&customBeginTime=" + customBeginTime + "&customEndTime=" + customEndTime,
            dataType: 'json',
            success: function (data) {
                console.log("成功");
                chart(data.serieses,2);
                $("#titleTime").html(data.timeTitle);
                $("#beginTime").val(data.beginDate);
            },
            error: function () {
                alert("错误");
            }
        });
    }

    function chart(data,type) {
    var typeName = type == 1 ? "人" :"节";
        Highcharts.chart('container', {
            title: {
                text: ''
            },
            credits: {
                enabled: false // 禁用版权信息
            },
            xAxis: {
                // type: 'category',
                type: 'datetime',
                dateTimeLabelFormats: {
                    day: '%Y-%m-%d',
                },
            },
            yAxis: {
                allowDecimals: false,
                title: {
                    text: ''
                },
                labels: {
                    format: '{value} '+typeName
                }
            },
            tooltip: {
                formatter: function () {
                    return Highcharts.dateFormat('%Y-%m-%d', this.x) + '<br/>'+ this.series.name +':' + this.y + ' 人';
                }
            },
            // tooltip: {
            //     valueSuffix: ' '+typeName,
            //     headerFormat: '{point.key}<br><b>{series.name}</b><br>',
            // },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top'
            },
            series: data,
            responsive: {
                rules: [{
                    condition: {
                        maxWidth: 500
                    },
                    chartOptions: {
                        legend: {
                            layout: 'horizontal',
                            align: 'center',
                            verticalAlign: 'bottom'
                        }
                    }
                }]
            }
        });
    }
function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}
