<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>油枪效率分析图</title>
    <!-- 引入 echarts.js-->
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
		       			<option value="all" selected>默认全部</option>
		    		</select>
		  请选择油枪：<select name="oilgun" id="oilgun">
		       			<option value="all" selected>默认全部</option>
		    	 </select>
    		
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="oilgundiv" style="width: 80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
         var myChart = echarts.init(document.getElementById('oilgundiv'));
        
        $.ajax({
				type:"GET",
				url:"/sysmanager/city/queryAll",
				dataType:"JSON",
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#oilgunstation").append(option);
					});
					$("#oilgunstation").change();
				}
			});
       /*  $("#oilgunstation").change(function(){
        	//$("#oilgun").empty();
        		$.ajax({
    				type:"GET",
    				url:"/sysmanager/oilGun/queryGun",
    				dataType:"JSON",
    				success:function(result){
    					$.each(result,function(i,oilgun){
    						var option = $("<option></option>").text(oilgun).val(oilgun);
    						$("#oilgun").append(option);
    					});
    				}
    			});		
        	}); */
        
       
        // 指定图表的配置项和数据
			var option = {
            title: {
                text: '北京壳牌'
            },
            tooltip: {},
            legend: {
				
				
                data:[{
					name: '出油量'
				}]
				
            },
            xAxis: {
                data: ["油枪1","油枪2","油枪3"]
            },
            yAxis: {},
            series: [{
                name: '出油量',
                type: 'bar',
                data: [5, 20, 36]
            }
			
			]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</body>
</html>