<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>会员来源</title>
</head>
<body>
<!-- 暂停，待做 -->
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="firstExpend" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('firstExpend'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function query() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/firstExpend/query",
				dataType:"JSON",
				success:function(map){
	        		myChart.setOption(option = {
	        			    title: {
	        			        text: '注册到首次消费'
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
       }
        
    </script>
</body>
</html>