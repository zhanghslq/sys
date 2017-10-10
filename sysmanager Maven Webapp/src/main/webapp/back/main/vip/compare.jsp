<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>对比图</title>
    <!-- 引入 echarts.js -->
    
</head>
<body>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <form action="">
  请选择维度：<select name="comparedate">
	    		<option value="day" >日</option>
	    		<option value="month">月</option>
	    		<option value="year">年</option>
    		</select>
  请选择开始时间段：	<input class="easyui-datetimebox" name="start"  id="comparestart"   
        data-options="required:true,showSeconds:false" value="2016-10-01 2:3" style="width:150px"> 
  请选择结束时间段：<input class="easyui-datetimebox" name="end"   id="compareend" 
        data-options="required:true,showSeconds:false" value="2017-10-01 0:0" style="width:150px"> 
  
  按照城市查询：<select id="comparecity" name="city">
       			<option value="all" selected>默认全部</option>
    		</select>
    		
   单个油站查询：<select name="station" id="comparestation">
       			<option value="all" selected>默认全部</option>
    		</select>
    		
    </form>
   
    <div id="compare" style="width:100%;height:100%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('compare'));
        // 指定图表的配置项和数据
		option = {
				title : {
					text: '占比图',
					subtext: '纯属虚构',
					x:'center'
				},
				tooltip : {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				legend: {
					orient: 'vertical',
					left: 'left',
					data: ['会员消费额','非会员消费额']
				},
				series : [
					{
						name: '消费额',
						type: 'pie',
						radius : '55%',
						center: ['50%', '60%'],
						data:[
							{value:335, name:'会员消费额'},
							{value:310, name:'非会员消费额'}
							
						],
						itemStyle: {
							emphasis: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.5)'
							}
						}
					}
				]
			};
        // 使用刚指定的配置项和数据显示图表。
        
   
	    $(function(){
			//获取所有城市
			$.ajax({
				type:"GET",
				url:"/sysmanager/city/queryCitys",
				dataType:"JSON",
				success:function(result){
					$.each(result,function(i,city){
						var option = $("<option></option>").text(city);
						$("#city").append(option);
					});
					
				}
			});
			
			
				
				//获取当前选中的省份的code
				var city =encodeURI($("#city").val()); 
				//根据省份信息查询所有市
				$.ajax({
					type:"POST",
					//data:"provinceCode="+provinceCode,
					data:{"city":city},
					url:"/sysmanager/city/queryStations",
					dataType:"JSON",
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station).val(station);
							$("#station").append(option);
						});
						//让市触发change事件
						//$("#station").change();
					}
				});
			//要发请求查询，带上所有的数据
			$("#station").change(function(){
				myChart.setOption(option);
				
			});
			//
	    });
    </script>
</body>
</html>