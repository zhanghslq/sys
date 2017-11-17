<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>会员来源</title>
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
<!-- 暂停，待做 -->
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<form action="">
  请选择开始时间段：	<input id="fromstart" class="easyui-datetimebox" name="start"
        data-options="required:true,showSeconds:false" value="2016-12-21 0:0"   style="width:150px"/>
        
  请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-1-1 0:0"  id="fromend"
        data-options="required:true,showSeconds:false"  style="width:150px"/> 
        
  
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryChannel()">查询</a>
</form>
   
    <div id="from" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    $(function() {
		queryChannel();
	});
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('from'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryChannel() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipChannel/queryChannel",
				dataType:"JSON",
				data:{"start":$("#fromstart").datetimebox("getValue"),
					"end":$("#fromend").datetimebox("getValue")},
				success:function(map){
        		myChart.setOption({
    				title : {
    					text: '会员来源',
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
    					data: ['未知','支付宝','PC','微信','手机']
    				},
    				series : [
    					{
    						name: '访问来源',
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