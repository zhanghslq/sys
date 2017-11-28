<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>油站营业额</title>
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
		  请选择前开始时间段：	<input id="zoushistart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2017-08-01 00:00" style="width:150px"> 
		  请选择前结束时间段：<input id="zoushiend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="2017-9-10 00:00" style="width:150px">
		  查询分类：<select name="query" id="query" onchange="queryneirong()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
		    查询内容：<select name="station" id="bazhstation">
		       			
		    		</select><br>
		 选择人群：<select  id="baseComparePeople" >
		       			<option value="all">全部交易数据</option>
		       			<option value="vip">会员交易数据</option>
		    		</select>		
		 选择油品:<select  id="oilCompareOilName">
		       		<option value='all'>默认不区分油品</option>	
		    </select>
		    请选择后开始时间段：	<input id="newzoushistart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2017-09-10 00:00" style="width:150px"> 
		   请选择后结束时间段：<input id="newzoushiend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="2017-10-10 00:00" style="width:150px">
		  <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="querybaseOil()">查询</a>  
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="amount" style="width:80%;height:60%;min-height: 600px;min-width: 800px"></div>
    <div id="number" style="width:80%;height:60%;min-height: 600px;min-width: 800px"></div>
    <div id="single" style="width:80%;height:60%;min-height: 600px;min-width: 800px"></div>
    
    <script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
        var amount = echarts.init(document.getElementById('amount'));
        var number = echarts.init(document.getElementById('number'));
        var single = echarts.init(document.getElementById('single'));
      //格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
         // 指定图表的配置项和数据
     $(function () {//页面加载完成之后
    	$.ajax({
			type:"GET",
			url:"/sysmanager/oil/queryAllName",
			dataType:"JSON",
			success:function(result){
				$.each(result,function(i,oil){
					var option = $("<option></option>").text(oil).val(oil);
					$("#oilCompareOilName").append(option);
				});
			}
		});
	}); 
        function queryneirong() {
    		 $("#bazhstation").empty();
    		 if($("#query").val()=="station"){
    			 $("#bazhstation").append($("<option></option>").text('全部油站').val('all'));
    		 }
    		 $.ajax({
					type:"GET",
					url:"/sysmanager/station/queryAllName",
					dataType:"JSON",
					data:{"query":$("#query").val()},
					success:function(result){
						$.each(result,function(i,station){
							var option = $("<option></option>").text(station.name).val(station.id);
							$("#bazhstation").append(option);
						});
					}
				});
		}
     $(function () {
    	 queryneirong();
    	 querybaseOil();
	}); 
	function querybaseOil(){
		$.ajax({
			type:"post",
			url:"/sysmanager/compare/queryOil",
			dataType:"JSON",
			data:{"station":$("#bazhstation").val(),"oldstart":$("#zoushistart").datetimebox("getValue"),
				"oldend":$("#zoushiend").datetimebox("getValue"),
				"newstart":$("#newzoushistart").datetimebox("getValue"),
				"newend":$("#newzoushiend").datetimebox("getValue"),"query":$("#query").val(),
				"oilName":$("#oilCompareOilName").val(),"people":$("#baseComparePeople").val()
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
    
    <div style="height: 100px"></div><!-- 最下面填充的div -->
</body>
</html>