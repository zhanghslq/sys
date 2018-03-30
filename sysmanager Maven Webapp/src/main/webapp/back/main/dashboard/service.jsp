<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" import="java.util.*" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<frameset rows="73,*" border="0" frameborder="no"  framespacing="0" >
  <frame name="serviceFrameMenu"  id="serviceFrameMenu" src="servicemenu.jsp" scrolling="No"></frame>
  <shiro:hasPermission name="bjdashboard">
	  <frame name="serviceFrameContent"  id="serviceFrameContent" src="dashboard.html" ></frame>
  </shiro:hasPermission>
   <shiro:lacksPermission name="bjdashboard">
   	 <frame name="serviceFrameContent"  id="serviceFrameContent" src="welcome.html" ></frame>
   </shiro:lacksPermission>
</frameset>