<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>走势图</title>
    <!-- 引入 echarts.js -->
</head>

<body>
<form action="">
		  请选择时间单位：<select name="date" id="zoushidate">
			    		<option value="day">日</option>
			    		<option value="month">月</option>
			    		<option value="year">年</option>
		    	 </select>
		  请选择开始时间段：	<input id="zoushistart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2016-10-01 00:00" style="width:150px"> 
		  请选择结束时间段：<input id="zoushiend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="2017-10-10 00:00" style="width:150px">
				
		   选择油站查询：<select name="station" id="bazhstation">
		       			<option value="all" selected>默认全部</option>
		    		</select>
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="zoushi" style="width:90%;height:80%;"></div>
    
    <script type="text/javascript">
   
    // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('zoushi'));
      //格式化时间
       
        
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
			
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
        
         // 指定图表的配置项和数据
        $.ajax({
				type:"GET",
				url:"/sysmanager/city/queryAll",
				dataType:"JSON",
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#bazhstation").append(option);
					});
				}
			});
        // 使用刚指定的配置项和数据显示图表。
     $(function () {
    	 test();//加载结束调用test方法
    	 $("#zoushiend").change(test());
    	 $("#zoushistart").change(test());
	}); 
	function test() {
		$.ajax({
			type:"post",
			url:"/sysmanager/base/querydata",
			dataType:"JSON",
			data:{"station":$("#bazhstation").val(),"start":$("#zoushistart").val(),
				"end":$("#zoushiend").val(),"date":$("#zoushidate").val(),"oilName":"92#"
			},
			success:function(result){
				$.each(result,function(i,map){//返回结果
					 myChart.setOption({//Echarts 的实例化
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
						    yAxis: {
						        type: 'value'
						    },
						 
						 xAxis: {
						        type: 'category',
						        boundaryGap:false,
						        data: map.dates
						    },
						    series: [
								        {
								            name:'交易升数',
								            type:'line',
								            stack: '总量',
								            data:map.litres
								        },
								        {
								            name:'油品营业额',
								            type:'line',
								            stack: '总量',
								            data:map.tradeAmounts
								        },
								        {
								            name:'润滑油销量',
								            type:'line',
								            stack: '总量',
								            data:map.lubeAmounts
								        },
								        {//假数据
								            name:'降水量',
								            type:'line',
								            stack: '总量',
								            data:[320, 332, 301, 334, 390, 330, 320]
								        },
								        {
								            name:'交易笔数',
								            type:'line',
								            stack: '总量',
								            data:map.numbers
								        },
										{
								            name:'非油交易额',
								            type:'line',
								            stack: '总量',
								            data:map.notOilAmounts
								        }
								    ]
						    //配置参数
					 });
					
				});
			}
		});
	}
    </script>
</body>
</html>