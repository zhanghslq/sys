<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <title>油站营业额</title>
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
<form id="exportExcel" method="post" action="/sysmanager/excelExport/exportVip1">
    <div class="contentRight" id="contentRightHeight">
    	 <div class="timeEndIng" id="dataTime"></div>
	     	<script type="text/javascript">
		     	$.ajax({
					type:"GET",
					url:"/sysmanager/time/queryThree",
					success:function(map){
						$("#dataTime").html("数据截止时间："+map);
					}
		     	});
	     	</script>
       <div class="rightDownSel" id="test">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                   
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择会员</span></div>
                           <div class="seleContent">
                              <div class="downCont">
                                  <div class="downNav">
                                      <a href="javascript:void(0);" class="titleCur">时间范围</a>
                                      <a href="javascript:void(0);">光顾油站</a>
                                      <a href="javascript:void(0);">油品消费行为</a>
                                      <a href="javascript:void(0);">便利店消费行为</a>
                                      <a href="javascript:void(0);">加油频次</a>
                                  </div>
                                  <div class="downContInfo">
                                      <ul style="display: block;">
	                                      <li><input style="display: inline-block;" type="radio" name="date" value="null" checked="checked"><span style="margin-left: 5px">不限</span></li>
	                                      <li><input style="display: inline-block;" type="radio" name="date" value="thirty"><span style="margin-left: 5px">近30天</span></li>
	                                      <li><input style="display: inline-block;" type="radio" name="date" value="sixty"><span style="margin-left: 5px">近60天</span></li>
	                                      <li><input style="display: inline-block;" type="radio" name="date" value="ninety"><span style="margin-left: 5px">近90天</span></li>
                                      </ul>
                                       <ul id="stations">
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='station' class='default'>
		                                      	<label for='station'></label>
		                                      	<span>全选</span>
                                      		</li>
                                      </ul>
                                     
                                      <ul id="oilHobbys">
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='oilName' class='default'>
		                                      	<label for='oilName'></label>
		                                      	<span>全选</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="oilName" value="oilnull" id='oilNamenull' class='default'>
		                                      	<label for='oilNamenull'></label>
		                                      	<span>无油品消费</span>
                                      		</li>
                                      </ul>
                                      <ul id="shopHobbys">
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='shopName' class='default'>
		                                      	<label for='shopName'></label>
		                                      	<span>全选</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="shopName" value="shopnull" id='shopnull' class='default'>
		                                      	<label for='shopnull'></label>
		                                      	<span>无便利店消费</span>
                                      		</li>
                                      </ul>
                                      <ul >
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='oilNumber' class='default'>
		                                      	<label for='oilNumber'></label>
		                                      	<span>全选</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="oilNumber" value="1" id='CheckOilNumber1' class='default'>
		                                      	<label for='CheckOilNumber1'></label>
		                                      	<span>1次</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="oilNumber" value="2" id='CheckOilNumber2' class='default'>
		                                      	<label for='CheckOilNumber2'></label>
		                                      	<span>2次</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="oilNumber" value="3" id='CheckOilNumber3' class='default'>
		                                      	<label for='CheckOilNumber3'></label>
		                                      	<span>3次</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="oilNumber" value="4" id='CheckOilNumber4' class='default'>
		                                      	<label for=CheckOilNumber4></label>
		                                      	<span>4次</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="oilNumber" value="5" id='CheckOilNumber5' class='default'>
		                                      	<label for='CheckOilNumber5'></label>
		                                      	<span>5次</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="oilNumber" value="6" id='CheckOilNumber6' class='default'>
		                                      	<label for='CheckOilNumber6'></label>
		                                      	<span>6次</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="oilNumber" value="7" id='CheckOilNumber7' class='default'>
		                                      	<label for='CheckOilNumber7'></label>
		                                      	<span>7次</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="oilNumber" value="8" id='CheckOilNumber8' class='default'>
		                                      	<label for='CheckOilNumber8'></label>
		                                      	<span>8次</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="oilNumber" value="9" id='CheckOilNumber9' class='default'>
		                                      	<label for='CheckOilNumber9'></label>
		                                      	<span>9次</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="oilNumber" value="10" id='CheckOilNumber10' class='default'>
		                                      	<label for='CheckOilNumber10'></label>
		                                      	<span>10次</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="oilNumber" value="11" id='CheckOilNumber11' class='default'>
		                                      	<label for='CheckOilNumber11'></label>
		                                      	<span>11次及以上</span>
                                      		</li>
                                      </ul>
                                  </div>
                              </div>
                               <div class="screenMain" >
                                  <ul id="tagContent">
                                      <!-- <li>
                                          <span>北京<em></em></span>
                                      </li> -->
                                  </ul>
                              </div>
                              <script type="text/javascript">
	                                  $(function() {
	                                	  $("ul li input[name='CheckAll']").each(function() {
											$(this).click(function () {
												var id=$(this).attr("id");
												CheckAll(id);
											});
										});
									});
	                                  function CheckAll(name) {
	                                	  $("ul li input[name='"+name+"']").each(function() {
	                                		  var id = $(this).attr("id");//这是获取的节点内容  
	                                		  if($(this).is(":checked")){
	                                			  $("#tagContent li[ids='" + id + "']").remove();
	                                		  }
	                                	  });
											//判断当前点击的复选框处于什么状态$(this).is(":checked") 返回的是布尔类型
											if($("ul li input[id='"+name+"']").is(":checked")){
												$("input[name='"+name+"']").prop("checked",true);
											}else{
												$("input[name='"+name+"']").prop("checked",false);
											}
											$("ul li input[name='"+name+"']").each(function() {
												var value = $(this).parent().find("span").html();//这是获取的节点内容
				                          		var id = $(this).attr("id");//这是获取的节点内容   
				                          		if (!$(this).is(':checked')) {
				                          			$("#tagContent li[ids='" + id + "']").remove();
				                          		} else {
				                          			$("#tagContent").append("<li ids="+id+"><span>"+value+"<em></em></span></li>");
				                          		};
											});
									}
	                                  $(function() {
                                            checkView("station");
                                            checkView("oilName");
                                            checkView("shopName");
                                            checkView("oilNumber");
          							})
                                  	</script>
                              <div class="downOperation">
                                <a id="determine" href="javascript:void(0);" onclick='Determine()' class="determine" >确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                               <a  href="javascript:void(0);" onclick="ExportExcel()" class="determine" >导出到Excel</a>
                              </div>
                           </div>
                       </div>
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择区域</span></div>
                           <div class="seleContent crowd">
                              <div class="downCont">
                                  <div class="downNav crowdNav">
                                      <a href="javascript:void(0);" onclick="ChangeArea('BJSHELL')" class="titleCur">北京会员</a>
                                      <a href="javascript:void(0); " onclick="ChangeArea('CDSHELL')">承德会员</a>
                                  </div>
                              </div>
                           </div>
                       </div>
                   </div>
               </div>
           </div>
       </div>
    </div>
  </form>
  <script type="text/javascript">
  var baseArea="BJSHELL";
  function ChangeArea(src){
	  baseArea=src;
	  queryStation();
  }
  function queryStation() {
	  $("#stations").empty();
	  var op="<li><input type='checkbox' name='CheckAll' id='station' class='default'><label for='station'></label><span>全选</span></li>";
	  $("#stations").append(op);
	  $.ajax({
    		type:"POST",
    		url:"/sysmanager/station/queryByArea",
    		async:false,
    		dataType:"JSON",
    		data:{"area":baseArea},
    		success:function(result){
    			$.each(result,function(i,station){
    				var option="<li><input name='station' value="+station.id+" type='checkbox' id='checkStation_"+i+"' class='default'><label for='checkStation_"+i+"'></label><span>"+station.name+"</span></li>";
    				$("#stations").append(option);
    			});
    		}
    	});
	  checkView("station");
	  $("#station").click(function () {
			var id=$(this).attr("id");
			CheckAll(id);
		});
	  
}
  $.ajax({
		type:"POST",
		url:"/sysmanager/oil/queryAllName",
		async:false,
		dataType:"JSON",
		success:function(result){
			$.each(result,function(i,oil){
				var option="<li><input name='oilName' value="+oil+" type='checkbox' id='checkOil_"+i+"' class='default'><label for='checkOil_"+i+"'></label><span>"+oil+"</span></li>";
				$("#oilHobbys").append(option);
			});
		}
	});
	$.ajax({
		type:"POST",
		url:"/sysmanager/notOil/queryAllName",
		async:false,
		dataType:"JSON",
		success:function(result){
			$.each(result,function(i,shop){
				var option="<li><input name='shopName' value="+shop+" type='checkbox' id='checkShop_"+i+"' class='default'><label for='checkShop_"+i+"'></label><span>"+shop+"</span></li>";
				$("#shopHobbys").append(option);
			});
		}
	});
