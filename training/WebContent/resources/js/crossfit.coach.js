var namespaceCoach = crossfit.namespace("cfer.coach");

/**
 * 教练列表
 */
namespaceCoach.coachList = function(){
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
			url: obj.ctx+"/setting/coach/getCoachListJson",
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
					coachs+='<td>'+item.phone+'</td>';
					coachs+='<td>'+item.level+'</td>';
					coachs+='<td>';
					coachs+='<a data-id="'+item.id+'" class="btn-c fr delete">删除</a>';
					coachs+='<a href="'+obj.ctx+'/setting/coach/edit/'+item.id+'" class="btn-c fr edit">编辑</a>';
					coachs+='</td>';
					coachs+='</tr>';
				});
				$("#coach-list").html(coachs);
				$("#totalRow").val(pager.totalRow);
				obj.totalRow = pager.totalRow;
			},
			complete: function(xhr,status) {
				$("#coach-list").on("click",".delete",function(){
					var id = $(this).data('id');
					$.ajax({
						url: obj.ctx+"/setting/coach/delete",
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
		
		$("#coach-list").on("click",".delete",function(){
			var id = $(this).data('id');
			$.ajax({
				url: obj.ctx+"/setting/coach/delete",
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
namespaceCoach.edit = function(){
	this.ctx = $("#ctx").val();
	this.init=function(){
		this.event();
	};
	this.event=function(){
		
		var obj = this;
		
		// 验证手机号
		function isPhoneNo(phone) { 
			var pattern = /^1[34578]\d{9}$/; 
			return pattern.test(phone); 
		}
		//校验用户名
		function isUserName(userName){
			if(userName.length>=8&&userName.length<=16){
				var pattern = /^\w+$/;
				return pattern.test(userName);
			}else{
				return false;
			}
		}
		//校验密码
		function isPassword(password){
			if(password.length>=8&&password.length<=16){
				var pattern = /^[A-Za-z0-9]+$/;
				return pattern.test(password);
			}else{
				return false;
			}
		}
		
		$("#hold").click(function(){
			var name = $("#name").val();
			var phone = $("#phone").val();
			var userName = $("#userName").val();
			var password = $("#password").val();
			var level = $("#level").val();
			var param = {"name":name,"phone":phone,"userName":userName,"password":password,"level":level};
			if(name==null||name==""){
				$("#name-tip").html("教练姓名不能为空");
				return false;
			}else{
				$("#name-tip").empty();
			}
			if(!isPhoneNo(phone)){
				$("#phone-tip").html("请输入有效的手机号");
				return false;
			}else{
				$("#phone-tip").empty();
			}
			if(!isUserName(userName)){
				$("#userName-tip").html("用户名为8~16位字母数字下划线组合");
				return false;
			}else{
				$("#userName-tip").empty();
			}
			if(!isPassword(password)){
				$("#password-tip").html("密码为8~16位字母数字组合");
				return false;
			}else{
				$("#password-tip").empty();
			}
			if(level==null||level==""){
				$("#level-tip").html("教练等级不能为空");
				return false;
			}else{
				$("#level-tip").empty();
			}
			$.ajax({
				url: obj.ctx+"/setting/coach/save",
				type: 'POST',
				dataType:'text',
				data:param,
				async:false,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/setting/coach";
					}else if(result="existingUser"){
						$("#userName-tip").html("用户名已被使用");
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
			var phone = $("#phone").val();
			var userName = $("#userName").val();
			var password = $("#password").val();
			var level = $("#level").val();
			var param = {"id":id,"name":name,"phone":phone,"userName":userName,"password":password,"level":level};
			if(name==null||name==""){
				$("#name-tip").html("教练姓名不能为空");
				return false;
			}else{
				$("#name-tip").empty();
			}
			if(!isPhoneNo(phone)){
				$("#phone-tip").html("请输入有效的手机号");
				return false;
			}else{
				$("#phone-tip").empty();
			}
			if(!isUserName(userName)){
				$("#userName-tip").html("用户名为8~16位字母数字下划线组合");
				return false;
			}else{
				$("#userName-tip").empty();
			}
			if(!isPassword(password)){
				$("#password-tip").html("密码为8~16位字母数字组合");
				return false;
			}else{
				$("#password-tip").empty();
			}
			if(level==null||level==""){
				$("#level-tip").html("教练等级不能为空");
				return false;
			}else{
				$("#level-tip").empty();
			}
			$.ajax({
				url: obj.ctx+"/setting/coach/update",
				type: 'POST',
				dataType:'text',
				data:param,
				async:false,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/setting/coach";
					}else if(result="existingUser"){
						$("#userName-tip").html("用户名已被使用");
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
		$("#cancel").click(function(){
			window.location = obj.ctx+"/setting/coach";
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