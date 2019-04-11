$(function () {
    let token = $.cookie('token');
    to_page(1);
});

function to_page(pn) {
    $.ajax({
        url: "/nios/registration/registration",
        data: "pageNumber=" + pn,
        type: "GET",
        success: function (result) {
            build_registration_table(result);
            build_page_nav(result);
        }
    });
}

function build_registration_table(result) {
    $("#registration_table tbody").empty();
    let appointmentInfo = result.content.pageInfo.list;
    $.each(appointmentInfo, function (index, item) {
        let gender;
        if (item.gender == "M") {
            gender = "男性";
        } else {
            gender = "女性";
        }
        let status;
        if (item.status == "Y") {
            status = "可用";
        } else {
            status = "停用";
        }
        let userIdTd = $("<td></td>").append(item.id);
        let userNameTd = $("<td></td>").append(item.name);
        let AgeTd = $("<td></td>").append(item.age);
        let GenderTd = $("<td></td>").append(gender);
        let MobileTd = $("<td></td>").append(item.mobile);
        let EmailTd = $("<td></td>").append(item.email);
        let StatusTd = $("<td></td>").append(status);
        let editBtn = $("<button></button>").addClass("btn btn-success  btn-sm edit_btn").append($("<span></span>").addClass(
            "glyphicon glyphicon-pencil")).append("update");
        editBtn.attr("edit-id", item.id);
        // let delBth = $("<button></button>").addClass(
        //     "btn btn-warning  btn-sm delete_btn").append(
        //     $("<span></span>")
        //         .addClass("glyphicon glyphicon-trash")).append("停用");
        // delBth.attr("del-id", item.id);
        let btnTd = $("<td></td>").append(editBtn)/*.append(" ").append(delBth)*/;
        $("<tr></tr>").append(userIdTd).append(userNameTd).append(AgeTd).append(GenderTd).append(MobileTd).append(EmailTd).append(StatusTd).append(btnTd).appendTo("#registration_table tbody");
    });
}

$("#patient_save_btn").click(function () {
    let json = getJson();
    $.ajax({
        url: "/nios/registration/registration",
        type: "POST",
        async: false,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: json,
        success: function (result) {
            if (result.code == 100) {
                to_page(0);
                reset_form("#Registration_Form");
            }
        },
    });
});


function reset_form(ele) {
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help_block").text("");
}

$(document).on("click", ".delete_btn", function () {
    let PatientId = $(this).attr("del-id");
    if (confirm("确认停用【" + PatientId + "】吗？")) {
        $.ajax({
            url: "/nios/registration/registration/"+PatientId,
            type: "DELETE",
            success: function (result) {
                if (result.code == 100) {
                    to_page(0);
                }
            }
        });
    }
});

$(document).on("click", ".edit_btn", function () {
    getPatient($(this).attr("edit-id"));
    $("#patient_save_btn").attr("edit-id", $(this).attr("edit-id"));
});

function getPatient(id) {
    $.ajax({
        url: "/nios/registration/registration/" + id,
        type: "GET",
        success: function (result) {
            let data = result.content.patient;
            $("#id").val(data.id);
            $("#Name").val(data.name);
            $("#Age").val(data.age);
            if (data.gender == "M") {
                $("input[name='radio2'][value='M']").attr("checked", true);
            } else {
                $("input[name='radio2'][value='F']").attr("checked", true);
            }
            $("#Email").val(data.email);
            $("#Mobile").val(data.mobile);
            if (data.status == "Y") {
                $("input[name='status'][value='Y']").attr("checked", true);
            } else {
                $("input[name='status'][value='N']").attr("checked", true);
            }

        }
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

function getJson() {
    let object = {};

    object['id'] = $("#id").val();
    object['name'] = $("#Name").val();
    object['age'] = $("#Age").val();
    object['gender'] = $('input[name="radio2"]:checked').val();
    object['mobile'] = $("#Mobile").val();
    object['email'] = $("#Email").val();
    object['status'] = $('input[name="status"]:checked').val();

    let json = JSON.stringify(object);
    return json
}





