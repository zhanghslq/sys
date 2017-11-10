<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>会员加油量分布</title>
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
  请选择时间：	<input id="drainstart" class="easyui-datetimebox" name="start"
        data-options="required:true,showSeconds:false" value="2016-12-21 0:0"   style="width:150px"/>
        
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryDrain()">查询</a>
</form>
   
    <div id="vipDrain" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    $.ajax({
		type:"GET",
		url:"/sysmanager/city/queryAll",
		dataType:"JSON",
		success:function(result){
			$.each(result,function(i,station){
				var option = $("<option></option>").text(station.name).val(station.id);
				$("#refuelstation").append(option);
			});
		}
	});
    
        // 基于准备好的dom，初始化echarts实例
        var myChartdrain = echarts.init(document.getElementById('vipDrain'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryDrain() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipFunnel/queryDrain",
				dataType:"JSON",
				data:{"time":$("#drainstart").datetimebox("getValue")},
					success:function(map){
					myChartdrain.setOption({
    				title : {
    					text: '会员流失占比',
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
    					data: ['流失人数','未流失人数']
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