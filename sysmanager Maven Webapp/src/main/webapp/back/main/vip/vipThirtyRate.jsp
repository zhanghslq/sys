<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>新招募会员30天转化率</title>
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
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<form action="">
  请选择开始时间段：	<input class="easyui-datetimebox" name="start"  id="thirtyRatestart"
        data-options="required:true,showSeconds:false" value="2017-9-21 0:0"   style="width:150px"> 
  请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-10-01 0:0"  id="thirtyRateend"
        data-options="required:true,showSeconds:false"  style="width:150px"> 
   请选择转化周期：<select id="rateQuery">
        	<option value="thirty">会员30天转化率</option>
        	<option value="sixty">会员60天转化率</option>
        </select>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="query()">查询</a> 
</form>
   
    <div id="thirtyRate" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
   
        // 基于准备好的dom，初始化echarts实例
        var myChartthirtyRate = echarts.init(document.getElementById('thirtyRate'));
        
        
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function query() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipChannel/queryRate",
				dataType:"JSON",
				data:{"start":$("#thirtyRatestart").datetimebox("getValue"),
					"end":$("#thirtyRateend").datetimebox("getValue"),
					"query":$("#rateQuery").val()
					},
				success:function(map){
        		myChartthirtyRate.setOption({
        			tooltip : {
        				trigger: 'axis',
        				axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        					type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        				},
        				formatter: '{c}%'
        			},
        			legend: {
        				data:['日新增会员30天转化率']
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
        					data : map.days
        				}
        			],
        			yAxis : [
        				{
        					type : 'value',
        					axisLabel: {
								formatter: '{value}%'
							}
        				}
        			],
        			series : [
        				{
        					name:'日新增会员30天转化率',
        					type:'bar',
        					stack: '总量',
        					data:map.rates,
        					label: {
			                    normal: {
			                        show: true,
			                        position: 'inside',
			                        formatter: '{c}%'
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