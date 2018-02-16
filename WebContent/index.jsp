<%@ page contentType="text/html;charset=GB2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
%>
<HTML>
<BODY bgcolor=cyan>
	<Font size=5>
		<P>Neo DoorServer
		</FORM>
</BODY>
</HTML>