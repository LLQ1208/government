var namespaceCourse = crossfit.namespace("cfer.course");

/**
 * 课程内容
 */
namespaceCourse.courseContent = function(){
	this.ctx = $("#ctx").val();
	this.addDate = new Array();
	this.currentDate = new Date();
	this.courseTypeId = $("#defaultCourseTypeId").val();
	this.calender = "";
	this.init=function(){
		this.initAddDate();
		this.initCalenderSquare();
		this.event();
	};
	/**
	 * 初始化已添加安排的日期
	 */
	this.initAddDate = function(){
		var obj = this;
		var currentMonthAddDate = $("#currentMonth-addDate").val();
		var addDateArr = currentMonthAddDate.split(",");
		for(var i=0;i<addDateArr.length;i++){
			obj.addDate.push(parseInt(addDateArr[i]));
		}
	};
	
	this.initCalenderSquare = function(){
		var obj = this;
		$('.zx-calender-square').zxCalenderSquare({
			initCallBack: false,
			addDates:obj.addDate,
			maxDate:new Date(2100, 12, 1),
			currentDate: obj.currentDate,
			ctx:obj.ctx,
			callBackFn: function(dates) {
				var dateArr = dates.split("/");
				var date = dateArr[0];
				location.href = obj.ctx+"/courseContent/edit/"+obj.courseTypeId+"/"+date;
			}
		});	
	};
	
	this.event=function(){
		var obj = this;
		
		$(".prevdate").unbind("click");

		$("#courseType").change(function () {
			obj.courseTypeId = $(this).val();
			var date = $(".md_headtext").data("date");
            var dateArray = date.split("-");
			obj.currentDate = new Date(dateArray[0], dateArray[1], 1);
			window.location = obj.ctx+"/courseContent/"+obj.courseTypeId+"/"+date;
        });
	};
};

/**
 * 添加课程内容
 */
