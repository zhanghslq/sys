<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<div style="text-align: center;">
    <form id="addFestivalForm" method="post">
        <div style="margin-top: 70px;">
           	节假日名称：<input name="name" class="easyui-textbox" data-options="required:true"/>
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