<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/IconExtension.css">
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
    <script src="/sysmanager/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="/sysmanager/back/easyui/js/form.validator.rules.js"></script>
    <script src="/sysmanager/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script>
        var $dguser, $dauser;
        $(function () {
            $dguser = $("#userDg");
             $dauser = $("#userDa");
            $dguser.datagrid({
                url: '/sysmanager/admin/queryAll',
                fit:true,
                columns: [[
                    {title: "编号", field: "id", width: 200, align: 'center'},
                    {title: "用户名", field: "name", width: 200, align: 'center'},
                    {
                        title: "操作", field: "options", width: 240, align: 'center',
                        formatter: function (value, row, index) {
                            return "<a class='del' onClick=\"delUser('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;" +
                                    "<a class='edit' onClick=\"editUser('" + row.id + "')\"  href='javascript:;'>修改</a>"+
                                    "<a class='edit' onClick=\"grantStation('" + row.name + "')\"  href='javascript:;'>油站授权</a>";
                        }
                    }
                ]],
                onLoadSuccess: function (data) {
                    $(".del").linkbutton({
                        plain: true,
                        iconCls: 'icon-remove',
                    });
                    $(".edit").linkbutton({
                        plain: true,
                        iconCls: 'icon-edit',
                    });
                },
                toolbar:'#Usertb',
            });
        });
        //删除的操作
        function delUser(id){
            $.messager.confirm("提示","您确定要删除吗?",function(r){
                if(r){
                	 $.ajax({
                     	url:"/sysmanager/admin/delete",
                     	data:{"id":id},
                     	async:false
                     });
                    $dguser.datagrid('reload');
                }
            });
        }
        //修改的操作
        function editUser(id){
             $dauser.dialog({
                width:600,
                height:300,
                title:"修改用户角色",
                iconCls:"icon-man",
                href:'/sysmanager/back/main/admin/update.jsp?id='+id,
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveUpdateUser,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
             
        }
        function grantStation(name) {
        	$dauser.dialog({
                width:300,
                height:500,
                title:"油站授权",
                iconCls:"icon-man",
                href:'/sysmanager/back/main/admin/grant.jsp?name='+name,
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveGrant,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
		}
        function saveGrant() {
        	var nodes = $('#tree').tree('getChecked');//获取:checked的结点.
			var s = '';
			for(var i=0; i<nodes.length; i++){
    				if (s != '') s += ',';
    				s += nodes[i].id;//例如:菜单的menuID
			}
        	$.ajax({
        		type:"POST",
        		async:false,
        		url:'/sysmanager/station/updateGrant',
        		dataType:"JSON",
        		data:{"uname":$("#uname").attr("value"),"sid":s},
        		success:function(message){
        			$dauser.dialog('close',true);
        			alert(message);
        		}
        	});
		}
        function addUser() {
             $dauser.dialog({
                width:600,
                height:300,
                title:"添加用户",
                iconCls:"icon-man",
                href:'/sysmanager/back/main/admin/regist.jsp',
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveAddUser,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
             
            
        }
        //保存添加
        function saveAddUser(){
            $("#registff").form('submit',{
                url:'/sysmanager/admin/regist',
                success:function(){
                     $dauser.dialog('close',true);
                     $dguser.datagrid('reload');
                }
            });
        }
        //保存修改
        function saveUpdateUser(){
            $("#userUpdateForm").form('submit',{
                url:'/sysmanager/admin/updateRole',
                success:function(){
                    $dauser.dialog('close',true);
                    $dguser.datagrid('reload');
                }
            });
        }
        //关闭对话框
        function closeDa(){
             $dauser.dialog('close',true);
        }
    </script>
    </head>
    <body>
    <div  class="easyui-layout" data-options="fit:true" style="width: 100%;height: 80%;min-width: 800px;min-height: 1000px">
        <div data-options="region:'center',">
            <table id="userDg" ></table>
            <div id="userDa"></div>
            <div id="Usertb">
                <a  onclick="addUser()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
            </div>
        </div>
    </div>
    </body>
</html>