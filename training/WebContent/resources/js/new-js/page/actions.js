/**
 * Created by anran on 2017/11/30.
 */
var searchType = 3;
var contentType = 6;
var keyword = "";
var pageIndex = 1;
$(function () {
    changeWin();
    var name = ''; //当前所属列表
    $("#contentList").hide();
    $('.section-type li').on('click', function () {
        $(this).addClass('active').siblings('li').removeClass('active');
        $('.add-btn').html($(this).text());
        $('.sections,.warm-up,.metcon').fadeOut();
    });
    $('.section-kind li').on('click', function () {
        $(this).addClass('active').siblings('li').removeClass('active')
    });
    $("#keyword").keyup(function(event){
        if(event.keyCode ==13){
            $("#searchBut").trigger("click");
        }
    });
    $('.select-all,.select-one').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        increaseArea: '20%' // optional
    });
    // section 查询
    $("#sectionSearchType li").click(function () {//点击事件
        //测试直接获取值
        var type = $(this).text();
        if (type == "自定义") {
            searchType = 3;
        }
        if (type == "系统") {
            searchType = 2;
        }
        if (type == "全部") {
            searchType = 1;
        }
        if (contentType == 6) {
            sectionSearch(keyword,pageIndex);
        } else {
            contentSearch(keyword,pageIndex);
        }
    });
    //content 查询
    $("#contentSearchType li").click(function () {//点击事件
        //测试直接获取值
        $("#contentList").show();
        $("#sectionList").hide();
        $("#keyword").val("");
        keyword = "";
        var type = $(this).text();
        if(type =="Warm-Ups"){
            $("#keyword").attr('placeholder','搜索输入 Warm-Ups');
            $("#warmUpTitle").html("Warm-Ups");
            contentType = 1;
        }
        if(type =="Gymnastics"){
            $("#keyword").attr('placeholder','搜索输入 Gymnastics');
            $("#warmUpTitle").html("Gymnastics");
            contentType = 2;
        }
        if(type =="Weightlifting"){
            $("#keyword").attr('placeholder','搜索输入 Weightlifting');
            $("#warmUpTitle").html("Weightlifting");
            contentType = 3;
        }
        if(type =="Metcons"){
            $("#keyword").attr('placeholder','搜索输入 Metcons');
            $("#metconTitle").html("Metcons");
            contentType = 4;
        }
        if(type =="Popular Wod"){
            $("#keyword").attr('placeholder','搜索输入 Popular Wod');
            $("#metconTitle").html("Popular Wod");
            contentType = 5;
        }
        if(type =="Sections"){
            $("#keyword").attr('placeholder','搜索输入 Sections');
            contentType = 6;
            $("#contentList").hide();
            $("#sectionList").show();
            sectionSearch(keyword,pageIndex);
        }else{
            contentSearch(keyword,pageIndex);
        }
        $("#contentType").val(contentType);
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
        for (var i = 0; i < $('.list-box li').length; i++) {
            if ($('.list-box li').eq(i).find('.select-one').prop('checked') == true) {
                n++;
            }
        }
        if (n == $('.list-box li').length) {
            $('.select-all').iCheck('check');
        } else {
            $('.select-all').iCheck('uncheck');
        }
    });

    // 点击按钮添加内容
    $('.add-btn').on('click', function () {
        name = $(this).html();
        console.log(name);
        $(".delete-btn").hide();
        $("#eachRoundRecord").hide();
        $(".eachRoundRecordType").hide();
        if (name == 'Sections') {
            $('.sections').fadeIn();
            $("#updateSe").hide();
            $("#saveSe").show();
        } else if (name == 'Warm-Ups' || name == 'Gymnastics' || name == 'Weightlifting') {
            $('.warm-up').fadeIn();
            $("#saveContentFrist").show();
            $("#updateContentFrist").hide();
            $("#contentName").val("");
            $("#contentDescription").val("");
            $("#contentName").removeAttr("readonly");
            $("#contentDescription").removeAttr("readonly");
        } else {
            if(name == 'Popular Wod'){
                $("#recordTypeFamousWodDiv").show();
            }else{
                $("#recordTypeFamousWodDiv").hide();
            }
            $("#record").selectpicker("val",1);
            $('.metcon').fadeIn();
            $("#saveContentSecond").show();
            $("#updateContentSecond").hide();
            $("#contentNameSecond").val("");
            $("#contentDescriptionSecond").val("");
            $("#contentNameSecond").removeAttr("readonly");
            $("#contentDescriptionSecond").removeAttr("readonly");
            $("#record").removeAttr("disabled","");
            $("#yes").prop("disabled",false);
            $("#no").prop("disabled",false);
        }
    });

    $('.close-btn,.section-cancel-btn').on('click', function () {
        $('.pop-box-bg').fadeOut(function () {
            $('.section-content').removeClass('red edit-content');
            $('.section-content input').val('');
        });
    });

    // 校验
    $('.section-save-btn').on('click', function () {
        console.log("校验-" + $.trim($('.section-content input').val()) + "-")
        if ($.trim($('.section-content input').val()) == '') {
            $('.section-content').addClass('red');
        } else {
            $('.section-content').removeClass('red');
        }
    });

    $('.title-box').on('click', function () {
        $("#transId").val($(this).siblings('.actionSourceId').val());
        $("#deletedId").val($(this).siblings('.actionSourceId').val());
        $(".delete-btn").show();
        if (contentType == 6) {
            $('.sections').fadeIn();
            $('.section-content').addClass('edit-content');
            $('.section-content input').val($(this).html())
            $("#saveSe").hide();
            $("#updateSe").show();
            console.log($(this).siblings('.actionSourceId').val());
        } else if (contentType == 1 || contentType == 2 || contentType == 3) {
            $('.warm-up').fadeIn();
            $('.section-content').addClass('edit-content');
            $('.section-content input').val($(this).html())
            $("#saveContentFrist").hide();
            $("#updateContentFrist").show();
        } else {
            $('.metcon').fadeIn();
            $('.section-content').addClass('edit-content');
            $('.section-content input').val($(this).html())
            $("#saveContentSecond").hide();
            $("#updateContentSecond").show();
        }
    });

    $('#record').selectpicker();
    $('#eachRoundRecordType').selectpicker();
    $('.radio').iCheck({
        radioClass: 'iradio_flat-red'
    });
    loadpage();
    $.each($("textarea"), function(i, n){
        autoTextarea($(n)[0]);
    });
});

