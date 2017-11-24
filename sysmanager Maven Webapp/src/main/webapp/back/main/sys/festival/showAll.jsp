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
    <script src="/sysmanager/back/echar/echarts.js"></script>
    <script>
        var $dgfestival, $dafestival;
        $(function () {
            $dgfestival = $("#festivalDg");
             $dafestival = $("#festivalDa");
            $dgfestival.datagrid({
                url: '/sysmanager/festival/queryAll',
                fit:true,
                columns: [[
                    {title: "编号", field: "id", width: 200, align: 'center'},
                    {title: "节日名称", field: "name", width: 200, align: 'center'},
                    {title: "开始时间", field: "start", width: 200, align: 'center',
                    	 formatter:function(value,row,index){  
                             var unixTimestamp = new Date(value);  
                             return unixTimestamp.toLocaleString(); 
                            } 
                    },
                    {title: "结束时间", field: "end", width: 200, align: 'center',
                   	 formatter:function(value,row,index){  
                            var unixTimestamp = new Date(value);  
                            return unixTimestamp.toLocaleString(); 
                           } 
                   },
                   {title: "所属年份", field: "year", width: 200, align: 'center',},
                    {
                        title: "操作", field: "options", width: 200, align: 'center',
                        formatter: function (value, row, index) {
                            return "<a class='del' onClick=\"delFestival('" + row.id + "')\"'>删除</a>&nbsp;&nbsp;" +
                                    "<a class='edit' onClick=\"editFestival('" + row.id + "')\"'>修改</a>";
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
                toolbar:'#festivaltb',
            });
        });
        //删除的操作
        function delFestival(id){
            $.messager.confirm("提示","您确定要删除吗?",function(r){
                if(r){
                    $.post("/sysmanager/festival/delete",{"id":id});
                    $dgfestival.datagrid('reload');
                }
            });
        }
        //修改的操作
        function editFestival(id){
             $dafestival.dialog({
                width:600,
                height:300,
                title:"修改节假日信息",
                iconCls:"icon-man",
                href:'/sysmanager/back/main/sys/festival/update.jsp?id='+id,
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveUpdateFestival,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        }
        function addFestival() {
             $dafestival.dialog({
                width:600,
                height:300,
                title:"添加posIp",
                iconCls:"icon-man",
                href:'/sysmanager/back/main/sys/festival/add.jsp',
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveAddFestival,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        }
        //保存添加
        function saveAddFestival(){
            $("#addFestivalForm").form('submit',{
                url:'/sysmanager/festival/add',
                success:function(){
                     $dafestival.dialog('close',true);
                     $dgfestival.datagrid('reload');
                }
            });
        }
        //保存修改
        function saveUpdateFestival(){
            $("#festivalUpdateForm").form('submit',{
                url:'/sysmanager/festival/update',
                success:function(){
                     $dafestival.dialog('close',true);
                    $dgfestival.datagrid('reload');
                }
            });
        }
        //关闭对话框
        function closeDa(){
             $dafestival.dialog('close',true);
        }
    </script>
    </head>
    <body>
    <div  class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',">
            <table id="festivalDg" ></table>
            <div id="festivalDa"></div>
            <div id="festivaltb">
                <a href="#" onclick="addFestival()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
            </div>
        </div>
    </div>
    </body>
</html>