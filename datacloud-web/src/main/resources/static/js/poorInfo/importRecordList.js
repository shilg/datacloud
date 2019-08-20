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
        elem: '#importRecordList',
        url : '/poorInfo/importRecordList',
        cellMinWidth : 95,
        page : true,
        height : "full-165",
        limit : 20,
        limits : [10,15,20,25],
        id : "importRecordTable",
        cols : [[
            {field: 'importDate', title: '数据导入时间',  align:'center',width:200,templet:function(d){
                    var  da = new Date(d.importDate);
                    var year = da.getFullYear()+'年';
                    var month = da.getMonth()+1+'月';
                    var date = da.getDate()+'日';
                    var dateStr = [year,month,date].join('-');
                    return dateStr;
                }
                },
            {field: 'importExcel', title: 'excel文件', align:'center',width:200},
            {field: 'importNum', title: '导入总数', align:'center',width:200},
            {field: 'remark', title: '备注信息', align:'center'},
            {title: '操作', width:200, templet:'#importRecordBar',fixed:"right",align:"center"}
        ]]
    });


    $(".importdata_btn").click(function(){
        importdata();
    })
    //导入数据
    function importdata(edit){
        var index = layui.layer.open({
            title : "导入数据",
            area : ["400px","300px"],
            type : 2,
            content : "toImportDataEdit"
        })
        //layui.layer.full(index);

    }


/*
    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('ListTable'),
            data = checkStatus.data,
            Id = [];
        if(data.length > 0) {
            for (var i in data) {
                Id.push(data[i].Id);
            }
            layer.confirm('确定删除选中的会员？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     Id : Id  //将需要删除的Id作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        }else{
            layer.msg("请选择需要删除的文章");
        }
    })*/

    //列表操作
    table.on('tool(importRecordList)', function(obj){
        var layEvent = obj.event,
            fileId = obj.data.fileId;
        if(layEvent === 'delete'){ //删除
            layer.confirm('删除此次导入记录，对应的导入数据也同时会删除，确定删除？',{icon:3, title:'提示信息'},function(index){
                layer.load();
                $.ajax({
                    url: '/poorInfo/deleteImportRecord',
                    method: 'post',
                    data:  {
                        fileId:fileId
                    },
                    //contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    success: function (result) {
                        layer.closeAll();
                        if (result.code == 0) {
                            //关闭添加窗口 并刷新文章列表
                            layer.closeAll();
                            // parent.table.reload("importRecordTable");
                            tableIns.reload({
                                page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                            parent.layer.msg("删除成功！", {icon: 1});
                        }else if(result.code == 401){
                            layer.alert("登陆超时或无权限！请重新登陆或联系管理员！", {icon: 5});
                        }else if(result.code == 500){
                            layer.alert("参数错误！，也可联系qq285374459咨询", {icon: 5});
                        } else {
                            layer.alert("删除失败！请稍后再试，也可联系qq285374459咨询", {icon: 5});
                            return false;
                        }
                    }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                        layer.closeAll();
                        layer.alert("删除失败！请稍后再试，也可联系qq285374459咨询", {icon: 5});
                        return false;
                    }
                });
                })
        }
    });

})