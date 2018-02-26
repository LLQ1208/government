var namespaceLogin = crossfit.namespace("cfer.login");

namespaceLogin.loginJS = function(){
	this.ctx = $("#ctx").val();
	this.init=function(){	
		this.event();
	}; 	
	this.event=function(){
		var obj = this;
		
	   //自动添加用户名密码  
	   function autoPwd(){  
	       if ($.cookie("rmbUser") == "true") {  
	           var username=$("#userName").val();  
	           if(username==$.cookie("username")){  
	               $("#ck_rmbUser").attr("checked", true);  
	               $("#password").val($.cookie("password"));  
	               $("#password").attr("type","password");
	           }  
	       }  
	   }  
		    
	   //记住用户名密码  
	   function saveUserNameAndPassword(userName, password, ck_rmbUser) {  
		   if(ck_rmbUser) {  
			   	var str_username = userName;  
			   	var str_password = password;  
			   	$.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie  
			   	$.cookie("username", str_username, { expires: 7 });  
			   	$.cookie("password", str_password, { expires: 7 });  
		    }else {  
		    	$.cookie("rmbUser", "false", { expire: -1 });  
		    	$.cookie("username", "", { expires: -1 });  
		    	$.cookie("password", "", { expires: -1 });  
		    }  
	   };
	   
	   $("#userName").blur(function(){
		   autoPwd();
	   });
		
		$('#userName').focus(function(){
			$(".login-status").empty();
			var txt_value = $(this).val();
			if(txt_value=="请输入用户名"){
				$(this).val("");
			}else if(txt_value==""||txt_value==null){
				$(this).val("请输入用户名");
			}
		});
		
		$('#userName').blur(function(){
			var txt_value = $(this).val();
			if(txt_value=="请输入用户名"){
				$(this).val("");
			}else if(txt_value==""||txt_value==null){
				$(this).val("请输入用户名");
			}
		});
		
		$('#password').focus(function(){
			$(".login-status").empty();
			var txt_value = $(this).val();
			if(txt_value=="请输入密码"){
				$(this).val("");
				$(this).attr("type","password");
			}else if(txt_value==""||txt_value==null){
				$(this).val("请输入密码");
				$(this).attr("type","text");
			}	
		});
		
		$('#password').blur(function(){
			var txt_value = $(this).val();
			if(txt_value=="请输入密码"){
				$(this).val("");
				$(this).attr("type","password");
			}else if(txt_value==""||txt_value==null){
				$(this).val("请输入密码");
				$(this).attr("type","text");
			}	
		});
		
		//点击登录按钮
		$("#loginBtn").click(function(){
			var userName = $("#userName").val();
			var password = $("#password").val();
			var ck_rmbUser = $("#ck_rmbUser").is(':checked');
			if(userName==""||userName==null||userName=="请输入用户名"||password==""||password==null||password=="请输入密码"){
				$(".login-status").html("请输入用户名密码");
				return false;
			}
			var param = {"userName":userName,"password":password};
			$.ajax({
				url: obj.ctx+"/login",
				type: "GET",
				dataType:'json',
				data:param,
				async:false,
				beforeSend: function(){
					$(".login-status").html("正在登录中.....");
				},
				success: function(rs){
					if(rs.result){
						if(rs.ret == '3'){
							window.location = obj.ctx+"/toInformation";
						}
						saveUserNameAndPassword(userName, password, ck_rmbUser);
						window.location = obj.ctx+"/index";
					}else{
						$(".login-status").html(rs.msg);
						console.log("login fail");
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			}); 
		});
	};
};

namespaceLogin.registerJS = function(){
	this.ctx = $("#ctx").val();
	this.init=function(){	
		this.event();
	}; 	
	this.event=function(){

		var obj = this;
		
		function readFile(file) {
	        //判断是否是图片类型
	        if (!/image\/\w+/.test(file.type)) {
	        	console.log('只能选择图片');
	            return false;
	        }
	        var reader = new FileReader();
	        reader.readAsDataURL(file);
	        reader.onload = function (e) { 
	        	$("#pic").attr("src",this.result);
	        };
	    }
		//头像选择
		$("#pic").click(function () {
			$("#upload").click(); //隐藏了input:file样式后，点击头像就可以本地上传
			$("#upload").on("change",function(){
				var objUrl = readFile(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
				if (objUrl) {
					$("#pic").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
				}
			});
		});
		
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
		
		$("#userName").focus(function(){
			$("#userNameExist-tip").empty();
		});
		
		//点击注册按钮
		$("#btn-register").click(function(){
			var name = $.trim($("#name").val());
			var phone = $.trim($("#phone").val());
			var email = $.trim($("#email").val());
			var address = $.trim($("#address").val());
			var userName = $.trim($("#userName").val());
			var password = $.trim($("#password").val());
			var logo = $("#pic").attr("src");
			var contact = $("#contact").val();
			var contactTel = $("#contactTel").val();
			var param = {"contact":contact,"contactTel":contactTel,"name":name,"phone":phone,"email":email,"address":address,"userName":userName,"password":password,"logo":logo};
			//校验名称
			if(name==null||name==''){
				$("#name-tip").removeClass("tip-blue").addClass("tip-red");
				return false;
			}else{
				$("#name-tip").removeClass("tip-red").addClass("tip-blue");
			}
			//校验手机号
			if(!isPhoneNo(phone)){
				$("#phone-tip").removeClass("tip-blue").addClass("tip-red");
				return false;
			}else{
				$("#phone-tip").removeClass("tip-red").addClass("tip-blue");
			}
			//校验邮箱
			if(!isEmail(email)){
				$("#email-tip").removeClass("tip-blue").addClass("tip-red");
				return false;
			}else{
				$("#email-tip").removeClass("tip-red").addClass("tip-blue");
			}
			//校验地址
			if(address==null||address==''){
				$("#address-tip").removeClass("tip-blue").addClass("tip-red");
				return false;
			}else{
				$("#address-tip").removeClass("tip-red").addClass("tip-blue");
			}
			//校验负责人
			if(contact==null||contact==''){
				$("#contact-tip").removeClass("tip-blue").addClass("tip-red");
				return false;
			}else{
				$("#contact-tip").removeClass("tip-red").addClass("tip-blue");
			}
			//校验负责人
			if(!isPhoneNo(contactTel)){
				$("#contactTel-tip").removeClass("tip-blue").addClass("tip-red");
				return false;
			}else{
				$("#contactTel-tip").removeClass("tip-red").addClass("tip-blue");
			}
			//用户名校验
			if(!isUserName(userName)){
				$("#userName-tip").removeClass("tip-blue").addClass("tip-red");
				return false;
			}else{
				$("#userName-tip").removeClass("tip-red").addClass("tip-blue");
			}
			//密码校验
			if(!isUserName(password)){
				$("#password-tip").removeClass("tip-blue").addClass("tip-red");
				return false;
			}else{
				$("#password-tip").removeClass("tip-red").addClass("tip-blue");
			}
			$.ajax({
				url: obj.ctx+"/registerSave",
				type: "POST",
				dataType:'text',
				data:param,
				async:false,
				success: function(result){
					if(result=="ok"){
						window.location = obj.ctx+"/";
					}else if(result=="existingUser"){
						$("#userNameExist-tip").html("用户名已被使用");
					}else{
						console.log("register fail");
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			}); 
		});
	};
};
