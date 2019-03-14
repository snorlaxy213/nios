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
        let userIdTd = $("<td></td>").append(item.id);
        let userNameTd = $("<td></td>").append(item.name);
        let AgeTd = $("<td></td>").append(item.age);
        let GenderTd = $("<td></td>").append(item.gender);
        let MobileTd = $("<td></td>").append(item.mobile);
        let EmailTd = $("<td></td>").append(item.email);
        let delBth = $("<button></button>").addClass(
            "btn btn-danger  btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append("delete");
        delBth.attr("del-id", item.id);
        let btnTd = $("<td></td>").append(" ").append(delBth);
        $("<tr></tr>").append(userIdTd).append(userNameTd).append(AgeTd).append(GenderTd).append(MobileTd).append(EmailTd).append(btnTd).appendTo("#registration_table tbody");
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





