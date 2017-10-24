<%@ page  contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div style="text-align: center;">
    <form id="posUpdateForm" method="post">
            <div style="margin-top: 70px;">
                <input name="id" type="hidden" value="${param.id}">
            </div>
        <div style="margin-top: 10px;">
          	  类别名称:<input  name="pos_ip" class="easyui-textbox" data-options="required:true"/><br/>
        </div>
    </form>
</div>
<script>
    $(function(){
        //构建子页面元素的操作
        $("#posUpdateForm").form('load','${pageContext.request.contextPath}/pos/queryById?id='+'${param.id}');
    });
</script>