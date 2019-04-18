$(function () {
    to_page(0);
    getDrug();
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
        let descriptionTd = $("<td></td>").append(item.description);
        let diagnosisBtn = $("<button></button>").addClass("btn btn-primary  btn-sm edit_btn").append($("<span></span>").addClass(
            "glyphicon glyphicon-pencil")).append("diagnosis");
        diagnosisBtn.attr("edit-id", item.id);
        let btnTd = $("<td></td>").append(diagnosisBtn);
        $("<tr></tr>").append(checkBoxTD).append(patientTd).append(doctorTd).append(appointmentIdTd).append(appointmentTimeTd).append(descriptionTd).append(btnTd).appendTo("#appointment_table tbody");
    });
}

$("#diagnosis_save_btn").click(function () {
    let json = getJson();
    $.ajax({
        url: "/nios/diagnosis/diagnosis",
        type: "POST",
        async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: json,
        success: function (result) {
            if (result.code == 100) {
                to_page(0);
                reset_form("#Appointment_Form");
            }
        },
    });
});

function reset_form(ele) {
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help_block").text("");
}


$(document).on("click", ".edit_btn", function () {
    getDiagnosis($(this).attr("edit-id"));
    $("#diagnosis_save_btn").attr("edit-id", $(this).attr("edit-id"));
});

function getDiagnosis(id) {
    $.ajax({
        url: "/nios/appointment/appointment/" + id,
        type: "GET",
        success: function (result) {
            let data = result.content.appointment;
            $("#id").val(data.id);
            $("#PatientId").val(data.patientDto.id);
            $("#DoctorId").val(data.userDto.id);
            $("#PatientName").val(data.patientDto.name);
            $("#DoctorName").val(data.userDto.name);
        }
    });
}

function getDrug() {
    $.ajax({
        url: "/nios/drugProfile/getAllDrugProfile",
        type: "GET",
        success: function (result) {
            let drugs = result.content.list;
            $.each(drugs, function (i, item) {
                if (i == 0) {
                    let optionEle = $("<option></option>").append(item.name).attr("value", item.id);
                    optionEle.appendTo("#Drug");
                } else {
                    let optionEle = $("<option></option>").append(item.name).attr("value", item.id);
                    optionEle.appendTo("#Drug");
                }
            })
        }
    });
}

function getJson() {
    let object = {};
    let params = [];

    object['description'] = $("#Description").val();
    let userObj = {};
    userObj["id"] = $("#DoctorId").val();
    userObj["name"] = $("#DoctorName").val();
    object['userDto'] = userObj;

    let patientObj = {};
    patientObj["id"] = $("#PatientId").val();
    patientObj["name"] = $("#PatientName").val();
    object['patientDto'] = patientObj;

    let appointmentObj = {};
    appointmentObj["id"] = $("#id").val();
    object['appointmentDto'] = appointmentObj;

    $("#Drug option:selected").each(function () {
        let value = $(this).val();
        let obj = {};
        obj["id"] = value;
        params.push(obj);
    })

    object['drugProfileDtos'] = params;

    let json = JSON.stringify(object);
    return json
}
