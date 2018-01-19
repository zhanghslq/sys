<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
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
<form id="exportExcel" method="post" action="/sysmanager/excelExport/exportVip">
    <div class="contentRight" id="contentRightHeight">
       <div class="rightDownSel" id="test">
           <div class="rightDownMain">
               <div class="downDetails" style="display: block;">
                   <div class="selectbox">
                       <div class="selemeTitle">
                           <div class="selemenu"><span>选择会员</span></div>
                           <div class="seleContent">
                              <div class="downCont">
                                  <div class="downNav">
                                      <a href="javascript:void(0);" class="titleCur">会员忠诚度</a>
                                      <a href="javascript:void(0);">会员身份</a>
                                      <a href="javascript:void(0);">会员性别</a>
                                      <a href="javascript:void(0);">会员年龄</a>
                                      <a href="javascript:void(0);">会员类型</a>
                                      <a href="javascript:void(0);">优惠券使用情况</a>
                                      <a href="javascript:void(0);">近期加油频次</a>
                                      <a href="javascript:void(0);">近期非油消费额</a>
                                      <a href="javascript:void(0);">短期加油频次</a>
                                      <a href="javascript:void(0);">经常光顾油站</a>
                                      <a href="javascript:void(0);">常用支付方式</a>
                                      <a href="javascript:void(0);">油品选择偏好</a>
                                      <a href="javascript:void(0);">便利店购物偏好</a>
                                      <a href="javascript:void(0);">活动标签</a>
                                  </div>
                                  <div class="downContInfo">
                                      <ul style="display: block;">
	                                      <li>
		                                      	<input type='checkbox' name="CheckAll"  id='loyalty' class='default'>
		                                      	<label for='loyalty'></label>
		                                      	<span>全选</span>
	                                      </li>
	                                      <li>
		                                      	<input type='checkbox' name="loyalty" value="1" id='checkOne1' class='default'>
		                                      	<label for='checkOne1'></label>
		                                      	<span>潜在顾客</span>
	                                      	</li>
	                                      	<li>
		                                      	<input type='checkbox' name="loyalty" value="2" id='checkOne2' class='default'>
		                                      	<label for='checkOne2'></label>
		                                      	<span>首次消费顾客</span>
	                                      	</li>
	                                      	<li>
		                                      	<input type='checkbox' name="loyalty" value="3" id='checkOne3' class='default'>
		                                      	<label for='checkOne3'></label>
		                                      	<span>潜力顾客</span>
	                                      	</li>
	                                      	<li>
		                                      	<input type='checkbox' name="loyalty" value="4" id='checkOne4' class='default'>
		                                      	<label for='checkOne4'></label>
		                                      	<span>忠诚顾客</span>
	                                      	</li>
	                                      	<li>
		                                      	<input type='checkbox' name="loyalty" value="5" id='checkOne5' class='default'>
		                                      	<label for='checkOne5'></label>
		                                      	<span>潜在流失顾客</span>
	                                      	</li>
	                                      	<li>
		                                      	<input type='checkbox'  name="loyalty" value="6" id='checkOne6' class='default'>
		                                      	<label for='checkOne6'></label>
		                                      	<span>沉睡顾客</span>
	                                      	</li>
                                      </ul>
                                     
                                      <ul id="select2">
                                      	<li>
	                                      	<input type='checkbox' name="CheckAll" id='identity' class='default'>
	                                      	<label for='identity'></label>
	                                      	<span>全选</span>
                                      	</li>
                                      	<li>
	                                      	<input type='checkbox' name="identity" value="40"  id='checkTwo1' class='default'>
	                                      	<label for='checkTwo1'></label>
	                                      	<span>出租车/专快车</span>
                                      	</li>
                                      	<li>
	                                      	<input type='checkbox' name="identity" value="1" id='checkTwo2' class='default'>
	                                      	<label for='checkTwo2'></label>
	                                      	<span>普通客户</span>
                                      	</li>
                                      	<li>
	                                      	<input type='checkbox' name="identity" value="10" id='checkTwo3' class='default'>
	                                      	<label for='checkTwo3'></label>
	                                      	<span>金卡客户</span>
                                      	</li>
                                      	<li>
	                                      	<input type='checkbox' name="identity" value="20" id='checkTwo4' class='default'>
	                                      	<label for='checkTwo4'></label>
	                                      	<span>铂金卡客户</span>
                                      	</li>
                                      	<li>
	                                      	<input type='checkbox' name="identity" value="30" id='checkTwo5' class='default'>
	                                      	<label for='checkTwo5'></label>
	                                      	<span>钻石卡客户</span>
                                      	</li>
                                      	<li>
	                                      	<input type='checkbox' name="identity" id='checkTwo6' value="50" class='default'>
	                                      	<label for='checkTwo6'></label>
	                                      	<span>壳牌中国员工</span>
                                      	</li>
                                      </ul>
                                      
                                      <ul >
                                      <li>
	                                      	<input type='checkbox' name="CheckAll" id='gender' class='default'>
	                                      	<label for='gender'></label>
	                                      	<span>全选</span>
                                      	</li>
	                                      <li>
		                                      	<input type='checkbox' name="gender" value="1" id='checkThree1' class='default'>
		                                      	<label for='checkThree1'></label>
		                                      	<span>男</span>
	                                      	</li>
	                                      	<li>
		                                      	<input type='checkbox' name="gender" value="2" id='checkThree2' class='default'>
		                                      	<label for='checkThree2'></label>
		                                      	<span>女</span>
	                                      	</li>
                                      </ul>
                                      
                                      <ul >
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='age' class='default'>
		                                      	<label for='age'></label>
		                                      	<span>全选</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="age" value="teenager" id='checkFour1' class='default'>
		                                      	<label for='checkFour1'></label>
		                                      	<span>青少年</span>
	                                      	</li>
                                      		<li>
		                                      	<input type='checkbox' name="age" value="youth" value="" id='checkFour2' class='default'>
		                                      	<label for='checkFour2'></label>
		                                      	<span>青年</span>
	                                      	</li>
                                      		<li>
		                                      	<input type='checkbox' name="age" value="middle" id='checkFour3' class='default'>
		                                      	<label for='checkFour3'></label>
		                                      	<span>中年</span>
	                                      	</li>
                                      		<li>
		                                      	<input type='checkbox' name="age" value="agedness" id='checkFour4' class='default'>
		                                      	<label for='checkFour4'></label>
		                                      	<span>老年</span>
	                                      	</li>
                                      </ul>
                                      <!-- 最近一次消费 -->
                                       <ul >
                                       		<li>
		                                      	<input type='checkbox' name="CheckAll" id='type' class='default'>
		                                      	<label for='type'></label>
		                                      	<span>全选</span>
                                      		</li>
                                      		<li>
		                                      	<input type='checkbox' name="type" value="new" id='checkZero1' class='default'>
		                                      	<label for='checkZero1'></label>
		                                      	<span>新注册会员</span>
	                                      	</li>
                                      		<li>
		                                      	<input type='checkbox' name="type" value="growth" id='checkZero2' class='default'>
		                                      	<label for='checkZero2'></label>
		                                      	<span>成长型会员</span>
	                                      	</li>
                                      		<li>
		                                      	<input type='checkbox' name="type" value="leave" id='checkZero3' class='default'>
		                                      	<label for='checkZero3'></label>
		                                      	<span>流失中会员</span>
	                                      	</li>
                                      		<li>
		                                      	<input type='checkbox' name="type" value="inactivity" id='checkZero4' class='default'>
		                                      	<label for='checkZero4'></label>
		                                      	<span>不活跃会员</span>
	                                      	</li>
                                      </ul>
                                      <ul >
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='coupon' class='default'>
		                                      	<label for='coupon'></label>
		                                      	<span>全选</span>
                                      		</li>
                                          <li>
		                                      	<input type='checkbox' name="coupon" value="usually" id='checkFive1' class='default'>
		                                      	<label for='checkFive1'></label>
		                                      	<span>经常使用优惠券</span>
	                                      	</li>
                                          <li>
		                                      	<input type='checkbox' name="coupon" value="occasionally" id='checkFive2' class='default'>
		                                      	<label for='checkFive2'></label>
		                                      	<span>偶尔使用优惠券</span>
	                                      	</li>
                                          <li>
		                                      	<input type='checkbox' name="coupon" value="never" id='checkFive3' class='default'>
		                                      	<label for='checkFive3'></label>
		                                      	<span>从未使用优惠券</span>
	                                      	</li>
                                      </ul>
                                      
                                      <ul >
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='recentOil' class='default'>
		                                      	<label for='recentOil'></label>
		                                      	<span>全选</span>
                                      		</li>
                                         	<li>
		                                      	<input type='checkbox' name="recentOil" value="high"  id='checkSix1' class='default'>
		                                      	<label for='checkSix1'></label>
		                                      	<span>近期加油频次高</span>
	                                      	</li>
                                         	<li>
		                                      	<input type='checkbox' name="recentOil" value="middle" id='checkSix2' class='default'>
		                                      	<label for='checkSix2'></label>
		                                      	<span>近期加油频次中</span>
	                                      	</li>
                                         	<li>
		                                      	<input type='checkbox' name="recentOil" value="low" id='checkSix3' class='default'>
		                                      	<label for='checkSix3'></label>
		                                      	<span>近期加油频次低</span>
	                                      	</li>
                                      </ul>
                                      
                                      <ul >
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='recentNotOil' class='default'>
		                                      	<label for='recentNotOil'></label>
		                                      	<span>全选</span>
                                      		</li>
                                          <li>
		                                      	<input type='checkbox' name="recentNotOil" value="much" id='checkSeven1' class='default'>
		                                      	<label for='checkSeven1'></label>
		                                      	<span>近期非油消费多</span>
	                                      	</li>
                                          <li>
		                                      	<input type='checkbox' name="recentNotOil" value="middle" id='checkSeven2' class='default'>
		                                      	<label for='checkSeven2'></label>
		                                      	<span>近期非油消费中</span>
	                                      	</li>
                                          <li>
		                                      	<input type='checkbox' name="recentNotOil" value="little" id='checkSeven3' class='default'>
		                                      	<label for='checkSeven3'></label>
		                                      	<span>近期非油消费少</span>
	                                      	</li>
                                      </ul>
                                      <ul >
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='shortOil' class='default'>
		                                      	<label for='shortOil'></label>
		                                      	<span>全选</span>
                                      		</li>
                                          <li>
		                                      	<input type='checkbox' name="shortOil" value="high" id='checkEight1' class='default'>
		                                      	<label for='checkEight1'></label>
		                                      	<span>短期加油频次高</span>
	                                      	</li>
                                          <li>
		                                      	<input type='checkbox' name="shortOil" value="middle" id='checkEight2' class='default'>
		                                      	<label for='checkEight2'></label>
		                                      	<span>短期加油频次中</span>
	                                      	</li>
                                          <li>
		                                      	<input type='checkbox' name="shortOil" value="low" id='checkEight3' class='default'>
		                                      	<label for='checkEight3'></label>
		                                      	<span>短期加油频次低</span>
	                                      	</li>
                                      </ul>
                                      <ul id="stations">
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='station' class='default'>
		                                      	<label for='station'></label>
		                                      	<span>全选</span>
                                      		</li>
                                      </ul>
                                      <ul id="mopTypes">
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='mopType' class='default'>
		                                      	<label for='mopType'></label>
		                                      	<span>全选</span>
                                      		</li>
                                      </ul>
                                      <ul id="oilHobbys">
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='oilName' class='default'>
		                                      	<label for='oilName'></label>
		                                      	<span>全选</span>
                                      		</li>
                                      </ul>
                                      <ul id="shopHobbys">
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='shopName' class='default'>
		                                      	<label for='shopName'></label>
		                                      	<span>全选</span>
                                      		</li>
                                      </ul>
                                      <ul id="activeTag">
                                      		<li>
		                                      	<input type='checkbox' name="CheckAll" id='tagActive' class='default'>
		                                      	<label for='tagActive'></label>
		                                      	<span>全选</span>
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
                                      	  	checkView("loyalty");
                                            checkView("identity");
                                            checkView("gender");
                                            checkView("age");
                                            checkView("type");
                                            checkView("coupon");
                                            checkView("recentOil");
                                            checkView("recentNotOil");
                                            checkView("shortOil");
                                            checkView("station");
                                            checkView("mopType");
                                            checkView("oilName");
                                            checkView("shopName");
                                            checkView("tagActive");
          							})
                                  	</script>
                              <div class="downOperation">
                                <a id="determine" href="javascript:void(0);" onclick='Determine()' class="determine" >确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                                <a  href="javascript:void(0);" onclick="ExportExcel()" class="determine" >导出到Excel</a>
                                <input id="groupName" name="groupName" style="margin-top: 100px ;min-width: 150px" type="text" class="easyui-textbox" data-options="prompt:'请输入查询名称'">
                                <a href="javascript:void(0);" onclick="Collect()" class="determine" >收藏查询</a>
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
  function Collect() {//收藏分组
	  $.ajax({
			type:"POST",
			url:"/sysmanager/vipTag/collect",
			async:false,
			dataType:"JSON",
			data:{"loyalty":jqchk("loyalty"),"identity":jqchk("identity"),"gender":jqchk("gender"),
				"age":jqchk("age"),"type":jqchk("type"),"coupon":jqchk("coupon"),
				"recentOil":jqchk("recentOil"),"recentNotOil":jqchk("recentNotOil"),"shortOil":jqchk("shortOil"),
				"shopName":jqchk("shopName"),"oilName":jqchk("oilName"),"mopType":jqchk("mopType"),
				"station":jqchk("station"),"groupName":$("#groupName").val(),"tagActive":jqchk("tagActive")},
			success:function(message){
				alert(message);
			}
	  });
	}
                                      			$.ajax({
                                              		type:"POST",
                                              		url:"/sysmanager/station/queryAll",
                                              		async:false,
                                              		dataType:"JSON",
                                              		success:function(result){
                                              			$.each(result,function(i,station){
                                              				var option="<li><input name='station' value="+station.id+" type='checkbox' id='checkStation_"+i+"' class='default'><label for='checkStation_"+i+"'></label><span>"+station.name+"</span></li>";
                                              				$("#stations").append(option);
                                              			});
                                              		}
                                              	});
                                      			$.ajax({
                                              		type:"POST",
                                              		url:"/sysmanager/vipTag/queryAllMop",
                                              		async:false,
                                              		dataType:"JSON",
                                              		success:function(result){
                                              			$.each(result,function(i,mop){
                                              				var option="<li><input name='mopType' value="+mop+" type='checkbox' id='checkMop_"+i+"' class='default'><label for='checkMop_"+i+"'></label><span>"+mop+"</span></li>";
                                              				$("#mopTypes").append(option);
                                              			});
                                              		}
                                              	});
                                      			$.ajax({
                                              		type:"POST",
                                              		url:"/sysmanager/vipTag/queryAllOil",
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
                                              		url:"/sysmanager/vipTag/queryAllShop",
                                              		async:false,
                                              		dataType:"JSON",
                                              		success:function(result){
                                              			$.each(result,function(i,shop){
                                              				var option="<li><input name='shopName' value="+shop+" type='checkbox' id='checkShop_"+i+"' class='default'><label for='checkShop_"+i+"'></label><span>"+shop+"</span></li>";
                                              				$("#shopHobbys").append(option);
                                              			});
                                              		}
                                              	});
                                      			$.ajax({
                                              		type:"POST",
                                              		url:"/sysmanager/tagActive/queryAll",
                                              		async:false,
                                              		dataType:"JSON",
                                              		success:function(result){
                                              			$.each(result,function(i,tag){
                                              				var option="<li><input name='tagActive' value="+tag.id+" type='checkbox' id='checkTag_"+i+"' class='default'><label for='checkTag_"+i+"'></label><span>"+tag.name+"</span></li>";
                                              				$("#activeTag").append(option);
                                              			});
                                              		}
                                              	});
                                      </script>
   <script type="text/javascript">
   function ExportExcel() {
	   $("#exportExcel").submit();
   }
   function Determine() {
	   var pager=$dg.datagrid("getPager");
	   pager.pagination('refresh', {
           pageNumber:1,
           pageSize:40,
	   }); 
	   queryvipTag(1,40,jqchk("loyalty"),jqchk("identity"),jqchk("gender"),
				jqchk("age"),jqchk("type"),jqchk("coupon"),
				jqchk("recentOil"),jqchk("recentNotOil"),jqchk("shortOil"),
				jqchk("station"),jqchk("oilName"),jqchk("shopName"),jqchk("mopType"),jqchk("tagActive"));
}
   $(function() {
	query();
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
	   function queryvipTag(pageNumber,pageSize,loyalty,identity,gender,age,type,coupon,recentOil,recentNotOil,
			   shortOil,stations,oilName,shopName,mopType,tagActive){
			$.ajax({
				type:"POST",
				url:"/sysmanager/vipTag/query",
				async:false,
				dataType:"JSON",
				data:{"loyalty":loyalty,"identity":identity,"gender":gender,
					"age":age,"type":type,"coupon":coupon,
					"recentOil":recentOil,"recentNotOil":recentNotOil,"shortOil":shortOil,
					"station":stations,"oilName":oilName,"shopName":shopName,"mopType":mopType,
					"page":pageNumber,"rows":pageSize,"tagActive":tagActive,
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
            url: '/sysmanager/vipTag/query',
            rownumbers:true,  
            fitColumns:true,  
            pagination:true,  
            pageSize: 40,
			pageList: [10,20,30,40,50],
			pageNumber:1,
            columns:[  
                [  
                    {title: "编号", field: "carduser_id", width: 200, align: 'center'},
	                {title: "名字", field: "name", width: 200, align: 'center'},
	                {title: "手机号", field: "mobilePhone", width: 200, align: 'center'}
                ]  
            ]  
        });
        var pager=$dg.datagrid("getPager");
       	pager.pagination({
            onSelectPage:function (pageNo, pageSize) {  
            	$.ajax({
    				type:"POST",
    				url:"/sysmanager/vipTag/query",
    				async:false,
    				dataType:"JSON",
    				data:{"loyalty":jqchk("loyalty"),"identity":jqchk("identity"),"gender":jqchk("gender"),
    					"age":jqchk("age"),"type":jqchk("type"),"coupon":jqchk("coupon"),
    					"recentOil":jqchk("recentOil"),"recentNotOil":jqchk("recentNotOil"),"shortOil":jqchk("shortOil"),
    					"shopName":jqchk("shopName"),"oilName":jqchk("oilName"),"mopType":jqchk("mopType"),
    					"station":jqchk("station"),"tagActive":jqchk("tagActive"),
    					"page":pageNo,"rows":pageSize
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
<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>