<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
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
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="">GestionContact</a>
    </div>
  </div>
</nav>
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
	
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="bootstrap/bootstrap.min.js"></script>
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>