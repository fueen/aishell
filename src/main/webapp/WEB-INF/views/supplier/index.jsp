<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/10/12
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>供应商管理</title>
    <%@include file="/WEB-INF/views/head.jsp"%>
    <script src="/js/model/supplier.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/index_currency.css"/>
</head>
<body>

<div id="toolbar">
    <a href="javascript:;" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
    <a href="javascript:;" data-method="update" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    <a href="javascript:;" data-method="delete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
</div>
<%-- 高级查询的表单--%>
<form id="searchForm" method="post" class="senior">
    权限:<input class="easyui-textbox" width="80px" id="username" name="name"/>
    <a href="#" class="easyui-linkbutton" inconCls="icon-search" data-method="search">查询</a>

</form>


<table id="tt" class="easyui-datagrid"
       url="/supplier/page" fitColumns="true"
       title="供应商列表" iconCls="icon-man"
       rownumbers="true" pagination="true"
>
    <thead>
    <tr>
        <th data-options="field:'name',width:100">供应商名称</th>

    </tr>
    </thead>
</table>
<%-- 添加和修改的弹出框--%>
<div id="editDialog" class="easyui-dialog" title="添加/修改" style="padding: 20px; width:600px;height:500px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,buttons:'#btns',closed:true">
    <form id="editForm" method="post">
        <table style="margin: auto;width: 400px;height: 300px;padding-top: 50px;">
            <tr>
                <input id="supplierId" type="hidden" name="id">
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">名称</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;height: 50px;text-align: center">
                    <input style="width: 200px;border: 4px #000 solid;font-size: 20px;text-align: center; font-weight: bold;font-family: '幼圆';border-radius: 20px;" class="easyui-validatebox" type="text" name="name" data-options="required:true" />
                </td>
            </tr>
        </table>
    </form>
    <div id="btns">
        <a href="javascript:;" data-method="save" class="easyui-linkbutton c1" data-options="iconCls:'icon-ok'">确定</a>
        <a href="javascript:;" data-method="close" class="easyui-linkbutton c4" data-options="iconCls:'icon-cancel'">取消</a>
    </div>
</div>

</body>
</html>
