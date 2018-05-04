<%@ page  contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div style="text-align: center;">
    <form id="userCrmUpdateForm" method="post">
            <div style="margin-top: 70px;">
                <input name="name" id="uname" type="hidden" value="${param.name}">
            </div>
	        <div style="margin-top: 10px;">
	          	  密码:<input name="password" id="password" class="easyui-textbox" data-options="required:true">
	        </div>
    </form>
</div>
<script>
    $(function(){
        //构建子页面元素的操作
    	$("#userCrmUpdateForm").form('load','${pageContext.request.contextPath}/userCrm/queryByName?name='+'${param.name}');
    });
</script>