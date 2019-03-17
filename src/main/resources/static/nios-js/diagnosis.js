$(function () {
    to_page(0);
});

function to_page(pn) {
    $.ajax({
        url: "/nios/user/user",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            build_users_table(result);
        }
    });
}

function build_users_table(result) {
    $("#users_table tbody").empty();
    let userInfo = result.content.list;
    $.each(userInfo, function (index, item) {
        let checkBoxTD = $("<td><input type='checkbox' class='check_item'/></td>");
        let userIdTd = $("<td></td>").append(item.id);
        let userNameTd = $("<td></td>").append(item.name);
        let MobileTd = $("<td></td>").append(item.mobile);
        let EmailTd = $("<td></td>").append(item.email);
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
        $("<tr></tr>").append(checkBoxTD).append(userIdTd).append(userNameTd).append(MobileTd).append(EmailTd).append(btnTd).appendTo("#users_table tbody");
    });
}