$(function () {
    to_page(0);
});

function to_page(pn) {
    $.ajax({
        url: "/nios/drugProfile/drugProfile",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            build_drugs_table(result);
        }
    });
}

function build_drugs_table(result) {
    $("#drugs_table tbody").empty();
    let userInfo = result.content.list;
    $.each(userInfo, function (index, item) {
        let checkBoxTD = $("<td><input type='checkbox' class='check_item'/></td>");
        let drugIdTd = $("<td></td>").append(item.id);
        let drugNameTd = $("<td></td>").append(item.name);
        let drugTypeTd = $("<td></td>").append(item.type);
        let DescriptionTd = $("<td></td>").append(item.description);
        let UnitTd = $("<td></td>").append(item.unit);
        let StatusTd = $("<td></td>").append(item.status);
        let editBtn = $("<button></button>").addClass("btn btn-primary  btn-sm edit_btn").append($("<span></span>").addClass(
            "glyphicon glyphicon-pencil")).append("update");
        editBtn.attr("edit-id", item.id);
        let delBth = $("<button></button>").addClass(
            "btn btn-danger  btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append(
            "delete");
        delBth.attr("del-id", item.id);
        let btnTd = $("<td></td>").append(editBtn).append(" ").append(
            delBth);
        $("<tr></tr>").append(checkBoxTD).append(drugIdTd).append(drugNameTd).append(drugTypeTd).append(DescriptionTd).append(UnitTd).append(StatusTd).append(btnTd).appendTo("#drugs_table tbody");
    });
}

$("#drug_save_btn").click(function () {
    let json = getJson();
    $.ajax({
        url: "/nios/drugProfile/drugProfile",
        type: "POST",
        async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: json,
        success: function (result) {
            if (result.code == 100) {
                to_page(0);
                reset_form("#DrugProfile_Form");
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
    getUser($(this).attr("edit-id"));
    $("#drug_save_btn").attr("edit-id", $(this).attr("edit-id"));
});

function getUser(id) {
    $.ajax({
        url: "/nios/drugProfile/drugProfile/" + id,
        type: "GET",
        success: function (result) {
            let data = result.content.drug;
            $("#DrugId").val(data.id);
            $("#DrugName").val(data.name);
            if (data.type == 'Sheet') {
                $("#DrugType_1").attr('checked', 'checked');
            } else if (data.type == "Granule") {
                $("#DrugType_2").attr('checked', 'checked');
            }
            $("#Description").val(data.description);
            $("#Unit").val(data.unit);
            if (data.status == 'Y') {
                $("#Status_Y").attr('checked', 'checked');
            } else if (data.status == "N") {
                $("#Status_N").attr('checked', 'checked');
            }
        }
    });
}

$(document).on("click", ".delete_btn", function () {
    let UserId = $(this).attr("del-id");
    $.ajax({
        url: "/nios/drugProfile/drugProfile/"+UserId,
        type: "DELETE",
        success: function (result) {
            if (result.code == 100) {
                to_page(0);
            }
        }
    });
});

$("#check_all").click(function(){
    $(".check_item").prop("checked",$(this).prop("checked"));
});

$(document).on("click",".check_item",function(){
    let flag=$(".check_item:checked").length==$(".check_item").length
    $("#check_all").prop("checked",flag);
});

$("#delete_all_btn").click(function(){
    let drugNames="";
    let delId_str="";
    $.each($(".check_item:checked"),function(){
        drugNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
        delId_str+=$(this).parents("tr").find("td:eq(1)").text()+"-";
    });
    drugNames=drugNames.substring(0,drugNames.length-1);
    delId_str=delId_str.substring(0,delId_str.length-1);
    if(confirm("确认删除【"+drugNames+"】吗？")){
        $.ajax({
            url:"/nios/drugProfile/drugProfile/"+delId_str,
            type:"DELETE",
            success:function(result)
            {
                to_page(0);
            }
        });
    }
});

function getJson() {
    let object = {};

    object['id'] = $("#DrugId").val();
    object['name'] = $("#DrugName").val();
    object['type'] = $('input[name="DrugType"]:checked').val();
    object['description'] = $("#Description").val();

    $("#Unit option:selected").each(function () {
        let value = $(this).val();
        object["unit"] = value;
    })

    object['status'] = $('input[name="Status"]:checked').val();

    let json = JSON.stringify(object);
    return json
}