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
        var $dgActive, $daActive;
        $(function () {
            $dgActive = $("#activeDg");
             $daActive = $("#activeDa");
            $dgActive.datagrid({
                url: '/sysmanager/tagActive/queryAll',
                fit:true,
                columns: [[
                    {title: "编号", field: "id", width: 200, align: 'center'},
                    {title: "标签名", field: "name", width: 200, align: 'center'},
                    {title: "描述", field: "description", width: 200, align: 'center'},
                    {
                        title: "操作", field: "options", width: 200, align: 'center',
                        formatter: function (value, row, index) {
                            return "<a class='del' onClick=\"delActive('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;" +
                                    "<a class='edit' onClick=\"editActive('" + row.id + "')\"  href='javascript:;'>导入数据</a>";
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
                toolbar:'#activetb',
            });
        });
        //删除的操作
        function delActive(id){
            $.messager.confirm("提示","您确定要删除吗?",function(r){
                if(r){
                	 $.ajax({
                     	url:"/sysmanager/tagActive/delete",
                     	data:{"id":id},
                     	async:false
                     });
                    $dgActive.datagrid('reload');
                }
            });
        }
        //修改的操作
        function editActive(id){
             $daActive.dialog({
                width:600,
                height:300,
                title:"上传会员信息",
                iconCls:"icon-man",
                href:'/sysmanager/back/main/tag/active/update.jsp?id='+id,
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveUpdateActive,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        }
        function addActive() {
             $daActive.dialog({
                width:600,
                height:300,
                title:"添加活动标签",
                iconCls:"icon-man",
                href:'/sysmanager/back/main/tag/active/add.jsp',
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveAddActive,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        }
        //保存添加
        function saveAddActive(){
            $("#addActiveForm").form('submit',{
                url:'/sysmanager/tagActive/insert',
                success:function(){
                     $daActive.dialog('close',true);
                     $dgActive.datagrid('reload');
                }
            });
        }
        //保存修改
        function saveUpdateActive(){
            $("#activeUpdateForm").form('submit',{
                url:'/sysmanager/tagActive/add',
                success:function(){
                     $daActive.dialog('close',true);
                    $dgActive.datagrid('reload');
                }
            });
        }
        //关闭对话框
        function closeDa(){
             $daActive.dialog('close',true);
        }
    </script>
    </head>
    <body>
    <div  class="easyui-layout" data-options="fit:true" style="width: 100%;height: 80%;min-width: 800px;min-height: 1000px">
        <div data-options="region:'center',">
            <table id="activeDg" ></table>
            <div id="activeDa"></div>
            <div id="activetb">
                <a  onclick="addActive()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
            </div>
        </div>
    </div>
    </body>
</html>