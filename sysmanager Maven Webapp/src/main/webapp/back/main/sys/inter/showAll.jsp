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
        var $dginter, $dainter;
        $(function () {
            $dginter = $("#interDg");
             $dainter = $("#interDa");
            $dginter.datagrid({
            	url: '/sysmanager/userCrm/queryAll',
                fit:true,
                columns: [[
                    {title: "编号", field: "id", width: 200, align: 'center'},
                    {title: "用户名", field: "name", width: 200, align: 'center'},
                    {
                        title: "操作", field: "options", width: 240, align: 'center',
                        formatter: function (value, row, index) {
                            return "<a class='del' onClick=\"delUserCrm('" + row.name + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;" +
                                    "<a class='edit' onClick=\"editInter('" + row.name + "')\"  href='javascript:;'>修改</a>"+
                                    "<a class='edit' onClick=\"grantPermission('" + row.name + "')\"  href='javascript:;'>接口授权</a>";
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
                toolbar:'#intertb',
            });
        });
        //删除的操作
        function delUserCrm(id){
            $.messager.confirm("提示","您确定要删除吗?",function(r){
                if(r){
                	 $.ajax({
                     	url:"/sysmanager/userCrm/delete",
                     	data:{"id":id},
                     	async:false
                     });
                    $dginter.datagrid('reload');
                }
            });
        }
        //修改的操作
        function editInter(name){
             $dainter.dialog({
                width:600,
                height:300,
                title:"修改",
                iconCls:"icon-man",
                href:'/sysmanager/back/main/sys/inter/update.jsp?name='+name,
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveUpdateInter,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
             
             
        }
        function grantPermission(name) {
        	$dainter.dialog({
                width:400,
                height:450,
                title:"分配权限",
                iconCls:"icon-man",
                href:'/sysmanager/back/main/sys/inter/grant.jsp?name='+name,
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
    				console.log(nodes[i].id)
			}
        	$.ajax({
        		type:"POST",
        		async:false,
        		url:'/sysmanager/userCrm/updatePermission',
        		dataType:"JSON",
        		data:{"uname":$("#uname").attr("value"),"permission":s},
        		success:function(message){
        			$dainter.dialog('close',true);
        			alert(message);
        		}
        	});
		}
        function addUserCrm() {
             $dainter.dialog({
                width:600,
                height:300,
                title:"添加角色",
                iconCls:"icon-man",
                href:'/sysmanager/back/main/sys/inter/add.jsp',
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveAddUserCrm,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        
        }
        //保存添加
        function saveAddUserCrm(){
            $("#addUserCrmForm").form('submit',{
                url:'/sysmanager/userCrm/regist',
                success:function(){
                     $dainter.dialog('close',true);
                     $dginter.datagrid('reload');
                }
            });
        }
        //保存修改
        function saveUpdateInter(){
            $("#userCrmUpdateForm").form('submit',{
                url:'/sysmanager/userCrm/update',
                success:function(){
                    $dainter.dialog('close',true);
                    $dginter.datagrid('reload');
                }
            });
        }
        //关闭对话框
        function closeDa(){
             $dainter.dialog('close',true);
        }
    </script>
    </head>
    <body>
    <div  class="easyui-layout" data-options="fit:true" style="width: 100%;height: 80%;min-width: 800px;min-height: 1000px">
        <div data-options="region:'center',">
            <table id="interDg" ></table>
            <div id="interDa"></div>
            <div id="intertb">
                <a  onclick="addUserCrm()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
            </div>
        </div>
    </div>
    </body>
</html>