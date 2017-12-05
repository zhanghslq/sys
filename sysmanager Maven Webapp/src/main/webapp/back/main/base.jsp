<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" import="java.util.*" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>大数据分析平台</title>
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/IconExtension.css">
    <link rel="stylesheet" href="/sysmanager/back/datepicker/assets/css/amazeui.min.css"/>
	<link rel="stylesheet" href="/sysmanager/back/datetimepicker-master/css/amazeui.datetimepicker.css"/>
	<link rel="stylesheet" href="/sysmanager/back/platform2/css/common.css" />
    <link rel="stylesheet" href="/sysmanager/back/platform2/css/index.css" />
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
    <script src="/sysmanager/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="/sysmanager/back/easyui/js/form.validator.rules.js"></script>
    <script src="/sysmanager/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="/sysmanager/back/echar/echarts.js"></script>
    <script src="/sysmanager/back/datetimepicker-master/js/amazeui.datetimepicker.js"></script>
</head>

<body>
    <div class="contentRight" id="contentRightHeight">
       <div class="rightDownSel">
           <ul class="tabNav">
               <li class="on" onclick='query("base/baseOil.jsp")'>油品营业额</li>
               <li onclick="query('shop/baseNotOil.jsp')">便利店营业额</li>
               <li onclick="query('shop/baseLube.jsp')">润滑油营业额</li>
               <li onclick="query('compare/baseCompare.jsp')">油品同比环比</li>
               <li onclick="query('compare/shopCompare.jsp')">便利店同比环比</li>
           </ul>
          <!--  <div class="rightDownMain" >
               <div class="downDetails" style="display: block;height: auto;width: auto">这是第一个内容区域
                   <iframe style="min-height: 1200px" src="base/baseOil.jsp"  class="introduceNav" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0" ></iframe>
               </div>
               <div class="downDetails" style="height: auto;width: auto">这里就是上面选择完成的内容 2
                   <iframe src="shop/baseNotOil.jsp" class="introduceNav" frameborder="0" scrolling="auto"  marginwidth="0" marginheight="0" ></iframe>
               </div>
               <div class="downDetails">
               <iframe src="shop/baseLube.jsp" class="introduceNav" frameborder="0" scrolling="auto"  marginwidth="0" marginheight="0" ></iframe>
				</div>第三个选项的内容
               <div class="downDetails">
               
               <iframe src="compare/baseCompare.jsp" class="introduceNav" frameborder="0" scrolling="auto"  marginwidth="0" marginheight="0" ></iframe>
               </div> 第四个选项的内容
               <div class="downDetails">
               	<iframe src="compare/shopCompare.jsp" class="introduceNav" frameborder="0" scrolling="auto"  marginwidth="0" marginheight="0" ></iframe>
               </div> 第四个选项的内容
           </div> -->
           <div id="content"></div>
       </div>
      
    </div>
		<script type="text/javascript">
			function query(url){
				$("#content").empty();
				$.ajax({
						url: url,//目标页面
						dataType: "html",
						type: "GET",
						cache: false,
						success: function(html){
							$("#content").html(html);
						}
					});
			}
		$(function(){
			query("base/baseOil.jsp");
	});
		</script>
    <!--右边 结束-->
</body>
<script type="text/javascript" src="/sysmanager/back/platform2/js/libs/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
<script type="text/javascript">
$(function(){
    $(window).resize(function(){
      navLeft(); 
    });
    //iframe高度
    $(window).load(function(){
        var cH = $("#iframepage").contents().find("#contentRightHeight").height();
        $("#iframepage").height(cH).attr("height",cH);
    }); 
});
</script>
</html>
