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
</head>
<body>
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
<script type="text/javascript">
function jqchk(chkName){ //jquery获取复选框值 
	var chk_value =[]; 
	$("input[name='"+chkName+"']:checked").each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	return chk_value;
}
function queryAdministriveRegionBy() {
		$("#regions").empty();
		$.ajax({
			type:"POST",
			url:"/sysmanager/station/queryAdministriveRegionBy",
			dataType:"JSON",
			data:{"citys":jqchk("citys")},
			success:function(result){
				$.each(result,function(i,region){
					var option="<li><input name='regions' value="+region+" type='checkbox' id='checktwo_"+i+"' class='default'><label for='checktwo_"+i+"'></label><span>"+region+"</span></li>";
					$("#regions").append(option);
				});
			}
		});
	}
function querySalesAreaBy() {
	$("#sales").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/querySalesAreaBy",
		dataType:"JSON",
		data:{"citys":jqchk("citys"),"regions":jqchk("regions")},
		success:function(result){
			$.each(result,function(i,sale){
				var option="<li><input name='sales' value="+sale+" type='checkbox' id='checksale_"+i+"' class='default'><label for='checksale_"+i+"'></label><span>"+sale+"</span></li>";
				$("#sales").append(option);
			});
		}
	});
}     
function queryGasolineBy() {
	$("#gasolines").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/queryGasolineBy",
		dataType:"JSON",
		data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk(sales)},
		success:function(result){
			$.each(result,function(i,gasoline){
				var option="<li><input name='gasolines' value="+gasoline+" type='checkbox' id='checkgasoline_"+i+"' class='default'><label for='checkgasoline_"+i+"'></label><span>"+gasoline+"</span></li>";
				$("#gasolines").append(option);
			});
		}
	});
}     
function queryLocationBy() {
	$("#location").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/queryLocationBy",
		dataType:"JSON",
		data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk(sales),"gasoline":jqchk("gasolines")},
		success:function(result){
			$.each(result,function(i,location){
				var option="<li><input name='location' value="+location+" type='checkbox' id='checklocation_"+i+"' class='default'><label for='checklocation_"+i+"'></label><span>"+location+"</span></li>";
				$("#location").append(option);
			});
		}
	});
}
function queryOpenDateBy() {
	$("#openDate").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/queryOpenDateBy",
		dataType:"JSON",
		data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
			"gasoline":jqchk("gasolines"),"locs":jqchk("location")},
		success:function(result){
			$.each(result,function(i,openDate){
				var option="<li><input name='openDate' value="+openDate+" type='checkbox' id='checkopenDate_"+i+"' class='default'><label for='checkopenDate_"+i+"'></label><span>"+openDate+"</span></li>";
				$("#openDate").append(option);
			});
		}
	});
}
function queryStationBy() {
	$("#station").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/queryStationBy",
		dataType:"JSON",
		data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
			"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate")},
		success:function(result){
			$.each(result,function(i,station){
				var option="<li><input name='station' value="+station.id+" type='checkbox' id='checkstation_"+i+"' class='default'><label for='checkstation_"+i+"'></label><span>"+station.name+"</span></li>";
				$("#station").append(option);
			});
		}
	});
}
	$(function(){
		$.ajax({
			type:"GET",
			url:"/sysmanager/station/queryAllCity",
			dataType:"JSON",
			success:function(result){
				$.each(result,function(i,city){
					var option="<li><input name='citys' type='checkbox' value='"+city+"' id='checkone_"+i+"' class='default'><label for='checkone_"+i+"'></label><span>"+city+"</span></li>";
					$("#citys").append(option);
				});
			}
		});
		
	});
	
