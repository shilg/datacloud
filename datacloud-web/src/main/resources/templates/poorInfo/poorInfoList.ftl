<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>已导入数据</title>
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
            &nbsp;&nbsp;&nbsp;
            <div class="layui-inline">
                姓名/证件号码：
                <div class="layui-input-inline">
                    <input type="text" class="layui-input searchVal" placeholder="请输入搜索的内容" />
                </div>
                <a class="layui-btn search_btn" data-type="reload">搜索</a>
            </div>
        </form>
    </blockquote>
    <table id="poorInfoList" lay-filter="poorInfoList"></table>
    <!--性别-->
    <script type="text/html" id="sexTempl">
        {{#  if(d.sex == "0"){ }}
        <span class="layui-blue">男</span>
        {{#  } else if(d.sex == "1"){ }}
        <span class="layui-blue">女</span>
        {{#  } else { }}
        --
        {{#  }}}
    </script>

    <!--操作-->
    <script type="text/html" id="poorInfoBar">
        <a class="layui-btn layui-btn-xs " lay-event="look">查看信息</a>
    </script>
<script type="text/javascript" src="/js/poorInfo/poorInfoList.js"></script>
</body>
</html>