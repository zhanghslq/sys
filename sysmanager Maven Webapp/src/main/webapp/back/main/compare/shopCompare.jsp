<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>便利店对比</title>
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
请选择开始时间段：<input class="easyui-datetimebox" name="start"  id="oldShopstart"
        data-options="required:true,showSeconds:false" value="2017-08-06 0:0"   style="width:150px"> 
请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-08-21 0:0"  id="oldShopend"
        data-options="required:true,showSeconds:false"  style="width:150px"> 
         查询分类：<select name="query" id="shopComparequery" onchange="queryneirongShopCompare()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
	查询内容：<select  id="shopComparestation">
	
		   </select>
	选择类别:<select  id="shopComparedepartmentName">
		       		<option value='all'>默认不区分类别</option>
		    </select>
	<br>
请选择开始时间段：<input class="easyui-datetimebox" name="start"  id="newShopstart"
        data-options="required:true,showSeconds:false" value="2017-09-06 0:0"   style="width:150px"> 
请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-09-21 0:0"  id="newShopend"
        data-options="required:true,showSeconds:false"  style="width:150px">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryshopCompare()">对比</a>
</form>
    <div id="shopCompare" style="width:80%;height:80%;"></div>
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
	}); 
        // 基于准备好的dom，初始化echarts实例
        var myChartshopCompare = echarts.init(document.getElementById('shopCompare'));
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
					"oldend":$("#oldShopend").datetimebox("getValue")
				},
				success:function(map){
        		myChartshopCompare.setOption({
                    title: {
                        text: '北京壳牌'
                    },
                    tooltip: {},
                    legend: {
                        data:[{
        					name: '增长率'
        				}]
        				
                    },
                    xAxis: {
                        data: ["交易额","交易数","单笔交易额"]
                    },
                    yAxis: {},
                    series: [{
                        name: '增长率',
                        type: 'bar',
                        data: map
                    }]
                });
				}//success 
        	});//ajax
       }
        
    </script>
</body>
</html>