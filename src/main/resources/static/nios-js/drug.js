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