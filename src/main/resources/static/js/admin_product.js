$(document).ready(function() {
    show1();
    showProductList();
});

function showProductList() {
    $("#product-list").empty();
    $.ajax({
        url: "/products",
        type: "GET",
        dataType: "JSON",
        success: function(json) {
            console.log(json);
            let list = json.data;
            for (let i = 0; i < list.length; i++) {
                let tr = '<tr>\n' +
                    '\t\t\t\t\t\t\t\t\t<td class="text-center">#{p-type}</td>\n' +
                    '\t\t\t\t\t\t\t\t\t<td>#{p-title}</td>\n' +
                    '\t\t\t\t\t\t\t\t\t<td>#{p-sell}</td>\n' +
                    '\t\t\t\t\t\t\t\t\t<td>#{p-price}</td>\n' +
                    '\t\t\t\t\t\t\t\t\t<td>#{p-num}</td>\n' +
                    '\t\t\t\t\t\t\t\t\t<td>\n' +
                    '\t\t\t\t\t\t\t\t\t\t<a onclick="p_put()" href="#" class="btn btn-primary  active btn-sm" role="button">上架</a>\n' +
                    '\t\t\t\t\t\t\t\t\t\t<a onclick="p_down()" href="#" class="btn btn-default  active btn-sm" role="button">下架</a>\n' +
                    '\t\t\t\t\t\t\t\t\t</td>\n' +
                    '\t\t\t\t\t\t\t\t\t<td>\n' +
                    '\t\t\t\t\t\t\t\t\t\t<!-- 商品添加弹出框 -->\n' +
                    '\t\t\t\t\t\t\t\t\t\t<div>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t<!--bootstrap实现弹出模态框-->\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t<!--\t\t\t\t\t\t\t\t\t\t<a href="#" class="btn btn-primary btn-lg active btn-sm" role="button">编辑</a>-->\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t<!--\t\t\t\t\t\t\t\t\t\t<a href="#" class="btn btn-default btn-lg active btn-sm" role="button">删除</a>-->\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t<button onclick="p_edit(#{pid})" type="button" class="btn btn-primary btn-lg active btn-sm" data-target="#myModal2" data-toggle="modal">编辑</button>\n' +
                    '\t\t\t\t\t\t\t\t\t\t\t<button onclick="p_delete(#{pid})" type="button" class="btn btn-default btn-lg active btn-sm">删除</button>\n' +
                    '\n' +
                    '\t\t\t\t\t\t\t\t\t\t</div>\n' +
                    '\t\t\t\t\t\t\t\t\t</td>\n' +
                    '\t\t\t\t\t\t\t\t\t<!--\t\t\t\t\t\t\t\t\t<a href="#" class="btn btn-default btn-lg active btn-sm" role="button">删除</a>-->\n' +
                    '\t\t\t\t\t\t\t\t</tr>';
                tr = tr.replace(/#{pid}/g, list[i].id);
                tr = tr.replace(/#{p-type}/g, list[i].itemType);
                tr = tr.replace(/#{p-title}/g, list[i].title);
                tr = tr.replace(/#{p-sell}/g, list[i].sellPoint);
                tr = tr.replace(/#{p-price}/g, list[i].price);
                tr = tr.replace(/#{p-num}/g, list[i].num);

                $("#product-list").append(tr);
            }
        }
    });
}

function p_edit(pid){
    $.ajax({
        url: "/admin/product/"+pid+"/update",
        type: "GET",
        dataType: "JSON",
        success: function (json){
            if (json.state==200){
                $("#p-id").val(json.data.id);
                $("#inputSeries3").val(json.data.itemType);
                $("#inputTitle3").val(json.data.title);
                $("#inputSell3").val(json.data.sellPoint);
                $("#inputPrice3").val(json.data.price);
                $("#inputRepertory3").val(json.data.num);
            }else {
                alert(json.message);
            }
        }
    });
}

function updatesubmit(){
    $.ajax({
        url: "/admin/update_product",
        type: "POST",
        dataType: "JSON",
        data: $("#pro_list_form").serialize(),
        success: function (json){
            if (json.state==200){
                alert("修改商品信息成功");
                location.href="managerTables.html"
            }else {
                alert(json.message);
            }
        }
    });
}

function add_product(){
    $.ajax({
        url: "/admin/add_product",
        type: "POST",
        dataType: "JSON",
        data: $("#pro_add_form").serialize(),
        success: function (json){
            if (json.state==200){
                alert("添加商品成功");
                location.href="managerTables.html"
            }else {
                alert(json.message);
            }
        }
    });
}

function p_delete(pid){
    if (confirm("确定删除吗？")){
        $.ajax({
            url: "/admin/"+pid+"/delete_product",
            type: "GET",
            dataType: "JSON",
            success: function (json){
                if (json.state==200){
                    alert("删除商品成功");
                    location.href="managerTables.html"
                }else {
                    alert(json.message);
                }
            }
        });
    }else{
        return false;
    }

}