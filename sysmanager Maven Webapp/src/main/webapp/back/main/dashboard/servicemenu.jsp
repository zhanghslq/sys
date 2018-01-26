<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" import="java.util.*" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>大数据分析平台</title>
    <link rel="stylesheet" href="/sysmanager/back/platform2/css/common.css"/>
    <link rel="stylesheet" href="/sysmanager/back/platform2/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/IconExtension.css">
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
    <script src="/sysmanager/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="/sysmanager/back/easyui/js/form.validator.rules.js"></script>
    <script src="/sysmanager/back/easyui/js/easyui-lang-zh_CN.js"></script>
</head>
<body>
    <div class="contentRight">
     	<div class="timeEndIng" id="dataTime"></div>
     	<script type="text/javascript">
     	$.ajax({
			type:"GET",
			url:"/sysmanager/time/queryOne",
			success:function(map){
				$("#dataTime").html("数据截止时间："+map);
			}
     	});
     	</script>
       <div class="rightDownSel">
           <ul class="tabNav">
             <shiro:hasPermission name="dashboard"><li class="on" ><a href="dashboard.html" target="serviceFrameContent">仪表盘首页</a></li></shiro:hasPermission>
               <shiro:hasPermission name="bySaleDashboard"><li  ><a href="ByStationdashboard.html" target="serviceFrameContent">大区仪表盘</a></li></shiro:hasPermission>
              <shiro:hasPermission name="byStationDashboard"> <li  ><a href="ByStationdashboard.html" target="serviceFrameContent">油站仪表盘</a></li></shiro:hasPermission>
           </ul>
       </div>
    </div>
</body>
<script type="text/javascript" src="/sysmanager/back/platform2/js/libs/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</html>
