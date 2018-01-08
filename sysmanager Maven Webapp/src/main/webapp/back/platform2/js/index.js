//获取上月的
function getLastFormatDate() {
	var nowdays = new Date();  
	var year = nowdays.getFullYear();  
	var month = nowdays.getMonth();  
	if(month==0)  
	{  
	    month=12;  
	    year=year-1;  
	}  
	if (month < 10) {  
	    month = "0" + month;  
	}  
	var myDate = new Date(year, month, 0);  
	var lastDay = year + "-" + month + "-" + myDate.getDate()+" "+"24:00";//上个月的最后一天
    return lastDay;
}
function getLastFormatDateOne() {
	var nowdays = new Date();  
	var year = nowdays.getFullYear();  
	var month = nowdays.getMonth();  
	if(month==0)  
	{  
	    month=12;  
	    year=year-1;  
	}  
	if (month < 10) {  
	    month = "0" + month;  
	}  
	var firstDay = year + "-" + month + "-" + "01" + " " + "00" + ":" + "00";
    return firstDay;
}
//获取当前日期的函数
function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	            + " " + date.getHours() + seperator2 + date.getMinutes();
	    return currentdate;
	}
function getNowMonth() {
    var date = new Date();
    var seperator1 = "-";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month;
    return currentdate;
}
	function getNowFormatDateOne() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + "01"
	            + " " + "00" + seperator2 + "00";
	    return currentdate;
	}
//自定义的函数
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
			async:false,
			dataType:"JSON",
			data:{"citys":jqchk("citys")},
			success:function(result){
				$.each(result,function(i,region){
					var option="<li><input name='regions' value="+region+" type='checkbox' id='checktwo_"+i+"' class='default'><label for='checktwo_"+i+"'></label><span>"+region+"</span></li>";
					$("#regions").append(option);
				});
			}
		});
		checkView("regions");
	}
