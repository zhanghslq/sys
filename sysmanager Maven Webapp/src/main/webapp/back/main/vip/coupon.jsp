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
    <div class="timeEndIng" id="dataTime"></div>
     	<script type="text/javascript">
	     	$.ajax({
				type:"GET",
				url:"/sysmanager/time/queryThree",
				success:function(map){
					$("#dataTime").html("数据截止时间："+map);
				}
	     	});
     	</script>
       <div class="rightDownSel">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
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
                       <a class="export" onclick="ExportExcelData()">导出源数据</a>
                   </div>
               </div>
               
           </div>
       </div>
    </div>
    </form>
    <div id="couponAll" style="width:80%;height:80%;"></div>
    <a class="export" style="margin-left: 30px" onclick="ExportExcelOil()">导出到Excel</a>
    <a class="export" style="margin-left: 30px" onclick="ExportExcelOilData()">导出源数据</a>
    <div id="couponOil" style="width:80%;height:80%;"></div>
    <a class="export" style="margin-left: 30px" onclick="ExportExcelShop()">导出到Excel</a>
    <a class="export" style="margin-left: 30px" onclick="ExportExcelShopData()">导出源数据</a>
    <div id="couponShop" style="width:80%;height:80%;"></div>
    <a class="export" style="margin-left: 30px" onclick="ExportExcelScore()">导出到Excel</a>
    <a class="export" style="margin-left: 30px" onclick="ExportExcelScoreData()">导出源数据</a>
    <div id="couponScoreChart" style="width:80%;height:80%;"></div>
    
    <a class="export" style="margin-left: 30px" onclick="ExportExcelTatics()">导出到Excel</a>
    <div id="couponTactics" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    function ExportExcelTatics() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportTactics");
 	   	$("#exportExcel").submit();
    }
    function ExportExcel() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCoupon");
 	   	$("#exportExcel").submit();
    }
    function ExportExcelData() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCouponData");
 	   	$("#exportExcel").submit();
    }
    function ExportExcelOil() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCouponOil");
 	   	$("#exportExcel").submit();
    }
    function ExportExcelOilData() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCouponDataOil");
 	   	$("#exportExcel").submit();
    }
    function ExportExcelShop() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCouponShop");
 	   	$("#exportExcel").submit();
    }
    function ExportExcelShopData() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCouponDataShop");
 	   	$("#exportExcel").submit();
    }
    function ExportExcelScore() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportScore");
 	   	$("#exportExcel").submit();
    }
    function ExportExcelScoreData() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCouponScoreData");
 	   	$("#exportExcel").submit();
    }
    	$(function() {
			queryCoupon();
		});
        // 基于准备好的dom，初始化echarts实例
        var myChartCoupon = echarts.init(document.getElementById('couponAll'));
        var myChartCouponOil = echarts.init(document.getElementById('couponOil'));
        var myChartCouponShop = echarts.init(document.getElementById('couponShop'));
        var myChartCouponScore = echarts.init(document.getElementById('couponScoreChart'));
        var myChartCouponTactics = echarts.init(document.getElementById('couponTactics'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryCoupon() {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/coupon/queryScore",
				dataType:"JSON",
				data:{"start":$("#couponstart").val(),
					"end":$("#couponend").val(),
					"date":$("input[name='date']:checked").val()},
				success:function(map){
					myChartCouponScore.setOption({
			            title: {
			                text: '优惠券发放与核销（积分兑换）',
			                x:'center',
			                subtext:'单位为元'
			            },
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			                }
			            },
			            legend: {
			            	top:'50',
			                data:['燃油兑换','便利店兑换','燃油核销','便利店核销']
			            },
			            grid:{top:'19%'},
			            color:['#FBCE07','#DD1D21','#FBCE07','#DD1D21'],
			            xAxis: {
			                data: map.days
			            },
			            yAxis: {},
			            series: [{
			                name: '燃油兑换',
			                type: 'bar',
			                stack:'发放',
			                data: map.oilGive
			            },{
			                name: '便利店兑换',
			                type: 'bar',
			                stack:'发放',
			                data: map.shopGive
			            },{
			                name: '燃油核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.oilUsed
			            },{
			                name: '便利店核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.shopUsed
			            }]
			        });
        	
				}//success 
        	});//ajax
        	$.ajax({
				type:"POST",
				url:"/sysmanager/coupon/queryTactics",
				dataType:"JSON",
				data:{"start":$("#couponstart").val(),
					"end":$("#couponend").val(),},
				success:function(map){
					myChartCouponTactics.setOption({
			            title: {
			                text: '优惠券发放与核销（活动）',
			                x:'center',
			                subtext:'单位为笔'
			            },
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			                }
			            },
			            legend: {
			            	top:'50',
			                data:['活动发放','活动核销']
			            },
			            grid:{top:'19%'},
			            color:['#FBCE07','#DD1D21','#FBCE07','#DD1D21'],
			            xAxis: {
			                data: map.names
			            },
			            yAxis: {},
			            series: [{
			                name: '活动发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.allNumber
			            },{
			                name: '活动核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.usedNumber
			            }]
			        });
        	
				}//success 
        	});//ajax
        	$.ajax({
				type:"post",
				url:"/sysmanager/coupon/queryAlls",
				dataType:"JSON",
				data:{"start":$("#couponstart").val(),
					"end":$("#couponend").val(),
					"date":$("input[name='date']:checked").val()},
				success:function(map){
					myChartCoupon.setOption({
			            title: {
			                text: '优惠券发放与核销（整体）',
			                x:'center',
			                subtext:'发放与核销单位为元'
			            },
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			                }
			            },
			            legend: {
			            	top:'50',
			                data:['燃油发放','便利店发放','积分兑换','燃油核销','便利店核销','积分兑换核销','其他活动发放','其他活动核销']
			            },
			            grid:{top:'19%'},
			            color:['#FBCE07','#DD1D21','#89CFDC','#009EB4',
			                   '#FBCE07','#DD1D21','#89CFDC','#009EB4'],
			            xAxis: {
			                data: map.days
			            },
			            yAxis: {},
				            series: [{
				                name: '燃油发放',
				                type: 'bar',
				                stack:'发放',
				                data: map.oilGive
				            },{
				                name: '便利店发放',
				                type: 'bar',
				                stack:'发放',
				                data: map.shopGive
				            },{
				                name: '积分兑换',
				                type: 'bar',
				                stack:'发放',
				                data: map.scoreGive
				            },{
				                name: '其他活动发放',
				                type: 'bar',
				                stack:'发放',
				                data: map.orderGive
				            },{
				                name: '燃油核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.oilUsed
				            },{
				                name: '便利店核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.shopUsed
				            },{
				                name: '积分兑换核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.scoreUsed
				            },{
				                name: '其他活动核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.orderUsed
				            }]
			        });
        	
				}//success 
        	});//ajax
        	$.ajax({
				type:"post",
				url:"/sysmanager/coupon/queryCouponOil",
				dataType:"JSON",
				data:{"start":$("#couponstart").val(),
					"end":$("#couponend").val(),
					"date":$("input[name='date']:checked").val()},
				success:function(map){
					myChartCouponOil.setOption({
			            title: {
			                text: '优惠券发放与核销（油品）',
			                x:'center',
			                subtext:'折扣发放与折扣核销单位为个，其余单位为元'
			            },
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			                }
			            },
			            legend: {
			            	top:'50',
			                data:['折扣发放','立减发放','赠送发放','折扣核销','立减核销','赠送核销']
			            },
			            grid:{top:'19%'},
			            color:['#FBCE07','#DD1D21','#89CFDC',
			                   '#FBCE07','#DD1D21','#89CFDC'],
			            xAxis: {
			                data: map.days
			            },
			            yAxis: {},
				            series: [{
				                name: '折扣发放',
				                type: 'bar',
				                stack:'发放',
				                data: map.discountGive
				            },{
				                name: '立减发放',
				                type: 'bar',
				                stack:'发放',
				                data: map.reductionGive
				            },{
				                name: '赠送发放',
				                type: 'bar',
				                stack:'发放',
				                data: map.givingGive
				            },{
				                name: '折扣核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.discountUsed
				            },{
				                name: '立减核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.reductionUsed
				            },{
				                name: '赠送核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.givingUsed
				            }]
			        });
        	
				}//success 
        	});//ajax
        	$.ajax({
				type:"post",
				url:"/sysmanager/coupon/queryCouponShop",
				dataType:"JSON",
				data:{"start":$("#couponstart").val(),
					"end":$("#couponend").val(),
					"date":$("input[name='date']:checked").val()},
				success:function(map){
					myChartCouponShop.setOption({
			            title: {
			                text: '优惠券发放与核销（便利店）',
			                x:'center',
			                subtext:'折扣发放与折扣核销单位为个，其余单位为元'
			            },
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			                }
			            },
			            legend: {
			            	top:'50',
			                data:['折扣发放','立减发放','赠送发放','折扣核销','立减核销','赠送核销']
			            },
			            grid:{top:'19%'},
			            color:['#FBCE07','#DD1D21','#89CFDC',
			                   '#FBCE07','#DD1D21','#89CFDC'],
			            xAxis: {
			                data: map.days
			            },
			            yAxis: {},
			            series: [{
			                name: '折扣发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.discountGive
			            },{
			                name: '立减发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.reductionGive
			            },{
			                name: '赠送发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.givingGive
			            },{
			                name: '折扣核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.discountUsed
			            },{
			                name: '立减核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.reductionUsed
			            },{
			                name: '赠送核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.givingUsed
			            }]
			        });
        	
				}//success 
        	});//ajax
        	
       }
    </script>
    <a style="margin-left: 30px" class="export" href="/sysmanager/coupon/exportCouponRate">导出到Excel</a>
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
    <form id="byStationCoupon" method="post">
    <div class="contentRight" id="contentRightHeight">
       <div class="rightDownSel" >
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择油站</span></div>
                           <div class="seleContent">
                              <div class="downCont">
                                  <div class="downNav">
                                      <a href="javascript:void(0);" class="titleCur">城市</a>
                                      <a href="javascript:void(0);" onclick="queryAdministriveRegionBy()">行政区</a>
                                      <a href="javascript:void(0);" onclick="querySalesAreaBy()">销售区</a>
                                      <a href="javascript:void(0);" onclick="queryGasolineBy()">商圈类型</a>
                                      <a href="javascript:void(0);" onclick="queryLocationBy()">位置</a>
                                      <a href="javascript:void(0);" onclick="queryOpenDateBy()">开业时间</a>
                                      <a href="javascript:void(0);" onclick="queryTypeBy()">油站类型</a>
                                      <a href="javascript:void(0);" onclick="queryStationBy()">站名</a>
                                  </div>
                                  <div class="downContInfo">
                                      <ul style="display: block;" id="citys">
                                      
                                      </ul>
                                      <ul id="regions">
                                      </ul>
                                      <ul id="sales">
                                      </ul>
                                      <ul id="gasolines">
                                      </ul>
                                      <ul id="location">
                                          
                                      </ul>
                                      <ul id="openDate">
                                         
                                      </ul>
                                      <ul id="types">
                                         
                                      </ul>
                                      <ul id="station">
                                          
                                      </ul>
                                  </div>
                              </div>
                              <div class="screenMain" >
                                  <ul id="tagContent">
                                      
                                  </ul>
		                       </div>
                              <div class="downOperation">
                                  <a href="javascript:void(0);" class="determine" onclick="queryCouponByStation()">确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                              </div>
                           </div>
                       </div>
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择时间</span></div>
                           <div class="seleContent selTime">
                              <div class="downCont selTimeMain">
                                  <div class="selTimeInfo">
                                     <div class="minimum">
                                        <em>最小时间单位</em>
                                        <div class="minimumRadio">
                                          <label><input name="date1" type="radio" value="year" /> <i>按年展示</i> </label>
                                          <label><input name="date1" type="radio" value="month" /> <i>按月展示</i> </label>
                                          <label><input name="date1" type="radio" value="day" checked="checked"/> <i>按日展示</i> </label>
                                        </div>
                                      </div>
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input name="start1"  style="width:300px" class="am-form-field" id='couponstartByStation'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input name="end1"  style="width:300px"  class="am-form-field" id='couponendByStation'></div>
                                      </div>
                                      <script>
                                      $('#couponstartByStation').attr("value",getNowFormatDateOne());
                                      $('#couponendByStation').attr("value",getNowFormatDate());
											$('#couponstartByStation').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#couponendByStation').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryCouponByStation()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                        <a href="javascript:void(0);" class="determine" onclick="exportByStation()">导出到Excel</a>
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
    <div style="height: 80%;width: 80%;min-height: 600px;min-width: 800px" id="byStationCouponUsed"></div>
    <a style="margin-left: 30px" class="export" onclick="exportLadder()">导出到Excel</a>
    <div style="height: 80%;width: 80%;min-height: 600px;min-width: 800px" id="byStationCouponDelete"></div>
    <script type="text/javascript">
    var byStationCouponUsed = echarts.init(document.getElementById('byStationCouponUsed'));
    var byStationCouponDelete= echarts.init(document.getElementById('byStationCouponDelete'));
	    function exportByStation() {
	    	$("#byStationCoupon").attr("action","/sysmanager/coupon/exportByStation");
	    	$("#byStationCoupon").submit();
		}
	    function exportLadder() {
	    	$("#byStationCoupon").attr("action","/sysmanager/coupon/exportLadder");
	    	$("#byStationCoupon").submit();
		}
	    $(function() {
			queryCouponByStation();
		});
	    function queryCouponByStation() {
	    	$.ajax({
				type:"POST",
				url:"/sysmanager/coupon/queryLadder",
				data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
					"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),"type":jqchk("type"),
					"station":jqchk("station"),"start":$("#couponstartByStation").val(),"end":$("#couponendByStation").val(),
					"date":$("input[name='date1']:checked").val()},
				dataType:"JSON",
				success:function(map){
					byStationCouponDelete.setOption({
			            title: {
			                text: '立减',
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
			                data:['立减金额']
			            },
			            grid:{top:'19%'},
			            color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88','#BA95BE','#641964','#FFEAC2','#EB8705',
			                   '#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88','#BA95BE','#641964','#FFEAC2','#EB8705'],
			            xAxis: {
			            	type: 'category',
			                data: map.days
			            },
			            yAxis: {
			                type: 'value',
			            },
				            series: [{
				                name: '立减金额',
				                type: 'bar',
				                data: map.number
				            }]
			        });
				}
	    	});
	    	$.ajax({
				type:"POST",
				url:"/sysmanager/coupon/queryByStation",
				data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
					"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),"type":jqchk("type"),
					"station":jqchk("station"),"start":$("#couponstartByStation").val(),"end":$("#couponendByStation").val(),
					"date":$("input[name='date1']:checked").val()},
				dataType:"JSON",
				success:function(map){
					byStationCouponUsed.setOption({
			            title: {
			                text: '优惠券分油站核销',
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
			                data:['燃油积分兑换核销','燃油人工赠送核销','燃油会员活动核销'
			                      ,'会员活动折扣核销（笔数）','燃油H5活动核销','非油积分兑换核销',
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
				                name: '会员活动折扣核销（笔数）',
				                type: 'bar',
				                stack:'核销',
				                data: map.oilordernumused
				            },{
				                name: '燃油H5活动核销',
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
        	
				}
	    	});
		}
    </script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>