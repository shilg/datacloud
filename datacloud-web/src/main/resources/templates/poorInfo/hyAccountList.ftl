<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>行业账号</title>
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
                <span style="font-size: 15px;font-weight: bold">行业用户账号列表</span>
                <a class="layui-btn layui-btn-normal add_btn" >添加账号</a>
                <span style="font-size: 12px;">说明：分享登录地址(http://www.fupinkaifa.top/toLogin)给行业用户，行业用户登录后可进入行业用户首页，行业用户无权限进入此后台管理页面</span>
        </form>
    </blockquote>
    <table id="hyAccountList" lay-filter="hyAccountList"></table>

    <!--操作-->
    <script type="text/html" id="hyAccountBar">
        <a class="layui-btn layui-btn-xs " lay-event="edit">修改</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
<script type="text/javascript" src="/js/poorInfo/hyAccountList.js"></script>
</body>
</html>