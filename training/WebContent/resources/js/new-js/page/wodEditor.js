var ctx = $("#ctx").val();
var wodId = $("#wodId").val();
$(function () {
    changeWin();
    $("div.delete_but").on("click",function(){
        if(confirm("确定删除该WOD?")) {
            $.ajax({
                url: ctx + "/wod/deleteWod",
                type: 'POST', //GET
                async: true,    //或false,是否异步
                data: "wodId=" + wodId,
                dataType: 'text',
                success: function (data) {
                    var boxId = data.boxId;
                    var courseTypeId = data.courseTypeId;
                    window.location.href = ctx + "/wod/queryWodList?boxId=" + boxId + "&courseTypeId=" + courseTypeId;
                },
                error: function () {
                    return false;
                }
            });
        }
    });
    $("#wodRemark").hide();

    $('.section-show').fadeIn();
    $('.contentDiv').show();
    $('.addContentA').hide();
    $('.metconA').fadeIn();


    loadSectionInit();
    loadContentInit();
    $('.selectpicker').selectpicker();
    $('.hours').selectpicker();
    $('.minus').selectpicker();

    $('#section').selectpicker();
    $('#content').selectpicker();
    $('#content1').selectpicker();
    $('.clean').selectpicker();
    $('.clean1').selectpicker();
    $('.time').selectpicker();

    queryOperatorRecord();
    // 点击section
    $('#section').on('change', function (e) {
        var sectionId = e.target.value;
        var sectionTitle = $(this).find("option:selected").text();
        if(sectionId != null && sectionId != undefined && sectionId != ""){
            $(this).selectpicker("val",null);
            $.ajax({
                url:ctx + "/wod/toAddSection",
                type:'POST', //GET
                async:false,    //或false,是否异步
                data:"wodId=" + wodId + "&sectionId="+sectionId,
                dataType:'text',
                success:function(data){
                    $("#sortable").append(selectToAddSection(data,sectionTitle));
                    $('.sectionEdit .addSectionCancel').unbind("click");
                    loadSectionInit();
                    $('#sectionSel'+data).selectpicker();
                    $('#section'+data).fadeIn();

                },
                error:function(){
                    return false;
                }
            });
        }
        queryOperatorRecord();
    });

    // 点击content
    $('#content').on('change', function (e) {
        var contentId = e.target.value;
        var contentTitle = $(this).find("option:selected").text();
        var contentType = $(this).find("option:selected").attr("contentType");
        var lastSectionId = getLastSectionId();
        if(contentId != null && contentId != undefined && contentId != ""){
            $(this).selectpicker("val",null);
            var param = {contentId:contentId,wodSectionId:lastSectionId,wodId:wodId};
            $.ajax({
                url:ctx + "/wod/toAddGerenalContent",
                type:'POST', //GET
                async:false,    //或false,是否异步
                data:param,
                dataType:'json',
                success:function(data){
                    //var wodContent = eval("("+data+")");
                    var wodContent = data;
                    var wodContentId = wodContent.wodContentId;
                    var addContainer =$("#sortable");
                    if(lastSectionId == 0){
                        addContainer.append(addNullSection(wodContent.wodSectionId,"临时"));
                    }
                    if (contentType == '1') {
                        addContainer.append(content_warmUp(wodContent));
                    } else if (contentType == '2') {
                        addContainer.append(content_gymnastics(wodContent));
                    } else if(contentType == '3'){
                        addContainer.append(content_weight(wodContent));
                        $('.clean1').selectpicker();
                    }else if(contentType == '4' || contentType == '5'){
                        addContainer.append(content_meton(wodContent));
                    }
                    $('#contentSel'+wodContentId).selectpicker();
                    $('#contentSel'+wodContentId).selectpicker("val",contentId);
                    $('.contentDiv .addSectionCancel').unbind("click");
                    loadContentInit();
                    $('#content'+wodContentId).fadeIn();

                },
                error:function(){
                    return false;
                }
            });
        }
        queryOperatorRecord();
    });

    // 点击自定义
    $('#content1').on('change', function (e) {
        var custonContentType = e.target.value;
        var lastSectionId = getLastSectionId();
        if(null != custonContentType && custonContentType != undefined && custonContentType != ""){
            $(this).selectpicker("val",null);
            $.ajax({
                url:ctx + "/wod/toAddCustomContent",
                type:'POST', //GET
                async:false,    //或false,是否异步
                data:"custonContentType=" + custonContentType + "&wodSectionId="+lastSectionId + "&wodId="+wodId,
                dataType:'text',
                success:function(data){
                    var wodContent = eval("("+data+")");
                    var wodContentId = wodContent.wodContentId;
                    var addContainer =$("#sortable");
                    if(lastSectionId == 0){
                        addContainer.append(addNullSection(wodContent.wodSectionId,"临时"));
                    }
                    if (custonContentType == '6') {
                        addContainer.append(custom_warmUp(wodContent));
                    } else if (custonContentType == '7') {
                        addContainer.append(custom_gymnastics(wodContent));
                    } else if(custonContentType == '8'){
                        addContainer.append(custom_weightlifting(wodContent));
                        $('.clean1').selectpicker();
                    }else if(custonContentType == '9'){
                        addContainer.append(custom_mecton(wodContent));
                        $("#content"+wodContentId).find(".custom_content_eachround").hide();
                        $("#content"+wodContentId).find(".custom_content_weight").hide();
                        $("#content"+wodContentId).find(".custom_content_distance").hide();
                        $("#metcon-type"+wodContentId).selectpicker();
                        $("#weightRecordUnit"+wodContentId).selectpicker();
                        $("#eachRoundRecord"+wodContentId).selectpicker();
                        $("#eachRoundUnit"+wodContentId).selectpicker();
                        $("#eachRoundA"+wodContentId).selectpicker();
                        $("#distanceRecord"+wodContentId).selectpicker();
                    }
                    $('#content'+wodContentId).fadeIn();
                    $('.contentDiv .addSectionCancel').unbind("click");
                    loadContentInit();
                },
                error:function(){
                    return false;
                }
            });
        }
        queryOperatorRecord();
    });


    $('#weight-mark').on('change', function (e) {
        if(e.target.value == '7'){
            $('.custom-metcon, .each-round, .distance').hide();
            $('.weight').fadeIn();
            $('#weight-mark').selectpicker('val', '7');
        }else if(e.target.value == '8'){
            $('.custom-metcon, .weight,.distance').hide();
            $('.each-round').fadeIn();
            $('#each-round-mark').selectpicker('val', '8');
        }else if(e.target.value == '9'){
            $('.custom-metcon,.weight,.each-round').hide();
            $('.distance').fadeIn();
            $('#distance-mark').selectpicker('val', '9');
        }else{
            $('.weight, .each-round, .distance').hide();
            $('.custom-metcon').fadeIn();
            $('#metcon-type').selectpicker('val', '1');
        }
    });
    $('#each-round-mark').on('change', function (e) {
        if(e.target.value == '7'){
            $('.custom-metcon, .each-round, .distance').hide();
            $('.weight').fadeIn();
            $('#weight-mark').selectpicker('val', '7');
        }else if(e.target.value == '8'){
            $('.custom-metcon, .weight,.distance').hide();
            $('.each-round').fadeIn();
            $('#each-round-mark').selectpicker('val', '8');
        }else if(e.target.value == '9'){
            $('.custom-metcon,.weight,.each-round').hide();
            $('.distance').fadeIn();
            $('#distance-mark').selectpicker('val', '9');
        }else{
            $('.weight, .each-round, .distance').hide();
            $('.custom-metcon').fadeIn();
            $('#metcon-type').selectpicker('val', '1');
        }
    });
    $('#distance-mark').on('change', function (e) {
        if(e.target.value == '7'){
            $('.custom-metcon, .each-round, .distance').hide();
            $('.weight').fadeIn();
            $('#weight-mark').selectpicker('val', '7');
        }else if(e.target.value == '8'){
            $('.custom-metcon, .weight,.distance').hide();
            $('.each-round').fadeIn();
            $('#each-round-mark').selectpicker('val', '8');
        }else if(e.target.value == '9'){
            $('.custom-metcon,.weight,.each-round').hide();
            $('.distance').fadeIn();
            $('#distance-mark').selectpicker('val', '9');
        }else{
            $('.weight, .each-round, .distance').hide();
            $('.custom-metcon').fadeIn();
        }
    });


    // 初始化checkbox
    $('.select-all').iCheck({
        checkboxClass: 'icheckbox_flat-red'
    });
    $('.select-one').iCheck({
        checkboxClass: 'icheckbox_flat-red'
    });

    // 点击编辑
    $('.nameEditor').on('click', function () {
        var showDate = $("#wodShowDate").html();
        $.ajax({
            url:ctx + "/wod/toEditWoding",
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:"wodId=" + wodId + "&showDate="+showDate,
            dataType:'text',
            success:function(data){
                var wodPageModel = eval("("+data+")");
                var courseTypeId = wodPageModel.courseType;
                var wodTopRelationList = wodPageModel.wodTopRelationList;
                var wod = wodPageModel.wod;
                $("#wodCourseType").selectpicker("val",courseTypeId);
                $("#wodEditName").val(wod.name);
                for(var i=0;i<wodTopRelationList.length;i++){
                    $("#boxCheckBox"+wodTopRelationList[i].boxId).iCheck('check');
                }
                var hour = wodPageModel.time;
                var minus = wodPageModel.minitus;
                $("#hours").selectpicker("val",hour);
                $("#minus").selectpicker("val",minus);
                $("#wodCommentValue").val(wod.remark);
                var isCanEdit = wodPageModel.isCanEdit;
                if(!isCanEdit){
                    $("#wodCourseType").attr("disabled",true);
                    $("#wodEditName").attr("readonly",true);
                    $("input.select-all").attr("disabled",true);
                    $("input.boxCheckBox").attr("disabled",true);
                    $("#hours").attr("disabled",true);
                    $("#minus").attr("disabled",true);
                    $("span.save-btn").hide();
                }
                $('.wodName').slideUp();
                $('.edit-box').slideDown();
            },
            error:function(){
                return false;
            }
        });

    });
    // 点击取消
    $('.cancel-btn').on('click', function () {
        $('.wodName').slideDown();
        $('.edit-box').slideUp();
    });
    // 点击保存
    $('.save-btn').on('click', function () {
        var wodName = $("#wodEditName").val().trim();
        if(wodName == ""){
            alert("请输入训练计划名称");
            return false;
        }
        var boxsIds = "";
        var courseTypeId  = $("#wodCourseType").val();
        var day = $(".years").text();
        var hours = $("#hours").val();
        var minus =$("#minus").val();
        var boxNames = "";
        var courseTypeName = $("#wodCourseType").find("option:selected").text();
        $.each($('input.boxCheckBox:checkbox'),function(){
            if(this.checked){
                boxsIds+= $(this).val()+",";
                boxNames += $(this).parents("li.box_li").find("span.box_name").html()+",";
            }
        });
        if(boxsIds == ""){
            alert("请选择训练馆");
            return false;
        }
        var comment = $("#wodCommentValue").val();
        $.ajax({
            url:ctx + "/wod/updateWoding",
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:"wodId=" + wodId +"&boxsIds=" + boxsIds + "&wodName=" + wodName+"&courseTypeId="+courseTypeId
            +"&hours="+hours+ "&minitus="+minus+"&comment="+comment,
            dataType:'text',
            success:function(data){
                $("#showWodName").html(wodName);
                $("#showCourseTypeName").html(courseTypeName);
                $("#showBoxName").html(boxNames.substring(0,boxNames.length-1));
                $('.wodName').slideDown();
                $('.edit-box').slideUp();
            },
            error:function(){
                return false;
            }
        });
        queryOperatorRecord();
    });

    // 添加备注
    $('#toAddRemark').on('click', function () {
        $("#wodCommentContent").val($("#wodCommentValue").val());
        $('#wodRemark').show();
    });
    $('#wodRemarkClose').on('click', function () {
        $('#wodRemark').hide();
    });
    $('#wodRemarkSure').on('click', function () {
        $("#wodCommentValue").val($("#wodCommentContent").val());
        $('#wodRemark').hide();
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


    //预览
    $(".titleRight").on("click",function(){
        $.ajax({
            url:ctx + "/wod/toEditSection",
            type:'POST', //GET
            async:true,    //或false,是否异步
            dataType: 'json',
            url: ctx+'/wod/previewWod',
            data: {wodId:wodId},
            success:function(data){
                var wodName = data.wod.name;
                var wodSectionList = data.sectionModelList;
                var popHtml = '<strong class="close"></strong>'+
                        '<p class="pop-title">' + wodName + '</p>';
                if(null != wodSectionList && wodSectionList != undefined){
                    for(var i=0;i<wodSectionList.length;i++){
                        var wodSection = wodSectionList[i].wodSection;
                        var wodContentList = wodSectionList[i].wodContentList;
                        popHtml += '<div class="pop-block">';
                        if(wodSection.type != 0){
                            popHtml += '<p class="little-title">' + wodSection.section.title + '</p>';
                        }
                        if(null != wodContentList && undefined != wodContentList){
                            for(var j=0;j<wodContentList.length;j++){
                                var wodContent = wodContentList[j];
                                var wodContentType = wodContent.contentType;
                                //序号
                                var seriaNum = wodContent.seriaNum;
                                var seriaText = "";
                                if(null != seriaNum && undefined != seriaNum){
                                    seriaText = seriaNum + "、";
                                }
                                //scheme
                                var repsScheme = wodContent.repsScheme;
                                var schemeText = "";
                                if(null != repsScheme && undefined != repsScheme && '' != repsScheme){
                                    schemeText = '(' + repsScheme + ')';
                                }
                                popHtml += '<div class="del-content">' +
                                '<p>' + seriaText;
                                if(wodContentType == "1"){
                                    popHtml += wodContent.contentEntity.name;
                                }else if(wodContentType == "2"){
                                    popHtml += wodContent.contentEntity.name + schemeText;
                                }else if(wodContentType == "3"){
                                    popHtml += wodContent.contentEntity.name + schemeText;
                                }else if(wodContentType == "4" || wodContentType == "5"){
                                    popHtml += wodContent.contentEntity.name + '(' + wodContent.contentRecordTypeName + ')';
                                }else if(wodContentType == "6"){
                                    popHtml += 'WarmUp(no measure)';
                                }else if(wodContentType == "7"){
                                    popHtml += (wodContent.contentTitle == null || wodContent.contentTitle == "" ? 'Weightliting':wodContent.contentTitle) + schemeText;
                                }else if(wodContentType == "8"){
                                    popHtml += (wodContent.contentTitle == null || wodContent.contentTitle == "" ? 'Gymnastics':wodContent.contentTitle) + schemeText;
                                }else if(wodContentType == "9"){
                                    popHtml += (wodContent.contentTitle == null || wodContent.contentTitle == "" ? 'Metcon':wodContent.contentTitle) +
                                    '(' + wodContent.contentRecordTypeName + ')';
                                }
                                popHtml += '</p>';
                                //备注
                                var remark = wodContent.remark;
                                if(null != remark && undefined != remark && '' != remark){
                                    popHtml += '<span>' +
                                        remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ")+
                                        '</span>';
                                }
                                popHtml += '</div>';
                            }
                        }
                        popHtml += '</div>';
                    }
                }
                $(".pop-box").html(popHtml);
                $(".pop-box").fadeIn();
                $('strong.close').on('click', function (){
                    $(".pop-box").fadeOut();
                });
            },
            error:function(){
                return false;
            },
            complete:function(){

            }
        });

    });

    $('strong.close').on('click', function (){
        $(".pop-box").fadeOut();
    });

    sortIndex();
});


function loadSectionInit(){

    $('.sectionShow .changeTitle').on('click', function () {
        var wodSectionId = $(this).parents('.section-box').find("input.sectionId").val();
        $.ajax({
            url:ctx + "/wod/toEditSection",
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:"wodSectionId=" + wodSectionId,
            dataType:'text',
            success:function(data){
                var wodSection = eval("("+data+")");
                var sectionId = wodSection.sectionId;
                var wodSectionComment = wodSection.remark;
                $("#sectionSel"+wodSectionId).selectpicker("val",sectionId);
                $("#sectionComment"+wodSectionId).val(wodSectionComment);
                if(null != wodSectionComment && undefined != wodSectionComment && !"" == wodSectionComment){
                    $("#sectionSel"+wodSectionId).find(".comment3").hide();
                    $("#sectionSel"+wodSectionId).find(".down2").show();
                }
            },
            error:function(){
                return false;
            },
            complete:function(){
                $("#section"+wodSectionId).children('.addContentA').slideDown();
                $("#section"+wodSectionId).children('.metconA').slideUp();
                $("#section"+wodSectionId).children(".comment3").show();
                $("#section"+wodSectionId).children(".down2").hide();
            }
        });
    });

    $('.addContentA .cancle').on('click', function () {
        $(this).parents('.section-box').children('.addContentA').slideUp();
        $(this).parents('.section-box').children('.metconA').slideDown();
    });
    $('.sectionEdit .confirm').on('click', function () {
        var wodSectionId = $(this).parents('.section-box').find("input.sectionId").val();
        var sectionId = $("#sectionSel"+wodSectionId).val();
        var sectionTitle = $("#sectionSel"+wodSectionId).find("option:selected").text();
        var remark = $("#sectionComment"+wodSectionId).val().trim();
        $.ajax({
            url:ctx + "/wod/editSection",
            type:'POST', //GET
            async:true,    //或false,是否异步
            dataType:'json',
            data:{wodSectionId:wodSectionId, sectionId:sectionId, remark:remark},
            success:function(data){
            },
            error:function(){
                return false;
            },
            complete:function(){
                var brRemark = remark.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ");
                $("#wodSecionCommentShow"+wodSectionId).html(brRemark);
                $("#secionTitle"+wodSectionId).html(sectionTitle);
                $("#section"+wodSectionId).children('.addContentA').slideUp();
                $("#section"+wodSectionId).children('.metconA').slideDown();
                queryOperatorRecord();
            }
        });
    });
    $('.addContent .comment3').on('click', function () {
        $(this).hide();
        $(this).next().show();
    });
    $('.addContent .circle2').on('click', function () {
        $(this).parents(".down2").hide();
        $(this).parents('.section-box').find("div.sectionCommentTitle").show();
    });
    $('.sectionEdit .addSectionCancel').on('click', function () {
        if(confirm("确定删除该section?")){
            var wodSectionId = $(this).parents('.section-box').find("input.sectionId").val();
            $.ajax({
                url:ctx + "/wod/delSection",
                type:'POST', //GET
                async:true,    //或false,是否异步
                data:"wodSectionId=" + wodSectionId,
                dataType:'text',
                success:function(data){
                    $("#section"+wodSectionId).remove();
                    queryOperatorRecord();
                },
                error:function(){
                    return false;
                }
            });
        }
    });
}

function loadContentInit(){
    $('.contentDiv .changeTitle').on('click', function () {
        var wodContentId = $(this).parents('.contentDiv').find("input.contentId").val();
        var isRx = 1;
        $.ajax({
            url:ctx + "/wod/toEditContent",
            type:'POST', //GET
            async:false,    //或false,是否异步
            data:"wodContentId=" + wodContentId,
            dataType:'text',
            success:function(data){
                var wodContent = eval("("+data+")");
                if(wodContent.canEdit == '1'){
                    return false;
                }
                var json  = assignContent(wodContent);
                var eachRoundUnitType = json.eachRoundUnitType;
                 isRx = json.isRx;

                $("#content"+wodContentId).children('.addContentA').slideDown();
                $("#content"+wodContentId).children('.metconA').slideUp();

                if(eachRoundUnitType == "3"){
                    $("#seriaNum"+wodContentId).parents(".contentDiv").find('.eachRoundWeightSel').show();
                }else{
                    $("#seriaNum"+wodContentId).parents(".contentDiv").find('.eachRoundWeightSel').hide();
                }
                if(eachRoundUnitType == "4"){
                    $("#seriaNum"+wodContentId).parents(".contentDiv").find('.eachRoundDistanceSel').show();
                }else{
                    $("#seriaNum"+wodContentId).parents(".contentDiv").find('.eachRoundDistanceSel').hide();
                }
                setTimeout(function(){
                    if(isRx ==1){
                        $("#isRecord"+wodContentId).prop("checked", "checked");
                    }else{
                        $("#noRecord"+wodContentId).prop("checked", "checked");
                    }
                },300);

            },
            error:function(){
                return false;
            }
        });

        if(isRx ==1){
            $("#isRecord"+wodContentId).prop("checked", "checked");
        }else{
            $("#noRecord"+wodContentId).prop("checked", "checked");
        }
    });

    $('.contentDiv .comment').on('click', function () {
        $(this).hide();
        $(this).next().show();
    });
    $('.contentDiv .cancle').on('click', function () {
        $(this).parents('.contentDiv').children('.metconA').slideDown();
        $(this).parents('.contentDiv').children('.addContentA').slideUp();
    });
    $('.contentDiv .confirm').on('click', function () {
        var wodContentId = $(this).parents('.contentDiv').find("input.contentId").val();
        var wodContentType = $(this).parents('.contentDiv').find("input.wodContentType").val();
        var description = $("#contentEditDes"+wodContentId).val();
        var repsScheme = $("#contentEditReps"+wodContentId).val();
        var contentId = $("#contentSel"+wodContentId).val();
        var comment = $("#contentComment"+wodContentId).val().trim();
        var seriaNum = $("#seriaNum"+wodContentId).val().trim();
        if($("#contentComment"+wodContentId).parents(".down1").is(":hidden")){
            comment = null;
        }
        var contentTitle = "";
        var json = "";
        if(wodContentType == "1"){
            json = {wodContentId:wodContentId,wodContentType:wodContentType,comment:comment,seriaNum:seriaNum,contentId:contentId};
            contentTitle = $("#contentSel"+wodContentId).find("option:selected").text();
        }else if(wodContentType == "2"){//体操 sets
            var sets = $("#contentRecordSet"+wodContentId).val();
            if("" == sets || isNaN(sets) || Number(sets) < 1){
                alert("请输入正确的SETS");
                return false;
            }
            contentTitle = $("#contentSel"+wodContentId).find("option:selected").text();
            json = {wodContentId:wodContentId,wodContentType:wodContentType,contentId:contentId,
                repsScheme:repsScheme,comment:comment,seriaNum:seriaNum,sets:sets};
        }else if(wodContentType == "3"){//重量 sets reps
            var sets = $("#contentRecordSet"+wodContentId).val();
            var reps = $("#contentRecordReps"+wodContentId).val();
            var unit = $("#contentRecordUnit"+wodContentId).val();
            if("" == sets || isNaN(sets) || Number(sets) < 1){
                alert("请输入正确的SETS");
                return false;
            }
            if("" == reps || isNaN(reps) || Number(reps) < 1){
                alert("请输入正确的REPS");
                return false;
            }
            contentTitle = $("#contentSel"+wodContentId).find("option:selected").text();
            json = {wodContentId:wodContentId,wodContentType:wodContentType,contentId:contentId,
                repsScheme:repsScheme,comment:comment,seriaNum:seriaNum,sets:sets,reps:reps,unit:unit};
        }else if(wodContentType == "4" || wodContentType == "5"){
            contentTitle = $("#contentSel"+wodContentId).find("option:selected").text();
            var recordTypeName = $("#metconRecord"+wodContentId).html();
            var textName = recordTypeName.replace("记录","");
            contentTitle += '(' + textName + ')';
            var isRx = $('input:radio[name="isRecord'+wodContentId+'"]:checked').val();
            json = {wodContentId:wodContentId,wodContentType:wodContentType,contentId:contentId,
                comment:comment,seriaNum:seriaNum,isRx:isRx};
        }else if(wodContentType == "6" ){
            contentTitle = "WarmUp(no measure)";
            var sets = $("#contentRecordSet"+wodContentId).val();

            json = {wodContentId:wodContentId,wodContentType:wodContentType,
                comment:comment,seriaNum:seriaNum,description:description};
        }else if(wodContentType == "7" ){
            contentTitle = $("#contentSaveTitle"+wodContentId).val();
            if(contentTitle.trim() == ""){
                alert("请输入TITLE");
                return false;
            }
            var sets = $("#contentRecordSet"+wodContentId).val();
            if("" == sets || isNaN(sets) || Number(sets) < 1){
                alert("请输入正确的SETS");
                return false;
            }
            json = {wodContentId:wodContentId,wodContentType:wodContentType,repsScheme:repsScheme,
                description:description,comment:comment,seriaNum:seriaNum,contentTitle:contentTitle,sets:sets};
        }else if(wodContentType == "8" ){
            contentTitle = $("#contentSaveTitle"+wodContentId).val();
            if(contentTitle.trim() == ""){
                alert("请输入TITLE");
                return false;
            }
            var sets = $("#contentRecordSet"+wodContentId).val();
            var reps = $("#contentRecordReps"+wodContentId).val();
            if("" == sets || isNaN(sets) || Number(sets) < 1){
                alert("请输入正确的SETS");
                return false;
            }
            if("" == reps || isNaN(reps) || Number(reps) < 1){
                alert("请输入正确的REPS");
                return false;
            }
            json = {wodContentId:wodContentId,wodContentType:wodContentType,repsScheme:repsScheme,
                description:description,comment:comment,seriaNum:seriaNum,contentTitle:contentTitle,sets:sets,reps:reps};
        }else if(wodContentType == "9" ){
            var metconTypeName = $("#metcon-type"+wodContentId).find("option:selected").text();
            contentTitle = "Metcon"+ "("+metconTypeName + ")";
            var metcon_type = $("#metcon-type"+wodContentId).val();
            if(metcon_type == "10"){
                var weightRecordUnit = $("#weightRecordUnit"+wodContentId).val();
                var isRx = $('input:radio[name="weightRecordRx'+wodContentId+'"]:checked').val();
                json = {wodContentId:wodContentId,wodContentType:wodContentType, comment:comment,description:description,
                    seriaNum:seriaNum,contentTitle:contentTitle,metconType:metcon_type,unit:weightRecordUnit,isRx:isRx};
            }else if(metcon_type == "5"){
                var weightRecordUnit = $("#eachRoundB"+wodContentId).val().trim();
                if("" == weightRecordUnit || isNaN(weightRecordUnit) || Number(weightRecordUnit) < 1){
                    alert("请输入正确的规则");
                    return false;
                }
                var eachRoundRecord = $("#eachRoundRecord"+wodContentId).val();
                var isRx = $('input:radio[name="weightRecordRx'+wodContentId+'"]:checked').val();
                if(eachRoundRecord == "3"){
                    var wUnit = $("#eachRoundUnit"+wodContentId).val();
                    json = {wodContentId:wodContentId,wodContentType:wodContentType, comment:comment,description:description,
                        seriaNum:seriaNum,contentTitle:contentTitle,metconType:metcon_type,unit:weightRecordUnit,
                        measure:eachRoundRecord,isRx:isRx,wUnit:wUnit};
                }else if(eachRoundRecord == "4"){
                    var wUnit = $("#eachRoundA"+wodContentId).val();
                    json = {wodContentId:wodContentId,wodContentType:wodContentType, comment:comment,description:description,
                        seriaNum:seriaNum,contentTitle:contentTitle,metconType:metcon_type,unit:weightRecordUnit,
                        measure:eachRoundRecord,isRx:isRx,wUnit:wUnit};
                }else{
                    json = {wodContentId:wodContentId,wodContentType:wodContentType, comment:comment,description:description,
                        seriaNum:seriaNum,contentTitle:contentTitle,metconType:metcon_type,unit:weightRecordUnit,
                        measure:eachRoundRecord,isRx:isRx};
                }
            }else if(metcon_type == "6"){
                var distanceRecord = $("#distanceRecord"+wodContentId).val();
                var isRx = $('input:radio[name="distanceRx'+wodContentId+'"]:checked').val();
                json = {wodContentId:wodContentId,wodContentType:wodContentType, comment:comment,description:description,
                    seriaNum:seriaNum,contentTitle:contentTitle,metconType:metcon_type,unit:distanceRecord,
                    isRx:isRx};
            }else{
                var isRx = $('input:radio[name="distanceRx'+wodContentId+'"]:checked').val();
                json = {wodContentId:wodContentId,wodContentType:wodContentType, comment:comment,description:description,
                    seriaNum:seriaNum,contentTitle:contentTitle,metconType:metcon_type,
                    isRx:isRx};
            }
        }
        $.ajax({
            url:ctx + "/wod/updateContent",
            type:'POST', //GET
            async:true,    //或false,是否异步
            data:json,
            dataType:'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success:function(data){
                //if(wodContentType != "2" && wodContentType != "3" && wodContentType != "6"  ){
                //    $("#contentShowDes"+wodContentId).html(description);
                //}
                //if(wodContentType == 2 ){
                //
                //}
                if(wodContentType == "1" || wodContentType == "6"){
                    if(null != description && description != ""){
                        $("#contentShowDes" + wodContentId).html(description.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "));
                    }
                    if(comment != "" && null != comment && undefined != comment){
                        $("#contentCommentShow" + wodContentId).html(comment.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "));
                    }
                }else if(wodContentType == "2" || wodContentType == "3"){
                    contentTitle = contentTitle + (repsScheme != ""?"("+repsScheme+")" : "");
                    if(comment != "" && null != comment && undefined != comment){
                        $("#contentCommentShow" + wodContentId).html(comment.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "));
                    }
                }else if(wodContentType == "4" || wodContentType == "5" ){
                    if(comment != "" && null != comment && undefined != comment){
                        $("#contentCommentShow" + wodContentId).html(comment.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "));
                    }
                }else if(wodContentType == "7" || wodContentType == "8"){
                    //origin
                    // contentTitle = contentTitle + (repsScheme != ""?"("+repsScheme+")" : "");
                    //RedCometJ update
                    contentTitle = contentTitle + (repsScheme != "" && repsScheme != undefined ?"("+repsScheme+")" : "");
                    if(description != "" && description != null && undefined != description){
                        $("#contentShowDes" + wodContentId).html(description.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "));
                    }
                    if(comment != "" && comment != null && undefined != comment){
                        $("#contentCommentShow" + wodContentId).html(comment.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "));
                    }
                }else if(wodContentType == "9"){
                    if(description != null && description != undefined && description != ''){
                        $("#contentShowDes" + wodContentId).html(description.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "));
                    }
                    if(comment != "" && null != comment && undefined != comment){
                        $("#contentCommentShow" + wodContentId).html(comment.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "));
                    }
                }
                contentTitle = (seriaNum != "" ? seriaNum+"、"+contentTitle : contentTitle+"")
                $("#contentTitle"+wodContentId).html(contentTitle);
                $("#content"+wodContentId).children('.addContentA').slideUp();
                $("#content"+wodContentId).children('.metconA').slideDown();

                queryOperatorRecord();
            },
            error:function(){
                return false;
            }
        });

    });
    $('.contentDiv .circle1').on('click', function () {
        $(this).parents('.contentDiv').find('.down1').hide();
        $(this).parents('.contentDiv').find('.comment1').show();
    })
    $('.contentDiv .addSectionCancel').on('click', function () {
        if(confirm("确定删除该动作!")){
            var wodContentId = $(this).parents('.contentDiv').find("input.contentId").val();
            $.ajax({
                url:ctx + "/wod/deleteContent",
                type:'POST', //GET
                async:true,    //或false,是否异步
                data:"wodContentId=" + wodContentId,
                dataType:'text',
                success:function(data){
                    $("#content"+wodContentId).remove();
                    queryOperatorRecord();
                },
                error:function(){
                    return false;
                }
            });
        }
    })

    // 自定义metcon
    $('.custom_metcon_type').on('change', function () {
        var type = $(this).val();
        var wodContentId = $(this).parents(".custom-metcon").find("input.contentId").val();
        if(type == '10'){
            $(this).parents(".contentDiv").find(".custom_content_general").hide();
            $(this).parents(".contentDiv").find(".custom_content_eachround").hide();
            $(this).parents(".contentDiv").find(".custom_content_distance").hide();
            $(this).parents(".contentDiv").find(".custom_content_weight").show();
        }else if(type == '5'){
            $(this).parents(".contentDiv").find('.custom_content_general').hide();
            $(this).parents(".contentDiv").find('.custom_content_eachround').show();
            $(this).parents(".contentDiv").find('.custom_content_distance').hide();
            $(this).parents(".contentDiv").find('.custom_content_weight').hide();
            $(this).parents(".contentDiv").find('.eachRoundWeightSel').hide();
            $(this).parents(".contentDiv").find('.eachRoundDistanceSel').hide();
        }else if(type == '6'){
            $(this).parents(".contentDiv").find('.custom_content_general').hide();
            $(this).parents(".contentDiv").find('.custom_content_eachround').hide();
            $(this).parents(".contentDiv").find('.custom_content_distance').show();
            $(this).parents(".contentDiv").find('.custom_content_weight').hide();
        }else{
            $(this).parents(".contentDiv").find('.custom_content_general').show();
            $(this).parents(".contentDiv").find('.custom_content_eachround').hide();
            $(this).parents(".contentDiv").find('.custom_content_distance').hide();
            $(this).parents(".contentDiv").find('.custom_content_weight').hide();
        }
    });

    $('.timeshort').on('change', function () {
        var type = $(this).val();
        var wodContentId = $(this).parents(".custom-metcon").find("input.contentId").val();
        if(type == "3"){
            $(this).parents(".contentDiv").find('.eachRoundWeightSel').show();
            $(this).parents(".contentDiv").find('.eachRoundDistanceSel').hide();
        }else if(type == "4"){
            $(this).parents(".contentDiv").find('.eachRoundWeightSel').hide();
            $(this).parents(".contentDiv").find('.eachRoundDistanceSel').show();
        }else{
            $(this).parents(".contentDiv").find('.eachRoundWeightSel').hide();
            $(this).parents(".contentDiv").find('.eachRoundDistanceSel').hide();
        }
    });
}
function getLastSectionId(){
    if($("input.sectionId").length < 1){
        return 0;
    }
    return $("input.sectionId").last().val();
}

//根据wodContent不同的类型给conteng块赋值
function assignContent(wodContent){
    var isRxxx = 1;
    var eachRoundUnitType = 0;
    var wodContentId = wodContent.wodContentId;
    var wodSectionId = wodContent.wodSectionId;
    var remark = wodContent.remark;
    var descriptioin = wodContent.descript;
    var seriaNum = wodContent.seriaNum;
    var wodContentTitle = wodContent.contentEntity.name;
    var wodContentType = wodContent.contentType;
    var contentId = wodContent.contentEntity.contentId;
    var repsScheme = wodContent.repsScheme;
    if(wodContentType == "1"){//常规warm_up
        $("#contentTitle"+wodContentId).html((seriaNum != ""&& seriaNum != undefined? seriaNum+"、":"")+wodContentTitle);
        //$("#contentShowDes"+wodContentId).html(descriptioin);
        $("#seriaNum"+wodContentId).val(seriaNum);
        $("#contentSel"+wodContentId).selectpicker("val",contentId);
        $("#contentEditDes"+wodContentId).val(descriptioin);
        $("#contentComment"+wodContentId).val(remark);
        $("#wodSecionCommentShow"+wodContentId).val(remark);
    }else if(wodContentType == "2"){
        wodContentTitle = wodContentTitle + (repsScheme != ""&& repsScheme != undefined? "(" + repsScheme+")":"")
        var wodContent_content = wodContent.content;
        var sets = 1;
        if(null != wodContent_content && wodContent_content != undefined && wodContent_content != ""){
            var content = eval("("+wodContent_content+")");
            sets = content.sets;
        }
        $("#contentTitle"+wodContentId).html((seriaNum != ""&& seriaNum != undefined? seriaNum+"、":"")
        +wodContentTitle);
        $("#seriaNum"+wodContentId).val(seriaNum);
        $("#contentSel"+wodContentId).selectpicker("val",contentId);
        if(repsScheme != null && repsScheme != undefined){
            $("#contentEditReps"+wodContentId).val(repsScheme);
        }
        $("#contentComment"+wodContentId).val(remark);
        $("#wodSecionCommentShow"+wodContentId).val(remark);
        $("#contentRecordSet"+wodContentId).val(sets);
    }else if(wodContentType == "3"){
        var wodContent_content = wodContent.content;
        var sets = 1;
        var reps = 1;
        var unit = 1;
        if(null != wodContent_content && wodContent_content != undefined && wodContent_content != ""){
            var content = eval("("+wodContent_content+")");
            sets = content.sets;
            reps = content.resps;
            unit = content.unit;
        }
        $("#contentTitle"+wodContentId).html((seriaNum != ""&& seriaNum != undefined? seriaNum+"、":"")+wodContentTitle
        +(repsScheme != "" && repsScheme != undefined ? "("+repsScheme+")":""));
        $("#seriaNum"+wodContentId).val(seriaNum);
        $("#contentSel"+wodContentId).selectpicker("val",contentId);
        if(repsScheme != null && repsScheme != undefined){
            $("#contentEditReps"+wodContentId).val(repsScheme);
        }
        $("#contentComment"+wodContentId).val(remark);
        $("#wodSecionCommentShow"+wodContentId).val(remark);
        $("#contentRecordSet"+wodContentId).val(sets);
        $("#contentRecordReps"+wodContentId).val(reps);
        $("#contentRecordUnit"+wodContentId).selectpicker("val",unit);
    }else if(wodContentType == "4" || wodContentType == "5" ){
        wodContentTitle += "(" + wodContent.contentRecordTypeName + ")";
        var wodContent_content = wodContent.content;
        descriptioin = wodContent.contentEntity.description;
        var isRx = 1;
        if(null != wodContent_content && wodContent_content != undefined && wodContent_content != ""){
            var content = eval("("+wodContent_content+")");
            isRx = content.isRx;
        }else{
            isRx = wodContent.contentEntity.isRx;
        }
        $("#contentTitle"+wodContentId).html((seriaNum != ""&& seriaNum != undefined? seriaNum+"、":"")+wodContentTitle);
        $("#gmetcondes"+wodContentId).html(descriptioin.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "));
        $("#seriaNum"+wodContentId).val(seriaNum);
        $("#contentSel"+wodContentId).selectpicker("val",contentId);
        $("#metconRecord"+wodContentId).html("记录"+wodContent.contentRecordTypeName);
        $("#contentComment"+wodContentId).val(remark);
        $("#wodSecionCommentShow"+wodContentId).val(remark);
        //$("input[name='isRecord"+wodContentId+"'][value='" + isRx + "']").prop("checked", "checked");
        if(isRx ==1){
            $("#isRecord"+wodContentId).prop("checked", "checked");
        }else{
            $("#noRecord"+wodContentId).prop("checked", "checked");
        }
        isRxxx = isRx;
        //$("input[name='isRecord"+wodContentId+"'][value='"+isRx+"']").prop("checked",true);
    }else if(wodContentType == "6"){
        if(wodContent.descript != null && wodContent.descript != undefined){
            $("#contentEditDes"+wodContentId).val(wodContent.descript);
        }
        $("#seriaNum"+wodContentId).val(seriaNum);
        $("#contentComment"+wodContentId).val(remark);
        $("#wodSecionCommentShow"+wodContentId).val(remark);
    }else if(wodContentType == "7"){
        var contentTitle = wodContent.contentTitle;

        var wodContent_content = wodContent.content;
        var sets = 1;
        if(null != wodContent_content && wodContent_content != undefined && wodContent_content != ""){
            var content = eval("("+wodContent_content+")");
            sets = content.sets;
        }
        $("#contentTitle"+wodContentId).html((seriaNum != ""&& seriaNum != undefined? seriaNum+"、":"")
        +(null == contentTitle || undefined == contentTitle || "" == contentTitle ? 'Gymnastics' : contentTitle)
        +(repsScheme != "" && repsScheme != undefined ? "("+repsScheme+")":""));
        $("#contentSaveTitle"+wodContentId).val("");

        //origin
        // if(null == contentTitle || undefined == contentTitle || "" == contentTitle){
        //     $("#contentSaveTitle"+wodContentId).val(contentTitle);
        // }
        //RedCometJ update
        if(null != contentTitle || undefined != contentTitle || "" != contentTitle){
            $("#contentSaveTitle"+wodContentId).val(contentTitle);
        }
        $("#seriaNum"+wodContentId).val(seriaNum);
        $("#contentComment"+wodContentId).val(remark);
        $("#wodSecionCommentShow"+wodContentId).val(remark);
        $("#contentEditDes"+wodContentId).val(descriptioin);
        $("#contentEditReps"+wodContentId).val((repsScheme != "" && repsScheme != undefined ? repsScheme:""));
        $("#contentRecordSet"+wodContentId).val(sets);
    }else if(wodContentType == "8"){
        var contentTitle = wodContent.contentTitle;


        var wodContent_content = wodContent.content;
        var sets = 1;
        var reps = 1;
        if(null != wodContent_content && wodContent_content != undefined && wodContent_content != ""){
            var content = eval("("+wodContent_content+")");
            sets = content.sets;
            reps = content.resps;
        }
        $("#contentTitle"+wodContentId).html((seriaNum != ""&& seriaNum != undefined? seriaNum+"、":"")
        +(null == contentTitle || undefined == contentTitle || "" == contentTitle ? 'Weightliting' : contentTitle)
        +(repsScheme != "" && repsScheme != undefined ? "("+repsScheme+")":""));
        $("#contentSaveTitle"+wodContentId).val("");

        //origin
        // if(null == contentTitle || undefined == contentTitle || "" == contentTitle){
        //     $("#contentSaveTitle"+wodContentId).val(contentTitle);
        // }
        //RedCometJ update
        if(null != contentTitle || undefined != contentTitle || "" != contentTitle){
            $("#contentSaveTitle"+wodContentId).val(contentTitle);
        }
        $("#seriaNum"+wodContentId).val(seriaNum);
        $("#contentComment"+wodContentId).val(remark);
        $("#wodSecionCommentShow"+wodContentId).val(remark);
        $("#contentEditDes"+wodContentId).val(descriptioin);
        $("#contentEditReps"+wodContentId).val((repsScheme != "" && repsScheme != undefined ? repsScheme:""));
        $("#contentRecordSet"+wodContentId).val(sets);
        $("#contentRecordReps"+wodContentId).val(reps);

    }else if(wodContentType == "9"){
        var contentTitle = "Metcon" + "(" + wodContent.contentRecordTypeName + ")";
        var wodContent_content = wodContent.content;
        $("#contentTitle"+wodContentId).html((seriaNum != ""&& seriaNum != undefined? seriaNum+"、":"")+contentTitle);
        $("#contentEditDes"+wodContentId).val(descriptioin);
        $("#seriaNum"+wodContentId).val(seriaNum);
        $("#contentComment"+wodContentId).val(remark);
        $("#wodSecionCommentShow"+wodContentId).val(remark);
        var metconType = 1;
        if(null != wodContent_content && wodContent_content != undefined && wodContent_content != ""){
            var content = eval("("+wodContent_content+")");
            metconType = content.metconType;
            if(metconType == "10"){
                var unit = content.unit;
                var isRx = content.isRx;
                $("#weightRecordUnit"+wodContentId).selectpicker("val",unit);
                $("input[name='isRecord"+wodContentId+"'][value='"+isRx+"']").attr("checked",'checked');
                $("#weightRecordUnit"+wodContentId).parents(".contentDiv").find(".custom_content_general").hide();
                $("#weightRecordUnit"+wodContentId).parents(".contentDiv").find(".custom_content_eachround").hide();
                $("#weightRecordUnit"+wodContentId).parents(".contentDiv").find(".custom_content_distance").hide();
                $("#weightRecordUnit"+wodContentId).parents(".contentDiv").find(".custom_content_weight").show();
                isRxxx = isRx;
            }else if(metconType == "5"){
                var unit = content.unit;
                var isRx = content.isRx;
                var measur = content.measures;
                $("#seriaNum"+wodContentId).parents(".contentDiv").find('.custom_content_general').hide();
                $("#seriaNum"+wodContentId).parents(".contentDiv").find('.custom_content_eachround').show();
                $("#seriaNum"+wodContentId).parents(".contentDiv").find('.custom_content_distance').hide();
                $("#seriaNum"+wodContentId).parents(".contentDiv").find('.custom_content_weight').hide();
                $("#eachRoundB"+wodContentId).val(unit);
                $("#eachRoundRecord"+wodContentId).selectpicker("val",measur);
                $("input[name='isRecord"+wodContentId+"'][value='"+isRx+"']").attr("checked",'checked');

                if(measur == "3"){
                    $("#seriaNum"+wodContentId).parents(".contentDiv").find('.eachRoundWeightSel').show();
                    $("#seriaNum"+wodContentId).parents(".contentDiv").find('.custom_content_weight').show();
                    $("#seriaNum"+wodContentId).parents(".contentDiv").find('.unit').show();
                    $("#eachRoundUnit"+wodContentId).selectpicker("val",content.in);
                }else{
                    $("#seriaNum"+wodContentId).parents(".contentDiv").find('.eachRoundWeightSel').hide();
                }
                if(measur == "4"){
                    $("#seriaNum"+wodContentId).parents(".contentDiv").find('.eachRoundDistanceSel').show();
                    $("#seriaNum"+wodContentId).parents(".contentDiv").find('.custom_content_distance').show();
                    $("#seriaNum"+wodContentId).parents(".contentDiv").find('.unit').show();
                    $("#eachRoundA"+wodContentId).selectpicker("val",content.in);
                }else{
                    $("#seriaNum"+wodContentId).parents(".contentDiv").find('.eachRoundDistanceSel').hide();
                }
                eachRoundUnitType = measur;
                isRxxx = isRx;
            }else if(metconType == "6"){
                var unit = content.unit;
                var isRx = content.isRx;
                $("#distanceRecord"+wodContentId).selectpicker("val",unit);
                $("input[name='distanceRx"+wodContentId+"'][value='"+isRx+"']").attr("checked",'checked');
                $("#distanceRecord"+wodContentId).parents(".contentDiv").find(".custom_content_general").hide();
                $("#distanceRecord"+wodContentId).parents(".contentDiv").find(".custom_content_eachround").hide();
                $("#distanceRecord"+wodContentId).parents(".contentDiv").find(".custom_content_distance").show();
                $("#distanceRecord"+wodContentId).parents(".contentDiv").find(".custom_content_weight").hide();
                isRxxx = isRx;
            }else{
                $("#seriaNum"+wodContentId).parents(".contentDiv").find(".custom_content_general").show();
                $("#seriaNum"+wodContentId).parents(".contentDiv").find(".custom_content_eachround").hide();
                $("#seriaNum"+wodContentId).parents(".contentDiv").find(".custom_content_distance").hide();
                $("#seriaNum"+wodContentId).parents(".contentDiv").find(".custom_content_weight").hide();
            }
        }
        $("#metcon-type"+wodContentId).selectpicker("val",metconType);
    }
    if(null != remark && undefined != remark && "" != remark){
        $("#content"+wodContentId).find('.down1').show();
        $("#content"+wodContentId).find('.comment1').hide();
    }
    return {eachRoundUnitType:eachRoundUnitType,isRx:isRxxx};
}


function queryOperatorRecord(){
    $.ajax({
        type: 'get',
        dataType: 'json',
        jsonp: 'callback',
        url: ctx+'/wod/queryWodRecord',
        data: {wodId:wodId},
        success: function (result) {
            if(null != result){
                var recordHtml = '<h2>记录</h2>';
                for(var i=0; i< result.length; i++){
                    var recordTime = formatDate(new Date(result[i].insertTime),'yyyy-MM-dd HH:mm');
                    var operator = result[i].type == '1' ? '添加':'修改';
                    recordHtml += '<p class="markDown">' + recordTime + '  '
                    + result[i].operatorUser.userName + operator + '该训练计划'
                    + '</p>';
                }
                $("div.record_mark").html(recordHtml);
            }
        },
        error: function () {

        }
    });
}


function formatDate(date, fmt)
{
    date = date == undefined ? new Date() : date;
    date = typeof date == 'number' ? new Date(date) : date;
    fmt = fmt || 'yyyy-MM-dd HH:mm:ss';
    var obj =
    {
        'y': date.getFullYear(), // 年份，注意必须用getFullYear
        'M': date.getMonth() + 1, // 月份，注意是从0-11
        'd': date.getDate(), // 日期
        'q': Math.floor((date.getMonth() + 3) / 3), // 季度
        'w': date.getDay(), // 星期，注意是0-6
        'H': date.getHours(), // 24小时制
        'h': date.getHours() % 12 == 0 ? 12 : date.getHours() % 12, // 12小时制
        'm': date.getMinutes(), // 分钟
        's': date.getSeconds(), // 秒
        'S': date.getMilliseconds() // 毫秒
    };
    var week = ['天', '一', '二', '三', '四', '五', '六'];
    for(var i in obj)
    {
        fmt = fmt.replace(new RegExp(i+'+', 'g'), function(m)
        {
            var val = obj[i] + '';
            if(i == 'w') return (m.length > 2 ? '星期' : '周') + week[val];
            for(var j = 0, len = val.length; j < m.length - len; j++) val = '0' + val;
            return m.length == 1 ? val : val.substring(val.length - m.length);
        });
    }
    return fmt;
}
function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}

function popularChange(obj){
    var wodContentId = $(obj).parents(".contentDiv").find(".contentId").val();
    var contentId = $(obj).val();
    $.ajax({
        type: 'get',
        dataType: 'json',
        jsonp: 'callback',
        url: ctx+'/wod/selectContentMetcon',
        data: {wodContentId:wodContentId,contentId:contentId},
        success: function (result) {
            if(null != result){
                var description = result.description;
                var isRx = result.isRx;
                $("#gmetcondes" + wodContentId).html(description.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," "));
                $("#metconRecord" + wodContentId).html("记录"+result.recordName);
                if(isRx != null && isRx != undefined && isRx == 1){
                    $("input[name='isRecord"+wodContentId+"'][value='1']").prop("checked", "checked");
                }else{
                    $("input[name='isRecord"+wodContentId+"'][value='0']").prop("checked", "checked");
                }
            }
        },
        error: function () {

        }
    });
}

function fouceToLast(obj){
    var t=$(obj).val();
    $(obj).val("").focus().val(t);
}


function sortIndex(){
    $('#sortable').sortable({
        cancel: '.ui-state-disabled',
        delay: 300,
        stop:function(event,ui){
            if(ui.item.hasClass("section-box")){
                var wodSectionId = ui.item.find(".sectionId").val();
                //移动前的上一个wodsectionId
                var allSection = $("#sortable").find("div.section-box");
                var newPreSectionId = $("#sortable").find("div.section-box:first-child").find(".sectionId").val();
                for(var i=0;i<allSection.length;i++){
                    var a = allSection[i];
                    var thisId = a.id;
                    var indexId = $("#"+thisId).find(".sectionId").val();
                    if(indexId == wodSectionId){
                        break;
                    }
                    newPreSectionId = indexId;
                }


                var newContentIds = "";
                var newNextAll = ui.item.nextAll();
                var indexDiv = ui.item;
                for(var i=0;i<newNextAll.length;i++){
                    indexDiv = indexDiv.next();
                    if(!indexDiv.hasClass("contentDiv")){
                        break;
                    }
                    newContentIds += indexDiv.find("input.contentId").val()+",";
                }
                if(newContentIds != ""){
                    newContentIds = newContentIds.substring(0,newContentIds.length-1);
                }
                $.ajax({
                    url:ctx + "/wod/sectionSort",
                    type:'POST', //GET
                    async:true,    //或false,是否异步
                    data:"wodSectionId="+wodSectionId+"&newPreSectionId=" + newPreSectionId + "&newContentIds="+newContentIds,
                    dataType:'text',
                    success:function(data){

                    },
                    error:function(){
                        return false;
                    }
                });
            }else{
                var wodContentId = ui.item.find(".contentId").val();
                var oldWodSectionId = ui.item.find(".contentOfwodsectionId").val();
                //移动的位置的前一个兄弟节点的id
                var newWodSectionId = "";
                //如果前一个节点不是wodSection，获取前一个节点的id
                var preWodContentId = "";
                if(ui.item.prev() != null && ui.item.prev() != undefined){
                    var lastDivId= ui.item.prev().attr("id");
                    if(lastDivId.startsWith("content")){
                        newWodSectionId = ui.item.prev().find(".contentOfwodsectionId").val();
                        preWodContentId = lastDivId.replace("content","");
                    }else if(lastDivId.startsWith("section")){
                        newWodSectionId = ui.item.prev().find(".sectionId").val();
                    }
                }else{
                    newWodSectionId = 0;
                }

                $.ajax({
                    url:ctx + "/wod/contentSort",
                    type:'POST', //GET
                    async:true,    //或false,是否异步
                    data:"wodContentId=" + wodContentId + "&oldWodSectionId="+oldWodSectionId
                    + "&newWodSectionId="+newWodSectionId + "&preWodContentId="+preWodContentId,
                    dataType:'text',
                    success:function(data){

                    },
                    error:function(){
                        return false;
                    }
                });
            }
            queryOperatorRecord();
        }
    });
}