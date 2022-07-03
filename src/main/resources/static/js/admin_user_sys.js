$(document).ready(function() {
    show1();
    showUserList();
});

function showUserList() {
    $("#user_list").empty();
    $.ajax({
        url: "/users",
        type: "GET",
        dataType: "JSON",
        success: function(json) {
            console.log(json);
            let list = json.data;
            for (let i = 0; i < list.length; i++) {
                let tr = ' <tr>\n' +
                    '<td class="text-center">#{username}</td>\n' +
                    '<td>#{userPhone}</td>\n' +
                    '<td>#{userEmail}</td>\n' +
                    '<td>#{userSex}</td>\n' +
                    '<td>\n' +
                    '<a href="#" class="btn btn-danger  active btn-sm" role="button">禁用</a>\n' +
                    '<a href="#" class="btn btn-default  active btn-sm" role="button">启用</a>\n' +
                    '</td>\n' +
                    '<td>\n' +
                    '<!-- 商品添加弹出框 -->\n' +
                    '<div>\n' +
                    '<!--bootstrap实现弹出模态框-->\n' +
                    '<!--\t\t\t\t\t\t\t\t\t\t<a href="#" class="btn btn-primary btn-lg active btn-sm" role="button">编辑</a>-->\n' +
                    '<!--\t\t\t\t\t\t\t\t\t\t<a href="#" class="btn btn-default btn-lg active btn-sm" role="button">删除</a>-->\n' +
                    '<button onclick="user_edit(#{uid})" type="button" class="btn btn-primary active btn-sm" data-target="#myModal4" data-toggle="modal">编辑</button>\n' +
                    '\n' +
                    '</div>\n' +
                    '<!--\t\t\t\t\t\t\t\t\t<a href="#" class="btn btn-default btn-lg active btn-sm" role="button">删除</a>-->\n' +
                    '</tr>';
                tr = tr.replace(/#{uid}/g, list[i].uid);
                tr = tr.replace(/#{username}/g, list[i].username);
                tr = tr.replace(/#{userPhone}/g, list[i].phone);
                tr = tr.replace(/#{userEmail}/g, list[i].email);
                tr = tr.replace(/#{userSex}/g, list[i].gender);

                $("#user_list").append(tr);
            }
        }
    });
}

function user_edit(uid){
    $.ajax({
        url: "/admin/user/"+uid+"/update",
        type: "GET",
        dataType: "JSON",
        success: function (json){
            if (json.state==200){
                $("#uid").val(json.data.uid);
                $("#inputUsername2").val(json.data.username);
                $("#inputPhone2").val(json.data.phone);
                $("#inputEmail2").val(json.data.email);
                $("#inputSex2").val(json.data.gender);
                $("#password").val(json.data.password);
            }else {
                alert(json.message);
            }
        }
    });
}

function userUpdateSubmit(){
    $.ajax({
        url: "/admin/update_user",
        type: "POST",
        dataType: "JSON",
        data: $("#user_list_update").serialize(),
        success: function (json){
            if (json.state==200){
                alert("用户信息修改成功");
                location.href="systemAdministrator.html"
                // show2();
            }else {
                alert(json.message);
            }
        }
    });
}