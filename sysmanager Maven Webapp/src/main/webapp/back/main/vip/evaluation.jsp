<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <title>评价平均分趋势</title>
    <!-- 引入 echarts.js -->
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
     <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryEvaluation()">查询</a>
</form>
   
    <div id="evaluation" style="width:90%;height:90%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChartevaluation = echarts.init(document.getElementById('evaluation'));
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
    	 queryEvaluation();
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
	        		myChartevaluation.setOption(option = {
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
    <form action="">
		  请选择开始时间：<input id="distributestart" class="easyui-datetimebox" name="start"
		        data-options="required:true,showSeconds:false" value="2017-3-01 0:0" style="width:150px"/>
		  请选择结束时间：<input id="distributeend" class="easyui-datetimebox" name="end"
		        data-options="required:true,showSeconds:false" value="2017-03-21 0:0" style="width:150px"/>
				
		 查询分类：<select name="query" id="distributequery" onchange="queryEva()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
		    查询内容：<select name="station" id="distributestation">
		       			
		    		</select>
     <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryEvaDis()">查询</a>
    		
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="evaluationDistribution" style="width: 80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
         var myChartevaluationDistribution = echarts.init(document.getElementById('evaluationDistribution'));
         function queryEva() {
    		 $("#distributestation").empty();
    		 if($("#distributequery").val()=="station"){
    			 $("#distributestation").append($("<option></option>").text('全部油站').val('all'));
    		 }
    		 $.ajax({
					type:"GET",
					url:"/sysmanager/station/queryAllName",
					dataType:"JSON",
					data:{"query":$("#distributequery").val()},
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#distributestation").append(option);
						});
					}
				});
		}
         $(function () {
        	 queryEva();
        	 queryEvaDis();
    	}); 
        
         function queryEvaDis () {
         	$.ajax({
 				type:"POST",
 				url:"/sysmanager/evaluation/queryDistribution",
 				dataType:"JSON",
 				data:{"start":$("#distributestart").datetimebox("getValue"),
 					"end":$("#distributeend").datetimebox("getValue"),
 					"station":$("#distributestation").val(),"query":$("#distributequery").val()
 				},
 				success:function(map){
 	        		myChartevaluationDistribution.setOption({
 	                   title: {
 	                      text: '北京壳牌'
 	                  },
 	                  tooltip: {
 	                	 trigger: 'axis'
 	                  },
 	                  legend: {
 	      				
 	      				
 	                      data:[{
 	      					name: '平均分'
 	      				}]
 	      				
 	                  },
 	                  xAxis: {
 	                      data: ["总体满意度","服务态度","油站环境","加油速度","推荐意愿"]
 	                  },
 	                  yAxis: {
 	                	  type:'value'
 	                  },
 	                  series: [{
 	                      name: '平均分',
 	                      type: 'bar',
 	                      data: map
 	                  }
 	      			]
 	              });//绘制完Echarts
 	        	
 				}//success 
         	});//ajax
 		};
    </script>
    
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
<a  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryEvaluations()">查询</a>
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
    	 queryEvaluations();
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