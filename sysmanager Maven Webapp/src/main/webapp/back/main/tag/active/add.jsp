<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<div style="text-align: center;">
    <form id="addActiveForm" method="post">
        <div style="margin-top: 70px;">
           	标签名字：<input name="name" class="easyui-textbox" data-options="required:true" style="width: 180px"/>
        </div>
        <div style="margin-top: 10px;">
           	标签描述：<input name="description" class="easyui-textbox" style="width: 180px"/>
        </div>
        <div style="margin-top: 10px;">
           	选择区域：<select  class="easyui-combobox" name="area" style="width: 180px">   
					    <option value="BJSHELL">北京</option>   
					    <option value="CDSHELL">承德</option>   
					</select>  
        </div>
    </form>
</div>