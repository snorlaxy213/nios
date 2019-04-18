$(function () {
    to_page(0);
    getUserRole();
});

function to_page(pn) {
    $.ajax({
        url: "/nios/user/user",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            build_users_table(result);
        }
    });
}

function build_users_table(result) {
    $("#users_table tbody").empty();
    let userInfo = result.content.pageInfo.list;
    $.each(userInfo, function (index, item) {
        let checkBoxTD = $("<td><input type='checkbox' class='check_item'/></td>");
        let userIdTd = $("<td></td>").append(item.id);
        let userNameTd = $("<td></td>").append(item.name);
        let MobileTd = $("<td></td>").append(item.mobile);
        let EmailTd = $("<td></td>").append(item.email);
        let OfficeTd = $("<td></td>").append(item.office);
        let editBtn = $("<button></button>").addClass("btn btn-info  btn-sm edit_btn").append($("<span></span>").addClass(
            "glyphicon glyphicon-pencil")).append("update");
        editBtn.attr("edit-id", item.id);
        let delBth = $("<button></button>").addClass(
            "btn btn-danger  btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append(
            "delete");
        delBth.attr("del-id", item.id);
        let btnTd = $("<td></td>").append(editBtn).append(" ").append(
            delBth);
        $("<tr></tr>").append(checkBoxTD).append(userIdTd).append(userNameTd).append(MobileTd).append(EmailTd).append(OfficeTd).append(btnTd).appendTo("#users_table tbody");
    });
}

$("#user_save_btn").click(function () {
    if (!validate_add_form()) {
        return false;
    }
    let json = getJson();
    $.ajax({
        url: "/nios/user/user",
        type: "POST",
        async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: json,
        success: function (result) {
            if (result.code == 100) {
                to_page(0);
                reset_form("#User_Form");
                alert("保存成功")
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
        }
    });
});

function reset_form(ele) {
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help_block").text("");
}

function validate_add_form() {
    //userId validation
    let userId = $("#UserID").val();
    //6-16 digit number or letter
    let regUserId = /(^[a-zA-Z0-9_-]{6,16}$)/;
    if (!regUserId.test(userId)) {
        show_validate_msg("#UserID", "error", "The user name can be a combination of 6-16 digits in English and a number");
        return false;
    } else {
        show_validate_msg("#UserID", "success", "");
    }

    //email validation
    let email = $("#Email").val();
    let regexMail = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (!regexMail.test(email)) {
        show_validate_msg("#Email", "error", "email format is not right");
        return false
    } else {
        show_validate_msg("#Email", "success", "");
    }
    return true;
}

function show_validate_msg(ele, status, msg) {
    $(ele).parent().removeClass("has-success has-error");
    $(ele).next("span").text("");

    if ("error" == status) {
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }
}

$(document).on("click", ".edit_btn", function () {
    getUser($(this).attr("edit-id"));
    $("#user_save_btn").attr("edit-id", $(this).attr("edit-id"));
});

$(document).on("click", ".delete_btn", function () {
    let UserId = $(this).attr("del-id");
    $.ajax({
        url: "/nios/user/user/"+UserId,
        type: "DELETE",
        success: function (result) {
            if (result.code == 100) {
                to_page(0);
            }
        }
    });
});

$("#check_all").click(function(){
    $(".check_item").prop("checked",$(this).prop("checked"));
});

$(document).on("click",".check_item",function(){
    let flag=$(".check_item:checked").length==$(".check_item").length
    $("#check_all").prop("checked",flag);
});

$("#delete_all_btn").click(function(){
    let userNames="";
    let delId_str="";
    $.each($(".check_item:checked"),function(){
        userNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
        delId_str+=$(this).parents("tr").find("td:eq(1)").text()+"-";
    });
    userNames=userNames.substring(0,userNames.length-1);
    delId_str=delId_str.substring(0,delId_str.length-1);
    if(confirm("确认删除【"+userNames+"】吗？")){
        $.ajax({
            url:"/nios/user/user/"+delId_str,
            type:"DELETE",
            success:function(result)
            {
                to_page(0);
            }
        });
    }
});

function getUser(id) {
    $.ajax({
        url: "/nios/user/user/" + id,
        type: "GET",
        success: function (result) {
            let data = result.content.user;
            let userRoles = data.userRoleDtos;
            $("#UserID").val(data.id);
            $("#UserName").val(data.name);
            $("#Email").val(data.email);
            $("#Mobile").val(data.mobile);
            $("#Office").val(data.office).select2();
            $("#UserRoles").val(userRoles[0].id).select2();
            // $("#UserRoles").val(userRoles[0].id);
        }
    });
}

function getUserRole() {
    $.ajax({
        url: "/nios/userRole/userRole",
        type: "GET",
        success: function (result) {
            let userRoles = result.content.list;
            $.each(userRoles, function (i, item) {
                if (i == 0) {
                    let optionEle = $("<option></option>").append(item.name).attr("value", item.id);
                    optionEle.appendTo("#UserRoles");
                } else {
                    let optionEle = $("<option></option>").append(item.name).attr("value", item.id);
                    optionEle.appendTo("#UserRoles");
                }
            })
            $("#UserRoles").val(userRoles[0].id).select2();
        }
    });
}

function getJson() {
    let object = {};
    let params = [];

    object['id'] = $("#UserID").val();
    object['name'] = $("#UserName").val();
    object['email'] = $("#Email").val();
    object['mobile'] = $("#Mobile").val();
    object['office'] = $("#Office").val();

    $("#UserRoles option:selected").each(function () {
        let value = $(this).val();
        let obj = {};
        obj["id"] = value;
        params.push(obj);
    })

    object['userRoleDtos'] = params;

    let json = JSON.stringify(object);
    return json
}