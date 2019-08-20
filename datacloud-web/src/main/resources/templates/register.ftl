<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <#include "common/import.ftl">
    <link rel="stylesheet" href="/css/edit.css" media="all" />
</head>
<body class="childrenBody">
<form class="layui-form layui-row layui-col-space10">

    <div class="layui-form-item">
        <label class="layui-form-label">设置登陆名</label>
        <div class="layui-input-block">
            <input type="text" name="userName" id="userName" placeholder="请输入登录名"  lay-verify="userName"  class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">设置密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" id="password"  placeholder="请输入密码"  lay-verify="password"  class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-block">
            <input  type="password"   name="confirmPassword" id="confirmPassword" placeholder="请确认密码" lay-verify="confirmPassword"  class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text"  required  name="email"  id="email" placeholder="请输入邮箱" lay-verify="email" class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <button type="button" class="layui-btn layui-block" lay-submit lay-filter="register" > 注册 </button>
    </div>
</form>

<script type="text/javascript">
    layui.use(['form','layer'],function(){
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer;
        //自定义验证规则
        form.verify({
            userName: function(value){
                if(value.length < 2){
                    return '用户名至少得2个字符';
                }else if(value.length > 20){
                    return '用户名过长';
                }
            }
            ,password: [/(.+){6,40}$/, '密码至少6位']
            ,confirmPassword: function(value){
                var password = $("#password").val();
                if(password!=value){
                    return '两次输入的密码不一致';
                }
            }

        });
        form.on('submit(register)', function (data) {
            //var formdata = $(data.form).serialize();
            //var formdata = JSON.stringify(data.field)
            var formdata={};
            formdata.userName = $("#userName").val();
            formdata.password = $("#password").val();
            formdata.email = $("#email").val();
            $.ajax({
                url: '/register',
                method: 'post',
                data: formdata,
                // contentType:"application/json",
                dataType: 'json',
                success: function (result) {

                    if (result.code == 403) {
                        parent.layer.msg("用户名已存在，请重新输入", {icon: 5});
                        return false;
                    }

                    if (result.code == 200) {
                        layer.msg('恭喜您注册成功！', {
                            time: 0 //不自动关闭
                            ,btn: ['立即登录', '稍后登录']
                            ,yes: function(index){
                                parent.window.location.href="toLogin";
                            }
                        });
                    }
                }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.alert("抱歉！注册遇到了点问题，请稍后再试或联系客服qq：285374459", {icon: 5});
                    return false;
                }
            });
            return false;
        })

    });


</script>
</body>
</html>