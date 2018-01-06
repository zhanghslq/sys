<%@ page  contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div style="text-align: center;">
    <form id="roleUpdateForm" method="post">
            <div style="margin-top: 70px;">
                <input name="id" type="hidden" value="${param.id}">
            </div>
        <div style="margin-top: 10px;">
          	  角&nbsp;&nbsp;色&nbsp;&nbsp;名:<input  name="name" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	  角色描述:<input name="message" id="message" class="easyui-textbox" data-options="required:true">
        </div>
    </form>
</div>
<script>
    $(function(){
        //构建子页面元素的操作
        $("#roleUpdateForm").form('load','${pageContext.request.contextPath}/role/queryById?id='+'${param.id}');
    });
</script>