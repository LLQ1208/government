var namespaceCourseType = crossfit.namespace("cfer.courseType");

/**
 * 课程类型列表
 */
namespaceCourseType.courseTypeList = function(){
	this.ctx = $("#ctx").val();
	this.isPageInit = true;   //是否为初始化分页列表
	this.totalRow = $('#totalRow').val();
	this.conditions = {pageSize:8,pageIndex:1,keyword:$("#keyword").val().trim()};
	this.init=function(){	
		this.initPage();
		this.event();
	};
	
	this.initPage=function(){
		var obj = this;
		$('#pagination-bottom').pagination(obj.totalRow,{
			current_page: obj.conditions.pageIndex-1,
			items_per_page:obj.conditions.pageSize,
			prev_text:'<',
			next_text:'>',
			callback:function(index){
				obj.conditions.pageIndex=index+1;
				if(obj.isPageInit){
					obj.isPageInit=false;					
				}else{
					obj.generateData();
				}
			}
        });
	};
	
	this.generateData = function(){
		var obj=this;
		$.ajax({
			url: obj.ctx+"/setting/courseType/getCourseTypeListJson",
			type: 'POST',
			dataType:'json',
			data:obj.conditions,
			async:false,
			success: function(pager){
				var list = pager.list;
				var coachs = '';
				$.each(list, function (i, item) {
					coachs+='<tr>';
					coachs+='<td>'+item.name+'</td>';
					coachs+='<td>';
					coachs+='<a data-id="'+item.id+'" class="btn-c fr delete">删除</a>';
					coachs+='<a href="'+obj.ctx+'/setting/courseType/edit/'+item.id+'" class="btn-c fr edit">编辑</a>';
					coachs+='</td>';
					coachs+='</tr>';
				});
				$("#courseType-list").html(coachs);
				$("#totalRow").val(pager.totalRow);
				obj.totalRow = pager.totalRow;
			},
			complete: function(xhr,status) {
				$("#courseType-list").on("click",".delete",function(){
					var id = $(this).data('id');
					$.ajax({
						url: obj.ctx+"/setting/courseType/delete",
						type: 'POST',
						dataType:'text',
						data:{'id':id},
						success: function(pager){
							obj.generateData();
							$('#pagination-bottom').html('');
							obj.isPageInit = true;
							obj.initPage();
						},
						error:function(XMLHttpRequest){
							console.log('服务器异常');
						}
					});	
				});
			},
			error:function(XMLHttpRequest){
				console.log('服务器异常');
			}
		});	
	};
	
	this.event=function(){
		var obj = this;
		
		$("#courseType-list").on("click",".delete",function(){
			var id = $(this).data('id');
			$.ajax({
				url: obj.ctx+"/setting/courseType/delete",
				type: 'POST',
				dataType:'text',
				data:{'id':id},
				success: function(pager){
					obj.generateData();
					$('#pagination-bottom').html('');
					obj.isPageInit = true;
					obj.initPage();
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
		$('.searchBtn').click(function(){
			obj.conditions.keyword = $("#keyword").val().trim();
			obj.conditions.pageIndex = 1;
			obj.generateData();
			$('#pagination-bottom').html('');
			obj.isPageInit = true;
			obj.initPage();
		});
	};
};

/**
 * 编辑
 */
namespaceCourseType.edit = function(){
	this.ctx = $("#ctx").val();
	this.init=function(){
		this.event();
	};
	this.event=function(){
		
		var obj = this;
		
		$("#add-item-btn").click(function(){
			var levelHtml = '<div class="row item">';
			levelHtml += '<div class="col-sm-12 fl">';
			levelHtml += '<div class="col-sm-3 input-item text-center">等级</div>';
			levelHtml += '<div class="col-sm-6">';
			levelHtml += '<input type="text" class="srk fl level">';
			levelHtml += '</div>';
			levelHtml += '<div class="col-sm-2">';
			levelHtml += '<div class="remove-item">';
			levelHtml += '<a class="btn btn-remove"><i class="fa fa-minus"></i></a>';
			levelHtml += '</div>';
			levelHtml += '</div>';
			levelHtml += '</div>';
			levelHtml += '<div class="tip"></div>';
			levelHtml += '</div>';
			$("#course-level").append(levelHtml);
			$(".btn-remove").click(function(){
				$(this).parents(".item").remove();
			});
		});
		
		$(".btn-remove").click(function(){
			$(this).parents(".item").remove();
		});
		
		$("#hold").click(function(){
			var name = $("#name").val();
			if(name==null||name==""){
				$("#name-tip").html("课程类型名称不能为空");
				return false;
			}else{
				$("#name-tip").empty();
			}
			var level_dom = $(".level");
			var _tag = true;
			var levelArr = "[";
			level_dom.each(function (index,domEle){
				var level_name = $.trim($(this).val());
				var _tip = $(this).parents(".item").find(".tip");
				if(level_name==null||level_name==''){
					_tip.html("请输入课程等级");
					_tag = false;
					return false;
				}else{
					_tag = true;
					_tip.empty();
				}
				levelArr += "'";
				levelArr += level_name;
				levelArr += "',";
			});
			if(!_tag){
				return false;
			}
			var level = levelArr.substring(0,levelArr.length-1)+"]";
			var hasLevel = 0;
			if(level_dom.size()!=0){
				hasLevel = 1;
			}else{
				level = null;
			}
			var param = {"name":name,'hasLevel':hasLevel,'level':level};
			$.ajax({
				url: obj.ctx+"/setting/courseType/save",
				type: 'POST',
				dataType:'text',
				data:param,
				async:false,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/setting/courseType";
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
		$("#update").click(function(){
			var id= $("#id").val();
			var name = $("#name").val();
			if(name==null||name==""){
				$("#name-tip").html("课程类型名称不能为空");
				return false;
			}else{
				$("#name-tip").empty();
			}
			var level_dom = $(".level");
			var _tag = true;
			var levelArr = "[";
			level_dom.each(function (index,domEle){
				var level_name = $.trim($(this).val());
				var _tip = $(this).parents(".item").find(".tip");
				if(level_name==null||level_name==''){
					_tip.html("请输入课程等级");
					_tag = false;
					return false;
				}else{
					_tag = true;
					_tip.empty();
				}
				levelArr += "'";
				levelArr += level_name;
				levelArr += "',";
			});
			if(!_tag){
				return false;
			}
			var level = levelArr.substring(0,levelArr.length-1)+"]";
			var hasLevel = 0;
			if(level_dom.size()!=0){
				hasLevel = 1;
			}else{
				level = null;
			}
			var param = {"id":id,"name":name,'hasLevel':hasLevel,'level':level};
			$.ajax({
				url: obj.ctx+"/setting/courseType/update",
				type: 'POST',
				dataType:'text',
				data:param,
				async:false,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/setting/courseType";
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
		$("#cancel").click(function(){
			window.location = obj.ctx+"/setting/courseType";
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
	};
};