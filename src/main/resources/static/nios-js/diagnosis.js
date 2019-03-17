$(function () {
    to_page(0);
});

function to_page(pn) {
    $.ajax({
        url: "/nios/appointment/appointment",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            build_users_table(result);
        }
    });
}

function build_users_table(result) {
    $("#appointment_table tbody").empty();
    let userInfo = result.content.list;
    $.each(userInfo, function (index, item) {
        let checkBoxTD = $("<td><input type='checkbox' class='check_item'/></td>");
        let patientTd = $("<td></td>").append(item.patientDto.name);
        let doctorTd = $("<td></td>").append(item.userDto.name);
        let appointmentIdTd = $("<td></td>").append(item.id);
        let appointmentTimeTd = $("<td></td>").append(item.appointmentTime);
        let durationTd = $("<td></td>").append(item.duration);
        let descriptionTd = $("<td></td>").append(item.description);
        let diagnosisBtn = $("<button></button>").addClass("btn btn-primary  btn-sm edit_btn").append($("<span></span>").addClass(
            "glyphicon glyphicon-pencil")).append("diagnosis");
        diagnosisBtn.attr("edit-id", item.id);
        let btnTd = $("<td></td>").append(diagnosisBtn);
        $("<tr></tr>").append(checkBoxTD).append(patientTd).append(doctorTd).append(appointmentIdTd).append(appointmentTimeTd).append(durationTd).append(descriptionTd).append(btnTd).appendTo("#appointment_table tbody");
    });
}