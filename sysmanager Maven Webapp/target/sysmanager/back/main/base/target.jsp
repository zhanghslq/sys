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
<form action="" id="exportExcel" method="post">
 <div class="contentRight" >
       <div class="rightDownSel" >
           
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
                              <div class="screenMain" >
		                                  <ul id="tagContent">
		                                      
		                                  </ul>
		                       </div>
                              <div class="downOperation">
                                <a href="javascript:void(0);" onclick="queryTarget()" class="determine">确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                                <a href="javascript:void(0);" onclick="ExportExcel()" class="determine">导出到Excel</a>
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
    </form>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="target" style="width:90%;height:80%; min-height: 600px;min-width: 800px"></div>
    
    <script type="text/javascript">
    function ExportExcel() {
    	$("#exportExcel").attr("action","/sysmanager/target/exportTarget");
 	   	$("#exportExcel").submit();
    }
    $(function() {
		queryTarget();
	});
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