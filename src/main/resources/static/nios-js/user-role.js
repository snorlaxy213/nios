$(function () {
    to_page(0);
});

function to_page(pn) {
    $.ajax({
        url: "/nios/userRole/userRole",
        data: "pageNumber=" + pn,
        type: "GET",
        success: function (result) {
            build_userRoles_table(result);
            build_page_nav(result);
        }
    });
}

function build_userRoles_table(result) {
    $("#userRoles_table tbody").empty();
    let userRoleInfo = result.content.pageInfo.list;
    $.each(userRoleInfo, function (index, item) {
        let checkBoxTD = $("<td><input type='checkbox' class='check_item'/></td>");
        let userRoleIdTd = $("<td></td>").append(item.id);
        let userRoleNameTd = $("<td></td>").append(item.name);
        let Status
        if (item.status == 'Effective') {
            Status = $("<td></td>").append("生效的");
        }else if (item.status == 'unEffective') {
            Status = $("<td></td>").append("非生效的");
        } else {
            Status = $("<td></td>").append("未知情况");
        }
        let editBtn = $("<button></button>").addClass("btn btn-info  btn-sm edit_btn").append($("<span></span>").addClass(
            "glyphicon glyphicon-pencil")).append("编辑");
        editBtn.attr("edit-id", item.id);
        let delBth = $("<button></button>").addClass(
            "btn btn-danger  btn-sm delete_btn").append(
            $("<span></span>")
                .addClass("glyphicon glyphicon-trash")).append(
            "删除");
        delBth.attr("del-id", item.id);
        let btnTd = $("<td></td>").append(editBtn).append(" ").append(
            delBth);
        $("<tr></tr>").append(checkBoxTD).append(userRoleIdTd).append(userRoleNameTd).append(Status).append(btnTd).appendTo("#userRoles_table tbody");
    });
}

function build_page_nav(result) {
    //page_nav_area
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");

    //构建元素
    var firstPageli = $("<li></li>").append(
        $("<a></a>").append("首页").attr("href", "#"));
    var prePageli = $("<li></li>").append(
        $("<a></a>").append("&laquo;"));
    if (result.content.pageInfo.hasPreviousPage == false) {
        firstPageli.addClass("disabled");
        prePageli.addClass("disabled");
    } else {
        //为元素添加点击事件
        firstPageli.click(function() {

            to_page(1);

        });
        prePageli.click(function() {
            //当前页面减一
            to_page(result.content.pageInfo.pageNum - 1);

        });

    }

    var nextPageli = $("<li></li>").append(
        $("<a></a>").append("&raquo;"));
    var lastPageli = $("<li></li>").append(
        $("<a></a>").append("末页").attr("href", "#"));
    if (result.content.pageInfo.hasNextPage == false) {
        nextPageli.addClass("disabled");
        lastPageli.addClass("disabled");
    } else {
        nextPageli.click(function() {
            //当前页面减一
            to_page(result.content.pageInfo.pageNum + 1);

        });
        lastPageli.click(function() {
            //当前页面减一
            to_page(result.content.pageInfo.pages);

        });

    }

    //添加首页和前一页的提示
    ul.append(firstPageli).append(prePageli);

    //遍历页码号1,2,3,4,5
    $.each(result.content.pageInfo.navigatepageNums, function(index,
                                                              item) {
        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.content.pageInfo.pageNum == item) {
            //让当前页面高亮
            numLi.addClass("active");
        }
        numLi.click(function() {

            to_page(item);
        });
        ul.append(numLi);
    });

    //添加下一页和末页的提示
    ul.append(nextPageli).append(lastPageli);
    //把url加入
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page_nav_area");
}

$(document).on("click", ".edit_btn", function () {
    getUserRole($(this).attr("edit-id"));
    $("#user_save_btn").attr("edit-id", $(this).attr("edit-id"));
});

function getUserRole(id) {
    $.ajax({
        url: "/nios/userRole/userRole/" + id,
        type: "GET",
        success: function (result) {
            let data = result.content.userRole;
            $("#UserRoleID").val(data.id);
            $("#UserRoleName").val(data.name);
            if (data.status == "Y") {
                $("input[name='Status'][value='Y']").attr("checked", true);
            } else {
                $("input[name='Status'][value='N']").attr("checked", true);
            }
        }
    });
}

$("#userRole_save_btn").click(function () {
    let json = getJson();
    $.ajax({
        url: "/nios/userRole/userRole",
        type: "POST",
        async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: json,
        success: function (result) {
            if (result.code == 100) {
                to_page(0);
                reset_form("#UserRole_Form");
                alert("保存成功");
            } else if (result.code == 300) {
                let errorMessages = result.errorMessages;
                let str = "";
                for (let i = 0; i < errorMessages.length; i++) {
                    if (i != errorMessages.length-1) {
                        str = str + errorMessages[i] + ",";
                    } else {
                        str = str + errorMessages[i];
                    }
                }
                alert(str+"不可以为空");
            }
        },
    });
});

function reset_form(ele) {
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help_block").text("");
    $(ele).find("*").attr("checked", false);
}

$("#check_all").click(function(){
    $(".check_item").prop("checked",$(this).prop("checked"));
});

$(document).on("click",".check_item",function(){
    let flag=$(".check_item:checked").length==$(".check_item").length
    $("#check_all").prop("checked",flag);
});

$("#delete_all_btn").click(function(){
    let userRoleNames="";
    let delId_str="";
    $.each($(".check_item:checked"),function(){
        userRoleNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
        delId_str+=$(this).parents("tr").find("td:eq(1)").text()+"-";
    });
    userRoleNames=userRoleNames.substring(0,userRoleNames.length-1);
    delId_str=delId_str.substring(0,delId_str.length-1);
    if(confirm("确认删除【"+userRoleNames+"】吗？")){
        $.ajax({
            url:"/nios/userRole/userRole/"+delId_str,
            type:"DELETE",
            success:function(result)
            {
                to_page(0);
            }
        });
    }
});

$(document).on("click", ".delete_btn", function () {
    let UserRoleId = $(this).attr("del-id");
    $.ajax({
        url: "/nios/userRole/userRole/"+UserRoleId,
        type: "DELETE",
        success: function (result) {
            if (result.code == 100) {
                to_page(0);
            }
        }
    });
});

function getJson() {
    let object = {};

    object['id'] = $("#UserRoleID").val();
    object['name'] = $("#UserRoleName").val();
    object['status'] = $("input[name='Status']:checked").val();

    let json = JSON.stringify(object);
    return json
}