
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
    form.on('submit(submit_account_btn)', function (data) {
        var formdata={};
        formdata.id = $("#id").val();
        formdata.userName = $("#userName").val();
        formdata.realName = $("#realName").val();
        formdata.password = $("#password").val();
        formdata.unit = $("#unit").val();
        $.ajax({
            url: '/telnant/addHyAccount',
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
                    layer.confirm('恭喜！添加成功！继续添加？', {
                        btn: ['继续','退出'] //按钮
                    }, function(){
                        $("#userName").val("");
                        $("#password").val("");
                        $("#confirmPassword").val("");
                        $("#realName").val("");
                        $("#unit").val("");
                        layer.closeAll();
                    }, function(){
                        parent.tableIns.reload({
                            page: {
                                curr: 1 //重新从第 1 页开始
                            }
                        });
                        parent.layer.closeAll();
                    });
                }else if(result.code == 202){
                    parent.tableIns.reload({
                        page: {
                            curr: 1 //重新从第 1 页开始
                        }
                    });
                    parent.layer.closeAll();
                    parent.layer.msg("修改成功！");
                }
            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.alert("抱歉！添加账号遇到了点问题，请稍后再试或联系客服qq：285374459", {icon: 5});
                return false;
            }
        });
        return false;
    })
function  closeWin() {
    parent.layer.closeAll()
}

});
