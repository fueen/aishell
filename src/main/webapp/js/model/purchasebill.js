
//格式化供应商
function supplierFormatter(v){
    return v?v.name:"";
}
//格式化采购人
function auditorFormatter(v){
    return v?v.username:"";
}
//格式化审核人
function buyerFormatter(v){
    return v?v.username:"";
}
//格式化录入人
function inputUserFormatter(v){
    return v?v.username:"";
}
//状态

function statusFormatter(v){
    if (v==0){
        return "<div style='background-color: #293462;color: #f5ecff;width: 120px;margin: auto;'>待审</div>";
    }else if (v==1){
        return "<div style='background-color: #445c3c;color: #f5ecff;width: 120px;margin: auto;'>通过</div>";
    }else {
        return "<div style='background-color: #7c0a02;color: #f5ecff;width: 120px;margin: auto;'>失败</div>";
    }
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
            //情况明细编辑
            dg.datagrid("loadData",[]);
        },
        //关闭面板
        close:function(){
            editDialog.dialog("close");
        },
        //保存和修改的方法
        save:function(){
            var url = "/purchasebill/save";
            var purchasebillId = $("#purchasebillId").val();
            if(purchasebillId){
                url = "/purchasebill/update?cmd=update";
            }
            editForm.form('submit',{
                //请求地址
                url:url,
                //发送请求之前的行为
                onSubmit:function (params) {
                    //获取明细中的所有行
                    var rows = dg.datagrid("getRows");
                    //遍历，然后拼接需要传递的数据
                    for (var i=0;i<rows.length;i++){
                        var row = rows[i];
                        params[`items[${i}].product.id`] = row.product.id;
                        params[`items[${i}].num`] = row.num;
                        params[`items[${i}].price`] = row.price;
                        params[`items[${i}].descs`] = row.descs;
                    }
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

                //完成供应商和采购员的回显
                if (rows.supplier){
                    rows["supplier.id"] = rows.supplier.id;
                }
                if (rows.buyer){
                    rows["buyer.id"] = rows.buyer.id;
                }
                //在表单中回显数据
                editForm.form('load',rows);
                //复制一份新的数据明细
                var newRows = [...rows.items];
                dg.datagrid("loadData",newRows);
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
                            url:"/purchasebill/delete",
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

    //明细数据的操作
    /**
     * 找到对应的datagrid
     *  defaultRow:默认行数据
     *  insertPosition:插入数据的位置(bottom:下面 top:上面)
     */
    var dg = $("#gridItems"),
        defaultRow = { product: "", productColor: "", productImage: "", num: 0, price: 0, amount: 0, descs: "" },
        insertPosition = "bottom";

    /**
     * 初始化创建咱们的编辑datagrid
     */
    var dgInit = function () {
        //grid中所有的列
        var getColumns = function () {
            var result = [];

            var normal = [
                {
                    field: 'product', title: '产品', width: 180,
                    editor: {
                        type: "combobox",
                        options: {
                            valueField:'id',
                            textField:'name',
                            panelHeight:'auto',
                            url:'/product/list',
                            required: true
                        }
                    },
                    formatter:function (v) {
                        return v?v.name:"";
                    }
                },
                {
                    field: 'productColor', title: '颜色', width: 80,
                    formatter:function (v,r) {
                        if (r && r.product){
                            return "<div style='width: 20px;height: 20px;background-color: "+r.product.color+"'></div>";
                        }
                    }
                },
                {
                    field: 'productImage', title: '图片', width: 100,
                    formatter:function (v,r) {
                        if (r && r.product){
                            return `<img src="${r.product.smallPic}" height="40px" width="40px"/>`;
                        }
                    }
                },
                {
                    field: 'num', title: '数量', width: 100,
                    editor: {
                        type: "numberbox",
                        options: {
                            required: true,
                            precision:1
                        }
                    }
                },
                {
                    field: 'price', title: '价格', width: 100,
                    editor: {
                        type: "numberbox",
                        options:{
                            precision:1
                        }
                    }
                },
                {
                    field: 'amount', title: '小计', width: 100,
                    formatter:function (v,r) {
                        if (r.num && r.price){
                            var amount = (r.num*r.price).toFixed(2);
                            return amount;
                        }
                    }
                },{

                    field: 'descs', title: '备注', width: 180,
                    editor: {
                        type: "text"
                    }
                }
            ];
            result.push(normal);

            return result;
        };
        //grid中的属性
        var options = {
            idField: "ID",
            rownumbers: true, //行号
            fitColumns: true,
            //fit: true,
            //border: false,
            toolbar:"#itemBtns",
            singleSelect: true,
            height:300,
            columns: getColumns(),
            //表示开启单元格编辑功能
            enableCellEdit: true
        };
        //创建grid
        dg.datagrid(options);
    };
    //插入的行索引
    //根据这个确定位置插件在上面还是下面(和上面的insertPosition匹配)
    var getInsertRowIndex = function () {
        return insertPosition == "top" ? 0 : dg.datagrid("getRows").length;
    }

    //事件注册
    var buttonBindEvent = function () {
        //添加行
        $("#btnInsert").click(function () {
            var targetIndex = getInsertRowIndex(), targetRow = $.extend({}, defaultRow);
            dg.datagrid("insertRow", { index: targetIndex, row: targetRow });
            dg.datagrid("editCell", { index: targetIndex, field: "Code" });
        });
        //删除行
        $("#btnRemove").click(function () {
            var rows = dg.datagrid("getSelected");
            //拿到对应的行
            var index = dg.datagrid("getRowIndex",rows);
            //删除这一行
            dg.datagrid("deleteRow",index);
        });



    };

    //让执行初始化的方法 buttonBindEvent:注册相应的按钮事件
    dgInit(); buttonBindEvent();

});