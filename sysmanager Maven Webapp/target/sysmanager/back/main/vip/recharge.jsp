<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>会员充值</title>
    <!-- 引入 echarts.js -->
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/IconExtension.css">
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
    <script src="/sysmanager/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="/sysmanager/back/easyui/js/form.validator.rules.js"></script>
    <script src="/sysmanager/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="/sysmanager/back/echar/echarts.js"></script>
    
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <form action="">
  请选择维度：<select name="date" id="rechargedate"  onchange="query()">
	    		<option value="day" >日</option>
	    		<option value="month">月</option>
	    		<option value="year">年</option>
    		</select>
  请选择开始时间段：	<input class="easyui-datetimebox" name="start"  id="rechargestart"
        data-options="required:true,showSeconds:false" value="2016-12-21 0:0"   style="width:150px"> 
  请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-1-1 0:0"  id="rechargeend"
        data-options="required:true,showSeconds:false"  style="width:150px"> 
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="query()">查询</a>  
</form>
   
    <div id="recharge" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
   
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('recharge'));
        
        
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function query() {
        	var tradeNumber=[];
        	var tradeAmounts=[];
        	var avgAmounts=[];
        	var dates=[];
				$.ajax({
					type:"post",
					url:"/sysmanager/recharge/query",
					dataType:"JSON",
					data:{"start":$("#rechargestart").datetimebox("getValue"),
						"end":$("#rechargeend").datetimebox("getValue"),"date":$("#rechargedate").val()},
					success:function(map){
						tradeNumber=map.tradeNumber;
						tradeAmounts=map.tradeAmounts;
						avgAmounts=map.avgAmounts;
						dates=map.dates;
						
						myChart.setOption({
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
								data:['充值单数','充值总金额','单笔交易额']
							},
							xAxis: [
								{
									type: 'category',
									data: dates,
									name:'    日期',
									axisPointer: {
										type: 'shadow'
									}
								}
							],
							yAxis: [
								{
									type: 'value',
									name: '充值单数',
									min: 0,
									
									//interval: 50,
									axisLabel: {
										formatter: '{value}单'
									}
								},
								{
									type: 'value',
									name: '充值总金额',
									min: 0,
									offset: 80,
									//interval: 5,
									axisLabel: {
										formatter: '{value}元'
									}
								},
								{
									type: 'value',
									name: '单笔交易额',
									min: 0,
									
									//interval: 5,
									axisLabel: {
										formatter: '{value}元'
									}
								}
							],
							series: [
								{
									name:'充值单数',
									type:'bar',
									
									data:tradeNumber
								},
								{
									name:'充值总金额',
									type:'bar',
									yAxisIndex: 1,
									data:tradeAmounts
								},
								{
									name:'单笔交易额',
									type:'line',
									yAxisIndex: 2,
									data:avgAmounts
								}
							]
						},true);
						//配置
						
					}
				});
				
			} 
        
    </script>
</body>
</html>