function layout(){
    $.ajax({
        url: "/users/layout",
        type: "GET",
        dataType: "JSON",
        success: function (json){
            if (json.state==200){
                if (confirm("确定退出吗")){
                    // alert("退出成功！");
                    location.href="login.html";
                }

            }
        }
    });
}