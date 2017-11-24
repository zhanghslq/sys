<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>便利店对比同比环比</title>
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
请选择前开始时间段：<input class="easyui-datetimebox" name="start"  id="oldShopstart"
        data-options="required:true,showSeconds:false" value="2017-08-06 0:0"   style="width:150px"> 
请选择前结束时间段：<input class="easyui-datetimebox" name="end" value="2017-08-21 0:0"  id="oldShopend"
        data-options="required:true,showSeconds:false"  style="width:150px"> 
         查询分类：<select name="query" id="shopComparequery" onchange="queryneirongShopCompare()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
	查询内容：<select  id="shopComparestation">
	
		   </select><br>
		    选择人群：<select  id="shopComparePeople" >
		       			<option value="all">全部交易数据</option>
		       			<option value="vip">会员交易数据</option>
		    		</select>	
	选择类别:<select  id="shopComparedepartmentName">
		       		<option value='all'>默认不区分类别</option>
		    </select>
	
请选择后开始时间段：<input class="easyui-datetimebox" name="start"  id="newShopstart"
        data-options="required:true,showSeconds:false" value="2017-09-06 0:0"   style="width:150px"> 
请选择后结束时间段：<input class="easyui-datetimebox" name="end" value="2017-09-21 0:0"  id="newShopend"
        data-options="required:true,showSeconds:false"  style="width:150px">
        <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryshopCompare()">对比</a>
</form>
    <div id="shopCompareMoneys" style="width:800px;height:600px;"></div>
    <div id="shopCompareNumbers" style="width:800px;height:600px;"></div>
    <div id="shopCompareSingle" style="width:800px;height:600px;"></div>
    <script type="text/javascript">
    function queryneirongShopCompare() {
    	
		 $("#shopComparestation").empty();
		 if($("#shopComparequery").val()=="station"){
			 $("#shopComparestation").append($("<option></option>").text('全部油站').val('all'));
		 }
		 $.ajax({
				type:"GET",
				url:"/sysmanager/station/queryAllName",
				dataType:"JSON",
				data:{"query":$("#shopComparequery").val()},
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#shopComparestation").append(option);
					});
				}
			});
	}
    $(function () {//页面加载完成之后
    	queryneirongShopCompare();
    	$.ajax({
			type:"GET",
			url:"/sysmanager/notOil/queryAllName",
			dataType:"JSON",
			success:function(result){
				$.each(result,function(i,oil){
					var option = $("<option></option>").text(oil).val(oil);
					$("#shopComparedepartmentName").append(option);
				});
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
				data:{"newstart":$("#newShopstart").datetimebox("getValue"),
					"newend":$("#newShopend").datetimebox("getValue"),
					"station":$("#shopComparestation").val(),
					"query":$("#shopComparequery").val(),
					"departmentName":$("#shopComparedepartmentName").val(),
					"oldstart":$("#oldShopstart").datetimebox("getValue"),
					"oldend":$("#oldShopend").datetimebox("getValue"),
					"people":$("#shopComparePeople").val()
				},
				success:function(map){
        		var colors=['#FBCE07','#DD1D21','#89CFDC'];
        		myChartshopCompareMoneys.setOption({
					color: colors,
					tooltip: {
						trigger: 'axis',
						axisPointer: {
							type: 'cross'
						},
						formatter: '前总销售额: {c0} 元<br />后总销售额: {c1} 元<br />销售额增长率: {c2}%'
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
							type:'bar',
							yAxisIndex: 1,
							data:map.moneys
						}
					]
				});//Echarts绘制完成
				myChartshopCompareNumbers.setOption({
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
							data:map.beforenumbers
						},
						{
							name:'后销售笔数',
							type:'bar',
							data:map.afternumbers
						},
						{
							name:'增长率',
							type:'bar',
							yAxisIndex: 1,
							data:map.numbers
						}
					]
				});//Echarts绘制完成
				myChartshopCompareSingle.setOption({
					color: colors,
					tooltip: {
						trigger: 'axis',
						axisPointer: {
							type: 'cross'
						},
						formatter: '前单笔销售额: {c0}元<br />后单笔销售额: {c1}元<br />单笔销售额增长率: {c2}%'
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
							type:'bar',
							yAxisIndex: 1,
							data:map.avgMoneys
						}
					]
				});//Echarts绘制完成
				}//success 
        	});//ajax
       }
    </script>
    <div style="height:50px;"></div>
</body>
</html>