function sectionSearch(keyword,pagess) {
    var page ="";
    if(typeof(pagess) == "undefined"){
        page = pageIndex;
    }else{
        page = pagess;
    }
    var ctx = $("#ctx").val();
        $.ajax({
        url: ctx + "/wod/sectionSearch",
        type: 'POST', //GET
        async: false,    //或false,是否异步
        data: "searchType=" + searchType+"&keyword="+keyword+"&pagess="+page,
        dataType: 'json',
        success: function (pager) {
            var list = pager.list;
            var members = ' <ul id="sectionUl" class="list-box">';
            $.each(list, function (i, item) {
                members += '<li class="clearfix">';
                members += '<div class="check-box fl">';
                members += '</div>';
                members += '<p class="title-box fl">' + item.title.replace(new RegExp('amp;',"gm"),'') + '</p>';
                if (item.userId == 174) {
                    members += '<p class="type-box fl">系统</p>';
                }
                if (item.userId != 174) {
                    members += '<p class="type-box fl">自定义</p>';
                }
                members += '<input type="hidden" class="actionSourceId" value="' + item.sectionId + '"/>';
                members += '<input type="hidden" class="sectionUserId" value="'+item.userId+'"/>'
                members += '</li>'
            });
            $("#sectionListSource").html(members);
            $("#PageCount").val(pager.totalRow);
            loadpage();
            $('.title-box').on('click', function () {
                $("#transId").val($(this).siblings('.actionSourceId').val());
                $("#deletedId").val($(this).siblings('.actionSourceId').val());
                $(".delete-btn").show();
                if (contentType == 6) {
                    if($(this).siblings('.sectionUserId').val() != 174){
                        $('.sections').fadeIn();
                        $('.section-content').addClass('edit-content');
                        $('.section-content input').val($(this).html().replace(new RegExp('amp;',"gm"),'') )
                        $("#saveSe").hide();
                        $("#updateSe").show();
                    }
                }
                console.log($(this).siblings('.actionSourceId').val());
            });
            console.log("成功");
            console.log(contentType);
        },
        error: function () {
            alert("错误");
        }
    });
}

