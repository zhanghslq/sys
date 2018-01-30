<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
   <title>便利店对比同比环比</title>
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
<div class="contentRight" id="contentRightHeight">
<div class="timeEndIng" id="dataTime"></div>
     	<script type="text/javascript">
	     	$.ajax({
				type:"GET",
				url:"/sysmanager/time/queryOne",
				success:function(map){
					$("#dataTime").html("数据截止时间："+map);
				}
	     	});
     	</script>
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
                                <a href="javascript:void(0);" class="determine" onclick="queryshopCompare()">确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                              </div>
                           </div>
                       </div>
                       <!-- 这是跟选择油站平级的 -->
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
                           <div class="selemenu"><span>选择类别</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav" id="shopComparedepartmentName">
                                      <a href="javascript:void(0);" onclick="ChangedepartmentName('all')" class="titleCur">默认不区分类别</a>
                                  </div>
                              </div>
                           </div>
                       </div>
                       
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择时间</span></div>
                           <div class="seleContent selTime">
                              <div class="downCont selTimeMain" >
                                  <div class="selTimeInfo">
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span><input size="14" readonly="readonly" style="width:220px" value="2017-08-14 14:45" class="am-form-field" id='oldShopstart'></div>
                                        <div class="endTime"><span>选择结束时间</span><input size="14" readonly="readonly" style="width:220px" value="2017-09-14 14:45" class="am-form-field" id='oldShopend'></div>
                                        <div class="startTime"><span>对比开始时间</span><input size="14" readonly="readonly" style="width:220px" value="2017-09-14 14:45" class="am-form-field" id='newShopstart'></div>
                                        <div class="endTime"><span>对比结束时间</span><input size="14" readonly="readonly" style="width:220px" value="2017-10-14 14:45" class="am-form-field" id='newShopend'></div>
                                      </div>
                                      <script>
                                      $('#oldShopstart').attr("value",getLastFormatDateOne());
                                      $('#oldShopend').attr("value",getLastFormatDate());
                                      $('#newShopstart').attr("value",getNowFormatDateOne());
                                      $('#newShopend').attr("value",getNowFormatDate());
											$('#oldShopstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#oldShopend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#newShopstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#newShopend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryshopCompare()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                      </div>
                                  </div>
                              </div>
                           </div>
                       </div>
                   </div>
               </div>
               <!-- 结束 -->
           </div>
       </div>
    </div>

    <div id="shopCompareMoneys" style="width:80%;height:80%;min-width:800px;min-height:600px;"></div>
    <div id="shopCompareNumbers" style="width:80%;height:80%;min-width:800px;min-height:600px;"></div>
    <div id="shopCompareSingle" style="width:80%;height:80%;min-width:800px;min-height:600px;"></div>
    <script type="text/javascript">
    var basedepartmentName="";
    function ChangedepartmentName(notOIlName) {
    	basedepartmentName=notOIlName;
    	queryshopCompare();
	 }
    
    var basePeople="all";
	   	function ChangePeople(src) {
				basePeople=src;
				queryshopCompare();
		}
    $(function () {//页面加载完成之后
    	$.ajax({
			type:"GET",
			url:"/sysmanager/notOil/queryAllName",
			async:false,
			dataType:"JSON",
			success:function(result){
				$.each(result,function(i,oil){
					/* <a href="javascript:void(0);" onclick="ChangedepartmentName('all')" class="titleCur">默认不区分类别</a> */
					var option =$("<a></a>").text(oil).on('click',function(){
						ChangedepartmentName(oil);
						});
					$("#shopComparedepartmentName").append(option);
				});
				basedepartmentName="all";
			}
		});
    
    queryshopCompare();
	}); 
        // 基于准备好的dom，初始化echarts实例
        var myChartshopCompareMoneys = echarts.init(document.getElementById('shopCompareMoneys'));
        var myChartshopCompareNumbers = echarts.init(document.getElementById('shopCompareNumbers'));
        var myChartshopCompareSingle = echarts.init(document.getElementById('shopCompareSingle'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryshopCompare() {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/compare/queryShop",
				dataType:"JSON",
				data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
					"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),"type":jqchk("type"),
					"station":jqchk("station"),
					"departmentName":basedepartmentName,
					"people":basePeople,
					
					"newstart":$("#newShopstart").val(),
					"newend":$("#newShopend").val(),
					"oldstart":$("#oldShopstart").val(),
					"oldend":$("#oldShopend").val(),
				},
				success:function(map){
        		var colors=['#FBCE07','#DD1D21','#89CFDC'];
        		myChartshopCompareMoneys.setOption({
					color: colors,
					 title: {
					        text: '销售额对比',
					        x:'center'
					    },
					tooltip: {
						trigger: 'axis',
						axisPointer: {
							type: 'cross'
						},
						formatter: '前总销售额: {c0} 元<br />后总销售额: {c1} 元<br />销售额增长率: {c2}%'
					},
		            	
					grid: {
						
						top:'10%',
						right:'10%',
						left:'15%',
					},
					toolbox: {
						right:18,
						feature: {
							dataView: {show: true, readOnly: false},
							restore: {show: true},
							saveAsImage: {show: true}
						}
					},
					legend: {
						top:30,
						data:['前总销售额','后总销售额','增长率']
					},
					xAxis: [
						{
							type: 'category',
							axisTick: {
								alignWithLabel: true
							},
							data: ['总销售额']
						}
					],
					yAxis: [
						{
							type: 'value',
							name: '总销售额',
							position: 'left',
							axisLine: {
								lineStyle: {
									color: 'black'
								}
							},
							axisLabel: {
								formatter: '{value} 元'
							}
						},
						{
							type: 'value',
							name: '增长率',
							position: 'right',
							axisLine: {
								lineStyle: {
									color: colors[1]
								}
							},
							axisLabel: {
								formatter: '{value} %'
							}
						}
					],
					series: [
						{
							name:'前总销售额',
							type:'bar',
							data:map.beforemoneys
						},
						{
							name:'后总销售额',
							type:'bar',
							data:map.aftermoneys
						},
						{
							name:'增长率',
							type:'line',
							yAxisIndex: 1,
							data:map.moneys
						}
					]
				});//Echarts绘制完成
				myChartshopCompareNumbers.setOption({
					color: colors,
					 title: {
					        text: '销售笔数对比',
					        x:'center'
					    },
					tooltip: {
						trigger: 'axis',
						axisPointer: {
							type: 'cross'
						},
						formatter: '前销售笔数: {c0}笔<br />后销售笔数: {c1}笔<br />销售笔数增长率: {c2}%'
					},
					grid: {
						top:'10%',
						right:'10%',
						left:'15%',
					},
					toolbox: {
						right:18,
						feature: {
							dataView: {show: true, readOnly: false},
							restore: {show: true},
							saveAsImage: {show: true}
						}
					},
					legend: {
						top:30,
						data:['前销售笔数','后销售笔数','增长率']
					},
					xAxis: [
						{
							type: 'category',
							axisTick: {
								alignWithLabel: true
							},
							data: ['销售笔数']
						}
					],
					yAxis: [
						{
							type: 'value',
							name: '销售笔数',
							position: 'left',
							axisLine: {
								lineStyle: {
									color: 'black'
								}
							},
							axisLabel: {
								formatter: '{value} 笔'
							}
						},
						{
							type: 'value',
							name: '增长率',
							position: 'right',
							axisLine: {
								lineStyle: {
									color: colors[1]
								}
							},
							axisLabel: {
								formatter: '{value} %'
							}
						}
					],
					series: [
						{
							name:'前销售笔数',
							type:'bar',
							data:map.beforenumbers
						},
						{
							name:'后销售笔数',
							type:'bar',
							data:map.afternumbers
						},
						{
							name:'增长率',
							type:'line',
							yAxisIndex: 1,
							data:map.numbers
						}
					]
				});//Echarts绘制完成
				myChartshopCompareSingle.setOption({
					color: colors,
					 title: {
					        text: '单笔销售额对比',
					        x:'center',
					    },
					tooltip: {
						trigger: 'axis',
						axisPointer: {
							type: 'cross'
						},
						formatter: '前单笔销售额: {c0}元<br />后单笔销售额: {c1}元<br />单笔销售额增长率: {c2}%'
					},
					grid: {
						top:'10%',
						right:'10%',
						left:'15%',
					},
					toolbox: {
						right:18,
						feature: {
							dataView: {show: true, readOnly: false},
							restore: {show: true},
							saveAsImage: {show: true}
						}
					},
					legend: {
						top:30,
						data:['前单笔销售额','后单笔销售额','增长率']
					},
					xAxis: [
						{
							type: 'category',
							axisTick: {
								alignWithLabel: true
							},
							data: ['单笔销售额']
						}
					],
					yAxis: [
						{
							type: 'value',
							name: '单笔销售额',
							position: 'left',
							axisLine: {
								lineStyle: {
									color: 'black'
								}
							},
							axisLabel: {
								formatter: '{value} 元'
							}
						},
						{
							type: 'value',
							name: '增长率',
							position: 'right',
							axisLine: {
								lineStyle: {
									color: colors[1]
								}
							},
							axisLabel: {
								formatter: '{value} %'
							}
						}
					],
					series: [
						{
							name:'前单笔销售额',
							type:'bar',
							data:map.beforeavgMoneys
						},
						{
							name:'后单笔销售额',
							type:'bar',
							data:map.afteravgMoneys
						},
						{
							name:'增长率',
							type:'line',
							yAxisIndex: 1,
							data:map.avgMoneys
						}
					]
				});//Echarts绘制完成
				}//success 
        	});//ajax
       }
    </script>
    <script type="text/javascript">
    	
    </script>
	<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>