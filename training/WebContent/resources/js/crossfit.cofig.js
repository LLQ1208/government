var namespaceConfig = crossfit.namespace("cfer.config");

/**
 * 配置列表
 */
namespaceConfig.configList = function(){
	this.ctx = $("#ctx").val();
	this.init=function(){	
		this.event();
	};
	
	this.event=function(){
		var obj = this;
		
		$("#hold").click(function(){
			var orderCancelTime = $("#orderCancelTime").val();
			var peopleLimit = $("#peopleLimit").val();
			var orderDay = $("#orderDay").val();
			var param = {"orderDay":orderDay,"peopleLimit":peopleLimit,"orderCancelTime":orderCancelTime};
			$.ajax({
				url: obj.ctx+"/setting/config/save",
				type: 'POST',
				dataType:'text',
				data:param,
				async:false,
				success: function(result){
					if(result=='ok'){
						window.location = obj.ctx+"/setting/config";
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
	};
};
