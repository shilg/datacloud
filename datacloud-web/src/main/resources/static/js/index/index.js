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
        $(".liveMsg").click(function(){
            var index = layui.layer.open({
                title : "客户留言",
                area : ["500px","370px"],
                type : 1,
                shade: 0.8,
                content : "<iframe style='width: 100%;height: 100%' id='liveMsgWin' src='/telnant/toLiveMsgEdit'></iframe>"
            })
        })
        $(".register").click(function(){
            var index = layui.layer.open({
                title : "快速注册",
                area : ["500px","400px"],
                type : 1,
                shade: 0.8,
                content : "<iframe style='width: 100%;height: 100%' id='liveMsgWin' src='/toRegister'></iframe>"
            })
        })
        $("#search_button").click(function(){
            var kayWords = $("#kayWordsId").val();
            if(kayWords.length<2){
                layer.msg("至少输入两位字符");
                return;
            }
            var src = "poorInfo/toSerchResult?kayWords="+kayWords;
            document.getElementById('search_result').src=src;
        })
        $(".onlineSearch").click(function(){
            document.getElementById("search_input").style.display="block";
            var src = "";
            document.getElementById('search_result').src=src;
            var h = document.documentElement.clientHeight || document.body.clientHeight;
            $("#search_result").css({"padding-top": "0px"});
            $("#search_result").height(h-146);
        })
        $(".excelComPar").click(function(){
            $("#search_result").height(h-60);
            document.getElementById("search_input").style.display="none";
            var src = "excelCompar/toexcelCompar";
            document.getElementById('search_result').src=src;
            $("#search_result").css({"padding-top": "60px"});
        })


    });

    layui.use(['element', 'util'], function(){
        var element = layui.element;
        var util = layui.util;
        //固定块
        util.fixbar({
            bar1: window.innerWidth < 480 ? false : '<i class="custom-icon" style="font-size: 28px;">&#xe613;</i>',
            bar2: false,
            css: {right: 30, bottom: 30},
            bgcolor: '#666',
            click: function(type){
                if(type === 'bar1'){
                    window.open("http://wpa.qq.com/msgrd?v=3&uin=285374459&site=qq&menu=yes");
                }
            }
        });
    });
