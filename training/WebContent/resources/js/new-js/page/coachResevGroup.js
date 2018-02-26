var ctx = $("#ctx").val();
//RX和RX+只能选一个
function rxSelect(){
    $("span.rx-span").on("click",function(){
        var rxplusObj = $(this).parents('.rx-box').find(".rxp-span");
        var rxplusCanSel =  $(this).parents('.rx-box').find(".rxp-span").attr("canSel") == "1" ? true:false;

        if(!$(this).hasClass("color-orange")){
            $(this).removeClass("color-gray").addClass("color-orange");
            if(rxplusObj.hasClass("color-orange")){
                rxplusObj.removeClass("color-orange").addClass("color-gray");
            }
        }else{
            $(this).removeClass("color-orange").addClass("color-gray");
        }

    });
    $("span.rxp-span").on("click",function(){
        var rxObj = $(this).parents('.rx-box').find(".rx-span");
        var rxplusCanSel = $(this).attr("canSel") == "1" ? true:false;
        if(rxplusCanSel){//如果能选择
            if($(this).hasClass("color-gray")){//如果没被选中、则选中状态
                if(rxObj.hasClass("color-orange")){//如果rx被选中，则恢复
                    rxObj.removeClass("color-orange").addClass("color-gray");
                }
                $(this).removeClass("color-gray").addClass("color-orange");
            }else{
                $(this).removeClass("color-orange").addClass("color-gray");
            }

        }
    });
}

