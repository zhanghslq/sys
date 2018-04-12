<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
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
    var $dg,$da;
    $(function () {
        $dg = $("#stationDg");
        $da = $("#stationDa");
        $dg.datagrid({
            url: '/sysmanager/station/queryAll',
            fit:true,
            width:'100%',
            height:'100%',
            columns: [[
                {title: "编号", field: "id", width: 70, align: 'center'},
                    {title: "油站名", field: "name", width: 100, align: 'center'},
                    {title: "所在城市", field: "city", width: 70, align: 'center'},
                    {title: "汽油商圈类型", field: "gasoline", width: 80, align: 'center'},
                    {title: "柴油商圈类型", field: "diesel", width: 80, align: 'center'},
                    {title: "位置", field: "location", width: 60, align: 'center'},
                    {title: "开业日期", field: "openDate", width: 100, align: 'center'},
                    {title: "销售区域", field: "salesArea", width: 100, align: 'center'},
                    {title: "行政区", field: "administraiveRegion", width: 80, align: 'center'},
                    {title: "油站类型", field: "type", width: 60, align: 'center'},
                    {title: "操作", field: "options", width: 100, align: 'center',
                    formatter: function (value, row, index) {
                        return "<a class='edit' onClick=\"editSta('" + row.id + "')\"  href='javascript:;'>修改</a>";
                    }
                }
            ]],
            onLoadSuccess: function (data) {
            	$(".edit").linkbutton({
                    plain: true,
                    iconCls: 'icon-edit',
                });
            },
        });
    });

    function editSta(id){
        $da.dialog({
            width:600,
            height:450,
            title:"修改油站信息",
            iconCls:"icon-man",
            href:'/sysmanager/back/main/sys/station/update.jsp?id='+id,
            buttons:[{
                text:'保存',
                iconCls:'icon-save',
                handler:saveSta,
            },{
                text:'关闭',
                iconCls:'icon-cancel',
                handler:closeDa,
            }],
            
        });
        
    }
    
   
    //保存用户
    function saveSta(){
        $("#staUpdateForm").form('submit',{
            url:'/sysmanager/station/update',
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

</head>
<body>
<div  class="easyui-layout" data-options="fit:true" style="width: 100%;height: 100%;min-width: 800px;min-height: 1500px">
    <div data-options="region:'center',">
        <table id="stationDg" ></table>
        <div id="stationDa"></div>
    </div>
</div>
</body>
</html>