<%@ page  contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div  style="margin: auto; margin-top:10%;margin-left: 30%">
    <form id="addUserCrmForm" class="easyui-form" method="post" data-options="novalidate:false" action="${pageContext.request.contextPath}/admin/regist">
	    <input id="name"   name="name" style="width:300px"  class="easyui-textbox">
	    <br>
	    <input id="password"  name="password" style="width:300px"><br>
    </form>
</div>
    <script>
        $(function () {
        	 $("#name").textbox({
                 prompt:'接口用户名',
                 required:true,
                 validType:['remote["/sysmanager/userCrm/checkName","name"]'],
                 invalidMessage: "接口用户名已存在"
            });
            $('#password').passwordbox({
                prompt:'请输入密码',
                required:true
            });
        });
    </script>