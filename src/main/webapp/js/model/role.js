
function permsFormat(v) {
    var permsName = "";
    for(var p of v){
        permsName += p.name +" ";
    }
    return permsName;
}

//入口函数：页面读取所有元素再执行
$(function () {

    //把常用的一些组件获取
    var  tt = $("#tt"); //页面显示表格
    var  searchForm = $("#searchForm"); //查询表单
    var  editDialog = $("#editDialog"); //添加弹出框
    var  editForm = $("#editForm"); //添加表单
    //拿到两个grid
    var  rolePermsGrid = $("#rolePermsGrid");  //左边角色的权限
    var  allPermsGrid = $("#allPermsGrid");  //右边所有权限数据

    //只要有data-method属性的元素我都要为它注册事件
    $("*[data-method]").on("click",function () {
        //谁调用，this就指向谁(这个this是普通dom对象)
        //$(dom对象) -> 变成jQuery对象，有很多jQuery特有的功能(更加强大)
        //console.debug($(this));
        //1.拿到当前按键的属性 add,update
        //var methodName = $(this).attr("data-method");
        //var methodName = $(this).data("method");
        //2.执行对应的方法(动态调用)
        //  ifueen.say() == ifueen["say"]();
        ifueen[$(this).data("method")]();
    })

    //准备了相应的方法功能
    //js ES5,ES6
    ifueen = {
        //添加(只是弹出form)
        add(){
            //密码显示并启用
            $("*[data-update]").show();
            $("*[data-update] input").validatebox("enable");
            //打开面板前把里面的数据清除(只清空form)
            editForm.form("clear");
            //把添加框(editDialog)打开
            editDialog.dialog("center").dialog("open");
            //loadData 加载本地数据，旧的行将被移除。
            rolePermsGrid.datagrid("loadData",[]);

        },
        //修改(只是弹出form)
        update () {
            //密码隐藏并禁用
            $("*[data-update]").hide();
            $("*[data-update] input").validatebox("disable");
            //1.拿到选中的那一条数据
            var row = tt.datagrid("getSelected");
            //2.如果没有选中，给出提示
            if(row==null){
                $.messager.alert('警告','什么都不选你修改什么呢？',"info");
                return;
            }
            //3.清空表单并弹出数据
            editForm.form("clear");
            editDialog.dialog("center").dialog("open");
            //4.完成咱们的数据回显
            editForm.form("load",row);
            //复制要修改的角色中的权限 JS新语法
            var newPerms = [...row.permissions];
            rolePermsGrid.datagrid("loadData",newPerms);
        },
        //保存修改功能(调用后台保存)
        save(){
            //添加功能的路径
            var url ="/role/save";
            //拿到表单中的员工id,如果id存在，代表修改
            var roleId = $("#roleId").val();
            if(roleId){
                url = "/role/update?cmd=update";
            }
            /**
             * 1.把表单数据传到后台
             */
            editForm.form('submit', {
                url:url, //提交的路径
                //提交前做点什么 如果返回false,它就不提交数据
                /**
                 * param:你给它加的所有数据都会向后台提交
                 */
                onSubmit: function(param){
                    //拼接出相应的权限(List<Permission> permissions)对应的格式
                    /**
                     *SringMVC需要的格式是：
                     *  permissions[0].id = 1
                     *  permissions[1].id = 2
                     *  ...
                     */
                        //1.拿到左边grid的所有值
                    var rows = rolePermsGrid.datagrid("getRows");
                    //2.遍历它，拿到每个id
                    for(var i=0;i<rows.length;i++){
                        param[`permissions[${i}].id`] = rows[i].id;
                    }
                    return $(this).form('validate');
                },
                //data是一个json字符串
                success:function(data){
                    // {"success":true}
                    //把一个Json字符串转成JSON对象
                    //eval("("+data+")")
                    var result = JSON.parse(data);
                    if(result.sueccess){
                        //成功就刷新页面
                        tt.datagrid("reload");
                    }else{
                        $.messager.alert('警告',`添加失败,原因是:${result.msg}`,"info");
                    }
                    //关闭面板
                    ifueen.close();
                }
            });
        },
        delete () {
            //1.拿到你选择的那一条数据 (easyui的datagrid中有这个功能)
            //  row就是你选择的这一行的数据
            var row = tt.datagrid("getSelected");
            //2.如果没有拿到数据,给一个提示，让他选中再执行(后面的代码就不运行) Messager alert
            if(row==null){
                //alert("没有货，滚蛋！"); //不建议使用 1.不同浏览器效果不一样 2.它会阻塞进程
                $.messager.alert('警告','你在删什么呢小老弟？',"info");
                return;
            }
            //3.如果拿到数据，给一个提示，是否要删除？ Messager confirm
            $.messager.confirm('确认','想清楚奥小老弟？',function(r){
                if (r){
                    //4.如果要删除 -> 发送Ajax请求到后台进行数据删除 $.get/post("/role/delete",{id:2},function(){....})
                    //  后台会返回相应的数据 ：{success:true/false,msg:xxx}
                    //  如果成功，刷新datagrid
                    $.get("/role/delete",{id:row.id},function (result) {
                        if(result.sueccess){
                            //删除成功，刷新datagrid
                            tt.datagrid("reload");
                        }else{
                            //删除失败，给出失败提示
                            $.messager.alert('失败','删除没有成功，原因是:'+result.msg,"error");
                        }
                    })
                }
            });
        },
        //高级查询
        search(){
            //JQuery没有提供拿到json格式的数据
            //直接拿到要查询的值  {username:xx,...}
            var params =searchForm.serializeObject();
            // //grid刷新
            tt.datagrid('load',params);
        },
        //关闭面板
        close(){
            editDialog.dialog("close");
        },
        //添加权限 index:索引 row:这一行信息
        addPerms(index, row){
            //1.拿到左边的所有权限
            var rows = rolePermsGrid.datagrid("getRows");
            //2.遍历所有权限，如果出现相同的权限，就给出提示
            for(var r of rows){
                if(r.id == row.id){
                    $.messager.show({
                        title:'警告',
                        msg:'该权限已经存在！',
                        showType:'slide',
                        style:{
                            right:'',
                            top:document.body.scrollTop+document.documentElement.scrollTop,
                            bottom:''
                        }
                    });
                    return;
                }
            }
            //为左边的grid添加一条数据
            rolePermsGrid.datagrid("appendRow",row);
        },
        //删除权限
        removePerms(index,row){
            //把你选中的这条数据给删除
            rolePermsGrid.datagrid("deleteRow",index);
        }
    };


    //创建左边角色的权限
    rolePermsGrid.datagrid({
        fit:true,
        fitColumns:true,
        onDblClickRow:ifueen.removePerms,
        columns:[[
            {field:'name',title:'名称',width:100},
            {field:'url',title:'路径',width:100},
            {field:'sn',title:'编码',width:100}
        ]]
    });

    //创建左边角色的权限
    allPermsGrid.datagrid({
        fit:true,
        fitColumns:true,
        url:"/permission/list",
        //监听双击一行的事件
        onDblClickRow:ifueen.addPerms,
        columns:[[
            {field:'name',title:'名称',width:100},
            {field:'url',title:'路径',width:100},
            {field:'sn',title:'编码',width:100}
        ]],

    });

    $.easyui.tooltip.init($("#t1"), {
        position: "right",
        content: "信息1"
    });

});