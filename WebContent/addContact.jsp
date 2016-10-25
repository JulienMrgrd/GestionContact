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
<title>Ajouter un contact</title>
</head>

<body>
	<div id="header"></div>

	<%
		String message = ((String)request.getAttribute("message"));
		if(message != null){
			out.print("<b><font color=\"red\">"+message+"</font></b><br><br>");
		}
		
		// récupération des éléments déjà renseignés (cas d'erreur)
		String firstname = request.getParameter("firstname")==null ? "" : request.getParameter("firstname");
		String lastname = request.getParameter("lastname")==null ? "" : request.getParameter("lastname");
		String email = request.getParameter("email")==null ? "" : request.getParameter("email");
		
		String street = request.getParameter("street")==null ? "" : request.getParameter("street");
		String city = request.getParameter("city")==null ? "" : request.getParameter("city");
		String zip = request.getParameter("zip")==null ? "" : request.getParameter("zip");
		String country = request.getParameter("country")==null ? "" : request.getParameter("country");
	%>

	<form method=post action="NewContactServlet">
		First Name : <input type="text" name="firstname" value="<%=firstname %>"><br>
		Last Name : <input type="text" name="lastname" value="<%=lastname %>"><br> 
		Email : <input type="text" name="email" value="<%=email %>"><br> 
		Street : <input type="text" name="street" value="<%=street %>"><br>
		City : <input type="text" name="city" value="<%=city %>"><br> 
		Zip : <input type="text" name="zip" value="<%=zip %>"><br> 
		Country : <input type="text" name="country" value="<%=country %>"><br> 
		
		<br/> 
		<input class="button" type="submit" value="Submit"> 
		<input class="button" type="reset" value="Reset">

	</form>
	
	<br/>
</body>

	<script src="bootstrap/jquery.min.js"></script>
	<% if(request.getSession().getAttribute("acc")==null){ %>
	<script> (function() { $("#header").load("header/headerNotConnected.html"); })(); </script>
	<% } else { %>
	<script> (function() { $("#header").load("header/headerConnected.html"); })(); </script>
	<% } %>

</html>