function querySalesAreaBy() {
	$("#sales").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/querySalesAreaBy",
		async:false,
		dataType:"JSON",
		data:{"citys":jqchk("citys"),"regions":jqchk("regions")},
		success:function(result){
			$.each(result,function(i,sale){
				var option="<li><input name='sales' value="+sale+" type='checkbox' id='checksale_"+i+"' class='default'><label for='checksale_"+i+"'></label><span>"+sale+"</span></li>";
				$("#sales").append(option);
			});
		}
	});
	checkView("sales");
}     
function queryGasolineBy() {
	$("#gasolines").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/queryGasolineBy",
		async:false,
		dataType:"JSON",
		data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk(sales)},
		success:function(result){
			$.each(result,function(i,gasoline){
				var option="<li><input name='gasolines' value="+gasoline+" type='checkbox' id='checkgasoline_"+i+"' class='default'><label for='checkgasoline_"+i+"'></label><span>"+gasoline+"</span></li>";
				$("#gasolines").append(option);
			});
		}
	});
	checkView("gasolines");
}     
function queryLocationBy() {
	$("#location").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/queryLocationBy",
		async:false,
		dataType:"JSON",
		data:{"citys":jqchk("citys"),"regions":jqchk("regions"),"sales":jqchk(sales),"gasoline":jqchk("gasolines")},
		success:function(result){
			$.each(result,function(i,location){
				var option="<li><input name='location' value="+location+" type='checkbox' id='checklocation_"+i+"' class='default'><label for='checklocation_"+i+"'></label><span>"+location+"</span></li>";
				$("#location").append(option);
			});
		}
	});
	checkView("location");
}
function queryOpenDateBy() {
	$("#openDate").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/queryOpenDateBy",
		async:false,
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
	checkView("openDate");
}
function queryStationBy() {
	$("#station").empty();
	$.ajax({
		type:"POST",
		url:"/sysmanager/station/queryStationBy",
		async:false,
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
	checkView("station");
}
	$(function(){
		$.ajax({
			type:"GET",
			url:"/sysmanager/station/queryAllCity",
			async:false,
			dataType:"JSON",
			success:function(result){
				$.each(result,function(i,city){
					var option="<li><input name='citys' type='checkbox' value='"+city+"' id='checkone_"+i+"' class='default'><label for='checkone_"+i+"'></label><span>"+city+"</span></li>";
					$("#citys").append(option);
				});
			}
		});
		checkView("citys");
	});
	


////////////////////
function checkView(name) {
        $("ul li input[name='"+name+"']").click(function() {
  	var value = $(this).parent().find("span").html();//这是获取的节点内容
  	var id = $(this).attr("id");//这是获取的节点内容   
  	
  	if (!$(this).is(':checked')) {
  		$("#tagContent li[ids='" + id + "']").remove();
  	} else {
  		$("#tagContent").append("<li ids="+id+"><span>"+value+"<em></em></span></li>");
  	};
    	});
    	$("#tagContent").delegate("li","click", function(){
    		var id = $(this).attr("ids");
    		$(this).remove();
    		$("ul li input[id='" + id + "']").prop("checked",false);
    	});
  }
/*左侧效果*/
function navLeft(){
  //nav 固定高度
	var bodyHeight=$(".contentLeft").height(),
		listHeight=$(".logoMain").height()+$(".footermenu").height()+$(".shrink").height();
	$(".downMenu").css("height",bodyHeight-listHeight-30);
    // nav收缩展开
    $('.menuItem>a').on('click',function(){
        if (!$('.contentLeft').hasClass('menuMini')) {
            if ($(this).next().css('display') == "none") {
                //展开未展开
                $('.menuItem').children('ul').slideUp(300);
                $(this).next('ul').slideDown(300);
                $(this).parent('li').addClass('Cur').siblings('li').removeClass('Cur');
            }else{
                //收缩已展开
                $(this).next('ul').slideUp(300);
                $('.menuItem.Cur').removeClass('Cur');
            }
        }
        $('.menuItem ul li').bind('click', function(){
            $(this).addClass('downCur').siblings().removeClass('downCur');
        });
    });
    //nav-mini切换
    $('#mini').on('click',function(){
        if (!$('.contentLeft').hasClass('menuMini')) {
                $('.menuItem a span,.logoMain .logoText,.footermenu a span').hide();
            $('.contentLeft').animate({width:"70px"},100,function(){
                $(".shrink i").addClass('narrow');
                $('.menuItem').children('ul').hide();
                $('.contentLeft').addClass('menuMini');
                $('.content .contentRight').css("paddingLeft","100px");
                parent.main.cols='70,*';
            });
        }else{
              $('.contentLeft').animate({width:"257px"},100,function(){
                $(".shrink i").removeClass('narrow');
                $('.contentLeft').removeClass('menuMini');
                $('.menuItem a span,.logoMain .logoText,.footermenu a span').show();
                $('.content .contentRight').css("paddingLeft","290px");
                parent.main.cols='300,*';
              });
        }
    });
    //底部按钮
    $(".footermenu li").on("click",function(){
        $('.downMenu li').removeClass('Cur');
        $('.downMenu .menuItem ul').hide();
        $(this).addClass('fotCur').siblings().removeClass('fotCur');
    });
    $(".downMenu li").on("click",function(){
        $(".footermenu li").removeClass('fotCur');
    });
}
/*右侧效果*/
function downTab(){
  $(".tabNav li").each(function(i){
    $(this).on("click",function(){
       $(this).addClass("on").siblings().removeClass("on");$(".rightDownMain .downDetails:eq("+i+")").show().siblings().hide();
    });  
  });
}
//右侧下拉
function rightDown(){
    $(".selemenu").click(function(){
        $(this).next().slideToggle();
        $(this).parents().siblings().find(".seleContent").slideUp();
    });
    $(".downCont .downNav a").each(function(i){
        $(this).on("click",function(){
           $(this).addClass("titleCur").siblings().removeClass("titleCur");$(".downContInfo ul:eq("+i+")").show().siblings().hide();
        });
    });
    $(".crowdNav a").on("click",function(){
        $(".crowd").slideUp();
    });
    $(function(){
        $(document).not($(".selectbox")).click(function(){
            $(".seleContent").slideUp();
         });
         $(".selectbox").click(function(event){
            event.stopPropagation();
        });
        $(".downOperation .determine").click(function(){
            $(".seleContent").slideUp();
         });
         $(".downOperation .cancel").click(function(){
            $(".seleContent").slideUp();
        });
    });
}

