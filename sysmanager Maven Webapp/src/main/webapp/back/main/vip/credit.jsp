<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>积分兑换</title>
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
    <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
</head>

<body>
<form action="" method="post" id="exportExcel">
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
                   <div class="selemeTitle">
                           <div class="selemenu"><span>选择区域</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                      <a href="javascript:void(0);" onclick="ChangeArea('BJSHELL')" class="titleCur">北京会员</a>
                                      <a href="javascript:void(0); " onclick="ChangeArea('CDSHELL')">承德会员</a>
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
                                          <label><input name="date" type="radio" value="year" /> <i>按年展示</i> </label>
                                          <label><input name="date" type="radio" value="month" /> <i>按月展示</i> </label>
                                          <label><input name="date" type="radio" value="day" checked="checked"/> <i>按日展示</i> </label>
                                        </div>
                                      </div>
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input name="start" size="16"  style="width:300px"  class="am-form-field" id='creditstart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end"  style="width:300px"  class="am-form-field" id='creditend'></div>
                                      </div>
                                      <script>
                                      		$('#creditstart').attr("value",getNowFormatDateOne());
											$('#creditstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#creditend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#creditend').attr("value",getNowFormatDate())
									  </script>
                                        <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryCredit()">确定</a>
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
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="credit" style="width:80%;height:80%;"></div>
    
    <script type="text/javascript">
    function ExportExcel() {
    	$("#exportExcel").attr("action","/sysmanager/credit/exportCredit?area="+baseArea);
 	   	$("#exportExcel").submit();
    }
    var baseArea="BJSHELL";
    function ChangeArea(src) {
		baseArea=src;
		queryCredit();
		queryZhanbi();
	}
    $(function() {
		queryCredit();
		queryZhanbi();
		queryWechatmall();
	});
        // 基于准备好的dom，初始化echarts实例
        var myChartcredit = echarts.init(document.getElementById('credit'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        // 指定图表的配置项和数据
        function queryCredit() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/credit/queryCredits",
				dataType:"JSON",
				data:{"date":$("input[name='date']:checked").val(),"start":$("#creditstart").val(),
					"end":$("#creditend").val(),"area":baseArea},
					success:function(map){
						myChartcredit.setOption({
					    title: {
					        text: '会员积分发放与兑换',
					        x:'center'
					    },
					    tooltip: {
					        trigger: 'axis'
					    },
					    legend: {
					    	top:30,
							itemWidth:5,
					        data:['兑换积分数量','发放积分数量']
					    },
					    color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88',
 						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
					    grid: {
					    	top:'10%',
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    toolbox: {
					    	right:18,
					        feature: {
					            saveAsImage: {}
					        }
					    },
					    xAxis: {
					        type: 'category',
					        boundaryGap: false,
					        data: map.days
					    },
					    yAxis: {
					        type: 'value'
					    },
					    series: [
					        {
					            name:'发放积分数量',
					            type:'line',
					            stack: '发放',
					            data:map.get
					        },
					        {
					            name:'兑换积分数量',
					            type:'line',
					            stack: '兑换',
					            data:map.used
					        }]
					});
        	
				}//success 
        	});//ajax
       }
        // 使用刚指定的配置项和数据显示图表。
       
    </script>
    <a style="margin-left: 30px" onclick="exportCreditRate()" class="export">导出到Excel</a>
        <div id="creditRate" style="width: 80%;height:80%;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        function exportCreditRate() {
			window.location.href="/sysmanager/credit/exportZhanbi?area="+baseArea;
		}
     var myChartcreditRate = echarts.init(document.getElementById('creditRate'));
      function queryZhanbi() {
    	  $.ajax({
     			type:"post",
     			url:"/sysmanager/credit/queryZhanbi",
     			dataType:"JSON",
     			data:{"area":baseArea},
     			success:function(map){
     				myChartcreditRate.setOption({
     					title : {
     						text: '积分余量占比',
     						x:'center'
     					},
     					tooltip : {
     						trigger: 'item',
     						formatter: "{a} <br/>{b} : {c} ({d}%)"
     					},
     					legend: {
     						top:30,
     						data: ['已使用','未使用']
     					},
     					color:['#FBCE07','#DD1D21','#89CFDC','#009EB4','#003C88',
  						       '#BA95BE','#641964','#FFEAC2','#EB8705','#743410','#BED50F','#008433','#595959','#7F7F7F'],
     					series : [
     						{
     							name: '积分占比',
     							type: 'pie',
     							radius : '55%',
     							center: ['50%', '60%'],
     							data:map,
     							itemStyle: {
     								emphasis: {
     									shadowBlur: 10,
     									shadowOffsetX: 0,
     									shadowColor: 'rgba(0, 0, 0, 0.5)'
     								}
     							}
     						}
     					]
     				});//Echarts
     					
     			}
     		});
	}
    </script>
    <form id="wechatmallExport" method="post">
    <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择油站</span></div>
                           <div class="seleContent">
                              <div class="downCont">
                                  <div class="downNav">
                                      <a href="javascript:void(0);" class="titleCur">站名</a>
                                  </div>
                                  <div class="downContInfo">
                                      <ul id="stationCredit" style="display: block;">
                                          <li>
	                                      	<input type='checkbox' name="CheckAll" id='stationCre' class='default'>
	                                      	<label for='stationCre'></label>
	                                      	<span>全选</span>
                                      		</li>
                                      		
                                      </ul>
                                  </div>
                              </div>
                              <div class="screenMain" >
		                                  <ul id="tagContent">
		                                      
		                                  </ul>
		                       </div>
                              <div class="downOperation">
                                <a href="javascript:void(0);" class="determine" onclick="queryWechatmall()">确定</a>
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
                                        <div class="startTime"><span>选择开始时间</span> <input name="start1" size="16"  style="width:300px"  class="am-form-field" id='wechatmallstart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end1"  style="width:300px"  class="am-form-field" id='wechatmallend'></div>
                                      </div>
                                      <script>
                                      		$('#wechatmallstart').attr("value",getNowFormatDateOne());
											$('#wechatmallstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#wechatmallend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#wechatmallend').attr("value",getNowFormatDate())
									  </script>
                                        <div class="downOperation timeOperation">
                                       <a href="javascript:void(0);" class="determine" onclick="queryWechatmall()">确定</a>
		                                <a href="javascript:void(0);" class="cancel">取消</a>
		                                <a href="javascript:void(0);" class="determine" onclick="exportStatus()">导出到Excel</a>
                                      </div>
                                  </div>
                              </div>
                           </div>
                       </div>
           </div>
     </div>
