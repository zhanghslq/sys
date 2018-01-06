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
        var $dgrole, $darole;
        $(function () {
        	$.ajaxSetup({   
                async : false  
            });
            $dgrole = $("#roleDg");
             $darole = $("#roleDa");
            $dgrole.datagrid({
                url: '/sysmanager/role/queryAll',
                fit:true,
                columns: [[
                    {title: "编号", field: "id", width: 200, align: 'center'},
                    {title: "角色名", field: "name", width: 200, align: 'center'},
                    {title: "角色描述", field: "message", width: 200, align: 'center'},
                    {
                        title: "操作", field: "options", width: 200, align: 'center',
                        formatter: function (value, row, index) {
                            return "<a class='del' onClick=\"delRole('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;" +
                                    "<a class='edit' onClick=\"editRole('" + row.id + "')\"  href='javascript:;'>修改</a>"+
                                    "<a class='edit' onClick=\"grantPermission('" + row.id + "')\"  href='javascript:;'>分配权限</a>";
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
                toolbar:'#roletb',
            });
        });
        //删除的操作
        function delRole(id){
            $.messager.confirm("提示","您确定要删除吗?",function(r){
                if(r){
                    $.post("/sysmanager/role/delete",{"id":id});
                    $dgrole.datagrid('reload');
                }
            });
        }
        //修改的操作
        function editRole(id){
             $darole.dialog({
                width:600,
                height:300,
                title:"修改角色",
                iconCls:"icon-man",
                href:'/sysmanager/back/main/admin/role/update.jsp?id='+id,
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveUpdateRole,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        }
        function grantPermission(id) {
        	$darole.dialog({
                width:400,
                height:450,
                title:"分配角色权限",
                iconCls:"icon-man",
                href:'/sysmanager/back/main/admin/role/grant.jsp?id='+id,
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
        		url:'/sysmanager/role/grantPermission',
        		dataType:"JSON",
        		data:{"rid":$("#roleId").attr("value"),"pid":s},
        		success:function(message){
        			$darole.dialog('close',true);
        			alert(message);
        		}
        	});
		}
        function addRole() {
             $darole.dialog({
                width:600,
                height:300,
                title:"添加角色",
                iconCls:"icon-man",
                href:'/sysmanager/back/main/admin/role/add.jsp',
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveAddRole,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        }
        //保存添加
        function saveAddRole(){
            $("#addRoleForm").form('submit',{
                url:'/sysmanager/role/insert',
                success:function(){
                     $darole.dialog('close',true);
                     $dgrole.datagrid('reload');
                }
            });
        }
        //保存修改
        function saveUpdateRole(){
            $("#roleUpdateForm").form('submit',{
                url:'/sysmanager/role/update',
                success:function(){
                    $darole.dialog('close',true);
                    $dgrole.datagrid('reload');
                }
            });
        }
        //关闭对话框
        function closeDa(){
             $darole.dialog('close',true);
        }
    </script>
    </head>
    <body>
    <div  class="easyui-layout" data-options="fit:true" style="width: 100%;height: 80%;min-width: 800px;min-height: 1000px">
        <div data-options="region:'center',">
            <table id="roleDg" ></table>
            <div id="roleDa"></div>
            <div id="roletb">
                <a  onclick="addRole()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
            </div>
        </div>
    </div>
    </body>
</html>