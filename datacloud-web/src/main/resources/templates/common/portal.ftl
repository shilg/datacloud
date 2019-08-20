<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>main</title>

    <link rel="stylesheet" href="/css/public.css" media="all" />
     <#include "../common/import.ftl">
</head>
<body class="childrenBody">
<blockquote class="layui-elem-quote layui-bg-green">
    <div id="nowTime"></div>
</blockquote>
<div class="layui-row layui-col-space10 panel_box">
    <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
        <a href="javascript:;" data-url="http://fly.layui.com/case/u/3198216" target="_blank">
            <div class="panel_icon layui-bg-green">
                <i class="layui-anim seraph icon-text"></i>
            </div>
            <div class="panel_word ">
                <span>${importDataNum}</span>
                <cite>导入数据总条数</cite>
            </div>
        </a>
    </div>
    <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg2">
        <a href="javascript:;" data-url="https://github.com/BrotherMa/layuicms2.0" target="_blank">
            <div class="panel_icon layui-bg-black">
                <i class="layui-anim seraph icon-icon10"></i>
            </div>
            <div class="panel_word userAll">
                <span>${hyUserNum}</span>
                <cite>行业用户总数</cite>
            </div>
        </a>
    </div>

    <script>
        //获取系统时间
        var newDate = '';
        getLangDate();
        //值小于10时，在前面补0
        function dateFilter(date){
            if(date < 10){return "0"+date;}
            return date;
        }
        function getLangDate(){
            var dateObj = new Date(); //表示当前系统时间的Date对象
            var year = dateObj.getFullYear(); //当前系统时间的完整年份值
            var month = dateObj.getMonth()+1; //当前系统时间的月份值
            var date = dateObj.getDate(); //当前系统时间的月份中的日
            var day = dateObj.getDay(); //当前系统时间中的星期值
            var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
            var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
            var hour = dateObj.getHours(); //当前系统时间的小时值
            var minute = dateObj.getMinutes(); //当前系统时间的分钟值
            var second = dateObj.getSeconds(); //当前系统时间的秒钟值
            var timeValue = "" +((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午" ); //当前时间属于上午、晚上还是下午
            newDate = dateFilter(year)+"年"+dateFilter(month)+"月"+dateFilter(date)+"日 "+" "+dateFilter(hour)+":"+dateFilter(minute)+":"+dateFilter(second);
            document.getElementById("nowTime").innerHTML = "欢迎使用扶贫云数据平台。当前时间为： "+newDate+"　"+week+"   您的系统使用期限至:【 ${endDate}】";
            setTimeout("getLangDate()",1000);
        }
    </script>
</body>
</html>