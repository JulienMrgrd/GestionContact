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

	<%
	String sign = (String) request.getParameter("SignInOrUp");
	if(sign!=null && sign.equals("up")){
	%>
		<title>Inscription</title>
	<%		
	} else if (sign!=null && sign.equals("in")){
	%>
		<title>Connexion</title>
	<% } %>

</head>

<body>
	<div id="header"></div>
	<form method=post action="SignServlet">
	<%
		String message = ((String)request.getAttribute("message"));
		if(message != null){
			out.print("<b><font color=\"red\">"+message+"</font></b><br><br>");
		}
		
		// récupération des éléments déjà renseignés (cas d'erreur)
		String login = request.getParameter("login")==null ? "" : request.getParameter("login");
		String password = request.getParameter("password")==null ? "" : request.getParameter("password");
		
		if(sign==null || (!sign.equals("up") && !sign.equals("in")) ){
			out.print("<b><font color=\"red\">Ni connexion, ni inscription...</font></b><br><br>");
		
		} else if(sign.equals("up")){
	%>
			<h1>Inscription</h1>
			<input type="hidden" name="SignInOrUp" value="up" />
			Login : <input type="text" name="login" value="<%=login %>"><br>
			Password : <input type="text" name="password" value="<%=password %>"><br> 
			Repeated password : <input type="text" name="secondPassword"><br> 
			
			<br/> 
			<input class="button" type="submit" value="Sign up"> 
			<input class="button" type="reset" value="Reset">
			
	<%		
		} else if (sign.equals("in")){
	%>
			<h1>Connexion</h1>
			<input type="hidden" name="SignInOrUp" value="in" />
			Login : <input type="text" name="login" value="<%=login %>"><br>
			Password : <input type="text" name="password" value="<%=password %>"><br> 
			
			<br/> 
			<input class="button" type="submit" value="Sign in"> 
			<input class="button" type="reset" value="Reset">
	<% } %>
	</form>
	
</body>

<script src="bootstrap/jquery.min.js"></script>
<% if(request.getSession().getAttribute("acc")==null){ %>
<script> (function() { $("#header").load("header/headerNotConnected.html"); })(); </script>
<% } else { %>
<script> (function() { $("#header").load("header/headerConnected.html"); })(); </script>
<% } %>

</html>