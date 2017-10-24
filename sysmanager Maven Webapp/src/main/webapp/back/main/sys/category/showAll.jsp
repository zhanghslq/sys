<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
    <script>
        var $dgCate,$daCate;
        $(function () {
            $dgCate = $("#categoryDg");
            $daCate = $("#categoryDa");
            $dgCate.datagrid({
                url: '${pageContext.request.contextPath}/category/queryAll',
                fit:true,
                columns: [[
                    {title: "编号", field: "id", width: 200, align: 'center'},
                    {title: "类别", field: "name", width: 200, align: 'center'},
                    {
                        title: "操作", field: "options", width: 200, align: 'center',
                        formatter: function (value, row, index) {
                            return "<a class='del' onClick=\"del('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;" +
                                    "<a class='edit' onClick=\"editCate('" + row.id + "')\"  href='javascript:;'>修改</a>";
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
                    $dgCate.datagrid('reload');
                }
            });
        }
        //修改的操作
        function editCate(id){
            $daCate.dialog({
                width:600,
                height:300,
                title:"修改类别",
                iconCls:"icon-man",
                href:'${pageContext.request.contextPath}/back/main/sys/category/update.jsp?id='+id,
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveEditCate,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        }
        function addCategory() {
            $daCate.dialog({
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
                    $daCate.dialog('close',true);
                    $dgCate.datagrid('reload');
                }
            });
        }
        //保存修改
        function saveEditCate(){
            $("#categoryUpdateForm").form('submit',{
                url:'${pageContext.request.contextPath}/category/update',
                success:function(){
                    $daCate.dialog('close',true);
                    $dgCate.datagrid('reload');
                }
            });
        }
        //关闭对话框
        function closeDa(){
            $daCate.dialog('close',true);
        }
    </script>
    <div  class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',">
            <table id="categoryDg" ></table>
            
            <div id="categoryDa"></div>
            <div id="categorytb">
                <a href="#" onclick="addCategory()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
            </div>
        </div>
    </div>