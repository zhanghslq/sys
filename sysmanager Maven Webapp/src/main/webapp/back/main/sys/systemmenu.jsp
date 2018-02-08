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
       <div class="rightDownSel">
           <ul class="tabNav">
               <li class="on" ><a href="station/showAll.jsp" target="systemFrameContent">油站管理</a></li>
               <li ><a href="pos/showAll.jsp" target="systemFrameContent">POS传输IP管理</a></li>
               <li ><a href="data/showAll.jsp" target="systemFrameContent">数据传输</a></li>
               <li ><a href="../admin/showAll.jsp" target="systemFrameContent">用户管理</a></li>
               <li ><a href="../role/showAll.jsp" target="systemFrameContent">角色管理</a></li>
           </ul>
       </div>
    </div>
</body>
<script type="text/javascript">
	function checkUser() {
		
	}
</script>
<script type="text/javascript" src="/sysmanager/back/platform2/js/libs/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</html>
