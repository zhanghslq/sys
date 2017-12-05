<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>会员招募</title>
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
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" readonly="readonly" style="width:300px" value="2017-08-14 14:45" class="am-form-field" id='addVipstart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" readonly="readonly" style="width:300px" value="2017-09-14 14:45" class="am-form-field" id='addVipend'></div>
                                      </div>
                                      <script>
											$('#addVipstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
											$('#addVipend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryAddVip()">确定</a>
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
    <div id="addVip" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChartAddVip = echarts.init(document.getElementById('addVip'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryAddVip() {
        	queryChannel();
        	$.ajax({
				type:"post",
				url:"/sysmanager/addVip/query",
				dataType:"JSON",
				data:{"start":$("#addVipstart").val(),
					"end":$("#addVipend").val(),"date":$("input[name='date']:checked").val()},
				success:function(map){
					myChartAddVip.setOption({
        			tooltip : {
        				trigger: 'axis',
        				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        				}
        			},
        			legend: {
        				data:['全网会员', '日新增会员']
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
        					name:'全网会员',
        					type:'bar',
        					stack: '总量',
        					data:map.totalPeoples
        				},
        				{
        					name:'日新增会员',
        					type:'bar',
        					stack: '总量',
        					data:map.addNumbers
        				}
        			]
        		});
        	
				}//success 
        	});//ajax
       }
        
    </script>
    <!-- 会员来源 -->
    <hr>
 
   
    <div id="from" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChartChannel = echarts.init(document.getElementById('from'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryChannel() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipChannel/queryChannel",
				dataType:"JSON",
				data:{"start":$("#addVipstart").val(),
					"end":$("#addVipend").val()},
				success:function(map){
					myChartChannel.setOption({
    				title : {
    					text: '会员来源',
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
    					data: ['未知','支付宝','PC','微信','手机']
    				},
    				series : [
    					{
    						name: '访问来源',
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
    			});
        	
				}//success 
        	});//ajax
       }
        
    </script>
<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>