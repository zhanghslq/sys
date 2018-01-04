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
    <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
</head>

<body>
<form action="" method="post" id="exportExcel">
<div class="contentRight">
       <div class="rightDownSel">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
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
                                          <label><input name="date" type="radio" value="hour"/> <i>小时</i> </label>
                                          <label><input name="date" type="radio" value="minute"/> <i>分钟</i> </label>
                                        </div>
                                      </div>
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input name="start" size="16"  style="width:300px"  class="am-form-field" id='creditstart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end"  style="width:300px"  class="am-form-field" id='creditend'></div>
                                      </div>
                                      <script>
                                      		$('#creditstart').attr("value",getNowFormatDateOne());
											$('#creditstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
											$('#creditend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii'
												});
											$('#creditend').attr("value",getNowFormatDate())
									  </script>
                                        <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryCredit()">确定</a>
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
           </div>
       </div>
    </div>
</form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="credit" style="width:80%;height:80%;"></div>
    
    <script type="text/javascript">
    function ExportExcel() {
    	$("#exportExcel").attr("action","/sysmanager/credit/exportCredit?area="+baseArea);
 	   	$("#exportExcel").submit();
    }
    var baseArea="BJSHELL";
    function ChangeArea(src) {
		baseArea=src;
		queryCredit();
		queryZhanbi();
	}
    $(function() {
		queryCredit();
		queryZhanbi();
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
					"end":$("#creditend").val(),"area":baseArea},
					success:function(map){
						myChartcredit.setOption({
					    title: {
					        text: '会员积分发放与兑换',
					        x:'center'
					    },
					    tooltip: {
					        trigger: 'axis'
					    },
					    legend: {
					    	top:30,
							itemWidth:5,
					        data:['兑换积分数量','发放积分数量']
					    },
					    color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88',
 						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
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
      function queryZhanbi() {
    	  $.ajax({
     			type:"post",
     			url:"/sysmanager/credit/queryZhanbi",
     			dataType:"JSON",
     			data:{"area":baseArea},
     			success:function(map){
     				myChartcreditRate.setOption({
     					title : {
     						text: '积分余量占比',
     						x:'center'
     					},
     					tooltip : {
     						trigger: 'item',
     						formatter: "{a} <br/>{b} : {c} ({d}%)"
     					},
     					legend: {
     						top:30,
     						data: ['已使用','未使用']
     					},
     					color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88',
  						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
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
	}
    </script>
<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>