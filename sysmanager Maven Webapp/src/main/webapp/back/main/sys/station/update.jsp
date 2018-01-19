<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"  language="java" %>
<div style="text-align: center;">
	<form id="staUpdateForm" method="post">
        <div style="margin-top: 70px;">
             <input name="id" type="hidden" value="${param.id}">
        </div>
        <div style="margin-top: 10px;">
          	  油&nbsp;&nbsp;站&nbsp;&nbsp;名&nbsp;&nbsp;称:<input  name="name"  class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	  所&nbsp;&nbsp;在&nbsp;&nbsp;城&nbsp;&nbsp;市:<input  name="city"  class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	  汽油商圈类型:<input  name="gasoline"  class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	柴油商圈类型:<input  name="diesel"  class="easyui-textbox" /><br/>
        </div>
        <div style="margin-top: 10px;">
          	&nbsp;&nbsp;&nbsp;&nbsp;  位&nbsp;&nbsp;&nbsp;&nbsp;置&nbsp;&nbsp;&nbsp;&nbsp;:<input  name="location"  class="easyui-textbox" /><br/>
        </div>
        <div style="margin-top: 10px;">
          	 开&nbsp;&nbsp;业&nbsp;&nbsp;日&nbsp;&nbsp;期:<input  name="openDate"  class="easyui-datebox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	&nbsp;&nbsp;&nbsp;销&nbsp;&nbsp;售&nbsp;&nbsp;区&nbsp;&nbsp;:<input  name="salesArea"  class="easyui-textbox"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	 行&nbsp;&nbsp;政&nbsp;&nbsp;区&nbsp;&nbsp;域:<input  name="administraiveRegion"  class="easyui-textbox" data-options="required:true"/><br/>
        </div>
    </form>
</div>
<script>

//javascript中遍历
    $(function(){
    	
   /*  	$("#tag").empty(); */
    	/* $.ajax({
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
    	}); */
    	//结束
    });
</script>
