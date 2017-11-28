<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>便利店营业额</title>
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
		  请选择时间单位：<select name="date" id="notOildate">
			    		<option value="day">日</option>
			    		<option value="month">月</option>
			    		<option value="year">年</option>
			    		<option value="minute">分钟</option>
			    		<option value="hour">小时</option>
		    	 	</select>
		  请选择开始时间段：	<input id="notOilstart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2017-09-01 00:00" style="width:150px"> 
		  请选择结束时间段：<input id="notOilend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="2017-10-10 00:00" style="width:150px">
		<br>选择人群：<select  id="shopPeople" >
		       			<option value="all">全部交易数据</option>
		       			<option value="vip">会员交易数据</option>
		    		</select>		
		  查询分类：<select name="query" id="notOilquery" onchange="querynotOil()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
		    查询内容：<select name="station" id="notOilstation">
		       			
		    	</select>
		  <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryBaseNotOil()">查询</a>  
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="notOilMoney" style="width:80%;height:70%;min-width: 800px;min-height: 600px"></div>
    <div id="notOilNumber" style="width:80%;height:70%;min-width: 800px;min-height: 600px"></div>
    <div id="notOilavgMoney" style="width:80%;height:70%;min-width: 800px;min-height: 600px"></div>
    <div id="exceptLube" style="width:80%;height:70%;min-width: 800px;min-height: 600px"></div>
    <script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
        var myChartnotOilMoney = echarts.init(document.getElementById('notOilMoney'));
        var myChartnotOilNumber = echarts.init(document.getElementById('notOilNumber'));
        var myChartnotOilavgMoney = echarts.init(document.getElementById('notOilavgMoney'));
        var myChartExceptLube = echarts.init(document.getElementById('exceptLube'));
      //格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
         // 指定图表的配置项和数据
         
        function querynotOils() {
    		 $("#notOilstation").empty();
    		 if($("#notOilquery").val()=='station'){
    			 $("#notOilstation").append($("<option></option>").text('全部油站').val('all'));
    		 }
    		 $.ajax({
					type:"GET",
					url:"/sysmanager/station/queryAllName",
					dataType:"JSON",
					data:{"query":$("#notOilquery").val()},
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#notOilstation").append(option);
						});
					}
				});
		}
        // 使用刚指定的配置项和数据显示图表。
     $(function () {
    	 querynotOils();
    	 queryBaseNotOil();
	}); 
        function queryExceptLube() {
        	$.ajax({
    			type:"post",
    			url:"/sysmanager/notOil/queryExceptLube",
    			dataType:"JSON",
    			data:{"station":$("#notOilstation").val(),"start":$("#notOilstart").datetimebox("getValue"),
    				"end":$("#notOilend").datetimebox("getValue"),"date":$("#notOildate").val(),
    				"query":$("#notOilquery").val(),"people":$("#shopPeople").val()
    			},
    			success:function(map){
    				myChartExceptLube.setOption({
    				    title: {
    				        text: '除去润滑油单笔消费额'
    				    },
    				    tooltip: {
    				        trigger: 'axis',
    				    },
    				    legend: {
    						itemWidth:5,
    				        data:['单笔消费额']
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
    				        boundaryGap: true,
    				        data: map.minutes,
    				    },
    				    yAxis: {
    				        type: 'value',
    				        axisLabel: {
    							formatter: '{value} 元'
    						}
    				    },
    				    series: [
    				        {
    				            name:'单笔消费额',
    				            type:'bar',
    				            stack: '总量',
    				            data:map.avgMoney
    				        }
    				    ]
    				});
    			}
        	});
		}
	function queryBaseNotOil(){
		queryExceptLube();
		$.ajax({
			type:"post",
			url:"/sysmanager/notOil/queryNotOils",
			dataType:"JSON",
			data:{"station":$("#notOilstation").val(),"start":$("#notOilstart").datetimebox("getValue"),
				"end":$("#notOilend").datetimebox("getValue"),"date":$("#notOildate").val(),
				"query":$("#notOilquery").val(),"people":$("#shopPeople").val()
			},
			success:function(map){
				myChartnotOilMoney.setOption({
				    title: {
				        text: '总销售额'
				    },
				    tooltip: {
				        trigger: 'axis',
				    },
				    legend: {
						itemWidth:5,
				        data:['总销售额']
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
				        boundaryGap: true,
				        data: map.dates,
				    },
				    yAxis: {
				        type: 'value',
				        axisLabel: {
							formatter: '{value} 元'
						}
				    },
				    series: [
				        {
				            name:'总销售额',
				            type:'bar',
				            stack: '总量',
				            data:map.moneys
				        }
				    ]
				});
				myChartnotOilNumber.setOption({
				    title: {
				        text: '销售笔数'
				    },
				    tooltip: {
				        trigger: 'axis',
				    },
				    legend: {
						itemWidth:5,
				        data:['销售笔数']
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
				        boundaryGap: true,
				        data: map.dates,
				    },
				    yAxis: {
				        type: 'value',
				        axisLabel: {
							formatter: '{value} 笔'
						}
				    },
				    series: [
				        {
				            name:'销售笔数',
				            type:'bar',
				            stack: '总量',
				            data:map.numbers
				        }
				    ]
				});
				myChartnotOilavgMoney.setOption({
				    title: {
				        text: '单笔消费额'
				    },
				    tooltip: {
				        trigger: 'axis',
				    },
				    legend: {
						itemWidth:5,
				        data:['单笔消费额']
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
				        boundaryGap: true,
				        data: map.dates,
				    },
				    yAxis: {
				        type: 'value',
				        axisLabel: {
							formatter: '{value} 元'
						}
				    },
				    series: [
				        {
				            name:'单笔消费额',
				            type:'bar',
				            stack: '总量',
				            data:map.avgMoney
				        }
				    ]
				});
					
			}
		});
	}
    </script>
</body>
</html>