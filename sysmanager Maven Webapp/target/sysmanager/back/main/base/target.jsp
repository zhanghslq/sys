<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>销售额完成情况</title>
    <!-- 引入 echarts.js -->
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
</head>

<body>

 <div class="contentRight" >
       <div class="rightDownSel" >
           <!-- <ul class="tabNav">
               <li class="on">整体销售</li>
               <li>燃油销售</li>
               <li>非油销售</li>
               <li>润滑油销售</li>
           </ul> -->
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择油站</span></div>
                           <div class="seleContent">
                              <div class="downCont">
                                  <div class="downNav">
                                      <a href="javascript:void(0);" class="titleCur">城市</a>
                                      <a href="javascript:void(0);" onclick="queryAdministriveRegionBy()">行政区</a>
                                      <a href="javascript:void(0);" onclick="querySalesAreaBy()">销售区</a>
                                      <a href="javascript:void(0);" onclick="queryGasolineBy()">商圈类型</a>
                                      <a href="javascript:void(0);" onclick="queryLocationBy()">位置</a>
                                      <a href="javascript:void(0);" onclick="queryOpenDateBy()">开业时间</a>
                                      <a href="javascript:void(0);" onclick="queryStationBy()">站名</a>
                                  </div>
<script type="text/javascript">
function jqchk(chkName){ //jquery获取复选框值 
	var chk_value =[]; 
	$("input[name='"+chkName+"']:checked").each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	return chk_value;
}
function queryAdministriveRegionBy() {
		$("#regions").empty();
		$.ajax({
			type:"POST",
			url:"/sysmanager/station/queryAdministriveRegionBy",
			dataType:"JSON",
			data:{"citys":jqchk("citys")},
			success:function(result){
				$.each(result,function(i,region){
					var option="<li><input name='regions' value="+region+" type='checkbox' id='checktwo_"+i+"' class='default'><label for='checktwo_"+i+"'></label><span>"+region+"</span></li>";
					$("#regions").append(option);
				});
			}
		});
	}
function querySalesAreaBy() {
	$("#sales").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/querySalesAreaBy",
		dataType:"JSON",
		data:{"citys":jqchk("citys"),"regions":jqchk("regions")},
		success:function(result){
			$.each(result,function(i,sale){
				var option="<li><input name='sales' value="+sale+" type='checkbox' id='checksale_"+i+"' class='default'><label for='checksale_"+i+"'></label><span>"+sale+"</span></li>";
				$("#sales").append(option);
			});
		}
	});
}     
function queryGasolineBy() {
	$("#gasolines").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/queryGasolineBy",
		dataType:"JSON",
		data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk(sales)},
		success:function(result){
			$.each(result,function(i,gasoline){
				var option="<li><input name='gasolines' value="+gasoline+" type='checkbox' id='checkgasoline_"+i+"' class='default'><label for='checkgasoline_"+i+"'></label><span>"+gasoline+"</span></li>";
				$("#gasolines").append(option);
			});
		}
	});
}     
function queryLocationBy() {
	$("#location").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/queryLocationBy",
		dataType:"JSON",
		data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk(sales),"gasoline":jqchk("gasolines")},
		success:function(result){
			$.each(result,function(i,location){
				var option="<li><input name='location' value="+location+" type='checkbox' id='checklocation_"+i+"' class='default'><label for='checklocation_"+i+"'></label><span>"+location+"</span></li>";
				$("#location").append(option);
			});
		}
	});
}
function queryOpenDateBy() {
	$("#openDate").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/queryOpenDateBy",
		dataType:"JSON",
		data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
			"gasoline":jqchk("gasolines"),"locs":jqchk("location")},
		success:function(result){
			$.each(result,function(i,openDate){
				var option="<li><input name='openDate' value="+openDate+" type='checkbox' id='checkopenDate_"+i+"' class='default'><label for='checkopenDate_"+i+"'></label><span>"+openDate+"</span></li>";
				$("#openDate").append(option);
			});
		}
	});
}
function queryStationBy() {
	$("#station").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/queryStationBy",
		dataType:"JSON",
		data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
			"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate")},
		success:function(result){
			$.each(result,function(i,station){
				var option="<li><input name='station' value="+station.id+" type='checkbox' id='checkstation_"+i+"' class='default'><label for='checkstation_"+i+"'></label><span>"+station.name+"</span></li>";
				$("#station").append(option);
			});
		}
	});
}
	$(function(){
		$.ajax({
			type:"GET",
			url:"/sysmanager/station/queryAllCity",
			dataType:"JSON",
			success:function(result){
				$.each(result,function(i,city){
					var option="<li><input name='citys' type='checkbox' value='"+city+"' id='checkone_"+i+"' class='default'><label for='checkone_"+i+"'></label><span>"+city+"</span></li>";
					$("#citys").append(option);
				});
			}
		});
		
	});
	
</script>
                                  <div class="downContInfo">
                                      <ul style="display: block;" id="citys">
                                      </ul>
                                      <ul id="regions">
                                      </ul>
                                      <ul id="sales">
                                      </ul>
                                      <ul id="gasolines">
                                      </ul>
                                      <ul id="location">
                                          
                                      </ul>
                                      <ul id="openDate">
                                         
                                      </ul>
                                      <ul id="station">
                                          
                                      </ul>
                                  </div>
                              </div>
                              <div class="downOperation">
                                <a href="javascript:void(0);" onclick="queryTarget()" class="determine">确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                              </div>
                           </div>
                       </div>
                       
                   </div>
               </div>
               <div class="downDetails"><!-- 2 -->
                 
               </div>
               <div class="downDetails">3</div>
               <div class="downDetails">4</div>
           </div>
       </div>
    </div>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="target" style="width:90%;height:80%; min-height: 600px;min-width: 800px"></div>
    
    <script type="text/javascript">
        //基于准备好的dom，初始化echarts实例
        var myCharttarget = echarts.init(document.getElementById('target'));
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        // 指定图表的配置项和数据
        function queryTarget(){
		$.ajax({
			type:"post",
			url:"/sysmanager/target/queryTarget",
			dataType:"JSON",
			data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
				"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),
				"station":jqchk("station")},
			success:function(map){
					 myCharttarget.setOption({
				            title: {
				                text: '目标达成率'
				            },
				            tooltip: {
				            	formatter: '{c}%'
				            },
				            legend: {
				                data:[{
									name: '油品销量达成率'
								}]
								
				            },
				            xAxis: {
				                data: map.days
				            },
				            yAxis: {
				            	type:'value',
				            	axisLabel: {
									formatter: '{value}%'
								}
				            },
				            series: [{
				                name: '油品销量达成率',
				                type: 'bar',
				                data: map.data,
				                markLine : {
				                    data : [
										{
										    name: '100%完成线',
										    yAxis: 100
										}
				                    ]
				                },
				                label: {
				                    normal: {
				                        show: true,
				                        position: 'inside',
				                        formatter: '{c}%'
				                    }
				                },
				                
				            }]
				        });
			}
		});
	}
				
        // 使用刚指定的配置项和数据显示图表。
    </script>
        <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>