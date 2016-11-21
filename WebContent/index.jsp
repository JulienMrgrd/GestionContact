<%@page import="java.util.Enumeration"%>
<%@page import="domain.metier.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
.demo-long {
	margin-top: 100px;
	margin-bottom: 200px;
}
</style>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="../../favicon.ico">
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<div id="header"></div>
	
	<%
		String message = ((String)request.getAttribute("message"));
		
		if(message != null){
			out.print("<b><font color=\"red\">"+message+"</font></b><br><br>");
		}
	%>

	<div class="container">
		
		<h1>Auto-Hiding Bootstrap Navbar Demo</h1>

		<p class="demo-long">Long content...</p>
		<p class="demo-long">Long content...</p>
		<p class="demo-long">Long content...</p>
		<p class="demo-long">Long content...</p>
		<p class="demo-long">Long content...</p>
		<p class="demo-long">Long content...</p>
	</div>

</body>

<script src="bootstrap/jquery.min.js"></script>
<% if(request.getSession().getAttribute("acc")==null){ %>
<script> (function() { $("#header").load("header/headerNotConnected.html"); })(); </script>
<% } else { %>
<script> (function() { $("#header").load("header/headerConnected.html"); })(); </script>
<% } %>

</html>