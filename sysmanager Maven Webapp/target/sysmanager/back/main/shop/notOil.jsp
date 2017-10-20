<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>走势图</title>
    <!-- 引入 echarts.js -->
</head>
<body>
<form action="">
		  请选择时间单位：<select name="date" id="notOildate">
			    		<option value="day">日</option>
			    		<option value="month">月</option>
			    		<option value="year">年</option>
			    		<option value="minute">分钟</option>
			    		<option value="hour">小时</option>
		    	 	</select>
		  请选择开始时间段：	<input id="notOilstart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2017-09-01 00:00" style="width:150px"> 
		  请选择结束时间段：<input id="notOilend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="2017-10-10 00:00" style="width:150px">
				
		  查询分类：<select name="query" id="notOilquery" onchange="querynotOil()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
		    查询内容：<select name="station" id="notOilstation">
		       			
		    	</select>
		  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="test()">查询</a>  
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="notOil" style="width:90%;height:80%;"></div>
    
    <script type="text/javascript">
   
    // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('notOil'));
      //格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
         // 指定图表的配置项和数据
         
        function querynotOil() {
    		 $("#notOilstation").empty();
    		 if($("#notOilquery").val()=='station'){
    			 $("#notOilstation").append($("<option></option>").text('全部油站').val('all'));
    		 }
    		 $.ajax({
					type:"GET",
					url:"/sysmanager/station/queryAllName",
					dataType:"JSON",
					data:{"query":$("#notOilquery").val()},
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#notOilstation").append(option);
						});
					}
				});
		}
        // 使用刚指定的配置项和数据显示图表。
     $(function () {
    	 querynotOil();
    	 
	}); 
	function test(){
		$.ajax({
			type:"post",
			url:"/sysmanager/notOil/queryNotOils",
			dataType:"JSON",
			data:{"station":$("#notOilstation").val(),"start":$("#notOilstart").datetimebox("getValue"),
				"end":$("#notOilend").datetimebox("getValue"),"date":$("#notOildate").val(),"query":$("#notOilquery").val()
			},
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
								data:['总销售额','交易笔数','单笔消费额']
							},
							xAxis: [
								{
									type: 'category',
									data: map.dates,
									axisPointer: {
										type: 'shadow'
									}
								}
							],
							yAxis: [
								{
									type: 'value',
									name: '总销售额',
									min: 0,
									axisLabel: {
										formatter: '{value}元'
									}
								},
								{
									type: 'value',
									name: '单笔消费额',
									min: 0,
									offset:60,
									axisLabel: {
										formatter: '{value}元'
									}
								},
								{
									type: 'value',
									name: '交易笔数',
									min: 0,
									axisLabel: {
										formatter: '{value}笔'
									}
								}
							],
							series: [
								{
									name:'总销售额',
									type:'bar',
									data:map.moneys
								},
								{
									name:'单笔消费额',
									type:'bar',
									yAxisIndex: 1,
									data:map.avgMoney
								},
								{
									name:'交易笔数',
									type:'line',
									yAxisIndex: 2,
									data:map.numbers
								}
							]
						});
					
			}
		});
	}
    </script>
</body>
</html>