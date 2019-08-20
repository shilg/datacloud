<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>导入Excel</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <#include "../common/import.ftl">
    <link rel="stylesheet" href="/css/edit.css" media="all" />
</head>
<body class="childrenBody">
<form class="layui-form layui-row layui-col-space10">

    <div class="layui-row" style="text-align: center;">

        <button type="button" class="layui-btn  layui-btn-normal" id="selectFile_button">
            <i class="layui-icon" >&#xe67c;</i>选择文件
        </button>

        <label   id="selectFileName"></label>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea  name="remark" id="remark" class="layui-textarea"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <button type="button" class="layui-btn layui-block" id="submitImport"> 提交 </button>
    </div>
</form>
<script type="text/javascript" src="/js/poorInfo/importDataEdit.js"></script>
</body>
</html>