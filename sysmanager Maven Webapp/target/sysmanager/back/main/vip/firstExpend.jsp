<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>会员来源</title>
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
 <!--VipDealMonth  -->
     请选择开始时间段：<input class="easyui-datetimebox" name="start"  id="vipDealMonthstart"   
        data-options="required:true,showSeconds:false" value="2016-12-01 2:3" style="width:150px"> 
  	请选择结束时间段：<input class="easyui-datetimebox" name="end"   id="vipDealMonthend" 
        data-options="required:true,showSeconds:false" value="2017-01-01 0:0" style="width:150px"> 
  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="query()">查询</a>
        
    <div id="vipDeal" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChartvipDeal = echarts.init(document.getElementById('vipDeal'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        $(function() {
			query();
		});
       function query() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/firstExpend/vipDealMonth",
				dataType:"JSON",
				data:{"start":$("#vipDealMonthstart").datetimebox("getValue"),
					"end":$("#vipDealMonthend").datetimebox("getValue")},
				success:function(map){
	        		myChartvipDeal.setOption({
	        				    title : {
	        				        text: '会员消费分布',
	        				        subtext: '抽样调查来自: 北京壳牌'
	        				    },
	        				    grid: {
	        				        left: '3%',
	        				        right: '7%',
	        				        bottom: '3%',
	        				        containLabel: true
	        				    },
	        				    tooltip : {
	        				         //trigger: 'axis',
	        				        showDelay : 0,
	        				        formatter : function (params) {
	        				            if (params.value.length > 1) {
	        				                return params.seriesName + ' :<br/>'
	        				                + params.value[0] + '次 '
	        				                + params.value[1] + '元 ';
	        				            }
	        							else {
	        				                return params.seriesName + ' :<br/>'
	        				                + params.name + ' : '
	        				                + params.value + '元 ';
	        				            }
	        				        },
	        				        axisPointer:{
	        				            show: true,
	        				            type : 'cross',
	        				            lineStyle: {
	        				                type : 'dashed',
	        				                width : 1
	        				            }
	        				        }
	        				    },
	        				    toolbox: {
	        				        feature: {
	        				            dataZoom: {},
	        				            brush: {
	        				                type: ['rect', 'polygon', 'clear']
	        				            }
	        				        }
	        				    },
	        				    brush: {
	        				    },
	        				    legend: {
	        				        data: ['会员消费'],
	        				        left: 'center'
	        				    },
	        				    xAxis : [
	        				        {
	        							
	        				            type : 'value',
	        				            scale:true,
	        				            axisLabel : {
	        				                formatter: '{value}次'
	        				            },
	        				            splitLine: {
	        				                show: false
	        				            }
	        				        }
	        				    ],
	        				    yAxis : [
	        				        {
	        							
	        				            type : 'value',
	        				            scale:true,
	        				            axisLabel : {
	        				                formatter: '{value} 元'
	        				            },
	        				            splitLine: {
	        				                show: false
	        				            }
	        				        }
	        				    ],
	        				    series : [
	        				        {
	        				            name:'会员消费',
	        				            type:'scatter',
	        				            data: map,
	        				            markArea: {
	        				                silent: true,
	        				                itemStyle: {
	        				                    normal: {
	        				                        color: 'transparent',
	        				                        borderWidth: 1,
	        				                        borderType: 'dashed'
	        				                    }
	        				                },
	        				                data: [[{
	        				                    name: '会员消费区间',
	        				                    xAxis: 'min',
	        				                    yAxis: 'min'
	        				                }, {
	        				                    xAxis: 'max',
	        				                    yAxis: 'max'
	        				                }]]
	        				            },
	        				            markPoint : {
	        				                data : [
	        				                    {type : 'max', name: '最大值'},
	        				                    {type : 'min', name: '最小值'}
	        				                ]
	        				            },
	        				            markLine : {
	        				                lineStyle: {
	        				                    normal: {
	        				                        type: 'solid'
	        				                    }
	        				                },
	        				                data : [
	        				                    {type : 'average', name: '平均值'},
	        				                ]
	        				            }
	        				        }
	        				    ]
	        				}
	        		);//绘制完Echarts
	        	
				}//success 
        	});//ajax
       };
        
    </script>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="firstExpend" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('firstExpend'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        $(function() {
        	$.ajax({
				type:"GET",
				url:"/sysmanager/firstExpend/queryAllExpend",
				dataType:"JSON",
				success:function(map){
	        		myChart.setOption(option = {
	        			    title: {
	        			        text: '注册到首次消费间隔天数'
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
    <!-- 两次消费时间间隔 -->
    <div id="gap" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChartgap = echarts.init(document.getElementById('gap'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        $(function() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/firstExpend/queryAllGap",
				dataType:"JSON",
				success:function(map){
	        		myChartgap.setOption(option = {
	        			    title: {
	        			        text: '两次消费平均时间间隔天数'
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
   
    <!-- 距离最后一次消费时间 -->
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