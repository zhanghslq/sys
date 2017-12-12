<%@ page contentType="text/html;charset=UTF-8"  isELIgnored="false" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>油站营业额</title>
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/sysmanager/back/easyui/css/IconExtension.css">
    <link rel="stylesheet" href="/sysmanager/back/datepicker/assets/css/amazeui.min.css"/>
	<link rel="stylesheet" href="/sysmanager/back/datetimepicker-master/css/amazeui.datetimepicker.css"/>
	<link rel="stylesheet" href="/sysmanager/back/platform2/css/common.css" />
    <link rel="stylesheet" href="/sysmanager/back/platform2/css/index.css" />
    <script src="/sysmanager/back/easyui/js/jquery.min.js"></script>
    <script src="/sysmanager/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="/sysmanager/back/easyui/js/form.validator.rules.js"></script>
    <script src="/sysmanager/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="/sysmanager/back/echar/echarts.js"></script>
    <script src="/sysmanager/back/datetimepicker-master/js/amazeui.datetimepicker.js"></script>
</head>
              <table id="tt" style="height:auto;width:auto" ></table>
              
        <script type="text/javascript" src="${basePath}/resources/easyui/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="${basePath}/resources/js/operate/PrepayRecord.js"></script>
        <script type="text/javascript" src="${basePath}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
   </body>
    <script type="text/javascript">
        $(function(){
            init(1,2,'${basePath}');
        })
    </script>
<script>
var basePath = '';
function init(comFlag,userType,serverPath) {
    basePath = serverPath;
    $("#tt").datagrid({
        url : null,
        singleSelect : true, /*是否选中一行*/
        width:'auto', 
        pagination : true,/*是否显示下面的分页菜单*/
        border:false,
        rownumbers:true,
        columns : [ [  {
            field : 'tradeSn', //这个对应的是实体类类里面属性
            title : 'id号',
            width : '160',
            align : 'center'
        }, {
            field : 'shortName',
            title : '商户名称',
            width : '50',
            align : 'center'
        }/*这里省略了其他的field*/
        ]]/*,
        loadMsg : '数据加载中,请稍候......'*/
    });
    //分页
    var pager=$('#tt').datagrid('getPager');
    pager.pagination({
        total:0,
        rows : 0,
        pageNumber : 1,
        pageList : [ 10,20,30 ],// 可以设置每页记录条数的列表
        onBeforeRefresh: function () {  
        },  
        onSelectPage: function (pageNumber, pageSize) {//分页触发  
            find(pageNumber, pageSize);  
        }
    });
}

function find(pageNumber, pageSize){
    if(validate())
    {
        $("#tt").datagrid('getPager').pagination({pageSize : pageSize, pageNumber : pageNumber});//重置
        $("#tt").datagrid("loading"); //加屏蔽
        $.ajax({
            type : "POST",
            dataType : "json",
            url : basePath+"/prepay/prepayPageList.html",
            data : {
                'page' : pageNumber,
                'rows' : pageSize
            },
            success : function(data) {
                $("#tt").datagrid('loadData',pageData(data.rows,data.total));//这里的pageData是我自己创建的一个对象，用来封装获取的总条数，和数据，data.rows是我在控制器里面添加的一个map集合的键的名称
                var total =data.total;
                $("#tt").datagrid("loaded"); //移除屏蔽
            },
            error : function(err) {
                $.messager.alert('操作提示', '获取信息失败...请联系管理员!', 'error');
                $("#tt").datagrid("loaded"); //移除屏蔽
            }
        });
    }
}

function pageData(list,total){
    var obj=new Object(); 
    obj.total=total; 
    obj.rows=list; 
    return obj; 
}
</script>

</body>
</html>