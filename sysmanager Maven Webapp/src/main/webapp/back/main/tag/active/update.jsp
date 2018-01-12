<%@ page  contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<div style="text-align: center;">
    <form id="activeUpdateForm" method="post" enctype="multipart/form-data">
        <div style="margin-top: 70px;">
            <input name="id" type="hidden" value="${param.id}">
        </div>
        <div style="margin-top: 10px;">
          	 上传会员信息:<input  name="multipartFile"  type="file"/><br/>
        </div>
    </form>
</div>
<script>
   /*  $(function(){
        //构建子页面元素的操作
        $("#posUpdateForm").form('load','${pageContext.request.contextPath}/pos/queryById?id='+'${param.id}');
    }); */
</script>