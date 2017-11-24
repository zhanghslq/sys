<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>分类别走势图</title>
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
<form action="">
		  请选择时间单位：<select name="date" id="byNotOildate">
			    		<option value="day">日</option>
			    		<option value="month">月</option>
			    		<option value="year">年</option>
			    		<option value="minute">分钟</option>
			    		<option value="hour">小时</option>
			    		
		    	 </select>
		  请选择开始时间段：	<input id="byNotOilstart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2017-10-01 00:00" style="width:150px"> 
		  请选择结束时间段：<input id="byNotOilend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="2017-10-10 00:00" style="width:150px">
				
		  查询分类：<select name="query" id="byNotOilquery" onchange="queryrong()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
		  查询内容：<select name="station" id="byNotOilstation">
		       			
		    	</select>
		 查询类别：<select name="station" id="byNotOilName">
		       			
		    	</select>
		  <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryByNotOil()">查询</a>  
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="byNotOil" style="width:90%;height:80%;" ></div>
    
    <script type="text/javascript">
   
    // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('byNotOil'));
      //格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
         // 指定图表的配置项和数据
         
        function queryrong() {
    		 $("#byNotOilstation").empty();
    		 if($("#byNotOilquery").val()=='station'){
    			 $("#byNotOilstation").append($("<option></option>").text('全部油站').val('all'));
    		 }
    		 $.ajax({
					type:"GET",
					url:"/sysmanager/station/queryAllName",
					dataType:"JSON",
					data:{"query":$("#byNotOilquery").val()},
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#byNotOilstation").append(option);
						});
					}
				});
		}
        // 使用刚指定的配置项和数据显示图表。
     $(function () {
    	 queryrong();
    	 queryDepartmentName();
    	 queryByNotOil();
	}); 
     function queryDepartmentName() {
		 $("#byNotOilName").empty();
		 $.ajax({
				type:"GET",
				url:"/sysmanager/notOil/queryAllName",
				dataType:"JSON",
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station);
						$("#byNotOilName").append(option);
					});
				}
			});
	}
	function queryByNotOil(){
		$.ajax({
			type:"post",
			url:"/sysmanager/notOil/queryByDepartmentName",
			dataType:"JSON",
			data:{"station":$("#byNotOilstation").val(),"start":$("#byNotOilstart").datetimebox("getValue"),
				"end":$("#byNotOilend").datetimebox("getValue"),"date":$("#byNotOildate").val(),"query":$("#byNotOilquery").val(),
				"departmentName":$("#byNotOilName").val()
			},
			success:function(map){
					 myChart.setOption({
						    title: {
						        text: '分类别销售额'
						    },
						    tooltip: {
						        trigger: 'axis'
						    },
						    legend: {
								itemWidth:5,
						        data:['销售额']
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
						        axisTick: {
					                alignWithLabel: true
					            },
						        data: map.dates
						    },
						    yAxis: {
						        type: 'value',
						        axisLabel: {
									formatter: '{value} 元'
								}
						    },
						    series: [
						        {
						            name:'销售额',
						            type:'bar',
						            stack: '总量',
						            data:map.amounts
						        }
						    ]
						});
					
			}
		});
	}
    </script>
</body>
</html>