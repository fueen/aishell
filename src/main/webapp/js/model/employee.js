



//格式化部门显示
function deptFormat(v) {
    return v?v.name:"";
}
function imageFormat(v){
    return `<img src="${v}" style="width: 60px;">`;
}



$(function () {
    //数据表格
    var tt = $('#tt');
    //查询的表单
    var searchForm = $('#searchForm');
    //弹出框
    var editDialog = $("#editDialog");
    //添加的表单
    var editForm = $("#editForm");

    $("*[data-method]").on("click",function () {

        ifueen[$(this).data("method")]();

    })

    //相关按钮对应的功能
    ifueen = {
        //添加
        add:function () {
            $("*[data-update]").show();
            $("*[data-update] input").validatebox("enable");
            //每次新打开弹出框时都要清除上次表单里的数据
            editDialog.form("clear");
            //打开弹出框
            editDialog.dialog("center").dialog("open");
        },
        //关闭面板
        close:function(){
            editDialog.dialog("close");
        },
        //保存和修改的方法
        save:function(){
            var url = "/employee/save";
            var employeeId = $("#employeeId").val();
            if(employeeId){
                url = "/employee/update?cmd=update";
            }
            editForm.form('submit',{
                //请求地址
                url:url,
                //发送请求之前的行为
                onSubmit:function () {
                    return $(this).form('validate');
                },
                //回调函数,添加之后的操作
                success:function (data) {
                    //将json格式字符串转化为Json对象
                    var result = JSON.parse(data);
                    if (result.sueccess){
                        tt.datagrid("reload");
                    }else {
                        $.messager.alert('失败',`添加失败，原因是${result.msg}`,"info");
                    }
                    //最后关闭面板
                    ifueen.close();
                }
            })
        },
        //修改
        update:function () {
            $("*[data-update]").hide();
            $("*[data-update] input").validatebox("disable");
            //拿到选中行的数据
            var rows = tt.datagrid('getSelected');
            if (rows==null){
                $.messager.alert('警告','什么都没选你改什么呢弟弟?');
                return;
            }else {
                //每次新打开弹出框时都要清除上次表单里的数据
                editDialog.form("clear");
                //打开弹出框
                editDialog.dialog("center").dialog("open");
                console.debug(rows.id);
                //在表单中回显数据
                var val = rows.department.id;
                editForm.form('load',rows);
                $("#employeeList").combobox('select',val);
            }
        },
        //删除功能
        delete:function () {
            var rows = tt.datagrid('getSelected');
            if (rows==null) {
                $.messager.alert('警告','你在删什么呢小老弟?');
                return;
            }else {
                $.messager.confirm('删除','想清楚奥小老弟',function (r) {
                    if (r){
                        $.get("/employee/delete",{id:rows.id},function (result) {
                            if(result.sueccess){
                                //删除成功，刷新datagrid
                                tt.datagrid("reload");
                            }else{
                                //删除失败，给出失败提示
                                $.messager.alert('失败','删除失败，原因是:'+result.msg,"error");
                            }
                        })
                    }
                });
            }
        },
        //高级查询功能
        search:function () {
            var params = searchForm.serializeObject();
            //获取文本框的值
            var username = $("#username").val();
            var email = $("#email").val();
            console.debug(params)
            console.debug(params.departmentId);
            if (params==null){
                $.messager.alert('警告','什么都不输入你在搜啥呢?');
                return;
            }else {
                tt.datagrid('load',params);
            }

        }

    };

});