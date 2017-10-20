<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <title>评价散点图</title>
</head>
<body>
    <form action="">
  请选择开始时间段：	<input class="easyui-datetimebox" name="start" id="evalsstart"    
        data-options="required:true,showSeconds:false" value="2016-10-1 2:3" style="width:150px"> 
  请选择结束时间段：<input class="easyui-datetimebox" name="end" id="evalsend"    
        data-options="required:true,showSeconds:false" value="2017-10-01 0:0" style="width:150px"> 
查询分类：<select name="query" id="evalsquery" onchange="queryEvals()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
 查询内容：<select name="station" id="evalsstation"></select>
<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryEvaluations()">查询</a>
</form>
    <div id="evals" style="width:90%;height:90%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('evals'));
        // 指定图表的配置项和数据
         function queryEvals() {
    		 $("#evalsstation").empty();
    		 if($("#evalsquery").val()=="station"){
    			 $("#evalsstation").append($("<option></option>").text('全部油站').val('all'));
    		 }
    		 $.ajax({
					type:"POST",
					url:"/sysmanager/station/queryAllName",
					dataType:"JSON",
					data:{"query":$("#evalsquery").val()},
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#evalsstation").append(option);
						});
					}
				});
		}
        // 使用刚指定的配置项和数据显示图表。
     $(function () {
    	 queryEvals();
    	 
	}); 
     var lineStyle = {
				normal: {
					width: 1,
					opacity: 0.5
				}
			};
        function queryEvaluations () {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/evaluation/queryEvaluations",
				dataType:"JSON",
				data:{"start":$("#evalsstart").datetimebox("getValue"),
					"end":$("#evalsend").datetimebox("getValue"),
					"station":$("#evalsstation").val(),"query":$("#evalsquery").val()
				},
				success:function(map){
	        		myChart.setOption({
	    				backgroundColor: '#161627',
	    				title: {
	    					text: '评价分数图',
	    					left: 'center',
	    					textStyle: {
	    						color: '#eee'
	    					}
	    				},
	    				legend: {
	    					bottom: 5,
	    					data: ['北京壳牌'],
	    					itemGap: 20,
	    					textStyle: {
	    						color: '#fff',
	    						fontSize: 14
	    					},
	    					selectedMode: 'single'
	    				},
	    				radar: {
	    					indicator: [
	    						{name: '总体满意度', max: 5},
	    						{name: '服务态度', max: 5},
	    						{name: '油站环境', max: 5},
	    						{name: '加油速度', max: 5},
	    						{name: '推荐意愿', max: 5},
	    					],
	    					shape: 'circle',
	    					splitNumber: 5,
	    					name: {
	    						textStyle: {
	    							color: 'rgb(238, 197, 102)'
	    						}
	    					},
	    					splitLine: {
	    						lineStyle: {
	    							color: [
	    								'rgba(238, 197, 102, 0.1)', 'rgba(238, 197, 102, 0.2)',
	    								'rgba(238, 197, 102, 0.4)', 'rgba(238, 197, 102, 0.6)',
	    								'rgba(238, 197, 102, 0.8)', 'rgba(238, 197, 102, 1)'
	    							].reverse()
	    						}
	    					},
	    					splitArea: {
	    						show: false
	    					},
	    					axisLine: {
	    						lineStyle: {
	    							color: 'rgba(238, 197, 102, 0.5)'
	    						}
	    					}
	    				},
	    				series: [
	    					{
	    						name: '北京壳牌',
	    						type: 'radar',
	    						lineStyle: lineStyle,
	    						data: map,
	    						symbol: 'none',
	    						itemStyle: {
	    							normal: {
	    								color: '#F9713C'
	    							}
	    						},
	    						areaStyle: {
	    							normal: {
	    								opacity: 0.1
	    							}
	    						}
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