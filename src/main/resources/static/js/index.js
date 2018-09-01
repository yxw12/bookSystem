/**
 * Created by Yan on 2018/8/27.
 */
/*
$(document).ready(function (){
    alert("OK TEST");
});*/
function registerUser() {
    window.location.href = "/register";
}
function submitForm(){
        var userCode = $("#userCode").val();
        var password = $("#password").val();
        var params = {"userCode": userCode, "password": password};
        $.post("/user/findUser", params, function (data) {
            //    alert(data.status);
            if (data.status == 200) {
                alert("登录成功！");
                window.location.href = "/success";
            } else {
                alert("登录失败，用户名或密码错误！");
                window.location.href = "/";
            }
        });
}
//条件分页查询
function doSearch(){
    $("#userTable").empty();
    var bookType=$('#bookType').val();
    var bookName=$('#bookName').val();
    var isBorrow=$('#isBorrow').val();
    var params = {
        "bookType":bookType,
        "bookName":bookName,
        "isBorrow":isBorrow,
    };
//    alert(bookType+".."+bookName+".."+isBorrow);
    $.ajax({
        url:"/fuzzySearch",
        type:"post",
        dataType:"json",
        data:params,
        success:function (data) {
            $('#userTable').datagrid('loadData',data);//刷新表单
            var pg = $("#userTable").datagrid("getPager");
            if(pg){
                $(pg).pagination({
                    onSelectPage:function(pageNumber,pageSize){
        //                alert("pageNumber"+pageNumber);
         //               alert(pageSize);
                        pg.pagination('refresh', {
                            page: pageNumber,
                            rows: pageSize
                        });
                        var params = {
                            "bookType":bookType,
                            "bookName":bookName,
                            "isBorrow":isBorrow,
                            "page":pageNumber,
                            "rows":pageSize
                        };
                        $.post("/fuzzySearch", params, function (data) {
                            $('#userTable').datagrid('loadData', data);//刷新表单
                        });
                    }
                });
            }
        }
    })
}

// 格式化书籍借阅状态
function formatStatus(val,row){
    if (val == 2){
        return '<a href="javascript:alterIsBorrow('+val+','+row.bookId+');" style="text-decoration-line: none" >未借阅</a>';
    } else if(val == 1){
        return '<span style="color:red;">已借阅</span>';
    } else {
        return '未知';
    }
}
function alterIsBorrow(val,bookId) {
    //    alert(val+".."+bookId+"..");
        var params = {"isBorrow":val,"bookId":bookId};
        var r=confirm("您确定要借阅此书籍？");
        if (r==true)
        {
            $.post("user/alterIsBorrow",params, function(data) {
                if (data.status == 200) {
                    alert("借阅成功！");
                    window.location.href = "/success";
                } else {
                    alert("借阅失败！");
                }
            });
        }
}
//表单提交
function registerForm() {
    if($("#ff").form('validate')) {
        var userCode = $("input[name='userCode']").val();
        var password = $("input[name='password']").val();
        var repassword = $("input[name='repassword']").val();
        var gender = $("input[name='gender']").val();
        var email = $("input[name='email']").val();
//    alert(userCode);
        var params = {
            "userCode": userCode,
            "password": password,
            "repassword": repassword,
            "gender": gender,
            "email": email
        };
        $.post("user/registerUser", params, function (data) {
            //       alert(data.status);
            if (data.status == 200) {
                alert("恭喜您，注册成功！请登录！");
                window.location.href = "/";
            } else {
                alert("两次密码不一致！");
                window.location.href = "/register";
            }
        });
    }else{
        alert("存在校验项未通过！");
    }
}
//清空查询菜单
function clearSearch() {
    $('#tb').form('clear');
}