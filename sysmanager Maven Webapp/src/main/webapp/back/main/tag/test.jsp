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
                                  </div>
                                  <div class="downContInfo">
                                      <ul style="display: block;" id="citys">
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
	                                      	<input type='checkbox' name="loyalty" value="6" id='checkOne6' class='default'>
	                                      	<label for='checkOne6'></label>
	                                      	<span>沉睡顾客</span>
                                      	</li>
                                      </ul>
                                      <ul >
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
	                                      	<input type='checkbox' id='checkTwo6' value="50" class='default'>
	                                      	<label for='checkTwo6'></label>
	                                      	<span>壳牌中国员工</span>
                                      	</li>
                                      </ul>
                                      
                                      <ul >
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
                                  </div>
                              </div>
                              <div class="downOperation">
                                <a href="javascript:void(0);" class="determine">确定</a>
                                <a href="javascript:void(0);" class="cancel">取消</a>
                              </div>
                           </div>
                       </div>
                      
                   </div>
               </div>
               
           </div>
       </div>
    </div>
<!-- ///////////////////////// -->		
   <script type="text/javascript">
	   function jqchk(chkName){ //jquery获取复选框值 
			var chk_value =[]; 
			$("input[name='"+chkName+"']:checked").each(function(){ 
				chk_value.push($(this).val()); 
			}); 
			return chk_value;
		}
	   function querybaseOil(){
			$.ajax({
				type:"post",
				url:"/sysmanager/vipTag/query",
				dataType:"JSON",
				data:{"loyalty":jqchk("loyalty"),"identity":jqchk("identity"),"gender":jqchk("gender"),
					"age":jqchk("age"),"type":jqchk("type"),"coupon":jqchk("coupon"),
					"recentOil":jqchk("recentOil"),"recentNotOil":jqchk("recentNotOil"),"shortOil":jqchk("shortOil"),
					},
				success:function(map){
					return map;
				}
			});
	   }
   </script>
    <script type="text/javascript">  
        // 表格数据源  
        var data =[];  
        // 用代码造30条数据  
        for (var i = 1; i < 31; ++i) {  
            data.push({  
                "id":i,  
                "name":"Student" + i  
            });
        }  
  
        $(function () {  
  
            $("#dd").datagrid({  
                title:"测试本地分页",  
                rownumbers:true,  
                fitColumns:true,  
                pagination:true,  
                data:data.slice(0,10),  
                columns:[  
                    [  
                        {field:'id', align:"center", title:"编号", width:100},  
                        {field:'name', align:"center", title:"姓名", width:100}  
                    ]  
                ]  
            });  
  
            var pager = $("#dd").datagrid("getPager");  
            pager.pagination({
                total:data.length,
                onSelectPage:function (pageNo, pageSize) {  
                    var start = (pageNo - 1) * pageSize;
                    var end = start + pageSize;  
                    $("#dd").datagrid("loadData", data.slice(start, end));  
                    pager.pagination('refresh', {
                        total:data.length,  
                        pageNumber:pageNo  
                    });  
                }  
            });  
  
        });  
  
    </script>  
 
<div id="dd"></div>  

<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</body>
</html>