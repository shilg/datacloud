<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>扶贫Saas云平台</title>
    <meta name="keywords" content="扶贫数据云平台，专业扶贫业务处理,援力精准扶贫">
    <meta name="description" content="扶贫Saas云平台是全国首个Saas模式的扶贫云服务平台，支持国务院扶贫办数据的本级扩展应用，低成本高效率，省去项目采购风险，提升业务处理能力">
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/common.css" >
    <link rel="stylesheet" href="/css/markdown-style.css">
    <!--[if lt IE 9]>
  <script src="../assets/js/html5.min.js" ></script>
  <script src="../assets/js/respond.min.js" ></script>
    <![endif]-->
    <#include "common/import.ftl">
</head>
<body style="overflow: hidden">
<div id="global-header" location="index">
    <div class="global-header-wrapper layui-bg-blue">
        <div class="global-header-content">
            <div class="global-header-logo">
                扶贫数据查询比对平台
            </div>
            <div class="global-header-nav">
                <ul class="layui-nav pc">
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="onlineSearch"><i class="layui-icon" data-icon="&#xe612;">&#xe612;</i><cite>贫困信息在线查询</cite></a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="excelComPar"><i class="layui-icon" data-icon="&#xe630;">&#xe630;</i><cite>行业Excle比对</cite></a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="liveMsg"><i class="layui-icon" data-icon="&#xe6b2;">&#xe6b2;</i><cite>客户留言</cite></a>
                    </li>
                    <li class="layui-nav-item" id="userInfo">
                        <a href="javascript:;"><cite class="adminName">当前用户：${realName!''}</cite></a>
                        <dl class="layui-nav-child">
                        <#-- <dd><a href="javascript:;" data-url="/toUserInfo"><i class="seraph icon-ziliao" data-icon="icon-ziliao"></i><cite>个人资料</cite></a></dd>-->
                            <dd><a href="javascript:;" class="changePwd"><i class="seraph icon-xiugai" data-icon="icon-xiugai"></i><cite>修改密码</cite></a></dd>
                            <dd><a href="/logOut" class="signOut"><i class="seraph icon-tuichu"></i><cite>退出</cite></a></dd>
                        </dl>
                    </li>
                </ul>

            </div>
            <div class="clear"></div>  </div>
    </div>
</div>
<div class="layui-row" style="padding-top: 60px" id="search_input">
    <div class="layui-col-md3">&nbsp;</div>
    <div class="layui-col-md6 searchs_box">
                <input type="text" name="kayWords" id="kayWordsId"  placeholder="请输入贫困户姓名或身份证号查询" class="layui-input">
                <button class="layui-btn" id="search_button">查询</button>
    </div>
    <div class="layui-col-md3"> &nbsp;</div>
</div>
<iframe style="width: 100%" id="search_result" ></iframe>
<script src="/js/index/index.js" ></script>
<script>
    var h = document.documentElement.clientHeight || document.body.clientHeight;
    var iframeHeight = h-146;
    $("#search_result").height(iframeHeight);
    function poorInfDetail(id) {
        var index = layui.layer.open({
            title: "贫困户详情",
            type: 2,
            area: ["800px", "450px"],
            content: "/poorInfo/toPoorInfoEdit",
            success: function (layero, index) {
                $.ajax({
                    url: '/poorInfo/getPoorInfoById',
                    method: 'post',
                    data: {
                        id: id
                    },
                    //contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    success: function (result) {
                        var body = layui.layer.getChildFrame('body', index);
                        var edit = result.poorInfo;
                        if (edit) {
                            body.find(".xzqh").html(edit.b + edit.c + edit.d + edit.e);
                            body.find(".c").html(edit.c);
                            body.find(".d").html(edit.d);
                            body.find(".e").html(edit.e);
                            body.find(".f").html(edit.f);
                            body.find(".g").html(edit.g);

                            body.find(".h").html(edit.h);
                            body.find(".i").html(edit.i);
                            body.find(".j").html(edit.j);
                            body.find(".k").html(edit.k);
                            body.find(".l").html(edit.l);
                            body.find(".m").html(edit.m);
                            body.find(".n").html(edit.n);
                            body.find(".o").html(edit.o);
                            body.find(".p").html(edit.p);
                            body.find(".q").html(edit.q);
                            body.find(".r").html(edit.r + "月");
                            body.find(".s").html(edit.s);
                            body.find(".t").html(edit.t);
                            body.find(".u").html(edit.u);
                            body.find(".v").html(edit.v);
                            body.find(".w").html(edit.w);
                            body.find(".x").html(edit.x);
                            body.find(".y").html(edit.y);
                            body.find(".z").html(edit.z);
                            body.find(".aa").html(edit.aa + "元");
                            body.find(".ab").html(edit.ab);

                            body.find(".t").val(edit.t);
                            form.render();
                        }
                    }
                })
            }
        })
    }

</script>
</body>
</html>