<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>前庭室内支付对比</title>
    <!-- 引入 echarts.js -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/css/IconExtension.css">
    <script src="${pageContext.request.contextPath}/back/easyui/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/form.validator.rules.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="${pageContext.request.contextPath}/back/echar/echarts.js"></script>
</head>
<body>
<form action="">
		  请选择开始时间段：<input id="hhtstart" class="easyui-datetimebox" name="start"     
		        data-options="required:true,showSeconds:false" value="2016-10-1 2:3" style="width:150px"> 
		  请选择结束时间段：<input id="hhtend" class="easyui-datetimebox" name="end"     
		        data-options="required:true,showSeconds:false" value="4/4/2010 0:0" style="width:150px"> 
				
		   查询分类：<select name="query" id="hhtquery" onchange="queryHHT()">
		       			<option value="station">油站</option>
		       			<option value="category">类别</option>
		       			<option value="tag">标签</option>
		    		</select>
		    查询内容：<select name="station" id="hhtstation">
		       			
		    	</select>
    		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"   
        onclick="queryhhtipt()">查询</a>
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="hhtipt" style="width:90%;height:80%;"></div>
    
    <script type="text/javascript">
    $(function () {
    	queryHHT();
	}); 
    function queryHHT() {
		 $("#hhtstation").empty();
		 if($("#hhtquery").val()=='station'){
			 $("#hhtstation").append($("<option></option>").text('全部油站').val('all'));
		 }
		 $.ajax({
				type:"GET",
				url:"/sysmanager/station/queryAllName",
				dataType:"JSON",
				data:{"query":$("#hhtquery").val()},
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<option></option>").text(station.name).val(station.id);
						$("#hhtstation").append(option);
					});
				}
			});
	}
        // 基于准备好的dom，初始化echarts实例
        var myCharthht = echarts.init(document.getElementById('hhtipt'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
         function queryhhtipt() {
        	 $.ajax({
     			type:"post",
     			url:"/sysmanager/mop/queryHHT",
     			dataType:"JSON",
     			data:{"station":$("#hhtstation").val(),"start":$("#hhtstart").datetimebox("getValue"),
     				"end":$("#hhtend").datetimebox("getValue"),"query":$("#hhtquery").val()
     			},
     			success:function(map){
     				myCharthht.setOption({
     						title : {
     							text: 'HHT IPT对比',
     							subtext: '北京壳牌',
     							x:'center'
     						},
     						tooltip : {
     							trigger: 'item',
     							formatter: "{a} <br/>{b} : {c} ({d}%)"
     						},
     						legend: {
     							orient: 'vertical',
     							left: 'left',
     							data: ['IPT支付','HHT支付']
     						},
     						series : [
     							{
     								name: '支付方式',
     								type: 'pie',
     								radius : '55%',
     								center: ['50%', '60%'],
     								data:map,
     								itemStyle: {
     									emphasis: {
     										shadowBlur: 10,
     										shadowOffsetX: 0,
     										shadowColor: 'rgba(0, 0, 0, 0.5)'
     									}
     								}
     							}
     						]
     					});
     			}
     		});
		}
        // 指定图表的配置项和数据
        
    </script>
</body>
</html>