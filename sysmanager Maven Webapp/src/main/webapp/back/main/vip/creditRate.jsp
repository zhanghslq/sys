<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>积分余量及其占比</title>
    <!-- 引入 echarts.js-->
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="creditRate" style="width: 80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
         var myChartcreditRate = echarts.init(document.getElementById('creditRate'));
       $(function() {
    	   $.ajax({
   			type:"post",
   			url:"/sysmanager/credit/queryZhanbi",
   			dataType:"JSON",
   			success:function(map){
   				myChartcreditRate.setOption({
   					title : {
   						text: '积分余量占比',
   						subtext: '北京壳牌',
   						x:'center'
   					},
   					tooltip : {
   						trigger: 'item',
   						formatter: "{a} <br/>{b} : {c} ({d}%)"
   					},
   					legend: {
   						orient: 'vertical',
   						left: 'left',
   						data: ['已使用','未使用',]
   					},
   					series : [
   						{
   							name: '积分占比',
   							type: 'pie',
   							radius : '55%',
   							center: ['50%', '60%'],
   							data:map,
   							itemStyle: {
   								emphasis: {
   									shadowBlur: 10,
   									shadowOffsetX: 0,
   									shadowColor: 'rgba(0, 0, 0, 0.5)'
   								}
   							}
   						}
   					]
   				});//Echarts
   					
   			}
   		});
	});
        	
    </script>
</body>
</html>