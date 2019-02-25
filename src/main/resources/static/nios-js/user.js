$(function () {
    to_page(0);
    getUserRole();
});

function to_page(pn) {
    $.ajax({
        url: "/nios/User/User",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            build_users_table(result);
        }
    });
}

function build_users_table(result) {
    $("#users_table tbody").empty();
    let userInfo = result.content.list;
    $.each(userInfo, function (index, item) {
        let checkBoxTD = $("<td><input type='checkbox' class='check_item'/></td>");
        let userIdTd = $("<td></td>").append(item.id);
        let userNameTd = $("<td></td>").append(item.name);
        let MobileTd = $("<td></td>").append(item.mobile);
        let EmailTd = $("<td></td>").append(item.email);
        let editBtn = $("<button></button>").addClass("btn btn-primary  btn-sm edit_btn").append($("<span></span>").addClass(
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
        $("<tr></tr>").append(checkBoxTD).append(userIdTd).append(userNameTd).append(MobileTd).append(EmailTd).append(btnTd).appendTo("#users_table tbody");
    });
}

$("#user_save_btn").click(function () {
    if (!validate_add_form()) {
        return false;
    }
    let json = getJson();
    $.ajax({
        url: "/nios/User/User",
        type: "POST",
        async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: json,
        success: function (result) {
            if (result.code == 100) {
                to_page(0);
            }
        }
    });

});

$("#user_update_btn").click(function () {

    $.ajax({
        url: "/nios/User/User",
        type: "POST",
        data: $("#UserUpdateModal form").serialize(),
        success: function (result) {
            if (result.code == 100) {
                $("#UserUpdateModal").modal('hide');
                to_page(0);
            }
        }
    });
});

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

    if ("success" == status) {
        $(ele).parent().addClass("has-success");
        $(ele).next("span").text(msg);
    } else if ("error" == status) {
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }
}

$(document).on("click", ".edit_btn", function () {
    getUser($(this).attr("edit-id"));

    $("#user_save_btn").attr("edit-id", $(this).attr("edit-id"));
});

function getUser(id) {
    $.ajax({
        url: "/nios/User/User/" + id,
        type: "GET",
        success: function (result) {
            let empData = result.content.user;
            $("#UserID").val(empData.id);
            $("#UserName").val(empData.name);
            $("#Email").val(empData.email);
            $("#Mobile").val(empData.mobile);
        }

    });
}

function getUserRole() {
    $.ajax({
        url: "/nios/UserRole/UserRole",
        type: "GET",
        success: function (result) {
            let userRoles = result.content.list;
            $.each(userRoles,function (i,item) {
                $("#userRoles").append("<option value="+item.id+">"+item.name+"</option>");
            })
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

    $("#userRoles option:selected").each(function () {
        let value = $(this).val();
        let obj = {};
        obj["id"] = value;
        params.push(obj);
    })

    object['userRoles'] = params;

    let json = JSON.stringify(object);
    return json
}