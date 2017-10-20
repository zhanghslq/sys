<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>会员充值</title>
    <!-- 引入 echarts.js -->
    
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <form action="">
  请选择开始时间段：	<input class="easyui-datetimebox" name="start"  id="viprechargestart"
        data-options="required:true,showSeconds:false" value="2016-12-21 0:0"   style="width:150px"> 
  请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-1-1 0:0"  id="viprechargeend"
        data-options="required:true,showSeconds:false"  style="width:150px"> 
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="query()">查询</a>
</form>
   
    <div id="viprecharge" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
   
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('viprecharge'));
        
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function query() {
				$.ajax({
					type:"post",
					url:"/sysmanager/vipRechargeMonth/query",
					dataType:"JSON",
					data:{"start":$("#viprechargestart").datetimebox("getValue"),
						"end":$("#viprechargeend").datetimebox("getValue")},
					success:function(map){
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
								data:['充值人数','充值总金额','单笔充值']
							},
							xAxis: [
								{
									type: 'category',
									data: map.dates,
									name:'    日期',
									axisPointer: {
										type: 'shadow'
									}
								}
							],
							yAxis: [
								{
									type: 'value',
									name: '充值人数',
									min: 0,
									
									//interval: 50,
									axisLabel: {
										formatter: '{value}人'
									}
								},
								{
									type: 'value',
									name: '充值总金额',
									min: 0,
									offset: 58,
									//interval: 5,
									axisLabel: {
										formatter: '{value}元'
									}
								},
								{
									type: 'value',
									name: '单笔充值',
									min: 0,
									
									//interval: 5,
									axisLabel: {
										formatter: '{value}元'
									}
								}
							],
							series: [
								{
									name:'充值人数',
									type:'bar',
									
									data:map.peoples
								},
								{
									name:'充值总金额',
									type:'bar',
									yAxisIndex: 1,
									data:map.rechargeTotals
								},
								{
									name:'单笔充值',
									type:'line',
									yAxisIndex: 2,
									data:map.avgRecharges
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