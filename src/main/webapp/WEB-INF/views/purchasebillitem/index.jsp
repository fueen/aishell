<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/10/12
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--引入公共的jsp--%>
    <%@ include file="/WEB-INF/views/head.jsp" %>
    <!-- 引入datagrid-view的支持 -->
    <script type="text/javascript" src="/easyui/plugins/datagrid-groupview.js"></script>
    <%--引入当前模板对应的js--%>
    <script src="/js/model/purchasebillitem.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/index_currency.css"/>
</head>
<body>
<div id="toolbar">
    <%--
        高级查询表单
        思路: 当你点击查询按键的时候，应该通过Ajax请求把用户名与邮件传到后台
              后台接收数据进行查询,刷新datagrid页面 【刷新datagrid的时候把数据传过来】
    --%>
    <form id="searchForm" method="post">
        交易时间: <input name="beginDate" class="easyui-datebox" style="width:120px"> -
        <input name="endDate" class="easyui-datebox" style="width:120px">
        状态:<select  class="easyui-combobox" name="status" style="width:100px;" panelHeight='auto'>
        <option value="">--请选择--</option>
        <option value="0">待审</option>
        <option value="1">已审</option>
        <option value="-1">作废</option>
    </select>
        分组依据:<select  class="easyui-combobox" name="groupBy" style="width:100px;" panelHeight='auto'>
        <option value="1">供应商</option>
        <option value="2">采购人</option>
        <option value="3">月份</option>
    </select>
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>
</div>
<%--展示数据使用datagrid组件
    url:请求路径(数据)  fit:自适应父容器
    fitColumns:列的自适应 singleSelect:是否单选
    pagination:分页工具栏
    ctrl+shift+delete
--%>

<table id="purchaseBillItemGrid"></table>

<%--添加或者修改的弹出框--%>
<div id="editDialog" class="easyui-dialog" title="数据操作" style="padding: 25px; "
     data-options="iconCls:'icon-save',resizable:true,modal:true,buttons:'#btns',closed:true">
    <form id="editForm" method="post">
        <input id="purchaseBillItemId" type="hidden" name="id" />
        <table>
            <tr>
                <td>名称</td>
                <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
            </tr>
            <tr>
                <td>编码</td>
                <td><input class="easyui-validatebox" type="text" name="sn" data-options="required:true" /></td>
            </tr>
            <tr>
                <td>路径</td>
                <td><input class="easyui-validatebox" type="text" name="url" data-options="required:true" /></td>
            </tr>
            <tr>
                <td>描述</td>
                <td><input class="easyui-validatebox" type="text" name="descs" data-options="required:true" /></td>
            </tr>

        </table>
    </form>
    <div id="btns">
        <a href="javascript:;" data-method="save" class="easyui-linkbutton c2" data-options="iconCls:'icon-ok'">确定</a>
        <a href="javascript:;" data-method="close" class="easyui-linkbutton c5" data-options="iconCls:'icon-cancel'">取消</a>
    </div>
</div>


</body>
</html>
