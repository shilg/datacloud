<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>数据导入</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <#include "../common/import.ftl">
</head>
<body class="childrenBody">

    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                已导入数据列表&nbsp;&nbsp;&nbsp;
                <a class="layui-btn layui-btn-normal importdata_btn" >导入数据</a>
            </div>
            <span style="font-size: 12px;">国办系统数据导出位置为登入国办系统，找到"查询-》基础信息查询-》贫困户信息查询-》服务器端导出"</span>

        </form>
    </blockquote>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;margin-bottom: 3px">
            <legend>数据导入记录列表</legend>
        </fieldset>

    <table id="importRecordList" lay-filter="importRecordList"></table>


    <!--操作-->
    <script type="text/html" id="importRecordBar">
        <a class="layui-btn layui-btn-xs layui-btn-danger " lay-event="delete">删除此次导入</a>
    </script>
<script type="text/javascript" src="/js/poorInfo/importRecordList.js"></script>
</body>
</html>