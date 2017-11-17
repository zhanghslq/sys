<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>积分兑换</title>
    <!-- 引入 echarts.js -->
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
		  请选择时间单位：<select name="date" id="creditdate">
			    		<option value="day">日</option>
			    		<option value="month">月</option>
			    		<option value="year">年</option>
		    	 </select>
		  请选择开始时间段：	<input id="creditstart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2016-10-1 2:3" style="width:150px"> 
		  请选择结束时间段：<input id="creditend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="4/4/2010 0:0" style="width:150px"> 
    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryCredit()">查询</a>
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="credit" style="width:90%;height:80%;"></div>
    
    <script type="text/javascript">
    $(function() {
		queryCredit();
	});
        // 基于准备好的dom，初始化echarts实例
        var myChartcredit = echarts.init(document.getElementById('credit'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        // 指定图表的配置项和数据
        function queryCredit() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/credit/queryCredits",
				dataType:"JSON",
				data:{"date":$("#creditdate").val(),"start":$("#creditstart").datetimebox("getValue"),
					"end":$("#creditend").datetimebox("getValue")},
					success:function(map){
						myChartcredit.setOption({
					    title: {
					        text: '走势图'
					    },
					    tooltip: {
					        trigger: 'axis'
					    },
					    legend: {
							itemWidth:5,
					        data:['兑换积分数量','发放积分数量']
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
					        data: map.days
					    },
					    yAxis: {
					        type: 'value'
					    },
					    series: [
					        {
					            name:'发放积分数量',
					            type:'line',
					            stack: '发放',
					            data:map.get
					        },
					        {
					            name:'兑换积分数量',
					            type:'line',
					            stack: '兑换',
					            data:map.used
					        }]
					});
        	
				}//success 
        	});//ajax
       }
        // 使用刚指定的配置项和数据显示图表。
       
    </script>
</body>
</html>