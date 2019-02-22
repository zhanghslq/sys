<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>分油品走势图</title>
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
<form action="" method="post" id="exportExcel">
<div class="contentRight" id="contentRightHeight">
<div class="timeEndIng" id="dataTime"></div>
     	<script type="text/javascript">
	     	$.ajax({
				type:"GET",
				url:"/sysmanager/time/queryTime",
                data:{"name":"res_oilb"},
				success:function(map){
					$("#dataTime").html("数据截止时间："+map);
				}
	     	});
     	</script>
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
                                      <a href="javascript:void(0);" onclick="queryTypeBy()">油站类型</a>
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
                                      <ul id="types">
                                         
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
                                <a href="javascript:void(0);" onclick="queryByOils()" class="determine">确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                                 <a href="javascript:void(0);"  class="determine" onclick="ExportExcel()">导出到Excel</a>
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
                                          <label><input name="date" type="radio" value="year" /> <i>按年展示</i> </label>
                                          <label><input name="date" type="radio" value="month" /> <i>按月展示</i> </label>
                                          <label><input name="date" type="radio" value="day" checked="checked"/> <i>按日展示</i> </label>
                                          <label><input name="date" type="radio" value="hour" /><i>按小时展示</i> </label>
                                          <label><input name="date" type="radio" value="minute" /><i>按分钟展示</i> </label>
                                        </div>
                                      </div>
                                    <div class="minimum">
                                        <em>工作日选择</em>
                                        <div class="minimumRadio">
                                          <label><input name="week" type="checkbox" value="2" /> <i>周一</i> </label>
                                          <label><input name="week" type="checkbox" value="3" /> <i>周二</i> </label>
                                          <label><input name="week" type="checkbox" value="4" /> <i>周三</i> </label>
                                          <label><input name="week" type="checkbox" value="5" /> <i>周四</i> </label>
                                          <label><input name="week" type="checkbox" value="6" /> <i>周五</i> </label>
                                          <label><input name="week" type="checkbox" value="7" /> <i>周六</i> </label>
                                          <label><input name="week" type="checkbox" value="1" /> <i>周日</i> </label>
                                        </div>
                                      </div>
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" name="start" style="width:300px"  class="am-form-field" id='oilzoushistart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end" style="width:300px"  class="am-form-field" id='oilzoushiend'></div>
                                      </div>
                                      <script>
											$('#oilzoushistart').attr("value",getNowFormatDateOne());
											$('#oilzoushistart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#oilzoushiend').attr("value",getNowFormatDate());
											$('#oilzoushiend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryByOils()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                        <a href="javascript:void(0);"  class="determine" onclick="ExportExcel()">导出到Excel</a>
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
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="oilzoushi" style="width:90%;height:60%;min-height: 800px"></div>
    
    <script type="text/javascript">
    function ExportExcel() {
    	$("#exportExcel").attr("action","/sysmanager/oil/exportByOils");
 	   	$("#exportExcel").submit();
    }
    // 基于准备好的dom，初始化echarts实例
        var myChartqueryByOils = echarts.init(document.getElementById('oilzoushi'));
      //格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
         // 指定图表的配置项和数据
         
        // 使用刚指定的配置项和数据显示图表。
    /*  $(function () {
    	 queryrong();
    	 test();
    	 queryPrice();
    	 queryProduct();
	});  */
	$(function() {
		queryByOils();
	});
	function queryByOils(){
		queryByOilsAmountAndSingle();
		queryFromController();
		$.ajax({
			type:"post",
			url:"/sysmanager/oil/queryByOils",
			dataType:"JSON",
			data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
				"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),
				"type":jqchk("type"),
				"station":jqchk("station"),"week":jqchk("week"),
				"start":$("#oilzoushistart").val(),
				"end":$("#oilzoushiend").val(),"date":$("input[name='date']:checked").val(),
			},
			success:function(map){
					 myChartqueryByOils.setOption({
						 title: {
				                text: '各标号燃油销售量对比',
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
								data:map.allName
							},
							toolbox: {
								show : true,
								 right:18,
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
     							top:'15%'
     						},
							xAxis : [
								{
									type : 'category',
									data : map.dates
								}
							],
							yAxis : [
								{
									type : 'value'
								}
							],
							series : [
								{
									name:'0#',
									type:'bar',
									stack: '总量',
									data:map.litre0,
									 itemStyle:{  
		                                    normal:{color:'#7F7F7F'}  
		                                } 
						
								},
								{
									name:'-10#',
									type:'bar',
									stack: '总量',
									data:map.litre10,
									itemStyle:{  
	                                    normal:{color:'#A6A6A6'}  
	                                } 
								},
								{
									name:'-20#',
									type:'bar',
									stack: '总量',
									data:map.litre20,
									itemStyle:{  
	                                    normal:{color:'#595959'}  
	                                } 
								},
								{
									name:'92#',
									type:'bar',
									stack: '总量',
									data:map.litre92,
									itemStyle:{  
	                                    normal:{color:'#DD1D21'}  
	                                } 
								},
								{
									name:'95#',
									type:'bar',
									stack: '总量',
									data:map.litre95,
									itemStyle:{  
	                                    normal:{color:'#008433'}  
	                                } 
								},
								{
									name:'97#',
									type:'bar',
									stack: '总量',
									data:map.litre97,
									itemStyle:{  
	                                    normal:{color:'#009EB4'}  
	                                }
								},
								{
									name:'98#',
									type:'bar',
									stack: '总量',
									data:map.litre98,
									itemStyle:{
	                                    normal:{color:'#003C88'}
	                                }
								},
								{
									name:'80#',
									type:'bar',
									stack: '总量',
									data:map.litre80,
									itemStyle:{
	                                    normal:{color:'#BA95BE'}
	                                }
								},
                                {
                                    name:'-35#',
                                    type:'bar',
                                    stack: '总量',
                                    data:map.litre35,
                                    itemStyle:{
                                        normal:{color:'#641964'}
                                    }
                                }
							]
						});
					
			}
		});
	}
    </script>
    <!-- 油品销量占比 -->
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="basezhanbi" style="width: 80%;height:60%;min-height: 600px;min-width: 800px"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChartbasezhanbi = echarts.init(document.getElementById('basezhanbi'));
        function queryFromController() {
        	$.ajax({
    			type:"post",
    			url:"/sysmanager/oil/queryzhanbi",
    			dataType:"JSON",
    			data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
    				"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),"type":jqchk("type"),
    				"station":jqchk("station"),
    				"start":$("#oilzoushistart").val(),
    				"end":$("#oilzoushiend").val()
    			},
    			success:function(map){
    				myChartbasezhanbi.setOption({
    					title : {
    						text: '各油品销量占比',
    						x:'center'
    					},
    					tooltip : {
    						trigger: 'item',
    						formatter: "{a} <br/>{b} : {c} ({d}%)"
    					},
    					color:['#008433','#DD1D21','#7F7F7F','#595959','#A6A6A6','#BA95BE','#641964',],
    					legend: {
    						top:30,
    						data: ['95#','92#','0#','-20#','-10#','98#','80#']
    					},
    					series : [
    						{
    							name: '油品占比',
    							type: 'pie',
    							radius : '55%',
    							center: ['50%', '60%'],
    							data:map,
    							itemStyle: {
    								emphasis: {
    									shadowBlur: 10,
    									shadowOffsetX: 0,
    									shadowColor: 'rgba(0, 0, 0, 0.5)'
    								}
    							}
    						}
    					]
    				});//Echarts
    					
    			}
    		});
			
		}
        
        // 指定图表的配置项和数据
    </script>
    <!-- 分油品的销量，与单车加油量 -->
    <div class="contentRight" >
       <div class="rightDownSel">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       <!-- 这是跟选择油站平级的 -->
                         <!-- 这是跟选择油站平级的 -->
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择油品</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav" id="AllOils">
                                  		
                                  </div>
                              </div>
                           </div>
                       </div>
                       <a class="export" onclick="exportByOilsAllAndVip()">导出到Excel</a>
                   </div>
               </div>
               <!-- 结束 -->
           </div>
       </div>
    </div>
    <div style="height: 80%;width: 80%;min-height: 600px" id="byOilsAmount"></div><!-- 销量的需要总的，会员的以及非会员的对比 -->
    <div style="height: 80%;width: 80%;min-height: 600px" id="byOilsSingle"></div><!-- 单车的 -->
    <script type="text/javascript">
    function exportByOilsAllAndVip() {
    	$("#exportExcel").attr("action","/sysmanager/oil/exportOilAndVipByOils?oils="+""+baseOils+"");
 	   	$("#exportExcel").submit();
	}
    $.ajax({
		type:"GET",
		async:false,
		url:"/sysmanager/oil/queryAllName",
		dataType:"JSON",
		success:function(result){
			$.each(result,function(i,oils){
				var option = $("<a></a>").text(oils).val(oils).on('click',function(){
					ChangeOils(oils);
				});
				$("#AllOils").append(option);
			});
		}
	});
    var baseOils="92#";//这是为了算分油品的销量与单车加油量
    function ChangeOils(oils) {
		baseOils=oils;
		queryByOilsAmountAndSingle();
	}
    var byOilsAmount = echarts.init(document.getElementById('byOilsAmount'));
    var byOilsSingle = echarts.init(document.getElementById('byOilsSingle'));
    $(function() {
		queryByOilsAmountAndSingle();
	});
    function queryByOilsAmountAndSingle() {
    	 $.ajax({
				type:"POST",
				url:"/sysmanager/oil/queryAndVipByOils",
				async:false,
				data:{"oils":baseOils,"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
					"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),
					"type":jqchk("type"),
					"station":jqchk("station"),"week":jqchk("week"),
					"start":$("#oilzoushistart").val(),
					"end":$("#oilzoushiend").val(),"date":$("input[name='date']:checked").val()},
				dataType:"JSON",
				success:function(map){
					byOilsAmount.setOption({
					    title: {
					        text: '总销量',
					        x:'center'
					    },
					    tooltip: {
					        trigger: 'axis',
					        formatter: '{b}<br>总消费: {c}千升 <br> 会员消费:{c1}千升 <br> 非会员消费:{c2}千升'
					    },
					    legend: {
					    	top:30,
							itemWidth:5,
					        data:['总销量','会员消费','非会员消费']
					    },
					    color:['#FBCE07','#DD1D21','#89CFDC'],
					    toolbox: {
					        show : true,
					        right:18,
					        feature : {
					            dataView : {show: true, readOnly: false},
					            magicType : {show: true, type: ['line', 'bar']},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    grid: {
					    	top:'10%',
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis: {
					        type: 'category',
					        boundaryGap: true,
					        data: map.dates,
					    },
					    yAxis: {
					        type: 'value',
					        axisLabel: {
								formatter: '{value} 千升'
							}
					    },
					    series: [
					        {
					            name:'总销量',
					            type:'bar',
					            data:map.amounts
					        },
					        {
					            name:'会员消费',
					            type:'line',
					            data:map.vipamounts
					        },
					        {
					            name:'非会员消费',
					            type:'line',
					            data:map.notvipamounts
					        }
					    ]
					});
					byOilsSingle.setOption({
					    title: {
					        text: '单车加油量',
					        x:'center'
					    },
					    tooltip: {
					        trigger: 'axis'
					    },
					    color:['#FBCE07','#DD1D21','#89CFDC'],
					    legend: {
					    	top:'30',
							itemWidth:5,
					        data:['单车加油量','会员消费','非会员消费']
					    },
					    toolbox: {
					        show : true,
					        right:18,
					        feature : {
					            dataView : {show: true, readOnly: false},
					            magicType : {show: true, type: ['line', 'bar']},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    grid: {
					    	top:'10%',
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis: {
					        type: 'category',
					        boundaryGap: true,
					        data: map.dates,
					    },
					    yAxis: {
					        type: 'value',
					        axisLabel: {
								formatter: '{value} 升'
							}
					    },
					    series: [
					        {
					            name:'单车加油量',
					            type:'bar',
					            data:map.avgAmounts
					        },
					        {
					            name:'会员消费',
					            type:'line',
					            data:map.vipavgAmounts
					        },
					        {
					            name:'非会员消费',
					            type:'line',
					            data:map.notvipavgAmounts
					        }
					    ]
					});
				}//Success
    	 });
	}
    </script>
    <!--油价  -->
    <form action="" method="post" id="priceexport">
    <div class="contentRight" >
       <div class="rightDownSel">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       <!-- 这是跟选择油站平级的 -->
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择油站</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav" id="pricestation">
                                  
                                  </div>
                              </div>
                           </div>
                       </div>
                         <!-- 这是跟选择油站平级的 -->
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择油品</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav" id="priceoilName">
                                  
                                  </div>
                              </div>
                           </div>
                       </div>
                       <div style="display: none;">
                       	<input name="station" value="" id="priceStationva">
                       	<input name="oilName" value="" id="priceOilName">
                       </div>
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择时间</span></div>
                           <div class="seleContent selTime">
                              <div class="downCont selTimeMain">
                                  <div class="selTimeInfo">
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input name="start" size="16"  style="width:300px" value="2017-09-01 14:45" class="am-form-field" id='pricestart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end"  style="width:300px"  class="am-form-field" id='priceend'></div>
                                      </div>
                                      <script>
                                     /*  $('#pricestart').attr("value",getNowFormatDateOne()); */
                                      $('#pricestart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
                                      $('#priceend').attr("value",getNowFormatDate());
											$('#priceend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryPrice()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                        <a href="javascript:void(0);" class="determine" onclick="exportPrice()">导出到Excel</a>
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
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="price" style="width:90%;height:60%;min-height: 800px"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        function exportPrice() {
        	$("#priceStationva").attr("value",baseStation);
        	$("#priceOilName").attr("value",baseOil);
			$("#priceexport").attr("action","/sysmanager/oilPrice/exportPrice");
			$("#priceexport").submit();
		}
        var price = echarts.init(document.getElementById('price'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        // 指定图表的配置项和数据
        $("#pricestation").empty();
        $.ajax({
				type:"GET",
				async:false,
				url:"/sysmanager/city/queryAll",
				dataType:"JSON",
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<a></a>").text(station.name).val(station.id).on('click',function(){
							ChangeStation(station.id);
						});
						$("#pricestation").append(option);
					});
				}
			});
		var baseStation="50001";
		function ChangeStation(src){
			baseStation=src;
			queryPrice();
		}
		
		var baseOil="92#";
	     function ChangeOilName(src) {
			baseOil=src;
			queryPrice();
		 }
   		 $("#priceoilName").empty();
   		 $.ajax({
   				type:"POST",
   				url:"/sysmanager/oilPrice/queryAllName",
   				async:false,
   				dataType:"JSON",
   				success:function(result){
   					$.each(result,function(i,oil){
   						var option = $("<a></a>").text(oil).val(oil).on('click',function(){
							ChangeOilName(oil);
						});
   						$("#priceoilName").append(option);
   					});
   				}
   			});
	   	$(function() {
			queryPrice();
		});
        function queryPrice(){
    		$.ajax({
    			type:"post",
    			url:"/sysmanager/oilPrice/queryPrice",
    			dataType:"JSON",
    			data:{"station":baseStation,"start":$("#pricestart").val(),
    				"end":$("#priceend").val(),"oilName":baseOil
    			},
    			success:function(map){
    				price.setOption({
    							    title: {
    							        text: '各标号油价调整情况'
    							    },
    							    tooltip: {
    							        trigger: 'axis'
    							    },
    							    legend: {
    							        data:['油价']
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
    							        data: map.dates
    							    },
    							    yAxis: {
    							        type: 'value'
    							    },
    							    series: [
    							        {
    							            name:'油价',
    							            type:'line',
    							            step: 'end',
    							            data:map.prices
    							        }
    							    ]
    							});
    			}
    		});
    	}
        // 使用刚指定的配置项和数据显示图表。
</script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>