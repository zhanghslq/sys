<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>积分兑换</title>
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
                                          <label><input name="date" type="radio" value="hour" /> <i>小时</i> </label>
                                          <label><input name="date" type="radio" value="minute" /> <i>分钟</i> </label>
                                        </div>
                                      </div>
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" readonly="readonly" style="width:300px" value="2017-08-14 14:45" class="am-form-field" id='creditstart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" readonly="readonly" style="width:300px" value="2017-09-14 14:45" class="am-form-field" id='creditend'></div>
                                      </div>
                                      <script>
											$('#creditstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
											$('#creditend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryCredit()">确定</a>
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
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="credit" style="width:90%;height:80%;"></div>
    
    <script type="text/javascript">
    $(function() {
		queryCredit();
	});
        // 基于准备好的dom，初始化echarts实例
        var myChartcredit = echarts.init(document.getElementById('credit'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        // 指定图表的配置项和数据
        function queryCredit() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/credit/queryCredits",
				dataType:"JSON",
				data:{"date":$("input[name='date']:checked").val(),"start":$("#creditstart").val(),
					"end":$("#creditend").val()},
					success:function(map){
						myChartcredit.setOption({
					    title: {
					        text: '走势图'
					    },
					    tooltip: {
					        trigger: 'axis'
					    },
					    legend: {
							itemWidth:5,
					        data:['兑换积分数量','发放积分数量']
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
					        data: map.days
					    },
					    yAxis: {
					        type: 'value'
					    },
					    series: [
					        {
					            name:'发放积分数量',
					            type:'line',
					            stack: '发放',
					            data:map.get
					        },
					        {
					            name:'兑换积分数量',
					            type:'line',
					            stack: '兑换',
					            data:map.used
					        }]
					});
        	
				}//success 
        	});//ajax
       }
        // 使用刚指定的配置项和数据显示图表。
       
    </script>
    
    <!--creditRate  -->
        <div id="creditRate" style="width: 80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
         var myChartcreditRate = echarts.init(document.getElementById('creditRate'));
       $(function() {
    	   $.ajax({
   			type:"post",
   			url:"/sysmanager/credit/queryZhanbi",
   			dataType:"JSON",
   			success:function(map){
   				myChartcreditRate.setOption({
   					title : {
   						text: '积分余量占比',
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
   						data: ['已使用','未使用',]
   					},
   					series : [
   						{
   							name: '积分占比',
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
	});
    </script>
<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>