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
    <script src="/sysmanager/back/echar/gl.js"></script>
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
       <div class="rightDownSel">
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
<div style="width: 80%;height: 60%;min-height: 600px;min-width: 800px" id="four"></div>
<script type="text/javascript">
	var baseArea="BJSHELL";
	function ChangeArea(src) {
		baseArea=src;
		query();
	}
	var one = echarts.init(document.getElementById('one'));
	var two = echarts.init(document.getElementById('two'));
	var three = echarts.init(document.getElementById('three'));
	var four = echarts.init(document.getElementById('four'));
	$(function() {
		query();
	});
    var schema = [
        {name: '加权平均', index: 0, text: '加权平均'},
        {name: '加权方差', index: 1, text: '加权方差'},
    ];
    var itemStyle = {
        normal: {
            opacity: 0.8,
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowOffsetY: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
    };
	function query() {
		$.ajax({
			type:"post",
			url:"/sysmanager/warn/queryByV14",
			dataType:"JSON",
			data:{"area":baseArea},
			success:function(map){
					one.setOption({
                        title : {
                            text: '会员消费周期分类',
                            subtext: 't:最近一次消费距今时间（天）；M：历史消费间隔的加权平均数（天）',
                            x:'center'
                        },
					    tooltip: {
					        trigger: 'item',
					        formatter: "{a} <br/>{b}: {c} ({d}%)"
					    },
					    legend: {
                            top:'50',
					        x: 'center',
					        data:['t<M','M<t<2M','2M<t<3M','t>3M','只消费过2次','只消费过1次','只消费过3次']
					    },
					    series: [
					        {
					            name:'某会员两次消费之间的间隔时间（加权平均，单位：天）',
					            type:'pie',
					            selectedMode: 'single',
					            radius: [0, '50%'],
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
					            data:map.datas
					        }
					    ]
					});//Echarts
					three.setOption({
                        title : {
                          text : '会员消费时间间隔的加权方差',
                            x:"center"
                        },
							     tooltip : {
							        trigger: 'axis'
							    },
							    xAxis: {
							         name:'分类',
							        type: 'category',
							        data: map.names
							    },
							    yAxis: {
							         name:'人数',
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
                    legend: {
                        y: 'top',
                        data: ['t<M', 'M<t<2M', '2M<t<3M','t>3M','只消费过2次','只消费过1次','只消费过3次'],
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
                            return '<div style=" font-size: 18px;padding-bottom: 7px;margin-bottom: 7px">'
                                + '</div>'
                                + schema[0].text + '：' + value[0] + '<br>'
                                + schema[1].text + '：' + value[1] + '<br>'

                        }
                    },
                    xAxis: {
                        type: 'value',
                        name: '加权平均',
                        nameTextStyle: {
                            color: '#fff',
                            fontSize: 14
                        },
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
                        name: '加权方差',
                        nameLocation: 'end',
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
                    series: [
                        {
                            name: 't<M',
                            type: 'scatter',
                            itemStyle: itemStyle,
                            data: map.pack0
                        },{
                            name: 'M<t<2M',
                            type: 'scatter',
                            itemStyle: itemStyle,
                            data: map.pack1
                        },{
                            name: '2M<t<3M',
                            type: 'scatter',
                            itemStyle: itemStyle,
                            data: map.pack2
                        },{
                            name: 't<3M',
                            type: 'scatter',
                            itemStyle: itemStyle,
                            data: map.pack3
                        },{
                            name: '只消费过2次',
                            type: 'scatter',
                            itemStyle: itemStyle,
                            data: map.pack4
                        },{
                            name: '只消费过1次',
                            type: 'scatter',
                            itemStyle: itemStyle,
                            data: map.pack5
                        },{
                            name: '只消费过3次',
                            type: 'scatter',
                            itemStyle: itemStyle,
                            data: map.pack6
                        }
                    ]
                });//Echarts

				}
			});
		$.ajax({
			type:"post",
			url:"/sysmanager/warn/queryShow",
			dataType:"JSON",
			data:{"area":baseArea},
			success:function(map){
            two.setOption({
                title : {
                    text: '会员消费时间间隔（加权平均）',
                    subtext: '权重展示',
                    x:'center'
                },
                    tooltip : {
                        trigger: 'axis'
                    },
                    xAxis: {
                         name:'间隔',
                        type: 'category',
                        data: map.names
                    },
                    yAxis: {
                        name:'权重',
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} '
                        }
                    },
                    series: [{
                        data: map.values,
                        type: 'bar'
                    }]
                });//Echarts
            }
        });
    }
</script>

<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>