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

	<%
	String sign = (String) request.getParameter("SignInOrUp");
	if(sign!=null && sign.equals("up")){
	%>
		<title>Sign up</title>
	<%		
	} else if (sign!=null && sign.equals("in")){
	%>
		<title>Sign in</title>
	<% } %>

</head>

<body>
	<div id="header"></div>
	
	<div class="container">
	<form method=post action="SignServlet">
	<div class="from-group col-md-5">
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
		<%}
		
		// récupération des éléments déjà renseignés (cas d'erreur)
		String login = request.getParameter("login")==null ? "" : request.getParameter("login");
		String password = request.getParameter("password")==null ? "" : request.getParameter("password");
		
		if(sign==null || (!sign.equals("up") && !sign.equals("in")) ){
			out.print("<b><font color=\"red\">Ni connexion, ni inscription...</font></b><br><br>");
		
		} else if(sign.equals("up")){
	%>
			<fieldset style="margin-top: 5%; margin-bottom: 5%;">
    			<legend style="color: #5826AB80;">Sign up</legend>
   			</fieldset>
			<input type="hidden" name="SignInOrUp" value="up" />
			Login : <input type="text" class="form-control" name="login" value="<%=login %>"><br>
			Password : <input type="text" class="form-control" name="password" value="<%=password %>"><br> 
			Repeated password : <input type="text" class="form-control" name="secondPassword"><br> 
			
			<br/> 
			<button class="btn btn-info" type="submit">Sign up</button>
			<button class="btn" type="reset">Reset</button>
			
	<%		
		} else if (sign.equals("in")){
	%>
			<fieldset style="margin-top: 5%; margin-bottom: 5%;">
    			<legend style="color: #5826AB80;">Sign in</legend>
   			</fieldset>
			<input type="hidden" name="SignInOrUp" value="in" />
			Login : <input type="text" class="form-control" name="login" value="<%=login %>"><br>
			Password : <input type="text" class="form-control" name="password" value="<%=password %>"><br> 
			
			<br/> 
			<button class="btn btn-info" type="submit">Sign in</button>
			<button class="btn" type="reset">Reset</button>
	<% } %>
	</div>
	</form>
	</div>
	
</body>

<script src="utils/jquery.min.js"></script>
<% if(request.getSession().getAttribute("acc")==null){ %>
<script> (function() { $("#header").load("header/headerNotConnected.html"); })(); </script>
<% } else { %>
<script> (function() { $("#header").load("header/headerConnected.html"); })(); </script>
<% } %>

</html>