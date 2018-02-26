/**
 * Created by anran on 2018/1/11.
 */
$('.add-btn').on('click', function () {
    var ctx = $("#ctx").val();
    window.location.href = ctx +  '/memberNew/toMemberTemplateAdd';
});
$(function () {
    loadpage();
    changeWin();
    $(".template_name").on("click",function(){
        var ctx = $("#ctx").val();
        var templateId = $(this).attr("templateId");
        window.location.href = ctx + '/memberNew/toMemberTemplateEdit?id='+templateId;
    });
})

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
                $.ajax({
                    url: ctx + "/memberNew/queryTemplateByPage",
                    type: 'POST', //GET
                    async: true,    //或false,是否异步
                    data: {pageIndex:num},
                    dataType: 'json',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (data) {
                        console.log(data);
                        var list = data.list;
                        var html = "";
                        for(var i=0;i<list.length;i++){
                            var template = list[i];
                            html += '<li class="clearfix">' +
                            '<p class="name template_name fl"  templateId="' + template.id + '">' + template.templateName + '</p>'+
                            '<p class="name fl">';
                            if(template.boutCardType == '2'){
                                html += '私教卡';
                            }else {
                                html += '团课卡';
                            }
                            html += '</p>'+
                            '<p class="price fl">' + template.money + '</p>'+
                            '<p class="classes fl">' + template.courseTypeIds + '</p>'+
                            '</li>';
                        }
                        $(".temp-list").html(html);

                    },
                    error: function () {
                        console.log("分页查询错误")
                    }
                });
            }
        }
    });
}

function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}