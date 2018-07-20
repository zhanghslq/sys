<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>某会员消费周期习惯人群分析</title>
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
                       <div class="selemeTitle">
                           <div > <div style="height: 15px"></div><span>
                           <input id="vipid" name="productCode" style="margin-top: 100px" type="text" class="easyui-textbox" data-options="prompt:'请输入会员id'"></span></div>
                           <div class="seleContent crowd">
                               <div class="downCont">
                                   <div class="downNav crowdNav">
                                   </div>
                               </div>
                           </div>
                       </div>
                       <div class="selemeTitle">
                           <div > <div style="height: 15px"></div>
                               <a class="export" onclick="query()">查询</a>

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
<script type="text/javascript">
	var baseArea="BJSHELL";
	function ChangeArea(src) {
		baseArea=src;
		query();
	}
	var one = echarts.init(document.getElementById('one'));
	var two = echarts.init(document.getElementById('two'));
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
			url:"/sysmanager/warn/querySomeVip",
			dataType:"JSON",
			data:{"area":baseArea,"id":$("#vipid").val()},
			success:function(map){
            one.setOption({
                    tooltip : {
                        trigger: 'axis'
                    },
                    xAxis: {
                         name:'间隔',
                        type: 'category',
                        data: map.names
                    },
                    yAxis: {
                        name:'次数',
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} '
                        }
                    },
                    series: [{
                        data: map.values,
                        type: 'line'
                    }]
                });//Echarts
                //开始散点图
                two.setOption({
                    backgroundColor: '#404a59',
                    color: [
                        '#dd4444', '#fec42c', '#80F1BE'
                    ],
                    legend: {
                        y: 'top',
                        data: ['相似会员', '自己'],
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
                            name: '相似会员',
                            type: 'scatter',
                            itemStyle: itemStyle,
                            data: map.douScatter
                        },{
                            name: '自己',
                            type: 'scatter',
                            itemStyle: itemStyle,
                            data: map.self
                        }
                    ]
                });//Echarts
            }
        });


    }
</script>

<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>