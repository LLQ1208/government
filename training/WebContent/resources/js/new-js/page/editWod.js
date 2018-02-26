function selectToAddSection(data,title){
    var sectionDiv =
        '<div class="section-box ui-state-default" id="section'+data+'">'+
            //查看section html
            '<div class="metcon clearfix metconA sectionShow">'+
                '<h3 class="changeTitle" id="secionTitle'+data+'">' + title + '</h3>'+
                    //内容
                '<p class="p1" id="wodSecionCommentShow'+data+'"></p>'+
            '</div>'+
            //编辑 html
            '<div class="addContent addContentA clearfix sectionEdit">'+
                '<div class="addSection clearfix">'+
                    '<div class="middle clearfix sectionShow">'+
                        '<div class="middleLeft clearfix single">'+
                            '<div class="sectionBtn sectionBtnsingle">'+
                                '<div class="cancle fl">取消</div>'+
                                '<div class="confirm fl">保存</div>'+
                            '</div>'+
                            '<div class="chodse clearfix">'+
                                '<select  class="clean singleSel" id="sectionSel'+data+'">'+getSectoinSelectHtml()+'</select>'+
                            '</div>'+
                            '<div class="clearfix number singleNumber">TYPE</div>'+
                            '<div class="comment comment3 singlecomment sectionCommentTitle" ><span class="jia"></span> COMMENT</div>'+
                            '<div class="down2 down">'+
                                '<div class="weight">'+
                                    '<textarea class="weightT singleweightT" id="sectionComment'+data+'" onclick="this.focus()"></textarea>'+
                                    '<span class="circle circle2"></span>'+
                                '</div>'+
                            '<div class="comment comment2 singlecomment">COMMENT</div>'+
                            '</div>'+
                        '</div>'+
                        '<div class="addSectionCancel"> 删除</div>'+
                    '</div>'   +
                '</div>'+
                '<input type="hidden" value="'+data+'" class="sectionId">'+
            '</div>';
    return sectionDiv;
}

//获取当前页面section下拉框list
function getSectoinSelectHtml(){
    var sectioHtml= '';
    var section = $("#section").val();
    $("#section option").each(function(i){
        if(i != 0){
            var id = $(this).val();
            var text = $(this).text();
            sectioHtml += '<option value="'+id+'">'+text+'</option>';
        }
    });
    return sectioHtml;
}

//获取当前页面section下拉框list
function getGenerContentSelectHtml(type){
    var contentHtml= '';
    $("#content option").each(function(i){
        if(i != 0){
            var id = $(this).val();
            var text = $(this).text();
            var thisType = $(this).attr("contentType");
            if(type == thisType){
                contentHtml += '<option value="'+id+'" contentType="'+type+'">'+text+'</option>';
            }
        }
    });
    return contentHtml;
}

//warm-up—html
function content_warmUp(wodContent){
    var wodContentId = wodContent.wodContentId;
    var descriptioin = wodContent.descript;
    var indexNum = wodContent.tIndex;
    var wodContentTitle = wodContent.contentEntity.name;
    var wodContentType = wodContent.contentType;
    var wodSectionId = wodContent.wodSectionId;
    var warmUpHtml =
         '<div class="addOne custon-weightlifting ui-state-default contentDiv" id="content'+wodContentId+'">'+
            //展示
            '<div class="metcon clearfix metconA" >'+
                '<h3 class="changeTitle"  id="contentTitle'+wodContentId+'">'+wodContentTitle+'</h3>'+
                //'<p class="p1" id="contentShowDes'+wodContentId+'">'+descriptioin+'</p>'+
                '<p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow'+wodContentId+'"></p>'+
            '</div>'+
            //编辑
            '<div class="addContent addContentA clearfix">'+
                '<div class="addSection clearfix">'+
                    '<div class="sectionBtn">'+
                        '<div class="cancle fl">取消</div>'+
                        '<div class="confirm fl">保存</div>'+
                    '</div>'+
                    '<div class="middle clearfix">'+
                        '<div class="middleLeft clearfix fl">'+
                            '<div class="title">Content</div>'+
                            '<div class="chodse clearfix">'+
                                '<input type="text" maxlength="5" class="seria_num" onclick="fouceToLast(this)" value="" id="seriaNum'+wodContentId+'">'+
                                '<select  class="clean" id="contentSel'+wodContentId+'">'+
                                    getGenerContentSelectHtml(wodContentType)+
                                '</select>'+
                            '</div>'+
                            '<div class="clearfix number">'+
                                '<p class="number1 fl" >序号</p>'+
                                '<p class="number2 fl" >WARM-UP</p>'+
                            '</div>'+
                            '<div class="comment comment1">'+
                                '<span class="jia"></span> COMMENT'+
                            '</div>'+
                            '<div class="down1 down">'+
                                '<div class="weight">'+
                                    '<textarea class="weightT" onclick="this.focus()" id="contentComment'+wodContentId+'"></textarea>'+
                                    '<span class="circle circle1"></span>'+
                                '</div>'+
                                '<div class="comment comment2">'+
                                    'COMMENT'+
                                '</div>'+
                            '</div>'+
                        '</div>'+
                        '<div class="middleright fl clearfix">'+
                            '<div class="title">记录方式</div>'+
                            '<p class="recode">记录no measure</p>'+
                        '</div>'+
                    '</div>'+
                    '<div class="addSectionCancel"> 删除</div>'+
                '</div>'+
            '</div>'+
            '<input type="hidden" value="'+wodContentId+'" class="contentId">'+
            '<input type="hidden" value="'+wodContentType+'" class="wodContentType">'+
             '<input type="hidden" value="'+wodSectionId+'" class="contentOfwodsectionId">'+
             '<input type="hidden" value="" id="contentNum'+wodSectionId+'">'+
        '</div>';
    return warmUpHtml;
}

