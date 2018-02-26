/**
 * Created with webstorm.
 * Author: dameng
 * Date: 2017/12/16
 * Time: 09:08
 *
 */
$(function () {

    $('.addRole').on('click', function () {
        var boxid = $(this).siblings('.hBoxId').val();
        $("#boxId").val(boxid);
        $("#roleName").val("");
        $('.add-role-title').html('添加角色');
        $('.add-role-box').removeClass('edit-role-box').fadeIn();
    });
    $('.cancel-btn').on('click', function () {
        $('.add-role-box').fadeOut();
    });

    $('.delete-btn').on('click', function () {
        if(confirm("是否确认要删除？")){
            var ctx = $("#ctx").val();
            var roleId = $("#roleId").val();
            $.ajax({
                url: ctx+"/setting/roleNew/delete",
                type: 'POST',
                dataType:'json',
                data:{id:roleId},
                success: function(rs){
                    if(rs.result){
                        window.location = ctx + "/setting/employee";
                    }else{
                        alert(rs.msg);
                    }
                },
                error:function(XMLHttpRequest){
                    alert("服务器异常，请稍后重试");
                }
            });
            $('.add-role-box').fadeOut();
        }
    });

    $('.save-btn').on('click', function () {
        var ctx = $("#ctx").val();
        var name = $("#roleName").val();
        var roleId = $("#roleId").val();
        var boxId = $("#boxId").val();
        var url;
        if(roleId==null || roleId=="" || roleId==0 || roleId=="0"){
            url=ctx+"/setting/roleNew/save";
        }else{
            url=ctx+"/setting/roleNew/update";
        }
        if(name==null||name==""){
           alert("角色名字不能为空!");
        }else{

            $.ajax({
                url: url,
                type: 'POST',
                dataType:'json',
                data:{name:name,id:roleId,boxId:boxId},
                success: function(rs){
                    if(rs.result){
                        window.location = ctx + "/setting/employee";
                    }else{
                        alert(rs.msg);
                    }
                },
                error:function(XMLHttpRequest){
                    alert("服务器异常，请稍后重试");
                }
            });
            $('.add-role-box').fadeOut();
        }

    });

    //员工编辑
    $('.customer .coachContent .coachCommon .name .txt').on('click', function () {
        //console.log("--------"+$(this).siblings('.employeeId').val());
        var employeeId = $(this).siblings('.employeeId').val();
        var employeeRoleId = $(this).siblings('.employeeRoleId').val();
        var callowDelete=$(this).siblings('.callowDelete').val();

        var ctx = $("#ctx").val();
        window.location = ctx+"/setting/employee/updateEmployee?employeeId="+employeeId+"&roleId="+employeeRoleId+"&callowDelete="+callowDelete;
    });

    //角色
    $('.addCoach .coachLine .name .txt').on('click', function () {
        var allowDelete=$(this).siblings('.allowDelete').val();
        if(allowDelete==0){
            var roleId=$(this).siblings('.roleId').val();
            var roleName=$(this).siblings('.roleName').val();
            var boxId=$(this).siblings('.rBoxId').val();
            $("#roleName").val(roleName);
            $("#roleId").val(roleId);
            $("#boxId").val(boxId);
            $('.add-role-title').html('编辑角色');
            $('.add-role-box').addClass('edit-role-box').fadeIn();
        }
    })

    $('.addBtn').on('click', function(){
        var itemSize = $(this).siblings('.itemSize').val();
        var roleIdAdd=$(this).siblings('.roleIdAdd').val();
        var eallowDelete=$(this).siblings('.eallowDelete').val();
        var boxId=$(this).siblings('.eBoxId').val();
        var ctx = $("#ctx").val();
        if(eallowDelete == 2 && itemSize >=1){
            alert("每个训练馆只能有一个店长");
            return false;
        }
        window.location = ctx+"/setting/employee/addEmployee?roleId="+roleIdAdd+"&boxId="+boxId+"&allowDelete="+eallowDelete;
    })

})