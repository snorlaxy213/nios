$(function () {
    to_page(0);
});

function to_page(pn) {
    $.ajax({
        url: "/nios/appointment/appointment",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            build_appointment_table(result);
        }
    });
}

function build_appointment_table(result) {
    $("#appointment_table tbody").empty();
    let info = result.content.list;
    $.each(info, function (index, item) {
        let checkBoxTD = $("<td><input type='checkbox' class='check_item'/></td>");
        let patientTd = $("<td></td>").append(item.patientDto.name);
        let doctorTd = $("<td></td>").append(item.userDto.name);
        let appointmentIdTd = $("<td></td>").append(item.id);
        let appointmentTimeTd = $("<td></td>").append(item.appointmentTime);
        let durationTd = $("<td></td>").append(item.duration);
        let descriptionTd = $("<td></td>").append(item.description);
        let diagnosisBtn = $("<button></button>").addClass("btn btn-primary  btn-sm edit_btn").append($("<span></span>").addClass(
            "glyphicon glyphicon-pencil")).append("diagnosis");
        diagnosisBtn.attr("edit-id", item.patientDto.id);
        let btnTd = $("<td></td>").append(diagnosisBtn);
        $("<tr></tr>").append(checkBoxTD).append(patientTd).append(doctorTd).append(appointmentIdTd).append(appointmentTimeTd).append(durationTd).append(descriptionTd).append(btnTd).appendTo("#appointment_table tbody");
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
            } else if (result.code == 300) {
                let errorMessages = result.errorMessages;
                let str = "";
                for (let i = 0; i < errorMessages.length; i++) {
                    if (i != errorMessages.length) {
                        str = str + errorMessages[i] + "\n";
                    } else {
                        str = str + errorMessages[i];
                    }
                }
                alert(str);
            }
        },

    });
});