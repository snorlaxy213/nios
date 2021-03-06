$(function () {
    to_page(0);
    getUserRole();
});

function to_page(pn) {
    $.ajax({
        url: "/nios/user/user",
        data: "pageNumber=" + pn,
        type: "GET",
        success: function (result) {
            build_users_table(result);
            build_page_nav(result);
        }
    });
}

function build_users_table(result) {
    $("#users_table tbody").empty();
    let userInfo = result.content.pageInfo.list;
    $.each(userInfo, function (index, item) {
        let checkBoxTD = $("<td><input type='checkbox' class='check_item'/></td>");
        let userIdTd = $("<td></td>").append(item.id);
        let userNameTd = $("<td></td>").append(item.name);
        let MobileTd = $("<td></td>").append(item.mobile);
        let EmailTd = $("<td></td>").append(item.email);
        let OfficeTd = $("<td></td>").append(item.office);
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
        $("<tr></tr>").append(checkBoxTD).append(userIdTd).append(userNameTd).append(MobileTd).append(EmailTd).append(OfficeTd).append(btnTd).appendTo("#users_table tbody");
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

$("#user_save_btn").click(function () {
    if (!validate_add_form()) {
        return false;
    }
    let json = getJson();
    $.ajax({
        url: "/nios/user/user",
        type: "POST",
        async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: json,
        success: function (result) {
            if (result.code == 100) {
                to_page(0);
                reset_form("#User_Form");
                alert("保存成功")
            } else if (result.code == 300) {
                let errorMessages = result.errorMessages;
                let str = "";
                for (let i = 0; i < errorMessages.length; i++) {
                    if (i != errorMessages.length-1) {
                        str = str + errorMessages[i] + "\n";
                    } else {
                        str = str + errorMessages[i];
                    }
                }
                alert(str);
            }
        }
    });
});

function reset_form(ele) {
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help_block").text("");
}

function validate_add_form() {
    /*//userId validation
    let userId = $("#UserName").val();
    //6-16 digit number or letter
    let regUserId = /(^[a-zA-Z0-9_-]{6,16}$)/;
    if (!regUserId.test(userId)) {
        show_validate_msg("#UserName", "error", "用户名可以是6-16位英文和数字的组合");
        return false;
    } else {
        show_validate_msg("#UserName", "success", "123");
    }*/

    //phone number validation
    let mobile = $("#Mobile").val();
    let regexMobile = /^1[345678]\d{9}$/;
    if (!regexMobile.test(mobile)) {
        show_validate_msg("#Mobile", "error", "手机号码格式错误");
        return false
    } else {
        show_validate_msg("#Mobile", "success", "");
    }

    //email validation
    let email = $("#Email").val();
    let regexMail = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (!regexMail.test(email)) {
        show_validate_msg("#Email", "error", "邮箱格式错误");
        return false
    } else {
        show_validate_msg("#Email", "success", "");
    }

    return true;
}

function show_validate_msg(ele, status, msg) {
    $(ele).parent().removeClass("has-success has-error");
    $(ele).next("span").text("");

    if ("error" == status) {
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }
}

$(document).on("click", ".edit_btn", function () {
    getUser($(this).attr("edit-id"));
    $("#user_save_btn").attr("edit-id", $(this).attr("edit-id"));
});

$(document).on("click", ".delete_btn", function () {
    let UserId = $(this).attr("del-id");
    if(confirm("确认删除【"+UserId+"】吗？")) {
        $.ajax({
            url: "/nios/user/user/" + UserId,
            type: "DELETE",
            success: function (result) {
                if (result.code == 100) {
                    to_page(0);
                    alert("删除成功")
                }
            }
        });
    }
});

$("#check_all").click(function(){
    $(".check_item").prop("checked",$(this).prop("checked"));
});

$(document).on("click",".check_item",function(){
    let flag=$(".check_item:checked").length==$(".check_item").length
    $("#check_all").prop("checked",flag);
});

$("#delete_all_btn").click(function(){
    let userNames="";
    let delId_str="";
    $.each($(".check_item:checked"),function(){
        userNames+=$(this).parents("tr").find("td:eq(2)").text()+",";
        delId_str+=$(this).parents("tr").find("td:eq(1)").text()+"-";
    });
    userNames=userNames.substring(0,userNames.length-1);
    delId_str=delId_str.substring(0,delId_str.length-1);
    if(confirm("确认删除【"+userNames+"】吗？")){
        $.ajax({
            url:"/nios/user/user/"+delId_str,
            type:"DELETE",
            success:function(result)
            {
                to_page(0);
                alert("删除成功")
            }
        });
    }
});

function getUser(id) {
    $.ajax({
        url: "/nios/user/user/" + id,
        type: "GET",
        success: function (result) {
            let data = result.content.user;
            let userRoles = data.userRoleDtos;
            $("#UserID").val(data.id);
            $("#UserName").val(data.name);
            $("#Email").val(data.email);
            $("#Mobile").val(data.mobile);
            $("#Office").val(data.office).select2();
            $("#UserRoles").val(userRoles[0].id).select2();
            // $("#UserRoles").val(userRoles[0].id);
            $('#UserID').attr('readonly',true);
        }
    });
}

function getUserRole() {
    $.ajax({
        url: "/nios/userRole/availableUserRole",
        type: "GET",
        success: function (result) {
            let userRoles = result.content.list;
            $.each(userRoles, function (i, item) {
                if (i == 0) {
                    let optionEle = $("<option></option>").append(item.name).attr("value", item.id);
                    optionEle.appendTo("#UserRoles");
                } else {
                    let optionEle = $("<option></option>").append(item.name).attr("value", item.id);
                    optionEle.appendTo("#UserRoles");
                }
            })
            $("#UserRoles").val(userRoles[0].id).select2();
        }
    });
}

function getJson() {
    let object = {};
    let params = [];

    object['id'] = $("#UserID").val();
    object['name'] = $("#UserName").val();
    object['email'] = $("#Email").val();
    object['mobile'] = $("#Mobile").val();
    object['office'] = $("#Office").val();

    $("#UserRoles option:selected").each(function () {
        let value = $(this).val();
        let obj = {};
        obj["id"] = value;
        params.push(obj);
    })

    object['userRoleDtos'] = params;

    let json = JSON.stringify(object);
    return json
}