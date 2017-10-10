<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<script>
    var $dg,$da;
    $(function () {
        $dg = $("#uDg");
        $da = $("#uDa");
        $dg.datagrid({
            url: '${pageContext.request.contextPath}/station/queryAll',
            fit:true,
            width:'100%',
            height:'100%',
            columns: [[
                {title: "编号", field: "id", width: 200, align: 'center'},
                    {title: "油站名", field: "name", width: 200, align: 'center'},
                    {title: "所在城市", field: "city", width: 200, align: 'center'},
                    {title: "类别", field: "category", width: 200, align: 'center'},
                    {title: "标签", field: "tag", width: 200, align: 'center'},
                    {title: "操作", field: "options", width: 160, align: 'center',
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
            },
            toolbar:'#tb',
        });
    });

    //删除的操作
    function del(id){
        $.messager.confirm("提示","您确定要删除吗?",function(r){
            if(r){
                //发送异步请求删除数据
                $.post("${pageContext.request.contextPath}/learn/delete",{"id":id});
                $dg.datagrid('reload');
            }
        });
    }
    function add() {
        $da.dialog({
            width:600,
            height:300,
            title:"添加功课",
            iconCls:"icon-man",
            href:'${pageContext.request.contextPath}/back/main/learn/add.jsp',
            buttons:[{
                text:'保存',
                iconCls:'icon-save',
                handler:saveStu,
            },{
                text:'关闭',
                iconCls:'icon-cancel',
                handler:closeDa,
            }],
        });
    }

    //保存用户
    function saveStu(){
        $("#adForm").form('submit',{
            url:'${pageContext.request.contextPath}/learn/save',
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
        <table id="uDg" ></table>


        <div id="uDa"></div>

    </div>
    <div id="tb">
        <a href="#" onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
    </div>
</div>