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
    <title>采购订单管理</title>
    <%@include file="/WEB-INF/views/head.jsp"%>
    <!-- 编辑框支持 -->
    <script src="/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.getColumnInfo.js"></script>
    <script src="/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.editors.js"></script>
    <script src="/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.edit.cellEdit.js"></script>

    <script src="/js/model/purchasebill.js"></script>

    <link rel="stylesheet" type="text/css" href="/css/index_currency.css"/>
    <style>
        .datagrid-row-selected {
            background: #fed6e3;
        }
    </style>
</head>
<body>

<div id="toolbar">
    <a href="javascript:;" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
    <a href="javascript:;" data-method="update" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    <a href="javascript:;" data-method="delete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
</div>
<%-- 高级查询的表单--%>
<form id="searchForm" method="post" class="senior">
    交易时间:<input class="easyui-datebox" width="120px" name="begindate"/>
    - <input name="enddate" class="easyui-datebox" width="120px"/>
    状态:<select  class="easyui-combobox" name="status" style="width:120px;" panelHeight='auto'>
            <option value="">--请选择--</option>
            <option value="0">待审</option>
            <option value="1">已审</option>
            <option value="-1">作废</option>
         </select>

    <a href="#" class="easyui-linkbutton" inconCls="icon-search" data-method="search">查询</a>

</form>


<table id="tt" class="easyui-datagrid"
       url="/purchasebill/page" fitColumns="true"
       title="采购列表" iconCls="icon-man"
       rownumbers="true" pagination="true"
>
    <thead>
    <tr>
        <th data-options="field:'vdate',width:100">交易时间</th>
        <th data-options="field:'totalAmount',width:100">总金额</th>
        <th data-options="field:'totalNum',width:100">总数量</th>
        <th data-options="field:'status',width:100,formatter:statusFormatter">状态</th>
        <th data-options="field:'supplier',width:100,formatter:supplierFormatter">供应商</th>
        <th data-options="field:'buyer',width:100,formatter:buyerFormatter">采购员</th>
    </tr>
    </thead>
</table>
<%-- 添加和修改的弹出框--%>
<div id="editDialog" class="easyui-dialog" title="数据操作" style="padding: 20px; width:1000px;height:500px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,buttons:'#btns',closed:true">
    <form id="editForm" method="post">
        <table style="width: 400px;height: 300px;">
            <tr>
                <input id="purchasebillId" type="hidden" name="id">
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">交易时间</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;text-align: center">
                    <input  class="easyui-datebox" type="text" name="vdate" data-options="required:true" />
                </td>
            </tr>
            <tr>
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">供应商</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;text-align: center">
                    <input id="supplierList" class="easyui-combobox" name="supplier.id"
                           data-options="valueField:'id',textField:'name',
                   url:'/supplier/list'"/>
                </td>
            </tr>
            <tr>
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">采购员</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;text-align: center">
                    <input id="sbuyerList" class="easyui-combobox" name="buyer.id"
                           data-options="valueField:'id',textField:'username',
                   url:'/employee/findByDept'"/>
                </td>
            </tr>

        </table>

    <%-- 明细数据 --%>
    <table id="gridItems" title="明细数据编辑" style="width:100%;height:300px">
    </table>
    <%-- 明细数据的按钮 --%>
        <div id="itemBtns">
            <a href="javascript:;" id="btnInsert" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
            <a href="javascript:;" id="btnRemove" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">删除</a>
        </div>

    </form>
    <div id="btns">
        <a href="javascript:;" data-method="save" class="easyui-linkbutton c1" data-options="iconCls:'icon-ok'">确定</a>
        <a href="javascript:;" data-method="close" class="easyui-linkbutton c4" data-options="iconCls:'icon-cancel'">取消</a>
    </div>
</div>

</body>
</html>
