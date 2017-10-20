<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
    <script>
        $(function () {
            $("#tagDg").datagrid({
                url: '${pageContext.request.contextPath}/tag/queryAll',
                fit:true,
                columns: [[
                    {title: "编号", field: "id", width: 200, align: 'center'},
                    {title: "标签名字", field: "name", width: 200, align: 'center'},
                    {
                        title: "操作", field: "options", width: 200, align: 'center',
                        formatter: function (value, row, index) {
                            return "<a class='del' onClick=\"del('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;" +
                                    "<a class='edit' onClick=\"editTag('" + row.id + "')\"  href='javascript:;'>修改</a>";
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
                toolbar:'#tagTb',
            });
        });

        //删除的操作
        function del(id){
            $.messager.confirm("提示","您确定要删除吗?",function(r){
                if(r){
                    $.post("${pageContext.request.contextPath}/tag/delete",{"id":id});
                    $("#tagDg").datagrid('reload');
                }
            });
        }
        //修改的操作
        function editTag(id){
            $("#tagDa").dialog({
                width:600,
                height:300,
                title:"修改标签信息",
                iconCls:"icon-man",
                href:'${pageContext.request.contextPath}/back/main/sys/tag/update.jsp?id='+id,
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveTag,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        }
        function addTag() {
            $("#tagDa").dialog({
                width:600,
                height:300,
                title:"添加标签",
                iconCls:"icon-man",
                href:'${pageContext.request.contextPath}/back/main/sys/tag/add.jsp',
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveAddTag,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        }
        //保存用户
        function saveAddTag(){
            $("#tagAddForm").form('submit',{
                url:'${pageContext.request.contextPath}/tag/add',
                success:function(){
                	$("#tagDa").dialog('close',true);
                    $("#tagDg").datagrid('reload');
                }
            });
        }
      //保存修改的标签
        function saveTag(){
            $("#tagUpdateForm").form('submit',{
                url:'${pageContext.request.contextPath}/tag/update',
                success:function(){
                    $("#tagDa").dialog('close',true);
                    $("#tagDg").datagrid('reload');
                }
            });
        }
        //关闭对话框
        function closeDa(){
            $("#tagDa").dialog('close',true);
        }
    
    </script>
    <div  class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',">
            <table id="tagDg" ></table>


            <div id="tagDa"></div>
            <div id="tagTb">
                <a href="#" onclick="addTag()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
            </div>
        </div>
    </div>