/**
 *@Author : RedCometJ
 *@Description : content列表查询
 *@params
 *@return
 *@Date : 2017/12/5
 */
function contentSearch(keyword,pagess) {
    var page ="";
    if(typeof(pagess) == "undefined"){
        page = pageIndex;
    }else{
        page = pagess;
    }
    var ctx = $("#ctx").val();
    $.ajax({
        url: ctx + "/wod/contentSearch",
        type: 'POST', //GET
        async: false,    //或false,是否异步
        data: "searchType=" + searchType + "&contentType=" + contentType+"&keyword="+keyword+"&pagess="+page,
        dataType: 'json',
        success: function (pager) {
            var list = pager.list;
            var members = ' <ul id="sectionUl" class="list-box">';
            $.each(list, function (i, item) {
                members += '<li class="clearfix">';
                members += '<div class="check-box fl">';
                members += '</div>';
                members += '<p class="title-box fl">' + item.title.replace(new RegExp('amp;',"gm"),'') + '</p>';
                if (item.userId == 1) {
                    members += '<p class="type-box fl">系统</p>';
                }
                if (item.userId != 1) {
                    members += '<p class="type-box fl">自定义</p>';
                }
                members += '<p class="record-box fl">' + item.record + '</p>';
                members += '<p class="hide-box fl">' + item.noUseDay + '</p>';
                members += '<p class="last-box fl">' + item.lastUseDay + '</p>';
                members += '<input type="hidden" class="actionSourceId" value="' + item.contentId + '"/>'
                members += '<input type="hidden" class="descriptions" value="' + item.descriptions + '"/>'
                members += '<input type="hidden" class="recordType" value="' + item.recordType + '"/>'
                members += '<input type="hidden" class="contentUserId" value="' + item.userId + '"/>'
                members+='<input type="hidden" class="recordTypeFamousWod" value="'+item.recordTypeFamousWod+'"/>'
                members += '<input type="hidden" class="isRx" value="' + item.isRx + '"/>'
                members += '<input type="hidden" class="eachRoundNum" value="' + item.eachRoundNum + '"/>'
                members += '<input type="hidden" class="eachRoundRecordType" value="' + item.eachRoundRecordType + '"/>'
                members += '</li>'
            });
            $("#contentListSource").html(members);
            $("#PageCount").val(pager.totalRow == 0 ? 1 : pager.totalRow);
            loadpage();
            $('.title-box').on('click', function () {
                $("#transId").val($(this).siblings('.actionSourceId').val());
                $("#deletedId").val($(this).siblings('.actionSourceId').val());
                $(".delete-btn").show();
                if (contentType == 1 || contentType == 2 || contentType == 3) {
                    $('.warm-up').fadeIn();
                    $('.section-content').addClass('edit-content');
                    $('.section-content input').val($(this).html().replace(new RegExp('amp;',"gm"),''));
                    $("#contentDescription").val($(this).siblings('.descriptions').val())
                    if($(this).siblings('.contentUserId').val() == 1){
                        $("#contentName").attr("readonly","readonly");
                        $("#contentDescription").attr("readonly","readonly");
                        $("#saveContentFrist").hide();
                        $("#updateContentFrist").hide();
                        $(".delete-btn").hide();
                    }else{
                        $("#saveContentFrist").hide();
                        $("#updateContentFrist").show();
                        $("#contentName").removeAttr("readonly");
                        $("#contentDescription").removeAttr("readonly");
                    }
                } else {
                    $('.metcon').fadeIn();
                    $('.section-content').addClass('edit-content');
                    $('#contentNameSecond').val($(this).html().replace(new RegExp('amp;',"gm"),''));
                    $("#contentDescriptionSecond").val($(this).siblings('.descriptions').val())
                    $("#record").selectpicker("val", $(this).siblings('.recordType').val());
                    if($(this).siblings('.recordType').val() == 5){
                        $("#eachRoundRecord").show();
                        $(".eachRoundRecordType").show();
                        $("#eachRoundRecord").val($(this).siblings('.eachRoundNum').val());
                        $("#eachRoundRecordType").selectpicker("val", $(this).siblings('.eachRoundRecordType').val());
                    }
                    console.log('isrx  '+$(this).siblings('.isRx').val());
                    console.log('用户id '+$(this).siblings('.contentUserId').val());

                    if($(this).siblings('.isRx').val() == 1){
                        $("#yes").iCheck("check");
                    }else{
                        $("#no").iCheck("check");
                    }
                    if($(this).siblings('.contentUserId').val() == 1){
                        $("#contentNameSecond").attr("readonly","readonly");
                        $("#contentDescriptionSecond").attr("readonly","readonly");
                        $("#record").attr("disabled","disabled");
                        $("#yes").prop("disabled",true);
                        $("#no").prop("disabled",true);
                        $("#saveContentSecond").hide();
                        $("#updateContentSecond").hide();
                        $(".delete-btn").hide();
                    }else{
                        $("#contentNameSecond").removeAttr("readonly");
                        $("#contentDescriptionSecond").removeAttr("readonly");
                        $("#record").removeAttr("disabled");
                        $("#yes").prop("disabled",false);
                        $("#no").prop("disabled",false);
                        $("#saveContentSecond").hide();
                        $("#updateContentSecond").show();
                        $("#record").selectpicker('refresh');
                    }
                }
            });
            console.log("成功");
            console.log(contentType);
        },
        error: function () {
            alert("错误");
        }
    });
}

