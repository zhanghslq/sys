<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<input id="roleId" name="id" type="hidden" value="${param.id}">
<ul id="tree" class="easyui-tree" url="/sysmanager/permission/queryAll?rid=${param.id}" checkbox="true"></ul>
<script type="text/javascript">
/* $("#tree").tree({
	onCheck:function(node){//当点击 checkbox 时触发
	    var  node1=$(tree).tree('getParent',node.target);          //得到父节点
	    $('#tree').tree('check', node1.target);
	}
}); */
/* $('#tt').tree({
	onContextMenu: function(e, node){
		e.preventDefault();
		// 查找节点
		$('#tt').tree('select', node.target);
		// 显示快捷菜单
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
	}
}); */

	
</script>