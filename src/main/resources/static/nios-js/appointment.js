$(function () {
    getDoctor();
    getPatients();
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
    if (!nullCheck()) {
        return false;
    }
    let json = getJson();
    $.ajax({
        url: "/nios/appointment/appointment",
        type: "POST",
        async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: json,
        success: function (result) {
            alert("添加成功");
            $("#saveModal").modal("hide");
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
        let a = $("<a></a>").attr("href", "#").addClass("list-group-item edit_btn");
        let content = $("<span></span>").addClass("badge").append(item.id);
        let content2 = $("<span></span>").append(item.name);
        a.attr("edit-id", item.id);
        content.appendTo(a);
        content2.appendTo(a);
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

$(document).on("click", ".edit_btn", function () {
    $("#doctorInput").val($(this).attr("edit-id"));
    $("#saveModal").modal({
        backdrop : "static"
    });
});

$("#patient").change(function(){
    //发送ajax请求检验用户名是否可用
    let id = this.value;
    $.ajax({
        url: "/nios/registration/registration/" + id,
        type: "GET",
        success: function (result) {
            if (result.code == 100) {
                show_validate_msg("#patient", "success", "");
            } else {
                show_validate_msg("#patient", "error", result.content.msg);
            }
        }
    });
});

function show_validate_msg(ele, status, msg) {
    $(ele).parent().removeClass("has-success has-error");
    $(ele).next("span").text("");

    if ("error" == status) {
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }
}

function getJson() {
    let object = {};

    let objUser = {};
    objUser["id"] = $("#doctorInput").val();
    object['userDto'] = objUser;

    let objPatient = {};
    objPatient["id"] = $("#patient").val();
    object['patientDto'] = objPatient;

    object['appointmentTime'] = $("#AppointmentTime").val();
    object['description'] = $("#Description").val();

    let json = JSON.stringify(object);
    return json
}

function nullCheck() {
    if ($("#doctorInput").val() == "" || $.trim($("#doctorInput").val()).length == 0) {
        show_validate_msg("#doctorInput", "error", "必填选项");
        return false;
    }

    if ($("#patient").val() == "" || $.trim($("#patient").val()).length == 0) {
        show_validate_msg("#patient", "error", "必填选项");
        return false;
    }

    if ($("#AppointmentTime").val() == "" || $.trim($("#AppointmentTime").val()).length == 0) {
        show_validate_msg("#AppointmentTime", "error", "必填选项");
        return false;
    }

    if ($("#Description").val() == "" || $.trim($("#Description").val()).length == 0) {
        show_validate_msg("#Description", "error", "必填选项");
        return false;
    }
    return true;
}

$("#doctorInput").change(function(){
    if ($("#doctorInput").val() != "") {
        show_validate_msg("#doctorInput", "success", "");
    }
});

$("#AppointmentTime").change(function(){
    if ($("#AppointmentTime").val() != "") {
        show_validate_msg("#AppointmentTime", "success", "");
    }
});

$("#Description").change(function(){
    if ($("#Description").val() != "") {
        show_validate_msg("#Description", "success", "");
    }
});