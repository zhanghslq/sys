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
	<link rel="stylesheet" href="/sysmanager/back/platform2/css/common.css" />
    <link rel="stylesheet" href="/sysmanager/back/platform2/css/index.css" />
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
</head>

<body>
    <div class="contentRight" >
       <div class="rightDownSel">
           <ul class="tabNav">
               <li class="on" ><a style="text-decoration: none;" href="shop/baseNotOil.jsp" target="shopFrameContent">便利店销售整体情况</a></li>
               <li ><a style="text-decoration: none;"  href="shop/byNotOil.jsp" target="shopFrameContent">便利店分品类销售情况</a></li>
               <li ><a style="text-decoration: none;"  href="shop/search.jsp" target="shopFrameContent">便利店分品类销售情况</a></li>
               <!-- <li onclick="query('shop/baseLube.jsp')">T&R</li> -->
               <li ><a style="text-decoration: none;" href="shop/baseLube.jsp" target="shopFrameContent">润滑油销售情况</a></li>
               <li ><a style="text-decoration: none;" href="shop/rate.jsp" target="shopFrameContent">开单率 千升比</a></li>
               <li ><a style="text-decoration: none;" href="compare/shopCompare.jsp" target="shopFrameContent">便利店同比环比</a></li>
           </ul>
           <div id="content"></div>
       </div>
    </div>
		<!-- <script type="text/javascript">
			function query(url){
				$("#content").empty();
				$.ajax({
						url: url,//目标页面
						dataType: "html",
						type: "GET",
						cache: false,
						success: function(html){
							$("#content").html(html);
						},
						error:function(result){
							console.log(result);
							alert("error");
						}
					});
			}
		$(function(){
			query("shop/baseNotOil.jsp");
	});
		</script> -->
    <!--右边 结束-->
</body>
<script type="text/javascript" src="/sysmanager/back/platform2/js/libs/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</html>
