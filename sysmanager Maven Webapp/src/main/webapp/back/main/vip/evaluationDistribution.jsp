<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>评价分布图</title>
    <!-- 引入 echarts.js-->
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
		  请选择开始时间：<input id="distributestart" class="easyui-datetimebox" name="start"
		        data-options="required:true,showSeconds:false" value="2016-12-01 0:0" style="width:150px"/>
		  请选择结束时间：<input id="distributeend" class="easyui-datetimebox" name="end"
		        data-options="required:true,showSeconds:false" value="2017-03-21 0:0" style="width:150px"/>
				
		 查询分类：<select name="query" id="distributequery" onchange="queryEva()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
		    查询内容：<select name="station" id="distributestation">
		       			
		    		</select>
     <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryEvaDis()">查询</a>
    		
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="evaluationDistribution" style="width: 80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
         var myChart = echarts.init(document.getElementById('evaluationDistribution'));
         function queryEva() {
    		 $("#distributestation").empty();
    		 if($("#distributequery").val()=="station"){
    			 $("#distributestation").append($("<option></option>").text('全部油站').val('all'));
    		 }
    		 $.ajax({
					type:"GET",
					url:"/sysmanager/station/queryAllName",
					dataType:"JSON",
					data:{"query":$("#distributequery").val()},
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#distributestation").append(option);
						});
					}
				});
		}
         $(function () {
        	 queryEva();
        	 queryEvaDis();
    	}); 
        
         function queryEvaDis () {
         	$.ajax({
 				type:"POST",
 				url:"/sysmanager/evaluation/queryDistribution",
 				dataType:"JSON",
 				data:{"start":$("#distributestart").datetimebox("getValue"),
 					"end":$("#distributeend").datetimebox("getValue"),
 					"station":$("#distributestation").val(),"query":$("#distributequery").val()
 				},
 				success:function(map){
 	        		myChart.setOption({
 	                   title: {
 	                      text: '北京壳牌'
 	                  },
 	                  tooltip: {},
 	                  legend: {
 	      				
 	      				
 	                      data:[{
 	      					name: '平均分'
 	      				}]
 	      				
 	                  },
 	                  xAxis: {
 	                      data: ["总体满意度","服务态度","油站环境","加油速度","推荐意愿"]
 	                  },
 	                  yAxis: {},
 	                  series: [{
 	                      name: '平均分',
 	                      type: 'bar',
 	                      data: map
 	                  }
 	      			]
 	              });//绘制完Echarts
 	        	
 				}//success 
         	});//ajax
 		};
    </script>
</body>
</html>