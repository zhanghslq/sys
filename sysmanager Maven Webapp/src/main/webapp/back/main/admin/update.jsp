<%@ page  contentType="text/html;charset=UTF-8" isELIgnored="false"  language="java" %>
<div style="text-align: center;">
    <form id="userUpdateForm" method="post">
            <div style="margin-top: 70px;">
                <input name="id" type="hidden" value="${param.id}">
            </div>
        <div style="margin-top: 10px;">
          	  用户名:<input  name="name" class="easyui-textbox" data-options="required:true,editable:false"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	  角色:<select name="role"   id="role" ></select>
        </div>
    </form>
</div>
<script>
    $(function(){
        //构建子页面元素的操作
    	$("#userUpdateForm").form('load','${pageContext.request.contextPath}/admin/queryById?id='+'${param.id}');
    	$.ajax({
			type:"GET",
			url:"/sysmanager/role/queryAll",//查询所有角色
			async:false,
			dataType:"JSON",
			success:function(result){
				$("#role").empty();
				$.each(result,function(i,role){
					var option = $("<option></option>").text(role.name).val(role.id);
					$("#role").append(option);
				}); ///遍历完之后
				$("#role").combobox({
				    textField:'text',   
				    required:true,
				    editable:false
				});
				toolbar:'#role';
			}
		});
        //准备获取tag的默认值
    	$.ajax({
    		type:"GET",
    		url:'${pageContext.request.contextPath}/role/queryByUserId?id='+'${param.id}',
    		dataType:"JSON",
    		async:false,
    		success:function(role){
    			if(typeof(role.id)!="undefined"){
    				$("#role").combobox('select',role.id);
    			}
    		}//这是成功之后执行的区域
    	}); 
    });
</script>