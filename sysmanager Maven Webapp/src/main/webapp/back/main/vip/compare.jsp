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
    
   
    <div id="compare" style="width:90%;height:50%;"></div>
    <form action="">
   		请选择月份查看详情：<select name="station" id="comparedate" onchange="queryLiveNessByDate()">
    		</select>
    <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="queryLiveNessByDate()">查询</a>
    </form>
    <div id="consumepie" style="width:90%;height:40%;"></div>
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
    $(function() {
    	queryLiveNess();
    	
	});
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('compare'));
        var myChartconsumepie = echarts.init(document.getElementById('consumepie'));
        // 指定图表的配置项和数据
        function queryLiveNessByDate() {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/liveNess/queryDataByDate",
				dataType:"JSON",
				data:{"month":$("#comparedate").val()},
				success:function(map){
					myChartconsumepie.setOption({
	    				title : {
	    					text: '消费频次占比图',
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
	    					data: ['未消费的','消费一次的','消费两次的','消费三次的','消费四次的','消费五次的','五次以上的']
	    				},
	    				series : [
	    					{
	    						name: '访问来源',
	    						type: 'pie',
	    						radius : '55%',
	    						center: ['50%', '60%'],
	    						data:map
	    					}
	    				]
	    			});
					}
				});
        	}
         function queryLiveNess () {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/liveNess/queryData",
				dataType:"JSON",
				success:function(map){
	        		myChart.setOption({
	        			title : {
	    					text: '消费频次趋势图',
	    					x:'left'
	    				},
	        			tooltip : {
	        				trigger: 'axis',
	        				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	        					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        				}
	        			},
	        			legend: {
	        				data:['未消费的', '消费一次的','消费两次的','消费三次的','消费四次的','消费五次的','五次以上的']
	        			},
	        			/* toolbox: {
	        				show : true,
	        				feature : {
	        					mark : {show: true},
	        					dataView : {show: true, readOnly: false},
	        					magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
	        					restore : {show: true},
	        					saveAsImage : {show: true}
	        				}
	        			}, */
	        			calculable : true,
	        			
	        			xAxis : [
	        				{
	        					type : 'category',
	        					data : map.date
	        				}
	        			],
	        			yAxis : [
	        				{
	        					type : 'value'
	        				}
	        			],
	        			series : [
	        				{
	        					name:'未消费的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.zero
	        				},
	        				{
	        					name:'消费一次的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.one
	        				},
	        				{
	        					name:'消费两次的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.two
	        				},
	        				{
	        					name:'消费三次的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.three
	        				},
	        				{
	        					name:'消费四次的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.four
	        				},
	        				{
	        					name:'消费五次的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.five
	        				},
	        				{
	        					name:'五次以上的',
	        					type:'bar',
	        					stack: '总量',
	        					data:map.overfive
	        				}
	        			]
	        		});//绘制完Echarts
	        	
				}//success 
        	});//ajax
		};
    </script>
</body>
</html>