<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>环比增长率</title>
    <!-- 引入 echarts.js -->
</head>

<body>
<form action="">
		  请选择时间单位：<select name="date" id="growthdate">
			    		<option value="day" >日</option>
			    		<option value="month">月</option>
			    		<option value="year">年</option>
		    	    </select>
		  请选择开始时间段：	<input id="growthstart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2010-10-1 2:3" style="width:150px"> 
		  请选择结束时间段：<input id="growthend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="4/4/2010 0:0" style="width:150px"> 
				
		   选择油站查询：<select name="station" id="growthstation">
		       			<option value="all" selected>默认全部</option>
		    		</select>
    		
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="growth" style="width:90%;height:80%;"></div>
    
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('growth'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
         
        // 指定图表的配置项和数据
        $.ajax({
				type:"GET",
				url:"/sysmanager/city/queryAll",
				dataType:"JSON",
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#growthstation").append(option);
					});
				}
			});
        option = {
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
					data:['蒸发量','降水量','平均温度']
				},
				xAxis: [
					{
						type: 'category',
						data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
						axisPointer: {
							type: 'shadow'
						}
					}
				],
				yAxis: [
					{
						type: 'value',
						name: '交易量',
						min: 0,
						
						interval: 50,
						axisLabel: {
							formatter: '{value}'
						}
					},
					{
						type: 'value',
						name: '交易额',
						min: 0,
						
						interval: 5,
						axisLabel: {
							formatter: '{value}'
						}
					}
				],
				series: [
					{
						name:'交易量',
						type:'bar',
						data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
					},
					{
						name:'交易额',
						type:'bar',
						data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
					},
					{
						name:'完成率',
						type:'line',
						yAxisIndex: 1,
						data:[0.8, 0.8, 0.8, 0.6, 0.8, 1.2, 1.5, 1.9, 1.5, 1.6, 1.2, 1.1]
					}
				]
			};
				myChart.setOption(option);
        // 使用刚指定的配置项和数据显示图表。
      /*  $.ajax({
				type:"post",
				url:"/sysmanager/base/querydata",
				dataType:"JSON",
				data:{"station":$("#growthstation").val(),"start":$("#growthstart").val(),
					"end":$("growthend").val(),"date":$("#growthdate").val()
				},
				success:function(result){
					$.each(result,function(i,map){//返回结果
						
					});
				}
			}); */
    </script>
</body>
</html>