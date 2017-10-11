<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>会员来源</title>
</head>
<body>
<!-- 暂停，待做 -->
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<form action="">
  请选择开始时间段：	<input id="fromstart" class="easyui-datetimebox" name="start"
        data-options="required:true,showSeconds:false" value="2016-12-21 0:0"   style="width:150px"/>
        
  请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-1-1 0:0"  id="fromend"
        data-options="required:true,showSeconds:false"  style="width:150px"/> 
        
  选择油站：<select name="station" id="fromstation">
		       			<option value="all" selected>默认全部</option>
		   </select>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="query()">查询</a>
</form>
   
    <div id="from" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    $.ajax({
		type:"GET",
		url:"/sysmanager/city/queryAll",
		dataType:"JSON",
		success:function(result){
			$.each(result,function(i,station){
				var option = $("<option></option>").text(station.name).val(station.id);
				$("#fromstation").append(option);
			});
		}
	});
    
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('from'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function query() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/refuel/query",
				dataType:"JSON",
				data:{"start":$("#fromstart").datetimebox("getValue"),"station":$("#fromstation").val(),
					"end":$("#fromend").datetimebox("getValue")},
				success:function(map){
        		myChart.setOption({
    				title : {
    					text: '会员来源',
    					subtext: '会员',
    					x:'center'
    				},
    				tooltip : {
    					trigger: 'item',
    					formatter: "{a} <br/>{b} : {c}人 ({d}%)"
    				},
    				legend: {
    					orient: 'vertical',
    					left: 'left',
    					data: ['<10L','10L~20L','20L~30L','30L~40L','40L~50L','>50L']
    				},
    				series : [
    					{
    						name: '加油区间',
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
    			});
        	
				}//success 
        	});//ajax
       }
        
    </script>
</body>
</html>