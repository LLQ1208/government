var namespaceNotification = crossfit.namespace("cfer.notification");

/**
 * member列表
 */
namespaceNotification.notificationList = function(){
	this.ctx = $("#ctx").val();
	this.isPageInit = true;   //是否为初始化分页列表
	this.totalRow = $('#totalRow').val();
	this.conditions = {
		pageSize:10,
		pageIndex:1,
		keyword:$("#keyword").val().trim()
	};
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
			url: obj.ctx+"/notification/getData",
			type: 'POST',
			dataType:'json',
			data:obj.conditions,
			async:false,
			success: function(pager){
				var list = pager.list;
				var _html = '<div class="row member">';
				$.each(list, function (i, item) {
					_html+='<div class="col-sm-12 notic-item">';
					_html+='<div class="col-sm-2">';
					_html+='<div class="cover"><img src="'+item.cover+'"></div>';
					_html+='</div>';
					_html+='<div class="col-sm-8">';
					_html+='<div class="title">'+item.title+'</div>';
					_html+='<div class="notic-content">'+item.content+'</div>';
					_html+='</div>';
					_html+='<div class="col-sm-2">';
					_html+='<div class="pushTime pull-right">'+item.pushTimeFormat+'</div>';
					_html+='<div class="heat pull-right">阅读：'+item.heat+'</div>';
					_html+='</div>';
					_html+='</div>';
				});
				_html+='</div>';
				$("#member-list").html(_html);
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
		
		$('.memberBanner').click(function() {
			if($('.popBox').is(':visible')){
				$('.popBox').css('display', 'none');
				$('.content').fadeIn();
			}		
		});
	};
};

/**
 * 添加
 */
namespaceNotification.add = function(){
	this.ctx = $("#ctx").val();
	this.init=function(){
		this.event();
	};

	this.event=function(){
		var obj = this;

		$("#content").summernote({
			height: 300,                 // set editor height
			minHeight: null,             // set minimum height of editor
			maxHeight: null,             // set maximum height of editor
			focus: true,
			lang:'zh-CN',
		});
		
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

		$("#hold").click(function(){
			var title = $("#title").val();
			var content = $("#content").code();
			var pushTime = $("#pushTime").val();
			var pushObject = $("#pushObject").val();
			var pushSex = $("#pushSex").val();
			var cover = $("#pic").attr("src");
			var param = {title:title,content:content,pushTime:pushTime,pushSex:pushSex,pushObject:pushObject,cover:cover};
			/*if(title==null||title==''){
				$("#title-tip").html("标题不能为空");
				return false;
			}else{
				$("#title-tip").empty();
			}*/
			$.ajax({
				url: obj.ctx+"/notification/save",
				type: 'POST',
				dataType:'json',
				data:param,
				async:false,
				success: function(rs){
					if(rs.result){
						window.location = obj.ctx+"/notification";
					}else{
						$("#photo-tip").html(rs.msg);
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
		$("#cancel").click(function(){
			window.location = obj.ctx+"/notification";
		});
		
		// 时间
		$("#pushTime").datetimepicker({
            format: "yyyy/mm/dd",  /* 控制显示格式，默认为空，显示小时分钟 */
            autoclose: true,
            weekStart: 1,
            language:  'zh-CN',
            startView: 3,
            minView: 2,
            maxView: 4,     
            pickerPosition: "bottom-left"
        });

	};
};