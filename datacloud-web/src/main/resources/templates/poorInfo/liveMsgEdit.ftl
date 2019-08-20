<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>客户留言</title>
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
        <label class="layui-form-label">联系类型</label>
        <div class="layui-input-block">
            <input type="radio" name="type" value="qq" title="QQ" checked="">
            <input type="radio" name="type" value="email" title="邮箱">
            <input type="radio" name="type" value="phone" title="手机">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">联系方式</label>
        <div class="layui-input-block">
            <input  name="contact" id="contact" class="layui-input"></input>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">留言内容</label>
        <div class="layui-input-block">
            <textarea  name="message" id="message" class="layui-textarea"></textarea>
        </div>
    </div>
</form>
<div class="layui-form-item">
    <button type="button" class="layui-btn layui-block" lay-submit lay-filter="add" > 提交 </button>
</div>
<script type="text/javascript">
    layui.use(['form','layer'],function(){
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer;
        form.on('submit(add)', function (data) {
            var formdata={};
            formdata.type=$('input:radio[name=type]:checked').val();
            formdata.contact=$("#contact").val();
            formdata.message=$("#message").val();
            if(formdata.contact.length<1){
                layer.msg("请留下您的联系方式以便需要时我们向您了解问题");
                return;
            }else if(formdata.message.length<1){
                layer.msg("请输入您的留言");
                return;
            }
            $.ajax({
                url: '/telnant/saveLiveMsg',
                method: 'post',
                data: formdata,
                // contentType:"application/json",
                dataType: 'json',
                success: function (result) {
                    parent.layer.closeAll();
                    if (result.code == 200) {
                        parent.layer.msg("留言成功！感谢您的支持！", {icon: 1});
                    }
                }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.alert("抱歉！留言未能成功，请稍后再试或联系客服qq：285374459", {icon: 5});
                    return false;
                }
            });
        })
    });


</script>
</body>
</html>