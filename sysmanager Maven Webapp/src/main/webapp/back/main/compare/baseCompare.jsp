<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>油站营业额</title>
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
<div class="contentRight" id="contentRightHeight">
       <div class="rightDownSel" id="test">
           <!-- <ul class="tabNav">
               <li class="on">整体销售</li>
               <li>燃油销售</li>
               <li>非油销售</li>
               <li>润滑油销售</li>
           </ul> -->
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
                                      <ul id="station">
                                          
                                      </ul>
                                  </div>
                              </div>
                              <div class="screenMain" >
		                                  <ul id="tagContent">
		                                      
		                                  </ul>
		                       </div>
                              <div class="downOperation">
                                <a href="javascript:void(0);" class="determine">确定</a>
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
                           <div class="selemenu"><span>选择油品</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav" id="oilCompareOilName">
                                      <a href="javascript:void(0);" onclick="ChangeOilName('all')" class="titleCur">默认不区分油品</a>
                                      
                                  </div>
                              </div>
                           </div>
                       </div>
                       
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择时间</span></div>
                           <div class="seleContent selTime">
                              <div class="downCont selTimeMain" >
                                  <div class="selTimeInfo">
                                    <!--  <div class="minimum">
                                        <em>最小时间单位</em>
                                        <div class="minimumRadio">
                                          <label><input name="date" type="radio" value="year" /> <i>年</i> </label>
                                          <label><input name="date" type="radio" value="month" /> <i>月</i> </label>
                                          <label><input name="date" type="radio" value="day" checked="checked"/> <i>日</i> </label>
                                          <label><input name="date" type="radio" value="hour" /> <i>小时</i> </label>
                                          <label><input name="date" type="radio" value="minute" /> <i>分钟</i> </label>
                                        </div>
                                      </div> -->
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span><input size="14" readonly="readonly" style="width:220px" value="2017-08-14 14:45" class="am-form-field" id='zoushistart'></div>
                                        <div class="endTime"><span>选择结束时间</span><input size="14" readonly="readonly" style="width:220px" value="2017-09-14 14:45" class="am-form-field" id='zoushiend'></div>
                                        <div class="startTime"><span>对比开始时间</span><input size="14" readonly="readonly" style="width:220px" value="2017-09-14 14:45" class="am-form-field" id='newzoushistart'></div>
                                        <div class="endTime"><span>对比结束时间</span><input size="14" readonly="readonly" style="width:220px" value="2017-10-14 14:45" class="am-form-field" id='newzoushiend'></div>
                                      </div>
                                      <script>
											$('#zoushistart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#zoushiend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#newzoushistart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#newzoushiend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
									  </script>
									  
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="querybaseOil()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                       <!--  <br><br>
                                        <a href="javascript:void(0);" class="determine" onclick="ExportExcel()">导出到Excel</a> -->
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
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="amount" style="width:80%;height:60%;min-height: 600px;min-width: 800px"></div>
    <div id="number" style="width:80%;height:60%;min-height: 600px;min-width: 800px"></div>
    <div id="single" style="width:80%;height:60%;min-height: 600px;min-width: 800px"></div>
    
    <script type="text/javascript">
    function ExportExcel() {
    	$("#exportExcel").attr("action","/sysmanager/compare/exportOils?people="+basePeople);
 	   	$("#exportExcel").submit();
    }
    // 基于准备好的dom，初始化echarts实例
    var baseOil="all";
        var amount = echarts.init(document.getElementById('amount'));
        var number = echarts.init(document.getElementById('number'));
        var single = echarts.init(document.getElementById('single'));
      //格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
         // 指定图表的配置项和数据
    	$.ajax({
			type:"GET",
			url:"/sysmanager/oil/queryAllName",
			dataType:"JSON",
			async: false,
			success:function(result){
				$.each(result,function(i,oil){
					/* <a href="javascript:void(0);" onclick="ChangeOilName('all')" class="titleCur">默认不区分油品</a> */
					var option = $("<a></a>").text(oil).val(oil).on('click',function(){
						ChangeOilName(oil);
					});
					$("#oilCompareOilName").append(option);
				});
				baseOil="all";
			}
		});
      
     
     function ChangeOilName(src) {
		baseOil=src;
	 }
     
     var basePeople="all";
	   	function ChangePeople(src) {
				basePeople=src;
		}
	function querybaseOil(){
		$.ajax({
			type:"post",
			url:"/sysmanager/compare/queryOil",
			dataType:"JSON",
			data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
				"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),
				"station":jqchk("station"),
				"oilName":baseOil,
				"people":basePeople,
				
				"oldstart":$("#zoushistart").val(),
				"oldend":$("#zoushiend").val(),
				"newstart":$("#newzoushistart").val(),
				"newend":$("#newzoushiend").val(),
			},
			success:function(map){
				var colors=['#FBCE07','#DD1D21','#89CFDC'];
				amount.setOption({
					color: colors,
					tooltip: {
						trigger: 'axis',
						axisPointer: {
							type: 'cross'
						},
						formatter: '前总销量: {c0}千升<br />后总销量: {c1}千升<br />销量增长率: {c2}%'
					},
		            	
					grid: {
						right: '20%'
					},
					toolbox: {
						feature: {
							dataView: {show: true, readOnly: false},
							restore: {show: true},
							saveAsImage: {show: true}
						}
					},
					legend: {
						data:['前总销量','后总销量','增长率']
					},
					xAxis: [
						{
							type: 'category',
							axisTick: {
								alignWithLabel: true
							},
							data: ['总销量']
						}
					],
					yAxis: [
						{
							type: 'value',
							name: '总销量',
							position: 'left',
							axisLine: {
								lineStyle: {
									color: 'black'
								}
							},
							axisLabel: {
								formatter: '{value} 千升'
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
							name:'前总销量',
							type:'bar',
							data:map.beforelitre
						},
						{
							name:'后总销量',
							type:'bar',
							data:map.afterlitre
						},
						{
							name:'增长率',
							type:'bar',
							yAxisIndex: 1,
							data:map.litre
						}
					]
				});//Echarts绘制完成
				number.setOption({
					color: colors,
					tooltip: {
						trigger: 'axis',
						axisPointer: {
							type: 'cross'
						},
						formatter: '前销售笔数: {c0}笔<br />后销售笔数: {c1}笔<br />销售笔数增长率: {c2}%'
					},
					grid: {
						right: '20%'
					},
					toolbox: {
						feature: {
							dataView: {show: true, readOnly: false},
							restore: {show: true},
							saveAsImage: {show: true}
						}
					},
					legend: {
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
							data:map.beforenumber
						},
						{
							name:'后销售笔数',
							type:'bar',
							data:map.afternumber
						},
						{
							name:'增长率',
							type:'bar',
							yAxisIndex: 1,
							data:map.number
						}
					]
				});//Echarts绘制完成
				single.setOption({
					color: colors,
					tooltip: {
						trigger: 'axis',
						axisPointer: {
							type: 'cross'
						},
						formatter: '前单笔销售量: {c0}升<br />后单笔销售量: {c1}升<br />单笔销售量增长率: {c2}%'
					},
					grid: {
						right: '20%'
					},
					toolbox: {
						feature: {
							dataView: {show: true, readOnly: false},
							restore: {show: true},
							saveAsImage: {show: true}
						}
					},
					legend: {
						data:['前单笔销售量','后单笔销售量','增长率']
					},
					xAxis: [
						{
							type: 'category',
							axisTick: {
								alignWithLabel: true
							},
							data: ['单笔销售量']
						}
					],
					yAxis: [
						{
							type: 'value',
							name: '单笔销售量',
							position: 'left',
							axisLine: {
								lineStyle: {
									color: 'black'
								}
							},
							axisLabel: {
								formatter: '{value} 升'
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
							name:'前单笔销售量',
							type:'bar',
							data:map.beforeavgLitre
						},
						{
							name:'后单笔销售量',
							type:'bar',
							data:map.afteravgLitre
						},
						{
							name:'增长率',
							type:'bar',
							yAxisIndex: 1,
							data:map.avgLitre
						}
					]
				});//Echarts绘制完成
				
			}
		});
	}
    </script>
    
    <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
	<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>