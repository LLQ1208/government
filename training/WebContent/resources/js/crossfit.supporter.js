var namespaceSupporter = crossfit.namespace("cfer.supporter");

/**
 * 客服列表
 */
namespaceSupporter.supporterList = function(){
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
			url: obj.ctx+"/setting/supporter/getSupporterListJson",
			type: 'POST',
			dataType:'json',
			data:obj.conditions,
			async:false,
			success: function(pager){
				var list = pager.list;
				var supporters = '';
				$.each(list, function (i, item) {
					supporters+='<tr>';
					supporters+='<td>'+item.name+'</td>';
					supporters+='<td>'+item.phone+'</td>';
					supporters+='<td>'+item.email+'</td>';
					supporters+='<td>';
					supporters+='<a data-id="'+item.id+'" class="btn-c fr delete">删除</a>';
					supporters+='<a href="'+obj.ctx+'/setting/supporter/edit/'+item.id+'" class="btn-c fr edit">编辑</a>';
					supporters+='</td>';
					supporters+='</tr>';
				});
				$("#supporter-list").html(supporters);
				$("#totalRow").val(pager.totalRow);
				obj.totalRow = pager.totalRow;
				
				$("#supporter-list").on("click",".delete",function(){
					var id = $(this).data('id');
					$.ajax({
						url: obj.ctx+"/setting/supporter/delete",
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
			complete: function(xhr,status) {
				$("#supporter-list").on("click",".delete",function(){
					var id = $(this).data('id');
					$.ajax({
						url: obj.ctx+"/setting/supporter/delete",
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
		
		$("#supporter-list").on("click",".delete",function(){
			var id = $(this).data('id');
			$.ajax({
				url: obj.ctx+"/setting/supporter/delete",
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
namespaceSupporter.edit = function(){
	this.ctx = $("#ctx").val();
	this.init=function(){
		this.event();
	};
	this.event=function(){
		
		var obj = this;
		
		//校验邮箱
		function isEmail(email){
			return email.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
		}
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
			var email = $("#email").val();
			var userName = $("#userName").val();
			var password = $("#password").val();
			var roleId = $("#roleId").val();
			if(name==null||name==""){
				$("#name-tip").html("客服名称不能为空");
				return false;
			}else{
				$("#name-tip").empty();
			}
			if(!isPhoneNo(phone)){
				$("#phone-tip").html("请输入有效的手机号码");
				return false;
			}else{
				$("#phone-tip").empty();
			}
			if(!isEmail(email)){
				$("#email-tip").html("请输入有效的邮箱地址");
				return false;
			}else{
				$("#email-tip").empty();
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
			var param = {"name":name,'phone':phone,'email':email,'userName':userName,'password':password,"roleId":roleId};
			$.ajax({
				url: obj.ctx+"/setting/supporter/save",
				type: 'POST',
				dataType:'text',
				data:param,
				async:false,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/setting/supporter";
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
			var email = $("#email").val();
			var userName = $("#userName").val();
			var password = $("#password").val();
			var roleId = $("#roleId").val();
			if(name==null||name==""){
				$("#name-tip").html("客服名称不能为空");
				return false;
			}else{
				$("#name-tip").empty();
			}
			if(!isPhoneNo(phone)){
				$("#phone-tip").html("请输入有效的手机号码");
				return false;
			}else{
				$("#phone-tip").empty();
			}
			if(!isEmail(email)){
				$("#email-tip").html("请输入有效的邮箱地址");
				return false;
			}else{
				$("#email-tip").empty();
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
			var param = {"id":id,"name":name,'phone':phone,'email':email,'userName':userName,'password':password,"roleId":roleId};
			$.ajax({
				url: obj.ctx+"/setting/supporter/update",
				type: 'POST',
				dataType:'text',
				data:param,
				async:false,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/setting/supporter";
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
			window.location = obj.ctx+"/setting/supporter";
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