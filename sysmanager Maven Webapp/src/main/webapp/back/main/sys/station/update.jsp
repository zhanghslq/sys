
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<form id="staUpdateForm" method="post">
            <div style="margin-top: 70px;">
                <input name="id" type="hidden" value="${param.id}">
            </div>
        <div style="margin-top: 10px;">
            描述:<input  name="description" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
    </form>
<script>

    $(function(){
        //构建子页面元素的操作
        console.log("${param.id}");
        $("#inputForm").form('load','${pageContext.request.contextPath}/image/queryById?id='+'${param.id}');
    });
</script>
