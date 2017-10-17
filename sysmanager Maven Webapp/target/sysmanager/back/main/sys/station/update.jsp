<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"  language="java" %>
<div style="text-align: center;">
	<form id="staUpdateForm" method="post">
        <div style="margin-top: 70px;">
             <input name="id" type="hidden" value="${param.id}">
        </div>
        <div style="margin-top: 10px;">
          	  油站名称:<input  name="name" readonly="readonly" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	  所在城市:<input  name="city" readonly="readonly" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
            	类别:<select id="category"   name="category.id" style="width:200px;" ></select><br/>
        </div>
      标签:  <div style="margin-top: 10px;" id="tag">
            	
        </div>
    </form>
</div>
<script>

//javascript中遍历

    $(function(){
    	$("#staUpdateForm").form('load','${pageContext.request.contextPath}/station/queryById?id='+'${param.id}');
    	$("#tag").empty();
    	
    	$.ajax({
			type:"GET",
			url:"/sysmanager/tag/queryAll",
			dataType:"JSON",
			success:function(result){
				$.each(result,function(i,tag){//这里不能使用下拉框了，改为一对多的复选框
					if(typeof(result)!='undefined'){
						var option = "<input name='tags' type='checkbox' value="+tag.id+">"+tag.name;
						$("#tag").append(option);
					}
				});//遍历完后
				
			}
		});
    	$("#category").empty();
    	$.ajax({
			type:"GET",
			url:"/sysmanager/category/queryAll",
			dataType:"JSON",
			success:function(result){
				$.each(result,function(i,category){
					var option = $("<option></option>").text(category.name).val(category.id);
					$("#category").append(option);
				});//遍历完之后
				$("#category").combobox({
				    textField:'text',   
				    required:true,
				    editable:false
				});  

			}
		});
        //准备获取tag的默认值
    	$.ajax({
    		type:"GET",
    		url:'${pageContext.request.contextPath}/station/queryById?id='+'${param.id}',
    		dataType:"JSON",
    		success:function(station){
    			if(typeof(station.category)!="undefined"){
    				$("#category").combobox('select',station.category.id);
    			}
    			//tag有待考虑，这是一个数组
    			if(typeof(station.tags)!="undefined"){//这是遍历一个油站的tag，然后选中
    				$.each(station.tags,function(i,tag){
    					$("input:checkbox[value="+tag.id+"]").prop('checked','true');
    				});
    				
    			}
    		}//这是成功之后执行的区域
    	});
    	//结束
    });
</script>
