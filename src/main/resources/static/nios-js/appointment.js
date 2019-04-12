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

function build_user_list(result) {
    //page_nav_area
    $("#user_list_area").empty();
    $("#subTitle").removeAttr("hidden");
    let userInfo = result.content.list;
    let div = $("<div></div>").addClass("list-group");

    $.each(userInfo, function (index, item) {
        let a = $("<a></a>").attr("href", "#").addClass("list-group-item");
        let content = $("<span></span>").append(item.name);
        content.appendTo(a);
        a.appendTo(div);
    });

    div.appendTo("#user_list_area");
}

$("#userSearch").click(function () {
    let office = $("#office").val();
    let doctor = $("#doctor").val();
    $.ajax({
        url: "/nios/user/userByDoctorAndOffice",
        data: "office=" + office + "&" + "doctor=" + doctor,
        type: "GET",
        success: function (result) {
            // alert(result.content.list);
            build_user_list(result);
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
