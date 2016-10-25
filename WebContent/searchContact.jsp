<%@page import="domain.metier.Contact"%>
<%@page import="java.util.List"%>
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
<title>Search contact</title>
</head>

<body>
	<div id="header"></div>

	<%
		String message = ((String)request.getAttribute("message"));
		if(message != null){
			out.print("<b><font color=\"red\">"+message+"</font></b><br><br>");
		}
		
		@SuppressWarnings("unchecked")
		List<Contact> contacts = ((List<Contact>) request.getAttribute("contacts"));
		
		if(contacts != null && !contacts.isEmpty()){
	%>
			<table cellpadding="10" cellspacing="2" border="1">
				<tr>
					<th>First name</th>
					<th>Last name</th>
					<th>Email</th>
				</tr>
	<%
				for (Contact c : contacts) {
					out.print("<tr>");
					out.print("<td>" + c.getFirstName() + "</td>");
					out.print("<td>" + c.getLastName() + "</td>");
					out.print("<td>" + c.getEmail() + "</td>");
					out.print("</tr>");
				}
	%>
			</table><br><br>  <!-- obligatoire car il y a une balise <table> au dessus -->
			<hr><br><b>New search :</b><br><br>
	<%
		}
	%>

	<form method=get action="SearchContact">
		First Name : <input type="text" name="firstname"><br>
		And/or Last Name : <input type="text" name="lastname"><br>
		And/or Email : <input type="text" name="email"><br>
	
		<br/>
		
		<input class="button" type="submit" value="Search">
		<input class="button" type="reset" value="Reset">
		
	</form>
	
</body>
<script src="bootstrap/jquery.min.js"></script>
<% if(request.getSession().getAttribute("acc")==null){ %>
<script> (function() { $("#header").load("header/headerNotConnected.html"); })(); </script>
<% } else { %>
<script> (function() { $("#header").load("header/headerConnected.html"); })(); </script>
<% } %>

</html>