// 点击动作弹框
function actionClick() {
    $('.score').on('click', function () {
        var ctx = $("#ctx").val();
        var type = $(this).parents('.rx-line').attr('type');
        var rxplus = $(this).parents('.rx-line').attr('rxplus');
        var wodId = $(this).parents(".reserOneLi").find("input.wodId").val();
        var courseId = $(this).parents(".reserOneLi").find("input.courseId").val();
        var courseOrderId = $(this).parents(".reserOneLi").find("input.courseOrderId").val();
        var wodContentId = $(this).parents(".rx-line").find("input.wodContentId").val();
        var memberId = $(this).parents(".reserOneLi").find("input.memberId").val();
        var sportResultId = $(this).parents('.rx-line').attr('sportResultId');
        if(sportResultId != ""){
            var content =$(this).parents(".rx-line").find("span.wodContentCon").html();
            $.ajax({
                url:ctx + "/reservation/toEditSportResult",
                type:'POST', //GET
                async:true,    //或false,是否异步
                data:{sportResultId:sportResultId},
                dataType:'json',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success:function(data){
                    if(type == 1){
                        if(rxplus == "1"){
                            $('.time').find("span.rxp-span").attr("canSel","1");
                        }else{
                            $('.time').find("span.rxp-span").attr("canSel","0");
                        }
                        $('.time').find("span.rx").removeClass("color-orange").addClass("color-gray");
                        if(data.isRx == 1){
                            $('.time').find("span.rx-span").removeClass("color-gray").addClass("color-orange");
                        }
                        if(data.isRxPlus == 1){
                            $('.time').find("span.rxp-span").removeClass("color-gray").addClass("color-orange");
                        }
                        $("#timeMinus").val(data.minutes);
                        $("#timeSecons").val(data.seconds);
                        $('.time').find(".recordRemark").val(data.remark != undefined ? data.remark : "");
                        $('.time').find("input.addResultId").val(sportResultId);

                        $('.time').find("input.addWodId").val(wodId);
                        $('.time').find("input.addCourseId").val(courseId);
                        $('.time').find("input.addRecordType").val(type);
                        $('.time').find("input.addCourseOrderId").val(courseOrderId);
                        $('.time').find("input.addWodContentId").val(wodContentId);
                        $('.time').find("input.addMemberId").val(memberId);
                        $('.time').fadeIn();
                    }else if(type == 4){

                        if(rxplus == "1"){
                            $('.amrapRounds').find("span.rxp-span").attr("canSel","1");
                        }else{
                            $('.amrapRounds').find("span.rxp-span").attr("canSel","0");
                        }
                        $('.amrapRounds').find("span.rx").removeClass("color-orange").addClass("color-gray");
                        if(data.isRx == 1){
                            $('.amrapRounds').find("span.rx-span").removeClass("color-gray").addClass("color-orange");
                        }
                        if(data.isRxPlus == 1){
                            $('.amrapRounds').find("span.rxp-span").removeClass("color-gray").addClass("color-orange");
                        }
                        $("#arSets").val(data.sets);
                        $("#arReps").val(data.reps);
                        $('.amrapRounds').find(".recordRemark").val(data.remark != undefined ? data.remark : "");
                        $('.amrapRounds').find("input.addResultId").val(sportResultId);

                        $('.amrapRounds').find("input.addWodId").val(wodId);
                        $('.amrapRounds').find("input.addCourseId").val(courseId);
                        $('.amrapRounds').find("input.addRecordType").val(type);
                        $('.amrapRounds').find("input.addCourseOrderId").val(courseOrderId);
                        $('.amrapRounds').find("input.addWodContentId").val(wodContentId);
                        $('.amrapRounds').find("input.addMemberId").val(memberId);
                        $('.amrapRounds').fadeIn();
                    }else if(type == 11||type == 2){

                        if(rxplus == "1"){
                            $('.gymnastics').find("span.rxp-span").attr("canSel","1");
                        }else{
                            $('.gymnastics').find("span.rxp-span").attr("canSel","0");
                        }
                        $('.gymnastics').find("span.rx").removeClass("color-orange").addClass("color-gray");
                        if(data.isRx == 1){
                            $('.gymnastics').find("span.rx-span").removeClass("color-gray").addClass("color-orange");
                        }
                        if(data.isRxPlus == 1){
                            $('.gymnastics').find("span.rxp-span").removeClass("color-gray").addClass("color-orange");
                        }
                        $("#gymSets").val(data.sets);
                        $("#gymReps").val(data.reps);
                        $('.gymnastics').find(".recordRemark").val(data.remark != undefined ? data.remark : "");
                        $('.gymnastics').find("input.addResultId").val(sportResultId);

                        $('.gymnastics').find("input.addWodId").val(wodId);
                        $('.gymnastics').find("input.addCourseId").val(courseId);
                        $('.gymnastics').find("input.addRecordType").val(type);
                        $('.gymnastics').find("input.addCourseOrderId").val(courseOrderId);
                        $('.gymnastics').find("input.addWodContentId").val(wodContentId);
                        $('.gymnastics').find("input.addMemberId").val(memberId);
                        $('.gymnastics').fadeIn();
                    }else if(type == 3){

                        if(rxplus == "1"){
                            $('.amrap').find("span.rxp-span").attr("canSel","1");
                        }else{
                            $('.amrap').find("span.rxp-span").attr("canSel","0");
                        }
                        $('.amrap').find("span.rx").removeClass("color-orange").addClass("color-gray");

                        if(data.isRx == 1){
                            $('.amrap').find("span.rx-span").removeClass("color-gray").addClass("color-orange");
                        }
                        if(data.isRxPlus == 1){
                            $('.amrap').find("span.rxp-span").removeClass("color-gray").addClass("color-orange");
                        }
                        $("#amReps").val(data.reps);
                        $('.amrap').find(".recordRemark").val(data.remark != undefined ? data.remark : "");
                        $('.amrap').find("input.addResultId").val(sportResultId);

                        $('.amrap').find("input.addWodId").val(wodId);
                        $('.amrap').find("input.addCourseId").val(courseId);
                        $('.amrap').find("input.addRecordType").val(type);
                        $('.amrap').find("input.addCourseOrderId").val(courseOrderId);
                        $('.amrap').find("input.addWodContentId").val(wodContentId);
                        $('.amrap').find("input.addMemberId").val(memberId);
                        $('.amrap').fadeIn();
                    }else if(type == 5){
                        $("#roundMeasure").selectpicker();
                        var contentObj = eval("(" + content + ")");
                        var measure = contentObj.measures;
                        var roundUnit = contentObj.in;
                        var roundNum = contentObj.unit;
                        var roundHtml ='<li class="clearfix">'+
                            '<p class="fl title-left">ROUND</p>'+
                            '<p class="fl title-center">'+getMeasureName(measure)+'</p>'+
                            '<p class="fl title-right">NOTE</p>'+
                            '</li>';
                        var roundJson = eval("(" + data.eachRoundJson + ")");
                        for(var i=0;i<roundJson.length; i++){
                            roundHtml += '<li class="clearfix inLi">'+
                            '<p class="fl title-left">'+ roundJson[i].index +'</p>'+
                            '<input type="text" class="title-center fl numIn" value="'+roundJson[i].num+'">'+
                            '<input type="text" class="title-right fl remarkIn" value="'+roundJson[i].remark+'">'+
                            '</li>';
                        }
                        $("#roundRoundUl").html(roundHtml);

                        if(measure == "3"){
                            var unitOption = '<option value="1">kg</option>'+
                                '<option value="2">lb</option>';
                            $("#roundMeasure").html(unitOption);
                            $("#roundMeasure").selectpicker('val',roundUnit);
                            $(".each-round").find(".unit-box").show();
                        }else if(measure == "4"){
                            var unitOption = '<option value="3">miles</option>'+
                                '<option value="4">meters</option>'+
                                '<option value="5">km</option>'+
                                '<option value="6">yards</option>'+
                                '<option value="7">feet</option>'+
                                '<option value="8">inches</option>'+
                                '<option value="9">cm</option>';
                            $("#roundMeasure").html(unitOption);
                            $("#roundMeasure").selectpicker('val',roundUnit);
                            $(".each-round").find(".unit-box").show();
                        }else{
                            $(".each-round").find(".unit-box").hide();
                        }

                        if(rxplus == "1"){
                            $('.each-round').find("span.rxp-span").attr("canSel","1");
                        }else{
                            $('.each-round').find("span.rxp-span").attr("canSel","0");
                        }
                        $('.each-round').find("span.rx").removeClass("color-orange").addClass("color-gray");

                        if(data.isRx == 1){
                            $('.each-round').find("span.rx-span").removeClass("color-gray").addClass("color-orange");
                        }
                        if(data.isRxPlus == 1){
                            $('.each-round').find("span.rxp-span").removeClass("color-gray").addClass("color-orange");
                        }
                        $('.each-round').find(".recordRemark").val(data.remark != undefined ? data.remark : "");
                        $('.each-round').find("input.addResultId").val(sportResultId);

                        $('.each-round').find("input.addWodId").val(wodId);
                        $('.each-round').find("input.addCourseId").val(courseId);
                        $('.each-round').find("input.addRecordType").val(type);
                        $('.each-round').find("input.addCourseOrderId").val(courseOrderId);
                        $('.each-round').find("input.addWodContentId").val(wodContentId);
                        $('.each-round').find("input.addMemberId").val(memberId);
                        $('.each-round').fadeIn();
                    }else if(type == 6){
                        var contentObj = eval("(" + content + ")");
                        var roundUnit = contentObj.unit;
                        $("#distanceUnitName").html(getRecordUnitName(roundUnit));
                        if(rxplus == "1"){
                            $('.distance').find("span.rxp-span").attr("canSel","1");
                        }else{
                            $('.distance').find("span.rxp-span").attr("canSel","0");
                        }
                        $('.distance').find("span.rx").removeClass("color-orange").addClass("color-gray");

                        if(data.isRx == 1){
                            $('.distance').find("span.rx-span").removeClass("color-gray").addClass("color-orange");
                        }
                        if(data.isRxPlus == 1){
                            $('.distance').find("span.rxp-span").removeClass("color-gray").addClass("color-orange");
                        }
                        $("#meters").val(data.meters);
                        $('.distance').find(".recordRemark").val(data.remark != undefined ? data.remark : "");
                        $('.distance').find("input.addResultId").val(sportResultId);

                        $('.distance').find("input.addWodId").val(wodId);
                        $('.distance').find("input.addCourseId").val(courseId);
                        $('.distance').find("input.addRecordType").val(type);
                        $('.distance').find("input.addCourseOrderId").val(courseOrderId);
                        $('.distance').find("input.addWodContentId").val(wodContentId);
                        $('.distance').find("input.addMemberId").val(memberId);
                        $('.distance').fadeIn();
                    }else if(type == 7){

                        if(rxplus == "1"){
                            $('.calories').find("span.rxp-span").attr("canSel","1");
                        }else{
                            $('.calories').find("span.rxp-span").attr("canSel","0");
                        }
                        $('.calories').find("span.rx").removeClass("color-orange").addClass("color-gray");

                        if(data.isRx == 1){
                            $('.calories').find("span.rx-span").removeClass("color-gray").addClass("color-orange");
                        }
                        if(data.isRxPlus == 1){
                            $('.calories').find("span.rxp-span").removeClass("color-gray").addClass("color-orange");
                        }
                        $("#calories").val(data.calories);
                        $('.calories').find(".recordRemark").val(data.remark != undefined ? data.remark : "");
                        $('.calories').find("input.addResultId").val(sportResultId);

                        $('.calories').find("input.addWodId").val(wodId);
                        $('.calories').find("input.addCourseId").val(courseId);
                        $('.calories').find("input.addRecordType").val(type);
                        $('.calories').find("input.addCourseOrderId").val(courseOrderId);
                        $('.calories').find("input.addWodContentId").val(wodContentId);
                        $('.calories').find("input.addMemberId").val(memberId);
                        $('.calories').fadeIn();
                    }else if(type == 10){

                        if(content != undefined && content != ""){
                            var contentObj = eval("(" + content + ")");
                            $("#weightUnit").selectpicker("val",contentObj.unit);
                        }
                        if(rxplus == "1"){
                            $('.weight').find("span.rxp-span").attr("canSel","1");
                        }else{
                            $('.weight').find("span.rxp-span").attr("canSel","0");
                        }
                        $('.weight').find("span.rx").removeClass("color-orange").addClass("color-gray");

                        if(data.isRx == 1){
                            $('.weight').find("span.rx-span").removeClass("color-gray").addClass("color-orange");
                        }
                        if(data.isRxPlus == 1){
                            $('.weight').find("span.rxp-span").removeClass("color-gray").addClass("color-orange");
                        }
                        $("#weightSets").val(data.sets);
                        $("#weightReps").val(data.reps);
                        $("#weightNum").val(data.weight);
                        $('.weight').find(".recordRemark").val(data.remark != undefined ? data.remark : "");
                        $('.weight').find("input.addResultId").val(sportResultId);

                        $('.weight').find("input.addWodId").val(wodId);
                        $('.weight').find("input.addCourseId").val(courseId);
                        $('.weight').find("input.addRecordType").val(type);
                        $('.weight').find("input.addCourseOrderId").val(courseOrderId);
                        $('.weight').find("input.addWodContentId").val(wodContentId);
                        $('.weight').find("input.addMemberId").val(memberId);
                        $('.weight').fadeIn();
                    }else if(type == 12){
                        if(content != undefined && content != ""){
                            var contentObj = eval("(" + content + ")");
                            //$("#metWeightUnit").selectpicker("val",contentObj.unitType);
                            $("#metWeightUnit").selectpicker();
                            $("#metWeightUnit").selectpicker("val",contentObj.unit);
                        }
                        if(rxplus == "1"){
                            $('.metWeight').find("span.rxp-span").attr("canSel","1");
                        }else{
                            $('.metWeight').find("span.rxp-span").attr("canSel","0");
                        }
                        $('.metWeight').find("span.rx").removeClass("color-orange").addClass("color-gray");

                        if(data.isRx == 1){
                            $('.metWeight').find("span.rx-span").removeClass("color-gray").addClass("color-orange");
                        }
                        if(data.isRxPlus == 1){
                            $('.metWeight').find("span.rxp-span").removeClass("color-gray").addClass("color-orange");
                        }
                        $("#metWeightNum").val(data.weight);
                        $('.metWeight').find(".recordRemark").val(data.remark != undefined ? data.remark : "");
                        $('.metWeight').find("input.addResultId").val(sportResultId);

                        $('.metWeight').find("input.addWodId").val(wodId);
                        $('.metWeight').find("input.addCourseId").val(courseId);
                        $('.metWeight').find("input.addRecordType").val(type);
                        $('.metWeight').find("input.addCourseOrderId").val(courseOrderId);
                        $('.metWeight').find("input.addWodContentId").val(wodContentId);
                        $('.metWeight').find("input.addMemberId").val(memberId);
                        $('.metWeight').fadeIn();
                    }
                },
                error:function(){
                    return false;
                }
            });


        }else{
            var content =$(this).parents(".rx-line").find("span.wodContentCon").html();
            if(type == 1){

                if(rxplus == "1"){
                    $('.time').find("span.rxp-span").attr("canSel","1");
                }else{
                    $('.time').find("span.rxp-span").attr("canSel","0");
                }
                $('.time').find("span.rx").removeClass("color-orange").addClass("color-gray");
                $('.time').find("input.addWodId").val(wodId);
                $('.time').find("input.addCourseId").val(courseId);
                $('.time').find("input.addRecordType").val(type);
                $('.time').find("input.addCourseOrderId").val(courseOrderId);
                $('.time').find("input.addWodContentId").val(wodContentId);
                $('.time').find("input.addMemberId").val(memberId);

                $("#timeMinus").val("");
                $("#timeSecons").val("");
                $('.time').find(".recordRemark").val("");
                $('.time').find("input.addResultId").val("");
                $('.time').fadeIn();
            }else if(type == 4){
                if(rxplus == "1"){
                    $('.amrapRounds').find("span.rxp-span").attr("canSel","1");
                }else{
                    $('.amrapRounds').find("span.rxp-span").attr("canSel","0");
                }
                $('.amrapRounds').find("span.rx").removeClass("color-orange").addClass("color-gray");
                $('.amrapRounds').find("input.addWodId").val(wodId);
                $('.amrapRounds').find("input.addCourseId").val(courseId);
                $('.amrapRounds').find("input.addRecordType").val(type);
                $('.amrapRounds').find("input.addCourseOrderId").val(courseOrderId);
                $('.amrapRounds').find("input.addWodContentId").val(wodContentId);
                $('.amrapRounds').find("input.addMemberId").val(memberId);

                $("#arSets").val("");
                $("#arReps").val("");
                $('.amrapRounds').find(".recordRemark").val( "");
                $('.amrapRounds').find("input.addResultId").val("");
                $('.amrapRounds').fadeIn();
            }else if(type == 11||type == 2){
                if(rxplus == "1"){
                    $('.gymnastics').find("span.rxp-span").attr("canSel","1");
                }else{
                    $('.gymnastics').find("span.rxp-span").attr("canSel","0");
                }
                $('.gymnastics').find("span.rx").removeClass("color-orange").addClass("color-gray");
                $('.gymnastics').find("input.addWodId").val(wodId);
                $('.gymnastics').find("input.addCourseId").val(courseId);
                $('.gymnastics').find("input.addRecordType").val(type);
                $('.gymnastics').find("input.addCourseOrderId").val(courseOrderId);
                $('.gymnastics').find("input.addWodContentId").val(wodContentId);
                $('.gymnastics').find("input.addMemberId").val(memberId);

                $("#gymSets").val("");
                $("#gymReps").val("");
                $('.gymnastics').find(".recordRemark").val( "");
                $('.gymnastics').find("input.addResultId").val("");
                $('.gymnastics').fadeIn();
            }else if(type == 3){

                if(rxplus == "1"){
                    $('.amrap').find("span.rxp-span").attr("canSel","1");
                }else{
                    $('.amrap').find("span.rxp-span").attr("canSel","0");
                }
                $('.amrap').find("span.rx").removeClass("color-orange").addClass("color-gray");
                $('.amrap').find("input.addWodId").val(wodId);
                $('.amrap').find("input.addCourseId").val(courseId);
                $('.amrap').find("input.addRecordType").val(type);
                $('.amrap').find("input.addCourseOrderId").val(courseOrderId);
                $('.amrap').find("input.addWodContentId").val(wodContentId);
                $('.amrap').find("input.addMemberId").val(memberId);

                $("#amReps").val("");
                $('.amrap').find(".recordRemark").val("");
                $('.amrap').find("input.addResultId").val("");
                $('.amrap').fadeIn();
            }else if(type == 5){
                $("#roundMeasure").selectpicker();
                var contentObj = eval("(" + content + ")");
                var measure = contentObj.measures;
                var roundUnit = contentObj.in;
                var roundNum = contentObj.unit;
                var roundHtml ='<li class="clearfix">'+
                    '<p class="fl title-left">ROUND</p>'+
                    '<p class="fl title-center">'+getMeasureName(measure)+'</p>'+
                    '<p class="fl title-right">NOTE</p>'+
                    '</li>';
                for(var i=0;i<roundNum; i++){
                    roundHtml += '<li class="clearfix inLi">'+
                    '<p class="fl title-left">'+ (i+1) +'</p>'+
                    '<input type="text" class="title-center fl numIn">'+
                    '<input type="text" class="title-right fl remarkIn">'+
                    '</li>';
                }
                $("#roundRoundUl").html(roundHtml);
                if(measure == "3"){
                    var unitOption = '<option value="1">kg</option>'+
                        '<option value="2">lb</option>';
                    $("#roundMeasure").html(unitOption);
                    $("#roundMeasure").selectpicker('val',roundUnit);
                    $(".each-round").find(".unit-box").show();
                }else if(measure == "4"){
                    var unitOption = '<option value="3">miles</option>'+
                        '<option value="4">meters</option>'+
                        '<option value="5">km</option>'+
                        '<option value="6">yards</option>'+
                        '<option value="7">feet</option>'+
                        '<option value="8">inches</option>'+
                        '<option value="9">cm</option>';
                    $("#roundMeasure").html(unitOption);
                    $("#roundMeasure").selectpicker('val',roundUnit);
                    $(".each-round").find(".unit-box").show();
                }else{
                    $(".each-round").find(".unit-box").hide();
                }
                if(rxplus == "1"){
                    $('.each-round').find("span.rxp-span").attr("canSel","1");
                }else{
                    $('.each-round').find("span.rxp-span").attr("canSel","0");
                }
                $('.each-round').find("span.rx").removeClass("color-orange").addClass("color-gray");
                $('.each-round').find("input.addWodId").val(wodId);
                $('.each-round').find("input.addCourseId").val(courseId);
                $('.each-round').find("input.addRecordType").val(type);
                $('.each-round').find("input.addCourseOrderId").val(courseOrderId);
                $('.each-round').find("input.addWodContentId").val(wodContentId);
                $('.each-round').find("input.addMemberId").val(memberId);

                $('.each-round').find(".recordRemark").val("");
                $('.each-round').find("input.addResultId").val("");
                $('.each-round').fadeIn();
            }else if(type == 6){
                var content =$(this).parents(".rx-line").find("span.wodContentCon").html();
                var contentObj = eval("(" + content + ")");
                var roundUnit = contentObj.unit;
                $("#distanceUnitName").html(getRecordUnitName(roundUnit));
                if(rxplus == "1"){
                    $('.distance').find("span.rxp-span").attr("canSel","1");
                }else{
                    $('.distance').find("span.rxp-span").attr("canSel","0");
                }
                $('.distance').find("span.rx").removeClass("color-orange").addClass("color-gray");
                $('.distance').find("input.addWodId").val(wodId);
                $('.distance').find("input.addCourseId").val(courseId);
                $('.distance').find("input.addRecordType").val(type);
                $('.distance').find("input.addCourseOrderId").val(courseOrderId);
                $('.distance').find("input.addWodContentId").val(wodContentId);
                $('.distance').find("input.addMemberId").val(memberId);

                $("#meters").val("");
                $('.distance').find(".recordRemark").val("");
                $('.distance').find("input.addResultId").val("");
                $('.distance').fadeIn();
            }else if(type == 7){

                if(rxplus == "1"){
                    $('.calories').find("span.rxp-span").attr("canSel","1");
                }else{
                    $('.calories').find("span.rxp-span").attr("canSel","0");
                }
                $('.calories').find("span.rx").removeClass("color-orange").addClass("color-gray");
                $('.calories').find("input.addWodId").val(wodId);
                $('.calories').find("input.addCourseId").val(courseId);
                $('.calories').find("input.addRecordType").val(type);
                $('.calories').find("input.addCourseOrderId").val(courseOrderId);
                $('.calories').find("input.addWodContentId").val(wodContentId);
                $('.calories').find("input.addMemberId").val(memberId);

                $("#calories").val("");
                $('.calories').find(".recordRemark").val("");
                $('.calories').find("input.addResultId").val("");
                $('.calories').fadeIn();
            }else if(type == 10){
                var content =$(this).parents(".rx-line").find("span.wodContentCon").html();
                if(content != undefined && content != ""){
                    var contentObj = eval("(" + content + ")");
                    $("#weightUnit").selectpicker("val",contentObj.unit);
                }

                if(rxplus == "1"){
                    $('.weight').find("span.rxp-span").attr("canSel","1");
                }else{
                    $('.weight').find("span.rxp-span").attr("canSel","0");
                }
                $('.weight').find("span.rx").removeClass("color-orange").addClass("color-gray");
                $('.weight').find("input.addWodId").val(wodId);
                $('.weight').find("input.addCourseId").val(courseId);
                $('.weight').find("input.addRecordType").val(type);
                $('.weight').find("input.addCourseOrderId").val(courseOrderId);
                $('.weight').find("input.addWodContentId").val(wodContentId);
                $('.weight').find("input.addMemberId").val(memberId);

                $("#weightSets").val("");
                $("#weightReps").val("");
                $("#weightNum").val("");
                $('.weight').find(".recordRemark").val("");
                $('.weight').find("input.addResultId").val("");
                $('.weight').fadeIn();
            }else if(type == 12){
                var content =$(this).parents(".rx-line").find("span.wodContentCon").html();
                if(content != undefined && content != ""){
                    var contentObj = eval("(" + content + ")");
                    $("#metWeightUnit").selectpicker();
                    $("#metWeightUnit").selectpicker("val",contentObj.unit);
                }

                if(rxplus == "1"){
                    $('.metWeight').find("span.rxp-span").attr("canSel","1");
                }else{
                    $('.metWeight').find("span.rxp-span").attr("canSel","0");
                }
                $('.metWeight').find("span.rx").removeClass("color-orange").addClass("color-gray");
                $('.metWeight').find("input.addWodId").val(wodId);
                $('.metWeight').find("input.addCourseId").val(courseId);
                $('.metWeight').find("input.addRecordType").val(type);
                $('.metWeight').find("input.addCourseOrderId").val(courseOrderId);
                $('.metWeight').find("input.addWodContentId").val(wodContentId);
                $('.metWeight').find("input.addMemberId").val(memberId);

                $("#metWeightNum").val("");
                $('.metWeight').find(".recordRemark").val("");
                $('.metWeight').find("input.addResultId").val("");
                $('.metWeight').fadeIn();
            }
        }




    })
}


