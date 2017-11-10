<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>劳动生产率</title>
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
		  请选择开始时间段：	<input id="productstart" class="easyui-datetimebox"
		        data-options="required:true,showSeconds:false" value="2016-10-01 0:0" style="width:150px"> 
		  请选择结束时间段：<input id="productend" class="easyui-datetimebox"  
		        data-options="required:true,showSeconds:false" value="2017-10-10 0:0" style="width:150px">
				
		    选择油站：<select name="station" id="productstation">
		       			
		    		</select>
		  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryProduct()">查询</a>  
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="product" style="width:95%;height:95%;"></div>
    <script type="text/javascript">
   
    // 基于准备好的dom，初始化echarts实例
        var myChartPRODUCT = echarts.init(document.getElementById('product'));
      	//格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
        // 指定图表的配置项和数据
    		 $.ajax({
					type:"POST",
					url:"/sysmanager/station/queryAll",
					dataType:"JSON",
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#productstation").append(option);
						});
					}
				});
        // 使用刚指定的配置项和数据显示图表。
	function queryProduct(){
		$.ajax({
			type:"post",
			url:"/sysmanager/product/queryProduct",
			dataType:"JSON",
			data:{"station":$("#productstation").val(),"start":$("#productstart").datetimebox("getValue"),
				"end":$("#productend").datetimebox("getValue")
			},
			success:function(map){
				myChartPRODUCT.setOption({
						    title: {
						        text: '劳动生产率'
						    },
						    tooltip: {
						        trigger: 'axis'
						    },
						    legend: {
								itemWidth:5,
						        data:['劳动生产率']
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
						        data: map.dates
						    },
						    yAxis: {
						        type: 'value'
						    },
						    series: [
						        {
						            name:'劳动生产率',
						            type:'line',
						            stack: '总量',
						            data:map.data
						        }
						    ]
						});
					
			}
		});
	}
    </script>
</body>
</html>