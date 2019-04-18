$("#dispensingSearch").click(function () {
    let diagnosisID = $("#diagnosisID").val();
    $.ajax({
        url: "/nios/dispensing/dispensing",
        data: "diagnosisID=" + diagnosisID,
        type: "GET",
        success: function (result) {
            build_diagnosis_area(result);
            build_basicInfo_area(result);
        },
    });
});

function build_diagnosis_area(result) {
    $("#diagnosis_area").empty();
    $("#subTitle").removeAttr("hidden");
    let dispensingDrugDtos = result.content.dispensing.dispensingDrugDtos;
    let div = $("<div></div>").addClass("list-group");

    $.each(dispensingDrugDtos, function (index, item) {
        let a = $("<a></a>").attr("href", "#").addClass("list-group-item edit_btn");
        let content = $("<span></span>").addClass("badge").append("数量： "+item.amount);
        let content2 = $("<span></span>").append(item.drugName);
        a.attr("edit-id", item.id);
        content.appendTo(a);
        content2.appendTo(a);
        a.appendTo(div);
    });

    div.appendTo("#diagnosis_area");
}

function build_basicInfo_area(result) {
    $("#basic_info_area").empty();

    let dispensing = result.content.dispensing;
    let div1 = $("<div></div>").addClass("panel panel-default");
    let h3 = $("<h3></h3>").addClass("panel-title").append("基本信息");
    let div2 = $("<div></div>").addClass("panel-heading").append(h3);
    let userName = $("<p></p>").append("求诊人姓名: "+dispensing.userName);
    let patientName = $("<p></p>").append("医师姓名: "+dispensing.patientName);
    let description = $("<p></p>").append("医嘱: "+dispensing.description);
    let div3 = $("<div></div>").addClass("panel-body").append(userName).append(patientName).append(description);
    div2.appendTo(div1);
    div3.appendTo(div1);

    div1.appendTo("#basic_info_area");
}