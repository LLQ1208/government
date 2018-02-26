/**
 * Created by anran on 2017/11/30.
 */

function editRole(roleId){
    $("#"+roleId).css("display","block");
}

function cencelRole(roleId){
    $("#"+roleId).css("display","none");
}

function sureRole(roleId){
    var roleDiv=$("#"+roleId);
    var selectMenu=roleDiv.find('input[name="menu"]:checked');
    var menuIds = "";
    selectMenu.each(function (index,element) {
        menuIds += $(this).val()+",";
    });
    if(menuIds.length>0){
        menuIds = menuIds.substring(0, menuIds.length-1);
    }
    var ctx = $("#ctx").val();
    $.ajax({
        url: ctx+"/setting/roleNew/updateMenuNew",
        type: 'POST',
        dataType:'json',
        data:{roleId:roleId, menuIds:menuIds},
        success: function(rs){
            if(rs.result){
                window.location = ctx + "/setting/roleNew";
            }else{

            }
        },
        error:function(XMLHttpRequest){
            alert("服务器异常，请稍后重试");
        }
    });
}