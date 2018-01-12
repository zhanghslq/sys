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
        var $dgpos, $dapos;
        $(function () {
            $dgpos = $("#posDg");
             $dapos = $("#posDa");
            $dgpos.datagrid({
                url: '/sysmanager/heart/queryAll',
                fit:true,
                columns: [[
                    {title: "编号", field: "id", width: 200, align: 'center'},
                    {title: "油站名", field: "name", width: 200, align: 'center'},
                    {title: "信息", field: "message", width: 200, align: 'center'},
                    {title: "最近数据传输时间", field: "lastTime", width: 200, align: 'center'},
                  ]],
                onLoadSuccess: function (data) {
                },
            });
        });
    </script>
    </head>
    <body>
    <div  class="easyui-layout" data-options="fit:true" style="width: 100%;height: 80%;min-width: 800px;min-height: 1000px">
        <div data-options="region:'center',">
            <table id="posDg" ></table>
            <div id="posDa"></div>
            <div id="postb">
            </div>
        </div>
    </div>
    </body>
</html>