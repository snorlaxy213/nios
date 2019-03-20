function reset_form(ele) {
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help_block").text("");
}

function validate_add_form() {
    //password validation
    let password = $("#Password").val();
    //6-16 digit number or letter
    let regUserId = /(^[a-zA-Z0-9_-]{6,16}$)/;
    if (!regUserId.test(password)) {
        show_validate_msg("#Password", "error", "密码必须是6-16位数字或字母");
        return false;
    } else {
        show_validate_msg("#Password", "success", "");
    }


    let passwordConfirm = $("#PasswordConfirm").val();
    if (passwordConfirm != password) {
        show_validate_msg("#PasswordConfirm", "error", "两次密码不相同");
        return false
    } else {
        show_validate_msg("#PasswordConfirm", "success", "");
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

$("#password_save_btn").click(function () {
    if (!validate_add_form()) {
        return false;
    }
    let json = getJson();
    $.ajax({
        url: "/nios/user/changePassword",
        type: "POST",
        async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: json,
        success: function (result) {
            if (result.code == 100) {
                alert("修改成功");
            } else {
                alert("修改失败");
            }
        }
    });
});
$("#sendMessage").click(function () {
    $.ajax({
        url: "/nios/user/getVerificationCode",
        type: "POST",
        success: function (result) {
            if (result.code == 100) {
                alert("发送成功");
            } else {
                alert("发送失败");
            }
        }
    });
});

function getJson() {
    let object = {};

    object['msgCode'] = $("#Verification").val();
    object['password'] = $("#Password").val();

    let json = JSON.stringify(object);
    return json
}