//Weightlifting content
function content_weight(wodContent){
    var wodContentId = wodContent.wodContentId;
    var descriptioin = wodContent.descript;
    var indexNum = wodContent.tIndex;
    var wodContentTitle = wodContent.contentEntity.name;
    var wodContentType = wodContent.contentType;
    var wodContent_content = wodContent.content;
    var wodSectionId = wodContent.wodSectionId;
    var sets =1;
    var reps = 1;
    if(wodContent_content != null && wodContent_content != undefined && wodContent_content != ""){
        var content = eval("("+wodContent_content+")");
        sets = content.sets;
        reps = content.reps;
    }
    var weightliffHtml =
        '<div class="addOne weightlifting ui-state-default contentDiv" id="content'+wodContentId+'">'+
            //展示
            '<div class="metcon clearfix metconA" >'+
                '<h3 class="changeTitle" id="contentTitle'+wodContentId+'">'+wodContentTitle+'</h3>'+
                //'<p class="p1" id="contentShowDes'+wodContentId+'">'+descriptioin+'</p>'+
                '<p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow'+wodContentId+'"></p>'+
            '</div>'+
            //编辑
            '<div class="addContent addContentA clearfix">'+
                '<div class="addSection clearfix">'+
                    '<div class="sectionBtn">'+
                        '<div class="cancle fl">取消</div>'+
                        '<div class="confirm fl">保存</div>'+
                    '</div>'+
                    '<div class="middle clearfix">'+
                        '<div class="middleLeft clearfix fl">'+
                            '<div class="title">Content</div>'+
                            '<div class="chodse clearfix">'+
                                '<input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)" id="seriaNum'+wodContentId+'" value="">'+
                                '<select  class="clean" id="contentSel'+wodContentId+'">'+
                                    getGenerContentSelectHtml(wodContentType)+
                                '</select>'+
                                //'<input style="width: 308px; margin: 0; text-align: left;" type="text" maxlength="100" placeholder="Title this WEIGHTLIFTING" onclick="fouceToLast(this)" id="contentEditDes'+wodContentId+'">'+
                            '</div>'+
                            '<div class="clearfix number">'+
                                '<p class="number1 fl">序号</p>'+
                                '<p class="number2 fl">WEIGHTLIFTING</p>'+
                            '</div>'+
                            '<input type="text" class="set5" placeholder="" maxlength="50" onclick="fouceToLast(this)" placeholder="Title this WEIGHTLIFTING" value="" id="contentEditReps'+wodContentId+'">'+
                            '<p class="rep">REP SCHEME</p>'+
                            '<div class="comment comment1">'+
                                '<span class="jia"></span> COMMENT'+
                            '</div>'+
                            '<div class="down1 down">'+
                                '<div class="weight">'+
                                    '<textarea class="weightT" onclick="fouceToLast(this)" id="contentComment'+wodContentId+'"></textarea>'+
                                    '<span class="circle circle1"></span>'+
                                '</div>'+
                                '<div class="comment comment2">'+
                                    'COMMENT'+
                                '</div>'+
                            '</div>'+
                        '</div>'+
                        '<div class="middleright fl clearfix">'+
                            '<div class="title">记录方式</div>'+
                            '<p class="recode">记录WEIGHT</p>'+
                            '<div class="in clearfix">'+
                                '<span class="spanin">in</span>'+
                                '<select class="clean1" id="contentRecordUnit'+wodContentId+'">'+
                                    '<option value="1">kg</option>'+
                                    '<option value="2">lb</option>'+
                                '</select>'+
                            '</div>'+
                            '<div class="for clearfix">'+
                                '<span class="spanfor">for</span>'+
                                '<input class="clean1" type="text" min="1" value="'+sets+'" onclick="fouceToLast(this)" id="contentRecordSet'+wodContentId+'">'+
                                '<span class="x">x</span>'+
                                '<input type="text" value="'+reps+'" min="1" onclick="fouceToLast(this)" id="contentRecordReps'+wodContentId+'">'+
                            '</div>'+
                            '<div class="sets">'+
                                '<span class="ref">SETS</span>'+
                                '<span class="ref">REPS</span>'+
                            '</div>'+
                        '</div>'+
                    '</div>'+
                '<div class="addSectionCancel"> 删除</div>'+
            '</div>'+
        '</div>'+
        '<input type="hidden" value="'+wodContentId+'" class="contentId">'+
        '<input type="hidden" value="'+wodContentType+'" class="wodContentType">'+
        '<input type="hidden" value="'+wodSectionId+'" class="contentOfwodsectionId">'+
        '<input type="hidden" value="" id="contentNum'+wodSectionId+'">'+
    '</div>';
    return weightliffHtml;
}

