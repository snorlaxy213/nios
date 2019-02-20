$(function () {
    to_page(0);
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
    //清空表格数据
    $("#users_table tbody").empty();
    var userInfo = result.extend.list;
    //jquery遍历方法each，userInfo第一个遍历的元素,item当前对象
    //此处要对照JSON数据，获取到员工的list
    //item.*读取bean中数据
    $.each(userInfo, function (index, item) {
        var checkBoxTD = $("<td><input type='checkbox' class='check_item'/></td>");
        var userIdTd = $("<td></td>").append(item.id);
        var userNameTd = $("<td></td>").append(item.name);
        var MobileTd = $("<td></td>").append(item.mobile);
        var EmailTd = $("<td></td>").append(item.email);
        var effecticeDateTd = $("<td></td>").append(item.effectiveStr);
        var expiryDateTd = $("<td></td>").append(item.expiryStr);
        var editBtn = $("<button></button>").addClass("btn btn-primary  btn-sm edit_btn").append($("<span></span>").addClass(
            "glyphicon glyphicon-pencil")).append("update");
        //为编辑按钮添加一个自定义的属性，来表示当前员工。赋值给edit-id
        editBtn.attr("edit-id", item.id);
        var delBth = $("<button></button>").addClass(
            "btn btn-danger  btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append(
            "dalete");
        //为删除按钮添加一个自定义的属性，来表示当前员工。赋值给edit-id
        delBth.attr("del-id", item.id);
        var btnTd = $("<td></td>").append(editBtn).append(" ").append(
            delBth);
        //append方法执行完以后还是返回原来的元素，所以可以不停使用append方法添加内容
        $("<tr></tr>").append(checkBoxTD).append(userIdTd).append(userNameTd).append(MobileTd).append(EmailTd).append(effecticeDateTd).append(expiryDateTd).append(btnTd).appendTo("#users_table tbody");
    });
}

$("#user_save_btn").click(function () {
    if (!validate_add_form()) {
        return false;
    }
    var json = getJson();
    $.ajax({
        url: "/nios/User/User",
        type: "POST",
        async: false,
        dataType : "json",
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
    var userId = $("#UserID").val();
    console.log("userId", userId)
    //6-16 digit number or letter
    var regUserId = /(^[a-zA-Z0-9_-]{6,16}$)/;
    if (!regUserId.test(userId)) {
        show_validate_msg("#UserID", "error", "The user name can be a combination of 6-16 digits in English and a number");
        return false;
    } else {
        show_validate_msg("#UserID", "success", "");
    }

    //email validation
    var email = $("#Email").val();
    var regemail = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (!regemail.test(email)) {
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

$(document).on("click",".edit_btn",function(){

    getUser($(this).attr("edit-id"));

    $("#user_save_btn").attr("edit-id",$(this).attr("edit-id"));
});

function getUser(id){
    $.ajax({
        url:"/nios/User/User/"+id,
        type:"GET",
        success:function(result){
            var empData=result.extend.user;
            $("#UserID").val(empData.id);
            $("#UserName").val(empData.name);
            $("#Email").val(empData.email);
            $("#Mobile").val(empData.mobile);
            $("#Effect_Date").val(empData.effectiveStr);
            $("#Expiry_Date").val(empData.expiryStr);
        }

    });
}

function getUserRole() {
    $.ajax({
        url:"/nios/UserRole/UserRole/"+id,
        type:"GET",
        success:function(result){
            var empData=result.extend.user;
            $("#UserID").val(empData.id);
            $("#UserName").val(empData.name);
            $("#Email").val(empData.email);
            $("#Mobile").val(empData.mobile);
            $("#Effect_Date").val(empData.effectiveStr);
            $("#Expiry_Date").val(empData.expiryStr);
        }
    });
}

function getJson() {
    var object = {};
    var params = [];

    object['id'] = $("#UserID").val();
    object['name'] = $("#UserName").val();
    object['email'] = $("#Email").val();
    object['mobile'] = $("#Mobile").val();
    object['effectiveStr'] = $("#Effect_Date").val();
    object['expiryStr'] = $("#Expiry_Date").val();


    $("#userRoles option:selected").each(function () {
        var value = $(this).val();
        var obj = {};
        obj["id"] = value;
        params.push(obj);
    })

    object['userroles'] = params;

    var json = JSON.stringify(object);
    return json
}