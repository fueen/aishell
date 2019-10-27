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
    <title>角色管理</title>
    <%@include file="/WEB-INF/views/head.jsp"%>
    <script src="/js/model/role.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/index_currency.css"/>

</head>
<body>
<%--<div class="top">
    <h1 style="text-align: center">员工管理系统</h1>
</div>--%>
<div id="toolbar">
    <a href="javascript:;" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
    <a href="javascript:;" data-method="update" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    <a href="javascript:;" data-method="delete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
</div>
<%-- 高级查询的表单--%>
<form id="searchForm" method="post" class="senior">
    名称:<input class="easyui-textbox" width="80px" id="username" name="name"/>
    <a href="#" class="easyui-linkbutton" inconCls="icon-search" data-method="search">查询</a>

</form>


<table id="tt" class="easyui-datagrid"
       url="/role/page" fitColumns="true"
       title="角色列表" iconCls="icon-man"
       rownumbers="true" pagination="true"
>
    <thead>
    <tr>
        <th style="height: 30px" data-options="field:'name',width:30">名称</th>
        <th style="height: 30px" data-options="field:'sn',width:30">编码</th>
        <th id="t1" style="height: 30px" data-options="field:'permissions',width:100,formatter:permsFormat">权限</th>
    </tr>
    </thead>
</table>

<div id="editDialog" class="easyui-dialog" title="添加/修改" style="padding: 25px;width: 900px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,buttons:'#btns',closed:true">
    <form id="editForm" method="post">
        <input id="roleId" type="hidden" name="id" />
        <table>
            <tr>
                <td>
                    名称:<input class="easyui-validatebox" type="text" name="name" data-options="required:true" />
                    编码:<input  class="easyui-validatebox" type="text" name="sn" data-options="required:true" />
                </td>
                <td></td>
            </tr>
        </table>
        <div class="easyui-layout" style="width:100%;height:400px;">
            <div data-options="region:'west',split:true" style="width:440px;">
                <table id="rolePermsGrid"></table>
            </div>
            <div data-options="region:'center'">
                <table id="allPermsGrid"></table>
            </div>
        </div>
    </form>
    <div id="btns">
        <a href="javascript:;" data-method="save" class="easyui-linkbutton c2" data-options="iconCls:'icon-ok'">确定</a>
        <a href="javascript:;" data-method="close" class="easyui-linkbutton c5" data-options="iconCls:'icon-cancel'">取消</a>
    </div>
</div>
</body>
</html>
