<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
				 readonly:true,
            });
            $('#uname').textbox('setValue',"<shiro:principal/>");
            $('#pwd').passwordbox({
                prompt:'请输入原始密码',
                required:true
            });
            $('#updatepwd').passwordbox({
                prompt:'请输入新密码',
                required:true
            });
            $('#rpwd').passwordbox({
                prompt:'请确认密码',
                required:true,
                validType:"equalTo['#updatepwd']",
            	invalidMessage:"两次输入密码不一致"
            });
            
        });
        function test() {
            $("#updateff").form('submit',{
        		success: function(result){
        			if(result=="success"){
        		            $.messager.confirm("提示","您已修改成功，请点击确定返回主页面",function(r){
        		                if(r){
        		                	parent.location.reload();
        		                }
        		            });
        			}else{
        				alert("修改失败，请检查后重试");
        			}
        		}
            });
        }
        
        
    </script>
</head>
<body>
<div style="margin: auto; margin-top:10%;margin-left: 30%">
<h3>修改密码</h3>
    <form id="updateff" class="easyui-form" method="post" data-options="novalidate:false" action="${pageContext.request.contextPath}/admin/update">
    <input id="uname"    name="name" style="width:300px"  class="easyui-textbox">

    <br>
    <input id="pwd"  name="oldpassword" style="width:300px"><br>
    <input id="updatepwd"  name="newpassword" style="width:300px"><br>
    <input id="rpwd"  name="rpassword" style="width:300px"><br>
        <a class="easyui-linkbutton" onclick="test()">确定修改</a>
        
    </form>
</div>
</body>
</html>
