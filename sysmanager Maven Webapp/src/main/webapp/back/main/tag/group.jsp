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
        var $dggroup, $dagroup;
        $(function () {
            $dggroup = $("#groupDg");
             $dagroup = $("#groupDa");
            $dggroup.datagrid({
                url: '/sysmanager/tagGroup/queryAll',
                fit:true,
                columns: [[
                    {title: "编号", field: "id", width: 200, align: 'center'},
                    {title: "分组名称", field: "name", width: 200, align: 'center'},
                    {title: "人数", field: "number", width: 200, align: 'center'},
                    {
                        title: "操作", field: "options", width: 200, align: 'center',
                        formatter: function (value, row, index) {
                            return "<a class='del' onClick=\"delgroup('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;" +
                                    "<a class='edit' onClick=\"editgroup('" + row.id + "')\"  href='javascript:;'>导出</a>";
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
                        iconCls: 'icon-save',
                    });
                },
            });
        });
        function editgroup(id) {//导出到Excel
			location.href="/sysmanager/tagGroup/exportById?id="+id;
		}
        //删除的操作
        function delgroup(id){
            $.messager.confirm("提示","您确定要删除吗?",function(r){
                if(r){
                    $.ajax({
                    	url:"/sysmanager/tagGroup/delete",
                    	data:{"id":id},
                    	async:false
                    });
                    $dggroup.datagrid('reload');
                }
            });
        }
    </script>
    </head>
    <body>
    <div  class="easyui-layout" data-options="fit:true" style="width: 100%;height: 80%;min-width: 800px;min-height: 1000px">
        <div data-options="region:'center',">
            <table id="groupDg" ></table>
            <div id="groupDa"></div>
        </div>
    </div>
    </body>
</html>