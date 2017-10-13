<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" import="java.util.*" language="java" %>
<%-- <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> --%>
<html>
<head>
    <title>北京壳牌后台展示</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/css/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/easyui/css/IconExtension.css">
    <script src="${pageContext.request.contextPath}/back/easyui/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/jquery.easyui.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/form.validator.rules.js"></script>
    <script src="${pageContext.request.contextPath}/back/easyui/js/easyui-lang-zh_CN.js"></script>
    <script src="${pageContext.request.contextPath}/back/editor/kindeditor-common.js"></script>
    <script src="${pageContext.request.contextPath}/back/editor/kindeditor.js"></script>
    <script src="${pageContext.request.contextPath}/back/editor/lang/zh_CN.js"></script>
    <script src="${pageContext.request.contextPath}/back/echar/echarts.js"></script>
    <script>
        var $aa,$tt;
        $(function(){
            $aa = $("#me");
            $tt = $("#tt");
            //初始化系统菜单
            $.post("/sysmanager/menu/queryAll",function(menus){
                $.each(menus,function(i,menu){
                    var content = "<div style='text-align: center;'>";
                    $.each(menu.menus,function(j,child){
                    	if(child.name!='首页'){
                        	content +="<div onclick=\"addTabs('"+child.name+"','"+child.icon+"','"+child.href+"')\" class='easyui-linkbutton' data-options=\"plain:true,iconCls:'"+child.icon+"'\" style='border:1px solid green; width:90%;margin: 5 0 5 0 ;'>"+child.name+"</div>";
                    	}
                        if(child.name='首页'){//打开的
                       	 if(!$tt.tabs('exists','首页')){
                                $tt.tabs('add',{
                               	 title:child.name,
                                    iconCls:child.icon,
                                    href:"${pageContext.request.contextPath}/"+child.href,
                                    closable:true,
                                    selected:true,
                                });
                            }else{
                                $tt.tabs('select','首页');
                            }
                       };
                       
                    });
                    content +="</div>";
               /*  <shiro:hasRole name="admin">
                    if(menu.name!='系统管理'){
                        $aa.accordion('add',{
                            title:menu.name,
                            iconCls:menu.icon,
                            content:content,
                        });
                    };
                </shiro:hasRole> */
                    
                $aa.accordion('add',{
                    title:menu.name,
                    iconCls:menu.icon,
                    content:content,
                });
               /*  <shiro:hasRole name="super">
                    $aa.accordion('add',{
                        title:menu.name,
                        iconCls:menu.icon,
                        content:content,
                    });
                </shiro:hasRole> */
               

                });

            },"JSON");
        });

        
        //添加选项卡
        function  addTabs(title,icon,href){
            /* if(!$tt.tabs('exists',title)){
                $tt.tabs('add',{
                    title:title,
                    iconCls:icon,
                    href:"${pageContext.request.contextPath}/"+href,
                    closable:true,
                });
            }else{ */
                $tt.tabs('select',title);
           // }
        }
        
    </script>
</head>
<body class="easyui-layout">

<div data-options="region:'north',split:false" style="height:100px;">
    <h5>当前用户：<!-- <shiro:principal/> -->&nbsp;&nbsp;&nbsp;&nbsp;<!-- <a href="/baizhi_cmfz_sys/admin/logout">退出</a> --></h5>
    <h1 align="center">北京壳牌</h1>
</div>

<div data-options="region:'south',split:false" style="height:40px;"></div>


<div data-options="region:'west',title:'系统菜单',split:false" style="width:200px;">

    <div id="me" class="easyui-accordion" data-options="fit:true">

    </div>
</div>

<div data-options="region:'center',iconCls:'icon-house'">
	
    <div id="tt" class="easyui-tabs" data-options="fit:true">
    </div>
</div>
</body>
</html>
