<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>会员漏斗图</title>
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/IconExtension.css">
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
    <script src="/sysmanager/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="/sysmanager/back/easyui/js/form.validator.rules.js"></script>
    <script src="/sysmanager/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="/sysmanager/back/echar/echarts.js"></script>
</head>
<body>
    <form action="">

  请选择时间：<select id="funnelMonth" onchange="queryVipFunnel()">
		  	
  		</select>
  		
  		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryVipFunnel()">查询</a>
</form>
    <div id="vipFunnel" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    $.ajax({
		type:"GET",
		url:"/sysmanager/vipFunnel/queryAllMonth",
		dataType:"JSON",
		success:function(result){
			$.each(result,function(i,station){
				var option = $("<option></option>").text(station).val(station);
				$("#funnelMonth").append(option);
			});
		}
	});
   $(function () {
  		queryVipFunnel();
	});
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('vipFunnel'));
        
        
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryVipFunnel() {
        	$.ajax({
				type:"post",
				url:"/sysmanager/vipFunnel/queryVipFunnel",
				dataType:"JSON",
				data:{"month":$("#funnelMonth").val()},
				success:function(map){
        			myChart.setOption({
        			    title: {
        			        text: '漏斗图',
        			        subtext: '会员人数'
        			    },
        			    tooltip: {
        			        trigger: 'item',
        			    },
        			    toolbox: {
        			        feature: {
        			            dataView: {readOnly: false},
        			            restore: {},
        			            saveAsImage: {}
        			        }
        			    },
        			    legend: {
        			        data: ['会员总数','从未消费的','至少消费一次的','活跃会员']
        			    },
        			    calculable: true,
        			    series: [
        			        {
        			            name:'漏斗图',
        			            type:'funnel',
        			            left: '10%',
        			            top: 60,
        			            bottom: 60,
        			            width: '80%',
        			            sort: 'descending',
        			            gap: 2,
        			            label: {
        			                normal: {
        			                    show: true,
        			                    position: 'inside'
        			                },
        			                emphasis: {
        			                    textStyle: {
        			                        fontSize: 20
        			                    }
        			                }
        			            },
        			            labelLine: {
        			                normal: {
        			                    length: 10,
        			                    lineStyle: {
        			                        width: 1,
        			                        type: 'solid'
        			                    }
        			                }
        			            },
        			            itemStyle: {
        			                normal: {
        			                    borderColor: '#fff',
        			                    borderWidth: 1
        			                }
        			            },
        			            data: map
        			        }
        			    ]
        			});//绘制完成Echarts
				}//success 
        	});//ajax
       }
        
    </script>
</body>
</html>