<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>优惠券余量占比</title>
    <!-- 引入 echarts.js -->
</head>
<body>
    <div id="couponRate" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChartCouponRate = echarts.init(document.getElementById('couponRate'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
       $(function() {
    	   $.ajax({
				type:"post",
				url:"/sysmanager/coupon/queryZhanbi",
				dataType:"JSON",
				success:function(map){
					myChartCouponRate.setOption({
						title : {
							text: '优惠券余量占比',
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
							data: map.names
						},
						series : [
							{
								name: '优惠券金额',
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
       	
				}//success 
       	});//ajax
	});
    </script>
</body>
</html>