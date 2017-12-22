<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>油站营业额</title>
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
<form  method="post" id="exportExcel">
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
                                <a href="javascript:void(0);" class="determine" onclick="querybaseOil()">确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                                <a href="javascript:void(0);"  class="determine" onclick="ExportExcel()">导出到Excel</a>
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
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" name="start" style="width:300px"  class="am-form-field" id='zoushistart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end" style="width:300px"  class="am-form-field" id='zoushiend'></div>
                                      </div>
                                      <script>
											$('#zoushistart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												  
												});
											$('#zoushistart').attr("value",getNowFormatDateOne());
											$('#zoushiend').attr("value",getNowFormatDate());
											$('#zoushiend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												  value:getNowFormatDate()
												});
											
									  </script>
									  
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="querybaseOil()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                        <br><br>
                                        <a href="javascript:void(0);"  class="determine" onclick="ExportExcel()">导出到Excel</a>
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
<!-- ///////////////////////// -->		
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="amount" style="width:80%;height:80%;min-height: 600px;min-width: 800px"></div>
    <div id="number" style="width:80%;height:80%;min-height: 600px;min-width: 800px"></div>
    <div id="single" style="width:80%;height:80%;min-height: 600px;min-width: 800px"></div>
    
    <script type="text/javascript">
    function ExportExcel() {
    	$("#exportExcel").attr("action","/sysmanager/oil/exportOils?people="+basePeople);
 	   	$("#exportExcel").submit();
    }
    // 基于准备好的dom，初始化echarts实例
        var amount = echarts.init(document.getElementById('amount'));
        var number = echarts.init(document.getElementById('number'));
        var single = echarts.init(document.getElementById('single'));
      //格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
         // 指定图表的配置项和数据
         var basePeople="all";
      	function ChangePeople(src) {
			basePeople=src;
		}
     $(function() {
		querybaseOil();
	});
	function querybaseOil(){
		$.ajax({
			type:"post",
			url:"/sysmanager/oil/queryOils",
			dataType:"JSON",
			data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
				"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),
				"station":jqchk("station"),"start":$("#zoushistart").val(),
				"end":$("#zoushiend").val(),"date":$("input[name='date']:checked").val(),
				"people":basePeople},
			success:function(map){
				amount.setOption({
				    title: {
				        text: '总销量',
				        x:'center'
				    },
				    tooltip: {
				        trigger: 'axis',
				        formatter: '{b}: {c}千升'
				    },
				    legend: {
				    	top:30,
						itemWidth:5,
				        data:['总销量']
				    },
				    grid: {
				    	top:'10%',
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
							formatter: '{value} 千升'
						}
				    },
				    series: [
				        {
				            name:'总销量',
				            type:'bar',
				            stack: '总量',
				            data:map.amounts
				        }
				    ]
				});
				number.setOption({
				    title: {
				        text: '销售笔数',
				        x:'center'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				    	top:30,
						itemWidth:5,
				        data:['销售笔数']
				    },
				    grid: {
				    	top:'10%',
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
				single.setOption({
				    title: {
				        text: '单车加油量',
				        x:'center'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				    	top:'30',
						itemWidth:5,
				        data:['单车加油量']
				    },
				    grid: {
				    	top:'10%',
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
							formatter: '{value} 升'
						}
				    },
				    series: [
				        {
				            name:'单车加油量',
				            type:'bar',
				            stack: '总量',
				            data:map.avgAmounts
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