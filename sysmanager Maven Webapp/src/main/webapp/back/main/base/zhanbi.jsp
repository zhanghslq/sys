<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>各油品的销量占比图</title>
    <!-- 引入 echarts.js-->
</head>
<body>
<form action="">
		  请选择时间单位：<select name="date" id="zhanbidate">
			    		<option name="day">日</option>
			    		<option name="month">月</option>
			    		<option name="year">年</option>
		    	 </select>
		  请选择时间：<input id="zhanbistart" class="easyui-datetimebox" name="start"
		        data-options="required:true,showSeconds:false" value="4/4/2010 0:0" style="width:150px">
				
		   选择油站查询：<select name="station" id="bazbstation">
		       			<option value="all" selected>默认全部</option>
		    		</select>
    		
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="basezhanbi" style="width: 80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        $.ajax({
				type:"GET",
				url:"/sysmanager/city/queryAll",
				dataType:"JSON",
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#bazbstation").append(option);
					});
				}
			});
        
        var myChart = echarts.init(document.getElementById('basezhanbi'));
        // 指定图表的配置项和数据
			option = {
				tooltip: {
					trigger: 'item',
					formatter: "{a} <br/>{b}: {c} ({d}%)"
				},
				legend: {
					orient: 'vertical',
					x: 'left',
					data:['直达','营销广告','搜索引擎','邮件营销','联盟广告','视频广告','百度','谷歌','必应','其他']
				},
				series: [
					{
						name:'销量',
						type:'pie',
						selectedMode: 'single',
						radius: [0, '30%'],

						label: {
							normal: {
								position: 'inner'
							}
						},
						labelLine: {
							normal: {
								show: false
							}
						},
						data:[
							{value:335, name:'直达'},
							{value:679, name:'营销广告'},
							{value:1548, name:'搜索引擎'}
						]
					},
					{
						name:'销售额',
						type:'pie',
						radius: ['30%', '55%'],
						label: {
							normal: {
								
								backgroundColor: '#eee',
								borderColor: '#aaa',
								borderWidth: 1,
								borderRadius: 4,
						
							}
						},
						data:[
							{value:310, name:'邮件营销'},
							{value:234, name:'联盟广告'},
							{value:135, name:'视频广告'},
							
						]
					},
					{
						name:'成交笔数',
						type:'pie',
						radius: ['55%', '70%'],
						label: {
							normal: {
								
								backgroundColor: '#bbb',
								borderColor: '#eee',
								borderWidth: 1,
								borderRadius: 4,
						
							}
						},
						data:[
							{value:1048, name:'百度'},
							{value:251, name:'谷歌'},
							{value:147, name:'必应'},
							{value:102, name:'其他'}
						]
					}
				]
			};


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</body>
</html>