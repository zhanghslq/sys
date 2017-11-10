<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>优惠券</title>
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
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <form action="">
  请选择开始时间段：	<input class="easyui-datetimebox" name="start"  id="couponstart"
        data-options="required:true,showSeconds:false" value="2017-9-21 0:0"   style="width:150px"> 
  请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-10-1 0:0"  id="couponend"
        data-options="required:true,showSeconds:false"  style="width:150px"> 
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryCoupon()">查询</a> 
</form>
    <div id="coupon" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
   
        // 基于准备好的dom，初始化echarts实例
        var myChartCoupon = echarts.init(document.getElementById('coupon'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryCoupon() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/coupon/query",
				dataType:"JSON",
				data:{"start":$("#couponstart").datetimebox("getValue"),
					"end":$("#couponend").datetimebox("getValue")},
				success:function(map){
					myChartCoupon.setOption({
			            title: {
			                text: '优惠券'
			            },
			            tooltip: {},
			            legend: {
			                data:[{
								name: '发放优惠券金额'
							},{
								name: '消费优惠券金额'
							}]
							
			            },
			            xAxis: {
			                data: map.days
			            },
			            yAxis: {},
			            series: [{
			                name: '发放优惠券金额',
			                type: 'bar',
			                data: map.all
			            },{
			                name: '消费优惠券金额',
			                type: 'bar',
			                data: map.used
			            }]
			        });
        	
				}//success 
        	});//ajax
       }
        
    </script>
</body>
</html>