//Gymnastics content
function content_gymnastics(wodContent){
    var wodContentId = wodContent.wodContentId;
    var descriptioin = wodContent.descript;
    var indexNum = wodContent.tIndex;
    var wodContentTitle = wodContent.contentEntity.name;
    var wodContentType = wodContent.contentType;
    var wodContent_content = wodContent.content;
    var wodSectionId = wodContent.wodSectionId;
    var sets =1;
    if(wodContent_content != null && wodContent_content != undefined && wodContent_content != ""){
        var content = eval("("+wodContent_content+")");
        sets = content.sets;
    }
    var grmasticsHtml =
        '<div class="addOne custom-gymnastics ui-state-default contentDiv" id="content'+wodContentId+'">'+
            //查看
            '<div class="metcon clearfix metconA" >'+
                '<h3 class="changeTitle"  id="contentTitle'+wodContentId+'">'+wodContentTitle+'</h3>'+
                //'<p class="p1" id="contentShowDes'+wodContentId+'">'+descriptioin+'</p>'+
                '<p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow'+wodContentId+'"></p>'+
            '</div>'+
            '<div class="addContent addContentA clearfix">'+
                '<div class="addSection clearfix ">'+
                    '<div class="sectionBtn">'+
                        '<div class="cancle fl">取消</div>'+
                        '<div class="confirm fl">保存</div>'+
                    '</div>'+
                    '<div class="middle clearfix">'+
                        '<div class="middleLeft clearfix fl">'+
                            '<div class="title">Content</div>'+
                            '<div class="chodse clearfix">'+
                                '<input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)" id="seriaNum'+wodContentId+'" value=""> '+
                                '<select  class="clean" id="contentSel'+wodContentId+'">'+
                                    getGenerContentSelectHtml(wodContentType)+
                                '</select>'+
                            '</div>'+
                            '<div class="clearfix number">'+
                                '<p class="number1 fl">序号</p>'+
                                '<p class="number2 fl">GYMNASTICS</p>'+
                            '</div>'+
                            '<input type="text" class="set5" value="" onclick="fouceToLast(this)" value="" id="contentEditReps'+wodContentId+'">'+
                            '<p class="rep">REP SCHEME</p>'+
                            '<div class="comment comment1">'+
                                '<span class="jia"></span> COMMENT'+
                            '</div>'+
                            '<div class="down1 down">'+
                                '<div class="weight">'+
                                    '<textarea class="weightT" onclick="this.focus()"  id="contentComment'+wodContentId+'"></textarea>'+
                                    '<span class="circle circle1"></span>'+
                                '</div>'+
                                '<div class="comment comment2">'+
                                    'COMMENT'+
                                '</div>'+
                            '</div>'+
                        '</div>'+
                        '<div class="middleright fl clearfix">'+
                            '<div class="title">记录方式</div>'+
                            '<p class="recode">记录REPS</p>'+
                            '<div class="for clearfix for3">'+
                                '<span class="spanfor">for</span>'+
                                '<input type="text" value="'+sets+'" min="1" onclick="fouceToLast(this)" id="contentRecordSet'+wodContentId+'">'+
                                '<span class="x">x</span>'+
                                '<input type="text" disabled value="MAX" onclick="fouceToLast(this)">'+
                            '</div>'+
                            '<div class="sets">'+
                                '<span class="ref">SETS</span>'+
                                '<span class="ref">REPS</span>'+
                            '</div>'+
                        '</div>'+
                    '</div>'+
                    '<div class="addSectionCancel"> 删除</div>'+
                '</div>'+
            '</div>'+
            '<input type="hidden" value="'+wodContentId+'" class="contentId">'+
            '<input type="hidden" value="'+wodContentType+'" class="wodContentType">'+
            '<input type="hidden" value="'+wodSectionId+'" class="contentOfwodsectionId">'+
            '<input type="hidden" value="" id="contentNum'+wodSectionId+'">'+
        '</div>';
    return grmasticsHtml;
}

