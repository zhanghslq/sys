<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>油站营业额</title>
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
		请选择时间单位：<select name="date" id="zoushidate">
			    		<option value="day">日</option>
			    		<option value="month">月</option>
			    		<option value="year">年</option>
			    		<option value="minute">分钟</option>
			    		<option value="hour">小时</option>
		    	 </select>
		  请选择开始时间段：	<input id="zoushistart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2017-08-01 00:00" style="width:150px"> 
		  请选择结束时间段：<input id="zoushiend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="2017-9-10 00:00" style="width:150px">
		<br> 选择人群：<select  id="basePeople" >
		       			<option value="all">全部交易数据</option>
		       			<option value="vip">会员交易数据</option>
		    		</select>
		  查询分类：<select name="query" id="query" onchange="queryneirong()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		       			<option value="city">城市</option>
		       			<option value="gasoline">汽油商圈类型</option>
		       			<option value="diesel">柴油商圈类型</option>
		       			<option value="time">开业时间</option>
		       			<option value="location">位置</option>
		    		</select>
		    查询内容：<select name="station" id="bazhstation">
		       			
		    		</select>
		  <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="querybaseOil()">查询</a>  
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="amount" style="width:80%;height:60%;"></div>
    <div id="number" style="width:80%;height:60%;"></div>
    <div id="single" style="width:80%;height:60%;"></div>
    
    <script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
        var amount = echarts.init(document.getElementById('amount'));
        var number = echarts.init(document.getElementById('number'));
        var single = echarts.init(document.getElementById('single'));
      //格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
         // 指定图表的配置项和数据
        function queryneirong() {
    		 $("#bazhstation").empty();
    		 if($("#query").val()=="station"){
    			 $("#bazhstation").append($("<option></option>").text('全部油站').val('all'));
    		 }
    		 $.ajax({
					type:"GET",
					url:"/sysmanager/station/queryAllName",
					dataType:"JSON",
					data:{"query":$("#query").val()},
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#bazhstation").append(option);
							
						});
					}
				});
		}
        
     $(function () {
    	 queryneirong();
    	 querybaseOil();
	}); 
	function querybaseOil(){
		$.ajax({
			type:"post",
			url:"/sysmanager/oil/queryOils",
			dataType:"JSON",
			data:{"station":$("#bazhstation").val(),"start":$("#zoushistart").datetimebox("getValue"),
				"end":$("#zoushiend").datetimebox("getValue"),"date":$("#zoushidate").val(),
				"query":$("#query").val(),"people":$("#basePeople").val()
			},
			success:function(map){
				amount.setOption({
				    title: {
				        text: '总销量'
				    },
				    tooltip: {
				        trigger: 'axis',
				        formatter: '{b}: {c}千升'
				    },
				    legend: {
						itemWidth:5,
				        data:['总销量']
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
							formatter: '{value} 千升'
						}
				    },
				    series: [
				        {
				            name:'总销量',
				            type:'bar',
				            stack: '总量',
				            data:map.amounts
				        }
				    ]
				});
				number.setOption({
				    title: {
				        text: '销售笔数'
				    },
				    tooltip: {
				        trigger: 'axis'
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
				single.setOption({
				    title: {
				        text: '单车加油量'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
						itemWidth:5,
				        data:['单车加油量']
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
							formatter: '{value} 升'
						}
				    },
				    series: [
				        {
				            name:'单车加油量',
				            type:'bar',
				            stack: '总量',
				            data:map.avgAmounts
				        }
				    ]
				});
				
			}
		});
	}
    </script>
    
    <div style="height: 100px ;"></div><!-- 最下面填充的div -->
</body>
</html>