function sectionEdit() {
    var id = $("#transId").val();
    var ctx = $("#ctx").val();
    var title = $("#sectionTitle").val();
    var data = {sectionId:id,title:title};
    // console.log("id "+$("#transId").val());
    $.ajax({
        url: ctx + "/wod/sectionEdit",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        data: data,
        dataType: 'text',
        success: function (data) {
            console.log("成功");
            $('.pop-box-bg').fadeOut(function () {
                $('.section-content').removeClass('red edit-content');
                $('.section-content input').val('');
            });
            sectionSearch(keyword,pageIndex);
        },
        error: function () {
            alert("错误");
        }
    });
}

function sectionAdd() {
    var ctx = $("#ctx").val();
    var title = $("#sectionTitle").val();
    if(title.trim() ==''){
        $('.section-content').addClass('red');
        return false;
    }else {
        $('.section-content').removeClass('red');
    }
    var data = {title:title};
    $.ajax({
        url: ctx + "/wod/sectionAdd",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        data: data,
        dataType: 'text',
        success: function (data) {
            $('.pop-box-bg').fadeOut(function () {
                $('.section-content').removeClass('red edit-content');
                $('.section-content input').val('');
            });
            console.log("成功");
            sectionSearch(keyword,pageIndex);
        },
        error: function () {
            alert("错误");
        }
    });
}

/**
 *@Author : RedCometJ
 *@Description : content添加
 *@params
 *@return
 *@Date : 2017/12/5
 */