</div>
</form>
<div id="wechatmallStatus" style="height: 80%;width: 80%;min-height: 600px;min-width: 800px"></div>
<a style="margin-left: 30px" onclick="exportTop()" class="export">导出到Excel</a>
<div id="wechatmallTop" style="height: 80%;width: 80%;min-height: 600px;min-width: 800px"></div>
<a style="margin-left: 30px" onclick="exportTopAll()" class="export">导出到Excel</a>
<div id="wechatmallTopAll" style="height: 80%;width: 80%;min-height: 600px;min-width: 800px"></div>
<script type="text/javascript">
function exportTop() {
	$("#wechatmallExport").attr("action","/sysmanager/Wechatmall/exportTop");
	$("#wechatmallExport").submit();
}
function exportTopAll() {
	$("#wechatmallExport").attr("action","/sysmanager/Wechatmall/exportTopAll");
	$("#wechatmallExport").submit();
}
function exportStatus() {
	$("#wechatmallExport").attr("action","/sysmanager/Wechatmall/exportStatus");
	$("#wechatmallExport").submit();
}
	var wechatmallStatus = echarts.init(document.getElementById('wechatmallStatus'));
	var wechatmallTop = echarts.init(document.getElementById('wechatmallTop'));
	var wechatmallTopAll = echarts.init(document.getElementById('wechatmallTopAll'));
	function queryWechatmall() {
		 $.ajax({
  			type:"post",
  			url:"/sysmanager/Wechatmall/queryByStation",
  			dataType:"JSON",
  			data:{"station":jqchk("stationCre"),
  				"date":$("input[name='date1']:checked").val(),"start":$("#wechatmallstart").val(),
				"end":$("#wechatmallend").val(),},
  			success:function(map){
			wechatmallStatus.setOption({
				 title: {
		               text: '积分交易详情',
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
						data:['已退款','待付款','待发货','已完成','已取消']
					},
					toolbox: {
						show : true,
						 right:18,
						feature : {
							mark : {show: true},
							dataView : {show: true, readOnly: false},
							magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
							restore : {show: true},
							saveAsImage : {show: true}
						}
					},
					calculable : true,
					grid:{
						top:'15%'
					},
					xAxis : [
						{
							type : 'category',
							data : map.days
						}
					],
					yAxis : [
						{
							type : 'value',
							axisLabel: {
								formatter: '{value} 积分'
							}
						}
					],
					series : [
						{
							name:'已退款',
							type:'bar',
							stack: '总量',
							data:map.refunded,
							 itemStyle:{  
		                           normal:{color:'#7F7F7F'}  
		                       } 
				
						},
						{
							name:'待付款',
							type:'bar',
							stack: '总量',
							data:map.notpay,
							itemStyle:{  
		                       normal:{color:'#A6A6A6'}  
		                   } 
						},
						{
							name:'待发货',
							type:'bar',
							stack: '总量',
							data:map.tosend,
							itemStyle:{  
		                       normal:{color:'#595959'}  
		                   } 
						},
						{
							name:'已完成',
							type:'bar',
							stack: '总量',
							data:map.paid,
							itemStyle:{  
		                       normal:{color:'#DD1D21'}  
		                   } 
						},
						{
							name:'已取消',
							type:'bar',
							stack: '总量',
							data:map.cancel,
							itemStyle:{  
		                       normal:{color:'#008433'}  
		                   } 
						}
					]
				});
  				}//Success
  			});//Ajax
		 $.ajax({
				type:"post",
				url:"/sysmanager/Wechatmall/queryTop",
				dataType:"JSON",
				data:{"station":jqchk("stationCre"),
					"start":$("#wechatmallstart").val(),
					"end":$("#wechatmallend").val()},
				success:function(map){
					wechatmallTop.setOption(
								 {
									    title: {
									        text: '积分商城Top榜（实物）',
									        x:'center'
									    },
									    tooltip: {
									        trigger: 'axis',
									        axisPointer: {
									            type: 'shadow'
									        }
									    },
									    legend: {
									    	top:30,
									        data: ['兑换个数']
									    },
									    grid: {
									    	top:'10%',
									        left: '3%',
									        right: '4%',
									        bottom: '3%',
									        containLabel: true
									    },
									    xAxis: {
									        type: 'value',
									        boundaryGap: [0, 0.01],
									        axisLabel: {
												formatter: '{value} 个'
											}
									    },
									    yAxis: {
									        type: 'category',
									        data: map.names
									    },
									    series: [
									        {
									            name: '兑换个数',
									            type: 'bar',
									            data: map.datas
									        }
									    ]
									});
						 
				}
				
			});
		 $.ajax({
				type:"POST",
				url:"/sysmanager/Wechatmall/queryTopAll",
				dataType:"JSON",
				data:{"start":$("#wechatmallstart").val(),
					"end":$("#wechatmallend").val()},
				success:function(map){
					wechatmallTopAll.setOption(
								 {
									    title: {
									        text: '积分商城Top榜(全部)',
									        x:'center'
									    },
									    tooltip: {
									        trigger: 'axis',
									        axisPointer: {
									            type: 'shadow'
									        }
									    },
									    legend: {
									    	top:30,
									        data: ['兑换个数']
									    },
									    grid: {
									    	top:'10%',
									        left: '3%',
									        right: '4%',
									        bottom: '3%',
									        containLabel: true
									    },
									    xAxis: {
									        type: 'value',
									        boundaryGap: [0, 0.01],
									        axisLabel: {
												formatter: '{value} 个'
											}
									    },
									    yAxis: {
									        type: 'category',
									        data: map.names
									    },
									    series: [
									        {
									            name: '兑换个数',
									            type: 'bar',
									            data: map.datas
									        }
									    ]
									});
						 
				}
				
			});
	}
	
