$(function () {
    to_page(0);
});

function to_page(pn) {
    $.ajax({
        url: "/nios/userRole/userRole",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            build_users_table(result);

            //build_page_info(result);

            //build_page_nav(result);
        }
    });
}

function build_users_table(result) {
    $("#userRoles_table tbody").empty();
    let userRoleInfo = result.content.list;
    $.each(userRoleInfo, function (index, item) {
        let checkBoxTD = $("<td><input type='checkbox' class='check_item'/></td>");
        let userRoleIdTd = $("<td></td>").append(item.id);
        let userRoleNameTd = $("<td></td>").append(item.name);
        let Status = $("<td></td>").append(item.status);
        let editBtn = $("<button></button>").addClass("btn btn-primary  btn-sm edit_btn").append($("<span></span>").addClass(
            "glyphicon glyphicon-pencil")).append("update");
        editBtn.attr("edit-id", item.id);
        let delBth = $("<button></button>").addClass(
            "btn btn-danger  btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append(
            "dalete");
        delBth.attr("del-id", item.id);
        let btnTd = $("<td></td>").append(editBtn).append(" ").append(
            delBth);
        $("<tr></tr>").append(checkBoxTD).append(userRoleIdTd).append(userRoleNameTd).append(Status).append(btnTd).appendTo("#userRoles_table tbody");
    });
}

$(document).on("click", ".edit_btn", function () {
    getUserRole($(this).attr("edit-id"));
    $("#user_save_btn").attr("edit-id", $(this).attr("edit-id"));
});

function getUserRole(id) {
    $.ajax({
        url: "/nios/userRole/userRole/" + id,
        type: "GET",
        success: function (result) {
            let data = result.content.userRole;
            $("#UserRoleID").val(data.id);
            $("#UserRoleName").val(data.name);
            if (data.status == "Y") {
                $("input[name='Status'][value='Y']").attr("checked", true);
            } else {
                $("input[name='Status'][value='N']").attr("checked", true);
            }

        }
    });
}

$("#userRole_save_btn").click(function () {
    let json = getJson();
    $.ajax({
        url: "/nios/userRole/userRole",
        type: "POST",
        async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: json,
        success: function (result) {
            if (result.code == 100) {
                to_page(0);
                reset_form("#UserRole_Form");
                alert("保存成功");
            } else if (result.code == 300) {
                let errorMessages = result.errorMessages;
                let str = "";
                for (let i = 0; i < errorMessages.length; i++) {
                    if (i != errorMessages.length-1) {
                        str = str + errorMessages[i] + ",";
                    } else {
                        str = str + errorMessages[i];
                    }
                }
                alert(str+"不可以为空");
            }
        },
    });
});

function getJson() {
    let object = {};

    // object['id'] = $("#UserRoleID").val();
    object['name'] = $("#UserRoleName").val();
    object['status'] = $("input[name='Status']:checked").val();

    let json = JSON.stringify(object);
    return json
}

function reset_form(ele) {
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help_block").text("");
    $(ele).find("*").attr("checked", false);
}

$("#check_all").click(function(){
    $(".check_item").prop("checked",$(this).prop("checked"));
});

$(document).on("click",".check_item",function(){
    let flag=$(".check_item:checked").length==$(".check_item").length
    $("#check_all").prop("checked",flag);
});

$("#delete_all_btn").click(function(){
    let userRoleNames="";
    let delId_str="";
    $.each($(".check_item:checked"),function(){
        userRoleNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
        delId_str+=$(this).parents("tr").find("td:eq(1)").text()+"-";
    });
    userRoleNames=userRoleNames.substring(0,userRoleNames.length-1);
    delId_str=delId_str.substring(0,delId_str.length-1);
    if(confirm("确认删除【"+userRoleNames+"】吗？")){
        $.ajax({
            url:"/nios/userRole/userRole/"+delId_str,
            type:"DELETE",
            success:function(result)
            {
                to_page(0);
            }
        });
    }
});


$(document).on("click", ".delete_btn", function () {
    let UserRoleId = $(this).attr("del-id");
    $.ajax({
        url: "/nios/userRole/userRole/"+UserRoleId,
        type: "DELETE",
        success: function (result) {
            if (result.code == 100) {
                to_page(0);
            }
        }
    });
});