function contentAdd() {
    var ctx = $("#ctx").val();
    var title = "";
    var description = "";
    var eachRoundRecordNum = "";
    var eachRoundRecordType = "";
    var recordType = $("#record").val();

    if (contentType == 4 || contentType == 5) {
        title = $("#contentNameSecond").val();
        description = $("#contentDescriptionSecond").val();
        if(title.trim() ==''){
            $('.section-content').addClass('red');
            return false;
        }
        //else {
        //    $('.section-content').removeClass('red');
        //}
        if(recordType == 5){
            eachRoundRecordNum = $("#eachRoundRecord").val();
            if("" == eachRoundRecordNum || isNaN(eachRoundRecordNum) || Number(eachRoundRecordNum) < 1){
                alert("请输入正确的数值");
                $('#eachRoundRecord').addClass('red');
                return false;
            }
            eachRoundRecordType = $("#eachRoundRecordType").val();
        }
    } else {
        title = $("#contentName").val();
        description = $("#contentDescription").val();
        if(title.trim() ==''){
            $('.section-content').addClass('red');
            return false;
        }else {
            $('.section-content').removeClass('red');
        }
    }

    var isRxId = $("input[name='rx']:checked").attr("id");
    var isRx = 0;
    if(isRxId == "yes"){
        isRx = 1;
    }

    var data = {title:title,description:description,contentType:contentType,recordType:recordType,isRx:isRx,eachRoundRecordNum:eachRoundRecordNum,eachRoundRecordType:eachRoundRecordType};
    $.ajax({
        url: ctx + "/wod/contentAdd",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        // data: "title=" + title + "&description=" + description + "&contentType=" + contentType + "&recordType=" + recordType + "&isRx=" + isRx+"&recordTypeFamousWod="+recordTypeFamousWod,
        data:data,
        dataType: 'text',
        success: function (data) {
            $('.pop-box-bg').fadeOut(function () {
                $('.section-content').removeClass('red edit-content');
                $('.section-content input').val('');
            });
            console.log("成功");
            contentSearch(keyword,pageIndex);
        },
        error: function () {
            alert("错误");
        }
    });
}

/**
 *@Author : RedCometJ
 *@Description : content编辑
 *@params
 *@return
 *@Date : 2017/12/5
 */
function contentEdit() {
    var ctx = $("#ctx").val();
    var id = $("#transId").val();
    var title = "";
    var description = "";
    var record_type = $("#record").val();
    var isRxId = $("input[name='rx']:checked").attr("id");
    var isRx = 0;
    if(isRxId == "yes"){
        isRx = 1;
    }
    var eachRoundRecordNum = "";
    var eachRoundRecordType = "";
    // var recordTypeFamousWod = $("#famousWodType").val();
    if(contentType == 4 || contentType == 5){
        title = $("#contentNameSecond").val();
        description = $("#contentDescriptionSecond").val();
        if(title.trim() ==''){
            $('.section-content').addClass('red');
            return false;
        }else {
            $('.section-content').removeClass('red');
        }
        if(record_type == 5){
            eachRoundRecordNum = $("#eachRoundRecord").val();
            if("" == eachRoundRecordNum || isNaN(eachRoundRecordNum) || Number(eachRoundRecordNum) < 1){
                alert("请输入正确的数值");
                $('#eachRoundRecord').addClass('red');
                return false;
            }
            eachRoundRecordType = $("#eachRoundRecordType").val();
        }
    }else{
        title = $("#contentName").val();
        description = $("#contentDescription").val();
        if(title.trim() ==''){
            $('.section-content').addClass('red');
            return false;
        }else {
            $('.section-content').removeClass('red');
        }
    }
    var data = {contentId:id,title:title,description:description,contentType:contentType,recordType:record_type,isRx:isRx,eachRoundRecordNum:eachRoundRecordNum,eachRoundRecordType:eachRoundRecordType};
    $.ajax({
        url: ctx + "/wod/contentEdit",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        // data: "title=" + title + "&description=" + description + "&contentType=" + contentType + "&contentId=" + id+"&recordType="+record_type+"&recordTypeFamousWod="+recordTypeFamousWod,
        data:data,
        dataType: 'text',
        success: function (data) {
            $('.pop-box-bg').fadeOut(function () {
                $('.section-content').removeClass('red edit-content');
                $('.section-content input').val('');
            });
            console.log("成功");
            contentSearch(keyword,pageIndex);
            $("#contentName").val("");
            $("#contentDescription").text("");
        },
        error: function () {
            alert("错误");
        }
    });
}

function sectionDel() {
    var ctx = $("#ctx").val();
    var id = $("#deletedId").val();
    var title = $("#sectionTitle").val();
    var msg = "是否删除 "+title+"?";
    if (confirm(msg)==true){
        $.ajax({
            url:ctx + "/wod/sectionDel",
            type:'POST', //POST
            async:true,    //或false,是否异步
            data:"id="+id,
            dataType:'text',
            success:function(data){
                $('.pop-box-bg').fadeOut(function () {
                    $('.section-content').removeClass('red edit-content');
                    $('.section-content input').val('');
                });
                console.log("成功");
                sectionSearch(keyword,pageIndex);
                $("#contentName").val("");
                $("#contentDescription").text("");
            },
            error:function(){
                alert("错误");
            }
        });
    }else{
        return false;
    }
}