</script>
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
                              <div class="downOperation">
                                <a href="javascript:void(0);" class="determine">确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                              </div>
                           </div>
                       </div>
                       <!-- 这是跟选择油站平级的 -->
                       <!-- <div class="selemeTitle">
                           <div class="selemenu"><span>选择人群</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                      <a href="javascript:void(0);" onclick="ChangePeople('all')" class="titleCur">全部人群</a>
                                      <a href="javascript:void(0); " onclick="ChangePeople('vip')">会员交易</a>
                                  </div>
                              </div>
                           </div>
                       </div> -->
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
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" readonly="readonly" style="width:300px" value="2017-08-14 14:45" class="am-form-field" id='oilzoushistart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" readonly="readonly" style="width:300px" value="2017-09-14 14:45" class="am-form-field" id='oilzoushiend'></div>
                                      </div>
                                      <script>
											$('#ratestart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
											$('#rateend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryByOils()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
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
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="oilzoushi" style="width:90%;height:60%;min-height: 800px"></div>
    
    <script type="text/javascript">
   
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
	function queryByOils(){
		queryFromController();
		$.ajax({
			type:"post",
			url:"/sysmanager/oil/queryByOils",
			dataType:"JSON",
			data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
				"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),
				"station":jqchk("station"),
				"start":$("#oilzoushistart").val(),
				"end":$("#oilzoushiend").val(),"date":$("input[name='date']:checked").val(),
			},
			success:function(map){
					 myChartqueryByOils.setOption({
						 title: {
				                text: '各标号燃油销售量对比'
				            },
							tooltip : {
								trigger: 'axis',
								axisPointer : {            // 坐标轴指示器，坐标轴触发有效
									type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
								}
							},
							legend: {
								data:map.allName
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
								}
							]
						});
					
			}
		});
	}
    </script>
    <!-- 油品销量占比 -->
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="basezhanbi" style="width: 90%;height:60%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChartbasezhanbi = echarts.init(document.getElementById('basezhanbi'));
        function queryFromController() {
        	$.ajax({
    			type:"post",
    			url:"/sysmanager/oil/queryzhanbi",
    			dataType:"JSON",
    			data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
    				"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),
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
    					legend: {
    						orient: 'vertical',
    						left: 'left',
    						data: ['92#','0#','95#',]
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
    <!--油价  -->
    <form action="">
		  请选择开始时间段：<input id="pricestart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2017-08-1 2:3" style="width:150px"> 
		  请选择结束时间段：<input id="priceend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="2017-10-1 2:3" style="width:150px"> 
				
		   选择油站：<select name="station" id="pricestation">
		    	</select>
		 查询油品：<select name="oilName" id="priceoilName">
		       			
		    	</select>
		    <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryPrice()">查询</a>
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="price" style="width:90%;height:60%;min-height: 800px"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var price = echarts.init(document.getElementById('price'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        // 指定图表的配置项和数据
        $.ajax({
				type:"GET",
				url:"/sysmanager/city/queryAll",
				dataType:"JSON",
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#pricestation").append(option);
					});
				}
			});
		$(function(){
			queryOilName();
		});
        function queryOilName() {
   		 $("#priceoilName").empty();
   		 $.ajax({
   				type:"POST",
   				url:"/sysmanager/oilPrice/queryAllName",
   				dataType:"JSON",
   				success:function(result){
   					$.each(result,function(i,station){
   						var option = $("<option></option>").text(station);
   						$("#priceoilName").append(option);
   					});
   				}
   			});
   		}
        function queryPrice(){
    		$.ajax({
    			type:"post",
    			url:"/sysmanager/oilPrice/queryPrice",
    			dataType:"JSON",
    			data:{"station":$("#pricestation").val(),"start":$("#pricestart").datetimebox("getValue"),
    				"end":$("#priceend").datetimebox("getValue"),"oilName":$("#priceoilName").val()
    			},
    			success:function(map){
    				price.setOption({
    				            title: {
    				                text: '各标号油价'
    				            },
    				            tooltip: {
    				            	trigger: 'axis'
    				            },
    				            legend: {
    				                data:[{
    									name: '油价'
    								}]
    				            },
    				            xAxis: {
    				                data: map.dates
    				            },
    				            yAxis: {},
    				            series: [{
    				                name: '油价',
    				                type: 'line',
    				                data: map.prices
    				            }]
    				        });
    					
    			}
    		});
    	}
        // 使用刚指定的配置项和数据显示图表。
    </script>
    <!-- 劳动生产率 -->
    <form action="">
		  请选择开始时间段：	<input id="productstart" class="easyui-datetimebox"
		        data-options="required:true,showSeconds:false" value="2016-10-01 0:0" style="width:150px"> 
		  请选择结束时间段：<input id="productend" class="easyui-datetimebox"  
		        data-options="required:true,showSeconds:false" value="2017-10-10 0:0" style="width:150px">
				
		    选择油站：<select name="station" id="productstation">
		       			
		    		</select>
		  <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryProduct()">查询</a>  
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="product" style="width:95%;height:60%;"></div>
    <script type="text/javascript">
   
    // 基于准备好的dom，初始化echarts实例
        var myChartPRODUCT = echarts.init(document.getElementById('product'));
      	//格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
        // 指定图表的配置项和数据
    		 $.ajax({
					type:"POST",
					url:"/sysmanager/station/queryAll",
					dataType:"JSON",
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#productstation").append(option);
						});
					}
				});
        // 使用刚指定的配置项和数据显示图表。
	function queryProduct(){
		$.ajax({
			type:"post",
			url:"/sysmanager/product/queryProduct",
			dataType:"JSON",
			data:{"station":$("#productstation").val(),"start":$("#productstart").datetimebox("getValue"),
				"end":$("#productend").datetimebox("getValue")
			},
			success:function(map){
				myChartPRODUCT.setOption({
						    title: {
						        text: '劳动生产率'
						    },
						    tooltip: {
						        trigger: 'axis'
						    },
						    legend: {
								itemWidth:5,
						        data:['劳动生产率']
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
						            name:'劳动生产率',
						            type:'line',
						            stack: '总量',
						            data:map.data
						        }
						    ]
						});
					
			}
		});
	}
    </script>
        <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>