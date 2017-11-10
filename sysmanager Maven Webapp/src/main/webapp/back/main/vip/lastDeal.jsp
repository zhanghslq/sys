<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>各时间段会员流失占比</title>
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/IconExtension.css">
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
    <script src="/sysmanager/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="/sysmanager/back/easyui/js/form.validator.rules.js"></script>
    <script src="/sysmanager/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="/sysmanager/back/echar/echarts.js"></script>
</head>
<body>
<!-- 暂停，待做 -->
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="lastDeal" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChartlastDeal = echarts.init(document.getElementById('lastDeal'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        $(function() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/firstExpend/queryLastDeal",
				dataType:"JSON",
				success:function(map){
					myChartlastDeal.setOption(option = {
	        			    title: {
	        			        text: '距离最后一次的消费时间'
	        			    },
	        			    tooltip: {
	        			        trigger: 'axis'
	        			    },
	        			    legend: {
	        					//itemWidth:5,
	        			        data:['人数']
	        			    },
	        			    grid: {
	        			        left: '3%',
	        			        right: '4%',
	        			        bottom: '3%',
	        			        containLabel: true
	        			    },
	        			    toolbox: {
	        			        feature: {
	        			            saveAsImage: {}
	        			        }
	        			    },
	        			    xAxis: {
	        			        type: 'category',
	        			        boundaryGap: false,
	        			        data: map.days
	        			    },
	        			    yAxis: {
	        			        type: 'value'
	        			    },
	        			    series: [
	        			        {
	        			            name:'人数',
	        			            type:'line',
	        			            stack: '总量',
	        			            data:map.numbers
	        			        }
	        			    ]
	        			});//绘制完Echarts
	        	
				}//success 
        	});//ajax
       });
        
    </script>
</body>
</html>