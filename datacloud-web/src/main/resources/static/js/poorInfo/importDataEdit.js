
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

    var uploadInst = upload.render({
        elem: '#selectFile_button' //绑定元素
        ,url: 'importDataByExcel' ,//上传接口
        auto: false //选择文件后不自动上传
        ,bindAction: '#submitImport' //指向一个按钮触发上传
        ,accept:'file'
        ,acceptMime: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel'
        ,choose: function(obj){
            //将每次选择的文件追加到文件队列
            var files = obj.pushFile();

            //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
            obj.preview(function(index, file, result){
                //console.log(index); //得到文件索引
                //console.log(file); //得到文件对象
               // console.log(result); //得到文件base64编码，比如图片
                $("#selectFileName").html(file.name);
                //这里还可以做一些 append 文件列表 DOM 的操作

                //obj.upload(index, file); //对上传失败的单个文件重新上传，一般在某个事件中使用
                //delete files[index]; //删除列表中对应的文件，一般在某个事件中使用
            });
        }
        ,data: {
            remark: function(){
                return $('#remark').val();
            }
        }
        ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
            layer.msg('开始加载文件！');
            layer.load(); //上传loading
        }
        ,done: function(res){
            //假设code=0代表上传成功
            if(res.code == 0){
                var importRecord = {};
                importRecord.importExcel = res.data.importExcel;
                importRecord.fileId = res.data.fileId;
                importRecord.remark = res.data.remark;
                layer.msg('文件导入系统成功！下一步进行数据解析导入！');
                $.ajax({
                    url: '/poorInfo/analyzeExcel',
                    method: 'post',
                    data:  importRecord,
                    //contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    success: function (result) {
                        parent.layer.closeAll();
                        if (result.code == 0) {
                            //关闭添加窗口 并刷新文章列表
                                parent.layer.closeAll();
                               // parent.table.reload("importRecordTable");
                                parent.tableIns.reload({
                                    page: {
                                        curr: 1 //重新从第 1 页开始
                                    }
                                });
                                parent.layer.msg("导入成功！", {icon: 1});
                        }else if(result.code == 0){
                            layer.alert("登陆超时或无权限！请重新登陆或联系管理员！", {icon: 5});
                        } else {
                            layer.alert("解析失败！请确保Excle文件为国办系统导出！或稍后再试，也可在【问题反馈或建议】模块提交您的问题", {icon: 5});
                            return false;
                        }
                    }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                        parent.layer.closeAll();
                        layer.alert("解析失败！请确保Excle文件为国办系统导出！或稍后再试，也可在【问题反馈或建议】模块提交您的问题", {icon: 5});
                        return false;
                    }
                });
            }

        }
        ,error: function(){
            //请求异常回调
            layer.closeAll('loading'); //关闭loading
        }
    });
    //日期
    laydate.render({
        elem: '#birthday'
    });
     laydate.render({
         elem: '#endDate'
     });

function  closeWin() {
    parent.layer.closeAll()
}

});
