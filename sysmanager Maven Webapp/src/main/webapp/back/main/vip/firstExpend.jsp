<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>会员来源</title>
     <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/IconExtension.css">
    <link rel="stylesheet" href="/sysmanager/back/datepicker/assets/css/amazeui.min.css"/>
	<link rel="stylesheet" href="/sysmanager/back/datetimepicker-master/css/amazeui.datetimepicker.css"/>
	<link rel="stylesheet" href="/sysmanager/back/platform2/css/common.css" />
    <link rel="stylesheet" href="/sysmanager/back/platform2/css/index.css" />
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
    <script src="/sysmanager/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="/sysmanager/back/easyui/js/form.validator.rules.js"></script>
    <script src="/sysmanager/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="/sysmanager/back/echar/echarts.js"></script>
    <script src="/sysmanager/back/datetimepicker-master/js/amazeui.datetimepicker.js"></script>
    <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
</head>
<body>
<div class="contentRight">
<div class="timeEndIng" id="dataTime"></div>
     	<script type="text/javascript">
	     	$.ajax({
				type:"GET",
				url:"/sysmanager/time/queryThree",
				success:function(map){
					$("#dataTime").html("数据截止时间："+map);
				}
	     	});
     	</script>
       <div class="rightDownSel">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       <!-- 这是跟选择油站平级的 -->
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择区域</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                      <a href="javascript:void(0);" onclick="ChangeArea('BJSHELL')" class="titleCur">北京会员</a>
                                      <a href="javascript:void(0); " onclick="ChangeArea('CDSHELL')">承德会员</a>
                                  </div>
                              </div>
                           </div>
                       </div>
                       <a class="export" onclick="exportFirstExpend()">导出到Excel</a>
                   </div>
               </div>
           </div>
       </div>
    </div>
    <script type="text/javascript">
    	function exportFirstExpend() {
			location.href="/sysmanager/firstExpend/exportFirstExpend?area="+baseArea;
		}
    	function exportGap() {
			location.href="/sysmanager/firstExpend/exportGap?area="+baseArea;
		}
    </script>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="firstExpend" style="width:80%;height:80%;"></div>
    <a class="export" onclick="exportGap()" style="margin-left: 30px">导出到Excel</a>
    <div id="gap" style="width:80%;height:80%;"></div>
	<form action="" method="post" id="consumeForm">
    <div class="contentRight" >
       <div class="rightDownSel" >
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       <!-- 这是跟选择油站平级的 -->
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择时间</span></div>
                           <div class="seleContent selTime">
                              <div class="downCont selTimeMain">
                                  <div class="selTimeInfo">
                                     <div class="minimum">
                                        <em>最小时间单位：</em>
                                        <div class="minimumRadio">
                                          <label><i>按照所选时间所在月份</i> </label>
                                        </div>
                                      </div>
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" name="start" style="width:300px"  class="am-form-field" id='vipDealMonthstart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end" style="width:300px"  class="am-form-field" id='vipDealMonthend'></div>
                                      </div>
                                      <script>
                                      $('#vipDealMonthstart').attr("value",getNowFormatDateOne());
                                      $('#vipDealMonthend').attr("value",getNowFormatDate());
											$('#vipDealMonthstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#vipDealMonthend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryByDate()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                        <a href="javascript:void(0);" class="determine" onclick="exportConsume()">导出到Excel</a>
                                      </div>
                                  </div>
                              </div>
                           </div>
                       </div>
                   </div>
               </div>
               
           </div>
       </div>
    </div>
    </form>
    <div id="vipDeal" style="width:80%;height:80%;"></div>
    <a class="export" style="margin-left: 30px;" onclick="exportLastDeal()">导出到Excel</a>
     <div id="lastDeal" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    function exportLastDeal() {
		location.href="/sysmanager/firstExpend/exportLastDeal?area="+baseArea;
	}
    function exportConsume() {
		$("#consumeForm").attr("action","/sysmanager/firstExpend/exportvipDealMonth?area="+baseArea);
		$("#consumeForm").submit();
	}
        // 基于准备好的dom，初始化echarts实例
        var baseArea="BJSHELL";
         function ChangeArea(src) {
			baseArea=src;
			query();
			queryByDate();
		}
       $(function() {
			queryByDate();
		});
        var myChart = echarts.init(document.getElementById('firstExpend'));
        var myChartgap = echarts.init(document.getElementById('gap'));
        var myChartlastDeal = echarts.init(document.getElementById('lastDeal'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        // 基于准备好的dom，初始化echarts实例
        var myChartvipDeal = echarts.init(document.getElementById('vipDeal'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryByDate() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/firstExpend/vipDealMonth",
				dataType:"JSON",
				data:{"start":$("#vipDealMonthstart").val(),
					"end":$("#vipDealMonthend").val(),
					"area":baseArea},
				success:function(map){
	        		myChartvipDeal.setOption({
	        				    title : {
	        				        text: '会员消费分布',
	        				        x:'center'
	        				    },
	        				    grid: {
	        				    	top:'10%',
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
	        				                + '平均'+params.value[1] + '元 ';
	        				            }
	        							else {
	        				                return params.seriesName + ' :<br/>'
	        				                + params.name + ' : '
	        				                + '平均'+params.value + '元 ';
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
	        				    	right:18,
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
	        				    	top:30,
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
		}
    </script>
    <!-- 两次消费时间间隔 -->
    
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
     
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
    </script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        $(function() {
			query();
		});
       function query() {
    	   $.ajax({
				type:"post",
				url:"/sysmanager/firstExpend/queryAllGap",
				dataType:"JSON",
				data:{"area":baseArea},
				success:function(map){
	        		myChartgap.setOption(option = {
	        			    title: {
	        			        text: '两次消费平均时间间隔天数',
	        			        subtext:'横轴代表会员平均两次消费间隔的天数，纵轴代表间隔这么长时间的人数',
	        			        x:'center'
	        			    },
	        			    tooltip: {
	        			        trigger: 'axis'
	        			    },
	        			    legend: {
	        					//itemWidth:5,
	        					top:'50',
	        			        data:['人数','累计人数']
	        			    },
	        			    grid: {
	        			    	top:'12%',
	        			        left: '3%',
	        			        right: '4%',
	        			        bottom: '3%',
	        			        containLabel: true
	        			    },
	        			    toolbox: {
	        			    	right:18,
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
	        			            stack: '每天',
	        			            data:map.numbers
	        			        },
	        			        {
	        			            name:'累计人数',
	        			            type:'line',
	        			            stack: '总量',
	        			            data:map.allNumbers
	        			        }
	        			    ]
	        			});//绘制完Echarts
	        	
				}//success 
       	});//ajax
    	   $.ajax({
				type:"GET",
				url:"/sysmanager/firstExpend/queryAllExpend",
				dataType:"JSON",
				data:{"area":baseArea},
				success:function(map){
	        		myChart.setOption(option = {
	        			    title: {
	        			        text: '注册到首次消费间隔天数',
	        			        subtext:'横轴代表注册到首次消费的天数，纵轴代表人数',
	        			        x:'center'
	        			    },
	        			    tooltip: {
	        			        trigger: 'axis'
	        			    },
	        			    legend: {
	        					//itemWidth:5,
	        					top:50,
	        			        data:['人数','累计人数']
	        			    },
	        			    grid: {
	        			    	top:'12%',
	        			        left: '3%',
	        			        right: '4%',
	        			        bottom: '3%',
	        			        containLabel: true
	        			    },
	        			    toolbox: {
	        			    	right:18,
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
	        			            stack: '每天',
	        			            data:map.numbers
	        			        },
	        			        {
	        			            name:'累计人数',
	        			            type:'line',
	        			            stack: '总量',
	        			            data:map.allNumber
	        			        }
	        			    ]
	        			});//绘制完Echarts
	        	
				}//success 
       	});//ajax
       	
       	$.ajax({
			type:"POST",
			url:"/sysmanager/firstExpend/queryLastDeal",
			dataType:"JSON",
			data:{"area":baseArea},
			success:function(map){
				myChartlastDeal.setOption(option = {
        			    title: {
        			        text: '距离最后一次的消费时间',
        			        subtext:'横轴代表现在距离上一次消费的时间，纵轴代表人数',
        			        x:'center'
        			    },
        			    tooltip: {
        			        trigger: 'axis'
        			    },
        			    legend: {
        					//itemWidth:5,
        			        data:['人数'],
        			        top:50
        			    },
        			    grid: {
        			    	top:'10%',
        			        left: '3%',
        			        right: '4%',
        			        bottom: '3%',
        			        containLabel: true
        			    },
        			    toolbox: {
        			    	right:18,
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
       	
       };
        
    </script>
    <!-- 距离最后一次消费时间 -->
   
    <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>