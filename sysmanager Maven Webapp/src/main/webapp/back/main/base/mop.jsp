<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>支付方式占比</title>
    <!-- 引入 echarts.js -->
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
</head>
<body>
<form action="" method="post" id="exportExcel">
 <div class="contentRight" id="contentRightHeight">
       <div class="rightDownSel" id="test">
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
                                <a href="javascript:void(0);" class="determine" onclick="queryPayMent()">确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                                <a href="javascript:void(0);"  class="determine" onclick="ExportExcel()">导出到Excel</a>
                              </div>
                           </div>
                       </div>
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择人群</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                      <a href="javascript:void(0);" onclick="ChangePeople('all')" class="titleCur">全部人群</a>
                                      <a href="javascript:void(0); " onclick="ChangePeople('vip')">会员交易</a>
                                  </div>
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
                                          <label><input name="date" type="radio" value="year" /> <i>按年展示</i></label>
                                          <label><input name="date" type="radio" value="month" /> <i>按月展示</i></label>
                                          <label><input name="date" type="radio" value="day" checked="checked"/><i>按日展示</i> </label>
                                        </div>
                                      </div>
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" name="start"  style="width:300px"  class="am-form-field" id='paystart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end"  style="width:300px"  class="am-form-field" id='payend'></div>
                                      </div>
                                      <script>
                                      $('#paystart').attr("value",getNowFormatDateOne());
                                      $('#payend').attr("value",getNowFormatDate());
											$('#paystart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#payend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryPayMent()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                        <a href="javascript:void(0);"  class="determine" onclick="ExportExcel()">导出到Excel</a>
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
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="payment" style="width:80%;height:80%;min-height: 600px;min-width: 800px"></div>
    <div id="HHTIPT" style="width:80%;height:80%;min-height: 600px;min-width: 800px"></div>
    <div id="MopList" style="width:80%;height:80%;min-height: 600px;min-width: 800px"></div>
    <script type="text/javascript">
    function ExportExcel() {
    	$("#exportExcel").attr("action","/sysmanager/mop/exportMop?people="+basePeople);
 	   	$("#exportExcel").submit();
    }
        // 基于准备好的dom，初始化echarts实例
        var basePeople="all";
      	function ChangePeople(src) {
			basePeople=src;
			queryPayMent();
		}
      	$(function() {
			queryPayMent();
		});
        var myChartPay = echarts.init(document.getElementById('payment'));
        var myChartHHTIPT=echarts.init(document.getElementById('HHTIPT'));
        var myChartMopList=echarts.init(document.getElementById('MopList'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
         function queryPayMent() {
        	 $.ajax({
     			type:"post",
     			url:"/sysmanager/mop/queryMop",
     			dataType:"JSON",
     			data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
    				"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),"type":jqchk("type"),
    				"station":jqchk("station"),"start":$("#zoushistart").val(),
     				"start":$("#paystart").val(),
     				"end":$("#payend").val(),
     				"people":basePeople,"date":$("input[name='date']:checked").val(),
     			},
     			success:function(map){
     				myChartPay.setOption({
     						title : {
     							text: '支付方式',
     							x:'center'
     						},
     						tooltip : {
     							trigger: 'item',
     							formatter: "{a} <br/>{b} : {c} ({d}%)"
     						},
     						color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88',
     						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
     						legend: {
     							top:30,
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
     								},
     								label: {
         						          normal: {
         					                    show: false,
         					                 	position: 'inside',
         					                  
         					              }
       								}
     							}
     						]
     					});//ECHARTS
     				myChartHHTIPT.setOption({
 						title : {
 							text: 'HHT IPT支付方式',
 							x:'center'
 						},
 						tooltip : {
 							trigger: 'item',
 							formatter: "{a} <br/>{b} : {c} ({d}%)"
 						},
 						color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88',
 						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
 						legend: {
 							top:30,
 							left: 'center',
 							data: ["HHT支付","IPT支付"]
 						},
 						series : [
								
 							{
 								name: '支付方式',
 								type: 'pie',
 								radius : '55%',
 								center: ['50%', '60%'],
 								data:map.all,
 								itemStyle: {
 									emphasis: {
 										shadowBlur: 10,
 										shadowOffsetX: 0,
 										shadowColor: 'rgba(0, 0, 0, 0.5)'
 									}
 								},
 								label: {
     						          normal: {
     					                    show: false,
     					                 	position: 'inside',
     					              }
   								}
 							}
 						]
 					});//Echarts完成
 					myChartMopList.setOption({
						 title: {
				                text: '支付方式趋势',
				                x:'center'
				            },
							tooltip : {
								trigger: 'axis',
								axisPointer : {            // 坐标轴指示器，坐标轴触发有效
									type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
								}
							},
							legend: {
								top:30,
								data:map.mop
							},
							 grid: {
							        top:'15%',
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
									type : 'value',
									min:0
								},
								
							],
							series : [
								{
									name:'EPS会员',
									type:'bar',
									stack: '总量',
									data:map.EPSMoney,
									 itemStyle:{  
		                                    normal:{color:'#FBCE07'}
		                                } 
						
								},
								{
									name:'优惠券',
									type:'bar',
									stack: '总量',
									data:map.couponMoney,
									itemStyle:{  
	                                    normal:{color:'#DD1D21'}  
	                                } 
								},
								{
									name:'会员优惠券',
									type:'bar',
									stack: '总量',
									data:map.vipCouponMoney,
									itemStyle:{  
	                                    normal:{color:'#89CFDC'}  
	                                } 
								},
								{
									name:'信用卡',
									type:'bar',
									stack: '总量',
									data:map.creditCardMoney,
									itemStyle:{  
	                                    normal:{color:'#009EB4'}  
	                                } 
								},
								{
									name:'壳牌车队卡',
									type:'bar',
									stack: '总量',
									data:map.teamCardMoney,
									itemStyle:{  
	                                    normal:{color:'#003C88'}  
	                                } 
								},
								{
									name:'微信支付',
									type:'bar',
									stack: '总量',
									data:map.wechatMoney,
									itemStyle:{  
	                                    normal:{color:'#BA95BE'}  
	                                }
								},
								{
									name:'支付宝支付',
									type:'bar',
									stack: '总量',
									data:map.alipayMoney,
									itemStyle:{  
	                                    normal:{color:'#641964'}  
	                                }
								},
								{
									name:'支票',
									type:'bar',
									stack: '总量',
									data:map.chequeMoney,
									itemStyle:{  
	                                    normal:{color:'#FFEAC2'}  
	                                }
								},
								{
									name:'滴滴支付',
									type:'bar',
									stack: '总量',
									data:map.didiMoney,
									itemStyle:{  
	                                    normal:{color:'#EB8705'}  
	                                }
								},
								{
									name:'现金',
									type:'bar',
									stack: '总量',
									data:map.cashMoney,
									itemStyle:{  
	                                    normal:{color:'#743410'}
	                                }
								},
								{
									name:'电子支付优惠',
									type:'bar',
									stack: '总量',
									data:map.ePaymentMoney,
									itemStyle:{  
	                                    normal:{color:'#BED50F'}  
	                                }
								},
								{
									name:'百度支付',
									type:'bar',
									stack: '总量',
									data:map.baiduMoney,
									itemStyle:{  
	                                    normal:{color:'#008433'}  
	                                }
								},
								{
									name:'第三方卡',
									type:'bar',
									stack: '总量',
									data:map.thirdPaymentMoney,
									itemStyle:{  
	                                    normal:{color:'#595959'}  
	                                }
								},
								{
									name:'车到收款',
									type:'bar',
									stack: '总量',
									data:map.carInMoney,
									itemStyle:{  
	                                    normal:{color:'#7F7F7F'}  
	                                }
								},
								{
									name:'银联钱包优惠券',
									type:'bar',
									stack: '总量',
									data:map.unionpayCouponMoney,
									itemStyle:{  
	                                    normal:{color:'pink'}  
	                                },
	                                
								},{
									name:'斑马支付',
									type:'bar',
									stack: '总量',
									data:map.zebpayMoney,
									itemStyle:{  
	                                    normal:{color:'black'}  
	                                },
	                                
								}
							]
						});
     			}
     		});
		}
        // 指定图表的配置项和数据
    </script>
<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>