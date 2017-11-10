<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/css/themes/icon.css">
    <script src="${pageContext.request.contextPath}/back/easyui/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/form.validator.rules.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/new.js"></script>
    <script>
        $(function () {
            $("#uname").textbox({
                 prompt:'username',
                 required:true,
                 validType:['remote["http://192.168.0.34:8989/sysmanager/admin/checkName","name"]'],
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
            $('#registCode').textbox({
                prompt:'请输入下方验证码',
                required:true,
            });
        });
        function test() {
            $("#registff").form('submit',{
        		success: function(result){
        			if(result=="success"){
        		            $.messager.confirm("提示","您已注册成功，请确认跳转登录页面进行登陆",function(r){
        		                if(r){
        		                	window.location.href="http://192.168.0.34:8989/sysmanager/back/main/admin/login.jsp";
        		                }
        		            });
        				//window.location.href="http://localhost:8989/sysmanager/back/main/admin/login.jsp";
        			}else{
        				alert("注册失败，请检查后重试");
        			}
        		}
            });
        }
        
        
    </script>
</head>
<body>
<div style="margin: auto; margin-top:10%;margin-left: 30%">
<h3>欢迎来到注册页面</h3>
    <form id="registff" class="easyui-form" method="post" data-options="novalidate:false" action="${pageContext.request.contextPath}/admin/regist">
    <input id="uname"   name="name" style="width:300px"  class="easyui-textbox">

    <br>
    <input id="registpwd"  name="password" style="width:300px"><br>
    <input id="rpwd"  name="rpassword" style="width:300px"><br>
    <input id="registCode" name="code" style="width:300px"><br>
	<img alt="" src="/sysmanager/image/code" onclick="this.src='/sysmanager/image/code?'+Math.random()"><br>
        <a class="easyui-linkbutton" onclick="test()">点击注册</a>
        <a class="easyui-linkbutton" href="login.jsp">返回登陆</a>
    </form>
</div>
</body>
</html>
