<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"  language="java" %>
<div style="text-align: center;">
	<form id="staUpdateForm" method="post">
        <div style="margin-top: 70px;">
             <input name="id" type="hidden" value="${param.id}">
        </div>
        <div style="margin-top: 10px;">
          	  油&nbsp;&nbsp;站&nbsp;&nbsp;名&nbsp;&nbsp;称:<input  name="name"  class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	  所&nbsp;&nbsp;在&nbsp;&nbsp;城&nbsp;&nbsp;市:<input  name="city"  class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	  汽油商圈类型:<input  name="gasoline"  class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	柴油商圈类型:<input  name="diesel"  class="easyui-textbox" /><br/>
        </div>
        <div style="margin-top: 10px;">
          	&nbsp;&nbsp;&nbsp;&nbsp;  位&nbsp;&nbsp;&nbsp;&nbsp;置&nbsp;&nbsp;&nbsp;&nbsp;:<input  name="location"  class="easyui-textbox" /><br/>
        </div>
        <div style="margin-top: 10px;">
          	 开&nbsp;&nbsp;业&nbsp;&nbsp;日&nbsp;&nbsp;期:<input  name="openDate"  class="easyui-datebox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	&nbsp;&nbsp;&nbsp;销&nbsp;&nbsp;售&nbsp;&nbsp;区&nbsp;&nbsp;:<input  name="salesArea"  class="easyui-textbox"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	 行&nbsp;&nbsp;政&nbsp;&nbsp;区&nbsp;&nbsp;域:<input  name="administraiveRegion"  class="easyui-textbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
          	 油&nbsp;&nbsp;站&nbsp;&nbsp;类&nbsp;&nbsp;型:<input  name="type"  class="easyui-textbox" data-options="required:true"/><br/>
        </div>
    </form>
</div>
<script>
    $(function(){
    	$("#staUpdateForm").form('load','${pageContext.request.contextPath}/station/queryById?id='+'${param.id}');
    });
</script>
