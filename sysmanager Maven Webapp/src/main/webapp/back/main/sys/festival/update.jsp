<%@ page  contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div style="text-align: center;">
    <form id="festivalUpdateForm" method="post">
        <div style="margin-top: 70px;">
            <input name="id" type="hidden" value="${param.id}">
        </div>
       	<div style="margin-top: 10px;">
           	节假日名称：<input name="name"  class="easyui-textbox" data-options="required:true"/>
        </div>
        <div style="margin-top: 10px;">
           	节假日开始时间：<input name="start" class="easyui-datetimebox" data-options="required:true,editable:false"/>
        </div>
        <div style="margin-top: 10px;">
           	节假日结束时间：<input name="end" class="easyui-datetimebox" data-options="required:true,editable:false"/>
        </div>
        <div style="margin-top: 10px;">
           	节假日所属年份：<input name="year" class="easyui-textbox" data-options="required:true"/>
        </div>
    </form>
</div>
<script>
    $(function(){
        //构建子页面元素的操作
        $("#festivalUpdateForm").form('load','${pageContext.request.contextPath}/festival/queryById?id='+'${param.id}');
    });
</script>