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
    <title>产品类型管理</title>
    <%@include file="/WEB-INF/views/head.jsp"%>
    <script src="/js/model/product.js"></script>
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
    名称:<input class="easyui-textbox" width="80px" id="username" name="name"/>
    <a href="#" class="easyui-linkbutton" inconCls="icon-search" data-method="search">查询</a>
    <button id="btnPrint" class="easyui-linkbutton" inconCls="icon-search">打印</button>

</form>


<startprint>    <%-- 页面打印开始--%>
<table id="tt" class="easyui-datagrid"
       url="/product/page" fitColumns="true"
       title="字典明细" iconCls="icon-man"
       rownumbers="true" pagination="true"
>
    <thead>
    <tr>
        <th data-options="field:'name',width:100">产品名称</th>
        <th data-options="field:'color',width:100,formatter:colorFormat">颜色</th>
        <%--<th data-options="field:'pic',width:100,formatter:picFormat">图片</th>--%>
        <th data-options="field:'smallPic',width:100,formatter:smallPicFormat,onLoadSuccess:loadSuccess">图片</th>
        <th data-options="field:'costPrice',width:100">成本价格</th>
        <th data-options="field:'salePrice',width:100">销售价格</th>
        <th data-options="field:'productType',width:100,formatter:productTypeFormat">类型</th>
        <th data-options="field:'systemDictionarydetail1',width:100,formatter:systemDictionarydetail1">单位</th>
        <th data-options="field:'systemDictionarydetail',width:100,formatter:systemDictionarydetail">品牌</th>


    </tr>
    </thead>
</table>
</startprint>   <%--页面打印结束--%>
<%-- 添加和修改的弹出框--%>
<div id="editDialog" class="easyui-dialog" title="添加/修改" style="padding: 20px; width:600px;height:500px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,buttons:'#btns',closed:true">
    <form id="editForm" method="post">
        <table style="margin: auto;width: 400px;height: 300px;padding-top: 50px;">
            <tr>
                <input id="productId" type="hidden" name="id">
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">名称</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;height: 50px;text-align: center">
                    <input style="width: 200px;border: 4px #000 solid;font-size: 20px;text-align: center; font-weight: bold;font-family: '幼圆';border-radius: 20px;" class="easyui-validatebox" type="text" name="name" data-options="required:true" />
                </td>
            </tr>
            <tr>
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">颜色</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;height: 50px;text-align: center">
                    <input style="width: 200px;border: 4px #000 solid;font-size: 20px;text-align: center; font-weight: bold;font-family: '幼圆';border-radius: 20px;" class="easyui-validatebox" type="text" name="color" data-options="required:true" />
                </td>
            </tr>
            <tr>
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">成本价格</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;height: 50px;text-align: center">
                    <input style="width: 200px;border: 4px #000 solid;font-size: 20px;text-align: center; font-weight: bold;font-family: '幼圆';border-radius: 20px;" class="easyui-validatebox" type="text" name="costPrice" data-options="required:true" />
                </td>
            </tr>
            <tr>
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">销售价格</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;height: 50px;text-align: center">
                    <input style="width: 200px;border: 4px #000 solid;font-size: 20px;text-align: center; font-weight: bold;font-family: '幼圆';border-radius: 20px;" class="easyui-validatebox" type="text" name="salePrice" data-options="required:true" />
                </td>
            </tr>
            <tr>
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">类型编号</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;height: 50px;text-align: center">
                    <input id="productTypeList" class="easyui-combobox" name="productType.id"
                           data-options="valueField:'id',textField:'name',
                   url:'/productType/list',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">字典编号</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;height: 50px;text-align: center">
                    <input id="systemDictionarydetailList" class="easyui-combobox" name="systemDictionarydetail.id"
                           data-options="valueField:'id',textField:'name',
                   url:'/systemDictionarydetail/list',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td style="font-weight: bold;font-family: '华文中宋';font-size: 20px;width: 50px">字典编号</td>
                <td style="padding-left: 10px;border: 0px;width: 50px;height: 50px;text-align: center">
                    <input id="systemDictionarydetail1List" class="easyui-combobox" name="systemDictionarydetail1.id"
                           data-options="valueField:'id',textField:'name',
                   url:'/systemDictionarydetail/list',panelHeight:'auto'"/>
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
