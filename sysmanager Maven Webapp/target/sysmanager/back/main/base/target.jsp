<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>销售额完成情况</title>
    <!-- 引入 echarts.js -->
</head>

<body>
<form action="">
		  请选择时间单位：<select name="date" id="targetdate">
			    		<option value="day" >日</option>
			    		<option value="month">月</option>
			    		<option value="year">年</option>
		    	 </select>
		  请选择开始时间段：	<input id="targetstart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2010-10-1 2:3" style="width:150px"> 
		  请选择结束时间段：<input id="targetend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="4/4/2010 0:0" style="width:150px"> 
				
		   选择油站查询：<select name="station" id="targetstation">
		       			<option value="all" selected>默认全部</option>
		    		</select>
    		
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="target" style="width:90%;height:80%;"></div>
    
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('target'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
         
        // 指定图表的配置项和数据
        $.ajax({
				type:"GET",
				url:"/sysmanager/city/queryAll",
				dataType:"JSON",
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#targetstation").append(option);
					});
				}
			});
        
        
				option = {
			    title: {
			        text: '走势图'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
					itemWidth:5,
			        data:['交易升数','油品营业额','润滑油销量','降水量','交易笔数','非油交易额']
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
			        data: ['周一','周二','周三','周四','周五','周六','周日']
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [
			        {
			            name:'交易升数',
			            type:'line',
			            stack: '总量',
			            data:[120, 132, 101, 134, 90, 230, 210]
			        },
			        {
			            name:'油品营业额',
			            type:'line',
			            stack: '总量',
			            data:[220, 182, 191, 234, 290, 330, 310]
			        },
			        {
			            name:'润滑油销量',
			            type:'line',
			            stack: '总量',
			            data:[150, 232, 201, 154, 190, 330, 410]
			        },
			        {
			            name:'降水量',
			            type:'line',
			            stack: '总量',
			            data:[320, 332, 301, 334, 390, 330, 320]
			        },
			        {
			            name:'交易笔数',
			            type:'line',
			            stack: '总量',
			            data:[820, 932, 901, 934, 1290, 1330, 1320]
			        },
					{
			            name:'非油交易额',
			            type:'line',
			            stack: '总量',
			            data:[1820, 932, 901, 934, 1290, 1330, 1320]
			        }
			    ]
			};
				myChart.setOption(option);
        // 使用刚指定的配置项和数据显示图表。
       
    </script>
</body>
</html>