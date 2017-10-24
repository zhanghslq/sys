<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>非油商品的Top10</title>
</head>
<body>
<form action="">
		  请选择开始时间段：	<input id="topstart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2016-10-01 00:00" style="width:150px"> 
		  请选择结束时间段：<input id="topend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="2017-10-10 00:00" style="width:150px">
				
		  查询分类：<select  id="topquery" onchange="queryneirongtop()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
		    查询内容：<select name="station" id="topstation">
		       			
		    		</select>
		  <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryTop()">查询</a>  
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="zoushi" style="width:95%;height:95%;"></div>
    
    <script type="text/javascript">
   
    // 基于准备好的dom，初始化echarts实例
        var myChartzoushi = echarts.init(document.getElementById('zoushi'));
      //格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
         // 指定图表的配置项和数据
        function queryneirongtop() {
    		 $("#topstation").empty();
    		 if($("#topquery").val()=="station"){
    			 $("#topstation").append($("<option></option>").text('全部油站').val('all'));
    		 }
    		 $.ajax({
					type:"GET",
					url:"/sysmanager/station/queryAllName",
					dataType:"JSON",
					data:{"query":$("#topquery").val()},
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#topstation").append(option);
						});
					}
				});
		}
        // 使用刚指定的配置项和数据显示图表。
     $(function () {
    	 queryneirongtop();
    	 
	}); 
	function queryTop(){
		$.ajax({
			type:"post",
			url:"/sysmanager/notOil/queryTop",
			dataType:"JSON",
			data:{"station":$("#topstation").val(),"start":$("#topstart").datetimebox("getValue"),
				"end":$("#topend").datetimebox("getValue"),"query":$("#topquery").val()
			},
			success:function(map){
					 myChartzoushi.setOption({
						    title: {
						        text: '便利店Top榜'
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
						        boundaryGap: true,
						        data: map.names
						    },
						    yAxis: {
						        type: 'value'
						    },
						    series: [
						        {
						            name:'销售额',
						            type:'bar',
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