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
               <li class="on" onclick='query("base/baseOil.jsp")'>燃油销售整体情况</li>
               <li onclick="query('base/oilDetail.jsp')">燃油分油品销售情况</li>
               <li onclick="query('base/target.jsp')">T&R</li>
               <li onclick="query('base/productivity.jsp')">劳动生产率</li>
               <li onclick="query('compare/baseCompare.jsp')">油品同比环比</li>
               <li onclick="query('compare/shopCompare.jsp')">便利店同比环比</li>
           </ul>
          
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
</html>
