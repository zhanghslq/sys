<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
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
                {title: "编号", field: "id", width: 200, align: 'center'},
                    {title: "油站名", field: "name", width: 200, align: 'center'},
                    {title: "所在城市", field: "city", width: 200, align: 'center'},
                    {
                        title: "类别", field: "category", width: 200, align: 'center',
	                        	formatter: function (value, row, index) {
	                        		if(typeof(row.category)!="undefined"){
	                            		return row.category.name;
	                        		}else{
	                        			return " ";
	                        		}
	                        	}
                        
                    },
                    {
                        title: "标签", field: "tag", width: 200, align: 'center',
	                        	formatter: function (value, row, index) {
	                        		if(typeof(row.tags)!="undefined"){
	                        			var option=" ";
	                        			$.each(row.tags,function(i,tag){//这里不能使用下拉框了，改为一对多的复选框
	                    					option+=tag.name+"  ";
	                    					
	                    				});//遍历完后
	                    				return option;
	                        		}else{
	                        			return " ";
	                        		}
	                        	}
                        
                    },
                    {title: "操作", field: "options", width: 160, align: 'center',
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
            height:300,
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
<div  class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',">
        <table id="stationDg" ></table>
        <div id="stationDa"></div>
    </div>
</div>
</body>
</html>