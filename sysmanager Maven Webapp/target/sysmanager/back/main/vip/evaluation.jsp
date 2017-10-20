<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <title>评价平均分趋势</title>
    <!-- 引入 echarts.js -->
    
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <form action="">
  请选择维度：<select id="evaluationdate">
	    		<option value="day">日</option>
	    		<option value="month">月</option>
	    		<option value="year">年</option>
    		</select>
  请选择开始时间段：	<input class="easyui-datetimebox" name="start" id="evaluationstart"    
        data-options="required:true,showSeconds:false" value="2016-10-1 2:3" style="width:150px"> 
  请选择结束时间段：<input class="easyui-datetimebox" name="end" id="evaluationend"    
        data-options="required:true,showSeconds:false" value="2017-10-01 0:0" style="width:150px"> 
          查询分类：<select name="query" id="evaluationquery" onchange="queryneirong()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
		    查询内容：<select name="station" id="evaluationstation">
		       			
		    		</select>
     <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryEvaluation()">查询</a>
</form>
   
    <div id="evaluation" style="width:90%;height:90%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('evaluation'));
        // 指定图表的配置项和数据
         function queryneirong() {
    		 $("#evaluationstation").empty();
    		 if($("#evaluationquery").val()=="station"){
    			 $("#evaluationstation").append($("<option></option>").text('全部油站').val('all'));
    		 }
    		 $.ajax({
					type:"GET",
					url:"/sysmanager/station/queryAllName",
					dataType:"JSON",
					data:{"query":$("#evaluationquery").val()},
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#evaluationstation").append(option);
						});
					}
				});
		}
        // 使用刚指定的配置项和数据显示图表。
     $(function () {
    	 queryneirong();
    	 
	}); 
        function queryEvaluation () {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/evaluation/queryTrends",
				dataType:"JSON",
				data:{"start":$("#evaluationstart").datetimebox("getValue"),
					"end":$("#evaluationend").datetimebox("getValue"),"date":$("#evaluationdate").val(),
					"station":$("#evaluationstation").val(),"query":$("#evaluationquery").val()
				},
				success:function(map){
	        		myChart.setOption(option = {
	        			    title: {
	        			        text: '评价得分趋势'
	        			    },
	        			    tooltip: {
	        			        trigger: 'axis'
	        			    },
	        			    legend: {
	        					//itemWidth:5,
	        			        data:['分数']
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
	        			        data: map.dates
	        			    },
	        			    yAxis: {
	        			        type: 'value'
	        			    },
	        			    series: [
	        			        {
	        			            name:'分数',
	        			            type:'line',
	        			            stack: '总量',
	        			            data:map.stars
	        			        }
	        			    ]
	        			});//绘制完Echarts
	        	
				}//success 
        	});//ajax
		};
		
        // 使用刚指定的配置项和数据显示图表。
    </script>
</body>
</html>