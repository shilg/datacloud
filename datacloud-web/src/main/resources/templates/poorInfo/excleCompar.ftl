<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Excel比对</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <#include "../common/import.ftl">
    <link rel="stylesheet" href="/css/edit.css" media="all" />
</head>
<style type="text/css">
    .sp-grid-job{border-collapse: collapse;width:100%; border:1px solid #E1E6EB; border-left:none;}
    .sp-grid-job  td{padding: 10px;line-height:50px;font-size:16px;border-bottom:1px solid #E1E6EB; border-left:1px solid #E1E6EB;}
    .sp-grid-job  .title{text-align:right;}
    .sp-page-content{margin:0 auto;}
    .td-title{background-color: #f5f5f5;width: 20%}
    .content{width: 25%}
</style>

<body class="childrenBody">
<div class="layui-form layui-row layui-col-space10">

    <table class="sp-grid-job">
        <tr>
            <td class="title"><i class="seraph icon-look"></i>文件选择 </td>
            <td >
                <button type="button" class="layui-btn  " id="selectFile_button">
                <i class="layui-icon" >&#xe67c;</i>选择文件
                </button> &nbsp;&nbsp;<span id="selectFileName" style="color:red">未选择
             </td>
        </tr>
        <tr>
            <td class="title"><i class="seraph icon-look"></i>数据范围 </td>
            <td  >
                数据起始行:&nbsp;&nbsp;<input type="text" id="dataStartRow" lay-verify="startRow" autocomplete="off" class="layui-input" style="display: inline;width: 60px">&nbsp;&nbsp;
                身份证所在列:&nbsp;&nbsp;<input type="text" id="idcardCol"  lay-verify="alphabet" autocomplete="off" class="layui-input" style="display: inline;width: 60px" >&nbsp;&nbsp;
                &nbsp;&nbsp;<button class="layui-btn layui-btn-normal  layui-btn-sm layui-btn-radius"" onclick="demoExcel()">怎么填<i class="layui-icon">&#xe607</i></button>
                &nbsp;&nbsp;<button class="layui-btn layui-btn-sm layui-btn-warm" >试试智能识别</button>
            </td>
        </tr>
        <tr>
            <td class="title"><i class="seraph icon-look"></i>默认匹配规则 </td>
            <td >
                <input type="checkbox" lay-filter="switchTest1" name="role[candi]" readonly="readonly" title="残疾证匹配" checked>
                <input type="checkbox" lay-filter="switchTest2" name="role[shiwuwei]" readonly="readonly" title="15位身份证匹配" checked>
                <input type="checkbox" lay-filter="switchTest3" name="role[moweix]" readonly="readonly" title="身份证末尾X大小写匹配" checked>
            </td>
        </tr>
        <tr >
            <td style="text-align: center" colspan="2"><button class="layui-btn layui-btn layui-btn-normal"  onclick="verifyFileName()" id="submitImport" lay-submit><i class="layui-icon">&#xe609;</i>开始执行</button></td>
        </tr>

    </table>

		<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i> 执行跟踪</blockquote>
		<div class="border category" style="background-color: #2B2E37;height: 200px">
			<div class="" id="outPrint" style="color: white;margin-left: 20px;">
                <p>请先选择要比对的excle文件
			</div>
		</div>

	</div>
<script type="text/javascript" src="/js/poorInfo/excelCompar.js"></script>
</body>
</html>