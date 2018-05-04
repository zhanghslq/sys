<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<input id="uname" name="name" type="hidden" value="${param.name}">
<ul id="tree"></ul>
<script type="text/javascript">
/* class="easyui-tree"  url='/sysmanager/permission/queryAll?rid=${param.id}' checkbox=true cascadeCheck=false */
$(function() {
	toolbar:'#tree';
	$("#tree").tree({
		url:'/sysmanager/userCrm/queryPermissionByName?name=${param.name}',
		checkbox:true,
/* 		cascadeCheck:false,
		onCheck:function(node){//当点击 checkbox 时触发
		    var  node1=$(tree).tree('getParent',node.target); //得到父节点
			if(node1!=null){
			    $('#tree').tree('check', node1.target);
			}
		}
 */	});
	toolbar:'#tree';
});
</script>