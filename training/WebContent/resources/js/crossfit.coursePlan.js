var namespaceCoursePlan = crossfit.namespace("cfer.coursePlan");

/**
 * 课程安排列表
 */
namespaceCoursePlan.coursePlanList = function(){
	this.ctx = $("#ctx").val();
	this.init=function(){	
		this.initScroll();
		this.event();
	};
	
	this.initScroll=function(){
		// 滚动条
		var newHeight = $(window).height();
		if(newHeight<700)
			newHeight = 700;
		$(".course").css('height',newHeight-126);
		$(".course").niceScroll({cursorcolor:"#ff3823",cursorwidth:6,cursoropacitymax:0.7,touchbehavior:false});
		
		$(window).resize(function(){
   			var newHeight = $(window).height();
			if(newHeight<700)
				newHeight = 700;
			$(".course").css('height',newHeight-126);
		});
	};
	
	this.event=function(){
		var obj = this;
		
		$(".add").click(function(){
			var week = $(this).data("week");
			window.location = obj.ctx+"/setting/coursePlan/add/"+week;
		});
		
		$(".item").click(function(){
			var id = $(this).data("id");
			window.location = obj.ctx+"/setting/coursePlan/edit/"+id;
		});
	};
};

/**
 * 编辑
 */
namespaceCoursePlan.edit = function(){
	this.ctx = $("#ctx").val();
	this.init=function(){
		this.event();
	};
	this.event=function(){
		
		var obj = this;
		
		$("#courseType").change(function(){
			var courseTypeId = $("#courseType option:selected").val();
			$("#course-level").empty();
			$.ajax({
				url: obj.ctx+"/setting/courseType/get/"+courseTypeId,
				type: 'GET',
				dataType:'json',
				async:false,
				success: function(result){
					var courseType = result.courseType;
					if(courseType.hasLevel==1){
						var levelDom = '<div class="row item">';
						levelDom += '<div class="col-sm-12 fl">';
						levelDom += '<div class="col-sm-3 input-item text-center">课程级别</div>';
						levelDom += '<select class="col-sm-8 selectpicker" id="courseLevel">';
						var levelList = courseType.levelList;
						$.each(levelList, function (n, value) {
							levelDom += '<option value="'+value+'">'+value+'</option>';
						});
						levelDom += '</select>';
						levelDom += '</div>';
						levelDom += '</div>';
						$("#course-level").html(levelDom);
						$('.selectpicker').selectpicker();
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
		function isTime(time){
			var pattern = /^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/; 
			return pattern.test(time); 
		}
		
		$("#hold").click(function(){
			var courseTypeId = $("#courseType option:selected").val();
			var courseLevel = $("#courseLevel option:selected").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var peopleLimit = $("#peopleLimit").val();
			if(isTime(startTime)&&isTime(endTime)){
				$("#time-tip").empty();
			}else{
				$("#time-tip").html('请输入正确的时间格式，如"09:10"');
				return false;
			}
			if(startTime>=endTime){
				$("#time-tip").html('课程结束时间必须大于课程开始时间');
				return false;
			}else{
				$("#time-tip").empty();
			}
			var week = $("#week").val();
			var param = {"peopleLimit":peopleLimit,"week":week,"courseTypeId":courseTypeId,"courseLevel":courseLevel,"startTime":"endTime","startTime":startTime,"endTime":endTime};
			$.ajax({
				url: obj.ctx+"/setting/coursePlan/save",
				type: 'POST',
				dataType:'text',
				data:param,
				async:false,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/setting/coursePlan";
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
		$("#update").click(function(){
			var id= $("#id").val();
			var courseTypeId = $("#courseType option:selected").val();
			var courseLevel = $("#courseLevel option:selected").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
            var peopleLimit = $("#peopleLimit").val();
			if(isTime(startTime)&&isTime(endTime)){
				$("#time-tip").empty();
			}else{
				$("#time-tip").html('请输入正确的时间格式，如"09:10"');
				return false;
			}
			var param = {"id":id,"peopleLimit":peopleLimit,"courseTypeId":courseTypeId,"courseLevel":courseLevel,"startTime":"endTime","startTime":startTime,"endTime":endTime};
			$.ajax({
				url: obj.ctx+"/setting/coursePlan/update",
				type: 'POST',
				dataType:'text',
				data:param,
				async:false,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/setting/coursePlan";
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
		$("#delete").click(function(){
			var id= $("#id").val();
			$.ajax({
				url: obj.ctx+"/setting/coursePlan/delete/"+id,
				type: 'POST',
				dataType:'text',
				async:false,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/setting/coursePlan";
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});
			window.location = obj.ctx+"/setting/coursePlan";
		});
		
		$("#cancel").click(function(){
			window.location = obj.ctx+"/setting/coursePlan";
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
	};
};