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
                           <div class="selemenu"><span>选择地区</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav" id="department">
                                  		<a href="javascript:void(0);" onclick="ChangeArea('BJSHELL')" class="titleCur">北京</a>
                                  		<a href="javascript:void(0);" onclick="ChangeArea('CDSHELL')">承德</a>
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
<div style="width: 80%;height: 60%;min-height: 600px;min-width: 800px" id="one"></div>
<div style="width: 80%;height: 60%;min-height: 600px;min-width: 800px" id="two"></div>
<div style="width: 80%;height: 60%;min-height: 600px;min-width: 800px" id="three"></div>
<script type="text/javascript">
	var baseArea="BJSHELL";
	function ChangeArea(src) {
		baseArea=src;
		query();
	}
	var one = echarts.init(document.getElementById('one'));
	var two = echarts.init(document.getElementById('two'));
	var three = echarts.init(document.getElementById('three'));
	$(function() {
		query();
	});
	function query() {
		$.ajax({
			type:"post",
			url:"/sysmanager/rfm/query",
			dataType:"JSON",
			data:{"area":baseArea},
			success:function(map){
					one.setOption({
					    tooltip: {
					        trigger: 'item',
					        formatter: "{a} <br/>{b}: {c} ({d}%)"
					    },
					    legend: {
					        x: 'center',
					        data:['90天内有消费','90天内无消费','重要客户',
					              '一般客户','多次消费','一次消费','重要价值客户','重要保持客户',
					              '重要发展客户','重要挽留客户','一般价值客户','一般发展客户',
					              '一般保持客户','一般挽留客户','沉睡客户','一次客户','重点唤醒客户','一般唤醒客户',
					              '历史上唯一一次消费发生在30天内','历史上唯一一次消费发生在31-90天']
					    },
					    series: [
					        {
					            name:'RFM',
					            type:'pie',
					            selectedMode: 'single',
					            radius: [0, '30%'],

					            label: {
					                normal: {
					                    position: 'inner'
					                }
					            },
					            labelLine: {
					                normal: {
					                    show: false
					                }
					            },
					            data:map.one
					        },
					        {
					            name:'RFM',
					            type:'pie',
					             label: {
					                normal: {
					                    position: 'inner'
					                }
					            },
					             labelLine: {
					                normal: {
					                    show: false
					                }
					            },
					            radius: ['40%', '55%'],
					            data:map.two
					        },{
					            name:'RFM',
					            type:'pie',
					             label: {
					                normal: {
					                    position: 'inner',
					                    show:false
					                }
					            },
					             labelLine: {
					                normal: {
					                    show: false
					                }
					            },
					            radius: ['60%', '70%'],
					            data:map.three
					        }
					    ]
					});//Echarts
					two.setOption({
					    tooltip: {
					        trigger: 'item',
					        formatter: "{a} <br/>{b}: {c} ({d}%)"
					    },
					    legend: {
					        x: 'center',
					        data:['多次消费','一次消费','历史上唯一一次消费发生在90天之前',
					              '历史上唯一一次消费发生在30天内','历史上唯一一次消费发生在31-90天']
					    },
					    series: [
					        {
					            name:'RFM',
					            type:'pie',
					            selectedMode: 'single',
					            radius: [0, '30%'],

					            label: {
					                normal: {
					                    position: 'inner'
					                }
					            },
					            labelLine: {
					                normal: {
					                    show: false
					                }
					            },
					            data:map.four
					        },
					        {
					            name:'RFM',
					            type:'pie',
					             label: {
					                normal: {
					                    position: 'inner',
					                    show:false
					                }
					            },
					             labelLine: {
					                normal: {
					                    show: false
					                }
					            },
					            radius: ['40%', '55%'],
					            data:map.five
					        }
					    ]
					});//Echarts
					three.setOption({
							     tooltip : {
							        trigger: 'axis'
							    },
							    xAxis: {
							        type: 'category',
							        data: map.names
							    },
							    yAxis: {
							        type: 'value',
							        axisLabel: {
						                formatter: '{value} 人'
						            }
							    },
							    series: [{
							        data: map.values,
							        type: 'bar'
							    }]
							});//Echarts
							four.setOption({
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
					);//four
					
				}
			});
	}
	
</script>

<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>