//metcon content
function content_meton(wodContent){
    var wodContentId = wodContent.wodContentId;
    var descriptioin = wodContent.contentEntity.description;
    var indexNum = wodContent.tIndex;
    var wodContentTitle = wodContent.contentEntity.name;
    var wodContentType = wodContent.contentType;
    var wodContent_content = wodContent.content;
    var wodSectionId = wodContent.wodSectionId;
    var contentRecordTypeName = wodContent.contentRecordTypeName;
    var isRx =0;
    if(wodContent_content != null && wodContent_content != undefined && wodContent_content != ""){
        var content = eval("("+wodContent_content+")");
        isRx = content.isRx;
    }else{
        if(wodContent.contentEntity.isRx != null && wodContent.contentEntity.isRx != undefined){
            isRx =  wodContent.contentEntity.isRx;
        }else{
            isRx = 0;
        }
    }
    var metconHtml =
        '<div class="addOne metcon-box ui-state-default contentDiv" id="content'+wodContentId+'">'+
            '<div class="metcon clearfix metconA" >'+
                '<h3 class="changeTitle"  id="contentTitle'+wodContentId+'">'+wodContentTitle + '(' + contentRecordTypeName + ')'+'</h3>'+
                '<p class="p1" id="contentShowDes'+wodContentId+'">'+(descriptioin != null && descriptioin != undefined ? descriptioin.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '')+'</p>'+
                '<p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow'+wodContentId+'"></p>'+
            '</div>'+
            '<div class="addContent addContentA clearfix">'+
                '<div class="addSection clearfix ">'+
                    '<div class="sectionBtn">'+
                        '<div class="cancle fl">取消</div>'+
                        '<div class="confirm fl">保存</div>'+
                    '</div>'+
                    '<div class="middle clearfix">'+
                        '<div class="middleLeft clearfix fl">'+
                            '<div class="title">Content</div>'+
                            '<div class="chodse clearfix">'+
                                '<input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)" id="seriaNum'+wodContentId+'" value="">'+
                                '<select  class="clean" id="contentSel'+wodContentId+'" onchange="popularChange(this)">'+
                                    getGenerContentSelectHtml(wodContentType)+
                                '</select>'+
                            '</div>'+
                            '<div class="clearfix number">'+
                                '<p class="number1 fl">序号</p>'+
                                '<p class="number2 fl">METCON</p>'+
                            '</div>'+
                            '<div style="height: auto;margin-left: 107px;"class="clearfix number">'+
                                '<p style="text-indent:0px" class="write" id="gmetcondes'+wodContentId+'">'+(descriptioin != null && descriptioin != undefined ? descriptioin.replace(/(\r)*\n/g,"<br/>").replace(/\s/g," ") : '')+'</p>'+
                            '</div>'+
                            '<div class="comment comment1">'+
                                '<span class="jia"></span> COMMENT'+
                            '</div>'+
                            '<div class="down1 down">'+
                                '<div class="weight">'+
                                    '<textarea class="weightT" onclick="fouceToLast(this)" id="contentComment'+wodContentId+'"></textarea>'+
                                    '<span class="circle circle1"></span>'+
                                '</div>'+
                                '<div class="comment comment2">'+
                                    'COMMENT'+
                                '</div>'+
                            '</div>'+
                        '</div>'+
                        '<div class="middleright fl clearfix">'+
                            '<div class="title">记录方式</div>'+
                            '<div class="recode">'+
                                '<span id="metconRecord'+wodContentId+'">记录'+contentRecordTypeName+'</span>'+
                            '</div>'+
                            '<div class="rx">'+
                                '<span>是否Rx+？</span>'+
                                '<input type="radio" value ="1" id="isRecord'+wodContentId+'" name="isRecord'+wodContentId+'" '+(isRx == 1 ? 'checked="true"':'')+'>是'+
                                '<input type="radio" value ="0" id="noRecord'+wodContentId+'"name="isRecord'+wodContentId+'" '+(isRx == 0 ? 'checked="true"':'')+' >否'+
                            '</div>'+
                        '</div>'+
                    '</div>'+
                    '<div class="addSectionCancel"> 删除</div>'+
                '</div>'+
            '</div>'+
            '<input type="hidden" value="'+wodContentId+'" class="contentId">'+
            '<input type="hidden" value="'+wodContentType+'" class="wodContentType">'+
            '<input type="hidden" value="'+wodSectionId+'" class="contentOfwodsectionId">'+
            '<input type="hidden" value="" id="contentNum'+wodSectionId+'">'+
        '</div>';
    return metconHtml;
}
//自定义 custom-metcon
function custom_weightlifting(wodContent){
    var wodContentId = wodContent.wodContentId;
    var indexNum = wodContent.tIndex;
    var wodContentType = wodContent.contentType;
    var wodSectionId = wodContent.wodSectionId;
    var custonWeightHtml =
        '<div class="addOne custon-weightlifting ui-state-default contentDiv" id="content'+wodContentId+'">'+
            '<div class="metcon clearfix metconA" >'+
                '<h3 class="changeTitle"  id="contentTitle'+wodContentId+'">Weightliting </h3>'+
                '<p class="p1" id="contentShowDes'+wodContentId+'"></p>'+
                '<p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow'+wodContentId+'"></p>'+
            '</div>'+
            '<div class="addContent addContentA clearfix">'+
                '<div class="addSection clearfix">'+
                    '<div class="sectionBtn">'+
                        '<div class="cancle fl">取消</div>'+
                        '<div class="confirm fl">保存</div>'+
                    '</div>'+
                    '<div class="middle clearfix">'+
                        '<div class="middleLeft clearfix fl">'+
                            '<div class="title">Content</div>'+
                            '<div class="chodse clearfix">'+
                                '<input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)" id="seriaNum'+wodContentId+'" value="">'+
                                '<input style="width: 308px; margin: 0; text-align: left;" type="text" maxlength="100" placeholder="Title this WEIGHTLIFTING" onclick="fouceToLast(this)" id="contentSaveTitle'+wodContentId+'">'+
                            '</div>'+
                            '<div class="clearfix number">'+
                                '<p class="number1 fl">序号</p>'+
                                '<p class="number2 fl">WEIGHTLIFTING TITLE*</p>'+
                            '</div>'+
                            '<div class="weight" style="width: 308px;">'+
                                '<textarea class="weightT" onclick="this.focus()" placeholder="Type the details of the weightliftine" id="contentEditDes'+wodContentId+'"></textarea>'+
                            '</div>'+
                            '<p class="rep">DETAIL</p>'+
                            '<input type="text" class="set5" placeholder="" maxlength="50" onclick="fouceToLast(this)"  value="" id="contentEditReps'+wodContentId+'">'+
                            '<p class="rep">REP SCHEME</p>'+
                            '<div class="comment comment1">'+
                                '<span class="jia"></span> COMMENT'+
                            '</div>'+
                            '<div class="down1 down">'+
                                '<div class="weight">'+
                                    '<textarea class="weightT" onclick="this.focus()"  id="contentComment'+wodContentId+'"></textarea>'+
                                    '<span class="circle circle1"></span>'+
                                '</div>'+
                                '<div class="comment comment2">'+
                                    'COMMENT'+
                                '</div>'+
                            '</div>'+
                        '</div>'+
                        '<div class="middleright fl clearfix">'+
                            '<div class="title">记录方式</div>'+
                            '<p class="recode">记录WEIGHT</p>'+
                            '<div class="in clearfix">'+
                                '<span class="spanin">in</span>'+
                                '<select class="clean1">'+
                                    '<option value="">kg</option>'+
                                    '<option value="">lb</option>'+
                                '</select>'+
                            '</div>'+
                            '<div class="for clearfix">'+
                                '<span class="spanfor">for</span>'+
                                '<input class="clean1" type="text" min="1" value="1" onclick="fouceToLast(this)" id="contentRecordSet'+wodContentId+'">'+
                                '<span class="x">x</span>'+
                                '<input type="text" value="2" min="1" onclick="fouceToLast(this)" id="contentRecordReps'+wodContentId+'">'+
                            '</div>'+
                            '<div class="sets">'+
                                '<span class="ref">SETS</span>'+
                                '<span class="ref">REPS</span>'+
                            '</div>'+
                        '</div>'+
                    '</div>'+
                    '<div class="addSectionCancel"> 删除</div>'+
                '</div>'+
            '</div>'+
            '<input type="hidden" value="'+wodContentId+'" class="contentId">'+
            '<input type="hidden" value="'+wodContentType+'" class="wodContentType">'+
        '<input type="hidden" value="'+wodSectionId+'" class="contentOfwodsectionId">'+
        '<input type="hidden" value="" id="contentNum'+wodSectionId+'">'+
        '</div>';
    return custonWeightHtml;
}

