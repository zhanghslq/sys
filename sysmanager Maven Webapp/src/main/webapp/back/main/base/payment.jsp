<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>支付方式占比</title>
    <!-- 引入 echarts.js -->
</head>
<body>
<form action="">
		  请选择开始时间段：<input id="paystart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2010-10-1 2:3" style="width:150px"> 
		  请选择结束时间段：<input id="payend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="4/4/2010 0:0" style="width:150px"> 
				
		   查询分类：<select name="query" id="payquery" onchange="queryPay()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
		    查询内容：<select name="station" id="paystation">
		       			
		    	</select>
    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryPayMent()">查询</a>
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="payment" style="width:90%;height:80%;"></div>
    
    <script type="text/javascript">
    $(function () {
    	queryPay();
	}); 
    function queryPay() {
		 $("#paystation").empty();
		 if($("#payquery").val()=='station'){
			 $("#paystation").append($("<option></option>").text('全部油站').val('all'));
		 }
		 $.ajax({
				type:"GET",
				url:"/sysmanager/station/queryAllName",
				dataType:"JSON",
				data:{"query":$("#payquery").val()},
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#paystation").append(option);
					});
				}
			});
	}
        // 基于准备好的dom，初始化echarts实例
        var myChartPay = echarts.init(document.getElementById('payment'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
         function queryPayMent() {
        	 $.ajax({
     			type:"post",
     			url:"/sysmanager/mop/queryMop",
     			dataType:"JSON",
     			data:{"station":$("#paystation").val(),"start":$("#paystart").datetimebox("getValue"),
     				"end":$("#payend").datetimebox("getValue"),"query":$("#payquery").val()
     			},
     			success:function(map){
     				myChartPay.setOption({
     						title : {
     							text: '支付方式',
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
     							data: map.mop
     						},
     						series : [
     							{
     								name: '支付方式',
     								type: 'pie',
     								radius : '55%',
     								center: ['50%', '60%'],
     								data:map.data,
     								itemStyle: {
     									emphasis: {
     										shadowBlur: 10,
     										shadowOffsetX: 0,
     										shadowColor: 'rgba(0, 0, 0, 0.5)'
     									}
     								}
     							}
     						]
     					});
     			}
     		});
		}
        // 指定图表的配置项和数据
        
    </script>
</body>
</html>