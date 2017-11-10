<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java"%>
<link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/IconExtension.css">
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
    <script src="/sysmanager/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="/sysmanager/back/easyui/js/form.validator.rules.js"></script>
    <script src="/sysmanager/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="/sysmanager/back/echar/echarts.js"></script>
<script>
    var $dg,$da;
    $(function () {
        $dg = $("#stuDg");
        $da = $("#stuDa");
        $dg.datagrid({
            url: '/sysmanager/user/queryAll',
            //fit:true,
            width:'100%',
            height:'100%',
            columns: [[
                {title: "编号", field: "id",width:100, align: 'center'},
                {title: "姓名", field: "name",width:100,  align: 'center'},
                {title: "法名", field: "farmington",width:100, align: 'center'},
                {title: "真实姓名", field: "nickname",width:100, align: 'center'},
                {title: "头像", field: "photo",width:100, align: 'center'},
                {title: "住址", field: "location",width:100, align: 'center'},
                {title: "个性签名", field: "description",width:100, align: 'center'},
                {title: "电话", field: "phone",width:100, align: 'center'},
                {title: "密码", field: "password",width:100, align: 'center'},
                {title: "性别", field: "sex",width:100, align: 'center'},
                {
                    title: "操作", field: "options", width: 160, align: 'center',
                    formatter: function (value, row, index) {
                        return "<a class='del' onClick=\"del('" + row.id + "')\" href='javascript:;'>删除</a>&nbsp;&nbsp;";
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
            pagination:true,
            pageNumber:2,
            pageSize:2,
            pageList:[2,4,6,8,10],
            toolbar:'#tb',


        });
    });

    //删除的操作
    function del(id){
        $.messager.confirm("提示","您确定要删除吗?",function(r){
            if(r){
                //发送异步请求删除数据
                $.post("/sysmanager/user/delete",{"id":id});
                $dg.datagrid('reload');
            }
        });
    }


    //修改的操作
    function editRow(id){
        $da.dialog({
            width:600,
            height:300,
            title:"员工的详细信息",
            iconCls:"icon-man",
            href:'/sysmanager/back/main/user/update.jsp?id='+id,
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
        $("#inputForm").form('submit',{
            url:'xiaohei',
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

    //搜索的处理程序
    function search(value,name){
        console.log(value);
        console.log(name);
        $dg.datagrid('load',{
           name:value
        });
    }

</script>


    <div  class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north'," style="height:50px;">
            <div style="text-align: center;margin: 10px 0px 10px 0px;">
                <input id="ss" class="easyui-searchbox" style="width:300px"
                       data-options="searcher:search,prompt:'Please Input Value',menu:'#mm'"/>
                <div id="mm" style="width:120px">
                    <div data-options="name:'name',iconCls:'icon-ok'">名称</div>
                    <div data-options="name:'age'">年龄</div>
                </div>
            </div>
        </div>
        <div data-options="region:'center',">
            <table id="stuDg" ></table>


            <div id="stuDa"></div>

        </div>
    </div>