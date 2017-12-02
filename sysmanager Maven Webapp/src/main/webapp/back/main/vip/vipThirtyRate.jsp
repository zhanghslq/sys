<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>新招募会员30天转化率</title>
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
  请选择开始时间段：	<input class="easyui-datetimebox" name="start"  id="thirtyRatestart"
        data-options="required:true,showSeconds:false" value="2017-9-21 0:0"   style="width:150px"> 
  请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-10-01 0:0"  id="thirtyRateend"
        data-options="required:true,showSeconds:false"  style="width:150px"> 
   请选择转化周期：<select id="rateQuery">
        	<option value="thirty">会员30天转化率</option>
        	<option value="sixty">会员60天转化率</option>
        </select>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="query()">查询</a> 
</form>
   
    <div id="thirtyRate" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
   
        // 基于准备好的dom，初始化echarts实例
        var myChartthirtyRate = echarts.init(document.getElementById('thirtyRate'));
        
        
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function query() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipChannel/queryRate",
				dataType:"JSON",
				data:{"start":$("#thirtyRatestart").datetimebox("getValue"),
					"end":$("#thirtyRateend").datetimebox("getValue"),
					"query":$("#rateQuery").val()
					},
				success:function(map){
        		myChartthirtyRate.setOption({
        			title:{
        				text:'日新增会员转化率'
        			},
        			tooltip : {
        				trigger: 'axis',
        				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        				},
        				formatter: '{c}%'
        			},
        			legend: {
        				data:['日新增会员转化率']
        			},
        			toolbox: {
        				show : true,
        				feature : {
        					mark : {show: true},
        					dataView : {show: true, readOnly: false},
        					magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
        					restore : {show: true},
        					saveAsImage : {show: true}
        				}
        			},
        			calculable : true,
        			
        			xAxis : [
        				{
        					type : 'category',
        					data : map.days
        				}
        			],
        			yAxis : [
        				{
        					type : 'value',
        					axisLabel: {
								formatter: '{value}%'
							}
        				}
        			],
        			series : [
        				{
        					name:'日新增会员转化率',
        					type:'bar',
        					stack: '总量',
        					data:map.rates,
        					label: {
			                    normal: {
			                        show: true,
			                        position: 'inside',
			                        formatter: '{c}%'
			                    }
			                }
        				}
        			]
        		});
        	
				}//success 
        	});//ajax
       }
        
    </script>
    <!-- 活跃会员人数及占比 -->
    
     <div id="compare" style="width:90%;height:60%;"></div>
    <form action="">
   		请选择月份查看详情：<select name="station" id="comparedate" onchange="queryLiveNessByDate()">
    		</select>
    <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryLiveNessByDate()">查询</a>
    </form>
    <div id="consumepie" style="width:90%;height:60%;"></div>
    <script type="text/javascript">
	    $.ajax({
			type:"GET",
			url:"/sysmanager/liveNess/queryAllDate",
			dataType:"JSON",
			success:function(result){
				$.each(result,function(i,station){
					var option = $("<option></option>").text(station).val(station);
					$("#comparedate").append(option);
				});
			}
		});
    $(function() {
    	queryLiveNess();
    	query();
	});
        // 基于准备好的dom，初始化echarts实例
        var myChartcompare = echarts.init(document.getElementById('compare'));
        var myChartconsumepie = echarts.init(document.getElementById('consumepie'));
        // 指定图表的配置项和数据
        function queryLiveNessByDate() {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/liveNess/queryDataByDate",
				dataType:"JSON",
				data:{"month":$("#comparedate").val()},
				success:function(map){
					myChartconsumepie.setOption({
	    				title : {
	    					text: '消费频次占比图',
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
	    					data: ['未消费的','消费一次的','消费两次的','消费三次的','消费四次的','消费五次的','五次以上的']
	    				},
	    				series : [
	    					{
	    						name: '访问来源',
	    						type: 'pie',
	    						radius : '55%',
	    						center: ['50%', '60%'],
	    						data:map
	    					}
	    				]
	    			});
					}
				});
        	}
         function queryLiveNess () {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/liveNess/queryData",
				dataType:"JSON",
				success:function(map){
					myChartcompare.setOption({
	        			title : {
	    					text: '消费频次趋势图',
	    					x:'left'
	    				},
	        			tooltip : {
	        				trigger: 'axis',
	        				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	        					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        				}
	        			},
	        			legend: {
	        				data:['未消费的', '消费一次的','消费两次的','消费三次的','消费四次的','消费五次的','五次以上的']
	        			},
	        			/* toolbox: {
	        				show : true,
	        				feature : {
	        					mark : {show: true},
	        					dataView : {show: true, readOnly: false},
	        					magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
	        					restore : {show: true},
	        					saveAsImage : {show: true}
	        				}
	        			}, */
	        			calculable : true,
	        			
	        			xAxis : [
	        				{
	        					type : 'category',
	        					data : map.date
	        				}
	        			],
	        			yAxis : [
	        				{
	        					type : 'value'
	        				}
	        			],
	        			series : [
	        				{
	        					name:'未消费的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.zero
	        				},
	        				{
	        					name:'消费一次的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.one
	        				},
	        				{
	        					name:'消费两次的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.two
	        				},
	        				{
	        					name:'消费三次的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.three
	        				},
	        				{
	        					name:'消费四次的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.four
	        				},
	        				{
	        					name:'消费五次的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.five
	        				},
	        				{
	        					name:'五次以上的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.overfive
	        				}
	        			]
	        		});//绘制完Echarts
	        	
				}//success 
        	});//ajax
		};
    </script>
    <hr>
    <!-- 会员流失 -->
    <form action="">