</script>
   <script type="text/javascript">
   function ExportExcel() {
	   $("#exportExcel").attr("value","/sysmanager/excelExport/exportVip1?area="+baseArea);
	   $("#exportExcel").submit();
   }
   function Determine() {
	   var pager=$dg.datagrid("getPager");
	   pager.pagination('refresh', {
           pageNumber:1,
           pageSize:40,
	   }); 
	   queryvipTag(1,40,jqchk("station"),jqchk("oilName"),jqchk("shopName"),jqchk("oilNumber"));
}
   $(function() {
	query();
	queryStation();
});
	   function jqchk(chkName){ //jquery获取复选框值 
			var chk_value =[]; 
			$("input[name='"+chkName+"']:checked").each(function(){ 
				chk_value.push($(this).val()); 
			}); 
			console.log(chkName);
			console.log(chk_value);
			return chk_value;
		}
	   function queryvipTag(pageNumber,pageSize,stations,oilName,shopName,oilNumber){
			$.ajax({
				type:"POST",
				url:"/sysmanager/vipTag/queryVip",
				async:false,
				dataType:"JSON",
				data:{"date":$("input[name='date']:checked").val(),
					"station":stations,"oilName":oilName,"shopName":shopName,
					"page":pageNumber,"rows":pageSize,"area":baseArea,"oilNumber":oilNumber
					},
				success:function(map){
					$dg.datagrid("loadData",pageData(map.rows, map.total));
					var pager=$dg.datagrid("getPager");
					   pager.pagination('refresh', {
						   total:map.total,
				           pageNumber:1,
				           pageSize:40,
					   });
				}
			});
	   }
    var $dg,$da;
    var start,end;
    function query() {
    	$dg = $("#reDg");
        $da = $("#reDa");
        $dg.datagrid({  
            title:"会员", 
            url: '/sysmanager/vipTag/queryVip',
            rownumbers:true,  
            fitColumns:true,  
            pagination:true,  
            pageSize: 40,
			pageList: [10,20,30,40,50],
			pageNumber:1,
            columns:[  
                [  
                    {title: "编号", field: "carduser_id", width: 80, align: 'center'},
	                {title: "名字", field: "name", width: 80, align: 'center'},
	                {title: "手机号", field: "mobilePhone", width: 80, align: 'center'},
	                {title: "标签", field: "tag", width: 300, align: 'center'}
                ]  
            ]  
        });
        var pager=$dg.datagrid("getPager");
       	pager.pagination({
            onSelectPage:function (pageNo, pageSize) {  
            	$.ajax({
    				type:"POST",
    				url:"/sysmanager/vipTag/queryVip",
    				async:false,
    				dataType:"JSON",
    				data:{"date":$("input[name='date']:checked").val(),
    					"shopName":jqchk("shopName"),"oilName":jqchk("oilName"),
    					"station":jqchk("station"),"oilNumber":jqchk("oilNumber"),
    					"page":pageNo,"rows":pageSize,"area":baseArea
    					},
    				success:function(map){
    					$dg.datagrid("loadData",pageData(map.rows, map.total));
    					pager.pagination('refresh', {
    	                	total:map.total,
    	                    pageNumber:pageNo,
    	                    pageSize:pageSize,
    	                }); 
    				}
    			});
            }  
        });   
	}
    function pageData(list,total){
        var obj=new Object(); 
        obj.total=total; 
        obj.rows=list; 
        return obj; 
    }
</script>
<div  class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',">
        <table id="reDg">
        </table>
        <div id="reDa" style="height: 60px">
        </div>
    </div>
</div>
<div style="height:50px"></div>
<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>