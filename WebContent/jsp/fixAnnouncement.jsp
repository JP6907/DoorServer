<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>慧开门</title>
		<link href="<%=basePath %>/css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>/js/jquery-1.8.0.min.js"></script>
		<script src="<%=basePath%>/js/list.js"></script>
	</head>
	<%
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		request.getSession().setAttribute("id", id);
	%>
	<body style="background: #e1e9eb;">
		<form action="#" id="fixAnnouncementForm" method="post">
			<div class="right">
				<div class="current">当前位置：<a href="javascript:void(0)" style="color:#6E6E6E;">后台管理</a> &gt; 发布公告<a href="<%=basePath%>loginOut.action" style="float:right;">退出</a></div>
				<div class="rightCont">
					<p class="g_title fix">发布公告&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn03" href="<%=basePath %>announcementList.action">返回</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn03" href="javascript:fixAnnouncement('<%=basePath%>')">确认修改</a></p>
					<table class="tab1">
						<tbody>
							<tr>
								<td align="right">公告标题：</td>
								<td>
									<textarea name="title" cols = "50" rows = "1" style="resize:none;font-size:20px" ><%=title %></textarea>
								</td>
	       					</tr>
	       					<tr>
								<td align="right">公告内容：</td>
								<td>
									<textarea name="content" cols = "50" rows = "5" style="resize:none;font-size:20px" ><%=content %></textarea>
								</td>
	       					</tr>
						</tbody>
					</table>
				</div>
			</div>
	    </form>
	</body>
</html>