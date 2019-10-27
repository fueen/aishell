<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/10/12
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>后台管理系统</title>
    <%@include file="/WEB-INF/views/head.jsp"%>

    <script>

        $(function(){
            //通过ID拿到选项卡
            var etab = $("#etabs");
            $('#tree').tree({
                url:'/menu/findLoginUser',
                method:'get',
                onClick:function(node){
                    var textName = node.text;
                    if(node.url){
                        //新增选项卡
                        //判断是否存在这个选项卡
                        if(etab.tabs("exists",textName)){
                            //存在就进行切换操作
                            etab.tabs("select",textName);
                        }else{
                            //不存在就进行新建
                            $("#etabs").tabs("add",{
                                'title':textName,
                                closable:true,
                                content:'<iframe src="'+node.url+'" frameborder="0" width="100%" height="100%"></iframe>'
                            });
                        }
                    }
                },
            });
        })

    </script>
    <style>
        .topt{
            height:70px;
            padding-left: 50px;
            text-align: center;
            font-family: '华文中宋';
            background-color: #292525;
            border: 0px solid #000;
            color: #fff;
        }
        .tops{
            margin: -20px auto;
            width: 200px;
            height: 100%;
            border-bottom: 5px #fff solid;
            border-left: 5px #fff solid;
            border-right: 5px #fff solid;
            background-color: #fff;
        }
        a:link {color: #fff; text-decoration:none;}
        a:visited {color:#fff;text-decoration:none;}

    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" class="topt">
    <shiro:user>
        <h1>后台管理</h1>
        <div style="float: right;margin-top: -80px;margin-right: 50px">
        <p>欢迎您:<shiro:principal property="username"/></p>
        <a href="/logout">注销</a>
        </div>
    </shiro:user>
</div>

<div data-options="region:'west',title:'管理',split:true" style="width:150px;">
    <ul id="tree"></ul>

</div>
<div data-options="region:'center'" id="etabs" class="easyui-tabs" style="padding:0px;">
    <div title="首页" style="text-align: center;padding-top:20px;font-family: '华文中宋';color: #2e7b5c;">
    </div>
</div>
</body>
</html>