function contentDel() {
    var ctx = $("#ctx").val();
    var id = $("#deletedId").val();
    var title ='';
    if(contentType != 6){
        if(contentType == 4 || contentType == 5){
            title = $("#contentNameSecond").val();
        }else{
            title = $("#contentName").val();
        }
        if(title ==''){
            console.log('数据获取错误');
        }
        var msg = "是否删除 "+title+"?";
        if (confirm(msg)==true){
            $.ajax({
                url:ctx + "/wod/contentDel",
                type:'POST', //POST
                async:true,    //或false,是否异步

                data:"id="+id,
                dataType:'text',
                success:function(data){
                    $('.pop-box-bg').fadeOut(function () {
                        $('.section-content').removeClass('red edit-content');
                        $('.section-content input').val('');
                    });
                    console.log("成功");
                    contentSearch(keyword,pageIndex);
                },
                error:function(){
                    alert("错误");
                }
            });
        }else{
            return false;
        }
    }
}

function searchAll(){
    keyword = $("#keyword").val();
    if(typeof(keyword) == "undefined"){
        keyword = "";
    }
    if(contentType == 6){
        sectionSearch(keyword,pageIndex);
    }else{
        contentSearch(keyword,pageIndex);
    }
}

function replaceAll(FindText, RepText) {
    regExp = new RegExp(FindText, 'g');
    return this.replace(regExp, RepText);
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
                if(contentType == 6){
                    sectionSearch(keyword,num);
                }else{
                    contentSearch(keyword,num)
                }
            }
        }
    });
}
function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}

function changeOption(){
    if($("#record").val() == 5){
        $("#eachRoundRecord").show();
        $(".eachRoundRecordType").show();
    }else{
        $("#eachRoundRecord").hide();
        $(".eachRoundRecordType").hide();
    }
}

var autoTextarea = function (elem, extra, maxHeight) {
    extra = extra || 0;
    var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window,
        isOpera = !!window.opera && !!window.opera.toString().indexOf('Opera'),
        addEvent = function (type, callback) {
            elem.addEventListener ?
                elem.addEventListener(type, callback, false) :
                elem.attachEvent('on' + type, callback);
        },
        getStyle = elem.currentStyle ?
            function (name) {
                var val = elem.currentStyle[name];
                if (name === 'height' && val.search(/px/i) !== 1) {
                    var rect = elem.getBoundingClientRect();
                    return rect.bottom - rect.top -
                        parseFloat(getStyle('paddingTop')) -
                        parseFloat(getStyle('paddingBottom')) + 'px';
                };
                return val;
            } : function (name) {
                return getComputedStyle(elem, null)[name];
            },
        minHeight = parseFloat(getStyle('height')) < 40 ? 40 : parseFloat(getStyle('height'));
    elem.style.resize = 'both';//如果不希望使用者可以自由的伸展textarea的高宽可以设置其他值

    var change = function () {
        var scrollTop, height,
            padding = 0,
            style = elem.style;

        if (elem._length === elem.value.length) return;
        elem._length = elem.value.length;

        if (!isFirefox && !isOpera) {
            padding = parseInt(getStyle('paddingTop')) + parseInt(getStyle('paddingBottom'));
        };
        scrollTop = document.body.scrollTop || document.documentElement.scrollTop;

        elem.style.height = minHeight + 'px';
        if (elem.scrollHeight > minHeight) {
            if (maxHeight && elem.scrollHeight > maxHeight) {
                height = maxHeight - padding;
                style.overflowY = 'auto';
            } else {
                height = elem.scrollHeight - padding;
                style.overflowY = 'hidden';
            };
            style.height = height + extra + 'px';
            scrollTop += parseInt(style.height) - elem.currHeight;
            document.body.scrollTop = scrollTop;
            document.documentElement.scrollTop = scrollTop;
            elem.currHeight = parseInt(style.height);
        };
    };

    addEvent('propertychange', change);
    addEvent('input', change);
    addEvent('focus', change);
    change();
};