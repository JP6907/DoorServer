<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.neo.bean.Announcement,java.util.ArrayList,java.util.List" %>
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
		<link href="<%=basePath %>css/all.css" rel="stylesheet" type="text/css" />
		<script src="<%=basePath%>js/jquery-1.8.0.min.js"></script>
		<script src="<%=basePath%>js/list.js"></script>
	</head>
	<body style="background: #e1e9eb;">
		<form action="#" id="announcementListForm" method="post">
			<div class="right">
				<div class="current">当前位置：<a href="javascript:void(0)" style="color:#6E6E6E;">后台管理</a> &gt; 公告列表     <a href="<%=basePath%>loginOut.action" style="float:right;">退出</a></div>
				<div class="rightCont">
					<p class="g_title fix">内容列表 <a class="btn03" href="<%=basePath %>jsp/publicaAnnouncement.jsp">发布公告</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn03" href="javascript:deleteBatch('<%=basePath%>')">删 除</a></p>
					<table class="tab1">
						<tbody>
							<tr>
								<td width="90" align="right">时间：</td>
								<td>
									<%
										String command = (String)request.getParameter("command");
										String description = (String)request.getParameter("description");
										if(command==null){
											command = "";
										}if(description==null){
											description= "";
										}
									%>
									<input name="command" type="text" class="allInput" value="<%=command%>" placeholder="时间..."/>
								</td>
								<td width="90" align="right">内容：</td>
								<td>
									<input name="description" type="text" class="allInput" value="<%=description%>" placeholder="内容..."/>
								</td>
	                            <td width="85" align="right"><input type="submit" class="tabSub" value="查 询" disabled="disabled"/></td>
	       					</tr>
						</tbody>
					</table>
					<div class="zixun fix">
						<table class="tab2" width="100%">
							<tbody>
								<tr>
								    <th><input type="checkbox" id="all" onclick="#"/></th>
								    <th>序号</th>
								    <th>公告时间</th>
								    <th>公告标题</th>
								    <th>公告内容</th>
								    <th>操作</th>
								</tr>
								<%
									ArrayList<Announcement> annList = (ArrayList<Announcement>)request.getAttribute("announcementlist");
									int index = 0;
									for(Announcement ann:annList){
								%>
								<tr	
										<% if(index++%2!=0){
											%>
											style="background-color:#ECF6EE;"
											<% 
										}
									%>
								>
									<td><input type="checkbox" name="id" value="<%=ann.getId()%>"/></td>
									<td><%=index %></td>
									<td><%=ann.getDate() %></td>
									<td><%=ann.getTitle() %></td>
									<td><%=ann.getContent() %></td>
									<td>
										<a href="jsp/fixAnnouncement.jsp?title=<%=ann.getTitle()%>&content=<%=ann.getContent()%>&id=<%=ann.getId()%>">修改</a>&nbsp;&nbsp;&nbsp;
										<a href="<%=basePath%>deleteOne.action?id=<%=ann.getId()%>">删除</a>
									</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<div class='page fix'>
							共 <b>1</b> 条
							<a href='###' class='first'>首页</a>
							<a href='###' class='pre'>上一页</a>
							当前第<span>1/1</span>页
							<a href='###' class='next'>下一页</a>
							<a href='###' class='last'>末页</a>
							跳至&nbsp;<input type='text' value='1' class='allInput w28' />&nbsp;页&nbsp;
							<a href='###' class='go'>GO</a>
						</div>
					</div>
				</div>
			</div>
	    </form>
	</body>
</html>