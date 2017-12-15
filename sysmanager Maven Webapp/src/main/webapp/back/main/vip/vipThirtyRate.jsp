<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>新招募会员30天转化率</title>
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
                           <div class="selemenu"><span>选择周期</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                      <a href="javascript:void(0);" onclick="changeQuery('thirty')" class="titleCur">会员30天转化率</a>
                                      <a href="javascript:void(0); " onclick="changeQuery('sixty')">会员60天转化率</a>
                                  </div>
                              </div>
                           </div>
                       </div>
                       <!-- 这是跟选择油站平级的 -->
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择时间</span></div>
                           <div class="seleContent selTime">
                              <div class="downCont selTimeMain">
                                  <div class="selTimeInfo">
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" readonly="readonly" style="width:300px" value="2017-08-14 14:45" class="am-form-field" id='thirtyRatestart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" readonly="readonly" style="width:300px" value="2017-09-14 14:45" class="am-form-field" id='thirtyRateend'></div>
                                      </div>
                                      <script>
											$('#thirtyRatestart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#thirtyRateend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryThirtyRate()">确定</a>
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
   
    <div id="thirtyRate" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
   			var baseQuery="thirty";
   		 function changeQuery(src) {
			baseQuery=src;
		}
        // 基于准备好的dom，初始化echarts实例
        var myChartthirtyRate = echarts.init(document.getElementById('thirtyRate'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryThirtyRate() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipChannel/queryRate",
				dataType:"JSON",
				data:{"start":$("#thirtyRatestart").val(),
					"end":$("#thirtyRateend").val(),
					"query":baseQuery
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
        				}
        			]
        		});
        	
				}//success 
        	});//ajax
       }
        
    </script>
    <!-- 活跃会员人数及占比 -->
    
     <div id="compare" style="width:90%;height:60%;"></div>
    <div class="contentRight">
       <div class="rightDownSel" id="test">
          
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       <!-- 这是跟选择油站平级的 -->
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择月份</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav" id="LiveNessMonth">
                                     
                                  </div>
                              </div>
                           </div>
                       </div>
                       
                   </div>
               </div>
               
           </div>
       </div>
    </div>
    
    
    <div id="consumepie" style="width:90%;height:60%;"></div>
    <script type="text/javascript">
	    $.ajax({
			type:"GET",
			url:"/sysmanager/liveNess/queryAllDate",
			dataType:"JSON",
			success:function(result){
				$.each(result,function(i,station){
					 /* <a href="javascript:void(0);" onclick="ChangePeople('all')">全部人群</a> */
					var option = $("<a></a>").text(station).val(station).on("click",
							function () {
								queryLiveNessByDate(station);
							}
					);
					$("#LiveNessMonth").append(option);
				});
			}
		});
    $(function() {
    	queryLiveNess();
	});
        // 基于准备好的dom，初始化echarts实例
        var myChartcompare = echarts.init(document.getElementById('compare'));
        var myChartconsumepie = echarts.init(document.getElementById('consumepie'));
        // 指定图表的配置项和数据
        function queryLiveNessByDate(src) {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/liveNess/queryDataByDate",
				dataType:"JSON",
				data:{"month":src},
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
	    				color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88',
 						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
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
	        			color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88',
 						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
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
    <div class="contentRight" >
       <div class="rightDownSel">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       
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
                                        </div>
                                      </div>
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" readonly="readonly" style="width:300px" value="2017-08-14 14:45" class="am-form-field" id='drainstart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" readonly="readonly" style="width:300px" value="2017-09-14 14:45" class="am-form-field" id='drainend'></div>
                                      </div>
                                      <script>
											$('#drainstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#drainend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryDrain()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                      </div>
                                  </div>
                              </div>
                           </div>
                       </div>
                   </div>
               </div>
               <div class="downDetails"><!-- 2 -->
                 
               </div>
               <div class="downDetails">3</div>
               <div class="downDetails">4</div>
           </div>
       </div>
    </div>
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
				data:{"start":$("#drainstart").val(),
					"end":$("#drainend").val(),"date":$("input[name='date']:checked").val(),
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
						color:['#89CFDC','#009EB4','#003C88',
 						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
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
  <div class="contentRight">
       <div class="rightDownSel" >
          
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       <!-- 这是跟选择油站平级的 -->
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择月份</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav" id="funnelMonth">
                                     
                                  </div>
                              </div>
                           </div>
                       </div>
                       
                   </div>
               </div>
               
           </div>
       </div>
    </div>
  	
    <div id="vipFunnel" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    $.ajax({
		type:"GET",
		url:"/sysmanager/vipFunnel/queryAllMonth",
		dataType:"JSON",
		success:function(result){
			$.each(result,function(i,station){
				var option = $("<a></a>").text(station).val(station).on("click",
						function () {
					queryVipFunnel(station);
				}
		);
				$("#funnelMonth").append(option);
			});
		}
	});
        // 基于准备好的dom，初始化echarts实例
        var myChartvipFunnel = echarts.init(document.getElementById('vipFunnel'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryVipFunnel(month) {
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipFunnel/queryVipFunnel",
				dataType:"JSON",
				data:{"month":month},
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
        			    color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88',
 						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
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
<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>