$(function () {
    getAppointmentCount();
    setInterval("getAppointmentCount()",60000);
});

function getAppointmentCount() {
    $.ajax({
        url: "/nios/appointment/getCount",
        type: "GET",
        success: function (result) {
            $("#count").html(result.content.count);
        }
    });
}