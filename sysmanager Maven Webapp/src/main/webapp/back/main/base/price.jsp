<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>价格走势</title>
    <!-- 引入 echarts.js -->
    <!--价格只能单个油站进行展示  -->
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
		  请选择开始时间段：<input id="pricestart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2017-08-1 2:3" style="width:150px"> 
		  请选择结束时间段：<input id="priceend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="2017-10-1 2:3" style="width:150px"> 
				
		   选择油站：<select name="station" id="pricestation">
		    	</select>
		 查询油品：<select name="oilName" id="priceoilName">
		       			
		    	</select>
		    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryPrice()">查询</a>
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="price" style="width:90%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var price = echarts.init(document.getElementById('price'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        // 指定图表的配置项和数据
        $.ajax({
				type:"GET",
				url:"/sysmanager/city/queryAll",
				dataType:"JSON",
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#pricestation").append(option);
					});
				}
			});
		$(function(){
			queryOilName();
		});
        function queryOilName() {
   		 $("#priceoilName").empty();
   		 $.ajax({
   				type:"POST",
   				url:"/sysmanager/oilPrice/queryAllName",
   				dataType:"JSON",
   				success:function(result){
   					$.each(result,function(i,station){
   						var option = $("<option></option>").text(station);
   						$("#priceoilName").append(option);
   					});
   				}
   			});
   		}
        function queryPrice(){
    		$.ajax({
    			type:"post",
    			url:"/sysmanager/oilPrice/queryPrice",
    			dataType:"JSON",
    			data:{"station":$("#pricestation").val(),"start":$("#pricestart").datetimebox("getValue"),
    				"end":$("#priceend").datetimebox("getValue"),"oilName":$("#priceoilName").val()
    			},
    			success:function(map){
    				price.setOption({
    				            title: {
    				                text: '北京壳牌'
    				            },
    				            tooltip: {
    				            	trigger: 'axis'
    				            },
    				            legend: {
    				                data:[{
    									name: '油价'
    								}]
    				            },
    				            xAxis: {
    				                data: map.dates
    				            },
    				            yAxis: {},
    				            series: [{
    				                name: '油价',
    				                type: 'line',
    				                data: map.prices
    				            }]
    				        });
    					
    			}
    		});
    	}
        // 使用刚指定的配置项和数据显示图表。
    </script>
</body>
</html>