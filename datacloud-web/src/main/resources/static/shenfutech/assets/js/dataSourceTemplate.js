// 数据展示页面tabbox部分的模板
var TB_DATA_TEMPLATE =  '{{#  layui.each(d.list, function(index, item){ }} '+
'{{#    if (index % 2 == 1) { }}'+
'  <section class="even">'+
'    <div class="section-main">'+
'      <div class="global-tabbox layui-container">'+
'        {{# if (item.img) { }}'+
'          <div class="global-tabbox-text layui-col-xs12 layui-col-sm7 layui-col-md7">'+
'        {{# } else { }}'+
'          <div class="global-tabbox-text layui-col-xs12 layui-col-sm12 layui-col-md12">'+
'        {{# } }}'+
'          {{# layui.each(item.text, function(index, data){ }}'+
'            {{# if (data.title) { }}'+
'              <div class="global-tabbox-title">{{ data.title }}</div>'+
'            {{# } }}'+
'            {{# if (data.content) { }}'+
'              <div class="global-tabbox-content">{{ data.content }}</div>'+
'            {{# } }}'+
'          {{# }); }}'+
'        </div>'+
'        {{# if (item.img) { }}'+
'          <div class="global-tabbox-img layui-col-xs12 layui-col-sm5 layui-col-md5">'+
'            <img src="../assets/images/{{ item.img }}" class="pc" >'+
'          </div>'+
'        {{# } }}'+
'      </div>'+
'    </div>'+
'    <div class="clear"></div>'+
'  </section>'+
'{{#    } else { }}'+
'  <section>'+
'    <div class="section-main">'+
'      <div class="global-tabbox layui-container">'+
'        {{# if (item.img) { }}'+
'          <div class="global-tabbox-img layui-col-xs12 layui-col-sm5 layui-col-md5">'+
'            <img src="../assets/images/{{ item.img }}" class="pc" >'+
'          </div>'+
'          <div class="global-tabbox-text layui-col-xs12 layui-col-sm7 layui-col-md7">'+
'        {{# } else { }}'+
'          <div class="global-tabbox-text layui-col-xs12 layui-col-sm12 layui-col-md12">'+
'        {{# } }}'+
'        {{# layui.each(item.text, function(index, data){ }}'+
'          {{# if (data.title) { }}'+
'            <div class="global-tabbox-title">{{ data.title }}</div>'+
'          {{# } }}'+
'          {{# if (data.content) { }}'+
'            <div class="global-tabbox-content">{{ data.content }}</div>'+
'          {{# } }}'+
'        {{# }); }}'+
'        </div>'+
'      </div>'+
'    </div>'+
'    <div class="clear"></div>'+
'  </section>'+
'{{#    } }}'+
'{{#  }); }} ';