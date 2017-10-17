<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div style="text-align: center;">
    <form id="tagUpdateForm" method="post">
            <div style="margin-top: 70px;">
                <input name="id" type="hidden" value="${param.id}">
            </div>
        <div style="margin-top: 10px;">
          	  标签名称:<input  name="name" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
    </form>
</div>
<script>
    $(function(){
        //构建子页面元素的操作
        console.log("${param.id}");
        $("#tagUpdateForm").form('load','${pageContext.request.contextPath}/tag/queryById?id='+'${param.id}');
    });
</script>