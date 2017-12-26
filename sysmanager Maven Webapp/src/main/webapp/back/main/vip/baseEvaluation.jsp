<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>评价</title>
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
<form action="" id="exportExcel" method="post">
<div class="contentRight" id="contentRightHeight">
       <div class="rightDownSel" id="test">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择油站</span></div>
                           <div class="seleContent">
                              <div class="downCont">
                                  <div class="downNav">
                                      <a href="javascript:void(0);" class="titleCur">城市</a>
                                      <a href="javascript:void(0);" onclick="queryAdministriveRegionBy()">行政区</a>
                                      <a href="javascript:void(0);" onclick="querySalesAreaBy()">销售区</a>
                                      <a href="javascript:void(0);" onclick="queryGasolineBy()">商圈类型</a>
                                      <a href="javascript:void(0);" onclick="queryLocationBy()">位置</a>
                                      <a href="javascript:void(0);" onclick="queryOpenDateBy()">开业时间</a>
                                      <a href="javascript:void(0);" onclick="queryStationBy()">站名</a>
                                  </div>

                                  <div class="downContInfo">
                                      <ul style="display: block;" id="citys">
                                      </ul>
                                      <ul id="regions">
                                      </ul>
                                      <ul id="sales">
                                      </ul>
                                      <ul id="gasolines">
                                      </ul>
                                      <ul id="location">
                                          
                                      </ul>
                                      <ul id="openDate">
                                         
                                      </ul>
                                      <ul id="station">
                                          
                                      </ul>
                                  </div>
                              </div>
                              <div class="screenMain" >
		                                  <ul id="tagContent">
		                                      
		                                  </ul>
		                       </div>
                              <div class="downOperation">
                                <a href="javascript:void(0);" class="determine" onclick="queryEvaluation()">确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                                <a href="javascript:void(0);" class="determine" onclick="ExportExcel()">导出到Excel</a>
                              </div>
                           </div>
                       </div>
                       <!-- 这是跟选择油站平级的 -->
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择人群</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                      <a href="javascript:void(0);" onclick="ChangePeople('all')" class="titleCur">全部人群</a>
                                      <a href="javascript:void(0); " onclick="ChangePeople('vip')">会员交易</a>
                                  </div>
                              </div>
                           </div>
                       </div>
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
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" name="start" style="width:300px" value="2017-08-14 14:45" class="am-form-field" id='evaluationstart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end" style="width:300px" value="2017-09-14 14:45" class="am-form-field" id='evaluationend'></div>
                                      </div>
                                      <script>
                                      $('#evaluationstart').attr("value",getNowFormatDateOne());
                                      $('#evaluationend').attr("value",getNowFormatDate());
											$('#evaluationstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
											$('#evaluationend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryEvaluation()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                        <br><br>
                                        <a href="javascript:void(0);" class="determine" onclick="ExportExcel()">导出到Excel</a>
                                      </div>
                                  </div>
                              </div>
                           </div>
                       </div>
                   </div>
               </div>
               <!-- 结束 -->
           </div>
       </div>
    </div>
    </form>
     <div id="evaluation" style="width:90%;height:90%;"></div>
     <div id="evaluationDistribution" style="width: 90%;height:90%;"></div>
     <div id="evals" style="width: 90%;height:90%;"></div>
     
     <script type="text/javascript">
     var myChartevaluation = echarts.init(document.getElementById('evaluation'));
     var myChartevaluationDistribution = echarts.init(document.getElementById('evaluationDistribution'));
     var myChart = echarts.init(document.getElementById('evals'));
     var lineStyle = {
				normal: {
					width: 1,
					opacity: 0.5
				}
			};
     $(function() {
		queryEvaluation();
	});
     function queryEvaluation () {
     	$.ajax({
				type:"POST",
				url:"/sysmanager/evaluation/queryTrends",
				dataType:"JSON",
				data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
					"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),
					"station":jqchk("station"),"start":$("#evaluationstart").val(),
					"end":$("#evaluationend").val(),"date":$("input[name='date']:checked").val(),
				},
				success:function(map){
	        		myChartevaluation.setOption(option = {
	        			    title: {
	        			        text: '评价得分趋势',
	        			        x:'center'
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
	  	                      data: ["总体满意度","油站环境","加油速度"]
	  	                  },
	  	                  yAxis: {
	  	                	  type:'value'
	  	                  },
	  	                  series: [{
	  	                      name: '平均分',
	  	                      type: 'bar',
	  	                      data: map.datas
	  	                  }
	  	      			]
	  	              });//绘制完Echarts
	  	            myChart.setOption(
	  	            		 {
	  	            		    title: {
	  	            		        text: '评价分数图'
	  	            		    },
	  	            		    tooltip: {},
	  	            		    legend: {
	  	            		        data: ['平均得分'],
	  	            		    },
	  	            		    radar: {
	  	            		        // shape: 'circle',
	  	            		        name: {
	  	            		            textStyle: {
	  	            		                color: '#fff',
	  	            		                backgroundColor: '#999',
	  	            		                borderRadius: 3,
	  	            		                padding: [3, 5],
	  	            		           },
	  	            		        },
	  	            		        indicator: [
									{name: '总体满意度', max: 5},
									{name: '油站环境', max: 5},
									{name: '加油速度', max: 5},
	  	            		        ],
	  	            		    },
	  	            		    series: [{
	  	            		        name: '评价平均分雷达图',
	  	            		        type: 'radar',
	  	            		        // areaStyle: {normal: {}},
	  	            		        data : [
	  	            		            {
	  	            		                value : map.datas,
	  	            		                name : '平均得分'
	  	            		            }
	  	            		        ]
	  	            		    }]
	  	            		}
	  	            	/* {
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
	    						{name: '油站环境', max: 5},
	    						{name: '加油速度', max: 5},
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
	    						data: map.data2,
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
	    			} */);//绘制完Echarts
				}//success 
     		});//ajax
		};
     </script>
    <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
		<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>