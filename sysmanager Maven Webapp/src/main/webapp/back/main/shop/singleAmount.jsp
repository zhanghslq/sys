<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>非油单笔消费额</title>
</head>
<body>
<form action="">
		  请选择时间单位：<select name="date" id="singledate">
			    		<option value="day">日</option>
			    		<option value="month">月</option>
			    		<option value="year">年</option>
		    	 </select>
		  请选择开始时间段：	<input id="singlestart" class="easyui-datetimebox" name="start"
		        data-options="required:true,showSeconds:false" value="2016-7-01 00:00" style="width:150px"/> 
		  请选择结束时间段：<input id="singleend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="2017-10-10 00:00" style="width:150px"/>
				
		   选择油站查询：<select name="station" id="singlestation">
		       			<option value="all" selected>默认全部</option>
		    		</select>
		    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        				onclick="query()">查询</a>
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="single" style="width:90%;height:80%;"></div>
    <script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('single'));
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
						$("#singlestation").append(option);
					});
				}
			});
        // 使用刚指定的配置项和数据显示图表。
	function query() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/notOilTrade/query",
				dataType:"JSON",
				data:{"start":$("#singlestart").datetimebox("getValue"),"station":$("#singlestation").val(),
					"end":$("#singleend").datetimebox("getValue"),"date":$("#singledate").val()},
				success:function(map){
        		myChart.setOption({
                    title: {
                        text: '单笔消费额'
                    },
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
                        data:[ '平均单笔消费额','总消费额','交易笔数']
                    },
                    xAxis: {
                    	type : 'category',
                        data: map.dates,
                        axisPointer: {
							type: 'shadow'
						}
                    },
                    yAxis: {},
                    series: [{
                        name: '平均单笔消费额',
                        type: 'bar',
                        data: map.singleAmounts
                    },{
                        name: '总消费额',
                        type: 'bar',
                        data: map.tradeAmounts
                    },{
                        name: '交易笔数',
                        type: 'line',
                        data: map.tradeNumbers
                    }]
                });
        	
				}//success 
        	});//ajax
       }
    </script>
</body>
</html>