function addCancleInit(){
    $(".cancel-btn").on("click",function(){
        $(this).parents(".add").fadeOut();
    });
}

function addButInit(){
    //确定记录方式类型
    $(".save-btn").on("click",function(){
        var ctx = $("#ctx").val();
        var addModal = $(this).parents(".add");
        var recordType = $(this).parents(".add").find("input.addRecordType").val();
        var wodId = $(this).parents(".add").find("input.addWodId").val();
        var courseId = $(this).parents(".add").find("input.addCourseId").val();
        var courseOrderId = $(this).parents(".add").find("input.addCourseOrderId").val();
        var memberId = $(this).parents(".add").find("input.addMemberId").val();
        var isRx = $(this).parents(".add").find(".rx-span").hasClass("color-orange") ? 1:0;
        var isRxPlus = $(this).parents(".add").find(".rxp-span").hasClass("color-orange") ? 1:0;
        var comment = $(this).parents(".add").find(".recordRemark").val();
        var wodContentId = $(this).parents(".add").find(".addWodContentId").val();
        var sportResultId = $(this).parents(".add").find(".addResultId").val();
        var json = "";
        if(recordType == "1"){//time
            var timeMinus = $("#timeMinus").val();
            var timeSecons = $("#timeSecons").val();
            if("" == timeMinus || timeSecons == ""){
                alert("请输入成绩");
                return false;
            }
            if(isNaN(timeMinus) || isNaN(timeSecons)){
                alert("请输入正确成绩");
                return false;
            }
            json = {recordType:recordType,wodId:wodId,courseId:courseId,courseOrderId:courseOrderId,wodContentId:wodContentId,sportResultId:sportResultId,
                memberId:memberId,isRx:isRx,isRxPlus:isRxPlus,comment:comment,minutes:timeMinus,seconds:timeSecons};
        }else if(recordType == 4){
            var arSets = $("#arSets").val();
            var arReps = $("#arReps").val();
            if(arSets == "" || isNaN(arSets)){
                alert("请输入正确的sets");
                return false;
            }
            if(arReps == "" || isNaN(arReps)){
                alert("请输入正确的reps");
                return false;
            }
            json = {recordType:recordType,wodId:wodId,courseId:courseId,courseOrderId:courseOrderId,wodContentId:wodContentId,sportResultId:sportResultId,
                memberId:memberId,isRx:isRx,isRxPlus:isRxPlus,comment:comment,sets:arSets,reps:arReps};
        } else if(recordType == 11||recordType == 2){//gymnastics
            var gymSets = $("#gymSets").val();
            var gymReps = $("#gymReps").val();
            if(gymSets == "" || isNaN(gymSets)){
                alert("请输入正确的sets");
                return false;
            }
            if(gymReps == "" || isNaN(gymReps)){
                alert("请输入正确的reps");
                return false;
            }
            json = {recordType:recordType,wodId:wodId,courseId:courseId,courseOrderId:courseOrderId,wodContentId:wodContentId,sportResultId:sportResultId,
                memberId:memberId,isRx:isRx,isRxPlus:isRxPlus,comment:comment,sets:gymSets,reps:gymReps};
        }else if(recordType == 3){//amrap
            var amReps = $("#amReps").val();
            if(amReps == "" || isNaN(amReps)){
                alert("请输入正确的reps");
                return false;
            }
            json = {recordType:recordType,wodId:wodId,courseId:courseId,courseOrderId:courseOrderId,wodContentId:wodContentId,sportResultId:sportResultId,
                memberId:memberId,isRx:isRx,isRxPlus:isRxPlus,comment:comment,reps:amReps};
        }else if(recordType == 5){//each-round
            var roundJson = '[';
            var roundNum = $(this).parents(".add").find(".numIn").length;
            var flag = true;
            $(this).parents(".add").find(".inLi").each(function(i){
                var inNum = $(this).find(".numIn").val();
                var inRemark = $(this).find(".remarkIn").val();
                if(inNum == "" || isNaN(inNum)){
                    alert("第" + (i+1) + "组请输入合理的数据");
                    flag = false;
                    return false;
                }
                roundJson += '{'+
                '"index":' + (i+1) + ','+
                '"num":' + inNum + ',' +
                '"remark":"' + inRemark + '"},';
            });
            if(!flag){
                return false;
            }
            if(roundJson.length > 1){
                roundJson = roundJson.substring(0,roundJson.length-1);
            }
            roundJson += ']';
            var unitSel = $(".each-round").find(".unit-box").is(":hidden");
            var roundUnit = 0;
            if(!unitSel){
                roundUnit = $("#roundMeasure").val();
            }
            json = {recordType:recordType,wodId:wodId,courseId:courseId,courseOrderId:courseOrderId,wodContentId:wodContentId,sportResultId:sportResultId,
                memberId:memberId,isRx:isRx,isRxPlus:isRxPlus,comment:comment,roundJson:roundJson,unit:roundUnit};
        }else if(recordType == 6) {//distance
            var meters = $("#meters").val();
            if (meters == "" || isNaN(meters)) {
                alert("请输入正确的meters");
                return false;
            }
            json = {recordType:recordType,wodId:wodId,courseId:courseId,courseOrderId:courseOrderId,wodContentId:wodContentId,sportResultId:sportResultId,
                memberId:memberId,isRx:isRx,isRxPlus:isRxPlus,comment:comment,meters:meters};
        }else if(recordType == 7) {//calories
            var calories = $("#calories").val();
            if (calories == "" || isNaN(calories)) {
                alert("请输入正确的calories");
                return false;
            }
            json = {recordType:recordType,wodId:wodId,courseId:courseId,courseOrderId:courseOrderId,wodContentId:wodContentId,sportResultId:sportResultId,
                memberId:memberId,isRx:isRx,isRxPlus:isRxPlus,comment:comment,calories:calories};
        }else if(recordType == 10) {//weight
            var weightSets = $("#weightSets").val();
            var weightReps = $("#weightReps").val();
            var weightNum = $("#weightNum").val();
            var weightUnit = $("#weightUnit").val();
            if (weightSets == "" || isNaN(weightSets)) {
                alert("请输入正确的sets");
                return false;
            }
            if (weightReps == "" || isNaN(weightReps)) {
                alert("请输入正确的reps");
                return false;
            }
            if (weightNum == "" || isNaN(weightNum)) {
                alert("请输入正确的weight");
                return false;
            }
            json = {recordType:recordType,wodId:wodId,courseId:courseId,courseOrderId:courseOrderId,wodContentId:wodContentId,sportResultId:sportResultId,
                memberId:memberId,isRx:isRx,isRxPlus:isRxPlus,comment:comment,
                sets:weightSets,reps:weightReps,weight:weightNum,unit:weightUnit};
        }else if(recordType == 12) {//met-weight
            var weightNum = $("#metWeightNum").val();
            var weightUnit = $("#metWeightUnit").val();
            if (weightNum == "" || isNaN(weightNum)) {
                alert("请输入正确的weight");
                return false;
            }
            json = {recordType:recordType,wodId:wodId,courseId:courseId,courseOrderId:courseOrderId,wodContentId:wodContentId,sportResultId:sportResultId,
                memberId:memberId,isRx:isRx,isRxPlus:isRxPlus,comment:comment,
                weight:weightNum,unit:weightUnit};
        }
        //如果记录成绩并且选了rx
        if(isRx){
            $("input.wodContentId[value='"+wodContentId+"']").parents(".rx-line").find(".show-rx").removeClass("color-gray").addClass("color-blue");
        }else{
            $("input.wodContentId[value='"+wodContentId+"']").parents(".rx-line").find(".show-rx").removeClass("color-blue").addClass("color-gray");
        }
        var detailImgObj = $("input.wodContentId[value='"+wodContentId+"']").parents(".rx-line").find("img.showPop");
        $(this).parents(".add").fadeOut();
        $.ajax({
            url:ctx + "/reservation/addSportResult",
            type:'post', //GET
            async:true,    //或false,是否异步
            data:json,
            dataType:'json',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success:function(data){
                if(data.hasDetail == "yes"){
                    $("input.wodContentId[value='"+wodContentId+"']").parents(".rx-line").find("img.showPop").show();
                    $("input.wodContentId[value='"+wodContentId+"']").parents(".rx-line").find("img.showPop").attr("contentid",data.contentId)
                }
                $("input.wodContentId[value='"+wodContentId+"']").parents(".rx-line").attr("sportResultId",data.id);
                getOrderList();
            },
            error:function(){
                return false;
            }
        });

    });

}



