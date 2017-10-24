<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
<head>
    <title>油品对比</title>
</head>
<body>
<form action="">
请选择开始时间段：	<input class="easyui-datetimebox" name="start"  id="oldOilstart"
        data-options="required:true,showSeconds:false" value="2017-8-21 0:0"   style="width:150px"> 
  请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-10-1 0:0"  id="oldOilend"
        data-options="required:true,showSeconds:false"  style="width:150px"> 
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="query()">对比</a> 
 请选择开始时间段：<input class="easyui-datetimebox" name="start"  id="newOilstart"
        data-options="required:true,showSeconds:false" value="2017-8-21 0:0"   style="width:150px"> 
  请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-10-1 0:0"  id="newOilend"
        data-options="required:true,showSeconds:false"  style="width:150px">
</form>
   
    <div id="addVip" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
   
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('addVip'));
        
        
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function query() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/addVip/query",
				dataType:"JSON",
				data:{"start":$("#addVipstart").datetimebox("getValue"),
					"end":$("#addVipend").datetimebox("getValue"),"date":$("#addVipdate").val()},
				success:function(map){
        		myChart.setOption({
        			tooltip : {
        				trigger: 'axis',
        				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        				}
        			},
        			legend: {
        				data:['全网会员', '日新增会员']
        			},
        			toolbox: {
        				show : true,
        				feature : {
        					mark : {show: true},
        					dataView : {show: true, readOnly: false},
        					magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
        					restore : {show: true},
        					saveAsImage : {show: true}
        				}
        			},
        			calculable : true,
        			
        			xAxis : [
        				{
        					type : 'category',
        					data : map.dates
        				}
        			],
        			yAxis : [
        				{
        					type : 'value'
        				}
        			],
        			series : [
        				{
        					name:'全网会员',
        					type:'bar',
        					stack: '总量',
        					itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
        					data:map.totalPeoples
        				},
        				{
        					name:'日新增会员',
        					type:'bar',
        					stack: '总量',
        					itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
        					data:map.addNumbers
        				}
        			]
        		});
        	
				}//success 
        	});//ajax
       }
        
    </script>
</body>
</html>