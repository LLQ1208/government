var namespaceMember = crossfit.namespace("cfer.member");

/**
 * member列表
 */
namespaceMember.memberList = function(){


	this.ctx = $("#ctx").val();
	this.isPageInit = true;   //是否为初始化分页列表
	this.totalRow = $('#totalRow').val();
	this.conditions = {pageSize:20,pageIndex:1,keyword:$("#keyword").val().trim()};
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
			url: obj.ctx+"/member/getMemberJson",
			type: 'POST',
			dataType:'json',
			data:obj.conditions,
			async:false,
			success: function(pager){
				var list = pager.list;
				var members = '<div class="row member">';
				$.each(list, function (i, item) {
					members+='<div class="col-sm-6">';
					members+='<div class="list borderTop member-list" data-id="'+item.memberId+'">';
					if(item.member.avatar==null||item.member.avatar==''){
						members+='<div class="pic fl"><img src="'+obj.ctx+'/resources/images/members.png"></div>';
					}else{
						members+='<div class="pic fl"><img src="'+item.member.avatar+'"></div>';
					}
					members+='<ul class="txt fl">';
					if(item.timeStatus==3){
						members+='<li class="name-timeout">'+item.member.name;
					}else{
						members+='<li class="name">'+item.member.name;
					}
					if(item.timeStatus==2){
						if(item.memberType==1){
							members+='<span class="label label-danger pull-right">即将过期</span>';
						}else if(item.memberType==2){
							members+='<span class="label label-primary pull-right">即将过期</span>';
						}
					}else if(item.timeStatus==3){
						members+='<span class="label label-default pull-right">已过期</span>';
					}
					members+="</li>";
					members+='<li class="phone">'+item.member.phone+'</li>';
					if(item.memberType==1){
						members+='<li class="time">'+item.memberStartTimeFormat+'-'+item.memberEndTimeFormat+'</li>';
					}else{
						members+='<li class="time">'+item.memberRemainNum+'次</li>';
					}
					members+='</ul>';
					members+='</div>';
					members+='</div>';
				});
				members+='</div>';
				$("#member-list").html(members);
				$("#totalRow").val(pager.totalRow);
				obj.totalRow = pager.totalRow;
			},
			complete: function(xhr,status) {
				$('.list').click(function(e) {
					var id = $(this).data('id');
					obj.getMemberInfo(id);
					$(this).parents('.content').css('display', 'none');
					$('.popBox').fadeIn();
					e = e || window.event;  
					if(e.stopPropagation) { //W3C阻止冒泡方法  
						e.stopPropagation();  
					} else {  
						e.cancelBubble = true; //IE阻止冒泡方法  
					}
				});
			},
			error:function(XMLHttpRequest){
				console.log('服务器异常');
			}
		});	
	};
	
	this.getMemberInfo = function(id){
		var obj = this;
		$("#member-info-box").empty();
		$.ajax({
			url: obj.ctx+"/member/getMemberInfo",
			type: 'GET',
			dataType:'text',
			data:{'id':id},
			async:false,
			success: function(result){
				$("#member-info-box").html(result);
			},
			error:function(XMLHttpRequest){
				console.log('服务器异常');
			}
		});
	};
	
	this.event=function(){
		var obj = this;
		
		$('.searchBtn').click(function(){
			obj.conditions.keyword = $("#keyword").val().trim();
			obj.conditions.pageIndex = 1;
			obj.generateData();
			$('#pagination-bottom').html('');
			obj.isPageInit = true;
			obj.initPage();
		});
		
		$('.member-list').click(function(e) {
			var id = $(this).data('id');
			obj.getMemberInfo(id);
			$(this).parents('.content').css('display', 'none');
			$('.popBox').fadeIn();
			e = e || window.event;  
			if(e.stopPropagation) { //W3C阻止冒泡方法  
				e.stopPropagation();  
			} else {  
				e.cancelBubble = true; //IE阻止冒泡方法  
			}
		});
		
		$('.memberBanner').click(function() {
			if($('.popBox').is(':visible')){
				$('.popBox').css('display', 'none');
				$('.content').fadeIn();
			}		
		});
	};
};

/**
 * 编辑
 */
