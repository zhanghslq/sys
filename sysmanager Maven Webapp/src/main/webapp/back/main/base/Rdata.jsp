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
                                <a href="javascript:void(0);" onclick="queryByOils()" class="determine">确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                                 <a href="javascript:void(0);"  class="determine" onclick="ExportExcel()">导出到Excel</a>
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
                                  		<a href="javascript:void(0);" onclick="ChangeDep('product')">商品名</a>
                                  </div>
                              </div>
                           </div>
                       </div>
                        <div class="selemeTitle">
                           <div > <div style="height: 15px"></div><span>
                           <input id="confidence" name="confidence" style="margin-top: 100px" type="text" class="easyui-textbox" data-options="prompt:'请输入置信度（0~1）'"></span></div>
                       </div>
                        <div class="selemeTitle">
                           <div > <div style="height: 15px"></div><span>
                           <input id="support" name="support" style="margin-top: 100px" type="text" class="easyui-textbox" data-options="prompt:'请输入支持度（0~1）'"></span></div>
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
<script type="text/javascript">
	var baseOil="all";
	function ChangeOil(src){
		baseOil=src;
	}
	var baseDep="department";
	function ChangeDep(src){
		baseDep=src;
	}
	function query() {
		$.ajax({
			type:"post",
			url:"/sysmanager/shopCart/query",
			dataType:"JSON",
			data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
				"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),
				"type":jqchk("type"),
				"station":jqchk("station"),
				"start":$("#oilzoushistart").val(),
				"end":$("#oilzoushiend").val(),
				"oil":baseOil,
				"support":$("#support").val(),
				"confidence":$("#confidence").val(),
				"department":baseDep
			},
			success:function(map){
				one.setOption({
				    title: {
				        text: '便利店类别Top榜(消费人数)',
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
				        data: ['人数']
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
							formatter: '{value} 人'
						}
				    },
				    yAxis: {
				        type: 'category',
				        data: map.names
				    },
				    series: [
				        {
				            name: '人数',
				            type: 'bar',
				            data: map.data
				        }
				    ]
				});//one
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
							name:'消费一件商品的',
							textStyle:{
    							color:'#008433',
							}
						},{
							name: '消费多件商品的',
							textStyle:{
								color:'#DD1D21'
							}
						}]
					},
					series : [
						{
							name: '购买件数占比',
							type: 'pie',
							radius : '55%',
							center: ['50%', '60%'],
							data:map.datasNumber,
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
				rule.setOption({
				    backgroundColor: '#404a59',
				    color: [
				        '#dd4444', '#fec42c', '#80F1BE'
				    ],
				    title : {
						text: '商品关联规则视图',
						x:'center',
						textStyle: {
				            color: '#fff',
				        }
					},
				    legend: {
				    	top:30,
				        y: 'top',
				        data: ['数据'],
				        textStyle: {
				            color: '#fff',
				            fontSize: 16
				        }
				    },
				    grid: {
				        x: '10%',
				        x2: 150,
				        y: '18%',
				        y2: '10%'
				    },
				    tooltip: {
				        padding: 10,
				        backgroundColor: '#222',
				        borderColor: '#777',
				        borderWidth: 1,
				        formatter: function (obj) {
				            var value = obj.value;
				            return '<div style="border-bottom: 1px solid rgba(255,255,255,.3); font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">'
				                + '</div>'
				                + schema[0].text + '：' + value[0] + '<br>'
				                + schema[1].text + '：' + value[1] + '<br>'
				                + schema[2].text + '：' + value[2] + '<br>'
				        }
				    },
				    xAxis: {
				        type: 'value',
				        name: '支持度',
				        nameGap: 16,
				        nameTextStyle: {
				            color: '#fff',
				            fontSize: 14
				        },
				        max: 1,
				        splitLine: {
				            show: false
				        },
				        axisLine: {
				            lineStyle: {
				                color: '#eee'
				            }
				        }
				    },
				    yAxis: {
				        type: 'value',
				        name: '置信度',
				        nameLocation: 'end',
				        nameGap: 20,
				        nameTextStyle: {
				            color: '#fff',
				            fontSize: 16
				        },
				        axisLine: {
				            lineStyle: {
				                color: '#eee'
				            }
				        },
				        splitLine: {
				            show: false
				        }
				    },
				    visualMap: [
				        {
				            left: 'right',
				            bottom: '5%',
				            dimension: 2,
				            min: 0,
				            max: 2,
				            itemHeight: 120,
				            calculable: true,
				            precision: 0.1,
				            text: ['明暗：提升度'],
				            textGap: 30,
				            textStyle: {
				                color: '#fff'
				            },
				            inRange: {
				                colorLightness: [1, 0.5]
				            },
				            outOfRange: {
				                color: ['rgba(255,255,255,.2)']
				            },
				            controller: {
				                inRange: {
				                    color: ['#c23531']
				                },
				                outOfRange: {
				                    color: ['#444']
				                }
				            }
				        }
				    ],
				    series: [
				        {
				            name: '数据',
				            type: 'scatter',
				            itemStyle: {
				            	normal: {
					                  opacity: 0.8,
					                  shadowBlur: 10,
					                  shadowOffsetX: 0,
					                  shadowOffsetY: 0,
					                  shadowColor: 'rgba(0, 0, 0, 0.5)'
					              }
				            },
				            data: map.rule
				        }
				    ]
				}		
		);//three
				}
			});
	}
	
</script>
<div style="width: 80%;height: 60%;min-height: 600px;min-width: 800px" id="one"></div>
<div style="width: 80%;height: 60%;min-height: 600px;min-width: 800px" id="number"></div>
<div style="width: 80%;height: 60%;min-height: 600px;min-width: 800px" id="rule"></div>
<script type="text/javascript">
var one = echarts.init(document.getElementById('one'));
var rule = echarts.init(document.getElementById('rule'));
var number = echarts.init(document.getElementById('number'));
var schema = [{name: 'support', index: 0, text: '支持度'},
              {name: 'confidence', index: 1, text: '置信度'},
              {name: 'lift', index: 2, text: '提升度'},];
	$(function() {
		query();
	});
	/* function queryOne(){
		$.ajax({
			type:"post",
			url:"/sysmanager/basket/queryOne",
			dataType:"JSON",
			success:function(map){
				
			}
		});
	}
	function queryNumber() {
		$.ajax({
			type:"post",
			url:"/sysmanager/basket/queryNumber",
			dataType:"JSON",
			success:function(map){
				
			}
		});
	}
	
	function queryRule() {
		$.ajax({
			type:"post",
			url:"/sysmanager/basket/queryRule",
			dataType:"JSON",
			success:function(map){
				
				
			}
		});
	} */
</script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>