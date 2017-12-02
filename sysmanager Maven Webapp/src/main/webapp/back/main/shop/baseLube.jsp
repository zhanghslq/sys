<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>润滑油销售量</title>
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
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" readonly="readonly" style="width:300px" value="2017-08-14 14:45" class="am-form-field" id='lubestart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" readonly="readonly" style="width:300px" value="2017-09-14 14:45" class="am-form-field" id='lubeend'></div>
                                      </div>
                                      <script>
											$('#lubestart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
											$('#lubeend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryLubeAmount()()">确定</a>
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
    <div id="lubeMoneys" style="width:90%;height:50%;min-width: 800px;min-height: 600px"></div>
    <div id="lubeAvgMoney" style="width:90%;height:50%;min-width: 800px;min-height: 600px"></div>
    <div id="lubeNumber" style="width:90%;height:50%;min-width: 800px;min-height: 600px"></div>
    <script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
        var myChartlubeNumber = echarts.init(document.getElementById('lubeNumber'));
        var myChartlubeMoneys = echarts.init(document.getElementById('lubeMoneys'));
        var myChartlubeAvgMoney = echarts.init(document.getElementById('lubeAvgMoney'));
      //格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
         // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
         var basePeople="all";
      	function ChangePeople(src) {
			basePeople=src;
		}
		function queryLubeAmount(){
		$.ajax({
			type:"post",
			url:"/sysmanager/lube/queryLubes",
			dataType:"JSON",
			data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
				"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),
				"station":jqchk("station"),"start":$("#lubestart").val(),
				"end":$("#lubeend").val(),"date":$("input[name='date']:checked").val(),
				"people":basePeople
			},
			success:function(map){
				myChartlubeNumber.setOption({
				    title: {
				        text: '销售笔数'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
						itemWidth:5,
				        data:['销售笔数']
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
				        boundaryGap: true,
				        data: map.dates,
				    },
				    yAxis: {
				        type: 'value',
				        axisLabel: {
							formatter: '{value} 笔'
						}
				    },
				    series: [
				        {
				            name:'销售笔数',
				            type:'bar',
				            stack: '总量',
				            data:map.numbers
				        }
				    ]
				});
				myChartlubeMoneys.setOption({
				    title: {
				        text: '总销售额'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
						itemWidth:5,
				        data:['总销售额']
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
				        boundaryGap: true,
				        data: map.dates,
				    },
				    yAxis: {
				        type: 'value',
				        axisLabel: {
							formatter: '{value} 元'
						}
				    },
				    series: [
				        {
				            name:'总销售额',
				            type:'bar',
				            stack: '总量',
				            data:map.moneys
				        }
				    ]
				});
				myChartlubeAvgMoney.setOption({
				    title: {
				        text: '单笔消费额'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
						itemWidth:5,
				        data:['单笔消费额']
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
				        boundaryGap: true,
				        data: map.dates,
				    },
				    yAxis: {
				        type: 'value',
				        axisLabel: {
							formatter: '{value} 元'
						}
				    },
				    series: [
				        {
				            name:'单笔消费额',
				            type:'bar',
				            stack: '总量',
				            data:map.avgMoney
				        }
				    ]
				});//绘制完成Echarts
			}
		});
	}
    </script>
    <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
		<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>