<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <link rel="stylesheet" href="/sysmanager/back/platform2/css/coupon.css" />
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
    <script src="/sysmanager/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="/sysmanager/back/easyui/js/form.validator.rules.js"></script>
    <script src="/sysmanager/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="/sysmanager/back/echar/echarts.js"></script>
    <script src="/sysmanager/back/datetimepicker-master/js/amazeui.datetimepicker.js"></script>
    <script type="text/javascript" src="/sysmanager/back/platform2/js/coupon.js"></script>
</head>
<body>

    <div class="contentRight">
    <div class="timeEndIng" id="dataTime"></div>
     	<script type="text/javascript">
	     	$.ajax({
				type:"GET",
				url:"/sysmanager/time/queryTime",
                data:{name:'res_vipcoupon_oilnotoil'},
				success:function(map){
					$("#dataTime").html("数据截止时间："+map);
				}
	     	});
     	</script>
      
<form action="" method="post" id="exportExcel">
       <div class="rightDownSel">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                   <div class="selemeTitle">
                           <div class="selemenu"><span>选择区域</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                  <shiro:hasPermission name="bjvip">
                                      <a href="javascript:void(0);" onclick="ChangeArea('6')" class="titleCur">北京会员</a>
                                  </shiro:hasPermission>
                                  <shiro:hasPermission name="cdvip">
                                      <a href="javascript:void(0); " onclick="ChangeArea('5')">承德会员</a>
                                  </shiro:hasPermission>
                                  
                                  <shiro:hasPermission name="bjvip">
                                     	<script type="text/javascript">
                                     		var baseArea="6";
                                     	</script>
                                     </shiro:hasPermission>
                                     <shiro:hasPermission name="cdvip">
                                     <shiro:lacksPermission name="bjvip">
                                     	<script type="text/javascript">
                                     		var baseArea="5";
                                     	</script>
                                     </shiro:lacksPermission>
                                     </shiro:hasPermission>
                                     <shiro:lacksPermission name="bjvip">
                                     	<shiro:lacksPermission name="cdvip">
                                     	<script type="text/javascript">
                                     		var baseArea="6";
                                     	</script>
                                     	</shiro:lacksPermission>
                                     </shiro:lacksPermission>
                                  </div>
                              </div>
                           </div>
                       </div>
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
                                        <a href="javascript:void(0);" class="determine" onclick="queryCouponAll()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                        <a href="javascript:void(0);" class="determine" onclick="ExportSource()">导出到Excel</a>
                                      </div>
                                  </div>
                              </div>
                           </div>
                       </div>
             </div>
               </div>
           </div>
       </div>
                      <div class="rightDownSel">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox"> 	
                        <div class="selemeTitle">
                           <div class="selemenu"><span>选择商品</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                      <a href="javascript:void(0);" onclick="ChangeOil1()" class="titleCur">全部</a>
                                      <a href="javascript:void(0);" onclick="ChangeOil1('oil')" >油品</a>
                                      <a href="javascript:void(0); " onclick="ChangeOil1('shop')">非油</a>
                                  </div>
                              </div>
                           </div>
                       </div>
                       <script type="text/javascript">
                       		var baseOil1=null;
                       		var baseType1=null;
                       		function ChangeType1(src) {
                       			baseType1=src;
                       			queryCoupon1();
							}
                       		function ChangeOil1(src) {
                       			baseOil1=src;
                       			queryCoupon1();
							}
                       </script>
                        <div class="selemeTitle">
                           <div class="selemenu"><span>选择种类</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                      <a href="javascript:void(0);" onclick="ChangeType1()" class="titleCur">全部</a>
                                      <a href="javascript:void(0);" onclick="ChangeType1('del')" >满减</a>
                                      <a href="javascript:void(0); " onclick="ChangeType1('discount')">折扣</a>
                                  </div>
                              </div>
                           </div>
                       </div>
                       <a class="export" onclick="ExportExcelData()">导出源数据</a>
                   </div>
               </div>
           </div>
       </div>
    </form>
    </div>
    <div id="couponSource" style="width:80%;height:80%;"></div>
     <div class="rightDownSel">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                        <div class="selemeTitle">
                           <div class="selemenu"><span>选择来源</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                      <a href="javascript:void(0);" onclick="ChangeType2()" class="titleCur">全部</a>
                                      <a href="javascript:void(0);" onclick="ChangeType2('score')" >积分兑换</a>
                                      <a href="javascript:void(0);" onclick="ChangeType2('order')" >活动</a>
                                      <a href="javascript:void(0);" onclick="ChangeType2('reissued')" >人工</a>
                                      <a href="javascript:void(0); " onclick="ChangeType2('other')">其他活动</a>
                                  </div>
                              </div>
                           </div>
                       </div>
                       <script type="text/javascript">
                       		var baseType2=null;
                       		var baseCoupon2=null;
                       		function ChangeType2(src) {
                       			baseType2=src;
                       			queryCoupon2();
							}
                       		function ChangeCoupon2(src) {
                       			baseCoupon2=src;
                       			queryCoupon2();
							}
                       </script>
                        <div class="selemeTitle">
                           <div class="selemenu"><span>选择种类</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                      <a href="javascript:void(0);" onclick="ChangeCoupon2()" class="titleCur">全部</a>
                                      <a href="javascript:void(0);" onclick="ChangeCoupon2('del')" >满减</a>
                                      <a href="javascript:void(0); " onclick="ChangeCoupon2('discount')">折扣</a>
                                  </div>
                              </div>
                           </div>
                       </div>
                       <!-- 这是跟选择油站平级的 -->
                      <a class="export" style="margin-left: 30px" onclick="ExportProduct()">导出到Excel</a>
    					<a class="export" style="margin-left: 30px" onclick="ExportExcelScoreData()">导出源数据</a>
                   </div>
               </div>
           </div>
       </div>
    
    <div id="couponOil" style="width:80%;height:80%;"></div>
    
    
     <div class="rightDownSel">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                        <div class="selemeTitle">
                           <div class="selemenu"><span>选择来源</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                      <a href="javascript:void(0);" onclick="ChangeType3()" class="titleCur">全选</a>
                                      <a href="javascript:void(0);" onclick="ChangeType3('score')" >积分兑换</a>
                                      <a href="javascript:void(0); " onclick="ChangeType3('order')">活动</a>
                                      <a href="javascript:void(0); " onclick="ChangeType3('reissued')">人工</a>
                                      <a href="javascript:void(0); " onclick="ChangeType3('other')">其他活动</a>
                                  </div>
                              </div>
                           </div>
                       </div>
                       <script type="text/javascript">
                       		var baseOil3=null;
                       		var baseType3=null;
                       		function ChangeType3(src) {
                       			baseType3=src;
                       			queryCoupon3();
							}
                       		function ChangeOil3(src) {
                       			baseOil3=src;
                       			queryCoupon3();
							}
                       </script>
                        <div class="selemeTitle">
                           <div class="selemenu"><span>选择商品</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                      <a href="javascript:void(0);" onclick="ChangeOil3()" class="titleCur">全部</a>
                                      <a href="javascript:void(0);" onclick="ChangeOil3('oil')" >燃油</a>
                                      <a href="javascript:void(0); " onclick="ChangeOil3('shop')">非油</a>
                                  </div>
                              </div>
                           </div>
                       </div>
                       <!-- 这是跟选择油站平级的 -->
                      <a class="export" style="margin-left: 30px" onclick="ExportType()">导出到Excel</a>
    					<a class="export" style="margin-left: 30px" onclick="ExportExcelShopData()">导出源数据</a>
                   </div>
               </div>
           </div>
       </div>
    <div id="couponNature" style="width:80%;height:80%;"></div>
     <!-- 这是跟选择油站平级的 -->
     <a class="export" style="margin-left: 30px" onclick="ExportDisSource()">导出到Excel</a>
    <a class="export" style="margin-left: 30px" onclick="ExportExcelOilData()">导出源数据</a>
    <div id="couponDiscSource" style="width:80%;height:80%;"></div>
    
    <a class="export" style="margin-left: 30px" onclick="ExportExcelTatics()">导出到Excel</a>
    <a class="export" style="margin-left: 30px" onclick="ExportExcelDataName()">导出源数据</a>
    <div id="couponTactics" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    function ChangeArea(src) {
		baseArea=src;
		queryCoupon();
		queryCouponAll();
    }
    function ExportExcelTatics() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportTactics");
 	   	$("#exportExcel").submit();
    }
    function ExportExcelDataName() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCouponDataByName");
 	   	$("#exportExcel").submit();
    }
    function ExportSource() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportSource?type="+baseType1+"&&oil="+baseOil1+"&&city="+baseArea);
 	   	$("#exportExcel").submit();
    }
    function ExportExcelData() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCouponData?type="+baseType1+"&&oil="+baseOil1);
 	   	$("#exportExcel").submit();
    }
    function ExportDisSource() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/ExportDisSource?city="+baseArea);
 	   	$("#exportExcel").submit();
    }
    function ExportExcelOilData() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCouponDataOil");
 	   	$("#exportExcel").submit();
    }
    function ExportType() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCouponShop");
 	   	$("#exportExcel").submit();
    }
    function ExportExcelShopData() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCouponDataShop?type="+baseType3+"&&coupon="+baseOil3);
 	   	$("#exportExcel").submit();
    }
    function ExportProduct() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportOilNotOil?city="+baseArea+"&&coupon="+baseCoupon2+"&&type="+baseType2);
 	   	$("#exportExcel").submit();
    }
    function ExportExcelScoreData() {
    	$("#exportExcel").attr("action","/sysmanager/coupon/exportCouponScoreData?type="+baseType2+"&&coupon+"+baseCoupon2);
 	   	$("#exportExcel").submit();
    }
    	$(function() {
			queryCoupon();
			queryCouponAll();
		});
        // 基于准备好的dom，初始化echarts实例
        var myChartCouponDiscSource = echarts.init(document.getElementById('couponDiscSource'));
        
        var myChartCouponOil = echarts.init(document.getElementById('couponOil'));
        
        var myChartCouponNature= echarts.init(document.getElementById('couponNature'));
        
        var myChartCouponSource = echarts.init(document.getElementById('couponSource'));
        
        var myChartCouponTactics = echarts.init(document.getElementById('couponTactics'));
        
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。\
        function queryCoupon1() {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/coupon/querySource",
				dataType:"JSON",
				data:{"start":$("#couponstart").val(),
					"end":$("#couponend").val(),
					"date":$("input[name='date']:checked").val(),"city":baseArea,
					"oil":baseOil1,"type":baseType1},
				success:function(map){
					myChartCouponSource.setOption({
			            title: {
			                text: '优惠券发放与核销（来源）',
			                x:'center',
			                subtext:'单位为元，折扣优惠券仅包括核销金额'
			            },
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			                }
			            },
			            legend: {
			            	top:'50',
			                data:['积分兑换发放','活动发放','人工发放','其他发放','积分兑换核销','活动核销','人工核销','其他核销']
			            },
			            grid:{top:'19%'},
			            color:['#FBCE07','#DD1D21','#89CFDC','#009EB4',
			                   '#FBCE07','#DD1D21','#89CFDC','#009EB4'],
			            xAxis: {
			                data: map.days
			            },
			            yAxis: {},
			            series: [{
			                name: '积分兑换发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.scoreAll
			            },{
			                name: '活动发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.orderAll
			            },{
			                name: '人工发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.reissuedAll
			            },{
			                name: '其他发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.otherAll
			            },{
			                name: '积分兑换核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.scoreUsed
			            },{
			                name: '活动核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.orderUsed
			            },{
			                name: '人工核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.reissuedUsed
			            },{
			                name: '其他核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.otherUsed
			            }]
			        });
        	
				}//success 
        	});//ajax
		}
        function queryCoupon2() {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/coupon/queryOil",
				dataType:"JSON",
				data:{"start":$("#couponstart").val(),
					"end":$("#couponend").val(),"city":baseArea,"type":baseType2,"coupon":baseCoupon2},
				success:function(map){
					myChartCouponOil.setOption({
			            title: {
			                text: '优惠券发放与核销（商品）',
			                x:'center',
			                subtext:'单位为元，折扣优惠券仅包括核销金额'
			            },
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			                }
			            },
			            legend: {
			            	top:'50',
			                data:['燃油发放','非油发放','燃油核销','非油核销']
			            },
			            grid:{top:'19%'},
			            color:['#FBCE07','#DD1D21','#FBCE07','#DD1D21'],
			            xAxis: {
			                data: map.days
			            },
			            yAxis: {},
			            series: [{
			                name: '燃油发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.oilAll
			            },{
			                name: '非油发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.shopAll
			            },{
			                name: '燃油核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.oilUsed
			            },{
			                name: '非油核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.shopUsed
			            }]
			        });
        	
				}//success 
        	});//ajax
		}
        function queryCoupon3() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/coupon/queryNature",
				dataType:"JSON",
				data:{"start":$("#couponstart").val(),
					"end":$("#couponend").val(),
					"date":$("input[name='date']:checked").val(),"type":baseType3,"coupon":baseOil3,"city":baseArea},
				success:function(map){
					myChartCouponNature.setOption({
			            title: {
			                text: '优惠券发放与核销（种类）',
			                x:'center',
			                subtext:'单位为元，折扣优惠券仅包括核销金额'
			            },
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			                }
			            },
			            legend: {
			            	top:'50',
			                data:['满减发放','折扣发放','满减核销','折扣核销']
			            },
			            grid:{top:'19%'},
			            color:['#FBCE07','#DD1D21',
			                   '#FBCE07','#DD1D21',],
			            xAxis: {
			                data: map.days
			            },
			            yAxis: {},
			            series: [{
			                name: '折扣发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.disAll
			            },{
			                name: '满减发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.delAll
			            },{
			                name: '折扣核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.disUsed
			            },{
			                name: '满减核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.delUsed
			            }]
			        });
        	
				}//success 
        	});//ajax
		}
        function queryCouponAll() {
			queryCoupon1();
			queryCoupon2();
			queryCoupon3();
			queryCoupon();
			$.ajax({
				type:"post",
				url:"/sysmanager/coupon/queryDiscSource",
				dataType:"JSON",
				data:{"start":$("#couponstart").val(),
					"end":$("#couponend").val(),
					"date":$("input[name='date']:checked").val(),"city":baseArea},
				success:function(map){
					myChartCouponDiscSource.setOption({
			            title: {
			                text: '折扣优惠券发放与核销（来源）',
			                x:'center',
			                subtext:'单位为个，均为燃油优惠券'
			            },
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			                }
			            },
			            legend: {
			            	top:'50',
			                data:['积分兑换发放','活动发放','人工发放','其他发放','积分兑换核销','活动核销','人工核销','其他核销']
			            },
			            grid:{top:'19%'},
			            color:['#FBCE07','#DD1D21','#89CFDC','#009EB4',
			                   '#FBCE07','#DD1D21','#89CFDC','#009EB4'],
			            xAxis: {
			                data: map.days
			            },
			            yAxis: {},
			            series: [{
			                name: '积分兑换发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.scoreAll
			            },{
			                name: '活动发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.orderAll
			            },{
			                name: '人工发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.reissuedAll
			            },{
			                name: '其他发放',
			                type: 'bar',
			                stack:'发放',
			                data: map.otherAll
			            },{
			                name: '积分兑换核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.scoreUsed
			            },{
			                name: '活动核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.orderUsed
			            },{
			                name: '人工核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.reissuedUsed
			            },{
			                name: '其他核销',
			                type: 'bar',
			                stack:'核销',
			                data: map.otherUsed
			            }]
			        });
        	
				}//success 
        	});//ajax
		}
        function queryCoupon() {
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
       <div class="rightDownSel">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择油站</span></div>
                           <div class="seleContent">
                              <div class="downCont">
                                  <div class="downNavtab">
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
                       <a href="javascript:void(0);" class="export" onclick="exportDataByStation()">导出源数据</a>
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
	    function exportDataByStation() {
	    	$("#byStationCoupon").attr("action","/sysmanager/coupon/exportCouponDataByStation");
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
			                text: '直降金额',
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
			                data:['直降金额']
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
				                name: '直降金额',
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
			            	top:'10%',
			                data:['燃油积分兑换满减核销','燃油人工赠送满减核销','燃油活动满减核销'
			                      ,'燃油活动折扣核销','燃油其他活动满减核销','非油积分兑换满减核销',
			                      '非油人工赠送满减核销','非油活动满减核销','非油其他活动满减核销','燃油其他活动折扣核销']
			            },
			            grid:{top:'19%'},
			            color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88','#BA95BE','#641964','#FFEAC2','#EB8705',
			                   '#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88','#BA95BE','#641964','#FFEAC2','#EB8705'],
			            xAxis: {
			                data: map.days
			            },
			            yAxis: {},
				            series: [{
				                name: '燃油人工赠送满减核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.oilreissuedused
				            },{
				                name: '燃油积分兑换满减核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.oilscoreused
				            },{
				                name: '燃油活动满减核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.oilorderused
				            },{
				                name: '燃油活动折扣核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.oilordernumused
				            },{
				                name: '燃油其他活动满减核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.oilhfiveused
				            },{
				                name: '非油积分兑换满减核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.shopScoreUsed
				            },{
				                name: '非油人工赠送满减核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.shopReissuedUsed
				            },{
				                name: '非油活动满减核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.shopOrderUsed
				            },{
				                name: '非油其他活动满减核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.shophfiveUsed
				            },{
				                name: '燃油其他活动折扣核销',
				                type: 'bar',
				                stack:'核销',
				                data: map.otherUsed
				            }]
			        });
        	
				}
	    	});
		}
    </script>	
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>