//自定义gymnastics
function custom_gymnastics(wodContent){
    var wodContentId = wodContent.wodContentId;
    var descriptioin = wodContent.descript;
    var indexNum = wodContent.tIndex;
    var wodContentType = wodContent.contentType;
    var wodContent_content = wodContent.content;
    var wodSectionId = wodContent.wodSectionId;
    var custGymnnasticsHtml =
        '<div class="addOne custom-gymnastics ui-state-default contentDiv" id="content'+wodContentId+'">'+
            '<div class="metcon clearfix metconA" >'+
                '<h3 class="changeTitle"  id="contentTitle'+wodContentId+'">Gymnastics</h3>'+
                '<p class="p1" id="contentShowDes'+wodContentId+'"></p>'+
                '<p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow'+wodContentId+'"></p>'+
            '</div>'+
            '<div class="addContent addContentA clearfix">'+
                '<div class="addSection clearfix ">'+
                    '<div class="sectionBtn">'+
                        '<div class="cancle fl">取消</div>'+
                        '<div class="confirm fl">保存</div>'+
                    '</div>'+
                    '<div class="middle clearfix">'+
                        '<div class="middleLeft clearfix fl">'+
                            '<div class="title">Content</div>'+
                            '<div class="chodse clearfix">'+
                                '<input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)" id="seriaNum'+wodContentId+'" value="">'+
                                '<input style="width: 308px; margin: 0; text-align: left;" type="text" maxlength="100" placeholder="Title this GYMNASTICS" onclick="fouceToLast(this)" id="contentSaveTitle'+wodContentId+'">'+
                            '</div>'+
                            '<div class="clearfix number">' +
                                '<p class="number1 fl">序号</p>'+
                                '<p class="number2 fl">GYMNASTICS TITLE*</p>'+
                            '</div>'+
                            '<div class="weight" style="width: 308px;">'+
                                '<textarea class="weightT" onclick="this.focus()" placeholder="Type the details of the gymnastics" id="contentEditDes'+wodContentId+'"></textarea>'+
                            '</div>'+
                            '<p class="rep">DETAIL</p>'+
                            '<input type="text" class="set5" value="" onclick="fouceToLast(this)" value="" id="contentEditReps'+wodContentId+'">'+
                            '<p class="rep">REP SCHEME</p>'+
                            '<div class="comment comment1">'+
                                '<span class="jia"></span> COMMENT'+
                            '</div>'+
                            '<div class="down1 down">'+
                                '<div class="weight">'+
                                    '<textarea class="weightT" onclick="this.focus()" id="contentComment'+wodContentId+'"></textarea>'+
                                    '<span class="circle circle1"></span>'+
                                '</div>'+
                                '<div class="comment comment2">'+
                                    'COMMENT'+
                                '</div>'+
                            '</div>'+
                        '</div>'+
                        '<div class="middleright fl clearfix">'+
                            '<div class="title">记录方式</div>'+
                            '<p class="recode">记录REPS</p>'+
                            '<div class="for clearfix for3">' +
                                '<span class="spanfor">for</span>'+
                                '<input type="text" value="1" min="1" onclick="fouceToLast(this)" id="contentRecordSet'+wodContentId+'">'+
                                '<span class="x">x</span>'+
                                '<input type="text" disabled value="MAX" onclick="fouceToLast(this)">'+
                            '</div>'+
                            '<div class="sets">'+
                                '<span class="ref">SETS</span>'+
                                '<span class="ref">REPS</span>'+
                            '</div>' +
                        '</div>'+
                    '</div>'+
                    '<div class="addSectionCancel"> 删除</div>'+
                '</div>'+
            '</div>'+
            '<input type="hidden" value="'+wodContentId+'" class="contentId">'+
            '<input type="hidden" value="'+wodContentType+'" class="wodContentType">'+
        '<input type="hidden" value="'+wodSectionId+'" class="contentOfwodsectionId">'+
        '<input type="hidden" value="" id="contentNum'+wodSectionId+'">'+
        '</div>';
    return custGymnnasticsHtml;
}

