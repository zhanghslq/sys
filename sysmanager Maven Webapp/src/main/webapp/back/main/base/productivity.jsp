<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>劳动生产率</title>
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
       <div class="rightDownSel">
          
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       <!-- 这是跟选择油站平级的 -->
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择油站</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav" id="productstation">
                                  
                                  </div>
                              </div>
                           </div>
                       </div>
                       <form action="" method="post" id="exportExcel">
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择时间</span></div>
                           <div class="seleContent selTime">
                              <div class="downCont selTimeMain">
                                  <div class="selTimeInfo">
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" name="start" style="width:300px"  class="am-form-field" id='productstart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end" style="width:300px"  class="am-form-field" id='productend'></div>
                                      </div>
                                      <script>
                                      $('#productstart').attr("value",getLastFormatDateOne());
											$('#productstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
											$('#productend').attr("value",getLastFormatDate());
											$('#productend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1,
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryProduct()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                        <a href="javascript:void(0);" class="determine" onclick="ExportExcel()">导出到Excel</a>
                                      </div>
                                  </div>
                              </div>
                           </div>
                       </div>
                       </form>
                   </div>
               </div>
               <!-- 结束 -->
           </div>
       </div>
    </div>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
   
    <script type="text/javascript">
    function ExportExcel() {
    	$("#exportExcel").attr("action","/sysmanager/product/exportProduct?station="+baseStation);
 	   	$("#exportExcel").submit();
    }
        // 基于准备好的dom，初始化echarts实例
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        // 指定图表的配置项和数据
        $.ajax({
				type:"GET",
				url:"/sysmanager/city/queryAll",
				dataType:"JSON",
				async: false,
				success:function(result){
					$.each(result,function(i,station){
						var option = $("<a href='javascript:void(0);' class=''></a>").text(station.name).val(station.id).on('click',function(){
							ChangeStation(station.id);
						});
						$("#productstation").append(option);
					});
				}
			});
		var baseStation="50001";
		function ChangeStation(src){
			baseStation=src;
			queryProduct();
		}
		
		
        </script>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="product" style="width:80%;height:80%;min-height: 600px;min-width: 800px"></div>
    <script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
        var myChartPRODUCT = echarts.init(document.getElementById('product'));
      	//格式化时间
		//定义ajax请求，当选择框发生变化的时候，发送ajax请求，携带下拉框的数据
        //应该定义一个方法，当选择框的数据发生变化时，调用方法，并把选择框的数据带过去
        // 指定图表的配置项和数据
        // 使用刚指定的配置项和数据显示图表。
        $(queryProduct());
	function queryProduct(){
		$.ajax({
			type:"post",
			url:"/sysmanager/product/queryProduct",
			dataType:"JSON",
			data:{"station":baseStation,"start":$("#productstart").val(),
				"end":$("#productend").val()
			},
			success:function(map){
				myChartPRODUCT.setOption({
						    title: {
						        text: '劳动生产率',
						        x:'center'
						    },
						    tooltip: {
						        trigger: 'axis'
						    },
						    legend: {
						    	top:30,
								itemWidth:5,
						        data:['劳动生产率']
						    },
						    grid: {
						    	top:'10%',
						        left: '3%',
						        right: '4%',
						        bottom: '3%',
						        containLabel: true
						    },
						    toolbox: {
						    	right:18,
						        feature: {
						            saveAsImage: {}
						        }
						    },
						    xAxis: {
						        type: 'category',
						        boundaryGap: false,
						        data: map.dates
						    },
						    yAxis: {
						        type: 'value'
						    },
						    series: [
						        {
						            name:'劳动生产率',
						            type:'line',
						            stack: '总量',
						            data:map.data
						        }
						    ]
						});
					
			}
		});
	}
    </script>
    <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
	<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>