namespaceCourse.addOrEditCourseContent = function(){
	this.ctx = $("#ctx").val();
	this.init=function(){	
		this.event();
	};
	
	this.event = function(){
		var obj = this;
		
		/**
		 * 保存
		 */
		$("#hold").click(function(){
			var contentItem = $(".content-main").find(".content-item");
			var planDate = $("#planDate").val();
			var openTime = $("#openTime option:selected").val();
			var coachId = $("#coach option:selected").val();
			var courseTypeId = $("#courseTypeId").val();
			var _tag = true;
			var content = "[";
			contentItem.each(function (index,domEle){
				content += "{";
				var title = $.trim($(this).find(".caption").val());
				var detail = $.trim($(this).find(".details").val());
				var remark = $.trim($(this).find(".remark").val());
				var time = $.trim($(this).find(".time option:selected").val());
				var _tip = $(this).find(".tip");
				if(title==null||title==''){
					_tip.html("课程项标题不能为空");
					_tag = false;
					return false;
				}else if(detail==null||detail==''){
					_tip.html("课程项描述不能为空");
					_tag = false;
					return false;
				}else if(time==null||time==''){
					_tip.html("课程项计划时间不能为空");
					_tag = false;
					return false;
				}else{
					_tag = true;
					_tip.empty();
				}
				content += "'title':'"+title+"',";
				content += "'detail':'"+detail+"',";
				content += "'remark':'"+remark+"',";
				content += "'time':'"+time+"'";
				content += "},";
			});
			if(!_tag){
				return false;
			}
			if(contentItem.size()==0){
				$("#main-tip").html("请至少添加一个训练项目");
				return false;
			}else{
				$("#main-tip").empty();
			}
			var con = content.substring(0,content.length-1)+"]";
			var param = {'courseTypeId':courseTypeId,'planDate':planDate,'openTime':openTime,'coachId':coachId,'content':con};
			$.ajax({
				url: obj.ctx+"/courseContent/save",
				type: 'POST',
				dataType:'text',
				data:param,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/courseContent";
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
		/**
		 * 更新
		 */
		$("#update").click(function(){
			var contentItem = $(".content-main").find(".content-item");
			var planDate = $("#planDate").val();
			var openTime = $("#openTime option:selected").val();
			var coachId = $("#coach option:selected").val();
			var id = $("#id").val();
			var _tag = true;
			var content = "[";
			contentItem.each(function (index,domEle){
				content += "{";
				var title = $(this).find(".caption").val();
				var detail = $(this).find(".details").val();
                var remark = $(this).find(".remark").val();
				var time = $(this).find(".time option:selected").val();
				var _tip = $(this).find(".tip");
				if(title==null||title==''){
					_tip.html("课程项标题不能为空");
					_tag = false;
					return false;
				}else if(detail==null||detail==''){
					_tip.html("课程项描述不能为空");
					_tag = false;
					return false;
				}else if(time==null||time==''){
					_tip.html("课程项计划时间不能为空");
					_tag = false;
					return false;
				}else{
					_tag = true;
					_tip.empty();
				}
				content += "'title':'"+title+"',";
				content += "'detail':'"+detail+"',";
                content += "'remark':'"+remark+"',";
				content += "'time':'"+time+"'";
				content += "},";
			});
			if(!_tag){
				return false;
			}
			if(contentItem.size()==0){
				$("#main-tip").html("请至少添加一个训练项目");
				return false;
			}else{
				$("#main-tip").empty();
			}
			var con = content.substring(0,content.length-1)+"]";
			var param = {'id':id,'planDate':planDate,'openTime':openTime,'coachId':coachId,'content':con};
			$.ajax({
				url: obj.ctx+"/courseContent/update",
				type: 'POST',
				dataType:'text',
				data:param,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/courseContent";
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
		/**
		 * 添加内容项
		 */
		$("#add-item-btn").click(function(){
			var contentMain = $(".content-main");
			var contentItem='<div class="row content-item">';
			contentItem+='<div class="col-sm-10">';
			contentItem+='<div class="row item">';
			contentItem+='<input type="text" class="caption fl" value="">';
			contentItem+='<div class="selects2">';
			contentItem+='<select class="selectpicker time">';
			for(var i=0;i<=120;i++){
				if(i==0){
					contentItem+='<option value="'+i+'">Not fixed</option>';
				}else{
					contentItem+='<option value="'+i+'">'+i+'mins</option>';
				}
			}
			contentItem+='</select>';
			contentItem+='</div>';
			contentItem+='</div>';
			contentItem+='<div class="row item">';
			contentItem+='<textarea rows="4" class="details"></textarea>';
			contentItem+='</div>';
        	contentItem+='<div class="row item">';
            contentItem+='<textarea rows="3" class="remark"></textarea>';
            contentItem+='</div>';
			contentItem+='</div>';
			contentItem+='<div class="col-sm-2">';
			contentItem+='<div class="remove-item">';
			contentItem+='<a class="btn btn-remove"><i class="fa fa-minus"></i></a>';
			contentItem+='</div>';
			contentItem+='</div>';
			contentItem+='<div class="col-sm-12 tip"></div>';
			contentItem+='</div>';
			contentMain.append(contentItem);
			$("#main-tip").empty();
			
			$('.selectpicker').selectpicker();
			$(".btn-remove").click(function(){
				$(this).parents(".content-item").remove();
			});
		});
		
		/**
		 * 删除内容项
		 */
		$(".btn-remove").click(function(){
			$(this).parents(".content-item").remove();
		});
		
		/**
		 * 点击取消跳转到课程管理页面
		 */
		$("#cancel").click(function(){
			window.location = obj.ctx+"/courseContent";
		});
		
		$('input[type=text]').focus(function(){
			var txt_value = $(this).val();
			if(txt_value==this.defaultValue){
				$(this).val("");
	            
			};	
		});
		$('input[type=text]').blur(function(){
			var txt_value = $(this).val();
			if(txt_value==""){
				$(this).val(this.defaultValue);
			};	
	         
		});	
		// 下拉框
		$('.selectpicker').selectpicker();
		// 滚动条
		$(".details").niceScroll({cursorcolor:"#ff3823",cursorwidth:6,cursoropacitymax:0.7,touchbehavior:false});
	};
};
