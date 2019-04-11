$(function () {
    getDoctor();
    getPatients();
});

$("#AppointmentTime").on("click",function(e){
    e.stopPropagation();
    $(this).lqdatetimepicker({
        css : 'datetime-day',
        dateType : 'D',
        selectback : function(){
        }
    });
});

function getDoctor() {
    $.ajax({
        url: "/nios/user/userByDoctor",
        type: "GET",
        success: function (result) {
            let doctors = result.content.list;
            $.each(doctors, function (i, item) {
                if (i == 0) {
                    let optionEle = $("<option></option>").append(item.name).attr("value", item.id);
                    optionEle.appendTo("#Doctors");
                } else {
                    let optionEle = $("<option></option>").append(item.name).attr("value", item.id);
                    optionEle.appendTo("#Doctors");
                }
            })
            $("#Doctors").val(doctors[0].id).select2();
        }
    });
}

function getDoctorList() {
    $.ajax({
        url: "/nios/user/userByDoctor",
        type: "GET",
        success: function (result) {
            let doctors = result.content.list;
            $.each(doctors, function (i, item) {
                if (i == 0) {
                    let optionEle = $("<option></option>").append(item.name).attr("value", item.id);
                    optionEle.appendTo("#Doctors");
                } else {
                    let optionEle = $("<option></option>").append(item.name).attr("value", item.id);
                    optionEle.appendTo("#Doctors");
                }
            })
            $("#Doctors").val(doctors[0].id).select2();
        }
    });
}

function getPatients() {
    $.ajax({
        url: "/nios/registration/registration",
        type: "GET",
        success: function (result) {
            let patients = result.content.pageInfo.list;
            $.each(patients, function (i, item) {
                if (i == 0) {
                    let optionEle = $("<option></option>").append(item.name).attr("value", item.id);
                    optionEle.appendTo("#Patients");
                } else {
                    let optionEle = $("<option></option>").append(item.name).attr("value", item.id);
                    optionEle.appendTo("#Patients");
                }

            })
            $("#Patients").val(patients[0].id).select2();
        }
    });
}

$("#appointment_save_btn").click(function () {

    let json = getJson();
    $.ajax({
        url: "/nios/appointment/appointment",
        type: "POST",
        async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: json,
        success: function (result) {
            alert("success");
        },

    });
});

$("#userSearch").click(function () {
    let office = $("#office").val();
    let doctor = $("#doctor").val();
    $.ajax({
        url: "/nios/appointment/appointment",
        data: "office=" + office + "&" + "doctor=" + doctor,
        type: "GET",
        success: function (result) {
            alert("success");
        },

    });
});

function getJson() {
    let object = {};

    object['appointmentTime'] = $("#AppointmentTime").val();
    object['description'] = $("#Description").val();

    $("#Doctors option:selected").each(function () {
        let value = $(this).val();
        let obj = {};
        obj["id"] = value;
        object['userDto'] = obj;
    })

    $("#Patients option:selected").each(function () {
        let value = $(this).val();
        let obj = {};
        obj["id"] = value;
        object['patientDto'] = obj;
    })

    let json = JSON.stringify(object);
    return json
}
