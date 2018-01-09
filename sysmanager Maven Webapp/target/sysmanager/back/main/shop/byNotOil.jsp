<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>分类别走势图</title>
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
<form action="" method="post" id="exportExcel">
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
                                <a href="javascript:void(0);" class="determine" onclick="queryByNotOil()">确定</a>
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
                                          <label><input name="date" type="radio" value="year" /> <i>按年展示</i> </label>
                                          <label><input name="date" type="radio" value="month" /> <i>按月展示</i> </label>
                                          <label><input name="date" type="radio" value="day" checked="checked"/> <i>按日展示</i> </label>
                                          <label><input name="date" type="radio" value="hour" /> <i>按小时展示</i> </label>
                                          <label><input name="date" type="radio" value="minute" /> <i>按分钟展示</i> </label>
                                        </div>
                                      </div>
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" name="start" style="width:300px" value="2017-08-14 14:45" class="am-form-field" id='byNotOilstart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end" style="width:300px" value="2017-09-14 14:45" class="am-form-field" id='byNotOilend'></div>
                                      </div>
                                      <script>
                                      $('#byNotOilstart').attr("value",getNowFormatDateOne());
                                      $('#byNotOilend').attr("value",getNowFormatDate());
											$('#byNotOilstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#byNotOilend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryByNotOil()">确定</a>
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
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="byNotOil" style="width:80%;height:80%; min-height: 600px"></div>
    <script type="text/javascript">
    function ExportExcel() {
    	$("#exportExcel").attr("action","/sysmanager/notOil/exportByDepartmentName?people="+basePeople);
 	   	$("#exportExcel").submit();
    }
  
   var basePeople="all";
 	function ChangePeople(src) {
		basePeople=src;
	}
   
    // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('byNotOil'));
      //格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
         // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
    $(function() {
		queryByNotOil();
	});
	function queryByNotOil(){
		queryTop();
		$.ajax({
			type:"post",
			url:"/sysmanager/notOil/queryByDepartmentName",
			dataType:"JSON",
			data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
				"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),
				"station":jqchk("station"),"date":$("input[name='date']:checked").val(),
				"start":$("#byNotOilstart").val(),
				"end":$("#byNotOilend").val(),
				"people":basePeople
			},
			success:function(map){
					 myChart.setOption({
						 title: {
				                text: '分类别销售趋势',
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
								data:map.name
							},
							 grid: {
							        top:'15%',
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
									type : 'value',
									min:0
								},
								
							],
							series : [
								{
									name:'店内服务',
									type:'bar',
									stack: '总量',
									data:map.instoreMoney,
									 itemStyle:{  
		                                    normal:{color:'#FBCE07'}
		                                } 
						
								},
								{
									name:'快餐产品',
									type:'bar',
									stack: '总量',
									data:map.fastfoodMoney,
									itemStyle:{  
	                                    normal:{color:'#DD1D21'}  
	                                } 
								},
								{
									name:'易腐产品',
									type:'bar',
									stack: '总量',
									data:map.perishableMoney,
									itemStyle:{  
	                                    normal:{color:'#89CFDC'}  
	                                } 
								},
								{
									name:'润滑油',
									type:'bar',
									stack: '总量',
									data:map.lubeMoney,
									itemStyle:{  
	                                    normal:{color:'#009EB4'}  
	                                } 
								},
								{
									name:'烟草',
									type:'bar',
									stack: '总量',
									data:map.cigaretteMoney,
									itemStyle:{  
	                                    normal:{color:'#003C88'}  
	                                } 
								},
								{
									name:'生活日用品',
									type:'bar',
									stack: '总量',
									data:map.dailyMoney,
									itemStyle:{  
	                                    normal:{color:'#BA95BE'}  
	                                }
								},
								{
									name:'车队卡(卡费)',
									type:'bar',
									stack: '总量',
									data:map.teamcardMoney,
									itemStyle:{  
	                                    normal:{color:'#641964'}  
	                                }
								},
								{
									name:'酒精饮料',
									type:'bar',
									stack: '总量',
									data:map.alcoholicMoney,
									itemStyle:{  
	                                    normal:{color:'#FFEAC2'}  
	                                }
								},
								{
									name:'零食',
									type:'bar',
									stack: '总量',
									data:map.snackMoney,
									itemStyle:{  
	                                    normal:{color:'#EB8705'}  
	                                }
								},
								{
									name:'非酒精饮料',
									type:'bar',
									stack: '总量',
									data:map.nonalcoholicMoney,
									itemStyle:{  
	                                    normal:{color:'#743410'}
	                                }
								},
								{
									name:'非食品',
									type:'bar',
									stack: '总量',
									data:map.nonfoodMoney,
									itemStyle:{  
	                                    normal:{color:'#BED50F'}  
	                                }
								}
							]
						});
					
			}
		});
	}
    </script>
    <hr>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="TOP10" style="width:80%;height:80%;min-height: 600px"></div>
    
    <script type="text/javascript">
   
    // 基于准备好的dom，初始化echarts实例
        var myChartTOP10 = echarts.init(document.getElementById('TOP10'));
      	//格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
         // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
	function queryTop(){
		$.ajax({
			type:"post",
			url:"/sysmanager/notOil/queryTop",
			dataType:"JSON",
			data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
				"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),
				"station":jqchk("station"),
				"start":$("#byNotOilstart").val(),
				"end":$("#byNotOilend").val(),
				"people":basePeople,
			},
			success:function(map){
					 myChartTOP10.setOption(
							 {
								    title: {
								        text: '便利店Top榜',
								        x:'center'
								    },
								    tooltip: {
								        trigger: 'axis',
								        axisPointer: {
								            type: 'shadow'
								        }
								    },
								    legend: {
								    	top:30,
								        data: ['销售额']
								    },
								    grid: {
								    	top:'10%',
								        left: '3%',
								        right: '4%',
								        bottom: '3%',
								        containLabel: true
								    },
								    xAxis: {
								        type: 'value',
								        boundaryGap: [0, 0.01],
								        axisLabel: {
											formatter: '{value} 元'
										}
								    },
								    yAxis: {
								        type: 'category',
								        data: map.names
								    },
								    series: [
								        {
								            name: '销售额',
								            type: 'bar',
								            data: map.data
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