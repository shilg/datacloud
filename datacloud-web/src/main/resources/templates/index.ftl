<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>扶贫Saas云平台</title>
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/common.css" >
    <link rel="stylesheet" href="/css/solution.css" >
    <link rel="stylesheet" href="/css/markdown-style.css">
    <!--[if lt IE 9]>
  <script src="../assets/js/html5.min.js" ></script>
  <script src="../assets/js/respond.min.js" ></script>
    <![endif]-->
    <#include "common/import.ftl">
</head>
<body>
<div id="global-header" location="index">
    <div class="global-header-wrapper layui-bg-black">
        <div class="global-header-content">
            <div class="global-header-logo">
                <#--<a href="index.html"><img src="/images/logo_light.png"></a>-->
            </div>
            <div class="global-header-nav">
                <ul class="layui-nav pc">
                    <li class="layui-nav-item">
                        <a href="/toIndex">数据平台介绍</a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="/czzn.pdf" target="view_frame">扶贫办人员操作指南</a>
                    </li>
                    <#--<li class="layui-nav-item">
                        <a href="">行业数据集成方案</span></a>
                    </li>-->
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="liveMsg"><i class="layui-icon" data-icon="&#xe6b2;">&#xe6b2;</i><cite>客户留言</cite></a>
                    </li>
                    <#if isLogin ==true>
                    <li class="layui-nav-item" style="margin-right:4px;margin-left:4px">
                        <a href="/toMainPage" class="layui-btn layui-btn-normal layui-btn-sm">后台管理</a>
                    </li>

                    <li class="layui-nav-item" style="margin-right:4px;margin-left:4px">
                        <a href="/toHyMainPage" class="layui-btn layui-btn-normal layui-btn-sm">行业人员首页</a>
                    </li>
                    <#else>
                    <li class="layui-nav-item" style="margin-right:4px;margin-left:4px">
                        <a href="/toLogin" class="layui-btn layui-btn-normal layui-btn-sm">登录</a>
                    </li>
                    <li class="layui-nav-item" style="margin-right:4px">
                        <a href="javascript:;"   class="layui-btn layui-btn-normal layui-btn-sm register">注册</a>
                    </li>
                    </#if>
                </ul>

            </div>
            <div class="clear"></div>  </div>
    </div><div style="height: 60px;">
</div>
</div>


<div class="solution-banner solution-banner-cmdb">
    <div class="layui-container">
        <div class="layui-row">
            <div class="layui-col-xs8 layui-col-sm8 layui-col-md8">
                <div class="solution-banner-title">扶贫数据云平台</div>
                <div class="solution-banner-content">整合扶贫数据资源，实现扶贫数据在市、县本级横向扩展应用</div>
                <div class="solution-banner-button">
                    <button  class="layui-btn register">立即注册</button>
                </div>
            </div>
            <div class="solution-banner-img layui-col-xs4 layui-col-sm4 layui-col-md4"><img src="/images/banner-img-cmdb.png" ></div>
        </div>
    </div>
</div>
<div class="global-content">
    <section>
        <div class="section-main">
            <div class="global-tabbox layui-container">
                <div class="global-tabbox-img layui-col-xs12 layui-col-sm5 layui-col-md5">
                    <img src="/images/cmdb-1.png"   class="pc" >
                </div>
                <div class="global-tabbox-text layui-col-xs12 layui-col-sm7 layui-col-md7">
                    <div class="global-tabbox-title">国办数据整合</div>
                    <div class="global-tabbox-content">
                        <p>实现国办系统导出的Excle数据一键导入，形成标准基础扶贫数据，并在此基础数据上进行本级横向扩展</p>
                    </div>

                </div>
            </div>
        </div>
    </section>
    <section class="even">
        <div class="section-main">
            <div class="global-tabbox layui-container">
                <div class="global-tabbox-text layui-col-xs12 layui-col-sm7 layui-col-md7">
                    <div class="global-tabbox-title">行业部门用户本级管理</div>
                    <div class="global-tabbox-content">
                        <p>可对行业部门进行账号分配管理，实现本级行业部门在线扶贫数据查询、Excle比对等功能，是国办扶贫垂直系统的本级横向扩展补充</p>
                    </div>
                    <div class="global-tabbox-title">行业Excle数据比对</div>
                    <div class="global-tabbox-content">
                        <p>实现本级行业部门数据与扶贫数据自动比对筛选</p>
                    </div>

                </div>
                <div class="global-tabbox-img layui-col-xs12 layui-col-sm5 layui-col-md5">
                    <img src="/images/bidui.jpg"   class="pc" >
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </section>
    <section>
        <div class="section-main">
            <div class="global-tabbox layui-container">
                <div class="global-tabbox-img layui-col-xs12 layui-col-sm5 layui-col-md5">
                    <img src="/images/integration-2.png"   class="pc" >
                </div>
                <div class="global-tabbox-text layui-col-xs12 layui-col-sm7 layui-col-md7">
                    <div class="global-tabbox-title">极高的数据安全性</div>
                    <div class="global-tabbox-content">
                        <p>与国内领先的阿里云Paas平台合作，并利用非对称加密技术存储数据，您的数据只有通过您的账号密码和系统自动分配给您的秘钥才可解密调取</p>
                    </div>
                    <div class="global-tabbox-title">持续集成</div>
                    <div class="global-tabbox-content">
                        <p>利用Saas模式的优势，根据用户需求，在云端不断完善优化以及开发新的符合业务场景的通用化功能，助力您高效便捷的开展扶贫工作</p>
                    </div>

                </div>
            </div>
        </div>
    </section>
    <div class="clear"></div>
</div>
<div id="global-footer"></div>
<script src="/js/index/index.js" ></script>
<script>
    $(document).ready(function(){
        $("#btn-try").on("click", function() {
            window.location.href = "/toRegister";
        });
    });
</script>
</body>
</html>