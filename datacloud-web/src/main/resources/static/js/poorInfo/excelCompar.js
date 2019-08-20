layui.use(['form','layer','layedit','laydate','upload'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    //上传excel
    var uploadInst = upload.render({
        elem: '#selectFile_button' //绑定元素
        ,url: '/excelCompar/excleUpload' ,//上传接口
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
                $("#selectFileName").css("color","black");
                $('#outPrint').append("<p>已选择文件"+file.name);
                //这里还可以做一些 append 文件列表 DOM 的操作

                //obj.upload(index, file); //对上传失败的单个文件重新上传，一般在某个事件中使用
                //delete files[index]; //删除列表中对应的文件，一般在某个事件中使用
            });
        }
        ,data: {
            idcardCol: function(){
                return $('#idcardCol').val();
            },
            dataStartRow: function(){
                return $('#dataStartRow').val();
            }
        }
        ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
            layer.load(); //上传loading
            $('#outPrint').append("<p>开始执行.......");
        }
        ,done: function(res){
            layer.closeAll('loading'); //关闭loading
            //假设code=0代表上传成功
            if(res.code == 0){
                layer.load();
                var importRecord = {};
                importRecord.fileId = res.fileId;
                importRecord.startRowNum = res.startRowNum;
                importRecord.idcardColNum = res.idcardColNum;
                importRecord.fileName = res.fileName;
                importRecord.suffix = res.suffix;
                layer.msg('文件导入系统成功！下一步进行数据解析导入！');
                $('#outPrint').append("<p>文件导入系统成功！")
                $('#outPrint').append("<p>开始进行数据解析比对！")
                $.ajax({
                    url: '/excelCompar/analyzeExcel',
                    method: 'post',
                    data:  importRecord,
                    //contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    success: function (result) {
                        parent.layer.closeAll();
                        if (result.code == 0) {
                            $('#outPrint').append("<p>比对解析完成");
                            var downloadurl ="/excelCompar/downloadResult?fileId="+result.fileId+"&fileName="+result.fileName+"&suffix="+result.suffix;
                            $('#outPrint').append("<a style='color: orange;font-size: 15px' href="+downloadurl+">可点此下载"+result.fileName+"比对结果</a>");
                            parent.layer.open({
                                type: 0 //Page层类型
                                ,area: ['400px', '200px']
                                ,title: '恭喜！比对完成！'
                                ,shade: 0.6 //遮罩透明度
                                ,skin: 'layui-layer-molv'
                                ,closeBtn: 1
                                ,btn: ['关闭']
                                ,yes: function(index, layero){
                                    layer.close(index);
                                }
                                ,maxmin: false//允许全屏最小化
                                ,anim: 1 //0-6的动画形式，-1不开启
                                ,content: '<div style="padding: 10px;"><a style="color:olivedrab;font-size: 15px" href='+downloadurl+'>点此下载'+result.fileName+'比对结果</a><div>'
                            });

                        }else if(result.code == 0){
                            layer.alert("登陆超时或无权限！请重新登陆或联系管理员！", {icon: 5});
                        }else if(result.code == 500){
                            layer.alert("未找到文件！请稍后再试，也可在【问题反馈或建议】模块提交您的问题", {icon: 5});
                        }else if(result.code == 999996){
                            layer.alert("身份证所在列或数据起始行有误！请检查后再试，也可在【问题反馈或建议】模块提交您的问题", {icon: 5});
                        }else {
                            layer.alert("解析失败！请稍后再试，也可在【问题反馈或建议】模块提交您的问题", {icon: 5});
                            return false;
                        }
                    }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                        parent.layer.closeAll();
                        layer.alert("解析失败！请稍后再试，也可在【问题反馈或建议】模块提交您的问题", {icon: 5});
                        return false;
                    }
                });
            }else if(res.code == 501){
                //未选择数据起始行和idcardcol
            }

        }
        ,error: function(){
            //请求异常回调
            layer.closeAll('loading'); //关闭loading
        }
    });

    form.on('checkbox(switchTest1)', function(data){
        layer.msg('温馨提示：此规则选中数据才更准确，暂不支持取消');
        this.checked = true;
        form.render();
    });
    form.on('checkbox(switchTest2)', function(data){
        layer.msg('温馨提示：此规则选中数据才更准确，暂不支持取消');
        this.checked = true;
        form.render();
    });
    form.on('checkbox(switchTest3)', function(data){
        layer.msg('温馨提示：此规则选中数据才更准确，暂不支持取消');
        this.checked = true;
        form.render();
    });
    form.verify({
        alphabet:function (value) {
            var regIdcardColNum=/^[a-zA-Z]+$/;
            if(!regIdcardColNum.test(value)){
                return '所在列为身份证对应的列名，请查看帮助【怎么填？】';
            }
        },
        startRow:function (value) {
            var regDataStartRow=/^[0-9]+$/;
            value=parseInt(value);
            if(!regDataStartRow.test(value)){
                return '数据起始行为除表头外要导入的数据在Excle中的行号，请查看帮助【怎么填？】';
            }
        }
    });
})
    function demoExcel(){
        layer.ready(function(){
            layer.open({
                type: 1,
                title: '填写示例',
                shadeClose: true,
                shade: 0,
                offset: 'c',
                area: ['710px', '300px'],
                content: '<img src="/images/Excel.jpg" />'
            });
        });
    }
    function verifyFileName() {

        var fielName = $("#selectFileName").html();
        if(fielName.size<1||fielName=="未选择"){
            layer.msg("未选择要比对的excel文件");
        }
        return;
    }