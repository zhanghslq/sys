<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>油枪效率分析图</title>
    <!-- 引入 echarts.js-->
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
		  请选择时间单位：<select name="date" id="oilgundate">
			    		<option value="day">日</option>
			    		<option value="month">月</option>
			    		<option value="year">年</option>
		    	 </select>
		  请选择开始时间：<input id="oilgunstart" class="easyui-datetimebox" name="start"
		        data-options="required:true,showSeconds:false" value="4/4/2010 0:0" style="width:150px"/>
		  请选择结束时间：<input id="oilgunend" class="easyui-datetimebox" name="end"
		        data-options="required:true,showSeconds:false" value="4/4/2010 0:0" style="width:150px"/>
				
		  请选择油站查询：<select name="station" id="oilgunstation">
		    		</select>
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="oilgundiv" style="width: 80%;height:80%;">
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    	<div></div>
    </div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        $.ajax({
				type:"GET",
				url:"/sysmanager/city/queryAll",
				dataType:"JSON",
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#oilgunstation").append(option);
					});
				}
			});
        // 使用刚指定的配置项和数据显示图表。
    </script>
</body>
</html>