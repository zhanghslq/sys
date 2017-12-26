<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>会员充值</title>
    <!-- 引入 echarts.js -->
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
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
     <div class="contentRight" id="contentRightHeight">
       <div class="rightDownSel" id="test">
           <!-- <ul class="tabNav">
               <li class="on">整体销售</li>
               <li>燃油销售</li>
               <li>非油销售</li>
               <li>润滑油销售</li>
           </ul> -->
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
                                        <em>最小时间单位</em>
                                        <div class="minimumRadio">
                                          <label><input name="date" type="radio" value="year" /> <i>年</i> </label>
                                          <label><input name="date" type="radio" value="month" /> <i>月</i> </label>
                                          <label><input name="date" type="radio" value="day" checked="checked"/> <i>日</i> </label>
                                          <label><input name="date" type="radio" value="hour" /> <i>小时</i> </label>
                                          <label><input name="date" type="radio" value="minute" /> <i>分钟</i> </label>
                                        </div>
                                      </div>
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input  style="width:300px"  class="am-form-field" id='rechargestart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input  style="width:300px"  class="am-form-field" id='rechargeend'></div>
                                      </div>
                                      <script>
                                      $('#rechargestart').attr("value",getNowFormatDateOne());
                                      $('#rechargeend').attr("value",getNowFormatDate());
											$('#rechargestart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
											$('#rechargeend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryrecharge()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
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
   
    <div id="recharge" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChartrecharge = echarts.init(document.getElementById('recharge'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryrecharge() {
        	queryvipRecharge();
        	var tradeNumber=[];
        	var tradeAmounts=[];
        	var avgAmounts=[];
        	var dates=[];
				$.ajax({
					type:"post",
					url:"/sysmanager/recharge/query",
					dataType:"JSON",
					data:{"start":$("#rechargestart").val(),
						"end":$("#rechargeend").val(),"date":$("input[name='date']:checked").val()},
					success:function(map){
						tradeNumber=map.tradeNumber;
						tradeAmounts=map.tradeAmounts;
						avgAmounts=map.avgAmounts;
						dates=map.dates;
						
						myChartrecharge.setOption({
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
								data:['充值单数','充值总金额','单笔交易额']
							},
							xAxis: [
								{
									type: 'category',
									data: dates,
									name:'    日期',
									axisPointer: {
										type: 'shadow'
									}
								}
							],
							yAxis: [
								{
									type: 'value',
									name: '充值单数',
									min: 0,
									axisLabel: {
										formatter: '{value}单'
									}
								},
								{
									type: 'value',
									name: '充值金额',
									axisLabel: {
										formatter: '{value}元'
									}
								}
							],
							series: [
								{
									name:'充值单数',
									type:'line',
									data:tradeNumber,
									itemStyle:{  
	                                    normal:{color:'#89CFDC'}  
	                                }  
								},
								{
									name:'充值总金额',
									type:'bar',
									yAxisIndex: 1,
									data:tradeAmounts,
									itemStyle:{  
	                                    normal:{color:'#DD1D21'}  
	                                }  
								},
								{
									name:'单笔交易额',
									type:'bar',
									yAxisIndex: 1,
									data:avgAmounts,
									itemStyle:{  
	                                    normal:{color:'#FBCE07'}  
	                                }  
								}
							]
						},true);
						//配置
						
					}
				});
				
			} 
        
    </script>
  
    <div id="vipRecharge" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    $(function() {
		queryrecharge();
	});
        // 基于准备好的dom，初始化echarts实例
        var myChartvipRecharge = echarts.init(document.getElementById('vipRecharge'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
       function queryvipRecharge() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipRechargeMonth/querySingle",
				dataType:"JSON",
				data:{"start":$("#rechargestart").val(),
					"end":$("#rechargeend").val()},
				success:function(map){
	        		myChartvipRecharge.setOption({
	        				    title : {
	        				        text: '会员充值分布',
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
	        				        data: ['会员充值'],
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
	        				            name:'会员充值',
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
	        				                    name: '会员充值区间',
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
    <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>