//自定义 warm-up
function custom_warmUp(wodContent){
    var wodContentId = wodContent.wodContentId;
    var indexNum = wodContent.tIndex;
    var wodContentType = wodContent.contentType;
    var contentTitle = wodContent.contentTitle;
    var wodSectionId = wodContent.wodSectionId;
    var custonWarmUpHtml =
        '<div class="addOne custom-four ui-state-default contentDiv" id="content'+wodContentId+'">'+
            '<div class="metcon clearfix metconA" >'+
                '<h3 class="changeTitle"  id="contentTitle'+wodContentId+'">WarmUp'+'(no measure)'+'</h3>'+
                '<p class="p1" id="contentShowDes'+wodContentId+'"></p>'+
                '<p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow'+wodContentId+'"></p>'+
            '</div>'+
            '<div class="addContent clearfix addContentA">'+
                '<div class="addSection1 clearfix ">'+
                    '<div class="sectionBtn">'+
                        '<div class="cancle fl">取消</div>'+
                        '<div class="confirm fl">保存</div>'+
                    '</div>'+
                    '<div class="middle clearfix">'+
                        '<div class="middleLeft clearfix fl">'+
                            '<div class="title">Content</div>'+
                            '<div class="chodse1 clearfix fl">'+
                                '<input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)" id="seriaNum'+wodContentId+'" value="">'+
                                '<p class="number1">序号</p>'+
                            '</div>'+
                            '<div class="chodseright1 fr">'+
                                '<textarea class="weightT1" onclick="this.focus()" id="contentEditDes'+wodContentId+'"></textarea>'+
                                '<p class="number2 ">DETAILS</p>'+
                                '<div class="comment comment1">'+
                                    '<span class="jia"></span> COMMENT'+
                                '</div>'+
                                '<div class="down down1">'+
                                    '<div class="weight">'+
                                        '<textarea class="weightT" onclick="this.focus()" id="contentComment'+wodContentId+'"></textarea>'+
                                        '<span class="circle circle1"></span>'+
                                    '</div>'+
                                    '<div class="comment commentaa">'+
                                        'COMMENT'+
                                    '</div>'+
                                '</div>'+
                            ' </div>'+
                        '</div>'+
                        '<div class="middleright fl clearfix">'+
                            '<div class="title">记录方式</div>'+
                            ' <p class="recode">记录no measure</p>'+
                        '</div>'+
                    '</div>'+
                    '<div class="addSectionCancel"> 删除</div>'+
                '</div>'+
            '</div>'+
            '<input type="hidden" value="'+wodContentId+'" class="contentId">'+
            '<input type="hidden" value="'+wodContentType+'" class="wodContentType">'+
            '<input type="hidden" value="'+wodSectionId+'" class="contentOfwodsectionId">'+
        '<input type="hidden" value="" id="contentNum'+wodSectionId+'">'+
        '</div>';
    return custonWarmUpHtml;
}

