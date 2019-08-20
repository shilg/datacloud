<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加行业账号</title>
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
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="userName" id="userName" lay-verify="userName" autocomplete="off" placeholder="请输入用户名" class="layui-input userName">
        </div>
    </div>
    <div class="layui-form-item">
        <input type="hidden" id="id" name="id">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="realName" id="realName"   autocomplete="off" placeholder="请输入姓名（选填）" class="layui-input realName">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="password" name="password"  id="password" lay-verify="password" autocomplete="off" placeholder="请输入输入密码" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-block">
            <input type="password" name="confirmPassword" id="confirmPassword" lay-verify="confirmPassword" autocomplete="off" placeholder="请确认密码" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单位</label>
        <div class="layui-input-block">
            <input type="text" name="unit" id="unit" lay-verify="required" autocomplete="off" placeholder="请输入单位" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <button type="button" class="layui-btn layui-block" lay-submit lay-filter="submit_account_btn" > 提交 </button>
    </div>
</form>
<script type="text/javascript" src="/js/poorInfo/hyAccountEdit.js"></script>
</body>
</html>