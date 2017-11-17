<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>分油品走势图</title>
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
		  请选择时间单位：<select name="date" id="oilzoushidate">
			    		<option value="day">日</option>
			    		<option value="month">月</option>
			    		<option value="year">年</option>
			    		<option value="minute">分钟</option>
			    		<option value="hour">小时</option>
		    	 </select>
		  请选择开始时间段：	<input id="oilzoushistart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2017-10-01 00:00" style="width:150px"> 
		  请选择结束时间段：<input id="oilzoushiend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="2017-10-10 00:00" style="width:150px">
				
		  查询分类：<select name="query" id="oilquery" onchange="queryrong()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
		    查询内容：<select name="station" id="oilzoushistation">
		       			
		    	</select>
		
		  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="test()">查询</a>  
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="oilzoushi" style="width:90%;height:80%;"></div>
    
    <script type="text/javascript">
   
    // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('oilzoushi'));
      //格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
         // 指定图表的配置项和数据
         
        function queryrong() {
    		 $("#oilzoushistation").empty();
    		 if($("#oilquery").val()=='station'){
    			 $("#oilzoushistation").append($("<option></option>").text('全部油站').val('all'));
    		 }
    		 $.ajax({
					type:"GET",
					url:"/sysmanager/station/queryAllName",
					dataType:"JSON",
					data:{"query":$("#oilquery").val()},
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#oilzoushistation").append(option);
						});
					}
				});
		}
        // 使用刚指定的配置项和数据显示图表。
     $(function () {
    	 queryrong();
	}); 
	function test(){
		$.ajax({
			type:"post",
			url:"/sysmanager/oil/queryByOils",
			dataType:"JSON",
			data:{"station":$("#oilzoushistation").val(),"start":$("#oilzoushistart").datetimebox("getValue"),
				"end":$("#oilzoushiend").datetimebox("getValue"),"date":$("#oilzoushidate").val(),"query":$("#oilquery").val()
			},
			success:function(map){
					 myChart.setOption({
							tooltip : {
								trigger: 'axis',
								axisPointer : {            // 坐标轴指示器，坐标轴触发有效
									type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
								}
							},
							legend: {
								data:map.allName
							},
							toolbox: {
								show : true,
								feature : {
									mark : {show: true},
									dataView : {show: true, readOnly: false},
									magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
									restore : {show: true},
									saveAsImage : {show: true}
								}
							},
							calculable : true,
							
							xAxis : [
								{
									type : 'category',
									data : map.dates
								}
							],
							yAxis : [
								{
									type : 'value'
								}
							],
							series : [
								{
									name:'0#',
									type:'bar',
									stack: '总量',
									data:map.litre0,
									 itemStyle:{  
		                                    normal:{color:'#DD1D21'}  
		                                } 
						
								},
								{
									name:'-10#',
									type:'bar',
									stack: '总量',
									data:map.litre10
								},
								{
									name:'-20#',
									type:'bar',
									stack: '总量',
									data:map.litre20
								},
								{
									name:'92#',
									type:'bar',
									stack: '总量',
									data:map.litre92,
									itemStyle:{  
	                                    normal:{color:'#FBCE07'}  
	                                } 
								},
								{
									name:'95#',
									type:'bar',
									stack: '总量',
									data:map.litre95,
									itemStyle:{  
	                                    normal:{color:'#404040'}  
	                                } 
								},
								{
									name:'97#',
									type:'bar',
									stack: '总量',
									data:map.litre97
								}
							]
						});
					
			}
		});
	}
    </script>
</body>
</html>