请选择时间单位：<select name="date" id="draindate">
			    		<option value="day">日</option>
			    		<option value="month">月</option>
			    		<option value="year">年</option>
		    	 </select>
  请选择开始时间：<input id="drainstart" class="easyui-datetimebox" name="start"
        data-options="required:true,showSeconds:false" value="2017-8-21 0:0"   style="width:150px"/>
 请选择结束时间：<input id="drainend" class="easyui-datetimebox" name="end"
        data-options="required:true,showSeconds:false" value="2017-10-21 0:0"   style="width:150px"/>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryDrain()">查询</a>
</form>
    <div id="vipDrain" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    /* $.ajax({
		type:"GET",
		url:"/sysmanager/city/queryAll",
		dataType:"JSON",
		success:function(result){
			$.each(result,function(i,station){
				var option = $("<option></option>").text(station.name).val(station.id);
				$("#refuelstation").append(option);
			});
		}
	}); */
    $(function() {
		queryDrain();
	});
        // 基于准备好的dom，初始化echarts实例
        var myChartdrain = echarts.init(document.getElementById('vipDrain'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryDrain() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipFunnel/queryDrain",
				data:{"start":$("#drainstart").datetimebox("getValue"),
					"end":$("#drainend").datetimebox("getValue"),"date":$("#draindate").val(),
				},
				dataType:"JSON",
					success:function(map){
					myChartdrain.setOption({
						title:{
							text:'流失会员人数及占比',
							subtext:'流失会员即连续3个月没有消费记录的会员',
							x:'center'
						},
						tooltip: {
							trigger: 'axis',
							axisPointer: {
								type: 'cross',
								crossStyle: {
									color: '#999'
								}
							}
						},
						toolbox: {
							feature: {
								dataView: {show: true, readOnly: false},
								magicType: {show: true, type: ['line', 'bar']},
								restore: {show: true},
								saveAsImage: {show: true}
							}
						},
						legend: {
							data:['流失会员人数','流失人数占比'],
							left: 'left',
						},
						xAxis: [
							{
								type: 'category',
								data: map.month,
								axisPointer: {
									type: 'shadow'
								}
							}
						],
						yAxis: [
							{
								type: 'value',
								name: '流失人数',
								min: 0,
								axisLabel: {
									formatter: '{value}'
								}
							},
							{
								type: 'value',
								name: '流失人数占比',
								min: 0,
								axisLabel: {
									formatter: '{value} %'
								}
							}
						],
						series: [
							{
								name:'流失会员人数',
								type:'bar',
								data:map.drain
							},
							{
								name:'流失人数占比',
								type:'line',
								yAxisIndex: 1,
								data:map.rate
							}
						]
					});
        	
				}//success 
        	});//ajax
       }
        
    </script>
    <hr>
    <!-- 会员漏斗图 -->
    <form action="">

  请选择时间：<select id="funnelMonth" onchange="queryVipFunnel()">
		  	
  		</select>
  		
  		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryVipFunnel()">查询</a>
</form>
    <div id="vipFunnel" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    $.ajax({
		type:"GET",
		url:"/sysmanager/vipFunnel/queryAllMonth",
		dataType:"JSON",
		success:function(result){
			$.each(result,function(i,station){
				var option = $("<option></option>").text(station).val(station);
				$("#funnelMonth").append(option);
			});
		}
	});
        // 基于准备好的dom，初始化echarts实例
        var myChartvipFunnel = echarts.init(document.getElementById('vipFunnel'));
        
        
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryVipFunnel() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipFunnel/queryVipFunnel",
				dataType:"JSON",
				data:{"month":$("#funnelMonth").val()},
				success:function(map){
        			myChartvipFunnel.setOption({
        			    title: {
        			        text: '漏斗图',
        			        subtext: '会员人数'
        			    },
        			    tooltip: {
        			        trigger: 'item',
        			    },
        			    toolbox: {
        			        feature: {
        			            dataView: {readOnly: false},
        			            restore: {},
        			            saveAsImage: {}
        			        }
        			    },
        			    legend: {
        			        data: ['会员总数','至少消费一次的','活跃会员']
        			    },
        			    calculable: true,
        			    series: [
        			        {
        			            name:'漏斗图',
        			            type:'funnel',
        			            left: '10%',
        			            top: 60,
        			            bottom: 60,
        			            width: '80%',
        			            sort: 'descending',
        			            gap: 2,
        			            label: {
        			                normal: {
        			                    show: true,
        			                    position: 'inside'
        			                },
        			                emphasis: {
        			                    textStyle: {
        			                        fontSize: 20
        			                    }
        			                }
        			            },
        			            labelLine: {
        			                normal: {
        			                    length: 10,
        			                    lineStyle: {
        			                        width: 1,
        			                        type: 'solid'
        			                    }
        			                }
        			            },
        			            itemStyle: {
        			                normal: {
        			                    borderColor: '#fff',
        			                    borderWidth: 1
        			                }
        			            },
        			            data: map
        			        }
        			    ]
        			});//绘制完成Echarts
				}//success 
        	});//ajax
       }
        
    </script>
</body>
</html>