//each-round获取measure名字
function getMeasureName(measure){
    if(measure == "1"){
        return "REPS";
    }else if(measure == "2"){
        return "TIME";
    }else if(measure == "3"){
        return "WEIGHT";
    }else if(measure == "4"){
        return "DISTANCE";
    }else if(measure == "5"){
        return "CALORIES";
    }
}

function signInit(){
    $("span.sign-but").on("click",function(){
        var ctx = $("#ctx").val();
        var isHasSign = $(this).hasClass("color-orange");
        var courseOrderId = $(this).parents(".reserOneLi").find("input.courseOrderId").val();
        if(isHasSign){
            if(confirm("是否取消签到?")){
                $(this).removeClass("color-orange").addClass("color-toSign").html("签到");
                $.ajax({
                    url:ctx + "/reservation/sign",
                    type:'POST', //GET
                    async:true,    //或false,是否异步
                    data:{courseOrderId:courseOrderId,signStatus:0},
                    dataType:'json',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success:function(data){
                    },
                    error:function(){
                        return false;
                    }
                });
            }
        }else{
            if(confirm("是否签到?")){
                $(this).removeClass("color-toSign").addClass("color-orange").html("已签到");
                $.ajax({
                    url:ctx + "/reservation/sign",
                    type:'POST', //GET
                    async:true,    //或false,是否异步
                    data:{courseOrderId:courseOrderId,signStatus:1},
                    dataType:'json',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success:function(data){
                    },
                    error:function(){
                        return false;
                    }
                });
            }
        }
    });
}


