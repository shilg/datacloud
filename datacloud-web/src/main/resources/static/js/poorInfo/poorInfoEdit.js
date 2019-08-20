
layui.use(['table', 'jquery', 'layer', 'form', 'laydate','flow', 'upload', 'element','util'], function () {
    var form = layui.form,
    table = layui.table,
    $ = layui.$,
    layer = layui.layer,
    flow = layui.flow,
    upload = layui.upload,
    element = layui.element,
    laydate = layui.laydate,
    util=layui.util;

    //日期
    laydate.render({
        elem: '#birthday'
    });
     laydate.render({
         elem: '#endDate'
     });


    //自定义验证规则
    form.verify({
        password: [/(.+){1,20}$/, '密码必须1到12位']
        ,name: function(value){
            if(value.length < 1){
                return '标题至少得5个字符啊';
            }else if(value.length > 20){
                return '姓名过长';
            }
        }
        ,birthday: function(value){
            var d1 = new Date(value.replace(/-/g, "\/"));
            var today = new Date();
            if (d1 > today) {
                return "生日不能晚于当前日期";
            }
        }
        ,qq: [/^[1-9]\d*|0$/,'请输入正确qq号']

    });

    //监听启用密码开关
    form.on('switch(switchIsUsePassword)', function(data){
        var checked = this.checked ? 'true' : 'false';
        $("#isUsePassword").val(checked);
        if(checked=="true"){
            $(".useassword").removeAttr("disabled");
        }else{
            $(".useassword").attr("disabled","disabled");
        }
        layer.tips('温馨提示：密码功能为会员验证身份功能，非必须项', data.othis,{
            tips: [1, '#3595CC'],
            time: 4000
        })
    });
    //监听启用过期日期开关
    form.on('switch(switchUseEndDate)', function(data){
        var checked = this.checked ? 'true' : 'false';
        $("#isNeverEnd").val(checked);
        layer.tips('温馨提示：超过过期日期后会员状态为失效，', data.othis )
    });

    //监听提交
    form.on('submit(add)', function (data) {
        submitMember(data,true);
    });
    function submitMember(data,flag){
        console.log(data.field);
        var formdata = $(data.form).serialize();
        console.log(formdata);

    $.ajax({
        url: '/poorInfo/add',
        method: 'post',
        data: formdata,
        // contentType:"application/json",
        dataType: 'json',
        success: function (result) {
            if (result.code == 0) {
                //关闭添加窗口 并刷新文章列表
                if(flag){
                    parent.layer.closeAll();
                    //parent.table.reload("memberListTable");
                    parent.tableIns.reload({
                         page: {
                            curr: 1 //重新从第 1 页开始
                        }
                    });
                    parent.layer.msg("操作成功！", {icon: 1});
                }
            } else {
                layer.alert("会员添加失败！系统错误~", {icon: 5});
                return false;
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            layer.alert("会员添加失败！请稍后再试~", {icon: 5});
            return false;
        }
    });
}
function  closeWin() {
    parent.layer.closeAll()
}

});
