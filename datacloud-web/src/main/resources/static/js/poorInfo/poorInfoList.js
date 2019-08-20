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
        elem: '#poorInfoList',
        url : '/poorInfo/poorInfoList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limit : 20,
        limits : [10,15,20,25],
        id : "poorInfoTable",
        cols : [[
            {field: 'h', title: '姓名',  align:'center'},
            {field: 'i', title: '证件号码', align:'center',width:200},
            {field: 'k', title: '与户主关系', align:'center'},
            {field: 'l', title: '民族', align:'center'},
            {field: 't', title: '脱贫属性', align:'center'},
            {field: 'u', title: '脱贫年度', align:'center'},
            {field: 'v', title: '贫困户属性', align:'center'},
            {field: 'w', title: '主要致贫原因', align:'center'},
            {title: '操作', width:170, templet:'#poorInfoBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        var key = $(".searchVal").val();
        if(key != ''){
           /* if(typeof(key)=="Number"&&key.length<5){
                layer.msg("身份证查找，至少");
            }*/

        }
        table.reload("poorInfoTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                key: key  //搜索的关键字
            }
        })
    });



    //详情
    function poorDetal(edit){
        var index = layui.layer.open({
            title : "贫困户详情",
            type : 2,
            content : "toPoorInfoEdit",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".xzqh").html(edit.b+edit.c+edit.d+edit.e);
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
                    body.find(".r").html(edit.r+"月");
                    body.find(".s").html(edit.s);
                    body.find(".t").html(edit.t);
                    body.find(".u").html(edit.u);
                    body.find(".v").html(edit.v);
                    body.find(".w").html(edit.w);
                    body.find(".x").html(edit.x);
                    body.find(".y").html(edit.y);
                    body.find(".z").html(edit.z);
                    body.find(".aa").html(edit.aa+"元");
                    body.find(".ab").html(edit.ab);

                    body.find(".t").val(edit.t);
                    form.render();
                }
                /*  setTimeout(function(){
                      layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                          tips: 3
                      });
                  },500)*/
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
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
    table.on('tool(poorInfoList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'look'){ //查看
            poorDetal(data);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此会员？',{icon:3, title:'提示信息'},function(index){
                // $.get("删除文章接口",{
                //     Id : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            });
        } else if(layEvent === 'look'){ //预览
            layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        }
    });

})