//教练签到
function coachSignInit(){
    $(".coach-sign").on("click",function(){
        var ctx = $("#ctx").val();
        var isSign = $(this).hasClass("color-orange");
        var boxId = $("#boxId").val();
        var courseId = $("#course").val();
        var date = $(".form_datetime").val();
        if(!isSign){
            if(confirm("确定签到?")){
                $(this).removeClass("color-toSign").addClass("color-orange").html("已签到");
                $.ajax({
                    type: 'post',
                    dataType: 'json',
                    url: ctx +'/coachBackReser/groupCoachSign',
                    data: {
                        date: date,
                        courseId:courseId,
                        boxId:boxId,
                        status:1
                    },
                    success: function (result) {

                    },
                    error: function () {}
                })
            }
        }else{
            if(confirm("确定取消签到?")){
                $(this).removeClass("color-orange").addClass("color-toSign").html("签到");
                $.ajax({
                    type: 'post',
                    dataType: 'json',
                    url: ctx +'/coachBackReser/groupCoachSign',
                    data: {
                        date: date,
                        courseId:courseId,
                        boxId:boxId,
                        status:2
                    },
                    success: function (result) {

                    },
                    error: function () {}
                })
            }
        }
    });
}

function getRecordUnitName(unit){
    if(unit == '1'){
        return 'KG';
    }else if(unit == '2'){
        return 'LB';
    }else if(unit == '3'){
        return 'METERS';
    }else if(unit == '4'){
        return 'MILES';
    }else if(unit == '5'){
        return 'KM';
    }else if(unit == '6'){
        return 'YARDS';
    }else if(unit == '7'){
        return 'FEET';
    }else if(unit == '8'){
        return 'INCHES';
    }else if(unit == '9'){
        return 'CM';
    }

}