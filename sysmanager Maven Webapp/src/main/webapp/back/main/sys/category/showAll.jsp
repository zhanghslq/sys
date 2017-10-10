<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
    <script>
        var $dg,$da;
        $(function () {
            $dg = $("#categoryDg");
            $da = $("#categoryDa");
            $dg.datagrid({
                url: '${pageContext.request.contextPath}/category/queryAll',
                fit:true,
                columns: [[
                    {title: "编号", field: "id", width: 200, align: 'center'},
                    {title: "类别", field: "name", width: 200, align: 'center'},
                    {
                        title: "操作", field: "options", width: 200, align: 'center',
                        formatter: function (value, row, index) {
                            return "<a class='del' onClick=\"del('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;" +
                                    "<a class='edit' onClick=\"editRow('" + row.id + "')\"  href='javascript:;'>修改</a>";
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
                toolbar:'#categorytb',
            });
        });

        //删除的操作
        function del(id){
            $.messager.confirm("提示","您确定要删除吗?",function(r){
                if(r){
                    $.post("${pageContext.request.contextPath}/category/delete",{"id":id});
                    $dg.datagrid('reload');
                }
            });
        }
        //修改的操作
        function editRow(id){
            $da.dialog({
                width:600,
                height:300,
                title:"修改类别",
                iconCls:"icon-man",
                href:'${pageContext.request.contextPath}/back/main/sys/category/update.jsp?id='+id,
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveEdit,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        }
        function add() {
            $da.dialog({
                width:600,
                height:300,
                title:"添加类别",
                iconCls:"icon-man",
                href:'${pageContext.request.contextPath}/back/main/sys/category/add.jsp',
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveCate,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        }
        //保存添加
        function saveCate(){
            $("#addCateForm").form('submit',{
                url:'${pageContext.request.contextPath}/category/add',
                success:function(){
                    $da.dialog('close',true);
                    $dg.datagrid('reload');
                }
            });
        }
        //保存修改
        function saveEdit(){
            $("#categoryUpdateForm").form('submit',{
                url:'${pageContext.request.contextPath}/category/update',
                success:function(){
                    $da.dialog('close',true);
                    $dg.datagrid('reload');
                }
            });
        }
        //关闭对话框
        function closeDa(){
            $da.dialog('close',true);
        }
    </script>
    <div  class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',">
            <table id="categoryDg" ></table>
            
            <div id="categoryDa"></div>
            <div id="categorytb">
                <a href="#" onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
            </div>
        </div>
    </div>