</script>


           <script type="text/javascript">
           $(function() {
         	  $("ul li input[name='CheckAll']").each(function() {
					$(this).click(function () {
						var id=$(this).attr("id");
						CheckAll(id);
					});
				});
			});
           function CheckAll(name) {
         	  $("ul li input[name='"+name+"']").each(function() {
         		  var id = $(this).attr("id");//这是获取的节点内容  
         		  if($(this).is(":checked")){
         			  $("#tagContent li[ids='" + id + "']").remove();
         		  }
         	  });
					//判断当前点击的复选框处于什么状态$(this).is(":checked") 返回的是布尔类型
					if($("ul li input[id='"+name+"']").is(":checked")){
						$("input[name='"+name+"']").prop("checked",true);
					}else{
						$("input[name='"+name+"']").prop("checked",false);
					}
					$("ul li input[name='"+name+"']").each(function() {
						var value = $(this).parent().find("span").html();//这是获取的节点内容
               		var id = $(this).attr("id");//这是获取的节点内容   
               		if (!$(this).is(':checked')) {
               			$("#tagContent li[ids='" + id + "']").remove();
               		} else {
               			$("#tagContent").append("<li ids="+id+"><span>"+value+"<em></em></span></li>");
               		};
					});
			}
           
           $.ajax({
         		type:"POST",
         		url:"/sysmanager/Wechatmall/queryAllStation",
         		async:false,
         		dataType:"JSON",
         		success:function(result){
         			$.each(result,function(i,station){
         				var option="<li><input name='stationCre' value="+station+" type='checkbox' id='checkStation_"+i+"' class='default'><label for='checkStation_"+i+"'></label><span>"+station+"</span></li>";
         				$("#stationCredit").append(option);
         			});
         		}
         	});
           checkView("stationCre");
           </script>
<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>