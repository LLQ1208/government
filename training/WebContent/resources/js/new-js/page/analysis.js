/**
 * Created with webstorm.
 * Author: dameng
 * Date: 2017/12/11
 * Time: 21:29
 *
 */
$(function () {
    $('.textBtn').on('click',function(){
        $('.textBtn').removeClass('active');
        $(this).addClass('active');
        queryDataAnalysis();
    });
    $('.tableL').selectpicker();
    $('.tableR').selectpicker();
    $('.choose').selectpicker();

    $("#boxRight,#member").on("change",function(){
        queryDataAnalysis();
    });
    magnifier();
    $(".pop .close").on("click",function(){
        $(".pop").fadeOut();
    })

    $(".choose").on("change",function(){
        var ctx = $("#ctx").val();
        if($(this).val() == "2"){
            window.location.href= ctx+"/wod/queryWodList";
        }
    })
})

function queryDataAnalysis(){
    var boxId = $("#boxRight").val();
    var memberId = $("#member").val();
    var contetnType = $('div.wodLeft').find(".active").attr("contentType");
    var ctx = $("#ctx").val();
    $.ajax({
        url:ctx + "/coachBackAnalysis/queryMemberScore",
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:{boxId:boxId,memberId:memberId,contetnType:contetnType},
        dataType:'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function(data){
            var scoreList = data.list;
            var scoreHtml = "";
            if(null != scoreList && undefined != scoreList ){
                //scoreList = eval("("+scoreList+")");
                for(var i=0;i<scoreList.length; i++){
                    var scoreData = scoreList[i];
                    scoreHtml += '<div class="pCont clearfix">'+
                        '<p style="cursor: pointer;" class="fl record-name" contentid="'+scoreData.contentId+'">' + scoreData.contentName + '</p>'+
                        '<p class="fl">' + scoreData.score + '</p>' +
                        '<p class="fl">' + scoreData.time + '</p>'+
                        '</div>';
                }
            }
            $(".score-list").html(scoreHtml);
            magnifier();
        },
        error:function(){
            return false;
        }
    });


}

function magnifier(){
    $('.record-name').on('click',function () {
        var ctx = $("#ctx").val();
        var boxId = $("#boxRight").val();
        var memberId = $("#member").val();
        var contentId = $(this).attr("contentid");
        $(".weightPopContentId").val(contentId);
        $.ajax({
            url:ctx + "/coachBackAnalysis/queryScorePop",
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:{boxId:boxId,memberId:memberId,contentId:contentId},
            dataType:'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success:function(result){
                $('.pop-max').html(result.popTitle);
                var personalHtml = '';
                for (var i = 0; i < result.showArr.length; i++) {
                    var score = result.showArr[i];
                    personalHtml += '<li class="clearfix">'+
                    '<p class="date fl">' + score.time + '</p>'+
                    '<p class="result fl">' + score.score + '</p>' +
                    '<p class="remark fl">' +(score.remark != null && score.remark != undefined ? score.remark : '') + '</p>' +
                    '</li>';
                }
                $('#score-ul').html(personalHtml);
                chart(result.charList);

                var count = result.charList[0].data.length; // 记录条数
                $(".allNum").html(count + '条记录');
                $(".actionName").html(result.actionName);
                //分页
                $("#PageCount").val(count);
                $("#countindex").val(1);
                loadpage();
                $(".pop").fadeIn();
            },
            error:function(){
                return false;
            }
        });

    });
    $(".pop .close").on("click",function(){
        $(".pop").fadeOut();
    })
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
            if(type != 'init'){
                var ctx = $("#ctx").val();
                console.log(num);
                searchPage(num,0);
            }
        }
    });

    $(".pop .history-block .page-box .page").css('display','');
}


function searchPage(page,type){
    var ctx = $("#ctx").val();
    var boxId = $("#boxRight").val();
    var memberId = $("#member").val();
    var contentId = $(".weightPopContentId").val();
    var param = {boxId:boxId,memberId:memberId,contentId:contentId,page:page};
    var ctx = $("#ctx").val();
    $.ajax({
        url: ctx + "/coachBackAnalysis/queryScorePopPage",
        type: 'POST', //GET
        async: false,    //或false,是否异步
        data: param,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (data) {
            var personalHtml = '';
            for (var i = 0; i < data.list.length; i++) {
                var score = data.list[i];
                personalHtml += '<li class="clearfix">'+
                '<p class="date fl">' + score.time + '</p>'+
                '<p class="result fl">' + score.score + '</p>' +
                '<p class="remark fl">' +(score.remark != null && score.remark != undefined ? score.remark : '') + '</p>' +
                '</li>';
            }
            $('#score-ul').html(personalHtml);
        },
        error: function () {
            console.log("搜索会员错误")
        }
    });
    if(type == 1){
        loadpage();
    }
}

function chart(data) {
    Highcharts.chart('weight-chart', {
        title: {
            text: ''
        },
        credits: {
            enabled: false // 禁用版权信息
        },
        xAxis: {
            type: 'datetime',
            dateTimeLabelFormats: {
                day: '%Y-%m-%d %H:%M:%S',
            },
        },
        yAxis: {
            allowDecimals: false,
            title: {
                text: ''
            },
            labels: {
                format: '{value}'
            }
        },
        tooltip: {
            formatter: function () {
                return Highcharts.dateFormat('%Y-%m-%d', this.x) + '<br/>'+ this.series.name +':' + this.y ;
            }
            // headerFormat: ' Highcharts.dateFormat(\'%Y-%m-%d\', {point.key})+<br><b>{series.name}</b><br>',
        },
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
