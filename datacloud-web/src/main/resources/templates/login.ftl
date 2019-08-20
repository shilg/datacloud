<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登陆</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet" href="css/login.css">
    <#include "common/import.ftl">
</head>

<body>
  <div class="kit-login">
    <div class="kit-login-bg"></div>
    <div class="kit-login-wapper">
      <div class="kit-login-form">
        <h4 class="kit-login-title">登录</h4>
          <div class="kit-login-row">
            <div class="kit-login-col">
              <i class="layui-icon">&#xe612;</i>
              <span class="kit-login-input">
                <input type="text" name="userName" id="userName" lay-verify="userName" placeholder="用户名" />
              </span>
            </div>
            <div class="kit-login-col"></div>
          </div>
          <div class="kit-login-row">
            <div class="kit-login-col">
              <i class="layui-icon">&#xe64c;</i>
              <span class="kit-login-input">
                <input type="password" name="password" id="password" lay-verify="password" placeholder="密码" />
              </span>
            </div>
            <div class="kit-login-col"></div>
          </div>
          <div class="kit-login-row">
            <#--<div class="kit-login-col">
                <a href="javascript:;" style="color: rgb(153, 153, 153); text-decoration: none; font-size: 13px;" id="forgot">还没账号？立即注册</a>
             &lt;#&ndash; <input type="checkbox" name="rememberMe" title="记住帐号" lay-skin="primary">&ndash;&gt;
            </div>-->
          </div>
          <div class="kit-login-row">
            <button class="layui-btn kit-login-btn" id="submit_button">登录</button>
          </div>
          <#--<div class="kit-login-row" style="margin-bottom:0;">
            <a href="javascript:;" style="color: rgb(153, 153, 153); text-decoration: none; font-size: 13px;" id="forgot">忘记密码</a>
          </div>-->
      </div>
    </div>
  </div>
  <script type="text/javascript" src="/js/login.js"></script>
</body>
</html>