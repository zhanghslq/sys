<%@ page  contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div  style="margin: auto; margin-top:10%;margin-left: 30%">
    <form id="registff" class="easyui-form" method="post" data-options="novalidate:false" action="${pageContext.request.contextPath}/admin/regist">
	    <input id="uname"   name="name" style="width:300px"  class="easyui-textbox">
	    <br>
	    <input id="registpwd"  name="password" style="width:300px"><br>
	    <input id="rpwd"  name="rpassword" style="width:300px"><br>
    </form>
</div>
    <script>
        $(function () {
            $("#uname").textbox({
                 prompt:'username',
                 required:true,
                 validType:['remote["/sysmanager/admin/checkName","name"]'],
                 invalidMessage: "用户名已存在"
            });
            $('#registpwd').passwordbox({
                prompt:'password',
                required:true
            });
            $('#rpwd').passwordbox({
                prompt:'请再次输入密码',
                required:true,
                validType:"equalTo['#registpwd']",
            	invalidMessage:"两次输入密码不一致"
            });
        });
    </script>