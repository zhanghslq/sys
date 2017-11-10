<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>销售额完成情况</title>
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
		
		  请选择油站：<select name="station" id="targetstation">
		       			
		    	</select>
		 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        		onclick="queryTarget()">查询</a>
    		
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="target" style="width:90%;height:80%;"></div>
    
    <script type="text/javascript">
        //基于准备好的dom，初始化echarts实例
       
    		 $.ajax({
					type:"GET",
					url:"/sysmanager/station/queryAll",
					dataType:"JSON",
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#targetstation").append(option);
						});
					}
				});
        
        var myCharttarget = echarts.init(document.getElementById('target'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        // 指定图表的配置项和数据
        function queryTarget(){
		$.ajax({
			type:"post",
			url:"/sysmanager/target/queryTarget",
			dataType:"JSON",
			data:{"station":$("#targetstation").val()},
			success:function(map){
					 myCharttarget.setOption({
				            title: {
				                text: '目标达成率'
				            },
				            tooltip: {},
				            legend: {
				                data:[{
									name: '油品销量达成率'
								}]
								
				            },
				            xAxis: {
				                data: map.days
				            },
				            yAxis: {},
				            series: [{
				                name: '油品销量达成率',
				                type: 'bar',
				                data: map.data
				            }]
				        });
					
			}
		});
	}
				
        // 使用刚指定的配置项和数据显示图表。
       
    </script>
</body>
</html>