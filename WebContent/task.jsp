<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="../../favicon.ico">
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<title>Mes contacts</title>
</head>

<body>
	<div id="header"></div>
	<%
		String message = ((String)request.getAttribute("message"));
		if(message != null){
			out.print("<b><font color=\"red\">"+message+"</font></b><br><br>");
		}
	%>

	<ul>
		<li><a href="addContact.jsp">Add contact</a></li>
		<li><a href="deleteContact.jsp">Remove contact</a></li>
		<li><a href="updateContact.jsp">Update contact</a></li>
		<li><a href="searchContact.jsp">Search contact</a></li>
	</ul>
	
</body>

<script src="bootstrap/jquery.min.js"></script>
<% if(request.getSession().getAttribute("acc")==null){ %>
<script> (function() { $("#header").load("header/headerNotConnected.html"); })(); </script>
<% } else { %>
<script> (function() { $("#header").load("header/headerConnected.html"); })(); </script>
<% } %>

</html>