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
        <label class="layui-form-label">原始密码</label>
        <div class="layui-input-block">
            <input type="password" name="oldPassword" id="oldPassword"  placeholder="请输入原始密码"  lay-verify="oldPassword"  class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">设置新密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" id="password"  placeholder="请输入新密码"  lay-verify="password"  class="layui-input"/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认新密码</label>
        <div class="layui-input-block">
            <input  type="password"   name="confirmPassword" id="confirmPassword" placeholder="请确认新密码" lay-verify="confirmPassword"  class="layui-input"/>
        </div>
    </div>

    <div class="layui-form-item">
        <button type="button" class="layui-btn layui-block" lay-submit lay-filter="changePwd" > 确认修改 </button>
    </div>
</form>

<script type="text/javascript">
    layui.use(['form','layer'],function(){
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer;
        //自定义验证规则
        form.verify({
            oldPassword: [/(.+){6,40}$/, '密码至少6位']
            ,password: [/(.+){6,40}$/, '密码至少6位']
            ,confirmPassword: function(value){
                var password = $("#password").val();
                if(password!=value){
                    return '两次输入的密码不一致';
                }
            }

        });
        form.on('submit(changePwd)', function (data) {
            //var formdata = $(data.form).serialize();
            //var formdata = JSON.stringify(data.field)
            var formdata={};
            formdata.oldPassword = $("#oldPassword").val();
            formdata.password = $("#password").val();
            $.ajax({
                url: '/changePwd',
                method: 'post',
                data: formdata,
                // contentType:"application/json",
                dataType: 'json',
                success: function (result) {

                    if (result.code == 403) {
                        parent.layer.msg("原密码不正确，请重新输入", {icon: 5});
                        return false;
                    }

                    if (result.code == 200) {
                        layer.closeAll();
                        layer.msg('修改成功！',{icon: 1} );
                    }
                }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.alert("抱歉！修改密码遇到了点问题，请稍后再试或联系客服qq：285374459", {icon: 5});
                    return false;
                }
            });
            return false;
        })

    });


</script>
</body>
</html>