<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="utils/bootstrap.min.css" rel="stylesheet">
<link href="utils/general.css" rel="stylesheet">
<title>Démo</title>
</head>

<body>
	<div id="header"></div>
	<div class="container">
	<%
		String message = ((String)request.getAttribute("message"));
		Boolean success = ((Boolean)request.getAttribute("success"));
		if(message != null){
			if(success==null || !success){ %>
				<div class="alert alert-dismissable alert-danger">
			<% } else { %>
				<div class="alert alert-dismissable alert-success">
			<% }%>
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				<h4><%=message %></h4> 
			
			<% if(success==null || !success){ %>
				</div>
			<% } else { %>
				</div>
			<% }%>
		<%}%>

	<ul>
		<li><a href="<%=request.getContextPath()%>/PeuplerDatabaseServlet">Peupler la base avec des contacts</a></li>
	</ul>
	</div>
	
</body>

<script src="utils/jquery.min.js"></script>
<% if(request.getSession().getAttribute("acc")==null){ %>
<script> (function() { $("#header").load("header/headerNotConnected.html"); })(); </script>
<% } else { %>
<script> (function() { $("#header").load("header/headerConnected.html"); })(); </script>
<% } %>

</html>