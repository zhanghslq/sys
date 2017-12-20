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
    	  $("ul li input[name='CheckAll']").each(function() {
			$(this).click(function () {
				var id=$(this).attr("id");
				CheckAll(id);
			});
		});
	});
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
	});
