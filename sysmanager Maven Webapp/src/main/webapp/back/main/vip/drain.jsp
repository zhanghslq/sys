<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>流失会员人数及占比</title>
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
   $(function () {
  		queryVipFunnel();
	});
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('vipFunnel'));
        
        
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryVipFunnel() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipFunnel/queryVipFunnel",
				dataType:"JSON",
				data:{"month":$("#funnelMonth").val()},
				success:function(map){
        			myChart.setOption({
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