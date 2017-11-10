<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>各油品的销量占比图</title>
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
		  请选择开始时间：<input id="zhanbistart" class="easyui-datetimebox" name="start"
		        data-options="required:true,showSeconds:false" value="2017-08-01 12:00" style="width:150px">
		        
		 请选择结束时间：<input id="zhanbiend" class="easyui-datetimebox" name="start"
		        data-options="required:true,showSeconds:false" value="2017-10-10 0:0" style="width:150px">
				
		查询分类：<select name="query" id="zhanbiquery" onchange="queryzhanbi()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
		查询内容：<select name="station" id="zhanbistation">
		       			
		    	</select>
    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryFromController()">查询</a>
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="basezhanbi" style="width: 80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        $(function () {
        	queryzhanbi();
		}); 
        var myChartbasezhanbi = echarts.init(document.getElementById('basezhanbi'));
        function queryzhanbi() {
   		 $("#zhanbistation").empty();
   		 if($("#zhanbiquery").val()=='station'){
   			$("#zhanbistation").append($("<option></option>").text('全部油站').val('all'));
   		 }
   		 $.ajax({
					type:"GET",
					url:"/sysmanager/station/queryAllName",
					dataType:"JSON",
					data:{"query":$("#zhanbiquery").val()},
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#zhanbistation").append(option);
						});
					}
				});
		}
        function queryFromController() {
        	$.ajax({
    			type:"post",
    			url:"/sysmanager/oil/queryzhanbi",
    			dataType:"JSON",
    			data:{"station":$("#zhanbistation").val(),"start":$("#zhanbistart").datetimebox("getValue"),
    				"end":$("#zhanbiend").datetimebox("getValue"),"query":$("#zhanbiquery").val()
    			},
    			success:function(map){
    				myChartbasezhanbi.setOption({
    					title : {
    						text: '油品占比',
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
    						data: ['92#','0#','95#',]
    					},
    					series : [
    						{
    							name: '油品占比',
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
    				});//Echarts
    					
    			}
    		});
			
		}
        
        // 指定图表的配置项和数据
			

        // 使用刚指定的配置项和数据显示图表。
        
    </script>
</body>
</html>