var tableIns;
layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //列表
     tableIns = table.render({
        elem: '#hyAccountList',
        url : '/telnant/hyAccountList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 20,
        limits : [10,15,20,25],
        id : "hyAccountList",
        cols : [[
            {field: 'userName', title: '用户名',  align:'center'},
            {field: 'realName', title: '姓名', align:'center'},
            {field: 'createTime', title: '创建时间', align:'center'},
            {field: 'unit', title: '单位', align:'center'},
            {title: '操作', width:170, templet:'#hyAccountBar',fixed:"right",align:"center"}
        ]]
    });
    $(".add_btn").click(function(){
        addAccount();
    })
    //导入数据
    function addAccount(edit){
        var index = layui.layer.open({
            title : "添加账号",
            area : ["500px","450px"],
            type : 2,
            content : "toHyAccountEdit",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find("#id").val(edit.id);
                    body.find("#userName").val(edit.userName);
                    body.find("#realName").val(edit.realName);
                    body.find("#password").val(edit.password);
                    body.find("#confirmPassword").val(edit.password);
                    body.find("#unit").val(edit.unit);
                }
            }
        })
    }

    //列表操作
    table.on('tool(hyAccountList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){
            addAccount(data);
        }
        else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此行业用户？',{icon:3, title:'提示信息'},function(index){
                var formdata={};
                formdata.userName = data.userName;
                $.ajax({
                    url: '/telnant/delHyAccount',
                    method: 'post',
                    data: formdata,
                    // contentType:"application/json",
                    dataType: 'json',
                    success: function (result) {
                        if(result.code == 200){
                            tableIns.reload();
                            layer.closeAll();
                            layer.msg("删除成功！");
                        }
                    }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                        layer.alert("抱歉！删除失败！，请稍后再试或联系客服qq：285374459", {icon: 5});
                        return false;
                    }
                });
            });
        }
    });

})