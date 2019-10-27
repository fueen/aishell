
//格式化产品类型编号
function productTypeFormat(v) {
    return v?v.name:"";
}

//格式化产品单位
function systemDictionarydetail(v) {
    return v?v.name:"";
}

//格式化产品品牌
function systemDictionarydetail1(v) {
    return v?v.name:"";
}



//图片格式化
function picFormat(v){
    return `<img src="${v}" style="width: 60px;">`;
}
//缩略图格式化
function smallPicFormat(v){
    return `<img src="${v}" style="width: 60px;">`;
}

//显示颜色
function colorFormat(v){
    return `<div style="background-color: ${v};width: 50px;height: 50px;margin: auto"></div>`;
}


//页面打印
function doPrint() {
    window.print();
}

//显示大图
/*function loadSuccess(data){
   var rows = data.rows;
   for (var i=0;i<rows.length;i++){
        var result = rows[i];
       //鼠标方上去显示大图
       $.easyui.tooltip.init($("img[src='"+result.smallPic+"']"), {
           position: "bottom",
           content: "<div style=\"width:400px;height:400px;\"><img src='"+result.smallPic+"'/></div>"
       });
   }
}*/


//成功后进行加载
function loadSuccess(data) {
    var rows = data.rows;
    console.debug(rows);
    for(var i=0;i<rows.length;i++){
        var result = rows[i];
        console.debug(result);
        $.easyui.tooltip.init($("img[src='"+result.smallPic+"']"), {
            position: "rigth",
            content: "<div style=\"width:600px;height:480px;\"><img src=\"/images/head/2.jpg \"  /></div>"
        });
    }
}



$(function () {


    //打印
    $("#btnPrint").bind("click", function (event) {
        doPrint();
    });

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
            var url = "/product/save";
            var productId = $("#productId").val();
            if(productId){
                url = "/product/update?cmd=update";
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

                //在表单中回显数据
                editForm.form('load',rows);
                var val = rows.productType.id;
                var val1 = rows.systemDictionarydetail.id;
                var val2 = rows.systemDictionarydetail1.id;
                $("#productTypeList").combobox('select',val);
                $("#systemDictionarydetailList").combobox('select',val1);
                $("#systemDictionarydetail1List").combobox('select',val2);
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
                        //进行ajax提交
                        $.ajax({
                            url:"/product/delete",
                            type:"post",
                            data:{id:rows.id},
                            dataType:"json",
                            success:function (result) {
                                if (result.sueccess){
                                    //刷新datagrid中的数据
                                    tt.datagrid("reload");
                                }else {
                                    $.messager.alert('失败','删除失败,原因是:'+result.msg,'error');
                                }
                            }
                        });
                    }
                });
            }
        },
        //高级查询功能
        search:function () {
            var params = searchForm.serializeObject();
            if (params==null){
                $.messager.alert('警告','什么都不输入你在搜啥呢?');
                return;
            }else {
                tt.datagrid('load',params);
            }

        }

    };

});