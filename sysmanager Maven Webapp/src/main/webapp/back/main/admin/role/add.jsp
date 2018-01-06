<%@ page  contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div  style="margin: auto; margin-top:10%;margin-left: 30%">
    <form id="addRoleForm" class="easyui-form" method="post" data-options="novalidate:false" action="${pageContext.request.contextPath}/admin/regist">
	    <input id="roleName"   name="name" style="width:300px"  class="easyui-textbox">
	    <br>
	    <input id="message"  name="message" style="width:300px"><br>
    </form>
</div>
    <script>
        $(function () {
            $("#roleName").textbox({
                 prompt:'角色名',
                 required:true,
                 validType:['remote["/sysmanager/role/checkName","name"]'],
                 invalidMessage: "角色名已存在"
            });
            $('#message').textbox({
                prompt:'角色描述',
                required:true
            });
            
        });
    </script>