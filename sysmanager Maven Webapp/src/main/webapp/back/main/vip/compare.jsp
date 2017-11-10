<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>活跃会员消费频次及占比</title>
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
    <form action="">
   		选择时间：<select name="station" id="comparedate" onchange="queryLiveNess()">
    		</select>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryLiveNess()">查询</a>
    </form>
   
    <div id="compare" style="width:100%;height:100%;"></div>
    <script type="text/javascript">
	    $.ajax({
			type:"GET",
			url:"/sysmanager/liveNess/queryAllDate",
			dataType:"JSON",
			success:function(result){
				$.each(result,function(i,station){
					var option = $("<option></option>").text(station).val(station);
					$("#comparedate").append(option);
				});
			}
		});
    
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('compare'));
        // 指定图表的配置项和数据
         function queryLiveNess () {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/liveNess/queryData",
				dataType:"JSON",
				data:{"date":$("#comparedate").val()},
				success:function(map){
	        		myChart.setOption({
	    				title : {
	    					text: '占比图',
	    					subtext: '北京壳牌',
	    					x:'center'
	    				},
	    				tooltip : {
	    					trigger: 'item',
	    					formatter: "{a} <br/>{b} : {c} ({d}%)"
	    				},
	    				legend: {
	    					orient: 'vertical',
	    					left: 'left',
	    					data: ['未消费的','消费一次的','消费两次的','消费三次的','三次以上的',]
	    				},
	    				series : [
	    					{
	    						name: '消费额',
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
	    			});//绘制完Echarts
	        	
				}//success 
        	});//ajax
		};
    </script>
</body>
</html>