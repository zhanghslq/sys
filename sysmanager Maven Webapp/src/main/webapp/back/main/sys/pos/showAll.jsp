<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
    <script>
        var $dgpos, $dapos;
        $(function () {
            $dgpos = $("#posDg");
             $dapos = $("#posDa");
            $dgpos.datagrid({
                url: '${pageContext.request.contextPath}/pos/queryAll',
                fit:true,
                columns: [[
                    {title: "编号", field: "id", width: 200, align: 'center'},
                    {title: "IP", field: "pos_ip", width: 200, align: 'center'},
                    {
                        title: "操作", field: "options", width: 200, align: 'center',
                        formatter: function (value, row, index) {
                            return "<a class='del' onClick=\"delPos('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;" +
                                    "<a class='edit' onClick=\"editPos('" + row.id + "')\"  href='javascript:;'>修改</a>";
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
                toolbar:'#postb',
            });
        });

        //删除的操作
        function delPos(id){
            $.messager.confirm("提示","您确定要删除吗?",function(r){
                if(r){
                    $.post("${pageContext.request.contextPath}/pos/delete",{"id":id});
                    $dgpos.datagrid('reload');
                }
            });
        }
        //修改的操作
        function editPos(id){
             $dapos.dialog({
                width:600,
                height:300,
                title:"修改posIp",
                iconCls:"icon-man",
                href:'${pageContext.request.contextPath}/back/main/sys/pos/update.jsp?id='+id,
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveUpdatePos,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        }
        function addPos() {
             $dapos.dialog({
                width:600,
                height:300,
                title:"添加posIp",
                iconCls:"icon-man",
                href:'${pageContext.request.contextPath}/back/main/sys/pos/add.jsp',
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:saveAddPos,
                },{
                    text:'关闭',
                    iconCls:'icon-cancel',
                    handler:closeDa,
                }],
            });
        }
        //保存添加
        function saveAddPos(){
            $("#addPosForm").form('submit',{
                url:'${pageContext.request.contextPath}/pos/add',
                success:function(){
                     $dapos.dialog('close',true);
                    $dgpos.datagrid('reload');
                }
            });
        }
        //保存修改
        function saveUpdatePos(){
            $("#posUpdateForm").form('submit',{
                url:'${pageContext.request.contextPath}/pos/update',
                success:function(){
                     $dapos.dialog('close',true);
                    $dgpos.datagrid('reload');
                }
            });
        }
        //关闭对话框
        function closeDa(){
             $dapos.dialog('close',true);
        }
    </script>
    <div  class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',">
            <table id="posDg" ></table>
            
            <div id="posDa"></div>
            <div id="postb">
                <a href="#" onclick="addPos()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
            </div>
        </div>
    </div>