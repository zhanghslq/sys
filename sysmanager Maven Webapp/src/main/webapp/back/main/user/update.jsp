<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div style="text-align: center;">
    <form id="inputForm" method="post">
        <div style="margin-top: 70px;">

            用户名:<input name="username" class="easyui-textbox" data-options="required:true,iconCls:'icon-man'"/><br/>
        </div>
        <div style="margin-top: 10px;">
            密码:<input  name="password" class="easyui-passwordbox" data-options="required:true"/><br/>
        </div>
        <div style="margin-top: 10px;">
            法名:<input  name="farrmington" class="easyui-textbox" /><br/>
        </div>
        <div style="margin-top: 10px;">
            真实姓名:<input  name="nickname" class="easyui-textbox" /><br/>
        </div>
        <div style="margin-top: 10px;">
        地址:<input  name="location" class="easyui-textbox" /><br/>
    </div>
        <div style="margin-top: 10px;">
            个性签名:<input  name="description" class="easyui-textbox" /><br/>
        </div>
        <div style="margin-top: 10px;">
            电话:<input  name="phone" class="easyui-textbox" /><br/>
        </div>
        <div style="margin-top: 10px;">
            密码:<input  name="passoword" class="easyui-textbox" /><br/>
        </div>


    </form>
</div>
<script>

    $(function(){
       //构建子页面元素的操作
        console.log("${param.id}");
        /*$("#inputForm").form('load','更新的url?id='+${param.id});*/

    });
</script>

