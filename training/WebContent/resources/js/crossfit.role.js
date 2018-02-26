var namespaceRole = crossfit.namespace("cfer.role");

/**
 * 角色列表
 */
namespaceRole.roleList = function(){
	this.ctx = $("#ctx").val();
	this.init=function(){
		this.event();
	};
	this.event=function(){
		var obj = this;

        $("#role-list").on("click", ".saveCoachMenu", function () {
            var _menuli = $(this).parent().next();
            var _selectedMenu = _menuli.find('input[name="menu"]:checked');
            var menuIds = "";
            _selectedMenu.each(function (index,element) {
                menuIds += $(this).val()+",";
            });
            if(menuIds.length>0){
                menuIds = menuIds.substring(0, menuIds.length-1);
            }
            $.ajax({
                url: obj.ctx+"/setting/role/updateCoachMenu",
                type: 'POST',
                dataType:'json',
                data:{menuIds:menuIds},
                success: function(rs){
                    if(rs.result){
                        BootstrapDialog.show({
                            title: "提示",
                            message: "角色权限修改成功",
                            buttons: [{
                                label: "关闭",
                                action: function (dialog) {
                                    dialog.close();
                                }
                            }]
                        })
                    }else{
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
        });

		$("#role-list").on("click", ".save", function () {
			var roleId = $(this).parent().data("id");
			var _menuli = $(this).parent().next();
            var _selectedMenu = _menuli.find('input[name="menu"]:checked');
            var menuIds = "";
            _selectedMenu.each(function (index,element) {
                menuIds += $(this).val()+",";
            });
            if(menuIds.length>0){
                menuIds = menuIds.substring(0, menuIds.length-1);
            }
            $.ajax({
                url: obj.ctx+"/setting/role/updateMenu",
                type: 'POST',
                dataType:'json',
                data:{roleId:roleId, menuIds:menuIds},
                success: function(rs){
                    if(rs.result){
                        BootstrapDialog.show({
                            title: "提示",
                            message: "角色权限修改成功",
                            buttons: [{
                                label: "关闭",
                                action: function (dialog) {
                                    dialog.close();
                                }
                            }]
                        })
                    }else{
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
		});
		
		$("#role-list").on("click",".delete",function(){
			var id = $(this).parent().data('id');
			BootstrapDialog.show({
				title:"警告",
				message:"确认删除该角色以及该角色下所有账户？",
				buttons:[
					{
						label:"取消",
						action:function (dialog) {
							dialog.close();
						}
					},
					{
						label:"确认",
						action:function (dialog) {
							$.ajax({
								url: obj.ctx+"/setting/role/delete/"+id,
								type: 'POST',
								dataType:'json',
								success: function(rs){
									dialog.close();
                                    if(rs.result) {
                                        window.location = obj.ctx + "/setting/role";
                                    }else{
                                        dialog.close();
                                        BootstrapDialog.show({
                                            title: "提示",
                                            message: "服务器异常，请稍后重试",
                                            buttons: [{
                                                label: "关闭",
                                                action: function (dialog) {
                                                    dialog.close();
                                                }
                                            }]
                                        });
                                    }
								},
								error:function(XMLHttpRequest){
									dialog.close();
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
					}
				]
			});
		});
	};
};

/**
 * 编辑
 */
namespaceRole.edit = function(){
	this.ctx = $("#ctx").val();
	this.init=function(){
		this.event();
	};
	this.event=function(){
		var obj = this;

		$("#hold").click(function(){
			var name = $("#name").val();
			if(name==null||name==""){
				$("#name-tip").html("角色名称不能为空");
				return false;
			}else{
				$("#name-tip").empty();
			}
			var param = {name:name};
			$.ajax({
				url: obj.ctx+"/setting/role/save",
				type: 'POST',
				dataType:'json',
				data:param,
				async:false,
				success: function(rs){
					if(rs.result){
						window.location = obj.ctx+"/setting/role";
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
				$("#name-tip").html("角色名不能为空");
				return false;
			}else{
				$("#name-tip").empty();
			}
			var param = {id:id,name:name};
			$.ajax({
				url: obj.ctx+"/setting/supporter/update",
				type: 'POST',
				dataType:'json',
				data:param,
				async:false,
				success: function(rs){
					if(rs.result){
						window.location = obj.ctx+"/setting/role";
					}
				},
				error:function(XMLHttpRequest){
					console.log('服务器异常');
				}
			});	
		});
		
		$("#cancel").click(function(){
			window.location = obj.ctx+"/setting/role";
		});
	};
};