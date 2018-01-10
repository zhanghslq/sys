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
    <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <form action="" method="post" id="exportExcel">
<div class="contentRight">
       <div class="rightDownSel">
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
                       <!-- 这是跟选择油站平级的 -->
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择时间</span></div>
                           <div class="seleContent selTime">
                              <div class="downCont selTimeMain">
                                  <div class="selTimeInfo">
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" name="start"  style="width:300px"  class="am-form-field" id='thirtyRatestart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end"  style="width:300px"  class="am-form-field" id='thirtyRateend'></div>
                                      </div>
                                      <script>
	                                      $('#thirtyRatestart').attr("value",getLastFormatDateOne());
	                                      $('#thirtyRateend').attr("value",getLastFormatDate());
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
                                        <a href="javascript:void(0);" class="determine" onclick="ExportExcel()">导出到Excel</a>
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
    <div id="thirtyRate" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    function ExportExcel() {
    	$("#exportExcel").attr("action","/sysmanager/vipChannel/exportRate?area="+baseArea+"&query="+baseQuery);
 	   	$("#exportExcel").submit();
    }
    var baseArea="BJSHELL";
    function ChangeArea(src) {
    	baseArea=src;
    	//顺便查询更新
    	queryThirtyRate();
    	queryLiveNess();
    	queryDrain();
    	queryLiveNessByDate(getNowMonth());
    	queryVipFunnel(getLastMonth());
    }
   		var baseQuery="thirty";
   		 function changeQuery(src) {
			baseQuery=src;
		}
        // 基于准备好的dom，初始化echarts实例
        var myChartthirtyRate = echarts.init(document.getElementById('thirtyRate'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        $(function() {
			queryThirtyRate();
		});
        function queryThirtyRate() {
        	queryLiveNess();
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipChannel/queryRate",
				dataType:"JSON",
				data:{"start":$("#thirtyRatestart").val(),
					"end":$("#thirtyRateend").val(),
					"query":baseQuery,"area":baseArea
					},
				success:function(map){
        		myChartthirtyRate.setOption({
        			title:{
        				text:'日新增会员转化率',
        				x:'center'
        			},
        			tooltip : {
        				trigger: 'axis',
        				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        				},
        				formatter: '{b} : {c}%'
        			},
        			legend: {
        				top:30,
        				data:['日新增会员转化率']
        			},
        			toolbox: {
        				right:18,
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
        			grid:{
        				top:'10%'
        			},
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
    
     <div id="compare" style="width:80%;height:80%;"></div>
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
    
    
    <div id="consumepie" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
	    $.ajax({
			type:"GET",
			url:"/sysmanager/liveNess/queryAllDate",
			async:false,
			dataType:"JSON",
			data:{"area":baseArea},
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
    	queryLiveNessByDate(getNowMonth());
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
				data:{"month":src,"area":baseArea},
				success:function(map){
					myChartconsumepie.setOption({
	    				title : {
	    					text: '消费频次占比图',
	    					x:'center'
	    				},
	    				tooltip : {
	    					trigger: 'item',
	    					formatter: "{a} <br/>{b} : {c} ({d}%)"
	    				},
	    				legend: {
							top:30,
	    					data: ['未消费的','消费一次的','消费两次的','消费三次的','消费四次的','消费五次的','五次以上的']
	    				},
	    				color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88',
 						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
	    				grid:{
	    					top:'10%'
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
				data:{"area":baseArea},
				success:function(map){
					myChartcompare.setOption({
	        			title : {
	    					text: '消费频次趋势图',
	    					x:'center'
	    				},
	        			tooltip : {
	        				trigger: 'axis',
	        				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	        					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        				}
	        			},
	        			legend: {
	        				top:30,
	        				data:['未消费的', '消费一次的','消费两次的','消费三次的','消费四次的','消费五次的','五次以上的']
	        			},
	        			color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88',
 						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
	        			
	        			calculable : true,
	        			grid:{
	        				top:'10%'
	        			},
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
                                        <div class="startTime"><span>选择开始时间</span> <input size="16"  style="width:300px"  class="am-form-field" id='drainstart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16"  style="width:300px"  class="am-form-field" id='drainend'></div>
                                      </div>
                                      <script>
                                      $('#drainstart').attr("value",getNowFormatDateOne());
                                      $('#drainend').attr("value",getNowFormatDate());
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
              
           </div>
       </div>
    </div>
    <div id="vipDrain" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
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
					"area":baseArea
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
							right:18,
							feature: {
								dataView: {show: true, readOnly: false},
								magicType: {show: true, type: ['line', 'bar']},
								restore: {show: true},
								saveAsImage: {show: true}
							}
						},
						legend: {
							top:50,							
							data:['流失会员人数','流失人数占比'],
						},
						grid:{
							top:'10%'
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
		type:"POST",
		url:"/sysmanager/vipFunnel/queryAllMonth",
		data:{"area":baseArea},
		async:false,
		dataType:"JSON",
		success:function(result){
			$.each(result,function(i,month){
				var option = $("<a></a>").text(month).val(month).on("click",
						function () {
					queryVipFunnel(month);
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
        function getLastMonth() {
		    var date = new Date();
		    var year=date.getFullYear();
		    var month = date.getMonth();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if(month==0)  
			{  
			    month=12;  
			    year=year-1;  
			}  
		    var currentdate = year+"-"+ month;
		    return currentdate;
		}
        $(function() {
			queryVipFunnel(getLastMonth());
		});
        function queryVipFunnel(month) {
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipFunnel/queryVipFunnel",
				dataType:"JSON",
				data:{"month":month,"area":baseArea},
				success:function(map){
        			myChartvipFunnel.setOption({
        			    title: {
        			        text: '会员活跃情况',
        			        x:'center'
        			    },
        			    tooltip: {
        			        trigger: 'item',
        			    },
        			    toolbox: {
        			    	right:18,
        			        feature: {
        			            dataView: {readOnly: false},
        			            restore: {},
        			            saveAsImage: {}
        			        }
        			    },
        			    legend: {
        			    	top:30,
        			        data: ['会员总数','至少消费一次的','活跃会员']
        			    },
        			    color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88',
 						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
        			    calculable: true,
        			    grid:{
        			    	top:'10%'
        			    },
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
<script type="text/javascript">downTab();rightDown();</script>
</body>
</html>