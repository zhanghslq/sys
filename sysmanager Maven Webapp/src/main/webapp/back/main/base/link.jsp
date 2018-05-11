<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>R获取的数据</title>
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
<div class="contentRight" id="contentRightHeight">
<div class="timeEndIng" id="dataTime"></div>
     	<script type="text/javascript">
	     	$.ajax({
				type:"GET",
				url:"/sysmanager/time/queryTwo",
				success:function(map){
					$("#dataTime").html("数据截止时间："+map);
				}
	     	});
     	</script>
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
                                <a href="javascript:void(0);" onclick="query()" class="determine">确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                              </div>
                           </div>
                       </div>
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择油品</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                  		<a href="javascript:void(0);" onclick="ChangeOil('all')" class="titleCur">默认全部</a>
                                  		<a href="javascript:void(0);" onclick="ChangeOil('92#')" >92#</a>
                                  		<a href="javascript:void(0);" onclick="ChangeOil('95#')" >95#</a>
                                  		<a href="javascript:void(0);" onclick="ChangeOil('0#')" >0#</a>
                                  		<a href="javascript:void(0);" onclick="ChangeOil('-10#')" >-10#</a>
                                  		<a href="javascript:void(0);" onclick="ChangeOil('-20#')" >-20#</a>
                                  </div>
                              </div>
                           </div>
                       </div>
                       <div class="selemeTitle">
                           <div class="selemenu"><span>关联项</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav" id="department">
                                  		<a href="javascript:void(0);" onclick="ChangeDep('department')" class="titleCur">默认类别</a>
                                  		<a href="javascript:void(0);" onclick="ChangeDep('product')">商品</a>
                                  </div>
                              </div>
                           </div>
                       </div>
                        <div class="selemeTitle">
                           <div > <div style="height: 15px"></div><span>
                           <input id="productCode" name="productCode" style="margin-top: 100px" type="text" class="easyui-textbox" data-options="prompt:'请输入关联项编号'"></span></div>
                       </div>
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择时间</span></div>
                           <div class="seleContent selTime">
                              <div class="downCont selTimeMain">
                                  <div class="selTimeInfo">
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" name="start" style="width:300px"  class="am-form-field" id='oilzoushistart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end" style="width:300px"  class="am-form-field" id='oilzoushiend'></div>
                                      </div>
                                      <script>
											$('#oilzoushistart').attr("value",getNowFormatDateOne());
											$('#oilzoushistart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#oilzoushiend').attr("value",getNowFormatDate());
											$('#oilzoushiend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="query()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
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
<div style="width: 80%;height: 60%;min-height: 600px;min-width: 800px" id="number"></div>
<div style="width: 80%;height: 60%;min-height: 600px;min-width: 800px" id="link"></div>
<script type="text/javascript">
	var baseOil="all";
	function ChangeOil(src){
		baseOil=src;
	}
	var baseDep="department";
	function ChangeDep(src){
		baseDep=src;
	}
	var number = echarts.init(document.getElementById('number'));
	var link = echarts.init(document.getElementById('link'));
	function query() {
		$.ajax({
			type:"post",
			url:"/sysmanager/shopCart/queryLink",
			dataType:"JSON",
			data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
				"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),
				"type":jqchk("type"),
				"station":jqchk("station"),
				"start":$("#oilzoushistart").val(),
				"end":$("#oilzoushiend").val(),
				"oil":baseOil,
				"productCode":$("#productCode").val(),
				"department":baseDep
			},
			success:function(map){
				number.setOption({
					title : {
						text: '购买件数占比',
						x:'center'
					},
					tooltip : {
						trigger: 'item',
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					color:['#008433','#DD1D21','#7F7F7F','#595959','#A6A6A6',],
					legend: {
						top:30,
						data: [{
							name:'本商品销售量',
							textStyle:{
    							color:'#008433',
							}
						},{
							name: '其余商品销售量',
							textStyle:{
								color:'#DD1D21'
							}
						}]
					},
					series : [
						{
							name: '销售量占比',
							type: 'pie',
							radius : '55%',
							center: ['50%', '60%'],
							data:map.linkNumber,
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
				
				link.setOption({
				    tooltip: {
				        trigger: 'axis',
				        axisPointer: {
				            type: 'cross',
				            crossStyle: {
				                color: '#999'
				            }
				        }
				    },
				    toolbox: {
				        feature: {
				            dataView: {show: true, readOnly: false},
				            magicType: {show: true, type: ['line', 'bar']},
				            restore: {show: true},
				            saveAsImage: {show: true}
				        }
				    },
				    legend: {
				        data:['同时购买可能性','同时购买占比','购买单数']
				    },
				    xAxis: [
				        {
				            type: 'category',
				            data: map.names,
				            axisPointer: {
				                type: 'shadow'
				            }
				        }
				    ],
				    yAxis: [
				        {
				            type: 'value',
				            name: '百分比',
				            min: 0,
				            max: 100,
				            axisLabel: {
				                formatter: '{value} %'
				            }
				        },
				        {
				            type: 'value',
				            name: '单数',
				            axisLabel: {
				                formatter: '{value} 单'
				            }
				        }
				    ],
				    series: [
				        {
				            name:'同时购买可能性',
				            type:'bar',
				            data:map.v4
				        },
				        {
				            name:'同时购买占比',
				            type:'bar',
				            data:map.support
				        },
				        {
				            name:'购买单数',
				            type:'line',
				            yAxisIndex: 1,
				            data:map.count
				        }
				    ]
				});//Echarts
				
				}
			});
	}
	
</script>

<script type="text/javascript">

	$(function() {
		query();
	});
</script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>