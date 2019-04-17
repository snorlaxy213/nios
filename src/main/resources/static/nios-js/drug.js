$(function () {
    to_page(0);
});

function to_page(pn) {
    $.ajax({
        url: "/nios/drugProfile/drugProfile",
        data: "pageNumber=" + pn,
        type: "GET",
        success: function (result) {
            build_drugs_table(result);
            build_page_nav(result);
        }
    });
}

function build_drugs_table(result) {
    $("#drugs_table tbody").empty();
    let drugInfo = result.content.pageInfo.list;
    $.each(drugInfo, function (index, item) {
        let checkBoxTD = $("<td><input type='checkbox' class='check_item'/></td>");
        let drugIdTd = $("<td></td>").append(item.id);
        let drugNameTd = $("<td></td>").append(item.name);
        let drugTypeTd;
        if (item.type == 'Sheet') {
            drugTypeTd = $("<td></td>").append("块状");
        } else {
            drugTypeTd = $("<td></td>").append("颗粒");
        }
        let DefaultAmountTd = $("<td></td>").append(item.defaultQuantity);
        let DescriptionTd = $("<td></td>").append(item.description);
        let AmountTd = $("<td></td>").append("0");
        let UnitTd;
        if (item.unit == 'Jra') {
            UnitTd = $("<td></td>").append("两");
        } else {
            UnitTd = $("<td></td>").append("克");
        }
        let StatusTd;
        if (item.status == 'Y') {
            StatusTd = $("<td></td>").append("生效");
        } else {
            StatusTd = $("<td></td>").append("失效");
        }
        let editBtn = $("<button></button>").addClass("btn btn-primary  btn-sm edit_btn").append($("<span></span>").addClass(
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
        $("<tr></tr>").append(checkBoxTD).append(drugIdTd).append(drugNameTd).append(drugTypeTd).append(DefaultAmountTd).append(DescriptionTd).append(AmountTd).append(UnitTd).append(StatusTd).append(btnTd).appendTo("#drugs_table tbody");
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
    getDrug($(this).attr("edit-id"));
    $("#drug_save_btn").attr("edit-id", $(this).attr("edit-id"));
});

function getDrug(id) {
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
            $("#DefaultAmount").val(data.defaultQuantity);
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

    object['id'] = $("#DrugId").val();
    object['name'] = $("#DrugName").val();
    object['type'] = $('input[name="DrugType"]:checked').val();
    object['defaultQuantity'] = $("#DefaultAmount").val();
    object['description'] = $("#Description").val();

    $("#Unit option:selected").each(function () {
        let value = $(this).val();
        object["unit"] = value;
    })

    object['status'] = $('input[name="Status"]:checked').val();

    let json = JSON.stringify(object);
    return json
}