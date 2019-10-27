<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/10/12
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>系统管理</title>
    <%@include file="/WEB-INF/views/head.jsp"%>
    <script src="/js/model/employee.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/employee.css"/>
</head>
<body>

<%--<div class="top">
    <h1 style="text-align: center">员工管理系统</h1>
</div>--%>


<div id="toolbar">

    <shiro:hasPermission name="employee:save">
    <a href="javascript:;" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="employee:update">
    <a href="javascript:;" data-method="update" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="employee:delete">
    <a href="javascript:;" data-method="delete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    </shiro:hasPermission>

</div>
<%-- 高级查询的表单--%>
<form id="searchForm" action="/employee/export" method="post" class="senior">
    用户名:<input class="search" width="80px" id="username" name="username"/>
    邮箱:<input class="search" width="80px" id="email" name="email"/>
    部门:<input class="easyui-combobox" name="departmentId"
              data-options="valueField:'id',textField:'name',
                   url:'/department/list',panelHeight:'auto'"/>


    <a href="#" class="easyui-linkbutton" inconCls="icon-search" data-method="search">查询</a>

    <button  class="easyui-linkbutton" inconCls="icon-search" data-method="search">导出</button>

</form>


<table id="tt" class="easyui-datagrid"
       url="/employee/page" fitColumns="true"
       title="员工列表" iconCls="icon-man"
       rownumbers="true" pagination="true"
>
    <thead>
    <tr>
        <th data-options="field:'username',width:100">用户名</th>
        <th data-options="field:'password',width:100">密码</th>
        <th data-options="field:'headImage',width:100,formatter:imageFormat">头像</th>
        <th data-options="field:'age',width:100">年龄</th>
        <th data-options="field:'email',width:100">邮箱</th>
        <th data-options="field:'department',width:100,formatter:deptFormat">部门</th>
    </tr>
    </thead>
</table>
<%-- 添加和修改的弹出框--%>
<div id="editDialog" class="easyui-dialog" title="添加/修改" style="padding: 20px; width:600px;height:500px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,buttons:'#btns',closed:true">
    <form id="editForm" method="post">
        <table style="margin: auto;width: 400px;height: 300px;padding-top: 50px;">
            <tr>
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">名称</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;height: 50px;text-align: center">
                    <input id="employeeId" type="hidden" name="id">
                    <input style="width: 200px;border: 4px #000 solid;font-size: 20px;text-align: center; font-weight: bold;font-family: '幼圆';border-radius: 20px;" class="easyui-validatebox" type="text" name="username" data-options="required:true" />
                </td>
            </tr>
            <tr data-update="true">
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">密码</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;height: 50px;text-align: center">
                    <input  style="width: 200px;border: 4px #000 solid;font-size: 20px;text-align: center; font-weight: bold;font-family: '幼圆';border-radius: 20px;" class="easyui-validatebox" type="password" name="password" data-options="required:true" />
                </td>
            </tr>
            <tr>
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">邮箱</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;height: 50px;text-align: center">
                    <input style="width: 200px;border: 4px #000 solid;font-size: 20px;text-align: center; font-weight: bold;font-family: '幼圆';border-radius: 20px;" class="easyui-validatebox" type="text" name="email" data-options="required:true" />
                </td>
            </tr>
            <tr>
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">年龄</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;height: 50px;text-align: center">
                    <input style="width: 200px;border: 4px #000 solid;font-size: 20px;text-align: center; font-weight: bold;font-family: '幼圆';border-radius: 20px;" class="easyui-validatebox" type="text" name="age" data-options="required:true" />
                </td>
            </tr>
            <tr>
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">部门</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;height: 50px;text-align: center">
                    <input id="employeeList" class="easyui-combobox" name="department.id"
                           data-options="valueField:'id',textField:'name',
                   url:'/department/list',panelHeight:'auto'"/>
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
