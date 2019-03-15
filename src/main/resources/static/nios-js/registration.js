$(function () {
    let token = $.cookie('token');
    to_page(0);
});

function to_page(pn) {
    $.ajax({
        url: "/nios/registration/registration",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            build_registration_table(result);
        }
    });
}

function build_registration_table(result) {
    $("#registration_table tbody").empty();
    let appointmentInfo = result.content.list;
    $.each(appointmentInfo, function (index, item) {
        let gender;
        if (item.gender == "M") {
            gender = "male";
        } else {
            gender = "female";
        }
        let status;
        if (item.status == "Y") {
            status = "usable";
        } else {
            status = "unusable";
        }
        let userIdTd = $("<td></td>").append(item.id);
        let userNameTd = $("<td></td>").append(item.name);
        let AgeTd = $("<td></td>").append(item.age);
        let GenderTd = $("<td></td>").append(gender);
        let MobileTd = $("<td></td>").append(item.mobile);
        let EmailTd = $("<td></td>").append(item.email);
        let StatusTd = $("<td></td>").append(status);
        let delBth = $("<button></button>").addClass(
            "btn btn-danger  btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append("delete");
        delBth.attr("del-id", item.id);
        let btnTd = $("<td></td>").append(" ").append(delBth);
        $("<tr></tr>").append(userIdTd).append(userNameTd).append(AgeTd).append(GenderTd).append(MobileTd).append(EmailTd).append(StatusTd).append(btnTd).appendTo("#registration_table tbody");
    });
}

$("#patient_save_btn").click(function () {
    let json = getJson();
    $.ajax({
        url: "/nios/registration/registration",
        type: "POST",
        async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: json,
        success: function (result) {
            if (result.code == 100) {
                to_page(0);
                reset_form("#Registration_Form");
            }
        },
    });
});

$(document).on("click", ".delete_btn", function () {
    let PatientId = $(this).attr("del-id");
    if (confirm("确认停用【" + PatientId + "】吗？")) {
        $.ajax({
            url: "/nios/registration/registration/"+PatientId,
            type: "DELETE",
            success: function (result) {
                if (result.code == 100) {
                    to_page(0);
                }
            }
        });
    }
});

function getJson() {
    let object = {};

    object['name'] = $("#Name").val();
    object['age'] = $("#Age").val();
    object['gender'] = $("#Gender").val();
    object['mobile'] = $("#Mobile").val();
    object['email'] = $("#Email").val();

    let json = JSON.stringify(object);
    return json
}





