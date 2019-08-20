// console.log($("#global-header").attr("location"));
var currentPageArr = window.location.pathname.split(/\//);
var currentPage = currentPageArr.length > 0 ? currentPageArr[currentPageArr.length - 1] : 'http://www.shenfutech.com/assets/js/index.html';
// IE8支持indexOf
if (!Array.prototype.indexOf)
{
  Array.prototype.indexOf = function(elt /*, from*/)
  {
    var len = this.length >>> 0;
    var from = Number(arguments[1]) || 0;
    from = (from < 0)
          ? Math.ceil(from)
          : Math.floor(from);
    if (from < 0)
      from += len;
    for (; from < len; from++)
    {
      if (from in this &&
          this[from] === elt)
        return from;
    }
    return -1;
  };
}
var currentPageName = currentPage.substring(0, currentPage.indexOf('.html'));

var currentLocation = $("#global-header").attr("location");
var urlPrefix = '';
if ('index' == currentLocation) {
  urlPrefix = '';
} else {
  urlPrefix = '../';
}

var serviceArr = ['integration', 'outsourcing'];
var solutionArr = ['monitor', 'log', 'easypc', 'cmdb', 'workorder', 'wireless', 'mobileops'];
var aboutArr = ['company', 'contact'];

var isIndex = currentPageName == "index" ? "layui-this" : "";
var isService = serviceArr.indexOf(currentPageName) > -1 ? "layui-this" : "";
var isSolution = solutionArr.indexOf(currentPageName) > -1 ? "layui-this" : "";
var isAbout = aboutArr.indexOf(currentPageName) > -1 ? "layui-this" : "";

var menuService =
  '<dd><a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'service/integration.html">系统集成</a></dd>'+
  '<dd><a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'service/outsourcing.html">运维外包</a></dd>';
var menuSolution =
  '<dd><a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'solution/monitor.html">监控管理</a></dd>'+
  '<dd><a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'solution/log.html">日志管理</a></dd>'+
  '<dd><a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'solution/easypc.html">易助手</a></dd>'+
  '<dd><a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'solution/cmdb.html">IT资源管理</a></dd>'+
  '<dd><a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'solution/workorder.html">工单管理</a></dd>'+
  '<dd><a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'solution/wireless.html">无线云平台</a></dd>'+
  '<dd><a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'solution/mobileops.html">移动运维</a></dd>';
var menuAbout =
  '<dd><a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'about/company.html">公司介绍</a></dd>'+
  '<dd><a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'about/contact.html" >联系我们</a></dd>';
/* 添加header,避免重复代码 */
var $global_header = $(
  '<div class="global-header-wrapper layui-bg-black">'+
  '  <div class="global-header-content">'+
  '    <div class="global-header-logo">'+
  '      <a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'index.html"><img src="'+ urlPrefix +'assets/images/logo_light.png"/*tpa=http://www.shenfutech.com/assets/js/'+ urlPrefix +'assets/images/logo_light.png*//></a>'+
  '    </div>'+
  '    <div class="global-header-nav">'+
  '      <ul id="global-nav-slide" style="display: none;" class="layui-nav layui-nav-tree layui-nav-side phone">'+
  '        <li class="layui-nav-item '+isIndex+' "><a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'index.html">首页</a></li>'+
  '        <li class="layui-nav-item '+isService+' ">'+
  '          <a href="javascript:;">服务</a>'+
  '          <dl class="layui-nav-child">'+menuService+'</dl>'+
  '        </li>'+
  '        <li class="layui-nav-item '+isSolution+' ">'+
  '          <a href="javascript:;">解决方案</a>'+
  '          <dl class="layui-nav-child">'+menuSolution+'</dl>'+
  '        </li>'+
  '        <li class="layui-nav-item '+isAbout+' ">'+
  '          <a href="javascript:;">关于我们</a>'+
  '          <dl class="layui-nav-child">'+menuAbout+'</dl>'+
  '        </li>'+
  '      </ul>'+
  '      <ul class="layui-nav pc">'+
  '        <li class="layui-nav-item '+isIndex+' "><a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'index.html">首页</a></li>'+
  '        <li class="layui-nav-item '+isService+' ">'+
  '          <a href="javascript:;">服务</a>'+
  '          <dl class="layui-nav-child">'+menuService+'</dl>'+
  '        </li>'+
  '        <li class="layui-nav-item '+isSolution+' ">'+
  '          <a href="javascript:;">解决方案</a>'+
  '          <dl class="layui-nav-child">'+menuSolution+'</dl>'+
  '        </li>'+
  '        <li class="layui-nav-item '+isAbout+' ">'+
  '          <a href="javascript:;">关于我们</a>'+
  '          <dl class="layui-nav-child">'+menuAbout+'</dl>'+
  '        </li>'+
  '      </ul>'+
  '      <ul class="layui-nav phone">'+
  '        <li class="layui-nav-item">'+
  '          <a id="global-menu-phone" href="javascript:;">菜单</a>'+
  '        </li>'+
  '      </ul>'+
  '    </div>'+
  '    <div class="clear"></div>'+
  '  </div>'+
  '</div>'+
  '<div style="height: 60px;"></div>'
);
$("#global-header").append($global_header);
$("#global-menu-phone").on("click", function() {
  var slideStatus = $("#global-nav-slide").css("display");
  if ("none" == slideStatus) {
    $("#global-nav-slide").css("display", "block");
    $("#global-menu-phone").html("关闭");
  } else {
    $("#global-nav-slide").css("display", "none");
    $("#global-menu-phone").html("菜单");
  }
});
/* 添加header,避免重复代码 */
var $global_footer = $(
  '<div class="global-footer layui-bg-black">'+
  '  <div class="global-footer-content">'+
  '    <div class="global-footer-logo">'+
  '      <img src="'+ urlPrefix +'assets/images/logo_light.png" height="60" />'+
  '    </div>'+
  '    <div class="global-footer-nav">'+
  '      <div class="global-footer-nav-left">'+
  '        <ul class="ul-outer pc">'+
  '          <li class="li-outer">'+
  '            解决方案'+
  '            <dl>'+menuSolution+'</dl>'+
  '          </li>'+
  '          <li class="li-outer">'+
  '            服务'+
  '            <dl>'+menuService+'</dl>'+
  '          </li>'+
  '          <li class="li-outer">'+
  '            关于我们'+
  '            <dl>'+menuAbout+'</dl>'+
  '          </li>'+
  '        </ul>'+
  '        <ul class="ul-outer phone">'+
  '          <li class="li-outer">'+
  '            关于我们'+
  '            <dl>'+menuAbout+'</dl>'+
  '          </li>'+
  '        </ul>'+
  '      </div>'+
  '      <div class="global-footer-nav-right">'+
  '        <img id="qrcode" src="'+ urlPrefix +'assets/images/qrcode.png">'+
  '      </div>'+
  '    </div>'+
  '    <div class="clear"></div>'+
  '    <div class="global-footer-copyright">'+
  '      <div>Copyright © 2016 <a href="http://www.shenfutech.com/assets/js/'+ urlPrefix +'index.html">ShenFu Tech</a>. All Rights Reserved</div>'+
  '      <div class="beian"><a target="_blank" href="http://www.miibeian.gov.cn/">沪ICP备16025983号-1</a></div>'+
  '    </div>'+
  '  </div>'+
  '</div>'
);
$("#global-footer").append($global_footer);

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
        window.open("http://wpa.qq.com/msgrd?v=3&uin=1941145551&site=qq&menu=yes");
      }
    }
  });
});