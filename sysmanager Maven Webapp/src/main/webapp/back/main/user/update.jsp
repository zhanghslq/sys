<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/IconExtension.css">
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
    <script src="/sysmanager/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="/sysmanager/back/easyui/js/form.validator.rules.js"></script>
    <script src="/sysmanager/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="/sysmanager/back/echar/echarts.js"></script>
    
	<div style="text-align: center;">
    	<form id="inputForm" method="post">
        <div style="margin-top: 70px;">

            用户名:<input name="username" class="easyui-textbox" value="" data-options="required:true,iconCls:'icon-man'"/><br/>
        </div>
        <div style="margin-top: 10px;">
           请输入原始密码:<input  name="password" class="easyui-passwordbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
            请输入新密码:<input  name="passoword" class="easyui-passwordbox" /><br/>
            
        </div>
       <div style="margin-top: 10px;">
       	请确认新密码:<input  name="passoword" class="easyui-passwordbox" /><br/>
            
        </div>
    </form>
</div>
<script>
    $(function(){
       //构建子页面元素的操作
        /*$("#inputForm").form('load','更新的url?id='+${param.id});*/
    });
</script>

