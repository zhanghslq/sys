<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/css/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/css/themes/icon.css"/>
    <script src="${pageContext.request.contextPath}/back/easyui/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/form.validator.rules.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script>
        $(function(){
            $('#loginName').textbox({
                prompt:'username',
                iconCls:'icon-man',
                iconAlign:'left',
                validType:'username',
                required:true
            });

            $('#loginPassowrd').passwordbox({
                prompt: 'Password',
                showEye: true,
                validType:'password',
                required:true 
            });

            $('#loginCode').textbox({
                prompt: '请输入下方验证码',
                required:true 
            });
        });
        function test() {
            $("#loginff").form('submit',{
            		onSubmit:function(){
            			return $(this).form('validate');
            		},
            		success: function(result){
            			if(result=='success'){
            				window.location.href="http://192.168.0.34:8989/sysmanager/back/platform/index.jsp";
            			}else if (result=='error') {
							alert("账号密码不匹配，请重新输入");
						}
            			else{
            				alert("验证码输入错误");
            			}
            	}
       	 });
       }
    </script>
</head>
	<body>
		<div  style=" margin:auto; width: 300px;height: 200px; margin-top:10%;margin-left: 30%">
			<h3 align="center">欢迎来到登陆页面</h3>
		    <form class="easyui-form" method="post" data-options="novalidate:false"  action="${pageContext.request.contextPath}/admin/login"  id="loginff">
		    <input id="loginName" type="text" style="width:300px" align="center" name="name"/><br>
		    <input id="loginPassowrd" type="text" style="width:300px" align="center" name="password"/><br>
		    <input id="loginCode" type="text" style="width:300px" align="center" name="code"/><br>
		    <img alt="" src="/sysmanager/image/code" onclick="this.src='/sysmanager/image/code?'+Math.random()"><br>
		    	
		    <a class="easyui-linkbutton" onclick="test()">登陆</a>
		        <a class="easyui-linkbutton" href="regist.jsp">转到注册</a>
		    </form>
		</div>
	</body>
</html>