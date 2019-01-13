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
    //清空表格数据
    $("#userRoles_table tbody").empty();
    var userRoleInfo = result.extend.list;
    //jquery遍历方法each，emps第一个遍历的元素,item当前对象
    //此处要对照JSON数据，获取到员工的list
    //item.*读取bean中数据
    $.each(userRoleInfo, function (index, item) {
        var checkBoxTD = $("<td><input type='checkbox' class='check_item'/></td>");
        var userRoleIdTd = $("<td></td>").append(item.id);
        var userRoleNameTd = $("<td></td>").append(item.name);
        var Description = $("<td></td>").append(item.description);
        var Status = $("<td></td>").append(item.status);
        var editBtn = $("<button></button>").addClass("btn btn-primary  btn-sm edit_btn").append($("<span></span>").addClass(
            "glyphicon glyphicon-pencil")).append("update");
        //为编辑按钮添加一个自定义的属性，来表示当前员工。赋值给edit-id
        editBtn.attr("edit-id", item.id);
        var delBth = $("<button></button>").addClass(
            "btn btn-danger  btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append(
            "dalete");
        //为删除按钮添加一个自定义的属性，来表示当前员工。赋值给edit-id
        delBth.attr("del-id", item.id);
        var btnTd = $("<td></td>").append(editBtn).append(" ").append(
            delBth);
        //append方法执行完以后还是返回原来的元素，所以可以不停使用append方法添加内容
        $("<tr></tr>").append(checkBoxTD).append(userRoleIdTd).append(userRoleNameTd).append(Description).append(Status).append(btnTd).appendTo("#userRoles_table tbody");
    });
}