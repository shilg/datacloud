layui.use(['layer', 'form'], function() {
    var form = layui.form,
        $ = layui.jquery;

    $('#forgot').on('click', function() {
        layer.msg('请联系管理员.');
    });
    //自定义验证规则
  /*  form.verify({
        password: [/(.+){6,12}$/, '密码必须6到12位']
        ,userName: function(value){
            if(value.length < 1){
                return '用户名至少3位以上字符';
            }else if(value.length > 30){
                return '用户名不能超过30位';
            }
        }

    });
    //监听提交

    form.on('submit(login_check)', function (data) {
        submitForm(data);
    });*/
    $('#submit_button').on('click', function() {
        var user = {};
        user.userName = $('#userName').val();
        user.password = $('#password').val();
        if(user.userName.length<2||user.userName.length>30){
            layer.msg("用户名不正确");
            return false;
        }else if(user.password.length<6||user.password.length>30){
            layer.msg("密码长度不合法");
            return false;
        }
        $.ajax({
            url: '/loginCheck',
            method: 'post',
            data: user,
            // contentType:"application/json",
            dataType: 'json',
            success: function (result) {
                if (result.code == 2001) {
                    if (window.top!=null){
                        window.top.location.href="toMainPage";
                        return;
                    }else{
                        window.location.href="toMainPage";
                        return;
                    }

                }else if(result.code == 2002) {
                    if (window.top!=null){
                        window.top.location.href="toHyMainPage";
                        return;
                    }else{
                        window.location.href="toHyMainPage";
                        return;
                    }
                } if(result.code == 401) {
                    layer.alert("用户名或密码错误", {icon: 5});
                    return false;
                }else if(result.code == 403) {
                    layer.alert("您无权访问该系统，请确认您是否以租用该系统？或与客服人员联系", {icon: 5});
                    return false;
                }else{
                    layer.alert("登陆失败！请稍后再试，或联系客服人员确认问题", {icon: 5});
                    return false;
                }
            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.alert("登陆失败！请稍后再试，或联系客服人员确认问题", {icon: 5});
                return false;
            }
        });
    });
});