//自定义mection
function custom_mecton(wodContent){
    var wodContentId = wodContent.wodContentId;
    var indexNum = wodContent.tIndex;
    var wodContentType = wodContent.contentType;
    var contentTitle = wodContent.contentTitle;
    var wodSectionId = wodContent.wodSectionId;
    var customContentHtml =
        '<div class="addOne custom-metcon ui-state-default contentDiv" id="content'+wodContentId+'">'+
            '<div class="metcon clearfix metconA" >'+
                '<h3 class="changeTitle"  id="contentTitle'+wodContentId+'">Metcon(Time)</h3>'+
                '<p class="p1" id="contentShowDes'+wodContentId+'"></p>'+
                '<p style="color: #999;margin-top: 10px;font-style: italic;" class="p1" id="contentCommentShow'+wodContentId+'"></p>'+
            '</div>'+
            '<div class="addContent clearfix addContentA">'+
                '<div class="addSection1 clearfix ">'+
                    '<div class="sectionBtn">'+
                        '<div class="cancle fl">取消</div>'+
                        '<div class="confirm fl">保存</div>'+
                    '</div>'+
                    '<div class="middle clearfix">'+
                        '<div class="middleLeft clearfix fl">'+
                            '<div class="title">Content</div>'+
                            '<div class="chodse1 clearfix fl">'+
                                '<input type="text" maxlength="5"  class="seria_num" onclick="fouceToLast(this)"  value="" id="seriaNum'+wodContentId+'">'+
                                '<p class="number1">序号</p>'+
                            '</div>'+
                            '<div class="chodseright1 fr">'+
                                '<textarea class="weightT1" onclick="this.focus()" id="contentEditDes'+wodContentId+'"></textarea>'+
                                '<p class="number2 ">DETAILS</p>'+
                                '<div class="comment comment1">'+
                                    '<span class="jia"></span> COMMENT'+
                                '</div>'+
                                '<div class="down down1">'+
                                    '<div class="weight">'+
                                        '<textarea class="weightT" onclick="this.focus()" id="contentComment'+wodContentId+'"></textarea>'+
                                        '<span class="circle circle1"></span>'+
                                    '</div>'+
                                    '<div class="comment commentaa">'+
                                        'COMMENT'+
                                    '</div>'+
                                '</div>'+
                            '</div>'+
                        '</div>'+
                        '<div class="middleright fl clearfix ">'+
                            '<div class="title">记录方式</div>'+
                            '<div class="recode">'+
                                '<span>记录</span>'+
                                '<select name="" id="metcon-type'+wodContentId+'" class="time custom_metcon_type">'+
                                    '<option value="1">time</option>'+
                                    '<option value="3">AMRAP-Reps</option>'+
                                    '<option value="4">AMRAP-Rounds and Reps</option>'+
                                    '<option value="7">Calories</option>'+
                                    '<option value="8">No Measure</option>'+
                                    '<option value="10">weight</option>'+
                                    '<option value="5">each round</option>'+
                                    '<option value="6">distance</option>'+
                                '</select>'+
                            '</div>'+
                            //常规的
                            '<div class="rx custom_content_general">'+
                                '<span>是否Rx+？</span>'+
                                '<input type="radio" name="generalRecordRx'+wodContentId+'" value="1" >是'+
                                '<input type="radio" value="0" name="generalRecordRx'+wodContentId+'" checked="true">否'+
                            '</div>'+
                            //7重量的
                            '<div class="recode custom_content_weight">'+
                                '<span> 单位</span>'+
                                '<select name="" class="time" id="weightRecordUnit'+wodContentId+'">'+
                                    '<option value="1">kg</option>'+
                                    '<option value="2">lb</option>'+
                                '</select>'+
                            '</div>'+
                            '<div class="rx custom_content_weight">'+
                                '<span>是否Rx+？</span>'+
                                '<input type="radio" value="1" name="weightRecordRx'+wodContentId+'" >是'+
                                '<input type="radio" value="0" name="weightRecordRx'+wodContentId+'" checked="true">否'+
                            '</div>'+
                            //eachround
                            '<div class="recode custom_content_eachround">'+
                                '<span> 规则</span>'+
                                '<input type="text" class="rounds" value="10" onclick="fouceToLast(this)" id="eachRoundB'+wodContentId+'">'+
                                '<select  id="eachRoundRecord'+wodContentId+'" class="time timeshort">'+
                                    '<option value="1">For Reps</option>'+
                                    '<option value="2">For Time</option>'+
                                    '<option value="3">For Weight</option>'+
                                    '<option value="4">For Distance</option>'+
                                    '<option value="5">For Calories</option>'+
                                '</select>'+
                            '</div>'+
                            '<div class="recode unit unit-weight custom_content_eachround  eachRoundWeightSel">'+
                                '<span> 单位</span>'+
                                '<select  id="eachRoundUnit'+wodContentId+'" class="time unit-weight-select">'+
                                    '<option value="1">kg</option>'+
                                    '<option value="2">lb</option>'+
                                '</select>'+
                            '</div>'+
                            '<div class="recode unit unit-distance custom_content_eachround eachRoundDistanceSel" id="eachRoundDistanceSel'+wodContentId+'">'+
                                '<span> 单位</span>'+
                                '<select id="eachRoundA'+wodContentId+'" class="time unit-distance-select eachRoundA">'+
                                    '<option value="3">miles</option>'+
                                    '<option value="4">meters</option>'+
                                    '<option value="5">km</option>'+
                                    '<option value="6">yards</option>'+
                                    '<option value="7">feet</option>'+
                                    '<option value="8">inches</option>'+
                                    '<option value="9">cm</option>'+
                                '</select>'+
                            '</div>'+
                            '<div class="rx custom_content_eachround">'+
                                '<span>是否Rx+？</span>'+
                                '<input type="radio" value="1" name="eachRoundRx'+wodContentId+'" >是'+
                                '<input type="radio" value="0" name="eachRoundRx'+wodContentId+'" checked="true">否'+
                            '</div>'+
                            //distance
                            '<div class="recode custom_content_distance">'+
                                '<span>单位</span>'+
                                '<select name="" id="distanceRecord'+wodContentId+'" class="time">'+
                                    '<option value="3">meters</option>'+
                                    '<option value="4">miles</option>'+
                                    '<option value="5">km</option>'+
                                    '<option value="6">yards</option>'+
                                    '<option value="7">feet</option>'+
                                    '<option value="8">inches</option>'+
                                    '<option value="9">cm</option>'+
                                '</select>'+
                            '</div>'+
                            ' <div class="rx custom_content_distance">'+
                                '<span>是否Rx+？</span>'+
                                '<input type="radio" value="1" name="distanceRx'+wodContentId+'" >是'+
                                '<input type="radio" value="0" name="distanceRx'+wodContentId+'" checked="true">否'+
                            '</div>'+
                        '</div>'+
                    '</div>'+
                    '<div class="addSectionCancel"> 删除</div>'+
                '</div>'+
            '</div>'+
            '<input type="hidden" value="'+wodContentId+'" class="contentId">'+
            '<input type="hidden" value="'+wodContentType+'" class="wodContentType">'+
        '<input type="hidden" value="'+wodSectionId+'" class="contentOfwodsectionId">'+
        '<input type="hidden" value="" id="contentNum'+wodSectionId+'">'+
        '</div>';
    return customContentHtml;
}




