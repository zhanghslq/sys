<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>评价</title>
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
    <script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
</head>
<body>
<form action="" id="exportExcel" method="post">
<div class="contentRight">
       <div class="rightDownSel">
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
                                      <a href="javascript:void(0);" onclick="queryTypeBy()">油站类型</a>
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
                                      <ul id="types">
                                         
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
                                <a href="javascript:void(0);" class="determine" onclick="queryEvaluation()">确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                                <a href="javascript:void(0);" class="determine" onclick="ExportExcel()">导出到Excel</a>
                              </div>
                           </div>
                       </div>
                      
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择时间</span></div>
                           <div class="seleContent selTime">
                              <div class="downCont selTimeMain">
                                  <div class="selTimeInfo">
                                     <div class="minimum">
                                        <em>最小时间单位</em>
                                        <div class="minimumRadio">
                                          <label><input name="date" type="radio" value="year" /> <i>按年展示</i> </label>
                                          <label><input name="date" type="radio" value="month" /> <i>按月展示</i> </label>
                                          <label><input name="date" type="radio" value="day" checked="checked"/> <i>按日展示</i> </label>
                                        </div>
                                      </div>
                                      <div class="startEndTime">
                                        <div class="startTime"><span>选择开始时间</span> <input size="16" name="start" style="width:300px" value="2017-08-14 14:45" class="am-form-field" id='evaluationstart'></div>
                                        <div class="endTime"><span>选择结束时间</span> <input size="16" name="end" style="width:300px" value="2017-09-14 14:45" class="am-form-field" id='evaluationend'></div>
                                      </div>
                                      <script>
                                      $('#evaluationstart').attr("value",getNowFormatDateOne());
                                      $('#evaluationend').attr("value",getNowFormatDate());
											$('#evaluationstart').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1
												});
											$('#evaluationend').datetimepicker({
												  format: 'yyyy-mm-dd hh:ii',
												  autoclose:1
												});
									  </script>
                                      <div class="downOperation timeOperation">
                                        <a href="javascript:void(0);" class="determine" onclick="queryEvaluation()">确定</a>
                                        <a href="javascript:void(0);" class="cancel">取消</a>
                                        <a href="javascript:void(0);" class="determine" onclick="ExportExcel()">导出到Excel</a>
                                      </div>
                                  </div>
                              </div>
                           </div>
                       </div>
                   </div>
               </div>
               <!-- 结束 -->
           </div>
       </div>
    </div>
    </form>
     <div id="evaluation" style="width:80%;height:80%;"></div>
     <div id="evaluationDistribution" style="width: 80%;height:80%;"></div>
     <div id="new" style="width: 80%;height:80%;"></div>
     <script type="text/javascript">
     
     function ExportExcel() {
     	$("#exportExcel").attr("action","/sysmanager/evaluation/exportEvaluation");
  	   	$("#exportExcel").submit();
     }
     var one = echarts.init(document.getElementById('new'));
     $(function() {
		queryEvaluationb();
	});
     function queryEvaluationb () {
      	$.ajax({
 				type:"POST",
 				url:"/sysmanager/evaluationb/queryEvaluation",
 				dataType:"JSON",
 				data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
 					"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),"type":jqchk("type"),
 					"station":jqchk("station"),"start":$("#evaluationstart").val(),
 					"end":$("#evaluationend").val(),"date":$("input[name='date']:checked").val()
 				},
 				success:function(map){
		     	one.setOption( {
		     		title : {
							text: '问题式评价结果',
							x:'center'
						},
		    	    tooltip : {
		    	        trigger: 'axis',
		    	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		    	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		    	        }
		    	    },
		    	    color:['#FBCE07','#DD1D21','#89CFDC'],
		    	    legend: {
		    	    	top:30,
		    	        data: ['是', '否','未回答']
		    	    },
		    	    grid: {
		    	    	top:'10%',
		    	        left: '3%',
		    	        right: '4%',
		    	        bottom: '3%',
		    	        containLabel: true
		    	    },
		    	    xAxis:  {
		    	        type: 'value'
		    	    },
		    	    yAxis: {
		    	        type: 'category',
		    	        data: map.name
		    	    },
		    	    series: [
		    	        {
		    	            name: '是',
		    	            type: 'bar',
		    	            stack: '总量',
		    	            data: map.yes
		    	        },
		    	        {
		    	            name: '否',
		    	            type: 'bar',
		    	            stack: '总量',
		    	            data: map.no
		    	        },{
		    	            name: '未回答',
		    	            type: 'bar',
		    	            stack: '总量',
		    	            data: map.unknown
		    	        }
		    	    ]
		    	});//Echarts
 				}//Success
      	});//ajax
     }//function
     </script>
     <script type="text/javascript">
     var myChartevaluation = echarts.init(document.getElementById('evaluation'));
     var myChartevaluationDistribution = echarts.init(document.getElementById('evaluationDistribution'));
     var lineStyle = {
				normal: {
					width: 1,
					opacity: 0.5
				}
			};
     $(function() {
		queryEvaluation();
	});
     function queryEvaluation () {
    	 queryEvaluationb();
     	$.ajax({
				type:"POST",
				url:"/sysmanager/evaluation/queryTrends",
				dataType:"JSON",
				data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk("sales"),
					"gasoline":jqchk("gasolines"),"locs":jqchk("location"),"openDate":jqchk("openDate"),"type":jqchk("type"),
					"station":jqchk("station"),"start":$("#evaluationstart").val(),
					"end":$("#evaluationend").val(),"date":$("input[name='date']:checked").val(),
					
				},
				success:function(map){
	        		myChartevaluation.setOption(option = {
	        			    title: {
	        			        text: '评价得分趋势',
	        			        x:'center'
	        			    },
	        			    tooltip: {
	        			        trigger: 'axis'
	        			    },
	        			    legend: {
	        			    	top:30,
	        					//itemWidth:5,
	        			        data:['分数']
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
	        			            name:'分数',
	        			            type:'line',
	        			            stack: '总量',
	        			            data:map.stars
	        			        }
	        			    ]
	        			});//绘制完Echarts
	        			myChartevaluationDistribution.setOption({
	  	                   title: {
	  	                      text: '评价平均分',
	  	                      x:'center'
	  	                  },
	  	                  tooltip: {
	  	                	 trigger: 'axis'
	  	                  },
	  	                  legend: {
	  	      				top:30,
	  	                    data:[{
	  	      					name: '平均分'
	  	      				}]
	  	                  },
	  	                  grid:{top:'10%'},
	  	                  xAxis: {
	  	                      data: ["总体满意度","油站环境","加油速度"]
	  	                  },
	  	                  yAxis: {
	  	                	  type:'value'
	  	                  },
	  	                  series: [{
	  	                      name: '平均分',
	  	                      type: 'bar',
	  	                      data: map.datas
	  	                  }
	  	      			]
	  	              });//绘制完Echarts
				}//success 
     		});//ajax
		};
</script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>