namespaceMember.edit = function(){
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
			$("#photo-tip").empty();
		});
		//护照校验
		function isPassport(pinCode){
			var re1 = /^[a-zA-Z]{5,17}$/;
			var re2 = /^[a-zA-Z0-9]{5,17}$/;
			return re2.test(pinCode) || re1.test(pinCode);
		}
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
		// 验证身份证 
		function isPinCode(pinCode) { 
			var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
			return pattern.test(pinCode); 
		} 
		
		$("#hold").click(function(){
            var phone = $("#phone").val();
			var boxId = $("#boxId").val();
			var memberType = $("#memberType").find("li.cur").data("value");
			var memberStartTime = $("#memberStartTime").val();
			var memberEndTime = $("#memberEndTime").val();
			var dayLimit = $("#dayLimit").val();
			var memberRemainNum = $("#memberRemainNum").val();
            var _courseTypes = $('input[name="courseType"]:checked');
            var courseTypes = "";
            _courseTypes.each(function (index,element) {
                courseTypes += $(this).val()+",";
            });
            if(courseTypes.length>0){
                courseTypes = courseTypes.substring(0, courseTypes.length-1);
            }
			var param = {boxId:boxId,"courseTypes":courseTypes,"phone":phone,
					"memberType":memberType,"memberStartTime":memberStartTime,"memberEndTime":memberEndTime,
					"dayLimit":dayLimit,"memberRemainNum":memberRemainNum};
			if(!isPhoneNo(phone)){
				$("#phone-tip").html("请输入有效的手机号");
				return false;
			}else{
				$("#phone-tip").empty();
			}
			$.ajax({
				url: obj.ctx+"/member/save",
				type: 'POST',
				dataType:'text',
				data:param,
				async:false,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/member";
					}else if(result=="avatorError"){
						$("#photo-tip").html("照片大小不能超过50K");
					}else if(result=="existingUser"){
                        $("#phone-tip").html("手机号已被使用");
                    }
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
		$("#update").click(function(){
			var id= $("#id").val();
			var phone = $.trim($("#phone").val());
			var memberType = $("#memberType").find("li.cur").data("value");
			var memberStartTime = $("#memberStartTime").val();
			var memberEndTime = $("#memberEndTime").val();
			var dayLimit = $("#dayLimit").val();
			var memberRemainNum = $("#memberRemainNum").val();
            var _courseTypes = $('input[name="courseType"]:checked');
            var courseTypes = "";
            _courseTypes.each(function (index,element) {
                courseTypes += $(this).val()+",";
            });
            if(courseTypes.length>0){
                courseTypes = courseTypes.substring(0, courseTypes.length-1);
            }
			var param = {"courseTypes":courseTypes,"id":id,"phone":phone,
					"memberType":memberType,"memberStartTime":memberStartTime,"memberEndTime":memberEndTime,
					"dayLimit":dayLimit,"memberRemainNum":memberRemainNum};
			if(!isPhoneNo(phone)){
				$("#phone-tip").html("请输入有效的手机号");
				return false;
			}else{
				$("#phone-tip").empty();
			}
			$.ajax({
				url: obj.ctx+"/member/update",
				type: 'POST',
				dataType:'text',
				data:param,
				async:false,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/member";
					}else if(result=="avatorError"){
						$("#photo-tip").html("照片大小不能超过50K");
					}else if(result=="existingUser"){
						$("#phone-tip").html("手机号已被使用");
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
		$("#cancel").click(function(){
			window.location = obj.ctx+"/member";
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
		
		// 时间
		$(".form_datetime").datetimepicker({
            format: "yyyy/mm/dd",  /* 控制显示格式，默认为空，显示小时分钟 */
            autoclose: true,
            weekStart: 1,
            //startDate: "2014-08-14 10:00",
            language:  'zh-CN',
            startView: 3,
            minView: 2,
            maxView: 4,     
            pickerPosition: "bottom-left"
        }); 
        // 性别切换
        $('.selects li').click(function() {
        	$(this).addClass('cur').siblings().removeClass('cur');
        });
        // 月卡和次卡切换
        $('.cards li').click(function() {
        	$(this).addClass('cur').siblings().removeClass('cur');
        });
        $('.cards li.monthCard').click(function() {
        	$('.memberTime').removeClass("hidden");
        	$('.cardTime').addClass("hidden");
        });
         $('.cards li.timeCard').click(function() {
        	$('.memberTime').addClass("hidden");
        	$('.cardTime').removeClass("hidden");
        });
        /*$('.cardTime .plus').click(function() {
        	var i = $(this).siblings('input').val();
            i++;
            $(this).siblings('input').val(i);
        });
        $('.cardTime .minus').click(function() {
        	var i = $(this).siblings('input').val();
            i--;
            if(i<0){
            	i=0;
            }
            $(this).siblings('input').val(i);
        });*/
        
        $('.cardTime-days .plus').click(function() {
        	var i = $(this).siblings('input').val();
            i++;
            $(this).siblings('input').val(i);
        });
        $('.cardTime-days .minus').click(function() {
        	var i = $(this).siblings('input').val();
            i--;
            if(i<1){
            	i=1;
            }
            $(this).siblings('input').val(i);
        });
        $("#dayLimit").change(function(){
        	var dayLimit= parseInt($(this).val());
        	if(dayLimit<1){
        		$(this).val(1);
        	}
        });
        
        $('.cardTime-totals .plus').click(function() {
        	var i = $(this).siblings('input').val();
            i++;
            $(this).siblings('input').val(i);
        });
        $('.cardTime-totals .minus').click(function() {
        	var i = $(this).siblings('input').val();
            i--;
            if(i<0){
            	i=0;
            }
            $(this).siblings('input').val(i);
        });
	};
};

function changeWin(){
    $('.content-box').css({"height": $(window).height()});
}
