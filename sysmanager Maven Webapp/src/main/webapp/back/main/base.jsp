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
</head>

<body>
    <div class="contentRight" id="contentRightHeight">
       <div class="rightDownSel">
           <ul class="tabNav">
               <li class="on">油品营业额</li>
               <li>便利店营业额</li>
               <li>润滑油营业额</li>
               <li>油品同比环比</li>
               <li>便利店同比环比</li>
           </ul>
           <div class="rightDownMain" >
               <div class="downDetails" style="display: block;height: auto;width: auto"><!-- 这是第一个内容区域 -->
                   <iframe style="min-height: 1200px" src="base/baseOil.jsp"  class="introduceNav" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0" ></iframe>
               </div>
               <div class="downDetails" style="height: auto;width: auto"><!-- 这里就是上面选择完成的内容 2-->
                   <iframe src="shop/baseNotOil.jsp" class="introduceNav" frameborder="0" scrolling="auto"  marginwidth="0" marginheight="0" ></iframe>
               </div>
               <div class="downDetails">
               <iframe src="shop/baseLube.jsp" class="introduceNav" frameborder="0" scrolling="auto"  marginwidth="0" marginheight="0" ></iframe>
				</div><!-- 第三个选项的内容 -->
               <div class="downDetails">
               
               <iframe src="compare/baseCompare.jsp" class="introduceNav" frameborder="0" scrolling="auto"  marginwidth="0" marginheight="0" ></iframe>
               </div> <!-- 第四个选项的内容 -->
               <div class="downDetails">
               
               <iframe src="compare/shopCompare.jsp" class="introduceNav" frameborder="0" scrolling="auto"  marginwidth="0" marginheight="0" ></iframe>
               </div> <!-- 第四个选项的内容 -->
           </div>
       </div>
      
    </div>

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
