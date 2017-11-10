<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>燃油销量月度分布</title>
    <!-- 引入 echarts.js -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/css/IconExtension.css">
    <script src="${pageContext.request.contextPath}/back/easyui/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/form.validator.rules.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="${pageContext.request.contextPath}/back/echar/echarts.js"></script>
</head>

<body>
<form action="">
		  请选择时间单位：<select name="date" id="oilmonthdate">
			    		<option value="day" >日</option>
			    		<option value="month">月</option>
			    		<option value="year">年</option>
		    	 </select>
		  请选择开始时间段：	<input id="oilmonthstart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2010-10-1 2:3" style="width:150px"> 
		  请选择结束时间段：<input id="oilmonthend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="4/4/2010 0:0" style="width:150px"> 
				
		   选择油站查询：<select name="station" id="oilmonthstation">
		       			<option value="all" selected>默认全部</option>
		    		</select>
    		
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="oilmonth" style="width:90%;height:80%;"></div>
    
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('oilmonth'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
         
        // 指定图表的配置项和数据
        $.ajax({
				type:"GET",
				url:"/sysmanager/city/queryAll",
				dataType:"JSON",
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#oilmonthstation").append(option);
					});
				}
			});
        
        
        var option = {
                title: {
                    text: '燃油销量月度分布'
                },
                tooltip: {},
                legend: {
    				
    				
                    data:[{
    					name: '销量升数'
    				},{
    					name: '降水量'
    				}]
    				
                },
                xAxis: {
                    data: ["一月","二月","三月"]
                },
                yAxis: {},
                series: [{
                    name: '销量升数',
                    type: 'bar',
                    data: [500, 200, 360]
                },{
                    name: '降水量',
                    type: 'bar',
                    data: [15, 120, 136]
                }
    			
    			]
            };
				myChart.setOption(option);
        // 使用刚指定的配置项和数据显示图表。
      /*  $.ajax({
				type:"post",
				url:"/sysmanager/base/querydata",
				dataType:"JSON",
				data:{"station":$("#growthstation").val(),"start":$("#growthstart").val(),
					"end":$("growthend").val(),"date":$("#growthdate").val()
				},
				success:function(result){
					$.each(result,function(i,map){//返回结果
						
					});
				}
			}); */
    </script>
</body>
</html>