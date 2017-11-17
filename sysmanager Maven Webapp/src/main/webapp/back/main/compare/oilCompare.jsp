<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>油品对比</title>
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
请选择开始时间段：<input class="easyui-datetimebox" name="start"  id="oldOilstart"
        data-options="required:true,showSeconds:false" value="2017-08-06 0:0"   style="width:150px"> 
请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-08-21 0:0"  id="oldOilend"
        data-options="required:true,showSeconds:false"  style="width:150px"> 
         查询分类：<select name="query" id="oilComparequery" onchange="queryneirongOilCompare()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
	查询内容：<select  id="oilComparestation">
	
		   </select>
	选择油品:<select  id="oilCompareOilName">
		       		<option value='all'>默认不区分油品</option>	
		    </select>
	<br>
请选择开始时间段：<input class="easyui-datetimebox" name="start"  id="newOilstart"
        data-options="required:true,showSeconds:false" value="2017-9-06 0:0"   style="width:150px"> 
请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-9-21 0:0"  id="newOilend"
        data-options="required:true,showSeconds:false"  style="width:150px">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryoilCompare()">对比</a>
</form>
   
    <div id="oilCompare" style="width:80%;height:80%;"></div>
   <!--  <div style="width:80%;height: 80%;background-color: red"></div> -->
    <script type="text/javascript">
    function queryneirongOilCompare() {
		 $("#oilComparestation").empty();
		 if($("#oilComparequery").val()=="station"){
			 $("#oilComparestation").append($("<option></option>").text('全部油站').val('all'));
		 }
		 $.ajax({
				type:"GET",
				url:"/sysmanager/station/queryAllName",
				dataType:"JSON",
				data:{"query":$("#oilComparequery").val()},
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#oilComparestation").append(option);
					});
				}
			});
	}
    $(function () {//页面加载完成之后
    	queryneirongOilCompare();
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
        // 基于准备好的dom，初始化echarts实例
        var myChartoilCompare = echarts.init(document.getElementById('oilCompare'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryoilCompare() {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/compare/queryOil",
				dataType:"JSON",
				data:{"newstart":$("#newOilstart").datetimebox("getValue"),
					"newend":$("#newOilend").datetimebox("getValue"),
					"station":$("#oilComparestation").val(),
					"query":$("#oilComparequery").val(),
					"oilName":$("#oilCompareOilName").val(),
					"oldstart":$("#oldOilstart").datetimebox("getValue"),
					"oldend":$("#oldOilend").datetimebox("getValue")
				},
				success:function(map){
        		myChartoilCompare.setOption({
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
                        data: ["交易升数","交易笔数","单笔交易额"] 
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