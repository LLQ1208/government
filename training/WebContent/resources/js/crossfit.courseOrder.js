var namespaceCourseOrder = crossfit.namespace("cfer.courseOrder");

/**
 * 约课列表
 */
namespaceCourseOrder.courseOrderList = function(){
	this.ctx = $("#ctx").val();
	this.addOrderModal = $("#addOrderModal");
	this.date = "";
	this.courseId="";
	this.init=function(){
		this.initDate();
		this.event();
	};
	
	this.getData = function(){
		var obj = this;
		var courseType = $("#courseType").val();
		$.ajax({
			url: obj.ctx+"/courseOrder/getData",
			type: 'POST',
			dataType:'text',
			data:{date:obj.date,courseType:courseType},
			async:false,
			success: function(result){
				$("#course-ul").empty().html(result);
			},
			error:function(XMLHttpRequest){
				console.log('服务器异常');
			}
		});	
	};
	
	this.event=function(){
		var obj = this;
		$("#courseType").change(function(){
			obj.getData();
		});
		
		$("#course-ul").on("click",".edit",function(){
			var editText = $(this).text();
			if(editText === "编辑") {
				$(this).parent().next().find(".delete").css("display","block");
				$(this).text("完成");
			}else if(editText === "完成") {
				$(this).parent().next().find(".delete").css("display","none");
				$(this).text("编辑");
			}
		});
		
		$("#course-ul").on("click",".delete",function(){
			var courseId = $(this).data("courseid");
			var memberId = $(this).data("memberid");
			BootstrapDialog.show({
				title: "提示",
				message: "确认删除该预约？",
				buttons: [{
					label: "关闭",
					action: function (dialog) {
						dialog.close();
					}
				},{
					label:"确认",
					action:function(dialog){
						dialog.close();
						$.ajax({
							url: obj.ctx+"/courseOrder/cancelReserve",
							type: 'POST',
							dataType:'json',
							data:{"date":obj.date,"courseId":courseId,"memberId":memberId},
							async:false,
							success: function(rs){
								if(rs.result){
									obj.getData();
								}else{
									BootstrapDialog.show({
										title: "提示",
										message: "删除失败，请稍后重试",
										buttons: [{
											label: "关闭",
											action: function (dialog) {
												dialog.close();
												obj.getData();
											}
										}]
									})
								}
							},
							error:function(XMLHttpRequest){
								BootstrapDialog.show({
									title: "提示",
									message: "服务器异常，请稍后重试",
									buttons: [{
										label: "关闭",
										action: function (dialog) {
											dialog.close();
										}
									}]
								})
							}
						});
					}
				}]
			})
		});

		$("#course-ul").on("click", ".reserve", function () {
			obj.courseId = $(this).data("courseid");
			obj.addOrderModal.modal("show");
		});

		$("#save").click(function () {
			var name = $("#name").val();
			$.ajax({
				url: obj.ctx+"/courseOrder/addPatchReserve",
				type: 'POST',
				dataType:'json',
				data:{name:name,date:obj.date,courseId:obj.courseId},
                async: false,
				success: function(rs){
					obj.addOrderModal.modal("hide");
					if(rs.result){
						obj.getData();
					}else{
						BootstrapDialog.show({
							title: "提示",
							message: "<span style='color:#ff0000;'>"+rs.msg+"</span>",
							buttons: [{
								label: "关闭",
								action: function (dialog) {
									dialog.close();
								}
							}]
						})
					}
				},
				error:function(XMLHttpRequest){
					obj.addOrderModal.modal("hide");
					BootstrapDialog.show({
						title: "提示",
						message: "服务器异常，请稍后重试",
						buttons: [{
							label: "关闭",
							action: function (dialog) {
								dialog.close();
							}
						}]
					})
				}
			});
            $("#name").val("");
		});
	};
	
	this.initDate=function(){
		var obj = this;
		// 时间
		function GetDateStr(day,AddDayCount) {
				var dd = new Date(day);
				dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
				var y = dd.getFullYear();
				var m = dd.getMonth()+1;//获取当前月份的日期
				var d = dd.getDate();
				var dateStr=y+"/"+m+"/"+d;
				var weekArr=['星期日','星期一','星期二','星期三','星期四','星期五','星期六'];
				var week=new Date(dateStr).getDay();
				return [dateStr,weekArr[week]];
		}
		Date.prototype.format = function(fmt) { 
				var o = { 
					"M+" : this.getMonth()+1,                 //月份 
					"d+" : this.getDate(),                    //日 
					"h+" : this.getHours(),                   //小时 
					"m+" : this.getMinutes(),                 //分 
					"s+" : this.getSeconds(),                 //秒 
					"q+" : Math.floor((this.getMonth()+3)/3), //季度 
					"S"  : this.getMilliseconds()             //毫秒 
				}; 
				if(/(y+)/.test(fmt)) {
					fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
				}
				for(var k in o) {
					if(new RegExp("("+ k +")").test(fmt)){
						fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
					}
				}
				return fmt; 
		};
		
		var day=new Date().format("yyyy/MM/dd");
		obj.date = day;
		var dates=GetDateStr(day,0);
		$('.stime').html('<o>'+dates[0]+'</o>  '+dates[1]);
		
		$('.minus').click(function(e) {
			dates=GetDateStr(day,-1);
			day=dates[0];
			obj.date = day;
			$('.stime').html('<o>'+dates[0]+'</o>  '+dates[1]);
			obj.getData();	
		});
		$('.plus').click(function(e) {
			dates=GetDateStr(day,+1);
			day=dates[0];
			obj.date = day;
			$('.stime').html('<o>'+dates[0]+'</o>  '+dates[1]);
			obj.getData();
		});

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
		
		$("#export").click(function(){
			window.location = obj.ctx+"/courseOrder/export?date="+obj.date;
		});
		
		/*$("#course-ul").on("click",".reserve",function(){
			var courseId = $(this).data("courseid");
			$.ajax({
				url: obj.ctx+"/courseOrder/reserve",
				type: 'POST',
				dataType:'text',
				data:{"date":obj.date,"courseId":courseId},
				async:false,
				success: function(result){
					if(result=="ok"){
						obj.getData();
					}else{
						
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});
		});
		
		$("#course-ul").on("click",".cancel-reserve",function(){
			var courseId = $(this).data("courseid");
			$.ajax({
				url: obj.ctx+"/courseOrder/cancelReserve",
				type: 'POST',
				dataType:'text',
				data:{"date":obj.date,"courseId":courseId},
				async:false,
				success: function(result){
					if(result=="ok"){
						obj.getData();
					}else{
						
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});
		});*/
	};
};