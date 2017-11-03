<%@  page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>油品对比</title>
</head>
<body>
<form action="">
请选择开始时间段：<input class="easyui-datetimebox" name="start"  id="oldRatestart"
        data-options="required:true,showSeconds:false" value="2017-08-06 0:0"   style="width:150px"> 
请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-08-21 0:0"  id="oldRateend"
        data-options="required:true,showSeconds:false"  style="width:150px"> 
         查询分类：<select name="query" id="rateComparequery" onchange="queryneirongRateCompare()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
	查询内容：<select  id="rateComparestation">
	
		   </select>
	
	<br>
请选择开始时间段：<input class="easyui-datetimebox" name="start"  id="newRatestart"
        data-options="required:true,showSeconds:false" value="2017-9-06 0:0"   style="width:150px"> 
请选择结束时间段：<input class="easyui-datetimebox" name="end" value="2017-9-21 0:0"  id="newRateend"
        data-options="required:true,showSeconds:false"  style="width:150px">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryrateCompare()">对比</a>
</form>
   
    <div id="rateCompare" style="width:80%;height:80%;"></div>
    <script type="text/javascript">
    $(function () {
    	queryneirongRateCompare();
	});
    function queryneirongRateCompare() {
		 $("#rateComparestation").empty();
		 if($("#rateComparequery").val()=="station"){
			 $("#rateComparestation").append($("<option></option>").text('全部油站').val('all'));
		 }
		 $.ajax({
				type:"GET",
				url:"/sysmanager/station/queryAllName",
				dataType:"JSON",
				data:{"query":$("#rateComparequery").val()},
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#rateComparestation").append(option);
					});
				}
			});
	}
        // 基于准备好的dom，初始化echarts实例
        var myChartrateCompare = echarts.init(document.getElementById('rateCompare'));
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        function queryrateCompare() {
        	$.ajax({
				type:"POST",
				url:"/sysmanager/compare/queryRateCompare",
				dataType:"JSON",
				data:{"newstart":$("#newRatestart").datetimebox("getValue"),
					"newend":$("#newRateend").datetimebox("getValue"),
					"station":$("#rateComparestation").val(),
					"query":$("#rateComparequery").val(),
					"oldstart":$("#oldRatestart").datetimebox("getValue"),
					"oldend":$("#oldRateend").datetimebox("getValue")
				},
				success:function(map){
        		myChartrateCompare.setOption({
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
                        data: ["便利店开单率"]
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