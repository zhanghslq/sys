<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1 align='center'>欢迎来到首页</h1>

 <select id="cc"  class="easyui-combobox" name="555" style="width:200px;" data-options="required:true,editable:false">  
        <option value="AL">Alabama</option>  
        <option value="AK">Alaska</option>  
        <option value="CO">Colorado</option>  
        <option value="CT">Connecticut</option>  
        <option value="DE">Delaware</option>  
        <option value="FL">Florida</option>  
        <option value="GA">Georgia</option>  
        <option value="HI">Hawaii</option>  
        <option value="ID">Idaho</option>  
        <option value="NV">Nevada</option>  
        <option value="NH">New Hampshire</option>  
        <option value="WY">Wyoming</option>
    </select>  
    
    
    <script type="text/javascript">
    $(function() {
    	var tagName="";
    	var categoryName="";
    	$.ajax({
    		type:"GET",
    		url:'${pageContext.request.contextPath}/station/queryById?id='+'50006',
    		dataType:"JSON",
    		success:function(station){
    			if(typeof(station.category)!="undefined"){
    				categoryName=station.category.name;
    			}
    			if(typeof(station.tag)!="undefined"){
    				tagName=station.tag.name;
    			}
    		}
    	});
    	
    	
    	
    	$("#cc").combobox({
    		select:"Idaho"
    	});
	});
    
    </script>
</body>
	
</html>