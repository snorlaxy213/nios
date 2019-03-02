$(function () {
    to_page(0);
});

function to_page(pn) {
    $.ajax({
        url: "/nios/UserRole/UserRole",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            build_users_table(result);

            //build_page_info(result);

            //build_page_nav(result);
        }
    });
}

function build_users_table(result) {
    $("#userRoles_table tbody").empty();
    let userRoleInfo = result.content.list;
    $.each(userRoleInfo, function (index, item) {
        let checkBoxTD = $("<td><input type='checkbox' class='check_item'/></td>");
        let userRoleIdTd = $("<td></td>").append(item.id);
        let userRoleNameTd = $("<td></td>").append(item.name);
        let Description = $("<td></td>").append(item.description);
        let Status = $("<td></td>").append(item.status);
        let editBtn = $("<button></button>").addClass("btn btn-primary  btn-sm edit_btn").append($("<span></span>").addClass(
            "glyphicon glyphicon-pencil")).append("update");
        editBtn.attr("edit-id", item.id);
        let delBth = $("<button></button>").addClass(
            "btn btn-danger  btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append(
            "dalete");
        delBth.attr("del-id", item.id);
        let btnTd = $("<td></td>").append(editBtn).append(" ").append(
            delBth);
        $("<tr></tr>").append(checkBoxTD).append(userRoleIdTd).append(userRoleNameTd).append(Description).append(Status).append(btnTd).appendTo("#userRoles_table tbody");
    });
}