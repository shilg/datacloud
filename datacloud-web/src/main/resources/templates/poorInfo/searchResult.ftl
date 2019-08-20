<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
</head>
<#include "../common/import.ftl">
<style>
    em{font-style:normal;}
    li{list-style:none; padding:0; margin:0;}
    a{text-decoration:none; color:#000000; padding:0; margin:0;}
    .clear{zoom:1;}
    .clear:after{content:"";display:block;clear:both;}

    .wrap{ width:100%; height:100%;  min-width:1280px; }
    .list{ width:800px; height:auto; margin:5px auto;}
    .list ul{ width:800px; float:left; margin-bottom:100px;}
    .list ul li{ display:inline-block; float:left; position:relative; margin-right:40px;}
    .list ul li input{ width:500px; border:1px solid #cc2a1e; background:none; height:40px; padding-left:100px; font-size:20px; color:#cc2a1e;}
    .list ul li img{ position:absolute; top:6px; left:10px; width:28px; height:28px; }
    .list ul .cxbtn{ width:100px; height:40px; line-height:40px; }
    .list ul .cxbtn a{ width:100px; height:40px; line-height:40px; background:#cc2a1e; display:inline-block; font-size:20px; color:#fff; text-align:center; }
    .list ul .cxbtn a:hover{opacity:0.6;filter:alpha(opacity=60)}

    .yhxx{ width:800px; float:left;}
    .tx1{ width:770px; height:150px; border:1px solid #e2e2e2; padding:15px 15px 15px 15px; }
    .tx1:hover{ border:1px solid #999999;  -webkit-box-shadow:0 0 10px #666666;  -moz-box-shadow:0 0 10px #666666;  box-shadow:0 0 10px #666666;transition: all 0.5s;}
    .tx1 .tab1{ width:770px; border:none;}
    .tx1 .tab1 td{ height:46px; line-height:46px; text-align:left; padding-left:5px; font-size:16px; color:#000;}
    .tx1 .tab1 td a{ font-size:20px; font-weight:bold;}
    .tx1 .tab1 td a:hover{ color:#cc2a1e;}
    .tx1 .tab1 .dz{ color:#666666;}
</style>
<body>
<div class="wrap">
    <div class="list">

        <div class="yhxx" >
            ${html}
        </div>
    </div>
</div>
<script type="application/javascript">
    var layer;
    layui.use(['form','layer'],function() {
        var form = layui.form,
                layer = parent.layer === undefined ? layui.layer : top.layer;
    })

</script>
</body>
</html>