function addNullSection(data,title){
    var sectionDiv =
        '<div class="section-box ui-state-default" id="section'+data+'" style="display:none">'+
            //查看section html
        '<div class="metcon clearfix metconA sectionShow">'+
        '<h3 class="changeTitle" id="secionTitle'+data+'">' + title + '</h3>'+
            //内容
        '<p class="p1" id="wodSecionCommentShow'+data+'"></p>'+
        '</div>'+
            //编辑 html
        '<div class="addContent addContentA clearfix sectionEdit">'+
        '<div class="addSection clearfix">'+
        '<div class="middle clearfix sectionShow">'+
        '<div class="middleLeft clearfix single">'+
        '<div class="sectionBtn sectionBtnsingle">'+
        '<div class="cancle fl">取消</div>'+
        '<div class="confirm fl">保存</div>'+
        '</div>'+
        '<div class="chodse clearfix">'+
        '<select  class="clean singleSel" id="sectionSel'+data+'">'+getSectoinSelectHtml()+'</select>'+
        '</div>'+
        '<div class="clearfix number singleNumber">TYPE</div>'+
        '<div class="comment comment3 singlecomment sectionCommentTitle" ><span class="jia"></span> COMMENT</div>'+
        '<div class="down2 down">'+
        '<div class="weight">'+
        '<textarea class="weightT singleweightT" id="sectionComment'+data+'" onclick="this.focus()"></textarea>'+
        '<span class="circle circle2"></span>'+
        '</div>'+
        '<div class="comment comment2 singlecomment">COMMENT</div>'+
        '</div>'+
        '</div>'+
        '<div class="addSectionCancel"> 删除</div>'+
        '</div>'   +
        '</div>'+
        '<input type="hidden" value="'+data+'" class="sectionId">'+
        '</div>';
    return sectionDiv;
}