<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>优惠券</title>
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/IconExtension.css">
    <link rel="stylesheet" href="/sysmanager/back/datepicker/assets/css/amazeui.min.css"/>
	<link rel="stylesheet" href="/sysmanager/back/datetimepicker-master/css/amazeui.datetimepicker.css"/>
	<link rel="stylesheet" href="/sysmanager/back/platform2/css/common.css" />
    <link rel="stylesheet" href="/sysmanager/back/platform2/css/index.css" />
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
    <script src="/sysmanager/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="/sysmanager/back/easyui/js/form.validator.rules.js"></script>
    <script src="/sysmanager/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="/sysmanager/back/echar/echarts.js"></script>
    <script src="/sysmanager/back/datetimepicker-master/js/amazeui.datetimepicker.js"></script>
    <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
</head>
<body>
<form action="" method="post" id="exportExcel">
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div class="contentRight">
       <div class="rightDownSel">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                   <!-- <div class="selemeTitle">
                           <div class="selemenu"><span>选择类型</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                  		<a >燃油优惠券</a>
                                  		<a >非油优惠券</a>
                                  </div>
                              </div>
                           </div>
                       </div> -->
                       <!-- 这是跟选择油站平级的 -->
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择时间</span></div>
                           <div class="seleContent selTime">
                              <div class="downCont selTimeMain">
                                  <div class="selTimeInfo">
                                     <div class="minimum">
                                        <em>最小时间单位</em>
                                        <div class="minimumRadio">
                                          <label><input name="date" type="radio" value="year" /> <i>按年展示</i> </label>
                                          <label><input name="date" type="radio" value="month" /> <i>按月展示</i> </label>
                                          <label><input name="date" type="radio" value="day" checked="checked"/> <i>按日展示</i> </label>
                                        </div>
                                      </div>
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input name="start"  style="width:300px" class="am-form-field" id='couponstart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input name="end"  style="width:300px"  class="am-form-field" id='couponend'></div>
                                      </div>
                                      <script>
                                      $('#couponstart').attr("value",getNowFormatDateOne());
                                      $('#couponend').attr("value",getNowFormatDate());
											$('#couponstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#couponend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryCoupon()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                        <a href="javascript:void(0);" class="determine" onclick="ExportExcel()">导出到Excel</a>
                                      </div>
                                  </div>
                              </div>
                           </div>
                       </div>
                   </div>
               </div>
               
           </div>
       </div>
    </div>
    </form>
    <div id="coupon" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    function ExportExcel() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCoupon");
 	   	$("#exportExcel").submit();
    }
    	$(function() {
			queryCoupon();
		});
        // 基于准备好的dom，初始化echarts实例
        var myChartCoupon = echarts.init(document.getElementById('coupon'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryCoupon() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/coupon/query",
				dataType:"JSON",
				data:{"start":$("#couponstart").val(),
					"end":$("#couponend").val(),
					"date":$("input[name='date']:checked").val()},
				success:function(map){
					myChartCoupon.setOption({
			            title: {
			                text: '优惠券',
			                x:'center'
			            },
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			                }
			            },
			            legend: {
			            	top:'30',
			                data:['燃油人工赠送','燃油积分兑换','燃油会员活动','会员活动折扣（笔数）','燃油H5活动发放','燃油积分兑换核销','燃油人工赠送核销','燃油会员活动核销'
			                      ,'燃油会员活动折扣核销（笔数）','燃油H5活动赠送核销','非油积分兑换','非油人工赠送','非油会员活动发放','非油H5活动发放','非油积分兑换核销',
			                      '非油人工赠送核销','非油会员活动核销','非油H5活动核销']
			            },
			            grid:{top:'19%'},
			            color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88','#BA95BE','#641964','#FFEAC2','#EB8705',
			                   '#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88','#BA95BE','#641964','#FFEAC2','#EB8705'],
			            xAxis: {
			                data: map.days
			            },
			            yAxis: {},
				            series: [{
				                name: '燃油人工赠送',
				                type: 'bar',
				                stack:'发放',
				                data: map.oilreissued
				            },{
				                name: '燃油积分兑换',
				                type: 'bar',
				                stack:'发放',
				                data: map.oilScore
				            },{
				                name: '燃油会员活动',
				                type: 'bar',
				                stack:'发放',
				                data: map.oilorder
				            },{
				                name: '会员活动折扣（笔数）',
				                type: 'bar',
				                stack:'发放',
				                data: map.oilorderNum
				            },{
				                name: '燃油H5活动发放',
				                type: 'bar',
				                stack:'发放',
				                data: map.oilhfive
				            },{
				                name: '非油积分兑换',
				                type: 'bar',
				                stack:'发放',
				                data: map.shopScore
				            },{
				                name: '非油人工赠送',
				                type: 'bar',
				                stack:'发放',
				                data: map.shopReissued
				            },{
				                name: '非油会员活动发放',
				                type: 'bar',
				                stack:'发放',
				                data: map.shopOrder
				            },{
				                name: '非油H5活动发放',
				                type: 'bar',
				                stack:'发放',
				                data: map.shophfive
				            },{
				                name: '燃油人工赠送核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.oilreissuedused
				            },{
				                name: '燃油积分兑换核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.oilscoreused
				            },{
				                name: '燃油会员活动核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.oilorderused
				            },{
				                name: '燃油会员活动折扣核销（笔数）',
				                type: 'bar',
				                stack:'核销',
				                data: map.oilordernumused
				            },{
				                name: '燃油H5活动赠送核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.oilhfiveused
				            },{
				                name: '非油积分兑换核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.shopScoreUsed
				            },{
				                name: '非油人工赠送核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.shopReissuedUsed
				            },{
				                name: '非油会员活动核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.shopOrderUsed
				            },{
				                name: '非油H5活动核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.shophfiveUsed
				            }]
			        });
        	
				}//success 
        	});//ajax
       }
    </script>
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
							x:'center'
						},
						tooltip : {
							trigger: 'item',
							formatter: "{a} <br/>{b} : {c} ({d}%)"
						},
						legend: {
							top:30,
							data: map.names
						},
						color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88',
 						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
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
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>